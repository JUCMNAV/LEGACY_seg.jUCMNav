package seg.jUCMNav.views.property;

import grl.Actor;
import grl.ActorRef;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KPIInformationElementRef;

import java.util.Vector;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;

import ucm.map.ComponentRef;
import ucm.map.RespRef;
import urn.URNspec;
import urncore.Component;
import urncore.Responsibility;
import urncore.URNmodelElement;

public class MetadataRefResolver {
    
    public IStructuredSelection adjustSelection(ISelection selection, EObject defaultSelected) {
        if (selection == null) {
            Vector v = new Vector();

            // choose only urn model elements
            if (defaultSelected instanceof URNmodelElement) {
                URNmodelElement defaultUrnelem = (URNmodelElement) defaultSelected;

                EObject o = defaultUrnelem.eContainer();
                URNspec urn = null;
                while (o != null) {
                    if (o instanceof URNspec) {
                        urn = (URNspec) o;
                    }

                    o = o.eContainer();
                }

                if (urn != null) {
                    TreeIterator urnIter = urn.eAllContents();
                    while (urnIter.hasNext()) {
                        Object obj = urnIter.next();

                        if (obj instanceof URNmodelElement) {
                            URNmodelElement urnelem = (URNmodelElement) obj;

                            // This code prevents the addition of metadata on *references* to intentional elements
                            // and components. This was also prevented for responsibility references in the past
                            // but this had to be allowed for CSM export.
                            // CHANGED on April 10, 2009: No longer allowed for resp. references... Too annoying.
                            if (urnelem instanceof IntentionalElementRef) {
                            } else if (urnelem instanceof KPIInformationElementRef) {
                            } else if (urnelem instanceof ActorRef) {
                            } else if (urnelem instanceof ComponentRef) {
                            } else if (urnelem instanceof RespRef) {
                            } else {
                                v.add(urnelem);
                            }
                        }
                    }
                }
            }
            else if(defaultSelected instanceof URNspec) {
                v.add(defaultSelected);
            }

            return new StructuredSelection(v);
        } else
            return null;
    }

    public EObject getRealObject(EObject defaultSelected) {
        EObject result;

        // This code prevents the addition of metadata on *references* to intentional elements
        // and components. This was also prevented for responsibility references in the past
        // but this had to be allowed for CSM export.
        // CHANGED on April 10, 2009: No longer allowed for resp. references... Too annoying.
        if (defaultSelected instanceof IntentionalElementRef) {
            IntentionalElement intentionalElem = ((IntentionalElementRef) defaultSelected).getDef();
            result = intentionalElem;
        } else if (defaultSelected instanceof RespRef) {
            Responsibility respElem = ((RespRef) defaultSelected).getRespDef();
            result = respElem;
        } else if (defaultSelected instanceof KPIInformationElementRef) {
            KPIInformationElement kpiInformationElem = ((KPIInformationElementRef) defaultSelected).getDef();
            result = kpiInformationElem;
        } else if (defaultSelected instanceof ComponentRef) {
            Component compElem = (Component) ((ComponentRef) defaultSelected).getContDef();
            result = compElem;
        } else if (defaultSelected instanceof ActorRef) {
            Actor actorElem = (Actor) ((ActorRef) defaultSelected).getContDef();
            result = actorElem;
        } else {
            result = defaultSelected;
        }

        return result;
    }
}
