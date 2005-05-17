package seg.jUCMNav.views.property;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;

import seg.jUCMNav.model.util.URNNamingHelper;
import ucm.map.RespRef;
import urn.URNspec;
import urncore.Responsibility;

/**
 * This class is special cased for RespRefs so that we can replace our id/name/description with that of the Responsibility and add the Responsibilities's
 * properties to ours.
 * 
 * ComponentElements are listed as a dropdown of all ComponentElements in the model.
 * 
 * The colors shown are those of the linked component definition.
 * 
 * @author jkealey, etremblay
 *  
 */
public class ResponsibilityPropertySource extends UCMElementPropertySource {

    //	 if this is a reference to a component, we want it.
    private Responsibility resp = null;
    int i = 0;

    /**
     * @param obj
     */
    public ResponsibilityPropertySource(EObject obj) {
        super(obj);

        if ((object instanceof RespRef) && ((RespRef) object).getRespDef() != null) {
            resp = ((RespRef) object).getRespDef();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.views.EObjectPropertySource#isAddable(org.eclipse.emf.ecore.EStructuralFeature)
     */
    protected boolean canAddFeature(EStructuralFeature attr) {
        if (resp != null && (attr.getName().equals("name") || attr.getName().equals("id") || attr.getName().equals("description"))) {
            // replace with that of RespRef with that of Responsibility
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

        if (resp != null) {
            // we are referencing another object; show its properties here.
            it = resp.eClass().getEAllAttributes().iterator();

            // add the new properties
            while (it.hasNext()) {
                EAttribute attr = (EAttribute) it.next();
                addPropertyToDescriptor(descriptors, attr, resp.eClass());
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

        if (type.getInstanceClass() == Responsibility.class) {
            responsibilityDescriptor(descriptors, attr, propertyid);
        } else
            super.addPropertyToDescriptor(descriptors, attr, c);
    }

    /**
     * @param descriptors
     * @param propertyname
     * @param propertyid
     */
    private void responsibilityDescriptor(Collection descriptors, EStructuralFeature attr, Object[] propertyid) {
        URNspec urn = (URNspec) ((RespRef) getEditableValue()).eContainer().eContainer().eContainer().eContainer();
        EList list = urn.getUrndef().getResponsibilities();
        String[] values = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {

            values[i] = ((Responsibility) list.get(i)).getName();
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
        if (result instanceof Responsibility) {
            URNspec urn = (URNspec) ((RespRef) getEditableValue()).eContainer().eContainer().eContainer().eContainer();
            EList list = urn.getUrndef().getResponsibilities();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(((RespRef) getEditableValue()).getRespDef()))
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
            result = resp.eGet(feature);
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
        Object[] o = (Object[]) id;
        EStructuralFeature feature = (EStructuralFeature) o[1];

        Object result = getPropertyValue(id);
        URNspec urn = (URNspec) ((RespRef) getEditableValue()).eContainer().eContainer().eContainer().eContainer();

        if (feature.getEType().getInstanceClass() == Responsibility.class) {

            EList list = urn.getUrndef().getResponsibilities();
            result = list.get(((Integer) value).intValue());
            setReferencedObject(o, feature, result);
            resp = ((RespRef) object).getRespDef();
        } else if (feature.getName() == "name") {
            if (value.toString().length() > 0 && !URNNamingHelper.doesResponsibilityNameExists(urn, (String) value)) {
                super.setPropertyValue(id, value);

            } else {
                if (++i % 2 == 1) {
                    String message;
                    if (value.toString().length() == 0)
                        message = "Invalid responsibility name.";
                    else
                        message = "Responsibility name already in use.";
                    MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Error", message);
                }
            }

        } else {
            super.setPropertyValue(id, value);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.views.EObjectPropertySource#setReferencedObject(java.lang.Object[], org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object)
     */
    protected void setReferencedObject(Object[] o, EStructuralFeature feature, Object result) {
        if ((EClass) o[0] != object.eClass())
            resp.eSet(feature, result);
        else
            object.eSet(feature, result);
    }
}