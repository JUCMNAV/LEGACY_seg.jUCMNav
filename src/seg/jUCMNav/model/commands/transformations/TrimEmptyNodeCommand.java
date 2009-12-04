package seg.jUCMNav.model.commands.transformations;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.delete.internal.RemovePathNodeCommand;
import seg.jUCMNav.model.util.URNNamingHelper;
import ucm.map.EmptyPoint;
import ucm.map.PathNode;
import ucm.map.UCMmap;

/**
 * This compound command deletes empty nodes/directionarrows that still have their original name and don't have a label.
 * 
 * Used by the autolayout tool.
 * 
 * @author jkealey
 * 
 */
public class TrimEmptyNodeCommand extends CompoundCommand {

    private Vector toRemove;

    /**
     * 
     * @param map
     *            the map to clean
     */
    public TrimEmptyNodeCommand(UCMmap map) {
        toRemove = new Vector();

        for (Iterator iter = map.getNodes().iterator(); iter.hasNext();) {
            PathNode pn = (PathNode) iter.next();
            if (pn instanceof EmptyPoint) {
                deleteIfPossible(pn);
            }
        }
        setLabel(Messages.getString("TrimEmptyNodeCommand.trimEmptyNode")); //$NON-NLS-1$

    }

    /**
     * Method changed to allow empty trimemptynodecommands to be executable so that they can be pushed on the stack. the external code might have been a better
     * place to check for this.
     * 
     * To change in future.
     */
    public boolean canExecute() {
        if (getCommands().size() == 0)
            return true;
        else
            return super.canExecute();
    }

    /**
     * Method changed to allow empty trimemptynodecommands to be executable so that they can be pushed on the stack. the external code might have been a better
     * place to check for this.
     * 
     * To change in future.
     */
    public boolean canUndo() {
        if (getCommands().size() == 0)
            return true;
        else
            return super.canUndo();
    }

    /**
     * Adds a DeleteNodeCommand to the compound command if this is an empty point that hasn't been named, that has no label and isn't connected to anything..
     * 
     * @param pn
     */
    private void deleteIfPossible(PathNode pn) {
        if (pn instanceof EmptyPoint && !toRemove.contains(pn) && pn.getLabel() == null && pn.getName().equals(URNNamingHelper.getPrefix(EmptyPoint.class))) {
            // don't add if is connected to something.
            if (pn.getPred().size() == 1 && pn.getSucc().size() == 1) {
                // I know we won't be using the editpartregistry to replace the empty point or direction arrow.
                add(new RemovePathNodeCommand(pn, null));
                toRemove.add(pn);
            }
        }
    }
}