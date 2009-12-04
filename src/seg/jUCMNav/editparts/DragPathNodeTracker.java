package seg.jUCMNav.editparts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.tools.DragEditPartsTracker;

/**
 * When nodes are dragged in GEF, they explictly remove connections from being possible drop targets. By overriding DragEditPartsTracker, we allow this
 * behaviour.
 * 
 * @author jkealey
 * 
 */
public class DragPathNodeTracker extends DragEditPartsTracker {
    private List exclusionSet;

    /**
     * @param sourceEditPart
     *            what are we dragging.
     */
    public DragPathNodeTracker(EditPart sourceEditPart) {
        super(sourceEditPart);
    }

    /**
     * we removed connections from the superclass' implementation
     * 
     * @return the collection of editparts that are to be excluded as drop targets
     */
    protected Collection getExclusionSet() {
        if (exclusionSet == null) {
            List set = getOperationSet();
            exclusionSet = new ArrayList(set.size() + 1);
            for (int i = 0; i < set.size(); i++) {
                GraphicalEditPart editpart = (GraphicalEditPart) set.get(i);
                exclusionSet.add(editpart.getFigure());
            }
        }
        return exclusionSet;
    }

}