/**
 * 
 */
package seg.jUCMNav.aourn.matcher;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ucm.map.Anything;
import ucm.map.EndPoint;
import ucm.map.PathNode;
import ucm.map.StartPoint;

/**
 * @author gunterm
 *
 */
public class PointcutElement extends MatchableElement {

	/**
	 * @param element
	 */
	public PointcutElement(PathNode element) {
		super(element);
	}
	
	// the anything pointcut element needs to be treated differently; only if we're at the edges of the series of mappings for the
	// anything pointcut element are the neighbors returned; furthermore, only the neigbhors that are outside of the series of mappings
	// for the anything pointcut element are returned because the neighbors inside are the anything pointcut element itself. 
	public List<MatchableNeighbor> getNeighbors(Mapping mapping) {
		if (getElement() instanceof Anything) {
			List<MatchableNeighbor> anythingNeighbors = new ArrayList<MatchableNeighbor>();
			List<MatchableNeighbor> forwardNeighbors = new ArrayList<MatchableNeighbor>();
			List<MatchableNeighbor> backwardNeighbors = new ArrayList<MatchableNeighbor>();
			for (Iterator iter = getNeighbors().iterator(); iter.hasNext();) {
				MatchableNeighbor matchableNeighbor = (MatchableNeighbor) iter.next();
				if (matchableNeighbor.isForward()) {
					forwardNeighbors.add(matchableNeighbor);
				} else {
					backwardNeighbors.add(matchableNeighbor);
				}
			}
			// if this is the first mapping for the series of mappings for the anything pointcut element, then the "backward" neighbors are returned;
			if (mapping.isFirst()) {
				anythingNeighbors.addAll(backwardNeighbors);
			}
			// if this is the last mapping for the series of mappings for the anything pointcut element, then the "forward" neighbors are returned; 
			if (mapping.isLast()) {
				anythingNeighbors.addAll(forwardNeighbors);
			}
			// if the mapping is in the middle (neither first nor last), no neighbor is returned; however, this can only happen if a loop
			// exists and loop detection should not let this case happen
			return anythingNeighbors;
		}
		return getNeighbors();
	}
	
	@Override
	public boolean isPointcutStartPoint() {
		return (getElement() instanceof StartPoint && getName().equals("") && (((StartPoint) getElement()).getPred().size() == 0)); //$NON-NLS-1$
	}

	@Override
	public boolean isPointcutEndPoint() {
		return (getElement() instanceof EndPoint && getName().equals("") && (((EndPoint) getElement()).getSucc().size() == 0)); //$NON-NLS-1$
	}
	
	@Override
	public boolean isPointcutStartOrEndPoint() {
		return (isPointcutStartPoint() || isPointcutEndPoint());
	}

}
