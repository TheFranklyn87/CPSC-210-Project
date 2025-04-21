package ui.gui.expense;

import java.awt.Font;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import model.AccountList;
import model.ExpenseRecord;
import ui.ExpenseRecordAppGUI;
import ui.gui.MainMenuOpen;
import ui.listeners.expense.EditExpenseListener;

// Frame that allows users to edit an existing expense
public class EditExpenseView extends ExpenseViewSuperClass {
    private EditExpenseListener editExpenseListener;

    // EFFECTS: Create a DetailView and set fields. Create the submit button that saves the changes to the reminder.
    public EditExpenseView(JList<String> list, DefaultListModel<String> listModel, AccountList acc,
                        List<ExpenseRecord> listExpense, ExpenseRecordAppGUI expenseRecordAppGUI,
                        MainMenuOpen mainMenuOpen) {
        super(list, listModel, listExpense);
        this.descriptionField.setText(listExpense.get(list.getSelectedIndex()).getDescription());
        int amount = listExpense.get(list.getSelectedIndex()).getAmount();
        this.amountField.setText(Integer.toString(amount));
        this.dateField.setText(listExpense.get(list.getSelectedIndex()).getDate());

        createButton("Save Changes");
        enterButton.setFont(new Font("Arial",0, 28));
        editExpenseListener = new EditExpenseListener(list, listModel, acc, listExpense, this,
        expenseRecordAppGUI, mainMenuOpen);
        enterButton.addActionListener(editExpenseListener);
        add(enterButton);
    }

}
