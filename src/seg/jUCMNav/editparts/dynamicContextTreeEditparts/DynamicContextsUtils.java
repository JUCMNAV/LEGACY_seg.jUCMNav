package seg.jUCMNav.editparts.dynamicContextTreeEditparts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import grl.Actor;
import grl.ActorRef;
import grl.Contribution;
import grl.ContributionContext;
import grl.ElementLink;
import grl.EvaluationStrategy;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.LinkRef;
import seg.jUCMNav.editparts.ActorRefEditPart;
import seg.jUCMNav.editparts.ComponentRefEditPart;
import seg.jUCMNav.editparts.IntentionalElementEditPart;
import seg.jUCMNav.editparts.LinkRefEditPart;
import seg.jUCMNav.editparts.PathNodeEditPart;
import seg.jUCMNav.editparts.RespRefEditPart;
import seg.jUCMNav.editparts.StubEditPart;
import ucm.map.ComponentRef;
import ucm.map.EndPoint;
import ucm.map.FailurePoint;
import ucm.map.OrFork;
import ucm.map.PluginBinding;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.Timer;
import ucm.map.WaitingPlace;
import ucm.scenario.ScenarioDef;
import urn.URNspec;
import urn.dyncontext.Change;
import urn.dyncontext.Changeable;
import urn.dyncontext.DynamicContext;
import urn.dyncontext.DynamicContextGroup;
import urn.dyncontext.TextChange;
import urncore.Component;
import urncore.Responsibility;

/**
 * Utility class for Dynamic Contexts.
 * 
 * @author aprajita
 * 
 */
public class DynamicContextsUtils {
	
	 /**
     * Get all the included contexts (recursively)that are related to this context
     * 
     * @param def
     *            the context
     * @return the list of {@link DynamicContext}
     */
    public static Vector getDefinedIncludedContexts(DynamicContext dyn) {
        Vector contexts = new Vector();
        getDefinedIncludedContexts(dyn, contexts);
        return contexts;
    }
    
    /**
     * Get all the included contexts (recursively)that are related to this context
     * 
     * @param def
     *            the context
     * @param contexts
     *            where to insert the found {@link DynamicContext}s
     */
    private static void getDefinedIncludedContexts(DynamicContext dyn, Vector contexts) {
        for (Iterator iter = dyn.getIncludedContexts().iterator(); iter.hasNext();) {
            DynamicContext context = (DynamicContext) iter.next();
            getDefinedIncludedContexts(context, contexts);
            if (!contexts.contains(context))
            	contexts.add(context);
        }
    }
    
    /**
     * Get the evaluation strategy that is related to this context
     * 
     * @param dyn
     *            the context
     * @return  {@link EvaluationStrategy}
     */
    public static EvaluationStrategy getDefinedEvaluationStrategy(DynamicContext dyn) {
        EvaluationStrategy strategy = dyn.getStrategy();
        return strategy;
    }
    
    /**
     * Get the scenario that is related to this context
     * 
     * @param dyn
     *            the context
     * @return  {@link ScenarioDef}
     */
    public static ScenarioDef getDefinedScenario(DynamicContext dyn) {
        ScenarioDef scenario = dyn.getScenario();
        return scenario;
    }
    
    /**
     * Get the contribution context that is related to this context
     * 
     * @param dyn
     *            the context
     * @return  {@link ContributionContext}
     */
    public static ContributionContext getDefinedContributionContext(DynamicContext dyn) {
        ContributionContext con = dyn.getContributionContext();
        return con;
    }
    
    /**
     * Get all the changes that are defined in this context
     * 
     * @param def
     *            the context
     * @param contexts
     *            where to insert the found {@link DynamicContext}s
     */
    public static Vector getDefinedChanges(DynamicContext dyn) {
        Vector changes = new Vector();
        for (Iterator iter = dyn.getChanges().iterator(); iter.hasNext();) {
            Change change = (Change) iter.next();
            changes.add(change);
        }
        return changes;
    }
    
