package seg.jUCMNav.model.util;

import java.util.Collection;
import java.util.Iterator;

import ucm.map.ComponentRef;
import ucm.map.Map;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import urn.URNspec;
import urncore.ComponentElement;
import urncore.Responsibility;
import urncore.UCMmodelElement;

/**
 * This class is used to find an element in an instance of the meta model with a particular ID. This ID is supposed to be unique.
 * 
 * Created on 13-May-2005
 * 
 * @author jkealey
 *  
 */
public class URNElementFinder {

    /**
     * Given an ID, find what element it belongs to. Currently only scans component elements, responsibilities, maps, pathnodes and componentrefs.
     * 
     * If not found, returns null.
     * 
     * @param urn
     * @param id
     * @return
     */
    public static Object find(URNspec urn, String id) {

        Object o = null;
        if ((o = findComponentElement(urn, id)) != null)
            return o;
        if ((o = findResponsibility(urn, id)) != null)
            return o;
        if ((o = findMap(urn, id)) != null)
            return o;

        for (Iterator iter = urn.getUcmspec().getMaps().iterator(); iter.hasNext();) {
            Map map = (Map) iter.next();
            if ((o = findComponentRef(map, id)) != null)
                return o;
            if ((o = findPathNode(map, id)) != null)
                return o;
        }
        return o;
    }

    /**
     * Given a URN spec, find the component element having the passed id or return null.
     * 
     * @param urn
     * @param id
     * @return
     */
    public static ComponentElement findComponentElement(URNspec urn, String id) {
        return (ComponentElement) find(urn.getUrndef().getComponents(), id);
    }

    /**
     * Given a URN spec, find the responsibility having the passed id or return null.
     * 
     * @param urn
     * @param id
     * @return
     */
    public static Responsibility findResponsibility(URNspec urn, String id) {
        return (Responsibility) find(urn.getUrndef().getResponsibilities(), id);
    }

    /**
     * Given a map, find the component reference having the passed id or return null.
     * 
     * @param map
     * @param id
     * @return
     */
    public static ComponentRef findComponentRef(Map map, String id) {
        return (ComponentRef) find(map.getCompRefs(), id);
    }

    /**
     * Given a map, find the pathnode having the passed id or return null.
     * 
     * @param map
     * @param id
     * @return
     */
    public static PathNode findPathNode(Map map, String id) {
        return (PathNode) find(map.getPathGraph().getPathNodes(), id);
    }

    /**
     * Given a URN spec, find the map having the passed id or return null.
     * 
     * @param urn
     * @param id
     * @return
     */
    public static Map findMap(URNspec urn, String id) {
        return (Map) find(urn.getUcmspec().getMaps(), id);
    }

    /**
     * Given a collection of UCMmodelElements, return the oen having the passed id or return null.
     * 
     * @param c
     * @param id
     * @return
     */
    private static Object find(Collection c, String id) {

        for (Iterator iter = c.iterator(); iter.hasNext();) {
            UCMmodelElement element = (UCMmodelElement) iter.next();

            if (element.getId().equals(id))
                return element;
        }
        return null;
    }
    
    public static NodeConnection findNodeConnection(Map map, String idSource, String idTarget)
    {
        for (Iterator iter = map.getPathGraph().getNodeConnections().iterator(); iter.hasNext();) {
            NodeConnection nc = (NodeConnection) iter.next();
            
            if (nc.getSource().getId().equals(idSource) && nc.getTarget().getId().equals(idTarget))
            {
                return nc;
            }
        }
        return null;
    }

}