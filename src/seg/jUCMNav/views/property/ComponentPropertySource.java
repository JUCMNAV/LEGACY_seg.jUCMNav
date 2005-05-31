package seg.jUCMNav.views.property;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;

import seg.jUCMNav.model.util.URNNamingHelper;
import ucm.map.ComponentRef;
import ucm.map.PathNode;
import urn.URNspec;
import urncore.ComponentElement;

/**
 * This class is special cased for ComponentRef's so that we can replace our id/name/description with that of the ComponentElement and add the
 * ComponentElement's properties to ours.
 * 
 * ComponentElements are listed as a dropdown of all ComponentElements in the model.
 * 
 * The colors shown are those of the linked component definition.
 * 
 * @author jkealey, etremblay
 *  
 */
public class ComponentPropertySource extends UCMElementPropertySource {

    //	 if this is a reference to a component, we want it.
    private ComponentElement comp = null;
    int i;

    /**
     * @param obj
     */
    public ComponentPropertySource(EObject obj) {
        super(obj);
        if ((object instanceof ComponentRef) && ((ComponentRef) object).getCompDef() != null) {
            comp = ((ComponentRef) object).getCompDef();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.views.EObjectPropertySource#isAddable(org.eclipse.emf.ecore.EStructuralFeature)
     */
    protected boolean canAddFeature(EStructuralFeature attr) {
        if (comp != null && (attr.getName().equals("name") || attr.getName().equals("id") || attr.getName().equals("description"))) {
            // replace with that of ComponentRef with that of ComponentElement
            return false;
        } else
            return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.views.EObjectPropertySource#addSpecificProperties()
     */
    protected Vector addSpecificProperties() {
        i = 0;
        Iterator it;
        EClass cls = object.eClass();
        Collection descriptors = new Vector();

        if (comp != null) {
            // we are referencing another object; show its properties here.
            it = comp.eClass().getEAllAttributes().iterator();

            // add the new properties
            while (it.hasNext()) {
                EAttribute attr = (EAttribute) it.next();
                addPropertyToDescriptor(descriptors, attr, comp.eClass());
            }
        }
        return (Vector) descriptors;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.views.EObjectPropertySource#addPropertyToDescriptor(java.util.Collection, org.eclipse.emf.ecore.EStructuralFeature,
     *      org.eclipse.emf.ecore.EClass)
     */
    public void addPropertyToDescriptor(Collection descriptors, EStructuralFeature attr, EClass c) {
        EClassifier type = getFeatureType(attr);

        Object[] propertyid = { c, attr };

        if (type.getInstanceClass() == ComponentElement.class) {
            componentElementDescriptor(descriptors, attr, propertyid);
        } else
            super.addPropertyToDescriptor(descriptors, attr, c);
    }

    /**
     * @param descriptors
     * @param propertyname
     * @param propertyid
     */
    private void componentElementDescriptor(Collection descriptors, EStructuralFeature attr, Object[] propertyid) {
        URNspec urn = (URNspec) ((ComponentRef) getEditableValue()).eContainer().eContainer().eContainer();
        EList list = urn.getUrndef().getComponents();
        String[] values = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {

            values[i] = ((ComponentElement) list.get(i)).getName();
            if (values[i] == null)
                values[i] = "[unnamed]";
        }

        ComboBoxPropertyDescriptor pd = new ComboBoxPropertyDescriptor(propertyid, "Definition", values);
        pd.setCategory("Reference");
        descriptors.add(pd);

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.views.EObjectPropertySource#returnPropertyValue(org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object)
     */
    protected Object returnPropertyValue(EStructuralFeature feature, Object result) {
        if (result instanceof ComponentElement) {
            /*
             * if (((ComponentElement) result).getId() != null) result = new Integer(((ComponentElement) result).getId()); else result = new Integer(0);
             */
            URNspec urn = (URNspec) ((ComponentRef) getEditableValue()).eContainer().eContainer().eContainer();
            EList list = urn.getUrndef().getComponents();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(((ComponentRef) getEditableValue()).getCompDef()))
                    result = new Integer(i);
            }

        } else
            return super.returnPropertyValue(feature, result);

        return result;
    }

    /**
     * @param o
     * @param feature
     * @return
     */
    protected Object getFeature(Object[] o, EStructuralFeature feature) {
        Object result = null;

        // if this attribute comes from the referenced object
        if ((EClass) o[0] != object.eClass())
            result = comp.eGet(feature);
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
        Object[] o = (Object[]) id;
        EStructuralFeature feature = (EStructuralFeature) o[1];

        if (feature.getName().toLowerCase().indexOf("color") >= 0
                || (feature instanceof EReference && ((EReference) feature).getEReferenceType().getInstanceClass() == ComponentRef.class && (getEditableValue() instanceof PathNode || getEditableValue() instanceof ComponentRef))) {
            if ((EClass) o[0] != object.eClass())
                comp.eSet(feature, null);
            else
                object.eSet(feature, null);
        } else
            super.resetPropertyValue(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
     */
    public void setPropertyValue(Object id, Object value) {
        Object[] o = (Object[]) id;
        EStructuralFeature feature = (EStructuralFeature) o[1];

        Object result = getPropertyValue(id);
        URNspec urn = (URNspec) ((ComponentRef) getEditableValue()).eContainer().eContainer().eContainer();
        if (feature.getEType().getInstanceClass() == ComponentElement.class) {

            EList list = urn.getUrndef().getComponents();
            result = list.get(((Integer) value).intValue());
            setReferencedObject(o, feature, result);
            comp = ((ComponentRef) object).getCompDef();
        } else if (feature.getName() == "name") {
            String message = URNNamingHelper.isNameValid(urn, (ComponentRef) object, value.toString());

            if (message.length() == 0) {
                super.setPropertyValue(id, value);

            } else if (++i % 2 == 1) { // because refreshed twice.
                MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Error", message);
            }

        } else
            super.setPropertyValue(id, value);

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.views.EObjectPropertySource#setReferencedObject(java.lang.Object[], org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object)
     */
    protected void setReferencedObject(Object[] o, EStructuralFeature feature, Object result) {
        if ((EClass) o[0] != object.eClass()) {
            comp.eSet(feature, result);
            if (feature.getName().equalsIgnoreCase("fillColor")) {
                comp.setFilled(true);
            }

        } else
            object.eSet(feature, result);
    }

}