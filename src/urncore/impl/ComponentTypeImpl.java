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
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import urncore.Component;
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
		return UrncorePackage.Literals.COMPONENT_TYPE;
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
			InternalEObject oldSuperType = (InternalEObject)superType;
			superType = (ComponentType)eResolveProxy(oldSuperType);
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
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
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
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UrncorePackage.COMPONENT_TYPE__SUB_TYPE:
				return ((InternalEList)getSubType()).basicRemove(otherEnd, msgs);
			case UrncorePackage.COMPONENT_TYPE__SUPER_TYPE:
				return basicSetSuperType(null, msgs);
			case UrncorePackage.COMPONENT_TYPE__INSTANCES:
				return ((InternalEList)getInstances()).basicRemove(otherEnd, msgs);
			case UrncorePackage.COMPONENT_TYPE__POOLS:
				return ((InternalEList)getPools()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
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
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
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
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
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
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case UrncorePackage.COMPONENT_TYPE__SUB_TYPE:
				return subType != null && !subType.isEmpty();
			case UrncorePackage.COMPONENT_TYPE__SUPER_TYPE:
				return superType != null;
			case UrncorePackage.COMPONENT_TYPE__INSTANCES:
				return instances != null && !instances.isEmpty();
			case UrncorePackage.COMPONENT_TYPE__POOLS:
				return pools != null && !pools.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ComponentTypeImpl
