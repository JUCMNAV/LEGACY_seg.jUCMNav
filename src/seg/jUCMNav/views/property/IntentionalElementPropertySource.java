/**
 * 
 */
package seg.jUCMNav.views.property;

import grl.Evaluation;
import grl.EvaluationRange;
import grl.EvaluationStrategy;
import grl.ImportanceType;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.QualitativeLabel;
import grl.kpimodel.Indicator;
import grl.kpimodel.IndicatorGroup;
import grl.kpimodel.KPIConversion;
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
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.create.CreateAllKPIModelLinkRefCommand;
import seg.jUCMNav.model.commands.create.CreateAllLinkRefCommand;
import seg.jUCMNav.model.commands.delete.DeleteAllKPIModelLinkRefCommand;
import seg.jUCMNav.model.commands.delete.DeleteAllLinkRefCommand;
import seg.jUCMNav.model.util.EObjectClassNameComparator;
import seg.jUCMNav.model.util.URNNamingHelper;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.preferences.StrategyEvaluationPreferences;
import seg.jUCMNav.views.property.descriptors.CustomTextPropertyDescriptor;
import seg.jUCMNav.views.property.descriptors.EvaluationRangePropertyDescriptor;
import seg.jUCMNav.views.property.descriptors.IndicatorGroupPropertyDescriptor;
import urn.URNspec;
import urncore.IURNNode;
import urncore.URNmodelElement;

/**
 * This class is a special case of the properties sheet for IntentionalElementRef
 * 
 * It should include the IntentionalElement property associate with this reference
 * 
 * @author Jean-François Roy, pchen
 * 
 */
public class IntentionalElementPropertySource extends URNElementPropertySource {

    private IntentionalElement def = null;
    private boolean strategyView;

    private int i;

