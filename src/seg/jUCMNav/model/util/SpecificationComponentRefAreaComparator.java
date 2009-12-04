package seg.jUCMNav.model.util;

import java.io.Serializable;
import java.util.Comparator;

import urncore.IURNContainerRef;

/**
 * Compares the area of SpecificationComponentRefs to know which one to put in back.
 * 
 * @author jkealey
 * 
 */
public class SpecificationComponentRefAreaComparator implements Comparator, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Sort by ascending area size.
     */
    public int compare(Object o1, Object o2) {
        IURNContainerRef c1 = (IURNContainerRef) o1;
        IURNContainerRef c2 = (IURNContainerRef) o2;
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
        IURNContainerRef c1 = (IURNContainerRef) o1;
        IURNContainerRef c2 = (IURNContainerRef) o2;
        return c1.getHeight() * c1.getWidth() == c2.getHeight() * c2.getWidth();
    }

}