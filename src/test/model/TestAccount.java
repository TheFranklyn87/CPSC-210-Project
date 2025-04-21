package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestAccount {

    private Account testAcc1;
    private Account testAcc2;
    private ExpenseRecord expense1;
    private ExpenseRecord expense2;
    private ExpenseRecord expense3;
    private ExpenseRecord expense4;
    
    @BeforeEach
    void runBefore() {
        testAcc1 = new Account("Jonathan", 1000);
        testAcc2 = new Account("Aldino", 2000);
        expense1 = new ExpenseRecord("Buy Iphone 15", 900, "1/2/2025");
        expense2 = new ExpenseRecord("Buy Laptop", 1500, "10/1/2025");
        expense3 = new ExpenseRecord("Lunch", 50, "1/2/2025");
        expense4 = new ExpenseRecord("Dinner", 100, "14/2/2025");
    }

    @Test
    void testConstructor() {
        assertEquals("Jonathan", testAcc1.getName());
        assertEquals("Aldino", testAcc2.getName());
        assertEquals(1000, testAcc1.getLimitExpense());
        assertEquals(2000, testAcc2.getLimitExpense());
        assertEquals(0, testAcc1.getExpenses().size());
        assertEquals(0, testAcc2.getExpenses().size());
    }

    @Test
    void testSetName() {
        assertEquals("Jonathan", testAcc1.getName());
        testAcc1.setName("Demetrio");
        assertEquals("Demetrio", testAcc1.getName()); 
    }

    @Test
    void testSetNameTwice() {
        assertEquals("Jonathan", testAcc1.getName());
        testAcc1.setName("Demetrio");
        assertEquals("Demetrio", testAcc1.getName()); 
        testAcc1.setName("Jefferson");
        assertEquals("Jefferson", testAcc1.getName()); 
    }

    @Test
    void testAddExpense() {
        assertFalse(testAcc1.addExpense(expense1));
        assertEquals(900, testAcc1.getExpenseNow());
    }

    @Test
    void testAddExpenseThenRemove() {
        assertFalse(testAcc1.addExpense(expense1));
        assertEquals(900, testAcc1.getExpenseNow());
        testAcc1.removeAtIndex(0);
        assertEquals(0, testAcc1.getExpenseNow());
    }

    @Test
    void testAddExpenseMoreThanLimit() {
        assertFalse(testAcc1.addExpense(expense1));
        assertEquals(900, testAcc1.getExpenseNow());
        assertTrue(testAcc1.addExpense(expense2));
        assertEquals(2400, testAcc1.getExpenseNow());
    }

    @Test
    void testAddExpenseMoreThanLimitThenSetLimitExpenseMoreThanExpense() {
        assertFalse(testAcc1.addExpense(expense1));
        assertEquals(900, testAcc1.getExpenseNow());
        assertTrue(testAcc1.addExpense(expense2));
        assertEquals(2400, testAcc1.getExpenseNow());
        testAcc1.setLimitExpense(2500);
        assertFalse(testAcc1.notification());
    }

    @Test
    void testAddExpenseMoreThanLimitThenSetLimitExpenseLessThanExpense() {
        assertFalse(testAcc1.addExpense(expense1));
        assertEquals(900, testAcc1.getExpenseNow());
        assertTrue(testAcc1.addExpense(expense2));
        assertEquals(2400, testAcc1.getExpenseNow());
        testAcc1.setLimitExpense(2000);
        assertTrue(testAcc1.notification());
    }

    @Test
    void testSetLimitExpense() {
        assertEquals(1000, testAcc1.getLimitExpense());
        testAcc1.setLimitExpense(1500); 
        assertEquals(1500, testAcc1.getLimitExpense());
        assertEquals(2000, testAcc2.getLimitExpense());
    }

    @Test
    void testSetLimitExpenseTwice() {
        assertEquals(1000, testAcc1.getLimitExpense());
        testAcc1.setLimitExpense(1500); 
        assertEquals(1500, testAcc1.getLimitExpense());
        testAcc1.setLimitExpense(500); 
        assertEquals(500, testAcc1.getLimitExpense());
    }

    @Test
    void testFind() {
        testAcc1.addExpense(expense1);
        testAcc1.addExpense(expense2);
        testAcc1.addExpense(expense3);
        testAcc1.addExpense(expense4);
        assertEquals("", testAcc1.find("13/2/2025"));
        assertEquals("14/2/2025: Dinner spend 100\n", testAcc1.find("14/2/2025"));
        assertEquals("1/2/2025: Buy Iphone 15 spend 900\n" + "1/2/2025: Lunch spend 50\n", testAcc1.find("1/2/2025"));
        assertEquals("10/1/2025: Buy Laptop spend 1500\n", testAcc1.find("10/1/2025"));
    }

    @Test
    void testGetExpenses() {
        assertEquals(0, testAcc1.getExpenses().size());
        testAcc1.addExpense(expense1);
        assertEquals(1, testAcc1.getExpenses().size());
        assertEquals(expense1, testAcc1.getExpenses().get(0));
        testAcc1.addExpense(expense2);
        assertEquals(2, testAcc1.getExpenses().size());
        assertEquals(expense1, testAcc1.getExpenses().get(0));
        assertEquals(expense2, testAcc1.getExpenses().get(1));
    }

    @Test
    void testGetExpenseList() {
        testAcc1.addExpense(expense2);
        testAcc1.addExpense(expense1);
        String expected = "1) 10/1/2025: Buy Laptop spend 1500\n" + "2) 1/2/2025: Buy Iphone 15 spend 900";
        assertEquals(expected, testAcc1.getExpenseList());
    }

    @Test
    void testGetExpenseListEmpty() {
        assertEquals("\nno saved expense", testAcc1.getExpenseList());
    }

}
