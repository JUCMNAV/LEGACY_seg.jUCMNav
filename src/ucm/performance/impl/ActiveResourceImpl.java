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

import ucm.UCMspec;
import ucm.UcmPackage;

import ucm.performance.ActiveResource;
import ucm.performance.PerformancePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Active Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.performance.impl.ActiveResourceImpl#getOpTime <em>Op Time</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActiveResourceImpl extends GeneralResourceImpl implements ActiveResource {
	/**
	 * The default value of the '{@link #getOpTime() <em>Op Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOpTime()
	 * @generated
	 * @ordered
	 */
	protected static final double OP_TIME_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getOpTime() <em>Op Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOpTime()
	 * @generated
	 * @ordered
	 */
	protected double opTime = OP_TIME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActiveResourceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return PerformancePackage.eINSTANCE.getActiveResource();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getOpTime() {
		return opTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOpTime(double newOpTime) {
		double oldOpTime = opTime;
		opTime = newOpTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.ACTIVE_RESOURCE__OP_TIME, oldOpTime, opTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case PerformancePackage.ACTIVE_RESOURCE__UCMSPEC:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, PerformancePackage.ACTIVE_RESOURCE__UCMSPEC, msgs);
				case PerformancePackage.ACTIVE_RESOURCE__DEMANDS:
					return ((InternalEList)getDemands()).basicAdd(otherEnd, msgs);
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
				case PerformancePackage.ACTIVE_RESOURCE__UCMSPEC:
					return eBasicSetContainer(null, PerformancePackage.ACTIVE_RESOURCE__UCMSPEC, msgs);
				case PerformancePackage.ACTIVE_RESOURCE__DEMANDS:
					return ((InternalEList)getDemands()).basicRemove(otherEnd, msgs);
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
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		if (eContainerFeatureID >= 0) {
			switch (eContainerFeatureID) {
				case PerformancePackage.ACTIVE_RESOURCE__UCMSPEC:
					return ((InternalEObject)eContainer).eInverseRemove(this, UcmPackage.UC_MSPEC__RESOURCES, UCMspec.class, msgs);
				default:
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return ((InternalEObject)eContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case PerformancePackage.ACTIVE_RESOURCE__UCMSPEC:
				return getUcmspec();
			case PerformancePackage.ACTIVE_RESOURCE__DEMANDS:
				return getDemands();
			case PerformancePackage.ACTIVE_RESOURCE__OP_TIME:
				return new Double(getOpTime());
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
			case PerformancePackage.ACTIVE_RESOURCE__UCMSPEC:
				setUcmspec((UCMspec)newValue);
				return;
			case PerformancePackage.ACTIVE_RESOURCE__DEMANDS:
				getDemands().clear();
				getDemands().addAll((Collection)newValue);
				return;
			case PerformancePackage.ACTIVE_RESOURCE__OP_TIME:
				setOpTime(((Double)newValue).doubleValue());
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
			case PerformancePackage.ACTIVE_RESOURCE__UCMSPEC:
				setUcmspec((UCMspec)null);
				return;
			case PerformancePackage.ACTIVE_RESOURCE__DEMANDS:
				getDemands().clear();
				return;
			case PerformancePackage.ACTIVE_RESOURCE__OP_TIME:
				setOpTime(OP_TIME_EDEFAULT);
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
			case PerformancePackage.ACTIVE_RESOURCE__UCMSPEC:
				return getUcmspec() != null;
			case PerformancePackage.ACTIVE_RESOURCE__DEMANDS:
				return demands != null && !demands.isEmpty();
			case PerformancePackage.ACTIVE_RESOURCE__OP_TIME:
				return opTime != OP_TIME_EDEFAULT;
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
		result.append(" (opTime: ");
		result.append(opTime);
		result.append(')');
		return result.toString();
	}

} //ActiveResourceImpl
