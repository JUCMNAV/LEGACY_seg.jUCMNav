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

import seg.jUCMNav.Messages;
import seg.jUCMNav.views.property.descriptors.CheckboxPropertyDescriptor;
import seg.jUCMNav.views.property.descriptors.CodePropertyDescriptor;
import seg.jUCMNav.views.property.descriptors.CustomTextPropertyDescriptor;
import seg.jUCMNav.views.property.descriptors.TextAreaPropertyDescriptor;
import ucm.map.FailurePoint;
import ucm.map.MapPackage;
import ucm.map.RespRef;
import urncore.Condition;

/**
 * This class is intended to be a generic property source for all the objects in the application's model.
 * 
 * It currently supports int, double, String, boolean and Colors
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
 * @author ddean, etremblay, jkealey, pchen
 * 
 */
public class EObjectPropertySource implements IPropertySource2 {
    protected EObject object;

    protected MapPackage ucmPackage;

    /**
     * Construct an EObjectPropertySource from an EObject.
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

        return (IPropertyDescriptor[]) descriptors.toArray(new IPropertyDescriptor[0]);
    }

    /**
     * This method is called by getPropertyDescriptors() to get a list of additional properties to add to this descriptor. Subclass can overwrite this method to
     * add more properties to the property descriptor.
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
     * @param c
     */
    public void addPropertyToDescriptor(Collection descriptors, EStructuralFeature attr, EClass c) {
        // Get type for the structural feature
        EClassifier type = getFeatureType(attr);

        String propertyname = attr.getName();

        // we are changing the passed property parameter because our feature id
        // depends on the Eclass, which it does not contain.
        // String propertyid = Integer.toString(attr.getFeatureID());
        PropertyID propertyid = new PropertyID(c, attr);

        if (attr instanceof EAttribute && ((EAttribute) attr).isID()) {
            // shouldn't be editable
            descriptors.add(new PropertyDescriptor(propertyid, propertyname));
        } else if (type.getInstanceClass() == String.class) {
            stringDescriptor(descriptors, attr, propertyid);
        } else if (type.getInstanceClass() == boolean.class) {
            booleanDescriptor(descriptors, attr, propertyid);
        } else if (type.getInstanceClass() == int.class) {
            intDescriptor(descriptors, attr, propertyid);
        } else if (type.getInstanceClass() == double.class) {
            doubleDescriptor(descriptors, attr, propertyid);
        }

    }

    /**
     * Return the type of the feature
     * 
     * @param attr
     * @return feature type
     */
    protected EClassifier getFeatureType(EStructuralFeature attr) {
        EClassifier type;
        if (attr instanceof EAttribute)
            type = ((EAttribute) attr).getEAttributeType();
        else
            // if (attr instanceof EReference)
            type = ((EReference) attr).getEReferenceType(); // ok to crash if
        // not EReference.
        return type;
    }

    /**
     * Build a boolean property descriptor
     * 
     * @param descriptors
     * @param attr
     * @param propertyid
     */
    protected void booleanDescriptor(Collection descriptors, EStructuralFeature attr, PropertyID propertyid) {
        CheckboxPropertyDescriptor pd = new CheckboxPropertyDescriptor(propertyid, attr.getName());

        String name = attr.getName().toLowerCase();
        if (name.equals("fixed") || name.equals("filled")) { //$NON-NLS-1$ //$NON-NLS-2$
            pd.setCategory(Messages.getString("EObjectPropertySource.appearance")); //$NON-NLS-1$
        } else if (name.equals("enabled")) { //$NON-NLS-1$ 
            pd.setCategory(Messages.getString("EObjectPropertySource.ScenarioStrategy")); //$NON-NLS-1$
        } else if (name.equalsIgnoreCase("isTimeMeasure") || name.equalsIgnoreCase("isCostMeasure") || name.equalsIgnoreCase("isQualityMeasure") || name.equalsIgnoreCase("isFlexibilityMeasure")) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
            pd.setCategory(Messages.getString("EObjectPropertySource.Indicator")); //$NON-NLS-1$
        } else if (object.eClass() != propertyid.getEClass()) {
            pd.setCategory(Messages.getString("EObjectPropertySource.reference")); //$NON-NLS-1$
        } else {
            pd.setCategory(Messages.getString("EObjectPropertySource.misc")); //$NON-NLS-1$
        }

