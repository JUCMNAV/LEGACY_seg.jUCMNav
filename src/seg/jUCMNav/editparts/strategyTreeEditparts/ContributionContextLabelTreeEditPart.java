package seg.jUCMNav.editparts.strategyTreeEditparts;

import grl.ContributionChange;
import grl.ContributionContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import seg.jUCMNav.strategies.EvaluationStrategyManager;

/**
 * Editpart for textual strings that are children of ContributionContext.
 * 
 * @author jkealey
 */
public class ContributionContextLabelTreeEditPart extends ScenarioLabelTreeEditPart {


    /**
     * @param model
     *            the child
     * @param root
     *            the context
     */
    public ContributionContextLabelTreeEditPart(Object model, ContributionContext root) {
        super(model, root);
    }
    /**
     * @return the list of children depending on the folder type. 
     */
    public List getModelChildren() {
        ArrayList list = new ArrayList();
        if (getLabel().equals(ContributionContextTreeEditPart.INCLUDED_CONTRIBUTION_CONTEXTS)) {
            list.addAll(EvaluationStrategyManager.getDefinedIncludedContributionContexts((ContributionContext)getRootElement()));
        } 
        else if (getLabel().equals(ContributionContextTreeEditPart.CONTRIBUTION_CHANGES)) {
            
            ContributionContext context = ((ContributionContext)getParent().getModel());
            HashMap map = new HashMap();
            findChildren(map, context);
            list.addAll(map.values());
        } 
        return list;
    }
    
    private void findChildren(HashMap map, ContributionContext context)
    {
        for (Iterator iterator = context.getIncludedContexts().iterator(); iterator.hasNext();) {
            ContributionContext child = (ContributionContext) iterator.next();
            findChildren(map, child);
        }        
        for (Iterator iterator = context.getChanges().iterator(); iterator.hasNext();) {
            ContributionChange change = (ContributionChange) iterator.next();
            map.put(change.getContribution(), change);
        }
    }
}