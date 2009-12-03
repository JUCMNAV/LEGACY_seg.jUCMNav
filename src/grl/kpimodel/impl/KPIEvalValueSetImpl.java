/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.kpimodel.impl;

import grl.Evaluation;
import grl.GrlPackage;

import grl.kpimodel.KPIEvalValueSet;
import grl.kpimodel.KpimodelPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KPI Eval Value Set</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.kpimodel.impl.KPIEvalValueSetImpl#getTargetValue <em>Target Value</em>}</li>
 *   <li>{@link grl.kpimodel.impl.KPIEvalValueSetImpl#getThresholdValue <em>Threshold Value</em>}</li>
 *   <li>{@link grl.kpimodel.impl.KPIEvalValueSetImpl#getWorstValue <em>Worst Value</em>}</li>
 *   <li>{@link grl.kpimodel.impl.KPIEvalValueSetImpl#getEvaluationValue <em>Evaluation Value</em>}</li>
 *   <li>{@link grl.kpimodel.impl.KPIEvalValueSetImpl#getUnit <em>Unit</em>}</li>
 *   <li>{@link grl.kpimodel.impl.KPIEvalValueSetImpl#getEval <em>Eval</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KPIEvalValueSetImpl extends EObjectImpl implements KPIEvalValueSet {
    /**
     * The default value of the '{@link #getTargetValue() <em>Target Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTargetValue()
     * @generated
     * @ordered
     */
    protected static final double TARGET_VALUE_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getTargetValue() <em>Target Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTargetValue()
     * @generated
     * @ordered
     */
    protected double targetValue = TARGET_VALUE_EDEFAULT;

    /**
     * The default value of the '{@link #getThresholdValue() <em>Threshold Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getThresholdValue()
     * @generated
     * @ordered
     */
    protected static final double THRESHOLD_VALUE_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getThresholdValue() <em>Threshold Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getThresholdValue()
     * @generated
     * @ordered
     */
    protected double thresholdValue = THRESHOLD_VALUE_EDEFAULT;

    /**
     * The default value of the '{@link #getWorstValue() <em>Worst Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWorstValue()
     * @generated
     * @ordered
     */
    protected static final double WORST_VALUE_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getWorstValue() <em>Worst Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWorstValue()
     * @generated
     * @ordered
     */
    protected double worstValue = WORST_VALUE_EDEFAULT;

    /**
     * The default value of the '{@link #getEvaluationValue() <em>Evaluation Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEvaluationValue()
     * @generated
     * @ordered
     */
    protected static final double EVALUATION_VALUE_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getEvaluationValue() <em>Evaluation Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEvaluationValue()
     * @generated
     * @ordered
     */
    protected double evaluationValue = EVALUATION_VALUE_EDEFAULT;

    /**
     * The default value of the '{@link #getUnit() <em>Unit</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUnit()
     * @generated
     * @ordered
     */
    protected static final String UNIT_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getUnit() <em>Unit</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUnit()
     * @generated
     * @ordered
     */
    protected String unit = UNIT_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected KPIEvalValueSetImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return KpimodelPackage.Literals.KPI_EVAL_VALUE_SET;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public double getTargetValue() {
        return targetValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTargetValue(double newTargetValue) {
        double oldTargetValue = targetValue;
        targetValue = newTargetValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KpimodelPackage.KPI_EVAL_VALUE_SET__TARGET_VALUE, oldTargetValue, targetValue));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public double getThresholdValue() {
        return thresholdValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setThresholdValue(double newThresholdValue) {
        double oldThresholdValue = thresholdValue;
        thresholdValue = newThresholdValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KpimodelPackage.KPI_EVAL_VALUE_SET__THRESHOLD_VALUE, oldThresholdValue, thresholdValue));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public double getWorstValue() {
        return worstValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setWorstValue(double newWorstValue) {
        double oldWorstValue = worstValue;
        worstValue = newWorstValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KpimodelPackage.KPI_EVAL_VALUE_SET__WORST_VALUE, oldWorstValue, worstValue));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public double getEvaluationValue() {
        return evaluationValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEvaluationValue(double newEvaluationValue) {
        double oldEvaluationValue = evaluationValue;
        evaluationValue = newEvaluationValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KpimodelPackage.KPI_EVAL_VALUE_SET__EVALUATION_VALUE, oldEvaluationValue, evaluationValue));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getUnit() {
        return unit;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUnit(String newUnit) {
        String oldUnit = unit;
        unit = newUnit;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KpimodelPackage.KPI_EVAL_VALUE_SET__UNIT, oldUnit, unit));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Evaluation getEval() {
        if (eContainerFeatureID() != KpimodelPackage.KPI_EVAL_VALUE_SET__EVAL) return null;
        return (Evaluation)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetEval(Evaluation newEval, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newEval, KpimodelPackage.KPI_EVAL_VALUE_SET__EVAL, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEval(Evaluation newEval) {
        if (newEval != eInternalContainer() || (eContainerFeatureID() != KpimodelPackage.KPI_EVAL_VALUE_SET__EVAL && newEval != null)) {
            if (EcoreUtil.isAncestor(this, newEval))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newEval != null)
                msgs = ((InternalEObject)newEval).eInverseAdd(this, GrlPackage.EVALUATION__KPI_EVAL_VALUE_SET, Evaluation.class, msgs);
            msgs = basicSetEval(newEval, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, KpimodelPackage.KPI_EVAL_VALUE_SET__EVAL, newEval, newEval));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case KpimodelPackage.KPI_EVAL_VALUE_SET__EVAL:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetEval((Evaluation)otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case KpimodelPackage.KPI_EVAL_VALUE_SET__EVAL:
                return basicSetEval(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID()) {
            case KpimodelPackage.KPI_EVAL_VALUE_SET__EVAL:
                return eInternalContainer().eInverseRemove(this, GrlPackage.EVALUATION__KPI_EVAL_VALUE_SET, Evaluation.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case KpimodelPackage.KPI_EVAL_VALUE_SET__TARGET_VALUE:
                return new Double(getTargetValue());
            case KpimodelPackage.KPI_EVAL_VALUE_SET__THRESHOLD_VALUE:
                return new Double(getThresholdValue());
            case KpimodelPackage.KPI_EVAL_VALUE_SET__WORST_VALUE:
                return new Double(getWorstValue());
            case KpimodelPackage.KPI_EVAL_VALUE_SET__EVALUATION_VALUE:
                return new Double(getEvaluationValue());
            case KpimodelPackage.KPI_EVAL_VALUE_SET__UNIT:
                return getUnit();
            case KpimodelPackage.KPI_EVAL_VALUE_SET__EVAL:
                return getEval();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case KpimodelPackage.KPI_EVAL_VALUE_SET__TARGET_VALUE:
                setTargetValue(((Double)newValue).doubleValue());
                return;
            case KpimodelPackage.KPI_EVAL_VALUE_SET__THRESHOLD_VALUE:
                setThresholdValue(((Double)newValue).doubleValue());
                return;
            case KpimodelPackage.KPI_EVAL_VALUE_SET__WORST_VALUE:
                setWorstValue(((Double)newValue).doubleValue());
                return;
            case KpimodelPackage.KPI_EVAL_VALUE_SET__EVALUATION_VALUE:
                setEvaluationValue(((Double)newValue).doubleValue());
                return;
            case KpimodelPackage.KPI_EVAL_VALUE_SET__UNIT:
                setUnit((String)newValue);
                return;
            case KpimodelPackage.KPI_EVAL_VALUE_SET__EVAL:
                setEval((Evaluation)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset(int featureID) {
        switch (featureID) {
            case KpimodelPackage.KPI_EVAL_VALUE_SET__TARGET_VALUE:
                setTargetValue(TARGET_VALUE_EDEFAULT);
                return;
            case KpimodelPackage.KPI_EVAL_VALUE_SET__THRESHOLD_VALUE:
                setThresholdValue(THRESHOLD_VALUE_EDEFAULT);
                return;
            case KpimodelPackage.KPI_EVAL_VALUE_SET__WORST_VALUE:
                setWorstValue(WORST_VALUE_EDEFAULT);
                return;
            case KpimodelPackage.KPI_EVAL_VALUE_SET__EVALUATION_VALUE:
                setEvaluationValue(EVALUATION_VALUE_EDEFAULT);
                return;
            case KpimodelPackage.KPI_EVAL_VALUE_SET__UNIT:
                setUnit(UNIT_EDEFAULT);
                return;
            case KpimodelPackage.KPI_EVAL_VALUE_SET__EVAL:
                setEval((Evaluation)null);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case KpimodelPackage.KPI_EVAL_VALUE_SET__TARGET_VALUE:
                return targetValue != TARGET_VALUE_EDEFAULT;
            case KpimodelPackage.KPI_EVAL_VALUE_SET__THRESHOLD_VALUE:
                return thresholdValue != THRESHOLD_VALUE_EDEFAULT;
            case KpimodelPackage.KPI_EVAL_VALUE_SET__WORST_VALUE:
                return worstValue != WORST_VALUE_EDEFAULT;
            case KpimodelPackage.KPI_EVAL_VALUE_SET__EVALUATION_VALUE:
                return evaluationValue != EVALUATION_VALUE_EDEFAULT;
            case KpimodelPackage.KPI_EVAL_VALUE_SET__UNIT:
                return UNIT_EDEFAULT == null ? unit != null : !UNIT_EDEFAULT.equals(unit);
            case KpimodelPackage.KPI_EVAL_VALUE_SET__EVAL:
                return getEval() != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (targetValue: ");
        result.append(targetValue);
        result.append(", thresholdValue: ");
        result.append(thresholdValue);
        result.append(", worstValue: ");
        result.append(worstValue);
        result.append(", evaluationValue: ");
        result.append(evaluationValue);
        result.append(", unit: ");
        result.append(unit);
        result.append(')');
        return result.toString();
    }

} //KPIEvalValueSetImpl