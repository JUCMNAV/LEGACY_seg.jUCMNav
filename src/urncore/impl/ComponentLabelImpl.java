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

import ucm.map.ComponentRef;
import ucm.map.MapPackage;

import urncore.ComponentLabel;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component Label</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link urncore.impl.ComponentLabelImpl#getCompRef <em>Comp Ref</em>}</li>
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
	public ComponentRef getCompRef() {
        if (eContainerFeatureID != UrncorePackage.COMPONENT_LABEL__COMP_REF) return null;
        return (ComponentRef)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setCompRef(ComponentRef newCompRef) {
        if (newCompRef != eContainer || (eContainerFeatureID != UrncorePackage.COMPONENT_LABEL__COMP_REF && newCompRef != null)) {
            if (EcoreUtil.isAncestor(this, newCompRef))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newCompRef != null)
                msgs = ((InternalEObject)newCompRef).eInverseAdd(this, MapPackage.COMPONENT_REF__LABEL, ComponentRef.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newCompRef, UrncorePackage.COMPONENT_LABEL__COMP_REF, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT_LABEL__COMP_REF, newCompRef, newCompRef));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case UrncorePackage.COMPONENT_LABEL__COMP_REF:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, UrncorePackage.COMPONENT_LABEL__COMP_REF, msgs);
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
                case UrncorePackage.COMPONENT_LABEL__COMP_REF:
                    return eBasicSetContainer(null, UrncorePackage.COMPONENT_LABEL__COMP_REF, msgs);
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
                case UrncorePackage.COMPONENT_LABEL__COMP_REF:
                    return eContainer.eInverseRemove(this, MapPackage.COMPONENT_REF__LABEL, ComponentRef.class, msgs);
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
            case UrncorePackage.COMPONENT_LABEL__COMP_REF:
                return getCompRef();
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
            case UrncorePackage.COMPONENT_LABEL__COMP_REF:
                setCompRef((ComponentRef)newValue);
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
            case UrncorePackage.COMPONENT_LABEL__COMP_REF:
                setCompRef((ComponentRef)null);
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
            case UrncorePackage.COMPONENT_LABEL__COMP_REF:
                return getCompRef() != null;
        }
        return eDynamicIsSet(eFeature);
    }

} //ComponentLabelImpl
