package ui.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.AccountList;
import persistence.JsonReader;
import ui.ExpenseRecordAppGUI;
import ui.listeners.*;
import ui.listeners.account.*;

// Class that represents the menu that displays when the user run the program
public class MainMenu extends JPanel implements ListSelectionListener {
    private JList<String> list;
    private DefaultListModel<String> listModel;

    private JPanel buttonPane;
    private JFrame frame;

    AccountList acc;

    private JsonReader jsonReader;

    private JButton deleteButton;
    private static final String DELETE_STRING = "Delete";
    private DeleteListener deleteListener;

    private JButton detailsButton;
    private static final String DETAILS_STRING = "Details";
    private DetailsListener detailsListener;

    private JButton openAccountButton;
    private static final String OPEN_ACCOUNT_STRING = "Open";
    private MainMenuClickOpenAccountListener openAccountListener;

    private JButton newAccountButton;
    private static final String NEW_ACCOUNT_STRING = "New Account";
    private MainMenuClickNewAccountListener newAccountListener;

    private JButton editAccountButton;
    private static final String EDIT_ACCOUNT_STRING = "Edit Account";
    private MainMenuClickEditAccountListener editAccountListener;

    private JButton loadButton;
    private static final String LOAD_STRING = "Load From File";
    private LoadListener loadListener;

    private JButton saveButton;
    private static final String SAVE_STRING = "Save";
    private SaveListener saveListener;

    JScrollPane listScrollPane;
    private  ExpenseRecordAppGUI expenseRecordAppGUI;

    // EFFECTS: create MainMenu and set fields
    public MainMenu(ExpenseRecordAppGUI expenseRecordAppGUI) {
        super(new BorderLayout());
        this.expenseRecordAppGUI = expenseRecordAppGUI;
        this.jsonReader = expenseRecordAppGUI.getJsonReader();
        this.acc = expenseRecordAppGUI.getAcc();
        this.frame = expenseRecordAppGUI.getFrame();

        init();
        disableButtons();
    }

    // MODIFIES: this, expenseRecordAppGUI
    // EFFECTS: - create new DefaultListModel and JList
    //          - loop through accounts and assign the title of each account to the listModel
    //          - create button pane and assign it:
    //              - createNewAccountButton
    //              - createDetailsButton
    //              - createEditAccountButton
    //              - crateOpenAccountButton
    //              - createDeleteButton
    //              - createLoadButton
    //              - createSaveButton
    //          - create a JScrollPane and populate it JScrollPane the list
    //          - add both the JScrollPane and the buttonPanel to the frame
    public void init() {
        listModel = new DefaultListModel<String>();
        list = new JList<String>(listModel);
        list.setFont(new Font("Arial",0, 28));

        for (int i = 0; i < acc.getSize(); i++) {
            listModel.addElement(acc.getAccountAtIndex(i).getName());
        }

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);

        buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        createNewAccountButton();
        createDetailsButton();
        createEditAccountButton();
        createOpenAccountButton();

        createDeleteButton();
        createLoadButton();
        createSaveButton();

        listScrollPane = new JScrollPane(list);
        checkButtonStatus();
        add(listScrollPane, BorderLayout.CENTER);

