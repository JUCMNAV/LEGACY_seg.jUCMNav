package seg.jUCMNav.model.commands.delete;

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.UCMmap;

/**
 * Given a next global id, removes all Start-nc-End that were created afterwards.
 * 
 * Used to remove useless paths when performing deletion on multiple path nodes that disconnect the same branches.
 * 
 * @author jkealey
 */
public class DeleteUselessStartNCEndCommand extends CompoundCommand {

    private int nextGlobalID;
    private java.util.Map editpartregistry;
    private UCMmap map;

    public DeleteUselessStartNCEndCommand(UCMmap map, java.util.Map editpartregistry) {
        this.map = map;
        this.editpartregistry = editpartregistry;
        if (map.getUrndefinition() != null) {
            this.nextGlobalID = Integer.parseInt(map.getUrndefinition().getUrnspec().getNextGlobalID());
        }
        setLabel(Messages.getString("DeleteUselessStartNCEndCommand.deleteUselessStartEndPoints")); //$NON-NLS-1$
    }

    /**
     * Returns true even if no commands exist.
     */
    public boolean canExecute() {
        if (getCommands().size() == 0)
            return true;
        else
            return super.canExecute();
    }

    /**
     * Returns true even if no commands exist.
     */
    public boolean canUndo() {
        if (getCommands().size() == 0)
            return true;
        else
            return super.canExecute();
    }

    /**
     * Late building
     */
    public void execute() {
        build();
        super.execute();
    }

    /**
     * Builds a sequence of DeletePathNodeCommands
     * 
     */
    private void build() {
        if (map.getUrndefinition() != null) {

            for (Iterator iter = map.getNodes().iterator(); iter.hasNext();) {
                PathNode pn = (PathNode) iter.next();
                if (pn instanceof StartPoint && Integer.parseInt(pn.getId()) >= nextGlobalID && pn.getPred().size() == 0 && pn.getSucc().size() == 1) {
                    if (pn.getSucc().get(0) != null) {
                        PathNode target = (PathNode) ((NodeConnection) pn.getSucc().get(0)).getTarget();
                        if (target instanceof EndPoint && target.getSucc().size() == 0 && Integer.parseInt(target.getId()) >= nextGlobalID) {
                            add(new DeletePathNodeCommand(pn, editpartregistry));
                        } else if (target instanceof EmptyPoint && target.getSucc().size() == 1 && Integer.parseInt(target.getId()) >= nextGlobalID) {
                            target = (PathNode) ((NodeConnection) target.getSucc().get(0)).getTarget();
                            if (target instanceof EndPoint && target.getSucc().size() == 0 && Integer.parseInt(target.getId()) >= nextGlobalID) {
                                add(new DeletePathNodeCommand(pn, editpartregistry));
                            }
                        }
                    }
                }

            }
        }

    }

    /**
     * 
     * @return the next global id from which start-nc-end will be removed
     */
    public int getNextGlobalID() {
        return nextGlobalID;
    }

    /**
     * 
     * @param nextGlobalID
     *            the next global id from which start-nc-end will be removed
     */
    public void setNextGlobalID(int nextGlobalID) {
        this.nextGlobalID = nextGlobalID;
    }
}
