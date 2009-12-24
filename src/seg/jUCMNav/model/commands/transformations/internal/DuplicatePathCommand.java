package seg.jUCMNav.model.commands.transformations.internal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.commands.delete.DeletePathNodeCommand;
import seg.jUCMNav.model.util.URNElementFinder;
import seg.jUCMNav.model.util.URNNamingHelper;
import seg.jUCMNav.model.util.modelexplore.GraphExplorer;
import seg.jUCMNav.model.util.modelexplore.queries.ReachableNodeFinder;
import seg.jUCMNav.model.util.modelexplore.queries.ReachableNodeFinder.QFindReachableNodes;
import ucm.map.Connect;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.UCMmap;
import urn.URNspec;
import urncore.Responsibility;
import urncore.URNmodelElement;

/**
 * This command duplicates a UCM path. It is not intended to be executed outside of the PasteCommand because it needs to have been passed a cloned model.
 * 
 * @author jkealey
 */
public class DuplicatePathCommand extends Command implements JUCMNavCommand {

    // we must recreate these plugin bindings after executing our command. Mapping of stubs from the initial model and their new equivalents.
    private HashMap bindingsThatMustBeCreatedAfterExecution;
    private HashMap duplicatedNodes, duplicatedNodeConnections;
    // if selective, will delete anything not specified in StartingPoints. otherwise, takes anything that can be reached from them.  
    private boolean isSelective = false;
    private UCMmap map; // The UCM diagram

    private int offsetX, offsetY; 

    // we must ensure that these responsibilities exist in the model before executing this command.
    private Vector respThatMustBeCreatedBeforeExecution;
    
    // the starting points for what should be copied. 
    private Vector startingPoints;
    
    // the target URN
    private URNspec urn; 

    // all the PathNodes that are copy pasted.
    private Vector vReachable;

    public DuplicatePathCommand(UCMmap map, PathNode start, int offsetX, int offsetY) {
        this.setMap(map);

        if (start == null)
            throw new IllegalArgumentException();
        startingPoints = new Vector();
        startingPoints.add(start);

        this.offsetX = offsetX;
        this.offsetY = offsetY;

        isSelective = false;
        init();
    }

    public DuplicatePathCommand(UCMmap map, Vector startingPoints, int offsetX, int offsetY) {
        this.setMap(map);
        if (startingPoints == null || startingPoints.size() == 0)
            throw new IllegalArgumentException();
        this.startingPoints = startingPoints;

        this.offsetX = offsetX;
        this.offsetY = offsetY;

        isSelective = true;
        init();
    }
    
    public DuplicatePathCommand(URNspec urn, UCMmap map, Vector startingPoints, int offsetX, int offsetY) {
        this.urn = urn;
        this.setMap(map);
        if (startingPoints == null || startingPoints.size() == 0)
            throw new IllegalArgumentException();
        this.startingPoints = startingPoints;

        this.offsetX = offsetX;
        this.offsetY = offsetY;

        isSelective = true;
        init();
    }    

    /**
     * We tried using cloning for this method, but were unsucessful. There is some code in here that is useful when in a cloned model that is left in case we ever manage to finish off the job.
     * 
     *  You want cloning to be able to run this command directly. Right now, it is modifying the source model before pasting elements and is only good when inside the Paste Command.  
     */
    private void build() {
        
        UCMmap targetMap = prepareMap();

        findAllReachableNodes(targetMap);

        duplicateElements();
    }

    /**
     * Given a node connection, clone it and prepare it to be added to the new model. 
     * @param nc the old node connection
     */
    private void cloneNodeConnection(NodeConnection nc) {
        NodeConnection clonedNc;

        if (duplicatedNodeConnections.containsKey(nc))
            // clonedNc = (NodeConnection) duplicatedNodeConnections.get(nc);
            return; // already done.
        else {
            clonedNc = (NodeConnection) EcoreUtil.copy(nc);
            duplicatedNodeConnections.put(nc, clonedNc);
        }

        clonedNc.setSource((PathNode) getDuplicatedNodes().get(nc.getSource()));
        clonedNc.setTarget((PathNode) getDuplicatedNodes().get(nc.getTarget()));
        // would cause refresh issues
        // getMap().getConnections().add(clonedNc);

    }

