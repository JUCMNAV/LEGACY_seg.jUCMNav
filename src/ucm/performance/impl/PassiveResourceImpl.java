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

import ucm.performance.PassiveResource;
import ucm.performance.PerformancePackage;

import urncore.ComponentElement;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Passive Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.performance.impl.PassiveResourceImpl#getComponent <em>Component</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PassiveResourceImpl extends GeneralResourceImpl implements PassiveResource {
    /**
     * The cached value of the '{@link #getComponent() <em>Component</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getComponent()
     * @generated
     * @ordered
     */
    protected ComponentElement component = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PassiveResourceImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return PerformancePackage.eINSTANCE.getPassiveResource();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ComponentElement getComponent() {
        if (component != null && component.eIsProxy()) {
            ComponentElement oldComponent = component;
            component = (ComponentElement)eResolveProxy((InternalEObject)component);
            if (component != oldComponent) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PerformancePackage.PASSIVE_RESOURCE__COMPONENT, oldComponent, component));
            }
        }
        return component;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ComponentElement basicGetComponent() {
        return component;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetComponent(ComponentElement newComponent, NotificationChain msgs) {
        ComponentElement oldComponent = component;
        component = newComponent;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PerformancePackage.PASSIVE_RESOURCE__COMPONENT, oldComponent, newComponent);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setComponent(ComponentElement newComponent) {
        if (newComponent != component) {
            NotificationChain msgs = null;
            if (component != null)
                msgs = ((InternalEObject)component).eInverseRemove(this, UrncorePackage.COMPONENT_ELEMENT__RESOURCE, ComponentElement.class, msgs);
            if (newComponent != null)
                msgs = ((InternalEObject)newComponent).eInverseAdd(this, UrncorePackage.COMPONENT_ELEMENT__RESOURCE, ComponentElement.class, msgs);
            msgs = basicSetComponent(newComponent, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.PASSIVE_RESOURCE__COMPONENT, newComponent, newComponent));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case PerformancePackage.PASSIVE_RESOURCE__DEMANDS:
                    return ((InternalEList)getDemands()).basicAdd(otherEnd, msgs);
                case PerformancePackage.PASSIVE_RESOURCE__COMPONENT:
                    if (component != null)
                        msgs = ((InternalEObject)component).eInverseRemove(this, UrncorePackage.COMPONENT_ELEMENT__RESOURCE, ComponentElement.class, msgs);
                    return basicSetComponent((ComponentElement)otherEnd, msgs);
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
                case PerformancePackage.PASSIVE_RESOURCE__DEMANDS:
                    return ((InternalEList)getDemands()).basicRemove(otherEnd, msgs);
                case PerformancePackage.PASSIVE_RESOURCE__COMPONENT:
                    return basicSetComponent(null, msgs);
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
            case PerformancePackage.PASSIVE_RESOURCE__DEMANDS:
                return getDemands();
            case PerformancePackage.PASSIVE_RESOURCE__COMPONENT:
                if (resolve) return getComponent();
                return basicGetComponent();
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
            case PerformancePackage.PASSIVE_RESOURCE__DEMANDS:
                getDemands().clear();
                getDemands().addAll((Collection)newValue);
                return;
            case PerformancePackage.PASSIVE_RESOURCE__COMPONENT:
                setComponent((ComponentElement)newValue);
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
            case PerformancePackage.PASSIVE_RESOURCE__DEMANDS:
                getDemands().clear();
                return;
            case PerformancePackage.PASSIVE_RESOURCE__COMPONENT:
                setComponent((ComponentElement)null);
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
            case PerformancePackage.PASSIVE_RESOURCE__DEMANDS:
                return demands != null && !demands.isEmpty();
            case PerformancePackage.PASSIVE_RESOURCE__COMPONENT:
                return component != null;
        }
        return eDynamicIsSet(eFeature);
    }

} //PassiveResourceImpl
