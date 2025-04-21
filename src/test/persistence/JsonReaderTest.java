package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import org.junit.jupiter.api.Test;

import model.Account;
import model.AccountList;
import model.ExpenseRecord;

public class JsonReaderTest extends JsonTest {

    private Account testAcc1;
    private Account testAcc2;
    private ExpenseRecord expense1;
    private ExpenseRecord expense2;
    private ExpenseRecord expense3;
    private ExpenseRecord expense4;

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyAccountList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyAccountList.json");
        try {
            AccountList al = reader.read();
            assertEquals(0, al.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralAccountList() {
        testAcc1 = new Account("Jonathan", 1000);
        testAcc2 = new Account("Aldino", 2000);
        expense1 = new ExpenseRecord("Buy Iphone 15", 900, "1/2/2025");
        expense2 = new ExpenseRecord("Buy Laptop", 1500, "10/1/2025");
        expense3 = new ExpenseRecord("Lunch", 50, "1/2/2025");
        expense4 = new ExpenseRecord("Dinner", 100, "14/2/2025");
        testAcc1.addExpense(expense1);
        testAcc2.addExpense(expense2);
        testAcc2.addExpense(expense3);
        testAcc2.addExpense(expense4);
        JsonReader reader = new JsonReader("./data/testReaderGeneralAccountList.json");
        try {
            AccountList al = reader.read();
            assertEquals(2, al.getSize());
            checkAccounts(testAcc1, al.getAccountAtIndex(0));
            checkAccounts(testAcc2, al.getAccountAtIndex(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
    
}
