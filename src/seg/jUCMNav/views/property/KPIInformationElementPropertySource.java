/**
 * 
 */
package seg.jUCMNav.views.property;

import grl.EvaluationStrategy;
import grl.kpimodel.KPIInformationConfig;
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KPIInformationElementRef;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.create.CreateAllKPIModelLinkRefCommand;
import seg.jUCMNav.model.commands.delete.DeleteAllKPIModelLinkRefCommand;
import seg.jUCMNav.model.util.EObjectClassNameComparator;
import seg.jUCMNav.model.util.URNNamingHelper;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.property.descriptors.CustomTextPropertyDescriptor;
import urn.URNspec;
import urncore.IURNNode;
import urncore.URNmodelElement;

/**
 * This class is a special case of the properties sheet for KPIInformationElementRef
 * 
 * It should include the KPIInformationElement property associate with this reference
 * 
 * @author pchen
 * 
 */
public class KPIInformationElementPropertySource extends URNElementPropertySource {

    private KPIInformationElement def = null;
    private boolean strategyView;

    private int i;

    /**
     * Constructor that initalize the KPIInformationElement definition
     * 
     * @param obj
     */
    public KPIInformationElementPropertySource(EObject obj) {
        super(obj);
        if ((obj instanceof KPIInformationElementRef) && ((KPIInformationElementRef) obj).getDef() != null) {
            def = ((KPIInformationElementRef) obj).getDef();
        }
        strategyView = false;
    }

