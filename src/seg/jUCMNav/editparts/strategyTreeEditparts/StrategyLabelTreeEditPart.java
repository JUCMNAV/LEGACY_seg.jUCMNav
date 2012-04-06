package seg.jUCMNav.editparts.strategyTreeEditparts;

import grl.EvaluationStrategy;

import java.util.ArrayList;
import java.util.List;

import seg.jUCMNav.Messages;
import seg.jUCMNav.scenarios.ScenarioUtils;
import seg.jUCMNav.strategies.EvaluationStrategyManager;

/**
 * Editpart for textual strings that are children of Strategies.
 * 
 * @author jkealey
 */
public class StrategyLabelTreeEditPart extends ScenarioLabelTreeEditPart {


    /**
     * @param model
     *            the child
     * @param root
     *            the strategy
     */
    public StrategyLabelTreeEditPart(Object model, EvaluationStrategy root) {
        super(model, root);
    }
    /**
     * @return the list of scenario children depending on the folder type. uses {@link ScenarioUtils}.
     */
    public List getModelChildren() {
        ArrayList list = new ArrayList();
        if (getLabel().equals(Messages.getString("StrategyLabelTreeEditPart.IncludedStrategies"))) { //$NON-NLS-1$
            list.addAll(EvaluationStrategyManager.getDefinedIncludedStrategies((EvaluationStrategy)getRootElement()));
        } 
        return list;
    }
}