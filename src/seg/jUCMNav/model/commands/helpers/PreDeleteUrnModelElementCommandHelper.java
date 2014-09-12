package seg.jUCMNav.model.commands.helpers;

import org.eclipse.emf.common.command.CompoundCommand;

import grl.ActorRef;
import grl.GRLNode;
import grl.kpimodel.KPIConversion;
import seg.jUCMNav.model.commands.delete.DisconnectCommand;
import seg.jUCMNav.model.commands.delete.internal.CleanRelationshipsCommand;
import seg.jUCMNav.model.commands.delete.internal.DisconnectGRLNodeCommand;
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

public class PreDeleteUrnModelElementCommandHelper extends CompoundCommand {

//    /**
//     * 
//     * @param map
//     *            the Map to be deleted.
//     */
//    public PreDeleteUrnModelElementCommandHelper(UCMmap map) {
//        append(new CleanRelationshipsCommand(map));
//    }
//
//    /**
//     * 
//     * @param var
//     *            the Variable to be deleted.
//     */
//    public PreDeleteUrnModelElementCommandHelper(Variable var) {
//        append(new CleanRelationshipsCommand(var));
//    }
//    
//    /**
//     * 
//     * @param conv
//     *            the KPIConversion to be deleted.
//     */
//    public PreDeleteUrnModelElementCommandHelper(KPIConversion conv) {
//        append(new CleanRelationshipsCommand(conv));
//    }
//
//    /**
//     * 
//     * @param et
//     *            the EnumerationType to be deleted.
//     */
//    public PreDeleteUrnModelElementCommandHelper(EnumerationType et) {
//        append(new CleanRelationshipsCommand(et));
//    }
//
//    /**
//     * 
//     * @param init
//     *            the Variable Initialization to be deleted.
//     */
//    public PreDeleteUrnModelElementCommandHelper(Initialization init) {
//        append(new CleanRelationshipsCommand(init));
//    }
//
//    /**
//     * 
//     * @param pt
//     *            the ScenarioStartPoint to be deleted.
//     */
//    public PreDeleteUrnModelElementCommandHelper(ScenarioStartPoint pt) {
//        append(new CleanRelationshipsCommand(pt));
//    }
//
//    /**
//     * 
//     * @param pt
//     *            the ScenarioEndPoint to be deleted.
//     */
//    public PreDeleteUrnModelElementCommandHelper(ScenarioEndPoint pt) {
//        append(new CleanRelationshipsCommand(pt));
//    }
//
//    /**
//     * 
//     * @param nc
//     *            the NodeConnection to be deleted.
//     */
//    public PreDeleteUrnModelElementCommandHelper(NodeConnection nc) {
//        append(new CleanRelationshipsCommand(nc));
//    }
//
//    /**
//     * 
//     * @param pn
//     *            the PathNode to be deleted.
//     */
//    public PreDeleteUrnModelElementCommandHelper(PathNode pn) {
//
//        DisconnectCommand cmd = new DisconnectCommand(pn);
//        if (cmd.canExecute())
//            add(cmd);
//        append(new CleanRelationshipsCommand(pn));
//
//    }
//
//    /**
//     * 
//     * @param cr
//     *            the ComponentRef to be deleted.
//     */
//    public PreDeleteUrnModelElementCommandHelper(ComponentRef cr) {
//
//        append(new CleanRelationshipsCommand(cr));
//    }
//
//    /**
//     * 
//     * @param ar
//     *            the ActorRef to be deleted.
//     */
//    public PreDeleteUrnModelElementCommandHelper(ActorRef ar) {
//
//        append(new CleanRelationshipsCommand(ar));
//    }
//
    /**
     * @param ref
     *            the GRLNode to be deleted.
     */
    public PreDeleteUrnModelElementCommandHelper(GRLNode ref) {

        append(new CleanRelationshipsCommandHelper(ref));
        append(new DisconnectGRLNodeCommandHelper(ref));

    }

//    /**
//     * 
//     * @param resx
//     *            the GeneralResource to be deleted.
//     */
//    public PreDeleteUrnModelElementCommandHelper(GeneralResource resx) {
//
//        append(new CleanRelationshipsCommand(resx));
//    }
//    
    
}
