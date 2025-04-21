package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import org.junit.jupiter.api.Test;

import model.Account;
import model.AccountList;
import model.ExpenseRecord;

public class JsonWriterTest extends JsonTest {

    private Account testAcc1;
    private Account testAcc2;
    private ExpenseRecord expense1;
    private ExpenseRecord expense2;
    private ExpenseRecord expense3;
    private ExpenseRecord expense4;

    @Test
    void testWriterInvalidFile() {
        try {
            new AccountList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            AccountList al = new AccountList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyAccountList.json");
            writer.open();
            writer.write(al);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyAccountList.json");
            al = reader.read();
            assertEquals(0, al.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        testAcc1 = new Account("Jonathan", 1000);
        testAcc2 = new Account("Aldino", 2000);
        expense1 = new ExpenseRecord("Buy Iphone 15", 900, "1/2/2025");
        expense2 = new ExpenseRecord("Buy Laptop", 1500, "10/1/2025");
        expense3 = new ExpenseRecord("Lunch", 50, "1/2/2025");
        expense4 = new ExpenseRecord("Dinner", 100, "14/2/2025");
        try {
            AccountList al = new AccountList();
            al.addAccount(testAcc1);
            testAcc1.addExpense(expense1);
            al.addAccount(testAcc2);
            testAcc2.addExpense(expense2);
            testAcc2.addExpense(expense3);
            testAcc2.addExpense(expense4);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(al);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            al = reader.read();
            assertEquals(2, al.getSize());
            checkAccounts(testAcc1, al.getAccountAtIndex(0));
            checkAccounts(testAcc2, al.getAccountAtIndex(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
