package ui.gui.account;

import java.awt.*;
import javax.swing.*;
import model.AccountList;
import ui.listeners.account.CloseDetailViewListener;

// Frame that shows the details of the account.
public class DetailView extends AccountViewSuperClass {

    private CloseDetailViewListener closeDetailViewListener;

    // EFFECTS: Create a DetailView and set fields. JTextFields are set to be uneditable.
    public DetailView(JList<String> list, DefaultListModel<String> listModel, AccountList acc, JFrame detailsListener) {
        super(list, listModel, acc);
        this.nameField.setText(acc.getAccountAtIndex(list.getSelectedIndex()).getName());
        int limit = acc.getAccountAtIndex(list.getSelectedIndex()).getLimitExpense();
        this.limitExpenseField.setText(Integer.toString(limit));

        createButton("Close");
        enterButton.setFont(new Font("Arial",0, 28));
        closeDetailViewListener = new CloseDetailViewListener(list, listModel, acc, this,
                null, detailsListener);
        enterButton.addActionListener(closeDetailViewListener);

        add(enterButton);

        nameField.setEditable(false);
        limitExpenseField.setEditable(false);
    }

}
