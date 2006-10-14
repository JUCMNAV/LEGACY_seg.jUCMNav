package seg.jUCMNav.scenarios.model;

import org.eclipse.emf.ecore.EObject;

public class TraversalWarning {

	private String msg;
	private EObject location;
	
	public TraversalWarning(String msg) {
		this.msg =msg;
		this.location = null;
	}
	
	public TraversalWarning(String msg, EObject location)
	{
		this.msg=msg;
		this.location=location;
	}

	public EObject getLocation() {
		return location;
	}

	public String getMsg() {
		return msg;
	}
	
	public String toString() {
		return msg;
	}
}
