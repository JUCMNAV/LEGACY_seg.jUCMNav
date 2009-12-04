package seg.jUCMNav.views.property;

import grl.kpimodel.KPIModelLink;
import grl.kpimodel.KPIModelLinkRef;

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
 * Property source for KPIModelLinkRefs
 * 
 * @author pchen
 * 
 */
public class KPIModelLinkRefPropertySource extends URNElementPropertySource {

    private KPIModelLink kpiModelLink = null;

    /**
     * @param obj
     */
    public KPIModelLinkRefPropertySource(EObject obj) {
        super(obj);
        if ((obj instanceof KPIModelLinkRef) && (((KPIModelLinkRef) obj).getLink() != null)) {
            kpiModelLink = ((KPIModelLinkRef) obj).getLink();
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

        if (kpiModelLink != null) {
            // we are referencing another object; show its properties here.
            it = kpiModelLink.eClass().getEAllAttributes().iterator();

            // add the new properties
            while (it.hasNext()) {
                EAttribute attr = (EAttribute) it.next();
                addPropertyToDescriptor(descriptors, attr, kpiModelLink.eClass());
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
            result = kpiModelLink.eGet(feature);
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
        URNspec urn = ((KPIModelLinkRef) getEditableValue()).getDiagram().getUrndefinition().getUrnspec();
        if (feature.getEType().getInstanceClass() == KPIModelLink.class) {

            Vector list = new Vector(urn.getGrlspec().getLinks());
            Collections.sort(list, new EObjectClassNameComparator());
            result = list.get(((Integer) value).intValue());
            setReferencedObject(propertyid, feature, result);
            kpiModelLink = ((KPIModelLinkRef) object).getLink();
        } else {
            super.setPropertyValue(id, value);
        }
    }

    protected void setReferencedObject(PropertyID propertyid, EStructuralFeature feature, Object result) {
        if (propertyid.getEClass() != object.eClass()) {
            kpiModelLink.eSet(feature, result);
        } else
            object.eSet(feature, result);
    }
}
