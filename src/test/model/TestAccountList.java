package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class TestAccountList {
    private Account acc1;
    private Account acc2;
    private Account acc3;
    private AccountList testAccList;

    @BeforeEach
    public void runBefore() {
        acc1 = new Account("Jonathan", 1000);
        acc2 = new Account("Aldino", 2000);
        acc3 = new Account("Carissa", 1500);
        testAccList = new AccountList();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, testAccList.getSize());
    }

    @Test
    public void testGetSizeEmpty() {
        assertEquals(0, testAccList.getSize());
    }

    @Test
    public void testGetSizeNotEmpty() {
        testAccList.addAccount(acc1);
        assertEquals(1, testAccList.getSize());
    }

    @Test
    public void testAddAccountToEmpty() {
        assertEquals(0, testAccList.getSize());
        testAccList.addAccount(acc1);
        assertEquals(1, testAccList.getSize());
        assertEquals(acc1, testAccList.getAccountAtIndex(0));
        assertNull(testAccList.getAccountAtIndex(1));
    }

    @Test
    public void testAddreminderToNotEmpty() {
        for (int i = 0; i < 10; i++) {
            testAccList.addAccount(acc1);
            assertEquals(i + 1, testAccList.getSize());
        }
    }

    @Test
    public void testRemoveAtIndexFirst() {
        testAccList.addAccount(acc1);
        testAccList.addAccount(acc2);
        testAccList.addAccount(acc3);
        testAccList.removeAtIndex(0);
        assertEquals(2, testAccList.getSize());
        assertEquals(acc2, testAccList.getAccountAtIndex(0));
        assertEquals(acc3, testAccList.getAccountAtIndex(1));
    }

    @Test
    public void testRemoveAtIndexMiddle() {
        testAccList.addAccount(acc1);
        testAccList.addAccount(acc2);
        testAccList.addAccount(acc3);
        testAccList.removeAtIndex(1);
        assertEquals(2, testAccList.getSize());
        assertEquals(acc1, testAccList.getAccountAtIndex(0));
        assertEquals(acc3, testAccList.getAccountAtIndex(1));
    } 

    @Test
    public void testRemoveAtIndexLast() {
        testAccList.addAccount(acc1);
        testAccList.addAccount(acc2);
        testAccList.addAccount(acc3);
        testAccList.removeAtIndex(testAccList.getSize() - 1);
        assertEquals(2, testAccList.getSize());
        assertEquals(acc1, testAccList.getAccountAtIndex(0));
        assertEquals(acc2, testAccList.getAccountAtIndex(1));
    } 

    @Test
    public void testGetAccountAtIndexFirst() {
        testAccList.addAccount(acc1);
        testAccList.addAccount(acc2);
        testAccList.addAccount(acc3);
        assertEquals(3, testAccList.getSize());
        assertEquals(acc1, testAccList.getAccountAtIndex(0));
        assertEquals(3, testAccList.getSize());
    }

    @Test
    public void testGetAccountAtIndexMiddle() {
        testAccList.addAccount(acc1);
        testAccList.addAccount(acc2);
        testAccList.addAccount(acc3);
        assertEquals(3, testAccList.getSize());
        assertEquals(acc2, testAccList.getAccountAtIndex(1));
        assertEquals(3, testAccList.getSize());
    }

    @Test
    public void testGetAccountAtIndexEnd() {
        testAccList.addAccount(acc1);
        testAccList.addAccount(acc2);
        testAccList.addAccount(acc3);
        assertEquals(3, testAccList.getSize());
        assertEquals(acc3, testAccList.getAccountAtIndex(testAccList.getSize() - 1));
        assertEquals(3, testAccList.getSize());
    }

    @Test
    public void testGetAccountAtIndexOutOfBound() {
        testAccList.addAccount(acc1);
        testAccList.addAccount(acc2);
        testAccList.addAccount(acc3);
        assertEquals(3, testAccList.getSize());
        assertNull(testAccList.getAccountAtIndex(testAccList.getSize()));
        assertEquals(3, testAccList.getSize());
    }

    @Test
    public void testGetAccountList() {
        testAccList.addAccount(acc1);
        testAccList.addAccount(acc2);
        testAccList.addAccount(acc3);
        assertEquals("1) Jonathan\n" + "2) Aldino\n" + "3) Carissa", testAccList.getAccountList()); 
    }

    @Test
    public void testGetAccountlistEmpty() {
        assertEquals("\nno saved account", testAccList.getAccountList());
    }

    @Test
    public void testGetAccList() {
        testAccList.addAccount(acc1);
        testAccList.addAccount(acc2);
        testAccList.addAccount(acc3);
        assertEquals(3, testAccList.getAccList().size());
        assertEquals(acc1, testAccList.getAccList().get(0));
        assertEquals(acc2, testAccList.getAccList().get(1)); 
        assertEquals(acc3, testAccList.getAccList().get(2)); 
    }
}
