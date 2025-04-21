package ui;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import model.AccountList;
import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.gui.MainMenu;

// Represents the ExpenseRecordAppGUI
public class ExpenseRecordAppGUI {

    private static final String JSON_STORE = "./data/accountlist.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private AccountList acc;
    private JFrame frame;
    private JComponent newContentPane;

    // MODIFIES: this;
    // EFFECTS: - create the ExpenseRecordAppGUI and set fields.
    //          - create the JFrame with title "Expense Records"
    //          - create the content pane and add it to the frame
    //          - set frame properties
    public ExpenseRecordAppGUI() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        acc = new AccountList();

        frame = new JFrame("Expense Records");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printEventLog();
                System.exit(0);
            }
        });

        newContentPane = new MainMenu(this);
        newContentPane.setOpaque(true);

        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setSize(new Dimension(990, 800));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // EFFECTS: Print all logged events when the user quits
    private void printEventLog() {
        System.out.println("Event Log:");
        for (Event event : EventLog.getInstance()) {
            System.out.println(event); // Uses Event's toString() method
        }
    }

    // EFFECTS: return the frame
    public JFrame getFrame() {
        return this.frame;
    }

    // EFFECTS: return the jsonWriter
    public JsonWriter getJsonWriter() {
        return jsonWriter;
    }

    // EFFECTS: return the jsonReader
    public JsonReader getJsonReader() {
        return jsonReader;
    }

    // EFFECTS: return the acc
    public AccountList getAcc() {
        return acc;
    }

    // EFFECTS: return the newContentPane
    public JComponent getMainMenu() {
        return this.newContentPane;
    }
    
}
