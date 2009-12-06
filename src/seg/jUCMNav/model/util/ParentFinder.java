package seg.jUCMNav.model.util;

import java.util.Collections;
import java.util.Vector;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

import seg.jUCMNav.Messages;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.IURNNode;
import urncore.URNmodelElement;

/**
 * When creating or moving a ComponentRef or a PathNode, one must set the parent so that bindings are preserved. Otherwise, weird things will happen.
 * 
 * See DevDocModelUtilities
 * 
 * @author jkealey
 * 
 */
public class ParentFinder {

    /**
     * Wrapper for findParent to find the possible parent for a child already in a diagram.
     * 
     * @param child
     * @return possible parent
     */
    public static IURNContainerRef getPossibleParent(URNmodelElement child) {

        IURNContainerRef parent;
        if (child instanceof IURNContainerRef) {
            IURNContainerRef cr = (IURNContainerRef) child;
            if (cr.getDiagram() == null)
                return null;
            assert cr.getDiagram() != null : Messages.getString("ParentFinder.shouldBeInModel"); //$NON-NLS-1$
            parent = ParentFinder.findParent(cr.getDiagram(), cr, cr.getX(), cr.getY(), cr.getWidth(), cr.getHeight());
        } else {
            IURNNode p = (IURNNode) child;
            if (p.getDiagram() == null)
                return null;
            assert p.getDiagram() != null : Messages.getString("ParentFinder.shouldBeInModel"); //$NON-NLS-1$
            parent = ParentFinder.findParent(p.getDiagram(), p.getX(), p.getY());
        }
        return parent;
    }

    /**
     * Will invoke getPossibleParent recursively to build a list of alternative parents that contain the element.
     * 
     * @param child
     * @return vector of possible parents
     */
    public static Vector getPossibleParents(URNmodelElement child) {

        Vector parents = new Vector();
        IURNContainerRef parent = getPossibleParent(child);
        while (parent != null) {
            if (!parents.contains(parent)) {
                parents.add(parent);
                parent = getPossibleParent((URNmodelElement) parent);
            }
            else 
                break; // infinite loop detected. 
        }
        return parents;

    }

    /**
     * Finds the most restrictive common parent.
     * 
     * @param ref1
     *            a component reference
     * @param ref2
     *            another component reference
     * @return their closet common parent
     */
    public static IURNContainerRef getCommonParent(IURNContainerRef ref1, IURNContainerRef ref2) {
        Vector parents1 = new Vector();
        Vector parents2 = new Vector();

        if (ref1 == null || ref2 == null)
            return null;
        IURNContainerRef parent = ref1;
        while (parent != null) {
            parents1.add(0, parent);
            parent = parent.getParent();
        }

        parent = ref2;
        while (parent != null) {
            parents2.add(0, parent);
            parent = parent.getParent();
        }

        int i;
        for (i = 0; i < Math.min(parents1.size(), parents2.size()); i++) {
            if (parents1.get(i) != parents2.get(i))
                break;
        }
        i = i - 1;
        if (i >= 0)
            return (IURNContainerRef) parents1.get(i);
        else
            return null;

    }

    /**
     * We want to know who is the smallest ComponentRef at location newX, newY (our parent).
     * 
     * @param diagram
     *            Diagram containing all the possible ComponentRefs to look at.
     * @param newX
     * @param newY
     * @return a SpecificationComponentRef
     */
    public static IURNContainerRef findParent(IURNDiagram diagram, int newX, int newY) {
        Vector v = new Vector();
        Point p = new Point(newX, newY);

        // get the components that contain the moved object.
        // this is a cheap O(number of components in map) trick to avoid having to use the edit parts
        for (int i = 0; i < diagram.getContRefs().size(); i++) {
            IURNContainerRef cr = (IURNContainerRef) diagram.getContRefs().get(i);
            if ((new Rectangle(cr.getX(), cr.getY(), cr.getWidth(), cr.getHeight())).contains(p)) {
                v.add(cr);
            }
        }

        if (v.size() == 0)
            return null; // is an orphan
        else {
            // sort them by ascending order
            Collections.sort(v, new SpecificationComponentRefAreaComparator());
            // pick the smallest container
            return (IURNContainerRef) v.get(0);
        }
    }

    /**
     * We want to know who is the smallest ComponentRef that can contain the rectangle of size newWidth, newHeight at position newX, newY. Because our parent
     * cannot be ourself, we need to pass the compRef.
     * 
     * @param diagram
     *            Diagram containing all the possible ComponentRefs to look at. May or may not contain compRef
     * @param compRef
     * @param newX
     * @param newY
     * @param newWidth
     * @param newHeight
     * @return a SpecificationComponentRef
     */
    public static IURNContainerRef findParent(IURNDiagram diagram, IURNContainerRef compRef, int newX, int newY, int newWidth, int newHeight) {
        Rectangle rectMoved = new Rectangle(newX, newY, newWidth, newHeight);

        Vector v = new Vector();
        // get the components that contain the moved object.
        for (int i = 0; i < diagram.getContRefs().size(); i++) {
            IURNContainerRef cr = (IURNContainerRef) diagram.getContRefs().get(i);
            if ((new Rectangle(cr.getX(), cr.getY(), cr.getWidth(), cr.getHeight())).contains(rectMoved) && !compRef.equals(cr)) {
                v.add(cr);
            }
        }

        if (v.size() == 0)
            return null;
        else {
            // sort them by ascending order
            Collections.sort(v, new SpecificationComponentRefAreaComparator());

            // circular bindings are possible to happen in the UI if you have two components of exactly the same size, you shrink the parent so that it becomes
            // the child of its current child.
            //
            // for this reason, this is not an assertion but rather we will not allow the new binding to happen
            // 
            // furthermore, we'll break our component out of the binding chain so that it is not in an illegal state
            // 
            // assert noCircularBindings((SpecificationComponentRef) v.get(0), compRef) : "ParentFinder: Circular Binding Found!";
            if (!noCircularBindings((IURNContainerRef) v.get(0), compRef)) {
                compRef.setParent(null);
                compRef.getChildren().clear();
                return null;
            }

            // pick the smallest container
            return (IURNContainerRef) v.get(0);
        }

    }

    /**
     * We want to find the new children for a certain compRef. Obviously, will not return itself. We won't return existing children.
     * 
     * @param diagram
     *            Diagram containing all the possible ComponentRefs to look at. May or may not contain compRef
     * @param compRef
     *            The parent for which we find the children.
     * @return vector with new children of the compRef
     */
    public static Vector findNewChildren(IURNDiagram diagram, IURNContainerRef compRef) {
        Vector v = new Vector();
        v.addAll(diagram.getContRefs());
        v.addAll(diagram.getNodes());

        for (int i = v.size() - 1; i >= 0; i--) {

            if (v.get(i) instanceof IURNContainerRef) {
                IURNContainerRef cr = (IURNContainerRef) v.get(i);
                if (compRef == cr.getParent() || compRef != findParent(diagram, cr, cr.getX(), cr.getY(), cr.getWidth(), cr.getHeight())) {
                    v.remove(i);
                }
            } else if (v.get(i) instanceof IURNNode) {
                IURNNode pn = (IURNNode) v.get(i);
                if (compRef == pn.getContRef() || compRef != findParent(diagram, pn.getX(), pn.getY())) {
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
     * @return a boolean = false if child is an ancestor parent
     */
    private static boolean noCircularBindings(IURNContainerRef parent, IURNContainerRef child) {
        // If there already is a circular binding present, infinite loop will occur.
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