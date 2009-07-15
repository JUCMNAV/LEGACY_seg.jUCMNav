package seg.jUCMNav.model.commands;

import org.eclipse.gef.commands.Command;

public class NoOpCommand extends Command
{

	public NoOpCommand()
	{
		
	}
	
	public boolean canExecute()
	{
		return true;
	}
	
	public boolean canUndo()
	{
		return true;
	}
	
}
