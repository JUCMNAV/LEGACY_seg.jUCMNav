package seg.jUCMNav.editparts;

import grl.Contribution;
import grl.ContributionChange;
import grl.LinkRef;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm;
import seg.jUCMNav.figures.LabelFigure;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.preferences.GeneralPreferencePage;
import seg.jUCMNav.views.wizards.scenarios.CodeEditor;
import urncore.ConnectionLabel;
import urncore.IURNConnection;
import urncore.IURNNode;
import urncore.Label;

/**
 * Editpart associated with urncore.ConnectionLabel; a special label.
 * 
 * @author etremblay
 */
public class ConnectionLabelEditPart extends LabelEditPart {
    private Image img;
    
    public ConnectionLabelEditPart(ConnectionLabel model) {
        super(model);

        // conditions can be on these model elements. we don't know which until we check the references.
        if (model.getConnection() != null)
            modelElement = model.getConnection();

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
                IURNNode node = ((LinkRef)getConnection()).getTarget();
                if(node != null) {
                    ConnectionLabel lbl = (ConnectionLabel) getModel();
                    ModelElementEditPart part = (ModelElementEditPart) this.getRoot().getViewer().getEditPartRegistry().get(node);
                    int height = 0;
                    if(part != null)
                        height = part.getFigure().getBounds().getCopy().height;
                    
                    int x = node.getX() - label.getDeltaX() - (labelDimension.width / 2);
                    int y = node.getY() - label.getDeltaY() - (labelDimension.height);
                    location = new Point(x, y);
                }
            }
            return location;
        }
        
        return super.calculateModelElementPosition(label, labelDimension);
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

        if(((LinkRef)getConnection()).getLink() instanceof Contribution) {
            int evalType = EvaluationStrategyManager.getInstance().getEvaluationAlgorithm().getEvaluationType();
            
            Contribution contrib = (Contribution)((LinkRef)getConnection()).getLink();
            // Set the contribution Label
            String type = contrib.getContribution().getName();
            if (!type.equals("Unknown")) { //$NON-NLS-1$


                if (GeneralPreferencePage.getGrlTextVisible()) {
                    if (evalType == IGRLStrategyAlgorithm.EVAL_FORMULA || evalType == IGRLStrategyAlgorithm.EVAL_QUANTITATIVE || evalType == IGRLStrategyAlgorithm.EVAL_CONSTRAINT_SOLVER) {
                        //int val = contrib.getQuantitativeContribution();
                        int val = EvaluationStrategyManager.getInstance().getActiveQuantitativeContribution(contrib);
                        //val = StrategyEvaluationPreferences.getValueToVisualize(val); 
                        labelFigure.setText("" + val); //$NON-NLS-1$
                    } else {
                        labelFigure.setText(type);
                    }

                    ContributionChange change = EvaluationStrategyManager.getInstance().findApplicableContributionChange(contrib, true);
                    if (change != null && change.getContext() == EvaluationStrategyManager.getInstance().getContributionContext())
                        labelFigure.setText(labelFigure.getText() + "(**)"); //$NON-NLS-1$ // two stars to mean locally changed.
                    else if (change != null)
                        labelFigure.setText(labelFigure.getText() + "(*)"); //$NON-NLS-1$ // star to mean inherited change.
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
                labelFigure.setVisible(true);
            } else {
                labelFigure.setVisible(false);
            }
        }
    }
}