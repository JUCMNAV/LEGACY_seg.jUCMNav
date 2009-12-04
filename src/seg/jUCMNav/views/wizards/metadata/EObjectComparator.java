package seg.jUCMNav.views.wizards.metadata;

import java.io.Serializable;
import java.util.Comparator;

import org.eclipse.emf.ecore.EObject;

import seg.jUCMNav.model.util.URNNamingHelper;
import urncore.URNmodelElement;

/**
 * To compare given EObjects
 * 
 * @author pchen
 */
public class EObjectComparator implements Comparator, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a EObjectComparator
     * 
     */
    public EObjectComparator() {
    }

    /**
     * Compares two objects (type EObject).
     * 
     * @param obj1
     *            First row to compare
     * @param obj2
     *            Second row to compare
     * @return negative if obj1 less than obj2, positive if obj1 greater than obj2, and zero if equal.
     */
    public int compare(Object obj1, Object obj2) {
        int ret = 0;

        EObject eobj1 = (EObject) obj1;
        EObject eobj2 = (EObject) obj2;
        String name1 = ""; //$NON-NLS-1$
        String name2 = ""; //$NON-NLS-1$

        if (eobj1 instanceof URNmodelElement && eobj2 instanceof URNmodelElement) {
            name1 = URNNamingHelper.getName((URNmodelElement) eobj1) + " (" + ((URNmodelElement) eobj1).getId() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
            name2 = URNNamingHelper.getName((URNmodelElement) eobj2) + " (" + ((URNmodelElement) eobj2).getId() + ")"; //$NON-NLS-1$ //$NON-NLS-2$

            ret = name1.compareTo(name2);
        } else if (eobj1 instanceof URNmodelElement) {
            ret = 1;
        } else if (eobj2 instanceof URNmodelElement) {
            ret = -1;
        } else {
            ret = 0;
        }

        return ret;
    }
}
