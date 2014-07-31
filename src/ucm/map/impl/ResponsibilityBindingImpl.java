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
import ucm.map.MapPackage;
import ucm.map.PluginBinding;
import ucm.map.RespRef;
import ucm.map.ResponsibilityBinding;
import urncore.Responsibility;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Responsibility Binding</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.map.impl.ResponsibilityBindingImpl#getBinding <em>Binding</em>}</li>
 *   <li>{@link ucm.map.impl.ResponsibilityBindingImpl#getPluginResp <em>Plugin Resp</em>}</li>
 *   <li>{@link ucm.map.impl.ResponsibilityBindingImpl#getParentResp <em>Parent Resp</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResponsibilityBindingImpl extends MinimalEObjectImpl.Container implements ResponsibilityBinding {
    /**
	 * The cached value of the '{@link #getPluginResp() <em>Plugin Resp</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPluginResp()
	 * @generated
	 * @ordered
	 */
    protected RespRef pluginResp;

    /**
	 * The cached value of the '{@link #getParentResp() <em>Parent Resp</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getParentResp()
	 * @generated
	 * @ordered
	 */
    protected Responsibility parentResp;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected ResponsibilityBindingImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return MapPackage.Literals.RESPONSIBILITY_BINDING;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PluginBinding getBinding() {
		if (eContainerFeatureID() != MapPackage.RESPONSIBILITY_BINDING__BINDING) return null;
		return (PluginBinding)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetBinding(PluginBinding newBinding, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newBinding, MapPackage.RESPONSIBILITY_BINDING__BINDING, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setBinding(PluginBinding newBinding) {
		if (newBinding != eInternalContainer() || (eContainerFeatureID() != MapPackage.RESPONSIBILITY_BINDING__BINDING && newBinding != null)) {
			if (EcoreUtil.isAncestor(this, newBinding))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newBinding != null)
				msgs = ((InternalEObject)newBinding).eInverseAdd(this, MapPackage.PLUGIN_BINDING__RESPONSIBILITIES, PluginBinding.class, msgs);
			msgs = basicSetBinding(newBinding, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.RESPONSIBILITY_BINDING__BINDING, newBinding, newBinding));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Responsibility getParentResp() {
		if (parentResp != null && parentResp.eIsProxy()) {
			InternalEObject oldParentResp = (InternalEObject)parentResp;
			parentResp = (Responsibility)eResolveProxy(oldParentResp);
			if (parentResp != oldParentResp) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MapPackage.RESPONSIBILITY_BINDING__PARENT_RESP, oldParentResp, parentResp));
			}
		}
		return parentResp;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Responsibility basicGetParentResp() {
		return parentResp;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetParentResp(Responsibility newParentResp, NotificationChain msgs) {
		Responsibility oldParentResp = parentResp;
		parentResp = newParentResp;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.RESPONSIBILITY_BINDING__PARENT_RESP, oldParentResp, newParentResp);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setParentResp(Responsibility newParentResp) {
		if (newParentResp != parentResp) {
			NotificationChain msgs = null;
			if (parentResp != null)
				msgs = ((InternalEObject)parentResp).eInverseRemove(this, UrncorePackage.RESPONSIBILITY__PARENT_BINDINGS, Responsibility.class, msgs);
			if (newParentResp != null)
				msgs = ((InternalEObject)newParentResp).eInverseAdd(this, UrncorePackage.RESPONSIBILITY__PARENT_BINDINGS, Responsibility.class, msgs);
			msgs = basicSetParentResp(newParentResp, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.RESPONSIBILITY_BINDING__PARENT_RESP, newParentResp, newParentResp));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public RespRef getPluginResp() {
		if (pluginResp != null && pluginResp.eIsProxy()) {
			InternalEObject oldPluginResp = (InternalEObject)pluginResp;
			pluginResp = (RespRef)eResolveProxy(oldPluginResp);
			if (pluginResp != oldPluginResp) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MapPackage.RESPONSIBILITY_BINDING__PLUGIN_RESP, oldPluginResp, pluginResp));
			}
		}
		return pluginResp;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public RespRef basicGetPluginResp() {
		return pluginResp;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetPluginResp(RespRef newPluginResp, NotificationChain msgs) {
		RespRef oldPluginResp = pluginResp;
		pluginResp = newPluginResp;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.RESPONSIBILITY_BINDING__PLUGIN_RESP, oldPluginResp, newPluginResp);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setPluginResp(RespRef newPluginResp) {
		if (newPluginResp != pluginResp) {
			NotificationChain msgs = null;
			if (pluginResp != null)
				msgs = ((InternalEObject)pluginResp).eInverseRemove(this, MapPackage.RESP_REF__PLUGIN_BINDINGS, RespRef.class, msgs);
			if (newPluginResp != null)
				msgs = ((InternalEObject)newPluginResp).eInverseAdd(this, MapPackage.RESP_REF__PLUGIN_BINDINGS, RespRef.class, msgs);
			msgs = basicSetPluginResp(newPluginResp, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.RESPONSIBILITY_BINDING__PLUGIN_RESP, newPluginResp, newPluginResp));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MapPackage.RESPONSIBILITY_BINDING__BINDING:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetBinding((PluginBinding)otherEnd, msgs);
			case MapPackage.RESPONSIBILITY_BINDING__PLUGIN_RESP:
				if (pluginResp != null)
					msgs = ((InternalEObject)pluginResp).eInverseRemove(this, MapPackage.RESP_REF__PLUGIN_BINDINGS, RespRef.class, msgs);
				return basicSetPluginResp((RespRef)otherEnd, msgs);
			case MapPackage.RESPONSIBILITY_BINDING__PARENT_RESP:
				if (parentResp != null)
					msgs = ((InternalEObject)parentResp).eInverseRemove(this, UrncorePackage.RESPONSIBILITY__PARENT_BINDINGS, Responsibility.class, msgs);
				return basicSetParentResp((Responsibility)otherEnd, msgs);
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
			case MapPackage.RESPONSIBILITY_BINDING__BINDING:
				return basicSetBinding(null, msgs);
			case MapPackage.RESPONSIBILITY_BINDING__PLUGIN_RESP:
				return basicSetPluginResp(null, msgs);
			case MapPackage.RESPONSIBILITY_BINDING__PARENT_RESP:
				return basicSetParentResp(null, msgs);
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
			case MapPackage.RESPONSIBILITY_BINDING__BINDING:
				return eInternalContainer().eInverseRemove(this, MapPackage.PLUGIN_BINDING__RESPONSIBILITIES, PluginBinding.class, msgs);
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
			case MapPackage.RESPONSIBILITY_BINDING__BINDING:
				return getBinding();
			case MapPackage.RESPONSIBILITY_BINDING__PLUGIN_RESP:
				if (resolve) return getPluginResp();
				return basicGetPluginResp();
			case MapPackage.RESPONSIBILITY_BINDING__PARENT_RESP:
				if (resolve) return getParentResp();
				return basicGetParentResp();
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
			case MapPackage.RESPONSIBILITY_BINDING__BINDING:
				setBinding((PluginBinding)newValue);
				return;
			case MapPackage.RESPONSIBILITY_BINDING__PLUGIN_RESP:
				setPluginResp((RespRef)newValue);
				return;
			case MapPackage.RESPONSIBILITY_BINDING__PARENT_RESP:
				setParentResp((Responsibility)newValue);
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
			case MapPackage.RESPONSIBILITY_BINDING__BINDING:
				setBinding((PluginBinding)null);
				return;
			case MapPackage.RESPONSIBILITY_BINDING__PLUGIN_RESP:
				setPluginResp((RespRef)null);
				return;
			case MapPackage.RESPONSIBILITY_BINDING__PARENT_RESP:
				setParentResp((Responsibility)null);
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
			case MapPackage.RESPONSIBILITY_BINDING__BINDING:
				return getBinding() != null;
			case MapPackage.RESPONSIBILITY_BINDING__PLUGIN_RESP:
				return pluginResp != null;
			case MapPackage.RESPONSIBILITY_BINDING__PARENT_RESP:
				return parentResp != null;
		}
		return super.eIsSet(featureID);
	}

} //ResponsibilityBindingImpl
