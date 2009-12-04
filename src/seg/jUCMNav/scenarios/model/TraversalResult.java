package seg.jUCMNav.scenarios.model;

/**
 * Represents the number of times an element was traversed by the algorithm. Used by the UI to know what to highlight.
 * 
 * A HashMap somewhere contains the mapping from object to traversal result.
 * 
 * @author jkealey
 * 
 */
public class TraversalResult {

    /** number of times we attempted this element */
    private int hitCount;

    /** number of unique times we attempted this element. re-queued items don't increase this value */
    private int externalHitCount;

    /**
     * Create a traversal result with zero visits.
     * 
     */
    public TraversalResult() {
        hitCount = 0;
    }

    /**
     * 
     * @return number of times we attempted this element
     */
    public int getHitCount() {
        return hitCount;
    }

    /**
     * 
     * @return number of unique times we attempted this element. re-queued items don't increase this value
     */
    public int getExternalHitCount() {
        return hitCount;
    }

    /**
     * We are attempting this element. Increment the hit count.
     * 
     */
    public void incrementHitCount() {
        hitCount++;
        externalHitCount++;
    }

    /**
     * We re-queued an element. Forget the last increment.
     * 
     */
    public void decrementHitCount() {
        externalHitCount--;
    }

    /**
     * Merge the parameter into this traversal (sum up the values); used when we launch multiple scenarios to compute coverage.
     * 
     * @param res
     *            the element to merge with.
     */
    public void merge(TraversalResult res) {
        hitCount += res.getHitCount();
        externalHitCount += res.getExternalHitCount();
    }
}
