/**
 */
package org.etsi.mts.tdl.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.etsi.mts.tdl.TdlPackage;
import org.etsi.mts.tdl.VerdictAssignment;
import org.etsi.mts.tdl.VerdictType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Verdict Assignment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.impl.VerdictAssignmentImpl#getVerdictType <em>Verdict Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VerdictAssignmentImpl extends AtomicBehaviourImpl implements VerdictAssignment {
	/**
	 * The cached value of the '{@link #getVerdictType() <em>Verdict Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVerdictType()
	 * @generated
	 * @ordered
	 */
	protected VerdictType verdictType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VerdictAssignmentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TdlPackage.Literals.VERDICT_ASSIGNMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VerdictType getVerdictType() {
		if (verdictType != null && verdictType.eIsProxy()) {
			InternalEObject oldVerdictType = (InternalEObject)verdictType;
			verdictType = (VerdictType)eResolveProxy(oldVerdictType);
			if (verdictType != oldVerdictType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TdlPackage.VERDICT_ASSIGNMENT__VERDICT_TYPE, oldVerdictType, verdictType));
			}
		}
		return verdictType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VerdictType basicGetVerdictType() {
		return verdictType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVerdictType(VerdictType newVerdictType) {
		VerdictType oldVerdictType = verdictType;
		verdictType = newVerdictType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TdlPackage.VERDICT_ASSIGNMENT__VERDICT_TYPE, oldVerdictType, verdictType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TdlPackage.VERDICT_ASSIGNMENT__VERDICT_TYPE:
				if (resolve) return getVerdictType();
				return basicGetVerdictType();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TdlPackage.VERDICT_ASSIGNMENT__VERDICT_TYPE:
				setVerdictType((VerdictType)newValue);
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
			case TdlPackage.VERDICT_ASSIGNMENT__VERDICT_TYPE:
				setVerdictType((VerdictType)null);
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
			case TdlPackage.VERDICT_ASSIGNMENT__VERDICT_TYPE:
				return verdictType != null;
		}
		return super.eIsSet(featureID);
	}

} //VerdictAssignmentImpl
