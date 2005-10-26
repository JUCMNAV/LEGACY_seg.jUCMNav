package seg.jUCMNav.model.util;

import java.util.Comparator;

import urncore.SpecificationComponentRef;

/**
 * Compares the area of SpecificationComponentRefs to know which one to put in back.
 * 
 * @author jkealey
 *  
 */
public class SpecificationComponentRefAreaComparator implements Comparator {

    /**
     * Sort by ascending area size.
     */
    public int compare(Object o1, Object o2) {
        SpecificationComponentRef c1 = (SpecificationComponentRef) o1;
        SpecificationComponentRef c2 = (SpecificationComponentRef) o2;
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
        SpecificationComponentRef c1 = (SpecificationComponentRef) o1;
        SpecificationComponentRef c2 = (SpecificationComponentRef) o2;
        return c1.getHeight() * c1.getWidth() == c2.getHeight() * c2.getWidth();
    }

}