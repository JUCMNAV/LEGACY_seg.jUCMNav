package seg.jUCMNav.views;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import org.eclipse.emf.common.util.EList;
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
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import seg.jUCMNav.model.util.ParentFinder;
import ucm.map.ComponentRef;
import ucm.map.MapPackage;
import ucm.map.PathNode;
import urn.URNspec;
import urncore.ComponentElement;
import urncore.UCMmodelElement;

/**
 * Created on 2005-01-30
 * 
 * This class is intended to be a generic property source for all the objects in the application's model.
 * 
 * It currently supports int, String, boolean, Colors, ComponentElement and ComponentRef.
 * 
 * Colors are supported if the following conditions hold: a) The field is a string b) The field name contains the word "color"
 * 
 * We support a basic reset mechanism to put back colors to their default value (null) because the ColorPropertyDescriptor does not have a "transparent" RGB
 * value.
 * 
 * We currently do not support or nested properties.
 * 
 * This class is special cased for ComponentRef's so that we can replace our id/name/description with that of the ComponentElement and add the
 * ComponentElement's properties to ours.
 * 
 * ComponentElements are listed as a dropdown of all ComponentElements in the model.
 * 
 * ComponentRefs are listed as a dropdown of all possible parents.
 * 
 * @author ddean, jkealey
 *  
 */
public class EObjectPropertySource implements IPropertySource {
    private EObject object;
    // if this is a reference to a component, we want it.
    private ComponentElement comp = null;
    private MapPackage ucmPackage;

    /**
     * Contruct an EObjectPropertySource from an EObject.
     * 
     * @param obj
     */
    public EObjectPropertySource(EObject obj) {
        this.object = obj;

        if ((object instanceof ComponentRef) && ((ComponentRef) object).getCompDef() != null) {
            comp = ((ComponentRef) object).getCompDef();
        }

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

            if (comp != null && (attr.getName().equals("name") || attr.getName().equals("id") || attr.getName().equals("description"))) {
                // replace with that of ComponentRef with that of ComponentElement
                continue;
            }

            addPropertyToDescriptor(descriptors, attr, object.eClass());
        }

        // we are referencing another object; show its properties here.
        if (comp != null)
            it = comp.eClass().getEAllAttributes().iterator();

        // add the new properties
        while (it.hasNext()) {
            EAttribute attr = (EAttribute) it.next();
            addPropertyToDescriptor(descriptors, attr, comp.eClass());
        }

