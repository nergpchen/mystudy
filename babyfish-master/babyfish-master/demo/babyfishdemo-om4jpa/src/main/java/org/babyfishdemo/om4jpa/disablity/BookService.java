package org.babyfishdemo.om4jpa.disablity;

import org.babyfish.lang.Arguments;
import org.babyfish.persistence.model.JPAEntities;
import org.babyfishdemo.om4jpa.disablity.entities.Book;
import org.babyfishdemo.om4jpa.disablity.entities.Book_;
import org.babyfishdemo.om4jpa.disablity.entities.Book__;

/*
 * The Book has five non-id properties: isbn, name, price, publisher and authors.
 * Suppose if the business logic consider these 5 properties as two groups:
 * (1) Primary group: isbn, name and price
 * (2) Secondary group: publisher, authors
 * The business logic wants to support 2 methods to change these 2 groups, these 2 
 * methods require different permissions. an user with one permission can ONLY 
 * update properties of one group, but can NOT modify properties of other group.
 * 
 * Without the ObjectModel, you can choose an ugly solution.
 * 
 *    First, define two DTO classes:
 * 
 *      public class PrimaryBookInfo {
 *          private long id;
 *          private String isbn;
 *          private String name;
 *          private BigDecmial price;
 *          ...Ignore the getters and setters...
 *      }
 * 
 *      public class SecondaryBookInfo {
 *          private long id;
 *          private String publisher;
 *          private Set<Author> authors;
 *          ...Ignore the getters and setters...
 *      }
 * 
 *  Then, let the modification methods of BookService accept these DTO classes, 
 *  not the entity class.
 *    
 *      public class BusinessService {
 * 
 *          private BookRepository bookRepository;
 * 
 *          public void modifyPrimaryBookInfo(PrimaryBookInfo primaryBookInfo) {
 * 
 *              Fake code { 
 *                  Check whether the current has permission to modify the primary info
 *              }
 * 
 *              Book book = this.bookRepository.getBookById(primaryBookInfo.getId());
 * 
 *              // Boring copying from DTO to entity, of course, you can choose some 
 *              // 3rd-party tools to avoid doing the boring work by yourself 
 *              book.setIsbn(primaryBookInfo.getIsbn());
 *              book.setName(primaryBookInfo.getName());
 *              book.setPrice(primaryBookInfo.getPrice());
 * 
 *              this.bookRepository.mergeBook(book); // Update database
 *          }
 * 
 *          public void modifySecondaryBookInfo(SecondaryBookInfo secondaryBookInfo) {
 * 
 *              Fake code { 
 *                  Check whether the current has permission to modify the primary info
 *              }
 * 
 *              Book book = this.bookRepository.getBookById(secondaryBookInfo.getId());
 * 
 *              // Boring copying from DTO to entity, of course, you can choose some 
 *              // 3rd-party tools to avoid doing the boring work by yourself 
 *              book.setPublisher(secondaryBookInfo.getPulisher());
 *              book.setAuthors(secondaryBookInfo.getAuhtors());
 * 
 *              this.bookRepository.mergeBook(book); // Update database
 *          }
 *      }
 * 
 * In this ugly solution, you need to define two DTO classes and copy the data from
 * DTO object to entity object. How boring it is! Life is precious, you should use it 
 * to do more significant work.
 */
/**
 * @author Tao Chen
 */
public class BookService {

    private BookRepository bookRepository = new BookRepository();
    
    public Book getBookById(
            long id,
            /*
             * We will learn this parameter "queryPaths" in the demo project 
             * babyfishdemo-querypath, don't worry, we use it very simply in
             * this demo so that you need NOT to learn it temporarily.
             */
            Book__ ... queryPaths) {
        try (TransactionScope ts = new TransactionScope(true)) {
            return this.bookRepository.getBookById(id, queryPaths);
        }
    }
    
    public long createBook(Book book) {
        Arguments.mustBeNull(
                "book.id", 
                Arguments.mustNotBeNull("book", book).getId()
        );
        try (TransactionScope ts = new TransactionScope()) {
            return ts.complete(this.bookRepository.mergeBook(book).getId());
        }
    }
    
    public long modifyPrimaryBookInfo(Book book) {
        /*
         * In real project, please add some logic here to check whether 
         * the current user permission to modify the primary info. 
         */
        
        Arguments.mustNotBeNull(
                "book.id", 
                Arguments.mustNotBeNull("book", book).getId()
        );
        
        /*
         * This method ONLY allows you to update three properties: 
         * "isbn", "name" and "price"
         */
        JPAEntities.validateMaxEnabledRange(
                book, 
                Book_.isbn,
                Book_.name,
                Book_.price
        );
        
        try (TransactionScope ts = new TransactionScope()) {
            return ts.complete(this.bookRepository.mergeBook(book).getId());
        }
    }
    
    public long modifySecondaryBookInfo(Book book) {
        /*
         * In real project, please add some logic here to check whether 
         * the current user permission to modify the secondary info. 
         */
        
        Arguments.mustNotBeNull(
                "book.id", 
                Arguments.mustNotBeNull("book", book).getId()
        );
        
        /*
         * This method ONLY allows you to update two properties: 
         * "publisher" and "authors"
         */
        JPAEntities.validateMaxEnabledRange(
                book, 
                Book_.publisher,
                Book_.authors
        );
        
        try (TransactionScope ts = new TransactionScope()) {
            return ts.complete(this.bookRepository.mergeBook(book).getId());
        }
    }
}
