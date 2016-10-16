/**
 */
package urn.dyncontext.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import urn.dyncontext.DyncontextPackage;
import urn.dyncontext.NumericChange;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Numeric Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link urn.dyncontext.impl.NumericChangeImpl#isSufficientOnceAchieved <em>Sufficient Once Achieved</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class NumericChangeImpl extends PropertyChangeImpl implements NumericChange {
	/**
	 * The default value of the '{@link #isSufficientOnceAchieved() <em>Sufficient Once Achieved</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSufficientOnceAchieved()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SUFFICIENT_ONCE_ACHIEVED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSufficientOnceAchieved() <em>Sufficient Once Achieved</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSufficientOnceAchieved()
	 * @generated
	 * @ordered
	 */
	protected boolean sufficientOnceAchieved = SUFFICIENT_ONCE_ACHIEVED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NumericChangeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DyncontextPackage.Literals.NUMERIC_CHANGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSufficientOnceAchieved() {
		return sufficientOnceAchieved;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSufficientOnceAchieved(boolean newSufficientOnceAchieved) {
		boolean oldSufficientOnceAchieved = sufficientOnceAchieved;
		sufficientOnceAchieved = newSufficientOnceAchieved;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DyncontextPackage.NUMERIC_CHANGE__SUFFICIENT_ONCE_ACHIEVED, oldSufficientOnceAchieved, sufficientOnceAchieved));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DyncontextPackage.NUMERIC_CHANGE__SUFFICIENT_ONCE_ACHIEVED:
				return isSufficientOnceAchieved() ? Boolean.TRUE : Boolean.FALSE;
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
			case DyncontextPackage.NUMERIC_CHANGE__SUFFICIENT_ONCE_ACHIEVED:
				setSufficientOnceAchieved(((Boolean)newValue).booleanValue());
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
			case DyncontextPackage.NUMERIC_CHANGE__SUFFICIENT_ONCE_ACHIEVED:
				setSufficientOnceAchieved(SUFFICIENT_ONCE_ACHIEVED_EDEFAULT);
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
			case DyncontextPackage.NUMERIC_CHANGE__SUFFICIENT_ONCE_ACHIEVED:
				return sufficientOnceAchieved != SUFFICIENT_ONCE_ACHIEVED_EDEFAULT;
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
		result.append(" (sufficientOnceAchieved: ");
		result.append(sufficientOnceAchieved);
		result.append(')');
		return result.toString();
	}

} //NumericChangeImpl
