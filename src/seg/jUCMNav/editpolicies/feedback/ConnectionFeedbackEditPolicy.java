package seg.jUCMNav.editpolicies.feedback;

import java.util.Map.Entry;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.swt.graphics.Color;

import seg.jUCMNav.editparts.BeliefLinkEditPart;
import seg.jUCMNav.editparts.NodeConnectionEditPart;
import seg.jUCMNav.editparts.dynamicContextEvaluationViewEditparts.DynamicContextTraversalEvaluation;
import seg.jUCMNav.figures.ColorManager;
import seg.jUCMNav.figures.SplineConnection;
import seg.jUCMNav.views.dynamicContexts.DynamicContextsView;
import ucm.map.NodeConnection;
import urncore.URNmodelElement;

/**
 * On mouse hover of node connection, it becomes thicker so that we can click on it more easily. Change the connection's color when selected.
 * 
 * @author jkealey
 */
public class ConnectionFeedbackEditPolicy extends SelectionEditPolicy {

    private Color previousColor = ColorManager.LINE;

	public static DynamicContextTraversalEvaluation te;

    /**
     * Convenience method to avoid casting.
     * 
     * @return the figure
     */
    private PolylineConnection getFigure() {
        return (PolylineConnection) ((AbstractConnectionEditPart) this.getHost()).getFigure();
    }

    /**
     * Make line thin again.
     */
    public void eraseTargetFeedback(Request request) {
        if (this.getHost() instanceof BeliefLinkEditPart) {
            getFigure().setLineWidth(2);
        } else {
			if (DynamicContextsView.te != null && te != null && DynamicContextsView.currentView == DynamicContextsView.ID_STRATEGY && DynamicContextsView.currentContext != null
					&& DynamicContextsView.currentContext.getScenario() != null) {
				if (this.getHost() instanceof NodeConnectionEditPart) {
					NodeConnectionEditPart ncep = (NodeConnectionEditPart) this.getHost();
					NodeConnection n = (NodeConnection) ncep.getModel();
					String idModelSource = ((URNmodelElement) n.getSource()).getId();
					String idModelTarget = ((URNmodelElement) n.getTarget()).getId();
					for (Entry<SplineConnection, Integer> entry : NodeConnectionEditPart.connectionWidth.entrySet()) {
						NodeConnection nc = entry.getKey().getLink();
						String idIncomingNCSource = ((URNmodelElement) nc.getSource()).getId();
						String idIncomingNCTarget = ((URNmodelElement) nc.getTarget()).getId();

						if (idModelSource.equals(idIncomingNCSource) && (idModelTarget.equals(idIncomingNCTarget)))
							getFigure().setLineWidth(entry.getValue());
					}
				}
			} else
            getFigure().setLineWidth(3);
        }
    }

    /**
     * Make line thicker.
     */
    public void showTargetFeedback(Request request) {
        if (this.getHost() instanceof BeliefLinkEditPart) {
            getFigure().setLineWidth(4);
        } else {
			if (DynamicContextsView.te != null && te != null  && DynamicContextsView.currentView == DynamicContextsView.ID_STRATEGY  && DynamicContextsView.currentContext != null
					&& DynamicContextsView.currentContext.getScenario() != null) {
				if (this.getHost() instanceof NodeConnectionEditPart) {
					NodeConnectionEditPart ncep = (NodeConnectionEditPart) this.getHost();
					NodeConnection n = (NodeConnection) ncep.getModel();
					String idModelSource = ((URNmodelElement) n.getSource()).getId();
					String idModelTarget = ((URNmodelElement) n.getTarget()).getId();
					for (Entry<SplineConnection, Integer> entry : NodeConnectionEditPart.connectionWidth.entrySet()) {
						NodeConnection nc = entry.getKey().getLink();
						String idIncomingNCSource = ((URNmodelElement) nc.getSource()).getId();
						String idIncomingNCTarget = ((URNmodelElement) nc.getTarget()).getId();

						if (idModelSource.equals(idIncomingNCSource) && (idModelTarget.equals(idIncomingNCTarget)))
							getFigure().setLineWidth(entry.getValue() + 3);
					}
				}
			} else
            getFigure().setLineWidth(6);
        }
    }

    /**
     * Draw black.
     * 
     * @see org.eclipse.gef.editpolicies.SelectionEditPolicy#hideSelection()
     */
    protected void hideSelection() {
        // Using previousColor to return to the previous color of the figure
        getFigure().setForegroundColor(previousColor);

    }

    /**
     * Draw blue.
     * 
     */
    protected void showSelection() {
        if (getFigure().getForegroundColor() != ColorManager.SELECTED)
            previousColor = getFigure().getForegroundColor();
        getFigure().setForegroundColor(ColorManager.SELECTED);
    }
}
