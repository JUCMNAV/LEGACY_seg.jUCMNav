package seg.jUCMNav.editparts;

import grl.Contribution;
import grl.ContributionChange;
import grl.ContributionRange;
import grl.LinkRef;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.RoutingListener;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm;
import seg.jUCMNav.figures.LabelFigure;
import seg.jUCMNav.figures.LinkRefConnection;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.strategies.QuantitativeGRLStrategyAlgorithm;
import seg.jUCMNav.views.preferences.GeneralPreferencePage;
import urncore.ConnectionLabel;
import urncore.IURNConnection;
import urncore.Label;

/**
 * Editpart associated with urncore.ConnectionLabel; a special label.
 * 
 * @author etremblay
 */
public class ConnectionLabelEditPart extends LabelEditPart {
    private Image img;
    private RoutingListener routingListener;
    
    public ConnectionLabelEditPart(ConnectionLabel model) {
        super(model);

        // conditions can be on these model elements. we don't know which until we check the references.
        if (model.getConnection() != null)
            modelElement = model.getConnection();

    }

    @Override
    public void activate() {
        LinkRefEditPart nc = (LinkRefEditPart) getViewer().getEditPartRegistry().get(getConnection());
        
        routingListener = new RoutingListener.Stub(){
            public void postRoute(Connection connection) {
                refreshVisuals();
            }
        };
        
        if (nc!=null)
            ((LinkRefConnection)nc.getConnectionFigure()).addRoutingListener(routingListener);
        super.activate();
    }

    @Override
    public void deactivate() {
        LinkRefEditPart nc = (LinkRefEditPart) getViewer().getEditPartRegistry().get(getConnection());
        
        if(nc != null)
            ((LinkRefConnection)nc.getConnectionFigure()).removeRoutingListener(routingListener);
        super.deactivate();
    }

    /**
     * Places labels on the screen given their size, the model element's position and the deltax/y.
     * 
     * NodeConnection conditions are placed relative to the middle of the node connection.
     * 
     * For pre/post-conditions, refer to superclass.
     * 
     */
    protected Point calculateModelElementPosition(Label label, Dimension labelDimension) {
        Point location = new Point(0, 0);

        if (getURNmodelElement() instanceof IURNConnection && label != null && labelDimension != null) {
            LinkRefEditPart nc = (LinkRefEditPart) getViewer().getEditPartRegistry().get(getConnection());
            if (nc != null) {
                /**
                 * The position of a ConnectionLabel is calculated perpendicularly to the connection.  In fact perpendicular to the last segment of the connection if the connection has bendpoints.
                 * If we consider the last segment of the connection as the vector A, then deltaY is added in the direction of vector A.
                 * DeltaX is added in the direction of the normal of A.
                 * 
                 * We simply use vector addition to get the final position x, y of the label.
                 * 
                 * See AbstractDiagramXYLayoutEditPolicy to see how we get the deltaX, deltaY when the user moves the label
                 */
                
                PointList points = nc.getConnectionFigure().getPoints();
                Point last = points.getLastPoint();
                Point preLast = points.getPoint(points.size()-2);
                
                Dimension normal = new Dimension(last.x - preLast.x, last.y - preLast.y);
                double normalL = length(normal.width, normal.height);
                
                double alpha = Math.asin(normal.height / normalL);
                int quadrant = calculateQuadrant(normal);
                
                if(quadrant == 2 || quadrant == 3)
                    alpha = Math.PI - alpha;
                
                double b1 = label.getDeltaY() * Math.cos(alpha) + label.getDeltaX()*Math.sin(alpha) - labelDimension.width / 2;
                double b2 = label.getDeltaY() * Math.sin(alpha) - label.getDeltaX()*Math.cos(alpha) - labelDimension.height / 2;
                
                location = new Point((int)b1 + last.x, (int)b2 + last.y);
            }
            return location;
        }
        
        return super.calculateModelElementPosition(label, labelDimension);
    }
    
    private double dotProduct(double p1x, double p1y, double p2x, double p2y) {
        return p1x*p2x + p1y*p2y;
    }
    
    private double length(double px, double py) {
        return Math.sqrt(Math.pow(px,2) + Math.pow(py, 2));
    }
    
    private int calculateQuadrant(Dimension vect) {
        if(vect.height > 0) {
            if(vect.width > 0)
                return 1;
            else
                return 2;
        } else
        {
            if(vect.width > 0)
                return 4;
            else
                return 3;
        }
    }

