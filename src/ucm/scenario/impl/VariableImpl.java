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
import org.eclipse.emf.ecore.util.InternalEList;

import ucm.map.Condition;
import ucm.map.MapPackage;

import ucm.scenario.ScenarioPackage;
import ucm.scenario.Variable;

import urncore.impl.UCMmodelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.scenario.impl.VariableImpl#getType <em>Type</em>}</li>
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
    public EList getUsages() {
        if (usages == null) {
            usages = new EObjectWithInverseResolvingEList.ManyInverse(Condition.class, this, ScenarioPackage.VARIABLE__USAGES, MapPackage.CONDITION__VARIABLES);
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
                case ScenarioPackage.VARIABLE__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicAdd(otherEnd, msgs);
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
                case ScenarioPackage.VARIABLE__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
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
    public Object eGet(EStructuralFeature eFeature, boolean resolve) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case ScenarioPackage.VARIABLE__URN_LINKS:
                return getUrnLinks();
            case ScenarioPackage.VARIABLE__ID:
                return getId();
            case ScenarioPackage.VARIABLE__NAME:
                return getName();
            case ScenarioPackage.VARIABLE__DESCRIPTION:
                return getDescription();
            case ScenarioPackage.VARIABLE__TYPE:
                return getType();
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
            case ScenarioPackage.VARIABLE__URN_LINKS:
                getUrnLinks().clear();
                getUrnLinks().addAll((Collection)newValue);
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
            case ScenarioPackage.VARIABLE__URN_LINKS:
                getUrnLinks().clear();
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
            case ScenarioPackage.VARIABLE__URN_LINKS:
                return urnLinks != null && !urnLinks.isEmpty();
            case ScenarioPackage.VARIABLE__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case ScenarioPackage.VARIABLE__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case ScenarioPackage.VARIABLE__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case ScenarioPackage.VARIABLE__TYPE:
                return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
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
