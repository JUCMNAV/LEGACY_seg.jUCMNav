package seg.jUCMNav.model.commands.cutcopypaste;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.util.Clipboard;
import urn.URNspec;
import urncore.URNmodelElement;

public class CopyCommand extends Command
{
	private List selection;
	private URNspec urn;

	public CopyCommand(URNspec urn, List selection)
	{
		this.selection = selection;
		this.urn = urn;

	}

	public void redo()
	{
		Clipboard c = Clipboard.getInstance();
		
		Vector selectedModelElements = new Vector();

		for (Iterator iterator = selection.iterator(); iterator.hasNext();)
		{
			Object object = (Object) iterator.next();
			if (object instanceof EditPart)
			{
				EditPart ep = (EditPart) object;
				if (ep.getModel() instanceof URNmodelElement) {
					
				URNmodelElement element = (URNmodelElement) ep.getModel();
				selectedModelElements.add(element.getId());
				}
			}
		}

		// deep copy of the model plus the selected ids. 
		c.setContents(new Object[] { EcoreUtil.copy(urn), selectedModelElements, selection});
	}

	public void execute()
	{
		redo();
	}

	public void undo()
	{
	}

}
