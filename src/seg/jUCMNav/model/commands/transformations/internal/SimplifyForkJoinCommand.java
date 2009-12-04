package seg.jUCMNav.model.commands.transformations.internal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.commands.changeConstraints.ContainerRefBindChildCommand;
import seg.jUCMNav.model.commands.create.AddBranchCommand;
import seg.jUCMNav.model.commands.transformations.AttachBranchCommand;
import seg.jUCMNav.model.commands.transformations.DividePathCommand;
import seg.jUCMNav.model.commands.transformations.MergeStartEndCommand;
import seg.jUCMNav.model.commands.transformations.SplitLinkCommand;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.ComponentRef;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.UCMmap;

/**
 * Command that simplifies one individual and fork/join that is deleted between the creation of this command and its execution.
 * 
 * @author jkealey
 * 
 */
public class SimplifyForkJoinCommand extends Command implements JUCMNavCommand {

    protected PathNode forkOrJoin;

    protected PathNode newForkOrJoin;

    protected Vector incoming;

    protected Vector outgoing;

    protected HashMap hmForkToNodeConnection;

    protected UCMmap map;

    protected ComponentRef parent;

    protected CompoundCommand internal;

    protected CommandStack cs;

    /**
     * Simplifies a complex fork/join. Creates new forks/joins that are grouped by target flowPoint.
     * 
     * @param forkOrJoin
     *            the andfork/andjoin; assumes it was already deleted before this command is executed but not when it is created.
     * @param hmForkToNodeConnection
     *            a map that groups the fork/joins out/in branches by a flow point later/earlier on the path.
     */
    public SimplifyForkJoinCommand(PathNode forkOrJoin, HashMap hmForkToNodeConnection) {
        this.forkOrJoin = forkOrJoin;

        this.incoming = new Vector();
        this.incoming.addAll(forkOrJoin.getPred());
        this.outgoing = new Vector();
        this.outgoing.addAll(forkOrJoin.getSucc());
        this.hmForkToNodeConnection = hmForkToNodeConnection;
        this.map = (UCMmap) forkOrJoin.getDiagram();
        this.parent = (ComponentRef) forkOrJoin.getContRef();

    }

    public void execute() {

        cs = new CommandStack();
        internal = new CompoundCommand();

        if (forkOrJoin instanceof AndJoin)
            newForkOrJoin = (AndJoin) ModelCreationFactory.getNewObject(map.getUrndefinition().getUrnspec(), AndJoin.class);
        else if (forkOrJoin instanceof AndFork)
            newForkOrJoin = (AndFork) ModelCreationFactory.getNewObject(map.getUrndefinition().getUrnspec(), AndFork.class);

        redo();
    }

