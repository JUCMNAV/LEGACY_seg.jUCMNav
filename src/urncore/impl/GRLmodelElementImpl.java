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

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import urn.URNlink;
import urn.UrnPackage;

import urncore.GRLmodelElement;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>GR Lmodel Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link urncore.impl.GRLmodelElementImpl#getUrnLinks <em>Urn Links</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class GRLmodelElementImpl extends URNmodelElementImpl implements GRLmodelElement {
    /**
     * The cached value of the '{@link #getUrnLinks() <em>Urn Links</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUrnLinks()
     * @generated
     * @ordered
     */
    protected EList urnLinks = null;

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
    public EList getUrnLinks() {
        if (urnLinks == null) {
            urnLinks = new EObjectWithInverseResolvingEList.ManyInverse(URNlink.class, this, UrncorePackage.GR_LMODEL_ELEMENT__URN_LINKS, UrnPackage.UR_NLINK__GRL_ELEMS);
        }
        return urnLinks;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case UrncorePackage.GR_LMODEL_ELEMENT__URN_LINKS:
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
                case UrncorePackage.GR_LMODEL_ELEMENT__URN_LINKS:
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
            case UrncorePackage.GR_LMODEL_ELEMENT__ID:
                return getId();
            case UrncorePackage.GR_LMODEL_ELEMENT__NAME:
                return getName();
            case UrncorePackage.GR_LMODEL_ELEMENT__DESCRIPTION:
                return getDescription();
            case UrncorePackage.GR_LMODEL_ELEMENT__URN_LINKS:
                return getUrnLinks();
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
            case UrncorePackage.GR_LMODEL_ELEMENT__ID:
                setId((String)newValue);
                return;
            case UrncorePackage.GR_LMODEL_ELEMENT__NAME:
                setName((String)newValue);
                return;
            case UrncorePackage.GR_LMODEL_ELEMENT__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case UrncorePackage.GR_LMODEL_ELEMENT__URN_LINKS:
                getUrnLinks().clear();
                getUrnLinks().addAll((Collection)newValue);
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
            case UrncorePackage.GR_LMODEL_ELEMENT__ID:
                setId(ID_EDEFAULT);
                return;
            case UrncorePackage.GR_LMODEL_ELEMENT__NAME:
                setName(NAME_EDEFAULT);
                return;
            case UrncorePackage.GR_LMODEL_ELEMENT__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case UrncorePackage.GR_LMODEL_ELEMENT__URN_LINKS:
                getUrnLinks().clear();
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
            case UrncorePackage.GR_LMODEL_ELEMENT__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case UrncorePackage.GR_LMODEL_ELEMENT__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case UrncorePackage.GR_LMODEL_ELEMENT__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case UrncorePackage.GR_LMODEL_ELEMENT__URN_LINKS:
                return urnLinks != null && !urnLinks.isEmpty();
        }
        return eDynamicIsSet(eFeature);
    }

} //GRLmodelElementImpl