    /**
     * Returns the node connection; no check to see if we are actually linking to a node connection
     * 
     * @return the node connection on which the ConnectionLabel is located
     */
    public IURNConnection getConnection() {
        return (IURNConnection) getURNmodelElement();
    }

    /**
     * Reverts to existing name in model when exiting from a direct edit (possibly before a commit which will result in a change in the label value)
     */
    public void revertNameChange() {
        LabelFigure tableFigure = (LabelFigure) getFigure();
        tableFigure.setVisible(true);
        // remove surrounding []
        if (tableFigure.getEditableText().startsWith("[") && tableFigure.getEditableText().length() > 2) //$NON-NLS-1$
            tableFigure.setEditableText(tableFigure.getEditableText().substring(1, tableFigure.getEditableText().length() - 1));

        refreshVisuals();
    }

    /**
     * Sets the text in the label and its properties. make it surrounded by [] and of a lighter color.
     */
    protected void setLabelText() {
        LabelFigure labelFigure = getLabelFigure();

        if(((LinkRef)getConnection()).getLink() instanceof Contribution) {
            IGRLStrategyAlgorithm algo = EvaluationStrategyManager.getInstance().getEvaluationAlgorithm();
            int evalType = algo.getEvaluationType();
            
            Contribution contrib = (Contribution)((LinkRef)getConnection()).getLink();
            // Set the contribution Label
            String type = contrib.getContribution().getName();
            if (!type.equals("Unknown")) { //$NON-NLS-1$


                if (GeneralPreferencePage.getGrlTextVisible()) {
                    if (algo instanceof QuantitativeGRLStrategyAlgorithm || evalType == IGRLStrategyAlgorithm.EVAL_CONSTRAINT_SOLVER) {
                        //int val = contrib.getQuantitativeContribution();
                        int val = EvaluationStrategyManager.getInstance().getActiveQuantitativeContribution(contrib);
                        //val = StrategyEvaluationPreferences.getValueToVisualize(val);
                        
                        
                        labelFigure.setText("" + val); //$NON-NLS-1$
                    } else {
                        labelFigure.setText(type);
                    }

                    ContributionChange change = EvaluationStrategyManager.getInstance().findApplicableContributionChange(contrib, true);
                    if (change!=null) {
                        if (change.getContribRange()!=null)
                        {
                            ContributionRange range = change.getContribRange();
                            labelFigure.setText(labelFigure.getText() + " [" + range.getStart() + ".." + range.getEnd() + "]" ); //$NON-NLS-1$  //$NON-NLS-2$ //$NON-NLS-3$  
                        }
                        if (change.getContext() == EvaluationStrategyManager.getInstance().getContributionContext())
                            labelFigure.setText(labelFigure.getText() + "(**)"); //$NON-NLS-1$ // two stars to mean locally changed.
                        else 
                            labelFigure.setText(labelFigure.getText() + "(*)"); //$NON-NLS-1$ // star to mean inherited change.
                    }
                } else {
                    labelFigure.setText(""); //$NON-NLS-1$
                }

                // Set the icon
                if (type.equals("Make")) { //$NON-NLS-1$
                    img = (JUCMNavPlugin.getImage("icons/Make.gif")); //$NON-NLS-1$
                } else if (type.equals("Help")) { //$NON-NLS-1$
                    img = (JUCMNavPlugin.getImage("icons/Help.gif")); //$NON-NLS-1$
                } else if (type.equals("SomePositive")) { //$NON-NLS-1$
                    img = (JUCMNavPlugin.getImage("icons/SomePositive.gif")); //$NON-NLS-1$
                } else if (type.equals("SomeNegative")) { //$NON-NLS-1$
                    img = (JUCMNavPlugin.getImage("icons/SomeNegative.gif")); //$NON-NLS-1$
                } else if (type.equals("Hurt")) { //$NON-NLS-1$
                    img = (JUCMNavPlugin.getImage("icons/Hurt.gif")); //$NON-NLS-1$
                } else if (type.equals("Break")) { //$NON-NLS-1$
                    img = (JUCMNavPlugin.getImage("icons/Break.gif")); //$NON-NLS-1$
                }
                if (img != null && GeneralPreferencePage.getGrlIconVisible()) {
                    labelFigure.setIcon(img);
                }
                else
                    labelFigure.setIcon(null);
                
                labelFigure.setVisible(true);
                
            } else {
                labelFigure.setVisible(false);
            }
        }
    }
}