    /**
     * Given a path node, clone it and prepare it to be added to the model. 
     * @param pn
     */
    private void clonePathNode(PathNode pn) {

        if (getDuplicatedNodes().containsKey(pn))
            return;

        PathNode clonedPn = (PathNode) EcoreUtil.copy(pn);
        resetCloneId(clonedPn);
        clonedPn.setX(clonedPn.getX() + offsetX);
        clonedPn.setY(clonedPn.getY() + offsetY);

        // would cause refresh issues.
        // getMap().getNodes().add(clonedPn);

        getDuplicatedNodes().put(pn, clonedPn);
        
        if (pn instanceof RespRef) {
            // the caller will need to add these resps in the target model before executing this command inside a compoundcommand. 
            if (!getResponsibilitiesThatMustBeCreatedBeforeExecution().contains(((RespRef) pn).getRespDef()))
                getResponsibilitiesThatMustBeCreatedBeforeExecution().add(((RespRef) pn).getRespDef());
        } else if (pn instanceof Stub) {
            // clean invalid relationships, if they are there. 
            ((Stub) clonedPn).getBindings().clear();

            getBindingsThatMustBeCreatedAfterExecution().put(pn, clonedPn);
        }
    }

    /**
     * Go through the list of reachable path nodes and duplicate them and their associated connections. 
     */
    private void duplicateElements() {
        setDuplicatedNodes(new HashMap());
        duplicatedNodeConnections = new HashMap();
        respThatMustBeCreatedBeforeExecution = new Vector();
        bindingsThatMustBeCreatedAfterExecution = new HashMap();

        for (Iterator iterator = vReachable.iterator(); iterator.hasNext();) {
            PathNode pn = (PathNode) iterator.next();

            clonePathNode(pn);
        }

        for (Iterator iterator = vReachable.iterator(); iterator.hasNext();) {
            PathNode pn = (PathNode) iterator.next();

            for (Iterator iterator2 = pn.getPred().iterator(); iterator2.hasNext();) {
                NodeConnection nc = (NodeConnection) iterator2.next();
                cloneNodeConnection(nc);
            }
            for (Iterator iterator2 = pn.getSucc().iterator(); iterator2.hasNext();) {
                NodeConnection nc = (NodeConnection) iterator2.next();
                cloneNodeConnection(nc);
            }
        }
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        redo();
    }

    /**
     * Cycle through the list of starting points to build the full list of reachable nodes. 
     * @param targetMap
     */
    private void findAllReachableNodes(UCMmap targetMap) {
        // find all nodes that are to be pasted.
        vReachable = new Vector();
        for (Iterator iterator = getStartingPoints().iterator(); iterator.hasNext();) {
            PathNode startingPoint = (PathNode) iterator.next();

            PathNode sp = null; // find the sp in the cloned model by using its id. 
            for (Iterator iterator2 = targetMap.getNodes().iterator(); iterator2.hasNext();) {
                PathNode pn = (PathNode) iterator2.next();
                if (pn instanceof PathNode && (pn.getId().equals(startingPoint.getId()))) {
                    sp = (PathNode) pn;
                    break;
                }
            }

            // find everything this element can reach. 
            HashSet exclusions = new HashSet();
            QFindReachableNodes qReachableNodes = new ReachableNodeFinder.QFindReachableNodes(sp, exclusions, QFindReachableNodes.DIRECTION_BOTH, true);
            ReachableNodeFinder.RReachableNodes rReachableNodes = (ReachableNodeFinder.RReachableNodes) GraphExplorer.run(qReachableNodes);
            Vector locallyReachable = rReachableNodes.getNodes();

            // append that to our global list of nodes that need to be duplicated. 
            for (Iterator iterator2 = locallyReachable.iterator(); iterator2.hasNext();) {
                PathNode pn = (PathNode) iterator2.next();
                if (!vReachable.contains(pn))
                    vReachable.add(pn);
            }
        }
    }

