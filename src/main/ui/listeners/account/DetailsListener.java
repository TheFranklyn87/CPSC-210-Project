package ui.listeners.account;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.AccountList;
import ui.gui.account.DetailView;

// Listener class for detail of an account
public class DetailsListener extends JFrame implements ActionListener {

    private JList<String> list;
    private DefaultListModel<String> listModel;
    private AccountList acc;

    // EFFECTS: create a DetailsListener and set fields
    public DetailsListener(JList<String> list, DefaultListModel<String> listModel, AccountList acc) {
        this.list = list;
        this.listModel = listModel;
        this.acc = acc;
    }

    // EFFECTS: create a new JFrame titled "Account Details" and add in a DetailView JComponent
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame("Account Details");
        JComponent newContentPane = new DetailView(list, listModel, acc, frame);
        newContentPane.setOpaque(true);
        frame.setLayout(new BoxLayout(frame, BoxLayout.PAGE_AXIS));
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
