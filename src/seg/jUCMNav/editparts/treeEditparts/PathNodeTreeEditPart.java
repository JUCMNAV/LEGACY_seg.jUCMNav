package seg.jUCMNav.editparts.treeEditparts;

import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.PathNodeComponentEditPolicy;
import seg.jUCMNav.figures.ColorManager;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.DirectionArrow;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Timer;
import ucm.map.WaitingPlace;

/**
 * TreeEditPart for PathNodes
 * 
 * @author TremblaE
 * 
 */
public class PathNodeTreeEditPart extends UrnModelElementTreeEditPart {

    /**
     * @param model
     *            the pathnode
     */
    public PathNodeTreeEditPart(PathNode model) {
        super(model);
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new PathNodeComponentEditPolicy());
    }

    /**
     * When setting widgets, uses a lighter coloir for DirectionArrows and EmptyPoints
     * 
     * @see org.eclipse.gef.editparts.AbstractTreeEditPart#setWidget(org.eclipse.swt.widgets.Widget)
     */
    public void setWidget(Widget widget) {
        super.setWidget(widget);
        if (widget instanceof TreeItem) {
            if (getModel() instanceof EmptyPoint || getModel() instanceof DirectionArrow)
                ((TreeItem) widget).setForeground(ColorManager.DARKGRAY);
        }

    }

    /**
     * @return the path node
     */
    protected PathNode getPathNode() {
        return (PathNode) getModel();
    }

    /**
     * Returns an image representing the PathNode.
     */
    protected Image getImage() {

        PathNode node = (PathNode) getModel();

        if (super.getImage() == null) {
            if (node instanceof Timer)
                setImage((JUCMNavPlugin.getImage("icons/Timer16.gif"))); //$NON-NLS-1$
            else if (node instanceof StartPoint || node instanceof WaitingPlace)
                setImage((JUCMNavPlugin.getImage("icons/Start16.gif"))); //$NON-NLS-1$
            else if (node instanceof EmptyPoint)
                setImage((JUCMNavPlugin.getImage("icons/Node16.gif"))); //$NON-NLS-1$
            else if (node instanceof EndPoint)
                setImage((JUCMNavPlugin.getImage("icons/End16.gif"))); //$NON-NLS-1$
            else if (node instanceof DirectionArrow)
                setImage((JUCMNavPlugin.getImage("icons/DirectionArrow16.gif"))); //$NON-NLS-1$
            else if (node instanceof RespRef)
                setImage((JUCMNavPlugin.getImage("icons/Resp16.gif"))); //$NON-NLS-1$
            else if (node instanceof OrFork) {
                setImage((JUCMNavPlugin.getImage("icons/OrFork16.gif"))); //$NON-NLS-1$
            } else if (node instanceof AndFork) {
                setImage((JUCMNavPlugin.getImage("icons/AndFork16.gif"))); //$NON-NLS-1$
            } else if (node instanceof OrJoin) {
                setImage((JUCMNavPlugin.getImage("icons/OrJoin16.gif"))); //$NON-NLS-1$
            } else if (node instanceof AndJoin) {
                setImage((JUCMNavPlugin.getImage("icons/AndJoin16.gif"))); //$NON-NLS-1$
            } else
                setImage((JUCMNavPlugin.getImage("icons/Node16.gif"))); //$NON-NLS-1$
        }

        return super.getImage();
    }
}