/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.EvaluationGroup;
import grl.EvaluationScenario;
import grl.GRLspec;
import grl.GrlPackage;

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
 * An implementation of the model object '<em><b>Evaluation Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.EvaluationGroupImpl#getScenarios <em>Scenarios</em>}</li>
 *   <li>{@link grl.impl.EvaluationGroupImpl#getGrlspec <em>Grlspec</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EvaluationGroupImpl extends GRLmodelElementImpl implements EvaluationGroup {
    /**
     * The cached value of the '{@link #getScenarios() <em>Scenarios</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getScenarios()
     * @generated
     * @ordered
     */
    protected EList scenarios = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EvaluationGroupImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return GrlPackage.eINSTANCE.getEvaluationGroup();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getScenarios() {
        if (scenarios == null) {
            scenarios = new EObjectWithInverseResolvingEList(EvaluationScenario.class, this, GrlPackage.EVALUATION_GROUP__SCENARIOS, GrlPackage.EVALUATION_SCENARIO__GROUP);
        }
        return scenarios;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GRLspec getGrlspec() {
        if (eContainerFeatureID != GrlPackage.EVALUATION_GROUP__GRLSPEC) return null;
        return (GRLspec)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGrlspec(GRLspec newGrlspec) {
        if (newGrlspec != eContainer || (eContainerFeatureID != GrlPackage.EVALUATION_GROUP__GRLSPEC && newGrlspec != null)) {
            if (EcoreUtil.isAncestor(this, newGrlspec))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newGrlspec != null)
                msgs = ((InternalEObject)newGrlspec).eInverseAdd(this, GrlPackage.GR_LSPEC__EVALUATION_GROUPS, GRLspec.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newGrlspec, GrlPackage.EVALUATION_GROUP__GRLSPEC, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.EVALUATION_GROUP__GRLSPEC, newGrlspec, newGrlspec));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case GrlPackage.EVALUATION_GROUP__FROM_LINKS:
                    return ((InternalEList)getFromLinks()).basicAdd(otherEnd, msgs);
                case GrlPackage.EVALUATION_GROUP__TO_LINKS:
                    return ((InternalEList)getToLinks()).basicAdd(otherEnd, msgs);
                case GrlPackage.EVALUATION_GROUP__SCENARIOS:
                    return ((InternalEList)getScenarios()).basicAdd(otherEnd, msgs);
                case GrlPackage.EVALUATION_GROUP__GRLSPEC:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, GrlPackage.EVALUATION_GROUP__GRLSPEC, msgs);
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
                case GrlPackage.EVALUATION_GROUP__FROM_LINKS:
                    return ((InternalEList)getFromLinks()).basicRemove(otherEnd, msgs);
                case GrlPackage.EVALUATION_GROUP__TO_LINKS:
                    return ((InternalEList)getToLinks()).basicRemove(otherEnd, msgs);
                case GrlPackage.EVALUATION_GROUP__SCENARIOS:
                    return ((InternalEList)getScenarios()).basicRemove(otherEnd, msgs);
                case GrlPackage.EVALUATION_GROUP__GRLSPEC:
                    return eBasicSetContainer(null, GrlPackage.EVALUATION_GROUP__GRLSPEC, msgs);
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
                case GrlPackage.EVALUATION_GROUP__GRLSPEC:
                    return eContainer.eInverseRemove(this, GrlPackage.GR_LSPEC__EVALUATION_GROUPS, GRLspec.class, msgs);
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
            case GrlPackage.EVALUATION_GROUP__FROM_LINKS:
                return getFromLinks();
            case GrlPackage.EVALUATION_GROUP__TO_LINKS:
                return getToLinks();
            case GrlPackage.EVALUATION_GROUP__ID:
                return getId();
            case GrlPackage.EVALUATION_GROUP__NAME:
                return getName();
            case GrlPackage.EVALUATION_GROUP__DESCRIPTION:
                return getDescription();
            case GrlPackage.EVALUATION_GROUP__SCENARIOS:
                return getScenarios();
            case GrlPackage.EVALUATION_GROUP__GRLSPEC:
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
            case GrlPackage.EVALUATION_GROUP__FROM_LINKS:
                getFromLinks().clear();
                getFromLinks().addAll((Collection)newValue);
                return;
            case GrlPackage.EVALUATION_GROUP__TO_LINKS:
                getToLinks().clear();
                getToLinks().addAll((Collection)newValue);
                return;
            case GrlPackage.EVALUATION_GROUP__ID:
                setId((String)newValue);
                return;
            case GrlPackage.EVALUATION_GROUP__NAME:
                setName((String)newValue);
                return;
            case GrlPackage.EVALUATION_GROUP__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case GrlPackage.EVALUATION_GROUP__SCENARIOS:
                getScenarios().clear();
                getScenarios().addAll((Collection)newValue);
                return;
            case GrlPackage.EVALUATION_GROUP__GRLSPEC:
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
            case GrlPackage.EVALUATION_GROUP__FROM_LINKS:
                getFromLinks().clear();
                return;
            case GrlPackage.EVALUATION_GROUP__TO_LINKS:
                getToLinks().clear();
                return;
            case GrlPackage.EVALUATION_GROUP__ID:
                setId(ID_EDEFAULT);
                return;
            case GrlPackage.EVALUATION_GROUP__NAME:
                setName(NAME_EDEFAULT);
                return;
            case GrlPackage.EVALUATION_GROUP__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case GrlPackage.EVALUATION_GROUP__SCENARIOS:
                getScenarios().clear();
                return;
            case GrlPackage.EVALUATION_GROUP__GRLSPEC:
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
            case GrlPackage.EVALUATION_GROUP__FROM_LINKS:
                return fromLinks != null && !fromLinks.isEmpty();
            case GrlPackage.EVALUATION_GROUP__TO_LINKS:
                return toLinks != null && !toLinks.isEmpty();
            case GrlPackage.EVALUATION_GROUP__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case GrlPackage.EVALUATION_GROUP__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case GrlPackage.EVALUATION_GROUP__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case GrlPackage.EVALUATION_GROUP__SCENARIOS:
                return scenarios != null && !scenarios.isEmpty();
            case GrlPackage.EVALUATION_GROUP__GRLSPEC:
                return getGrlspec() != null;
        }
        return eDynamicIsSet(eFeature);
    }

} //EvaluationGroupImpl
