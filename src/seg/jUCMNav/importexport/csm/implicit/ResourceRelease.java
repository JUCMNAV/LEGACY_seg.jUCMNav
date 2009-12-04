package seg.jUCMNav.importexport.csm.implicit;

import java.io.PrintStream;
import java.util.Stack;

import seg.jUCMNav.importexport.csm.duplicate.CSMDupConnection;
import seg.jUCMNav.importexport.csm.duplicate.CSMDupConnectionList;
import seg.jUCMNav.importexport.csm.duplicate.CSMDupNode;
import seg.jUCMNav.importexport.csm.duplicate.CSMDupNodeList;
import ucm.map.ComponentRef;
import ucm.map.PathNode;

/**
 * Inserts Resource Release objects in duplicate map.
 * 
 */
public class ResourceRelease {

    // RR and Empty Point IDs
    static int rr_id = 3000; // TODO: wave limitation.

    static int seq_id = 4000; // TODO: wave limitation.

    PrintStream ps;

    // constructor
    public ResourceRelease(PrintStream ps) {
        this.ps = ps;
    }

    // adds all hyperedge parent components to stack
    public void findParentsRR(ComponentRef compRef, Stack edge_stack) {
        // The top of the stack is the outermost component
        ComponentRef parent_compRef = (ComponentRef) compRef.getParent();
        if (parent_compRef != null) {
            findParentsRR(parent_compRef, edge_stack);
            edge_stack.push(parent_compRef);
        }
    }

    /**
     * Compute the resources to be released
     * 
     * @param curr_edge
     *            node associated with the resource release
     * @param dup_map
     * @param dup_map_conn
     * @return the number of newly created nodes
     */
    public int releaseResource(PathNode curr_edge, CSMDupNodeList dup_map, CSMDupConnectionList dup_map_conn) {
        // list that will store edges to be parsed (will contain pathnodes only)
        int nodes_inserted = 0; // total nodes inserted since last run
        // Compute resources to be released:
        CSMResourceSet usedResources = null; // requested resources +
        // containing components
        CSMResourceSet resToRelease = null; // usedResources - resNeededNext
        CSMDupNode curr_edge_dupNode = dup_map.get(dup_map.getNodeIndex(curr_edge));
        CSMDupNode nextDupNode = null;
        // EndPoint releases all containing components (minus those previously
        // released)
        if ((curr_edge_dupNode.getType() == CSMDupNode.RESPREF) || (curr_edge_dupNode.getType() == CSMDupNode.STUB)) {
            if (curr_edge_dupNode.getResourcesDownstream() != null) {
                usedResources = curr_edge_dupNode.getResourcesDownstream().toRelease();
            } else {
                usedResources = null;
            }
            nextDupNode = dup_map_conn.getTargetForSource(curr_edge);
            if ((nextDupNode != null) && (nextDupNode.getResourcesUpstream() != null)) {
                resToRelease = usedResources.minus(nextDupNode.getResourcesUpstream().toAcquire());
            } else {
                resToRelease = usedResources;
            }
        }
        while ((resToRelease != null) && (resToRelease.size() != 0)) {
            nodes_inserted = addRR(resToRelease, usedResources, dup_map, dup_map_conn, curr_edge, nodes_inserted);
        }
        return nodes_inserted;
    } // function

    // prints XML representation of Resource Release element
    public void releaseRes(CSMResource resource, CSMDupNode node, CSMDupConnectionList list) {

        // initializing attributes
        String successor = list.getTargetForSource(node.getId());
        String predecessor = list.getSourceForTarget(node.getId());

        // object attributes
        String resType = resource.getResourcePrefix();
        String rr_attributes = "<ResourceRelease id=\"" + node.getId() + "\" " + "release=\"" + resType + resource.getResource() + "\" " + "rUnits=\"" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
                + resource.getQty() + "\" "; //$NON-NLS-1$

        String rr_predecessor = "predecessor=\"" + "h" + predecessor + "\" "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        String rr_successor = "successor=\"" + "h" + successor + "\" "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        String end_rr = "/>"; //$NON-NLS-1$

        // special naming convention for RR/RA objects
        if (predecessor.startsWith("G")) { //$NON-NLS-1$
            rr_predecessor = "predecessor=\"" + predecessor + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        }
        if (successor.startsWith("G")) { //$NON-NLS-1$
            rr_successor = "successor=\"" + successor + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        }

        // printing attributes
        ps.print("            " + rr_attributes); //$NON-NLS-1$
        ps.print(rr_predecessor);
        ps.print(rr_successor);
        ps.print(end_rr);
        ps.println(""); //$NON-NLS-1$
    }

    public void releaseComp(String res, CSMDupNode node, CSMDupConnectionList list) {

        // initializing attributes
        String successor = list.getTargetForSource(node.getId());
        String predecessor = list.getSourceForTarget(node.getId());

        // object attributes
        String rr_attributes = "<ResourceRelease id=\"" + node.getId() + "\" " + "release=\"" + "r" + res + "\" "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

        String rr_predecessor = "predecessor=\"" + "h" + predecessor + "\" "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        String rr_successor = "successor=\"" + "h" + successor + "\" "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        String end_rr = "/>"; //$NON-NLS-1$

        // special naming convention for RR/RA objects
        if (predecessor.startsWith("G")) { //$NON-NLS-1$
            rr_predecessor = "predecessor=\"" + predecessor + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        }
        if (successor.startsWith("G")) { //$NON-NLS-1$
            rr_successor = "successor=\"" + successor + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        }

        // printing attributes
        ps.print("            " + rr_attributes); //$NON-NLS-1$
        ps.print(rr_predecessor);
        ps.print(rr_successor);
        ps.print(end_rr);
        ps.println(""); //$NON-NLS-1$
    }

