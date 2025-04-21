package ui.listeners.account;

import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.*;
import model.Account;
import model.AccountList;
import ui.gui.account.AccountViewSuperClass;
import ui.ExpenseRecordAppGUI;
import ui.gui.MainMenu;

// Listener class for create account function
public class CreateAccountListener extends AccountListenerSuperClass {

    // EFFECTS: create a CreateAccountListener and set fields
    public CreateAccountListener(JList<String> list, DefaultListModel<String> listModel, AccountList acc,
                                  AccountViewSuperClass accountView, ExpenseRecordAppGUI expenseRecordAppGUI) {
        super(list, listModel, acc, accountView, expenseRecordAppGUI);
    }

    // MODIFIES: acccountView, acc, listModel
    // EFFECTS: Try to create a new account based on text in the fields.
    //          - create a account and add it to acc and listModel.
    //          - clear the text in the fields
    //          - create a popup to notify the user that the account was created.
    @Override
    public void actionPerformed(ActionEvent e) {
        Account account = new Account(accountView.getName(), Integer.parseInt(accountView.getLimitExpense()));

        acc.addAccount(account);
        listModel.addElement(account.getName());;
        clearFields();
        MainMenu m = (MainMenu) expenseRecordAppGUI.getMainMenu();
        m.enableButtons();

        ImageIcon icon = new ImageIcon("./image/thumbs-up.png");
        Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(img));

        JPanel panel = new JPanel();
        panel.add(imageLabel);
        panel.add(new JLabel("Account created!"));
        JOptionPane.showMessageDialog(null, panel);
    }

    // MODIFIES: accountView
    // EFFECTS: removes the text from the JTextFieldsinside of accountView
    private void clearFields() {
        accountView.getNameField().setText("");
        accountView.getLimitExpenseField().setText("");
    }

}
