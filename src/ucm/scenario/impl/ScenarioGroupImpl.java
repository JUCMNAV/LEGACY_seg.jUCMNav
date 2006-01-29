/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.scenario.impl;

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

import ucm.UCMspec;
import ucm.UcmPackage;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;
import ucm.scenario.ScenarioPackage;
import urncore.impl.UCMmodelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.scenario.impl.ScenarioGroupImpl#getUcmspec <em>Ucmspec</em>}</li>
 *   <li>{@link ucm.scenario.impl.ScenarioGroupImpl#getScenarios <em>Scenarios</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScenarioGroupImpl extends UCMmodelElementImpl implements ScenarioGroup {
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
    protected ScenarioGroupImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return ScenarioPackage.eINSTANCE.getScenarioGroup();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UCMspec getUcmspec() {
        if (eContainerFeatureID != ScenarioPackage.SCENARIO_GROUP__UCMSPEC) return null;
        return (UCMspec)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUcmspec(UCMspec newUcmspec) {
        if (newUcmspec != eContainer || (eContainerFeatureID != ScenarioPackage.SCENARIO_GROUP__UCMSPEC && newUcmspec != null)) {
            if (EcoreUtil.isAncestor(this, newUcmspec))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newUcmspec != null)
                msgs = ((InternalEObject)newUcmspec).eInverseAdd(this, UcmPackage.UC_MSPEC__SCENARIO_GROUPS, UCMspec.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newUcmspec, ScenarioPackage.SCENARIO_GROUP__UCMSPEC, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.SCENARIO_GROUP__UCMSPEC, newUcmspec, newUcmspec));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getScenarios() {
        if (scenarios == null) {
            scenarios = new EObjectWithInverseResolvingEList.ManyInverse(ScenarioDef.class, this, ScenarioPackage.SCENARIO_GROUP__SCENARIOS, ScenarioPackage.SCENARIO_DEF__GROUPS);
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
                case ScenarioPackage.SCENARIO_GROUP__FROM_LINKS:
                    return ((InternalEList)getFromLinks()).basicAdd(otherEnd, msgs);
                case ScenarioPackage.SCENARIO_GROUP__TO_LINKS:
                    return ((InternalEList)getToLinks()).basicAdd(otherEnd, msgs);
                case ScenarioPackage.SCENARIO_GROUP__UCMSPEC:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, ScenarioPackage.SCENARIO_GROUP__UCMSPEC, msgs);
                case ScenarioPackage.SCENARIO_GROUP__SCENARIOS:
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
                case ScenarioPackage.SCENARIO_GROUP__FROM_LINKS:
                    return ((InternalEList)getFromLinks()).basicRemove(otherEnd, msgs);
                case ScenarioPackage.SCENARIO_GROUP__TO_LINKS:
                    return ((InternalEList)getToLinks()).basicRemove(otherEnd, msgs);
                case ScenarioPackage.SCENARIO_GROUP__UCMSPEC:
                    return eBasicSetContainer(null, ScenarioPackage.SCENARIO_GROUP__UCMSPEC, msgs);
                case ScenarioPackage.SCENARIO_GROUP__SCENARIOS:
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
                case ScenarioPackage.SCENARIO_GROUP__UCMSPEC:
                    return eContainer.eInverseRemove(this, UcmPackage.UC_MSPEC__SCENARIO_GROUPS, UCMspec.class, msgs);
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
            case ScenarioPackage.SCENARIO_GROUP__FROM_LINKS:
                return getFromLinks();
            case ScenarioPackage.SCENARIO_GROUP__TO_LINKS:
                return getToLinks();
            case ScenarioPackage.SCENARIO_GROUP__ID:
                return getId();
            case ScenarioPackage.SCENARIO_GROUP__NAME:
                return getName();
            case ScenarioPackage.SCENARIO_GROUP__DESCRIPTION:
                return getDescription();
            case ScenarioPackage.SCENARIO_GROUP__UCMSPEC:
                return getUcmspec();
            case ScenarioPackage.SCENARIO_GROUP__SCENARIOS:
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
            case ScenarioPackage.SCENARIO_GROUP__FROM_LINKS:
                getFromLinks().clear();
                getFromLinks().addAll((Collection)newValue);
                return;
            case ScenarioPackage.SCENARIO_GROUP__TO_LINKS:
                getToLinks().clear();
                getToLinks().addAll((Collection)newValue);
                return;
            case ScenarioPackage.SCENARIO_GROUP__ID:
                setId((String)newValue);
                return;
            case ScenarioPackage.SCENARIO_GROUP__NAME:
                setName((String)newValue);
                return;
            case ScenarioPackage.SCENARIO_GROUP__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case ScenarioPackage.SCENARIO_GROUP__UCMSPEC:
                setUcmspec((UCMspec)newValue);
                return;
            case ScenarioPackage.SCENARIO_GROUP__SCENARIOS:
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
            case ScenarioPackage.SCENARIO_GROUP__FROM_LINKS:
                getFromLinks().clear();
                return;
            case ScenarioPackage.SCENARIO_GROUP__TO_LINKS:
                getToLinks().clear();
                return;
            case ScenarioPackage.SCENARIO_GROUP__ID:
                setId(ID_EDEFAULT);
                return;
            case ScenarioPackage.SCENARIO_GROUP__NAME:
                setName(NAME_EDEFAULT);
                return;
            case ScenarioPackage.SCENARIO_GROUP__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case ScenarioPackage.SCENARIO_GROUP__UCMSPEC:
                setUcmspec((UCMspec)null);
                return;
            case ScenarioPackage.SCENARIO_GROUP__SCENARIOS:
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
            case ScenarioPackage.SCENARIO_GROUP__FROM_LINKS:
                return fromLinks != null && !fromLinks.isEmpty();
            case ScenarioPackage.SCENARIO_GROUP__TO_LINKS:
                return toLinks != null && !toLinks.isEmpty();
            case ScenarioPackage.SCENARIO_GROUP__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case ScenarioPackage.SCENARIO_GROUP__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case ScenarioPackage.SCENARIO_GROUP__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case ScenarioPackage.SCENARIO_GROUP__UCMSPEC:
                return getUcmspec() != null;
            case ScenarioPackage.SCENARIO_GROUP__SCENARIOS:
                return scenarios != null && !scenarios.isEmpty();
        }
        return eDynamicIsSet(eFeature);
    }

} //ScenarioGroupImpl
