package seg.jUCMNav.views.property;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.views.properties.ColorPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource2;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import ucm.map.MapPackage;

/**
 * Created on 2005-01-30
 * 
 * This class is intended to be a generic property source for all the objects in the application's model.
 * 
 * It currently supports int, String, boolean and Colors
 * 
 * Colors are supported if the following conditions hold: a) The field is a string b) The field name contains the word "color"
 * 
 * We support a basic reset mechanism to put back colors to their default value (null) because the ColorPropertyDescriptor does not have a "transparent" RGB
 * value.
 * 
 * We currently do not support or nested properties.
 * 
 * ComponentRefs are listed as a dropdown of all possible parents.
 * 
 * @author ddean, etremblay, jkealey
 *  
 */
public class EObjectPropertySource implements IPropertySource2 {
    protected EObject object;

    protected MapPackage ucmPackage;

    /**
     * Contruct an EObjectPropertySource from an EObject.
     * 
     * @param obj
     */
    public EObjectPropertySource(EObject obj) {
        this.object = obj;

        Map registry = EPackage.Registry.INSTANCE;
        String networkURI = MapPackage.eNS_URI;
        ucmPackage = (MapPackage) registry.get(networkURI);
    }

    /**
     * Returns the internal object to be shown.
     */
    public Object getEditableValue() {
        return object;
    }

    /**
     * This function will loop trough all the property of this EObject and find the one that could be represented by our property descriptors. We have to create
     * a property descriptor for each property we want to be able to edit in our property page.
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyDescriptors()
     */
    public IPropertyDescriptor[] getPropertyDescriptors() {

        Iterator it;
        EClass cls = object.eClass();
        Collection descriptors = new Vector();

        it = cls.getEAllStructuralFeatures().iterator();
        while (it.hasNext()) {
            EStructuralFeature attr = (EStructuralFeature) it.next();

            // Can we add this feature in our property descriptor
            if (canAddFeature(attr))
                addPropertyToDescriptor(descriptors, attr, object.eClass());
        }

        // Add more property descriptor as needed by subclass.
        descriptors.addAll(addSpecificProperties());

        return (IPropertyDescriptor[]) descriptors.toArray(new IPropertyDescriptor[] {});
    }

    /**
     * This method is called by getPropertyDescriptors() to get a list of additionnal properties to add to this descriptor. Subclass can overwrite this method
     * to add more properties to the property descritor.
     */
    protected Vector addSpecificProperties() {
        return new Vector();
    }

    /**
     * This function is called whenever we want to know if we can or not add a feature to the descriptor. Subclass can overwrite this to delete some unwanted
     * features from the list
     * 
     * @param attr
     */
    protected boolean canAddFeature(EStructuralFeature attr) {
        return true;
    }

    /**
     * This function will add an attribute to the current descriptor. Currently, it handles a limited number of types.
     * 
     * @param descriptors
     * @param attr
     * @param type
     */
    public void addPropertyToDescriptor(Collection descriptors, EStructuralFeature attr, EClass c) {
        // Get type for the structural feature
        EClassifier type = getFeatureType(attr);

        String propertyname = attr.getName();

        // we are changing the passed property parameter because our feature id depends on the Eclass, which it does not contain.
        //String propertyid = Integer.toString(attr.getFeatureID());
        Object[] propertyid = { c, attr };

        if (attr instanceof EAttribute && ((EAttribute) attr).isID()) {
            // shouldn't be editable
            descriptors.add(new PropertyDescriptor(propertyid, propertyname));
        } else if (type.getInstanceClass() == String.class) {
            stringDescriptor(descriptors, attr, propertyid);
        } else if (type.getInstanceClass() == boolean.class) {
            booleanDescriptor(descriptors, attr, propertyid);
        } else if (type.getInstanceClass() == int.class) {
            intDescriptor(descriptors, attr, propertyid);
        }

    }

    /**
     * Return the type of the feature
     * 
     * @param attr
     * @return
     */
    protected EClassifier getFeatureType(EStructuralFeature attr) {
        EClassifier type;
        if (attr instanceof EAttribute)
            type = ((EAttribute) attr).getEAttributeType();
        else
            // if (attr instanceof EReference)
            type = ((EReference) attr).getEReferenceType(); // ok to crash if not EReference.
        return type;
    }

    /**
     * Build a boolean property descriptor
     * 
     * @param descriptors
     * @param propertyname
     * @param propertyid
     */
    private void booleanDescriptor(Collection descriptors, EStructuralFeature attr, Object[] propertyid) {
        // this class doesn't exist. Etienne had recreated it, but it relies on a bogus class in the framework.
        //descriptors.add(new CheckboxPropertyDescriptor(Integer.toString(attr.getFeatureID()), attr.getName()));
        //		String[] values = { "false", "true" };
        CheckboxPropertyDescriptor pd = new CheckboxPropertyDescriptor(propertyid, attr.getName());

        String name = attr.getName().toLowerCase();
        if (name.equals("fixed")) {
            pd.setCategory("Appearance");
        } else if (object.eClass() != (EClass) propertyid[0]) {
            pd.setCategory("Reference");
        } else {
            pd.setCategory("Misc");
        }

        descriptors.add(pd);

    }

