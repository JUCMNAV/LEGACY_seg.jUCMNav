package seg.jUCMNav.model.commands.delete.internal;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.ComponentRef;
import ucm.map.Map;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import urncore.Condition;

/**
 * Given a ComponentRef, PathNode, NodeConnection or Map, remove its performance/scenario information.
 * 
 * Currently does nothing for performance because we have not implemented the relationship between UCMspec and performance annotations.
 * 
 * Currently does nothing for PathNode conditions because we have not implemented the relationship between UCMspec and its variables/scenarios.
 * 
 * All that it currently does is remove Conditions from NodeConnections because we need to move the conditions to NodeConnections that directly follow a
 * Timer/WaitingPlace/OrFork.
 * 
 * This command does not clean plugin binding relationships.
 * 
 * @author jkealey
 * 
 */
public class RemoveLinkedInfoCommand extends Command implements JUCMNavCommand {

    private EObject element;

    private Condition condition;

    /**
     * 
     * @param pn
     *            the PathNode to be cleaned.
     */
    public RemoveLinkedInfoCommand(PathNode pn) {
        this.element = pn;
    }

    /**
     * 
     * @param cr
     *            the ComponentRef to be cleaned.
     */
    public RemoveLinkedInfoCommand(ComponentRef cr) {
        this.element = cr;
    }

    /**
     * 
     * @param nc
     *            the NodeConnection to be cleaned.
     */
    public RemoveLinkedInfoCommand(NodeConnection nc) {
        this.element = nc;
    }

    /**
     * 
     * @param map
     *            the Map to be cleaned.
     */
    public RemoveLinkedInfoCommand(Map map) {
        this.element = map;
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        if (element instanceof NodeConnection)
            this.condition = ((NodeConnection) element).getCondition();
        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        if (element instanceof NodeConnection)
            ((NodeConnection) element).setCondition(null);

        testPostConditions();

    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        if (element instanceof NodeConnection)
            ((NodeConnection) element).setCondition(condition);

        testPreConditions();
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert element != null : "pre something is null";
        assert (element instanceof NodeConnection || element instanceof ComponentRef || element instanceof PathNode || element instanceof Map) : "pre invalid class";
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert element != null : "post something is null";
        if (condition != null && element instanceof NodeConnection)
            assert ((NodeConnection) element).getCondition() == null : "post condition not removed";
    }

}
