package seg.jUCMNav.model.util;

import java.io.Serializable;
import java.util.Comparator;

import urncore.URNmodelElement;

/**
 * Orders URNmodelElements by ascending identifier (id) field. 
 * 
 * @author jkealey
 *  
 */
public class URNmodelElementIDComparator implements Comparator, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Sorts items by ascending ID. 

     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     * @see URNmodelElement
     */
    public int compare(Object arg0, Object arg1) {

        try {
        	if (arg0 instanceof URNmodelElement && arg1 instanceof URNmodelElement) {
        		
        		URNmodelElement element1 = (URNmodelElement) arg1;
				URNmodelElement element0 = (URNmodelElement) arg0;
				
				return Integer.parseInt(element1.getId())-Integer.parseInt(element0.getId());
        	}
        	else
        		return 0;
        

        } catch (Exception ex) {
            return 0;
        }
    }

}