/*
 * Created on 17-May-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.editparts.treeEditparts;

import org.eclipse.gef.EditPolicy;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.PathNodeComponentEditPolicy;
import seg.jUCMNav.editpolicies.feedback.PathNodeNonResizableEditPolicy;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.StartPoint;

/**
 * @author TremblaE
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PathNodeTreeEditPart extends UcmModelElementTreeEditPart {
	
	private PathGraph grah;

	/**
	 * @param model
	 */
	public PathNodeTreeEditPart(Object model, PathGraph graph) {
		super(model);
		this.grah = graph;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.EditPart#installEditPolicy(java.lang.Object, org.eclipse.gef.EditPolicy)
	 */
	public void installEditPolicy(Object key, EditPolicy editPolicy) {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new PathNodeComponentEditPolicy());
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, new PathNodeNonResizableEditPolicy());
	}
	
//	protected List getModelChildren() {
//		ArrayList list = new ArrayList();
//		if(getPathNode().getLabel() != null)
//			list.add(getPathNode().getLabel());
//		return list;
//	}
	
	protected PathNode getPathNode(){
		return (PathNode)getModel();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.editparts.AbstractTreeEditPart#getImage()
	 */
	protected Image getImage() {
		PathNode node = (PathNode)getModel();
		if(node instanceof StartPoint)
			return (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Start16.gif")).createImage();
		else if(node instanceof EmptyPoint)
			return (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Node16.gif")).createImage();
		else if(node instanceof EndPoint)
			return (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/End16.gif")).createImage();
		else if(node instanceof RespRef)
			return (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Resp16.gif")).createImage();
		else
			return (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Node16.gif")).createImage();
	}
}
