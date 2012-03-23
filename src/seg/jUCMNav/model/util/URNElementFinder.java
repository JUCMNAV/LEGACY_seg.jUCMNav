package seg.jUCMNav.model.util;

import grl.Actor;
import grl.ActorRef;
import grl.ContributionContext;
import grl.ContributionContextGroup;
import grl.EvaluationStrategy;
import grl.GRLGraph;
import grl.GRLNode;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.StrategiesGroup;
import grl.kpimodel.KPIInformationElement;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

import seg.jUCMNav.views.preferences.DisplayPreferences;
import ucm.map.ComponentRef;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.UCMmap;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;
import ucm.scenario.Variable;
import urn.URNspec;
import urncore.Component;
import urncore.GRLmodelElement;
import urncore.IURNConnection;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.IURNNode;
import urncore.Responsibility;
import urncore.URNmodelElement;

/**
 * This class is used to find an element in an instance of the meta model with a particular ID. This ID is supposed to be unique.
 * 
 * @author jkealey, pchen
 * 
 */
public class URNElementFinder {

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
        if ((o = findComponent(urn, id)) != null)
            return o;
        if ((o = findResponsibility(urn, id)) != null)
            return o;
        if ((o = findMap(urn, id)) != null)
            return o;
        if ((o = findScenario(urn, id)) != null)
            return o;
        if ((o = findScenarioGroup(urn, id)) != null)
            return o;
        if ((o = findStrategy(urn, id)) != null)
            return o;
        if ((o = findStrategyGroup(urn, id)) != null)
            return o;
        if ((o = findActor(urn, id)) != null) // faster
            return o;
        if ((o = findGRLmodelElement(urn, id)) != null)
            return o;

