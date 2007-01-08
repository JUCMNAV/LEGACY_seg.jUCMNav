package seg.jUCMNav.scenarios.model;

import java.util.Iterator;
import java.util.Vector;

import ucm.map.ComponentRef;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.PluginBinding;
import ucm.map.UCMmap;
import urncore.IURNContainer;
import urncore.IURNContainerRef;

public class TraversalVisit {

	private PathNode visitedElement;
	private Vector context;
	private NodeConnection source;
	private int threadID;

	
	private Vector parentComponentRefs;
	
	
	public TraversalVisit(PathNode obj, Vector context, int threadID) {
		this.visitedElement = obj;
		this.context = context;
		this.threadID = threadID;
		// if (isValidParentComponent() && getParentComponent()!=null)
		// System.out.println("ThreadID: " + threadID + ", " + obj.toString() + ", " + getParentComponent().toString());
		// else
		// System.out.println("ThreadID: " + threadID + ", " + obj.toString());
	}

	public TraversalVisit(NodeConnection source, PathNode obj, Vector context, int threadID) {
		this.source = source;
		this.visitedElement = obj;
		this.context = context;
		this.threadID = threadID;
		// if (isValidParentComponent() && getParentComponent()!=null)
		// System.out.println("ThreadID: " + threadID + ", " + obj.toString() + ", " + getParentComponent().toString());
		// else
		// System.out.println("ThreadID: " + threadID + ", " + obj.toString());
	}

	public Vector getContext() {
		return context;
	}

	public void setContext(Vector context) {
		this.context = context;
	}

	public PathNode getVisitedElement() {
		return visitedElement;
	}

	public void setVisitedElement(PathNode visitedElement) {
		this.visitedElement = visitedElement;
	}

	public NodeConnection getSourceNodeConnection() {
		return this.source;
	}

	public void increaseContext(Vector v) {
		for (Iterator iter = v.iterator(); iter.hasNext();) {
			PluginBinding binding = (PluginBinding) iter.next();
			if (!context.contains(binding))
				context.add(binding);
		}
	}

	public int getThreadID() {
		return threadID;
	}

	public void setThreadID(int threadID) {
		this.threadID = threadID;
	}

	public boolean isValidParentComponent() {

		if (context == null)
			return true;
		else {
			boolean b = false;
			for (Iterator iter = context.iterator(); iter.hasNext();) {
				PluginBinding binding = (PluginBinding) iter.next();
				if (binding.getStub().getContRef() != null) {
					if (b)
						return false;
					else
						b = true;
				}
			}

			return true;
		}
	}

	public IURNContainerRef getParentComponentRef() {
		Vector v = getParentComponentRefs();

		if (v.size()==0)
			return null;
		else
			return (IURNContainerRef) v.get(0);
	}

	public IURNContainer getParentComponentDef() {
		IURNContainerRef ref = getParentComponentRef();
		if (ref == null)
			return null;
		else
			return ref.getContDef();
	}

	public Vector getParentComponentRefs() {
		if (parentComponentRefs!=null) return parentComponentRefs;
		Vector list = new Vector();

		addComponentRefs(visitedElement, list);
		
		parentComponentRefs = list;
		return list;

	}

	protected void addComponentRefs(PathNode pn, Vector list) {
		ComponentRef ref = (ComponentRef) pn.getContRef();
		// add all of pathnode's containers.
		while (ref != null) {
			list.add(ref);
			ref = (ComponentRef) ref.getParent();
		}

		// recurse up the plugin stack to add more components.
		// TODO: this is imperfect... does not work when you have increased the context from different maps.. 
		// only picks one ancestor and moves up; not much we can do about it though.
		for (Iterator iter = ((UCMmap) pn.getDiagram()).getParentStub().iterator(); iter.hasNext();) {
			PluginBinding element = (PluginBinding) iter.next();
			if (context != null && context.contains(element)) {
				addComponentRefs(element.getStub(), list);
				break;
			}

		}

	}
}
