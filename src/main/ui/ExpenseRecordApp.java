package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import model.*;
import persistence.*;

// Represents the ExpenseRecordApp
public class ExpenseRecordApp {
    private AccountList acc;
    private Scanner input;
    private Account save;
    private String description;
    private String date;
    private int amount;
    private String name;
    private int limitExpense;
    private int saveNumAccount;
    private int saveNumExpense;

    private static final String JSON_STORE = "./data/accountlist.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS : runs the Expense Record application
    public ExpenseRecordApp() throws FileNotFoundException {
        acc = new AccountList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runExpenseRecord();
    }

    // MODIFIES : this
    // EFFECTS : processes user input
    private void runExpenseRecord() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                System.out.println("\nHave a nice day!");
                System.exit(0);
            } else {
                processCommand(command);
            }
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\to -> open saved account");
        System.out.println("\ta -> add new account");
        System.out.println("\ts -> save account list to file");
        System.out.println("\tl -> load account list from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("o")) {
            doOpen();
        } else if (command.equals("a")) {
            doAdd();
        } else if (command.equals("s")) {
            saveAccountList();
        } else if (command.equals("l")) {
            loadAccountList();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes accounts
    private void init() {
        // acc = new AccountList();
        input = new Scanner(System.in);
        input.useDelimiter("\r?\n|\r");
    }

    // MODIFIES: this
    // EFFECTS: open the List of Account
    private void doOpen() {
        boolean keepGoing = true;
        int command = -1;
        System.out.println(acc.getAccountList());

        while (keepGoing) {
            displayMenuOpen();
            command = input.nextInt();
            processCommand2(command);
        }
        System.out.println("\nHave a nice day!");
    }

    // EFFECTS: displays menu of options after Open the List of Account
    private void displayMenuOpen() {
        if (acc.getSize() == 0) {
            System.exit(0);
        } else {
            System.out.println("\nInsert number to open your account");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand2(int command) {
        doNext(command);
    }

    // MODIFIES: this
    // EFFECTS: add or open expense
    private void doNext(int command) {
        boolean keepGoing = true;
        String next = null;
        System.out.println(acc.getAccountAtIndex(command - 1).getExpenseList());
        save = acc.getAccountAtIndex(command - 1);
        saveNumAccount = command;

        while (keepGoing) {
            displayMenuExpense();
            next = input.next();
            next = next.toLowerCase();

            if (next.equals("q")) {
                System.out.println("\nHave a nice day!");
                System.exit(0);
            } else if (next.equals("b")) {
                doOpen();
            } else if (next.equals("f")) {
                doFind();
            } else if (next.equals("s")) {
                saveAccountList();
            } else {
                processCommand3(next);
            }
        }
        System.out.println("\nHave a nice day!");
    }

    // EFFECTS: displays menu of options after displaying the List of Expense
    private void displayMenuExpense() {
        if (save.getExpenses().size() == 0) {
            System.out.println("\nSelect from:");
            System.out.println("\ta -> add new expense");
            System.out.println("\ts -> save account list to file");
            System.out.println("\tb -> back");
            System.out.println("\tq -> quit");
        } else {
            System.out.println("\nSelect from:");
            System.out.println("\te -> edit saved expense");
            System.out.println("\ta -> add new expense");
            System.out.println("\tf for find expense");
            System.out.println("\ts -> save account list to file");
            System.out.println("\tb -> back");
            System.out.println("\tq -> quit");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand3(String command) {
        if (command.equals("e")) {
            doEdit();
        } else if (command.equals("a")) {
            doAddExpense();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: edit the expense
    private void doEdit() {
        boolean keepGoing = true;
        int command = -1;
        System.out.println(save.getExpenseList());

        while (keepGoing) {
            displayMenuEdit();
            command = input.nextInt();

            processCommand4(command); 
        }
        System.out.println("\nHave a nice day!");
    }

    // EFFECTS: displays menu of options after Open the List of Account
    private void displayMenuEdit() {
        System.out.println("\nInsert number to edit your expense");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand4(int command) {
        doNext2(command);
    }

    // MODIFIES: this
    // EFFECTS: add new expense to the list
    private void doNext2(int command) {
        boolean keepGoing = true;
        String next = null;
        System.out.println(save.getExpenses().get(command - 1).getExpense());
        saveNumExpense = command;

        while (keepGoing) {
            displayMenuExpenseSelected();
            next = input.next();
            next = next.toLowerCase();

            if (next.equals("q")) {
                System.out.println("\nHave a nice day!");
                System.exit(0);
            } else if (next.equals("b")) {
                doNext(saveNumAccount);
            } else if (next.equals("s")) {
                saveAccountList();
            } else {
                processCommand5(next);
            }
        }
        System.out.println("\nHave a nice day!");
    }

    // EFFECTS: displays menu of options after select expense to edit
    private void displayMenuExpenseSelected() {
        System.out.println("\tSelect what you want to edit:");
        System.out.println("\td for date");
        System.out.println("\tdesc for description");
        System.out.println("\ta for amount");
        System.out.println("\ts for save account list to file");
        System.out.println("\tb for back");
        System.out.println("\tq for quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand5(String command) {
        if (command.equals("desc")) {
            changeDesc();
        } else if (command.equals("a")) {
            changeAmount();
        } else if (command.equals("d")) {
            changeDate();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: add new expense to the list
    private void changeDesc() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            System.out.println("input new description");
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                System.out.println("\nHave a nice day!");
                System.exit(0);
            } else if (command.equals("b")) {
                doNext2(saveNumExpense);
            } else {
                save.getExpenses().get(saveNumExpense - 1).setDescription(command);
                System.out.println("Changed Save");
                doNext2(saveNumExpense);
            }
        }
        System.out.println("\nHave a nice day!");
    }

    // MODIFIES: this
    // EFFECTS: add new expense to the list
    private void changeAmount() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            System.out.println("input new Amount");
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                System.out.println("\nHave a nice day!");
                System.exit(0);
            } else if (command.equals("b")) {
                doNext2(saveNumExpense);
            } else {
                save.getExpenses().get(saveNumExpense - 1).setAmount(Integer.parseInt(command));
                System.out.println("Changed Save");
                doNext2(saveNumExpense);
            }
        }
        System.out.println("\nHave a nice day!");
    }

    // MODIFIES: this
    // EFFECTS: add new expense to the list
    private void changeDate() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            System.out.println("input new date");
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                System.out.println("\nHave a nice day!");
                System.exit(0);
            } else if (command.equals("b")) {
                doNext2(saveNumExpense);
            } else {
                save.getExpenses().get(saveNumExpense - 1).setDate(command);
                System.out.println("Changed Save");
                doNext2(saveNumExpense);
            }
        }
        System.out.println("\nHave a nice day!");
    }

    // MODIFIES: this
    // EFFECTS: find the expense on the list
    private void doFind() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            System.out.println("input the date(DD/MM/YYYY)");
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                System.out.println("\nHave a nice day!");
                System.exit(0);
            } else if (command.equals("b")) {
                doEdit();
            } else {
                System.out.println(save.find(command));
                doNext(saveNumAccount);
            }
        }
        System.out.println("\nHave a nice day!");
    }

    // MODIFIES: this
    // EFFECTS: input the description for new expense
    private void doAddExpense() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            System.out.println("input the description");
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                System.out.println("\nHave a nice day!");
                System.exit(0);
            } else if (command.equals("b")) {
                doNext(saveNumAccount);
            } else {
                this.description = command;
                inputAmount();
            }
        }
        System.out.println("\nHave a nice day!");
    }

    // MODIFIES: this
    // EFFECTS: input the amount for new expense
    private void inputAmount() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            System.out.println("input the amount");
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                System.out.println("\nHave a nice day!");
                System.exit(0);
            } else if (command.equals("b")) {
                doNext(saveNumAccount);
            } else {
                this.amount = Integer.parseInt(command);
                inputDate();
            }
        }
        System.out.println("\nHave a nice day!");
    }

    // MODIFIES: this
    // EFFECTS: input the date for new expense
    private void inputDate() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            System.out.println("input the date(DD/MM/YYYY)");
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                System.out.println("\nHave a nice day!");
                System.exit(0);
            } else if (command.equals("b")) {
                doNext(saveNumAccount);
            } else {
                this.date = command;
                System.out.println("Expense Added");
                if (save.addExpense(new ExpenseRecord(description, amount, date))) {
                    System.out.println("You Already Exceed Your Limit");
                }
                doNext(saveNumAccount);
            }
        }
        System.out.println("\nHave a nice day!");
    }

    // MODIFIES: this
    // EFFECTS: input the name for new account
    private void doAdd() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            System.out.println("input the name");
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                System.out.println("\nHave a nice day!");
                System.exit(0);
            } else if (command.equals("b")) {
                runExpenseRecord();
            } else {
                this.name = command;
                inputLimitExpense();
            }
        }
        System.out.println("\nHave a nice day!");
    }

    // MODIFIES: this
    // EFFECTS: input the limit expense for new account
    private void inputLimitExpense() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            System.out.println("input the limit expense");
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                System.out.println("\nHave a nice day!");
                System.exit(0);
            } else if (command.equals("b")) {
                runExpenseRecord();
            } else {
                this.limitExpense = Integer.parseInt(command);
                Account a = new Account(name, limitExpense);
                acc.addAccount(a);
                System.out.println("Account Added");
                runExpenseRecord();
            }
        }
        System.out.println("\nHave a nice day!");
    }

    // EFFECTS: saves the acc to file
    private void saveAccountList() {
        try {
            jsonWriter.open();
            jsonWriter.write(acc);
            jsonWriter.close();
            System.out.println("Saved "  + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads acc from file
    private void loadAccountList() {
        try {
            acc = jsonReader.read();
            System.out.println("Loaded " + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
    
}
