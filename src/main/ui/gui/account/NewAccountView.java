package ui.gui.account;

import java.awt.*;
import javax.swing.*;
import model.AccountList;
import ui.ExpenseRecordAppGUI;
import ui.listeners.account.CreateAccountListener;

// Class that represents the menu that displays when the user is creating a new account
public class NewAccountView extends AccountViewSuperClass {
    private CreateAccountListener createAccountListener;

    // MODIFIES: expenseRecordAppGUI
    // EFFECTS: create the NewAccountView and set fields. Also create the "Create Account" button that will create
    //          the account (assign this button the CreateAccountListener). Add the button to the pane.
    public NewAccountView(JList<String> list, DefaultListModel<String> listModel, AccountList acc,
                           ExpenseRecordAppGUI expenseRecordAppGUI) {
        super(list, listModel, acc);

        createButton("Create Account");
        createAccountListener = new CreateAccountListener(list, listModel, acc, this,
        expenseRecordAppGUI);
        enterButton.addActionListener(createAccountListener);
        enterButton.setFont(new Font("Arial",0, 28));

        add(enterButton);
        
    }
}
