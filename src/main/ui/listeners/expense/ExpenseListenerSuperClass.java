package ui.listeners.expense;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import model.ExpenseRecord;
import ui.ExpenseRecordAppGUI;
import ui.gui.expense.ExpenseViewSuperClass;

// Class that will be extended by all Expense Listener 
public class ExpenseListenerSuperClass implements ActionListener {

    protected JList<String> list;
    protected DefaultListModel<String> listModel;
    protected List<ExpenseRecord> listExpense;
    protected JFrame frame;
    protected ExpenseRecordAppGUI expenseRecordAppGUI;

    protected ExpenseViewSuperClass expenseView;

    // EFFECTS: Create a new ExpenseListenerSuperClass and set fields
    public ExpenseListenerSuperClass(JList<String> list, DefaultListModel<String> listModel,
                                    List<ExpenseRecord> listExpense, ExpenseViewSuperClass expenseView,
                                    ExpenseRecordAppGUI expenseRecordAppGUI) {
        this.list = list;
        this.listModel = listModel;
        this.listExpense = listExpense;
        this.expenseView = expenseView;
        this.expenseRecordAppGUI = expenseRecordAppGUI;
    }

    // EFFECTS: None
    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
