package model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class TestExpenseRecord {
    private ExpenseRecord testExpense;
    
    @BeforeEach
    void runBefore() {
        testExpense = new ExpenseRecord("Buy Iphone 15", 900, "1/2/2025");
    }

    @Test
    void testConstructor() {
        assertEquals("Buy Iphone 15", testExpense.getDescription());
        assertEquals(900, testExpense.getAmount());
        assertEquals("1/2/2025", testExpense.getDate());
    }

    @Test
    void testSetDescription() {
        assertEquals("Buy Iphone 15", testExpense.getDescription());
        testExpense.setDescription("Buy Laptop");
        assertEquals("Buy Laptop", testExpense.getDescription());
    }

    @Test
    void testSetDescriptionTwice() {
        assertEquals("Buy Iphone 15", testExpense.getDescription());
        testExpense.setDescription("Buy Laptop");
        assertEquals("Buy Laptop", testExpense.getDescription());
        testExpense.setDescription("Buy Camera");
        assertEquals("Buy Camera", testExpense.getDescription()); 
    }

    @Test
    void testSetAmount() {
        assertEquals(900, testExpense.getAmount());
        testExpense.setAmount(1700);
        assertEquals(1700, testExpense.getAmount());
    }

    @Test
    void testSetAmountTwice() {
        assertEquals(900, testExpense.getAmount());
        testExpense.setAmount(1700);
        assertEquals(1700, testExpense.getAmount());
        testExpense.setAmount(2500);
        assertEquals(2500, testExpense.getAmount());
    }

    @Test
    void testSetDate() {
        assertEquals("1/2/2025", testExpense.getDate());
        testExpense.setDate("10/1/2025");
        assertEquals("10/1/2025", testExpense.getDate());
    }

    @Test
    void testSetDateTwice() {
        assertEquals("1/2/2025", testExpense.getDate());
        testExpense.setDate("10/1/2025");
        assertEquals("10/1/2025", testExpense.getDate());
        testExpense.setDate("3/9/2024");
        assertEquals("3/9/2024", testExpense.getDate());
    }

    @Test
    void testGetExpense() {
        assertEquals("1/2/2025: Buy Iphone 15 spend 900", testExpense.getExpense());
    }
}
