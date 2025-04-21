package model;

// Represents the expense for the user
public class ExpenseRecord {
    private String description;
    private String date;
    private int amount;

    // EFFECTS : record an expense with description, amount, limit Expense, and date
    public ExpenseRecord(String description, int amount, String date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
        EventLog.getInstance().logEvent(new Event("Expense Added"));
    }

    // MODIFIES: this
    // EFFECTS : sets this.description to newest description
    public void setDescription(String description) {
        this.description = description;
    }

    // EFFECTS: returns the description
    public String getDescription() {
        return description;
    }

    // MODIFIES: this
    // EFFECTS : sets this.amount to newest amount
    public void setAmount(int amount) {
        this.amount = amount;
    }

    // EFFECTS: returns the amount
    public int getAmount() {
        return amount;
    }

    // MODIFIES: this
    // EFFECTS : sets this.date to the newest date
    public void setDate(String date) {
        this.date = date;
    }

    // EFFECTS: returns the date
    public String getDate() {
        return date;
    }

    // EFFECTS: return the expense as a string
    public String getExpense() {
        String result = getDate() + ": " + getDescription() + " spend " + Integer.toString(getAmount());
        return result; 
    }
}