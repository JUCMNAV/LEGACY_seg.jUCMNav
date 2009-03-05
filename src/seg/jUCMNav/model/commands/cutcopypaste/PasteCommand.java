package seg.jUCMNav.model.commands.cutcopypaste;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;

public class PasteCommand extends Command
{

	public PasteCommand(EObject target)
	{
	
	}
	public void redo()
	{
	}
	
	public void execute()
	{
		redo();
	}
	
	
	public void undo()
	{
	}
	
}
