package seg.jUCMNav.model.commands.delete;

import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.ComponentRef;
import ucm.map.EndPoint;
import ucm.map.InBinding;
import ucm.map.NodeConnection;
import ucm.map.OutBinding;
import ucm.map.PathGraph;
import ucm.map.StartPoint;

/**
 * This command will remove a simple path from the map. A simple path is defined as a start point, node connection and end point.
 * 
 * @author jkealey
 *  
 */
public class DeleteStartNCEndCommand extends CompoundCommand implements JUCMNavCommand {

    private StartPoint start;

    private EndPoint end;

    private NodeConnection nc;

    private PathGraph pg;

    private ComponentRef startParent, endParent;

    private boolean aborted = false;

    /**
     * 
     * @param start
     *            The simple path's start point.
     */
    public DeleteStartNCEndCommand(StartPoint start) {
        this.start = start;
    }

    /**
     * 
     * @param end
     *            The simple path's end point.
     */
    public DeleteStartNCEndCommand(EndPoint end) {
        this.end = end;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {

        if (start != null) {

            // might have already been deleted.
            if (start.getSucc().size() == 0 || !(((NodeConnection) start.getSucc().get(0)).getTarget() instanceof EndPoint)) {
                aborted = true;
                return;
            }
            nc = (NodeConnection) start.getSucc().get(0);
            end = (EndPoint) nc.getTarget();
            
            List ins = start.getInBindings();
        	for (Iterator i = ins.iterator(); i.hasNext();) {
				InBinding in = (InBinding) i.next();
				DeleteInBindingCommand cmd = new DeleteInBindingCommand(in);
				add(cmd);
			}
        } else if (end != null) {
            // might have already been deleted.
            if (end.getPred().size() == 0 || !(((NodeConnection) end.getPred().get(0)).getSource() instanceof StartPoint)) {
                aborted = true;
                return;
            }
            nc = (NodeConnection) end.getPred().get(0);
            start = (StartPoint) nc.getSource();
            
            List outs = end.getOutBindings();
            for (Iterator i = outs.iterator(); i.hasNext();) {
				OutBinding out = (OutBinding) i.next();
				DeleteOutBindingCommand cmd = new DeleteOutBindingCommand(out);
				add(cmd);
			}
        }
        
        pg = start.getPathGraph();
        startParent = start.getCompRef();
        endParent = end.getCompRef();

        testPreConditions();
        
        doRedo();
        super.execute();
        
        testPostConditions();
    }
    
    /* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	public boolean canUndo() {
		// Make sure we can undo even if we don't have any added commands
		if(getCommands().size() == 0)
			return true;
		return super.canUndo();
	}

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        if (aborted)
            return;
        testPreConditions();
        
        doRedo();
        super.redo();
        
        testPostConditions();
    }

    /**
	 * 
	 */
	private void doRedo() {
		pg.getPathNodes().remove(start);
        pg.getPathNodes().remove(end);
        pg.getNodeConnections().remove(nc);
        start.setCompRef(null);
        end.setCompRef(null);
	}

	/*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        if (aborted)
            return;
        testPostConditions();
        
        super.undo();
        
        pg.getPathNodes().add(start);
        pg.getPathNodes().add(end);
        pg.getNodeConnections().add(nc);
        start.setCompRef(startParent);
        end.setCompRef(endParent);
        
        testPreConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert start != null && nc != null && end != null && pg != null : "pre something is null"; //$NON-NLS-1$
        assert start.getSucc().size() == 1 && ((NodeConnection) start.getSucc().get(0)).getTarget() == end : "pre is simple path"; //$NON-NLS-1$
        assert pg.getPathNodes().contains(start) && pg.getPathNodes().contains(end) : "pre nodes in graph"; //$NON-NLS-1$
        assert pg.getNodeConnections().contains(nc) : "pre connection in graph"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert start != null && nc != null && end != null && pg != null : "post something is null"; //$NON-NLS-1$
        assert start.getSucc().size() == 1 && ((NodeConnection) start.getSucc().get(0)).getTarget() == end : "post is simple path"; //$NON-NLS-1$
        assert !pg.getPathNodes().contains(start) && !pg.getPathNodes().contains(end) : "post nodes not in graph"; //$NON-NLS-1$
        assert !pg.getNodeConnections().contains(nc) : "post connection not in graph"; //$NON-NLS-1$
    }

}