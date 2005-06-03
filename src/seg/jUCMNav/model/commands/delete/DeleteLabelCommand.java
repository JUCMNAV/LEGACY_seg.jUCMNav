/*
 * Created on May 3, 2005
 */
package seg.jUCMNav.model.commands.delete;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;

import ucm.map.ComponentRef;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import urncore.ComponentLabel;
import urncore.Condition;
import urncore.Label;
import urncore.NodeLabel;

/**
 * @author Jordan
 */
public class DeleteLabelCommand extends Command {
    private static final String CreateCommand_Label = "DeleteLabelCommand";
    private Label label;
    EObject modelElement;

    public boolean canExecute() {
        return label != null && modelElement != null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        redo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        if (modelElement instanceof PathNode) {
            PathNode node = (PathNode) modelElement;
            node.setLabel(null);
        } else if (modelElement instanceof ComponentRef) {
            ComponentRef component = (ComponentRef) modelElement;
            component.setLabel(null);
        } else if (modelElement instanceof NodeConnection) {
            NodeConnection nc = (NodeConnection) modelElement;
            nc.setCondition(null);
        }

        testPostConditions();
    }

    public boolean canUndo() {
        if (modelElement instanceof PathNode) {
            PathNode node = (PathNode) modelElement;
            return label != null && node.getLabel() == null;
        } else if (modelElement instanceof ComponentRef) {
            ComponentRef component = (ComponentRef) modelElement;
            return label != null && component.getLabel() == null;
        } else if (modelElement instanceof NodeConnection) {
            NodeConnection nc = (NodeConnection) modelElement;
            return label != null && nc.getCondition() == null;
        }

        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        if (modelElement instanceof PathNode) {
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
        assert label != null : "pre Label";
        assert modelElement != null : "pre UCMmodelElement";
        //assert label.getPathNode() != null : "pre NodeLabel not connected to a PathNode";
        //assert node.getLabel() != null : "pre PathNode not connected to a NodeLabel";
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert label != null : "pre Label";
        assert modelElement != null : "pre UCMmodelElement";
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