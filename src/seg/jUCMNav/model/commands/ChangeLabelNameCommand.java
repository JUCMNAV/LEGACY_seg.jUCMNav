/*
 * Created on Apr 1, 2005
 */
package seg.jUCMNav.model.commands;

import org.eclipse.gef.commands.Command;

import ucm.map.PathNode;

/**
 * @author Jordan
 */
public class ChangeLabelNameCommand extends Command {
    private PathNode node;
	private String name, oldName;

	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute()
	{
	    node.setName(name);
	}

	/**
	 * @return whether we can apply changes
	 */
	public boolean canExecute()
	{
		if (name != null)
		{
			return true;
		}
		else
		{
			name = oldName;
			return false;
		}
	}

	/**
	 * Sets the new Column name
	 * 
	 * @param string
	 *            the new name
	 */
	public void setName(String string)
	{
		this.name = string;
	}

	/**
	 * Sets the old Column name
	 * 
	 * @param string
	 *            the old name
	 */
	public void setOldName(String string)
	{
		oldName = string;
	}

	/**
	 * @param table
	 *            The table to set.
	 */
	public void setPathNode(PathNode node)
	{
		this.node = node;
	}

	/**
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo()
	{
		node.setName(oldName);
	}
}
