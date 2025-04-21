package ui;

import java.io.FileNotFoundException;

// Represents the Main to run the code
public class Main {
    public static void main(String[] args) {
        // try {
        //     new ExpenseRecordApp();
        // } catch (FileNotFoundException e) {
        //     System.out.println("Unable to run application: file not found");
        // }

        new ExpenseRecordAppGUI();
    }
}
