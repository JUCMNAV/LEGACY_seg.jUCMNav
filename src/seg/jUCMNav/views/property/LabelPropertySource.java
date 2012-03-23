package seg.jUCMNav.views.property;

import grl.IntentionalElementRef;
import grl.kpimodel.KPIInformationElementRef;

import java.util.Vector;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import ucm.map.RespRef;
import urncore.ComponentLabel;
import urncore.Condition;
import urncore.ConnectionLabel;
import urncore.IURNContainerRef;
import urncore.IURNNode;
import urncore.NodeLabel;

/**
 * Property source for labels. Extends URNElementPropertySource to obtain all necessary behaviour to list a label's properties. Includes an EPropertySource to
 * include all the properties of the labeled element.
 * 
 * @author jkealey, pchen
 * 
 */
public class LabelPropertySource extends URNElementPropertySource {

    URNElementPropertySource referencePS;

    /**
     * @param obj
     *            Must be a ComponentLabel or a NodeLabel.
     */
    public LabelPropertySource(EObject obj) {
        super(obj);
        if (obj instanceof ComponentLabel) {
            ComponentLabel cl = (ComponentLabel) obj;
            if (cl.getContRef().getDiagram() != null)
                referencePS = new ContainerPropertySource(cl.getContRef());
        } else if (obj instanceof NodeLabel) {
            NodeLabel nl = (NodeLabel) obj;
            IURNNode pn = nl.getNode();
            if (pn.getDiagram() != null) {
                if (pn instanceof RespRef)
                    referencePS = new ResponsibilityPropertySource(pn);
                else if (pn instanceof IntentionalElementRef)
                    referencePS = new IntentionalElementPropertySource(pn);
                else if (pn instanceof KPIInformationElementRef)
                    referencePS = new KPIInformationElementPropertySource(pn);
                else
                    referencePS = new URNElementPropertySource(pn);
            }
        } else if (obj instanceof Condition) {
            if (((Condition) obj).getNodeConnection() != null)
                referencePS = new URNElementPropertySource(((Condition) obj).getNodeConnection());
            else if (((Condition) obj).getStartPoint() != null)
                referencePS = new URNElementPropertySource(((Condition) obj).getStartPoint());
            else if (((Condition) obj).getEndPoint() != null)
                referencePS = new URNElementPropertySource(((Condition) obj).getEndPoint());
        } else if(obj instanceof ConnectionLabel) {
            new URNElementPropertySource(((ConnectionLabel) obj).getConnection());
        }
    }

    /**
     * Adds all of the labeled element's properties
     */
    protected Vector addSpecificProperties() {

        Vector v = new Vector();

        if (referencePS != null) {

            if (referencePS.getEditableValue() instanceof IURNNode) {
                if (((IURNNode) referencePS.getEditableValue()).getDiagram() == null)
                    return v;
            } else if (referencePS.getEditableValue() instanceof IURNContainerRef) {
                if (((IURNContainerRef) referencePS.getEditableValue()).getDiagram() == null)
                    return v;
            }

            IPropertyDescriptor pds[] = referencePS.getPropertyDescriptors();
            for (int i = 0; i < pds.length; i++) {
                if (((PropertyID) pds[i].getId()).getFeature().getEType().getInstanceClass() != Condition.class)
                    v.add(pds[i]);
            }
        }
        return v;
    }

    /**
     * Returns the property value. If is not the label's, delegates.
     */
    public Object getPropertyValue(Object id) {
        PropertyID propertyid = (PropertyID) id;
        if (propertyid.getEClass() != object.eClass() && referencePS != null)
            return referencePS.getPropertyValue(id);
        else
            return super.getPropertyValue(id);
    }

    /**
     * Sets the property value. If is not the label's, delegates.
     */
    public void setPropertyValue(Object id, Object value) {
        PropertyID propertyid = (PropertyID) id;
        if (propertyid.getEClass() != object.eClass() && referencePS != null)
            referencePS.setPropertyValue(id, value);
        else
            super.setPropertyValue(id, value);
    }
}