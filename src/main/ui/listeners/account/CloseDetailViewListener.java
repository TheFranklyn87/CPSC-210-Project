package ui.listeners.account;

import java.awt.event.ActionEvent;
import javax.swing.*;
import model.AccountList;
import ui.ExpenseRecordAppGUI;
import ui.gui.account.AccountViewSuperClass;

// Listener Class for close detail pop up
public class CloseDetailViewListener extends AccountListenerSuperClass {
    private JFrame frame;

    public CloseDetailViewListener(JList<String> list, DefaultListModel<String> listModel, AccountList acc,
                                   AccountViewSuperClass accountView, ExpenseRecordAppGUI expenseRecordAppGUI, 
                                   JFrame frame) {
        super(list, listModel, acc, accountView, expenseRecordAppGUI);
        this.frame = frame;
    }

    //EFFECTS: Close the frame on button press.
    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
    }

}