        return (IPropertyDescriptor[]) descriptors.toArray(new IPropertyDescriptor[] {});
    }

    /**
     * This function will add an attribute to the current descriptor. Currently, it handles a limited number of types.
     * 
     * @param descriptors
     * @param attr
     * @param type
     */
    public void addPropertyToDescriptor(Collection descriptors, EStructuralFeature attr, EClass c) {

        EClassifier type;
        if (attr instanceof EAttribute)
            type = ((EAttribute) attr).getEAttributeType();
        else
            // if (attr instanceof EReference)
            type = ((EReference) attr).getEReferenceType(); // ok to crash if not EReference.

        String propertyname = attr.getName();

        // we are changing the passed property parameter because our feature id depends on the Eclass, which it does not contain.
        //String propertyid = Integer.toString(attr.getFeatureID());
        Object[] propertyid = { c, attr };

        if (attr instanceof EAttribute && ((EAttribute) attr).isID()) {
            // shouldn't be editable
            descriptors.add(new PropertyDescriptor(propertyid, propertyname));
        } else if (type.getInstanceClass() == String.class) {
            if (attr.getName().toLowerCase().indexOf("color") >= 0)
                descriptors.add(new ColorPropertyDescriptor(propertyid, propertyname));
            else
                descriptors.add(new TextPropertyDescriptor(propertyid, propertyname));
        } else if (type.getInstanceClass() == boolean.class) {
            // this class doesn't exist. Etienne had recreated it, but it relies on a bogus class in the framework.
            //descriptors.add(new CheckboxPropertyDescriptor(Integer.toString(attr.getFeatureID()), attr.getName()));
            String[] values = { "false", "true" };
            descriptors.add(new ComboBoxPropertyDescriptor(propertyid, propertyname, values));
        } else if (type.getInstanceClass() == int.class) {
            TextPropertyDescriptor desc = new TextPropertyDescriptor(propertyid, propertyname);

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
        } else if (type.getInstanceClass() == ComponentElement.class) {
            URNspec urn = (URNspec) ((ComponentRef) getEditableValue()).eContainer().eContainer().eContainer();
            EList list = urn.getUrndef().getComponents();
            String[] values = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {

                values[i] = ((ComponentElement) list.get(i)).getName();
                if (values[i] == null)
                    values[i] = "[unnamed]";
            }

            descriptors.add(new ComboBoxPropertyDescriptor(propertyid, "Component Definition", values));
        } else if (type.getInstanceClass() == ComponentRef.class && (getEditableValue() instanceof PathNode || getEditableValue() instanceof ComponentRef) && attr.getUpperBound()==1){
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
    }

    /**
     * Given the property id, return the contained value
     */
    public Object getPropertyValue(Object id) {

        //int propertyid = Integer.parseInt((String) id);
        //EStructuralFeature feature = object.eClass().getEStructuralFeature(propertyid);
        Object[] o = (Object[]) id;
        EStructuralFeature feature = (EStructuralFeature) o[1];

        Object result = null;

        // if this attribute comes from the referenced object
        if ((EClass) o[0] != object.eClass())
            result = comp.eGet(feature);
        else
            result = object.eGet(feature);

        if (result instanceof Integer) {
            result = ((Integer) result).toString();
        } else if (result instanceof Boolean) {
            result = ((Boolean) result).booleanValue() ? new Integer(1) : new Integer(0);
        } else if (feature.getName().toLowerCase().indexOf("color") >= 0) {
            if (result == null || ((String) result).length() == 0)
                result = new RGB(0, 0, 0);
            else
                result = StringConverter.asRGB((String) result);
        } else if (result instanceof ComponentElement) {
            /*
             * if (((ComponentElement) result).getId() != null) result = new Integer(((ComponentElement) result).getId()); else result = new Integer(0);
             */
            URNspec urn = (URNspec) ((ComponentRef) getEditableValue()).eContainer().eContainer().eContainer();
            EList list = urn.getUrndef().getComponents();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(((ComponentRef) getEditableValue()).getCompDef()))
                    result = new Integer(i);
            }

        } else if (feature instanceof EReference && ((EReference) feature).getEReferenceType().getInstanceClass() == ComponentRef.class && (getEditableValue() instanceof PathNode || getEditableValue() instanceof ComponentRef) ) {
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

        }

        return result != null ? result : "";
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
        } else if (feature instanceof EReference && ((EReference) feature).getEReferenceType().getInstanceClass() == ComponentRef.class && (getEditableValue() instanceof PathNode || getEditableValue() instanceof ComponentRef) ) {
            return ((Integer)getPropertyValue(id)).intValue()>0;
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

        if (feature.getName().toLowerCase().indexOf("color") >= 0 || (feature instanceof EReference && ((EReference) feature).getEReferenceType().getInstanceClass() == ComponentRef.class && (getEditableValue() instanceof PathNode || getEditableValue() instanceof ComponentRef) )) {
            if ((EClass) o[0] != object.eClass())
                comp.eSet(feature, null);
            else
                object.eSet(feature, null);
        }

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
            result = new Boolean(((Integer) value).intValue() == 1);
        } else if (result instanceof RGB) {
            result = StringConverter.asString((RGB) value);
        } else if (feature.getEType().getInstanceClass() == ComponentElement.class) {
            URNspec urn = (URNspec) ((ComponentRef) getEditableValue()).eContainer().eContainer().eContainer();
            EList list = urn.getUrndef().getComponents();
            result = list.get(((Integer) value).intValue());
        } else if (feature instanceof EReference && ((EReference) feature).getEReferenceType().getInstanceClass() == ComponentRef.class && (getEditableValue() instanceof PathNode || getEditableValue() instanceof ComponentRef) ) {
            Vector list = ParentFinder.getPossibleParents((UCMmodelElement) getEditableValue());
            if (((Integer) value).equals(new Integer(0)))
                result = null;
            else
                result = list.get(((Integer) value).intValue() - 1);
        } else
            result = value;

        // if this attribute concerns a referenced object.
        if ((EClass) o[0] != object.eClass())
            comp.eSet(feature, result);
        else
            object.eSet(feature, result);
    }
}