package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.json.*;
import model.*;

// Represents a reader that reads AccountList from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads accountlist from file and returns it;
    // throws IOException if an error occurs reading data from file
    public AccountList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAccountList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses AccountList from JSON object and returns it
    private AccountList parseAccountList(JSONObject jsonObject) {
        AccountList al = new AccountList();
        addAccounts(al, jsonObject);
        return al;
    }

    // MODIFIES: al
    // EFFECTS: parses Accounts from JSON object and adds them to AccountList
    private void addAccounts(AccountList al, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("accounts");
        for (Object json : jsonArray) {
            JSONObject nextAccount = (JSONObject) json;
            addAccount(al, nextAccount);
        }
    }

    // MODIFIES: al
    // EFFECTS: parses Account from JSON object and adds it to AccountList
    private void addAccount(AccountList al, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int limitExpense = Integer.valueOf(jsonObject.getInt("limitExpense"));
        Account account = new Account(name, limitExpense);
        addExpenses(account, jsonObject);
        al.addAccount(account);
    }

    // MODIFIES: acc
    // EFFECTS: parses Expenses from JSON object and adds them to Account
    private void addExpenses(Account acc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("expenses");
        for (Object json : jsonArray) {
            JSONObject nextExpense = (JSONObject) json;
            addExpense(acc, nextExpense);
        }
    }

    // MODIFIES: acc
    // EFFECTS: parses Expense from JSON object and adds it to Account
    private void addExpense(Account acc, JSONObject jsonObject) {
        String description = jsonObject.getString("description");
        int amount = jsonObject.getInt("amount");
        String date = jsonObject.getString("date");
        ExpenseRecord expense = new ExpenseRecord(description, amount, date);
        acc.addExpense(expense);
    }     
}
