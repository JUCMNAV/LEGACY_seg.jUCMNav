package seg.jUCMNav.views.property;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ui.views.properties.PropertyDescriptor;

import seg.jUCMNav.model.util.EObjectClassNameComparator;
import seg.jUCMNav.views.property.descriptors.ChangePropertyDescriptor;
import urn.dyncontext.Change;
import urn.dyncontext.ConstantChange;
import urn.dyncontext.DeactivationChange;
import urn.dyncontext.DynamicContext;
import urn.dyncontext.EnumChange;
import urn.dyncontext.FormulaChange;
import urn.dyncontext.LinearChange;
import urn.dyncontext.PropertyChange;
import urn.dyncontext.QuadraticChange;
import urncore.URNmodelElement;

/**
 * Property source for Change
 * 
 * @author aprajita
 * 
 */
public class ChangePropertySource extends URNElementPropertySource {
	
	private Change def = null;

    private String changeType;

    /**
     * Constructor that initialize the Change definition
     * 
     * @param obj
     */
    public ChangePropertySource(EObject obj) {
        super(obj);
        if ((obj instanceof Change) && ((Change) obj).getContext() != null) {
            def = (Change) obj;
            if (def instanceof EnumChange)
            	this.changeType = "Enum Change";
            else if (def instanceof DeactivationChange)
            	this.changeType = "Deactivation Change";
            else if (def instanceof ConstantChange)
            	this.changeType = "Constant Change";
            else if (def instanceof LinearChange)
            	this.changeType = "Linear Change";
            else if (def instanceof QuadraticChange)
            	this.changeType = "Quadratic Change";
            else if (def instanceof FormulaChange)
            	this.changeType = "Formula Change";
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
     * @see seg.jUCMNav.views.EObjectPropertySource#addPropertyToDescriptor(java.util.Collection, org.eclipse.emf.ecore.EStructuralFeature,
     * org.eclipse.emf.ecore.EClass)
     */
    public void addPropertyToDescriptor(Collection descriptors, EStructuralFeature attr, EClass c) {
        EClassifier type = getFeatureType(attr);

        PropertyID propertyid = new PropertyID(c, attr);

        if (type.getInstanceClass() == URNmodelElement.class) {
            elementDescriptor(descriptors, attr, propertyid);
        } else if (type.getInstanceClass() == DynamicContext.class) {
        	dynContextDescriptor(descriptors, attr, propertyid);
        } else if (type.getInstanceClass() == Date.class && attr.getName().equals("start")) {
        	startDateDescriptor(descriptors, attr, propertyid);
        } else if (type.getInstanceClass() == Date.class && attr.getName().equals("end")) {
        	endDateDescriptor(descriptors, attr, propertyid);
        } else if (type.getInstanceClass() == String.class && attr.getName().equals("affectedProperty")) {
        	affectedPropertyDescriptor(descriptors, attr, propertyid);
        } else if (attr.getName().equals("newValue")) {
        	newValueDescriptor(descriptors, attr, propertyid);
        } else if (attr.getName().equals("quadraticCoefficient")) {
        	quadCoefficientDescriptor(descriptors, attr, propertyid);
        } else if (attr.getName().equals("linearCoefficient")) {
        	linearCoefficientDescriptor(descriptors, attr, propertyid);
        } else if (attr.getName().equals("constant")) {
        	constantDescriptor(descriptors, attr, propertyid);
        } else if (attr.getName().equals("formula")) {
        	formulaDescriptor(descriptors, attr, propertyid);
        } else {
            super.addPropertyToDescriptor(descriptors, attr, c);
        }
    }

    /**
     * @param descriptors
     * @param attr
     * @param propertyid
     */
    private void elementDescriptor(Collection descriptors, EStructuralFeature attr, PropertyID propertyid) {
        if (((Change) getEditableValue()).getContext() == null)
            return;
        PropertyDescriptor dp = new PropertyDescriptor(propertyid, "element"); //$NON-NLS-1$
        dp.setCategory(changeType); //$NON-NLS-1$ 
        descriptors.add(dp);

    }
    
    /**
     * @param descriptors
     * @param attr
     * @param propertyid
     */
    private void affectedPropertyDescriptor(Collection descriptors, EStructuralFeature attr, PropertyID propertyid) {
        if (((Change) getEditableValue()).getContext() == null)
            return;
        PropertyDescriptor dp = new PropertyDescriptor(propertyid, "affectedProperty"); //$NON-NLS-1$
        dp.setCategory(changeType); //$NON-NLS-1$ 
        descriptors.add(dp);

    }
    
    /**
     * @param descriptors
     * @param attr
     * @param propertyid
     */
    private void newValueDescriptor(Collection descriptors, EStructuralFeature attr, PropertyID propertyid) {
        if (((Change) getEditableValue()).getContext() == null)
            return;
        ChangePropertyDescriptor dp = new ChangePropertyDescriptor(propertyid, (Change) getEditableValue(), "newValue"); //$NON-NLS-1$
        dp.setCategory(changeType); //$NON-NLS-1$ 
        descriptors.add(dp);

    }
    
    /**
     * @param descriptors
     * @param attr
     * @param propertyid
     */
    private void quadCoefficientDescriptor(Collection descriptors, EStructuralFeature attr, PropertyID propertyid) {
        if (((Change) getEditableValue()).getContext() == null)
            return;
        ChangePropertyDescriptor dp = new ChangePropertyDescriptor(propertyid, (Change) getEditableValue(), "quadraticCoefficient"); //$NON-NLS-1$
        dp.setCategory(changeType); //$NON-NLS-1$ 
        descriptors.add(dp);

    }
    
    /**
     * @param descriptors
     * @param attr
     * @param propertyid
     */
    private void linearCoefficientDescriptor(Collection descriptors, EStructuralFeature attr, PropertyID propertyid) {
        if (((Change) getEditableValue()).getContext() == null)
            return;
        ChangePropertyDescriptor dp = new ChangePropertyDescriptor(propertyid, (Change) getEditableValue(), "linearCoefficient"); //$NON-NLS-1$
        dp.setCategory(changeType); //$NON-NLS-1$ 
        descriptors.add(dp);

    }
    
    /**
     * @param descriptors
     * @param attr
     * @param propertyid
     */
    private void constantDescriptor(Collection descriptors, EStructuralFeature attr, PropertyID propertyid) {
        if (((Change) getEditableValue()).getContext() == null)
            return;
        ChangePropertyDescriptor dp = new ChangePropertyDescriptor(propertyid, (Change) getEditableValue(), "constant"); //$NON-NLS-1$
        dp.setCategory(changeType); //$NON-NLS-1$ 
        descriptors.add(dp);

    }
    
    /**
     * @param descriptors
     * @param attr
     * @param propertyid
     */
    private void formulaDescriptor(Collection descriptors, EStructuralFeature attr, PropertyID propertyid) {
        if (((Change) getEditableValue()).getContext() == null)
            return;
        ChangePropertyDescriptor dp = new ChangePropertyDescriptor(propertyid, (Change) getEditableValue(), "formula"); //$NON-NLS-1$
        dp.setCategory(changeType); //$NON-NLS-1$ 
        descriptors.add(dp);

    }
    
    /**
     * @param descriptors
     * @param attr
     * @param propertyid
     */
    private void startDateDescriptor(Collection descriptors, EStructuralFeature attr, PropertyID propertyid) {
    	if (((Change) getEditableValue()).getContext() == null)
            return;
    	ChangePropertyDescriptor dp = new ChangePropertyDescriptor(propertyid, (Change) getEditableValue(), "startDate"); //$NON-NLS-1$
        dp.setCategory(changeType); //$NON-NLS-1$ 
        descriptors.add(dp);

    }
    
    /**
     * @param descriptors
     * @param attr
     * @param propertyid
     */
    private void endDateDescriptor(Collection descriptors, EStructuralFeature attr, PropertyID propertyid) {
    	if (((Change) getEditableValue()).getContext() == null)
            return;
    	ChangePropertyDescriptor dp = new ChangePropertyDescriptor(propertyid, (Change) getEditableValue(), "endDate"); //$NON-NLS-1$
        dp.setCategory(changeType); //$NON-NLS-1$ 
        descriptors.add(dp);

    }
    
    /**
     * @param descriptors
     * @param attr
     * @param propertyid
     */
    private void dynContextDescriptor(Collection descriptors, EStructuralFeature attr, PropertyID propertyid) {
    	if (((Change) getEditableValue()).getContext() == null)
            return;
        PropertyDescriptor dp = new PropertyDescriptor(propertyid, "dynamicContext"); //$NON-NLS-1$
        dp.setCategory(changeType); //$NON-NLS-1$ 
        descriptors.add(dp);

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.views.EObjectPropertySource#returnPropertyValue(org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object)
     */
    protected Object returnPropertyValue(EStructuralFeature feature, Object result) {
        if (getFeatureType(feature).getInstanceClass() == URNmodelElement.class && getEditableValue() instanceof Change) {
        	result = ((Change) getEditableValue()).getElement().getName();

        } else if (getFeatureType(feature).getInstanceClass() == DynamicContext.class && getEditableValue() instanceof Change) {
        	result = EObjectClassNameComparator.getSortableElementName(((Change) getEditableValue()).getContext());
            
        } else if (getFeatureType(feature).getInstanceClass() == Date.class && getEditableValue() instanceof Change && feature.getName().equals("start")) {
        	DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            result = df.format(((Change) getEditableValue()).getStart());
            
        } else if (getFeatureType(feature).getInstanceClass() == Date.class && getEditableValue() instanceof Change && feature.getName().equals("end")) {
        	DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            result = df.format(((Change) getEditableValue()).getEnd());
            
        } else if (getFeatureType(feature).getInstanceClass() == String.class && getEditableValue() instanceof Change && feature.getName().equals("affectedProperty")) {
        	result = ((PropertyChange) getEditableValue()).getAffectedProperty();
            
        } else if (getEditableValue() instanceof Change && feature.getName().equals("newValue")) {
        	if (getEditableValue() instanceof EnumChange)
        		result = ((EnumChange) getEditableValue()).getNewValue();
        	else if (getEditableValue() instanceof ConstantChange)
        		result = ((ConstantChange) getEditableValue()).getNewValue();
        	else if (getEditableValue() instanceof LinearChange)
        		result = ((LinearChange) getEditableValue()).getNewValue();
            
        } else if (getEditableValue() instanceof Change && feature.getName().equals("quadraticCoefficient")) {
        	result = ((QuadraticChange) getEditableValue()).getQuadraticCoefficient();
            
        } else if (getEditableValue() instanceof Change && feature.getName().equals("linearCoefficient")) {
        	result = ((QuadraticChange) getEditableValue()).getLinearCoefficient();
            
        } else if (getEditableValue() instanceof Change && feature.getName().equals("constant")) {
        	result = ((QuadraticChange) getEditableValue()).getConstant();
            
        } else if (getEditableValue() instanceof Change && feature.getName().equals("formula")) {
        	result = ((FormulaChange) getEditableValue()).getFormula();
            
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
        if (propertyid.getEClass().getName() == "Change") //$NON-NLS-1$
            result = def.eGet(feature);
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
        PropertyID propertyid = (PropertyID) id;
        EStructuralFeature feature = propertyid.getFeature();

        if (feature.getName().toLowerCase().indexOf("color") >= 0 //$NON-NLS-1$
                || (feature instanceof EReference && ((EReference) feature).getEReferenceType().getInstanceClass() == Change.class && (getEditableValue() instanceof Change))) {
            def.eSet(feature, null);
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
        if (feature.getEType().getInstanceClass() == Change.class && feature.getName().equals("start")) {
        	result = (Date) value;
            ((Change) object).setStart((Date) result);

            def = ((Change) object);
            
        } else if (feature.getEType().getInstanceClass() == Change.class && feature.getName().equals("end")) {
        	result = (Date) value;
            ((Change) object).setEnd((Date) result);

            def = ((Change) object);
            
        } else {
            if (feature.getName().equalsIgnoreCase("fillColor")) { //$NON-NLS-1$
                // To be enabled if the color is added
                // ((KPIInformationElementRef) getEditableValue()).getDef().setFilled(true);
            }
            super.setPropertyValue(id, value);
        }
    }

}
