package seg.jUCMNav.model.commands.delete;

import grl.Contribution;
import grl.LinkRef;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;

import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import urncore.ComponentLabel;
import urncore.Condition;
import urncore.ConnectionLabel;
import urncore.IURNContainerRef;
import urncore.Label;
import urncore.NodeLabel;

/**
 * Deletes a label from the model. It is to be noted that Conditions which extend Labels are not removed, they simply have their label string put to null, so
 * they no longer appear.
 * 
 * @author Jordan, jkealey
 */
public class DeleteLabelCommand extends Command {

    // the label to delete
    private Label label;
    // the element it labels.
    EObject modelElement;
    // the old string contents, to be used by conditions.
    private String oldStr;

    /**
     * A label must exist.
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        if (label instanceof Condition) {
            boolean b = label != null && modelElement != null;
            if (label instanceof Condition && modelElement instanceof StartPoint)
                return b && ((StartPoint) modelElement).getPrecondition() != null;
            else if (label instanceof Condition && modelElement instanceof EndPoint)
                return b && ((EndPoint) modelElement).getPostcondition() != null;
            else if (label instanceof Condition && modelElement instanceof NodeConnection)
                return b && ((NodeConnection) modelElement).getCondition() != null;
            else
                return false;
        } else if (label instanceof ConnectionLabel) {
            ConnectionLabel conLabel = (ConnectionLabel)label;
            if(conLabel.getConnection() instanceof LinkRef && ((LinkRef)conLabel.getConnection()).getLink() instanceof Contribution) {
                return false;
            }
        }

        return label != null && modelElement != null;
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        if (label instanceof Condition && modelElement instanceof StartPoint && ((StartPoint) modelElement).getPrecondition() != null)
            oldStr = ((StartPoint) modelElement).getPrecondition().getLabel();
        else if (label instanceof Condition && modelElement instanceof EndPoint && ((EndPoint) modelElement).getPostcondition() != null)
            oldStr = ((EndPoint) modelElement).getPostcondition().getLabel();
        else if (label instanceof Condition && modelElement instanceof NodeConnection && ((NodeConnection) modelElement).getCondition() != null)
            oldStr = ((NodeConnection) modelElement).getCondition().getLabel();

        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        if (label instanceof Condition) {

            if (modelElement instanceof StartPoint)
                ((StartPoint) modelElement).getPrecondition().setLabel(null);
            else if (modelElement instanceof EndPoint)
                ((EndPoint) modelElement).getPostcondition().setLabel(null);
            else if (modelElement instanceof NodeConnection) {
                if (((NodeConnection) modelElement).getCondition() != null) {
                    ((NodeConnection) modelElement).getCondition().setLabel(null);
                }
            }

        } else if (modelElement instanceof PathNode) {
            PathNode node = (PathNode) modelElement;
            node.setLabel(null);
        } else if (modelElement instanceof IURNContainerRef) {
            IURNContainerRef component = (IURNContainerRef) modelElement;
            component.setLabel(null);
        }

        testPostConditions();
    }

    /**
     * A label must not exist.
     * 
     * @see org.eclipse.gef.commands.Command#canUndo()
     */
    public boolean canUndo() {

        if (label instanceof Condition) {
            if (modelElement instanceof StartPoint)
                return label != null && ((StartPoint) modelElement).getPrecondition() != null;
            else if (modelElement instanceof EndPoint)
                return label != null && ((EndPoint) modelElement).getPostcondition() != null;
            else if (modelElement instanceof NodeConnection)
                return label != null;
        } else if (modelElement instanceof PathNode) {
            PathNode node = (PathNode) modelElement;
            return label != null && node.getLabel() == null;
        } else if (modelElement instanceof IURNContainerRef) {
            IURNContainerRef component = (IURNContainerRef) modelElement;
            return label != null && component.getLabel() == null;
        } else if (modelElement instanceof NodeConnection) {
            NodeConnection nc = (NodeConnection) modelElement;
            return label != null && nc.getCondition() == null;
        }

        return true;
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        if (label instanceof Condition) {
            if (modelElement instanceof StartPoint)
                ((StartPoint) modelElement).getPrecondition().setLabel(oldStr);
            else if (modelElement instanceof EndPoint)
                ((EndPoint) modelElement).getPostcondition().setLabel(oldStr);
            else if (modelElement instanceof NodeConnection)
                if (((NodeConnection) modelElement).getCondition() != null && oldStr != null) {
                    ((NodeConnection) modelElement).getCondition().setLabel(oldStr);
                }

        } else if (modelElement instanceof PathNode) {
            PathNode node = (PathNode) modelElement;
            node.setLabel((NodeLabel) label);
        } else if (modelElement instanceof IURNContainerRef) {
            IURNContainerRef component = (IURNContainerRef) modelElement;
            component.setLabel((ComponentLabel) label);
        } else if (modelElement instanceof NodeConnection) {
            NodeConnection nc = (NodeConnection) modelElement;
            nc.setCondition((Condition) label);
        }

        testPreConditions();
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert label != null : "pre Label"; //$NON-NLS-1$
        assert modelElement != null : "pre UCMmodelElement"; //$NON-NLS-1$
        // jkealey: don't know why this is commented.
        // assert label.getPathNode() != null : "pre NodeLabel not connected to a PathNode";
        // assert node.getLabel() != null : "pre PathNode not connected to a NodeLabel";
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert label != null : "pre Label"; //$NON-NLS-1$
        assert modelElement != null : "pre UCMmodelElement"; //$NON-NLS-1$
        // jkealey: don't know why this is commented.
        // assert label.getPathNode().equals(node) : "pre NodeLabel connected to correct PathNode";
        // assert node.getLabel().equals(label) : "pre PathNode connected to correct NodeLabel";
    }

    /**
     * @param modelElement
     *            The modelElement to set.
     */
    public void setModelElement(EObject modelElement) {
        this.modelElement = modelElement;
    }

    /**
     * @param label
     *            The label to set.
     */
    public void setLabel(Label label) {
        this.label = label;
    }
}