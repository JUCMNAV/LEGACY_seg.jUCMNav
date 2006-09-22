package seg.jUCMNav.editparts.strategyTreeEditparts;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Widget;

import seg.jUCMNav.JUCMNavPlugin;
import ucm.map.EndPoint;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.WaitingPlace;
import ucm.scenario.ScenarioEndPoint;
import ucm.scenario.ScenarioStartPoint;

/**
 * TreeEditPart for PathNodes
 * 
 * @author TremblaE
 * 
 */
public class ScenarioPathNodeTreeEditPart extends StrategyUrnModelElementTreeEditPart {

	/**
	 * @param model
	 *            the pathnode
	 */
	public ScenarioPathNodeTreeEditPart(ScenarioStartPoint model) {
		super(model);
	}

	/**
	 * @param model
	 *            the pathnode
	 */
	public ScenarioPathNodeTreeEditPart(ScenarioEndPoint model) {
		super(model);
	}

	/**
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
		// installEditPolicy(EditPolicy.COMPONENT_ROLE, new
		// PathNodeComponentEditPolicy());
	}

	/**
	 * When setting widgets, uses a lighter colour for inherited variables.
	 * 
	 * @see org.eclipse.gef.editparts.AbstractTreeEditPart#setWidget(org.eclipse.swt.widgets.Widget)
	 */
	public void setWidget(Widget widget) {
		super.setWidget(widget);
		// TODO
		// if (widget instanceof TreeItem) {
		// if (getModel() instanceof EmptyPoint || getModel() instanceof
		// DirectionArrow)
		// ((TreeItem) widget).setForeground(new Color(null, 150, 150, 150));
		// }

	}

	/**
	 * Returns an image representing the PathNode.
	 */
	protected Image getImage() {

		PathNode node = getPathNode();

		if (super.getImage() == null) {
			if (node instanceof StartPoint || node instanceof WaitingPlace)
				setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Start16.gif")).createImage()); //$NON-NLS-1$
			else if (node instanceof EndPoint)
				setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/End16.gif")).createImage()); //$NON-NLS-1$
		}

		return super.getImage();
	}

	private PathNode getPathNode() {
		PathNode node = null;
		if (getModel() instanceof ScenarioStartPoint)
			node = ((ScenarioStartPoint) getModel()).getStartPoint();
		else if (getModel() instanceof ScenarioEndPoint)
			node = ((ScenarioEndPoint) getModel()).getEndPoint();
		return node;
	}
}