package ui.listeners.account;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.AccountList;
import ui.ExpenseRecordAppGUI;
import ui.gui.account.NewAccountView;

// Listener class when click new that pop up window for add new account
public class MainMenuClickNewAccountListener extends JFrame implements ActionListener {

    private JList<String> list;
    private DefaultListModel<String> listModel;
    private AccountList acc;
    private JFrame frame;
    private ExpenseRecordAppGUI expenseRecordAppGUI;

    // EFFECTS: create a new MainMenuClickNewAccountListener and set fields.
    public MainMenuClickNewAccountListener(JList<String> list, DefaultListModel<String> listModel, AccountList acc, 
                                            ExpenseRecordAppGUI expenseRecordAppGUI) {
        this.list = list;
        this.listModel = listModel;
        this.acc = acc;
        this.expenseRecordAppGUI = expenseRecordAppGUI;
    }

    // EFFECTS: create a new JFrame titled "New Account" and add in a NewAccountView JComponent
    @Override
    public void actionPerformed(ActionEvent e) {
        frame = new JFrame("New Account");
        JComponent newContentPane = new NewAccountView(list, listModel, acc, expenseRecordAppGUI);
        newContentPane.setOpaque(true);
        frame.setLayout(new BoxLayout(frame, BoxLayout.PAGE_AXIS));
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
