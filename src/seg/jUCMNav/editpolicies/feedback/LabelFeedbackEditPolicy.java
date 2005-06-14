/*
 * Created on 2005-03-04
 *
 */
package seg.jUCMNav.editpolicies.feedback;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

import seg.jUCMNav.editparts.LabelEditPart;
import seg.jUCMNav.editparts.NodeConnectionEditPart;
import seg.jUCMNav.editparts.PathNodeEditPart;
import seg.jUCMNav.figures.LabelFigure;
import ucm.map.ComponentRef;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import urncore.ComponentLabel;
import urncore.Condition;
import urncore.Label;
import urncore.NodeLabel;

/**
 * Created 2005-03-04
 * 
 * On mouse hover of labels, draw line from label to referenced part.
 * 
 * @author jkealey
 */
public class LabelFeedbackEditPolicy extends GraphicalEditPolicy {

    Polyline line;
    RoundedRectangle roundrect;

    /**
     *  
     */
    public LabelFeedbackEditPolicy() {
        super();
    }

    private LabelFigure getFigure() {
        return (LabelFigure) ((LabelEditPart) this.getHost()).getFigure();
    }

    private EObject getReference() {
        Label lbl = (Label) ((LabelEditPart) this.getHost()).getModel();
        if (lbl instanceof NodeLabel)
            return ((NodeLabel) lbl).getPathNode();
        else if (lbl instanceof ComponentLabel)
            return ((ComponentLabel) lbl).getCompRef();
        else if (lbl instanceof Condition) {
            if (((Condition) lbl).getNodeConnection() != null)
                return ((Condition) lbl).getNodeConnection();
            else if (((Condition) lbl).getStartPoint() != null)
                return ((Condition) lbl).getStartPoint();
            else if (((Condition) lbl).getEndPoint() != null)
                return ((Condition) lbl).getEndPoint();
            else
                return null;
        } else
            return null;
    }

    public void eraseTargetFeedback(Request request) {
        if (line != null) {
            getFeedbackLayer().remove(line);
            line = null;
        }
        if (roundrect != null) {
            getFeedbackLayer().remove(roundrect);
            roundrect = null;
        }
    }

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
            if (getReference() instanceof ComponentRef) {
                ComponentRef cr = (ComponentRef) getReference();
                pt2 = new Point(cr.getX(), cr.getY());
            } else if (getReference() instanceof PathNode) {
                PathNode pn = (PathNode) getReference();
                try {
                    // we're looking for the first child so that we don't point to the external bounding box; we want the real figure.
                    // TODO: adjust when there are no children.
                    rect = ((IFigure) ((PathNodeEditPart) getHost().getViewer().getEditPartRegistry().get(pn)).getNodeFigure().getChildren().get(0))
                            .getBounds();
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

            }

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
            line.setForegroundColor(new Color(null, 180, 180, 180));
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
        }
    }
}