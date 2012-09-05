package seg.jUCMNav.aourn.matcher;

import grl.Actor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ucm.map.Connect;
import ucm.map.DirectionArrow;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Timer;
import urncore.Component;

public abstract class MatchableElement {
	
	// TODO only PathNode at the moment
	private PathNode element;
	private List<MatchableNeighbor> neighbors;
	// TODO add other information required to match components etc. (see PointcutMatcher.matchElement)
	
	public MatchableElement(PathNode element) {
		assert element != null : "Element parameter cannot be null"; //$NON-NLS-1$
		this.element = element;
		this.neighbors = new ArrayList<MatchableNeighbor>();
	}
	
	public void setNeighbors() {
		// ignores "map" whitespace: empty points, direction arrows, connects, and
		// connected end/start point pairs (no condition set for start point);
		// timeoutPath is not used by jUCMNav - timeout path is always the second successor of a Timer
		for (Iterator iter = element.getSucc().iterator(); iter.hasNext();) {
			NodeConnection nodeConnection = (NodeConnection) iter.next();
			PathNode nextPathNode = (PathNode) nodeConnection.getTarget();
			boolean timeout = false;
			boolean connect = (nextPathNode instanceof Connect);
			nextPathNode = ignoreWhitespace(nextPathNode, true);
			if (element instanceof Timer && ((Timer) element).getSucc().size() == 2 && ((Timer) element).getSucc().get(1).equals(nodeConnection)) {
				timeout = true;
			}
			// the second parameter sets the direction to "forward", the third sets the timeout path, the fourth the connect
			MatchableNeighbor matchableNeighbor = new MatchableNeighbor(nextPathNode, true, timeout, connect, this);
			neighbors.add(matchableNeighbor);
		}
		for (Iterator iter = element.getPred().iterator(); iter.hasNext();) {
			NodeConnection nodeConnection = (NodeConnection) iter.next();
			PathNode previousPathNode = (PathNode) nodeConnection.getSource();
			boolean timeout = false;
			boolean connect = (previousPathNode instanceof Connect);
			previousPathNode = ignoreWhitespace(previousPathNode, false);
			if (previousPathNode instanceof Timer && ((Timer) previousPathNode).getSucc().size() == 2 && ((Timer) previousPathNode).getSucc().get(1).equals(nodeConnection)) {
				timeout = true;
			}
			// the second parameter sets the direction to not "forward", the third sets the timeout path, the fourth the connect
			MatchableNeighbor matchableNeighbor = new MatchableNeighbor(previousPathNode, false, timeout, connect, this);
			neighbors.add(matchableNeighbor);
		}
	}
	
	// TODO refactor this code for semantic matching and the code in MatchableElementFactory to reduce duplication
	private PathNode ignoreWhitespace(PathNode neighborPathNode, boolean forward) {
		if (neighborPathNode instanceof EmptyPoint || neighborPathNode instanceof DirectionArrow || neighborPathNode instanceof Connect) {
			if (forward) {
				// there is always one next path node after an empty point or direction arrow or connect
				return ignoreWhitespace(((PathNode) ((NodeConnection) neighborPathNode.getSucc().get(0)).getTarget()), true);				
			} else {
				// there is always one previous path node before an empty point or direction arrow or connect
				return ignoreWhitespace(((PathNode) ((NodeConnection) neighborPathNode.getPred().get(0)).getSource()), false);
			}
		}
		if (forward && neighborPathNode instanceof EndPoint) {
			// check if end point is connected to a start point without a condition; if yes, ignore them
			// if an end point is connected to a start point, the Connect node is in between the two nodes
			if (neighborPathNode.getSucc().size() > 0) {
				// there is always at the most one node after an end point
				PathNode pn = ((PathNode) ((NodeConnection) neighborPathNode.getSucc().get(0)).getTarget());
				if (pn instanceof Connect) {
					// there is always one node after a connect
					pn = ((PathNode) ((NodeConnection) pn.getSucc().get(0)).getTarget());
					if (pn instanceof StartPoint && ((StartPoint) pn).getPrecondition().getLabel() == "") { //$NON-NLS-1$
						// there is always a node after a start point
						 return ignoreWhitespace(((PathNode) ((NodeConnection) pn.getSucc().get(0)).getTarget()), true);
					}
				}
			}
		}
		if (!forward && neighborPathNode instanceof StartPoint && ((StartPoint) neighborPathNode).getPrecondition().getLabel() == "") { //$NON-NLS-1$
			// check if start point without a condition is connected to an end point; if yes, ignore them
			// if an end point is connected to a start point, the Connect node is in between the two nodes
			if (neighborPathNode.getPred().size() > 0) {
				// there is always at the most one node before a start point
				PathNode pn = ((PathNode) ((NodeConnection) neighborPathNode.getPred().get(0)).getSource());
				if (pn instanceof Connect) {
					// there is always one node before a connect
					pn = ((PathNode) ((NodeConnection) pn.getPred().get(0)).getSource());
					if (pn instanceof EndPoint) {
						// there is always a node before an end point
						return ignoreWhitespace(((PathNode) ((NodeConnection) pn.getPred().get(0)).getSource()), false);
					}
				}				
			}
		}
		return neighborPathNode;
	}

	public PathNode getElement() {
		return element;
	}

	public String getId() {
		return element.getId();
	}
	
	public String getName() {
		if (element instanceof RespRef) {
			return ((RespRef) element).getRespDef().getName();
		}
		if (element instanceof StartPoint || element instanceof EndPoint) {
			return element.getName();
		}
		// many path nodes do not usually have labels but a default name is nevertheless assigned to them by the tool; therefore, if no label 
		// is specified for a path node in the pointcut expression, anything should be matched against it - a wildcard is hence returned
		// furthermore, if a label is specified but it is empty, then also a wildcard should be returned
		//if ((element.getLabel() != null && element.getName() != "") || this instanceof Joinpoint) {
		//if ((element.getLabel() == null || element.getName() == null || element.getName().equals("")) && this instanceof PointcutElement) {
		if ((element.getLabel() == null || element.getName() == "") && this instanceof PointcutElement) { //$NON-NLS-1$
			return "*"; //$NON-NLS-1$
		} else {
			return element.getName();
		}
	}
	
	public String getContainerName() {
		// if a pointcut element does not have a container, then any element in a container can be matched
		// hence, a wildcard is returned
		// in the case of a joinpoint element, the empty String is returned which can only be matched if anything
		// matches the pointcut because the name of a container in a pointcut expression cannot be the empty string
		if (element.getContRef() == null)
			if (this instanceof PointcutElement)
				return "*"; //$NON-NLS-1$
			else
				return ""; //$NON-NLS-1$
		if (element.getContRef().getContDef() instanceof Component)
			return ((Component) element.getContRef().getContDef()).getName();
		else 
			return ((Actor) element.getContRef().getContDef()).getName();
	}

	public List<MatchableNeighbor> getNeighbors() {
		return neighbors;
	}
	
	protected abstract boolean isPointcutStartPoint();
	
	protected abstract boolean isPointcutEndPoint();
	
	protected abstract boolean isPointcutStartOrEndPoint();
	
}