    public HashMap getBindingsThatMustBeCreatedAfterExecution() {
        return bindingsThatMustBeCreatedAfterExecution;
    }

    public Vector getImpactedNodes() {
        return vReachable;
    }

    public UCMmap getMap() {
        return map;
    }

    public Vector getResponsibilitiesThatMustBeCreatedBeforeExecution() {
        return respThatMustBeCreatedBeforeExecution;
    }

    public Vector getStartingPoints() {
        return startingPoints;
    }

    /**
     * Do init before execute because our command stacks need to call getNewDiagram() before it is executed.
     * 
     */
    private void init() {
        setLabel(Messages.getString("DuplicatePathCommand_DuplicatePath")); //$NON-NLS-1$

        if (urn == null && getMap() != null && getMap().getUrndefinition()!=null)
            urn = getMap().getUrndefinition().getUrnspec();

        if (urn == null)
            return;

        build();
    }

    private UCMmap prepareMap() {
        // we cannot clone here - will lose pointers to resp def, etc. needs to be done at URNspec level (in PasteCommand)
        // UCMmap clone = (UCMmap) EcoreUtil.copy(((PathNode) getStartingPoints().get(0)).getDiagram());
        UCMmap clone = (UCMmap) ((PathNode) getStartingPoints().get(0)).getDiagram();

        Vector toDelete = new Vector();
        // clean up the model
        for (Iterator iterator = clone.getNodes().iterator(); iterator.hasNext();) {
            PathNode pn = (PathNode) iterator.next();
            /*
             * if we clone the model above, we need to get rid of bindings
             * 
            if (pn instanceof Stub) {
                ((Stub) pn).getBindings().clear(); // inconsistent state aften a clone. 
            }
            */

            // if selective, we need to mark certain pathnodes for deletion.
            if (isSelective) {
                if (pn instanceof StartPoint || pn instanceof EndPoint || pn instanceof Connect) {
                    // don't delete start/end for now.
                } else {
                    boolean found = false;
                    for (Iterator iterator2 = startingPoints.iterator(); iterator2.hasNext();) {
                        PathNode pn2 = (PathNode) iterator2.next();
                        if (pn2.getId() == pn.getId()) {
                            found = true;
                            break;
                        }
                    }

                    if (!found)
                        toDelete.add(pn);
                }
            }
        }

        // manipulate to get rid of elements.
        if (toDelete != null) {
            for (Iterator iterator = toDelete.iterator(); iterator.hasNext();) {
                PathNode pn = (PathNode) iterator.next();
                DeletePathNodeCommand cmd = new DeletePathNodeCommand(pn, null);
                cmd.execute();
            }
        }
        return clone;
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        if (urn == null || getDuplicatedNodes() == null || duplicatedNodeConnections == null)
            return;
        testPreConditions();

        if (getMap() != null) {

            // refresh issues.
            // getMap().getNodes().addAll(duplicatedNodes.values());
            // getMap().getConnections().addAll(duplicatedNodeConnections.values());

            for (Iterator iterator = duplicatedNodeConnections.values().iterator(); iterator.hasNext();) {
                NodeConnection nc = (NodeConnection) iterator.next();
                nc.setDiagram(getMap());
            }
            for (Iterator iterator = getDuplicatedNodes().values().iterator(); iterator.hasNext();) {
                PathNode pn = (PathNode) iterator.next();
                pn.setDiagram(getMap());
            }

            for (Iterator iterator = vReachable.iterator(); iterator.hasNext();) {
                PathNode src = (PathNode) iterator.next();
                PathNode dest = (PathNode) getDuplicatedNodes().get(src);

                if (src instanceof RespRef && dest instanceof RespRef) {
                    RespRef srcref = (RespRef) src;
                    RespRef destref = (RespRef) dest;

                    destref.setRespDef(URNElementFinder.findResponsibilityByName(urn, srcref.getRespDef().getName()));
                }
            }
        }

        testPostConditions();
    }

