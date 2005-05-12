package seg.jUCMNav.model.util;

import java.util.Comparator;

/**
 * Created on 12-May-2005
 * 
 * Converts the argumets to longs and compares them.
 * 
 * @author jkealey
 *  
 */
public class LongAsStringComparator implements Comparator {

    /*
     * (non-Javadoc)
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