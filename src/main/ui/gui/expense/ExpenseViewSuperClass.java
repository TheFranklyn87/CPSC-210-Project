package ui.gui.expense;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.ExpenseRecord;

// Class that will be extended by the popup menu that the edit and new functions will use.
public abstract class ExpenseViewSuperClass extends JPanel {
    protected JList<String> list;
    protected DefaultListModel<String> listModel;
    protected List<ExpenseRecord> listExpense;

    protected JLabel descriptionLabel;
    protected JLabel amountLabel;
    protected JLabel dateLabel;

    protected JTextField descriptionField;
    protected JTextField amountField;
    protected JTextField dateField;

    protected JButton enterButton;

    protected JPanel descriptionSection;
    protected JPanel amountSection;
    protected JPanel dateSection;

    // MODIFIES: this
    // EFFECTS: - create the ExpenseViewSuperClass and set the fields.
    //          - create the JLabels and the JTextField
    //          - Add labels and textboxes to the panel.
    protected ExpenseViewSuperClass(JList<String> list, DefaultListModel<String> listModel,
                                    List<ExpenseRecord> listExpense) {
        this.list = list;
        this.listModel = listModel;
        this.listExpense = listExpense;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        createLabels();
        createFields();

        add(descriptionSection);
        add(amountSection);
        add(dateSection);
    }

    // MODIFIES: this
    // EFFECTS: create the description, amount, and date labels that will be displayed. Set Font to Arial and size to 28
    private void createLabels() {
        descriptionLabel = new JLabel("Description: ");
        descriptionLabel.setFont(new Font("Arial",0, 28));

        amountLabel = new JLabel("Amount: ");
        amountLabel.setFont(new Font("Arial",0, 28));

        dateLabel = new JLabel("Date(DD/MM/YYYY): ");
        dateLabel.setFont(new Font("Arial",0, 28));
    }

    // MODIFIES: this
    // EFFECTS: create the fields for the description, amount, and date. Set font to Arial and size to 28.
    //          Set up a grid to lay out the fields vertically
    //              - Each section will have its own panel, with each panel containing the label and the textbox
    //                next to each other horizontally
    //              - the sections are laid out vertically to get the desired grid.
    private void createFields() {
        descriptionField = new JTextField(20);
        descriptionField.setFont(new Font("Arial",0, 28));

        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;

        amountField = new JTextField(20);
        amountField.setFont(new Font("Arial",0, 28));

        dateField = new JTextField(20);
        dateField.setFont(new Font("Arial", 0, 28));

        descriptionSection = new JPanel();
        descriptionSection.add(descriptionLabel);
        descriptionSection.add(descriptionField, c);

        amountSection = new JPanel();
        amountSection.add(amountLabel);
        amountSection.add(amountField, c);

        dateSection = new JPanel();
        dateSection.add(dateLabel);
        dateSection.add(dateField, c);
    }

    // MODIFIES: this
    // EFFECTS: create the button at the button of the frame.
    protected void createButton(String buttonString) {
        enterButton = new JButton(buttonString);
        enterButton.setActionCommand(buttonString);
    }

    public String getDescription() {
        return this.descriptionField.getText();
    }

    public String getAmount() {
        return this.amountField.getText();
    }

    public String getDate() {
        return this.dateField.getText();
    }

    public JTextField getDescriptionField() {
        return this.descriptionField;
    }

    public JTextField getAmountField() {
        return this.amountField;
    }

    public JTextField getDateField() {
        return this.dateField;
    }

}
