/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import ucm.map.Condition;
import ucm.map.MapPackage;

import ucm.scenario.ScenarioPackage;
import ucm.scenario.Variable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Condition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.map.impl.ConditionImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link ucm.map.impl.ConditionImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link ucm.map.impl.ConditionImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link ucm.map.impl.ConditionImpl#getLabelX <em>Label X</em>}</li>
 *   <li>{@link ucm.map.impl.ConditionImpl#getLabelY <em>Label Y</em>}</li>
 *   <li>{@link ucm.map.impl.ConditionImpl#getVariables <em>Variables</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConditionImpl extends EObjectImpl implements Condition {
    /**
     * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLabel()
     * @generated
     * @ordered
     */
    protected static final String LABEL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLabel()
     * @generated
     * @ordered
     */
    protected String label = LABEL_EDEFAULT;

    /**
     * The default value of the '{@link #getExpression() <em>Expression</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExpression()
     * @generated
     * @ordered
     */
    protected static final String EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getExpression() <em>Expression</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExpression()
     * @generated
     * @ordered
     */
    protected String expression = EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected static final String DESCRIPTION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected String description = DESCRIPTION_EDEFAULT;

    /**
     * The default value of the '{@link #getLabelX() <em>Label X</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLabelX()
     * @generated
     * @ordered
     */
    protected static final int LABEL_X_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getLabelX() <em>Label X</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLabelX()
     * @generated
     * @ordered
     */
    protected int labelX = LABEL_X_EDEFAULT;

    /**
     * The default value of the '{@link #getLabelY() <em>Label Y</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLabelY()
     * @generated
     * @ordered
     */
    protected static final int LABEL_Y_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getLabelY() <em>Label Y</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLabelY()
     * @generated
     * @ordered
     */
    protected int labelY = LABEL_Y_EDEFAULT;

    /**
     * The cached value of the '{@link #getVariables() <em>Variables</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getVariables()
     * @generated
     * @ordered
     */
    protected EList variables = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ConditionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return MapPackage.eINSTANCE.getCondition();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getLabel() {
        return label;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLabel(String newLabel) {
        String oldLabel = label;
        label = newLabel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.CONDITION__LABEL, oldLabel, label));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getExpression() {
        return expression;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setExpression(String newExpression) {
        String oldExpression = expression;
        expression = newExpression;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.CONDITION__EXPRESSION, oldExpression, expression));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDescription() {
        return description;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDescription(String newDescription) {
        String oldDescription = description;
        description = newDescription;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.CONDITION__DESCRIPTION, oldDescription, description));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getLabelX() {
        return labelX;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLabelX(int newLabelX) {
        int oldLabelX = labelX;
        labelX = newLabelX;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.CONDITION__LABEL_X, oldLabelX, labelX));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getLabelY() {
        return labelY;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLabelY(int newLabelY) {
        int oldLabelY = labelY;
        labelY = newLabelY;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.CONDITION__LABEL_Y, oldLabelY, labelY));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getVariables() {
        if (variables == null) {
            variables = new EObjectWithInverseResolvingEList.ManyInverse(Variable.class, this, MapPackage.CONDITION__VARIABLES, ScenarioPackage.VARIABLE__USAGES);
        }
        return variables;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case MapPackage.CONDITION__VARIABLES:
                    return ((InternalEList)getVariables()).basicAdd(otherEnd, msgs);
                default:
                    return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
            }
        }
        if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
        return eBasicSetContainer(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case MapPackage.CONDITION__VARIABLES:
                    return ((InternalEList)getVariables()).basicRemove(otherEnd, msgs);
                default:
                    return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
            }
        }
        return eBasicSetContainer(null, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(EStructuralFeature eFeature, boolean resolve) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case MapPackage.CONDITION__LABEL:
                return getLabel();
            case MapPackage.CONDITION__EXPRESSION:
                return getExpression();
            case MapPackage.CONDITION__DESCRIPTION:
                return getDescription();
            case MapPackage.CONDITION__LABEL_X:
                return new Integer(getLabelX());
            case MapPackage.CONDITION__LABEL_Y:
                return new Integer(getLabelY());
            case MapPackage.CONDITION__VARIABLES:
                return getVariables();
        }
        return eDynamicGet(eFeature, resolve);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eSet(EStructuralFeature eFeature, Object newValue) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case MapPackage.CONDITION__LABEL:
                setLabel((String)newValue);
                return;
            case MapPackage.CONDITION__EXPRESSION:
                setExpression((String)newValue);
                return;
            case MapPackage.CONDITION__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case MapPackage.CONDITION__LABEL_X:
                setLabelX(((Integer)newValue).intValue());
                return;
            case MapPackage.CONDITION__LABEL_Y:
                setLabelY(((Integer)newValue).intValue());
                return;
            case MapPackage.CONDITION__VARIABLES:
                getVariables().clear();
                getVariables().addAll((Collection)newValue);
                return;
        }
        eDynamicSet(eFeature, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset(EStructuralFeature eFeature) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case MapPackage.CONDITION__LABEL:
                setLabel(LABEL_EDEFAULT);
                return;
            case MapPackage.CONDITION__EXPRESSION:
                setExpression(EXPRESSION_EDEFAULT);
                return;
            case MapPackage.CONDITION__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case MapPackage.CONDITION__LABEL_X:
                setLabelX(LABEL_X_EDEFAULT);
                return;
            case MapPackage.CONDITION__LABEL_Y:
                setLabelY(LABEL_Y_EDEFAULT);
                return;
            case MapPackage.CONDITION__VARIABLES:
                getVariables().clear();
                return;
        }
        eDynamicUnset(eFeature);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet(EStructuralFeature eFeature) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case MapPackage.CONDITION__LABEL:
                return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
            case MapPackage.CONDITION__EXPRESSION:
                return EXPRESSION_EDEFAULT == null ? expression != null : !EXPRESSION_EDEFAULT.equals(expression);
            case MapPackage.CONDITION__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case MapPackage.CONDITION__LABEL_X:
                return labelX != LABEL_X_EDEFAULT;
            case MapPackage.CONDITION__LABEL_Y:
                return labelY != LABEL_Y_EDEFAULT;
            case MapPackage.CONDITION__VARIABLES:
                return variables != null && !variables.isEmpty();
        }
        return eDynamicIsSet(eFeature);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (label: ");
        result.append(label);
        result.append(", expression: ");
        result.append(expression);
        result.append(", description: ");
        result.append(description);
        result.append(", labelX: ");
        result.append(labelX);
        result.append(", labelY: ");
        result.append(labelY);
        result.append(')');
        return result.toString();
    }

} //ConditionImpl
