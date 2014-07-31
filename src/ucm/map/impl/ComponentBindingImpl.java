/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import ucm.map.ComponentBinding;
import ucm.map.ComponentRef;
import ucm.map.MapPackage;
import ucm.map.PluginBinding;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component Binding</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.map.impl.ComponentBindingImpl#getBinding <em>Binding</em>}</li>
 *   <li>{@link ucm.map.impl.ComponentBindingImpl#getParentComponent <em>Parent Component</em>}</li>
 *   <li>{@link ucm.map.impl.ComponentBindingImpl#getPluginComponent <em>Plugin Component</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComponentBindingImpl extends MinimalEObjectImpl.Container implements ComponentBinding {
	/**
	 * The cached value of the '{@link #getParentComponent() <em>Parent Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentComponent()
	 * @generated
	 * @ordered
	 */
	protected ComponentRef parentComponent;

	/**
	 * The cached value of the '{@link #getPluginComponent() <em>Plugin Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPluginComponent()
	 * @generated
	 * @ordered
	 */
	protected ComponentRef pluginComponent;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentBindingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return MapPackage.Literals.COMPONENT_BINDING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PluginBinding getBinding() {
		if (eContainerFeatureID() != MapPackage.COMPONENT_BINDING__BINDING) return null;
		return (PluginBinding)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBinding(PluginBinding newBinding, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newBinding, MapPackage.COMPONENT_BINDING__BINDING, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBinding(PluginBinding newBinding) {
		if (newBinding != eInternalContainer() || (eContainerFeatureID() != MapPackage.COMPONENT_BINDING__BINDING && newBinding != null)) {
			if (EcoreUtil.isAncestor(this, newBinding))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newBinding != null)
				msgs = ((InternalEObject)newBinding).eInverseAdd(this, MapPackage.PLUGIN_BINDING__COMPONENTS, PluginBinding.class, msgs);
			msgs = basicSetBinding(newBinding, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.COMPONENT_BINDING__BINDING, newBinding, newBinding));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentRef getParentComponent() {
		if (parentComponent != null && parentComponent.eIsProxy()) {
			InternalEObject oldParentComponent = (InternalEObject)parentComponent;
			parentComponent = (ComponentRef)eResolveProxy(oldParentComponent);
			if (parentComponent != oldParentComponent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MapPackage.COMPONENT_BINDING__PARENT_COMPONENT, oldParentComponent, parentComponent));
			}
		}
		return parentComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentRef basicGetParentComponent() {
		return parentComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParentComponent(ComponentRef newParentComponent, NotificationChain msgs) {
		ComponentRef oldParentComponent = parentComponent;
		parentComponent = newParentComponent;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.COMPONENT_BINDING__PARENT_COMPONENT, oldParentComponent, newParentComponent);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentComponent(ComponentRef newParentComponent) {
		if (newParentComponent != parentComponent) {
			NotificationChain msgs = null;
			if (parentComponent != null)
				msgs = ((InternalEObject)parentComponent).eInverseRemove(this, MapPackage.COMPONENT_REF__PARENT_BINDINGS, ComponentRef.class, msgs);
			if (newParentComponent != null)
				msgs = ((InternalEObject)newParentComponent).eInverseAdd(this, MapPackage.COMPONENT_REF__PARENT_BINDINGS, ComponentRef.class, msgs);
			msgs = basicSetParentComponent(newParentComponent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.COMPONENT_BINDING__PARENT_COMPONENT, newParentComponent, newParentComponent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentRef getPluginComponent() {
		if (pluginComponent != null && pluginComponent.eIsProxy()) {
			InternalEObject oldPluginComponent = (InternalEObject)pluginComponent;
			pluginComponent = (ComponentRef)eResolveProxy(oldPluginComponent);
			if (pluginComponent != oldPluginComponent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MapPackage.COMPONENT_BINDING__PLUGIN_COMPONENT, oldPluginComponent, pluginComponent));
			}
		}
		return pluginComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentRef basicGetPluginComponent() {
		return pluginComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPluginComponent(ComponentRef newPluginComponent, NotificationChain msgs) {
		ComponentRef oldPluginComponent = pluginComponent;
		pluginComponent = newPluginComponent;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.COMPONENT_BINDING__PLUGIN_COMPONENT, oldPluginComponent, newPluginComponent);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPluginComponent(ComponentRef newPluginComponent) {
		if (newPluginComponent != pluginComponent) {
			NotificationChain msgs = null;
			if (pluginComponent != null)
				msgs = ((InternalEObject)pluginComponent).eInverseRemove(this, MapPackage.COMPONENT_REF__PLUGIN_BINDINGS, ComponentRef.class, msgs);
			if (newPluginComponent != null)
				msgs = ((InternalEObject)newPluginComponent).eInverseAdd(this, MapPackage.COMPONENT_REF__PLUGIN_BINDINGS, ComponentRef.class, msgs);
			msgs = basicSetPluginComponent(newPluginComponent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.COMPONENT_BINDING__PLUGIN_COMPONENT, newPluginComponent, newPluginComponent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MapPackage.COMPONENT_BINDING__BINDING:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetBinding((PluginBinding)otherEnd, msgs);
			case MapPackage.COMPONENT_BINDING__PARENT_COMPONENT:
				if (parentComponent != null)
					msgs = ((InternalEObject)parentComponent).eInverseRemove(this, MapPackage.COMPONENT_REF__PARENT_BINDINGS, ComponentRef.class, msgs);
				return basicSetParentComponent((ComponentRef)otherEnd, msgs);
			case MapPackage.COMPONENT_BINDING__PLUGIN_COMPONENT:
				if (pluginComponent != null)
					msgs = ((InternalEObject)pluginComponent).eInverseRemove(this, MapPackage.COMPONENT_REF__PLUGIN_BINDINGS, ComponentRef.class, msgs);
				return basicSetPluginComponent((ComponentRef)otherEnd, msgs);
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
			case MapPackage.COMPONENT_BINDING__BINDING:
				return basicSetBinding(null, msgs);
			case MapPackage.COMPONENT_BINDING__PARENT_COMPONENT:
				return basicSetParentComponent(null, msgs);
			case MapPackage.COMPONENT_BINDING__PLUGIN_COMPONENT:
				return basicSetPluginComponent(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case MapPackage.COMPONENT_BINDING__BINDING:
				return eInternalContainer().eInverseRemove(this, MapPackage.PLUGIN_BINDING__COMPONENTS, PluginBinding.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MapPackage.COMPONENT_BINDING__BINDING:
				return getBinding();
			case MapPackage.COMPONENT_BINDING__PARENT_COMPONENT:
				if (resolve) return getParentComponent();
				return basicGetParentComponent();
			case MapPackage.COMPONENT_BINDING__PLUGIN_COMPONENT:
				if (resolve) return getPluginComponent();
				return basicGetPluginComponent();
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
			case MapPackage.COMPONENT_BINDING__BINDING:
				setBinding((PluginBinding)newValue);
				return;
			case MapPackage.COMPONENT_BINDING__PARENT_COMPONENT:
				setParentComponent((ComponentRef)newValue);
				return;
			case MapPackage.COMPONENT_BINDING__PLUGIN_COMPONENT:
				setPluginComponent((ComponentRef)newValue);
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
			case MapPackage.COMPONENT_BINDING__BINDING:
				setBinding((PluginBinding)null);
				return;
			case MapPackage.COMPONENT_BINDING__PARENT_COMPONENT:
				setParentComponent((ComponentRef)null);
				return;
			case MapPackage.COMPONENT_BINDING__PLUGIN_COMPONENT:
				setPluginComponent((ComponentRef)null);
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
			case MapPackage.COMPONENT_BINDING__BINDING:
				return getBinding() != null;
			case MapPackage.COMPONENT_BINDING__PARENT_COMPONENT:
				return parentComponent != null;
			case MapPackage.COMPONENT_BINDING__PLUGIN_COMPONENT:
				return pluginComponent != null;
		}
		return super.eIsSet(featureID);
	}

} //ComponentBindingImpl