    public static Vector getIndexesOfPrimaryDefinedIncludedContexts(DynamicContext dyn) {
        Vector all = getDefinedIncludedContexts(dyn);
        Vector indexes = new Vector();
        for (int i=0;i<dyn.getIncludedContexts().size();i++)
        {
            // add the index of the context in this list. 
            // given how we merge included contexts (to avoid duplication), this list is non-obvious  
            indexes.add(new Integer(all.indexOf(dyn.getIncludedContexts().get(i))));
        }
        return indexes;
    }
    
    /**
     * Returns all dynamic contexts that we may include into the given parent. Will not cause any circular references.
     * 
     * @param parent
     *            the parent dynamic context definition
     * @return the list of possible children.
     */
    public static List getPossibleIncludedDynamicContexts(DynamicContext parent) {
        List list = getPossibleIncludedDynamicContextsNonRecursive(parent);

        ArrayList toRemove = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            DynamicContext child = (DynamicContext) iter.next();
            if (!getPossibleIncludedDynamicContextsNonRecursive(child).contains(parent))
                toRemove.add(child);
        }
        for (Iterator iter = toRemove.iterator(); iter.hasNext();) {
            DynamicContext element = (DynamicContext) iter.next();
            if (list.contains(element))
                list.remove(element);
        }
        return list;
    }
    
    /**
     * Gets the list of dynamic contexts that it would be possible to include, without recursing. Used in a context where recursion would cause an infinite loop.
     * 
     * @param parent
     *            the dynamic context
     * @return the list of possible {@link ScenarioDef}
     */
    private static List getPossibleIncludedDynamicContextsNonRecursive(DynamicContext parent) {
        if ((DynamicContextGroup) parent.getGroups().get(0) == null)
            return new ArrayList();
        URNspec urn = parent.getUrnspec();
        List list = getAllDynamicContexts(urn);

        removeIncludedDynamicContexts(list, parent);
        return list;

    }
    
    /**
     * Returns a list of all DynamicContexts in all groups.
     * 
     * @param urn
     *            the root urnspec
     * @return the list of dynamic contexts
     */
    public static List getAllDynamicContexts(URNspec urn) {
        ArrayList list = new ArrayList();
        for (Iterator iter = urn.getDynamicContextGroups().iterator(); iter.hasNext();) {
            DynamicContextGroup group = (DynamicContextGroup) iter.next();

            for (Iterator iterator = group.getContexts().iterator(); iterator.hasNext();) {
                DynamicContext dyn = (DynamicContext) iterator.next();
                list.add(dyn);
            }
        }
        return list;
    }
    
    /**
     * Recursively removes all the included dynamic contexts from the given list.
     * 
     * @param list
     *            list of DynamicContexts
     * @param parent
     *            the root dynamicContext from which we remove the children. we also remove the parent from the list.
     */
    private static void removeIncludedDynamicContexts(List list, DynamicContext parent) {
        for (Iterator iter = parent.getIncludedContexts().iterator(); iter.hasNext();) {
            DynamicContext child = (DynamicContext) iter.next();
            removeIncludedDynamicContexts(list, child);
        }

        if (list.contains(parent))
            list.remove(parent);
    }
    
    /*
     * Returns a list of all available changes for a particular element and
     * a given dynamic context
     * 
     * @param parent
     * 				URNmodelElement for which we need to extract changes
     * @param dyn
     * 			Selected dynamic context
     */
    public static List getAllAvailableChanges(Object parent, DynamicContext dyn, URNspec urn){
    	
    	List<Change> availableChanges = new ArrayList();
    	if (dyn != null) {
			List<Change> allChanges = dyn.getChanges();
			Changeable elt = null;
	    	if (parent instanceof LinkRefEditPart)
	    		elt = ((LinkRef)((LinkRefEditPart) parent).getModel()).getLink();
	    	else if (parent instanceof LinkRef)
	    		elt = ((LinkRef) parent).getLink();
	    	else if (parent instanceof ElementLink)
	    		elt = (ElementLink) parent;
	    	else if (parent instanceof Contribution)
	    		elt = (Contribution) parent;
	    	else if (parent instanceof IntentionalElementEditPart)
	    		elt = ((IntentionalElementRef)((IntentionalElementEditPart) parent).getModel()).getDef();
	    	else if (parent instanceof IntentionalElement)
	    		elt = (IntentionalElement) parent;
	    	else if (parent instanceof ActorRefEditPart)
	    		elt = (Actor) ((ActorRef)((ActorRefEditPart) parent).getModel()).getContDef();
	    	else if (parent instanceof Actor)
	    		elt = (Actor) parent;
            else if (parent instanceof RespRefEditPart)
    	    	elt = ((RespRef)((RespRefEditPart) parent).getModel()).getRespDef();
    	    else if (parent instanceof Responsibility)
    	    	elt = (Responsibility) parent;
    	    else if (parent instanceof ComponentRefEditPart)
    	    	elt = (Component)((ComponentRef)((ComponentRefEditPart) parent).getModel()).getContDef();
    	    else if (parent instanceof Component)
    	    	elt = (Component) parent;
			else if (parent instanceof FailurePoint)
				elt = (FailurePoint) parent;
			else if (parent instanceof StartPoint)
				elt = (StartPoint) parent;
			else if (parent instanceof Timer)
				elt = (Timer) parent;
			else if (parent instanceof WaitingPlace)
				elt = (WaitingPlace) parent;
			else if (parent instanceof Stub)
				elt = (Stub) parent;
			else if (parent instanceof PluginBinding)
				elt = (PluginBinding) parent;
			else if (parent instanceof urncore.Condition)
				elt = (urncore.Condition) parent;
			else if (parent instanceof StubEditPart)
				elt = (Stub) ((StubEditPart) parent).getModel();
			else if (parent instanceof PathNodeEditPart) {
				if ((((PathNodeEditPart) parent).getModel()) instanceof FailurePoint)
					elt = (FailurePoint)((PathNodeEditPart) parent).getModel();
				else if ((((PathNodeEditPart) parent).getModel()) instanceof StartPoint)
					elt = (StartPoint)((PathNodeEditPart) parent).getModel();
				else if ((((PathNodeEditPart) parent).getModel()) instanceof EndPoint)
					elt = (EndPoint)((PathNodeEditPart) parent).getModel();
				else if ((((PathNodeEditPart) parent).getModel()) instanceof OrFork)
					elt = (OrFork)((PathNodeEditPart) parent).getModel();
				else if ((((PathNodeEditPart) parent).getModel()) instanceof Timer)
					elt = (Timer)((PathNodeEditPart) parent).getModel();
				else if ((((PathNodeEditPart) parent).getModel()) instanceof WaitingPlace)
					elt = (WaitingPlace)((PathNodeEditPart) parent).getModel();
				}
	    		
			for (Change change : allChanges) {
				if (change.getElement().equals(elt))
					availableChanges.add(change);
				else if (parent instanceof PathNodeEditPart) {
					if (elt instanceof StartPoint && change.getElement() instanceof urncore.Condition && ((TextChange) change).getAffectedProperty().equals("Failure List/Start Point Pre-Condition")) {
						if (((StartPoint) elt).getId().equals(((urncore.Condition) change.getElement()).getStartPoint().getId()))
	    			availableChanges.add(change);
	    		}
					else if (elt instanceof FailurePoint && change.getElement() instanceof urncore.Condition && ((TextChange) change).getAffectedProperty().equals("Failure Condition")) {
						if (((FailurePoint) elt).getId().equals(((FailurePoint) ((urncore.Condition) change.getElement()).getNodeConnection().getSource()).getId()))
							availableChanges.add(change);
	    	}
					else if (elt instanceof OrFork && change.getElement() instanceof urncore.Condition && ((TextChange) change).getAffectedProperty().startsWith("Expression")) {
						if (((OrFork) elt).getId().equals(((OrFork) ((urncore.Condition) change.getElement()).getNodeConnection().getSource()).getId()))
							availableChanges.add(change);
					}
					else if (elt instanceof EndPoint && change.getElement() instanceof urncore.Condition && ((TextChange) change).getAffectedProperty().equals("Post-Condition")) {
						if (((EndPoint) elt).getId().equals(((urncore.Condition) change.getElement()).getEndPoint().getId()))
							availableChanges.add(change);
					}
					else if (elt instanceof Stub && change.getElement() instanceof urncore.Condition && 
							(((TextChange) change).getAffectedProperty().startsWith("Pre-Condition- ") || ((TextChange) change).getAffectedProperty().startsWith("Replication factor"))) {
						if (((Stub) elt).getId().equals(((urncore.Condition) change.getElement()).getPluginBinding().getStub().getId()))
							availableChanges.add(change);
					}
					else if (elt instanceof Stub && change.getElement() instanceof PluginBinding) {
						String changeElementPluginID = ((PluginBinding) change.getElement()).getPlugin().getId();
						List<PluginBinding> bindings = ((Stub) elt).getBindings();
						for (PluginBinding p : bindings) {
							if (p.getPlugin().getId().equals(changeElementPluginID))
								availableChanges.add(change);
						}
					}
				}
			} 
    	} else
    		availableChanges = null;
    	if (availableChanges != null) {
	    	Collections.sort(availableChanges, new Comparator<Change>() {
			    public int compare(Change c1, Change c2) {
			        return c1.getStart().compareTo(c2.getStart());
			    }
			});
    	}
    	return availableChanges;
    }
    
    /*
     * Returns a boolean indicating if any change has been added to the URNModelElement
     * 
     * @param el
	 * 				Changeable element for which we need to check if any change exists
     * @param urn
     * 			URNSpec
     */
	public static boolean changeExistsFor(Changeable el, URNspec urn) {
    	boolean exists = false;
    	List dynContexts = urn.getDynamicContexts();
    	for (Iterator iter = dynContexts.iterator(); iter.hasNext();) {
    		DynamicContext dyn = (DynamicContext) iter.next();
	    	List allChanges = dyn.getChanges();
	    	
	    	for (Iterator iter1 = allChanges.iterator(); iter1.hasNext();) {
				try {
	    		Change change = (Change) iter1.next();
					if (change instanceof TextChange) {
						if (((((TextChange) change).getAffectedProperty().equals("Post-Condition")) && el instanceof EndPoint)) {
							String changeId = ((urncore.Condition) change.getElement()).getEndPoint().getId();
							if (((EndPoint) el).getId().equals(changeId)) {
	    			exists = true;
	    			break;
							}	
						}
						else if ((((TextChange) change).getAffectedProperty().startsWith("Expression")) && el instanceof OrFork) {
							String changeId = ((OrFork) ((urncore.Condition) change.getElement()).getNodeConnection().getSource()).getId();
							if (((OrFork) el).getId().equals(changeId)) {
								exists = true;
								break;
							}
						}
						else if ((((TextChange) change).getAffectedProperty().equals("Failure Condition")) && el instanceof FailurePoint 
								&& (change.getElement() instanceof FailurePoint || change.getElement() instanceof urncore.Condition)) {
							String changeId = ((FailurePoint) ((urncore.Condition) change.getElement()).getNodeConnection().getSource()).getId();
							if (((FailurePoint) el).getId().equals(changeId)) {
								exists = true;
								break;
							}
						}
						else if ((((TextChange) change).getAffectedProperty().equals("Failure List/Start Point Pre-Condition")) && el instanceof StartPoint) {
							String changeId = ((urncore.Condition) change.getElement()).getStartPoint().getId();
							if (((StartPoint) el).getId().equals(changeId)) {
								exists = true;
								break;
							}
						}
					}
					if (change.getElement().equals(el)) {
						exists = true;
						break;
					}
				} catch (NullPointerException e) {

	    		}
	    	}
	    	if (exists)
	    		break;
    	} 
    	return exists;
    }
    
}
