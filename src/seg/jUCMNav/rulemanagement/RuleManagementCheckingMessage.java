package seg.jUCMNav.rulemanagement;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;

/**
 * This class encapsulates a static checking result.
 * 
 * @author Byrne Yan
 * 
 */
public class RuleManagementCheckingMessage {
    private String message;
    private EObject location;
    private int severity;

    /**
     * Constructs a static checking message with a string of message. Error by default.
     */
    public RuleManagementCheckingMessage(String msg) {
        this.setMessage(msg);
        this.location = null;
        this.setError();
    }

    /**
     * Constructs a static checking message with a string of message and an information location object
     * 
     * @param msg
     *            a string of the error message
     * @param loc
     *            an object on which the error occurs
     */
    public RuleManagementCheckingMessage(String msg, EObject loc) {
        this.setMessage(msg);
        this.setLocation(loc);
        this.setInfo();
    }

    /**
     * Constructs a static checking message with a string of message and an error location object
     * 
     * @param msg
     *            a string of the error message
     * @param loc
     *            an object on which the error occurs
     * @param warningOnly
     *            the severity level
     */
    public RuleManagementCheckingMessage(String msg, EObject loc, boolean warningOnly) {
        this.setMessage(msg);
        this.setLocation(loc);
        if (warningOnly) {
            this.setWarning();
        } else {
            this.setError();
        }
    }

    /**
     * Return the error message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the error message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Returns the error object
     */
    public EObject getLocation() {
        return location;
    }

    /**
     * Set the error object
     */
    public void setLocation(EObject location) {
        this.location = location;
    }

    /**
     * Returns the severity of the error
     * 
     * @return the value of severity
     */
    public int getSeverity() {
        return severity;
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
        return message;
    }

}
