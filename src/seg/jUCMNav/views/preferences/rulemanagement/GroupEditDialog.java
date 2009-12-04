package seg.jUCMNav.views.preferences.rulemanagement;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
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
import seg.jUCMNav.rulemanagement.RuleGroup;
import seg.jUCMNav.rulemanagement.RuleManagementDefinitionManager;

/**
 * This class provides the GUI of creating a new group or editing an existing group. The dialog is resizable.
 * 
 * @author Byrne Yan
 * 
 */
public class GroupEditDialog extends Dialog {

    private static final int BTN_ID_AddToGroup = IDialogConstants.CANCEL_ID + 1;
    private static final int BTN_ID_RemoveFromGroup = IDialogConstants.CANCEL_ID + 3;
    private RuleManagementDefinitionManager defferManager;

    /**
     * A table that contains all rules in the group
     */
    private Table members;
    /**
     * A table that contains all rules not in the group
     */
    private Table nonMembers;

    /**
     * The rule group associated with the dialog
     */
    private RuleGroup group;
    /**
     * The GUI component for the group name
     */
    private Text txtName;

    public GroupEditDialog(Shell parentShell, RuleManagementDefinitionManager defferManager) {
        super(parentShell);
        this.setShellStyle(this.getShellStyle() | SWT.RESIZE);
        this.defferManager = defferManager;
    }

    public GroupEditDialog(IShellProvider parentShell, RuleManagementDefinitionManager defferManager) {
        super(parentShell);
        this.setShellStyle(this.getShellStyle() | SWT.RESIZE);
        this.defferManager = defferManager;
    }

    /**
     * Close the dialog without saving the modification of group
     */
    protected void cancelPressed() {
        super.cancelPressed();
    }

    /**
     * Create all GUI components
     */
    protected Control createDialogArea(Composite parent) {
        Composite control = (Composite) super.createDialogArea(parent);

        Composite top = new Composite(control, SWT.NONE);
        GridLayout leftLayout = new GridLayout();
        leftLayout.numColumns = 1;
        top.setLayout(leftLayout);
        top.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        Composite middle = new Composite(control, SWT.NONE);
        GridLayout middleLayout = new GridLayout();
        middleLayout.numColumns = 2;
        middle.setLayout(middleLayout);
        middle.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        Composite bottom = new Composite(control, SWT.NONE);
        GridLayout rightLayout = new GridLayout();
        rightLayout.numColumns = 1;
        bottom.setLayout(leftLayout);
        bottom.setLayoutData(new GridData(GridData.FILL_BOTH));

        // Top panel
        Composite head = new Composite(top, SWT.NONE);
        GridLayout headLayout = new GridLayout();
        headLayout.numColumns = 2;
        head.setLayout(headLayout);
        head.setLayoutData(new GridData(GridData.FILL_BOTH));

        Label lblName = new Label(head, SWT.None);
        lblName.setText(Messages.getString("GroupEditDialog.GroupName")); //$NON-NLS-1$
        txtName = new Text(head, SWT.BORDER);
        txtName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        if (group != null)
            txtName.setText(group.getName());

        members = new Table(top, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        members.setHeaderVisible(true);
        String[] titles = {
                Messages.getString("GroupEditDialog.Name"), Messages.getString("GroupEditDialog.Description"), Messages.getString("GroupEditDialog.Context"), Messages.getString("GroupEditDialog.QueryExpression"), Messages.getString("GroupEditDialog.ConstraintExpression") }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
        int[] widths = { 150, 50, 50, 50, 50 };
        for (int i = 0; i < titles.length; i++) {
            TableColumn column = new TableColumn(members, SWT.LEFT);
            column.setText(titles[i]);
            column.setWidth(widths[i]);
        }
        members.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL | GridData.VERTICAL_ALIGN_BEGINNING));

        // Middle panel

        // Since CreateButton increases one column of parent's layout, to keep the number of column not changed, we decrease one first
        middleLayout.numColumns--;
        this.createButton(middle, BTN_ID_AddToGroup, Messages.getString("GroupEditDialog.AddToGroup"), false); //$NON-NLS-1$
        middleLayout.numColumns--;
        this.createButton(middle, BTN_ID_RemoveFromGroup, Messages.getString("GroupEditDialog.RemoveFromGroup"), false); //$NON-NLS-1$

