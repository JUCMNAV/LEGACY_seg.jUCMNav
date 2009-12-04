package seg.jUCMNav.editparts.concernsTreeEditparts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editparts.treeEditparts.MapTreeEditPart;
import seg.jUCMNav.editparts.treeEditparts.UrnModelElementTreeEditPart;
import seg.jUCMNav.model.util.DelegatingElementComparator;
import ucm.map.PathNode;
import ucm.map.Stub;
import ucm.map.UCMmap;

/**
 * TreeEditPart for a UCMmap in the Concern Outline
 * 
 * @author gunterm
 */
public class ConcernsMapTreeEditPart extends MapTreeEditPart {

    // the value is set in getImage because it is called by the framework before getModelChildren
    private boolean isAncestorOfItself;

    /**
     * @param model
     *            represents a map
     */
    public ConcernsMapTreeEditPart(UCMmap model) {
        super(model);
    }

    /**
     * @return list of stubs sorted by name or empty list if this map already occurred in the current branch of the outline tree
     * @see seg.jUCMNav.editparts.treeEditparts.MapTreeEditPart#getModelChildren()
     */
    public List getModelChildren() {
        ArrayList list = new ArrayList();
        // check if the map has already appeared in the branch of the tree above the location of
        // this TreeEditPart, only add the stubs if it hasn't to avoid an infinite loop
        if (!isAncestorOfItself) {
            for (Iterator iter = getMap().getNodes().iterator(); iter.hasNext();) {
                PathNode element = (PathNode) iter.next();
                if (element instanceof Stub)
                    list.add(element);
            }
            Collections.sort(list, new DelegatingElementComparator());
        }
        return list;
    }

    /**
     * @return an icon representing a map or an icon indicating recursion if the map already occurred in the current branch of the outline tree
     * @see seg.jUCMNav.editparts.treeEditparts.MapTreeEditPart#getImage()
     */
    protected Image getImage() {
        isAncestorOfItself = false;
        if (getParent() != null)
            isAncestorOfItself = ((UrnModelElementTreeEditPart) getParent()).isAncestor(this);
        if (isAncestorOfItself)
            setImage((JUCMNavPlugin.getImage("icons/Stop16.gif"))); //$NON-NLS-1$
        else if (super.getImage() == null)
            setImage((JUCMNavPlugin.getImage("icons/ucm16.gif"))); //$NON-NLS-1$
        return super.getImage();
    }
}