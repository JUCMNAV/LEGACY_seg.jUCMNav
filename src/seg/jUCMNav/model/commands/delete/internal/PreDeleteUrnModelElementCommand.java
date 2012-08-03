package seg.jUCMNav.model.commands.delete.internal;

import grl.ActorRef;
import grl.GRLNode;
import grl.kpimodel.KPIConversion;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.delete.DisconnectCommand;
import ucm.map.ComponentRef;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.UCMmap;
import ucm.performance.GeneralResource;
import ucm.scenario.EnumerationType;
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

    /**
     * 
     * @param map
     *            the Map to be deleted.
     */
    public PreDeleteUrnModelElementCommand(UCMmap map) {
        add(new CleanRelationshipsCommand(map));
    }

    /**
     * 
     * @param var
     *            the Variable to be deleted.
     */
    public PreDeleteUrnModelElementCommand(Variable var) {
        add(new CleanRelationshipsCommand(var));
    }
    
    /**
     * 
     * @param conv
     *            the KPIConversion to be deleted.
     */
    public PreDeleteUrnModelElementCommand(KPIConversion conv) {
        add(new CleanRelationshipsCommand(conv));
    }

    /**
     * 
     * @param et
     *            the EnumerationType to be deleted.
     */
    public PreDeleteUrnModelElementCommand(EnumerationType et) {
        add(new CleanRelationshipsCommand(et));
    }

    /**
     * 
     * @param init
     *            the Variable Initialization to be deleted.
     */
    public PreDeleteUrnModelElementCommand(Initialization init) {
        add(new CleanRelationshipsCommand(init));
    }

    /**
     * 
     * @param pt
     *            the ScenarioStartPoint to be deleted.
     */
    public PreDeleteUrnModelElementCommand(ScenarioStartPoint pt) {
        add(new CleanRelationshipsCommand(pt));
    }

    /**
     * 
     * @param pt
     *            the ScenarioEndPoint to be deleted.
     */
    public PreDeleteUrnModelElementCommand(ScenarioEndPoint pt) {
        add(new CleanRelationshipsCommand(pt));
    }

    /**
     * 
     * @param nc
     *            the NodeConnection to be deleted.
     */
    public PreDeleteUrnModelElementCommand(NodeConnection nc) {
        add(new CleanRelationshipsCommand(nc));
    }

    /**
     * 
     * @param pn
     *            the PathNode to be deleted.
     */
    public PreDeleteUrnModelElementCommand(PathNode pn) {

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

        add(new CleanRelationshipsCommand(cr));
    }

    /**
     * 
     * @param ar
     *            the ActorRef to be deleted.
     */
    public PreDeleteUrnModelElementCommand(ActorRef ar) {

        add(new CleanRelationshipsCommand(ar));
    }

    /**
     * @param ref
     *            the GRLNode to be deleted.
     */
    public PreDeleteUrnModelElementCommand(GRLNode ref) {

        add(new CleanRelationshipsCommand(ref));
        add(new DisconnectGRLNodeCommand(ref));

    }

    /**
     * 
     * @param resx
     *            the GeneralResource to be deleted.
     */
    public PreDeleteUrnModelElementCommand(GeneralResource resx) {

        add(new CleanRelationshipsCommand(resx));
    }
}
