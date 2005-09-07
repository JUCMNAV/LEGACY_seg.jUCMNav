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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

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
 * </ul>
 * </p>
 *
 * @generated
 */
public class InBindingImpl extends EObjectImpl implements InBinding {
    /**
     * The cached value of the '{@link #getStartPoint() <em>Start Point</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getStartPoint()
     * @generated
     * @ordered
     */
	protected StartPoint startPoint = null;

    /**
     * The cached value of the '{@link #getStubEntry() <em>Stub Entry</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getStubEntry()
     * @generated
     * @ordered
     */
	protected NodeConnection stubEntry = null;

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
        return MapPackage.eINSTANCE.getInBinding();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public PluginBinding getBinding() {
        if (eContainerFeatureID != MapPackage.IN_BINDING__BINDING) return null;
        return (PluginBinding)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setBinding(PluginBinding newBinding) {
        if (newBinding != eContainer || (eContainerFeatureID != MapPackage.IN_BINDING__BINDING && newBinding != null)) {
            if (EcoreUtil.isAncestor(this, newBinding))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newBinding != null)
                msgs = ((InternalEObject)newBinding).eInverseAdd(this, MapPackage.PLUGIN_BINDING__IN, PluginBinding.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newBinding, MapPackage.IN_BINDING__BINDING, msgs);
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
            StartPoint oldStartPoint = startPoint;
            startPoint = (StartPoint)eResolveProxy((InternalEObject)startPoint);
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
            NodeConnection oldStubEntry = stubEntry;
            stubEntry = (NodeConnection)eResolveProxy((InternalEObject)stubEntry);
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
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case MapPackage.IN_BINDING__BINDING:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, MapPackage.IN_BINDING__BINDING, msgs);
                case MapPackage.IN_BINDING__START_POINT:
                    if (startPoint != null)
                        msgs = ((InternalEObject)startPoint).eInverseRemove(this, MapPackage.START_POINT__IN_BINDINGS, StartPoint.class, msgs);
                    return basicSetStartPoint((StartPoint)otherEnd, msgs);
                case MapPackage.IN_BINDING__STUB_ENTRY:
                    if (stubEntry != null)
                        msgs = ((InternalEObject)stubEntry).eInverseRemove(this, MapPackage.NODE_CONNECTION__IN_BINDINGS, NodeConnection.class, msgs);
                    return basicSetStubEntry((NodeConnection)otherEnd, msgs);
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
                case MapPackage.IN_BINDING__BINDING:
                    return eBasicSetContainer(null, MapPackage.IN_BINDING__BINDING, msgs);
                case MapPackage.IN_BINDING__START_POINT:
                    return basicSetStartPoint(null, msgs);
                case MapPackage.IN_BINDING__STUB_ENTRY:
                    return basicSetStubEntry(null, msgs);
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
                case MapPackage.IN_BINDING__BINDING:
                    return eContainer.eInverseRemove(this, MapPackage.PLUGIN_BINDING__IN, PluginBinding.class, msgs);
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
            case MapPackage.IN_BINDING__BINDING:
                return getBinding();
            case MapPackage.IN_BINDING__START_POINT:
                if (resolve) return getStartPoint();
                return basicGetStartPoint();
            case MapPackage.IN_BINDING__STUB_ENTRY:
                if (resolve) return getStubEntry();
                return basicGetStubEntry();
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
            case MapPackage.IN_BINDING__BINDING:
                setBinding((PluginBinding)newValue);
                return;
            case MapPackage.IN_BINDING__START_POINT:
                setStartPoint((StartPoint)newValue);
                return;
            case MapPackage.IN_BINDING__STUB_ENTRY:
                setStubEntry((NodeConnection)newValue);
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
            case MapPackage.IN_BINDING__BINDING:
                setBinding((PluginBinding)null);
                return;
            case MapPackage.IN_BINDING__START_POINT:
                setStartPoint((StartPoint)null);
                return;
            case MapPackage.IN_BINDING__STUB_ENTRY:
                setStubEntry((NodeConnection)null);
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
            case MapPackage.IN_BINDING__BINDING:
                return getBinding() != null;
            case MapPackage.IN_BINDING__START_POINT:
                return startPoint != null;
            case MapPackage.IN_BINDING__STUB_ENTRY:
                return stubEntry != null;
        }
        return eDynamicIsSet(eFeature);
    }

} //InBindingImpl
