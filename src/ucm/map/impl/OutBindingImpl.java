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
import ucm.map.EndPoint;
import ucm.map.MapPackage;
import ucm.map.NodeConnection;
import ucm.map.OutBinding;
import ucm.map.PluginBinding;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Out Binding</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.map.impl.OutBindingImpl#getBinding <em>Binding</em>}</li>
 *   <li>{@link ucm.map.impl.OutBindingImpl#getEndPoint <em>End Point</em>}</li>
 *   <li>{@link ucm.map.impl.OutBindingImpl#getStubExit <em>Stub Exit</em>}</li>
 *   <li>{@link ucm.map.impl.OutBindingImpl#getPointcutEntry <em>Pointcut Entry</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OutBindingImpl extends MinimalEObjectImpl.Container implements OutBinding {
    /**
	 * The cached value of the '{@link #getEndPoint() <em>End Point</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getEndPoint()
	 * @generated
	 * @ordered
	 */
    protected EndPoint endPoint;

    /**
	 * The cached value of the '{@link #getStubExit() <em>Stub Exit</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getStubExit()
	 * @generated
	 * @ordered
	 */
    protected NodeConnection stubExit;

    /**
	 * The cached value of the '{@link #getPointcutEntry() <em>Pointcut Entry</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPointcutEntry()
	 * @generated
	 * @ordered
	 */
    protected NodeConnection pointcutEntry;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected OutBindingImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return MapPackage.Literals.OUT_BINDING;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PluginBinding getBinding() {
		if (eContainerFeatureID() != MapPackage.OUT_BINDING__BINDING) return null;
		return (PluginBinding)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBinding(PluginBinding newBinding, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newBinding, MapPackage.OUT_BINDING__BINDING, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setBinding(PluginBinding newBinding) {
		if (newBinding != eInternalContainer() || (eContainerFeatureID() != MapPackage.OUT_BINDING__BINDING && newBinding != null)) {
			if (EcoreUtil.isAncestor(this, newBinding))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newBinding != null)
				msgs = ((InternalEObject)newBinding).eInverseAdd(this, MapPackage.PLUGIN_BINDING__OUT, PluginBinding.class, msgs);
			msgs = basicSetBinding(newBinding, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.OUT_BINDING__BINDING, newBinding, newBinding));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EndPoint getEndPoint() {
		if (endPoint != null && endPoint.eIsProxy()) {
			InternalEObject oldEndPoint = (InternalEObject)endPoint;
			endPoint = (EndPoint)eResolveProxy(oldEndPoint);
			if (endPoint != oldEndPoint) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MapPackage.OUT_BINDING__END_POINT, oldEndPoint, endPoint));
			}
		}
		return endPoint;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EndPoint basicGetEndPoint() {
		return endPoint;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetEndPoint(EndPoint newEndPoint, NotificationChain msgs) {
		EndPoint oldEndPoint = endPoint;
		endPoint = newEndPoint;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.OUT_BINDING__END_POINT, oldEndPoint, newEndPoint);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setEndPoint(EndPoint newEndPoint) {
		if (newEndPoint != endPoint) {
			NotificationChain msgs = null;
			if (endPoint != null)
				msgs = ((InternalEObject)endPoint).eInverseRemove(this, MapPackage.END_POINT__OUT_BINDINGS, EndPoint.class, msgs);
			if (newEndPoint != null)
				msgs = ((InternalEObject)newEndPoint).eInverseAdd(this, MapPackage.END_POINT__OUT_BINDINGS, EndPoint.class, msgs);
			msgs = basicSetEndPoint(newEndPoint, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.OUT_BINDING__END_POINT, newEndPoint, newEndPoint));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NodeConnection getStubExit() {
		if (stubExit != null && stubExit.eIsProxy()) {
			InternalEObject oldStubExit = (InternalEObject)stubExit;
			stubExit = (NodeConnection)eResolveProxy(oldStubExit);
			if (stubExit != oldStubExit) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MapPackage.OUT_BINDING__STUB_EXIT, oldStubExit, stubExit));
			}
		}
		return stubExit;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NodeConnection basicGetStubExit() {
		return stubExit;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetStubExit(NodeConnection newStubExit, NotificationChain msgs) {
		NodeConnection oldStubExit = stubExit;
		stubExit = newStubExit;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.OUT_BINDING__STUB_EXIT, oldStubExit, newStubExit);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setStubExit(NodeConnection newStubExit) {
		if (newStubExit != stubExit) {
			NotificationChain msgs = null;
			if (stubExit != null)
				msgs = ((InternalEObject)stubExit).eInverseRemove(this, MapPackage.NODE_CONNECTION__OUT_BINDINGS, NodeConnection.class, msgs);
			if (newStubExit != null)
				msgs = ((InternalEObject)newStubExit).eInverseAdd(this, MapPackage.NODE_CONNECTION__OUT_BINDINGS, NodeConnection.class, msgs);
			msgs = basicSetStubExit(newStubExit, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.OUT_BINDING__STUB_EXIT, newStubExit, newStubExit));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NodeConnection getPointcutEntry() {
		if (pointcutEntry != null && pointcutEntry.eIsProxy()) {
			InternalEObject oldPointcutEntry = (InternalEObject)pointcutEntry;
			pointcutEntry = (NodeConnection)eResolveProxy(oldPointcutEntry);
			if (pointcutEntry != oldPointcutEntry) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MapPackage.OUT_BINDING__POINTCUT_ENTRY, oldPointcutEntry, pointcutEntry));
			}
		}
		return pointcutEntry;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NodeConnection basicGetPointcutEntry() {
		return pointcutEntry;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetPointcutEntry(NodeConnection newPointcutEntry, NotificationChain msgs) {
		NodeConnection oldPointcutEntry = pointcutEntry;
		pointcutEntry = newPointcutEntry;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.OUT_BINDING__POINTCUT_ENTRY, oldPointcutEntry, newPointcutEntry);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setPointcutEntry(NodeConnection newPointcutEntry) {
		if (newPointcutEntry != pointcutEntry) {
			NotificationChain msgs = null;
			if (pointcutEntry != null)
				msgs = ((InternalEObject)pointcutEntry).eInverseRemove(this, MapPackage.NODE_CONNECTION__OUT_BINDINGS_PLUGIN, NodeConnection.class, msgs);
			if (newPointcutEntry != null)
				msgs = ((InternalEObject)newPointcutEntry).eInverseAdd(this, MapPackage.NODE_CONNECTION__OUT_BINDINGS_PLUGIN, NodeConnection.class, msgs);
			msgs = basicSetPointcutEntry(newPointcutEntry, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.OUT_BINDING__POINTCUT_ENTRY, newPointcutEntry, newPointcutEntry));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MapPackage.OUT_BINDING__BINDING:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetBinding((PluginBinding)otherEnd, msgs);
			case MapPackage.OUT_BINDING__END_POINT:
				if (endPoint != null)
					msgs = ((InternalEObject)endPoint).eInverseRemove(this, MapPackage.END_POINT__OUT_BINDINGS, EndPoint.class, msgs);
				return basicSetEndPoint((EndPoint)otherEnd, msgs);
			case MapPackage.OUT_BINDING__STUB_EXIT:
				if (stubExit != null)
					msgs = ((InternalEObject)stubExit).eInverseRemove(this, MapPackage.NODE_CONNECTION__OUT_BINDINGS, NodeConnection.class, msgs);
				return basicSetStubExit((NodeConnection)otherEnd, msgs);
			case MapPackage.OUT_BINDING__POINTCUT_ENTRY:
				if (pointcutEntry != null)
					msgs = ((InternalEObject)pointcutEntry).eInverseRemove(this, MapPackage.NODE_CONNECTION__OUT_BINDINGS_PLUGIN, NodeConnection.class, msgs);
				return basicSetPointcutEntry((NodeConnection)otherEnd, msgs);
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
			case MapPackage.OUT_BINDING__BINDING:
				return basicSetBinding(null, msgs);
			case MapPackage.OUT_BINDING__END_POINT:
				return basicSetEndPoint(null, msgs);
			case MapPackage.OUT_BINDING__STUB_EXIT:
				return basicSetStubExit(null, msgs);
			case MapPackage.OUT_BINDING__POINTCUT_ENTRY:
				return basicSetPointcutEntry(null, msgs);
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
			case MapPackage.OUT_BINDING__BINDING:
				return eInternalContainer().eInverseRemove(this, MapPackage.PLUGIN_BINDING__OUT, PluginBinding.class, msgs);
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
			case MapPackage.OUT_BINDING__BINDING:
				return getBinding();
			case MapPackage.OUT_BINDING__END_POINT:
				if (resolve) return getEndPoint();
				return basicGetEndPoint();
			case MapPackage.OUT_BINDING__STUB_EXIT:
				if (resolve) return getStubExit();
				return basicGetStubExit();
			case MapPackage.OUT_BINDING__POINTCUT_ENTRY:
				if (resolve) return getPointcutEntry();
				return basicGetPointcutEntry();
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
			case MapPackage.OUT_BINDING__BINDING:
				setBinding((PluginBinding)newValue);
				return;
			case MapPackage.OUT_BINDING__END_POINT:
				setEndPoint((EndPoint)newValue);
				return;
			case MapPackage.OUT_BINDING__STUB_EXIT:
				setStubExit((NodeConnection)newValue);
				return;
			case MapPackage.OUT_BINDING__POINTCUT_ENTRY:
				setPointcutEntry((NodeConnection)newValue);
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
			case MapPackage.OUT_BINDING__BINDING:
				setBinding((PluginBinding)null);
				return;
			case MapPackage.OUT_BINDING__END_POINT:
				setEndPoint((EndPoint)null);
				return;
			case MapPackage.OUT_BINDING__STUB_EXIT:
				setStubExit((NodeConnection)null);
				return;
			case MapPackage.OUT_BINDING__POINTCUT_ENTRY:
				setPointcutEntry((NodeConnection)null);
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
			case MapPackage.OUT_BINDING__BINDING:
				return getBinding() != null;
			case MapPackage.OUT_BINDING__END_POINT:
				return endPoint != null;
			case MapPackage.OUT_BINDING__STUB_EXIT:
				return stubExit != null;
			case MapPackage.OUT_BINDING__POINTCUT_ENTRY:
				return pointcutEntry != null;
		}
		return super.eIsSet(featureID);
	}

} //OutBindingImpl
