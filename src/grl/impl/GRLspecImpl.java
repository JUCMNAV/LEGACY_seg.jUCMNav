/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.Actor;
import grl.ElementLink;
import grl.EvaluationGroup;
import grl.EvaluationScenario;
import grl.GRLspec;
import grl.GrlPackage;
import grl.IntentionalElement;

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

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>GR Lspec</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.GRLspecImpl#getUrnspec <em>Urnspec</em>}</li>
 *   <li>{@link grl.impl.GRLspecImpl#getIntElements <em>Int Elements</em>}</li>
 *   <li>{@link grl.impl.GRLspecImpl#getActors <em>Actors</em>}</li>
 *   <li>{@link grl.impl.GRLspecImpl#getLinks <em>Links</em>}</li>
 *   <li>{@link grl.impl.GRLspecImpl#getEvaluationGroups <em>Evaluation Groups</em>}</li>
 *   <li>{@link grl.impl.GRLspecImpl#getScenarios <em>Scenarios</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GRLspecImpl extends EObjectImpl implements GRLspec {
    /**
     * The cached value of the '{@link #getIntElements() <em>Int Elements</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIntElements()
     * @generated
     * @ordered
     */
    protected EList intElements = null;

    /**
     * The cached value of the '{@link #getActors() <em>Actors</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getActors()
     * @generated
     * @ordered
     */
    protected EList actors = null;

    /**
     * The cached value of the '{@link #getLinks() <em>Links</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLinks()
     * @generated
     * @ordered
     */
    protected EList links = null;

    /**
     * The cached value of the '{@link #getEvaluationGroups() <em>Evaluation Groups</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEvaluationGroups()
     * @generated
     * @ordered
     */
    protected EList evaluationGroups = null;

    /**
     * The cached value of the '{@link #getScenarios() <em>Scenarios</em>}' containment reference list.
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
    public EList getIntElements() {
        if (intElements == null) {
            intElements = new EObjectContainmentWithInverseEList(IntentionalElement.class, this, GrlPackage.GR_LSPEC__INT_ELEMENTS, GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC);
        }
        return intElements;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getActors() {
        if (actors == null) {
            actors = new EObjectContainmentWithInverseEList(Actor.class, this, GrlPackage.GR_LSPEC__ACTORS, GrlPackage.ACTOR__GRLSPEC);
        }
        return actors;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getLinks() {
        if (links == null) {
            links = new EObjectContainmentWithInverseEList(ElementLink.class, this, GrlPackage.GR_LSPEC__LINKS, GrlPackage.ELEMENT_LINK__GRLSPEC);
        }
        return links;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getEvaluationGroups() {
        if (evaluationGroups == null) {
            evaluationGroups = new EObjectContainmentWithInverseEList(EvaluationGroup.class, this, GrlPackage.GR_LSPEC__EVALUATION_GROUPS, GrlPackage.EVALUATION_GROUP__GRLSPEC);
        }
        return evaluationGroups;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getScenarios() {
        if (scenarios == null) {
            scenarios = new EObjectContainmentWithInverseEList(EvaluationScenario.class, this, GrlPackage.GR_LSPEC__SCENARIOS, GrlPackage.EVALUATION_SCENARIO__GRLSPEC);
        }
        return scenarios;
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
                case GrlPackage.GR_LSPEC__INT_ELEMENTS:
                    return ((InternalEList)getIntElements()).basicAdd(otherEnd, msgs);
                case GrlPackage.GR_LSPEC__ACTORS:
                    return ((InternalEList)getActors()).basicAdd(otherEnd, msgs);
                case GrlPackage.GR_LSPEC__LINKS:
                    return ((InternalEList)getLinks()).basicAdd(otherEnd, msgs);
                case GrlPackage.GR_LSPEC__EVALUATION_GROUPS:
                    return ((InternalEList)getEvaluationGroups()).basicAdd(otherEnd, msgs);
                case GrlPackage.GR_LSPEC__SCENARIOS:
                    return ((InternalEList)getScenarios()).basicAdd(otherEnd, msgs);
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
                case GrlPackage.GR_LSPEC__INT_ELEMENTS:
                    return ((InternalEList)getIntElements()).basicRemove(otherEnd, msgs);
                case GrlPackage.GR_LSPEC__ACTORS:
                    return ((InternalEList)getActors()).basicRemove(otherEnd, msgs);
                case GrlPackage.GR_LSPEC__LINKS:
                    return ((InternalEList)getLinks()).basicRemove(otherEnd, msgs);
                case GrlPackage.GR_LSPEC__EVALUATION_GROUPS:
                    return ((InternalEList)getEvaluationGroups()).basicRemove(otherEnd, msgs);
                case GrlPackage.GR_LSPEC__SCENARIOS:
                    return ((InternalEList)getScenarios()).basicRemove(otherEnd, msgs);
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
                    return eContainer.eInverseRemove(this, UrnPackage.UR_NSPEC__GRLSPEC, URNspec.class, msgs);
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
            case GrlPackage.GR_LSPEC__URNSPEC:
                return getUrnspec();
            case GrlPackage.GR_LSPEC__INT_ELEMENTS:
                return getIntElements();
            case GrlPackage.GR_LSPEC__ACTORS:
                return getActors();
            case GrlPackage.GR_LSPEC__LINKS:
                return getLinks();
            case GrlPackage.GR_LSPEC__EVALUATION_GROUPS:
                return getEvaluationGroups();
            case GrlPackage.GR_LSPEC__SCENARIOS:
                return getScenarios();
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
            case GrlPackage.GR_LSPEC__INT_ELEMENTS:
                getIntElements().clear();
                getIntElements().addAll((Collection)newValue);
                return;
            case GrlPackage.GR_LSPEC__ACTORS:
                getActors().clear();
                getActors().addAll((Collection)newValue);
                return;
            case GrlPackage.GR_LSPEC__LINKS:
                getLinks().clear();
                getLinks().addAll((Collection)newValue);
                return;
            case GrlPackage.GR_LSPEC__EVALUATION_GROUPS:
                getEvaluationGroups().clear();
                getEvaluationGroups().addAll((Collection)newValue);
                return;
            case GrlPackage.GR_LSPEC__SCENARIOS:
                getScenarios().clear();
                getScenarios().addAll((Collection)newValue);
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
            case GrlPackage.GR_LSPEC__INT_ELEMENTS:
                getIntElements().clear();
                return;
            case GrlPackage.GR_LSPEC__ACTORS:
                getActors().clear();
                return;
            case GrlPackage.GR_LSPEC__LINKS:
                getLinks().clear();
                return;
            case GrlPackage.GR_LSPEC__EVALUATION_GROUPS:
                getEvaluationGroups().clear();
                return;
            case GrlPackage.GR_LSPEC__SCENARIOS:
                getScenarios().clear();
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
            case GrlPackage.GR_LSPEC__INT_ELEMENTS:
                return intElements != null && !intElements.isEmpty();
            case GrlPackage.GR_LSPEC__ACTORS:
                return actors != null && !actors.isEmpty();
            case GrlPackage.GR_LSPEC__LINKS:
                return links != null && !links.isEmpty();
            case GrlPackage.GR_LSPEC__EVALUATION_GROUPS:
                return evaluationGroups != null && !evaluationGroups.isEmpty();
            case GrlPackage.GR_LSPEC__SCENARIOS:
                return scenarios != null && !scenarios.isEmpty();
        }
        return eDynamicIsSet(eFeature);
    }

} //GRLspecImpl
