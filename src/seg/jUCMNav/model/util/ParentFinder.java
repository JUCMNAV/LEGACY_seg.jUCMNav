package seg.jUCMNav.model.util;

import java.util.Collections;
import java.util.Vector;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

import seg.jUCMNav.Messages;
import ucm.map.ComponentRef;
import ucm.map.Map;
import ucm.map.PathNode;
import urncore.UCMmodelElement;

/**
 * Created on 2-May-2005 When creating or moving a ComponentRef or a PathNode, one must set the parent so that bindings are preserved. Otherwise, weird things
 * will happen.
 * 
 * @author jkealey
 *  
 */
public class ParentFinder {

    /**
     * Wrapper for findParent to find the possible parent for a child already in a Map.
     * 
     * @param child
     * @return possible parent
     */
    public static ComponentRef getPossibleParent(UCMmodelElement child) {

        ComponentRef parent;
        if (child instanceof ComponentRef) {
            ComponentRef cr = (ComponentRef) child;
            assert cr.getMap() != null : Messages.getString("ParentFinder.shouldBeInModel"); //$NON-NLS-1$
            parent = ParentFinder.findParent(cr.getMap(), cr, cr.getX(), cr.getY(), cr.getWidth(), cr.getHeight());
        } else {
            PathNode p = (PathNode) child;
            assert p.getPathGraph().getMap() != null : Messages.getString("ParentFinder.shouldBeInModel"); //$NON-NLS-1$
            parent = ParentFinder.findParent(p.getPathGraph().getMap(), p.getX(), p.getY());
        }
        return parent;
    }

    /**
     * Will invoke getPossibleParent recursively to build a list of alternative parents that contain the element.
     * 
     * @param child
     * @return vector of possible parents
     */
    public static Vector getPossibleParents(UCMmodelElement child) {

        Vector parents = new Vector();
        ComponentRef parent = getPossibleParent(child);
        while (parent != null) {
            parents.add(parent);
            parent = getPossibleParent(parent);
        }
        return parents;

    }

    /**
     * We want to know who is the smallest ComponentRef at location newX, newY (our parent).
     * 
     * @param map
     *            Map containing all the possible ComponentRefs to look at.
     * @param newX
     * @param newY
     * @return a ComponentRef
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
     * We want to know who is the smallest ComponentRef that can contain the rectangle of size newWidth, newHeight at position newX, newY. Because our parent
     * cannot be ourself, we need to pass the compRef.
     * 
     * @param map
     *            Map containing all the possible ComponentRefs to look at. May or may not contain compRef
     * @param compRef
     * @param newX
     * @param newY
     * @param newWidth
     * @param newHeight
     * @return a ComponentRef
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

            // circular bindings are possible to happen in the UI if you have two components of exactly the same size, you shrink the parent so that it becomes
            // the child of its current child.
            //
            // for this reason, this is not an assertion but rather we will not allow the new binding to happen
            // 
            // furthermore, we'll break our component out of the binding chain so that it is not in an illegal state
            // 
            //assert noCircularBindings((ComponentRef) v.get(0), compRef) : "ParentFinder: Circular Binding Found!";
            if (!noCircularBindings((ComponentRef) v.get(0), compRef)) {
                compRef.setParent(null);
                compRef.getChildren().clear();
                return null;
            }

            // pick the smallest container
            return (ComponentRef) v.get(0);
        }

    }

    /**
     * We want to find the new children for a certain compRef. Obviously, will not return itself. We won't return existing children.
     * 
     * @param map
     *            Map containing all the possible ComponentRefs to look at. May or may not contain compRef
     * @param compRef
     *            The parent for which we find the children.
     * @return vector with new children of the compRef
     */
    public static Vector findNewChildren(Map map, ComponentRef compRef) {
        Rectangle rectMoved = new Rectangle(compRef.getX(), compRef.getY(), compRef.getWidth(), compRef.getHeight());

        Vector v = new Vector();
        v.addAll(map.getCompRefs());
        v.addAll(map.getPathGraph().getPathNodes());

        for (int i = v.size() - 1; i >= 0; i--) {

            if (v.get(i) instanceof ComponentRef) {
                ComponentRef cr = (ComponentRef) v.get(i);
                if (compRef == cr.getParent() || compRef != findParent(map, cr, cr.getX(), cr.getY(), cr.getWidth(), cr.getHeight())) {
                    v.remove(i);
                }
            } else if (v.get(i) instanceof PathNode) {
                PathNode pn = (PathNode) v.get(i);
                if (compRef == pn.getCompRef() || compRef != findParent(map, pn.getX(), pn.getY())) {
                    v.remove(i);
                }
            }

        }

        return v;
    }

    /**
     * Returns false if child is an ancestor of parent. If there already is a circular binding present, infinite loop will occur.
     * 
     * @param parent
     * @param child
     * @return
     */
    private static boolean noCircularBindings(ComponentRef parent, ComponentRef child) {
        //If there already is a circular binding present, infinite loop will occur.
        while (parent.getParent() != null) {
            if (parent.getParent() == child) {
                // circular binding found
                System.out.println(Messages.getString("ParentFinder.circularBinding")); //$NON-NLS-1$
                return false;
            } else
                parent = parent.getParent();
        }
        return true;
    }

}