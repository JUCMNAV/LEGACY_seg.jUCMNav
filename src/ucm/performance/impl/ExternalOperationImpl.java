/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.performance.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.InternalEList;

import ucm.performance.ExternalOperation;
import ucm.performance.PerformancePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>External Operation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.performance.impl.ExternalOperationImpl#getDescription <em>Description</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExternalOperationImpl extends ActiveResourceImpl implements ExternalOperation {
    /**
     * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected static final String DESCRIPTION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected String description = DESCRIPTION_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ExternalOperationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return PerformancePackage.eINSTANCE.getExternalOperation();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDescription() {
        return description;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDescription(String newDescription) {
        String oldDescription = description;
        description = newDescription;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.EXTERNAL_OPERATION__DESCRIPTION, oldDescription, description));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case PerformancePackage.EXTERNAL_OPERATION__DEMANDS:
                    return ((InternalEList)getDemands()).basicAdd(otherEnd, msgs);
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
                case PerformancePackage.EXTERNAL_OPERATION__DEMANDS:
                    return ((InternalEList)getDemands()).basicRemove(otherEnd, msgs);
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
            case PerformancePackage.EXTERNAL_OPERATION__DEMANDS:
                return getDemands();
            case PerformancePackage.EXTERNAL_OPERATION__OP_TIME:
                return new Double(getOpTime());
            case PerformancePackage.EXTERNAL_OPERATION__DESCRIPTION:
                return getDescription();
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
            case PerformancePackage.EXTERNAL_OPERATION__DEMANDS:
                getDemands().clear();
                getDemands().addAll((Collection)newValue);
                return;
            case PerformancePackage.EXTERNAL_OPERATION__OP_TIME:
                setOpTime(((Double)newValue).doubleValue());
                return;
            case PerformancePackage.EXTERNAL_OPERATION__DESCRIPTION:
                setDescription((String)newValue);
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
            case PerformancePackage.EXTERNAL_OPERATION__DEMANDS:
                getDemands().clear();
                return;
            case PerformancePackage.EXTERNAL_OPERATION__OP_TIME:
                setOpTime(OP_TIME_EDEFAULT);
                return;
            case PerformancePackage.EXTERNAL_OPERATION__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
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
            case PerformancePackage.EXTERNAL_OPERATION__DEMANDS:
                return demands != null && !demands.isEmpty();
            case PerformancePackage.EXTERNAL_OPERATION__OP_TIME:
                return opTime != OP_TIME_EDEFAULT;
            case PerformancePackage.EXTERNAL_OPERATION__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
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
        result.append(" (description: ");
        result.append(description);
        result.append(')');
        return result.toString();
    }

} //ExternalOperationImpl
