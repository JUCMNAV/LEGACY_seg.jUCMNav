package seg.jUCMNav.views.property;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import org.eclipse.emf.common.util.AbstractEnumerator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.util.EObjectClassNameComparator;
import seg.jUCMNav.model.util.ParentFinder;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.WaitingPlace;
import ucm.performance.Workload;
import urn.URNspec;
import urncore.Condition;
import urncore.IURNContainerRef;
import urncore.IURNNode;
import urncore.Label;
import urncore.URNmodelElement;

/**
 * URNElementPropertySource adds code to replace the parent attribute in ucm model elements to represent the ComponentRef to which this ucm element is bound to.
 * Both ComponentRefs and PathNodes are bound to the parent.
 * 
 * @author jkealey, etremblay
 *  
 */
public class URNElementPropertySource extends EObjectPropertySource {

    /**
     * @param obj
     */
    public URNElementPropertySource(EObject obj) {
        super(obj);
    }

    public void addPropertyToDescriptor(Collection descriptors, EStructuralFeature attr, EClass c) {
        // Get type for the structural feature
        EClassifier type = getFeatureType(attr);
        PropertyID propertyid = new PropertyID(c, attr);

        if (type.getInstanceClass() == IURNContainerRef.class && (getEditableValue() instanceof IURNNode || getEditableValue() instanceof IURNContainerRef)
                && attr.getUpperBound() == 1) {
            componentRefDescriptor(descriptors, attr, propertyid);
        } else if (type.getInstanceClass() == Workload.class) {
            workloadDescriptor(descriptors, propertyid);
        } else if (type.getInstanceClass() == Condition.class && !(getEditableValue() instanceof Label)) {
            if (getEditableValue() instanceof NodeConnection) {
                NodeConnection nc = (NodeConnection) getEditableValue();
                // only on node connections that follow an or fork or a waitingplace/timer, but not on the timeout path
                if (nc.getSource() instanceof OrFork || (nc.getSource() instanceof WaitingPlace && ((WaitingPlace) nc.getSource()).getSucc().indexOf(nc) == 0)) {
                    conditionDescriptor(descriptors, propertyid);
                }
            } else
                conditionDescriptor(descriptors, propertyid);
        } else if (type.getInstanceClass().getSuperclass() == AbstractEnumerator.class) {
            // these are enums created by EMF
            enumerationDescriptor(descriptors, propertyid);
        } else {
            super.addPropertyToDescriptor(descriptors, attr, c);
        }
    }

    /**
     * @param descriptors
     * @param propertyid
     */
    private void enumerationDescriptor(Collection descriptors, PropertyID propertyid) {
        EClassifier type = getFeatureType(propertyid.getFeature());
        Class enumer = type.getInstanceClass();
        String[] values = getEnumerationValues(enumer);
        //String name = enumer.getName().substring(enumer.getName().lastIndexOf('.') + 1);
        String name = propertyid.getFeature().getName();
        ComboBoxPropertyDescriptor pd = new ComboBoxPropertyDescriptor(propertyid, name, values);
        pd.setCategory(Messages.getString("EObjectPropertySource.misc")); //$NON-NLS-1$
        descriptors.add(pd);

    }

    /**
     * @param enumer
     *            A class having a VALUES field returning a List of AbstractEnumerators.
     * @return A sorted list of strings corresponding to the names of the values.
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    private String[] getEnumerationValues(Class enumer) {
        try {
            List VALUES;

            VALUES = (List) enumer.getField("VALUES").get(null); //$NON-NLS-1$
            String[] values = new String[VALUES.size()];
            for (int i = 0; i < VALUES.size(); i++)
                values[i] = ((AbstractEnumerator) (VALUES.get(i))).getName();

            return values;

        } catch (Exception e) {
            e.printStackTrace();
            return new String[] {};
        }
    }

    /**
     * @param descriptors
     * @param propertyid
     */
    private void workloadDescriptor(Collection descriptors, PropertyID propertyid) {
        PropertyDescriptor pd = new PropertyDescriptor(propertyid, Messages.getString("UCMElementPropertySource.workload")); //$NON-NLS-1$
        pd.setCategory(Messages.getString("UCMElementPropertySource.performance")); //$NON-NLS-1$
        pd.setLabelProvider(new LabelProvider() {
            public String getText(Object element) {
                return ""; //$NON-NLS-1$
            }
        });

        descriptors.add(pd);
    }

    /**
     * @param descriptors
     * @param propertyid
     */
    private void conditionDescriptor(Collection descriptors, PropertyID propertyid) {
        PropertyDescriptor pd;

        pd = new PropertyDescriptor(propertyid, propertyid.getFeature().getName());

        pd.setCategory(Messages.getString("UCMElementPropertySource.scenario")); //$NON-NLS-1$
        pd.setLabelProvider(new LabelProvider() {
            public String getText(Object element) {
                return ""; //$NON-NLS-1$
            }
        });

        descriptors.add(pd);
    }

