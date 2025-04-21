package ui.listeners.account;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.AccountList;
import ui.ExpenseRecordAppGUI;
import ui.gui.MainMenuOpen;

// Listener class when click open that pop up window that represent expenses of that account
public class MainMenuClickOpenAccountListener extends JFrame implements ActionListener {
    private JList<String> list;
    private DefaultListModel<String> listModel;
    private AccountList acc;
    private JFrame frame;
    private ExpenseRecordAppGUI expenseRecordAppGUI;

    // EFFECTS: create a new MainMenuClickOpenAccountListener and set fields.
    public MainMenuClickOpenAccountListener(JList<String> list, DefaultListModel<String> listModel, AccountList acc,
                                            ExpenseRecordAppGUI expenseRecordAppGUI) {
        this.list = list;
        this.listModel = listModel;
        this.acc = acc;
        this.expenseRecordAppGUI = expenseRecordAppGUI;
    }

    // EFFECTS: create a new JFrame titled "Expenses of ... Account" and add in a OpenView JComponent
    @Override
    public void actionPerformed(ActionEvent e) {
        frame = new JFrame("Expenses of " + acc.getAccountAtIndex(list.getSelectedIndex()).getName() + " Account");
        JComponent newContentPane = new MainMenuOpen(list, listModel, expenseRecordAppGUI, acc);
        newContentPane.setOpaque(true);
        frame.setLayout(new BoxLayout(frame, BoxLayout.PAGE_AXIS));
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
