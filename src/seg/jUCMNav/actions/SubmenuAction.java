package seg.jUCMNav.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import seg.jUCMNav.JUCMNavPlugin;

/**
 * This action contains other actions and helps create another level of contextual menus.
 * 
 * @author jkealey
 * 
 */
public class SubmenuAction extends Action implements SelectionListener {
    // / Who to inform when this action is fired (meaning display the submenu)
    private SelectionListener actionInstance;

    // the list of actions that are contained within this action
    private IAction[] actions;

    // should we hide the disabled ones (if not, they will appear as grayed out)
    private boolean hideDisabled;

    /***
     * Create a submenu.
     * 
     * @param subactions
     *            the actions that are contained within
     * @param text
     *            the container's textual label
     * @param toolTip
     *            the container's tooltip
     * @param descriptor
     *            the container's image descriptor
     * @param hideDisabledActions
     *            should we hide the disabled ones (if not, they will appear as grayed out)
     */
    public SubmenuAction(IAction[] subactions, String text, String toolTip, ImageDescriptor descriptor, boolean hideDisabledActions) {
        // indicate that this is a secondary fly-out menu.
        super("", IAction.AS_DROP_DOWN_MENU); //$NON-NLS-1$
    
        this.actionInstance = this;
        this.actions = subactions;
        this.hideDisabled = hideDisabledActions;

        setText(text);
        setToolTipText(toolTip);
        setImageDescriptor(descriptor);

        // the secondary menu logic
        setMenuCreator(new IMenuCreator() {
            private Menu fCreatedMenu;

            public Menu getMenu(Control parent) {
                // this would be used outside of a menu. not useful for us.
                return null;
            }

            public Menu getCreatedMenu() {
                return fCreatedMenu;
            }

            public void setCreatedMenu(Menu createdMenu) {
                fCreatedMenu = createdMenu;
            }

            public Menu getMenu(Menu parent) {
                if (getCreatedMenu() != null) {
                    getCreatedMenu().setEnabled(false);
                    getCreatedMenu().dispose();
                }

                // create a submenu
                Menu menu = new Menu(parent);
                // fill it with our actions
                for (int i = 0; i < actions.length; i++) {
                    // skip the disabled ones if necessary (or null actions)
                    if (actions[i] == null || !actions[i].isEnabled() && hideDisabled)
                        continue;

                    // create the submenu item
                    MenuItem item = new MenuItem(menu, SWT.NONE);

                    // memorize the index
                    item.setData(new Integer(i));

                    // identify it
                    item.setText(actions[i].getText());

                    // create its image
                    if (actions[i].getImageDescriptor() != null)
                        item.setImage(JUCMNavPlugin.getImage(actions[i].getImageDescriptor()));

                    // inform us when something is selected.
                    item.addSelectionListener(actionInstance);

                }
                setCreatedMenu(menu);

                return menu;
            }

            public void dispose() {
                if (getCreatedMenu() != null) {
                    getCreatedMenu().setEnabled(false);
                    getCreatedMenu().dispose();
                }
            }
        });

    }

    /**
     * Returns how many items are enabled in the flyout. Useful to hide the submenu when none are enabled.
     * 
     * @return the number of currently enabled menu items.
     */
    public int getActiveOperationCount() {
        int operationCount = 0;
        for (int i = 0; i < actions.length; i++)
            operationCount += actions[i] != null && actions[i].isEnabled() ? 1 : 0;

        return operationCount;
    }

    /**
     * Runs the default action
     */
    public void run() {
        actions[0].run();
    }

    /**
     * Runs the default action
     */
    public void widgetDefaultSelected(SelectionEvent e) {
        actions[0].run();
    }

    /**
     * Called when an item in the drop-down menu is selected. Runs the associated run() method
     */
    public void widgetSelected(SelectionEvent e) {
        // get the index from the data and run that action.
        actions[((Integer) (((MenuItem) (e.getSource())).getData())).intValue()].run();
    }

    public IAction find(String id) {
        if (actions == null)
            return null;
        for (int i = 0; i < actions.length; i++) {
            if (actions[i] != null && actions[i].getId().equals(id))
                return actions[i];
        }
        return null;
    }
}