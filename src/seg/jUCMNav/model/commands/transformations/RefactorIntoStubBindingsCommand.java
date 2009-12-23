package seg.jUCMNav.model.commands.transformations;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.create.AddInBindingCommand;
import seg.jUCMNav.model.commands.create.AddOutBindingCommand;
import seg.jUCMNav.model.util.modelexplore.GraphExplorer;
import seg.jUCMNav.model.util.modelexplore.queries.ReachableNodeFinder;
import seg.jUCMNav.model.util.modelexplore.queries.ReachableNodeFinder.QFindReachableNodes;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.PluginBinding;
import ucm.map.StartPoint;
import ucm.map.Stub;

public class RefactorIntoStubBindingsCommand extends CompoundCommand {

    private Stub stub;

    public RefactorIntoStubBindingsCommand(Stub stub) {
        this.stub = stub;
    }

    public boolean canExecute() {
        return true;
    }
    public boolean canUndo() {
        return true;
    }

    public void execute() {
        if (stub.getBindings().size() > 0) {

            PluginBinding binding = (PluginBinding) stub.getBindings().get(0);

            createInBindings(binding);
            createOutBindings(binding);
        }
        super.execute();
    }

    private void createInBindings(PluginBinding binding) {
        // nc -> start point in plugin
        HashMap hmAssociated = new HashMap();
        Vector notAssociated = new Vector();

        // first associate by name.
        for (Iterator iterator = stub.getPred().iterator(); iterator.hasNext();) {
            NodeConnection nc = (NodeConnection) iterator.next();

            QFindReachableNodes qReachableNodes = new ReachableNodeFinder.QFindReachableNodes((PathNode) nc.getSource(), new HashSet(),
                    QFindReachableNodes.DIRECTION_REVERSE);
            ReachableNodeFinder.RReachableNodes rReachableNodes = (ReachableNodeFinder.RReachableNodes) GraphExplorer.run(qReachableNodes);
            Vector vReachable = rReachableNodes.getNodes();

            for (Iterator iterator2 = vReachable.iterator(); iterator2.hasNext();) {
                PathNode pn = (PathNode) iterator2.next();
                if (pn instanceof StartPoint && pn.getName() != null) {

                    for (Iterator iterator3 = binding.getPlugin().getNodes().iterator(); iterator3.hasNext();) {
                        PathNode childPn = (PathNode) iterator3.next();
                        if (childPn instanceof StartPoint && pn.getName().equals(childPn.getName()) && !hmAssociated.containsValue(childPn)) {
                            hmAssociated.put(nc, childPn);
                            add(new AddInBindingCommand(binding, (StartPoint) childPn, nc));
                            break;
                        }
                    }
                }
            }

            if (!hmAssociated.containsKey(nc))
                notAssociated.add(nc);
        }

        // then associate remainder randomly.
        for (Iterator iterator = notAssociated.iterator(); iterator.hasNext();) {
            NodeConnection nc = (NodeConnection) iterator.next();

            for (Iterator iterator3 = binding.getPlugin().getNodes().iterator(); iterator3.hasNext();) {
                PathNode childPn = (PathNode) iterator3.next();
                if (childPn instanceof StartPoint && !hmAssociated.containsValue(childPn)) {
                    hmAssociated.put(nc, childPn);
                    add(new AddInBindingCommand(binding, (StartPoint) childPn, nc));
                    break;
                }
            }

        }
    }

    private void createOutBindings(PluginBinding binding) {
        // nc -> end point in plugin
        HashMap hmAssociated = new HashMap();
        Vector notAssociated = new Vector();

        for (Iterator iterator = stub.getSucc().iterator(); iterator.hasNext();) {
            NodeConnection nc = (NodeConnection) iterator.next();

            QFindReachableNodes qReachableNodes = new ReachableNodeFinder.QFindReachableNodes((PathNode) nc.getTarget(), new HashSet(),
                    QFindReachableNodes.DIRECTION_FORWARD);
            ReachableNodeFinder.RReachableNodes rReachableNodes = (ReachableNodeFinder.RReachableNodes) GraphExplorer.run(qReachableNodes);
            Vector vReachable = rReachableNodes.getNodes();

            for (Iterator iterator2 = vReachable.iterator(); iterator2.hasNext();) {
                PathNode pn = (PathNode) iterator2.next();
                if (pn instanceof EndPoint && pn.getName() != null) {

                    for (Iterator iterator3 = binding.getPlugin().getNodes().iterator(); iterator3.hasNext();) {
                        PathNode childPn = (PathNode) iterator3.next();
                        if (childPn instanceof EndPoint && pn.getName().equals(childPn.getName()) && !hmAssociated.containsValue(childPn)) {
                            hmAssociated.put(nc, childPn);
                            add(new AddOutBindingCommand(binding, (EndPoint) childPn, nc));
                            break;
                        }
                    }
                }
            }

            if (!hmAssociated.containsKey(nc))
                notAssociated.add(nc);
        }
        // then associate remainder randomly.
        for (Iterator iterator = notAssociated.iterator(); iterator.hasNext();) {
            NodeConnection nc = (NodeConnection) iterator.next();

            for (Iterator iterator3 = binding.getPlugin().getNodes().iterator(); iterator3.hasNext();) {
                PathNode childPn = (PathNode) iterator3.next();
                if (childPn instanceof EndPoint && !hmAssociated.containsValue(childPn)) {
                    hmAssociated.put(nc, childPn);
                    add(new AddOutBindingCommand(binding, (EndPoint) childPn, nc));
                    break;
                }
            }
        }
    }

}
