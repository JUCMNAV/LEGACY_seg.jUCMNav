/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore.impl;

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

import ucm.performance.PassiveResource;
import ucm.performance.PerformancePackage;
import ucm.performance.ProcessingResource;

import urncore.Component;
import urncore.ComponentKind;
import urncore.ComponentRegular;
import urncore.ComponentType;
import urncore.Pool;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link urncore.impl.ComponentTypeImpl#getSubType <em>Sub Type</em>}</li>
 *   <li>{@link urncore.impl.ComponentTypeImpl#getSuperType <em>Super Type</em>}</li>
 *   <li>{@link urncore.impl.ComponentTypeImpl#getInstances <em>Instances</em>}</li>
 *   <li>{@link urncore.impl.ComponentTypeImpl#getPools <em>Pools</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComponentTypeImpl extends ComponentRegularImpl implements ComponentType {
    /**
     * The cached value of the '{@link #getSubType() <em>Sub Type</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSubType()
     * @generated
     * @ordered
     */
    protected EList subType = null;

    /**
     * The cached value of the '{@link #getSuperType() <em>Super Type</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSuperType()
     * @generated
     * @ordered
     */
    protected ComponentType superType = null;

    /**
     * The cached value of the '{@link #getInstances() <em>Instances</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInstances()
     * @generated
     * @ordered
     */
    protected EList instances = null;

    /**
     * The cached value of the '{@link #getPools() <em>Pools</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPools()
     * @generated
     * @ordered
     */
    protected EList pools = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ComponentTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return UrncorePackage.eINSTANCE.getComponentType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getSubType() {
        if (subType == null) {
            subType = new EObjectWithInverseResolvingEList(ComponentType.class, this, UrncorePackage.COMPONENT_TYPE__SUB_TYPE, UrncorePackage.COMPONENT_TYPE__SUPER_TYPE);
        }
        return subType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ComponentType getSuperType() {
        if (superType != null && superType.eIsProxy()) {
            ComponentType oldSuperType = superType;
            superType = (ComponentType)eResolveProxy((InternalEObject)superType);
            if (superType != oldSuperType) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, UrncorePackage.COMPONENT_TYPE__SUPER_TYPE, oldSuperType, superType));
            }
        }
        return superType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ComponentType basicGetSuperType() {
        return superType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSuperType(ComponentType newSuperType, NotificationChain msgs) {
        ComponentType oldSuperType = superType;
        superType = newSuperType;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT_TYPE__SUPER_TYPE, oldSuperType, newSuperType);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSuperType(ComponentType newSuperType) {
        if (newSuperType != superType) {
            NotificationChain msgs = null;
            if (superType != null)
                msgs = ((InternalEObject)superType).eInverseRemove(this, UrncorePackage.COMPONENT_TYPE__SUB_TYPE, ComponentType.class, msgs);
            if (newSuperType != null)
                msgs = ((InternalEObject)newSuperType).eInverseAdd(this, UrncorePackage.COMPONENT_TYPE__SUB_TYPE, ComponentType.class, msgs);
            msgs = basicSetSuperType(newSuperType, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT_TYPE__SUPER_TYPE, newSuperType, newSuperType));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getInstances() {
        if (instances == null) {
            instances = new EObjectWithInverseResolvingEList(Component.class, this, UrncorePackage.COMPONENT_TYPE__INSTANCES, UrncorePackage.COMPONENT__TYPE);
        }
        return instances;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getPools() {
        if (pools == null) {
            pools = new EObjectWithInverseResolvingEList(Pool.class, this, UrncorePackage.COMPONENT_TYPE__POOLS, UrncorePackage.POOL__COMPONENT_TYPE);
        }
        return pools;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case UrncorePackage.COMPONENT_TYPE__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicAdd(otherEnd, msgs);
                case UrncorePackage.COMPONENT_TYPE__INCLUDING_COMPONENT:
                    if (includingComponent != null)
                        msgs = ((InternalEObject)includingComponent).eInverseRemove(this, UrncorePackage.COMPONENT_REGULAR__INCLUDED_COMPONENT, ComponentRegular.class, msgs);
                    return basicSetIncludingComponent((ComponentRegular)otherEnd, msgs);
                case UrncorePackage.COMPONENT_TYPE__RESOURCE:
                    if (resource != null)
                        msgs = ((InternalEObject)resource).eInverseRemove(this, PerformancePackage.PASSIVE_RESOURCE__COMPONENT, PassiveResource.class, msgs);
                    return basicSetResource((PassiveResource)otherEnd, msgs);
                case UrncorePackage.COMPONENT_TYPE__COMP_REFS:
                    return ((InternalEList)getCompRefs()).basicAdd(otherEnd, msgs);
                case UrncorePackage.COMPONENT_TYPE__INCLUDED_COMPONENT:
                    return ((InternalEList)getIncludedComponent()).basicAdd(otherEnd, msgs);
                case UrncorePackage.COMPONENT_TYPE__HOST:
                    if (host != null)
                        msgs = ((InternalEObject)host).eInverseRemove(this, PerformancePackage.PROCESSING_RESOURCE__COMPONENTS, ProcessingResource.class, msgs);
                    return basicSetHost((ProcessingResource)otherEnd, msgs);
                case UrncorePackage.COMPONENT_TYPE__SUB_TYPE:
                    return ((InternalEList)getSubType()).basicAdd(otherEnd, msgs);
                case UrncorePackage.COMPONENT_TYPE__SUPER_TYPE:
                    if (superType != null)
                        msgs = ((InternalEObject)superType).eInverseRemove(this, UrncorePackage.COMPONENT_TYPE__SUB_TYPE, ComponentType.class, msgs);
                    return basicSetSuperType((ComponentType)otherEnd, msgs);
                case UrncorePackage.COMPONENT_TYPE__INSTANCES:
                    return ((InternalEList)getInstances()).basicAdd(otherEnd, msgs);
                case UrncorePackage.COMPONENT_TYPE__POOLS:
                    return ((InternalEList)getPools()).basicAdd(otherEnd, msgs);
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
                case UrncorePackage.COMPONENT_TYPE__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
                case UrncorePackage.COMPONENT_TYPE__INCLUDING_COMPONENT:
                    return basicSetIncludingComponent(null, msgs);
                case UrncorePackage.COMPONENT_TYPE__RESOURCE:
                    return basicSetResource(null, msgs);
                case UrncorePackage.COMPONENT_TYPE__COMP_REFS:
                    return ((InternalEList)getCompRefs()).basicRemove(otherEnd, msgs);
                case UrncorePackage.COMPONENT_TYPE__INCLUDED_COMPONENT:
                    return ((InternalEList)getIncludedComponent()).basicRemove(otherEnd, msgs);
                case UrncorePackage.COMPONENT_TYPE__HOST:
                    return basicSetHost(null, msgs);
                case UrncorePackage.COMPONENT_TYPE__SUB_TYPE:
                    return ((InternalEList)getSubType()).basicRemove(otherEnd, msgs);
                case UrncorePackage.COMPONENT_TYPE__SUPER_TYPE:
                    return basicSetSuperType(null, msgs);
                case UrncorePackage.COMPONENT_TYPE__INSTANCES:
                    return ((InternalEList)getInstances()).basicRemove(otherEnd, msgs);
                case UrncorePackage.COMPONENT_TYPE__POOLS:
                    return ((InternalEList)getPools()).basicRemove(otherEnd, msgs);
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
            case UrncorePackage.COMPONENT_TYPE__URN_LINKS:
                return getUrnLinks();
            case UrncorePackage.COMPONENT_TYPE__ID:
                return getId();
            case UrncorePackage.COMPONENT_TYPE__NAME:
                return getName();
            case UrncorePackage.COMPONENT_TYPE__DESCRIPTION:
                return getDescription();
            case UrncorePackage.COMPONENT_TYPE__LINE_COLOR:
                return getLineColor();
            case UrncorePackage.COMPONENT_TYPE__FILL_COLOR:
                return getFillColor();
            case UrncorePackage.COMPONENT_TYPE__INCLUDING_COMPONENT:
                if (resolve) return getIncludingComponent();
                return basicGetIncludingComponent();
            case UrncorePackage.COMPONENT_TYPE__RESOURCE:
                if (resolve) return getResource();
                return basicGetResource();
            case UrncorePackage.COMPONENT_TYPE__COMP_REFS:
                return getCompRefs();
            case UrncorePackage.COMPONENT_TYPE__KIND:
                return getKind();
            case UrncorePackage.COMPONENT_TYPE__PROTECTED:
                return isProtected() ? Boolean.TRUE : Boolean.FALSE;
            case UrncorePackage.COMPONENT_TYPE__SLOT:
                return isSlot() ? Boolean.TRUE : Boolean.FALSE;
            case UrncorePackage.COMPONENT_TYPE__INCLUDED_COMPONENT:
                return getIncludedComponent();
            case UrncorePackage.COMPONENT_TYPE__HOST:
                if (resolve) return getHost();
                return basicGetHost();
            case UrncorePackage.COMPONENT_TYPE__SUB_TYPE:
                return getSubType();
            case UrncorePackage.COMPONENT_TYPE__SUPER_TYPE:
                if (resolve) return getSuperType();
                return basicGetSuperType();
            case UrncorePackage.COMPONENT_TYPE__INSTANCES:
                return getInstances();
            case UrncorePackage.COMPONENT_TYPE__POOLS:
                return getPools();
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
            case UrncorePackage.COMPONENT_TYPE__URN_LINKS:
                getUrnLinks().clear();
                getUrnLinks().addAll((Collection)newValue);
                return;
            case UrncorePackage.COMPONENT_TYPE__ID:
                setId((String)newValue);
                return;
            case UrncorePackage.COMPONENT_TYPE__NAME:
                setName((String)newValue);
                return;
            case UrncorePackage.COMPONENT_TYPE__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case UrncorePackage.COMPONENT_TYPE__LINE_COLOR:
                setLineColor((String)newValue);
                return;
            case UrncorePackage.COMPONENT_TYPE__FILL_COLOR:
                setFillColor((String)newValue);
                return;
            case UrncorePackage.COMPONENT_TYPE__INCLUDING_COMPONENT:
                setIncludingComponent((ComponentRegular)newValue);
                return;
            case UrncorePackage.COMPONENT_TYPE__RESOURCE:
                setResource((PassiveResource)newValue);
                return;
            case UrncorePackage.COMPONENT_TYPE__COMP_REFS:
                getCompRefs().clear();
                getCompRefs().addAll((Collection)newValue);
                return;
            case UrncorePackage.COMPONENT_TYPE__KIND:
                setKind((ComponentKind)newValue);
                return;
            case UrncorePackage.COMPONENT_TYPE__PROTECTED:
                setProtected(((Boolean)newValue).booleanValue());
                return;
            case UrncorePackage.COMPONENT_TYPE__SLOT:
                setSlot(((Boolean)newValue).booleanValue());
                return;
            case UrncorePackage.COMPONENT_TYPE__INCLUDED_COMPONENT:
                getIncludedComponent().clear();
                getIncludedComponent().addAll((Collection)newValue);
                return;
            case UrncorePackage.COMPONENT_TYPE__HOST:
                setHost((ProcessingResource)newValue);
                return;
            case UrncorePackage.COMPONENT_TYPE__SUB_TYPE:
                getSubType().clear();
                getSubType().addAll((Collection)newValue);
                return;
            case UrncorePackage.COMPONENT_TYPE__SUPER_TYPE:
                setSuperType((ComponentType)newValue);
                return;
            case UrncorePackage.COMPONENT_TYPE__INSTANCES:
                getInstances().clear();
                getInstances().addAll((Collection)newValue);
                return;
            case UrncorePackage.COMPONENT_TYPE__POOLS:
                getPools().clear();
                getPools().addAll((Collection)newValue);
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
            case UrncorePackage.COMPONENT_TYPE__URN_LINKS:
                getUrnLinks().clear();
                return;
            case UrncorePackage.COMPONENT_TYPE__ID:
                setId(ID_EDEFAULT);
                return;
            case UrncorePackage.COMPONENT_TYPE__NAME:
                setName(NAME_EDEFAULT);
                return;
            case UrncorePackage.COMPONENT_TYPE__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case UrncorePackage.COMPONENT_TYPE__LINE_COLOR:
                setLineColor(LINE_COLOR_EDEFAULT);
                return;
            case UrncorePackage.COMPONENT_TYPE__FILL_COLOR:
                setFillColor(FILL_COLOR_EDEFAULT);
                return;
            case UrncorePackage.COMPONENT_TYPE__INCLUDING_COMPONENT:
                setIncludingComponent((ComponentRegular)null);
                return;
            case UrncorePackage.COMPONENT_TYPE__RESOURCE:
                setResource((PassiveResource)null);
                return;
            case UrncorePackage.COMPONENT_TYPE__COMP_REFS:
                getCompRefs().clear();
                return;
            case UrncorePackage.COMPONENT_TYPE__KIND:
                setKind(KIND_EDEFAULT);
                return;
            case UrncorePackage.COMPONENT_TYPE__PROTECTED:
                setProtected(PROTECTED_EDEFAULT);
                return;
            case UrncorePackage.COMPONENT_TYPE__SLOT:
                setSlot(SLOT_EDEFAULT);
                return;
            case UrncorePackage.COMPONENT_TYPE__INCLUDED_COMPONENT:
                getIncludedComponent().clear();
                return;
            case UrncorePackage.COMPONENT_TYPE__HOST:
                setHost((ProcessingResource)null);
                return;
            case UrncorePackage.COMPONENT_TYPE__SUB_TYPE:
                getSubType().clear();
                return;
            case UrncorePackage.COMPONENT_TYPE__SUPER_TYPE:
                setSuperType((ComponentType)null);
                return;
            case UrncorePackage.COMPONENT_TYPE__INSTANCES:
                getInstances().clear();
                return;
            case UrncorePackage.COMPONENT_TYPE__POOLS:
                getPools().clear();
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
            case UrncorePackage.COMPONENT_TYPE__URN_LINKS:
                return urnLinks != null && !urnLinks.isEmpty();
            case UrncorePackage.COMPONENT_TYPE__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case UrncorePackage.COMPONENT_TYPE__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case UrncorePackage.COMPONENT_TYPE__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case UrncorePackage.COMPONENT_TYPE__LINE_COLOR:
                return LINE_COLOR_EDEFAULT == null ? lineColor != null : !LINE_COLOR_EDEFAULT.equals(lineColor);
            case UrncorePackage.COMPONENT_TYPE__FILL_COLOR:
                return FILL_COLOR_EDEFAULT == null ? fillColor != null : !FILL_COLOR_EDEFAULT.equals(fillColor);
            case UrncorePackage.COMPONENT_TYPE__INCLUDING_COMPONENT:
                return includingComponent != null;
            case UrncorePackage.COMPONENT_TYPE__RESOURCE:
                return resource != null;
            case UrncorePackage.COMPONENT_TYPE__COMP_REFS:
                return compRefs != null && !compRefs.isEmpty();
            case UrncorePackage.COMPONENT_TYPE__KIND:
                return kind != KIND_EDEFAULT;
            case UrncorePackage.COMPONENT_TYPE__PROTECTED:
                return protected_ != PROTECTED_EDEFAULT;
            case UrncorePackage.COMPONENT_TYPE__SLOT:
                return slot != SLOT_EDEFAULT;
            case UrncorePackage.COMPONENT_TYPE__INCLUDED_COMPONENT:
                return includedComponent != null && !includedComponent.isEmpty();
            case UrncorePackage.COMPONENT_TYPE__HOST:
                return host != null;
            case UrncorePackage.COMPONENT_TYPE__SUB_TYPE:
                return subType != null && !subType.isEmpty();
            case UrncorePackage.COMPONENT_TYPE__SUPER_TYPE:
                return superType != null;
            case UrncorePackage.COMPONENT_TYPE__INSTANCES:
                return instances != null && !instances.isEmpty();
            case UrncorePackage.COMPONENT_TYPE__POOLS:
                return pools != null && !pools.isEmpty();
        }
        return eDynamicIsSet(eFeature);
    }

} //ComponentTypeImpl
