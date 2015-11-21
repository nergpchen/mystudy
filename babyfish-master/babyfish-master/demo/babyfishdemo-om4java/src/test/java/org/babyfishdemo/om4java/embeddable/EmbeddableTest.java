package org.babyfishdemo.om4java.embeddable;

import junit.framework.Assert;

import org.babyfishdemo.om4java.embeddable.Consultant;
import org.babyfishdemo.om4java.embeddable.Contact;
import org.babyfishdemo.om4java.embeddable.Customer;
import org.babyfishdemo.om4java.embeddable.ElectronicalInfo;
import org.babyfishdemo.om4java.embeddable.Name;
import org.junit.Test;

/**
 * @author Tao Chen
 */
public class EmbeddableTest {
    
    @Test
    public void testModifyEmbeddable() {
        
        Consultant consultant = createConsultantWithJamesAndDennis();
        Customer james = consultant.getCustomers().descendingIterator().next();
        
        {
        	/*
        	 * Initialized data,
        	 * 
        	 * The order of NavgiabeSet "consultant.getCustomers()" is: dennis < james
        	 */
        	Assert.assertEquals(
                    "["
                    + "{ "
                    +   "contact: { "
                    +     "name: { first: dennis, last: ritchie }, "
                    +     "electronicalInfo: { phone: dennis@x.com, email: 23456789123 } "
                    +   "} "
                    + "}, "
                    + "{ "
                    +   "contact: { "
                    +     "name: { first: james, last: gosling }, "
                    +     "electronicalInfo: { phone: james@x.com, email: 12345678912 } "
                    +   "} "
                    + "}"
                    + "]", 
                    consultant.getCustomers().toString()
            );
        }
        
        {
	        /*
	         * Now modify the of "james.contact.name" from "james gosling" to  "James Gosling".
	         */
	        james.getContact().setName(new Name("James", "Gosling"));
	        
	        /*
	         * (1) This modification can make the "Contact" feel the event about it has been modified.
	         * (2) the event can be bubbled to the parent "Customer" so that "Customer" can feel it has been modified.
	         * (3) the opposite site "Consultant" will feel the event and adjust its collection property "customers". 
	         * 
	         * So
	         * The NavigableSet "consultant.getCustomers()" will be adjusted automatically and implicitly,
	         * now its order become: "James" < "dennis"
	         */
	        Assert.assertEquals(
	                "["
	                + "{ "
	                +   "contact: { "
	                +     "name: { first: James, last: Gosling }, "
	                +     "electronicalInfo: { phone: james@x.com, email: 12345678912 } "
	                +   "} "
	                + "}, "
	                + "{ "
	                +   "contact: { "
	                +     "name: { first: dennis, last: ritchie }, "
	                +     "electronicalInfo: { phone: dennis@x.com, email: 23456789123 } "
	                +   "} "
	                + "}"
	                + "]", 
	                consultant.getCustomers().toString()
	        );
        }
    }
    
    @Test
    public void testModifyNestedEmbeddable() {
        
        Consultant consultant = createConsultantWithJamesAndDennis();
        Customer james = consultant.getCustomers().descendingIterator().next();
        
        {
        	/*
        	 * Initialized data,
        	 * 
        	 * The order of NavgiabeSet "consultant.getCustomers()" is: dennis < james
        	 */
        	Assert.assertEquals(
                    "["
                    + "{ "
                    +   "contact: { "
                    +     "name: { first: dennis, last: ritchie }, "
                    +     "electronicalInfo: { phone: dennis@x.com, email: 23456789123 } "
                    +   "} "
                    + "}, "
                    + "{ "
                    +   "contact: { "
                    +     "name: { first: james, last: gosling }, "
                    +     "electronicalInfo: { phone: james@x.com, email: 12345678912 } "
                    +   "} "
                    + "}"
                    + "]", 
                    consultant.getCustomers().toString()
            );
        }
        
        {
	        /*
	         * Now modify the of "james.contact.name.first" from "james" to  "James".
	         */
	        james.getContact().getName().setFirst("James");
	        
	        /*
	         * (1) This modification can make the "Name" feel the event that it has been modified.
	         * (2) the event can be bubbled to the parent "Contact" so that "Contact" can feel it has been modified.
	         * (3) the event can be bubbled to the parent "Customer" so that "Customer" can feel it has been modified.
	         * (4) the opposite site "Consultant" will feel the event and adjust its collection property "customers". 
	         * 
	         * So
	         * The NavigableSet "consultant.getCustomers()" will be adjusted automatically and implicitly,
	         * now its order become: "James" < "dennis"
	         */
	        Assert.assertEquals(
	                "["
	                + "{ "
	                +   "contact: { "
	                +     "name: { first: James, last: gosling }, "
	                +     "electronicalInfo: { phone: james@x.com, email: 12345678912 } "
	                +   "} "
	                + "}, "
	                + "{ "
	                +   "contact: { "
	                +     "name: { first: dennis, last: ritchie }, "
	                +     "electronicalInfo: { phone: dennis@x.com, email: 23456789123 } "
	                +   "} "
	                + "}"
	                + "]", 
	                consultant.getCustomers().toString()
	        );
        }
    }
    
