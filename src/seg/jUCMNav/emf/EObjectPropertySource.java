package seg.jUCMNav.emf;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import ucm.map.MapPackage;

/**
 * Created on 2005-01-30
 * 
 * This class is intended to be a generic property source for all the objects in the application's model. It currently only supports String and boolean types,
 * and doesn't support reset or nested properties. It uses information in the EMF EAttribute and EDataType classes to get the id, display name, and type
 * information.
 * 
 * @author ddean
 *  
 */
public class EObjectPropertySource implements IPropertySource {
    private EObject object;
    private MapPackage ucmPackage;

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

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#getEditableValue()
     */
    public Object getEditableValue() {
        return object;
    }

    /**
     * This function will loop trough all the property of this EObject and find the one that could be represented by our property descriptors. We have to create
     * a property descriptor for each property we want to be able to edit in our property page.
     * 
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyDescriptors()
     */
    public IPropertyDescriptor[] getPropertyDescriptors() {
        Iterator it;
        EClass cls = object.eClass();
        Collection descriptors = new Vector();

        it = cls.getEAllAttributes().iterator();
        while (it.hasNext()) {
            EAttribute attr = (EAttribute) it.next();

            EDataType type = attr.getEAttributeType();
            if (attr.isID()) {
                // shouldn't be editable
                descriptors.add(new PropertyDescriptor(Integer.toString(attr.getFeatureID()), attr.getName()));
            } else if (type.getInstanceClass() == String.class) {
                descriptors.add(new TextPropertyDescriptor(Integer.toString(attr.getFeatureID()), attr.getName()));
            } else if (type.getInstanceClass() == boolean.class) {
                // this class doesn't exist.
                //descriptors.add(new CheckboxPropertyDescriptor(Integer.toString(attr.getFeatureID()), attr.getName()));
                String[] values = { "false", "true" };
                descriptors.add(new ComboBoxPropertyDescriptor(Integer.toString(attr.getFeatureID()), attr.getName(), values));
            } else if (type.getInstanceClass() == int.class) {
                TextPropertyDescriptor desc = new TextPropertyDescriptor(Integer.toString(attr.getFeatureID()), attr.getName());

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
                descriptors.add(desc);
            }
        }

        return (IPropertyDescriptor[]) descriptors.toArray(new IPropertyDescriptor[] {});
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
     */
    public Object getPropertyValue(Object id) {
        EStructuralFeature feature = object.eClass().getEStructuralFeature(Integer.parseInt((String) id));

        Object result = object.eGet(feature);
        if (result instanceof Integer) {
            result = ((Integer) result).toString();
        } else if (result instanceof Boolean) {
            result = ((Boolean)result).booleanValue()?new Integer(1):new Integer(0);
        }
        return result != null ? result : "";
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#isPropertySet(java.lang.Object)
     */
    public boolean isPropertySet(Object id) {
        // TODO Implement method
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.views.properties.IPropertySouce#resetPropertyValue(java.lang.Object)
     */
    public void resetPropertyValue(Object id) {
        // TODO Implement Method
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
     */
    public void setPropertyValue(Object id, Object value) {
        EStructuralFeature feature = object.eClass().getEStructuralFeature(Integer.parseInt((String) id));

        Object result = object.eGet(feature);
        if (result instanceof Integer) {
            result = new Integer(Integer.parseInt((String) value));
            object.eSet(feature, result);
        } else if (result instanceof Boolean) {
            result = new Boolean(((Integer)value).intValue()==1);
            object.eSet(feature, result);
        } else

            object.eSet(feature, value);
    }

}