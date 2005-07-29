package seg.jUCMNav.model.commands.delete.internal;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.changeConstraints.ComponentRefUnbindChildCommand;
import seg.jUCMNav.model.commands.delete.DeleteBindingsCommand;
import ucm.map.ComponentRef;
import ucm.map.Map;
import ucm.map.NodeConnection;
import ucm.map.PathNode;

/**
 * Removes all invisible relationships related to a NodeConnection, PathNode or ComponentRef.
 * 
 * Performance information, Scenario information, Plugin-Binding information, ComponentRef Binding information.
 * 
 * @author jkealey
 * 
 */
public class CleanRelationshipsCommand extends CompoundCommand {

    private EObject element;

    /**
     * 
     * @param map
     *            the Map to be cleaned.
     */
    public CleanRelationshipsCommand(Map map) {
        this.element = map;
    }

    /**
     * 
     * @param map
     *            the Map to be cleaned
     */
    private void build(Map map) {
        add(new RemoveLinkedInfoCommand(map));
        add(new DeleteBindingsCommand(map));
    }

    /**
     * 
     * @param nc
     *            the NodeConnection to be cleaned.
     */
    public CleanRelationshipsCommand(NodeConnection nc) {
        this.element = nc;
    }

    /**
     * 
     * @param nc
     *            the NodeConnection to be cleaned.
     */
    private void build(NodeConnection nc) {
        add(new RemoveLinkedInfoCommand(nc));
        add(new DeleteBindingsCommand(nc));
    }

    /**
     * 
     * @param pn
     *            the PathNode to be cleaned.
     */
    public CleanRelationshipsCommand(PathNode pn) {
        this.element = pn;
    }

    /**
     * 
     * @param pn
     *            the PathNode to be cleaned.
     */
    private void build(PathNode pn) {
        add(new RemoveLinkedInfoCommand(pn));

        // get rid of conditions on outgoing connections
        for (Iterator iter = pn.getSucc().iterator(); iter.hasNext();) {
            NodeConnection element = (NodeConnection) iter.next();
            add(new RemoveLinkedInfoCommand(element));
        }
        if (pn.getCompRef() != null)
            add(new ComponentRefUnbindChildCommand(pn.getCompRef(), pn));
        add(new DeleteBindingsCommand(pn));
    }

    /**
     * 
     * @param cr
     *            the ComponentRef to be cleaned.
     */
    public CleanRelationshipsCommand(ComponentRef cr) {
        this.element = cr;

    }

    /**
     * 
     * @param cr
     *            the ComponentRef to be cleaned.
     */
    private void build(ComponentRef cr) {
        add(new RemoveLinkedInfoCommand(cr));
        if (cr.getChildren().size() > 0)
            add(new ComponentRefUnbindChildCommand(cr, cr.getChildren()));

        if (cr.getPathNodes().size() > 0)
            add(new ComponentRefUnbindChildCommand(cr, cr.getPathNodes()));

        if (cr.getParent() != null)
            add(new ComponentRefUnbindChildCommand(cr.getParent(), cr));
    }

    /**
     * Returns true even if no commands exist.
     */
    public boolean canExecute() {
        if (getCommands().size() == 0)
            return true;
        else
            return super.canExecute();
    }

    /**
     * Returns true even if no commands exist.
     */
    public boolean canUndo() {
        if (getCommands().size() == 0)
            return true;
        else
            return super.canUndo();
    }

    /**
     * Builds command as late as possible.
     */
    public void execute() {
        build();
        super.execute();
    }

    /**
     * redirects to the appropriate build method.
     */
    private void build() {
        if (element instanceof Map)
            build((Map)element);
        else if (element instanceof ComponentRef)
            build((ComponentRef)element);
        else if (element instanceof NodeConnection)
            build((NodeConnection)element);
        else if (element instanceof PathNode)
            build((PathNode)element);
    }

}
