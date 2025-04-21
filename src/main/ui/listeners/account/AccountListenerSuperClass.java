package ui.listeners.account;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.AccountList;
import ui.ExpenseRecordAppGUI;
import ui.gui.account.AccountViewSuperClass;

// Class that will be extended by all Account Listener 
public class AccountListenerSuperClass implements ActionListener {

    protected JList<String> list;
    protected DefaultListModel<String> listModel;
    protected AccountList acc;
    protected JFrame frame;
    protected ExpenseRecordAppGUI expenseRecordAppGUI;

    protected AccountViewSuperClass accountView;

    // EFFECTS: Create a new AccountListenerSuperClass and set fields
    public AccountListenerSuperClass(JList<String> list, DefaultListModel<String> listModel, AccountList acc,
                                    AccountViewSuperClass accountView, ExpenseRecordAppGUI expenseRecordAppGUI) {
        this.list = list;
        this.listModel = listModel;
        this.acc = acc;
        this.accountView = accountView;
        this.expenseRecordAppGUI = expenseRecordAppGUI;
    }

    // EFFECTS: None
    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
