package ui.listeners.account;

import javax.swing.*;
import model.AccountList;
import ui.ExpenseRecordAppGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Listener class for delete an account
public class DeleteListener implements ActionListener {

    JList<String> list;
    DefaultListModel<String> listModel;
    JButton deleteButton;
    AccountList acc;
    ExpenseRecordAppGUI expenseRecordAppGUI;

    // EFFECTS: create a DeleteListener and set fields
    public DeleteListener(JList<String> list, DefaultListModel<String> listModel, JButton deleteButton, AccountList acc,
                        ExpenseRecordAppGUI expenseRecordAppGUI) {
        this.list = list;
        this.listModel = listModel;
        this.deleteButton = deleteButton;
        this.acc = acc;
        this.expenseRecordAppGUI = expenseRecordAppGUI;
    }

    // MODIFIES: listModel, acc, deleteButton
    // EFFECTS: remove the Account from the listModel and acc at the currently selected index.
    //          Set the currently selected index to be one less than it was before the deletion
    //          If the list is empty after the deletion, disable the button.
    @Override
    public void actionPerformed(ActionEvent e) {

        int index = list.getSelectedIndex();
        listModel.remove(index);

        acc.removeAtIndex(index);
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
