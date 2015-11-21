package org.babyfishdemo.foundation.typedi18n.lazy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Tao Chen
 */
public class AnnualLeaveRequestTest {
    
    private AnnualLeaveRequest annualLeaveRequest;
    
    @Before
    public void setUp() {
        this.annualLeaveRequest = new AnnualLeaveRequest(
                "Zeratul", 
                date("2015-05-04 09:00:00"), 
                date("2015-05-06 18:00:00")
        );
    }
    
    @Test
    public void testModifySubmittedRequest() {
        this.annualLeaveRequest.submit();
        try {
            this.annualLeaveRequest.modify(date("2015-05-04 09:00:00"), date("2015-05-07 18:00:00"));
            Assert.fail(IllegalStateException.class.getName() + " is expected");
        } catch (IllegalStateException ex) {
            Assert.assertEquals(
                    "The current annual leave request can't be modified because its state is " +
                    "\"org.babyfishdemo.foundation.typedi18n.lazy.AnnualLeaveRequestState.SUBMITTED\"",
                    ex.getMessage()
            );
        }
    }

    @Test
    public void testModifyCancelledRequest() {
        this.annualLeaveRequest.cancel();
        try {
            this.annualLeaveRequest.modify(date("2015-05-04 09:00:00"), date("2015-05-07 18:00:00"));
            Assert.fail(IllegalStateException.class.getName() + " is expected");
        } catch (IllegalStateException ex) {
            Assert.assertEquals(
                    "The current annual leave request can't be modified because its state is " +
                    "\"org.babyfishdemo.foundation.typedi18n.lazy.AnnualLeaveRequestState.CANCELLED\"",
                    ex.getMessage()
            );
        }
    }
    
    @Test
    public void testModifyApprovedRequest() {
        this.annualLeaveRequest.submit();
        this.annualLeaveRequest.approve();
        try {
            this.annualLeaveRequest.modify(date("2015-05-04 09:00:00"), date("2015-05-07 18:00:00"));
            Assert.fail(IllegalStateException.class.getName() + " is expected");
        } catch (IllegalStateException ex) {
            Assert.assertEquals(
                    "The current annual leave request can't be modified because its state is " +
                    "\"org.babyfishdemo.foundation.typedi18n.lazy.AnnualLeaveRequestState.APPROVED\"",
                    ex.getMessage()
            );
        }
    }
    
    @Test
    public void testModifyRejectedRequest() {
        this.annualLeaveRequest.submit();
        this.annualLeaveRequest.reject();
        try {
            this.annualLeaveRequest.modify(date("2015-05-04 09:00:00"), date("2015-05-07 18:00:00"));
            Assert.fail(IllegalStateException.class.getName() + " is expected");
        } catch (IllegalStateException ex) {
            Assert.assertEquals(
                    "The current annual leave request can't be modified because its state is " +
                    "\"org.babyfishdemo.foundation.typedi18n.lazy.AnnualLeaveRequestState.REJECTED\"",
                    ex.getMessage()
            );
        }
    }
    
    @Test
    public void testSubmitSubmittedRequest() {
        this.annualLeaveRequest.submit();
        try {
            this.annualLeaveRequest.submit();
            Assert.fail(IllegalStateException.class.getName() + " is expected");
        } catch (IllegalStateException ex) {
            Assert.assertEquals(
                    "The current annual leave request can't be submitted because its state is " +
                    "\"org.babyfishdemo.foundation.typedi18n.lazy.AnnualLeaveRequestState.SUBMITTED\"",
                    ex.getMessage()
            );
        }
    }
    
    @Test
    public void testSubmitCancelledRequest() {
        this.annualLeaveRequest.cancel();
        try {
            this.annualLeaveRequest.submit();
            Assert.fail(IllegalStateException.class.getName() + " is expected");
        } catch (IllegalStateException ex) {
            Assert.assertEquals(
                    "The current annual leave request can't be submitted because its state is " +
                    "\"org.babyfishdemo.foundation.typedi18n.lazy.AnnualLeaveRequestState.CANCELLED\"",
                    ex.getMessage()
            );
        }
    }
    
