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

import ucm.map.EndPoint;
import ucm.map.MapPackage;
import ucm.map.NodeConnection;
import ucm.map.OutBinding;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Out Binding</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.map.impl.OutBindingImpl#getEndPoint <em>End Point</em>}</li>
 *   <li>{@link ucm.map.impl.OutBindingImpl#getStubExit <em>Stub Exit</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OutBindingImpl extends EObjectImpl implements OutBinding {
    /**
     * The cached value of the '{@link #getEndPoint() <em>End Point</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEndPoint()
     * @generated
     * @ordered
     */
    protected EndPoint endPoint = null;

    /**
     * The cached value of the '{@link #getStubExit() <em>Stub Exit</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStubExit()
     * @generated
     * @ordered
     */
    protected NodeConnection stubExit = null;

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
        return MapPackage.eINSTANCE.getOutBinding();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EndPoint getEndPoint() {
        if (endPoint != null && endPoint.eIsProxy()) {
            EndPoint oldEndPoint = endPoint;
            endPoint = (EndPoint)eResolveProxy((InternalEObject)endPoint);
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
            NodeConnection oldStubExit = stubExit;
            stubExit = (NodeConnection)eResolveProxy((InternalEObject)stubExit);
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
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case MapPackage.OUT_BINDING__END_POINT:
                    if (endPoint != null)
                        msgs = ((InternalEObject)endPoint).eInverseRemove(this, MapPackage.END_POINT__OUT_BINDINGS, EndPoint.class, msgs);
                    return basicSetEndPoint((EndPoint)otherEnd, msgs);
                case MapPackage.OUT_BINDING__STUB_EXIT:
                    if (stubExit != null)
                        msgs = ((InternalEObject)stubExit).eInverseRemove(this, MapPackage.NODE_CONNECTION__OUT_BINDINGS, NodeConnection.class, msgs);
                    return basicSetStubExit((NodeConnection)otherEnd, msgs);
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
                case MapPackage.OUT_BINDING__END_POINT:
                    return basicSetEndPoint(null, msgs);
                case MapPackage.OUT_BINDING__STUB_EXIT:
                    return basicSetStubExit(null, msgs);
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
            case MapPackage.OUT_BINDING__END_POINT:
                if (resolve) return getEndPoint();
                return basicGetEndPoint();
            case MapPackage.OUT_BINDING__STUB_EXIT:
                if (resolve) return getStubExit();
                return basicGetStubExit();
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
            case MapPackage.OUT_BINDING__END_POINT:
                setEndPoint((EndPoint)newValue);
                return;
            case MapPackage.OUT_BINDING__STUB_EXIT:
                setStubExit((NodeConnection)newValue);
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
            case MapPackage.OUT_BINDING__END_POINT:
                setEndPoint((EndPoint)null);
                return;
            case MapPackage.OUT_BINDING__STUB_EXIT:
                setStubExit((NodeConnection)null);
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
            case MapPackage.OUT_BINDING__END_POINT:
                return endPoint != null;
            case MapPackage.OUT_BINDING__STUB_EXIT:
                return stubExit != null;
        }
        return eDynamicIsSet(eFeature);
    }

} //OutBindingImpl
