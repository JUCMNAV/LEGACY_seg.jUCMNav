package seg.jUCMNav.scenarios.model;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.emf.ecore.EObject;

import ucm.map.NodeConnection;
import ucm.map.PluginBinding;

public class TraversalVisit {

	private EObject visitedElement;
	private Vector context;
	private NodeConnection source;
	
	public TraversalVisit(EObject obj) {
		this.visitedElement = obj;
		this.context = null;
	}
	
	public TraversalVisit(EObject obj, PluginBinding binding) {
		this.visitedElement = obj;
		this.context = new Vector();
		this.context.add(binding);
	}	
	
	public TraversalVisit(EObject obj, Vector context) {
		this.visitedElement = obj;
		this.context = context; 
	}		

	public TraversalVisit(NodeConnection source, EObject obj, Vector context) {
		this.source=source;
		this.visitedElement = obj;
		this.context = context; 
	}	
	
	public Vector getContext() {
		return context;
	}

	public void setContext(Vector context) {
		this.context = context;
	}

	public EObject getVisitedElement() {
		return visitedElement;
	}

	public void setVisitedElement(EObject visitedElement) {
		this.visitedElement = visitedElement;
	}	
	
	public NodeConnection getSourceNodeConnection() {
		return this.source;
	}
	
	public void increaseContext(Vector v)
	{
		for (Iterator iter = v.iterator(); iter.hasNext();) {
			PluginBinding binding = (PluginBinding) iter.next();
			if (!context.contains(binding))
				context.add(binding);
		}
	}
	
	
	
}
