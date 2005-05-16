package seg.jUCMNav.model.util;

import java.util.Iterator;

import seg.jUCMNav.model.ModelCreationFactory;
import ucm.map.ComponentRef;
import ucm.map.Map;
import ucm.map.PathNode;
import ucm.map.RespRef;
import urn.URNspec;
import urncore.Component;
import urncore.ComponentElement;
import urncore.ComponentKind;
import urncore.Responsibility;

/**
 * Created on 13-May-2005
 * 
 * @author jkealey
 *  
 */
public class URNReferencerChecker {

    public static void sanitizeReferences(URNspec urn) {

        // for each map
        for (Iterator maps = urn.getUcmspec().getMaps().iterator(); maps.hasNext();) {
            Map map = (Map) maps.next();

            // verify that all component refs have definitions
            for (Iterator iter = map.getCompRefs().iterator(); iter.hasNext();) {
                ComponentRef compRef = (ComponentRef) iter.next();
                if (compRef.getCompDef() == null)
                    // not linked? create one.                    
                    compRef.setCompDef((ComponentElement) ModelCreationFactory.getNewObject(urn, Component.class, ComponentKind.TEAM));
                	urn.getUrndef().getComponents().add(compRef.getCompDef());
            }

            // verify that all responsibility references have definitions
            for (Iterator iter = map.getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
                PathNode node = (PathNode) iter.next();
                if (node instanceof RespRef && ((RespRef) node).getRespDef() == null) {
                    // not linked? create one.
                    ((RespRef) node).setRespDef((Responsibility) ModelCreationFactory.getNewObject(urn, Responsibility.class));
                    urn.getUrndef().getResponsibilities().add(((RespRef)node).getRespDef());
                }
            }

        }
    }
}