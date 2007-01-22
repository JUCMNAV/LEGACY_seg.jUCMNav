/**
 * 
 */
package seg.jUCMNav.views.property;

import grl.ElementLink;
import grl.LinkRef;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import seg.jUCMNav.model.util.EObjectClassNameComparator;
import urn.URNspec;

/**
 * @author Jean-François Roy
 *
 */
public class LinkRefPropertySource extends URNElementPropertySource {

    private ElementLink element = null;
    
    /**
     * @param obj
     */
    public LinkRefPropertySource(EObject obj) {
        super(obj);
        if ((obj instanceof LinkRef) && (((LinkRef)obj).getLink() != null)){
            element = ((LinkRef)obj).getLink();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.views.EObjectPropertySource#addSpecificProperties()
     */
    protected Vector addSpecificProperties() {
        Iterator it;
        Collection descriptors = new Vector();

        if (element != null) {
            // we are referencing another object; show its properties here.
            it = element.eClass().getEAllAttributes().iterator();

            // add the new properties
            while (it.hasNext()) {
                EAttribute attr = (EAttribute) it.next();
                addPropertyToDescriptor(descriptors, attr, element.eClass());
            }
        }
        return (Vector) descriptors;
    }
    
    
    /**
     * @param propertyid
     * @param feature
     * @return a feature
     */
    protected Object getFeature(PropertyID propertyid, EStructuralFeature feature) {
        Object result = null;

        // if this attribute comes from the referenced object
        if (propertyid.getEClass() != object.eClass())
            result = element.eGet(feature);
        else
            result = object.eGet(feature);
        return result;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
     */
    public void setPropertyValue(Object id, Object value) {
        PropertyID propertyid = (PropertyID) id;
        EStructuralFeature feature = propertyid.getFeature();

        Object result = getPropertyValue(id);
        URNspec urn = ((LinkRef) getEditableValue()).getDiagram().getUrndefinition().getUrnspec();
        if (feature.getEType().getInstanceClass() == ElementLink.class) {

            Vector list = new Vector(urn.getGrlspec().getLinks());
            Collections.sort(list, new EObjectClassNameComparator());
            result = list.get(((Integer) value).intValue());
            setReferencedObject(propertyid, feature, result);
            element = ((LinkRef) object).getLink();
        } else {
            super.setPropertyValue(id, value);
        }
    }
    
    protected void setReferencedObject(PropertyID propertyid, EStructuralFeature feature, Object result) {
        if (propertyid.getEClass() != object.eClass()) {
            element.eSet(feature, result);
        } else
            object.eSet(feature, result);
    }
}
