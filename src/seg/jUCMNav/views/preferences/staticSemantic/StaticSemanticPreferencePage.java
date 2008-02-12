/**
 * 
 */
package seg.jUCMNav.views.preferences.staticSemantic;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
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
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import seg.jUCMNav.staticSemantic.Rule;
import seg.jUCMNav.staticSemantic.RuleGroup;
import seg.jUCMNav.staticSemantic.StaticSemanticDefMgr;

/**
 * @author Byrne
 * 
 */
public class StaticSemanticPreferencePage extends PreferencePage implements IWorkbenchPreferencePage, SelectionListener {
    private static final String APPDATA_UTILITIES_NUMBER = "UtilitiesNumber";
    private static final String APPDATA_CHECKBOX = "CheckBox";
    private static final String APPDATA_UTILITY = "Utility";
    private static final String BUTTON_EDIT_THE_SELECTED_RULE = "Edit";
    private static final String BUTTON_DEFINE_A_NEW_RULE = "New Rule";
    private static final String BUTTON_DEFINE_A_NEW_GROUP = "New Group";
    private static final String BUTTON_DELETE = "Delete";
    private static final String BUTTON_EXPORT = "Export";
    private static final String BUTTON_IMPORT = "Import";

    private static final int BTN_COLUMN_CHECK = 0;
    private static final int TBL_COLUMN_NAME = 1;
    private static final int TBL_COLUMN_DESCRIPTION = 5;
    private static final int TBL_COLUMN_CONTEXT = 3;
    private static final int TBL_COLUMN_CLASSIFIER = 2;
    private static final int TBL_COLUMN_CONSTRAINT = 4;
    private static final int TBL_COLUMN_UTILITY = 6;

    private Tree tree;
    private Button btnNewRule;
    private Button btnNewGroup;
    private Button btnEdit;

    private Button btnExport;
    private Button btnImport;

    private Button btnShowDescription;
    private Button btnDelete;

    /*
     * (non-Javadoc)
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
        btnShowDescription.setText("Show rule description in the rule violation reporting");

        Label label1 = new Label(parent, SWT.LEFT);
        label1.setText("Rules defined:");

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
                }
            }
        });

        String[] titles = { "Name", "Context", "Query Expression", "Constraint Expression", "Descreption", "Utilities" };
        int[] widths = { 150, 50, 50, 50, 50, 50 };
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
        btnNewRule.addSelectionListener(this);

        btnNewRule = new Button(c, SWT.PUSH);
        btnNewRule.setText(BUTTON_DEFINE_A_NEW_GROUP);
        btnNewRule.addSelectionListener(this);

        btnEdit = new Button(c, SWT.PUSH);
        btnEdit.setText(BUTTON_EDIT_THE_SELECTED_RULE);
        btnEdit.addSelectionListener(this);

        btnDelete = new Button(c, SWT.PUSH);
        btnDelete.setText(BUTTON_DELETE);
        btnDelete.addSelectionListener(this);

        btnImport = new Button(c, SWT.PUSH);
        btnImport.setText(BUTTON_IMPORT);
        btnImport.addSelectionListener(this);

        btnExport = new Button(c, SWT.PUSH);
        btnExport.setText(BUTTON_EXPORT);
        btnExport.addSelectionListener(this);

        initializeValues();
        return null;
    }

    // check all sub rule items of the group item
    protected void checkRules(TreeItem item, boolean checked) {
        TreeItem[] ruleItems = item.getItems();
        for (int i = 0; i < ruleItems.length; ++i) {
            Rule r = (Rule) ruleItems[i].getData();
            r.setEnabled(checked);
            ruleItems[i].setChecked(checked);
            ruleItems[i].setGrayed(false);
        }

    }

    // check all groups based their rules
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

    // check all rule items that point to the same rule
    // no change to any group items
    protected void checkSameRules(TreeItem item, Rule[] rules, boolean checked) {
        TreeItem[] groupItems = tree.getItems();
        for (int i = 0; i < groupItems.length; ++i) {
            TreeItem[] ruleItems = groupItems[i].getItems();
            for (int j = 0; j < ruleItems.length; ++j) {
                if (ruleItems[j] != item) {
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

    }

    private void initializeValues() {
        StaticSemanticDefMgr.instance().load();
        btnShowDescription.setSelection(StaticSemanticDefMgr.instance().isShowDesc());
        populateTree();
        tree.pack();
    }

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
     * (non-Javadoc)
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

    private void performDelete() {
        TreeItem[] items = tree.getSelection();
        for (int i = 0; i < items.length; ++i) {
            Object data = items[i].getData();
            if (data instanceof RuleGroup) {
                // Only empty group can be deleted
                // The group ALl can not be deleted
                RuleGroup g = (RuleGroup) data;
                if (g.getRules().size() == 0 && g.getName().compareTo("All") != 0) {
                    StaticSemanticDefMgr.instance().removeGroup(g);
                }
            } else if (data instanceof Rule) {
                Rule r = (Rule) data;
                StaticSemanticDefMgr.instance().removeRule(r);
            }
        }
        updateTree();
    }

    private void performNewGroup() {
        GroupEditDialog dlg = new GroupEditDialog(getControl().getShell());
        if (Window.OK == dlg.open()) {
            StaticSemanticDefMgr.instance().addGroup(dlg.getGroup());
            updateTree();
        }
    }

    private void performNewRule() {
        RuleEditDialog dlg = new RuleEditDialog(getControl().getShell());

        if (Window.OK == dlg.open()) {
            StaticSemanticDefMgr.instance().addRule(dlg.getRule());
            updateTree();
        }
    }

    private void performExport() {

        TreeItem[] items = tree.getSelection();
        if (items.length > 0) {
            FileDialog dlg = new FileDialog(getControl().getShell(), SWT.SAVE);
            String file = dlg.open();
            if (file != null) {
                List rules = new ArrayList();

                for (int i = 0; i < items.length; ++i) {
                    Object data = items[i].getData();
                    if (data instanceof Rule) {
                        rules.add(data);
                    }
                }
                StaticSemanticDefMgr.exportRules(rules, file);
            }
        }

    }

    private void performImport() {

        FileDialog dlg = new FileDialog(getControl().getShell(), SWT.OPEN);
        String file = dlg.open();
        if (file != null) {
            // save modification before import new rules
            performOk();

            try {
                StaticSemanticDefMgr.instance().importRules(file, getControl().getShell());
                updateTree();
            } catch (FileNotFoundException e) {
                MessageBox msg = new MessageBox(getControl().getShell(), SWT.ICON_ERROR);
                msg.setMessage(e.getMessage());
                msg.setText("Failure to import rules");
                msg.open();
            }
        }
    }

    /*
     * private void performDelete() {
     * 
     * int nDelete = table.getSelectionCount(); if(nDelete>0) { MessageBox msg = new MessageBox(getControl().getShell(), SWT.YES|SWT.NO); msg.setMessage("Are
     * you sure to delete the selected "+nDelete+" rules?"); msg.setText("Rule delete confirmation"); if(SWT.NO == msg.open()) return ; } deleteRule(
     * table.getSelectionIndices()); }
     * 
     *//**
         * @param idx
         */
    /*
     * private void deleteRule(int idx) { TableEditor editor = (TableEditor) table.getItem(idx).getData(APPDATA_CHECKBOX); table.remove(idx);
     * editor.getEditor().dispose(); editor.dispose(); table.setSelection(0); }
     */
    /*
     * private void deleteRule(int[] indices) { for(int i=0;i<indices.length;++i) { TableEditor editor = (TableEditor)
     * table.getItem(indices[i]).getData(APPDATA_CHECKBOX); editor.getEditor().dispose(); editor.dispose(); } table.remove(indices); }
     */

