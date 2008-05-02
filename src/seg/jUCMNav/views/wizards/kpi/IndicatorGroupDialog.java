package seg.jUCMNav.views.wizards.kpi;

import grl.GRLspec;
import grl.kpimodel.Indicator;
import grl.kpimodel.IndicatorGroup;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.kpi.AssignIndicatorGroupCommand;
import seg.jUCMNav.model.util.URNElementFinder;

/**
 * A dialog that assigns groups to the indicator
 * 
 * @author pchen
 */
public class IndicatorGroupDialog {
    private Shell shell = null;
    private Group group = null;
    private CLabel lblName = null;
    private CLabel lblId = null;
    private CLabel lblDescription = null;
    private CLabel cLabelGrlName = null;
    private CLabel cLabelGrlId = null;
    private CLabel cLabelGrlDescription = null;
    private Group groupAvailableIndicatorGroups = null;
    private Group groupAssignedIndicatorGroups = null;
    private Button buttonFinish = null;
    private Button buttonCancel = null;

    // The commandstack from the editor where the dialog was opened.
    private CommandStack cmdStack;

    // The IntentionalElement used to create the indicators
    private Indicator indicator; // @jve:decl-index=0:
    // The GRLspec of the current model
    private GRLspec grlSpec; // @jve:decl-index=0:

    private HashMap availableIndicatorGroups; // @jve:decl-index=0:
    private HashMap assignedIndicatorGroups; // @jve:decl-index=0:

    private Tree treeAvailableIndicatorGroups = null;
    private Tree treeAssignedIndicatorGroups = null;
    private Button buttonAssign = null;
    private Button buttonRemove = null;

    private static int shell_w = 602;
    private static int shell_h = 438;

    public IndicatorGroupDialog(CommandStack cmdStack, Indicator indicator) {
        this.cmdStack = cmdStack;
        init(indicator);
    }

    /**
     * This method initializes the Window
     * 
     */
    private void init(Indicator indicator) {
        createSShell();
        shell.setVisible(true);
        setElement(indicator);
        refresh();
    }

    /**
     * Set the Indicator associate with the instance
     * 
     * @param indicator
     */
    private void setElement(Indicator indicator) {
        this.indicator = indicator;
        cLabelGrlName.setText(indicator.getName());
        cLabelGrlDescription.setText(indicator.getDescription());
        cLabelGrlId.setText(indicator.getId());
        grlSpec = indicator.getGrlspec();

        initAvailableIndicatorGroups();
        initAssignedIndicatorGroups();
    }

