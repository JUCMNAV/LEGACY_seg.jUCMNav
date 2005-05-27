/*
 * Created on 2005-03-04
 *
 */
package seg.jUCMNav.editpolicies.feedback;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.Request;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;

import seg.jUCMNav.editparts.LabelEditPart;
import seg.jUCMNav.editparts.PathNodeEditPart;
import seg.jUCMNav.figures.LabelFigure;
import ucm.map.ComponentRef;
import ucm.map.PathNode;
import urncore.ComponentLabel;
import urncore.Label;
import urncore.NodeLabel;
import urncore.UCMmodelElement;

/**
 * Created 2005-03-04
 * 
 * On mouse hover of labels, draw line from label to referenced part.
 * 
 * @author jkealey
 */
public class LabelFeedbackEditPolicy extends GraphicalEditPolicy {

    Polyline line;

    /**
     *  
     */
    public LabelFeedbackEditPolicy() {
        super();
    }

    private LabelFigure getFigure() {
        return (LabelFigure) ((LabelEditPart) this.getHost()).getFigure();
    }

    private UCMmodelElement getReference() {
        Label lbl = (Label) ((LabelEditPart) this.getHost()).getModel();
        if (lbl instanceof NodeLabel)
            return ((NodeLabel) lbl).getPathNode();
        else if (lbl instanceof ComponentLabel)
            return ((ComponentLabel) lbl).getCompRef();
        else
            return null;
    }

    public void eraseTargetFeedback(Request request) {
        if (line != null) {
            getFeedbackLayer().remove(line);
            line = null;
        }
    }

    public void showTargetFeedback(Request request) {
        if (line == null) {

            line = new Polyline();
            PointList pl = new PointList();
            Point pt = getFigure().getLocation();
            pt.x += getFigure().getBounds().width / 2;
            pt.y += getFigure().getBounds().height / 2;
            Point pt2 = null;
            //pl.addPoint(getFigure().getLocation());
            //pl.addPoint(pt);

            Rectangle rect = null;
            if (getReference() instanceof ComponentRef) {
                ComponentRef cr = (ComponentRef) getReference();
                pt2 = new Point(cr.getX(), cr.getY());
            } else if (getReference() instanceof PathNode) {
                PathNode pn = (PathNode) getReference();
                try {
                    rect = ((IFigure) ((PathNodeEditPart) getHost().getViewer().getEditPartRegistry().get(pn)).getNodeFigure().getChildren().get(0))
                            .getBounds();
                } catch (Exception ex) {
                    rect = null;
                }
                pt2 = new Point(pn.getX(), pn.getY());
            }

            int diffx = pt.x - pt2.x;
            int diffy = pt.y - pt2.y;

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

            pl.addPoint(pt);
            pl.addPoint(pt2);

            line.setPoints(pl);
            getFeedbackLayer().add(line);
        }
    }
}