    private void performEdit() {
        // Check if only one item selected
        if (tree.getSelectionCount() != 1) {
            MessageBox msg = new MessageBox(getControl().getShell(), SWT.ICON_WARNING);
            msg.setMessage("Please select one and only one rule to edit");
            msg.setText("Rule selection");
            msg.open();
            return;
        }
        TreeItem item = tree.getSelection()[0];

        Object o = item.getData();
        if (o instanceof Rule) {
            editRule((Rule) o);
        } else if (o instanceof RuleGroup) {
            editRuleGroup((RuleGroup) o);
            updateGroupNode(item);
            checkGroups();
        }

    }

    private void updateGroupNode(TreeItem item) {
        RuleGroup group = (RuleGroup) item.getData();

        // Save check information before remove items
        HashMap checkInfo = saveCheckInfo(item);

        item.removeAll();
        populateGroupNode(item, group);
        // Restore check states
        restoreCheckState(item, checkInfo);

    }

    private void restoreCheckState(TreeItem item, HashMap checkInfo) {
        TreeItem[] items = item.getItems();
        for (int i = 0; i < items.length; ++i) {
            Boolean checked = (Boolean) checkInfo.get(items[i].getData());
            if (checked != null) {
                items[i].setChecked(checked.booleanValue());
            }
        }

    }

    private HashMap saveCheckInfo(TreeItem item) {
        HashMap info = new HashMap();
        TreeItem[] items = item.getItems();
        for (int i = 0; i < items.length; ++i) {
            info.put(items[i].getData(), new Boolean(items[i].getChecked()));
        }
        return info;
    }

    /**
     * @param r
     */
    private void editRule(Rule r) {
        RuleEditDialog dlg = new RuleEditDialog(getControl().getShell());

        dlg.setRule(r);

        dlg.open();
    }

    private void updateTree() {
        tree.removeAll();
        populateTree();

    }

    private void editRuleGroup(RuleGroup g) {
        if (g.getName().compareTo("All") == 0) {
            MessageBox msg = new MessageBox(getControl().getShell(), SWT.ICON_WARNING);
            msg.setMessage("The special group [All] can not be edited.");
            msg.setText("Warning");
            msg.open();
            return;
        }
        GroupEditDialog dlg = new GroupEditDialog(getControl().getShell());
        dlg.setGroup(g);
        dlg.open();

    }

