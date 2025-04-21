package model;

import java.util.ArrayList;
import java.util.List;

// Represents the account for the user
public class Account {

    private String name;
    private ArrayList<ExpenseRecord> expenseList;
    private int limitExpense;
    private int expenseNow;
    private Boolean alreadyExceed;

    // EFFECTS : creates an account with name and limitExpense
    public Account(String name, int limitExpense) {
        this.name = name;
        expenseList = new ArrayList<ExpenseRecord>();
        this.limitExpense = limitExpense;
        alreadyExceed = false;
    }

    // MODIFIES: this
    // EFFECTS : sets this.name to newest name
    public void setName(String name) {
        EventLog.getInstance().logEvent(new Event("Account " + this.name + " changed to " + name));
        this.name = name;
    }

    // EFFECTS: returns the name
    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds e to end of expenseList
    public Boolean addExpense(ExpenseRecord e) {
        expenseList.add(e);
        expenseNow += e.getAmount();
        if (expenseNow > limitExpense) {
            alreadyExceed = true;
        }
        return alreadyExceed;
    }

    // REQUIRES: index i is in bounds of expenseList
    // MODIFIES: this
    // EFFECTS: remove the Expense stored at index i of expenseList
    public void removeAtIndex(int i) {
        expenseNow -= expenseList.get(i).getAmount();
        expenseList.remove(i);
    }

    // MODIFIES: this
    // EFFECTS : sets this.limitExpense to newest limitExpense
    public void setLimitExpense(int limitExpense) {
        EventLog.getInstance().logEvent(new Event(this.name + "'s account limit expense set to " 
                                    + Integer.toString(limitExpense)));
        this.limitExpense = limitExpense;
        if (expenseNow > limitExpense) {
            alreadyExceed = true;
        } else {
            alreadyExceed = false;      
        }
    }

    // EFFECTS : returns the limitExpense
    public int getLimitExpense() {
        return limitExpense;
    }

    //EFFECTS : return the list of expense that match the date
    public String find(String date) {
        String found = "";
        for (int i = 0; i < expenseList.size(); i++) {
            if (expenseList.get(i).getDate().equals(date)) {
                found += expenseList.get(i).getExpense() + "\n";
            }
        }
        return found;
    }

    // EFFECTS : return list of Expense in order
    public List<ExpenseRecord> getExpenses() {
        return expenseList;
    }

    // EFFECTS : returns the True when Expenses > Limit and otherwise
    public Boolean notification() {
        return alreadyExceed;
    }

    // EFFECTS : return expenseNow
    public int getExpenseNow() {
        return expenseNow;
    }

    // EFFECTS : return list of Expense in String
    public String getExpenseList() {
        String result = "";
        if (expenseList.size() == 0) {
            return "\nno saved expense";
        } else {
            for (int i = 0; i < expenseList.size(); i++) {
                String date = expenseList.get(i).getDate();
                String desc = expenseList.get(i).getDescription();
                String amount = Integer.toString(expenseList.get(i).getAmount());
                if (i == expenseList.size() - 1) {
                    result += Integer.toString(i + 1) + ") " + date + ": " + desc + " spend " + amount;
                } else {
                    result += Integer.toString(i + 1) + ") " + date + ": " + desc + " spend " + amount + "\n";
                }
            }
            return result;
        }
    }
}