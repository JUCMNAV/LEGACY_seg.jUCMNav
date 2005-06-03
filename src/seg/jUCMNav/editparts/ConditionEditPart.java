/*
 * Created on Jun 1, 2005
 */
package seg.jUCMNav.editparts;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.TextCellEditor;

import seg.jUCMNav.editpolicies.directEditPolicy.ExtendedDirectEditManager;
import seg.jUCMNav.editpolicies.directEditPolicy.LabelCellEditorLocator;
import seg.jUCMNav.figures.EditableLabel;
import seg.jUCMNav.figures.LabelFigure;
import seg.jUCMNav.figures.SplineConnection;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import urncore.Condition;
import urncore.Label;

/**
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

        NodeConnectionEditPart nc = (NodeConnectionEditPart) getViewer().getEditPartRegistry().get(getNodeConnection());
        if (nc != null) {
            SplineConnection sp = (SplineConnection) nc.getFigure();
            if (sp != null) {
                PointList list = sp.getPoints();
                if (list != null) {
                    Point mid = list.getMidpoint();
                    location = new Point(mid.x - ((Condition) getModel()).getDeltaX(), mid.y - ((Condition) getModel()).getDeltaY());
                }
            }
        }

        return location;
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
                    return "";
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

    public NodeConnection getNodeConnection() {
        return (NodeConnection) getUCMmodelElement();
    }

}