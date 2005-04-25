package seg.jUCMNav.editors.palette.tools;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.tools.CreationTool;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;

import ucm.map.EndPoint;
import ucm.map.PathGraph;
import ucm.map.StartPoint;

/**
 * Created 2005-03-28
 * 
 * @author Etienne Tremblay
 */
public class PathTool extends CreationTool implements ISelectionListener {
	
	private final int ENDPOINT = 0;
	private final int STARTPOINT = 1;
	private final int NOSELECT = 2;
	
	private int state = NOSELECT;
	
	protected void performCreation(int button) {
		executeCurrentCommand();
		selectAddedObject();
	}
	
	/*
	 * Add the newly created object to the viewer's selected objects.
	 */
	private void selectAddedObject() {
		final Object model = getCreateRequest().getNewObject();
		if (model == null)
			return;
		EditPartViewer viewer = getCurrentViewer();
		Object editpart = viewer.getEditPartRegistry().get(model);
		if (editpart instanceof EditPart) {
			viewer.flush();
			viewer.select((EditPart)editpart);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		List selecteds =((IStructuredSelection)selection).toList();
		if(selecteds.size() == 1){
			EditPart selected = (EditPart)(selecteds.get(0));
			if(selected.getModel() instanceof EndPoint){
				state = ENDPOINT;
			}
			else if(selected.getModel() instanceof StartPoint){
				state = STARTPOINT;
			}
			else if(selected.getModel() instanceof PathGraph) {
				state = NOSELECT;
			}
		}
	}
}
