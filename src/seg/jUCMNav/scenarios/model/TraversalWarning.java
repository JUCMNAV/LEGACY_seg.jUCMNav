package seg.jUCMNav.scenarios.model;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;

import ucm.map.NodeConnection;
import urncore.Condition;

/**
 * A warning issued during the traversal that will be added to the problems view.
 * 
 * @author jkealey
 * 
 */
public class TraversalWarning extends AnalysisWarning{

    private Condition condition;
   
    /**
     * 
     * @param msg
     *            the warning message
     */
    public TraversalWarning(String msg) {
       super(msg);
    }

    /**
     * 
     * @param msg
     *            the warning message
     * @param location
     *            where did it occur, we might be able to offer a quick fix or at least double clicking functionality
     */
    public TraversalWarning(String msg, EObject location) {
       super(msg,location);
        if (location instanceof Condition) {
            setCondition((Condition) location);
            this.location = location.eContainer();
            if (this.location instanceof NodeConnection) {
                this.location = ((NodeConnection) this.location).getSource();
            }
        }
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
    public TraversalWarning(String msg, EObject location, int severity) {
       super(msg,location,severity);
        if (this.location instanceof Condition) {
            setCondition((Condition) this.location);
            this.location = this.location.eContainer();
            if (this.location instanceof NodeConnection) {
                this.location = ((NodeConnection) this.location).getSource();
            }
        }
    }

    /**
     * 
     * @param msg
     *            the warning message
     * @param severity
     *            the warning's severity; {@link IMarker#SEVERITY_ERROR}, {@link IMarker#SEVERITY_WARNING} or {@link IMarker#SEVERITY_INFO}
     */
    public TraversalWarning(String msg, int severity) {
        super(msg,severity);
    }

    /**
     * If the location in the constructor is a condition, this element will be set and the actual location will be the location of this condition in the model.
     * 
     * @return the condition where this warning occurred.
     */
    public Condition getCondition() {
        return condition;
    }

    /**
     * 
     * @param condition
     *            the condition where the warning occured.
     */
    public void setCondition(Condition condition) {
        this.condition = condition;
    }

}