    protected Object returnPropertyValue(EStructuralFeature feature, Object result) {
        if (feature instanceof EReference && ((EReference) feature).getEReferenceType().getInstanceClass() == IURNContainerRef.class
                && (getEditableValue() instanceof IURNNode || getEditableValue() instanceof IURNContainerRef)) {
            //&& feature.getName().toLowerCase().indexOf("parent") >= 0) {
            Vector list = ParentFinder.getPossibleParents((URNmodelElement) getEditableValue());
            Collections.sort(list, new EObjectClassNameComparator());

            for (int i = 0; i < list.size(); i++) {
                IURNContainerRef parent;
                if (getEditableValue() instanceof IURNContainerRef)
                    parent = (IURNContainerRef)((IURNContainerRef) getEditableValue()).getParent();
                else
                    parent = (IURNContainerRef)((IURNNode) getEditableValue()).getContRef();
                if (list.get(i).equals(parent))
                    result = new Integer(i + 1);
            }
            if (result == null)
                result = new Integer(0);

        } else if (getFeatureType(feature).getInstanceClass() == Workload.class) {
            if (result == null) {
                URNspec urn = ((StartPoint) getEditableValue()).getDiagram().getUrndefinition().getUrnspec();
                result = (Workload) ModelCreationFactory.getNewObject(urn, Workload.class);
            }
            result = new URNElementPropertySource((EObject) result);
        } else if (getFeatureType(feature).getInstanceClass() == Condition.class) {
            if (result == null) {
                URNspec urn;
                if (getEditableValue() instanceof NodeConnection)
                    urn = ((NodeConnection) getEditableValue()).getDiagram().getUrndefinition().getUrnspec();
                else
                    urn = ((PathNode) getEditableValue()).getDiagram().getUrndefinition().getUrnspec();

                result = (Condition) ModelCreationFactory.getNewObject(urn, Condition.class);
            }
            result = new URNElementPropertySource((EObject) result);
        } else if (result instanceof AbstractEnumerator) {
            // if this is an EMF enumeration
            int i = getEnumerationIndex((AbstractEnumerator) result);
            result = new Integer(i);
        } else {
            result = super.returnPropertyValue(feature, result);
        }

        return result;
    }

    /**
     * @param result
     * @return the index of an AbstractEnumerator in its sorted list.
     */
    private int getEnumerationIndex(AbstractEnumerator result) {
        String[] values = getEnumerationValues(result.getClass());
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(((AbstractEnumerator) result).getName()))
                return i;
        }
        return -1;
    }

    public void setPropertyValue(Object id, Object value) {
        PropertyID propertyid = (PropertyID) id;
        EStructuralFeature feature = propertyid.getFeature();

        Object result = getPropertyValue(id);

        if (feature instanceof EReference && ((EReference) feature).getEReferenceType().getInstanceClass() == IURNContainerRef.class
                && (getEditableValue() instanceof IURNNode || getEditableValue() instanceof IURNContainerRef)) {
            Vector list = ParentFinder.getPossibleParents((URNmodelElement) getEditableValue());
            Collections.sort(list, new EObjectClassNameComparator());
            if (((Integer) value).equals(new Integer(0)))
                result = null;
            else
                result = list.get(((Integer) value).intValue() - 1);
            setReferencedObject(propertyid, feature, result);
        } else if (getFeatureType(feature).getInstanceClass().getSuperclass() == AbstractEnumerator.class) {
            // if this is an EMF enumeration
            Class enumer = getFeatureType(feature).getInstanceClass();
            try {

                int selectedIndex = ((Integer) value).intValue();
                String selectedString = getEnumerationValues(enumer)[selectedIndex];
                result = enumer.getMethod("get", new Class[] { String.class }).invoke(getEditableValue(), new Object[] { selectedString }); //$NON-NLS-1$
            } catch (Exception e) {
                e.printStackTrace();
            }

            setReferencedObject(propertyid, feature, result);
        } else

            super.setPropertyValue(id, value);
    }

    private void componentRefDescriptor(Collection descriptors, EStructuralFeature attr, PropertyID propertyid) {
        Vector list = ParentFinder.getPossibleParents((URNmodelElement) getEditableValue());
        Collections.sort(list, new EObjectClassNameComparator());
        String[] values = new String[list.size() + 1];
        values[0] = Messages.getString("UCMElementPropertySource.unbound"); //$NON-NLS-1$
        for (int i = 1; i < list.size() + 1; i++) {
            values[i] = EObjectClassNameComparator.getSortableElementName((IURNContainerRef) list.get(i - 1));
            if (values[i] == null)
                values[i] = Messages.getString("UCMElementPropertySource.unnamed"); //$NON-NLS-1$
        }

        ComboBoxPropertyDescriptor pd = new ComboBoxPropertyDescriptor(propertyid, Messages.getString("UCMElementPropertySource.parent"), values); //$NON-NLS-1$
        pd.setCategory(Messages.getString("EObjectPropertySource.info")); //$NON-NLS-1$
        descriptors.add(pd);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource#isPropertySet(java.lang.Object)
     */
    public boolean isPropertySet(Object id) {
        PropertyID propertyid = (PropertyID) id;
        EStructuralFeature feature = propertyid.getFeature();

        if (feature instanceof EReference && ((EReference) feature).getEReferenceType().getInstanceClass() == IURNContainerRef.class
                && (getEditableValue() instanceof IURNNode || getEditableValue() instanceof IURNContainerRef)) {
            return ((Integer) getPropertyValue(id)).intValue() > 0;
        } else
            return super.isPropertySet(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.views.properties.IPropertySource2#isPropertyResettable(java.lang.Object)
     */
    public boolean isPropertyResettable(Object id) {
        PropertyID propertyid = (PropertyID) id;
        EStructuralFeature feature = propertyid.getFeature();

        if (feature instanceof EReference && ((EReference) feature).getEReferenceType().getInstanceClass() == IURNContainerRef.class
                && (getEditableValue() instanceof IURNNode || getEditableValue() instanceof IURNContainerRef)) {
            return true;
        } else
            return super.isPropertyResettable(id);
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
                || (feature instanceof EReference && ((EReference) feature).getEReferenceType().getInstanceClass() == IURNContainerRef.class && (getEditableValue() instanceof IURNNode || getEditableValue() instanceof IURNContainerRef))) {
            object.eSet(feature, null);
        } else
            super.resetPropertyValue(id);
    }
}