    /**
     * Constructor that initalize the intentionalElement definition
     * 
     * @param obj
     */
    public IntentionalElementPropertySource(EObject obj) {
        super(obj);
        if ((obj instanceof IntentionalElementRef) && ((IntentionalElementRef) obj).getDef() != null) {
            def = ((IntentionalElementRef) obj).getDef();
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
            // replace with that of IntentionalElementRef with that of IntentionalElement
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
                EStructuralFeature attr = (EStructuralFeature) it.next();
                addPropertyToDescriptor(descriptors, attr, def.eClass());
            }

            // add the groups properties for Indicator
            if (def instanceof Indicator) {
                EStructuralFeature attr = ((Indicator) def).eClass().getEStructuralFeature("groups"); //$NON-NLS-1$
                addPropertyToDescriptor(descriptors, attr, def.eClass());
            }

        }
        if (strategyView && EvaluationStrategyManager.getInstance().getEvaluationStrategy() != null) {
            // get the strategy attribute
            EvaluationStrategy strategy = EvaluationStrategyManager.getInstance().getEvaluationStrategy();
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

            // add the properties properties for the range
            if (temp.getStrategies() != null && temp.getEvalRange() != null && temp.getStrategies() == strategy) {
                EStructuralFeature attr = temp.eClass().getEStructuralFeature("evalRange"); //$NON-NLS-1$
                addPropertyToDescriptor(descriptors, attr, temp.getEvalRange().eClass());
            }

            /*
             * if (temp.getStrategies()!=null) { EvaluationRange range = temp.getEvalRange(); it = range.eClass().getEAllAttributes().iterator(); if
             * (attr.getName() == "evalRange" && temp.getStrategies()!=null) addPropertyToDescriptor(descriptors, attr, temp.getStrategies().eClass()); }
             */

            // Add KPIEvalValueSet to Indicator
            if (def instanceof Indicator) {
                KPIEvalValueSet kpiEval = EvaluationStrategyManager.getInstance().getActiveKPIEvalValueSet(def);
                it = kpiEval.eClass().getEAllAttributes().iterator();
                // Add the new properties
                while (it.hasNext()) {
                    EAttribute attr = (EAttribute) it.next();
                    addPropertyToDescriptor(descriptors, attr, kpiEval.eClass());
                }

                it = kpiEval.eClass().getEAllReferences().iterator();
                // Add the new properties
                while (it.hasNext()) {
                    EReference attr = (EReference) it.next();
                    if (attr.getName() == "kpiConv")
                        addPropertyToDescriptor(descriptors, attr, kpiEval.eClass());
                }
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

        if (type.getInstanceClass() == IntentionalElement.class || type.getInstanceClass() == Indicator.class) {
            intentionalElementDescriptor(descriptors, attr, propertyid);
        } else if (c.getInstanceClass() == EvaluationStrategy.class) {
            strategyDescriptor(descriptors, attr, propertyid);
        } else if (c.getInstanceClass() == Evaluation.class) {
            evaluationDescriptor(descriptors, attr, propertyid, c);
        } else if (type.getInstanceClass() == EvaluationRange.class) {
            evaluationRangeDescriptor(descriptors, attr, propertyid);
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
    private void evaluationRangeDescriptor(Collection descriptors, EStructuralFeature attr, PropertyID propertyid) {
        PropertyDescriptor pd = null;
        String name = attr.getName().toLowerCase();
        if (name.indexOf("evalrange") >= 0 && def != null) { //$NON-NLS-1$
            Evaluation ev = EvaluationStrategyManager.getInstance().getEvaluationObject(def);
            pd = new EvaluationRangePropertyDescriptor(propertyid, ev);
        } else {
            pd = new TextPropertyDescriptor(propertyid, attr.getName());
        }
        pd.setCategory("Strategy"); //$NON-NLS-1$
        descriptors.add(pd);
    }

    /**
     * @param descriptors
     * @param propertyid
     */
    private void indicatorGroupDescriptor(Collection descriptors, EStructuralFeature attr, PropertyID propertyid) {
        PropertyDescriptor pd = null;
        String name = attr.getName().toLowerCase();

        if (name.equals("groups") && getEditableValue() instanceof IntentionalElementRef //$NON-NLS-1$
                && ((IntentionalElementRef) getEditableValue()).getDef() instanceof Indicator) {
            pd = new IndicatorGroupPropertyDescriptor(propertyid, (IntentionalElementRef) getEditableValue());
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
    private void intentionalElementDescriptor(Collection descriptors, EStructuralFeature attr, PropertyID propertyid) {
        if (getEditableValue() == null || ((IntentionalElementRef) getEditableValue()).getDiagram() == null
                || ((IntentionalElementRef) getEditableValue()).getDiagram().getUrndefinition() == null)
            return;
        URNspec urn = ((IntentionalElementRef) getEditableValue()).getDiagram().getUrndefinition().getUrnspec();
        Vector list = new Vector(urn.getGrlspec().getIntElements());
        Collections.sort(list, new EObjectClassNameComparator());

        String[] values = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {

            values[i] = EObjectClassNameComparator.getSortableElementName((IntentionalElement) list.get(i));
            if (values[i] == null)
                values[i] = Messages.getString("IntentionalElementPropertySource.Unnamed"); //$NON-NLS-1$
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
    private void evaluationDescriptor(Collection descriptors, EStructuralFeature attr, PropertyID propertyid, EClass eclass) {
        if (attr.getName() == "evaluation") { //$NON-NLS-1$
            String name = Messages.getString("IntentionalElementPropertySource.EvaluationLevel"); //$NON-NLS-1$

            URNspec urn = def.getGrlspec().getUrnspec();
            if (StrategyEvaluationPreferences.getVisualizeAsPositiveRange(urn))
                name = name.replace("-100", "0"); //$NON-NLS-1$ //$NON-NLS-2$
            TextPropertyDescriptor pd = new TextPropertyDescriptor(propertyid, name);

            ((PropertyDescriptor) pd).setValidator(new ICellEditorValidator() {
                public String isValid(Object value) {
                    int intValue = -1;
                    try {
                        intValue = Integer.parseInt((String) value);
                        URNspec urn2 = def.getGrlspec().getUrnspec();
                        if (StrategyEvaluationPreferences.getVisualizeAsPositiveRange(urn2)) {
                            if (intValue < 0 || intValue > 100)
                                return "Not In Range";//$NON-NLS-1$ 
                        } else if (intValue < -100 || intValue > 100)
                            return "Not In Range";//$NON-NLS-1$

                        return null;
                    } catch (NumberFormatException exc) {
                        return "Not Number"; //$NON-NLS-1$
                    }
                }
            });
            pd.setCategory("Strategy"); //$NON-NLS-1$
            descriptors.add(pd);
        } else if (attr.getName() == "qualitativeEvaluation") { //$NON-NLS-1$

            Collection c = QualitativeLabel.VALUES;
            String[] values = new String[c.size()];
            int i = 0;
            Iterator vIter = c.iterator();
            while (vIter.hasNext()) {
                QualitativeLabel tmp = (QualitativeLabel) vIter.next();
                values[i++] = tmp.getName();
            }
            ComboBoxPropertyDescriptor pd = new ComboBoxPropertyDescriptor(propertyid, "qualitativeEvaluation", values); //$NON-NLS-1$

            pd.setCategory("Strategy"); //$NON-NLS-1$
            descriptors.add(pd);
        } else {
            int size = descriptors.size();
            super.addPropertyToDescriptor(descriptors, attr, eclass);
            if (size < descriptors.size()) {
                PropertyDescriptor pd = (PropertyDescriptor) descriptors.toArray()[descriptors.size() - 1];
                pd.setCategory("Strategy"); //$NON-NLS-1$
            }
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

            pd.setCategory("KPI Model Strategy");
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

            pd.setCategory("KPI Model Strategy");
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

            pd.setCategory("KPI Model Strategy");
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

            pd.setCategory("KPI Model Strategy");
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

            pd.setCategory("KPI Model Strategy");
            descriptors.add(pd);
        } else if (getFeatureType(attr).getInstanceClass() == KPIConversion.class) { //$NON-NLS-1$
            kpiConversionDescriptor(descriptors, propertyid);
        } else if (attr.getName() == "qualitativeEvaluationValue") { //$NON-NLS-1$
            CustomTextPropertyDescriptor pd = new CustomTextPropertyDescriptor(propertyid, "Qualitative Evaluation Value"); //$NON-NLS-1$

            ((PropertyDescriptor) pd).setValidator(new ICellEditorValidator() {
                public String isValid(Object value) {
                    return null;
                }
            });

            // Open for editing in the simulation test, will be readonly when a formal way exists.
            // pd.setReadOnly(true);

            pd.setCategory("KPI Model Strategy");
            descriptors.add(pd);
        }
    }

    /**
     * Creates a drop down list for strategy groups.
     * 
     * @param descriptors
     * @param propertyid
     */
    private void kpiConversionDescriptor(Collection descriptors, PropertyID propertyid) {
        Vector list = getKPIConversionList();

        String[] values = new String[list.size() + 1];
        values[0] = "(undefined)";
        for (int i = 0; i < list.size(); i++) {

            values[i + 1] = EObjectClassNameComparator.getSortableElementName((KPIConversion) list.get(i));
            if (values[i + 1] == null)
                values[i + 1] = Messages.getString("URNElementPropertySource.unnamed"); //$NON-NLS-1$
        }

        ComboBoxPropertyDescriptor pd = new ComboBoxPropertyDescriptor(propertyid, "KPI Conversion", values);
        pd.setCategory("KPI Model Strategy");
        descriptors.add(pd);
    }

    public Vector getKPIConversionList() {
        URNspec urn = def.getGrlspec().getUrnspec();
        Vector list;
        list = new Vector(urn.getGrlspec().getKPIConversion());
        Collections.sort(list, new EObjectClassNameComparator());
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.views.EObjectPropertySource#returnPropertyValue(org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object)
     */
    protected Object returnPropertyValue(EStructuralFeature feature, Object result) {
        if (result instanceof IntentionalElement) {
            URNspec urn = ((IntentionalElementRef) getEditableValue()).getDiagram().getUrndefinition().getUrnspec();
            Vector list = new Vector(urn.getGrlspec().getIntElements());
            Collections.sort(list, new EObjectClassNameComparator());
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(((IntentionalElementRef) getEditableValue()).getDef()))
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
        } else if (feature.getName() == "kpiConv") {
            if (result == null)
                result = new Integer(0);
            else
                result = new Integer(getKPIConversionList().indexOf(result) + 1);
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
        if (propertyid.getEClass().getName() == "IntentionalElement" || propertyid.getEClass().getName() == "Indicator") //$NON-NLS-1$ //$NON-NLS-2$
            result = def.eGet(feature);
        else if (propertyid.getEClass().getName() == "EvaluationStrategy") { //$NON-NLS-1$
            result = EvaluationStrategyManager.getInstance().getEvaluationStrategy().eGet(feature);
        } else if (propertyid.getEClass().getName() == "EvaluationRange") { //$NON-NLS-1$
            result = EvaluationStrategyManager.getInstance().getEvaluationObject(def).eGet(feature);
        } else if (propertyid.getEClass().getName() == "Evaluation") { //$NON-NLS-1$
            result = EvaluationStrategyManager.getInstance().getEvaluationObject(def).eGet(feature);
            /*
             * if (result instanceof Integer) { result = new Integer(StrategyEvaluationPreferences.getValueToVisualize(((Integer)result).intValue())); }
             */
        } else if (propertyid.getEClass().getName() == "KPIEvalValueSet") { //$NON-NLS-1$
            Evaluation eval = EvaluationStrategyManager.getInstance().getEvaluationObject(def);
            if (eval != null && EvaluationStrategyManager.getInstance().getActiveKPIEvalValueSet(def) != null) {
                result = EvaluationStrategyManager.getInstance().getActiveKPIEvalValueSet(def).eGet(feature);
            }
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
                || (feature instanceof EReference && ((EReference) feature).getEReferenceType().getInstanceClass() == IntentionalElementRef.class && (getEditableValue() instanceof IURNNode || getEditableValue() instanceof IntentionalElementRef))) {
            if (propertyid.getEClass().getName() == "IntentionalElement" || propertyid.getEClass().getName() == "Indicator") //$NON-NLS-1$ //$NON-NLS-2$
                def.eSet(feature, null);
            else if (propertyid.getEClass().getName() == "EvaluationStrategy") { //$NON-NLS-1$
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
        URNspec urn = ((IntentionalElementRef) getEditableValue()).getDiagram().getUrndefinition().getUrnspec();
        if (feature.getEType().getInstanceClass() == IntentionalElement.class || feature.getEType().getInstanceClass() == Indicator.class) {

            Vector list = new Vector(urn.getGrlspec().getIntElements());
            Collections.sort(list, new EObjectClassNameComparator());
            result = list.get(((Integer) value).intValue());

            DeleteAllLinkRefCommand deleteCmd = new DeleteAllLinkRefCommand((IntentionalElementRef) getEditableValue());
            deleteCmd.execute();
            setReferencedObject(propertyid, feature, result);
            CreateAllLinkRefCommand createCmd = new CreateAllLinkRefCommand((IntentionalElementRef) getEditableValue());
            createCmd.execute();

            if (feature.getEType().getInstanceClass() == Indicator.class) {
                DeleteAllKPIModelLinkRefCommand deleteKpiModelLinkCmd = new DeleteAllKPIModelLinkRefCommand((IntentionalElementRef) getEditableValue());
                deleteKpiModelLinkCmd.execute();
                setReferencedObject(propertyid, feature, result);
                CreateAllKPIModelLinkRefCommand createKpiModelLinkCmd = new CreateAllKPIModelLinkRefCommand((IntentionalElementRef) getEditableValue());
                createKpiModelLinkCmd.execute();
            }

            def = ((IntentionalElementRef) object).getDef();
        } else if (feature.getContainerClass() == Evaluation.class) {
            // The feature should be a int
            if (feature.getEType().getInstanceClass() == int.class) {
                Integer temp = new Integer(Integer.parseInt((String) value));
                int val = temp.intValue();
                // val = StrategyEvaluationPreferences.getModelValueFromVisualization(urn, val);
                EvaluationStrategyManager.getInstance().setIntentionalElementEvaluation(def, val);
            } else if (feature.getEType().getInstanceClass() == QualitativeLabel.class) {
                QualitativeLabel label = QualitativeLabel.get(((Integer) value).intValue());
                EvaluationStrategyManager.getInstance().setIntentionalElementQualitativeEvaluation(def, label);
            } else {
                super.setPropertyValue(id, value);
            }
        } else if (feature.getContainerClass() == KPIEvalValueSet.class) {
            // The feature should be a number, except the unit which is String
            if (feature.getEType().getInstanceClass() == double.class) {
                Double temp = new Double(Double.parseDouble((String) value));
                EvaluationStrategyManager.getInstance().setKPIEvalValueSet(def, feature, temp.doubleValue());
            } else if (feature.getEType().getInstanceClass() == String.class) {
                EvaluationStrategyManager.getInstance().setKPIEvalValueSetString(def, feature, (String) value);
            } else if (feature.getName() == "kpiConv" && value instanceof Integer)
            {
                int idx = ((Integer)value).intValue();
                KPIConversion conv = null;
                if (idx>0)
                    conv = (KPIConversion)getKPIConversionList().get(idx-1);
                
                EvaluationStrategyManager.getInstance().setKPIEvalValueSetKPIConversion(def, feature, conv);
            }
        } else if (feature.getContainerClass() == IntentionalElement.class) {
            if (feature.getEType().getInstanceClass() == ImportanceType.class) {
                ImportanceType temp = ImportanceType.get(((Integer) value).intValue());
                EvaluationStrategyManager.getInstance().setIntentionalElementQualitativeImportance(def, temp);
            } else if (feature.getEType().getInstanceClass() == int.class) {
                Integer temp = new Integer(Integer.parseInt((String) value));
                EvaluationStrategyManager.getInstance().setIntentionalElementQuantitativeImportance(def, temp.intValue());
            } else {
                super.setPropertyValue(id, value);
            }

        } else if (feature.getName() == "name") { //$NON-NLS-1$
            value = value.toString().trim();
            String message = URNNamingHelper.isNameValid(urn, (URNmodelElement) object, value.toString());

            if (message.length() == 0) {
                super.setPropertyValue(id, value);

            } else if (++i % 2 == 1) { // because refreshed twice.
                MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Error", message); //$NON-NLS-1$
            }

        } else {
            if (feature.getName().equalsIgnoreCase("fillColor")) { //$NON-NLS-1$
                ((IntentionalElementRef) getEditableValue()).getDef().setFilled(true);
            }
            super.setPropertyValue(id, value);
        }
    }

    protected void setReferencedObject(PropertyID propertyid, EStructuralFeature feature, Object result) {
        if (propertyid.getEClass().getName() == "IntentionalElement" || propertyid.getEClass().getName() == "Indicator") { //$NON-NLS-1$ //$NON-NLS-2$
            def.eSet(feature, result);
        } else if (propertyid.getEClass().getName() == "EvaluationStrategy") { //$NON-NLS-1$
            EvaluationStrategyManager.getInstance().getEvaluationStrategy().eSet(feature, result);
        } else if (propertyid.getEClass().getName() == "Evaluation") { //$NON-NLS-1$
            Evaluation eval = EvaluationStrategyManager.getInstance().getEvaluationObject(def);
            eval.eSet(feature, result);
        } else {
            object.eSet(feature, result);
        }
    }
}
