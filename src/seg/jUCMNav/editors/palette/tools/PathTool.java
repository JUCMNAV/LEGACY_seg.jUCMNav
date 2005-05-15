package seg.jUCMNav.editors.palette.tools;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.tools.CreationTool;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;

import seg.jUCMNav.model.ModelCreationFactory;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
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
	private final int CONNECTION = 4;

	private int state = NOSELECT;

	private URNspec urn;

	private EditPart target;
	
	private Request targetRequest;
	
	private EditPart selected;

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
	
	protected Request createTargetRequest() {
		return super.createTargetRequest();
	}
	
	protected void setTargetRequest(Request req) {
		targetRequest = req;
		super.setTargetRequest(req);
	}

	protected void setTargetEditPart(EditPart editpart) {
		if(target != editpart){
			target = editpart;
			setState();
			if(target != null)
				setTargetRequest(createTargetRequest());
		}
		super.setTargetEditPart(editpart);
	}
	
	protected boolean handleMove() {
		// Change the target under the mouse before updating the target request...
		updateTargetUnderMouse();
		updateTargetRequest();
		setCurrentCommand(getCommand());
		showTargetFeedback();
		return true;
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
		ModelCreationFactory factory;
		switch (state) {
		case ENDPOINT:
			factory = new ModelCreationFactory(getURNspec(), EndPoint.class);
			break;
		case STARTPOINT:
			factory = new ModelCreationFactory(getURNspec(), StartPoint.class);
			break;
		case NOSELECT:
			factory = new ModelCreationFactory(getURNspec(), StartPoint.class);
			break;
		case CONNECTION:
			factory = new ModelCreationFactory(getURNspec(), EmptyPoint.class);
			break;
		default:
			factory = new ModelCreationFactory(getURNspec(), StartPoint.class);
		}
		
		return factory;
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
			selected = (EditPart) (list.get(0));
			
			setState();
			
			// Regenerate the request depending on the state
			createTargetRequest();
		}
	}
	
	protected void setState(){
		if(target != null) {
			if(target.getModel() instanceof NodeConnection){
				state = CONNECTION;
				return;
			}
		}

		if (selected.getModel() instanceof EndPoint) {
			state = ENDPOINT;
		} else if (selected.getModel() instanceof StartPoint) {
			state = STARTPOINT;
		} else {
			state = NOSELECT;
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
		} else if(model instanceof EmptyPoint){
			selectModelElement(findEndPoint((PathNode)model));
		}else
			selectModelElement(model);
	}
	
	private PathNode findEndPoint(PathNode start){
		PathNode node = null;
		PathNode end = null;
		end = start;
		if(end instanceof EndPoint)
			return end;
		
		while(!(end instanceof EndPoint)){
			end = (PathNode) ((NodeConnection) end.getSucc().get(0)).getTarget();
		}
		
		return end;
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