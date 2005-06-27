package seg.jUCMNav.model.util;

import java.util.Comparator;

import org.eclipse.emf.ecore.EObject;

import ucm.map.ComponentRef;
import ucm.map.DirectionArrow;
import ucm.map.EmptyPoint;
import ucm.map.RespRef;

/**
 * Created on 19-May-2005
 * 
 * @author jkealey
 *  
 */
public class EObjectClassNameComparator implements Comparator {

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(Object arg0, Object arg1) {

        try {
            // sort by classes first
            int i = compareClass((EObject) arg0, (EObject) arg1);

            if (i != 0)
                return i;
            else {
                // then by name if same class.
                return getSortableElementName((EObject) arg0).compareToIgnoreCase(getSortableElementName((EObject) arg1));
            }

        } catch (Exception ex) {
            return 0;
        }
    }

    public static String getSortableElementName(EObject o) {

        String s;
        // want to build a string like so: Name (ID)
        // but have to deal with special cases

        if (o instanceof ComponentRef && ((ComponentRef) o).getCompDef() != null) {
            s = ((ComponentRef) o).getCompDef().getName();
        } else if (o instanceof RespRef && ((RespRef) o).getRespDef() != null) {
            s = ((RespRef) o).getRespDef().getName();
        } else {
            try {
                Object name = o.eGet(o.eClass().getEStructuralFeature("name")); //$NON-NLS-1$
                if (name == null)
                    s = ""; //$NON-NLS-1$
                else
                    s = name.toString();
            } catch (IllegalArgumentException ex) {
                s = o.eClass().getName();
            }

        }

        try {
            Object id = o.eGet(o.eClass().getEStructuralFeature("id")); //$NON-NLS-1$
            if (id != null)
                s += " (" + id.toString() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
        } catch (IllegalArgumentException ex) {
        }

        return s;
    }

    /**
     * Compares elements by class type
     *  
     */
    private int compareClass(EObject arg0, EObject arg1) {
        int i = getClassRank(arg0) - getClassRank(arg1);

        if (i != 0)
            return i;
        else {
            return arg0.eClass().getInstanceClassName().compareToIgnoreCase(arg1.eClass().getInstanceClassName());
        }
    }

    /**
     * Associates a rank to model element classes. In a sorted list, elements should be sorted by ascending rank.
     * 
     * @param o
     * @return rank
     */
    private int getClassRank(Object o) {
        if (o instanceof EmptyPoint || o instanceof DirectionArrow) {
            return 1;
        } else
            return 0;
    }
}