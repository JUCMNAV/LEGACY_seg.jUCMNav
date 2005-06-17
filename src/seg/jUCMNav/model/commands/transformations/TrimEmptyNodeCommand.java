package seg.jUCMNav.model.commands.transformations;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.delete.DeleteNodeCommand;
import seg.jUCMNav.model.util.URNNamingHelper;
import ucm.map.EmptyPoint;
import ucm.map.Map;
import ucm.map.PathNode;

/**
 * Created on 16-May-2005
 * 
 * This compound command deletes empty nodes that still have their original name and don't have a label.
 * 
 * Used by the autolayout tool.
 * 
 * @author jkealey
 *  
 */
public class TrimEmptyNodeCommand extends CompoundCommand {

    private Vector toRemove;

    public TrimEmptyNodeCommand(Map map) {
        toRemove = new Vector();

        for (Iterator iter = map.getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode pn = (PathNode) iter.next();
            if (pn instanceof EmptyPoint) {
                deleteIfPossible(pn);
            }
        }

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
                add(new DeleteNodeCommand(pn));
                toRemove.add(pn);
            }
        }
    }
}