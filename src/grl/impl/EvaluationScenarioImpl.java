/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.Evaluation;
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

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import urncore.impl.GRLmodelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Evaluation Scenario</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.EvaluationScenarioImpl#getEvaluations <em>Evaluations</em>}</li>
 *   <li>{@link grl.impl.EvaluationScenarioImpl#getGroups <em>Groups</em>}</li>
 *   <li>{@link grl.impl.EvaluationScenarioImpl#getGrlspec <em>Grlspec</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EvaluationScenarioImpl extends GRLmodelElementImpl implements EvaluationScenario {
    /**
     * The cached value of the '{@link #getEvaluations() <em>Evaluations</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEvaluations()
     * @generated
     * @ordered
     */
    protected EList evaluations = null;

    /**
     * The cached value of the '{@link #getGroups() <em>Groups</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getGroups()
     * @generated
     * @ordered
     */
    protected EList groups = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EvaluationScenarioImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return GrlPackage.eINSTANCE.getEvaluationScenario();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getEvaluations() {
        if (evaluations == null) {
            evaluations = new EObjectContainmentWithInverseEList(Evaluation.class, this, GrlPackage.EVALUATION_SCENARIO__EVALUATIONS, GrlPackage.EVALUATION__SCENARIO);
        }
        return evaluations;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getGroups() {
        if (groups == null) {
            groups = new EObjectWithInverseResolvingEList.ManyInverse(EvaluationGroup.class, this, GrlPackage.EVALUATION_SCENARIO__GROUPS, GrlPackage.EVALUATION_GROUP__SCENARIOS);
        }
        return groups;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GRLspec getGrlspec() {
        if (eContainerFeatureID != GrlPackage.EVALUATION_SCENARIO__GRLSPEC) return null;
        return (GRLspec)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGrlspec(GRLspec newGrlspec) {
        if (newGrlspec != eContainer || (eContainerFeatureID != GrlPackage.EVALUATION_SCENARIO__GRLSPEC && newGrlspec != null)) {
            if (EcoreUtil.isAncestor(this, newGrlspec))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newGrlspec != null)
                msgs = ((InternalEObject)newGrlspec).eInverseAdd(this, GrlPackage.GR_LSPEC__SCENARIOS, GRLspec.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newGrlspec, GrlPackage.EVALUATION_SCENARIO__GRLSPEC, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.EVALUATION_SCENARIO__GRLSPEC, newGrlspec, newGrlspec));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case GrlPackage.EVALUATION_SCENARIO__FROM_LINKS:
                    return ((InternalEList)getFromLinks()).basicAdd(otherEnd, msgs);
                case GrlPackage.EVALUATION_SCENARIO__TO_LINKS:
                    return ((InternalEList)getToLinks()).basicAdd(otherEnd, msgs);
                case GrlPackage.EVALUATION_SCENARIO__EVALUATIONS:
                    return ((InternalEList)getEvaluations()).basicAdd(otherEnd, msgs);
                case GrlPackage.EVALUATION_SCENARIO__GROUPS:
                    return ((InternalEList)getGroups()).basicAdd(otherEnd, msgs);
                case GrlPackage.EVALUATION_SCENARIO__GRLSPEC:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, GrlPackage.EVALUATION_SCENARIO__GRLSPEC, msgs);
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
                case GrlPackage.EVALUATION_SCENARIO__FROM_LINKS:
                    return ((InternalEList)getFromLinks()).basicRemove(otherEnd, msgs);
                case GrlPackage.EVALUATION_SCENARIO__TO_LINKS:
                    return ((InternalEList)getToLinks()).basicRemove(otherEnd, msgs);
                case GrlPackage.EVALUATION_SCENARIO__EVALUATIONS:
                    return ((InternalEList)getEvaluations()).basicRemove(otherEnd, msgs);
                case GrlPackage.EVALUATION_SCENARIO__GROUPS:
                    return ((InternalEList)getGroups()).basicRemove(otherEnd, msgs);
                case GrlPackage.EVALUATION_SCENARIO__GRLSPEC:
                    return eBasicSetContainer(null, GrlPackage.EVALUATION_SCENARIO__GRLSPEC, msgs);
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
                case GrlPackage.EVALUATION_SCENARIO__GRLSPEC:
                    return eContainer.eInverseRemove(this, GrlPackage.GR_LSPEC__SCENARIOS, GRLspec.class, msgs);
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
            case GrlPackage.EVALUATION_SCENARIO__FROM_LINKS:
                return getFromLinks();
            case GrlPackage.EVALUATION_SCENARIO__TO_LINKS:
                return getToLinks();
            case GrlPackage.EVALUATION_SCENARIO__ID:
                return getId();
            case GrlPackage.EVALUATION_SCENARIO__NAME:
                return getName();
            case GrlPackage.EVALUATION_SCENARIO__DESCRIPTION:
                return getDescription();
            case GrlPackage.EVALUATION_SCENARIO__EVALUATIONS:
                return getEvaluations();
            case GrlPackage.EVALUATION_SCENARIO__GROUPS:
                return getGroups();
            case GrlPackage.EVALUATION_SCENARIO__GRLSPEC:
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
            case GrlPackage.EVALUATION_SCENARIO__FROM_LINKS:
                getFromLinks().clear();
                getFromLinks().addAll((Collection)newValue);
                return;
            case GrlPackage.EVALUATION_SCENARIO__TO_LINKS:
                getToLinks().clear();
                getToLinks().addAll((Collection)newValue);
                return;
            case GrlPackage.EVALUATION_SCENARIO__ID:
                setId((String)newValue);
                return;
            case GrlPackage.EVALUATION_SCENARIO__NAME:
                setName((String)newValue);
                return;
            case GrlPackage.EVALUATION_SCENARIO__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case GrlPackage.EVALUATION_SCENARIO__EVALUATIONS:
                getEvaluations().clear();
                getEvaluations().addAll((Collection)newValue);
                return;
            case GrlPackage.EVALUATION_SCENARIO__GROUPS:
                getGroups().clear();
                getGroups().addAll((Collection)newValue);
                return;
            case GrlPackage.EVALUATION_SCENARIO__GRLSPEC:
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
            case GrlPackage.EVALUATION_SCENARIO__FROM_LINKS:
                getFromLinks().clear();
                return;
            case GrlPackage.EVALUATION_SCENARIO__TO_LINKS:
                getToLinks().clear();
                return;
            case GrlPackage.EVALUATION_SCENARIO__ID:
                setId(ID_EDEFAULT);
                return;
            case GrlPackage.EVALUATION_SCENARIO__NAME:
                setName(NAME_EDEFAULT);
                return;
            case GrlPackage.EVALUATION_SCENARIO__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case GrlPackage.EVALUATION_SCENARIO__EVALUATIONS:
                getEvaluations().clear();
                return;
            case GrlPackage.EVALUATION_SCENARIO__GROUPS:
                getGroups().clear();
                return;
            case GrlPackage.EVALUATION_SCENARIO__GRLSPEC:
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
            case GrlPackage.EVALUATION_SCENARIO__FROM_LINKS:
                return fromLinks != null && !fromLinks.isEmpty();
            case GrlPackage.EVALUATION_SCENARIO__TO_LINKS:
                return toLinks != null && !toLinks.isEmpty();
            case GrlPackage.EVALUATION_SCENARIO__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case GrlPackage.EVALUATION_SCENARIO__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case GrlPackage.EVALUATION_SCENARIO__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case GrlPackage.EVALUATION_SCENARIO__EVALUATIONS:
                return evaluations != null && !evaluations.isEmpty();
            case GrlPackage.EVALUATION_SCENARIO__GROUPS:
                return groups != null && !groups.isEmpty();
            case GrlPackage.EVALUATION_SCENARIO__GRLSPEC:
                return getGrlspec() != null;
        }
        return eDynamicIsSet(eFeature);
    }

} //EvaluationScenarioImpl
