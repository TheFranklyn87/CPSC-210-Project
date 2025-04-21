package ui.gui.account;

import java.awt.*;
import javax.swing.*;
import model.AccountList;
import ui.ExpenseRecordAppGUI;
import ui.gui.MainMenu;
import ui.listeners.account.EditAccountListener;

// Frame that allows users to edit an existing account
public class EditAccountView extends AccountViewSuperClass {
    private EditAccountListener editAccountListener;

    // EFFECTS: Create a DetailView and set fields. Create the submit button that saves the changes to the reminder.
    public EditAccountView(JList<String> list, DefaultListModel<String> listModel, AccountList acc,
                            ExpenseRecordAppGUI expenseRecordAppGUI, MainMenu mainMenu) {
        super(list, listModel, acc);
        this.nameField.setText(acc.getAccountAtIndex(list.getSelectedIndex()).getName());
        int limit = acc.getAccountAtIndex(list.getSelectedIndex()).getLimitExpense();
        this.limitExpenseField.setText(Integer.toString(limit));

        createButton("Save Changes");
        enterButton.setFont(new Font("Arial",0, 28));
        editAccountListener = new EditAccountListener(list, listModel, acc, this,
        expenseRecordAppGUI, mainMenu);
        enterButton.addActionListener(editAccountListener);
        add(enterButton);
    }
    
}