    @Test
    public void testSubmitApprovedRequest() {
        this.annualLeaveRequest.submit();
        this.annualLeaveRequest.approve();
        try {
            this.annualLeaveRequest.submit();
            Assert.fail(IllegalStateException.class.getName() + " is expected");
        } catch (IllegalStateException ex) {
            Assert.assertEquals(
                    "The current annual leave request can't be submitted because its state is " +
                    "\"org.babyfishdemo.foundation.typedi18n.lazy.AnnualLeaveRequestState.APPROVED\"",
                    ex.getMessage()
            );
        }
    }
    
    @Test
    public void testSubmitRejectedRequest() {
        this.annualLeaveRequest.submit();
        this.annualLeaveRequest.reject();
        try {
            this.annualLeaveRequest.submit();
            Assert.fail(IllegalStateException.class.getName() + " is expected");
        } catch (IllegalStateException ex) {
            Assert.assertEquals(
                    "The current annual leave request can't be submitted because its state is " +
                    "\"org.babyfishdemo.foundation.typedi18n.lazy.AnnualLeaveRequestState.REJECTED\"",
                    ex.getMessage()
            );
        }
    }
    
    @Test
    public void testCancelSubmittedRequest() {
        this.annualLeaveRequest.submit();
        try {
            this.annualLeaveRequest.cancel();
            Assert.fail(IllegalStateException.class.getName() + " is expected");
        } catch (IllegalStateException ex) {
            Assert.assertEquals(
                    "The current annual leave request can't be cancelled because its state is " +
                    "\"org.babyfishdemo.foundation.typedi18n.lazy.AnnualLeaveRequestState.SUBMITTED\"",
                    ex.getMessage()
            );
        }
    }
    
    @Test
    public void testCancelCancelleddRequest() {
        this.annualLeaveRequest.cancel();
        try {
            this.annualLeaveRequest.cancel();
            Assert.fail(IllegalStateException.class.getName() + " is expected");
        } catch (IllegalStateException ex) {
            Assert.assertEquals(
                    "The current annual leave request can't be cancelled because its state is " +
                    "\"org.babyfishdemo.foundation.typedi18n.lazy.AnnualLeaveRequestState.CANCELLED\"",
                    ex.getMessage()
            );
        }
    }
    
    @Test
    public void testCancelApprovedRequest() {
        this.annualLeaveRequest.submit();
        this.annualLeaveRequest.approve();
        try {
            this.annualLeaveRequest.cancel();
            Assert.fail(IllegalStateException.class.getName() + " is expected");
        } catch (IllegalStateException ex) {
            Assert.assertEquals(
                    "The current annual leave request can't be cancelled because its state is " +
                    "\"org.babyfishdemo.foundation.typedi18n.lazy.AnnualLeaveRequestState.APPROVED\"",
                    ex.getMessage()
            );
        }
    }
    
    @Test
    public void testCancelRejectedRequest() {
        this.annualLeaveRequest.submit();
        this.annualLeaveRequest.reject();
        try {
            this.annualLeaveRequest.cancel();
            Assert.fail(IllegalStateException.class.getName() + " is expected");
        } catch (IllegalStateException ex) {
            Assert.assertEquals(
                    "The current annual leave request can't be cancelled because its state is " +
                    "\"org.babyfishdemo.foundation.typedi18n.lazy.AnnualLeaveRequestState.REJECTED\"",
                    ex.getMessage()
            );
        }
    }
    
    @Test
    public void testApproveCreatedRequest() {
        try {
            this.annualLeaveRequest.approve();
            Assert.fail(IllegalStateException.class.getName() + " is expected");
        } catch (IllegalStateException ex) {
            Assert.assertEquals(
                    "The current annual leave request can't be approved because its state is " +
                    "\"org.babyfishdemo.foundation.typedi18n.lazy.AnnualLeaveRequestState.CREATED\"",
                    ex.getMessage()
            );
        }
    }
    
