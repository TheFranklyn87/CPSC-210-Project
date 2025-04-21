package ui.listeners.account;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.AccountList;
import ui.ExpenseRecordAppGUI;
import ui.gui.MainMenu;
import ui.gui.account.EditAccountView;

// Listener class when click edit that pop up window for editting the account
public class MainMenuClickEditAccountListener extends JFrame implements ActionListener {
    private JList<String> list;
    private DefaultListModel<String> listModel;
    private AccountList acc;
    private JFrame frame;
    private ExpenseRecordAppGUI expenseRecordAppGUI;
    private MainMenu mainMenu;

    // EFFECTS: create a new MainMenuClickEditAccountListener and set fields.
    public MainMenuClickEditAccountListener(JList<String> list, DefaultListModel<String> listModel, AccountList acc,
                                            ExpenseRecordAppGUI expenseRecordAppGUI, MainMenu mainMenu) {
        this.list = list;
        this.listModel = listModel;
        this.acc = acc;
        this.expenseRecordAppGUI = expenseRecordAppGUI;
        this.mainMenu = mainMenu;
    }

    // EFFECTS: create a new JFrame titled "Edit Account" and add in a EditAccountView JComponent
    @Override
    public void actionPerformed(ActionEvent e) {
        frame = new JFrame("Edit Account");
        JComponent newContentPane = new EditAccountView(list, listModel, acc, expenseRecordAppGUI, mainMenu);
        newContentPane.setOpaque(true);
        frame.setLayout(new BoxLayout(frame, BoxLayout.PAGE_AXIS));
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
