package ui.listeners.expense;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.*;
import model.ExpenseRecord;
import ui.ExpenseRecordAppGUI;
import ui.gui.MainMenu;
import ui.gui.expense.ExpenseViewSuperClass;

// Listener class for create expense function
public class CreateExpenseListener extends ExpenseListenerSuperClass {

    // EFFECTS: create a CreateExpenseListener and set fields
    public CreateExpenseListener(JList<String> list, DefaultListModel<String> listModel,
                                List<ExpenseRecord> listExpense, ExpenseViewSuperClass expenseView, 
                                ExpenseRecordAppGUI expenseRecordAppGUI) {
        super(list, listModel, listExpense, expenseView, expenseRecordAppGUI);
    }

    // MODIFIES: expenseView, listExpense, listModel
    // EFFECTS: Try to create a new expense based on text in the fields.
    //          - create a expense and add it to listExpense and listModel.
    //          - clear the text in the fields
    //          - create a popup to notify the user that the expense was created.
    @Override
    public void actionPerformed(ActionEvent e) {
        ExpenseRecord expense = new ExpenseRecord(expenseView.getDescription(), 
                                                    Integer.parseInt(expenseView.getAmount()), expenseView.getDate());

        listExpense.add(expense);
        listModel.addElement(expense.getExpense());;
        clearFields();
        MainMenu m = (MainMenu) expenseRecordAppGUI.getMainMenu();
        m.enableButtons();
        
        ImageIcon icon = new ImageIcon("./image/thumbs-up.png");
        Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(img));

        JPanel panel = new JPanel();
        panel.add(imageLabel);
        panel.add(new JLabel("Expense created!"));
        JOptionPane.showMessageDialog(null, panel);
    }

    // MODIFIES: expenseView
    // EFFECTS: removes the text from the JTextFieldsinside of accountView
    private void clearFields() {
        expenseView.getDescriptionField().setText("");
        expenseView.getAmountField().setText("");
        expenseView.getDateField().setText("");
    }

}
