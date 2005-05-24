package seg.jUCMNav.model.util;

import java.util.Comparator;

import org.eclipse.emf.ecore.EObject;

import ucm.map.ComponentRef;
import ucm.map.EmptyPoint;
import ucm.map.RespRef;
import urncore.UCMmodelElement;

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
            // if same class
            if (((EObject) arg0).eClass().getInstanceClassName().equals(((EObject) arg1).eClass().getInstanceClassName())) {

                if (arg0 instanceof ComponentRef && arg1 instanceof ComponentRef && ((ComponentRef) arg0).getCompDef()!=null && ((ComponentRef) arg1).getCompDef()!=null  ) {
                    // order by component definition names
                    return ((ComponentRef) arg0).getCompDef().getName().compareTo(((ComponentRef) arg1).getCompDef().getName());
                } else if (arg0 instanceof RespRef && arg1 instanceof RespRef && ((RespRef) arg0).getRespDef()!=null && ((RespRef) arg1).getRespDef()!=null) {
                    // order by responsibility definition names
                    return ((RespRef) arg0).getRespDef().getName().compareTo(((RespRef) arg1).getRespDef().getName());
                } else if (arg0 instanceof UCMmodelElement && arg1 instanceof UCMmodelElement) {
                    // order by ucmmodelelement name
                    return ((UCMmodelElement) arg0).getName().compareTo(((UCMmodelElement) arg1).getName());
                }
            }

            if (arg0 instanceof EmptyPoint && !(arg1 instanceof EmptyPoint)) {
                // empty point is lower in the list.
                return 1;
            } else if (arg1 instanceof EmptyPoint && !(arg0 instanceof EmptyPoint)) {
                // empty point is lower in the list.
                return -1;
            }

            // order class names alphabetically
            return ((EObject) arg0).eClass().getInstanceClassName().compareTo(((EObject) arg1).eClass().getInstanceClassName());
        } catch (Exception ex) {
            return 0;
        }
    }
}