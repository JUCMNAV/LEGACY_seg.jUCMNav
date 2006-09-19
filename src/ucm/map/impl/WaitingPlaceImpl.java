/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import ucm.map.MapPackage;
import ucm.map.WaitingPlace;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Waiting Place</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.map.impl.WaitingPlaceImpl#getWaitType <em>Wait Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WaitingPlaceImpl extends PathNodeImpl implements WaitingPlace {
	/**
	 * The default value of the '{@link #getWaitType() <em>Wait Type</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getWaitType()
	 * @generated
	 * @ordered
	 */
    protected static final String WAIT_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWaitType() <em>Wait Type</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getWaitType()
	 * @generated
	 * @ordered
	 */
    protected String waitType = WAIT_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected WaitingPlaceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return MapPackage.Literals.WAITING_PLACE;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getWaitType() {
		return waitType;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setWaitType(String newWaitType) {
		String oldWaitType = waitType;
		waitType = newWaitType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.WAITING_PLACE__WAIT_TYPE, oldWaitType, waitType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MapPackage.WAITING_PLACE__WAIT_TYPE:
				return getWaitType();
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
			case MapPackage.WAITING_PLACE__WAIT_TYPE:
				setWaitType((String)newValue);
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
			case MapPackage.WAITING_PLACE__WAIT_TYPE:
				setWaitType(WAIT_TYPE_EDEFAULT);
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
			case MapPackage.WAITING_PLACE__WAIT_TYPE:
				return WAIT_TYPE_EDEFAULT == null ? waitType != null : !WAIT_TYPE_EDEFAULT.equals(waitType);
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
		result.append(" (waitType: ");
		result.append(waitType);
		result.append(')');
		return result.toString();
	}

} //WaitingPlaceImpl
