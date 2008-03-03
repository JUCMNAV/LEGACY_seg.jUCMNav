/**
 * 
 */
package seg.jUCMNav.views.preferences.staticSemantic;

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
import seg.jUCMNav.staticSemantic.StaticSemanticDefMgr;

/**
 * @author Byrne
 * 
 */

import seg.jUCMNav.staticSemantic.Rule;
/**
 * This dialof provides the GUI of creating 1 new static checking rule or editing an exisiting rule. In this dialog, users can change the rule name, rule classifier, contxt expression,invariant expression and rule description. Furthermore, a user can open a utility editor from this dialog to create or edit a utility.
 *  
 * @author Byrne Yan
 *
 */
public class RuleEditDialog extends Dialog implements SelectionListener {

    private static final String BUTTON_DEFINE_A_NEW_UTILITY = "New";
    private static final String BUTTON_DEFINE_A_EDIT_UTILITY = "Edit";
    private static final String BUTTON_DEFINE_A_DELETE_UTILITY = "Delete";

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
    private Text txtCheck;
    /**
     * The rule description
     */
    private Text txtDesc;

    private Button btnNew;
    private Button btnEdit;
    private Button btnDelete;

    /**
     * A table that contains all utilities
     */
    private Table table;

    public RuleEditDialog(Shell parentShell) {
        super(parentShell);
    }

