/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.EvaluationStrategy;
import grl.GRLspec;
import grl.GrlPackage;
import grl.StrategiesGroup;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import urncore.impl.GRLmodelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Strategies Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.StrategiesGroupImpl#getStrategies <em>Strategies</em>}</li>
 *   <li>{@link grl.impl.StrategiesGroupImpl#getGrlspec <em>Grlspec</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StrategiesGroupImpl extends GRLmodelElementImpl implements StrategiesGroup {
    /**
     * The cached value of the '{@link #getStrategies() <em>Strategies</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStrategies()
     * @generated
     * @ordered
     */
    protected EList strategies = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected StrategiesGroupImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return GrlPackage.eINSTANCE.getStrategiesGroup();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getStrategies() {
        if (strategies == null) {
            strategies = new EObjectWithInverseResolvingEList(EvaluationStrategy.class, this, GrlPackage.STRATEGIES_GROUP__STRATEGIES, GrlPackage.EVALUATION_STRATEGY__GROUP);
        }
        return strategies;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GRLspec getGrlspec() {
        if (eContainerFeatureID != GrlPackage.STRATEGIES_GROUP__GRLSPEC) return null;
        return (GRLspec)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGrlspec(GRLspec newGrlspec) {
        if (newGrlspec != eContainer || (eContainerFeatureID != GrlPackage.STRATEGIES_GROUP__GRLSPEC && newGrlspec != null)) {
            if (EcoreUtil.isAncestor(this, newGrlspec))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newGrlspec != null)
                msgs = ((InternalEObject)newGrlspec).eInverseAdd(this, GrlPackage.GR_LSPEC__GROUPS, GRLspec.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newGrlspec, GrlPackage.STRATEGIES_GROUP__GRLSPEC, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.STRATEGIES_GROUP__GRLSPEC, newGrlspec, newGrlspec));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case GrlPackage.STRATEGIES_GROUP__URNLINKS:
                    return ((InternalEList)getUrnlinks()).basicAdd(otherEnd, msgs);
                case GrlPackage.STRATEGIES_GROUP__STRATEGIES:
                    return ((InternalEList)getStrategies()).basicAdd(otherEnd, msgs);
                case GrlPackage.STRATEGIES_GROUP__GRLSPEC:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, GrlPackage.STRATEGIES_GROUP__GRLSPEC, msgs);
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
                case GrlPackage.STRATEGIES_GROUP__URNLINKS:
                    return ((InternalEList)getUrnlinks()).basicRemove(otherEnd, msgs);
                case GrlPackage.STRATEGIES_GROUP__STRATEGIES:
                    return ((InternalEList)getStrategies()).basicRemove(otherEnd, msgs);
                case GrlPackage.STRATEGIES_GROUP__GRLSPEC:
                    return eBasicSetContainer(null, GrlPackage.STRATEGIES_GROUP__GRLSPEC, msgs);
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
                case GrlPackage.STRATEGIES_GROUP__GRLSPEC:
                    return eContainer.eInverseRemove(this, GrlPackage.GR_LSPEC__GROUPS, GRLspec.class, msgs);
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
            case GrlPackage.STRATEGIES_GROUP__ID:
                return getId();
            case GrlPackage.STRATEGIES_GROUP__NAME:
                return getName();
            case GrlPackage.STRATEGIES_GROUP__DESCRIPTION:
                return getDescription();
            case GrlPackage.STRATEGIES_GROUP__URNLINKS:
                return getUrnlinks();
            case GrlPackage.STRATEGIES_GROUP__STRATEGIES:
                return getStrategies();
            case GrlPackage.STRATEGIES_GROUP__GRLSPEC:
                return getGrlspec();
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
            case GrlPackage.STRATEGIES_GROUP__ID:
                setId((String)newValue);
                return;
            case GrlPackage.STRATEGIES_GROUP__NAME:
                setName((String)newValue);
                return;
            case GrlPackage.STRATEGIES_GROUP__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case GrlPackage.STRATEGIES_GROUP__URNLINKS:
                getUrnlinks().clear();
                getUrnlinks().addAll((Collection)newValue);
                return;
            case GrlPackage.STRATEGIES_GROUP__STRATEGIES:
                getStrategies().clear();
                getStrategies().addAll((Collection)newValue);
                return;
            case GrlPackage.STRATEGIES_GROUP__GRLSPEC:
                setGrlspec((GRLspec)newValue);
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
            case GrlPackage.STRATEGIES_GROUP__ID:
                setId(ID_EDEFAULT);
                return;
            case GrlPackage.STRATEGIES_GROUP__NAME:
                setName(NAME_EDEFAULT);
                return;
            case GrlPackage.STRATEGIES_GROUP__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case GrlPackage.STRATEGIES_GROUP__URNLINKS:
                getUrnlinks().clear();
                return;
            case GrlPackage.STRATEGIES_GROUP__STRATEGIES:
                getStrategies().clear();
                return;
            case GrlPackage.STRATEGIES_GROUP__GRLSPEC:
                setGrlspec((GRLspec)null);
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
            case GrlPackage.STRATEGIES_GROUP__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case GrlPackage.STRATEGIES_GROUP__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case GrlPackage.STRATEGIES_GROUP__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case GrlPackage.STRATEGIES_GROUP__URNLINKS:
                return urnlinks != null && !urnlinks.isEmpty();
            case GrlPackage.STRATEGIES_GROUP__STRATEGIES:
                return strategies != null && !strategies.isEmpty();
            case GrlPackage.STRATEGIES_GROUP__GRLSPEC:
                return getGrlspec() != null;
        }
        return eDynamicIsSet(eFeature);
    }

} //StrategiesGroupImpl
