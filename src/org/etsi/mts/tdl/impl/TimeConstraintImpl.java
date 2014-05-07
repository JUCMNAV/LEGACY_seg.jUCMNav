/**
 */
package org.etsi.mts.tdl.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.etsi.mts.tdl.AtomicBehaviour;
import org.etsi.mts.tdl.TdlPackage;
import org.etsi.mts.tdl.TimeConstraint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Time Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.impl.TimeConstraintImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.impl.TimeConstraintImpl#getAtomicBehaviours <em>Atomic Behaviour</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TimeConstraintImpl extends ElementImpl implements TimeConstraint {
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
	 * The cached value of the '{@link #getAtomicBehaviours() <em>Atomic Behaviour</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAtomicBehaviours()
	 * @generated
	 * @ordered
	 */
	protected EList<AtomicBehaviour> atomicBehaviours;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TimeConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TdlPackage.Literals.TIME_CONSTRAINT;
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
			eNotify(new ENotificationImpl(this, Notification.SET, TdlPackage.TIME_CONSTRAINT__EXPRESSION, oldExpression, expression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AtomicBehaviour> getAtomicBehaviours() {
		if (atomicBehaviours == null) {
			atomicBehaviours = new EObjectWithInverseResolvingEList.ManyInverse<AtomicBehaviour>(AtomicBehaviour.class, this, TdlPackage.TIME_CONSTRAINT__ATOMIC_BEHAVIOUR, TdlPackage.ATOMIC_BEHAVIOUR__TIME_CONSTRAINT);
		}
		return atomicBehaviours;
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
			case TdlPackage.TIME_CONSTRAINT__ATOMIC_BEHAVIOUR:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAtomicBehaviours()).basicAdd(otherEnd, msgs);
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
			case TdlPackage.TIME_CONSTRAINT__ATOMIC_BEHAVIOUR:
				return ((InternalEList<?>)getAtomicBehaviours()).basicRemove(otherEnd, msgs);
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
			case TdlPackage.TIME_CONSTRAINT__EXPRESSION:
				return getExpression();
			case TdlPackage.TIME_CONSTRAINT__ATOMIC_BEHAVIOUR:
				return getAtomicBehaviours();
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
			case TdlPackage.TIME_CONSTRAINT__EXPRESSION:
				setExpression((String)newValue);
				return;
			case TdlPackage.TIME_CONSTRAINT__ATOMIC_BEHAVIOUR:
				getAtomicBehaviours().clear();
				getAtomicBehaviours().addAll((Collection<? extends AtomicBehaviour>)newValue);
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
			case TdlPackage.TIME_CONSTRAINT__EXPRESSION:
				setExpression(EXPRESSION_EDEFAULT);
				return;
			case TdlPackage.TIME_CONSTRAINT__ATOMIC_BEHAVIOUR:
				getAtomicBehaviours().clear();
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
			case TdlPackage.TIME_CONSTRAINT__EXPRESSION:
				return EXPRESSION_EDEFAULT == null ? expression != null : !EXPRESSION_EDEFAULT.equals(expression);
			case TdlPackage.TIME_CONSTRAINT__ATOMIC_BEHAVIOUR:
				return atomicBehaviours != null && !atomicBehaviours.isEmpty();
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
		result.append(" (expression: ");
		result.append(expression);
		result.append(')');
		return result.toString();
	}

} //TimeConstraintImpl
