package seg.jUCMNav.scenarios.model;

public class TraversalResult {

	private int hitCount;
	private int externalHitCount;
	public TraversalResult() {
		hitCount=0;
	}
	public int getHitCount() {
		return hitCount;
	}
	
	public int getExternalHitCount() {
		return hitCount;
	}
	
	
	public void incrementHitCount() {
		hitCount++;
		externalHitCount++;
	}
	
	public void decrementHitCount() {
		externalHitCount--;
	}
		
	
	public void merge (TraversalResult res) {
		hitCount += res.getHitCount();
		externalHitCount += res.getExternalHitCount();
	}
}
