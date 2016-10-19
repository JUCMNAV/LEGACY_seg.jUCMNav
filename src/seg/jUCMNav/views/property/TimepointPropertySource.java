package seg.jUCMNav.views.property;

import java.util.Collection;
import java.util.Date;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import seg.jUCMNav.Messages;
import seg.jUCMNav.views.property.descriptors.DatePropertyDescriptor;
import urn.URNspec;
import urn.dyncontext.Timepoint;
import urn.dyncontext.TimepointGroup;

/**
 * Property source for Timepoint
 * 
 * @author aprajita
 * 
 */
public class TimepointPropertySource extends URNElementPropertySource {
	
	private Timepoint def = null;

    /**
     * Constructor that initialize the Timepoint definition
     * 
     * @param obj
     */
    public TimepointPropertySource(EObject obj) {
        super(obj);
        if ((obj instanceof Timepoint) && ((Timepoint) obj).getGroup() != null) {
            def = (Timepoint) obj;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.views.EObjectPropertySource#isAddable(org.eclipse.emf.ecore.EStructuralFeature)
     */
    protected boolean canAddFeature(EStructuralFeature attr) {
        if (def != null && (attr.getName().equals("name") || attr.getName().equals("id") || attr.getName().equals("description"))) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            return false;
        } else
            return true;
    }


    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.views.EObjectPropertySource#addPropertyToDescriptor(java.util.Collection, org.eclipse.emf.ecore.EStructuralFeature,
     * org.eclipse.emf.ecore.EClass)
     */
    public void addPropertyToDescriptor(Collection descriptors, EStructuralFeature attr, EClass c) {
        EClassifier type = getFeatureType(attr);

        PropertyID propertyid = new PropertyID(c, attr);

        if (type.getInstanceClass() == Date.class) {
            timepointDescriptor(descriptors, attr, propertyid);
        } else if (type.getInstanceClass() == TimepointGroup.class) {
            timepointGroupDescriptor(descriptors, attr, propertyid);
        } else {
            super.addPropertyToDescriptor(descriptors, attr, c);
        }
    }

    /**
     * @param descriptors
     * @param attr
     * @param propertyid
     */
    private void timepointDescriptor(Collection descriptors, EStructuralFeature attr, PropertyID propertyid) {
        if (((Timepoint) getEditableValue()).getGroup() == null)
            return;
        DatePropertyDescriptor dp = new DatePropertyDescriptor(propertyid, ((Timepoint) getEditableValue())); //$NON-NLS-1$
        dp.setCategory(Messages.getString("TimepointPropertySource.timepoint")); //$NON-NLS-1$ 
        descriptors.add(dp);

    }
    
    /**
     * @param descriptors
     * @param attr
     * @param propertyid
     */
    private void timepointGroupDescriptor(Collection descriptors, EStructuralFeature attr, PropertyID propertyid) {
        if (((Timepoint) getEditableValue()).getGroup() == null)
            return;
        URNspec urn = ((Timepoint) getEditableValue()).getGroup().getUrnspec();
        TextPropertyDescriptor dp = new TextPropertyDescriptor(propertyid, "group"); //$NON-NLS-1$
        dp.setCategory(Messages.getString("TimepointPropertySource.timepoint")); //$NON-NLS-1$ 
        descriptors.add(dp);

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.views.EObjectPropertySource#returnPropertyValue(org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object)
     */
    protected Object returnPropertyValue(EStructuralFeature feature, Object result) {
        if (getFeatureType(feature).getInstanceClass() == Date.class && getEditableValue() instanceof Timepoint) {
        	result = ((Timepoint) getEditableValue()).getTimepoint();

        } else if (getFeatureType(feature).getInstanceClass() == TimepointGroup.class && getEditableValue() instanceof Timepoint) {
        	result = ((Timepoint) getEditableValue()).getGroup().toString();

        } else
            return super.returnPropertyValue(feature, result);

        return result;
    }

    /**
     * @param propertyid
     * @param feature
     * @return a feature
     */
    protected Object getFeature(PropertyID propertyid, EStructuralFeature feature) {
        Object result = null;

        // if this attribute comes from the referenced object
        if (propertyid.getEClass().getName() == "Timepoint") //$NON-NLS-1$
            result = def.eGet(feature);
        else
            result = object.eGet(feature);
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#resetPropertyValue(java.lang.Object)
     */
    public void resetPropertyValue(Object id) {
        PropertyID propertyid = (PropertyID) id;
        EStructuralFeature feature = propertyid.getFeature();

        if (feature.getName().toLowerCase().indexOf("color") >= 0 //$NON-NLS-1$
                || (feature instanceof EReference && ((EReference) feature).getEReferenceType().getInstanceClass() == Timepoint.class && (getEditableValue() instanceof Timepoint))) {
            def.eSet(feature, null);
        } else
            super.resetPropertyValue(id);
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
        if (feature.getEType().getInstanceClass() == Timepoint.class) {

            result = (Date) value;
            ((Timepoint) object).setTimepoint((Date) result);

            def = ((Timepoint) object);
        } else {
            if (feature.getName().equalsIgnoreCase("fillColor")) { //$NON-NLS-1$
                // To be enabled if the color is added
                // ((KPIInformationElementRef) getEditableValue()).getDef().setFilled(true);
            }
            super.setPropertyValue(id, value);
        }
    }

}