    /**
     * Build a string property descriptor
     * 
     * @param descriptors
     * @param attr
     * @param propertyname
     * @param propertyid
     */
    private void stringDescriptor(Collection descriptors, EStructuralFeature attr, Object[] propertyid) {
        PropertyDescriptor pd;
        String name = attr.getName().toLowerCase();
        if (name.indexOf("color") >= 0) {
            pd = new ColorPropertyDescriptor(propertyid, attr.getName());
            descriptors.add(pd);
        } else if (name.equals("id")) {
            CustomTextPropertyDescriptor text = new CustomTextPropertyDescriptor(propertyid, attr.getName());
            text.setReadOnly(true);
            pd = text;
        } else {
            pd = new TextPropertyDescriptor(propertyid, attr.getName());
        }

        if (name.equals("id") || name.equals("name") || name.equals("description")) {
            pd.setCategory("Info");
        } else if (name.indexOf("color") >= 0) {
            pd.setCategory("Appearance");
        } else if (object.eClass() != (EClass) propertyid[0]) {
            pd.setCategory("Reference");
        } else {
            pd.setCategory("Misc");
        }
        descriptors.add(pd);
    }

    /**
     * int property descriptor
     * 
     * @param descriptors
     * @param attr
     * @param propertyid
     */
    private void intDescriptor(Collection descriptors, EStructuralFeature attr, Object[] propertyid) {
        TextPropertyDescriptor desc = new TextPropertyDescriptor(propertyid, attr.getName());

        ((PropertyDescriptor) desc).setValidator(new ICellEditorValidator() {
            public String isValid(Object value) {
                int intValue = -1;
                try {
                    intValue = Integer.parseInt((String) value);
                } catch (NumberFormatException exc) {
                    return "Not a number";
                }
                return (intValue >= 0) ? null : "Value must be >=  0";
            }
        });
        String name = attr.getName().toLowerCase();
        if (name.equals("x") || name.equals("y") || name.equals("height") || name.equals("width")) {
            desc.setCategory("Appearance");
        } else if (object.eClass() != (EClass) propertyid[0]) {
            desc.setCategory("Reference");
        } else {
            desc.setCategory("Misc");
        }

        descriptors.add(desc);
    }

    /**
     * Given the property id, return the contained value
     */
    public Object getPropertyValue(Object id) {

        //int propertyid = Integer.parseInt((String) id);
        //EStructuralFeature feature = object.eClass().getEStructuralFeature(propertyid);
        Object[] o = (Object[]) id;
        EStructuralFeature feature = (EStructuralFeature) o[1];

        Object result = getFeature(o, feature);

        result = returnPropertyValue(feature, result);

        return result != null ? result : "";
    }

    /**
     * 
     * @param o
     * @param feature
     * @return
     */
    protected Object getFeature(Object[] o, EStructuralFeature feature) {
        return object.eGet(feature);
    }

    /**
     * @param feature
     * @param result
     * @return
     */
    protected Object returnPropertyValue(EStructuralFeature feature, Object result) {
        if (result instanceof Integer) {
            result = ((Integer) result).toString();
        } else if (result instanceof Boolean) {
            //            result = ((Boolean) result).booleanValue() ? new Integer(1) : new Integer(0);
            result = (Boolean) result;
        } else if (feature.getName().toLowerCase().indexOf("color") >= 0) {
            if (result == null || ((String) result).length() == 0)
                result = new RGB(0, 0, 0);
            else
                result = StringConverter.asRGB((String) result);
        }
        return result;
    }

    /**
     * For colors, we want to be able to reset to the default value (null) so we have to implement this method, indicating when the property is not at its
     * default value.
     * 
     * Same added for parent ComponentRef.
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#isPropertySet(java.lang.Object)
     */
    public boolean isPropertySet(Object id) {
        Object[] o = (Object[]) id;
        EStructuralFeature feature = (EStructuralFeature) o[1];

        if (feature.getName().toLowerCase().indexOf("color") >= 0) {
            return getPropertyValue(id) != null;
        }

        return false;
    }

    /**
     * Reset colors to their default value (null).
     * 
     * Same added for parent ComponentRef.
     * 
     * @see org.eclipse.ui.views.properties.IPropertySouce#resetPropertyValue(java.lang.Object)
     */
    public void resetPropertyValue(Object id) {
        Object[] o = (Object[]) id;
        EStructuralFeature feature = (EStructuralFeature) o[1];

        if (feature instanceof EReference)
            object.eSet(feature, null);
    }

    /**
     * The user has changed a property in the interface; change the model to reflect the user's action.
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
     */
    public void setPropertyValue(Object id, Object value) {
        //int propertyid = Integer.parseInt((String) id);
        //EStructuralFeature feature = object.eClass().getEStructuralFeature(propertyid);
        //EStructuralFeature feature = (EStructuralFeature) id;
        Object[] o = (Object[]) id;
        EStructuralFeature feature = (EStructuralFeature) o[1];

        Object result = getPropertyValue(id);

        if (feature.getEType().getInstanceClass() == int.class) {
            result = new Integer(Integer.parseInt((String) value));
        } else if (feature.getEType().getInstanceClass() == boolean.class) {
            result = value;
        } else if (result instanceof RGB) {
            result = StringConverter.asString((RGB) value);
        } else
            result = value;

        setReferencedObject(o, feature, result);
    }

    /**
     * @param o
     * @param feature
     * @param result
     */
    protected void setReferencedObject(Object[] o, EStructuralFeature feature, Object result) {
        // if this attribute concerns a referenced object.
        if ((EClass) o[0] == object.eClass())
            object.eSet(feature, result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource2#isPropertyResettable(java.lang.Object)
     */
    public boolean isPropertyResettable(Object id) {
        Object[] o = (Object[]) id;
        EStructuralFeature feature = (EStructuralFeature) o[1];

        return feature.getName().toLowerCase().indexOf("color") >= 0;

    }

}