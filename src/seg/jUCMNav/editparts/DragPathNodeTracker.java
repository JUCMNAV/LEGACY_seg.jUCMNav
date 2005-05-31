package seg.jUCMNav.editparts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.tools.DragEditPartsTracker;

/**
 * Created on 31-May-2005
 * 
 * @author jkealey
 *  
 */
public class DragPathNodeTracker extends DragEditPartsTracker {
    private List exclusionSet;

    /**
     * @param sourceEditPart
     */
    public DragPathNodeTracker(EditPart sourceEditPart) {
        super(sourceEditPart);
    }

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