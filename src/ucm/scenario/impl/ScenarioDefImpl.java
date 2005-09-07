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

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import ucm.UCMspec;
import ucm.UcmPackage;

import ucm.map.StartPoint;

import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioPackage;

import urncore.impl.UCMmodelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Def</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.scenario.impl.ScenarioDefImpl#getStartPoints <em>Start Points</em>}</li>
 *   <li>{@link ucm.scenario.impl.ScenarioDefImpl#getUrnspec <em>Urnspec</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScenarioDefImpl extends UCMmodelElementImpl implements ScenarioDef {
    /**
     * The cached value of the '{@link #getStartPoints() <em>Start Points</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getStartPoints()
     * @generated
     * @ordered
     */
	protected EList startPoints = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ScenarioDefImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return ScenarioPackage.eINSTANCE.getScenarioDef();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getStartPoints() {
        if (startPoints == null) {
            startPoints = new EObjectResolvingEList(StartPoint.class, this, ScenarioPackage.SCENARIO_DEF__START_POINTS);
        }
        return startPoints;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public UCMspec getUrnspec() {
        if (eContainerFeatureID != ScenarioPackage.SCENARIO_DEF__URNSPEC) return null;
        return (UCMspec)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setUrnspec(UCMspec newUrnspec) {
        if (newUrnspec != eContainer || (eContainerFeatureID != ScenarioPackage.SCENARIO_DEF__URNSPEC && newUrnspec != null)) {
            if (EcoreUtil.isAncestor(this, newUrnspec))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newUrnspec != null)
                msgs = ((InternalEObject)newUrnspec).eInverseAdd(this, UcmPackage.UC_MSPEC__SCENARIO_DEFS, UCMspec.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newUrnspec, ScenarioPackage.SCENARIO_DEF__URNSPEC, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.SCENARIO_DEF__URNSPEC, newUrnspec, newUrnspec));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case ScenarioPackage.SCENARIO_DEF__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicAdd(otherEnd, msgs);
                case ScenarioPackage.SCENARIO_DEF__URNSPEC:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, ScenarioPackage.SCENARIO_DEF__URNSPEC, msgs);
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
                case ScenarioPackage.SCENARIO_DEF__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
                case ScenarioPackage.SCENARIO_DEF__URNSPEC:
                    return eBasicSetContainer(null, ScenarioPackage.SCENARIO_DEF__URNSPEC, msgs);
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
                case ScenarioPackage.SCENARIO_DEF__URNSPEC:
                    return eContainer.eInverseRemove(this, UcmPackage.UC_MSPEC__SCENARIO_DEFS, UCMspec.class, msgs);
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
            case ScenarioPackage.SCENARIO_DEF__URN_LINKS:
                return getUrnLinks();
            case ScenarioPackage.SCENARIO_DEF__ID:
                return getId();
            case ScenarioPackage.SCENARIO_DEF__NAME:
                return getName();
            case ScenarioPackage.SCENARIO_DEF__DESCRIPTION:
                return getDescription();
            case ScenarioPackage.SCENARIO_DEF__START_POINTS:
                return getStartPoints();
            case ScenarioPackage.SCENARIO_DEF__URNSPEC:
                return getUrnspec();
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
            case ScenarioPackage.SCENARIO_DEF__URN_LINKS:
                getUrnLinks().clear();
                getUrnLinks().addAll((Collection)newValue);
                return;
            case ScenarioPackage.SCENARIO_DEF__ID:
                setId((String)newValue);
                return;
            case ScenarioPackage.SCENARIO_DEF__NAME:
                setName((String)newValue);
                return;
            case ScenarioPackage.SCENARIO_DEF__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case ScenarioPackage.SCENARIO_DEF__START_POINTS:
                getStartPoints().clear();
                getStartPoints().addAll((Collection)newValue);
                return;
            case ScenarioPackage.SCENARIO_DEF__URNSPEC:
                setUrnspec((UCMspec)newValue);
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
            case ScenarioPackage.SCENARIO_DEF__URN_LINKS:
                getUrnLinks().clear();
                return;
            case ScenarioPackage.SCENARIO_DEF__ID:
                setId(ID_EDEFAULT);
                return;
            case ScenarioPackage.SCENARIO_DEF__NAME:
                setName(NAME_EDEFAULT);
                return;
            case ScenarioPackage.SCENARIO_DEF__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case ScenarioPackage.SCENARIO_DEF__START_POINTS:
                getStartPoints().clear();
                return;
            case ScenarioPackage.SCENARIO_DEF__URNSPEC:
                setUrnspec((UCMspec)null);
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
            case ScenarioPackage.SCENARIO_DEF__URN_LINKS:
                return urnLinks != null && !urnLinks.isEmpty();
            case ScenarioPackage.SCENARIO_DEF__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case ScenarioPackage.SCENARIO_DEF__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case ScenarioPackage.SCENARIO_DEF__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case ScenarioPackage.SCENARIO_DEF__START_POINTS:
                return startPoints != null && !startPoints.isEmpty();
            case ScenarioPackage.SCENARIO_DEF__URNSPEC:
                return getUrnspec() != null;
        }
        return eDynamicIsSet(eFeature);
    }

} //ScenarioDefImpl
