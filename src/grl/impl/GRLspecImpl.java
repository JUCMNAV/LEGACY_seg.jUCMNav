/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.GRLspec;
import grl.GrlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import urn.URNspec;
import urn.UrnPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>GR Lspec</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.GRLspecImpl#getUrnspec <em>Urnspec</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GRLspecImpl extends EObjectImpl implements GRLspec {
    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected GRLspecImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return GrlPackage.eINSTANCE.getGRLspec();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public URNspec getUrnspec() {
        if (eContainerFeatureID != GrlPackage.GR_LSPEC__URNSPEC) return null;
        return (URNspec)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setUrnspec(URNspec newUrnspec) {
        if (newUrnspec != eContainer || (eContainerFeatureID != GrlPackage.GR_LSPEC__URNSPEC && newUrnspec != null)) {
            if (EcoreUtil.isAncestor(this, newUrnspec))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newUrnspec != null)
                msgs = ((InternalEObject)newUrnspec).eInverseAdd(this, UrnPackage.UR_NSPEC__GRLSPEC, URNspec.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newUrnspec, GrlPackage.GR_LSPEC__URNSPEC, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.GR_LSPEC__URNSPEC, newUrnspec, newUrnspec));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case GrlPackage.GR_LSPEC__URNSPEC:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, GrlPackage.GR_LSPEC__URNSPEC, msgs);
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
                case GrlPackage.GR_LSPEC__URNSPEC:
                    return eBasicSetContainer(null, GrlPackage.GR_LSPEC__URNSPEC, msgs);
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
                case GrlPackage.GR_LSPEC__URNSPEC:
                    return ((InternalEObject)eContainer).eInverseRemove(this, UrnPackage.UR_NSPEC__GRLSPEC, URNspec.class, msgs);
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
            case GrlPackage.GR_LSPEC__URNSPEC:
                return getUrnspec();
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
            case GrlPackage.GR_LSPEC__URNSPEC:
                setUrnspec((URNspec)newValue);
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
            case GrlPackage.GR_LSPEC__URNSPEC:
                setUrnspec((URNspec)null);
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
            case GrlPackage.GR_LSPEC__URNSPEC:
                return getUrnspec() != null;
        }
        return eDynamicIsSet(eFeature);
    }

} //GRLspecImpl
