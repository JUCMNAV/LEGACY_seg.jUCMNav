/*
 * Created on May 3, 2005
 */
package seg.jUCMNav.model.commands.delete;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;

import ucm.map.ComponentRef;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import urncore.ComponentLabel;
import urncore.Condition;
import urncore.Label;
import urncore.NodeLabel;

/**
 * @author Jordan
 */
public class DeleteLabelCommand extends Command {
    private static final String CreateCommand_Label = "DeleteLabelCommand"; //$NON-NLS-1$
    private Label label;
    EObject modelElement;
    private String oldStr;

    public boolean canExecute() {
        if (!(label instanceof Condition))
            return label != null && modelElement != null;
        else {
            boolean b = label != null && modelElement != null;
            if (label instanceof Condition && modelElement instanceof StartPoint)
                return b && ((StartPoint) modelElement).getPrecondition() != null;
            else if (label instanceof Condition && modelElement instanceof EndPoint)
                return b && ((EndPoint) modelElement).getPostcondition() != null;
            else if (label instanceof Condition && modelElement instanceof NodeConnection)
                return b && ((NodeConnection) modelElement).getCondition() != null;
            else
                return false;
        }

    }

    /*
     * (non-Javadoc)
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

    /*
     * (non-Javadoc)
     * 
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
        } else if (modelElement instanceof ComponentRef) {
            ComponentRef component = (ComponentRef) modelElement;
            component.setLabel(null);
        }

        testPostConditions();
    }

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
        } else if (modelElement instanceof ComponentRef) {
            ComponentRef component = (ComponentRef) modelElement;
            return label != null && component.getLabel() == null;
        } else if (modelElement instanceof NodeConnection) {
            NodeConnection nc = (NodeConnection) modelElement;
            return label != null && nc.getCondition() == null;
        }

        return true;
    }

    /*
     * (non-Javadoc)
     * 
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
        } else if (modelElement instanceof ComponentRef) {
            ComponentRef component = (ComponentRef) modelElement;
            component.setLabel((ComponentLabel) label);
        } else if (modelElement instanceof NodeConnection) {
            NodeConnection nc = (NodeConnection) modelElement;
            nc.setCondition((Condition) label);
        }

        testPreConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert label != null : "pre Label"; //$NON-NLS-1$
        assert modelElement != null : "pre UCMmodelElement"; //$NON-NLS-1$
        //assert label.getPathNode() != null : "pre NodeLabel not connected to a PathNode";
        //assert node.getLabel() != null : "pre PathNode not connected to a NodeLabel";
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert label != null : "pre Label"; //$NON-NLS-1$
        assert modelElement != null : "pre UCMmodelElement"; //$NON-NLS-1$
        //assert label.getPathNode().equals(node) : "pre NodeLabel connected to correct PathNode";
        //assert node.getLabel().equals(label) : "pre PathNode connected to correct NodeLabel";
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