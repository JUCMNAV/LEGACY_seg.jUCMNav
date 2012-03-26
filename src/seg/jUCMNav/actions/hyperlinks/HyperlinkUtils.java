package seg.jUCMNav.actions.hyperlinks;

import grl.Actor;
import seg.jUCMNav.actions.SelectionHelper;
import urncore.Component;
import urncore.URNmodelElement;

/**
 * Provides common utilities for hyperlink-related actions
 * 
 * @author damyot
 */

public class HyperlinkUtils {

    /**
     * Metadata name for hyperlinks
     */
    public static String HYPERLINK = "hyperlink"; //$NON-NLS-1$

    /**
     * Find the element abstracting from common references
     */
    public static URNmodelElement findURNmodelElement(SelectionHelper sel) {
        URNmodelElement element = null;

        if (sel.getSelectionType() == SelectionHelper.INTENTIONALELEMENTREF) {
            element = sel.getIntentionalElementRef().getDef();
        } else if (sel.getSelectionType() == SelectionHelper.ACTORREF) {
            element = (Actor) sel.getActorref().getContDef();
        } else if (sel.getSelectionType() == SelectionHelper.RESPONSIBILITYREF) {
            element = sel.getRespRef().getRespDef();
        } else if (sel.getSelectionType() == SelectionHelper.COMPONENTREF) {
            element = (Component) sel.getComponentref().getContDef();
        } else if (sel.getSelectionType() == SelectionHelper.LINKREF) {
            element = sel.getLinkref().getLink();
        } else if (sel.getURNmodelElement() != null) {
            element = sel.getURNmodelElement();
        }

        return element;
    }
}
