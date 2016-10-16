/**
 */
package urn.dyncontext.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import urn.dyncontext.DyncontextPackage;
import urn.dyncontext.PropertyChange;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Property Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link urn.dyncontext.impl.PropertyChangeImpl#getAffectedProperty <em>Affected Property</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class PropertyChangeImpl extends ChangeImpl implements PropertyChange {
	/**
	 * The default value of the '{@link #getAffectedProperty() <em>Affected Property</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAffectedProperty()
	 * @generated
	 * @ordered
	 */
	protected static final String AFFECTED_PROPERTY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAffectedProperty() <em>Affected Property</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAffectedProperty()
	 * @generated
	 * @ordered
	 */
	protected String affectedProperty = AFFECTED_PROPERTY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PropertyChangeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DyncontextPackage.Literals.PROPERTY_CHANGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAffectedProperty() {
		return affectedProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAffectedProperty(String newAffectedProperty) {
		String oldAffectedProperty = affectedProperty;
		affectedProperty = newAffectedProperty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DyncontextPackage.PROPERTY_CHANGE__AFFECTED_PROPERTY, oldAffectedProperty, affectedProperty));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DyncontextPackage.PROPERTY_CHANGE__AFFECTED_PROPERTY:
				return getAffectedProperty();
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
			case DyncontextPackage.PROPERTY_CHANGE__AFFECTED_PROPERTY:
				setAffectedProperty((String)newValue);
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
			case DyncontextPackage.PROPERTY_CHANGE__AFFECTED_PROPERTY:
				setAffectedProperty(AFFECTED_PROPERTY_EDEFAULT);
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
			case DyncontextPackage.PROPERTY_CHANGE__AFFECTED_PROPERTY:
				return AFFECTED_PROPERTY_EDEFAULT == null ? affectedProperty != null : !AFFECTED_PROPERTY_EDEFAULT.equals(affectedProperty);
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
		result.append(" (affectedProperty: ");
		result.append(affectedProperty);
		result.append(')');
		return result.toString();
	}

} //PropertyChangeImpl
