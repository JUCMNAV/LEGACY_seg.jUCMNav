/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.kpimodel.impl;

import grl.Evaluation;
import grl.GrlPackage;
import grl.kpimodel.KPINewEvalValue;
import grl.kpimodel.KpimodelPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KPI New Eval Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.kpimodel.impl.KPINewEvalValueImpl#getEvaluationValue <em>Evaluation Value</em>}</li>
 *   <li>{@link grl.kpimodel.impl.KPINewEvalValueImpl#getEval <em>Eval</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KPINewEvalValueImpl extends MinimalEObjectImpl.Container implements KPINewEvalValue {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected KPINewEvalValueImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return KpimodelPackage.Literals.KPI_NEW_EVAL_VALUE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, KpimodelPackage.KPI_NEW_EVAL_VALUE__EVALUATION_VALUE, oldEvaluationValue, evaluationValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Evaluation getEval() {
		if (eContainerFeatureID() != KpimodelPackage.KPI_NEW_EVAL_VALUE__EVAL) return null;
		return (Evaluation)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEval(Evaluation newEval, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newEval, KpimodelPackage.KPI_NEW_EVAL_VALUE__EVAL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEval(Evaluation newEval) {
		if (newEval != eInternalContainer() || (eContainerFeatureID() != KpimodelPackage.KPI_NEW_EVAL_VALUE__EVAL && newEval != null)) {
			if (EcoreUtil.isAncestor(this, newEval))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newEval != null)
				msgs = ((InternalEObject)newEval).eInverseAdd(this, GrlPackage.EVALUATION__KPI_NEW_EVAL_VALUE, Evaluation.class, msgs);
			msgs = basicSetEval(newEval, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KpimodelPackage.KPI_NEW_EVAL_VALUE__EVAL, newEval, newEval));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case KpimodelPackage.KPI_NEW_EVAL_VALUE__EVAL:
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
			case KpimodelPackage.KPI_NEW_EVAL_VALUE__EVAL:
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
			case KpimodelPackage.KPI_NEW_EVAL_VALUE__EVAL:
				return eInternalContainer().eInverseRemove(this, GrlPackage.EVALUATION__KPI_NEW_EVAL_VALUE, Evaluation.class, msgs);
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
			case KpimodelPackage.KPI_NEW_EVAL_VALUE__EVALUATION_VALUE:
				return new Double(getEvaluationValue());
			case KpimodelPackage.KPI_NEW_EVAL_VALUE__EVAL:
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
			case KpimodelPackage.KPI_NEW_EVAL_VALUE__EVALUATION_VALUE:
				setEvaluationValue(((Double)newValue).doubleValue());
				return;
			case KpimodelPackage.KPI_NEW_EVAL_VALUE__EVAL:
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
			case KpimodelPackage.KPI_NEW_EVAL_VALUE__EVALUATION_VALUE:
				setEvaluationValue(EVALUATION_VALUE_EDEFAULT);
				return;
			case KpimodelPackage.KPI_NEW_EVAL_VALUE__EVAL:
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
			case KpimodelPackage.KPI_NEW_EVAL_VALUE__EVALUATION_VALUE:
				return evaluationValue != EVALUATION_VALUE_EDEFAULT;
			case KpimodelPackage.KPI_NEW_EVAL_VALUE__EVAL:
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
		result.append(" (evaluationValue: ");
		result.append(evaluationValue);
		result.append(')');
		return result.toString();
	}

} //KPINewEvalValueImpl
