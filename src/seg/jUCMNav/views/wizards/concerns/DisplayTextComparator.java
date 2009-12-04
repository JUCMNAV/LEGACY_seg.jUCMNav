package seg.jUCMNav.views.wizards.concerns;

import grl.GRLGraph;

import java.io.Serializable;
import java.util.Comparator;

import ucm.map.UCMmap;
import urncore.Concern;

/**
 * Compares two objects based on their textual representation in the UI
 * 
 * @author gunterm
 */
public class DisplayTextComparator implements Comparator, Serializable {

    private static final long serialVersionUID = 1L;
    // ranking of classes (the lower the rank, the earlier an instance will appear when sorted)
    private static final int CONCERN = 0;
    private static final int UCM = 1;
    private static final int GRL = 2;
    private static final int OTHER = 3;

    /**
     * constructor
     */
    public DisplayTextComparator() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Comparator#compare(T, T)
     */
    public int compare(Object obj1, Object obj2) {
        int ret = 0;
        // first try to compare by type
        ret = compareType(obj1, obj2);
        // if the types are the same, try comparing text
        if (ret == 0) {
            String name1 = DisplayTextHelper.getDisplayName(obj1).trim().toLowerCase();
            String name2 = DisplayTextHelper.getDisplayName(obj2).trim().toLowerCase();
            ret = name1.compareTo(name2);
        }
        return ret;
    }

    /**
     * @param obj1
     *            to compare
     * @param obj2
     *            to compare
     * @return negative int if obj1's class comes before obj2's class, positive int if obj2's class comes before obj1's class, and 0 if equal
     */
    private int compareType(Object obj1, Object obj2) {
        return getTypeOrder(obj1) - getTypeOrder(obj2);
    }

    /**
     * @param obj
     *            object
     * @return the ranking of the object's class
     */
    private int getTypeOrder(Object obj) {
        if (obj instanceof UpdatedConcern || obj instanceof Concern)
            return CONCERN;
        if (obj instanceof UpdatedDiagram)
            obj = ((UpdatedDiagram) obj).getOriginal();
        if (obj instanceof UCMmap)
            return UCM;
        else if (obj instanceof GRLGraph)
            return GRL;
        else
            return OTHER;
    }
}
