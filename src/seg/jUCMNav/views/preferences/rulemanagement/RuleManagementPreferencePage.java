package seg.jUCMNav.views.preferences.rulemanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.rulemanagement.Rule;
import seg.jUCMNav.rulemanagement.RuleGroup;
import seg.jUCMNav.rulemanagement.RuleManagementDefinitionManager;
import seg.jUCMNav.rulemanagement.RuleManagementUtil;
import seg.jUCMNav.views.preferences.metrics.MetricsPreferencePage;

/**
 * This is the abstract class for Rule Management.
 * 
 * @author Anisur Rahman
 * 
 */
public abstract class RuleManagementPreferencePage extends PreferencePage implements IWorkbenchPreferencePage, SelectionListener, FocusListener {

    private static final String APPDATA_UTILITIES_NUMBER = Messages.getString("RuleManagementPreferencePage.UtilitiesNumber"); //$NON-NLS-1$
    private static final String APPDATA_CHECKBOX = Messages.getString("RuleManagementPreferencePage.Checkbox"); //$NON-NLS-1$
    private static final String APPDATA_UTILITY = Messages.getString("RuleManagementPreferencePage.Utility"); //$NON-NLS-1$
    private static final String BUTTON_EDIT_THE_SELECTED_RULE = Messages.getString("RuleManagementPreferencePage.Edit"); //$NON-NLS-1$
    private static final String BUTTON_DEFINE_A_NEW_RULE = Messages.getString("RuleManagementPreferencePage.NewRule"); //$NON-NLS-1$
    private static final String BUTTON_DEFINE_A_NEW_GROUP = Messages.getString("RuleManagementPreferencePage.NewGroup"); //$NON-NLS-1$
    private static final String BUTTON_DELETE = Messages.getString("RuleManagementPreferencePage.Delete"); //$NON-NLS-1$
    private static final String BUTTON_EXPORT = Messages.getString("RuleManagementPreferencePage.Export"); //$NON-NLS-1$
    private static final String BUTTON_IMPORT = Messages.getString("RuleManagementPreferencePage.Import"); //$NON-NLS-1$
    private static final String BUTTON_EDIT_THE_SELECTED_RULE_TIP = Messages.getString("RuleManagementPreferencePage.EditTip"); //$NON-NLS-1$
    private static final String BUTTON_DEFINE_A_NEW_RULE_TIP = Messages.getString("RuleManagementPreferencePage.NewRuleTip"); //$NON-NLS-1$
    private static final String BUTTON_DEFINE_A_NEW_GROUP_TIP = Messages.getString("RuleManagementPreferencePage.NewGroupTip"); //$NON-NLS-1$
    private static final String BUTTON_DELETE_TIP = Messages.getString("RuleManagementPreferencePage.DeleteTip"); //$NON-NLS-1$
    private static final String BUTTON_EXPORT_TIP = Messages.getString("RuleManagementPreferencePage.ExportTip"); //$NON-NLS-1$
    private static final String BUTTON_IMPORT_TIP = Messages.getString("RuleManagementPreferencePage.ImportTip"); //$NON-NLS-1$

    private static boolean alreadyClicked = false;
    private static TreeItem currentTreeItem = null;

    /**
     * The tree represents rule groups and rules in the corresponding group
     */
    protected Tree tree;
    private Button btnNewRule;
    private Button btnNewGroup;
    private Button btnEdit;

    private Button btnExport;
    private Button btnImport;

    protected Button btnShowDescription;
    private Button btnDelete;

