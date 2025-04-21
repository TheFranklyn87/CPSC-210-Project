package ui.listeners.expense;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

import model.Event;
import model.EventLog;
import model.ExpenseRecord;
import ui.ExpenseRecordAppGUI;

// Listener class for delete an expense
public class DeleteExpenseListener implements ActionListener {

    JList<String> list;
    DefaultListModel<String> listModel;
    JButton deleteButton;
    List<ExpenseRecord> listExpense;
    ExpenseRecordAppGUI expenseRecordAppGUI;

    // EFFECTS: create a DeleteExpenseListener and set fields
    public DeleteExpenseListener(JList<String> list, DefaultListModel<String> listModel, JButton deleteButton, 
                                List<ExpenseRecord> listExpense, ExpenseRecordAppGUI expenseRecordAppGUI) {
        this.list = list;
        this.listModel = listModel;
        this.deleteButton = deleteButton;
        this.listExpense = listExpense;
        this.expenseRecordAppGUI = expenseRecordAppGUI;
    }

    // MODIFIES: listModel, listExpense, deleteButton
    // EFFECTS: remove the expense from the listModel and acc at the currently selected index.
    //          Set the currently selected index to be one less than it was before the deletion
    //          If the list is empty after the deletion, disable the button.
    @Override
    public void actionPerformed(ActionEvent e) {

        int index = list.getSelectedIndex();
        listModel.remove(index);
        
        // EventLog.getInstance().logEvent(new Event("Expense Removed : " + listExpense.get(index).getExpense()));
        listExpense.remove(index);
        int size = listModel.getSize();

        if (size == 0) {
            deleteButton.setEnabled(false);

        } else {
            if (index == listModel.getSize()) {
                index--;
            }

            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }
    }

}
