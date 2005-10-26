package seg.jUCMNav.views.property;

import java.util.Vector;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import ucm.map.RespRef;
import urncore.ComponentLabel;
import urncore.Condition;
import urncore.NodeLabel;
import urncore.SpecificationComponentRef;
import urncore.SpecificationNode;

/**
 * Property source for labels. Extends URNElementPropertySource to obtain all necessary behaviour to list a label's properties. Includes an EPropertySource to
 * include all the properties of the labeled element.
 * 
 * @author jkealey
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
            if (cl.getCompRef().getSpecDiagram() != null)
                referencePS = new ComponentPropertySource(cl.getCompRef());
        } else if (obj instanceof NodeLabel) {
            NodeLabel nl = (NodeLabel) obj;
            SpecificationNode pn = (SpecificationNode)nl.getNode();
            if (pn.getSpecDiagram() != null) {
                if (pn instanceof RespRef)
                    referencePS = new ResponsibilityPropertySource(pn);
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

        }
    }

    /**
     * Adds all of the labeled element's properties
     */
    protected Vector addSpecificProperties() {

        Vector v = new Vector();

        if (referencePS != null) {

            if (referencePS.getEditableValue() instanceof SpecificationNode) {
                if (((SpecificationNode) referencePS.getEditableValue()).getSpecDiagram() == null)
                    return v;
            } else if (referencePS.getEditableValue() instanceof SpecificationComponentRef) {
                if (((SpecificationComponentRef) referencePS.getEditableValue()).getSpecDiagram() == null)
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