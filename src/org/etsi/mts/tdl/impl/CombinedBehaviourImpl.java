/**
 */
package org.etsi.mts.tdl.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.etsi.mts.tdl.CombinedBehaviour;
import org.etsi.mts.tdl.ExceptionalBehaviour;
import org.etsi.mts.tdl.PeriodicBehaviour;
import org.etsi.mts.tdl.TdlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Combined Behaviour</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.impl.CombinedBehaviourImpl#getPeriodics <em>Periodic</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.impl.CombinedBehaviourImpl#getExceptionals <em>Exceptional</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class CombinedBehaviourImpl extends BehaviourImpl implements CombinedBehaviour {
	/**
	 * The cached value of the '{@link #getPeriodics() <em>Periodic</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPeriodics()
	 * @generated
	 * @ordered
	 */
	protected EList<PeriodicBehaviour> periodics;

	/**
	 * The cached value of the '{@link #getExceptionals() <em>Exceptional</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExceptionals()
	 * @generated
	 * @ordered
	 */
	protected EList<ExceptionalBehaviour> exceptionals;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CombinedBehaviourImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TdlPackage.Literals.COMBINED_BEHAVIOUR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PeriodicBehaviour> getPeriodics() {
		if (periodics == null) {
			periodics = new EObjectContainmentEList<PeriodicBehaviour>(PeriodicBehaviour.class, this, TdlPackage.COMBINED_BEHAVIOUR__PERIODIC);
		}
		return periodics;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PeriodicBehaviour createPeriodic() {
		PeriodicBehaviour newPeriodic = (PeriodicBehaviour) create(TdlPackage.Literals.PERIODIC_BEHAVIOUR);
		getPeriodics().add(newPeriodic);
		return newPeriodic;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExceptionalBehaviour> getExceptionals() {
		if (exceptionals == null) {
			exceptionals = new EObjectContainmentEList<ExceptionalBehaviour>(ExceptionalBehaviour.class, this, TdlPackage.COMBINED_BEHAVIOUR__EXCEPTIONAL);
		}
		return exceptionals;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExceptionalBehaviour createExceptional(EClass eClass) {
		ExceptionalBehaviour newExceptional = (ExceptionalBehaviour) create(eClass);
		getExceptionals().add(newExceptional);
		return newExceptional;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TdlPackage.COMBINED_BEHAVIOUR__PERIODIC:
				return ((InternalEList<?>)getPeriodics()).basicRemove(otherEnd, msgs);
			case TdlPackage.COMBINED_BEHAVIOUR__EXCEPTIONAL:
				return ((InternalEList<?>)getExceptionals()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TdlPackage.COMBINED_BEHAVIOUR__PERIODIC:
				return getPeriodics();
			case TdlPackage.COMBINED_BEHAVIOUR__EXCEPTIONAL:
				return getExceptionals();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TdlPackage.COMBINED_BEHAVIOUR__PERIODIC:
				getPeriodics().clear();
				getPeriodics().addAll((Collection<? extends PeriodicBehaviour>)newValue);
				return;
			case TdlPackage.COMBINED_BEHAVIOUR__EXCEPTIONAL:
				getExceptionals().clear();
				getExceptionals().addAll((Collection<? extends ExceptionalBehaviour>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case TdlPackage.COMBINED_BEHAVIOUR__PERIODIC:
				getPeriodics().clear();
				return;
			case TdlPackage.COMBINED_BEHAVIOUR__EXCEPTIONAL:
				getExceptionals().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TdlPackage.COMBINED_BEHAVIOUR__PERIODIC:
				return periodics != null && !periodics.isEmpty();
			case TdlPackage.COMBINED_BEHAVIOUR__EXCEPTIONAL:
				return exceptionals != null && !exceptionals.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //CombinedBehaviourImpl
