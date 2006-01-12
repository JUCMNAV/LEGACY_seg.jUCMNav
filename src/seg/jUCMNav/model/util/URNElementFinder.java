package seg.jUCMNav.model.util;

import java.util.Collection;
import java.util.Iterator;

import ucm.map.ComponentRef;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.UCMmap;
import urn.URNspec;
import urncore.ComponentElement;
import urncore.Responsibility;
import urncore.SpecificationDiagram;
import urncore.URNmodelElement;

/**
 * This class is used to find an element in an instance of the meta model with a particular ID. This ID is supposed to be unique.
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
     * @return element
     */
    public static Object find(URNspec urn, String id) {

        Object o = null;
        if ((o = findComponentElement(urn, id)) != null)
            return o;
        if ((o = findResponsibility(urn, id)) != null)
            return o;
        if ((o = findMap(urn, id)) != null)
            return o;

        for (Iterator iter = urn.getUrndef().getSpecDiagrams().iterator(); iter.hasNext();) {
            SpecificationDiagram g = (SpecificationDiagram) iter.next();
            if (g instanceof UCMmap) {
                UCMmap map = (UCMmap) g;
                if ((o = findComponentRef(map, id)) != null)
                    return o;
                if ((o = findPathNode(map, id)) != null)
                    return o;
            }
        }
        return o;
    }

    /**
     * Given a name, find what element it belongs to. Can only find responsibility and component definitions by name. Will return component if both component
     * and responsibility exist.
     * 
     * Has been extended to also look for diagrams by name, even if they are not currently constrained to be unique.
     * 
     * If not found, returns null.
     * 
     * @param urn
     * @param name
     * @return element
     */
    public static Object findByName(URNspec urn, String name) {

        Object o = null;
        if ((o = findComponentElementByName(urn, name)) != null)
            return o;
        if ((o = findResponsibilityByName(urn, name)) != null)
            return o;
        if ((o = findMapByName(urn, name)) != null)
            return o;

        return o;
    }

    /**
     * Given a URN spec, find the component element having the passed id or return null.
     * 
     * @param urn
     * @param id
     * @return matching component element
     */
    public static ComponentElement findComponentElement(URNspec urn, String id) {
        return (ComponentElement) find(urn.getUrndef().getComponents(), id);
    }

    /**
     * Given a URN spec, find the component element having the passed name or return null.
     * 
     * @param urn
     * @param name
     * @return matching component element
     */
    public static ComponentElement findComponentElementByName(URNspec urn, String name) {
        return (ComponentElement) findByName(urn.getUrndef().getComponents(), name);
    }

    /**
     * Given a URN spec, find the responsibility having the passed id or return null.
     * 
     * @param urn
     * @param id
     * @return matching resp
     */
    public static Responsibility findResponsibility(URNspec urn, String id) {
        return (Responsibility) find(urn.getUrndef().getResponsibilities(), id);
    }

    /**
     * Given a URN spec, find the responsibility having the passed name or return null.
     * 
     * @param urn
     * @param name
     * @return matching resp
     */
    public static Responsibility findResponsibilityByName(URNspec urn, String name) {
        return (Responsibility) findByName(urn.getUrndef().getResponsibilities(), name);
    }

    /**
     * Given a map, find the component reference having the passed id or return null.
     * 
     * @param map
     * @param id
     * @return matching ref
     */
    public static ComponentRef findComponentRef(UCMmap map, String id) {
        return (ComponentRef) find(map.getCompRefs(), id);
    }

    /**
     * Given a map, find the pathnode having the passed id or return null.
     * 
     * @param map
     * @param id
     * @return matching pathnode
     */
    public static PathNode findPathNode(UCMmap map, String id) {
        return (PathNode) find(map.getNodes(), id);
    }

    /**
     * Given a URN spec, find the map having the passed id or return null.
     * 
     * @param urn
     * @param id
     * @return matching graph
     */
    public static SpecificationDiagram findMap(URNspec urn, String id) {
        return (SpecificationDiagram) find(urn.getUrndef().getSpecDiagrams(), id);
    }

    /**
     * Given a URN spec, find the diagram having the passed name or return null.
     * 
     * Note that diagram names are not constrained to be unique. Will return the first occurence.
     * 
     * @param urn
     * @param name
     * @return matching diagram
     */
    public static SpecificationDiagram findMapByName(URNspec urn, String name) {
        return (SpecificationDiagram) findByName(urn.getUrndef().getSpecDiagrams(), name);
    }

    /**
     * Given a collection of UCMmodelElements, return the oen having the passed id or return null.
     * 
     * @param c
     * @param id
     * @return matching model elem
     */
    private static Object find(Collection c, String id) {

        for (Iterator iter = c.iterator(); iter.hasNext();) {
            URNmodelElement element = (URNmodelElement) iter.next();

            if (element.getId().equals(id))
                return element;
        }
        return null;
    }

    /**
     * Given a collection of UCMmodelElements, return the oen having the passed name or return null.
     * 
     * @param c
     * @param name
     * @return matching model elem
     */
    private static Object findByName(Collection c, String name) {

        for (Iterator iter = c.iterator(); iter.hasNext();) {
            URNmodelElement element = (URNmodelElement) iter.next();

            if (element.getName().equalsIgnoreCase(name))
                return element;
        }
        return null;
    }

    /**
     * Given a map, find the NodeConnection connected to PathNodes with the specified ids.
     * 
     * @param map
     *            the map containing the connections.
     * @param idSource
     *            the source PathNode
     * @param idTarget
     *            the target PathNode
     * @return matching node connection
     */
    public static NodeConnection findNodeConnection(UCMmap map, String idSource, String idTarget) {
        for (Iterator iter = map.getConnections().iterator(); iter.hasNext();) {
            NodeConnection nc = (NodeConnection) iter.next();

            if (((PathNode) nc.getSource()).getId().equals(idSource) && ((PathNode) nc.getTarget()).getId().equals(idTarget)) {
                return nc;
            }
        }
        return null;
    }

}