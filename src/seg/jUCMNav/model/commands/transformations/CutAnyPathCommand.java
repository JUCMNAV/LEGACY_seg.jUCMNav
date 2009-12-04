package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.ModelCreationFactory;
import ucm.map.DirectionArrow;
import ucm.map.EmptyPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.UCMmap;
import urn.URNspec;

public class CutAnyPathCommand extends CompoundCommand {
    private UCMmap pg;
    private URNspec urn;
    private NodeConnection link;

    public CutAnyPathCommand(UCMmap pg, NodeConnection link, int x, int y) {
        this.pg = pg;
        this.link = link;
        urn = pg.getUrndefinition().getUrnspec();
        EmptyPoint newPoint = (EmptyPoint) ModelCreationFactory.getNewObject(urn, EmptyPoint.class);
        EmptyPoint newPoint2 = (EmptyPoint) ModelCreationFactory.getNewObject(urn, EmptyPoint.class);
        NodeConnection newConn = (NodeConnection) ModelCreationFactory.getNewObject(urn, NodeConnection.class);

        SplitLinkCommand cmd1 = new SplitLinkCommand(pg, newPoint, link, newConn, x - 20, y - 20);
        add(cmd1);

        SplitLinkCommand cmd2 = new SplitLinkCommand(pg, newPoint2, newConn, x + 20, y + 20);
        add(cmd2);

        CutPathCommand cmd3 = new CutPathCommand(pg, newConn);
        add(cmd3);
    }

    public CutAnyPathCommand(UCMmap pg, PathNode ep, int x, int y) {
        this.pg = pg;
        this.link = (NodeConnection) ep.getPred().get(0);
        urn = pg.getUrndefinition().getUrnspec();
        EmptyPoint newPoint = (EmptyPoint) ModelCreationFactory.getNewObject(urn, EmptyPoint.class);
        NodeConnection newConn = (NodeConnection) ModelCreationFactory.getNewObject(urn, NodeConnection.class);

        SplitLinkCommand cmd1 = new SplitLinkCommand(pg, newPoint, link, newConn, x - 20, y - 20);
        add(cmd1);

        CutPathCommand cmd3 = new CutPathCommand(pg, newConn);
        add(cmd3);
    }

    public boolean canExecute() {
        return true;
    }

    public static boolean canExecute(Object p) {
        // check if no connects
        return p instanceof NodeConnection || (p instanceof EmptyPoint && ((EmptyPoint) p).getPred().size() == 1 && ((EmptyPoint) p).getSucc().size() == 1)
                || p instanceof DirectionArrow;
    }
}
