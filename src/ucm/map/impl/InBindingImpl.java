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
import ucm.map.InBinding;
import ucm.map.MapPackage;
import ucm.map.NodeConnection;
import ucm.map.PluginBinding;
import ucm.map.StartPoint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>In Binding</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.map.impl.InBindingImpl#getBinding <em>Binding</em>}</li>
 *   <li>{@link ucm.map.impl.InBindingImpl#getStartPoint <em>Start Point</em>}</li>
 *   <li>{@link ucm.map.impl.InBindingImpl#getStubEntry <em>Stub Entry</em>}</li>
 *   <li>{@link ucm.map.impl.InBindingImpl#getPointcutExit <em>Pointcut Exit</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InBindingImpl extends MinimalEObjectImpl.Container implements InBinding {
    /**
	 * The cached value of the '{@link #getStartPoint() <em>Start Point</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getStartPoint()
	 * @generated
	 * @ordered
	 */
    protected StartPoint startPoint;

    /**
	 * The cached value of the '{@link #getStubEntry() <em>Stub Entry</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getStubEntry()
	 * @generated
	 * @ordered
	 */
    protected NodeConnection stubEntry;

    /**
	 * The cached value of the '{@link #getPointcutExit() <em>Pointcut Exit</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPointcutExit()
	 * @generated
	 * @ordered
	 */
    protected NodeConnection pointcutExit;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected InBindingImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return MapPackage.Literals.IN_BINDING;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PluginBinding getBinding() {
		if (eContainerFeatureID() != MapPackage.IN_BINDING__BINDING) return null;
		return (PluginBinding)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBinding(PluginBinding newBinding, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newBinding, MapPackage.IN_BINDING__BINDING, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setBinding(PluginBinding newBinding) {
		if (newBinding != eInternalContainer() || (eContainerFeatureID() != MapPackage.IN_BINDING__BINDING && newBinding != null)) {
			if (EcoreUtil.isAncestor(this, newBinding))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newBinding != null)
				msgs = ((InternalEObject)newBinding).eInverseAdd(this, MapPackage.PLUGIN_BINDING__IN, PluginBinding.class, msgs);
			msgs = basicSetBinding(newBinding, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.IN_BINDING__BINDING, newBinding, newBinding));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public StartPoint getStartPoint() {
		if (startPoint != null && startPoint.eIsProxy()) {
			InternalEObject oldStartPoint = (InternalEObject)startPoint;
			startPoint = (StartPoint)eResolveProxy(oldStartPoint);
			if (startPoint != oldStartPoint) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MapPackage.IN_BINDING__START_POINT, oldStartPoint, startPoint));
			}
		}
		return startPoint;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public StartPoint basicGetStartPoint() {
		return startPoint;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetStartPoint(StartPoint newStartPoint, NotificationChain msgs) {
		StartPoint oldStartPoint = startPoint;
		startPoint = newStartPoint;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.IN_BINDING__START_POINT, oldStartPoint, newStartPoint);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setStartPoint(StartPoint newStartPoint) {
		if (newStartPoint != startPoint) {
			NotificationChain msgs = null;
			if (startPoint != null)
				msgs = ((InternalEObject)startPoint).eInverseRemove(this, MapPackage.START_POINT__IN_BINDINGS, StartPoint.class, msgs);
			if (newStartPoint != null)
				msgs = ((InternalEObject)newStartPoint).eInverseAdd(this, MapPackage.START_POINT__IN_BINDINGS, StartPoint.class, msgs);
			msgs = basicSetStartPoint(newStartPoint, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.IN_BINDING__START_POINT, newStartPoint, newStartPoint));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NodeConnection getStubEntry() {
		if (stubEntry != null && stubEntry.eIsProxy()) {
			InternalEObject oldStubEntry = (InternalEObject)stubEntry;
			stubEntry = (NodeConnection)eResolveProxy(oldStubEntry);
			if (stubEntry != oldStubEntry) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MapPackage.IN_BINDING__STUB_ENTRY, oldStubEntry, stubEntry));
			}
		}
		return stubEntry;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NodeConnection basicGetStubEntry() {
		return stubEntry;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetStubEntry(NodeConnection newStubEntry, NotificationChain msgs) {
		NodeConnection oldStubEntry = stubEntry;
		stubEntry = newStubEntry;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.IN_BINDING__STUB_ENTRY, oldStubEntry, newStubEntry);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setStubEntry(NodeConnection newStubEntry) {
		if (newStubEntry != stubEntry) {
			NotificationChain msgs = null;
			if (stubEntry != null)
				msgs = ((InternalEObject)stubEntry).eInverseRemove(this, MapPackage.NODE_CONNECTION__IN_BINDINGS, NodeConnection.class, msgs);
			if (newStubEntry != null)
				msgs = ((InternalEObject)newStubEntry).eInverseAdd(this, MapPackage.NODE_CONNECTION__IN_BINDINGS, NodeConnection.class, msgs);
			msgs = basicSetStubEntry(newStubEntry, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.IN_BINDING__STUB_ENTRY, newStubEntry, newStubEntry));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NodeConnection getPointcutExit() {
		if (pointcutExit != null && pointcutExit.eIsProxy()) {
			InternalEObject oldPointcutExit = (InternalEObject)pointcutExit;
			pointcutExit = (NodeConnection)eResolveProxy(oldPointcutExit);
			if (pointcutExit != oldPointcutExit) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MapPackage.IN_BINDING__POINTCUT_EXIT, oldPointcutExit, pointcutExit));
			}
		}
		return pointcutExit;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NodeConnection basicGetPointcutExit() {
		return pointcutExit;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetPointcutExit(NodeConnection newPointcutExit, NotificationChain msgs) {
		NodeConnection oldPointcutExit = pointcutExit;
		pointcutExit = newPointcutExit;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.IN_BINDING__POINTCUT_EXIT, oldPointcutExit, newPointcutExit);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setPointcutExit(NodeConnection newPointcutExit) {
		if (newPointcutExit != pointcutExit) {
			NotificationChain msgs = null;
			if (pointcutExit != null)
				msgs = ((InternalEObject)pointcutExit).eInverseRemove(this, MapPackage.NODE_CONNECTION__IN_BINDINGS_PLUGIN, NodeConnection.class, msgs);
			if (newPointcutExit != null)
				msgs = ((InternalEObject)newPointcutExit).eInverseAdd(this, MapPackage.NODE_CONNECTION__IN_BINDINGS_PLUGIN, NodeConnection.class, msgs);
			msgs = basicSetPointcutExit(newPointcutExit, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.IN_BINDING__POINTCUT_EXIT, newPointcutExit, newPointcutExit));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MapPackage.IN_BINDING__BINDING:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetBinding((PluginBinding)otherEnd, msgs);
			case MapPackage.IN_BINDING__START_POINT:
				if (startPoint != null)
					msgs = ((InternalEObject)startPoint).eInverseRemove(this, MapPackage.START_POINT__IN_BINDINGS, StartPoint.class, msgs);
				return basicSetStartPoint((StartPoint)otherEnd, msgs);
			case MapPackage.IN_BINDING__STUB_ENTRY:
				if (stubEntry != null)
					msgs = ((InternalEObject)stubEntry).eInverseRemove(this, MapPackage.NODE_CONNECTION__IN_BINDINGS, NodeConnection.class, msgs);
				return basicSetStubEntry((NodeConnection)otherEnd, msgs);
			case MapPackage.IN_BINDING__POINTCUT_EXIT:
				if (pointcutExit != null)
					msgs = ((InternalEObject)pointcutExit).eInverseRemove(this, MapPackage.NODE_CONNECTION__IN_BINDINGS_PLUGIN, NodeConnection.class, msgs);
				return basicSetPointcutExit((NodeConnection)otherEnd, msgs);
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
			case MapPackage.IN_BINDING__BINDING:
				return basicSetBinding(null, msgs);
			case MapPackage.IN_BINDING__START_POINT:
				return basicSetStartPoint(null, msgs);
			case MapPackage.IN_BINDING__STUB_ENTRY:
				return basicSetStubEntry(null, msgs);
			case MapPackage.IN_BINDING__POINTCUT_EXIT:
				return basicSetPointcutExit(null, msgs);
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
			case MapPackage.IN_BINDING__BINDING:
				return eInternalContainer().eInverseRemove(this, MapPackage.PLUGIN_BINDING__IN, PluginBinding.class, msgs);
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
			case MapPackage.IN_BINDING__BINDING:
				return getBinding();
			case MapPackage.IN_BINDING__START_POINT:
				if (resolve) return getStartPoint();
				return basicGetStartPoint();
			case MapPackage.IN_BINDING__STUB_ENTRY:
				if (resolve) return getStubEntry();
				return basicGetStubEntry();
			case MapPackage.IN_BINDING__POINTCUT_EXIT:
				if (resolve) return getPointcutExit();
				return basicGetPointcutExit();
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
			case MapPackage.IN_BINDING__BINDING:
				setBinding((PluginBinding)newValue);
				return;
			case MapPackage.IN_BINDING__START_POINT:
				setStartPoint((StartPoint)newValue);
				return;
			case MapPackage.IN_BINDING__STUB_ENTRY:
				setStubEntry((NodeConnection)newValue);
				return;
			case MapPackage.IN_BINDING__POINTCUT_EXIT:
				setPointcutExit((NodeConnection)newValue);
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
			case MapPackage.IN_BINDING__BINDING:
				setBinding((PluginBinding)null);
				return;
			case MapPackage.IN_BINDING__START_POINT:
				setStartPoint((StartPoint)null);
				return;
			case MapPackage.IN_BINDING__STUB_ENTRY:
				setStubEntry((NodeConnection)null);
				return;
			case MapPackage.IN_BINDING__POINTCUT_EXIT:
				setPointcutExit((NodeConnection)null);
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
			case MapPackage.IN_BINDING__BINDING:
				return getBinding() != null;
			case MapPackage.IN_BINDING__START_POINT:
				return startPoint != null;
			case MapPackage.IN_BINDING__STUB_ENTRY:
				return stubEntry != null;
			case MapPackage.IN_BINDING__POINTCUT_EXIT:
				return pointcutExit != null;
		}
		return super.eIsSet(featureID);
	}

} //InBindingImpl
