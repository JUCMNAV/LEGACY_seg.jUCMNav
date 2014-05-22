package seg.jUCMNav.strategies;

import grl.IntentionalElement;

/**
 * Data container object used by the propagation mechanism.
 * 
 * @author Jean-François Roy, Yanji Liu, gunterm
 * 
 */
public class EvaluationCalculation {
    private IntentionalElement element;
    private int linkCalc;
    private int totalLinkDest;

    public EvaluationCalculation(IntentionalElement element, int totalLink) {
        this.element = element;
        this.totalLinkDest = totalLink;
        linkCalc = 0;
    }

	public IntentionalElement getElement() {
		return element;
	}
    
    public boolean hasReachedTotalLink() {
   		return this.linkCalc >= this.totalLinkDest;
    }
    
    public void incrementLinkCalc() {
    	this.linkCalc++;
    }
}
