/**
 */
package urn.dyncontext.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import urn.dyncontext.BooleanChange;
import urn.dyncontext.DyncontextPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Boolean Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link urn.dyncontext.impl.BooleanChangeImpl#isNewValue <em>New Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BooleanChangeImpl extends PropertyChangeImpl implements BooleanChange {
	/**
	 * The default value of the '{@link #isNewValue() <em>New Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNewValue()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NEW_VALUE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isNewValue() <em>New Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNewValue()
	 * @generated
	 * @ordered
	 */
	protected boolean newValue = NEW_VALUE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BooleanChangeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DyncontextPackage.Literals.BOOLEAN_CHANGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isNewValue() {
		return newValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNewValue(boolean newNewValue) {
		boolean oldNewValue = newValue;
		newValue = newNewValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DyncontextPackage.BOOLEAN_CHANGE__NEW_VALUE, oldNewValue, newValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case DyncontextPackage.BOOLEAN_CHANGE__NEW_VALUE:
			return isNewValue() ? Boolean.TRUE : Boolean.FALSE;
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
		case DyncontextPackage.BOOLEAN_CHANGE__NEW_VALUE:
			setNewValue(((Boolean)newValue).booleanValue());
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
		case DyncontextPackage.BOOLEAN_CHANGE__NEW_VALUE:
			setNewValue(NEW_VALUE_EDEFAULT);
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
		case DyncontextPackage.BOOLEAN_CHANGE__NEW_VALUE:
			return newValue != NEW_VALUE_EDEFAULT;
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
		result.append(" (newValue: ");
		result.append(newValue);
		result.append(')');
		return result.toString();
	}

} //BooleanChangeImpl