    @Test
    public void testApproveCancelledRequest() {
        this.annualLeaveRequest.cancel();
        try {
            this.annualLeaveRequest.approve();
            Assert.fail(IllegalStateException.class.getName() + " is expected");
        } catch (IllegalStateException ex) {
            Assert.assertEquals(
                    "The current annual leave request can't be approved because its state is " +
                    "\"org.babyfishdemo.foundation.typedi18n.lazy.AnnualLeaveRequestState.CANCELLED\"",
                    ex.getMessage()
            );
        }
    }
    
    @Test
    public void testApproveApprovedRequest() {
        this.annualLeaveRequest.submit();
        this.annualLeaveRequest.approve();
        try {
            this.annualLeaveRequest.approve();
            Assert.fail(IllegalStateException.class.getName() + " is expected");
        } catch (IllegalStateException ex) {
            Assert.assertEquals(
                    "The current annual leave request can't be approved because its state is " +
                    "\"org.babyfishdemo.foundation.typedi18n.lazy.AnnualLeaveRequestState.APPROVED\"",
                    ex.getMessage()
            );
        }
    }
    
    @Test
    public void testApproveRejectedRequest() {
        this.annualLeaveRequest.submit();
        this.annualLeaveRequest.reject();
        try {
            this.annualLeaveRequest.approve();
            Assert.fail(IllegalStateException.class.getName() + " is expected");
        } catch (IllegalStateException ex) {
            Assert.assertEquals(
                    "The current annual leave request can't be approved because its state is " +
                    "\"org.babyfishdemo.foundation.typedi18n.lazy.AnnualLeaveRequestState.REJECTED\"",
                    ex.getMessage()
            );
        }
    }
    
    @Test
    public void testRejectCreatedRequest() {
        try {
            this.annualLeaveRequest.reject();
            Assert.fail(IllegalStateException.class.getName() + " is expected");
        } catch (IllegalStateException ex) {
            Assert.assertEquals(
                    "The current annual leave request can't be rejected because its state is " +
                    "\"org.babyfishdemo.foundation.typedi18n.lazy.AnnualLeaveRequestState.CREATED\"",
                    ex.getMessage()
            );
        }
    }
    
    @Test
    public void testRejectCancelledRequest() {
        this.annualLeaveRequest.cancel();
        try {
            this.annualLeaveRequest.reject();
            Assert.fail(IllegalStateException.class.getName() + " is expected");
        } catch (IllegalStateException ex) {
            Assert.assertEquals(
                    "The current annual leave request can't be rejected because its state is " +
                    "\"org.babyfishdemo.foundation.typedi18n.lazy.AnnualLeaveRequestState.CANCELLED\"",
                    ex.getMessage()
            );
        }
    }
    
    @Test
    public void testRejectApprovedRequest() {
        this.annualLeaveRequest.submit();
        this.annualLeaveRequest.approve();
        try {
            this.annualLeaveRequest.reject();
            Assert.fail(IllegalStateException.class.getName() + " is expected");
        } catch (IllegalStateException ex) {
            Assert.assertEquals(
                    "The current annual leave request can't be rejected because its state is " +
                    "\"org.babyfishdemo.foundation.typedi18n.lazy.AnnualLeaveRequestState.APPROVED\"",
                    ex.getMessage()
            );
        }
    }
    
    @Test
    public void testRejectRejectedRequest() {
        this.annualLeaveRequest.submit();
        this.annualLeaveRequest.reject();
        try {
            this.annualLeaveRequest.reject();
            Assert.fail(IllegalStateException.class.getName() + " is expected");
        } catch (IllegalStateException ex) {
            Assert.assertEquals(
                    "The current annual leave request can't be rejected because its state is " +
                    "\"org.babyfishdemo.foundation.typedi18n.lazy.AnnualLeaveRequestState.REJECTED\"",
                    ex.getMessage()
            );
        }
    }
    
    private static Date date(String text) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(text);
        } catch (ParseException ex) {
            throw new IllegalArgumentException(
                    "\""
                    + text
                    + "\" can not be parsed to date"
            );
        }
    }
}
