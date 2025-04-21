package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.Account;

public class JsonTest {
    protected void checkAccounts(Account testAcc, Account acc) {
        assertEquals(testAcc.getName(), acc.getName());
        assertEquals(testAcc.getLimitExpense(), acc.getLimitExpense());
        for (int i = 0; i < testAcc.getExpenses().size(); i++) {
            assertEquals(testAcc.getExpenses().get(i).getAmount(), acc.getExpenses().get(i).getAmount());
            assertEquals(testAcc.getExpenses().get(i).getDate(), acc.getExpenses().get(i).getDate());
            assertEquals(testAcc.getExpenses().get(i).getDescription(), acc.getExpenses().get(i).getDescription());
        }
        assertEquals(testAcc.getExpenses().size(), acc.getExpenses().size());
        assertEquals(testAcc.getExpenseNow(), acc.getExpenseNow());
        assertEquals(testAcc.getExpenseList(), acc.getExpenseList());
    }
    
}
