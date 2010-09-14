package seg.jUCMNav.aourn.composer;

import java.util.ArrayList;
import java.util.List;

import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.OutBinding;
import ucm.map.PathNode;
import ucm.map.PluginBinding;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.UCMmap;


public class AspectMarkerMappings {
	
	// this is the plug-in binding for the first binding of the aspect marker
	// the first element in the list is a joinpoint (PathNode), the second
	// can be either an in-path or an out-path of a pointcut stub on the aspect map
	private List<Object> firstMapping = new ArrayList<Object>();
	// this is the plug-in binding for the second binding of the aspect marker
	// the joinpoint (PathNode) is the same as for the first mapping and is therefore not repeated, the second 
	// element can be either a start/end point or null
	private PathNode secondMapping;
	// sometimes, the second mapping is not needed. this is indicated by setting isAllCreated to true
	// if isAllCreated is false, secondMapping must be set; otherwise, secondMapping is null
	private boolean isAllCreated;
	// indicates where exactly the aspect marker is to be inserted in the model
	private NodeConnection insertionPoint;
	// indicates the nth match of the pointcut expression to which these mappings belong
	private Integer counter; 
	// the aspect map
	private UCMmap aspectMap;
	// indicates whether this mapping is part of a replacement
	private boolean replacement;
	
	public AspectMarkerMappings(Integer counter, UCMmap aspectMap) {
		this.counter = counter;
		this.aspectMap = aspectMap;
		this.isAllCreated = false;
		this.secondMapping = null;
	}

	// TODO rewrite capture, so this one is not needed!
	public List<Object> getFirstMapping() {
		return firstMapping;
	}
	
	// TODO rewrite capture, so this one is not needed!
	public PathNode getSecondMapping() {
		return secondMapping;
	}

	public void setFirstMapping(PathNode joinpoint, InoutInfo aspectElement) {
		firstMapping.add(joinpoint);
		firstMapping.add(aspectElement);
	}

	public void setSecondMapping(PathNode aspectElement) {
		secondMapping = aspectElement;
		isAllCreated = true;
	}
	
	public boolean isAllCreated() {
		return isAllCreated;
	}
	
	public NodeConnection getInsertionPoint() {
		return insertionPoint;
	}
	
	public void setInsertionPoint(NodeConnection insertionPoint) {
		this.insertionPoint = insertionPoint;
	}
	
	public int getInsertionXCoordinate() {
		int offset;
		// the insertion position is a third from the joinpoint
		if (insertionPoint.getSource().equals(firstMapping.get(0))) {
			offset = (insertionPoint.getTarget().getX() - insertionPoint.getSource().getX()) / 3;
			return insertionPoint.getSource().getX() + offset;
		} else {
			offset = (insertionPoint.getSource().getX() - insertionPoint.getTarget().getX()) / 3;
			return insertionPoint.getTarget().getX() + offset;
		}
	}
	
	public int getInsertionYCoordinate() {
		int offset;
		// the insertion position is a third from the joinpoint
		if (insertionPoint.getSource().equals(firstMapping.get(0))) {
			offset = (insertionPoint.getTarget().getY() - insertionPoint.getSource().getY()) / 3;
			return insertionPoint.getSource().getY() + offset;
		} else {
			offset = (insertionPoint.getSource().getY() - insertionPoint.getTarget().getY()) / 3;
			return insertionPoint.getTarget().getY() + offset;
		}
	}
	
	public Integer getCounter() {
		return counter;
	}
	
	public int getType() {
		// returns the type of aspect marker (6 = regular, 7 = entrance, 8 = exit, 9 = conditional)
		// by default it is a regular stub
		int type = 6;
		// if a second mapping is not required, then the stub is a tunnel (entrance or exit)
		if (getOutBindingPluginElement() == null) {
			// if a second mapping is not required and it's for the out-binding, then the in-binding exists only
			// hence, its an tunnel entrance aspect marker
			type = 7;
			
		} else if (getInBindingPluginElement() == null) {
			// if a second mapping is not required and it's for the in-binding, then the out-binding exists only
			// hence, its an tunnel exit aspect marker
			type = 8;
		}
		// TODO figure out if it's a conditional aspect marker
		return type;
	}
	
	public UCMmap getBaseMap() {
		return (UCMmap) ((PathNode) getFirstMapping().get(0)).getDiagram();
	}
	
	public UCMmap getAspectMap() {
		return aspectMap;
	}
	
	public Object getInBindingPluginElement() {
		if (replacement) {
			// the first and second mappings of a replacement pointcut stub are created just like for any other stub (but also including  
			// the empty segment before the replacement pointcut stub); therefore, there are two aspect marker mappings: one for the segment before 
			// and one for the segment after the replacement pointcut stub; the one before is identified by second mapping being a start point,
			// while the one after is identified by the second mapping being an end point. to achieve the desired tunnel effect, the one before needs
			// to result in an in-binding to the out-path of the stub, while the one after needs to result in an out-binding from the end point;
			if (secondMapping instanceof StartPoint) {
				NodeConnection inpath = ((InoutInfo) getFirstMapping().get(1)).getInoutPath();
				// given the in-path of the stub, find the out-path
				NodeConnection outpath = (NodeConnection) ((OutBinding) ((PluginBinding) ((Stub) inpath.getTarget()).getBindings().get(0)).getOut().get(0)).getStubExit(); 
				return outpath;
			} else
				return null;
			
		} else {
			// the inBinding is determined by whether the second entry of the first mapping is an in-path or an out-path of the pointcut stub
			// if it's an out-path, then the first mapping is an InBinding and the second mapping is an OutBinding;
			// if it's an in-path, then the first mapping is an OutBinding and the second mapping is an InBinding;
			NodeConnection outpath = ((InoutInfo) getFirstMapping().get(1)).getOutPath();
			if (outpath != null) 
				return outpath;
			else
				return secondMapping;			
		}
	}
	
	// see getInBindingsPluginElement
	public Object getOutBindingPluginElement() {
		if (replacement) {
			if (secondMapping instanceof EndPoint)
				return secondMapping;
			else
				return null;			
		} else {
			NodeConnection inpath = ((InoutInfo) getFirstMapping().get(1)).getInPath();
			if (inpath != null) 
				return inpath;
			else
				return secondMapping;			
		}
	}

	public void setReplacement(boolean replacement) {
		this.replacement = replacement;
	}
	
}