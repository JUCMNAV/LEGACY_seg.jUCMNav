/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.performance.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.InternalEList;

import ucm.performance.Device;
import ucm.performance.DeviceKind;
import ucm.performance.PerformancePackage;

import urncore.impl.UCMmodelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Device</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.performance.impl.DeviceImpl#getDeviceKind <em>Device Kind</em>}</li>
 *   <li>{@link ucm.performance.impl.DeviceImpl#getOptime <em>Optime</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DeviceImpl extends UCMmodelElementImpl implements Device {
	/**
	 * The default value of the '{@link #getDeviceKind() <em>Device Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeviceKind()
	 * @generated
	 * @ordered
	 */
	protected static final DeviceKind DEVICE_KIND_EDEFAULT = DeviceKind.PROCESSOR_LITERAL;

	/**
	 * The cached value of the '{@link #getDeviceKind() <em>Device Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeviceKind()
	 * @generated
	 * @ordered
	 */
	protected DeviceKind deviceKind = DEVICE_KIND_EDEFAULT;

	/**
	 * The default value of the '{@link #getOptime() <em>Optime</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOptime()
	 * @generated
	 * @ordered
	 */
	protected static final String OPTIME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOptime() <em>Optime</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOptime()
	 * @generated
	 * @ordered
	 */
	protected String optime = OPTIME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DeviceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return PerformancePackage.eINSTANCE.getDevice();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeviceKind getDeviceKind() {
		return deviceKind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeviceKind(DeviceKind newDeviceKind) {
		DeviceKind oldDeviceKind = deviceKind;
		deviceKind = newDeviceKind == null ? DEVICE_KIND_EDEFAULT : newDeviceKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.DEVICE__DEVICE_KIND, oldDeviceKind, deviceKind));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOptime() {
		return optime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOptime(String newOptime) {
		String oldOptime = optime;
		optime = newOptime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.DEVICE__OPTIME, oldOptime, optime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case PerformancePackage.DEVICE__URN_LINKS:
					return ((InternalEList)getUrnLinks()).basicAdd(otherEnd, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case PerformancePackage.DEVICE__URN_LINKS:
					return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case PerformancePackage.DEVICE__URN_LINKS:
				return getUrnLinks();
			case PerformancePackage.DEVICE__ID:
				return getId();
			case PerformancePackage.DEVICE__NAME:
				return getName();
			case PerformancePackage.DEVICE__DESCRIPTION:
				return getDescription();
			case PerformancePackage.DEVICE__DEVICE_KIND:
				return getDeviceKind();
			case PerformancePackage.DEVICE__OPTIME:
				return getOptime();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case PerformancePackage.DEVICE__URN_LINKS:
				getUrnLinks().clear();
				getUrnLinks().addAll((Collection)newValue);
				return;
			case PerformancePackage.DEVICE__ID:
				setId((String)newValue);
				return;
			case PerformancePackage.DEVICE__NAME:
				setName((String)newValue);
				return;
			case PerformancePackage.DEVICE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case PerformancePackage.DEVICE__DEVICE_KIND:
				setDeviceKind((DeviceKind)newValue);
				return;
			case PerformancePackage.DEVICE__OPTIME:
				setOptime((String)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case PerformancePackage.DEVICE__URN_LINKS:
				getUrnLinks().clear();
				return;
			case PerformancePackage.DEVICE__ID:
				setId(ID_EDEFAULT);
				return;
			case PerformancePackage.DEVICE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PerformancePackage.DEVICE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case PerformancePackage.DEVICE__DEVICE_KIND:
				setDeviceKind(DEVICE_KIND_EDEFAULT);
				return;
			case PerformancePackage.DEVICE__OPTIME:
				setOptime(OPTIME_EDEFAULT);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case PerformancePackage.DEVICE__URN_LINKS:
				return urnLinks != null && !urnLinks.isEmpty();
			case PerformancePackage.DEVICE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case PerformancePackage.DEVICE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PerformancePackage.DEVICE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case PerformancePackage.DEVICE__DEVICE_KIND:
				return deviceKind != DEVICE_KIND_EDEFAULT;
			case PerformancePackage.DEVICE__OPTIME:
				return OPTIME_EDEFAULT == null ? optime != null : !OPTIME_EDEFAULT.equals(optime);
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (deviceKind: ");
		result.append(deviceKind);
		result.append(", optime: ");
		result.append(optime);
		result.append(')');
		return result.toString();
	}

} //DeviceImpl
