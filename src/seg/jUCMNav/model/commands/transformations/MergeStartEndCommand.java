package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.delete.internal.PreDeleteUrnModelElementCommand;
import seg.jUCMNav.model.commands.transformations.internal.DoMergeCommand;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.StartPoint;
import ucm.map.UCMmap;

/**
 * Will merge a start point and end point into an empty point located at x,y;
 * 
 * Will get rid of any plugin bindings associated with the start/end points.
 * 
 * @author jkealey
 * 
 */
public class MergeStartEndCommand extends CompoundCommand {

    private DoMergeCommand cmd;

    /**
     * @param map
     *            the map containing the elements
     * @param sp
     *            the startpoint to merge
     * @param ep
     *            the endpoint to merge
     * @param x
     *            where the new empty point should be created.
     * @param y
     *            where the new empty point should be created
     * 
     */
    public MergeStartEndCommand(UCMmap map, StartPoint sp, EndPoint ep, int x, int y) {
        add(new PreDeleteUrnModelElementCommand(sp));
        add(new PreDeleteUrnModelElementCommand(ep));
        cmd = new DoMergeCommand(map, sp, ep, x, y);
        add(cmd);
        setLabel(Messages.getString("MergeStartEndCommand.mergeStartEnd")); //$NON-NLS-1$
    }

    public EmptyPoint getNewEmptyPoint() {
        return cmd.getNewEmptyPoint();
    }
}