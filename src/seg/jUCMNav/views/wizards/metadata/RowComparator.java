package seg.jUCMNav.views.wizards.metadata;

import java.io.Serializable;
import java.util.Comparator;

/**
 * To compare entries (rows) by the given column
 * 
 * @author pchen
 */
public class RowComparator implements Comparator, Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private int column;

    /**
     * Constructs a RowComparator given the column index
     * 
     * @param col
     *            The index (starting at zero) of the column
     */
    public RowComparator(int col) {
        column = col;
    }

    /**
     * Compares two rows (type String[]) using the specified column entry.
     * 
     * @param obj1
     *            First row to compare
     * @param obj2
     *            Second row to compare
     * @return negative if obj1 less than obj2, positive if obj1 greater than obj2, and zero if equal.
     */
    public int compare(Object obj1, Object obj2) {
        String[] row1 = (String[]) obj1;
        String[] row2 = (String[]) obj2;

        return row1[column].compareTo(row2[column]);
    }
}
