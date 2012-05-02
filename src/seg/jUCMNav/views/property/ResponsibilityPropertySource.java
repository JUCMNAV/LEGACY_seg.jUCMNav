package seg.jUCMNav.views.property;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.util.EObjectClassNameComparator;
import seg.jUCMNav.model.util.URNNamingHelper;
import seg.jUCMNav.views.property.descriptors.CheckboxPropertyDescriptor;
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
public class ResponsibilityPropertySource extends URNElementPropertySource {

    // if this is a reference to a component, we want it.
    private Responsibility resp = null;
    private int i = 0;

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

        // If a responsibility as resp bindings, don't show the context property
        //if(resp != null && attr.getName() == "context" && resp.getParentBindings().size() > 0)
        //    return false;
        
        if (resp != null && (attr.getName().equals("name") || attr.getName().equals("id") || attr.getName().equals("description"))) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
        Collection descriptors = new Vector();

        if (resp != null) {
            // we are referencing another object; show its properties here.
            it = resp.eClass().getEAllAttributes().iterator();

            // add the new properties
            while (it.hasNext()) {
                EAttribute attr = (EAttribute) it.next();

                //if(canAddFeature(attr))
                addPropertyToDescriptor(descriptors, attr, resp.eClass());
            }
        }
        return (Vector) descriptors;
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

        if (type.getInstanceClass() == Responsibility.class) {
            responsibilityDescriptor(descriptors, attr, propertyid);
        } else
            super.addPropertyToDescriptor(descriptors, attr, c);
        
        if (getEditableValue() instanceof RespRef && attr.getName().equals("context")) { //$NON-NLS-1$
            Vector v = (Vector) descriptors;
            CheckboxPropertyDescriptor pd = (CheckboxPropertyDescriptor) v.get(v.size() - 1);

            if (getEditableValue()!=null && ((RespRef) getEditableValue()).getRespDef() !=null) { // bug 778 - during batch deletes sometimes had error. refresh only
                for (Iterator iterator = ((RespRef) getEditableValue()).getRespDef().getRespRefs().iterator(); iterator.hasNext();) {
                    RespRef ref = (RespRef) iterator.next();
                    if (ref.getPluginBindings().size() != 0)
                        pd.setReadOnly(true);
                }
            }
        }        
    }

    /**
     * @param descriptors
     * @param attr
     * @param propertyid
     */
    private void responsibilityDescriptor(Collection descriptors, EStructuralFeature attr, PropertyID propertyid) {
        if (((RespRef) getEditableValue()).getDiagram() == null || ((RespRef) getEditableValue()).getDiagram().getUrndefinition() == null)
            return;
        URNspec urn = ((RespRef) getEditableValue()).getDiagram().getUrndefinition().getUrnspec();
        Vector list = new Vector(urn.getUrndef().getResponsibilities());
        Collections.sort(list, new EObjectClassNameComparator());
        String[] values = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {

            values[i] = EObjectClassNameComparator.getSortableElementName((Responsibility) list.get(i));
            if (values[i] == null)
                values[i] = "[unnamed]"; //$NON-NLS-1$
        }

        ComboBoxPropertyDescriptor pd = new ComboBoxPropertyDescriptor(propertyid, "definition", values); //$NON-NLS-1$
        pd.setCategory(Messages.getString("EObjectPropertySource.reference")); //$NON-NLS-1$
        descriptors.add(pd);

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.views.EObjectPropertySource#returnPropertyValue(org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object)
     */
    protected Object returnPropertyValue(EStructuralFeature feature, Object result) {
        if (result instanceof Responsibility) {
            URNspec urn = ((RespRef) getEditableValue()).getDiagram().getUrndefinition().getUrnspec();
            Vector list = new Vector(urn.getUrndef().getResponsibilities());
            Collections.sort(list, new EObjectClassNameComparator());
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(((RespRef) getEditableValue()).getRespDef()))
                    result = new Integer(i);
            }

        } else
            return super.returnPropertyValue(feature, result);

        return result;
    }

    /**
     * @param propertyid
     * @param feature
     * @return the feature
     */
    protected Object getFeature(PropertyID propertyid, EStructuralFeature feature) {
        Object result = null;

        // if this attribute comes from the referenced object
        if (propertyid.getEClass() != object.eClass())
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
        PropertyID propertyid = (PropertyID) id;
        EStructuralFeature feature = propertyid.getFeature();

        Object result = getPropertyValue(id);
        URNspec urn = ((RespRef) getEditableValue()).getDiagram().getUrndefinition().getUrnspec();

        if (feature.getEType().getInstanceClass() == Responsibility.class) {

            Vector list = new Vector(urn.getUrndef().getResponsibilities());
            Collections.sort(list, new EObjectClassNameComparator());
            result = list.get(((Integer) value).intValue());
            setReferencedObject(propertyid, feature, result);
            resp = ((RespRef) object).getRespDef();
        } else if (feature.getName() == "name") { //$NON-NLS-1$
            value = value.toString().trim();
            String message = URNNamingHelper.isNameValid(urn, (RespRef) object, value.toString());

            if (message.length() == 0) {
                super.setPropertyValue(id, value);

            } else if (++i % 2 == 1) { // because refreshed twice.
                MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Error", message); //$NON-NLS-1$
            }

        } else {
            super.setPropertyValue(id, value);
        }

    }

    protected void setReferencedObject(PropertyID propertyid, EStructuralFeature feature, Object result) {
        if (propertyid.getEClass() != object.eClass())
            resp.eSet(feature, result);
        else
            object.eSet(feature, result);
    }
}