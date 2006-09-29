package seg.jUCMNav.model.commands.delete.internal;

import grl.ActorRef;
import grl.GRLNode;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.delete.DisconnectCommand;
import ucm.map.ComponentRef;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.UCMmap;
import ucm.scenario.Initialization;
import ucm.scenario.ScenarioEndPoint;
import ucm.scenario.ScenarioStartPoint;
import ucm.scenario.Variable;

/**
 * This command prepares an element for deletion. It gets rid of synch/asynch connections and invisible relationships.
 * 
 * This is a wrapper around CleanRelationshipsCommand which only adds DisconnectCommands to PathNodes but might have more future use.
 * 
 * @author jkealey
 * 
 */
public class PreDeleteUrnModelElementCommand extends CompoundCommand {
    private EObject element;

    /**
     * 
     * @param map
     *            the Map to be deleted.
     */
    public PreDeleteUrnModelElementCommand(UCMmap map) {
        this.element = map;
        add(new CleanRelationshipsCommand(map));
    }

    /**
     * 
     * @param var
     *            the Variable to be deleted.
     */
    public PreDeleteUrnModelElementCommand(Variable var) {
        this.element = var;
        add(new CleanRelationshipsCommand(var));
    }
    
    /**
     * 
     * @param init
     *            the Variable Initialization to be deleted.
     */
    public PreDeleteUrnModelElementCommand(Initialization init) {
        this.element = init;
        add(new CleanRelationshipsCommand(init));
    }    
    /**
     * 
     * @param pt
     *            the ScenarioStartPoint to be deleted.
     */
    public PreDeleteUrnModelElementCommand(ScenarioStartPoint pt) {
        this.element = pt;
        add(new CleanRelationshipsCommand(pt));
    }
    
    
    /**
     * 
     * @param pt
     *            the ScenarioEndPoint to be deleted.
     */
    public PreDeleteUrnModelElementCommand(ScenarioEndPoint pt) {
        this.element = pt;
        add(new CleanRelationshipsCommand(pt));
    }    
    
    /**
     * 
     * @param nc
     *            the NodeConnection to be deleted.
     */
    public PreDeleteUrnModelElementCommand(NodeConnection nc) {
        this.element = nc;
        add(new CleanRelationshipsCommand(nc));
    }

    /**
     * 
     * @param pn
     *            the PathNode to be deleted.
     */
    public PreDeleteUrnModelElementCommand(PathNode pn) {
        this.element = pn;
        DisconnectCommand cmd = new DisconnectCommand(pn);
        if (cmd.canExecute())
            add(cmd);
        add(new CleanRelationshipsCommand(pn));

    }

    /**
     * 
     * @param cr
     *            the ComponentRef to be deleted.
     */
    public PreDeleteUrnModelElementCommand(ComponentRef cr) {
        this.element = cr;
        add(new CleanRelationshipsCommand(cr));
    }
    
    /**
     * 
     * @param ar
     *            the ActorRef to be deleted.
     */
    public PreDeleteUrnModelElementCommand(ActorRef ar) {
        this.element = ar;
        add(new CleanRelationshipsCommand(ar));
    }
    
    /**
     * @param ref
     *            the GRLNode to be deleted.
     */
    public PreDeleteUrnModelElementCommand(GRLNode ref) {
        this.element = ref;
        add(new CleanRelationshipsCommand(ref));
        add(new DisconnectGRLNodeCommand(ref));

    }
}
