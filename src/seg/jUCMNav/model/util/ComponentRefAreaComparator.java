package seg.jUCMNav.model.util;

import java.util.Comparator;

import ucm.map.ComponentRef;

/**
 * Compares the area of ComponentRefs to know which one to put in back.
 * 
 * @author jkealey
 *  
 */
public class ComponentRefAreaComparator implements Comparator {

    /**
     * Sort by ascending area size.
     */
    public int compare(Object o1, Object o2) {
        ComponentRef c1 = (ComponentRef) o1;
        ComponentRef c2 = (ComponentRef) o2;
        return c1.getHeight() * c1.getWidth() - c2.getHeight() * c2.getWidth();
    }

    /**
     * Do they have the same area?
     * 
     * @param o1
     *            first ComponentRef
     * @param o2
     *            second ComponentRef
     * @return true if same area.
     */
    public boolean equals(Object o1, Object o2) {
        ComponentRef c1 = (ComponentRef) o1;
        ComponentRef c2 = (ComponentRef) o2;
        return c1.getHeight() * c1.getWidth() == c2.getHeight() * c2.getWidth();
    }

}