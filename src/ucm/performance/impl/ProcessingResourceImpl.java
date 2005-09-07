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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import ucm.UCMspec;
import ucm.UcmPackage;

import ucm.performance.DeviceKind;
import ucm.performance.PerformancePackage;
import ucm.performance.ProcessingResource;

import urncore.ComponentRegular;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Processing Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.performance.impl.ProcessingResourceImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link ucm.performance.impl.ProcessingResourceImpl#getComponents <em>Components</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProcessingResourceImpl extends ActiveResourceImpl implements ProcessingResource {
    /**
     * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getKind()
     * @generated
     * @ordered
     */
	protected static final DeviceKind KIND_EDEFAULT = DeviceKind.PROCESSOR_LITERAL;

    /**
     * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getKind()
     * @generated
     * @ordered
     */
	protected DeviceKind kind = KIND_EDEFAULT;

    /**
     * The cached value of the '{@link #getComponents() <em>Components</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getComponents()
     * @generated
     * @ordered
     */
	protected EList components = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ProcessingResourceImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return PerformancePackage.eINSTANCE.getProcessingResource();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public DeviceKind getKind() {
        return kind;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setKind(DeviceKind newKind) {
        DeviceKind oldKind = kind;
        kind = newKind == null ? KIND_EDEFAULT : newKind;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.PROCESSING_RESOURCE__KIND, oldKind, kind));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getComponents() {
        if (components == null) {
            components = new EObjectWithInverseResolvingEList(ComponentRegular.class, this, PerformancePackage.PROCESSING_RESOURCE__COMPONENTS, UrncorePackage.COMPONENT_REGULAR__HOST);
        }
        return components;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case PerformancePackage.PROCESSING_RESOURCE__UCMSPEC:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, PerformancePackage.PROCESSING_RESOURCE__UCMSPEC, msgs);
                case PerformancePackage.PROCESSING_RESOURCE__DEMANDS:
                    return ((InternalEList)getDemands()).basicAdd(otherEnd, msgs);
                case PerformancePackage.PROCESSING_RESOURCE__COMPONENTS:
                    return ((InternalEList)getComponents()).basicAdd(otherEnd, msgs);
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
                case PerformancePackage.PROCESSING_RESOURCE__UCMSPEC:
                    return eBasicSetContainer(null, PerformancePackage.PROCESSING_RESOURCE__UCMSPEC, msgs);
                case PerformancePackage.PROCESSING_RESOURCE__DEMANDS:
                    return ((InternalEList)getDemands()).basicRemove(otherEnd, msgs);
                case PerformancePackage.PROCESSING_RESOURCE__COMPONENTS:
                    return ((InternalEList)getComponents()).basicRemove(otherEnd, msgs);
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
                case PerformancePackage.PROCESSING_RESOURCE__UCMSPEC:
                    return eContainer.eInverseRemove(this, UcmPackage.UC_MSPEC__RESOURCES, UCMspec.class, msgs);
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
            case PerformancePackage.PROCESSING_RESOURCE__UCMSPEC:
                return getUcmspec();
            case PerformancePackage.PROCESSING_RESOURCE__DEMANDS:
                return getDemands();
            case PerformancePackage.PROCESSING_RESOURCE__OP_TIME:
                return new Double(getOpTime());
            case PerformancePackage.PROCESSING_RESOURCE__KIND:
                return getKind();
            case PerformancePackage.PROCESSING_RESOURCE__COMPONENTS:
                return getComponents();
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
            case PerformancePackage.PROCESSING_RESOURCE__UCMSPEC:
                setUcmspec((UCMspec)newValue);
                return;
            case PerformancePackage.PROCESSING_RESOURCE__DEMANDS:
                getDemands().clear();
                getDemands().addAll((Collection)newValue);
                return;
            case PerformancePackage.PROCESSING_RESOURCE__OP_TIME:
                setOpTime(((Double)newValue).doubleValue());
                return;
            case PerformancePackage.PROCESSING_RESOURCE__KIND:
                setKind((DeviceKind)newValue);
                return;
            case PerformancePackage.PROCESSING_RESOURCE__COMPONENTS:
                getComponents().clear();
                getComponents().addAll((Collection)newValue);
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
            case PerformancePackage.PROCESSING_RESOURCE__UCMSPEC:
                setUcmspec((UCMspec)null);
                return;
            case PerformancePackage.PROCESSING_RESOURCE__DEMANDS:
                getDemands().clear();
                return;
            case PerformancePackage.PROCESSING_RESOURCE__OP_TIME:
                setOpTime(OP_TIME_EDEFAULT);
                return;
            case PerformancePackage.PROCESSING_RESOURCE__KIND:
                setKind(KIND_EDEFAULT);
                return;
            case PerformancePackage.PROCESSING_RESOURCE__COMPONENTS:
                getComponents().clear();
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
            case PerformancePackage.PROCESSING_RESOURCE__UCMSPEC:
                return getUcmspec() != null;
            case PerformancePackage.PROCESSING_RESOURCE__DEMANDS:
                return demands != null && !demands.isEmpty();
            case PerformancePackage.PROCESSING_RESOURCE__OP_TIME:
                return opTime != OP_TIME_EDEFAULT;
            case PerformancePackage.PROCESSING_RESOURCE__KIND:
                return kind != KIND_EDEFAULT;
            case PerformancePackage.PROCESSING_RESOURCE__COMPONENTS:
                return components != null && !components.isEmpty();
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
        result.append(" (kind: ");
        result.append(kind);
        result.append(')');
        return result.toString();
    }

} //ProcessingResourceImpl
