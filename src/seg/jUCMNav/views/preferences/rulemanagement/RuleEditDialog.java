package seg.jUCMNav.views.preferences.rulemanagement;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import seg.jUCMNav.Messages;
import seg.jUCMNav.rulemanagement.Rule;
import seg.jUCMNav.rulemanagement.RuleManagementDefinitionManager;

/**
 * This dialog provides the GUI of creating 1 new static checking rule or editing an existing rule. In this dialog, users can change the rule name, rule
 * classifier, context expression,invariant expression and rule description. Furthermore, a user can open a utility editor from this dialog to create or edit a
 * utility.
 * 
 * @author Byrne Yan
 * 
 */
public class RuleEditDialog extends Dialog implements SelectionListener {

    private static final String BUTTON_DEFINE_A_NEW_UTILITY = Messages.getString("RuleEditDialog.New"); //$NON-NLS-1$
    private static final String BUTTON_DEFINE_A_EDIT_UTILITY = Messages.getString("RuleEditDialog.Edit"); //$NON-NLS-1$
    private static final String BUTTON_DEFINE_A_DELETE_UTILITY = Messages.getString("RuleEditDialog.Delete"); //$NON-NLS-1$

    private Rule rule = null;

    /**
     * The rule name
     */
    private Text txtName;
    /**
     * The rule classifier
     */
    private Text txtContext;
    /**
     * The rule context expression
     */
    private Text txtQuery;
    /**
     * The rule invariant expression
     */
    protected Text txtCheck;

    /**
     * The rule description
     */
    private Text txtDesc;

    /**
     * The level error indicator. True means Warning instead of Error.
     */
    private Button btnWarningOnly;

    private Button btnNew;
    private Button btnEdit;
    private Button btnDelete;

    private RuleManagementDefinitionManager defferManager;

    /**
     * A table that contains all utilities
     */
    private Table table;

    public RuleEditDialog(Shell parentShell, RuleManagementDefinitionManager defferManager) {
        super(parentShell);
        this.defferManager = defferManager;
    }

    public RuleEditDialog(IShellProvider parentShell, RuleManagementDefinitionManager defferManager) {
        super(parentShell);
        this.defferManager = defferManager;
    }

    /**
     * Create all GUI components.
     * 
     */
    protected Control createDialogArea(Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);

        Composite c1 = new Composite(parent, SWT.NULL);
        GridLayout layout1 = new GridLayout();
        layout1.numColumns = 1;
        c1.setLayout(layout1);

        Label lblName = new Label(c1, SWT.LEFT);
        lblName.setText(Messages.getString("RuleEditDialog.RuleName")); //$NON-NLS-1$
        txtName = new Text(c1, SWT.SINGLE | SWT.BORDER);
        txtName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        txtName.setSize(200, SWT.DEFAULT);

        Label lblContext = new Label(c1, SWT.LEFT);
        lblContext.setText(Messages.getString("RuleEditDialog.Context")); //$NON-NLS-1$
        txtContext = new Text(c1, SWT.MULTI | SWT.BORDER);
        txtContext.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        txtContext.setSize(200, SWT.DEFAULT);
        // Metrics cannot edit this field
        if (this.getClass().getName().endsWith("MetricsRuleEditDialog")) //$NON-NLS-1$
        {
            txtContext.setText("urn::URNspec"); //$NON-NLS-1$
            txtContext.setEnabled(false);
        }

        Label lblQuery = new Label(c1, SWT.LEFT);
        lblQuery.setText(getQueryLabel()); //$NON-NLS-1$
        txtQuery = new Text(c1, SWT.MULTI | SWT.BORDER);

        GridData gd = getQueryGridData();
        txtQuery.setLayoutData(gd);
        txtQuery.setSize(600, SWT.DEFAULT);

        // create the GUI for constraint
        createConstraintGUI(c1);

        Label lblDesc = new Label(c1, SWT.LEFT);
        lblDesc.setText(Messages.getString("RuleEditDialog.RuleDescription")); //$NON-NLS-1$
        txtDesc = new Text(c1, SWT.MULTI | SWT.BORDER);
        txtDesc.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        txtDesc.setSize(600, SWT.DEFAULT);

        btnWarningOnly = new Button(c1, SWT.CHECK);
        btnWarningOnly.setText(Messages.getString("RuleEditDialog.ReportAsWarning")); //$NON-NLS-1$ 
        btnWarningOnly.addSelectionListener(this);
        // Metrics do not need this checkbox...
        if (this.getClass().getName().endsWith("MetricsRuleEditDialog")) //$NON-NLS-1$
        {
            btnWarningOnly.setVisible(false);
        }

        Label lblUtilities = new Label(c1, SWT.LEFT);
        lblDesc.setText(Messages.getString("RuleEditDialog.Description")); //$NON-NLS-1$

