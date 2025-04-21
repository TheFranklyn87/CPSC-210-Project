package ui.listeners.expense;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import model.ExpenseRecord;
import ui.ExpenseRecordAppGUI;
import ui.gui.expense.NewExpenseView;

// Listener class when click new that pop up window for add new expense
public class MainMenuClickNewExpenseListener extends JFrame implements ActionListener {

    private JList<String> list;
    private DefaultListModel<String> listModel;
    private List<ExpenseRecord> listExpense;
    private JFrame frame;
    private ExpenseRecordAppGUI expenseRecordAppGUI;

    // EFFECTS: create a new MainMenuClickNewExpenseListener and set fields.
    public MainMenuClickNewExpenseListener(JList<String> list, DefaultListModel<String> listModel,
                                        List<ExpenseRecord> listExpense, ExpenseRecordAppGUI expenseRecordAppGUI) {
        this.list = list;
        this.listModel = listModel;
        this.listExpense = listExpense;
        this.expenseRecordAppGUI = expenseRecordAppGUI;
    }

    // EFFECTS: create a new JFrame titled "New Expense" and add in a NewExpenseView JComponent
    @Override
    public void actionPerformed(ActionEvent e) {
        frame = new JFrame("New Expense");
        JComponent newContentPane = new NewExpenseView(list, listModel, listExpense, expenseRecordAppGUI);
        newContentPane.setOpaque(true);
        frame.setLayout(new BoxLayout(frame, BoxLayout.PAGE_AXIS));
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