    private void resetCloneId(URNmodelElement clone) {
        String name = clone.getName();
        clone.setId(""); //$NON-NLS-1$
        URNNamingHelper.setElementNameAndID(urn, clone);
        clone.setName(name);
    }

    public void setMap(UCMmap map) {
        this.map = map;
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert urn != null;

        assert startingPoints != null  && startingPoints.size()>0  : "post nothing to duplicate"; //$NON-NLS-1$
        assert getDuplicatedNodes() != null  && duplicatedNodeConnections!=null : "post problem with build"; //$NON-NLS-1$
        assert bindingsThatMustBeCreatedAfterExecution!=null && respThatMustBeCreatedBeforeExecution!=null : "post problem with external objects"; //$NON-NLS-1$ 

        for (Iterator iterator = duplicatedNodeConnections.values().iterator(); iterator.hasNext();) {
            NodeConnection nc = (NodeConnection) iterator.next();
            assert nc.getDiagram() == getMap() : "post nc not in model"; //$NON-NLS-1$
        }
        for (Iterator iterator = getDuplicatedNodes().values().iterator(); iterator.hasNext();) {
            PathNode pn = (PathNode) iterator.next();
            assert pn.getDiagram() == getMap() : "post pn not in model"; //$NON-NLS-1$
        }
    }

    /**
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {

        assert urn != null;

        assert startingPoints != null  && startingPoints.size()>0  : "pre nothing to duplicate"; //$NON-NLS-1$
        assert getDuplicatedNodes() != null  && duplicatedNodeConnections!=null : "pre problem with build"; //$NON-NLS-1$
        assert bindingsThatMustBeCreatedAfterExecution!=null && respThatMustBeCreatedBeforeExecution!=null : "pre problem with external objects"; //$NON-NLS-1$ 

        for (Iterator iterator = duplicatedNodeConnections.values().iterator(); iterator.hasNext();) {
            NodeConnection nc = (NodeConnection) iterator.next();
            assert nc.getDiagram() == null : "pre nc in model"; //$NON-NLS-1$
        }
        for (Iterator iterator = getDuplicatedNodes().values().iterator(); iterator.hasNext();) {
            PathNode pn = (PathNode) iterator.next();
            assert pn.getDiagram() == null : "pre pn in model"; //$NON-NLS-1$
        }
        
        for (Iterator iterator = respThatMustBeCreatedBeforeExecution.iterator(); iterator.hasNext();) {
            Responsibility resp = (Responsibility) iterator.next();
            assert URNElementFinder.findResponsibilityByName(urn, resp.getName())!=null : "pre resp is missing in model" + resp.getName();     //$NON-NLS-1$
        }
        
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        if (urn == null || getDuplicatedNodes() == null || duplicatedNodeConnections == null)
            return;
        testPostConditions();

        if (getMap() != null) {
            // refresh issues.
            // getMap().getNodes().removeAll(duplicatedNodes.values());
            // getMap().getConnections().removeAll(duplicatedNodeConnections.values());
            for (Iterator iterator = getDuplicatedNodes().values().iterator(); iterator.hasNext();) {
                PathNode pn = (PathNode) iterator.next();
                pn.setDiagram(null);

                if (pn instanceof RespRef) {
                    ((RespRef) pn).setRespDef(null);
                }
            }

            for (Iterator iterator = duplicatedNodeConnections.values().iterator(); iterator.hasNext();) {
                NodeConnection nc = (NodeConnection) iterator.next();
                nc.setDiagram(null);
            }
        }

        testPreConditions();
    }

    public void setDuplicatedNodes(HashMap duplicatedNodes) {
        this.duplicatedNodes = duplicatedNodes;
    }

    public HashMap getDuplicatedNodes() {
        return duplicatedNodes;
    }
}