    // prints XML representation of Dummy EmptyPoint element
    public void acquireEmptyPoint(CSMDupNode node, CSMDupConnectionList list) {

        // initializing attributes
        String target = list.getTargetForSource(node.getId());
        String source = list.getSourceForTarget(node.getId());

        // object attributes
        String epoint_attributes = "<Sequence id=\"" + node.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        String epoint_target = "target=\"h" + target + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        String epoint_source = "source=\"h" + source + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        String epoint_end = "/> <!-- RA-Sequence -->"; //$NON-NLS-1$

        // special naming convention for RR/RA objects
        if (source.startsWith("G")) { //$NON-NLS-1$
            epoint_source = "source=\"" + source + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        }
        if (target.startsWith("G")) { //$NON-NLS-1$
            epoint_target = "target=\"" + target + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        }

        // output to file
        ps.print("            " + epoint_attributes); //$NON-NLS-1$
        ps.print(epoint_source);
        ps.print(epoint_target);
        ps.print(epoint_end);
        ps.println(""); //$NON-NLS-1$
        ps.flush();
    }

    // inserts RR and Empty Points where necessary in the duplicate map
    public int addRR(CSMResourceSet resToRelease, CSMResourceSet usedResources, CSMDupNodeList map, CSMDupConnectionList conn_map, PathNode curr_edge,
            int ins_nodes) {
        int nodesInserted = ins_nodes;
        // create resource release component and insert it in duplicate map
        CSMDupNode rr_node = new CSMDupNode(++rr_id);
        rr_node.setResourcesDownstream(usedResources); // to compute
        // release/acquire sets
        rr_node.setResourcesUpstream(usedResources); // to compute
        // release/acquire sets
        map.add(rr_node);
        nodesInserted++;
        // create empty point and insert it in duplicate map
        // create new links
        CSMDupNode target = conn_map.getTargetForSource(curr_edge);
        if (target != null) { // not EndPoint
            CSMDupNode e_node = new CSMDupNode(++seq_id);
            e_node.setResourcesDownstream(usedResources); // to compute
            // release/acquire
            // sets
            e_node.setResourcesUpstream(usedResources); // to compute
            // release/acquire sets
            map.add(e_node);
            nodesInserted++;
            conn_map.add(new CSMDupConnection(curr_edge, e_node, map));
            conn_map.add(new CSMDupConnection(e_node, rr_node));
            // add an empty point if immediatly followed by RR/RA/RESPREF node
            if ((target.getType() == CSMDupNode.RR) || (target.getType() == CSMDupNode.RA) || (target.getType() == CSMDupNode.RESPREF)
                    || (target.getType() == CSMDupNode.STUB)) {
                // create empty point and insert it in duplicate map
                CSMDupNode e2_node = new CSMDupNode(++seq_id);
                e2_node.setResourcesDownstream(usedResources);
                e2_node.setResourcesUpstream(usedResources);
                map.add(e2_node);
                nodesInserted++;
                conn_map.add(new CSMDupConnection(rr_node, e2_node));
                conn_map.add(new CSMDupConnection(e2_node, target));
            } else {
                conn_map.add(new CSMDupConnection(rr_node, target));
            }

            conn_map.remove(curr_edge, target);
        } else { // EndPoint
            // for each connection source-EndPoint? js
            CSMDupNode source = conn_map.getSourceForTarget(curr_edge);
            if ((source.getType() == CSMDupNode.RR) || (source.getType() == CSMDupNode.RA) || (source.getType() == CSMDupNode.RESPREF)
                    || (source.getType() == CSMDupNode.STUB)) {
                CSMDupNode e_node = new CSMDupNode(++seq_id);
                e_node.setResourcesDownstream(usedResources); // to compute
                // release/acquire
                // sets
                e_node.setResourcesUpstream(usedResources); // to compute
                // release/acquire
                // sets
                map.add(e_node);
                nodesInserted++;
                conn_map.add(new CSMDupConnection(source, e_node));
                conn_map.add(new CSMDupConnection(e_node, rr_node));
            } else {
                conn_map.add(new CSMDupConnection(source, rr_node));
            }
            conn_map.add(new CSMDupConnection(rr_node, curr_edge, map));
            conn_map.remove(source, curr_edge);
        }

        if (resToRelease.size() != 0) { // this ought to be non-empty
            rr_node.setResourceToRelease(resToRelease.get(0)); // the resource
            // to be
            // released
            resToRelease.remove(0);
        }
        return nodesInserted;
    }

    // methods to manipulate RR and Dummy Sequence IDs
    public int getRrSeqId() {
        return seq_id;
    }

    public int getRrId() {
        return rr_id;
    }

    public void setRrSeqId(int id) {
        seq_id = id;
    }

    public void setRrId(int id) {
        rr_id = id;
    }
}
