package seg.jUCMNav.importexport.csm.one2one;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;

/**
 * A warning issued during the CSM export that will be added to the problems view.
 * 
 * @author jkealey, damyot
 * 
 */
public class CsmExportWarning {

    private EObject location;

    private String msg;

    private int severity;

    /**
     * 
     * @param msg
     *            the warning message
     */
    public CsmExportWarning(String msg) {
        this.msg = msg;
        location = null;
        setWarning();
    }

    /**
     * 
     * @param msg
     *            the warning message
     * @param location
     *            where did it occur, we might be able to offer a quick fix or at least double clicking functionality
     */
    public CsmExportWarning(String msg, EObject location) {
        this.msg = msg;
        this.location = location;
        setWarning();
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
    public CsmExportWarning(String msg, EObject location, int severity) {
        this.msg = msg;
        this.location = location;
        this.severity = severity;
    }

    /**
     * 
     * @param msg
     *            the warning message
     * @param severity
     *            the warning's severity; {@link IMarker#SEVERITY_ERROR}, {@link IMarker#SEVERITY_WARNING} or {@link IMarker#SEVERITY_INFO}
     */
    public CsmExportWarning(String msg, int severity) {
        this.msg = msg;
        location = null;
        this.severity = severity;
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
