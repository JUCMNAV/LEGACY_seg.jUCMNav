package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.ICreateElementCommand;
import seg.jUCMNav.model.util.URNNamingHelper;
import ucm.map.ComponentRef;
import ucm.map.PathNode;
import urncore.ComponentLabel;
import urncore.Condition;
import urncore.Label;
import urncore.NodeLabel;
import urncore.UCMmodelElement;

/**
 * Creates a NodeLabels and ComponentLabels for PathNodes and ComponentRefs that don't have any.
 * 
 * Doesn't handle Conditions as they are supposed to always exist with their model elements.
 * 
 * @author Jordan, jkealey
 */
public class CreateLabelCommand extends Command implements JUCMNavCommand, ICreateElementCommand {
    private static final String CreateCommand_Label = "CreateLabelCommand"; //$NON-NLS-1$
    private Label label;
    private UCMmodelElement modelElement;
    private int deltaX;
    private int deltaY;

    private Condition cond;

    /**
     * Create a label for elem.
     * 
     * @param elem
     *            a PathNode or ComponentRef
     */
    public CreateLabelCommand(UCMmodelElement elem) {
        modelElement = elem;
        setLabel(Messages.getString("CreateLabelCommand.createLabel")); //$NON-NLS-1$
    }

    /**
     * Create a label for elem.
     * 
     * @param cond
     *            a PathNode or ComponentRef
     */
    public CreateLabelCommand(Condition cond) {
        this.cond = cond;
        setLabel(Messages.getString("CreateLabelCommand.createLabel")); //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return modelElement != null || cond != null;
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        if (cond == null) {
            if (modelElement instanceof PathNode) {
                label = (Label) ModelCreationFactory.getNewObject(((PathNode) modelElement).getDiagram().getUrndefinition().getUrnspec(), NodeLabel.class);
            } else if (modelElement instanceof ComponentRef) {
                label = (Label) ModelCreationFactory.getNewObject(((ComponentRef) modelElement).getDiagram().getUrndefinition().getUrnspec(),
                        ComponentLabel.class);
            }

            label.setDeltaX(deltaX);
            label.setDeltaY(deltaY);
        } else {
            label = cond;
        }
        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        if (cond == null) {
            if (modelElement instanceof PathNode) {
                ((PathNode) modelElement).setLabel((NodeLabel) label);
            } else if (modelElement instanceof ComponentRef) {
                ((ComponentRef) modelElement).setLabel((ComponentLabel) label);
            }
        } else {
            cond.setLabel(URNNamingHelper
                    .getNameFromExpression((cond.getExpression() == null || cond.getExpression().length() == 0) ? "-" : cond.getExpression())); //$NON-NLS-1$
        }

        testPostConditions();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        if (cond == null) {
            if (modelElement instanceof PathNode) {
                ((PathNode) modelElement).setLabel(null);
            } else if (modelElement instanceof ComponentRef) {
                ((ComponentRef) modelElement).setLabel(null);
            }
        } else {
            cond.setLabel(null);
        }

        testPreConditions();
    }

    /**
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert label != null : "pre Label"; //$NON-NLS-1$
        assert modelElement != null || cond != null : "pre UCMmodelElement"; //$NON-NLS-1$

        // jkealey: don't know why these are commented
        // assert label.getPathNode() == null : "pre NodeLabel not connected to a PathNode";
        // assert node.getLabel() == null : "pre PathNode not connected to a NodeLabel";
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert label != null : "pre Label"; //$NON-NLS-1$
        assert modelElement != null || cond != null : "pre UCMmodelElement"; //$NON-NLS-1$
        // jkealey: don't know why these are commented
        // assert label.getPathNode().equals(node) : "pre NodeLabel connected to correct PathNode";
        // assert node.getLabel().equals(label) : "pre PathNode connected to correct NodeLabel";
    }

    /**
     * @param modelElement
     *            The modelElement to set.
     */
    public void setModelElement(UCMmodelElement modelElement) {
        this.modelElement = modelElement;
    }

    /**
     * @param deltaX
     *            The deltaX to set.
     */
    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    /**
     * @param deltaY
     *            The deltaY to set.
     */
    public void setDeltaY(int deltaY) {
        this.deltaY = deltaY;
    }

    public Object getNewModelElement() {
        return label;
    }
}