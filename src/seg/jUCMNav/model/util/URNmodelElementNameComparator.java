package seg.jUCMNav.model.util;

import java.io.Serializable;
import java.util.Comparator;

import urncore.URNmodelElement;

/**
 * Orders URNmodelElements by ascending name field.
 * 
 * @author jkealey
 * 
 */
public class URNmodelElementNameComparator implements Comparator, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Sorts items by ascending name.
     * 
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     * @see URNmodelElement
     */
    public int compare(Object arg0, Object arg1) {

        try {
            if (arg0 instanceof URNmodelElement && arg1 instanceof URNmodelElement) {

                URNmodelElement element1 = (URNmodelElement) arg1;
                URNmodelElement element0 = (URNmodelElement) arg0;

                String name1 = element1.getName();
                if (name1 == null)
                    name1 = ""; //$NON-NLS-1$
                String name0 = element0.getName();
                if (name0 == null)
                    name0 = ""; //$NON-NLS-1$

                return name0.compareToIgnoreCase(name1);
            } else if (arg0 instanceof URNmodelElement)
                return -1;
            else if (arg1 instanceof URNmodelElement)
                return 1;
            else
                return 0;

        } catch (Exception ex) {
            return 0;
        }
    }

}