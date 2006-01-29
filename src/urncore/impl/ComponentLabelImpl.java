/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import urncore.ComponentLabel;
import urncore.IURNContainerRef;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component Label</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link urncore.impl.ComponentLabelImpl#getContRef <em>Cont Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComponentLabelImpl extends LabelImpl implements ComponentLabel {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ComponentLabelImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return UrncorePackage.eINSTANCE.getComponentLabel();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IURNContainerRef getContRef() {
        if (eContainerFeatureID != UrncorePackage.COMPONENT_LABEL__CONT_REF) return null;
        return (IURNContainerRef)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setContRef(IURNContainerRef newContRef) {
        if (newContRef != eContainer || (eContainerFeatureID != UrncorePackage.COMPONENT_LABEL__CONT_REF && newContRef != null)) {
            if (EcoreUtil.isAncestor(this, newContRef))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newContRef != null)
                msgs = ((InternalEObject)newContRef).eInverseAdd(this, UrncorePackage.IURN_CONTAINER_REF__LABEL, IURNContainerRef.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newContRef, UrncorePackage.COMPONENT_LABEL__CONT_REF, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT_LABEL__CONT_REF, newContRef, newContRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case UrncorePackage.COMPONENT_LABEL__CONT_REF:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, UrncorePackage.COMPONENT_LABEL__CONT_REF, msgs);
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
                case UrncorePackage.COMPONENT_LABEL__CONT_REF:
                    return eBasicSetContainer(null, UrncorePackage.COMPONENT_LABEL__CONT_REF, msgs);
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
                case UrncorePackage.COMPONENT_LABEL__CONT_REF:
                    return eContainer.eInverseRemove(this, UrncorePackage.IURN_CONTAINER_REF__LABEL, IURNContainerRef.class, msgs);
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
            case UrncorePackage.COMPONENT_LABEL__DELTA_X:
                return new Integer(getDeltaX());
            case UrncorePackage.COMPONENT_LABEL__DELTA_Y:
                return new Integer(getDeltaY());
            case UrncorePackage.COMPONENT_LABEL__CONT_REF:
                return getContRef();
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
            case UrncorePackage.COMPONENT_LABEL__DELTA_X:
                setDeltaX(((Integer)newValue).intValue());
                return;
            case UrncorePackage.COMPONENT_LABEL__DELTA_Y:
                setDeltaY(((Integer)newValue).intValue());
                return;
            case UrncorePackage.COMPONENT_LABEL__CONT_REF:
                setContRef((IURNContainerRef)newValue);
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
            case UrncorePackage.COMPONENT_LABEL__DELTA_X:
                setDeltaX(DELTA_X_EDEFAULT);
                return;
            case UrncorePackage.COMPONENT_LABEL__DELTA_Y:
                setDeltaY(DELTA_Y_EDEFAULT);
                return;
            case UrncorePackage.COMPONENT_LABEL__CONT_REF:
                setContRef((IURNContainerRef)null);
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
            case UrncorePackage.COMPONENT_LABEL__DELTA_X:
                return deltaX != DELTA_X_EDEFAULT;
            case UrncorePackage.COMPONENT_LABEL__DELTA_Y:
                return deltaY != DELTA_Y_EDEFAULT;
            case UrncorePackage.COMPONENT_LABEL__CONT_REF:
                return getContRef() != null;
        }
        return eDynamicIsSet(eFeature);
    }

} //ComponentLabelImpl
