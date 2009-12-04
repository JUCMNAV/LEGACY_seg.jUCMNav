package seg.jUCMNav.editparts.treeEditparts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.MapComponentEditPolicy;
import seg.jUCMNav.model.util.DelegatingElementComparator;
import seg.jUCMNav.views.preferences.DisplayPreferences;
import ucm.map.Connect;
import ucm.map.PathNode;
import ucm.map.UCMmap;

/**
 * TreeEditPart for the Map.
 * 
 * @author TremblaE, gunterm
 */
public class MapTreeEditPart extends UrnModelElementTreeEditPart {

    /**
     * @param model
     *            the map
     */
    public MapTreeEditPart(UCMmap model) {
        super(model);
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractTreeEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new MapComponentEditPolicy());
    }

    /**
     * Returns list of components and pathnodes, sorted by type and name using EObjectClassNameComparator
     * 
     * Doesn't include connects
     * 
     * @see org.eclipse.gef.EditPart#getChildren()
     */
    public List getModelChildren() {
        ArrayList list = new ArrayList();
        UCMmap map = getMap();
        list.addAll(map.getContRefs());
        Vector v = new Vector();
        for (Iterator iter = map.getNodes().iterator(); iter.hasNext();) {
            PathNode element = (PathNode) iter.next();
            // Do not add the Empty point if the preference is not selected.
            if (!(element instanceof Connect) && elementShouldBeChildren(element))
                v.add(element);
        }
        list.addAll(v);

        Collections.sort(list, new DelegatingElementComparator());
        return list;
    }

    /*
     * Verify if the path node should be added to the children of the map.
     */
    private boolean elementShouldBeChildren(PathNode element) {
        return !DisplayPreferences.getInstance().isElementFiltered(element);
    }

    /**
     * @return the map being represented.
     */
    protected UCMmap getMap() {
        return ((UCMmap) getModel());
    }

    /**
     * Returns an icon representing a map.
     */
    protected Image getImage() {
        if (super.getImage() == null)
            setImage((JUCMNavPlugin.getImage("icons/ucm16.gif"))); //$NON-NLS-1$
        return super.getImage();
    }

}