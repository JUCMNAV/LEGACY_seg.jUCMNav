package seg.jUCMNav.importexport.csm.duplicate;

import java.util.Iterator;
import java.util.Vector;

import ucm.map.PathNode;
import ucm.map.UCMmap;

/**
 * Creates a list of CSMDupNodes, that is meant to replicate the UCM map.
 * 
 */
public class CSMDupNodeList {
    // will contain CSMDupNodes (which in turn may point to a PathNode or an RA
    // node)
    private CSMDupNode[] nodeList = new CSMDupNode[1000]; // TODO: remove size
    // limitation.

    private int length = 0;

    // create list of PathNodes
    public void DuplicateHyperEdges(UCMmap map, Vector warnings) {
        for (Iterator iter = map.getNodes().iterator(); iter.hasNext();) {
            nodeList[length++] = new CSMDupNode((PathNode) iter.next(), warnings);
        }
    }

    /* The following are methods used to access the CSMDupNodeList */

    // size of path list
    public int size() {
        return length;
    }

    // get node
    public CSMDupNode get(int i) {
        return nodeList[i];
    }

    // add node at the end of list
    public void add(CSMDupNode node) {
        nodeList[length++] = node;
    }

    // add node at a specific point in map
    public void add(int position, CSMDupNode node) {
        nodeList[position] = node;
    }

    // checks if list is empty
    public boolean isEmpty() {
        return length == 0;
    }

    // return a PathNode from the Duplicate Graph
    public PathNode getListNode(int i) {
        return nodeList[i].getNode();
    }

    // for debug - prints ids of all elements in list
    public void printDupList() {
        System.out.println("----------Printing duplicate Node List-------"); //$NON-NLS-1$
        System.out.println("List size: " + length); //$NON-NLS-1$
        String typeName;
        for (int i = 0; i < length; i++) {
            int type = nodeList[i].getType();
            typeName = " (" + nodeList[i].getTypeString() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
            if (type == CSMDupNode.RA || type == CSMDupNode.RR || type == CSMDupNode.CSMEMPTY) {
                String id = nodeList[i].getId();
                System.out.println("Index " + i + " id: " + id + typeName); //$NON-NLS-1$ //$NON-NLS-2$
            } else {
                System.out.println("Node : " + nodeList[i].getNode()); //$NON-NLS-1$
                String node_id = nodeList[i].getId();
                System.out.println("Index " + i + " id: " + node_id + typeName); //$NON-NLS-1$ //$NON-NLS-2$
            }
        }
    }

    // returns the position of the node in the list of dup nodes
    public int getNodeIndex(PathNode node) {
        for (int i = 0; i < length; i++) {
            if (nodeList[i].getNode() == node)
                return i;
        }
        return -1; // not found
    }

    // removes a node from the node list (and just one)
    public void remove(CSMDupNode node) {
        boolean found = false;
        for (int i = 0; i < length; i++) {
            if (nodeList[i].getNode() != null) {
                if (nodeList[i].getNode().getId() == node.getId()) {
                    found = true;
                    // this.nodeList.remove(i);
                } // if
                if (found && ((i + 1) < length)) {
                    nodeList[i] = nodeList[i + 1];
                }
            } // if
        } // for
        if (found) {
            length--;
        }
    } // method

    public void remove(PathNode node) {
        boolean found = false;
        for (int i = 0; i < length; i++) {
            if (nodeList[i].getNode() == node) {
                found = true;
                // this.nodeList.remove(i);
            } // if
            if (found && ((i + 1) < length)) {
                nodeList[i] = nodeList[i + 1];
            }
        } // for
        if (found) {
            length--;
        }
    } // method

    /**
     * Populate non-RESPREF and non-STUB nodes with downstream and upstream resource lists such that computing RA and RR can be optimized later on (i.e. by not
     * acquiring a resource acquired previously, and by not releasing a resource that will be needed next).
     * 
     * @param dupMapConnList
     *            list of duplicated connections
     */
    public void computeNodesResources(CSMDupConnectionList dupMapConnList) {
        boolean done = false;
        // propagate resources - forward and backward - from nodes RESPRES and STUB.
        // TODO: investigate possibility to take into account nodes START and END.
        while (!done) {
            done = true;
            for (int i = 0; i < dupMapConnList.size(); i++) {
                CSMDupConnection conn = dupMapConnList.get(i);
                CSMDupNode source = conn.getCSMSource();
                int srcType = source.getType();
                CSMDupNode target = conn.getCSMTarget();
                int tgtType = target.getType();
                // propagate upward
                if ((tgtType != CSMDupNode.RESPREF) && (tgtType != CSMDupNode.STUB)) {
                    if ((source.getResourcesDownstream() != null) && (source.getResourcesDownstream().size() != 0)) {
                        if ((target.getNode().getPred().size() == 1) && (target.getNode().getSucc().size() == 1)) {
                            if (target.getResourcesDownstream() != source.getResourcesDownstream()) {
                                target.setResourcesDownstream(source.getResourcesDownstream());
                                done = false;
                            }
                        } else {
                            /*
                             * Unpredictable incoming multipath node: empty downstream resources (as if none were requested previously), actually forcing a
                             * "request all"
                             */
                            target.setResourcesDownstream(null);
                        }
                    } // if
                } // if
                // propagate downard
                if ((srcType != CSMDupNode.RESPREF) && (srcType != CSMDupNode.STUB)) {
                    if ((target.getResourcesUpstream() != null) && (target.getResourcesUpstream().size() != 0)) {
                        if ((source.getNode().getPred().size() == 1) && (source.getNode().getSucc().size() == 1)) {
                            if (source.getResourcesUpstream() != target.getResourcesUpstream()) {
                                source.setResourcesUpstream(target.getResourcesUpstream());
                                done = false;
                            }
                        } else {
                            /*
                             * Unpredictable outgoing multipath node: empty upstream resources (as if none were needed thereafter), actually forcing a "release
                             * all"
                             */
                            source.setResourcesUpstream(null);
                        }
                    } // if
                } // if
            } // for
        } // while
    } // method

    /**
     * Change the type of a CSMDupNode
     * 
     * @param dupNode
     *            the node to be changed
     * @param type
     *            the new type of the node
     */
    public void retype(CSMDupNode dupNode, int type) {
        int nodeIndex = getNodeIndex(dupNode.getNode());
        nodeList[nodeIndex].setType(type);
    }
}