        descriptors.add(pd);

    }

    /**
     * Build a string property descriptor
     * 
     * @param descriptors
     * @param attr
     * @param propertyid
     */
    protected void stringDescriptor(Collection descriptors, EStructuralFeature attr, PropertyID propertyid) {
        PropertyDescriptor pd;
        String name = attr.getName().toLowerCase();
        if (name.indexOf("color") >= 0) { //$NON-NLS-1$
            pd = new ColorPropertyDescriptor(propertyid, attr.getName());
        } else if (name.equals("id") || name.equals("created") || name.equals("modified") || name.equals("nextglobalid") || name.equals("urnversion") || name.equals("specversion") || (name.equals("type") && "Variable".equals(propertyid.getEClass().getName())) || (name.equals("values") && "EnumerationType".equals(propertyid.getEClass().getName()))) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$
            CustomTextPropertyDescriptor text = new CustomTextPropertyDescriptor(propertyid, attr.getName());
            text.setReadOnly(true);
            pd = text;
        } else if (name.equals("expression") && getEditableValue() instanceof Condition) //$NON-NLS-1$
        {
            // conditions have expressions
            pd = new CodePropertyDescriptor(propertyid, (Condition) getEditableValue());
        } else if (name.equals("expression") && getEditableValue() instanceof RespRef) //$NON-NLS-1$
        {
            // conditions have expressions
            pd = new CodePropertyDescriptor(propertyid, ((RespRef) getEditableValue()).getRespDef());
        } else if (name.equals("expression") && getEditableValue() instanceof FailurePoint) //$NON-NLS-1$
        {
            // conditions have expressions
            pd = new CodePropertyDescriptor(propertyid, ((FailurePoint) getEditableValue()));
        } else if (name.equals("description")) { //$NON-NLS-1$
            pd = new TextAreaPropertyDescriptor(propertyid, attr.getName());
        } else {
            pd = new TextPropertyDescriptor(propertyid, attr.getName());
        }

        if (name.equals("id") || name.equals("name") || name.equals("description")) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            pd.setCategory(Messages.getString("EObjectPropertySource.info")); //$NON-NLS-1$
        } else if (name.indexOf("color") >= 0) { //$NON-NLS-1$
            pd.setCategory(Messages.getString("EObjectPropertySource.appearance")); //$NON-NLS-1$
        } else if (name.indexOf("expression") >= 0) { //$NON-NLS-1$
            pd.setCategory(Messages.getString("EObjectPropertySource.Scenarios")); //$NON-NLS-1$
            // } else if (name.equalsIgnoreCase("kpiValueDatasource") || name.equalsIgnoreCase("kpiReportDatasource")) { //$NON-NLS-1$
            // pd.setCategory(Messages.getString("EObjectPropertySource.Indicator")); //$NON-NLS-1$
        } else if (object.eClass() != propertyid.getEClass()) {
            pd.setCategory(Messages.getString("EObjectPropertySource.reference")); //$NON-NLS-1$
        } else {
            pd.setCategory(Messages.getString("EObjectPropertySource.misc")); //$NON-NLS-1$
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
    protected void intDescriptor(Collection descriptors, EStructuralFeature attr, PropertyID propertyid) {
        TextPropertyDescriptor desc = new TextPropertyDescriptor(propertyid, attr.getName());

        ((PropertyDescriptor) desc).setValidator(new ICellEditorValidator() {
            public String isValid(Object value) {
                int intValue = -1;
                try {
                    intValue = Integer.parseInt((String) value);
                    return null;
                } catch (NumberFormatException exc) {
                    return Messages.getString("EObjectPropertySource.notNumber"); //$NON-NLS-1$
                }
            }
        });
        String name = attr.getName().toLowerCase();
        if (name.equals("x") || name.equals("y") || name.equals("deltax") || name.equals("deltay") || name.equals("height") || name.equals("width")) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
            desc.setCategory(Messages.getString("EObjectPropertySource.appearance")); //$NON-NLS-1$
        } else if (object.eClass() != propertyid.getEClass()) {
            desc.setCategory(Messages.getString("EObjectPropertySource.reference")); //$NON-NLS-1$
        } else {
            desc.setCategory(Messages.getString("EObjectPropertySource.misc")); //$NON-NLS-1$
        }

        descriptors.add(desc);
    }

    /**
     * int property descriptor
     * 
     * @param descriptors
     * @param attr
     * @param propertyid
     */
    protected void doubleDescriptor(Collection descriptors, EStructuralFeature attr, PropertyID propertyid) {
        TextPropertyDescriptor desc = new TextPropertyDescriptor(propertyid, attr.getName());

        ((PropertyDescriptor) desc).setValidator(new ICellEditorValidator() {
            public String isValid(Object value) {
                double doubleValue = -1;
                try {
                    doubleValue = Double.parseDouble(value.toString());
                    return null;
                } catch (NumberFormatException exc) {
                    return Messages.getString("EObjectPropertySource.notValidNumber"); //$NON-NLS-1$
                }
            }
        });

        desc.setCategory(Messages.getString("EObjectPropertySource.misc")); //$NON-NLS-1$

        descriptors.add(desc);
    }

    /**
     * Given the property id, return the contained value
     */
    public Object getPropertyValue(Object id) {

        // int propertyid = Integer.parseInt((String) id);
        // EStructuralFeature feature =
        // object.eClass().getEStructuralFeature(propertyid);
        PropertyID propertyid = (PropertyID) id;
        EStructuralFeature feature = propertyid.getFeature();

        Object result = getFeature(propertyid, feature);

        result = returnPropertyValue(feature, result);

        return result != null ? result : ""; //$NON-NLS-1$
    }

    /**
     * 
     * @param propertyid
     * @param feature
     * @return a feature
     */
    protected Object getFeature(PropertyID propertyid, EStructuralFeature feature) {
        return object.eGet(feature);

    }

    /**
     * @param feature
     * @param result
     * @return value
     */
    protected Object returnPropertyValue(EStructuralFeature feature, Object result) {
        if (result instanceof Integer) {
            result = ((Integer) result).toString();
        } else if (result instanceof Double) {
            result = ((Double) result).toString();
        } else if (result instanceof Boolean) {
            // result = ((Boolean) result).booleanValue() ? new Integer(1) : new
            // Integer(0);
            // result = result;
        } else if (feature.getName().toLowerCase().indexOf("color") >= 0) { //$NON-NLS-1$
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
        PropertyID propertyid = (PropertyID) id;
        EStructuralFeature feature = propertyid.getFeature();

        if (feature.getName().toLowerCase().indexOf("color") >= 0) { //$NON-NLS-1$
            return getPropertyValue(id) != null;
        }

        return false;
    }

    /**
     * Reset colors to their default value (null).
     * 
     * Same added for parent ComponentRef.
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#resetPropertyValue(java.lang.Object)
     */
    public void resetPropertyValue(Object id) {
        PropertyID propertyid = (PropertyID) id;
        EStructuralFeature feature = propertyid.getFeature();

        if (feature instanceof EReference)
            object.eSet(feature, null);
    }

    /**
     * The user has changed a property in the interface; change the model to reflect the user's action.
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
     */
    public void setPropertyValue(Object id, Object value) {
        // int propertyid = Integer.parseInt((String) id);
        // EStructuralFeature feature =
        // object.eClass().getEStructuralFeature(propertyid);
        // EStructuralFeature feature = (EStructuralFeature) id;
        PropertyID propertyid = (PropertyID) id;
        EStructuralFeature feature = propertyid.getFeature();

        Object result = getPropertyValue(id);

        if (feature.getEType().getInstanceClass() == int.class) {
            result = new Integer(Integer.parseInt((String) value));
        } else if (feature.getEType().getInstanceClass() == double.class) {
            result = new Double(Double.parseDouble(value.toString()));
        } else if (feature.getEType().getInstanceClass() == boolean.class) {
            result = value;
        } else if (result instanceof RGB) {
            result = StringConverter.asString((RGB) value);
        } else
            result = value;

        if (feature.getName().equals("name") && result instanceof String)
            result = result.toString().trim();
        setReferencedObject(propertyid, feature, result);
    }

    /**
     * @param propertyid
     * @param feature
     * @param result
     */
    protected void setReferencedObject(PropertyID propertyid, EStructuralFeature feature, Object result) {
        // if this attribute concerns a referenced object.
        if (propertyid.getEClass() == object.eClass())
            object.eSet(feature, result);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource2#isPropertyResettable(java.lang.Object)
     */
    public boolean isPropertyResettable(Object id) {
        PropertyID propertyid = (PropertyID) id;
        EStructuralFeature feature = propertyid.getFeature();

        return feature.getName().toLowerCase().indexOf("color") >= 0; //$NON-NLS-1$

    }

}