package seg.jUCMNav.importexport.csm.duplicate;

import java.util.ArrayList;
import java.util.Iterator;

import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.UCMmap;

/**
 * A CSMDupConnectionList is a list of references to all the connections in the UCMap.
 * 
 */

public class CSMDupConnectionList {
    // will contain CSMDupConnection
    ArrayList connList = new ArrayList(1000); // TODO: wave limitation

    // create list of NodeConnections
    public void DuplicateConnection(UCMmap map, CSMDupNodeList dupNodeList) {
        for (Iterator iter = map.getConnections().iterator(); iter.hasNext();) {
            connList.add(new CSMDupConnection((NodeConnection) iter.next(), dupNodeList));
        }
    }

    /* The following are methods used to access the CSMDupNodeList */

    public void swap(int left, int right) {
        CSMDupConnection tmp;
        tmp = (CSMDupConnection) connList.get(left);
        connList.add(left, connList.get(right));
        connList.remove(left + 1);
        connList.add(right, tmp);
        connList.remove(right + 1);
    }

    // size of connection list
    public int size() {
        return connList.size();
    }

    // get connection
    public CSMDupConnection get(int i) {
        return (CSMDupConnection) connList.get(i);
    }

    // add connection at the end of list
    public void add(CSMDupConnection conn) {
        connList.add(conn);
    }

    // remove connection
    public void remove(int i) {
        connList.remove(i);
    }

    public void remove(CSMDupConnection conn) {
        connList.remove(conn);
    }

    // scans connections list and remove connection having the given target and
    // source
    public void remove(CSMDupNode source, PathNode target) {
        for (int i = 0; i < connList.size(); i++) {
            if (((CSMDupConnection) connList.get(i)).getSourceStr() == source.getId() && ((CSMDupConnection) connList.get(i)).getTargetStr() == target.getId()) {
                connList.remove(i);
            }
        }
    }

    public void remove(PathNode source, CSMDupNode target) {
        for (int i = 0; i < connList.size(); i++) {
            if (((CSMDupConnection) connList.get(i)).getSourceStr() == source.getId() && ((CSMDupConnection) connList.get(i)).getTargetStr() == target.getId()) {
                connList.remove(i);
            }
        }
    }

    public void remove(CSMDupNode source, CSMDupNode target) {
        for (int i = 0; i < connList.size(); i++) {
            if (((CSMDupConnection) connList.get(i)).getSourceStr() == source.getId() && ((CSMDupConnection) connList.get(i)).getTargetStr() == target.getId()) {
                connList.remove(i);
            }
        }
    }

    public void remove(PathNode source, PathNode target) {
        for (int i = 0; i < connList.size(); i++) {
            if (((CSMDupConnection) connList.get(i)).getSourceStr() == source.getId() && ((CSMDupConnection) connList.get(i)).getTargetStr() == target.getId()) {
                connList.remove(i);
            }
        }
    }

    // returns the source of a given target
    public CSMDupNode getSourceForTarget(PathNode target) {
        for (int i = 0; i < connList.size(); i++) {
            if (((CSMDupConnection) connList.get(i)).getCSMTarget() != null && (((CSMDupConnection) connList.get(i)).getCSMTarget()).getNode() != null) {
                if ((((CSMDupConnection) connList.get(i)).getCSMTarget()).getNode() == target) {
                    CSMDupNode source = ((CSMDupConnection) connList.get(i)).getCSMSource();
                    // return new CSMDupNode (source);
                    return source;
                }
            }
        }
        return null;
    }

    // returns the target of a given source.
    public CSMDupNode getTargetforSourceTowardNode(PathNode source, PathNode destination) {
        for (int i = 0; i < connList.size(); i++) {
            if ((((CSMDupConnection) connList.get(i)).getCSMSource()).getId() == source.getId()) {
                CSMDupNode target = ((CSMDupConnection) connList.get(i)).getCSMTarget();
                if (destination == null) {
                    return target;
                } else if (target.getId() == destination.getId()) {
                    if (target.getNode() != null) {
                        if (getTargetforSourceTowardNode(target.getNode(), destination) != null) {
                            return target;
                        } else if (getTargetForSourceTowardNode(target.getId(), destination) != null) {
                            return target;
                        }
                    }
                }
            }
        }
        return null;
    }

    // returns the target of a given source.
    public CSMDupNode getTargetForSourceTowardNode(String source, PathNode destination) {
        for (int i = 0; i < connList.size(); i++) {
            if ((((CSMDupConnection) connList.get(i)).getCSMSource()).getId() == source) {
                CSMDupNode target = ((CSMDupConnection) connList.get(i)).getCSMTarget();
                if (destination == null) {
                    return target;
                } else if ((target.getId() == destination.getId()) || (getTargetForSourceTowardNode(target.getId(), destination) != null)) {
                    return target;
                }
            }
        }
        return null;
    }

    // returns the source of a given target.
    public CSMDupNode getSourceForTargetTowardNode(PathNode target, PathNode destination) {
        for (int i = 0; i < connList.size(); i++) {
            if ((((CSMDupConnection) connList.get(i)).getCSMTarget()).getId() == target.getId()) {
                CSMDupNode source = ((CSMDupConnection) connList.get(i)).getCSMSource();
                if (destination == null) {
                    return source;
                } else if (source.getId() == destination.getId()) {
                    if (source.getNode() != null) {
                        if (getSourceForTargetTowardNode(source.getNode(), destination) != null) {
                            return source;
                        } else if (getSourceForTargetTowardNode(source.getId(), destination) != null) {
                            return source;
                        }
                    }
                }
            }
        }
        return null;
    }

