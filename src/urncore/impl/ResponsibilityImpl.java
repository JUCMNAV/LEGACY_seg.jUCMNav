/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import ucm.map.MapPackage;
import ucm.map.RespRef;

import ucm.performance.Demand;

import urncore.Responsibility;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Responsibility</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link urncore.impl.ResponsibilityImpl#getDemands <em>Demands</em>}</li>
 *   <li>{@link urncore.impl.ResponsibilityImpl#getRespRefs <em>Resp Refs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResponsibilityImpl extends UCMmodelElementImpl implements Responsibility {
    /**
     * The cached value of the '{@link #getDemands() <em>Demands</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDemands()
     * @generated
     * @ordered
     */
    protected EList demands = null;

    /**
     * The cached value of the '{@link #getRespRefs() <em>Resp Refs</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRespRefs()
     * @generated
     * @ordered
     */
    protected EList respRefs = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ResponsibilityImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return UrncorePackage.eINSTANCE.getResponsibility();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getDemands() {
        if (demands == null) {
            demands = new EObjectContainmentEList(Demand.class, this, UrncorePackage.RESPONSIBILITY__DEMANDS);
        }
        return demands;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getRespRefs() {
        if (respRefs == null) {
            respRefs = new EObjectWithInverseResolvingEList(RespRef.class, this, UrncorePackage.RESPONSIBILITY__RESP_REFS, MapPackage.RESP_REF__RESP_DEF);
        }
        return respRefs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case UrncorePackage.RESPONSIBILITY__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicAdd(otherEnd, msgs);
                case UrncorePackage.RESPONSIBILITY__RESP_REFS:
                    return ((InternalEList)getRespRefs()).basicAdd(otherEnd, msgs);
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
                case UrncorePackage.RESPONSIBILITY__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
                case UrncorePackage.RESPONSIBILITY__DEMANDS:
                    return ((InternalEList)getDemands()).basicRemove(otherEnd, msgs);
                case UrncorePackage.RESPONSIBILITY__RESP_REFS:
                    return ((InternalEList)getRespRefs()).basicRemove(otherEnd, msgs);
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
            case UrncorePackage.RESPONSIBILITY__URN_LINKS:
                return getUrnLinks();
            case UrncorePackage.RESPONSIBILITY__ID:
                return getId();
            case UrncorePackage.RESPONSIBILITY__NAME:
                return getName();
            case UrncorePackage.RESPONSIBILITY__DESCRIPTION:
                return getDescription();
            case UrncorePackage.RESPONSIBILITY__DEMANDS:
                return getDemands();
            case UrncorePackage.RESPONSIBILITY__RESP_REFS:
                return getRespRefs();
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
            case UrncorePackage.RESPONSIBILITY__URN_LINKS:
                getUrnLinks().clear();
                getUrnLinks().addAll((Collection)newValue);
                return;
            case UrncorePackage.RESPONSIBILITY__ID:
                setId((String)newValue);
                return;
            case UrncorePackage.RESPONSIBILITY__NAME:
                setName((String)newValue);
                return;
            case UrncorePackage.RESPONSIBILITY__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case UrncorePackage.RESPONSIBILITY__DEMANDS:
                getDemands().clear();
                getDemands().addAll((Collection)newValue);
                return;
            case UrncorePackage.RESPONSIBILITY__RESP_REFS:
                getRespRefs().clear();
                getRespRefs().addAll((Collection)newValue);
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
            case UrncorePackage.RESPONSIBILITY__URN_LINKS:
                getUrnLinks().clear();
                return;
            case UrncorePackage.RESPONSIBILITY__ID:
                setId(ID_EDEFAULT);
                return;
            case UrncorePackage.RESPONSIBILITY__NAME:
                setName(NAME_EDEFAULT);
                return;
            case UrncorePackage.RESPONSIBILITY__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case UrncorePackage.RESPONSIBILITY__DEMANDS:
                getDemands().clear();
                return;
            case UrncorePackage.RESPONSIBILITY__RESP_REFS:
                getRespRefs().clear();
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
            case UrncorePackage.RESPONSIBILITY__URN_LINKS:
                return urnLinks != null && !urnLinks.isEmpty();
            case UrncorePackage.RESPONSIBILITY__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case UrncorePackage.RESPONSIBILITY__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case UrncorePackage.RESPONSIBILITY__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case UrncorePackage.RESPONSIBILITY__DEMANDS:
                return demands != null && !demands.isEmpty();
            case UrncorePackage.RESPONSIBILITY__RESP_REFS:
                return respRefs != null && !respRefs.isEmpty();
        }
        return eDynamicIsSet(eFeature);
    }

} //ResponsibilityImpl
