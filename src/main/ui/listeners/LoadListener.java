package ui.listeners;

import javax.swing.*;
import model.AccountList;
import model.Event;
import model.EventLog;
import persistence.JsonReader;
import ui.gui.MainMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// Listener class for load the data
public class LoadListener implements ActionListener {

    private JsonReader jsonReader;
    private JFrame frame;
    private static final String JSON_STORE = "./data/accountlist.json";

    private AccountList acc;
    private MainMenu contentFrame;

    // EFFECTS: Create a LoadListener and set fields for MainMenu
    public LoadListener(JsonReader jsonReader, JFrame frame, AccountList acc, MainMenu contentFrame) {
        this.jsonReader = jsonReader;
        this.frame = frame;
        this.acc = acc;
        this.contentFrame = contentFrame;
    }

    // MODIFIES: acc, contentFrame
    // EFFECTS: Attempt to load AccountList from JSON file stored at "./data/accountlist.json" and
    //          update the AccountList and list model.
    //          If successful: Create a popup notifying the user that accounts were loaded
    //          If unsuccessful: Create a popup notifying the user that the load failed.
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // EventLog.getInstance().logEvent(new Event("File Loaded"));
            acc = jsonReader.read();
            contentFrame.resetPanels(acc);
            JOptionPane.showMessageDialog(frame, "Successfully loaded from file!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Unable to load from file: " + JSON_STORE);
        }
    }

}
