/*
 * Created on Jun 1, 2005
 */
package seg.jUCMNav.editparts;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.graphics.Color;

import seg.jUCMNav.editpolicies.directEditPolicy.ExtendedDirectEditManager;
import seg.jUCMNav.editpolicies.directEditPolicy.LabelCellEditorLocator;
import seg.jUCMNav.figures.EditableLabel;
import seg.jUCMNav.figures.LabelFigure;
import seg.jUCMNav.figures.util.JUCMNavFigure;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import urncore.Condition;
import urncore.Label;

/**
 * Editpart associated with urncore.Condition
 * 
 * @author Jordan
 */
public class ConditionEditPart extends LabelEditPart {
    private PathGraph diagram;

    public ConditionEditPart(Condition model, PathGraph diagram) {
        super(model);
        this.diagram = diagram;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.editparts.LabelEditPart#calculateModelElementPosition(urncore.Label, org.eclipse.draw2d.geometry.Dimension)
     */
    protected Point calculateModelElementPosition(Label label, Dimension labelDimension) {
        Point location = new Point(0, 0);

        if (getUCMmodelElement() instanceof NodeConnection) {
            NodeConnectionEditPart nc = (NodeConnectionEditPart) getViewer().getEditPartRegistry().get(getNodeConnection());
            if (nc != null) {
                location = new Point(nc.getMiddlePoint().x - ((Condition) getModel()).getDeltaX(), nc.getMiddlePoint().y - ((Condition) getModel()).getDeltaY());
            }
            return location;
        } else if (getUCMmodelElement() instanceof PathNode) {
            PathNode node = (PathNode) getUCMmodelElement();
            location = new Point(node.getX() - labelDimension.width / 2 - label.getDeltaX(), node.getY()
                    - (labelDimension.height + JUCMNavFigure.getDimension(getUCMmodelElement()).height / 2) - label.getDeltaY());
        }

        return super.calculateModelElementPosition(label, labelDimension);

    }

    public NodeConnection getNodeConnection() {
        return (NodeConnection) getUCMmodelElement();
    }

    /**
     * Opens the direct edit manager.
     *  
     */
    protected void performDirectEdit() {
        LabelFigure figure = (LabelFigure) getFigure();
        EditableLabel nameLabel = figure.getLabel();

        // remove surrounding []
        nameLabel.setText(nameLabel.getText().substring(1, nameLabel.getText().length() - 1));

        if (manager == null) {

            ICellEditorValidator validator = new ICellEditorValidator() {
                public String isValid(Object value) {
                    return ""; //$NON-NLS-1$
                }
            };

            manager = new ExtendedDirectEditManager(this, TextCellEditor.class, new LabelCellEditorLocator(nameLabel), nameLabel, validator);
        }
        manager.show();
    }

    /**
     * Reverts to existing name in model when exiting from a direct edit (possibly before a commit which will result in a change in the label value)
     */
    public void revertNameChange() {
        LabelFigure tableFigure = (LabelFigure) getFigure();
        EditableLabel label = tableFigure.getLabel();
        label.setVisible(true);
        // remove surrounding []
        label.setText(label.getText().substring(1, label.getText().length() - 1));

        refreshVisuals();
    }

    protected void setLabelText() {
        LabelFigure labelFigure = getLabelFigure();
        EditableLabel label = labelFigure.getLabel();

        Condition cond = null;
        if (getUCMmodelElement() instanceof NodeConnection) {
            cond = getNodeConnection().getCondition();
        } else if (getUCMmodelElement() instanceof StartPoint) {
            cond = ((StartPoint) getUCMmodelElement()).getPrecondition();
        } else if (getUCMmodelElement() instanceof EndPoint) {
            cond = ((EndPoint) getUCMmodelElement()).getPostcondition();
        }

        if (cond != null) {
            if (cond.getLabel() != null && !cond.getLabel().equals("")) { //$NON-NLS-1$
                label.setText("[" + cond.getLabel() + "]"); //$NON-NLS-1$ //$NON-NLS-2$
                labelFigure.setVisible(true);
            } else {
                labelFigure.setVisible(false);
                this.setSelected(0);
            }

            label.setForegroundColor(new Color(null, 100, 100, 100));
        }
    }

}