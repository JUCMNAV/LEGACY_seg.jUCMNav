package seg.jUCMNav.model.commands.delete.internal;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.ComponentRef;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.StartPoint;
import ucm.map.UCMmap;

/**
 * This command will remove a simple path from the map. A simple path is defined as a start point, node connection and end point.
 * 
 * All other relationships must have been removed previously.
 * 
 * The real use of this command is removing a small path when one of its extremities has not been defined when the command is created, only when it is executed.
 * 
 * @author jkealey
 * 
 */
public class DeleteStartNCEndCommand extends Command implements JUCMNavCommand {

    private boolean aborted = false;
    private EndPoint end;
    private NodeConnection nc;
    private UCMmap pg;
    private StartPoint start;
    private ComponentRef startParent, endParent;

    /**
     * 
     * @param end
     *            The simple path's end point.
     */
    public DeleteStartNCEndCommand(EndPoint end) {
        this.end = end;
    }

    /**
     * 
     * @param start
     *            The simple path's start point.
     */
    public DeleteStartNCEndCommand(StartPoint start) {
        this.start = start;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        build();
        if (!aborted)
            redo();
    }

    public void build() {
        if (start != null) {

            // might have already been deleted.
            if (start.getSucc().size() == 0 || !(((NodeConnection) start.getSucc().get(0)).getTarget() instanceof EndPoint)) {
                aborted = true;
                return;
            }
            nc = (NodeConnection) start.getSucc().get(0);
            end = (EndPoint) nc.getTarget();

        } else if (end != null) {
            // might have already been deleted.
            if (end.getPred().size() == 0 || !(((NodeConnection) end.getPred().get(0)).getSource() instanceof StartPoint)) {
                aborted = true;
                return;
            }
            nc = (NodeConnection) end.getPred().get(0);
            start = (StartPoint) nc.getSource();
        }

        if (start == null || end == null) {
            aborted = true;
            return;
        }
        pg = (UCMmap) start.getDiagram();
        startParent = (ComponentRef) start.getContRef();
        endParent = (ComponentRef) end.getContRef();

        if (pg == null) {
            aborted = true;
            return;
        }
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        if (aborted)
            return;
        testPreConditions();

        pg.getNodes().remove(start);
        pg.getNodes().remove(end);
        pg.getConnections().remove(nc);
        start.setContRef(null);
        end.setContRef(null);

        testPostConditions();
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert start != null && nc != null && end != null && pg != null : "post something is null"; //$NON-NLS-1$
        assert start.getSucc().size() == 1 && ((NodeConnection) start.getSucc().get(0)).getTarget() == end : "post is simple path"; //$NON-NLS-1$
        assert !pg.getNodes().contains(start) && !pg.getNodes().contains(end) : "post nodes not in graph"; //$NON-NLS-1$
        assert !pg.getConnections().contains(nc) : "post connection not in graph"; //$NON-NLS-1$
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert start != null && nc != null && end != null && pg != null : "pre something is null"; //$NON-NLS-1$
        assert start.getSucc().size() == 1 && ((NodeConnection) start.getSucc().get(0)).getTarget() == end : "pre is simple path"; //$NON-NLS-1$
        assert pg.getNodes().contains(start) && pg.getNodes().contains(end) : "pre nodes in graph"; //$NON-NLS-1$
        assert pg.getConnections().contains(nc) : "pre connection in graph"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        if (aborted)
            return;
        testPostConditions();

        pg.getNodes().add(start);
        pg.getNodes().add(end);
        pg.getConnections().add(nc);
        start.setContRef(startParent);
        end.setContRef(endParent);

        testPreConditions();
    }

    public EndPoint getEnd() {
        return end;
    }

    public void setEnd(EndPoint end) {
        this.end = end;
    }

    public StartPoint getStart() {
        return start;
    }

    public void setStart(StartPoint start) {
        this.start = start;
    }

    public boolean isAborted() {
        return aborted;
    }

    public void setAborted(boolean aborted) {
        this.aborted = aborted;
    }

}