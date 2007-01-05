package seg.jUCMNav.model.util;

import java.util.List;

/**
 * A few utility methods for arrays and lists. 
 * 
 * @author jkealey
 *
 */
public class ArrayAndListUtils {
	public static String arrayToString(Object[] a, String separator) {
	    StringBuffer result = new StringBuffer();
	    if (a.length > 0) {
	        result.append(a[0].toString());
	        for (int i=1; i<a.length; i++) {
	            result.append(separator);
	            result.append(a[i].toString());
	        }
	    }
	    return result.toString();
	}
	
	public static String listToString(List a, String separator) {
	    StringBuffer result = new StringBuffer();
	    if (a.size() > 0) {
	        result.append(a.get(0).toString());
	        for (int i=1; i<a.size(); i++) {
	            result.append(separator);
	            result.append(a.get(i).toString());
	        }
	    }
	    return result.toString();
	}
}