        // Bottom panel
        Label lblNonMembers = new Label(bottom, SWT.None);
        lblNonMembers.setText(Messages.getString("GroupEditDialog.OtherRules")); //$NON-NLS-1$
        nonMembers = new Table(bottom, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        nonMembers.setHeaderVisible(true);
        for (int i = 0; i < titles.length; i++) {
            TableColumn column = new TableColumn(nonMembers, SWT.LEFT);
            column.setText(titles[i]);
            column.setWidth(widths[i]);
        }
        nonMembers.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        if (group != null) {
            List rules = group.getRules();
            for (int i = 0; i < rules.size(); ++i) {
                appendRule(members, (Rule) rules.get(i));
            }
        }
        List allRules = defferManager.getRules();
        for (int i = 0; i < allRules.size(); ++i) {
            Rule r = (Rule) allRules.get(i);
            if (group == null || !group.contain(r)) {
                appendRule(nonMembers, r);
            }
        }

        return control;
    }

    /**
     * Append a specified rule to a specified table.
     * 
     * @param table
     *            the table to which the specified rule will be appended
     * @param rule
     *            the rule that will be appended to the specified table
     */
    private void appendRule(Table table, Rule rule) {
        TableItem item = new TableItem(table, SWT.NONE);
        item.setData(rule);
        item.setText(new String[] { rule.getName(), rule.getDescription(), rule.getClassifier(), rule.getContext(), rule.getQuery() });

    }

    /**
     * Close the dialog with saving the modified group. The following regularities are checked:
     * <ul>
     * <li>The group name must be not empty
     * <li>The group name must be unique
     * </ul>
     */
    protected void okPressed() {
        if (txtName.getText().trim().length() == 0) {
            MessageBox msg = new MessageBox(this.getShell(), SWT.ICON_ERROR);
            msg.setMessage(Messages.getString("GroupEditDialog.GroupNameNotEmpty")); //$NON-NLS-1$
            msg.setText(Messages.getString("GroupEditDialog.InvalidGroupDefinition")); //$NON-NLS-1$
            msg.open();
            return;
        }
        if ((group == null || group.getName().compareTo(txtName.getText()) != 0) && defferManager.lookupGroup(txtName.getText()) != null) {
            MessageBox msg = new MessageBox(this.getShell(), SWT.ICON_ERROR);
            msg.setMessage(Messages.getString("GroupEditDialog.GroupNameDuplicates")); //$NON-NLS-1$
            msg.setText(Messages.getString("GroupEditDialog.InvalidGroupDefinition")); //$NON-NLS-1$
            msg.open();
            return;
        }
        if (group == null)// create mode
        {
            group = defferManager.createRuleGroup(txtName.getText());
        }
        group.removeAll();
        TableItem[] items = members.getItems();
        for (int i = 0; i < items.length; ++i) {
            group.addRule((Rule) items[i].getData());
        }
        group.setName(txtName.getText()); // Update the group name
        super.okPressed();
    }

    /**
     * Associated a group object with the dialog
     */
    public void setGroup(RuleGroup group) {
        this.group = group;
    }

    /**
     * Set the title of the dialog
     */
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        if (group != null)
            newShell.setText(Messages.getString("GroupEditDialog.EditGroup")); //$NON-NLS-1$
        else
            newShell.setText(Messages.getString("GroupEditDialog.CreateGroup")); //$NON-NLS-1$
    }

    /**
     * Button click event dispatcher
     */
    protected void buttonPressed(int buttonId) {
        switch (buttonId) {
        case BTN_ID_AddToGroup:
            addSelectionToGroup();
            break;
        case BTN_ID_RemoveFromGroup:
            removeSelectionFromGroup();
            break;
        }

        super.buttonPressed(buttonId);
    }

    /**
     * Remove all selected rules in the group and put them back the non-members table
     */
    private void removeSelectionFromGroup() {
        TableItem[] itmes = members.getSelection();
        for (int i = 0; i < itmes.length; ++i) {
            appendRule(nonMembers, (Rule) itmes[i].getData());
        }
        members.remove(members.getSelectionIndices());
    }

    /**
     * Add all selected rules in the non-members table to the group
     */
    private void addSelectionToGroup() {
        TableItem[] itmes = nonMembers.getSelection();
        for (int i = 0; i < itmes.length; ++i) {
            appendRule(members, (Rule) itmes[i].getData());
        }
        nonMembers.remove(nonMembers.getSelectionIndices());
        this.getContents().redraw();
    }

    /**
     * Returns the group object associated with the dialog
     */
    public RuleGroup getGroup() {
        return group;
    }

}