    /**
     * This method initializes sShell
     */
    private void createSShell() {
        shell = new Shell(SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        shell.setImage(JUCMNavPlugin.getImage("icons/perspectiveIcon.gif")); //$NON-NLS-1$
        shell.setText(Messages.getString("IndicatorGroupDialog.shellTitle")); //$NON-NLS-1$
        shell.setSize(new Point(shell_w, shell_h));
        shell.setLocation(new Point((shell.getDisplay().getBounds().width - shell_w) / 2, (shell.getDisplay().getBounds().height - shell_h) / 2));
        createGroup();
        createGroupAvailableIndicatorGroupss();
        createGroupAssignedIndicatorGroups();
        buttonFinish = new Button(shell, SWT.NONE);
        buttonFinish.setBounds(new Rectangle(404, 377, 91, 26));
        buttonFinish.setText(Messages.getString("IndicatorGroupDialog.finish")); //$NON-NLS-1$
        buttonAssign = new Button(shell, SWT.ARROW | SWT.RIGHT);
        buttonAssign.setBounds(new Rectangle(278, 203, 40, 23));
        buttonAssign.setText(Messages.getString("IndicatorGroupDialog.assign")); //$NON-NLS-1$
        buttonRemove = new Button(shell, SWT.ARROW | SWT.LEFT);
        buttonRemove.setBounds(new Rectangle(278, 232, 40, 23));
        buttonRemove.setText(Messages.getString("IndicatorGroupDialog.remove")); //$NON-NLS-1$
        buttonCancel = new Button(shell, SWT.NONE);
        buttonCancel.setBounds(new Rectangle(499, 377, 91, 26));
        buttonCancel.setText(Messages.getString("IndicatorGroupDialog.cancel")); //$NON-NLS-1$

        buttonFinish.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                HashSet assignedIndicatorGroupsSet = new HashSet();
                for (Iterator it = assignedIndicatorGroups.keySet().iterator(); it.hasNext();) {
                    String indicatorGroupId = (String) it.next();
                    IndicatorGroup indicatorGroup = (IndicatorGroup) URNElementFinder.find(grlSpec.getUrnspec(), indicatorGroupId);

                    if (indicatorGroup != null) {
                        assignedIndicatorGroupsSet.add(indicatorGroup);
                    }
                }

                CompoundCommand cmd = new CompoundCommand();
                cmd.add(new AssignIndicatorGroupCommand(indicator, assignedIndicatorGroupsSet));

                if (cmd.canExecute()) {
                    cmdStack.execute(cmd);
                }

                shell.close();
            }
        });

        buttonCancel.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                shell.close();
            }
        });

        buttonAssign.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                assignIndicatorGroups();
            }
        });

        buttonRemove.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
            public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                removeIndicators();
            }
        });
    }

    /**
     * This method initializes group
     * 
     */
    private void createGroup() {
        group = new Group(shell, SWT.NONE);
        group.setText(Messages.getString("IndicatorGroupDialog.indicator")); //$NON-NLS-1$
        group.setBounds(new org.eclipse.swt.graphics.Rectangle(3, 2, 419, 75));
        lblName = new CLabel(group, SWT.NONE);
        lblName.setText(Messages.getString("IndicatorGroupDialog.name")); //$NON-NLS-1$
        lblName.setFont(new Font(Display.getDefault(), "Tahoma", 8, SWT.BOLD)); //$NON-NLS-1$
        lblName.setBounds(new Rectangle(8, 15, 44, 22));
        lblId = new CLabel(group, SWT.NONE);
        lblId.setText(Messages.getString("IndicatorGroupDialog.id")); //$NON-NLS-1$
        lblId.setFont(new Font(Display.getDefault(), "Tahoma", 8, SWT.BOLD)); //$NON-NLS-1$
        lblId.setBounds(new Rectangle(263, 17, 37, 19));
        lblDescription = new CLabel(group, SWT.NONE);
        lblDescription.setFont(new Font(Display.getDefault(), "Tahoma", 8, SWT.BOLD)); //$NON-NLS-1$
        lblDescription.setLocation(new org.eclipse.swt.graphics.Point(8, 41));
        lblDescription.setText(Messages.getString("IndicatorGroupDialog.description")); //$NON-NLS-1$
        lblDescription.setSize(new org.eclipse.swt.graphics.Point(80, 22));
        cLabelGrlName = new CLabel(group, SWT.SHADOW_IN);
        cLabelGrlName.setText(""); //$NON-NLS-1$
        cLabelGrlName.setLocation(new Point(56, 15));
        cLabelGrlName.setSize(new Point(199, 23));
        cLabelGrlDescription = new CLabel(group, SWT.SHADOW_IN);
        cLabelGrlDescription.setText(""); //$NON-NLS-1$
        cLabelGrlDescription.setLocation(new Point(95, 41));
        cLabelGrlDescription.setSize(new org.eclipse.swt.graphics.Point(315, 23));
        cLabelGrlId = new CLabel(group, SWT.SHADOW_IN);
        cLabelGrlId.setText(""); //$NON-NLS-1$
        cLabelGrlId.setLocation(new Point(306, 15));
        cLabelGrlId.setSize(new org.eclipse.swt.graphics.Point(103, 23));
    }

    /**
     * This method initializes groupCurrentLink
     * 
     */
    private void createGroupAvailableIndicatorGroupss() {
        groupAvailableIndicatorGroups = new Group(shell, SWT.NONE);
        groupAvailableIndicatorGroups.setText(Messages.getString("IndicatorGroupDialog.groupAvailableIndicatorGroups")); //$NON-NLS-1$
        groupAvailableIndicatorGroups.setBounds(new Rectangle(5, 86, 267, 271));
        treeAvailableIndicatorGroups = new Tree(groupAvailableIndicatorGroups, SWT.BORDER | SWT.CHECK);
        treeAvailableIndicatorGroups.setToolTipText(Messages.getString("IndicatorGroupDialog.groupAvailableIndicatorGroups.tiptext")); //$NON-NLS-1$
        treeAvailableIndicatorGroups.setBounds(new Rectangle(6, 51, 253, 212));

        TreeColumn nameCol = new TreeColumn(treeAvailableIndicatorGroups, SWT.LEFT);
        nameCol.setText(Messages.getString("IndicatorGroupDialog.Name")); //$NON-NLS-1$
        nameCol.pack();

        TreeColumn idCol = new TreeColumn(treeAvailableIndicatorGroups, SWT.LEFT);
        idCol.setText("ID"); //$NON-NLS-1$
        idCol.pack();
    }

    /**
     * This method initializes groupAddLink
     * 
     */
    private void createGroupAssignedIndicatorGroups() {
        groupAssignedIndicatorGroups = new Group(shell, SWT.NONE);
        groupAssignedIndicatorGroups.setText(Messages.getString("IndicatorGroupDialog.groupAssignedIndicatorGroups")); //$NON-NLS-1$
        groupAssignedIndicatorGroups.setBounds(new Rectangle(324, 86, 267, 271));
        treeAssignedIndicatorGroups = new Tree(groupAssignedIndicatorGroups, SWT.BORDER | SWT.CHECK);
        treeAssignedIndicatorGroups.setToolTipText(Messages.getString("IndicatorGroupDialog.groupAssignedIndicatorGroups.tiptext")); //$NON-NLS-1$
        treeAssignedIndicatorGroups.setBounds(new Rectangle(6, 22, 253, 241));

        TreeColumn nameCol = new TreeColumn(treeAssignedIndicatorGroups, SWT.LEFT);
        nameCol.setText(Messages.getString("IndicatorGroupDialog.Name")); //$NON-NLS-1$
        nameCol.pack();

        TreeColumn idCol = new TreeColumn(treeAssignedIndicatorGroups, SWT.LEFT);
        idCol.setText("ID"); //$NON-NLS-1$
        idCol.pack();
    }

    /**
     * This method used the command stack to assign selected indicators to the grl element
     */
    private void assignIndicatorGroups() {
        HashMap checkedItems = getCheckedIndicatorGroups(treeAvailableIndicatorGroups);
        moveItems(checkedItems, availableIndicatorGroups, assignedIndicatorGroups);

        refresh();
    }

    private void moveItems(HashMap itemsMoved, HashMap fromMap, HashMap toMap) {
        Set keySet = itemsMoved.keySet();
        for (Iterator keyIt = keySet.iterator(); keyIt.hasNext();) {
            String key = (String) keyIt.next();
            String value = (String) itemsMoved.get(key);

            fromMap.remove(key);
            toMap.put(key, value);
        }
    }

    private HashMap getCheckedIndicatorGroups(Tree tree) {
        HashMap checkedItems = new HashMap();

        TreeItem[] items = tree.getItems();
        for (int i = 0; i < items.length; i++) {
            if (items[i].getChecked()) {
                String groupId = items[i].getText(1);
                String groupName = items[i].getText(0);

                checkedItems.put(groupId, groupName);
            }
        }

        return checkedItems;
    }

    /**
     * This method used the command stack to remove selected indicators from the grl element
     */
    private void removeIndicators() {
        HashMap checkedItems = getCheckedIndicatorGroups(treeAssignedIndicatorGroups);
        moveItems(checkedItems, assignedIndicatorGroups, availableIndicatorGroups);

        refresh();
    }

    /**
     * Refresh all the component of the window
     * 
     */
    private void refresh() {
        refreshAvailableIndicatorsTree();
        refreshAssignedIndicatorsTree();

        treeAvailableIndicatorGroups.getColumn(0).setWidth(1000);
        treeAvailableIndicatorGroups.getColumn(1).setWidth(1);
        treeAssignedIndicatorGroups.getColumn(0).setWidth(1000);
        treeAssignedIndicatorGroups.getColumn(1).setWidth(1);
    }

    /**
     * Initialize the Available IndicatorGroups
     * 
     */
    private void initAvailableIndicatorGroups() {
        // Clean the hashmap
        availableIndicatorGroups = new HashMap();

        // Set available indicator groups
        for (Iterator it = grlSpec.getIndicatorGroup().iterator(); it.hasNext();) {
            IndicatorGroup indGroup = (IndicatorGroup) it.next();

            if (!indicator.getGroups().contains(indGroup)) {
                availableIndicatorGroups.put(indGroup.getId(), indGroup.getName());
            }
        }
    }

    /**
     * Refresh the Available Indicators Tree
     * 
     */
    private void refreshAvailableIndicatorsTree() {
        // Clean the tree
        treeAvailableIndicatorGroups.removeAll();

        // Set available IndicatorGroups
        for (Iterator it = availableIndicatorGroups.keySet().iterator(); it.hasNext();) {
            String indGroupId = (String) it.next();
            String indGroupName = (String) availableIndicatorGroups.get(indGroupId);

            TreeItem groupItem = new TreeItem(treeAvailableIndicatorGroups, SWT.NONE);
            groupItem.setText(new String[] { indGroupName, indGroupId });

            groupItem.setExpanded(true);
        }
    }

    /**
     * Initialize the Assigned IndicatorGroups
     * 
     */
    private void initAssignedIndicatorGroups() {
        // Clean the hashmap
        assignedIndicatorGroups = new HashMap();

        // Set assigned indicator groups associate with the grl element
        for (Iterator it = indicator.getGroups().iterator(); it.hasNext();) {
            IndicatorGroup indGrp = (IndicatorGroup) it.next();
            assignedIndicatorGroups.put(indGrp.getId(), indGrp.getName());
        }
    }

    /**
     * Refresh the Assigned Indicators Tree
     * 
     */
    private void refreshAssignedIndicatorsTree() {
        // Clean the tree
        treeAssignedIndicatorGroups.removeAll();

        // Set Assigned IndicatorGroups
        for (Iterator it = assignedIndicatorGroups.keySet().iterator(); it.hasNext();) {
            String indGroupId = (String) it.next();
            String indGroupName = (String) assignedIndicatorGroups.get(indGroupId);

            TreeItem groupItem = new TreeItem(treeAssignedIndicatorGroups, SWT.NONE);
            groupItem.setText(new String[] { indGroupName, indGroupId });

            groupItem.setExpanded(true);
        }
    }

}
