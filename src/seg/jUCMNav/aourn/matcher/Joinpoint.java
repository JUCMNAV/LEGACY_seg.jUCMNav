/**
 * 
 */
package seg.jUCMNav.aourn.matcher;

import ucm.map.PathNode;

/**
 * @author gunterm
 *
 */
public class Joinpoint extends MatchableElement {
	
	// the semantic neighbor is based on semantic equivalences (it is used as an alternative element if the element itself does not result in a match)
	private MatchableNeighbor semanticNeighbor;
	private MatchableNeighbor replacedNeighbor;

	/**
	 * @param pathNode
	 */
	public Joinpoint(PathNode pathNode) {
		super(pathNode);
		semanticNeighbor = null;
		replacedNeighbor = null;
	}

	public MatchableNeighbor getSemanticNeighbor() {
		return semanticNeighbor;
	}

	public MatchableNeighbor getReplacedNeighbor() {
		return replacedNeighbor;
	}

	public void replaceWithSemanticNeighbor(Joinpoint neighbor, MatchableNeighbor semanticNeighbor) {
		this.replacedNeighbor = getNeighbor(neighbor);
		this.semanticNeighbor = semanticNeighbor;
	}

	// TODO assumes that there is only one path going from this Joinpoint to the neighbor (may not be the case for stubs!) 
	private MatchableNeighbor getNeighbor(Joinpoint neighbor) {
		for (int i = 0; i < getNeighbors().size(); i++) {
			MatchableNeighbor mn = getNeighbors().get(i);
			if (mn.getElement().equals(neighbor))
				return mn;
		}
		return null;
	}
	
	@Override
	public boolean isPointcutStartPoint() {
		return false;
	}

	@Override
	public boolean isPointcutEndPoint() {
		return false;
	}
	
	@Override
	public boolean isPointcutStartOrEndPoint() {
		return false;
	}

}
