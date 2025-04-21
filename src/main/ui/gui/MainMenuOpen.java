package ui.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;
import model.AccountList;
import model.ExpenseRecord;
import ui.ExpenseRecordAppGUI;
import ui.listeners.SaveListener;
import ui.listeners.expense.*;

// Class that represents the menu that displays when the user open an Account
public class MainMenuOpen extends JPanel implements ListSelectionListener {
    private JList<String> list;
    private DefaultListModel<String> listModel;

    private JPanel buttonPane;

    AccountList acc;
    List<ExpenseRecord> listExpense;

    private JButton deleteExpenseButton;
    private static final String DELETE_EXPENSE_STRING = "Delete";
    private DeleteExpenseListener deleteExpenseListener;

    private JButton newExpenseButton;
    private static final String NEW_EXPENSE_STRING = "New Expense";
    private MainMenuClickNewExpenseListener newExpenseListener;

    private JButton editExpenseButton;
    private static final String EDIT_EXPENSE_STRING = "Edit Expense";
    private MainMenuClickEditExpenseListener editExpenseListener;

    private JButton saveButton;
    private static final String SAVE_STRING = "Save";
    private SaveListener saveListener;

    JScrollPane listScrollPane;
    private  ExpenseRecordAppGUI expenseRecordAppGUI;

    // EFFECTS : create MainMenuOpen and set fields
    public MainMenuOpen(JList<String> list,  DefaultListModel<String> listModel,
                        ExpenseRecordAppGUI expenseRecordAppGUI, AccountList acc) {
        super(new BorderLayout());
        this.expenseRecordAppGUI = expenseRecordAppGUI;
        this.acc = acc;
        this.listExpense = acc.getAccountAtIndex(list.getSelectedIndex()).getExpenses();

        init();
        disableButtons();
    }

    // MODIFIES: this, expenseRecordAppGUI
    // EFFECTS: - create new DefaultListModel and JList
    //          - loop through expenses and assign each expense to the listModel
    //          - create button pane and assign it:
    //              - createNewExpenseButton
    //              - createEditExpenseButton
    //              - createDeleteButton
    //              - createSaveButton
    //          - create a JScrollPane and populate it JScrollPane the list
    //          - add both the JScrollPane and the buttonPanel to the frame
    public void init() {
        listModel = new DefaultListModel<String>();
        list = new JList<String>(listModel);
        list.setFont(new Font("Arial",0, 28));

        for (int i = 0; i < listExpense.size(); i++) {
            listModel.addElement(listExpense.get(i).getExpense());
        }

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);

        buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        createNewExpenseButton();
        createEditExpenseButton();

        createDeleteExpenseButton();
        createSaveButton();

        listScrollPane = new JScrollPane(list);
        checkButtonStatus();
        add(listScrollPane, BorderLayout.CENTER);

        add(buttonPane, BorderLayout.PAGE_END);
    }

    // MODIFIES: this
    // EFFECTS: create the newExpenseButton, assign it the MainMenuClickNewAccountListener
    //          and add it to the buttonPane.
    private void createNewExpenseButton() {
        newExpenseButton = new JButton(NEW_EXPENSE_STRING);
        newExpenseButton.setFont(new Font("Arial",0, 26));
        newExpenseButton.setActionCommand(NEW_EXPENSE_STRING);
        newExpenseListener = new MainMenuClickNewExpenseListener(list, listModel, listExpense,
        expenseRecordAppGUI);
        newExpenseButton.addActionListener(newExpenseListener);
        buttonPane.add(newExpenseButton);
    }

    // MODIFIES: this
    // EFFECTS: create the editAccountButton, assign it the MainMenuClickEditAccountListener
    //          and add it to the buttonPane.
    private void createEditExpenseButton() {
        editExpenseButton = new JButton(EDIT_EXPENSE_STRING);
        editExpenseButton.setFont(new Font("Arial",0, 26));
        editExpenseButton.setActionCommand(EDIT_EXPENSE_STRING);
        editExpenseListener = new MainMenuClickEditExpenseListener(list, listModel, acc, listExpense,
                expenseRecordAppGUI, this);
        editExpenseButton.addActionListener(editExpenseListener);
        buttonPane.add(editExpenseButton);
    }

    // MODIFIES: this
    // EFFECTS: create the deleteButton, assign it the deleteListener and add it to the buttonPane.
    private void createDeleteExpenseButton() {
        deleteExpenseButton = new JButton(DELETE_EXPENSE_STRING);
        deleteExpenseButton.setFont(new Font("Arial",0, 26));
        deleteExpenseButton.setActionCommand(DELETE_EXPENSE_STRING);
        deleteExpenseListener = new DeleteExpenseListener(list, listModel, deleteExpenseButton, listExpense, 
            expenseRecordAppGUI);
        deleteExpenseButton.addActionListener(deleteExpenseListener);
        buttonPane.add(deleteExpenseButton);
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
    // EFFECTS : disable or enable the delete and edit expense buttons.
    private void checkButtonStatus() {
        if (listModel.isEmpty()) {
            disableButtons();
        } else {
            enableButtons();
        }
    }

    // MODIFIES: this
    // EFFECTS: disables the delete and edit expense buttons.
    private void disableButtons() {
        deleteExpenseButton.setEnabled(false);
        editExpenseButton.setEnabled(false);
    }

    // MODIFIES: this
    // EFFECTS: enables the delete and edit expense buttons.
    public void enableButtons() {
        deleteExpenseButton.setEnabled(true);
        editExpenseButton.setEnabled(true);
    }

    // MODIFIES: this
    // EFFECTS: when the value of the listModel is updated, check to see if the listModel is empty.
    //          If listModel is empty: disable the delete and details button
    //          Otherwise, do nothing.
    @Override
    public void valueChanged(ListSelectionEvent e) {
        checkButtonStatus();
    }


}