    @Test
	public void testModifyNestedSharedEmbeddable() {
	    Name sharedName = new Name("james", "gosling");
	    Customer sunJames = new Customer();
	    sunJames.setContact(
	            new Contact(
	                    sharedName, 
	                    new ElectronicalInfo("james@sun.com", "12345678912")
	            )
	    );
	    Customer dennis = new Customer();
	    dennis.setContact(
	            new Contact(
	                    new Name("dennis", "ritchie"), 
	                    new ElectronicalInfo("dennis@x.com", "23456789123")
	            )
	    );
	    Customer oracleJames = new Customer();
	    oracleJames.setContact(
	            new Contact(
	                    sharedName, 
	                    new ElectronicalInfo("james@oracle.com", "12345678912")
	            )
	    );
	    
	    Consultant consultant = new Consultant();
	    consultant.getCustomers().add(sunJames);
	    consultant.getCustomers().add(dennis);
	    consultant.getCustomers().add(oracleJames);
	    
	    {
	        /*
	         * The "Consultant.customers" is NavigableSet and it uses 
	         * the "Customer.contact" to be its comparison rule,
	         * the "declaredProeprtiesOrder" of Contact is "name, electronicalInfo" 
	         * and the "declaredPropertiesOrder" of Name is "first, last",
	         * so the property with highest comparison priority is 
	         * "Customer.contact.name.first", finally, "dennis" < "james(oracle)" < "james(sun)"
	         */
	        Assert.assertEquals(
	                "["
	                + "{ "
	                +   "contact: { "
	                +     "name: { first: dennis, last: ritchie }, "
	                +     "electronicalInfo: { phone: dennis@x.com, email: 23456789123 } "
	                +   "} "
	                + "}, "
	                + "{ "
	                +   "contact: { "
	                +     "name: { first: james, last: gosling }, "
	                +     "electronicalInfo: { phone: james@oracle.com, email: 12345678912 } "
	                +   "} "
	                + "}, "
	                + "{ "
	                +   "contact: { "
	                +     "name: { first: james, last: gosling }, "
	                +     "electronicalInfo: { phone: james@sun.com, email: 12345678912 } "
	                +   "} "
	                + "}"
	                + "]", 
	                consultant.getCustomers().toString()
	        );
	    }
	    
	    /*
	     * Now modify the of "sharedName.first" from "james" to  "James".
	     */
	    sharedName.setFirst("James");
	    
	    /*
	     * (1) This modification can make both the "sunJames.contact.name" and "oracleJames.contact.name" 
	     *      feel the event about they have been modified.
	     * (2) the event can be bubbled to the parents, so both "sunJames.contact" and "oracleJames.contact" 
	     *      can feel they have been modified.
	     * (3) the event can be bubbled to the parents, so both "sunJames" so that "oracleJames" 
	     *      can feel they have been been modified.
	     * (4) the opposite site "Consultant" will feel the event and adjust its collection property "customers". 
	     * 
	     * So
	     * The NavigableSet "consultant.getCustomers()" will be adjusted automatically and implicitly,
	     * now its order become: "James(oracle)" < "James(sun)" < "dennis".
	     */
	    Assert.assertEquals(
	            "["
	            + "{ "
	            +   "contact: { "
	            +     "name: { first: James, last: gosling }, "
	            +     "electronicalInfo: { phone: james@oracle.com, email: 12345678912 } "
	            +   "} "
	            + "}, "
	            + "{ "
	            +   "contact: { "
	            +     "name: { first: James, last: gosling }, "
	            +     "electronicalInfo: { phone: james@sun.com, email: 12345678912 } "
	            +   "} "
	            + "}, "
	            + "{ "
	            +   "contact: { "
	            +     "name: { first: dennis, last: ritchie }, "
	            +     "electronicalInfo: { phone: dennis@x.com, email: 23456789123 } "
	            +   "} "
	            + "}"
	            + "]", 
	            consultant.getCustomers().toString()
	    );
	}

	private static Consultant createConsultantWithJamesAndDennis() {
        Customer james = new Customer();
        james.setContact(
                new Contact(
                        new Name("james", "gosling"), 
                        new ElectronicalInfo("james@x.com", "12345678912")
                )
        );
        Customer dennis = new Customer();
        dennis.setContact(
                new Contact(
                        new Name("dennis", "ritchie"), 
                        new ElectronicalInfo("dennis@x.com", "23456789123")
                )
        );
        
        Consultant consultant = new Consultant();
        consultant.getCustomers().add(james);
        consultant.getCustomers().add(dennis);
        
        /*
         * The "Consultant.customers" is NavigableSet and it uses 
         * the "Customer.contact" to be its comparison rule,
         * the "declaredProeprtiesOrder" of Contact is "name, electronicalInfo" 
         * and the "declaredPropertiesOrder" of Name is "first, last",
         * so the property with highest comparison priority is 
         * "Customer.contact.name.first", finally, "dennis" < "james"
         */
        Assert.assertEquals(
                "["
                + "{ "
                +   "contact: { "
                +     "name: { first: dennis, last: ritchie }, "
                +     "electronicalInfo: { phone: dennis@x.com, email: 23456789123 } "
                +   "} "
                + "}, "
                + "{ "
                +   "contact: { "
                +     "name: { first: james, last: gosling }, "
                +     "electronicalInfo: { phone: james@x.com, email: 12345678912 } "
                +   "} "
                + "}"
                + "]", 
                consultant.getCustomers().toString()
        );
        
        return consultant;
    }
}
