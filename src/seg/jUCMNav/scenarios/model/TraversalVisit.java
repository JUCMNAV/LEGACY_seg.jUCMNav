package seg.jUCMNav.scenarios.model;

import java.util.Iterator;
import java.util.Vector;

import ucm.map.ComponentRef;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.PluginBinding;
import ucm.map.UCMmap;
import urncore.IURNContainer;
import urncore.IURNContainerRef;

/**
 * Represents a PathNode that mst be visited by the traversal engine.
 * 
 * @author jkealey
 */
public class TraversalVisit {

    private Vector context;
    private Vector parentComponentRefs;
    private NodeConnection source;
    private int threadID;
    private PathNode visitedElement;

    /**
     * Must traverse obj in threadID, given a certain context.
     * 
     * @param obj
     *            the path node to traverse.
     * @param source
     *            we traversed this node connection to get to obj
     * @param context
     *            a series of {@link PluginBinding}s that were traversed to get here.
     * @param threadID
     *            the thread id.
     */
    public TraversalVisit(NodeConnection source, PathNode obj, Vector context, int threadID) {
        this.source = source;
        this.visitedElement = obj;
        this.context = context;
        this.threadID = threadID;
    }

    /**
     * Must traverse obj in threadID, given a certain context.
     * 
     * @param obj
     *            the path node to traverse.
     * @param context
     *            a series of pluginbindings that were traversed to get here.
     * @param threadID
     *            the thread id.
     */
    public TraversalVisit(PathNode obj, Vector context, int threadID) {
        this.visitedElement = obj;
        this.context = context;
        this.threadID = threadID;
    }

    /**
     * Recursively build a List of parent {@link ComponentRef}s by going back up the context.  
     * @param pn the initial node. 
     * @param list where to add the parent components. 
     */
    protected void addComponentRefs(PathNode pn, Vector list) {
        ComponentRef ref = (ComponentRef) pn.getContRef();
        // add all of pathnode's containers.
        while (ref != null) {
            list.add(ref);
            ref = (ComponentRef) ref.getParent();
        }

        // recurse up the plugin stack to add more components.
        // TODO: this is imperfect... does not work when you have increased the context from different maps..
        // only picks one ancestor and moves up; not much we can do about it though.
        for (Iterator iter = ((UCMmap) pn.getDiagram()).getParentStub().iterator(); iter.hasNext();) {
            PluginBinding element = (PluginBinding) iter.next();
            if (context != null && context.contains(element)) {
                addComponentRefs(element.getStub(), list);
                break;
            }

        }

    }

    /**
     * 
     * @return the list of {@link PluginBinding}s
     */
    public Vector getContext() {
        return context;
    }

    /**
     * 
     * @return The closest component definition to which this element is bound
     */
    public IURNContainer getParentComponentDef() {
        IURNContainerRef ref = getParentComponentRef();
        if (ref == null)
            return null;
        else
            return ref.getContDef();
    }

    
    /**
     * 
     * @return The closest component reference to which this element is bound
     */
    public IURNContainerRef getParentComponentRef() {
        Vector v = getParentComponentRefs();

        if (v.size() == 0)
            return null;
        else
            return (IURNContainerRef) v.get(0);
    }

    /**
     * 
     * @return the list of component references to which this element is bound
     */
    public Vector getParentComponentRefs() {
        if (parentComponentRefs != null)
            return parentComponentRefs;
        Vector list = new Vector();

        addComponentRefs(visitedElement, list);

        parentComponentRefs = list;
        return list;

    }

    /**
     * 
     * @return the node connection that was traversed to get to here. 
     */
    public NodeConnection getSourceNodeConnection() {
        return this.source;
    }

    /**
     * 
     * @return the thread ID
     */
    public int getThreadID() {
        return threadID;
    }

    /**
     * 
     * @return the path node that must be visited.  
     */
    public PathNode getVisitedElement() {
        return visitedElement;
    }

    /**
     * 
     * @param v  add a list of {@link PluginBinding}s to the internal context.   
     */
    public void increaseContext(Vector v) {
        for (Iterator iter = v.iterator(); iter.hasNext();) {
            PluginBinding binding = (PluginBinding) iter.next();
            if (!context.contains(binding))
                context.add(binding);
        }
    }


    /**
     * We called {@link #getParentComponentRef()} or {@link #getParentComponentDef()} and obtained a component reference/definition. Are there any ambiguities on the possible parent?
     * 
     * @return is the closest parent unique?  
     */
    public boolean isValidParentComponent() {

        if (context == null)
            return true;
        else {
            boolean b = false;
            for (Iterator iter = context.iterator(); iter.hasNext();) {
                PluginBinding binding = (PluginBinding) iter.next();
                if (binding.getStub().getContRef() != null) {
                    if (b)
                        return false;
                    else
                        b = true;
                }
            }

            return true;
        }
    }

   
}
