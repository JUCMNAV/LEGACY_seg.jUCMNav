package seg.jUCMNav.model.util;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Converts the arguments to longs and compares them numerically instead of alphabetically.
 * 
 * @author jkealey
 * 
 */
public class LongAsStringComparator implements Comparator, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Uses Long.parseLong on arguments.toString() to compare.
     * 
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Object arg0, Object arg1) {

        long first;
        try {
            first = Long.parseLong(arg0.toString());
        } catch (Exception e) {
            first = 0;
        }

        long second;
        try {
            second = Long.parseLong(arg1.toString());
        } catch (Exception e) {
            second = 0;
        }

        long diff = first - second;

        return diff < 0 ? -1 : diff == 0 ? 0 : 1;

    }

}