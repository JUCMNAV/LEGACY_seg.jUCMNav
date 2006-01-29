package seg.jUCMNav.editparts;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.directEditPolicy.ExtendedDirectEditManager;
import seg.jUCMNav.editpolicies.directEditPolicy.LabelCellEditorLocator;
import seg.jUCMNav.figures.LabelFigure;
import seg.jUCMNav.views.preferences.GeneralPreferencePage;
import ucm.map.EndPoint;
import ucm.map.UCMmap;
import ucm.map.NodeConnection;
import ucm.map.StartPoint;
import urncore.Condition;
import urncore.Label;

/**
 * Editpart associated with urncore.Condition; a special label.
 * 
 * @author Jordan, jkealey
 */
public class ConditionEditPart extends LabelEditPart {

    public ConditionEditPart(Condition model, UCMmap diagram) {
        super(model);

        // conditions can be on these model elements. we don't know which until we check the references.
        if (model.getNodeConnection() != null)
            modelElement = model.getNodeConnection();
        else if (model.getStartPoint() != null)
            modelElement = model.getStartPoint();
        else if (model.getEndPoint() != null)
            modelElement = model.getEndPoint();

    }

    // jkealey: Removed during cleanup; I don't think we need to listen to conditions as they don't hold any condition relevant properties. leaving just in case
    // until next cleanup; see dactivate() as well. 
    //
    //    public void activate() {
    //        if (!isActive()) {
    //            if (modelElement instanceof Condition && ((Condition) modelElement).getNodeConnection() != null) {
    //                NodeConnection nc = ((Condition) modelElement).getNodeConnection();
    //                if (nc != null)
    //                    nc.eAdapters().add(this);
    //            }
    //        }
    //        super.activate();
    //    }

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

        if (getURNmodelElement() instanceof NodeConnection) {
            NodeConnectionEditPart nc = (NodeConnectionEditPart) getViewer().getEditPartRegistry().get(getNodeConnection());
            if (nc != null) {
                location = new Point(nc.getMiddlePoint().x - ((Condition) getModel()).getDeltaX(), nc.getMiddlePoint().y - ((Condition) getModel()).getDeltaY());
            }
            return location;
        }
        return super.calculateModelElementPosition(label, labelDimension);

    }

    //    public void deactivate() {
    //        if (isActive()) {
    //            if (modelElement instanceof Condition && ((Condition) modelElement).getNodeConnection() != null) {
    //                NodeConnection nc = ((Condition) modelElement).getNodeConnection();
    //                if (nc != null)
    //                    nc.eAdapters().remove(this);
    //            }
    //        }
    //        super.deactivate();
    //    }

    /**
     * Returns the node connection; no check to see if we are actually linking to a node connection
     * 
     * @return the node connection on which the condition is located
     */
    public NodeConnection getNodeConnection() {
        return (NodeConnection) getURNmodelElement();
    }

    /**
     * Opens the direct edit manager.
     *  
     */
    protected void performDirectEdit() {
        LabelFigure figure = (LabelFigure) getFigure();

        // remove surrounding []
        figure.setText(figure.getText().substring(1, figure.getText().length() - 1));

        if (manager == null) {

            ICellEditorValidator validator = new ICellEditorValidator() {
                public String isValid(Object value) {
                    return ""; //$NON-NLS-1$
                }
            };

            manager = new ExtendedDirectEditManager(this, TextCellEditor.class, new LabelCellEditorLocator(figure), figure, validator);
        }
        manager.show();
    }

    /**
     * Reverts to existing name in model when exiting from a direct edit (possibly before a commit which will result in a change in the label value)
     */
    public void revertNameChange() {
        LabelFigure tableFigure = (LabelFigure) getFigure();
        tableFigure.setVisible(true);
        // remove surrounding []
        tableFigure.setText(tableFigure.getText().substring(1, tableFigure.getText().length() - 1));

        refreshVisuals();
    }

    /**
     * Sets the text in the label and its properties. make it surrounded by [] and of a lighter color.
     */
    protected void setLabelText() {
        LabelFigure labelFigure = getLabelFigure();

        Condition cond = null;
        if (getURNmodelElement() instanceof NodeConnection) {
            cond = getNodeConnection().getCondition();
        } else if (getURNmodelElement() instanceof StartPoint) {
            cond = ((StartPoint) getURNmodelElement()).getPrecondition();
        } else if (getURNmodelElement() instanceof EndPoint) {
            cond = ((EndPoint) getURNmodelElement()).getPostcondition();
        }

        if (cond != null) {
            if (cond.getLabel() != null && !cond.getLabel().equals("")) { //$NON-NLS-1$
                labelFigure.setText("[" + cond.getLabel() + "]"); //$NON-NLS-1$ //$NON-NLS-2$
                labelFigure.setVisible(true);
            } else {
                labelFigure.setVisible(false);
                this.setSelected(0);
            }

            // get color from preferences
            RGB rgb = PreferenceConverter.getColor(JUCMNavPlugin.getDefault().getPreferenceStore(),GeneralPreferencePage.PREF_CONDITIONLABELCOLOR );
            labelFigure.setForegroundColor(new Color(null, rgb.red, rgb.green, rgb.blue));
        }
    }

}