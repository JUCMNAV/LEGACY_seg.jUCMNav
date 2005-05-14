package seg.jUCMNav.editors.palette.tools;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.tools.CreationTool;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;

import seg.jUCMNav.model.ModelCreationFactory;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.Map;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import urn.URNspec;

/**
 * Created 2005-03-28
 * 
 * @author Etienne Tremblay
 */
public class PathTool extends CreationTool implements ISelectionChangedListener {

	private final int ENDPOINT = 0;
	private final int STARTPOINT = 1;
	private final int NOSELECT = 2;
	private final int EMPTYPOINT = 3;

	private int state = NOSELECT;

	private URNspec urn;

	private EditPart target;

	/**
	 *  
	 */
	private PathTool() {
		super();
	}

	/**
	 * @param aFactory
	 */
	public PathTool(URNspec urn) {
		super();
		this.urn = urn;
	}

	protected void setTargetEditPart(EditPart editpart) {
		target = editpart;
		super.setTargetEditPart(editpart);
	}

	public void setViewer(EditPartViewer viewer) {
		super.setViewer(viewer);

		EditPartViewer oldViewer = getCurrentViewer();
		if (oldViewer != null)
			oldViewer.removeSelectionChangedListener(this);
		if (viewer != null) {
			viewer.addSelectionChangedListener(this);
			setSelectionState((IStructuredSelection) viewer.getSelection());
		}
	}

	protected CreationFactory getFactory() {
		switch (state) {
		case ENDPOINT:
			return new ModelCreationFactory(getURNspec(), EndPoint.class);
		case STARTPOINT:
			return new ModelCreationFactory(getURNspec(), StartPoint.class);
		case NOSELECT:
			return new ModelCreationFactory(getURNspec(), StartPoint.class);
		default:
			return new ModelCreationFactory(getURNspec(), EmptyPoint.class);
		}
	}

	protected URNspec getURNspec() {
		return urn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		IStructuredSelection selecteds = (IStructuredSelection) event.getSelection();
		setSelectionState(selecteds);
	}

	/**
	 * @param selected
	 */
	private void setSelectionState(IStructuredSelection selecteds) {
		List list = selecteds.toList();

		if (list.size() == 1) {
			EditPart selected = (EditPart) (list.get(0));

			if (selected.getModel() instanceof EndPoint) {
				state = ENDPOINT;
			} else if (selected.getModel() instanceof StartPoint) {
				state = STARTPOINT;
			} else if (selected.getModel() instanceof PathGraph || selected.getModel() instanceof Map) {
				state = NOSELECT;
			}
		}
	}

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
		if (model instanceof StartPoint) {
			StartPoint start = (StartPoint) model;
			if (state == NOSELECT) {
				PathNode node = (PathNode) ((NodeConnection) start.getSucc().get(0)).getTarget();
				PathNode end = (PathNode) ((NodeConnection) node.getSucc().get(0)).getTarget();
				if (end instanceof EndPoint)
					selectModelElement(end);
			} else
				selectModelElement(start);
		} else
			selectModelElement(model);
	}

	protected void selectModelElement(Object model) {
		EditPartViewer viewer = getCurrentViewer();
		Object editpart = viewer.getEditPartRegistry().get(model);
		if (editpart instanceof EditPart) {
			viewer.flush();
			viewer.select((EditPart) editpart);
		}
	}
}