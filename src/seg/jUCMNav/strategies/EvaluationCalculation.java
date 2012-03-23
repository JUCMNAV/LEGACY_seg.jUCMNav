package seg.jUCMNav.strategies;

import grl.IntentionalElement;

/**
 * Data container object used by the propagation mechanism.
 * 
 * @author Jean-François Roy
 * 
 */
public class EvaluationCalculation {
    public IntentionalElement element;
    public int linkCalc;
    public int totalLinkDest;

    public EvaluationCalculation(IntentionalElement element, int totalLink) {
        this.element = element;
        this.totalLinkDest = totalLink;
        linkCalc = 0;
    }
}
