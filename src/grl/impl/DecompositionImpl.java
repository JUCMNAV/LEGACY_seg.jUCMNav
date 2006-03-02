/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.Decomposition;
import grl.GRLspec;
import grl.GrlPackage;
import grl.IntentionalElement;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Decomposition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class DecompositionImpl extends ElementLinkImpl implements Decomposition {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DecompositionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return GrlPackage.eINSTANCE.getDecomposition();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case GrlPackage.DECOMPOSITION__FROM_LINKS:
                    return ((InternalEList)getFromLinks()).basicAdd(otherEnd, msgs);
                case GrlPackage.DECOMPOSITION__TO_LINKS:
                    return ((InternalEList)getToLinks()).basicAdd(otherEnd, msgs);
                case GrlPackage.DECOMPOSITION__REFS:
                    return ((InternalEList)getRefs()).basicAdd(otherEnd, msgs);
                case GrlPackage.DECOMPOSITION__GRLSPEC:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, GrlPackage.DECOMPOSITION__GRLSPEC, msgs);
                case GrlPackage.DECOMPOSITION__SRC:
                    if (src != null)
                        msgs = ((InternalEObject)src).eInverseRemove(this, GrlPackage.INTENTIONAL_ELEMENT__LINKS_SRC, IntentionalElement.class, msgs);
                    return basicSetSrc((IntentionalElement)otherEnd, msgs);
                case GrlPackage.DECOMPOSITION__DEST:
                    if (dest != null)
                        msgs = ((InternalEObject)dest).eInverseRemove(this, GrlPackage.INTENTIONAL_ELEMENT__LINKS_DEST, IntentionalElement.class, msgs);
                    return basicSetDest((IntentionalElement)otherEnd, msgs);
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
                case GrlPackage.DECOMPOSITION__FROM_LINKS:
                    return ((InternalEList)getFromLinks()).basicRemove(otherEnd, msgs);
                case GrlPackage.DECOMPOSITION__TO_LINKS:
                    return ((InternalEList)getToLinks()).basicRemove(otherEnd, msgs);
                case GrlPackage.DECOMPOSITION__REFS:
                    return ((InternalEList)getRefs()).basicRemove(otherEnd, msgs);
                case GrlPackage.DECOMPOSITION__GRLSPEC:
                    return eBasicSetContainer(null, GrlPackage.DECOMPOSITION__GRLSPEC, msgs);
                case GrlPackage.DECOMPOSITION__SRC:
                    return basicSetSrc(null, msgs);
                case GrlPackage.DECOMPOSITION__DEST:
                    return basicSetDest(null, msgs);
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
                case GrlPackage.DECOMPOSITION__GRLSPEC:
                    return eContainer.eInverseRemove(this, GrlPackage.GR_LSPEC__LINKS, GRLspec.class, msgs);
                default:
                    return eDynamicBasicRemoveFromContainer(msgs);
            }
        }
        return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(EStructuralFeature eFeature, boolean resolve) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case GrlPackage.DECOMPOSITION__FROM_LINKS:
                return getFromLinks();
            case GrlPackage.DECOMPOSITION__TO_LINKS:
                return getToLinks();
            case GrlPackage.DECOMPOSITION__ID:
                return getId();
            case GrlPackage.DECOMPOSITION__NAME:
                return getName();
            case GrlPackage.DECOMPOSITION__DESCRIPTION:
                return getDescription();
            case GrlPackage.DECOMPOSITION__REFS:
                return getRefs();
            case GrlPackage.DECOMPOSITION__GRLSPEC:
                return getGrlspec();
            case GrlPackage.DECOMPOSITION__SRC:
                if (resolve) return getSrc();
                return basicGetSrc();
            case GrlPackage.DECOMPOSITION__DEST:
                if (resolve) return getDest();
                return basicGetDest();
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
            case GrlPackage.DECOMPOSITION__FROM_LINKS:
                getFromLinks().clear();
                getFromLinks().addAll((Collection)newValue);
                return;
            case GrlPackage.DECOMPOSITION__TO_LINKS:
                getToLinks().clear();
                getToLinks().addAll((Collection)newValue);
                return;
            case GrlPackage.DECOMPOSITION__ID:
                setId((String)newValue);
                return;
            case GrlPackage.DECOMPOSITION__NAME:
                setName((String)newValue);
                return;
            case GrlPackage.DECOMPOSITION__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case GrlPackage.DECOMPOSITION__REFS:
                getRefs().clear();
                getRefs().addAll((Collection)newValue);
                return;
            case GrlPackage.DECOMPOSITION__GRLSPEC:
                setGrlspec((GRLspec)newValue);
                return;
            case GrlPackage.DECOMPOSITION__SRC:
                setSrc((IntentionalElement)newValue);
                return;
            case GrlPackage.DECOMPOSITION__DEST:
                setDest((IntentionalElement)newValue);
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
            case GrlPackage.DECOMPOSITION__FROM_LINKS:
                getFromLinks().clear();
                return;
            case GrlPackage.DECOMPOSITION__TO_LINKS:
                getToLinks().clear();
                return;
            case GrlPackage.DECOMPOSITION__ID:
                setId(ID_EDEFAULT);
                return;
            case GrlPackage.DECOMPOSITION__NAME:
                setName(NAME_EDEFAULT);
                return;
            case GrlPackage.DECOMPOSITION__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case GrlPackage.DECOMPOSITION__REFS:
                getRefs().clear();
                return;
            case GrlPackage.DECOMPOSITION__GRLSPEC:
                setGrlspec((GRLspec)null);
                return;
            case GrlPackage.DECOMPOSITION__SRC:
                setSrc((IntentionalElement)null);
                return;
            case GrlPackage.DECOMPOSITION__DEST:
                setDest((IntentionalElement)null);
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
            case GrlPackage.DECOMPOSITION__FROM_LINKS:
                return fromLinks != null && !fromLinks.isEmpty();
            case GrlPackage.DECOMPOSITION__TO_LINKS:
                return toLinks != null && !toLinks.isEmpty();
            case GrlPackage.DECOMPOSITION__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case GrlPackage.DECOMPOSITION__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case GrlPackage.DECOMPOSITION__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case GrlPackage.DECOMPOSITION__REFS:
                return refs != null && !refs.isEmpty();
            case GrlPackage.DECOMPOSITION__GRLSPEC:
                return getGrlspec() != null;
            case GrlPackage.DECOMPOSITION__SRC:
                return src != null;
            case GrlPackage.DECOMPOSITION__DEST:
                return dest != null;
        }
        return eDynamicIsSet(eFeature);
    }

} //DecompositionImpl
