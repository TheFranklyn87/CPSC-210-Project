package ui.gui.expense;

import java.awt.Font;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import model.ExpenseRecord;
import ui.ExpenseRecordAppGUI;
import ui.listeners.expense.CreateExpenseListener;

public class NewExpenseView extends ExpenseViewSuperClass {
    private CreateExpenseListener createExpenseListener;

    // MODIFIES: expenseRecordAppGUI
    // EFFECTS: create the NewExpenseView and set fields. Also create the "Create Expense" button that will create
    //          the expense (assign this button the CreateExpenseListener). Add the button to the pane.
    public NewExpenseView(JList<String> list, DefaultListModel<String> listModel, List<ExpenseRecord> listExpense,
                           ExpenseRecordAppGUI expenseRecordAppGUI) {
        super(list, listModel, listExpense);

        createButton("Create Expense");
        createExpenseListener = new CreateExpenseListener(list, listModel, listExpense, this,
        expenseRecordAppGUI);
        enterButton.addActionListener(createExpenseListener);
        enterButton.setFont(new Font("Arial",0, 28));

        add(enterButton);
        
    }

}
