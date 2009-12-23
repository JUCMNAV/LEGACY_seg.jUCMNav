package seg.jUCMNav.editparts;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.figures.ColorManager;
import seg.jUCMNav.figures.LabelFigure;
import seg.jUCMNav.views.wizards.scenarios.CodeEditor;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.StartPoint;
import ucm.map.UCMmap;
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
        // open condition editor
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        CodeEditor wizard = new CodeEditor();

        wizard.init(PlatformUI.getWorkbench(), null, (EObject) getModel());
        WizardDialog dialog = new WizardDialog(shell, wizard);
        dialog.open();

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

            // Set tool tip with description and formal expression, if any
            String toolTipText = ""; //$NON-NLS-1$
            if (cond.getExpression() != null && !cond.getExpression().equals("")) { //$NON-NLS-1$
                if (cond.getDescription() != null && !cond.getDescription().equals("")) { //$NON-NLS-1$
                    // Both
                    toolTipText = " " + cond.getDescription() + " \n " + cond.getExpression() + " "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                } else {
                    // Expression only
                    toolTipText = " " + cond.getExpression() + " "; //$NON-NLS-1$ //$NON-NLS-2$            		
                }
            } else {
                if (cond.getDescription() != null && !cond.getDescription().equals("")) { //$NON-NLS-1$
                    // Description only
                    toolTipText = " " + cond.getDescription() + " "; //$NON-NLS-1$ //$NON-NLS-2$
                }
            }

            if (toolTipText.equals("")) { //$NON-NLS-1$
                labelFigure.setToolTip(null);
            } else {
                labelFigure.setToolTip(new org.eclipse.draw2d.Label(toolTipText));
            }

            // get color from preferences
            labelFigure.setForegroundColor(ColorManager.CONDITIONLABEL);
        }
    }

}