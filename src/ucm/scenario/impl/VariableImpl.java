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

import ucm.scenario.ScenarioPackage;
import ucm.scenario.Variable;

import urncore.Condition;
import urncore.UrncorePackage;

import urncore.impl.UCMmodelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.scenario.impl.VariableImpl#getType <em>Type</em>}</li>
 *   <li>{@link ucm.scenario.impl.VariableImpl#getUcmspec <em>Ucmspec</em>}</li>
 *   <li>{@link ucm.scenario.impl.VariableImpl#getUsages <em>Usages</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VariableImpl extends UCMmodelElementImpl implements Variable {
    /**
     * The default value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected static final String TYPE_EDEFAULT = "boolean";

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected String type = TYPE_EDEFAULT;

    /**
     * The cached value of the '{@link #getUsages() <em>Usages</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUsages()
     * @generated
     * @ordered
     */
    protected EList usages = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected VariableImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return ScenarioPackage.eINSTANCE.getVariable();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getType() {
        return type;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setType(String newType) {
        String oldType = type;
        type = newType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.VARIABLE__TYPE, oldType, type));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UCMspec getUcmspec() {
        if (eContainerFeatureID != ScenarioPackage.VARIABLE__UCMSPEC) return null;
        return (UCMspec)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUcmspec(UCMspec newUcmspec) {
        if (newUcmspec != eContainer || (eContainerFeatureID != ScenarioPackage.VARIABLE__UCMSPEC && newUcmspec != null)) {
            if (EcoreUtil.isAncestor(this, newUcmspec))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newUcmspec != null)
                msgs = ((InternalEObject)newUcmspec).eInverseAdd(this, UcmPackage.UC_MSPEC__VARIABLES, UCMspec.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newUcmspec, ScenarioPackage.VARIABLE__UCMSPEC, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.VARIABLE__UCMSPEC, newUcmspec, newUcmspec));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getUsages() {
        if (usages == null) {
            usages = new EObjectWithInverseResolvingEList.ManyInverse(Condition.class, this, ScenarioPackage.VARIABLE__USAGES, UrncorePackage.CONDITION__VARIABLES);
        }
        return usages;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case ScenarioPackage.VARIABLE__FROM_LINKS:
                    return ((InternalEList)getFromLinks()).basicAdd(otherEnd, msgs);
                case ScenarioPackage.VARIABLE__TO_LINKS:
                    return ((InternalEList)getToLinks()).basicAdd(otherEnd, msgs);
                case ScenarioPackage.VARIABLE__UCMSPEC:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, ScenarioPackage.VARIABLE__UCMSPEC, msgs);
                case ScenarioPackage.VARIABLE__USAGES:
                    return ((InternalEList)getUsages()).basicAdd(otherEnd, msgs);
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
                case ScenarioPackage.VARIABLE__FROM_LINKS:
                    return ((InternalEList)getFromLinks()).basicRemove(otherEnd, msgs);
                case ScenarioPackage.VARIABLE__TO_LINKS:
                    return ((InternalEList)getToLinks()).basicRemove(otherEnd, msgs);
                case ScenarioPackage.VARIABLE__UCMSPEC:
                    return eBasicSetContainer(null, ScenarioPackage.VARIABLE__UCMSPEC, msgs);
                case ScenarioPackage.VARIABLE__USAGES:
                    return ((InternalEList)getUsages()).basicRemove(otherEnd, msgs);
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
                case ScenarioPackage.VARIABLE__UCMSPEC:
                    return eContainer.eInverseRemove(this, UcmPackage.UC_MSPEC__VARIABLES, UCMspec.class, msgs);
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
            case ScenarioPackage.VARIABLE__FROM_LINKS:
                return getFromLinks();
            case ScenarioPackage.VARIABLE__TO_LINKS:
                return getToLinks();
            case ScenarioPackage.VARIABLE__ID:
                return getId();
            case ScenarioPackage.VARIABLE__NAME:
                return getName();
            case ScenarioPackage.VARIABLE__DESCRIPTION:
                return getDescription();
            case ScenarioPackage.VARIABLE__TYPE:
                return getType();
            case ScenarioPackage.VARIABLE__UCMSPEC:
                return getUcmspec();
            case ScenarioPackage.VARIABLE__USAGES:
                return getUsages();
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
            case ScenarioPackage.VARIABLE__FROM_LINKS:
                getFromLinks().clear();
                getFromLinks().addAll((Collection)newValue);
                return;
            case ScenarioPackage.VARIABLE__TO_LINKS:
                getToLinks().clear();
                getToLinks().addAll((Collection)newValue);
                return;
            case ScenarioPackage.VARIABLE__ID:
                setId((String)newValue);
                return;
            case ScenarioPackage.VARIABLE__NAME:
                setName((String)newValue);
                return;
            case ScenarioPackage.VARIABLE__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case ScenarioPackage.VARIABLE__TYPE:
                setType((String)newValue);
                return;
            case ScenarioPackage.VARIABLE__UCMSPEC:
                setUcmspec((UCMspec)newValue);
                return;
            case ScenarioPackage.VARIABLE__USAGES:
                getUsages().clear();
                getUsages().addAll((Collection)newValue);
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
            case ScenarioPackage.VARIABLE__FROM_LINKS:
                getFromLinks().clear();
                return;
            case ScenarioPackage.VARIABLE__TO_LINKS:
                getToLinks().clear();
                return;
            case ScenarioPackage.VARIABLE__ID:
                setId(ID_EDEFAULT);
                return;
            case ScenarioPackage.VARIABLE__NAME:
                setName(NAME_EDEFAULT);
                return;
            case ScenarioPackage.VARIABLE__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case ScenarioPackage.VARIABLE__TYPE:
                setType(TYPE_EDEFAULT);
                return;
            case ScenarioPackage.VARIABLE__UCMSPEC:
                setUcmspec((UCMspec)null);
                return;
            case ScenarioPackage.VARIABLE__USAGES:
                getUsages().clear();
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
            case ScenarioPackage.VARIABLE__FROM_LINKS:
                return fromLinks != null && !fromLinks.isEmpty();
            case ScenarioPackage.VARIABLE__TO_LINKS:
                return toLinks != null && !toLinks.isEmpty();
            case ScenarioPackage.VARIABLE__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case ScenarioPackage.VARIABLE__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case ScenarioPackage.VARIABLE__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case ScenarioPackage.VARIABLE__TYPE:
                return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
            case ScenarioPackage.VARIABLE__UCMSPEC:
                return getUcmspec() != null;
            case ScenarioPackage.VARIABLE__USAGES:
                return usages != null && !usages.isEmpty();
        }
        return eDynamicIsSet(eFeature);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (type: ");
        result.append(type);
        result.append(')');
        return result.toString();
    }

} //VariableImpl
