package seg.jUCMNav.strategies.util;

import grl.Contribution;
import grl.ElementLink;
import grl.GRLGraph;
import grl.GRLspec;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.LinkRef;

import java.util.HashSet;
import java.util.Set;

import urncore.IURNDiagram;

public class ReusedElementUtil {

	private ReusedElementUtil() {

	}

	/**
	 * Returns a set containing all the reused intentional elements in the
	 * provided grlspec.
	 * 
	 * @param grlspec
	 *            the grlspec containing all the reused intentional elements
	 * @return a set of reused intentional elements from the grlspec
	 */
	public static synchronized Set<IntentionalElement> getReusedElements(
			GRLspec grlspec) {
		Set<IntentionalElement> reusedElements = new HashSet<IntentionalElement>();

		for (Object specDiagram : grlspec.getUrnspec().getUrndef()
				.getSpecDiagrams()) {
			if (specDiagram instanceof GRLGraph) {
				for (Object intElemRef : ((IURNDiagram) specDiagram).getNodes()) {
					IntentionalElement intElem = ((IntentionalElementRef) intElemRef)
							.getDef();
					if (isReusedElement(grlspec, intElem)) {
						reusedElements.add(intElem);
					}
				}
			}
		}

		return reusedElements;
	}

	/**
	 * Get all the elements which are reusing the specified intentional element
	 * in the specified grlspec.
	 * 
	 * @param grlspec
	 *            the grlspec where to check
	 * @param elem
	 *            to check
	 * @return a set containing all the reusing elements of the specified
	 *         intentional element
	 */
	public static synchronized Set<IntentionalElement> getReusingElements(
			GRLspec grlspec, IntentionalElement elem) {
		Set<IntentionalElement> reusingElements = new HashSet<IntentionalElement>();

		for (IntentionalElementRef intElemRef : getReusedElementRefs(grlspec,
				elem)) {
			for (Object linkRef : intElemRef.getSucc()) {
				reusingElements.add((IntentionalElement) ((LinkRef) linkRef)
						.getLink().getDest());
			}
		}

		return reusingElements;
	}

	/*
	 * Returns a set containing all the intentional element reference associated
	 * which have the specified intentional element as definition in the
	 * specified grlspec.
	 */
	private static synchronized Set<IntentionalElementRef> getReusedElementRefs(
			GRLspec grlspec, IntentionalElement elem) {
		if (grlspec.equals(elem.getGrlspec())) {
			return null;
		}

		Set<IntentionalElementRef> reusedElemRefs = new HashSet<IntentionalElementRef>();

		for (Object specDiagram : grlspec.getUrnspec().getUrndef()
				.getSpecDiagrams()) {
			if (specDiagram instanceof GRLGraph) {
				for (Object intElemRef : ((IURNDiagram) specDiagram).getNodes()) {
					IntentionalElement intElem = ((IntentionalElementRef) intElemRef)
							.getDef();
					if (intElem.equals(elem)) {
						reusedElemRefs.add((IntentionalElementRef) intElemRef);
					}
				}
			}
		}

		return reusedElemRefs;
	}

	/**
	 * Verifies if the specified intentional element is a reused element in the
	 * provided grlspec.
	 * 
	 * @param grlspec
	 *            the grlspec where to check
	 * @param elem
	 *            the element to check
	 * @return true if the element is a reused element in the grlspec. Returns
	 *         false otherwise
	 */
	public static synchronized boolean isReusedElement(GRLspec grlspec,
			IntentionalElement elem) {
		return !(elem.getGrlspec().equals(grlspec));
	}

	/**
	 * Verifies if the specified link is a reuse link.
	 * 
	 * @param link
	 *            the link to check
	 * @return true is the link is a reuse link in the grlspec. Returns false
	 *         otherwise
	 */
	public static synchronized boolean isReuseLink(ElementLink link) {
		// return link instanceof Reuse;
		return (link instanceof Contribution && link.getName().substring(0, 5)
				.equals("Reuse")); //$NON-NLS-1$

	}

}