    public void redo() {
        testPreConditions();

        if (cs.canRedo()) {
            while (cs.canRedo())
                cs.redo();
        } else {
            if (forkOrJoin instanceof AndJoin)
                internal.add(new DividePathCommand(newForkOrJoin, (NodeConnection) outgoing.get(0), 0, 0));
            else if (forkOrJoin instanceof AndFork)
                internal.add(new DividePathCommand(newForkOrJoin, (NodeConnection) incoming.get(0), 0, 0));

            for (int i = 2; i < hmForkToNodeConnection.keySet().size(); i++) {
                internal.add(new AddBranchCommand(newForkOrJoin, true));
            }

            if (parent != null)
                internal.add(new ContainerRefBindChildCommand(parent, newForkOrJoin));

            cs.execute(internal);
            internal = new CompoundCommand();
            // we now have as many branches on this new fork/join as we have little paths that we'll need to reconnect.

            int i = 0;
            for (Iterator iter = hmForkToNodeConnection.values().iterator(); iter.hasNext();) {
                Vector v = (Vector) iter.next();

                if (newForkOrJoin instanceof AndFork) {

                    PathNode endPoint = (PathNode) ((NodeConnection) newForkOrJoin.getSucc().get(i)).getTarget();
                    while (!(endPoint instanceof EndPoint)) {
                        endPoint = (PathNode) ((NodeConnection) endPoint.getSucc().get(0)).getTarget();
                    }

                    assert v.size() >= 1;

                    internal.add(new MergeStartEndCommand(map, (StartPoint) ((NodeConnection) v.firstElement()).getSource(), (EndPoint) endPoint, 100, 100));

                    cs.execute(internal);
                    internal = new CompoundCommand();

                    AndFork newFork2 = (AndFork) ModelCreationFactory.getNewObject(map.getUrndefinition().getUrnspec(), AndFork.class);

                    if (parent != null)
                        internal.add(new ContainerRefBindChildCommand(parent, newFork2));

                    if (v.size() >= 2) { // doesn't create empty branch
                        internal.add(new SplitLinkCommand(map, newFork2, (NodeConnection) v.firstElement(), 150, 150)); // creates the second level fork.
                    }

                    // attach all other branches on this secondary one.
                    for (int j = 1; j < v.size(); j++) {

                        PathNode startPoint = (PathNode) ((NodeConnection) v.get(j)).getSource();
                        while (!(startPoint instanceof StartPoint)) {
                            startPoint = (PathNode) ((NodeConnection) v.get(0)).getSource();
                        }
                        internal.add(new AttachBranchCommand(startPoint, newFork2));
                    }

                    cs.execute(internal);
                    internal = new CompoundCommand();

                } else if (newForkOrJoin instanceof AndJoin) {

                    PathNode startPoint = (PathNode) ((NodeConnection) newForkOrJoin.getPred().get(i)).getSource();
                    while (!(startPoint instanceof StartPoint)) {
                        startPoint = (PathNode) ((NodeConnection) startPoint.getPred().get(0)).getSource();
                    }

                    assert v.size() >= 1;

                    internal.add(new MergeStartEndCommand(map, (StartPoint) startPoint, (EndPoint) ((NodeConnection) v.firstElement()).getTarget(), 100, 100));

                    cs.execute(internal);
                    internal = new CompoundCommand();

                    AndJoin newJoin2 = (AndJoin) ModelCreationFactory.getNewObject(map.getUrndefinition().getUrnspec(), AndJoin.class);

                    if (parent != null)
                        internal.add(new ContainerRefBindChildCommand(parent, newJoin2));

                    if (v.size() >= 2) { // doesn't create empty branch
                        internal.add(new SplitLinkCommand(map, newJoin2, (NodeConnection) v.firstElement(), 150, 150)); // creates the second level join.
                    }

                    // attach all other branches on this secondary one.
                    for (int j = 1; j < v.size(); j++) {

                        PathNode endPoint = (PathNode) ((NodeConnection) v.get(j)).getTarget();
                        while (!(endPoint instanceof EndPoint)) {
                            endPoint = (PathNode) ((NodeConnection) v.get(0)).getTarget();
                        }
                        internal.add(new AttachBranchCommand(endPoint, newJoin2));
                    }

                    cs.execute(internal);
                    internal = new CompoundCommand();

                }

                i++;
            }

            cs.execute(internal);

        }

        testPostConditions();
    }

    public void undo() {
        testPostConditions();

        while (cs.canUndo())
            cs.undo();

        testPreConditions();
    }

    public void testPostConditions() {
        assert forkOrJoin != null && (forkOrJoin instanceof AndFork || forkOrJoin instanceof AndJoin) && forkOrJoin.getDiagram() == null; // make sure was
                                                                                                                                          // deleted first.
        assert map != null;

    }

    public void testPreConditions() {
        assert forkOrJoin != null && (forkOrJoin instanceof AndFork || forkOrJoin instanceof AndJoin) && forkOrJoin.getDiagram() == null; // make sure was
                                                                                                                                          // deleted first.
        assert map != null;

    }

}