        add(buttonPane, BorderLayout.PAGE_END);
    }

    // MODIFIES: this
    // EFFECTS: create the newAccountButton, assign it the MainMenuClickNewAccountListener
    //          and add it to the buttonPane.
    private void createNewAccountButton() {
        newAccountButton = new JButton(NEW_ACCOUNT_STRING);
        newAccountButton.setFont(new Font("Arial",0, 26));
        newAccountButton.setActionCommand(NEW_ACCOUNT_STRING);
        newAccountListener = new MainMenuClickNewAccountListener(list, listModel, acc,
        expenseRecordAppGUI);
        newAccountButton.addActionListener(newAccountListener);
        buttonPane.add(newAccountButton);
    }

    // MODIFIES: this
    // EFFECTS: create the detailsButton, assign it the DetailsListener and add it to the buttonPane.
    private void createDetailsButton() {
        detailsButton = new JButton(DETAILS_STRING);
        detailsButton.setActionCommand(DETAILS_STRING);
        detailsButton.setFont(new Font("Arial",0, 26));
        detailsListener = new DetailsListener(list, listModel, acc);
        detailsButton.addActionListener(detailsListener);
        buttonPane.add(detailsButton);
    }

    // MODIFIES: this
    // EFFECTS: create the editAccountButton, assign it the MainMenuClickEditAccountListener
    //          and add it to the buttonPane.
    private void createEditAccountButton() {
        editAccountButton = new JButton(EDIT_ACCOUNT_STRING);
        editAccountButton.setFont(new Font("Arial",0, 26));
        editAccountButton.setActionCommand(EDIT_ACCOUNT_STRING);
        editAccountListener = new MainMenuClickEditAccountListener(list, listModel, acc,
                expenseRecordAppGUI, this);
        editAccountButton.addActionListener(editAccountListener);
        buttonPane.add(editAccountButton);
    }

    // MODIFIES: this
    // EFFECTS: create the openAccountButton, assign it the MainMenuClickOpenAccountListener
    //          and add it to the buttonPane.
    private void createOpenAccountButton() {
        openAccountButton = new JButton(OPEN_ACCOUNT_STRING);
        openAccountButton.setFont(new Font("Arial",0, 26));
        openAccountButton.setActionCommand(EDIT_ACCOUNT_STRING);
        openAccountListener = new MainMenuClickOpenAccountListener(list, listModel, acc,
        expenseRecordAppGUI);
        openAccountButton.addActionListener(openAccountListener);
        buttonPane.add(openAccountButton);
    }

    // MODIFIES: this
    // EFFECTS: create the deleteButton, assign it the deleteListener and add it to the buttonPane.
    private void createDeleteButton() {
        deleteButton = new JButton(DELETE_STRING);
        deleteButton.setFont(new Font("Arial",0, 26));
        deleteButton.setActionCommand(DELETE_STRING);
        deleteListener = new DeleteListener(list, listModel, deleteButton, acc, expenseRecordAppGUI);
        deleteButton.addActionListener(deleteListener);
        buttonPane.add(deleteButton);
    }

    // MODIFIES: this
    // EFFECTS: create the loadButton, assign it the LoadListener and add it to the buttonPane.
    private void createLoadButton() {
        loadButton = new JButton(LOAD_STRING);
        loadButton.setFont(new Font("Arial",0, 26));
        loadButton.setActionCommand(LOAD_STRING);
        loadListener = new LoadListener(jsonReader, frame, acc, this);
        loadButton.addActionListener(loadListener);
        buttonPane.add(loadButton);
    }

    // MODIFIES: this
    // EFFECTS: create the saveButton, assign it the SaveListener and add it to the buttonPane.
    private void createSaveButton() {
        saveButton = new JButton(SAVE_STRING);
        saveButton.setFont(new Font("Arial",0, 26));
        saveButton.setActionCommand(SAVE_STRING);
        saveListener = new SaveListener(acc, expenseRecordAppGUI);
        saveButton.addActionListener(saveListener);
        buttonPane.add(saveButton);
    }

    // MODIFIES: this, expenseRecordAppGUI
    // EFFECTS: when called, re-instantiate listModel and list. Remove the JScrollPane and the buttonPane.
    //          call init() to re-add all of these fields.
    public void resetPanels(AccountList acc) {
        this.acc = acc;
        listModel = new DefaultListModel<String>();
        list = new JList<String>(listModel);
        revalidate();
        remove(listScrollPane);
        remove(buttonPane);
        init();
    }

    // MODIFIES : this
    // EFFECTS : disable or enable the details, delete and edit account buttons.
    private void checkButtonStatus() {
        if (listModel.isEmpty()) {
            disableButtons();
        } else {
            enableButtons();
        }
    }

    // MODIFIES: this
    // EFFECTS: disables the details, delete and edit account buttons.
    private void disableButtons() {
        detailsButton.setEnabled(false);
        deleteButton.setEnabled(false);
        editAccountButton.setEnabled(false);
    }

    // MODIFIES: this
    // EFFECTS: enables the details, delete and edit account buttons.
    public void enableButtons() {
        detailsButton.setEnabled(true);
        deleteButton.setEnabled(true);
        editAccountButton.setEnabled(true);
    }

    // MODIFIES: this
    // EFFECTS: when the value of the listModel is updated, check to see if the listModel is empty.
    //          If listModel is empty: disable the edit, delete and details button
    //          Otherwise, do nothing.
    @Override
    public void valueChanged(ListSelectionEvent e) {
        checkButtonStatus();
    }
}