        for (Iterator iter = urn.getUrndef().getSpecDiagrams().iterator(); iter.hasNext();) {
            IURNDiagram g = (IURNDiagram) iter.next();
            if (g instanceof UCMmap) {
                UCMmap map = (UCMmap) g;
                if ((o = findComponentRef(map, id)) != null)
                    return o;
                if ((o = findPathNode(map, id)) != null)
                    return o;
            } else if (g instanceof GRLGraph) {
                GRLGraph graph = (GRLGraph) g;
                if ((o = findActorRef(graph, id)) != null)
                    return o;
                if ((o = findGRLNode(graph, id)) != null)
                    return o;
            }
        }
        return null;
    }

    /**
     * Given a URN spec, find the actor element having the passed id or return null.
     * 
     * @param urn
     * @param id
     * @return matching actor element
     */
    public static Actor findActor(URNspec urn, String id) {
        return (Actor) find(urn.getGrlspec().getActors(), id);
    }

    /**
     * Given a URN spec, find the actor having the passed name or return null.
     * 
     * @param urn
     * @param name
     * @return matching actor element
     */
    public static Actor findActorByName(URNspec urn, String name) {
        return (Actor) findByName(urn.getGrlspec().getActors(), name);
    }

    /**
     * Given a graph, find the actor reference having the passed id or return null.
     * 
     * @param graph
     * @param id
     * @return matching ref
     */
    public static ActorRef findActorRef(GRLGraph graph, String id) {
        return (ActorRef) find(graph.getContRefs(), id);
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

            if (URNNamingHelper.getName(element).equalsIgnoreCase(name))
                return element;
        }
        return null;
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
        if ((o = findComponentByName(urn, name)) != null)
            return o;
        if ((o = findResponsibilityByName(urn, name)) != null)
            return o;
        if ((o = findMapByName(urn, name)) != null)
            return o;

        return null;
    }

    public static Collection findAllByNamePattern(URNspec urn, String name) {
        Vector elements = new Vector();
        elements.addAll(urn.getUrndef().getComponents());
        elements.addAll(urn.getUrndef().getResponsibilities());
        elements.addAll(urn.getUcmspec().getVariables());
        elements.addAll(urn.getUcmspec().getResources());
        elements.addAll(urn.getUcmspec().getScenarioGroups());
        for (Iterator iterator = urn.getUcmspec().getScenarioGroups().iterator(); iterator.hasNext();) {
            ScenarioGroup sg = (ScenarioGroup) iterator.next();
            elements.addAll(sg.getScenarios());
        }
        elements.addAll(urn.getUcmspec().getEnumerationTypes());

        elements.addAll(urn.getGrlspec().getActors());
        elements.addAll(urn.getGrlspec().getIntElements());
        elements.addAll(urn.getGrlspec().getKpiInformationElements());
        elements.addAll(urn.getUrndef().getSpecDiagrams());

        for (Iterator iterator = urn.getUrndef().getSpecDiagrams().iterator(); iterator.hasNext();) {
            IURNDiagram d = (IURNDiagram) iterator.next();
            if (d instanceof UCMmap)
                elements.addAll(((UCMmap) d).getNodes());
            else
                elements.addAll(((GRLGraph) d).getNodes());
        }

        Vector filteredElements = new Vector();
        for (Iterator iterator = elements.iterator(); iterator.hasNext();) {
            URNmodelElement model = (URNmodelElement) iterator.next();
            // todo: enhance to support patterns, etc.
            if (doesElementMatchPattern(name, model)) {
                filteredElements.add(model);
            }
        }
        return filteredElements;

    }

    public static boolean doesElementMatchPattern(String name, URNmodelElement model) {
        if (model instanceof IURNDiagram) // if has child that matches, it matches.
        {
            IURNDiagram mmap = (IURNDiagram) model;
            for (Iterator iterator = mmap.getNodes().iterator(); iterator.hasNext();) {
                URNmodelElement pn = (URNmodelElement) iterator.next();
                if (doesElementMatchPattern(name, pn) && !DisplayPreferences.getInstance().isElementFiltered(pn, false))
                    return true;
            }
            for (Iterator iterator = mmap.getContRefs().iterator(); iterator.hasNext();) {
                URNmodelElement pn = (URNmodelElement) iterator.next();
                if (doesElementMatchPattern(name, pn) && !DisplayPreferences.getInstance().isElementFiltered(pn, false))
                    return true;
            }
        }

        return URNNamingHelper.getName(model).toLowerCase().indexOf(name.toLowerCase()) >= 0;
    }

    /**
     * Given a URN spec, find the component element having the passed id or return null.
     * 
     * @param urn
     * @param id
     * @return matching component element
     */
    public static Component findComponent(URNspec urn, String id) {
        return (Component) find(urn.getUrndef().getComponents(), id);
    }

    /**
     * Given a URN spec, find the component element having the passed name or return null.
     * 
     * @param urn
     * @param name
     * @return matching component element
     */
    public static Component findComponentByName(URNspec urn, String name) {
        return (Component) findByName(urn.getUrndef().getComponents(), name);
    }

    /**
     * Given a map, find the component reference having the passed id or return null.
     * 
     * @param map
     * @param id
     * @return matching ref
     */
    public static ComponentRef findComponentRef(UCMmap map, String id) {
        return (ComponentRef) find(map.getContRefs(), id);
    }
    
    /**
     * Given a map, find the component reference having the passed id or return null.
     * 
     * @param map
     * @param id
     * @return matching ref
     */
    public static ComponentRef findComponentRefByName(UCMmap map, String name) {
        return (ComponentRef) findByName(map.getContRefs(), name);
    }

    /**
     * Given a map, find the Connection connected to Nodes with the specified ids.
     * 
     * @param map
     *            the map containing the connections.
     * @param idSource
     *            the source IURNNode
     * @param idTarget
     *            the target IURNNode
     * @return matching connection
     */
    public static IURNConnection findConnection(IURNDiagram map, String idSource, String idTarget) {
        for (Iterator iter = map.getConnections().iterator(); iter.hasNext();) {
            IURNConnection nc = (IURNConnection) iter.next();

            if (((URNmodelElement) nc.getSource()).getId().equals(idSource) && ((URNmodelElement) nc.getTarget()).getId().equals(idTarget)) {
                return nc;
            }
        }
        return null;
    }

    /**
     * Given a map, find the container reference having the passed id or return null.
     * 
     * @param map
     * @param id
     * @return matching ref
     */
    public static IURNContainerRef findContainerRef(IURNDiagram map, String id) {
        return (IURNContainerRef) find(map.getContRefs(), id);
    }

    /**
     * Given a URN spec, find the component element having the passed id or return null.
     * 
     * @param urn
     * @param id
     * @return matching component element
     */
    public static GRLmodelElement findGRLmodelElement(URNspec urn, String id) {
        GRLmodelElement grlmodelElement = null;

        if (((grlmodelElement = (GRLmodelElement) find(urn.getGrlspec().getIndicatorGroup(), id)) != null)
                || ((grlmodelElement = (GRLmodelElement) find(urn.getGrlspec().getGroups(), id)) != null)
                || ((grlmodelElement = (GRLmodelElement) find(urn.getGrlspec().getActors(), id)) != null)
                || ((grlmodelElement = (GRLmodelElement) find(urn.getGrlspec().getIntElements(), id)) != null)
                || ((grlmodelElement = (GRLmodelElement) find(urn.getGrlspec().getKpiInformationElements(), id)) != null)
                || ((grlmodelElement = (GRLmodelElement) find(urn.getGrlspec().getKpiModelLinks(), id)) != null)
                || ((grlmodelElement = (GRLmodelElement) find(urn.getGrlspec().getLinks(), id)) != null)
                || ((grlmodelElement = (GRLmodelElement) find(urn.getGrlspec().getStrategies(), id)) != null)
                || ((grlmodelElement = (GRLmodelElement) find(urn.getGrlspec().getContributionContexts(), id)) != null)
                || ((grlmodelElement = (GRLmodelElement) find(urn.getGrlspec().getContributionGroups(), id)) != null)) {
            return grlmodelElement;
        } else {
            return null;
        }

    }

    /**
     * Given a URN, find an intentional element with the specified name.
     * 
     * @param urn
     * @param name
     * @return matching element
     */
    public static IntentionalElement findIntentionalElementByName(URNspec urn, String name) {
        return (IntentionalElement) findByName(urn.getGrlspec().getIntElements(), name);
    }

    /**
     * Given a graph, find the element having the passed id or return null.
     * 
     * @param graph
     * @param id
     * @return matching element
     */
    public static GRLNode findGRLNode(GRLGraph graph, String id) {
        return (GRLNode) find(graph.getNodes(), id);
    }

    /**
     * Given a URN, find a KPI information element with the specified name.
     * 
     * @param urn
     * @param name
     * @return matching element
     */
    public static KPIInformationElement findKPIInformationElementByName(URNspec urn, String name) {
        return (KPIInformationElement) findByName(urn.getGrlspec().getKpiInformationElements(), name);
    }

    /**
     * Given a URN spec, find the map having the passed id or return null.
     * 
     * @param urn
     * @param id
     * @return matching graph
     */
    public static IURNDiagram findMap(URNspec urn, String id) {
        return (IURNDiagram) find(urn.getUrndef().getSpecDiagrams(), id);
    }

    /**
     * Given a URN spec, find the diagram having the passed name or return null.
     * 
     * Note that diagram names are not constrained to be unique. Will return the first occurrence.
     * 
     * @param urn
     * @param name
     * @return matching diagram
     */
    public static IURNDiagram findMapByName(URNspec urn, String name) {
        return (IURNDiagram) findByName(urn.getUrndef().getSpecDiagrams(), name);
    }
    
       
    /**
     * Given a map, find the node having the passed id or return null.
     * 
     * @param map
     * @param id
     * @return matching node
     */
    public static IURNNode findNode(IURNDiagram map, String id) {
        return (IURNNode) find(map.getNodes(), id);
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
     * Given a URN spec, find the scenariodef having the passed id or return null.
     * 
     * @param urn
     * @param id
     * @return matching scenario def
     */
    public static ScenarioDef findScenario(URNspec urn, String id) {
        Vector v = new Vector();
        for (Iterator iter = urn.getUcmspec().getScenarioGroups().iterator(); iter.hasNext();) {
            ScenarioGroup group = (ScenarioGroup) iter.next();
            v.addAll(group.getScenarios());
        }
        return (ScenarioDef) find(v, id);
    }

    /**
     * Given a URN spec, find the scenario group having the passed id or return null.
     * 
     * @param urn
     * @param id
     * @return matching scenario group
     */
    public static ScenarioGroup findScenarioGroup(URNspec urn, String id) {
        return (ScenarioGroup) find(urn.getUcmspec().getScenarioGroups(), id);
    }
    

    /**
     * Given a URN spec, find the EvaluationStrategy having the passed id or return null.
     * 
     * @param urn
     * @param id
     * @return matching strategy
     */
    public static EvaluationStrategy findStrategy(URNspec urn, String id) {
        Vector v = new Vector();
        for (Iterator iter = urn.getGrlspec().getGroups().iterator(); iter.hasNext();) {
            StrategiesGroup group = (StrategiesGroup) iter.next();
            v.addAll(group.getStrategies());
        }
        return (EvaluationStrategy) find(v, id);
    }
    

    /**
     * Given a URN spec, find the ContributionContext group having the passed id or return null.
     * 
     * @param urn
     * @param id
     * @return matching ContributionContext group
     */
    public static ContributionContextGroup findContributionContextGroup(URNspec urn, String id) {
        return (ContributionContextGroup) find(urn.getGrlspec().getGroups(), id);
    }

    /**
     * Given a URN spec, find the ContributionContext having the passed id or return null.
     * 
     * @param urn
     * @param id
     * @return matching ContributionContext
     */
    public static ContributionContext findContributionContext(URNspec urn, String id) {
        Vector v = new Vector();
        for (Iterator iter = urn.getGrlspec().getContributionGroups().iterator(); iter.hasNext();) {
            ContributionContextGroup group = (ContributionContextGroup) iter.next();
            v.addAll(group.getContribs());
        }
        return (ContributionContext) find(v, id);
    }
    

    /**
     * Given a URN spec, find the strategy group having the passed id or return null.
     * 
     * @param urn
     * @param id
     * @return matching strategy group
     */
    public static StrategiesGroup findStrategyGroup(URNspec urn, String id) {
        return (StrategiesGroup) find(urn.getGrlspec().getGroups(), id);
    }
    /**
     * Given a URN spec, find the variable having the passed id or return null.
     * 
     * @param urn
     * @param id
     * @return matching var
     */
    public static Variable findVariable(URNspec urn, String id) {
        return (Variable) find(urn.getUcmspec().getVariables(), id);
    }

    /**
     * Given a URN spec, find the variable having the passed name or return null.
     * 
     * @param urn
     * @param name
     * @return matching var
     */
    public static Variable findVariableByName(URNspec urn, String name) {
        return (Variable) findByName(urn.getUcmspec().getVariables(), name);
    }

    /**
     * Returns a sorted list of all actor element names.
     * 
     * @param urn
     * @return list of element names
     */
    public static Vector getActorNames(URNspec urn) {
        Vector v = new Vector();
        for (Iterator iter = urn.getGrlspec().getActors().iterator(); iter.hasNext();) {
            URNmodelElement element = (URNmodelElement) iter.next();
            v.add(element.getName());
        }
        Collections.sort(v, String.CASE_INSENSITIVE_ORDER);
        return v;
    }

    /**
     * Returns a sorted list of all component names.
     * 
     * @param urn
     * @return list of element names
     */
    public static Vector getComponentNames(URNspec urn) {
        Vector v = new Vector();
        for (Iterator iter = urn.getUrndef().getComponents().iterator(); iter.hasNext();) {
            URNmodelElement element = (URNmodelElement) iter.next();
            v.add(element.getName());
        }
        Collections.sort(v, String.CASE_INSENSITIVE_ORDER);
        return v;
    }

    /**
     * Returns a sorted list of all intentional element names.
     * 
     * @param urn
     * @return list of element names
     */
    public static Vector getIntentionalElementNames(URNspec urn) {
        Vector v = new Vector();
        for (Iterator iter = urn.getGrlspec().getIntElements().iterator(); iter.hasNext();) {
            URNmodelElement element = (URNmodelElement) iter.next();
            v.add(element.getName());
        }
        Collections.sort(v, String.CASE_INSENSITIVE_ORDER);
        return v;
    }

    /**
     * Returns a sorted list of all KPI information element names.
     * 
     * @param urn
     * @return list of element names
     */
    public static Vector getKPIInformationElementNames(URNspec urn) {
        Vector v = new Vector();
        for (Iterator iter = urn.getGrlspec().getKpiInformationElements().iterator(); iter.hasNext();) {
            URNmodelElement element = (URNmodelElement) iter.next();
            v.add(element.getName());
        }
        Collections.sort(v, String.CASE_INSENSITIVE_ORDER);
        return v;
    }

    /**
     * Returns a sorted list of all responsibility names.
     * 
     * @param urn
     * @return list of element names
     */
    public static Vector getResponsibilityNames(URNspec urn) {
        Vector v = new Vector();
        for (Iterator iter = urn.getUrndef().getResponsibilities().iterator(); iter.hasNext();) {
            URNmodelElement element = (URNmodelElement) iter.next();
            v.add(element.getName());
        }
        Collections.sort(v, String.CASE_INSENSITIVE_ORDER);
        return v;
    }

	public static URNmodelElement getParentElement( URNmodelElement element )
	    {
	    	if( element instanceof ActorRef )
	    		return( (Actor) (((ActorRef) element).getContDef()) );
	    	else if( element instanceof IntentionalElementRef )
	    		return( ((IntentionalElementRef) element).getDef() );
	    	else if( element instanceof RespRef )
	    		return( ((RespRef) element).getRespDef() );
	    	else if( element instanceof ComponentRef )
	    		return( (Component) (((ComponentRef) element).getContDef()) );
	//    	else if( element instanceof LinkRef )
	//    		return( ((LinkRef) element).getLink() );
	    	else
	    		return element;
	    }

}