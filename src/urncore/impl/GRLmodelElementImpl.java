/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;

import urncore.GRLmodelElement;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>GR Lmodel Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class GRLmodelElementImpl extends URNmodelElementImpl implements GRLmodelElement {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected GRLmodelElementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return UrncorePackage.eINSTANCE.getGRLmodelElement();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case UrncorePackage.GR_LMODEL_ELEMENT__FROM_LINKS:
                    return ((InternalEList)getFromLinks()).basicAdd(otherEnd, msgs);
                case UrncorePackage.GR_LMODEL_ELEMENT__TO_LINKS:
                    return ((InternalEList)getToLinks()).basicAdd(otherEnd, msgs);
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
                case UrncorePackage.GR_LMODEL_ELEMENT__FROM_LINKS:
                    return ((InternalEList)getFromLinks()).basicRemove(otherEnd, msgs);
                case UrncorePackage.GR_LMODEL_ELEMENT__TO_LINKS:
                    return ((InternalEList)getToLinks()).basicRemove(otherEnd, msgs);
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
            case UrncorePackage.GR_LMODEL_ELEMENT__FROM_LINKS:
                return getFromLinks();
            case UrncorePackage.GR_LMODEL_ELEMENT__TO_LINKS:
                return getToLinks();
            case UrncorePackage.GR_LMODEL_ELEMENT__ID:
                return getId();
            case UrncorePackage.GR_LMODEL_ELEMENT__NAME:
                return getName();
            case UrncorePackage.GR_LMODEL_ELEMENT__DESCRIPTION:
                return getDescription();
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
            case UrncorePackage.GR_LMODEL_ELEMENT__FROM_LINKS:
                getFromLinks().clear();
                getFromLinks().addAll((Collection)newValue);
                return;
            case UrncorePackage.GR_LMODEL_ELEMENT__TO_LINKS:
                getToLinks().clear();
                getToLinks().addAll((Collection)newValue);
                return;
            case UrncorePackage.GR_LMODEL_ELEMENT__ID:
                setId((String)newValue);
                return;
            case UrncorePackage.GR_LMODEL_ELEMENT__NAME:
                setName((String)newValue);
                return;
            case UrncorePackage.GR_LMODEL_ELEMENT__DESCRIPTION:
                setDescription((String)newValue);
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
            case UrncorePackage.GR_LMODEL_ELEMENT__FROM_LINKS:
                getFromLinks().clear();
                return;
            case UrncorePackage.GR_LMODEL_ELEMENT__TO_LINKS:
                getToLinks().clear();
                return;
            case UrncorePackage.GR_LMODEL_ELEMENT__ID:
                setId(ID_EDEFAULT);
                return;
            case UrncorePackage.GR_LMODEL_ELEMENT__NAME:
                setName(NAME_EDEFAULT);
                return;
            case UrncorePackage.GR_LMODEL_ELEMENT__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
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
            case UrncorePackage.GR_LMODEL_ELEMENT__FROM_LINKS:
                return fromLinks != null && !fromLinks.isEmpty();
            case UrncorePackage.GR_LMODEL_ELEMENT__TO_LINKS:
                return toLinks != null && !toLinks.isEmpty();
            case UrncorePackage.GR_LMODEL_ELEMENT__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case UrncorePackage.GR_LMODEL_ELEMENT__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case UrncorePackage.GR_LMODEL_ELEMENT__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
        }
        return eDynamicIsSet(eFeature);
    }

} //GRLmodelElementImpl