    /*
     * private void performNew() {
     * 
     * RuleEditDialog dlg = new RuleEditDialog(getControl().getShell()); dlg.setTitle("Define a new rule"); if(Window.OK==dlg.open()) { Rule r = new
     * Rule(dlg.getName(),dlg.getContext(),dlg.getQuery(),dlg.getConstraint(),true,dlg.getDescription()); for(int i=0;i<dlg.getUtilities().size();++i) { String
     * s = (String) dlg.getUtilities().get(i); r.addUtility(s); } appendRule(r); table.setSelection(table.getItemCount()-1); } }
     */
    /**
     * 
     * 
     */
    /*
     * private void appendRule(Rule r) { TableItem item = new TableItem (table, SWT.NONE);
     * 
     * TableEditor editor = new TableEditor (table); Button btnSelect = new Button(table,SWT.CHECK); editor.grabHorizontal = true;
     * btnSelect.setSelection(r.isEnabled()); editor.setEditor(btnSelect, item, BTN_COLUMN_CHECK);
     * 
     * item.setData(APPDATA_CHECKBOX,editor); item.setData(APPDATA_UTILITIES_NUMBER,r.getUtilities().size()); for(int i=0;i<r.getUtilities().size();++i) {
     * item.setData(APPDATA_UTILITY+i,r.getUtilities().get(i)); } updateRule(item, r); }
     * 
     * private void updateRule(TableItem item, Rule r) { TableEditor editor = (TableEditor) item.getData(APPDATA_CHECKBOX); Button chk = (Button)
     * editor.getEditor();
     * 
     * chk.setSelection(r.isEnabled()); item.setText(TBL_COLUMN_NAME, r.getName()); item.setText(TBL_COLUMN_DESCRIPTION, r.getDescription());
     * item.setText(TBL_COLUMN_CLASSIFIER, r.getClassifier()); item.setText(TBL_COLUMN_CONTEXT, r.getContext()); item.setText(TBL_COLUMN_CONSTRAINT,
     * r.getQuery()); item.setText(TBL_COLUMN_UTILITY, String.valueOf(r.getUtilities().size()));
     * 
     * 
     * item.setData(APPDATA_UTILITIES_NUMBER, new Integer( r.getUtilities().size()));
     * 
     * int nNumber = Integer.parseInt(item.getData(APPDATA_UTILITIES_NUMBER).toString());
     * 
     * 
     * for(int i =0;i<r.getUtilities().size();++i) { String u = (String) r.getUtilities().get(i); item.setData( APPDATA_UTILITY + i, u); } }
     */
    protected void performDefaults() {
        super.performDefaults();

        StaticSemanticDefMgr.instance().loadDefault();

        btnShowDescription.setSelection(StaticSemanticDefMgr.instance().isShowDesc());
        updateTree();

    }

    private void populateTree() {
        List groups = StaticSemanticDefMgr.instance().getGroups();
        for (int i = 0; i < groups.size(); ++i) {
            RuleGroup g = (RuleGroup) groups.get(i);
            TreeItem item = new TreeItem(tree, SWT.NONE);
            populateGroupNode(item, g);
        }
        checkGroups();
    }

    /**
     * @param item
     * @param g
     */
    private void populateGroupNode(TreeItem item, RuleGroup g) {
        item.setText(new String[] { g.getName(), "", "", "", "" });
        item.setData(g);
        item.setExpanded(true);
        List rules = g.getRules();
        for (int j = 0; j < rules.size(); j++) {
            Rule r = (Rule) rules.get(j);
            TreeItem subItem = new TreeItem(item, SWT.NONE);
            subItem.setText(new String[] { r.getName(), r.getClassifier(), r.getContext(), r.getQuery(), r.getDescription() });
            subItem.setData(r);
            subItem.setChecked(r.isEnabled());
        }
    }

    public boolean performOk() {

        StaticSemanticDefMgr.instance().save();
        return true;
    }

    /*
     * private Rule[] retriveRules() { Rule[] rules = new Rule[table.getItemCount()]; for(int i=0;i<table.getItemCount();++i) { rules[i] =
     * retriveOneRule(table.getItem(i)); } return rules; }
     */
    /**
     * @param item
     */
    private Rule retriveOneRule(TableItem item) {

        TableEditor editor = (TableEditor) item.getData(APPDATA_CHECKBOX);
        Button chk = (Button) editor.getEditor();
        Rule r = StaticSemanticDefMgr.instance().createRule(item.getText(TBL_COLUMN_NAME));
        r.setContext(item.getText(TBL_COLUMN_CONTEXT));
        r.setClassifier(item.getText(TBL_COLUMN_CLASSIFIER));
        r.setDescription(item.getText(TBL_COLUMN_DESCRIPTION));
        r.setQuery(item.getText(TBL_COLUMN_CONSTRAINT));
        r.setEnabled(chk.getSelection());

        int nNumber = Integer.parseInt(item.getData(APPDATA_UTILITIES_NUMBER).toString());

        for (int j = 0; j < nNumber; ++j) {
            r.addUtility(item.getData(APPDATA_UTILITY + j).toString());
        }
        return r;
    }

    public boolean performCancel() {
        StaticSemanticDefMgr.instance().load();
        return super.performCancel();
    }

}
