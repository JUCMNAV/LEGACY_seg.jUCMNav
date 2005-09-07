/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import urn.URNspec;
import urn.UrnPackage;

import urncore.ComponentElement;
import urncore.GRLmodelElement;
import urncore.Responsibility;
import urncore.URNdefinition;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>UR Ndefinition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link urncore.impl.URNdefinitionImpl#getUrnspec <em>Urnspec</em>}</li>
 *   <li>{@link urncore.impl.URNdefinitionImpl#getResponsibilities <em>Responsibilities</em>}</li>
 *   <li>{@link urncore.impl.URNdefinitionImpl#getComponents <em>Components</em>}</li>
 *   <li>{@link urncore.impl.URNdefinitionImpl#getGRLelements <em>GR Lelements</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class URNdefinitionImpl extends EObjectImpl implements URNdefinition {
    /**
     * The cached value of the '{@link #getResponsibilities() <em>Responsibilities</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getResponsibilities()
     * @generated
     * @ordered
     */
	protected EList responsibilities = null;

    /**
     * The cached value of the '{@link #getComponents() <em>Components</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getComponents()
     * @generated
     * @ordered
     */
	protected EList components = null;

    /**
     * The cached value of the '{@link #getGRLelements() <em>GR Lelements</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getGRLelements()
     * @generated
     * @ordered
     */
	protected EList grLelements = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected URNdefinitionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return UrncorePackage.eINSTANCE.getURNdefinition();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public URNspec getUrnspec() {
        if (eContainerFeatureID != UrncorePackage.UR_NDEFINITION__URNSPEC) return null;
        return (URNspec)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setUrnspec(URNspec newUrnspec) {
        if (newUrnspec != eContainer || (eContainerFeatureID != UrncorePackage.UR_NDEFINITION__URNSPEC && newUrnspec != null)) {
            if (EcoreUtil.isAncestor(this, newUrnspec))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newUrnspec != null)
                msgs = ((InternalEObject)newUrnspec).eInverseAdd(this, UrnPackage.UR_NSPEC__URNDEF, URNspec.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newUrnspec, UrncorePackage.UR_NDEFINITION__URNSPEC, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.UR_NDEFINITION__URNSPEC, newUrnspec, newUrnspec));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getResponsibilities() {
        if (responsibilities == null) {
            responsibilities = new EObjectContainmentWithInverseEList(Responsibility.class, this, UrncorePackage.UR_NDEFINITION__RESPONSIBILITIES, UrncorePackage.RESPONSIBILITY__URNDEFINITION);
        }
        return responsibilities;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getComponents() {
        if (components == null) {
            components = new EObjectContainmentWithInverseEList(ComponentElement.class, this, UrncorePackage.UR_NDEFINITION__COMPONENTS, UrncorePackage.COMPONENT_ELEMENT__URNDEFINITION);
        }
        return components;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getGRLelements() {
        if (grLelements == null) {
            grLelements = new EObjectContainmentWithInverseEList(GRLmodelElement.class, this, UrncorePackage.UR_NDEFINITION__GR_LELEMENTS, UrncorePackage.GR_LMODEL_ELEMENT__URNDEFINITION);
        }
        return grLelements;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case UrncorePackage.UR_NDEFINITION__URNSPEC:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, UrncorePackage.UR_NDEFINITION__URNSPEC, msgs);
                case UrncorePackage.UR_NDEFINITION__RESPONSIBILITIES:
                    return ((InternalEList)getResponsibilities()).basicAdd(otherEnd, msgs);
                case UrncorePackage.UR_NDEFINITION__COMPONENTS:
                    return ((InternalEList)getComponents()).basicAdd(otherEnd, msgs);
                case UrncorePackage.UR_NDEFINITION__GR_LELEMENTS:
                    return ((InternalEList)getGRLelements()).basicAdd(otherEnd, msgs);
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
                case UrncorePackage.UR_NDEFINITION__URNSPEC:
                    return eBasicSetContainer(null, UrncorePackage.UR_NDEFINITION__URNSPEC, msgs);
                case UrncorePackage.UR_NDEFINITION__RESPONSIBILITIES:
                    return ((InternalEList)getResponsibilities()).basicRemove(otherEnd, msgs);
                case UrncorePackage.UR_NDEFINITION__COMPONENTS:
                    return ((InternalEList)getComponents()).basicRemove(otherEnd, msgs);
                case UrncorePackage.UR_NDEFINITION__GR_LELEMENTS:
                    return ((InternalEList)getGRLelements()).basicRemove(otherEnd, msgs);
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
                case UrncorePackage.UR_NDEFINITION__URNSPEC:
                    return eContainer.eInverseRemove(this, UrnPackage.UR_NSPEC__URNDEF, URNspec.class, msgs);
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
            case UrncorePackage.UR_NDEFINITION__URNSPEC:
                return getUrnspec();
            case UrncorePackage.UR_NDEFINITION__RESPONSIBILITIES:
                return getResponsibilities();
            case UrncorePackage.UR_NDEFINITION__COMPONENTS:
                return getComponents();
            case UrncorePackage.UR_NDEFINITION__GR_LELEMENTS:
                return getGRLelements();
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
            case UrncorePackage.UR_NDEFINITION__URNSPEC:
                setUrnspec((URNspec)newValue);
                return;
            case UrncorePackage.UR_NDEFINITION__RESPONSIBILITIES:
                getResponsibilities().clear();
                getResponsibilities().addAll((Collection)newValue);
                return;
            case UrncorePackage.UR_NDEFINITION__COMPONENTS:
                getComponents().clear();
                getComponents().addAll((Collection)newValue);
                return;
            case UrncorePackage.UR_NDEFINITION__GR_LELEMENTS:
                getGRLelements().clear();
                getGRLelements().addAll((Collection)newValue);
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
            case UrncorePackage.UR_NDEFINITION__URNSPEC:
                setUrnspec((URNspec)null);
                return;
            case UrncorePackage.UR_NDEFINITION__RESPONSIBILITIES:
                getResponsibilities().clear();
                return;
            case UrncorePackage.UR_NDEFINITION__COMPONENTS:
                getComponents().clear();
                return;
            case UrncorePackage.UR_NDEFINITION__GR_LELEMENTS:
                getGRLelements().clear();
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
            case UrncorePackage.UR_NDEFINITION__URNSPEC:
                return getUrnspec() != null;
            case UrncorePackage.UR_NDEFINITION__RESPONSIBILITIES:
                return responsibilities != null && !responsibilities.isEmpty();
            case UrncorePackage.UR_NDEFINITION__COMPONENTS:
                return components != null && !components.isEmpty();
            case UrncorePackage.UR_NDEFINITION__GR_LELEMENTS:
                return grLelements != null && !grLelements.isEmpty();
        }
        return eDynamicIsSet(eFeature);
    }

} //URNdefinitionImpl
