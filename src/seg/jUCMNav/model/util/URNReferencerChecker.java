package seg.jUCMNav.model.util;

import java.util.Iterator;

import seg.jUCMNav.model.ModelCreationFactory;
import ucm.map.ComponentRef;
import ucm.map.Map;
import ucm.map.PathNode;
import ucm.map.PluginBinding;
import ucm.map.RespRef;
import ucm.map.Stub;
import urn.URNspec;
import urncore.Component;
import urncore.ComponentElement;
import urncore.ComponentKind;
import urncore.Condition;
import urncore.Responsibility;

/**
 * Verifies that references have definitions; if not, creates them. To be used on Component and Responsibility references.
 * 
 * @author jkealey
 *  
 */
public class URNReferencerChecker {

    /**
     * For each ComponentRef and RespRef in any of the maps, will verify if it has a definition. If not, creates one.
     * 
     * Check the PluginBinding too to see if it has a Condition.  If not, create a default one.
     * 
     * @param urn
     *            the URNspec to clean
     */
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
                    urn.getUrndef().getResponsibilities().add(((RespRef) node).getRespDef());
                }
                
                if(node instanceof Stub) {
                	Stub stub = (Stub)node;
                	for (Iterator i = stub.getBindings().iterator(); i.hasNext();) {
						PluginBinding plug = (PluginBinding) i.next();
						if(plug.getPrecondition() == null)
							plug.setPrecondition((Condition) ModelCreationFactory.getNewObject(urn, Condition.class));
					}
                }
            }

        }

        // uncomment the following to verify that components with colors are filled
        /*
         * for (Iterator iter = urn.getUrndef().getComponents().iterator(); iter.hasNext();) { ComponentElement comp = (ComponentElement) iter.next(); if
         * (comp.getFillColor() != null && comp.getFillColor().length() > 0 && !comp.isFilled()) comp.setFilled(true); }
         */
    }
}