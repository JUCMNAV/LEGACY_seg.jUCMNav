/*
 * Created on 17-May-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.editparts.treeEditparts;

import org.eclipse.gef.EditPolicy;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.PathNodeComponentEditPolicy;
import ucm.map.AndFork;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.OrFork;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.Timer;
import ucm.map.WaitingPlace;

/**
 * @author TremblaE
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java - Code Style - Code Templates
 */
public class PathNodeTreeEditPart extends UcmModelElementTreeEditPart {

    /**
     * @param model
     */
    public PathNodeTreeEditPart(Object model) {
        super(model);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new PathNodeComponentEditPolicy());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractTreeEditPart#setWidget(org.eclipse.swt.widgets.Widget)
     */
    public void setWidget(Widget widget) {
        super.setWidget(widget);
        if (widget instanceof TreeItem) {
            if (getModel() instanceof EmptyPoint)
                ((TreeItem) widget).setForeground(new Color(null, 200, 200, 200));
        }

    }

    //	protected List getModelChildren() {
    //		ArrayList list = new ArrayList();
    //		if(getPathNode().getLabel() != null)
    //			list.add(getPathNode().getLabel());
    //		return list;
    //	}

    protected PathNode getPathNode() {
        return (PathNode) getModel();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractTreeEditPart#getImage()
     */
    protected Image getImage() {

        PathNode node = (PathNode) getModel();

        if (super.getImage() == null) {
            if (node instanceof StartPoint || node instanceof WaitingPlace)
                setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Start16.gif")).createImage()); //$NON-NLS-1$
            else if (node instanceof EmptyPoint)
                setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Node16.gif")).createImage()); //$NON-NLS-1$
            else if (node instanceof EndPoint)
                setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/End16.gif")).createImage()); //$NON-NLS-1$
            else if (node instanceof RespRef)
                setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Resp16.gif")).createImage()); //$NON-NLS-1$
            else if (node instanceof OrFork) {
                setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/OrFork16.gif")).createImage()); //$NON-NLS-1$
            } else if (node instanceof AndFork) {
                setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/AndFork16.gif")).createImage()); //$NON-NLS-1$
            } else if (node instanceof Stub)
                setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Stub16.gif")).createImage()); //$NON-NLS-1$
            else if (node instanceof Timer)
                setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Timer16.gif")).createImage()); //$NON-NLS-1$
            else
                setImage((ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Node16.gif")).createImage()); //$NON-NLS-1$
        }

        return super.getImage();
    }
}