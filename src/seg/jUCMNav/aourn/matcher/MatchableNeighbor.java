package seg.jUCMNav.aourn.matcher;

import ucm.map.PathNode;


public class MatchableNeighbor {
	
	private MatchableElement element;
	// TODO make sure that the usage of forward is consistent for a GRL model
	private boolean forward;
	// timeout path
	private boolean timeout;
	// connect (used between pairs of end/start point, and for trigger paths of waiting places and timers)
	private boolean connect;
	// indicates whether this matchable element is a "forward" neighbor of a pointcut start point
	private boolean fromPointcutStartPoint;
	
	public MatchableNeighbor(PathNode pathNode, boolean forward, boolean timeout, boolean connect, MatchableElement element) {
		MatchableElement matchableElement = MatchableElementFactory.getMatchablePathNode(pathNode, element);
		assert matchableElement != null : "MatchableElement has not been created for PathNode " + pathNode.getId(); //$NON-NLS-1$
		this.element = matchableElement;
		this.forward = forward;
		this.timeout = timeout;
		this.connect = connect;
		this.fromPointcutStartPoint = element.isPointcutStartPoint() && forward;
	}
	
	public MatchableElement getElement() {
		return element;
	}
	
	public boolean isForward() {
		return forward;
	}
	
	public boolean isTimeout() {
		return timeout;
	}
	
	public boolean isConnect() {
		return connect;
	}

	public boolean isFromPointcutStartPoint() {
		return fromPointcutStartPoint;
	}

}
