package seg.jUCMNav.strategies.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm;
import seg.jUCMNav.model.util.MetadataHelper;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import urncore.Metadata;
import fm.Feature;
import fm.MandatoryFMLink;
import fm.OptionalFMLink;
import grl.Decomposition;
import grl.DecompositionType;
import grl.ElementLink;
import grl.GRLspec;
import grl.IntentionalElement;

public class FeatureUtil {
    
	
	/**
	 * Returns true if elem does not have another feature as a child (may have other IntentionalElements as children but not Features)
	 * @param elem
	 * @return
	 */
	public static boolean isLeafFeature(Feature elem) {
        Iterator it = elem.getLinksDest().iterator();
        while (it.hasNext()) {
            ElementLink link = (ElementLink) it.next();
            if (link.getSrc() instanceof Feature) {
                return false;
            }
        }
		return true;
	}
	
	/**
	 * Returns true if elem does not have another feature as a parent (may have other IntentionalElements as parent but not features)
	 * @param elem
	 * @return
	 */
	private static boolean isRootFeature(Feature elem) {
        Iterator it = elem.getLinksSrc().iterator();
        while (it.hasNext()) {
            ElementLink link = (ElementLink) it.next();
            if (link.getDest() instanceof Feature) {
                return false;
            }
        }
		return true;
	}
	
	/**
	 * Given a GRLspec, the list of root features is returned
	 * @param grl
	 * @return
	 */
	public static List<Feature> getRootFeatures(GRLspec grl) {
		Iterator it = grl.getIntElements().iterator();
		List<Feature> rootFeatures = new ArrayList<Feature>();
		while (it.hasNext()) {
			IntentionalElement elem = (IntentionalElement) it.next();
			if (elem instanceof Feature && isRootFeature((Feature) elem))
				rootFeatures.add((Feature) elem);
		}
		return rootFeatures;
	}

	/**
     * Returns true if elem only has optional destination links, returns false otherwise
     * (note that elem is not allowed to have any other destination links even to IntentionalElements that are not Features)
     * Note: when no dest link, return false
     * @param elem
     * @return
     */
    public static boolean containsOnlyOptionalDestLink(Feature elem){
        Iterator it = elem.getLinksDest().iterator();
        if (!it.hasNext()) {
            return false;
        }
        while (it.hasNext()) {
            ElementLink link = (ElementLink) it.next();
            if (!(link instanceof OptionalFMLink)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Returns true if the element only has optional src links, returns false otherwise 
     * (only considers links to other features, may have other links to IntentionalElements that are not Features)
     * Note: when no src link, return false
     * @param elem
     * @return
     */
    public static boolean containsOnlyOptionalSrcLinkToFeature(Feature elem) {
    	if (FeatureUtil.isRootFeature(elem))
    		return false;
        Iterator it = elem.getLinksSrc().iterator();
        if (!it.hasNext()) {
            return false;
        }
        while (it.hasNext()) {
            ElementLink link = (ElementLink) it.next();
            if (link.getDest() instanceof Feature && !(link instanceof OptionalFMLink)) {
                return false;
            }
        }
        return true;
    }
    
	/**
	 * Returns true if elem only has src links to other features with an evaluation of 0
	 * (only considers links to other features, may have other links to IntentionalElements that are not Features)
	 * @param elem
	 * @return
	 */
	public static boolean containsOnlySrcLinkToNotSelectedFeature(Feature elem) {
		if (FeatureUtil.isRootFeature(elem))
			return false;
        Iterator it = elem.getLinksSrc().iterator();
        if (!it.hasNext()) {
            return false;
        }
        while (it.hasNext()) {
            ElementLink link = (ElementLink) it.next();
            if (link.getDest() instanceof Feature && !(checkSelectionStatus((Feature) link.getDest(), false))) {
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
    public static int getNumberOfMandatoryDestLinks(Feature element){
        int mandatoryLinks = 0;
        Iterator it = element.getLinksDest().iterator();
        while (it.hasNext()) {
            ElementLink link = (ElementLink) it.next();
            if (link instanceof MandatoryFMLink) {
            	mandatoryLinks++;
            }
        }
        return mandatoryLinks;
    }
    
    /**
     * Returns true if elem has an OR/XOR src link, and there exists an OR/XOR brother element that has numerical evaluation at 100
	 * (only considers links to other features, may have other links to IntentionalElements that are not Features)
	 * @param elem
     * @return
     */
    public static boolean hasSelectedOrXorBrother(Feature elem, boolean checkForOR, boolean checkForXOR) {
        Iterator it = elem.getLinksSrc().iterator();
        // if it doesn't have source, return false
        if (!it.hasNext()) {
            return false;
        }
        while (it.hasNext()) {
        	ElementLink link = (ElementLink) it.next();
        	IntentionalElement srcElem = (IntentionalElement)link.getDest();
        	if (srcElem instanceof Feature && link instanceof Decomposition) {
        		// if decomposition type is XOR or OR and the relevant type needs to be checked
        		if ((srcElem.getDecompositionType() == DecompositionType.OR_LITERAL && checkForOR) || 
        				(srcElem.getDecompositionType() == DecompositionType.XOR_LITERAL && checkForXOR)) {
        			Iterator srcIt = srcElem.getLinksDest().iterator();
        			while (srcIt.hasNext()) {
        				ElementLink broLink = (ElementLink) srcIt.next();
        				if (broLink instanceof Decomposition) {
        					// for each brother element
        					IntentionalElement broElem = (IntentionalElement)broLink.getSrc();
        					if (broElem instanceof Feature && !(broElem.equals(elem)) && checkSelectionStatus((Feature) broElem, true)) {
        						return true;
        					}
        				}
        			}
        		}
        	}
        }
        return false;
    }
    
    /**
     * Returns true if elem is either selected (isSelected = true) or not selected (isSelected = false)
     * Note: Null numerical value will be treated as 0
     * @param elem
     * @param isSelected
     * @return
     */
    public static boolean checkSelectionStatus(Feature elem, boolean isSelected) {
        String metaDataStr;
        if (elem == null) 
        	return false;
        Metadata metaNumerical = MetadataHelper.getMetaDataObj(elem, EvaluationStrategyManager.METADATA_NUMEVAL);
    	metaDataStr = "0";
    	if (metaNumerical != null) 
        	metaDataStr = metaNumerical.getValue();
    	int value = IGRLStrategyAlgorithm.FEATURE_NOT_SELECTED; 
    	if (isSelected)
    		value = IGRLStrategyAlgorithm.FEATURE_SELECTED;
    	return metaDataStr.equals(Integer.toString(value));
    }

}
