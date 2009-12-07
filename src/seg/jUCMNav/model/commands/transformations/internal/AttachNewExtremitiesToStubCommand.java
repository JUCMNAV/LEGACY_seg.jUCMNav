package seg.jUCMNav.model.commands.transformations.internal;

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.delete.DeleteNodeConnectionCommand;
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
    protected boolean built = false;
    protected boolean replaceFirst = false;

    /**
     * Between the time this command is created and its execution, the model has changed. we want to attach new start/end points in map to the stub.
     * 
     * @param map
     * @param stub
     * @param replaceFirst
     *            should we disconnect the first in/out we find?
     */
    public AttachNewExtremitiesToStubCommand(UCMmap map, Stub stub, boolean replaceFirst) {

        this.stub = stub;
        this.map = map;
        this.replaceFirst = replaceFirst;
        maxIdDuringCreation = Integer.parseInt(this.map.getUrndefinition().getUrnspec().getNextGlobalID());
    }

    public boolean canExecute() {
        return true;
    }

    public void execute() {
        if (!built) {

            boolean firstIn = true;
            boolean firstOut = true;
            for (Iterator iterator = map.getNodes().iterator(); iterator.hasNext();) {
                PathNode pn = (PathNode) iterator.next();

                if (pn instanceof EndPoint || pn instanceof StartPoint) {
                    int newid = Integer.parseInt(pn.getId());
                    if (newid >= maxIdDuringCreation) {
                        if (replaceFirst) {

                            if (SafePathChecker.isSafeFusion(pn, stub)) {
                                // get rid of the first connection
                                if (firstIn && pn instanceof EndPoint) {
                                    add(new DeleteNodeConnectionCommand((NodeConnection) stub.getPred().get(0), null));
                                    firstIn = false;
                                } else if (firstOut && pn instanceof StartPoint) {
                                    add(new DeleteNodeConnectionCommand((NodeConnection) stub.getSucc().get(0), null));
                                    firstOut = false;
                                }

                                add(new AttachBranchCommand(pn, stub));
                            }
                        }

                    }
                }
            }
            built = true;
        }
        super.execute();
    }
}
