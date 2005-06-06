package seg.jUCMNav.views.property;

import java.util.Collection;
import java.util.Vector;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.util.ParentFinder;
import ucm.map.ComponentRef;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.Timer;
import ucm.performance.ArrivalProcess;
import ucm.performance.Workload;
import urn.URNspec;
import urncore.Condition;
import urncore.Label;
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
        PropertyID propertyid = new PropertyID(c, attr);

        if (type.getInstanceClass() == ComponentRef.class && (getEditableValue() instanceof PathNode || getEditableValue() instanceof ComponentRef)
                && attr.getUpperBound() == 1) {
            componentRefDescriptor(descriptors, attr, propertyid);
        } else if (type.getInstanceClass() == Workload.class) {
            workloadDescriptor(descriptors, propertyid);
        } else if (type.getInstanceClass() == Condition.class && !(getEditableValue() instanceof Label)) {
            if (getEditableValue() instanceof NodeConnection) {
                NodeConnection nc = (NodeConnection) getEditableValue();
                // only on node connections that follow an or fork or for timeout paths
                if (nc.getSource() instanceof OrFork || (nc.getSource() instanceof Timer && ((Timer) nc.getSource()).getSucc().indexOf(nc) == 1)) {
                    conditionDescriptor(descriptors, propertyid);
                }
            } else
                conditionDescriptor(descriptors, propertyid);
        } else if (type.getInstanceClass() == ArrivalProcess.class) {
            String[] values = new String[ArrivalProcess.VALUES.size()];
            for (int i = 0; i < ArrivalProcess.VALUES.size(); i++)
                values[i] = ((ArrivalProcess) (ArrivalProcess.VALUES.get(i))).getName();
            descriptors.add(new ComboBoxPropertyDescriptor(propertyid, "Arrival process", values));
        } else {
            super.addPropertyToDescriptor(descriptors, attr, c);
        }
    }

    /**
     * @param descriptors
     * @param propertyid
     */
    private void workloadDescriptor(Collection descriptors, PropertyID propertyid) {
        PropertyDescriptor pd = new PropertyDescriptor(propertyid, "Workload");
        pd.setCategory("Performance");
        pd.setLabelProvider(new LabelProvider() {
            public String getText(Object element) {
                return "";
            }
        });

        descriptors.add(pd);
    }

    /**
     * @param descriptors
     * @param propertyid
     */
    private void conditionDescriptor(Collection descriptors, PropertyID propertyid) {
        PropertyDescriptor pd = new PropertyDescriptor(propertyid, "Condition");
        pd.setCategory("Scenario");
        pd.setLabelProvider(new LabelProvider() {
            public String getText(Object element) {
                return "";
            }
        });

        descriptors.add(pd);
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

        } else if (getFeatureType(feature).getInstanceClass() == Workload.class) {
            if (result == null) {
                URNspec urn = ((StartPoint) getEditableValue()).getPathGraph().getMap().getUcmspec().getUrnspec();
                result = (Workload) ModelCreationFactory.getNewObject(urn, Workload.class);
            }
            result = new UCMElementPropertySource((EObject) result);
        } else if (getFeatureType(feature).getInstanceClass() == Condition.class) {
            if (result == null) {
                URNspec urn;
                if (getEditableValue() instanceof NodeConnection)
                    urn = ((NodeConnection) getEditableValue()).getPathGraph().getMap().getUcmspec().getUrnspec();
                else
                    urn = ((PathNode) getEditableValue()).getPathGraph().getMap().getUcmspec().getUrnspec();

                result = (Condition) ModelCreationFactory.getNewObject(urn, Condition.class);
            }
            result = new UCMElementPropertySource((EObject) result);
        } else if (getFeatureType(feature).getInstanceClass() == ArrivalProcess.class) {
            result = new Integer(((Workload) getEditableValue()).getArrivalPattern().getValue());
        } else {
            result = super.returnPropertyValue(feature, result);
        }

        return result;
    }

    public void setPropertyValue(Object id, Object value) {
        PropertyID propertyid = (PropertyID) id;
        EStructuralFeature feature = propertyid.getFeature();

        Object result = getPropertyValue(id);

        if (feature instanceof EReference && ((EReference) feature).getEReferenceType().getInstanceClass() == ComponentRef.class
                && (getEditableValue() instanceof PathNode || getEditableValue() instanceof ComponentRef)) {
            Vector list = ParentFinder.getPossibleParents((UCMmodelElement) getEditableValue());
            if (((Integer) value).equals(new Integer(0)))
                result = null;
            else
                result = list.get(((Integer) value).intValue() - 1);
            setReferencedObject(propertyid, feature, result);
        } else if (getFeatureType(feature).getInstanceClass() == ArrivalProcess.class) {
            setReferencedObject(propertyid, feature, ArrivalProcess.get(((Integer) value).intValue()));
        } else

            super.setPropertyValue(id, value);
    }

    private void componentRefDescriptor(Collection descriptors, EStructuralFeature attr, PropertyID propertyid) {
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
        PropertyID propertyid = (PropertyID) id;
        EStructuralFeature feature = propertyid.getFeature();

        if (feature instanceof EReference && ((EReference) feature).getEReferenceType().getInstanceClass() == ComponentRef.class
                && (getEditableValue() instanceof PathNode || getEditableValue() instanceof ComponentRef)) {
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

        if (feature instanceof EReference && ((EReference) feature).getEReferenceType().getInstanceClass() == ComponentRef.class
                && (getEditableValue() instanceof PathNode || getEditableValue() instanceof ComponentRef)) {
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

        if (feature.getName().toLowerCase().indexOf("color") >= 0
                || (feature instanceof EReference && ((EReference) feature).getEReferenceType().getInstanceClass() == ComponentRef.class && (getEditableValue() instanceof PathNode || getEditableValue() instanceof ComponentRef))) {
            object.eSet(feature, null);
        } else
            super.resetPropertyValue(id);
    }
}