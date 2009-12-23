package seg.jUCMNav.model.commands.transformations.internal;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.delete.DeleteNodeConnectionCommand;
import seg.jUCMNav.model.commands.delete.internal.DeleteStartNCEndCommand;
import seg.jUCMNav.model.commands.transformations.AttachBranchCommand;
import seg.jUCMNav.model.util.SafePathChecker;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.UCMmap;

public class AttachNewExtremitiesToStubCommand extends CompoundCommand {

    protected int maxIdDuringCreation = 0;
    protected UCMmap map;
    protected Stub stub;
    protected Vector alsoAttachThese;
    protected boolean built = false;
    protected boolean replaceFirst = false;
    protected int inCount;
    protected int outCount;

    /**
     * Between the time this command is created and its execution, the model has changed. we want to attach new start/end points in map to the stub.
     * 
     * @param map
     * @param stub
     * @param replaceFirst
     *            should we disconnect the first in/out we find?
     */
    public AttachNewExtremitiesToStubCommand(UCMmap map, Stub stub, Vector alsoAttachThese, boolean replaceFirst) {

        this.stub = stub;
        this.map = map;
        this.replaceFirst = replaceFirst;
        this.alsoAttachThese = alsoAttachThese;
        maxIdDuringCreation = Integer.parseInt(this.map.getUrndefinition().getUrnspec().getNextGlobalID());
        inCount = 0;
        outCount = 0;
    }

    public boolean canExecute() {
        return true;
    }

    public void execute() {
        if (!built) {
            /*
             * DeleteUselessStartNCEndCommand uselessCmd = new DeleteUselessStartNCEndCommand(map, null); uselessCmd.setNextGlobalID(maxIdDuringCreation);
             * add(uselessCmd);
             */

            Vector toAttach = new Vector();
            for (Iterator iterator = map.getNodes().iterator(); iterator.hasNext();) {
                PathNode pn = (PathNode) iterator.next();

                if (pn instanceof EndPoint || pn instanceof StartPoint) {
                    int newid = Integer.parseInt(pn.getId());
                    if (newid >= maxIdDuringCreation) {
                        toAttach.add(pn);
                    }
                }
            }

            for (Iterator iterator = toAttach.iterator(); iterator.hasNext();) {
                PathNode pn = (PathNode) iterator.next();
                // attach branch unless both ends will be attached.
                // if (!removeTinyBranch(toAttach, pn))
                attachBranch(pn);
            }

            if (alsoAttachThese != null) {
                for (Iterator iterator2 = alsoAttachThese.iterator(); iterator2.hasNext();) {
                    PathNode pn = (PathNode) iterator2.next();
                    if (pn.getDiagram() != null) // if still exists.
                        attachBranch(pn);
                }
            }

            built = true;
        }
        super.execute();
    }

    private void attachBranch(PathNode pn) {
        if (SafePathChecker.isSafeFusion(pn, stub)) {

            // System.out.println("Attached: " + pn.getId());
            if (replaceFirst) {

                // get rid of the first connection
                if (this.inCount == 0 && pn instanceof EndPoint) {
                    NodeConnection nc = (NodeConnection) stub.getPred().get(0);
                    // if (nc.getSource() instanceof StartPoint)
                    // add(new MergeStartEndCommand((UCMmap) stub.getDiagram(), (StartPoint) nc.getSource(), (EndPoint) pn, pn.getX(), pn.getY()));
                    // else {
                    add(new DeleteNodeConnectionCommand(nc, null));
                    add(new AttachBranchCommand(pn, stub));
                    // }
                } else if (this.outCount == 0 && pn instanceof StartPoint) {
                    NodeConnection nc = (NodeConnection) stub.getSucc().get(0);
                    // if (nc.getTarget() instanceof EndPoint)
                    // add(new MergeStartEndCommand((UCMmap) stub.getDiagram(), (StartPoint) pn, (EndPoint) nc.getTarget(), pn.getX(), pn.getY()));
                    // else {
                    add(new DeleteNodeConnectionCommand(nc, null));
                    add(new AttachBranchCommand(pn, stub));
                    // }
                } else {
                    add(new AttachBranchCommand(pn, stub));
                }
            } else {
                add(new AttachBranchCommand(pn, stub));
            }

            if (pn instanceof StartPoint)
                outCount++;
            else if (pn instanceof EndPoint)
                inCount++;

        }
    }

    private boolean removeTinyBranch(Vector toAttach, PathNode pn) {

        // instead of attaching a tiny branch
        DeleteStartNCEndCommand cmd = null;
        if (pn instanceof StartPoint)
            cmd = new DeleteStartNCEndCommand((StartPoint) pn);
        else if (pn instanceof EndPoint)
            cmd = new DeleteStartNCEndCommand((EndPoint) pn);
        if (cmd != null) {
            cmd.build();
            boolean aborted = cmd.isAborted();
            if (!aborted) { // means this is a tiny branch.
                // are both ends scheduled to be attached?
                if (toAttach.contains(cmd.getStart()) && toAttach.contains(cmd.getEnd())) {
                    add(cmd);
                    return true;
                }
            }
        }

        return false;

    }

   }
