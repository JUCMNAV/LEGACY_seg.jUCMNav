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

public class SubmenuAction extends Action implements SelectionListener
{
	private SelectionListener actionInstance;
	private IAction[] actions;
	private boolean hideDisabled;
	
	public SubmenuAction(IAction[] subactions, String text, String toolTip, ImageDescriptor descriptor, boolean hideDisabledActions)
	{
		super("", IAction.AS_DROP_DOWN_MENU);

		this.actionInstance = this;
		this.actions = subactions;
		this.hideDisabled = hideDisabledActions;

		setText(text);
		setToolTipText(toolTip);
		setImageDescriptor(descriptor);
		
		setMenuCreator(new IMenuCreator()
		{
			public Menu getMenu(Control parent)
			{
				return null;
			}

			public Menu getMenu(Menu parent)
			{
				Menu menu = new Menu(parent);
				for (int i = 0; i < actions.length; i++)
				{
					if (actions[i]==null || !actions[i].isEnabled() && hideDisabled)
						continue;
					MenuItem item = new MenuItem(menu, SWT.NONE);
					item.setData(new Integer(i));
					item.setText(actions[i].getText());
					if (actions[i].getImageDescriptor()!=null)
						item.setImage(actions[i].getImageDescriptor().createImage());
					item.addSelectionListener(actionInstance);
				}
				return menu;
			}

			public void dispose()
			{
			}
		});

	}

	public int getActiveOperationCount()
	{
		int operationCount=0;
		for (int i=0;i<actions.length;i++)
			operationCount += actions[i]!=null && actions[i].isEnabled() ? 1 : 0; 

		return operationCount;
	}

	/**
	 * Runs the default action
	 */
	public void run()
	{
		actions[0].run();
	}

	/**
	 * Runs the default action
	 */
	public void widgetDefaultSelected(SelectionEvent e)
	{
		actions[0].run();
	}

	/**
	 * Called when an item in the drop-down menu is selected. Runs the
	 * associated run() method
	 */
	public void widgetSelected(SelectionEvent e)
	{
		actions[((Integer) (((MenuItem) (e.getSource())).getData())).intValue()].run();
	}
}