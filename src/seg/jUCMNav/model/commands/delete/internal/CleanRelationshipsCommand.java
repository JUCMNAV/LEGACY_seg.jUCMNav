package seg.jUCMNav.model.commands.delete.internal;

import grl.ActorRef;
import grl.GRLGraph;
import grl.GRLNode;
import grl.IntentionalElementRef;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.changeConstraints.ContainerRefUnbindChildCommand;
import seg.jUCMNav.model.commands.delete.DeleteBindingsCommand;
import ucm.map.ComponentRef;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.UCMmap;

/**
 * Removes all invisible relationships related to a Connection, Node or ContainerRef, .
 * 
 * Performance information, Strategy information, Plugin-Binding information, ContainerRef Binding information.
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
    public CleanRelationshipsCommand(UCMmap map) {
        this.element = map;
    }

    /**
     * 
     * @param map
     *            the Map to be cleaned
     */
    private void build(UCMmap map) {
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
        if (pn.getContRef() != null)
            add(new ContainerRefUnbindChildCommand((ComponentRef)pn.getContRef(), pn));
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
            add(new ContainerRefUnbindChildCommand(cr, cr.getChildren()));

        if (cr.getNodes().size() > 0)
            add(new ContainerRefUnbindChildCommand(cr, cr.getNodes()));

        if (cr.getParent() != null)
            add(new ContainerRefUnbindChildCommand((ComponentRef)cr.getParent(), cr));
    }

    /**
     * 
     * @param graph
     *            the graph to be cleaned.
     */
    public CleanRelationshipsCommand(GRLGraph graph) {
        this.element = graph;
    }

    /**
     * 
     * @param map
     *            the Map to be cleaned
     */
    private void build(GRLGraph graph) {
        add(new RemoveLinkedInfoCommand(graph));
    }
    
    /**
     * 
     * @param cr
     *            the actor to be cleaned.
     */
    public CleanRelationshipsCommand(ActorRef cr) {
        this.element = cr;

    }

    /**
     * 
     * @param cr
     *            the actor to be cleaned.
     */
    private void build(ActorRef cr) {
        if (cr.getChildren().size() > 0)
            add(new ContainerRefUnbindChildCommand(cr, cr.getChildren()));

        if (cr.getNodes().size() > 0)
            add(new ContainerRefUnbindChildCommand(cr, cr.getNodes()));

        if (cr.getParent() != null)
            add(new ContainerRefUnbindChildCommand((ComponentRef)cr.getParent(), cr));
    }

    /**
     * 
     * @param ref
     *            the GRLNode to be cleaned.
     */
    public CleanRelationshipsCommand(GRLNode ref) {
        this.element = ref;

    }

    /**
     * 
     * @param ref
     *            the GRLNode to be cleaned.
     */
    private void build(GRLNode ref) {
        if (ref.getContRef() != null){
            add(new ContainerRefUnbindChildCommand((ActorRef)ref.getContRef(), ref));
        }
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
        if (element instanceof UCMmap)
            build((UCMmap)element);
        else if (element instanceof ComponentRef)
            build((ComponentRef)element);
        else if (element instanceof ActorRef)
            build((ActorRef)element);
        else if (element instanceof NodeConnection)
            build((NodeConnection)element);
        else if (element instanceof PathNode)
            build((PathNode)element);
        else if (element instanceof IntentionalElementRef)
            build((IntentionalElementRef)element);
    }

}
