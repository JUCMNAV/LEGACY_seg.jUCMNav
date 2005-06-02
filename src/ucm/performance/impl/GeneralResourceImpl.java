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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import ucm.UCMspec;
import ucm.UcmPackage;

import ucm.performance.Demand;
import ucm.performance.GeneralResource;
import ucm.performance.PerformancePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>General Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.performance.impl.GeneralResourceImpl#getUcmspec <em>Ucmspec</em>}</li>
 *   <li>{@link ucm.performance.impl.GeneralResourceImpl#getDemands <em>Demands</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GeneralResourceImpl extends EObjectImpl implements GeneralResource {
    /**
     * The cached value of the '{@link #getDemands() <em>Demands</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getDemands()
     * @generated
     * @ordered
     */
	protected EList demands = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected GeneralResourceImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return PerformancePackage.eINSTANCE.getGeneralResource();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public UCMspec getUcmspec() {
        if (eContainerFeatureID != PerformancePackage.GENERAL_RESOURCE__UCMSPEC) return null;
        return (UCMspec)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setUcmspec(UCMspec newUcmspec) {
        if (newUcmspec != eContainer || (eContainerFeatureID != PerformancePackage.GENERAL_RESOURCE__UCMSPEC && newUcmspec != null)) {
            if (EcoreUtil.isAncestor(this, newUcmspec))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newUcmspec != null)
                msgs = ((InternalEObject)newUcmspec).eInverseAdd(this, UcmPackage.UC_MSPEC__RESOURCES, UCMspec.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newUcmspec, PerformancePackage.GENERAL_RESOURCE__UCMSPEC, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.GENERAL_RESOURCE__UCMSPEC, newUcmspec, newUcmspec));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getDemands() {
        if (demands == null) {
            demands = new EObjectWithInverseResolvingEList(Demand.class, this, PerformancePackage.GENERAL_RESOURCE__DEMANDS, PerformancePackage.DEMAND__RESOURCE);
        }
        return demands;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case PerformancePackage.GENERAL_RESOURCE__UCMSPEC:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, PerformancePackage.GENERAL_RESOURCE__UCMSPEC, msgs);
                case PerformancePackage.GENERAL_RESOURCE__DEMANDS:
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
                case PerformancePackage.GENERAL_RESOURCE__UCMSPEC:
                    return eBasicSetContainer(null, PerformancePackage.GENERAL_RESOURCE__UCMSPEC, msgs);
                case PerformancePackage.GENERAL_RESOURCE__DEMANDS:
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
                case PerformancePackage.GENERAL_RESOURCE__UCMSPEC:
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
            case PerformancePackage.GENERAL_RESOURCE__UCMSPEC:
                return getUcmspec();
            case PerformancePackage.GENERAL_RESOURCE__DEMANDS:
                return getDemands();
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
            case PerformancePackage.GENERAL_RESOURCE__UCMSPEC:
                setUcmspec((UCMspec)newValue);
                return;
            case PerformancePackage.GENERAL_RESOURCE__DEMANDS:
                getDemands().clear();
                getDemands().addAll((Collection)newValue);
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
            case PerformancePackage.GENERAL_RESOURCE__UCMSPEC:
                setUcmspec((UCMspec)null);
                return;
            case PerformancePackage.GENERAL_RESOURCE__DEMANDS:
                getDemands().clear();
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
            case PerformancePackage.GENERAL_RESOURCE__UCMSPEC:
                return getUcmspec() != null;
            case PerformancePackage.GENERAL_RESOURCE__DEMANDS:
                return demands != null && !demands.isEmpty();
        }
        return eDynamicIsSet(eFeature);
    }

} //GeneralResourceImpl
