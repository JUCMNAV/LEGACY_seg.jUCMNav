package seg.jUCMNav.strategies.util;

import grl.Contribution;
import grl.Decomposition;
import grl.DecompositionType;
import grl.ElementLink;
import grl.IntentionalElement;

import java.util.Iterator;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.util.MetadataHelper;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import urncore.Metadata;

public class IntentionalElementUtil {
    /**
     * Returns true if the element only contains optional destination links
     * @param elem
     * @return
     */
    public static boolean containsOnlyOptionalDestLink(IntentionalElement elem){
        Iterator it = elem.getLinksSrc().iterator();
        if (!it.hasNext()) {
            return false;
        }
        while (it.hasNext()) {
            ElementLink link = (ElementLink) it.next();
            if (link instanceof Contribution) {
                if (!ModelCreationFactory.containsMetadata(link.getMetadata(), ModelCreationFactory.getFeatureModelOptionalLinkMetadata())) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Returns the number of Mandatory destination links
     * @param element
     * @return
     */
    public static int getNumberOfMandatoryDestLinks(IntentionalElement element){
        int MandatoryLinks = 0;
        Iterator it = element.getLinksDest().iterator();
        while (it.hasNext()) {
            ElementLink link = (ElementLink) it.next();
            if (link instanceof Contribution) {
                if(ModelCreationFactory.containsMetadata(link.getMetadata(), ModelCreationFactory.getFeatureModelMandatoryLinkMetadata()))
                    MandatoryLinks++;
            }
        }
        return MandatoryLinks;
    }
    
    /**
     * Returns true if element has a XOR src link, and there exists a XOR brother element that has numerical evaluation at 100
     * @param elem
     * @return
     */
    public static boolean hasFullXorBrother(IntentionalElement elem) {
        Iterator it = elem.getLinksSrc().iterator();
        // if it doesn't have source, return falses
        if (!it.hasNext()) {
            return false;
        }
        while (it.hasNext()) {
            ElementLink link = (ElementLink) it.next();
            if (link instanceof Decomposition) {
                // for each source
                IntentionalElement srcElem = (IntentionalElement)link.getDest();
                // if decomposition type is XOR
                if (srcElem != null) {
                    if (srcElem.getDecompositionType() == DecompositionType.XOR_LITERAL) {
                        Iterator srcIt = srcElem.getLinksDest().iterator();
                        while (srcIt.hasNext()) {
                            ElementLink broLink = (ElementLink) srcIt.next();
                            if (broLink instanceof Decomposition) {
                                // for each brother element
                                IntentionalElement broElem = (IntentionalElement)broLink.getSrc();
                                if (!broElem.equals(elem) && hasNumericalValue(broElem, 100)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Returns true if element has a OR or XOR src link, and there exists a OR or XOR brother element that has numerical evaluation at 100
     * @param elem
     * @return
     */
    public static boolean hasFullOrBrother(final IntentionalElement elem) {
        Iterator it = elem.getLinksSrc().iterator();
        // if it doesn't have source, return falses
        if (!it.hasNext()) {
            return false;
        }
        while (it.hasNext()) {
            ElementLink link = (ElementLink) it.next();
            if (link instanceof Decomposition) {
                // for each source
                IntentionalElement srcElem = (IntentionalElement)link.getDest();
                // if decomposition type is XOR
                if (srcElem != null) {
                    if (srcElem.getDecompositionType() == DecompositionType.OR_LITERAL || srcElem.getDecompositionType() == DecompositionType.XOR_LITERAL) {
                        Iterator srcIt = srcElem.getLinksDest().iterator();
                        while (srcIt.hasNext()) {
                            ElementLink broLink = (ElementLink) srcIt.next();
                            if (broLink instanceof Decomposition) {
                                // for each brother element
                                IntentionalElement broElem = (IntentionalElement)broLink.getSrc();
                                if (!broElem.equals(elem) && hasNumericalValue(broElem, 100)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * Returns true if elem has given numerical value
     * Note: Null numerical value will be treated as 0
     * @param elem
     * @param value
     * @return
     */
    public static  boolean hasNumericalValue(IntentionalElement elem, int value) {
        String metaDataStr, valueStr;
        if (elem == null) return false;
        Metadata metaNumerical = MetadataHelper.getMetaDataObj(elem, EvaluationStrategyManager.METADATA_NUMEVAL);
        if (metaNumerical == null) metaDataStr = "0";
        else metaDataStr = metaNumerical.getValue();
        valueStr = Integer.toString(value);
        return metaDataStr.equals(valueStr);
    }
}
