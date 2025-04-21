package ui.gui.account;

import java.awt.*;
import javax.swing.*;
import model.AccountList;

// Class that will be extended by the popup menu that the edit, new and details functions will use.
public abstract class AccountViewSuperClass extends JPanel {
    protected JList<String> list;
    protected DefaultListModel<String> listModel;
    protected AccountList acc;

    protected JLabel nameLabel;
    protected JLabel limitExpenseLabel;

    protected JTextField nameField;
    protected JTextField limitExpenseField;

    protected JButton enterButton;

    protected JPanel nameSection;
    protected JPanel limitExpenseSection;

    // MODIFIES: this
    // EFFECTS: - create the AccountViewSuperClass and set the fields.
    //          - create the JLabels, JTextArea and the JTextField
    //          - Add labels and textboxes to the panel.
    protected AccountViewSuperClass(JList<String> list, DefaultListModel<String> listModel, AccountList acc) {
        this.list = list;
        this.listModel = listModel;
        this.acc = acc;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        createLabels();
        createFields();

        add(nameSection);
        add(limitExpenseSection);
    }

    // MODIFIES: this
    // EFFECTS: create the name and limit expense labels that will be displayed. Set Font to Arial and size to 28
    private void createLabels() {
        nameLabel = new JLabel("Name: ");
        nameLabel.setFont(new Font("Arial",0, 28));

        limitExpenseLabel = new JLabel("Limit Expense: ");
        limitExpenseLabel.setFont(new Font("Arial",0, 28));
    }

    // MODIFIES: this
    // EFFECTS: create the fields for the name and limit expense. Set font to Arial and size to 28.
    //          Set up a grid to lay out the fields vertically
    //              - Each section will have its own panel, with each panel containing the label and the textbox
    //                next to each other horizontally
    //              - the sections are laid out vertically to get the desired grid.
    private void createFields() {
        nameField = new JTextField(20);
        nameField.setFont(new Font("Arial",0, 28));

        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;

        limitExpenseField = new JTextField(20);
        limitExpenseField.setFont(new Font("Arial",0, 28));

        nameSection = new JPanel();
        nameSection.add(nameLabel);
        nameSection.add(nameField, c);

        limitExpenseSection = new JPanel();
        limitExpenseSection.add(limitExpenseLabel);
        limitExpenseSection.add(limitExpenseField, c);
    }

    // MODIFIES: this
    // EFFECTS: create the button at the button of the frame.
    protected void createButton(String buttonString) {
        enterButton = new JButton(buttonString);
        enterButton.setActionCommand(buttonString);
    }

    // EFFECTS: return the name that input to the nameField
    public String getName() {
        return this.nameField.getText();
    }

    // EFFECTS: return the limitExpense that input to the limitExpenseField
    public String getLimitExpense() {
        return this.limitExpenseField.getText();
    }

    // EFFECTS: return the name as JTextField type
    public JTextField getNameField() {
        return this.nameField;
    }

    // EFFECTS: return the limitExpense as JTextField type
    public JTextField getLimitExpenseField() {
        return this.limitExpenseField;
    }

}
