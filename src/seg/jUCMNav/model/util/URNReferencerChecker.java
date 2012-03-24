package seg.jUCMNav.model.util;

import grl.Contribution;
import grl.GRLGraph;
import grl.LinkRef;

import java.util.Iterator;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionEndpointLocator;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;

import seg.jUCMNav.model.ModelCreationFactory;
import ucm.map.ComponentRef;
import ucm.map.PathNode;
import ucm.map.PluginBinding;
import ucm.map.RespRef;
import ucm.map.Stub;
import ucm.map.UCMmap;
import urn.URNspec;
import urncore.Component;
import urncore.ComponentKind;
import urncore.Condition;
import urncore.ConnectionLabel;
import urncore.IURNConnection;
import urncore.IURNDiagram;
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
     * Check the PluginBinding too to see if it has a Condition. If not, create a default one.
     * 
     * @param urn
     *            the URNspec to clean
     */
    public static void sanitizeReferences(URNspec urn) {

        // for each map
        for (Iterator maps = urn.getUrndef().getSpecDiagrams().iterator(); maps.hasNext();) {
            IURNDiagram g = (IURNDiagram) maps.next();
            if (g instanceof UCMmap) {
                UCMmap map = (UCMmap) g;

                // verify that all component refs have definitions
                for (Iterator iter = map.getContRefs().iterator(); iter.hasNext();) {
                    ComponentRef compRef = (ComponentRef) iter.next();
                    if (compRef.getContDef() == null)
                        // not linked? create one.
                        compRef.setContDef((Component) ModelCreationFactory.getNewObject(urn, Component.class, ComponentKind.TEAM));
                    urn.getUrndef().getComponents().add(compRef.getContDef());
                }

                // verify that all responsibility references have definitions
                for (Iterator iter = map.getNodes().iterator(); iter.hasNext();) {
                    PathNode node = (PathNode) iter.next();
                    if (node instanceof RespRef && ((RespRef) node).getRespDef() == null) {
                        // not linked? create one.
                        ((RespRef) node).setRespDef((Responsibility) ModelCreationFactory.getNewObject(urn, Responsibility.class));
                        urn.getUrndef().getResponsibilities().add(((RespRef) node).getRespDef());
                    }

                    if (node instanceof Stub) {
                        Stub stub = (Stub) node;
                        for (Iterator i = stub.getBindings().iterator(); i.hasNext();) {
                            PluginBinding plug = (PluginBinding) i.next();
                            if (plug.getPrecondition() == null)
                                plug.setPrecondition((Condition) ModelCreationFactory.getNewObject(urn, Condition.class));
                        }
                    }
                }
            } else if (g instanceof GRLGraph) {
                GRLGraph grlGraph = (GRLGraph)g;
                for (Iterator i = grlGraph.getConnections().iterator(); i.hasNext();) {
                    IURNConnection con = (IURNConnection) i.next();
                    if(con instanceof LinkRef) {
                        LinkRef ref = (LinkRef)con;
                        if(ref.getLink() instanceof Contribution) {
                            if(ref.getLabel() == null) {
                                ConnectionLabel labelTarget = (ConnectionLabel)ModelCreationFactory.getNewObject(urn, ConnectionLabel.class);
                              
                              
                                int varY = ref.getTarget().getY() - ref.getSource().getY();
                                int varX = ref.getTarget().getX() - ref.getSource().getX();
                                
                                // very cheap algo - could be improved. 
                                if (varX>=0 && varY>=0 || Math.abs(varX)<50)
                                {
                                    labelTarget.setDeltaX((int)(varX*0.1));
                                    labelTarget.setDeltaY((int)(varY*0.1));
                                }
                                else if (varX>=0 && varY<0)
                                {
                                    labelTarget.setDeltaX((int)(varX*0.1));
                                    labelTarget.setDeltaY((int)(varY*0.1)-50);
                                }   
                                else if (varX<0 && varY>=0)
                                {
                                    labelTarget.setDeltaX((int)(varX*0.1)-150);
                                    labelTarget.setDeltaY((int)(varY*0.1)-25);
                                }  else
                                {
                                    labelTarget.setDeltaX((int)(varX*0.1)-150);
                                    labelTarget.setDeltaY((int)(varY*0.1)-50);
                                }
                                ref.setLabel(labelTarget);
                                
                            }
                        }
                    }
                }
            }
        }

        // uncomment the following to verify that components with colors are filled
        /*
         * for (Iterator iter = urn.getUrndef().getComponents().iterator(); iter.hasNext();) { Component comp = (Component) iter.next(); if (comp.getFillColor()
         * != null && comp.getFillColor().length() > 0 && !comp.isFilled()) comp.setFilled(true); }
         */
    }
}