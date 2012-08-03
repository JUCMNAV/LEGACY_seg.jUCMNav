package seg.jUCMNav.views.property;

import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.kpimodel.Indicator;
import grl.kpimodel.IndicatorGroup;
import grl.kpimodel.KPIEvalValueSet;

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
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.util.EObjectClassNameComparator;
import seg.jUCMNav.model.util.URNNamingHelper;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.property.descriptors.CustomTextPropertyDescriptor;
import seg.jUCMNav.views.property.descriptors.IndicatorGroupPropertyDescriptor;
import urn.URNspec;
import urncore.IURNNode;
import urncore.URNmodelElement;

/**
 * This class is a special case of the properties sheet for Indicator
 * 
 * It should include the Indicator property associate with this reference
 * 
 * @author pchen
 * 
 */
public class IndicatorPropertySource extends URNElementPropertySource {

    private Indicator def = null;

    private int i;

    /**
     * Constructor that initalize the intentionalElement definition
     * 
     * @param obj
     */
    public IndicatorPropertySource(EObject obj) {
        super(obj);
        if (obj instanceof Indicator) {
            def = (Indicator) obj;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.views.EObjectPropertySource#isAddable(org.eclipse.emf.ecore.EStructuralFeature)
     */
    protected boolean canAddFeature(EStructuralFeature attr) {
        if (def != null && (attr.getName().equals("name") || attr.getName().equals("id") || attr.getName().equals("description"))) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
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
            // show its special referrenced properties here.
        }

        EvaluationStrategy strategy = EvaluationStrategyManager.getInstance().getEvaluationStrategy();
        if (strategy != null) {
            // get the strategy attribute
            it = strategy.eClass().getEAllAttributes().iterator();

            // add the new properties
            while (it.hasNext()) {
                EAttribute attr = (EAttribute) it.next();
                addPropertyToDescriptor(descriptors, attr, strategy.eClass());
            }

            // Add the evaluation
            Evaluation temp = EvaluationStrategyManager.getInstance().getEvaluationObject(def);
            it = temp.eClass().getEAllAttributes().iterator();
            // add the new properties
            while (it.hasNext()) {
                EAttribute attr = (EAttribute) it.next();
                addPropertyToDescriptor(descriptors, attr, temp.eClass());
            }

            // Add KPIEvalValueSet
            KPIEvalValueSet kpiEval = EvaluationStrategyManager.getInstance().getActiveKPIEvalValueSet(def);
            it = kpiEval.eClass().getEAllAttributes().iterator();
            // Add the new properties
            while (it.hasNext()) {
                EAttribute attr = (EAttribute) it.next();
                addPropertyToDescriptor(descriptors, attr, kpiEval.eClass());
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

        if (c.getInstanceClass() == EvaluationStrategy.class) {
            strategyDescriptor(descriptors, attr, propertyid);
        } else if (c.getInstanceClass() == Evaluation.class) {
            evaluationDescriptor(descriptors, attr, propertyid);
        } else if (c.getInstanceClass() == KPIEvalValueSet.class) {
            kpiEvalValueSetDescriptor(descriptors, attr, propertyid);
        } else if (type.getInstanceClass() == IndicatorGroup.class) {
            indicatorGroupDescriptor(descriptors, attr, propertyid);
        } else {
            super.addPropertyToDescriptor(descriptors, attr, c);
        }
    }

    /**
     * @param descriptors
     * @param propertyid
     */
    private void indicatorGroupDescriptor(Collection descriptors, EStructuralFeature attr, PropertyID propertyid) {
        PropertyDescriptor pd = null;
        String name = attr.getName().toLowerCase();

        if (name.equals("groups") && getEditableValue() instanceof Indicator) { //$NON-NLS-1$
            pd = new IndicatorGroupPropertyDescriptor(propertyid, (Indicator) getEditableValue());
            pd.setCategory(Messages.getString("EObjectPropertySource.Indicator")); //$NON-NLS-1$
        } else {
            pd = new TextPropertyDescriptor(propertyid, attr.getName());
        }

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
    private void evaluationDescriptor(Collection descriptors, EStructuralFeature attr, PropertyID propertyid) {
        if (attr.getName() == "evaluation") { //$NON-NLS-1$
            TextPropertyDescriptor pd = new TextPropertyDescriptor(propertyid, "evaluationLevel (100 to -100)"); //$NON-NLS-1$

            ((PropertyDescriptor) pd).setValidator(new ICellEditorValidator() {
                public String isValid(Object value) {
                    int intValue = -1;
                    try {
                        intValue = Integer.parseInt((String) value);
                        return null;
                    } catch (NumberFormatException exc) {
                        return "Not Number"; //$NON-NLS-1$
                    }
                }
            });
            pd.setCategory("Strategy"); //$NON-NLS-1$
            descriptors.add(pd);
        }
    }

    /**
     * @param descriptors
     * @param attr
     * @param propertyid
     */
    private void kpiEvalValueSetDescriptor(Collection descriptors, EStructuralFeature attr, PropertyID propertyid) {
        if (attr.getName() == "targetValue") { //$NON-NLS-1$
            TextPropertyDescriptor pd = new TextPropertyDescriptor(propertyid, "Target value"); //$NON-NLS-1$

            ((PropertyDescriptor) pd).setValidator(new ICellEditorValidator() {
                public String isValid(Object value) {
                    double doubleValue = -1.1;
                    try {
                        doubleValue = Double.parseDouble((String) value);
                        return null;
                    } catch (NumberFormatException exc) {
                        return "Not Number"; //$NON-NLS-1$
                    }
                }
            });

            pd.setCategory("KPI Model Strategy"); //$NON-NLS-1$
            descriptors.add(pd);
        } else if (attr.getName() == "thresholdValue") { //$NON-NLS-1$
            TextPropertyDescriptor pd = new TextPropertyDescriptor(propertyid, "Threshold value"); //$NON-NLS-1$

            ((PropertyDescriptor) pd).setValidator(new ICellEditorValidator() {
                public String isValid(Object value) {
                    double doubleValue = -1.1;
                    try {
                        doubleValue = Double.parseDouble((String) value);
                        return null;
                    } catch (NumberFormatException exc) {
                        return "Not Number"; //$NON-NLS-1$
                    }
                }
            });

            pd.setCategory("KPI Model Strategy"); //$NON-NLS-1$
            descriptors.add(pd);
        } else if (attr.getName() == "worstValue") { //$NON-NLS-1$
            TextPropertyDescriptor pd = new TextPropertyDescriptor(propertyid, "Worst value"); //$NON-NLS-1$

            ((PropertyDescriptor) pd).setValidator(new ICellEditorValidator() {
                public String isValid(Object value) {
                    double doubleValue = -1.1;
                    try {
                        doubleValue = Double.parseDouble((String) value);
                        return null;
                    } catch (NumberFormatException exc) {
                        return "Not Number"; //$NON-NLS-1$
                    }
                }
            });

            pd.setCategory("KPI Model Strategy"); //$NON-NLS-1$
            descriptors.add(pd);
        } else if (attr.getName() == "evaluationValue") { //$NON-NLS-1$
            CustomTextPropertyDescriptor pd = new CustomTextPropertyDescriptor(propertyid, "Evaluation value"); //$NON-NLS-1$

            ((PropertyDescriptor) pd).setValidator(new ICellEditorValidator() {
                public String isValid(Object value) {
                    double doubleValue = -1.1;
                    try {
                        doubleValue = Double.parseDouble((String) value);
                        return null;
                    } catch (NumberFormatException exc) {
                        return "Not Number"; //$NON-NLS-1$
                    }
                }
            });

            // Open for editing in the simulation test, will be readonly when a formal way exists.
            // pd.setReadOnly(true);

            pd.setCategory("KPI Model Strategy"); //$NON-NLS-1$
            descriptors.add(pd);
        } else if (attr.getName() == "unit") { //$NON-NLS-1$
            CustomTextPropertyDescriptor pd = new CustomTextPropertyDescriptor(propertyid, "Unit"); //$NON-NLS-1$

            ((PropertyDescriptor) pd).setValidator(new ICellEditorValidator() {
                public String isValid(Object value) {
                    return null;
                }
            });

            // Open for editing in the simulation test, will be readonly when a formal way exists.
            // pd.setReadOnly(true);

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
        if (result instanceof IntentionalElement) {
            URNspec urn = ((IntentionalElement) getEditableValue()).getGrlspec().getUrnspec();
            Vector list = new Vector(urn.getGrlspec().getIntElements());
            Collections.sort(list, new EObjectClassNameComparator());
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(((IntentionalElement) getEditableValue())))
                    result = new Integer(i);
            }
        } else if (feature.getName().equals("evaluationValue")) {
            IntentionalElement elem = null;
            if (object instanceof IntentionalElementRef) {
                elem = ((IntentionalElementRef) object).getDef();
            } else if (object instanceof IntentionalElement)
                elem = (IntentionalElement) object;
            if (elem != null)
                return new Double(EvaluationStrategyManager.getInstance().getActiveKPIValue(elem)).toString();
            else
                return super.returnPropertyValue(feature, result);
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
        if (propertyid.getEClass().getName() == "EvaluationStrategy") { //$NON-NLS-1$
            result = EvaluationStrategyManager.getInstance().getEvaluationStrategy().eGet(feature);
        } else if (propertyid.getEClass().getName() == "Evaluation") { //$NON-NLS-1$
            result = EvaluationStrategyManager.getInstance().getEvaluationObject(def).eGet(feature);
        } else if (propertyid.getEClass().getName() == "KPIEvalValueSet") { //$NON-NLS-1$
            result = EvaluationStrategyManager.getInstance().getActiveKPIEvalValueSet(def).eGet(feature);
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
                || (feature instanceof EReference && ((EReference) feature).getEReferenceType().getInstanceClass() == IntentionalElement.class && (getEditableValue() instanceof IURNNode || getEditableValue() instanceof IntentionalElement))) {
            if (propertyid.getEClass().getName() == "EvaluationStrategy") { //$NON-NLS-1$
                EvaluationStrategyManager.getInstance().getEvaluationStrategy().eSet(feature, null);
            } else if (propertyid.getEClass().getName() == "Evaluation") { //$NON-NLS-1$
                // The default value for an Evaluation is 0
                EvaluationStrategyManager.getInstance().setIntentionalElementEvaluation(def, 0);
            } else if (feature.getContainerClass() == KPIEvalValueSet.class) {
                // The default value for an attribute of KPIEvalValueSet is 0
                EvaluationStrategyManager.getInstance().resetKPIEvalValueSet(def);
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
        URNspec urn = ((IntentionalElement) getEditableValue()).getGrlspec().getUrnspec();
        if (feature.getContainerClass() == Evaluation.class) {
            // The feature should be a int
            if (feature.getEType().getInstanceClass() == int.class) {
                Integer temp = new Integer(Integer.parseInt((String) value));
                EvaluationStrategyManager.getInstance().setIntentionalElementEvaluation(def, temp.intValue());
            }
        } else if (feature.getContainerClass() == KPIEvalValueSet.class) {
            // The feature should be a number, except the unit which is String
            if (feature.getEType().getInstanceClass() == double.class) {
                EvaluationStrategyManager.getInstance().setKPIEvalValueSet(def, feature, Double.parseDouble((String) value));
            } else if (feature.getEType().getInstanceClass() == String.class) {
                EvaluationStrategyManager.getInstance().setKPIEvalValueSetString(def, feature, (String) value);
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
                ((IntentionalElement) getEditableValue()).setFilled(true);
            }
            super.setPropertyValue(id, value);
        }
    }

    protected void setReferencedObject(PropertyID propertyid, EStructuralFeature feature, Object result) {
        if (propertyid.getEClass().getName() == "EvaluationStrategy") { //$NON-NLS-1$
            EvaluationStrategyManager.getInstance().getEvaluationStrategy().eSet(feature, result);
        } else {
            object.eSet(feature, result);
        }
    }
}