    public RuleEditDialog(IShellProvider parentShell) {
        super(parentShell);
    }
    /**
     * Create all GUI components. 
     * 
     */
    protected Control createDialogArea(Composite parent) {
        /*
         * FillLayout fl = new FillLayout(); fl.marginHeight = 10; fl.marginWidth = 10; parent.setLayoutData(fl);
         */
        Composite composite = (Composite) super.createDialogArea(parent);

        Composite c1 = new Composite(parent, SWT.NULL);
        GridLayout layout1 = new GridLayout();
        layout1.numColumns = 1;
        c1.setLayout(layout1);

        Label lblName = new Label(c1, SWT.LEFT);
        lblName.setText("Rule Name:");
        txtName = new Text(c1, SWT.SINGLE | SWT.BORDER);
        /*
         * txtName.addModifyListener(new ModifyListener(){ @Override public void modifyText(ModifyEvent e) { eventTextNameModify();
         *  } } );
         */
        txtName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        txtName.setSize(200, SWT.DEFAULT);
        // txtName.setEditable(false);

        // txtDesc.setEditable(false);

        Label lblContext = new Label(c1, SWT.LEFT);
        lblContext.setText("Context:");
        txtContext = new Text(c1, SWT.MULTI | SWT.BORDER);
        /*
         * txtContext.addModifyListener(new ModifyListener(){ @Override public void modifyText(ModifyEvent e) { eventTextNameModify();
         *  } } );
         */
        txtContext.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        txtContext.setSize(200, SWT.DEFAULT);
        // txtContext.setEditable(false);

        Label lblQuery = new Label(c1, SWT.LEFT);
        lblQuery.setText("OCL query expression of collecting all objects to be checked:");
        txtQuery = new Text(c1, SWT.MULTI | SWT.BORDER);
        /*
         * txtQuery.addModifyListener(new ModifyListener(){ @Override public void modifyText(ModifyEvent e) { eventTextNameModify();
         *  } } );
         */
        txtQuery.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));
        // txtCheck.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        txtQuery.setSize(600, SWT.DEFAULT);
        // txtQuery.setEditable(false);

        Label lblCheck = new Label(c1, SWT.LEFT);
        lblCheck.setText("OCL constraint expression:");
        txtCheck = new Text(c1, SWT.MULTI | SWT.BORDER);
        /*
         * txtCheck.addModifyListener(new ModifyListener(){ @Override public void modifyText(ModifyEvent e) { eventTextNameModify();
         *  } } );
         */
        // txtCheck.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));
        txtCheck.setLayoutData(new GridData(GridData.FILL_BOTH));
        txtCheck.setSize(600, 600);
        // txtCheck.setEditable(false);

        Label lblDesc = new Label(c1, SWT.LEFT);
        lblDesc.setText("Rule Description:");
        txtDesc = new Text(c1, SWT.MULTI | SWT.BORDER);
        txtDesc.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        txtDesc.setSize(600, SWT.DEFAULT);

        Label lblUtilities = new Label(c1, SWT.LEFT);
        lblDesc.setText("Description:");

        table = new Table(c1, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        TableColumn column = new TableColumn(table, SWT.NONE);
        column.setText("Utilities");
        column.setWidth(600);

        /*
         * for(TableColumn tc: table.getColumns()) { tc.pack(); }
         */
        // table.setSize (table.computeSize (SWT.DEFAULT, SWT.DEFAULT));
        // table.setSize(600, 600);
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

    /**
     * Fill contents of components based on the rule object associated with this dailog.
     */
    private void init() {
        if (rule != null) {
            txtName.setText(rule.getName());
            txtContext.setText(rule.getClassifier());
            txtQuery.setText(rule.getContext());
            txtCheck.setText(rule.getQuery());
            txtDesc.setText(rule.getDescription());
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
            newShell.setText("Create a rule");
        else
            newShell.setText("Edit a rule");
    }

    /**
     * Close the dialog with saving the rule contents. The following regularities are checked:
     * <ul>
     * <li>The rule name must not be empty ( after trimming all leading and tailing spaces )
     * <li>The rule name must be uniqe
     * <li>The rule classifier, context expression, invraint expression and utilities must be valid.
     * </ul>
     */
    protected void okPressed() {
        if(txtName.getText().trim().length()==0)
        {
            MessageBox msg = new MessageBox(this.getShell(), SWT.ICON_ERROR);
            msg.setMessage("Rule name should not be empty.");
            msg.setText("Invalidated rule difinition");
            msg.open();
            return;
        }

        if (rule == null)// create mode
        {
            Rule r = StaticSemanticDefMgr.instance().createRule(txtName.getText(), txtContext.getText(), txtQuery.getText(), txtCheck.getText(), false,
                    txtDesc.getText());
            if (r == null) {
                MessageBox msg = new MessageBox(this.getShell(), SWT.ICON_ERROR);
                msg.setMessage("Rule name duplicates");
                msg.setText("Invalidated rule difinition");
                msg.open();
                return;
            }

            for (int i = 0; i < table.getItems().length; ++i) {
                TableItem item = table.getItems()[i];
                r.addUtility(item.getText());
            }
            if (r.isValid() == false) {
                MessageBox msg = new MessageBox(this.getShell(), SWT.ICON_ERROR);
                msg.setMessage(r.getErrors());
                msg.setText("Invalidated rule difinition");
                msg.open();
                return;
            }
            rule = r;
        } else // edit mode
        {
            if (rule.getName().compareTo(txtName.getText()) != 0)// name is chenged
            {
                Rule r = StaticSemanticDefMgr.instance().lookupRule(txtName.getText());
                if (r != null) {
                    MessageBox msg = new MessageBox(this.getShell(), SWT.ICON_ERROR);
                    msg.setMessage("Rule name duplicates");
                    msg.setText("Invalidated rule difinition");
                    msg.open();
                    return;
                }
            }
            Rule r = StaticSemanticDefMgr.instance().createRule("", txtContext.getText(), this.txtQuery.getText(), this.txtCheck.getText(), false,
                    txtDesc.getText());
            for (int i = 0; i < table.getItems().length; ++i) {
                TableItem item = table.getItems()[i];
                r.addUtility(item.getText());
            }
            if (!r.isValid()) {
              
                    MessageBox msg = new MessageBox(this.getShell(), SWT.ICON_ERROR);
                    msg.setMessage(r.getErrors());
                    msg.setText("Invalidated rule difinition");
                    msg.open();
                    return;
            }
            rule.setName(txtName.getText());
            rule.setClassifier(txtContext.getText());
            rule.setDescription(txtDesc.getText());
            rule.setContext(txtQuery.getText());
            rule.setQuery(txtQuery.getText());
        }
        super.okPressed();
    }

    public void widgetDefaultSelected(SelectionEvent e) {
        // do nothing
    }

    /**
     * Button click event dispacher
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
     * @param idx   the row index of the utility
     */
    private void performDeleteUtility(int idx) {
        table.remove(idx);
        table.select(idx);

    }

    /**
     * Open the RuleUtilityEditDialog and update the utility after the dialog is closed.
     */
    private void performEditUtility() {
        RuleUtilityEditDialog dlg = new RuleUtilityEditDialog(this.getShell());
        dlg.setTitle("Modify a utility");
        dlg.setText(table.getSelection()[0].getText());
        if (Window.OK == dlg.open()) {
            updateUtility(table.getSelection()[0], dlg.getText());
        }
    }

    /**
     * Update a row of table with the specified content
     * @param item  the row of table to be updated
     * @param text  the content to be updated to the table
     */
    private void updateUtility(TableItem item, String text) {
        item.setText(0, text);
    }

    /**
     * Open a rule utility creating dialog and then append the created utility at the end of the table and make a selction on this item.
     */
    private void performNewUtility() {

        RuleUtilityEditDialog dlg = new RuleUtilityEditDialog(this.getShell());
        dlg.setTitle("Define a new utility");
        if (Window.OK == dlg.open()) {
            appendUtility(dlg.getText());
            table.setSelection(table.getItemCount() - 1);
        }
    }

    /**
     * Append a new table item with the specified content
     * @param text  the content to be appended
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
     * Asscociated a rule object with the dialog.
     * @param rule
     */
    public void setRule(Rule rule) {
        this.rule = rule;
    }
}
