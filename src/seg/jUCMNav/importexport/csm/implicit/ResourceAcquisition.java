package seg.jUCMNav.importexport.csm.implicit;

import java.io.PrintStream;
import java.util.Stack;

import seg.jUCMNav.importexport.csm.duplicate.CSMDupConnection;
import seg.jUCMNav.importexport.csm.duplicate.CSMDupConnectionList;
import seg.jUCMNav.importexport.csm.duplicate.CSMDupNode;
import seg.jUCMNav.importexport.csm.duplicate.CSMDupNodeList;
import ucm.map.ComponentRef;
import ucm.map.EndPoint;
import ucm.map.PathNode;
import ucm.map.StartPoint;

/**
 * Inserts Resource Acquisition objects in duplicate map.
 * 
 */

public class ResourceAcquisition {

    // RA and Empty Point IDs
    static int ra_id = 1000; // TODO: wave limitation.

    static int seq_id = 2000; // TODO: wave limitation.

    PrintStream ps;

    // constructor
    public ResourceAcquisition(PrintStream ps) {
        this.ps = ps;
    }

    // adds all hyperedge parent components to stack
    public void findParentsRA(ComponentRef compRef, Stack edge_stack) {
        // The top of the stack is the outermost component
        ComponentRef parent_compRef = (ComponentRef) compRef.getParent();
        if (parent_compRef != null) {
            edge_stack.push(parent_compRef);
            findParentsRA(parent_compRef, edge_stack);
        }
    }

    /**
     * Compute Resources to be acquired
     * 
     * @param curr_edge
     *            node associated with the resource acquisition
     * @param dup_map
     * @param dup_map_conn
     * @return the number of newly created nodes
     */
    public int acquireResource(PathNode curr_edge, CSMDupNodeList dup_map, CSMDupConnectionList dup_map_conn) {
        // list that will store edges to be parsed (will contain pathnodes only)
        int nodes_inserted = 0; // total nodes inserted since last run
        // Compute resources to be acquired
        CSMResourceSet usedResources = null; // requested resources +
        // containing components
        CSMResourceSet resToAcquire = null; // usedResources -
        // resPreviouslyNeeded
        CSMDupNode curr_edge_dupNode = dup_map.get(dup_map.getNodeIndex(curr_edge));
        CSMDupNode previousDupNode = null;
        // StartPoint acquires all containing components
        if ((curr_edge_dupNode.getType() == CSMDupNode.RESPREF) || (curr_edge_dupNode.getType() == CSMDupNode.STUB)) {
            if (curr_edge_dupNode.getResourcesDownstream() != null) {
                usedResources = curr_edge_dupNode.getResourcesDownstream().toAcquire();
            } else {
                usedResources = null;
            }
            previousDupNode = dup_map_conn.getSourceForTarget(curr_edge);
            if ((previousDupNode != null) && previousDupNode.getResourcesDownstream() != null) {
                resToAcquire = usedResources.minus(previousDupNode.getResourcesDownstream().toAcquire());
            } else {
                resToAcquire = usedResources;
            }
        }
        while ((resToAcquire != null) && (resToAcquire.size() != 0)) {
            nodes_inserted = addRA(resToAcquire, usedResources, curr_edge, dup_map, dup_map_conn, nodes_inserted);
        }
        return nodes_inserted;
    } // function

    // prints XML representation of Resource Acquire element
    public void acquireRes(CSMResource resAttribs, CSMDupNode node, CSMDupConnectionList list) {

        // initializing attributes
        String successor = list.getTargetForSource(node.getId());
        String predecessor = list.getSourceForTarget(node.getId());

        // object attributes
        String resType = resAttribs.getResourcePrefix();
        String ra_attributes = "<ResourceAcquire id=\"" + node.getId() + "\" " + "acquire=\"" + resType + resAttribs.getResource() + "\" " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
                + "rUnits=\"" + resAttribs.getQty() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        String ra_predecessor = "predecessor=\"" + "h" + predecessor + "\" "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        String ra_successor = "successor=\"" + "h" + successor + "\" "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        String end_ra = "/>"; //$NON-NLS-1$

        // special naming convention for RR/RA objects
        if (predecessor.startsWith("G")) { //$NON-NLS-1$
            ra_predecessor = "predecessor=\"" + predecessor + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        }
        if (successor.startsWith("G")) { //$NON-NLS-1$
            ra_successor = "successor=\"" + successor + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        }

        // printing attributes
        ps.print("            " + ra_attributes); //$NON-NLS-1$
        ps.print(ra_predecessor);
        ps.print(ra_successor);
        ps.print(end_ra);
        ps.println(""); //$NON-NLS-1$
    }

