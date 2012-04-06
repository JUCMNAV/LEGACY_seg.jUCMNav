package seg.jUCMNav.views.property;

import grl.Contribution;
import grl.ContributionChange;
import grl.ContributionRange;
import grl.ContributionType;
import grl.ElementLink;
import grl.LinkRef;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import seg.jUCMNav.model.util.EObjectClassNameComparator;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.property.descriptors.ContributionRangePropertyDescriptor;
import urn.URNspec;

/**
 * Property source for LinkRefs
 * 
 * @author Jean-François Roy, sghanava
 * 
 */
public class LinkRefPropertySource extends URNElementPropertySource {

    private ElementLink element = null;

    /**
     * @param obj
     */
    public LinkRefPropertySource(EObject obj) {
        super(obj);
        if ((obj instanceof LinkRef) && (((LinkRef) obj).getLink() != null)) {
            element = ((LinkRef) obj).getLink();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.views.EObjectPropertySource#addSpecificProperties()
     */
    protected Vector addSpecificProperties() {
        Iterator it;
        Collection descriptors = new Vector();

        if (element != null) {
            // we are referencing another object; show its properties here.
            it = element.eClass().getEAllAttributes().iterator();

            // add the new properties
            while (it.hasNext()) {
                EAttribute attr = (EAttribute) it.next();
                addPropertyToDescriptor(descriptors, attr, element.eClass());
            }

            if (element instanceof Contribution) {
                Contribution contrib = (Contribution) element;
                ContributionChange change = EvaluationStrategyManager.getInstance().findApplicableContributionChange(contrib, false);

                if (change != null) {
                    EStructuralFeature attr = change.eClass().getEStructuralFeature("contribRange"); //$NON-NLS-1$
                    addPropertyToDescriptor(descriptors, attr, change.getContribRange().eClass());
                }
            }
        }
        return (Vector) descriptors;
    }

    /**
     * @param descriptors
     * @param propertyid
     */
    private void contributionRangeDescriptor(Collection descriptors, EStructuralFeature attr, PropertyID propertyid) {
        PropertyDescriptor pd = null;
        String name = attr.getName().toLowerCase();
        if (name.indexOf("contribrange") >= 0 && element != null && element instanceof Contribution) { //$NON-NLS-1$
            Contribution contrib = (Contribution) element;

            ContributionChange change = EvaluationStrategyManager.getInstance().findApplicableContributionChange(contrib, false);
            pd = new ContributionRangePropertyDescriptor(propertyid, change);
        } else {
            pd = new TextPropertyDescriptor(propertyid, attr.getName());
        }
        pd.setCategory("Strategy"); //$NON-NLS-1$
        descriptors.add(pd);
    }

    /**
     * @param propertyid
     * @param feature
     * @return a feature
     */
    protected Object getFeature(PropertyID propertyid, EStructuralFeature feature) {
        Object result = null;

        // if this attribute comes from the referenced object
        if (propertyid.getEClass() != object.eClass()) {
            if (propertyid.getEClass().getName().equals("ContributionRange")) //$NON-NLS-1$
                result = EvaluationStrategyManager.getInstance().findApplicableContributionChange((Contribution) element, false);
            else
            result = element.eGet(feature);
        } else
            result = object.eGet(feature);

        if (result instanceof Integer && propertyid.getFeature().getName() == "quantitativeContribution") { //$NON-NLS-1$
            int val = ((Integer) result).intValue();
            // val = StrategyEvaluationPreferences.getValueToVisualize(val);
            result = new Integer(val);
        }
        return result;
    }

    public void addPropertyToDescriptor(Collection descriptors, EStructuralFeature attr, EClass c) {
        EClassifier type = getFeatureType(attr);
        PropertyID propertyid = new PropertyID(c, attr);

        if (type.getInstanceClass() == ContributionRange.class) {
            contributionRangeDescriptor(descriptors, attr, propertyid);
        } else {
            super.addPropertyToDescriptor(descriptors, attr, c);
        }
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
        URNspec urn = ((LinkRef) getEditableValue()).getDiagram().getUrndefinition().getUrnspec();
        if (feature.getEType().getInstanceClass() == ElementLink.class) {

            Vector list = new Vector(urn.getGrlspec().getLinks());
            Collections.sort(list, new EObjectClassNameComparator());
            result = list.get(((Integer) value).intValue());
            setReferencedObject(propertyid, feature, result);
            element = ((LinkRef) object).getLink();

        } else if (feature.getContainerClass() == Contribution.class) {
            // The feature should be a int
            if (feature.getEType().getInstanceClass() == int.class) {
                Integer temp = new Integer(Integer.parseInt((String) value));
                setElementLinkQuantitativeContribution(element, temp.intValue());
            } else if (feature.getEType().getInstanceClass() == ContributionType.class) {
                ContributionType label = ContributionType.get(((Integer) value).intValue());
                setElementLinkQualitativeContribution(element, label);
            } else {
                super.setPropertyValue(id, value);
            }
        } else {
            super.setPropertyValue(id, value);
        }
    }

    protected void setReferencedObject(PropertyID propertyid, EStructuralFeature feature, Object result) {
        if (propertyid.getEClass() != object.eClass()) {
            element.eSet(feature, result);
        } else
            object.eSet(feature, result);
    }

    /**
     * Sets the qualitative contribution
     * 
     * @param link
     *            the link to which to apply the new value
     * @param type
     *            the new qualitative contribution type
     * 
     */
    private synchronized void setElementLinkQualitativeContribution(ElementLink link, ContributionType type) {
        EvaluationStrategyManager m = EvaluationStrategyManager.getInstance();
        // Change the value in the evaluation
        Contribution ele = (Contribution) link;
        ContributionType val = m.getActiveContribution(ele);
        if (type != val) {
            // ele.setContribution(type);
            m.setActiveContribution(m.getContributionContext(), ele, type);
            syncElementLinkQuantitativeContribution(ele, type);
        }
        // If it is a new Evaluation enter by the user, link it with the strategy and intentionalElement
        // ChangeElementLinkContributionCommand cmd = new ChangeElementLinkContributionCommand(ele, type);
        // if (cmd.canExecute()) {
        // cmd.execute();
        // }
    }

    /**
     * Sets the quantitative contribution
     * 
     * @param link
     *            the link to which to apply the new value
     * @param value
     *            the new quantitative contribution type
     * 
     */
    private synchronized void setElementLinkQuantitativeContribution(ElementLink link, int value) {
        EvaluationStrategyManager m = EvaluationStrategyManager.getInstance();
        Contribution ele = (Contribution) link;
        // value = StrategyEvaluationPreferences.getModelValueFromVisualization(value);

        int oldValue = m.getActiveQuantitativeContribution(ele);

        // Change the value in the evaluation
        if (value < -100)
            value = -100;
        else if (value > 100)
            value = 100;
        if (value != oldValue) {
            // ele.setQuantitativeContribution(value);
            m.setActiveQuantitativeContribution(m.getContributionContext(), ele, value);
            syncElementLinkQualitativeContribution(ele, value);
        }
        // If it is a new Evaluation enter by the user, link it with the strategy and intentionalElement
        // ChangeElementLinkContributionCommand cmd = new ChangeElementLinkContributionCommand(ele, type);
        // if (cmd.canExecute()) {
        // cmd.execute();
        // }
    }

    /**
     * Synchronize the quantitative evaluation to the qualitative evaluation
     * 
     * @param ele
     *            contribution model for which its type is being synchronized
     * @param value
     *            qualitative value for the contribution link
     * 
     */
    public static void syncElementLinkQuantitativeContribution(Contribution ele, ContributionType value) {
        EvaluationStrategyManager m = EvaluationStrategyManager.getInstance();
        // int quantitativeContribution = ele.getQuantitativeContribution();
        int quantitativeContribution = m.getActiveQuantitativeContribution(ele);
        String type = value.getName();

        if (ContributionType.MAKE_LITERAL.getName().equals(type) && quantitativeContribution < 100)
            m.setActiveQuantitativeContribution(m.getContributionContext(), ele, 100); // ele.setQuantitativeContribution(100);
        else if (ContributionType.SOME_POSITIVE_LITERAL.getName().equals(type) && (quantitativeContribution == 100 || quantitativeContribution < 50))
            m.setActiveQuantitativeContribution(m.getContributionContext(), ele, 75); // ele.setQuantitativeContribution(75);
        else if (ContributionType.HELP_LITERAL.getName().equals(type) && (quantitativeContribution >= 50 || quantitativeContribution <= 0))
            m.setActiveQuantitativeContribution(m.getContributionContext(), ele, 25); // ele.setQuantitativeContribution(25);
        else if (ContributionType.SOME_NEGATIVE_LITERAL.getName().equals(type) && (quantitativeContribution == -100 || quantitativeContribution > -50))
            m.setActiveQuantitativeContribution(m.getContributionContext(), ele, -75); // ele.setQuantitativeContribution(-75);
        else if (ContributionType.HURT_LITERAL.getName().equals(type) && (quantitativeContribution <= -50 || quantitativeContribution >= 0))
            m.setActiveQuantitativeContribution(m.getContributionContext(), ele, -25); // ele.setQuantitativeContribution(-25);
        else if (ContributionType.BREAK_LITERAL.getName().equals(type) && quantitativeContribution > -100)
            m.setActiveQuantitativeContribution(m.getContributionContext(), ele, -100); // ele.setQuantitativeContribution(-100);
        else if (ContributionType.UNKNOWN_LITERAL.getName().equals(type) && quantitativeContribution != 0)
            m.setActiveQuantitativeContribution(m.getContributionContext(), ele, 0); // ele.setQuantitativeContribution(0);
    }

    /**
     * Synchronize the qualitative contribution to the quantitative contribution
     * 
     * @param ele
     *            contribution model for which its type is being synchronized
     * @param newQuantitativeContribution
     *            quantitative value for the contribution link
     * 
     */
    public static void syncElementLinkQualitativeContribution(Contribution ele, int newQuantitativeContribution) {
        EvaluationStrategyManager m = EvaluationStrategyManager.getInstance();
        // ContributionType type = ele.getContribution();
        ContributionType type = m.getActiveContribution(ele);

        if (newQuantitativeContribution == 100 && !type.equals(ContributionType.MAKE_LITERAL))
            m.setActiveContribution(m.getContributionContext(), ele, ContributionType.MAKE_LITERAL); // ele.setContribution(ContributionType.MAKE_LITERAL);
        else if (newQuantitativeContribution >= 50 && newQuantitativeContribution < 100 && !type.equals(ContributionType.SOME_POSITIVE_LITERAL))
            m.setActiveContribution(m.getContributionContext(), ele, ContributionType.SOME_POSITIVE_LITERAL); // ele.setContribution(ContributionType.SOME_POSITIVE_LITERAL);
        else if (newQuantitativeContribution > 0 && newQuantitativeContribution < 50 && !type.equals(ContributionType.HELP_LITERAL))
            m.setActiveContribution(m.getContributionContext(), ele, ContributionType.HELP_LITERAL); // ele.setContribution(ContributionType.HELP_LITERAL);
        else if (newQuantitativeContribution > -50 && newQuantitativeContribution < 0 && !type.equals(ContributionType.HURT_LITERAL))
            m.setActiveContribution(m.getContributionContext(), ele, ContributionType.HURT_LITERAL); // ele.setContribution(ContributionType.HURT_LITERAL);
        else if (newQuantitativeContribution > -100 && newQuantitativeContribution <= -50 && !type.equals(ContributionType.SOME_NEGATIVE_LITERAL))
            m.setActiveContribution(m.getContributionContext(), ele, ContributionType.SOME_NEGATIVE_LITERAL); // ele.setContribution(ContributionType.SOME_NEGATIVE_LITERAL);
        else if (newQuantitativeContribution == -100 && !type.equals(ContributionType.BREAK_LITERAL))
            m.setActiveContribution(m.getContributionContext(), ele, ContributionType.BREAK_LITERAL); // ele.setContribution(ContributionType.BREAK_LITERAL);
        else if (newQuantitativeContribution == 0 && !type.equals(ContributionType.UNKNOWN_LITERAL))
            m.setActiveContribution(m.getContributionContext(), ele, ContributionType.UNKNOWN_LITERAL); // ele.setContribution(ContributionType.UNKNOWN_LITERAL);
    }
}
