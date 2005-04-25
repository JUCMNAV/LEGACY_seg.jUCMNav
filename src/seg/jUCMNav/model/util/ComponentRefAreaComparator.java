package seg.jUCMNav.model.util;

import java.util.Comparator;

import ucm.map.ComponentRef;


/**
 * Created on 4-Apr-2005
 * Compares the area of ComponentRefs to know which one to put in back.
 * @author jkealey
 *
 */
public class ComponentRefAreaComparator implements Comparator {
    public int compare(Object o1, Object o2) 
    {
        ComponentRef c1 = (ComponentRef) o1;
        ComponentRef c2 = (ComponentRef) o2;
        return c1.getHeight()*c1.getWidth()-c2.getHeight()*c2.getWidth();
    }
    public boolean equals(Object o1, Object o2) 
    {
        ComponentRef c1 = (ComponentRef) o1;
        ComponentRef c2 = (ComponentRef) o2;
        return c1.getHeight()*c1.getWidth()==c2.getHeight()*c2.getWidth();
    }
    
}
