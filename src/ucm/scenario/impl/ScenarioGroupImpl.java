/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.scenario.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

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
 *   <li>{@link ucm.scenario.impl.ScenarioGroupImpl#getScenarioDef <em>Scenario Def</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScenarioGroupImpl extends UCMmodelElementImpl implements ScenarioGroup {
    /**
     * The cached value of the '{@link #getScenarioDef() <em>Scenario Def</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getScenarioDef()
     * @generated
     * @ordered
     */
    protected EList scenarioDef = null;

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
    public EList getScenarioDef() {
        if (scenarioDef == null) {
            scenarioDef = new EObjectContainmentEList(ScenarioDef.class, this, ScenarioPackage.SCENARIO_GROUP__SCENARIO_DEF);
        }
        return scenarioDef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case ScenarioPackage.SCENARIO_GROUP__URN_LINKS:
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
                case ScenarioPackage.SCENARIO_GROUP__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
                case ScenarioPackage.SCENARIO_GROUP__SCENARIO_DEF:
                    return ((InternalEList)getScenarioDef()).basicRemove(otherEnd, msgs);
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
            case ScenarioPackage.SCENARIO_GROUP__URN_LINKS:
                return getUrnLinks();
            case ScenarioPackage.SCENARIO_GROUP__ID:
                return getId();
            case ScenarioPackage.SCENARIO_GROUP__NAME:
                return getName();
            case ScenarioPackage.SCENARIO_GROUP__DESCRIPTION:
                return getDescription();
            case ScenarioPackage.SCENARIO_GROUP__SCENARIO_DEF:
                return getScenarioDef();
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
            case ScenarioPackage.SCENARIO_GROUP__URN_LINKS:
                getUrnLinks().clear();
                getUrnLinks().addAll((Collection)newValue);
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
            case ScenarioPackage.SCENARIO_GROUP__SCENARIO_DEF:
                getScenarioDef().clear();
                getScenarioDef().addAll((Collection)newValue);
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
            case ScenarioPackage.SCENARIO_GROUP__URN_LINKS:
                getUrnLinks().clear();
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
            case ScenarioPackage.SCENARIO_GROUP__SCENARIO_DEF:
                getScenarioDef().clear();
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
            case ScenarioPackage.SCENARIO_GROUP__URN_LINKS:
                return urnLinks != null && !urnLinks.isEmpty();
            case ScenarioPackage.SCENARIO_GROUP__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case ScenarioPackage.SCENARIO_GROUP__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case ScenarioPackage.SCENARIO_GROUP__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case ScenarioPackage.SCENARIO_GROUP__SCENARIO_DEF:
                return scenarioDef != null && !scenarioDef.isEmpty();
        }
        return eDynamicIsSet(eFeature);
    }

} //ScenarioGroupImpl
