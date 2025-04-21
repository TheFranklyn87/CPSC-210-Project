package model;

import java.util.ArrayList;
import org.json.JSONObject;
import persistence.Writable;

// Represents the account list for the user
public class AccountList implements Writable {
    private ArrayList<Account> accountList;

    // EFFECTS : create an empty AccountList
    public AccountList() {
        accountList = new ArrayList<Account>();
    }

    // EFFECTS: returns the number of elements in accountList
    public int getSize() {
        return accountList.size();
    }

    // MODIFIES: this
    // EFFECTS: adds a to end of accountList
    public void addAccount(Account a) {
        accountList.add(a);
        EventLog.getInstance().logEvent(new Event("Account " + a.getName() + " Added"));
    }

    // REQUIRES: index i is in bounds of accountList
    // MODIFIES: this
    // EFFECTS: remove the Account stored at index i of accountList
    public void removeAtIndex(int i) {
        EventLog.getInstance().logEvent(new Event("Removed account : " + accountList.get(i).getName()));
        accountList.remove(i);
    }

    // REQUIRES: accountList must not be empty, i >= 0
    // EFFECTS: Returns the Account stored at index i of accountList.
    //          return null if index is out of bounds
    public Account getAccountAtIndex(int i) {
        if (i < accountList.size()) {
            return accountList.get(i);
        } else {
            return null;
        }
    }

    // EFFECTS: Return the list of account in String
    public String getAccountList() {
        String result = "";
        if (accountList.size() == 0) {
            return "\nno saved account";
        } else {
            for (int i = 0; i < accountList.size(); i++) {
                if (i == accountList.size() - 1) {
                    result += Integer.toString(i + 1) + ") " + accountList.get(i).getName();
                } else {
                    result += Integer.toString(i + 1) + ") " + accountList.get(i).getName() + "\n";
                }
            }
            return result;
        }
    }

    // return the object accountList
    public ArrayList<Account> getAccList() {
        return accountList;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("accounts", accountList);
        return json;
    }
}