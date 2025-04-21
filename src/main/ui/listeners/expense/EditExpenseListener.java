package ui.listeners.expense;

import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.*;
import model.AccountList;
import model.Event;
import model.EventLog;
import model.ExpenseRecord;
import ui.ExpenseRecordAppGUI;
import ui.gui.MainMenuOpen;
import ui.gui.expense.ExpenseViewSuperClass;

// Listener class for edit an expense
public class EditExpenseListener extends ExpenseListenerSuperClass {

    private MainMenuOpen mainMenuOpen;
    private AccountList acc;

    // EFFECTS: create a EditExpenseListener and set fields.
    public EditExpenseListener(JList<String> list, DefaultListModel<String> listModel, AccountList acc,
                                List<ExpenseRecord> listExpense, ExpenseViewSuperClass expenseView,
                                ExpenseRecordAppGUI expenseRecordAppGUI, MainMenuOpen mainMenuOpen) {
        super(list, listModel, listExpense, expenseView, expenseRecordAppGUI);
        this.mainMenuOpen = mainMenuOpen;
        this.acc = acc;
    }

    // REQUIRED: amount must be integer
    // MODIFIES: ExpenseView, listExpense, listModel
    // EFFECTS: Try to create a new expense based on text in the fields.
    //          - get the expense at the current index and replace the fields of the expense with
    //            the fields of the NEW expense
    //          - Create a popup to notify the user that the expense was created.
    @Override
    public void actionPerformed(ActionEvent e) {
        ExpenseRecord expense = listExpense.get(list.getSelectedIndex());
        expense.setDescription(expenseView.getDescription());
        expense.setAmount(Integer.parseInt(expenseView.getAmount()));
        expense.setDate(expenseView.getDate());
        // EventLog.getInstance().logEvent(new Event("Expense Info Changed"));

        mainMenuOpen.resetPanels(acc);
        JOptionPane.showMessageDialog(new JPanel(), "Expense updated!");
    }

}
