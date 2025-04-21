package ui.listeners.account;

import java.awt.event.ActionEvent;
import javax.swing.*;
import model.Account;
import model.AccountList;
import ui.gui.account.AccountViewSuperClass;
import ui.ExpenseRecordAppGUI;
import ui.gui.MainMenu;

// Listener class for edit an account
public class EditAccountListener extends AccountListenerSuperClass {

    private MainMenu mainMenu;

    // EFFECTS: create a EditAccountListener and set fields.
    public EditAccountListener(JList<String> list, DefaultListModel<String> listModel, AccountList acc,
                                AccountViewSuperClass accountView, ExpenseRecordAppGUI expenseRecordAppGUI, 
                                MainMenu mainMenu) {
        super(list, listModel, acc, accountView, expenseRecordAppGUI);
        this.mainMenu = mainMenu;
    }

    // REQUIRED: limitExpense must be integer
    // MODIFIES: AccountView, acc, listModel
    // EFFECTS: Try to create a new account based on text in the fields.
    //          - get the account at the current index and replace the fields of the account with
    //            the fields of the NEW account
    //          - Create a popup to notify the user that the account was created.
    @Override
    public void actionPerformed(ActionEvent e) {
        Account account = acc.getAccountAtIndex(list.getSelectedIndex());
        account.setName(accountView.getName());
        account.setLimitExpense(Integer.parseInt(accountView.getLimitExpense()));

        mainMenu.resetPanels(acc);
        JOptionPane.showMessageDialog(new JPanel(), "Account updated!");
    }

}
