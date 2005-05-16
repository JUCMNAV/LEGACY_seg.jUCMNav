package seg.jUCMNav.views.property;

import java.util.Collection;
import java.util.Vector;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;

import seg.jUCMNav.model.util.ParentFinder;
import ucm.map.ComponentRef;
import ucm.map.PathNode;
import urncore.UCMmodelElement;

/**
 * UCMElementPropertySource adds code to replace the parent attribute in ucm model elements to represent the ComponentRef to which this ucm element is bound to.
 * Both ComponentRefs and PathNodes are bound to the parent.
 * 
 * @author jkealey, etremblay
 *  
 */
public class UCMElementPropertySource extends EObjectPropertySource {

    /**
     * @param obj
     */
    public UCMElementPropertySource(EObject obj) {
        super(obj);
    }

    public void addPropertyToDescriptor(Collection descriptors, EStructuralFeature attr, EClass c) {
        // Get type for the structural feature
        EClassifier type = getFeatureType(attr);
        Object[] propertyid = { c, attr };

        if (type.getInstanceClass() == ComponentRef.class && (getEditableValue() instanceof PathNode || getEditableValue() instanceof ComponentRef)
                && attr.getUpperBound() == 1) {
            componentRefDescriptor(descriptors, attr, propertyid);
        } else
            super.addPropertyToDescriptor(descriptors, attr, c);
    }

    protected Object returnPropertyValue(EStructuralFeature feature, Object result) {
        if (feature instanceof EReference && ((EReference) feature).getEReferenceType().getInstanceClass() == ComponentRef.class
                && (getEditableValue() instanceof PathNode || getEditableValue() instanceof ComponentRef)) {
            //&& feature.getName().toLowerCase().indexOf("parent") >= 0) {
            Vector list = ParentFinder.getPossibleParents((UCMmodelElement) getEditableValue());
            for (int i = 0; i < list.size(); i++) {
                ComponentRef parent;
                if (getEditableValue() instanceof ComponentRef)
                    parent = ((ComponentRef) getEditableValue()).getParent();
                else
                    parent = ((PathNode) getEditableValue()).getCompRef();
                if (list.get(i).equals(parent))
                    result = new Integer(i + 1);
            }
            if (result == null)
                result = new Integer(0);

        } else
            result = super.returnPropertyValue(feature, result);

        return result;
    }

    public void setPropertyValue(Object id, Object value) {
        Object[] o = (Object[]) id;
        EStructuralFeature feature = (EStructuralFeature) o[1];

        Object result = getPropertyValue(id);

        if (feature instanceof EReference && ((EReference) feature).getEReferenceType().getInstanceClass() == ComponentRef.class
                && (getEditableValue() instanceof PathNode || getEditableValue() instanceof ComponentRef)) {
            Vector list = ParentFinder.getPossibleParents((UCMmodelElement) getEditableValue());
            if (((Integer) value).equals(new Integer(0)))
                result = null;
            else
                result = list.get(((Integer) value).intValue() - 1);
            setReferencedObject(o, feature, result);
        } else
            super.setPropertyValue(id, value);
    }

    private void componentRefDescriptor(Collection descriptors, EStructuralFeature attr, Object[] propertyid) {
        Vector list = ParentFinder.getPossibleParents((UCMmodelElement) getEditableValue());
        String[] values = new String[list.size() + 1];
        values[0] = "[unbound]";
        for (int i = 1; i < list.size() + 1; i++) {
            values[i] = ((ComponentRef) list.get(i - 1)).getCompDef().getName();
            if (values[i] == null)
                values[i] = "[unnamed]";
        }

        descriptors.add(new ComboBoxPropertyDescriptor(propertyid, "parent", values));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#isPropertySet(java.lang.Object)
     */
    public boolean isPropertySet(Object id) {
        Object[] o = (Object[]) id;
        EStructuralFeature feature = (EStructuralFeature) o[1];

        if (feature instanceof EReference && ((EReference) feature).getEReferenceType().getInstanceClass() == ComponentRef.class
                && (getEditableValue() instanceof PathNode || getEditableValue() instanceof ComponentRef)) {
            return ((Integer) getPropertyValue(id)).intValue() > 0;
        } else
            return super.isPropertySet(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#resetPropertyValue(java.lang.Object)
     */
    public void resetPropertyValue(Object id) {
        Object[] o = (Object[]) id;
        EStructuralFeature feature = (EStructuralFeature) o[1];

        if (feature.getName().toLowerCase().indexOf("color") >= 0
                || (feature instanceof EReference && ((EReference) feature).getEReferenceType().getInstanceClass() == ComponentRef.class && (getEditableValue() instanceof PathNode || getEditableValue() instanceof ComponentRef))) {
            object.eSet(feature, null);
        } else
            super.resetPropertyValue(id);
    }
}