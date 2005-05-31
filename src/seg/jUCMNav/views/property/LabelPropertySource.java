package seg.jUCMNav.views.property;

import java.util.Vector;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import ucm.map.ComponentRef;
import ucm.map.PathNode;
import ucm.map.RespRef;
import urncore.ComponentLabel;
import urncore.NodeLabel;

/**
 * Created on 31-May-2005
 * 
 * Property source for labels. Extends UCMElementPropertySource to obtain all necessary behaviour to list a label's properties. Includes an EPropertySource to
 * include all the properties of the labeled element.
 * 
 * @author jkealey
 *  
 */
public class LabelPropertySource extends UCMElementPropertySource {

    UCMElementPropertySource referencePS;

    /**
     * @param obj
     *            Must be a ComponentLabel or a NodeLabel.
     */
    public LabelPropertySource(EObject obj) {
        super(obj);
        if (obj instanceof ComponentLabel) {
            ComponentLabel cl = (ComponentLabel) obj;
            if (cl.getCompRef().getMap() != null)
                referencePS = new ComponentPropertySource(cl.getCompRef());
        } else if (obj instanceof NodeLabel) {
            NodeLabel nl = (NodeLabel) obj;
            PathNode pn = nl.getPathNode();
            if (pn.getPathGraph() != null && pn.getPathGraph().getMap() != null) {
                if (pn instanceof RespRef)
                    referencePS = new ResponsibilityPropertySource(pn);
                else
                    referencePS = new UCMElementPropertySource(pn);
            }
        }
    }

    /**
     * Adds all of the labeled element's properties
     */
    protected Vector addSpecificProperties() {

        Vector v = new Vector();

        if (referencePS != null) {
            
            if (referencePS.getEditableValue() instanceof PathNode) {
                if (((PathNode)referencePS.getEditableValue()).getPathGraph()==null) return v;
            }
            else if (referencePS.getEditableValue() instanceof ComponentRef) {
                if (((ComponentRef)referencePS.getEditableValue()).getMap()==null) return v;
            }
              
            IPropertyDescriptor pds[] = referencePS.getPropertyDescriptors();
            for (int i = 0; i < pds.length; i++) {
                v.add(pds[i]);
            }
        }
        return v;
    }

    /**
     * Returns the property value. If is not the label's, delegates.
     */
    public Object getPropertyValue(Object id) {
        Object[] o = (Object[]) id;
        if ((EClass) o[0] != object.eClass() && referencePS != null)
            return referencePS.getPropertyValue(id);
        else
            return super.getPropertyValue(id);
    }

    /**
     * Sets the property value. If is not the label's, delegates.
     */
    public void setPropertyValue(Object id, Object value) {
        Object[] o = (Object[]) id;
        if ((EClass) o[0] != object.eClass() && referencePS != null)
            referencePS.setPropertyValue(id, value);
        else
            super.setPropertyValue(id, value);
    }
}