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
public class TraversalWarning {

    private Condition condition;
    private EObject location;
    private String msg;
    private int severity;
    private String expression;

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    /**
     * 
     * @param msg
     *            the warning message
     */
    public TraversalWarning(String msg) {
        this.msg = msg;
        this.location = null;
        setWarning();
    }

    /**
     * 
     * @param msg
     *            the warning message
     * @param location
     *            where did it occur, we might be able to offer a quick fix or at least double clicking functionality
     */
    public TraversalWarning(String msg, EObject location) {
        this.msg = msg;
        this.location = location;
        setWarning();
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
        this.msg = msg;
        this.location = location;
        this.severity = severity;

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
        this.msg = msg;
        this.location = null;
        this.severity = severity;
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
     * @return the location where this warning cocurred
     */
    public EObject getLocation() {
        return location;
    }

    /**
     * 
     * @return the warning message
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 
     * @return the warning's severity; {@link IMarker#SEVERITY_ERROR}, {@link IMarker#SEVERITY_WARNING} or {@link IMarker#SEVERITY_INFO}
     */
    public int getSeverity() {
        return severity;
    }

    /**
     * 
     * @param condition
     *            the condition where the warning occured.
     */
    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    /**
     * Set the severity as {@link IMarker#SEVERITY_ERROR}
     * 
     */
    public void setError() {
        severity = IMarker.SEVERITY_ERROR;
    }

    /**
     * Set the severity as {@link IMarker#SEVERITY_INFO}
     * 
     */
    public void setInfo() {
        severity = IMarker.SEVERITY_INFO;
    }

    /**
     * Set the severity as {@link IMarker#SEVERITY_WARNING}
     * 
     */
    public void setWarning() {
        severity = IMarker.SEVERITY_WARNING;
    }

    /**
     * The warning message.
     */
    public String toString() {
        return msg;
    }

}