    public void acquireComp(String res, CSMDupNode node, CSMDupConnectionList list) {

        // initializing attributes
        String successor = list.getTargetForSource(node.getId());
        String predecessor = list.getSourceForTarget(node.getId());

        // object attributes
        String ra_attributes = "<ResourceAcquire id=\"" + node.getId() + "\" " + "acquire=\"" + "r" + res + "\" "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
        String ra_predecessor = "predecessor=\"" + "h" + predecessor + "\" "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        String ra_successor = "successor=\"" + "h" + successor + "\" "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        String end_ra = "/>"; //$NON-NLS-1$

        // special naming convention for RR/RA objects
        if (predecessor.startsWith("G")) { //$NON-NLS-1$
            ra_predecessor = "predecessor=\"" + predecessor + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        }
        if (successor.startsWith("G")) { //$NON-NLS-1$
            ra_successor = "successor=\"" + successor + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
        }

        // printing attributes
        ps.print("            " + ra_attributes); //$NON-NLS-1$
        ps.print(ra_predecessor);
        ps.print(ra_successor);
        ps.print(end_ra);
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

    // inserts RA and Empty Points where necessary in the duplicate map
    public int addRA(CSMResourceSet resToAcquire, CSMResourceSet usedResources, PathNode curr_edge, CSMDupNodeList map, CSMDupConnectionList conn_map,
            int ins_nodes) {
        int nodesInserted = ins_nodes;
        // create resource acquire component and insert it in duplicate map
        CSMDupNode ra_node = new CSMDupNode(++ra_id);
        ra_node.setResourcesDownstream(usedResources); // to compute the
        // release set
        ra_node.setResourcesUpstream(usedResources); // to compute the
        // release set
        map.add(ra_node);
        nodesInserted++;
        // create new links
        CSMDupNode source = conn_map.getSourceForTarget(curr_edge);
        // add an empty point if immediatly preceded by RR/RA/RESPREF node

        if (source != null) {

            if ((source.getType() == CSMDupNode.RR) || (source.getType() == CSMDupNode.RA) || (source.getType() == CSMDupNode.RESPREF)
                    || (source.getType() == CSMDupNode.STUB) || (source.getType() == CSMDupNode.CONNECT)) {
                // create empty point and insert it in duplicate map
                CSMDupNode e2_node = new CSMDupNode(++seq_id);
                e2_node.setResourcesDownstream(usedResources); // to compute
                // the release
                // set
                e2_node.setResourcesUpstream(usedResources); // to compute
                // the release
                // set
                map.add(e2_node);
                nodesInserted++;
                conn_map.add(new CSMDupConnection(source, e2_node));
                conn_map.add(new CSMDupConnection(e2_node, ra_node));

            } else {
                conn_map.add(new CSMDupConnection(source, ra_node));
            }
            conn_map.remove(source, map.get(map.getNodeIndex(curr_edge)));
            if ((curr_edge instanceof StartPoint) || (curr_edge instanceof EndPoint)) { // curr_edge is
                // StartPoint of a
                // Connect (special
                // case)
                conn_map.add(new CSMDupConnection(ra_node, curr_edge, map));
            } else {
                // create empty point and insert it in duplicate map
                CSMDupNode e_node = new CSMDupNode(++seq_id);
                e_node.setResourcesDownstream(usedResources); // to compute
                // the release
                // set
                e_node.setResourcesUpstream(usedResources); // to compute the
                // release set
                map.add(e_node);
                nodesInserted++;
                conn_map.add(new CSMDupConnection(ra_node, e_node));
                conn_map.add(new CSMDupConnection(e_node, curr_edge, map));
            }
        } else { // curr_edge is StartPoint (special case)
            // create empty point and insert it in duplicate map after RA
            CSMDupNode e_node = new CSMDupNode(++seq_id);
            e_node.setResourcesDownstream(usedResources); // to compute the
            // release set
            e_node.setResourcesUpstream(usedResources); // to compute the
            // release set
            map.add(e_node);
            nodesInserted++;
            CSMDupNode nextDupNode = conn_map.getTargetForSource(curr_edge);
            conn_map.add(new CSMDupConnection(curr_edge, ra_node, map));
            conn_map.add(new CSMDupConnection(ra_node, e_node));
            conn_map.add(new CSMDupConnection(e_node, nextDupNode));
            conn_map.remove(map.get(map.getNodeIndex(curr_edge)), nextDupNode);
        }

        if (resToAcquire.size() != 0) {
            ra_node.setResourceToAcquire(resToAcquire.get(0)); // resource to
            // acquire
            resToAcquire.remove(0);
        }
        return nodesInserted;
    }

    // methods to manipulate RA and Dummy Sequence IDs
    public int getRaSeqId() {
        return seq_id;
    }

    public int getRaId() {
        return ra_id;
    }

    public void setRaSeqId(int id) {
        seq_id = id;
    }

    public void setRaId(int id) {
        ra_id = id;
    }
}
