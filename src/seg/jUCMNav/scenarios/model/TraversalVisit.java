package seg.jUCMNav.scenarios.model;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.emf.ecore.EObject;

import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.PluginBinding;
import urncore.ComponentElement;
import urncore.IURNContainer;

public class TraversalVisit {

	private EObject visitedElement;
	private Vector context;
	private NodeConnection source;
	private int threadID;
	private ComponentElement parent;
	
	public TraversalVisit(EObject obj, int threadID) {
		this.visitedElement = obj;
		this.context = null;
		if (isValidParentComponent() && getParentComponent()!=null)
			System.out.println("ThreadID: " + threadID + ", " + obj.toString() + ", " + getParentComponent().toString());
		else
			System.out.println("ThreadID: " + threadID + ", " + obj.toString());
			
	}
	
	public TraversalVisit(EObject obj, PluginBinding binding, int threadID) {
		this.visitedElement = obj;
		this.context = new Vector();
		this.context.add(binding);
		this.threadID = threadID;
		if (isValidParentComponent() && getParentComponent()!=null)
			System.out.println("ThreadID: " + threadID + ", " + obj.toString() + ", " + getParentComponent().toString());
		else
			System.out.println("ThreadID: " + threadID + ", " + obj.toString());
	}	
	
	public TraversalVisit(EObject obj, Vector context, int threadID) {
		this.visitedElement = obj;
		this.context = context; 
		this.threadID = threadID;
		if (isValidParentComponent() && getParentComponent()!=null)
			System.out.println("ThreadID: " + threadID + ", " + obj.toString() + ", " + getParentComponent().toString());
		else
			System.out.println("ThreadID: " + threadID + ", " + obj.toString());
	}		

	public TraversalVisit(NodeConnection source, EObject obj, Vector context, int threadID) {
		this.source=source;
		this.visitedElement = obj;
		this.context = context; 
		this.threadID = threadID;
		if (isValidParentComponent() && getParentComponent()!=null)
			System.out.println("ThreadID: " + threadID + ", " + obj.toString() + ", " + getParentComponent().toString());
		else
			System.out.println("ThreadID: " + threadID + ", " + obj.toString());
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
	
	public int getThreadID() {
		return threadID;
	}
	
	public void setThreadID(int threadID) {
		this.threadID = threadID;
	}
	
	public boolean isValidParentComponent() {
	
		if (context==null)
			return true;
		else
		{
			boolean b=false;
			for (Iterator iter = context.iterator(); iter.hasNext();) {
				PluginBinding binding = (PluginBinding) iter.next();
				if (binding.getStub().getContRef()!=null) {
					if (b)
						return false;
					else
						b = true;
				}
			}
			
			return true;
		}
	}
	public IURNContainer getParentComponent()
	{
		if (visitedElement instanceof PathNode)
		{
			PathNode node = (PathNode) visitedElement;
			
			if (node.getContRef()!=null)
				return node.getContRef().getContDef();
			else if (context!=null && context.size()>0)
			{
				for (Iterator iter = context.iterator(); iter.hasNext();) {
					PluginBinding binding = (PluginBinding) iter.next();
					if (binding.getStub().getContRef()!=null) // return first one. 
						return binding.getStub().getContRef().getContDef();
				}
			}
		}
		return null;

	}
}