    // returns the source of a given target.
    public CSMDupNode getSourceForTargetTowardNode(String target, PathNode destination) {
        for (int i = 0; i < connList.size(); i++) {
            if ((((CSMDupConnection) connList.get(i)).getCSMTarget()).getId() == target) {
                CSMDupNode source = ((CSMDupConnection) connList.get(i)).getCSMSource();
                if (destination == null) {
                    return source;
                } else if ((source.getId() == destination.getId()) || (getSourceForTargetTowardNode(source.getId(), destination) != null)) {
                    return source;
                }
            }
        }
        return null;
    }

    public String getSourceForTarget(String target) {
        for (int i = 0; i < connList.size(); i++) {
            if (((CSMDupConnection) connList.get(i)).getTargetStr() == target) {
                return ((CSMDupConnection) connList.get(i)).getSourceStr();
            }
        }
        return null;
    }

    // returns the target of a given source
    public CSMDupNode getTargetForSource(PathNode source) {
        for (int i = 0; i < connList.size(); i++) {
            if (((CSMDupConnection) connList.get(i)).getCSMSource() != null && (((CSMDupConnection) connList.get(i)).getCSMSource()).getNode() != null) {
                if ((((CSMDupConnection) connList.get(i)).getCSMSource()).getNode() == source) {
                    CSMDupNode target = ((CSMDupConnection) connList.get(i)).getCSMTarget();
                    return target;
                }
            }
        }
        return null;
    }

    public String getTargetForSource(String source) {
        for (int i = 0; i < connList.size(); i++) {
            if (((CSMDupConnection) connList.get(i)).getSourceStr() == source) {
                return ((CSMDupConnection) connList.get(i)).getTargetStr();
            }
        }
        return null;
    }

    // checks if list is empty
    public boolean isEmpty() {
        return connList.isEmpty();
    }

    // recursively builds list of previous edges
    public void getPrevEdgeList(String target, ArrayList sources) {
        for (int i = 0; i < connList.size(); i++) {
            String target_str = ((CSMDupConnection) connList.get(i)).getTargetStr();
            if (target_str == target) {
                String source_str = ((CSMDupConnection) connList.get(i)).getSourceStr();
                getPrevEdgeList(source_str, sources); // recursive call
                if (((CSMDupConnection) connList.get(i)).getCSMSource().isPathNode()) {
                    sources.add((((CSMDupConnection) connList.get(i)).getCSMSource()).getNode());
                } // if
            } // if
        } // for
    } // method

    // recursively builds list of next edges
    public void getNextEdgeList(String source, ArrayList targets) {
        for (int i = 0; i < connList.size(); i++) {
            String source_str = ((CSMDupConnection) connList.get(i)).getSourceStr();
            if (source_str == source) {
                String target_str = ((CSMDupConnection) connList.get(i)).getTargetStr();
                getNextEdgeList(target_str, targets); // recursive call
                if (((CSMDupConnection) connList.get(i)).getCSMTarget().isPathNode()) {
                    targets.add((((CSMDupConnection) connList.get(i)).getCSMTarget()).getNode());
                } // if
            } // if
        } // for
    } // method

    // reverses any given list
    public ArrayList reverseList(ArrayList list) {
        ArrayList reversed_list = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            int j = list.size() - i - 1;
            reversed_list.add(i, list.get(j));
        }
        return reversed_list;
    }

    // given a source node, searches the list for the target nodes associated to
    // that source
    public ArrayList getTargetFromList(String source) {
        ArrayList targets = new ArrayList(1000);
        for (int i = 0; i < connList.size(); i++) {
            if (((CSMDupConnection) connList.get(i)).getSourceStr() == source) {
                targets.add(((CSMDupConnection) connList.get(i)).getTargetStr());
            }
        }
        return targets;
    }

    // returns the position of the node in the list of dup nodes
    public int getConnectionIndex(String source) {
        for (int i = 0; i < connList.size(); i++) {
            if (((CSMDupConnection) (connList.get(i))).getSourceStr() == source)
            return i;
        }
        return -1; // not found
    }

    // for debug - prints source/target of all elements in list
    public void printDupList() {
        System.out.println("----------Printing duplicate Connection List-------"); //$NON-NLS-1$
        System.out.println("List size: " + connList.size()); //$NON-NLS-1$
        for (int i = 0; i < connList.size(); i++) {
            CSMDupNode source = ((CSMDupConnection) connList.get(i)).getCSMSource();
            PathNode sourceNode = source.getNode();
            String sourceName = sourceNode != null ? " (" + sourceNode.getName() + ") " : ""; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            CSMDupNode target = ((CSMDupConnection) connList.get(i)).getCSMTarget();
            PathNode targetNode = target.getNode();
            String targetName = targetNode != null ? " (" + targetNode.getName() + ") " : ""; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            System.out.println("Connection " + i + ": " + " Source: " + ((CSMDupConnection) connList.get(i)).getSourceStr() + sourceName + " Target: " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
                    + ((CSMDupConnection) connList.get(i)).getTargetStr() + targetName);
        }
    }

    // checks to see if a connection exists for a given source
    public boolean existsConnectionForSource(CSMDupNode source) {
        for (int i = 0; i < connList.size(); i++) {
            String conn_source_id = (((CSMDupConnection) (connList.get(i)))).getSourceStr();
            if (conn_source_id.compareTo(source.getId()) == 0) {
                return true;
            }
        }
        return false;
    }

    // given a source method returns a connection
    public CSMDupConnection getConnectionForSource(CSMDupNode source) {
        for (int i = 0; i < connList.size(); i++) {
            String conn_source_id = (((CSMDupConnection) (connList.get(i)))).getSourceStr();
            if (conn_source_id.compareTo(source.getId()) == 0)
                return (CSMDupConnection) (connList.get(i));
        }
        return null;
    }
}
