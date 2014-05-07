/**
 */
package org.etsi.mts.tdl.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.etsi.mts.tdl.AtomicBehaviour;
import org.etsi.mts.tdl.TdlPackage;
import org.etsi.mts.tdl.TimeConstraint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Atomic Behaviour</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.impl.AtomicBehaviourImpl#getTimeConstraints <em>Time Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AtomicBehaviourImpl extends BehaviourImpl implements AtomicBehaviour {
	/**
	 * The cached value of the '{@link #getTimeConstraints() <em>Time Constraint</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeConstraints()
	 * @generated
	 * @ordered
	 */
	protected EList<TimeConstraint> timeConstraints;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AtomicBehaviourImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TdlPackage.Literals.ATOMIC_BEHAVIOUR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TimeConstraint> getTimeConstraints() {
		if (timeConstraints == null) {
			timeConstraints = new EObjectWithInverseResolvingEList.ManyInverse<TimeConstraint>(TimeConstraint.class, this, TdlPackage.ATOMIC_BEHAVIOUR__TIME_CONSTRAINT, TdlPackage.TIME_CONSTRAINT__ATOMIC_BEHAVIOUR);
		}
		return timeConstraints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TdlPackage.ATOMIC_BEHAVIOUR__TIME_CONSTRAINT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTimeConstraints()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TdlPackage.ATOMIC_BEHAVIOUR__TIME_CONSTRAINT:
				return ((InternalEList<?>)getTimeConstraints()).basicRemove(otherEnd, msgs);
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
			case TdlPackage.ATOMIC_BEHAVIOUR__TIME_CONSTRAINT:
				return getTimeConstraints();
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
			case TdlPackage.ATOMIC_BEHAVIOUR__TIME_CONSTRAINT:
				getTimeConstraints().clear();
				getTimeConstraints().addAll((Collection<? extends TimeConstraint>)newValue);
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
			case TdlPackage.ATOMIC_BEHAVIOUR__TIME_CONSTRAINT:
				getTimeConstraints().clear();
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
			case TdlPackage.ATOMIC_BEHAVIOUR__TIME_CONSTRAINT:
				return timeConstraints != null && !timeConstraints.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //AtomicBehaviourImpl
