package seg.jUCMNav.editpolicies.feedback;

import grl.ActorRef;
import grl.LinkRef;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.swt.SWT;

import seg.jUCMNav.editparts.LabelEditPart;
import seg.jUCMNav.editparts.LinkRefEditPart;
import seg.jUCMNav.editparts.NodeConnectionEditPart;
import seg.jUCMNav.editparts.PathNodeEditPart;
import seg.jUCMNav.figures.ColorManager;
import seg.jUCMNav.figures.LabelFigure;
import seg.jUCMNav.figures.LinkRefConnection;
import ucm.map.NodeConnection;
import urncore.ComponentLabel;
import urncore.Condition;
import urncore.ConnectionLabel;
import urncore.IURNConnection;
import urncore.IURNContainerRef;
import urncore.IURNNode;
import urncore.Label;
import urncore.NodeLabel;

/**
 * On mouse hover of labels, draw line from label to referenced part.
 * 
 * @author jkealey
 */
public class LabelFeedbackEditPolicy extends GraphicalEditPolicy {

    // the line from the element to the label
    private Polyline line;

    // the bounding box around the label.
    private RoundedRectangle roundrect;

    /**
     * Convenience method to avoid casting.
     * 
     * @return the LabelFigure being editd.
     */
    private LabelFigure getFigure() {
        return (LabelFigure) ((LabelEditPart) this.getHost()).getFigure();
    }

    /**
     * Returns the associated ComponentRef, PathNode or NodeConnection, given a the label type.
     * 
     * @return the model object associated with this label
     */
    private EObject getReference() {
        Label lbl = (Label) ((LabelEditPart) this.getHost()).getModel();
        if (lbl instanceof NodeLabel)
            return ((NodeLabel) lbl).getNode();
        else if (lbl instanceof ComponentLabel)
            return ((ComponentLabel) lbl).getContRef();
        else if (lbl instanceof Condition) {
            if (((Condition) lbl).getNodeConnection() != null)
                return ((Condition) lbl).getNodeConnection();
            else if (((Condition) lbl).getStartPoint() != null)
                return ((Condition) lbl).getStartPoint();
            else if (((Condition) lbl).getEndPoint() != null)
                return ((Condition) lbl).getEndPoint();
            else
                return null;
        } 
        else if (lbl instanceof ConnectionLabel) {
            if (((ConnectionLabel) lbl).getConnection() != null)
                return ((ConnectionLabel) lbl).getConnection();
        }
        
        return null;
    }

    /**
     * Remove the bounding box and line.
     */
    public void eraseTargetFeedback(Request request) {
        if (line != null) {
            getFeedbackLayer().remove(line);
            line = null;
        }
        if (roundrect != null) {
            getFeedbackLayer().remove(roundrect);
            roundrect = null;
        }

        if (getHost().getViewer() != null) {
            EditPart referencedEp = (EditPart) getHost().getViewer().getEditPartRegistry().get(getReference());
            if (referencedEp != null)
                referencedEp.eraseTargetFeedback(request);
        }
    }

    /**
     * Add the bounding box and line.
     */
    public void showTargetFeedback(Request request) {
        if (line == null && roundrect == null) {
            // we need to scale our feedback.
            double zoomLevel = ((ZoomManager) ((ScrollingGraphicalViewer) getHost().getViewer()).getProperty(ZoomManager.class.toString())).getZoom();

            line = new Polyline();
            PointList pl = new PointList();

            // get the center of the label.
            Point pt = getFigure().getLocation();
            pt.x += getFigure().getBounds().width / 2;
            pt.y += getFigure().getBounds().height / 2;

            // get the pathnode/component ref's figure
            Point pt2 = null;
            Rectangle rect = null;
            if (getReference() instanceof IURNContainerRef) {
                IURNContainerRef cr = (IURNContainerRef) getReference();
                // If it is an actor reference, the feedback should be in the middle top of the element
                if (cr instanceof ActorRef) {
                    pt2 = new Point(cr.getX() + (cr.getWidth() / 2), cr.getY());
                } else {
                    pt2 = new Point(cr.getX(), cr.getY());
                }
            } else if (getReference() instanceof IURNNode) {
                IURNNode pn = (IURNNode) getReference();
                try {
                    // we're looking for the first child so that we don't point to the external bounding box; we want the real figure.
                    // TODO: adjust when there are no children.
                    if (getHost().getViewer().getEditPartRegistry().get(pn) instanceof PathNodeEditPart) {
                        rect = ((IFigure) ((PathNodeEditPart) getHost().getViewer().getEditPartRegistry().get(pn)).getNodeFigure().getChildren().get(0))
                                .getBounds();
                    }
                } catch (Exception ex) {
                    rect = null;
                }
                pt2 = new Point(pn.getX(), pn.getY());
            } else if (getReference() instanceof NodeConnection) {
                NodeConnection nc = (NodeConnection) getReference();

                try {
                    pt2 = ((NodeConnectionEditPart) getHost().getViewer().getEditPartRegistry().get(nc)).getMiddlePoint();
                } catch (Exception ex) {
                    pt2 = pt;
                }
            } else if(getReference() instanceof IURNConnection) {
                pt2 = pt;
                if (getReference() instanceof LinkRef) {
                    LinkRef nc = (LinkRef)getReference();
                    LinkRefEditPart part = ((LinkRefEditPart) getHost().getViewer().getEditPartRegistry().get(nc));
                    LinkRefConnection conn = ((LinkRefConnection)part.getConnectionFigure());
                    pt2 = conn.getEnd();
                }
            }

            if (pt2 == null)
                return;

            // calculate the differences.
            int diffx = pt.x - pt2.x;
            int diffy = pt.y - pt2.y;

            // put the lines on the bounds.
            // TODO: improve positioning on center. must never be exact center but should always have either x or y positioned at center.
            if (diffx != 0 && Math.abs(diffx) > getFigure().getBounds().width) {
                pt.x -= (Math.abs(diffx) / diffx) * getFigure().getBounds().width / 2;
                if (rect != null)
                    pt2.x += (Math.abs(diffx) / diffx) * rect.width / 2;
            }

            if (diffy != 0) {
                pt.y -= (Math.abs(diffy) / diffy) * getFigure().getBounds().height / 2;
                if (rect != null)
                    pt2.y += (Math.abs(diffy) / diffy) * rect.height / 2;
            }

            // scale
            pt.scale(zoomLevel);
            pt2.scale(zoomLevel);
            pl.addPoint(pt);
            pl.addPoint(pt2);

            // style
            line.setPoints(pl);
            line.setForegroundColor(ColorManager.GRAY);
            line.setLineStyle(SWT.LINE_DOT);

            // rectangle over label.
            roundrect = new RoundedRectangle();
            roundrect.setFill(false);
            roundrect.setBounds(getFigure().getBounds().getCopy().scale(zoomLevel));
            roundrect.setForegroundColor(line.getForegroundColor());
            roundrect.setLineStyle(SWT.LINE_DOT);

            // add feedback
            getFeedbackLayer().add(roundrect);
            getFeedbackLayer().add(line);

            if (getHost().getViewer() != null) {
                EditPart referencedEp = (EditPart) getHost().getViewer().getEditPartRegistry().get(getReference());
                if (referencedEp != null)
                    referencedEp.showTargetFeedback(request);
            }

        }
    }
}