    public void setEvaluationStrategyView(boolean view) {
        strategyView = view;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.views.EObjectPropertySource#isAddable(org.eclipse.emf.ecore.EStructuralFeature)
     */
    protected boolean canAddFeature(EStructuralFeature attr) {
        if (def != null && (attr.getName().equals("name") || attr.getName().equals("id") || attr.getName().equals("description"))) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            // replace with that of KPIInformationElementRef with that of KPIInformationElement
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

        if (def != null) {
            // we are referencing another object; show its properties here.
            it = def.eClass().getEAllAttributes().iterator();

            // add the new properties
            while (it.hasNext()) {
                EAttribute attr = (EAttribute) it.next();
                addPropertyToDescriptor(descriptors, attr, def.eClass());
            }
        }
        if (strategyView) {
            // get the strategy attribute
            EvaluationStrategy strategy = EvaluationStrategyManager.getInstance().getEvaluationStrategy();
            it = strategy.eClass().getEAllAttributes().iterator();

            // add the new properties
            while (it.hasNext()) {
                EAttribute attr = (EAttribute) it.next();
                addPropertyToDescriptor(descriptors, attr, strategy.eClass());
            }

            // Add the KPIInformationConfig
            KPIInformationConfig temp = EvaluationStrategyManager.getInstance().getKPIInformationConfigObject(def);
            it = temp.eClass().getEAllAttributes().iterator();
            // add the new properties
            while (it.hasNext()) {
                EAttribute attr = (EAttribute) it.next();
                addPropertyToDescriptor(descriptors, attr, temp.eClass());
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

        if (type.getInstanceClass() == KPIInformationElement.class) {
            kpiInformationElementDescriptor(descriptors, attr, propertyid);
        } else if (c.getInstanceClass() == EvaluationStrategy.class) {
            strategyDescriptor(descriptors, attr, propertyid);
        } else if (c.getInstanceClass() == KPIInformationConfig.class) {
            kpiInformationConfigDescriptor(descriptors, attr, propertyid);
        } else {
            super.addPropertyToDescriptor(descriptors, attr, c);
        }
    }

    /**
     * @param descriptors
     * @param attr
     * @param propertyid
     */
    private void kpiInformationElementDescriptor(Collection descriptors, EStructuralFeature attr, PropertyID propertyid) {
        if (((KPIInformationElementRef) getEditableValue()).getDiagram().getUrndefinition() == null)
            return;
        URNspec urn = ((KPIInformationElementRef) getEditableValue()).getDiagram().getUrndefinition().getUrnspec();
        Vector list = new Vector(urn.getGrlspec().getKpiInformationElements());
        Collections.sort(list, new EObjectClassNameComparator());

        String[] values = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {

            values[i] = EObjectClassNameComparator.getSortableElementName((KPIInformationElement) list.get(i));
            if (values[i] == null)
                values[i] = Messages.getString("KPIInformationElementPropertySource.Unnamed"); //$NON-NLS-1$
        }

        ComboBoxPropertyDescriptor pd = new ComboBoxPropertyDescriptor(propertyid, "definition", values); //$NON-NLS-1$
        pd.setCategory(Messages.getString("EObjectPropertySource.reference")); //$NON-NLS-1$ 
        descriptors.add(pd);

    }

    /**
     * @param descriptors
     * @param attr
     * @param propertyid
     */
    private void strategyDescriptor(Collection descriptors, EStructuralFeature attr, PropertyID propertyid) {
        // We add only the strategy name
        if (attr.getName() == "name") { //$NON-NLS-1$
            CustomTextPropertyDescriptor pd = new CustomTextPropertyDescriptor(propertyid, "strategyName"); //$NON-NLS-1$
            pd.setReadOnly(true);
            pd.setCategory("Strategy"); //$NON-NLS-1$
            descriptors.add(pd);
        }
    }

    /**
     * @param descriptors
     * @param attr
     * @param propertyid
     */
    private void kpiInformationConfigDescriptor(Collection descriptors, EStructuralFeature attr, PropertyID propertyid) {
        if (attr.getName() == "levelOfDimension") { //$NON-NLS-1$
            TextPropertyDescriptor pd = new TextPropertyDescriptor(propertyid, "Level of Dimension"); //$NON-NLS-1$

            ((PropertyDescriptor) pd).setValidator(new ICellEditorValidator() {
                public String isValid(Object value) {
                    return null;
                }
            });
            pd.setCategory("KPI Model Strategy"); //$NON-NLS-1$
            descriptors.add(pd);
        } else if (attr.getName() == "valueOfDimension") { //$NON-NLS-1$
            TextPropertyDescriptor pd = new TextPropertyDescriptor(propertyid, "Value of Dimension"); //$NON-NLS-1$

            ((PropertyDescriptor) pd).setValidator(new ICellEditorValidator() {
                public String isValid(Object value) {
                    return null;
                }
            });
            pd.setCategory("KPI Model Strategy"); //$NON-NLS-1$
            descriptors.add(pd);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.views.EObjectPropertySource#returnPropertyValue(org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object)
     */
    protected Object returnPropertyValue(EStructuralFeature feature, Object result) {
        if (result instanceof KPIInformationElement) {
            URNspec urn = ((KPIInformationElementRef) getEditableValue()).getDiagram().getUrndefinition().getUrnspec();
            Vector list = new Vector(urn.getGrlspec().getKpiInformationElements());
            Collections.sort(list, new EObjectClassNameComparator());
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(((KPIInformationElementRef) getEditableValue()).getDef()))
                    result = new Integer(i);
            }

        } else
            return super.returnPropertyValue(feature, result);

        return result;
    }

    /**
     * @param propertyid
     * @param feature
     * @return a feature
     */
    protected Object getFeature(PropertyID propertyid, EStructuralFeature feature) {
        Object result = null;

        // if this attribute comes from the referenced object
        if (propertyid.getEClass().getName() == "KPIInformationElement") //$NON-NLS-1$
            result = def.eGet(feature);
        else if (propertyid.getEClass().getName() == "EvaluationStrategy") { //$NON-NLS-1$
            result = EvaluationStrategyManager.getInstance().getEvaluationStrategy().eGet(feature);
        } else if (propertyid.getEClass().getName() == "KPIInformationConfig") { //$NON-NLS-1$
            result = EvaluationStrategyManager.getInstance().getKPIInformationConfigObject(def).eGet(feature);
        } else
            result = object.eGet(feature);
        return result;
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
                || (feature instanceof EReference && ((EReference) feature).getEReferenceType().getInstanceClass() == KPIInformationElementRef.class && (getEditableValue() instanceof IURNNode || getEditableValue() instanceof KPIInformationElementRef))) {
            if (propertyid.getEClass().getName() == "KPIInformationElement") //$NON-NLS-1$
                def.eSet(feature, null);
            else if (propertyid.getEClass().getName() == "EvaluationStrategy") { //$NON-NLS-1$
                EvaluationStrategyManager.getInstance().getEvaluationStrategy().eSet(feature, null);
            } else if (propertyid.getEClass().getName() == "KPIInformationConfig") { //$NON-NLS-1$
                // The default value for an KPIInformationConfig is ""
                EvaluationStrategyManager.getInstance().setLevelOfDimension(def, ""); //$NON-NLS-1$
                EvaluationStrategyManager.getInstance().setValueOfDimension(def, ""); //$NON-NLS-1$
            } else
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
        PropertyID propertyid = (PropertyID) id;
        EStructuralFeature feature = propertyid.getFeature();

        Object result = getPropertyValue(id);
        URNspec urn = ((KPIInformationElementRef) getEditableValue()).getDiagram().getUrndefinition().getUrnspec();
        if (feature.getEType().getInstanceClass() == KPIInformationElement.class) {

            Vector list = new Vector(urn.getGrlspec().getKpiInformationElements());
            Collections.sort(list, new EObjectClassNameComparator());
            result = list.get(((Integer) value).intValue());

            DeleteAllKPIModelLinkRefCommand deleteCmd = new DeleteAllKPIModelLinkRefCommand((KPIInformationElementRef) getEditableValue());
            deleteCmd.execute();
            setReferencedObject(propertyid, feature, result);
            CreateAllKPIModelLinkRefCommand createCmd = new CreateAllKPIModelLinkRefCommand((KPIInformationElementRef) getEditableValue());
            createCmd.execute();

            def = ((KPIInformationElementRef) object).getDef();
        } else if (feature.getContainerClass() == KPIInformationConfig.class && feature.getName() == "levelOfDimension") { //$NON-NLS-1$
            // The feature should be a String
            if (feature.getEType().getInstanceClass() == String.class) {
                String temp = (String) value;
                EvaluationStrategyManager.getInstance().setLevelOfDimension(def, temp);
            }
        } else if (feature.getContainerClass() == KPIInformationConfig.class && feature.getName() == "valueOfDimension") { //$NON-NLS-1$
            // The feature should be a String
            if (feature.getEType().getInstanceClass() == String.class) {
                String temp = (String) value;
                EvaluationStrategyManager.getInstance().setValueOfDimension(def, temp);
            }
        } else if (feature.getName() == "name") { //$NON-NLS-1$
            String message = URNNamingHelper.isNameValid(urn, (URNmodelElement) object, value.toString());

            if (message.length() == 0) {
                super.setPropertyValue(id, value);

            } else if (++i % 2 == 1) { // because refreshed twice.
                MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Error", message); //$NON-NLS-1$
            }

        } else {
            if (feature.getName().equalsIgnoreCase("fillColor")) { //$NON-NLS-1$
                // To be enabled if the color is added
                // ((KPIInformationElementRef) getEditableValue()).getDef().setFilled(true);
            }
            super.setPropertyValue(id, value);
        }
    }

    protected void setReferencedObject(PropertyID propertyid, EStructuralFeature feature, Object result) {
        if (propertyid.getEClass().getName() == "KPIInformationElement") { //$NON-NLS-1$
            def.eSet(feature, result);
        } else if (propertyid.getEClass().getName() == "EvaluationStrategy") { //$NON-NLS-1$
            EvaluationStrategyManager.getInstance().getEvaluationStrategy().eSet(feature, result);
        } else {
            object.eSet(feature, result);
        }
    }
}
