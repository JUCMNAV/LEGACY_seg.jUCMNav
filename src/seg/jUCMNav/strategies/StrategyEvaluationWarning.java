package seg.jUCMNav.strategies;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;

import seg.jUCMNav.scenarios.model.AnalysisWarning;


/**
 * A warning issued during the evaluation of grl strategy that will be added to the problems view.
 * 
 * 
 * 
 */
public class StrategyEvaluationWarning extends AnalysisWarning{



    /**
     * 
     * @param msg
     *            the warning message
     */
    public StrategyEvaluationWarning(String msg) {
        super(msg);
    }

    /**
     * 
     * @param msg
     *            the warning message
     * @param location
     *            where did it occur, we might be able to offer a quick fix or at least double clicking functionality
     */
    public StrategyEvaluationWarning(String msg, EObject location) {
       super(msg,location);
    }

    /**
     * 
     * @param msg
     *            the warning message
     * @param location
     *            where did it occur, we might be able to offer a quick fix or at least double clicking functionality
     * @param severity
     *            the warning's severity; {@link IMarker#SEVERITY_ERROR}, {@link IMarker#SEVERITY_WARNING} or {@link IMarker#SEVERITY_INFO}
     */
    public StrategyEvaluationWarning(String msg, EObject location, int severity) {
        super(msg,location,severity);

    }

    /**
     * 
     * @param msg
     *            the warning message
     * @param severity
     *            the warning's severity; {@link IMarker#SEVERITY_ERROR}, {@link IMarker#SEVERITY_WARNING} or {@link IMarker#SEVERITY_INFO}
     */
    public StrategyEvaluationWarning(String msg, int severity) {
        super(msg,severity);
    }



}