        table = new Table(c1, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        TableColumn column = new TableColumn(table, SWT.NONE);
        column.setText(Messages.getString("RuleEditDialog.Utilities")); //$NON-NLS-1$
        column.setWidth(600);

        table.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL | GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL));

        Composite c = new Composite(c1, SWT.NULL);
        GridLayout layout = new GridLayout();
        layout.numColumns = 3;
        c.setLayout(layout);

        c.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));
        btnNew = new Button(c, SWT.PUSH);
        btnNew.setText(BUTTON_DEFINE_A_NEW_UTILITY);
        btnNew.addSelectionListener(this);

        btnEdit = new Button(c, SWT.PUSH);
        btnEdit.setText(BUTTON_DEFINE_A_EDIT_UTILITY);
        btnEdit.addSelectionListener(this);

        btnDelete = new Button(c, SWT.PUSH);
        btnDelete.setText(BUTTON_DEFINE_A_DELETE_UTILITY);
        btnDelete.addSelectionListener(this);

        init();
        return composite;
    }

    protected GridData getQueryGridData() {
        GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
        gd.heightHint = 50;
        return gd;

    }

    protected String getQueryLabel() {
        return Messages.getString("RuleEditDialog.QueryExpression"); //$NON-NLS-1$
    }

    protected void createConstraintGUI(Composite c1) {
        Label lblCheck = new Label(c1, SWT.LEFT);
        lblCheck.setText(Messages.getString("RuleEditDialog.ConstraintExpression")); //$NON-NLS-1$
        txtCheck = new Text(c1, SWT.MULTI | SWT.BORDER);

        GridData gdText = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
        gdText.heightHint = 80;
        txtCheck.setLayoutData(gdText);
        txtCheck.setSize(600, SWT.DEFAULT);
    }

    /**
     * Fill contents of components based on the rule object associated with this dialog.
     */
    private void init() {
        if (rule != null) {
            txtName.setText(rule.getName());
            txtContext.setText(rule.getClassifier());
            txtQuery.setText(rule.getContext());

            if (txtCheck != null) {
                txtCheck.setText(rule.getQuery());
            }
            txtDesc.setText(rule.getDescription());
            btnWarningOnly.setSelection(rule.getWarningOnly());
            for (int i = 0; i < rule.getUtilities().size(); ++i) {
                String s = (String) rule.getUtilities().get(i);
                appendUtility(s);
            }
        }
        table.pack();
        table.setFocus();
        table.setSelection(0);

    }

    /**
     * Specify the title of dialog.
     */
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        if (rule == null)
            newShell.setText(Messages.getString("RuleEditDialog.CreateARule")); //$NON-NLS-1$
        else
            newShell.setText(Messages.getString("RuleEditDialog.EditARule")); //$NON-NLS-1$
    }

    /**
     * Close the dialog with saving the rule contents. The following regularities are checked:
     * <ul>
     * <li>The rule name must not be empty ( after trimming all leading and tailing spaces )
     * <li>The rule name must be unique
     * <li>The rule classifier, context expression, invariant expression and utilities must be valid.
     * </ul>
     */
    protected void okPressed() {
        if (txtName.getText().trim().length() == 0) {
            MessageBox msg = new MessageBox(this.getShell(), SWT.ICON_ERROR);
            msg.setMessage(Messages.getString("RuleEditDialog.RuleNameNotEmpty")); //$NON-NLS-1$
            msg.setText(Messages.getString("RuleEditDialog.InvalidRuleDefinition")); //$NON-NLS-1$
            msg.open();
            return;
        }

        if (rule == null)// create mode
        {
            Rule r = defferManager.createRule(txtName.getText(), txtContext.getText(), txtQuery.getText(), txtCheck == null ? "" : txtCheck.getText(), false, //$NON-NLS-1$
                    btnWarningOnly.getSelection(), txtDesc.getText());
            if (r == null) {
                MessageBox msg = new MessageBox(this.getShell(), SWT.ICON_ERROR);
                msg.setMessage(Messages.getString("RuleEditDialog.RuleNameDuplicates")); //$NON-NLS-1$
                msg.setText(Messages.getString("RuleEditDialog.InvalidRuleDefinition")); //$NON-NLS-1$
                msg.open();
                return;
            }

            for (int i = 0; i < table.getItems().length; i++) {
                TableItem item = table.getItems()[i];
                r.addUtility(item.getText());
            }
            if (r.isValid() == false) {
                MessageBox msg = new MessageBox(this.getShell(), SWT.ICON_ERROR);
                msg.setMessage(r.getErrors());
                msg.setText(Messages.getString("RuleEditDialog.InvalidRuleDefinition")); //$NON-NLS-1$
                msg.open();
                return;
            }
            rule = r;
        } else // edit mode
        {
            if (rule.getName().compareTo(txtName.getText()) != 0)// name is changed
            {
                Rule r = defferManager.lookupRule(txtName.getText());
                if (r != null) {
                    MessageBox msg = new MessageBox(this.getShell(), SWT.ICON_ERROR);
                    msg.setMessage(Messages.getString("RuleEditDialog.RuleNameDuplicates")); //$NON-NLS-1$
                    msg.setText(Messages.getString("RuleEditDialog.InvalidRuleDefinition")); //$NON-NLS-1$
                    msg.open();
                    return;
                }
            }
            Rule r = defferManager.createRule("", txtContext.getText(), this.txtQuery.getText(), this.txtCheck == null ? "" : this.txtCheck.getText(), false, //$NON-NLS-1$ //$NON-NLS-2$
                    btnWarningOnly.getSelection(), txtDesc.getText());

            for (int i = 0; i < table.getItems().length; i++) {
                TableItem item = table.getItems()[i];
                r.addUtility(item.getText());
            }
            if (!r.isValid()) {
                MessageBox msg = new MessageBox(this.getShell(), SWT.ICON_ERROR);
                msg.setMessage(r.getErrors());
                msg.setText(Messages.getString("RuleEditDialog.InvalidRuleDefinition")); //$NON-NLS-1$
                msg.open();
                return;
            }
            rule.setName(txtName.getText());
            rule.setClassifier(txtContext.getText());
            rule.setDescription(txtDesc.getText());
            rule.setContext(txtQuery.getText());
            rule.setWarningOnly(btnWarningOnly.getSelection());
            rule.setQuery(txtCheck == null ? "" : txtCheck.getText()); //$NON-NLS-1$
            rule.clearUtilities();
            for (int i = 0; i < table.getItems().length; i++) {
                TableItem item = table.getItems()[i];
                rule.addUtility(item.getText());
            }
        }
        super.okPressed();
    }

    public void widgetDefaultSelected(SelectionEvent e) {
        // do nothing
    }

    /**
     * Button click event dispatcher
     */
    public void widgetSelected(SelectionEvent e) {
        if (e.getSource() instanceof Button) {
            Button btn = (Button) e.getSource();
            if (btn.getText().compareTo(BUTTON_DEFINE_A_NEW_UTILITY) == 0) {
                performNewUtility();
            } else if (btn.getText().compareTo(BUTTON_DEFINE_A_EDIT_UTILITY) == 0) {
                performEditUtility();
            } else if (btn.getText().compareTo(BUTTON_DEFINE_A_DELETE_UTILITY) == 0) {
                performDeleteUtility(table.getSelectionIndex());
            }
        }
    }

    /**
     * Remove a specified utility in the table and then make a selection on the next utility.
     * 
     * @param idx
     *            the row index of the utility
     */
    private void performDeleteUtility(int idx) {
        table.remove(idx);
        if (idx < table.getItemCount())
            table.select(idx);
        else
            table.select(idx - 1);
    }

    /**
     * Open the RuleUtilityEditDialog and update the utility after the dialog is closed.
     */
    private void performEditUtility() {
        RuleUtilityEditDialog dlg = new RuleUtilityEditDialog(this.getShell());
        dlg.setTitle(Messages.getString("RuleEditDialog.ModifyUtility")); //$NON-NLS-1$
        dlg.setText(table.getSelection()[0].getText());
        if (Window.OK == dlg.open()) {
            updateUtility(table.getSelection()[0], dlg.getText());
        }
    }

    /**
     * Update a row of table with the specified content
     * 
     * @param item
     *            the row of table to be updated
     * @param text
     *            the content to be updated to the table
     */
    private void updateUtility(TableItem item, String text) {
        item.setText(0, text);
    }

    /**
     * Open a rule utility creating dialog and then append the created utility at the end of the table and make a selction on this item.
     */
    private void performNewUtility() {

        RuleUtilityEditDialog dlg = new RuleUtilityEditDialog(this.getShell());
        dlg.setTitle(Messages.getString("RuleEditDialog.DefineNewUtility")); //$NON-NLS-1$
        if (Window.OK == dlg.open()) {
            appendUtility(dlg.getText());
            table.setSelection(table.getItemCount() - 1);
        }
    }

    /**
     * Append a new table item with the specified content
     * 
     * @param text
     *            the content to be appended
     */
    private void appendUtility(String text) {
        TableItem item = new TableItem(table, SWT.NONE);
        table.select(table.getItemCount() - 1);
        updateUtility(item, text);
    }

    /**
     * Returns the rule object associated with the dialog
     */
    public Rule getRule() {
        return rule;
    }

    /**
     * Associated a rule object with the dialog.
     * 
     * @param rule
     */
    public void setRule(Rule rule) {
        this.rule = rule;
    }
}