    /*
     * Create all GUI components
     * 
     * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
     */
    protected Control createContents(Composite parent) {

        Composite head = new Composite(parent, SWT.NULL);
        GridLayout headLayout = new GridLayout();
        headLayout.numColumns = 1;
        head.setLayout(headLayout);
        head.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));
        btnShowDescription = new Button(head, SWT.CHECK);
        btnShowDescription.setText(Messages.getString("RuleManagementPreferencePage.ShowRule")); //$NON-NLS-1$
        // btnShowDescription.addSelectionListener(this);
        btnShowDescription.addFocusListener(this);

        Label label1 = new Label(parent, SWT.LEFT);
        label1.setText(Messages.getString("RuleManagementPreferencePage.RulesDefined")); //$NON-NLS-1$

        tree = new Tree(parent, SWT.BORDER | SWT.CHECK | SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI | SWT.FULL_SELECTION);
        tree.setHeaderVisible(true);
        tree.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                if (event.detail == SWT.CHECK) {
                    TreeItem item = (TreeItem) event.item;
                    Object data = item.getData();
                    boolean checked = item.getChecked();
                    if (data instanceof Rule) {
                        checkSameRules(item, new Rule[] { (Rule) data }, checked);
                        checkGroups();
                    } else if (data instanceof RuleGroup) {
                        RuleGroup group = (RuleGroup) data;
                        checkRules(item, checked);
                        Rule[] rules = new Rule[group.getRules().size()];
                        for (int i = 0; i < rules.length; ++i) {
                            rules[i] = (Rule) group.getRules().get(i);
                        }
                        checkSameRules(null, rules, checked);
                        checkGroups();
                    }
                } else {
                    // Fake double-click on rule/group item to edit it.
                    if (alreadyClicked && (currentTreeItem == (TreeItem) event.item)) {
                        alreadyClicked = false;
                        currentTreeItem = null;
                        performEdit();
                    } else {
                        currentTreeItem = (TreeItem) event.item;
                        alreadyClicked = true;
                    }
                }
            }
        });

        String[] titles = {
                Messages.getString("RuleManagementPreferencePage.Name"), Messages.getString("RuleManagementPreferencePage.Context"), Messages.getString("RuleManagementPreferencePage.Description") }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ 
        int[] widths = { 150, 50, 200 };
        for (int i = 0; i < titles.length; i++) {
            TreeColumn column = new TreeColumn(tree, SWT.LEFT);
            column.setText(titles[i]);
            column.setWidth(widths[i]);
        }
        tree.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL | GridData.VERTICAL_ALIGN_FILL | GridData.GRAB_VERTICAL));

        Composite c = new Composite(parent, SWT.NULL);
        GridLayout layout = new GridLayout();
        layout.numColumns = 6;
        c.setLayout(layout);

        c.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));

        btnNewRule = new Button(c, SWT.PUSH);
        btnNewRule.setText(BUTTON_DEFINE_A_NEW_RULE);
        btnNewRule.setToolTipText(BUTTON_DEFINE_A_NEW_RULE_TIP);
        btnNewRule.addSelectionListener(this);

        btnNewGroup = new Button(c, SWT.PUSH);
        btnNewGroup.setText(BUTTON_DEFINE_A_NEW_GROUP);
        btnNewGroup.setToolTipText(BUTTON_DEFINE_A_NEW_GROUP_TIP);
        btnNewGroup.addSelectionListener(this);

        btnEdit = new Button(c, SWT.PUSH);
        btnEdit.setText(BUTTON_EDIT_THE_SELECTED_RULE);
        btnEdit.setToolTipText(BUTTON_EDIT_THE_SELECTED_RULE_TIP);
        btnEdit.addSelectionListener(this);

        btnDelete = new Button(c, SWT.PUSH);
        btnDelete.setText(BUTTON_DELETE);
        btnDelete.setToolTipText(BUTTON_DELETE_TIP);
        btnDelete.addSelectionListener(this);

        btnImport = new Button(c, SWT.PUSH);
        btnImport.setText(BUTTON_IMPORT);
        btnImport.setToolTipText(BUTTON_IMPORT_TIP);
        btnImport.addSelectionListener(this);

        btnExport = new Button(c, SWT.PUSH);
        btnExport.setText(BUTTON_EXPORT);
        btnExport.setToolTipText(BUTTON_EXPORT_TIP);
        btnExport.addSelectionListener(this);

        initializeValues();
        return null;
    }

    protected abstract RuleManagementDefinitionManager getDefinitionManager();

    /**
     * Put a check/uncheck on all sub rule items of the specified group item
     * 
     * @param item
     *            the specified tree item on which a check/uncheck will be put
     * @param checked
     *            true if a check is put, false if not.
     */
    protected void checkRules(TreeItem item, boolean checked) {
        TreeItem[] ruleItems = item.getItems();
        for (int i = 0; i < ruleItems.length; ++i) {
            Rule r = (Rule) ruleItems[i].getData();
            r.setEnabled(checked);
            ruleItems[i].setChecked(checked);
            ruleItems[i].setGrayed(false);
        }

    }

    /**
     * Put a check mark on all groups based on their rules. If all rules of a group are checked, the group is put on checked mark. If only some of rules of a
     * group are checked, the group is put on a greyed mark. If no rules of a group is checked, the group is put on an unchecked mark.
     */
    protected void checkGroups() {
        TreeItem[] groupItems = tree.getItems();
        for (int i = 0; i < groupItems.length; ++i) {
            boolean checked = groupItems[i].getChecked();
            boolean grayed = groupItems[i].getGrayed();
            TreeItem[] ruleItems = groupItems[i].getItems();
            if (ruleItems.length == 1) {
                checked = ruleItems[0].getChecked();
                grayed = false;
            } else if (ruleItems.length > 1) {
                checked = ruleItems[0].getChecked();
                grayed = false;
                for (int j = 1; j < ruleItems.length; ++j) {
                    if (checked != ruleItems[j].getChecked()) {
                        checked = grayed = true;
                        break;
                    }
                }
            }
            groupItems[i].setChecked(checked);
            groupItems[i].setGrayed(grayed);
        }

    }

    /**
     * Put an checked or unchecked mark on all rule items that point to the specified same rules. No change to any group items
     * 
     * @param item
     *            the tree item that will not be considered during the processing.
     * @param rules
     *            the specified rules. Any rule item that point to one of these rules will be put on a specified mark.
     * @param checked
     *            true if an checked mark will be put on, otherwise false.
     */
    protected void checkSameRules(TreeItem item, Rule[] rules, boolean checked) {
        TreeItem[] groupItems = tree.getItems();
        for (int i = 0; i < groupItems.length; ++i) {
            TreeItem[] ruleItems = groupItems[i].getItems();
            for (int j = 0; j < ruleItems.length; ++j) {
                Rule rule = (Rule) ruleItems[j].getData();
                for (int k = 0; k < rules.length; ++k) {
                    if (rule == rules[k]) {
                        ruleItems[j].setGrayed(false);
                        ruleItems[j].setChecked(checked);
                        Rule r = (Rule) ruleItems[j].getData();
                        r.setEnabled(checked);
                        break;
                    }
                }
            }
        }

    }

    /**
     * Fill contents of all GUI components
     */
    protected abstract void initializeValues();

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public void init(IWorkbench workbench) {
        // do nothing

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetDefaultSelected(SelectionEvent e) {
        // do nothing
    }

    /*
     * Buttons click event dispatcher
     * 
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetSelected(SelectionEvent e) {
        if (e.getSource() instanceof Button) {
            Button btn = (Button) e.getSource();

            if (btn.getText().compareTo(BUTTON_DEFINE_A_NEW_RULE) == 0) {
                performNewRule();
            } else if (btn.getText().compareTo(BUTTON_DEFINE_A_NEW_GROUP) == 0) {
                performNewGroup();
            } else if (btn.getText().compareTo(BUTTON_EDIT_THE_SELECTED_RULE) == 0) {
                performEdit();
            } else if (btn.getText().compareTo(BUTTON_DELETE) == 0) {
                performDelete();
            } else if (btn.getText().compareTo(BUTTON_IMPORT) == 0) {
                performImport();
            } else if (btn.getText().compareTo(BUTTON_EXPORT) == 0) {
                performExport();
            }
        }
    }

    public void focusGained(FocusEvent e) {
        performShowDescriptionChanged();

    }

    public void focusLost(FocusEvent e) {
        performShowDescriptionChanged();

    }

    protected abstract void performShowDescriptionChanged();

    /**
     * Remove the selected rule group or rule item. Notice: the group of "All" can not be removed.
     */
    private void performDelete() {
        TreeItem[] items = tree.getSelection();
        for (int i = 0; i < items.length; ++i) {
            Object data = items[i].getData();
            if (data instanceof RuleGroup) {
                // Only empty group can be deleted
                // The group ALL can not be deleted
                RuleGroup g = (RuleGroup) data;
                if (g.getRules().size() == 0 && g.getName().compareTo("All") != 0) { //$NON-NLS-1$
                    getDefinitionManager().removeGroup(g);
                } else {
                    MessageBox msg = new MessageBox(getControl().getShell(), SWT.ICON_WARNING);
                    msg.setText(Messages.getString("RuleManagementPreferencePage.0")); //$NON-NLS-1$
                    msg.setMessage(Messages.getString("RuleManagementPreferencePage.NonEmpty")); //$NON-NLS-1$
                    msg.open();
                }

            } else if (data instanceof Rule) {
                Rule r = (Rule) data;
                getDefinitionManager().removeRule(r);
            }
        }
        updateTree(); // Can't be done inside one group; may affect multiple groups.
    }

    /**
     * Open a dialog to create a new group and put the created group into the tree.
     */
    private void performNewGroup() {
        GroupEditDialog dlg = new GroupEditDialog(getControl().getShell(), getDefinitionManager());
        if (Window.OK == dlg.open()) {
            getDefinitionManager().addGroup(dlg.getGroup());
            updateTree();
        }
    }

    /**
     * Open a dialog to create a new rule and update the group items that contains this rule(A rule is always in the group of "All").
     */
    private void performNewRule() {
        RuleEditDialog dlg = createRuleEditDialog(); // new RuleEditDialog(getControl().getShell(), getDefinitionManager());

        if (Window.OK == dlg.open()) {
            getDefinitionManager().addRule(dlg.getRule());
            updateTree();
        }
    }

    protected RuleEditDialog createRuleEditDialog() {
        return new RuleEditDialog(getControl().getShell(), getDefinitionManager());

    }

    /**
     * Export selected rules into a file that the user specifies in the file dialog.Notice: only rule items in the selected items are exported. group items
     * selected are ignored.
     */
    private void performExport() {

        TreeItem[] items = tree.getSelection();
        String fileName = null;

        if (items.length > 0) {
            boolean done = false;

            FileDialog dlg = new FileDialog(getControl().getShell(), SWT.SAVE);
            dlg.setText(Messages.getString("RuleManagementPreferencePage.ExportRulesMetricsAs")); //$NON-NLS-1$
            dlg.setFilterExtensions(new String[] { "*.xml" }); //$NON-NLS-1$

            while (!done) {
                // Open the File Dialog
                fileName = dlg.open();
                if (fileName == null) {
                    // User has cancelled, so quit and return
                    done = true;
                } else {
                    // User has selected a file; see if it already exists
                    File file = new File(fileName);
                    if (file.exists()) {
                        // The file already exists; asks for confirmation
                        MessageBox mb = new MessageBox(dlg.getParent(), SWT.ICON_WARNING | SWT.YES | SWT.NO);
                        mb.setText("Attention!"); //$NON-NLS-1$
                        mb.setMessage(fileName + " " + Messages.getString("RuleManagementPreferencePage.AlreadyExists")); //$NON-NLS-1$ //$NON-NLS-2$

                        // If they click Yes, we're done and we drop out. If
                        // they click No, we redisplay the File Dialog
                        done = mb.open() == SWT.YES;
                        if (!done) {
                            fileName = null;
                        }
                    } else {
                        // File does not exist, so drop out
                        done = true;
                    }
                }
            }

            if (fileName != null) {
                List rules = new ArrayList();

                for (int i = 0; i < items.length; ++i) {
                    Object data = items[i].getData();
                    if (data instanceof Rule) {
                        rules.add(data);
                    }
                }
                RuleManagementUtil.exportRules(rules, fileName);
            }
        }

    }

    /**
     * Import rules in the file that the user specifies in the file dialog.
     */
    private void performImport() {

        FileDialog dlg = new FileDialog(getControl().getShell(), SWT.OPEN);
        dlg.setText(Messages.getString("RuleManagementPreferencePage.ImportRulesMetricsAs")); //$NON-NLS-1$
        dlg.setFilterExtensions(new String[] { "*.xml" }); //$NON-NLS-1$

        String file = dlg.open();
        if (file != null) {
            // save modification before import new rules
            performOk();

            try {
                getDefinitionManager().importRules(file, getControl().getShell());
                updateTree();
            } catch (FileNotFoundException e) {
                MessageBox msg = new MessageBox(getControl().getShell(), SWT.ICON_ERROR);
                msg.setMessage(e.getMessage());
                msg.setText(Messages.getString("RuleManagementPreferencePage.FailureToImport")); //$NON-NLS-1$
                msg.open();
            }
        }
    }

    /**
     * Edit the selected rule or rule group. If more than one item is selected, a warning message dialog is showed.
     */
    private void performEdit() {
        // Check if only one item selected
        if (tree.getSelectionCount() != 1) {
            MessageBox msg = new MessageBox(getControl().getShell(), SWT.ICON_WARNING);
            msg.setMessage(Messages.getString("RuleManagementPreferencePage.PleaseSelectOne")); //$NON-NLS-1$
            msg.setText(Messages.getString("RuleManagementPreferencePage.RuleSelection")); //$NON-NLS-1$
            msg.open();
            return;
        }
        TreeItem item = tree.getSelection()[0];

        Object o = item.getData();
        if (o instanceof Rule) {
            editRule((Rule) o);
            updateGroupNode(item.getParentItem());
        } else if (o instanceof RuleGroup) {
            editRuleGroup((RuleGroup) o);
            updateGroupNode(item);
            checkGroups();
        }
    }

    /**
     * Update a rule group items due to the change of members of the group. The enable/disabled information will not be changed even it is not saved.
     * 
     * @param item
     */
    private void updateGroupNode(TreeItem item) {
        RuleGroup group = (RuleGroup) item.getData();

        // Save check information before remove items
        HashMap checkInfo = saveCheckInfo(item);

        item.removeAll();
        populateGroupNode(item, group);
        // Restore check states
        restoreCheckState(item, checkInfo);
    }

    /**
     * Restore all enabled/disabled information on the subitems of the specified item.
     * 
     * @param item
     *            the specified item
     * @param checkInfo
     *            the HashMap object that holds all enabled/disabled information
     */
    private void restoreCheckState(TreeItem item, HashMap checkInfo) {
        TreeItem[] items = item.getItems();
        for (int i = 0; i < items.length; ++i) {
            Boolean checked = (Boolean) checkInfo.get(items[i].getData());
            if (checked != null) {
                items[i].setChecked(checked.booleanValue());
            }
        }

    }

    /**
     * Save enabled/disabled information of all subitems of the specified item to the specified HashMap.
     * 
     * @param item
     *            the specified tree item
     * @return HashMap the HashMap object that holds all enabled/disabled information
     */
    private HashMap saveCheckInfo(TreeItem item) {
        HashMap info = new HashMap();
        TreeItem[] items = item.getItems();
        for (int i = 0; i < items.length; ++i) {
            info.put(items[i].getData(), new Boolean(items[i].getChecked()));
        }
        return info;
    }

    /**
     * Open a dialog to edit a rule.
     * 
     * @param r
     *            the rule object to be edited
     */
    protected void editRule(Rule r) {
        RuleEditDialog dlg = createRuleEditDialog(); // new RuleEditDialog(getControl().getShell(),getDefinitionManager());

        dlg.setRule(r);

        dlg.open();
    }

    /**
     * Update the entire tree based on the system groups and rules.
     */
    private void updateTree() {
        tree.removeAll();
        populateTree();
    }

    /**
     * Open a dialog to edit a specified rule group. Notice: the group of "All" can not be edited.
     * 
     * @param g
     *            the rule group to be edited
     */
    private void editRuleGroup(RuleGroup g) {
        if (g.getName().compareTo("All") == 0) { //$NON-NLS-1$
            MessageBox msg = new MessageBox(getControl().getShell(), SWT.ICON_WARNING);
            msg.setMessage(Messages.getString("RuleManagementPreferencePage.SpecialGroupAll")); //$NON-NLS-1$
            msg.setText(Messages.getString("RuleManagementPreferencePage.0")); //$NON-NLS-1$
            msg.open();
            return;
        }
        GroupEditDialog dlg = new GroupEditDialog(getControl().getShell(), getDefinitionManager());
        dlg.setGroup(g);
        dlg.open();

    }

    /**
     * Set all default values of settings
     * 
     */
    protected void performDefaults() {
        super.performDefaults();

        getDefinitionManager().loadDefault();

        btnShowDescription.setSelection(getDefinitionManager().isShowDesc());
        updateTree();

    }

    /**
     * Construct the entire tree based on the system groups and rules.
     */
    protected void populateTree() {
        List groups = getDefinitionManager().getGroups();
        for (int i = 0; i < groups.size(); ++i) {
            RuleGroup g = (RuleGroup) groups.get(i);
            TreeItem item = new TreeItem(tree, SWT.NONE);
            if (item.getImage() == null)
                item.setImage(JUCMNavPlugin.getImage("icons/folder16.gif")); //$NON-NLS-1$
            populateGroupNode(item, g);
        }
        checkGroups();
    }

    /**
     * Construct subitems of the specified rule group tree item based on the specified rule group object.
     * 
     * @param item
     *            the specified rule group tree item
     * @param g
     *            the specified rule group object
     */
    private void populateGroupNode(TreeItem item, RuleGroup g) {
        item.setText(new String[] { g.getName(), "", "" }); //$NON-NLS-1$  //$NON-NLS-2$
        item.setData(g);
        item.setExpanded(true);
        List rules = g.getRules();
        for (int j = 0; j < rules.size(); j++) {
            Rule r = (Rule) rules.get(j);
            TreeItem subItem = new TreeItem(item, SWT.NONE);
            subItem.setText(new String[] { r.getName(), r.getClassifier(), r.getDescription() });
            subItem.setData(r);
            subItem.setChecked(r.isEnabled());
            // Set the right icon in the tree subitem
            if (this instanceof MetricsPreferencePage) // Cheats a bit by looking at subclass...
            {
                if (subItem.getImage() == null)
                    subItem.setImage(JUCMNavPlugin.getImage("icons/info16.gif")); //$NON-NLS-1$
            } else if (r.getWarningOnly()) {
                if (subItem.getImage() == null)
                    subItem.setImage(JUCMNavPlugin.getImage("icons/warning16.gif")); //$NON-NLS-1$
            } else {
                if (subItem.getImage() == null)
                    subItem.setImage(JUCMNavPlugin.getImage("icons/error16.gif")); //$NON-NLS-1$
            }
        }
    }

    /**
     * Close the preference page and save all settings
     */
    public boolean performOk() {
        getDefinitionManager().save();
        return true;
    }

    /**
     * Close the preference page and discard all changes of settings
     */
    public boolean performCancel() {
        getDefinitionManager().load();
        return super.performCancel();
    }

}
