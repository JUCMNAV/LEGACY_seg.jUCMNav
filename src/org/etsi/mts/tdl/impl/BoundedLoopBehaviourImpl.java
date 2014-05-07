/**
 */
package org.etsi.mts.tdl.impl;

import java.util.Map;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.etsi.mts.tdl.BoundedLoopBehaviour;
import org.etsi.mts.tdl.TdlPackage;

import org.etsi.mts.tdl.util.TdlValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Bounded Loop Behaviour</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.impl.BoundedLoopBehaviourImpl#getNumIteration <em>Num Iteration</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BoundedLoopBehaviourImpl extends SingleCombinedBehaviourImpl implements BoundedLoopBehaviour {
	/**
	 * The default value of the '{@link #getNumIteration() <em>Num Iteration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumIteration()
	 * @generated
	 * @ordered
	 */
	protected static final int NUM_ITERATION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getNumIteration() <em>Num Iteration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumIteration()
	 * @generated
	 * @ordered
	 */
	protected int numIteration = NUM_ITERATION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BoundedLoopBehaviourImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TdlPackage.Literals.BOUNDED_LOOP_BEHAVIOUR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNumIteration() {
		return numIteration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumIteration(int newNumIteration) {
		int oldNumIteration = numIteration;
		numIteration = newNumIteration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TdlPackage.BOUNDED_LOOP_BEHAVIOUR__NUM_ITERATION, oldNumIteration, numIteration));
	}

	/**
	 * The cached validation expression for the '{@link #invBoundedLoopmustnothaveaguard(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map) <em>Inv Bounded Loopmustnothaveaguard</em>}' invariant operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #invBoundedLoopmustnothaveaguard(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 * @generated
	 * @ordered
	 */
	protected static final String INV_BOUNDED_LOOPMUSTNOTHAVEAGUARD_DIAGNOSTIC_CHAIN_MAP__EEXPRESSION = "self.block.guard.oclIsUndefined()";

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean invBoundedLoopmustnothaveaguard(DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			TdlValidator.validate
				(TdlPackage.Literals.BOUNDED_LOOP_BEHAVIOUR,
				 this,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL",
				 TdlPackage.Literals.BOUNDED_LOOP_BEHAVIOUR.getEOperations().get(0),
				 INV_BOUNDED_LOOPMUSTNOTHAVEAGUARD_DIAGNOSTIC_CHAIN_MAP__EEXPRESSION,
				 Diagnostic.ERROR,
				 TdlValidator.DIAGNOSTIC_SOURCE,
				 TdlValidator.BOUNDED_LOOP_BEHAVIOUR__INV_BOUNDED_LOOPMUSTNOTHAVEAGUARD);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TdlPackage.BOUNDED_LOOP_BEHAVIOUR__NUM_ITERATION:
				return getNumIteration();
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
			case TdlPackage.BOUNDED_LOOP_BEHAVIOUR__NUM_ITERATION:
				setNumIteration((Integer)newValue);
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
			case TdlPackage.BOUNDED_LOOP_BEHAVIOUR__NUM_ITERATION:
				setNumIteration(NUM_ITERATION_EDEFAULT);
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
			case TdlPackage.BOUNDED_LOOP_BEHAVIOUR__NUM_ITERATION:
				return numIteration != NUM_ITERATION_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (numIteration: ");
		result.append(numIteration);
		result.append(')');
		return result.toString();
	}

} //BoundedLoopBehaviourImpl
