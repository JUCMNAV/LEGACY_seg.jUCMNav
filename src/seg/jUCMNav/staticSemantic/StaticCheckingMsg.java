package seg.jUCMNav.staticSemantic;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;

public class StaticCheckingMsg {
private	String message;
private EObject location;
private int severity;

public StaticCheckingMsg(String msg)
{
	this.setMessage(msg);
	this.location = null;
	this.setError();
}

public StaticCheckingMsg(String msg, EObject loc)
{
	this.setMessage(msg);
	this.setLocation(loc);
	this.setError();
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public EObject getLocation() {
	return location;
}
public void setLocation(EObject location) {
	this.location = location;
}
public int getSeverity() {
	return severity;
}
public void setSeverity(int severity) {
	this.severity = severity;
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
