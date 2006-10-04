package seg.jUCMNav.scenarios.model;

public class TraversalResult {

	private int hitCount;

	public TraversalResult() {
		hitCount=0;
	}
	public int getHitCount() {
		return hitCount;
	}
	
	public void incrementHitCount() {
		hitCount++;
	}
}
