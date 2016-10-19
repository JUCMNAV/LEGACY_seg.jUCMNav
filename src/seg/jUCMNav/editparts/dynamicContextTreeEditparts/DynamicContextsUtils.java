package seg.jUCMNav.editparts.dynamicContextTreeEditparts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import grl.ActorRef;
import grl.Contribution;
import grl.ContributionContext;
import grl.ElementLink;
import grl.EvaluationStrategy;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.LinkRef;
import seg.jUCMNav.editparts.ActorRefEditPart;
import seg.jUCMNav.editparts.IntentionalElementEditPart;
import seg.jUCMNav.editparts.LinkRefEditPart;
import ucm.scenario.ScenarioDef;
import urn.URNspec;
import urn.dyncontext.Change;
import urn.dyncontext.ConstantChange;
import urn.dyncontext.DeactivationChange;
import urn.dyncontext.DynamicContext;
import urn.dyncontext.DynamicContextGroup;
import urn.dyncontext.EnumChange;
import urn.dyncontext.FormulaChange;
import urn.dyncontext.LinearChange;
import urn.dyncontext.QuadraticChange;
import urn.dyncontext.impl.ConstantChangeImpl;
import urn.dyncontext.impl.DeactivationChangeImpl;
import urn.dyncontext.impl.EnumChangeImpl;
import urn.dyncontext.impl.FormulaChangeImpl;
import urn.dyncontext.impl.LinearChangeImpl;
import urn.dyncontext.impl.QuadraticChangeImpl;
import urncore.URNmodelElement;

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
    		List allChanges = dyn.getChanges();
	    	URNmodelElement elt = null;
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
	    		elt = (ActorRef)((ActorRefEditPart) parent).getModel();
	    	else if (parent instanceof ActorRef)
	    		elt = (ActorRef) parent;
	    	for (Iterator iter = allChanges.iterator(); iter.hasNext();) {
	    		Change change = null;
	    		Object nextIter = iter.next();
	    		if (nextIter instanceof ConstantChangeImpl) {
	    			change = (ConstantChange) nextIter;
				} else if (nextIter instanceof LinearChangeImpl) {
	    			change = (LinearChange) nextIter;
				} else if (nextIter instanceof QuadraticChangeImpl) {
	    			change = (QuadraticChange) nextIter;
				} else if (nextIter instanceof FormulaChangeImpl) {
	    			change = (FormulaChange) nextIter;
				} else if (nextIter instanceof DeactivationChangeImpl) {
	    			change = (DeactivationChange) nextIter;
				} else if (nextIter instanceof EnumChangeImpl) {
	    			change = (EnumChange) nextIter;
				}
	    		
	    		if (change.getElement().equals(elt)){
	    			availableChanges.add(change);
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
    
}
