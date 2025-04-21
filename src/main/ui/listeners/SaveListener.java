package ui.listeners;

import javax.swing.*;
import model.AccountList;
import model.Event;
import model.EventLog;
import persistence.JsonWriter;
import ui.ExpenseRecordAppGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

// Listener class for save the data
public class SaveListener implements ActionListener {

    private AccountList acc;
    private ExpenseRecordAppGUI expenseRecordAppGUI;

    // EFFECTS: Create a new SaveListener and set fields.
    public SaveListener(AccountList acc, ExpenseRecordAppGUI expenseRecordAppGUI) {
        this.acc = acc;
        this.expenseRecordAppGUI = expenseRecordAppGUI;
    }

    // MODIFIES: "./data/accountlist.json"
    // EFFECTS: Tries to save the acc in AccountList to "./data/accountlist.json".
    //          If successful: Create a popup notifying the user that accounts were saved.
    //          If unsuccessful: Create a popup notifying the user that the save has failed.
    @Override
    public void actionPerformed(ActionEvent e) {
        // EventLog.getInstance().logEvent(new Event("File Saved"));
        JsonWriter jsonWriter = expenseRecordAppGUI.getJsonWriter();
        JFrame frame = expenseRecordAppGUI.getFrame();
        try {
            jsonWriter.open();
            jsonWriter.write(acc);
            jsonWriter.close();
            JOptionPane.showMessageDialog(frame, "Save complete!");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        } catch (FileNotFoundException exception) {
            JOptionPane.showMessageDialog(frame, "Was not able to save");
        }
    }

}
