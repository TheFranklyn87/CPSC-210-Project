package ui.listeners.expense;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import model.AccountList;
import model.ExpenseRecord;
import ui.ExpenseRecordAppGUI;
import ui.gui.MainMenuOpen;
import ui.gui.expense.EditExpenseView;

// Listener class when click edit that pop up window for editting the expense
public class MainMenuClickEditExpenseListener extends JFrame implements ActionListener {
    private JList<String> list;
    private DefaultListModel<String> listModel;
    private List<ExpenseRecord> listExpense;
    private JFrame frame;
    private ExpenseRecordAppGUI expenseRecordAppGUI;
    private MainMenuOpen mainMenuOpen;
    private AccountList acc;

    // EFFECTS: create a new MainMenuClickEditExpenseListener and set fields.
    public MainMenuClickEditExpenseListener(JList<String> list, DefaultListModel<String> listModel, AccountList acc,
                                            List<ExpenseRecord> listExpense, ExpenseRecordAppGUI expenseRecordAppGUI,
                                            MainMenuOpen mainMenuOpen) {
        this.list = list;
        this.listModel = listModel;
        this.acc = acc;
        this.listExpense = listExpense;
        this.expenseRecordAppGUI = expenseRecordAppGUI;
        this.mainMenuOpen = mainMenuOpen;
    }

    // EFFECTS: create a new JFrame titled "Edit Expense" and add in a EditExpenseView JComponent
    @Override
    public void actionPerformed(ActionEvent e) {
        frame = new JFrame("Edit Expense");
        JComponent newContentPane = new EditExpenseView(list, listModel, acc, listExpense, 
                                                        expenseRecordAppGUI, mainMenuOpen);
        newContentPane.setOpaque(true);
        frame.setLayout(new BoxLayout(frame, BoxLayout.PAGE_AXIS));
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
