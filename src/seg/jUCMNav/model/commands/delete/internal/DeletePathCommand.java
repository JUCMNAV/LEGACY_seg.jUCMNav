package seg.jUCMNav.model.commands.delete.internal;

import java.util.Map;
import java.util.Vector;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.IDelayedBuildCompoundCommand;
import seg.jUCMNav.model.commands.delete.DeleteBranchesCommand;
import seg.jUCMNav.model.commands.delete.DeletePathNodeCommand;
import seg.jUCMNav.model.util.DoesDisconnectImplyDelete;
import seg.jUCMNav.model.util.modelexplore.GraphExplorer;
import seg.jUCMNav.model.util.modelexplore.queries.DeletionPathFinder;
import seg.jUCMNav.model.util.modelexplore.queries.DeletionPathFinder.QFindSpline;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.StartPoint;

/**
 * Given a StartPoint / EndPoint, find the deletion path using the query infrastructure and delete the whole path.
 * 
 * @author jkealey
 * 
 */
public class DeletePathCommand extends CompoundCommand implements IDelayedBuildCompoundCommand {

    private PathNode pnSeed;
    private NodeConnection ncSeed;
    private Map editpartregistry;
    private boolean built=false;

    public DeletePathCommand(StartPoint start, Map editpartregistry) {
        this.pnSeed = start;
        this.ncSeed = (NodeConnection) pnSeed.getSucc().get(0);
        this.editpartregistry = editpartregistry;
        // build();
    }

    public DeletePathCommand(EndPoint end, Map editpartregistry) {
        this.pnSeed = end;
        this.ncSeed = (NodeConnection) pnSeed.getPred().get(0);
        this.editpartregistry = editpartregistry;
        // build();
    }

    public void build() {
        if (built)return;
        built=true;
        // we want to make sure we aren't merging elements on the same deletion path to cause illegal loops.
        QFindSpline qry = new DeletionPathFinder().new QFindSpline(ncSeed);
        DeletionPathFinder.RSpline resp = (DeletionPathFinder.RSpline) GraphExplorer.run(qry);
        Vector nodes = resp.getPathNodes();
        Vector connections = resp.getConnections();

        if (nodes.size() == 0 || connections.size() == 0)
            return;

        PathNode otherExtremity;
        NodeConnection otherExtremityNC;

        // the pathnodes are listed in the order of a normal path from StartPoint to EndPoint.
        DoesDisconnectImplyDelete verification = null;
        if (pnSeed instanceof StartPoint) {
            assert nodes.firstElement() == pnSeed : "problem with query"; //$NON-NLS-1$
            otherExtremity = (PathNode) nodes.lastElement();
            otherExtremityNC = (NodeConnection) connections.lastElement();

            if (!(otherExtremity instanceof EndPoint)) {
                Vector v = new Vector();
                v.add(otherExtremityNC);
                verification = new DoesDisconnectImplyDelete(otherExtremity, v, new Vector());
            }
        } else {
            assert nodes.lastElement() == pnSeed : "problem with query"; //$NON-NLS-1$
            otherExtremity = (PathNode) nodes.firstElement();
            otherExtremityNC = (NodeConnection) connections.firstElement();
            if (!(otherExtremity instanceof StartPoint)) {
                Vector v = new Vector();
                v.add(otherExtremityNC);
                verification = new DoesDisconnectImplyDelete(otherExtremity, new Vector(), v);
            }
        }

        if (verification != null) {
            add(new DeleteBranchesCommand(otherExtremity, verification, editpartregistry));
        } else {
            // otherExtremity is an existing start/end point; clean it.
            add(new PreDeleteUrnModelElementCommand(otherExtremity));
        }

        for (int i = 1; i < nodes.size() - 1; i++) {
            PathNode pn = (PathNode) nodes.get(i);
            add(new DeletePathNodeCommand(pn, editpartregistry));
        }

        // clean the seed.
        add(new PreDeleteUrnModelElementCommand(pnSeed));

        // finish off with the cleaned start--nc--end
        if (pnSeed instanceof StartPoint)
            add(new DeleteStartNCEndCommand((StartPoint) pnSeed));
        else
            add(new DeleteStartNCEndCommand((EndPoint) pnSeed));

        assert canExecute() : "cannot execute??"; //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        // System.out.println("execute delete path for " + pnSeed);
        build();
        super.execute();
    }

    /**
     * true even if no commands exist because we build it as late as possible.
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        if (getCommands().size() == 0)
            return true;
        else
            return super.canExecute();
    }
}
