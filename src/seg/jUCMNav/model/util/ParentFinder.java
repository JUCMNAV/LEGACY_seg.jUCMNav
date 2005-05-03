package seg.jUCMNav.model.util;

import java.util.Collections;
import java.util.Vector;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

import ucm.map.ComponentRef;
import ucm.map.Map;

/**
 * Created on 2-May-2005
 * When creating or moving a ComponentRef or a PathNode, one must set the parent so that bindings are preserved. 
 * Otherwise, weird things will happen. 
 * 
 * @author jkealey
 *  
 */
public class ParentFinder {

    /**
     * We want to know who is the smallest ComponentRef at location newX, newY (our parent).
     * @param map Map containing all the possible ComponentRefs to look at.  
     * @param newX
     * @param newY
     * @return
     */
    public static ComponentRef findParent(Map map, int newX, int newY) {
        Vector v = new Vector();
        Point p = new Point(newX, newY);

        // get the components that contain the moved object.
        // this is a cheap O(number of components in map) trick to avoid having to use the edit parts
        for (int i = 0; i < map.getCompRefs().size(); i++) {
            ComponentRef cr = (ComponentRef) map.getCompRefs().get(i);
            if ((new Rectangle(cr.getX(), cr.getY(), cr.getWidth(), cr.getHeight())).contains(p)) {
                v.add(cr);
            }
        }

        if (v.size() == 0)
            return null; // is an orphan
        else {
            // sort them by ascending order
            Collections.sort(v, new ComponentRefAreaComparator());
            // pick the smallest container
            return (ComponentRef) v.get(0);
        }
    }
    
    /**
     * We want to know who is the smallest ComponentRef that can contain the rectangle of size newWidth, newHeight at position newX, newY.
     * Because our parent cannot be ourself, we need to pass the compRef. 
     * 
     * @param map Map containing all the possible ComponentRefs to look at. May or may not contain compRef  
     * @param compRef
     * @param newX
     * @param newY
     * @param newWidth
     * @param newHeight
     * @return
     */
    public static ComponentRef findParent(Map map, ComponentRef compRef, int newX, int newY, int newWidth, int newHeight) {
        Rectangle rectMoved = new Rectangle(newX, newY, newWidth, newHeight);
        
        Vector v = new Vector();
        // get the components that contain the moved object.
        for (int i = 0; i < map.getCompRefs().size(); i++) {
            ComponentRef cr = (ComponentRef) map.getCompRefs().get(i);
            if ((new Rectangle(cr.getX(), cr.getY(), cr.getWidth(), cr.getHeight())).contains(rectMoved) && !compRef.equals(cr)) {
                v.add(cr);
            }
        }

        if (v.size() == 0)
            return null;
        else {
            // sort them by ascending order
            Collections.sort(v, new ComponentRefAreaComparator());
            
            assert noCircularBindings((ComponentRef) v.get(0), compRef) : "ParentFinder: Circular Binding Found!";
            
            // pick the smallest container
            return (ComponentRef) v.get(0);
        }
        

    }    
    
    /**
     * Returns false if child is an ancestor of parent. If there already is a circular binding present, infinite loop will occur.
     * 
     * @param parent
     * @param child
     * @return
     */
    private static boolean noCircularBindings(ComponentRef parent, ComponentRef child)
    {
        //If there already is a circular binding present, infinite loop will occur. 
        while (parent.getParent()!=null)
        {
            if (parent.getParent()==child)
            {
                // circular binding found
                return false;
            }
            else
                parent=parent.getParent();
        }
        return true;
    }
    
}