/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.InternalEList;

import ucm.map.ComponentRef;
import ucm.map.Condition;
import ucm.map.MapPackage;
import ucm.map.NodeConnection;
import ucm.map.Timer;

import ucm.scenario.Variable;

import urncore.NodeLabel;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Timer</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.map.impl.TimerImpl#getTimeoutCondition <em>Timeout Condition</em>}</li>
 *   <li>{@link ucm.map.impl.TimerImpl#getTimeoutPath <em>Timeout Path</em>}</li>
 *   <li>{@link ucm.map.impl.TimerImpl#getTimerVar <em>Timer Var</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TimerImpl extends WaitingPlaceImpl implements Timer {
    /**
     * The cached value of the '{@link #getTimeoutCondition() <em>Timeout Condition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTimeoutCondition()
     * @generated
     * @ordered
     */
    protected Condition timeoutCondition = null;

    /**
     * The cached value of the '{@link #getTimeoutPath() <em>Timeout Path</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTimeoutPath()
     * @generated
     * @ordered
     */
    protected NodeConnection timeoutPath = null;

    /**
     * The cached value of the '{@link #getTimerVar() <em>Timer Var</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTimerVar()
     * @generated
     * @ordered
     */
    protected Variable timerVar = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TimerImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return MapPackage.eINSTANCE.getTimer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Condition getTimeoutCondition() {
        return timeoutCondition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTimeoutCondition(Condition newTimeoutCondition, NotificationChain msgs) {
        Condition oldTimeoutCondition = timeoutCondition;
        timeoutCondition = newTimeoutCondition;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.TIMER__TIMEOUT_CONDITION, oldTimeoutCondition, newTimeoutCondition);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTimeoutCondition(Condition newTimeoutCondition) {
        if (newTimeoutCondition != timeoutCondition) {
            NotificationChain msgs = null;
            if (timeoutCondition != null)
                msgs = ((InternalEObject)timeoutCondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MapPackage.TIMER__TIMEOUT_CONDITION, null, msgs);
            if (newTimeoutCondition != null)
                msgs = ((InternalEObject)newTimeoutCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MapPackage.TIMER__TIMEOUT_CONDITION, null, msgs);
            msgs = basicSetTimeoutCondition(newTimeoutCondition, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.TIMER__TIMEOUT_CONDITION, newTimeoutCondition, newTimeoutCondition));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NodeConnection getTimeoutPath() {
        if (timeoutPath != null && timeoutPath.eIsProxy()) {
            NodeConnection oldTimeoutPath = timeoutPath;
            timeoutPath = (NodeConnection)eResolveProxy((InternalEObject)timeoutPath);
            if (timeoutPath != oldTimeoutPath) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, MapPackage.TIMER__TIMEOUT_PATH, oldTimeoutPath, timeoutPath));
            }
        }
        return timeoutPath;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NodeConnection basicGetTimeoutPath() {
        return timeoutPath;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTimeoutPath(NodeConnection newTimeoutPath) {
        NodeConnection oldTimeoutPath = timeoutPath;
        timeoutPath = newTimeoutPath;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.TIMER__TIMEOUT_PATH, oldTimeoutPath, timeoutPath));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Variable getTimerVar() {
        if (timerVar != null && timerVar.eIsProxy()) {
            Variable oldTimerVar = timerVar;
            timerVar = (Variable)eResolveProxy((InternalEObject)timerVar);
            if (timerVar != oldTimerVar) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, MapPackage.TIMER__TIMER_VAR, oldTimerVar, timerVar));
            }
        }
        return timerVar;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Variable basicGetTimerVar() {
        return timerVar;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTimerVar(Variable newTimerVar) {
        Variable oldTimerVar = timerVar;
        timerVar = newTimerVar;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.TIMER__TIMER_VAR, oldTimerVar, timerVar));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case MapPackage.TIMER__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicAdd(otherEnd, msgs);
                case MapPackage.TIMER__SUCC:
                    return ((InternalEList)getSucc()).basicAdd(otherEnd, msgs);
                case MapPackage.TIMER__PRED:
                    return ((InternalEList)getPred()).basicAdd(otherEnd, msgs);
                case MapPackage.TIMER__COMP_REF:
                    if (compRef != null)
                        msgs = ((InternalEObject)compRef).eInverseRemove(this, MapPackage.COMPONENT_REF__PATH_NODES, ComponentRef.class, msgs);
                    return basicSetCompRef((ComponentRef)otherEnd, msgs);
                case MapPackage.TIMER__LABEL:
                    if (label != null)
                        msgs = ((InternalEObject)label).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MapPackage.TIMER__LABEL, null, msgs);
                    return basicSetLabel((NodeLabel)otherEnd, msgs);
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
                case MapPackage.TIMER__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
                case MapPackage.TIMER__SUCC:
                    return ((InternalEList)getSucc()).basicRemove(otherEnd, msgs);
                case MapPackage.TIMER__PRED:
                    return ((InternalEList)getPred()).basicRemove(otherEnd, msgs);
                case MapPackage.TIMER__COMP_REF:
                    return basicSetCompRef(null, msgs);
                case MapPackage.TIMER__LABEL:
                    return basicSetLabel(null, msgs);
                case MapPackage.TIMER__TIMEOUT_CONDITION:
                    return basicSetTimeoutCondition(null, msgs);
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
            case MapPackage.TIMER__URN_LINKS:
                return getUrnLinks();
            case MapPackage.TIMER__ID:
                return getId();
            case MapPackage.TIMER__NAME:
                return getName();
            case MapPackage.TIMER__DESCRIPTION:
                return getDescription();
            case MapPackage.TIMER__X:
                return new Integer(getX());
            case MapPackage.TIMER__Y:
                return new Integer(getY());
            case MapPackage.TIMER__SUCC:
                return getSucc();
            case MapPackage.TIMER__PRED:
                return getPred();
            case MapPackage.TIMER__COMP_REF:
                if (resolve) return getCompRef();
                return basicGetCompRef();
            case MapPackage.TIMER__LABEL:
                return getLabel();
            case MapPackage.TIMER__WAIT_TYPE:
                return getWaitType();
            case MapPackage.TIMER__TIMEOUT_CONDITION:
                return getTimeoutCondition();
            case MapPackage.TIMER__TIMEOUT_PATH:
                if (resolve) return getTimeoutPath();
                return basicGetTimeoutPath();
            case MapPackage.TIMER__TIMER_VAR:
                if (resolve) return getTimerVar();
                return basicGetTimerVar();
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
            case MapPackage.TIMER__URN_LINKS:
                getUrnLinks().clear();
                getUrnLinks().addAll((Collection)newValue);
                return;
            case MapPackage.TIMER__ID:
                setId((String)newValue);
                return;
            case MapPackage.TIMER__NAME:
                setName((String)newValue);
                return;
            case MapPackage.TIMER__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case MapPackage.TIMER__X:
                setX(((Integer)newValue).intValue());
                return;
            case MapPackage.TIMER__Y:
                setY(((Integer)newValue).intValue());
                return;
            case MapPackage.TIMER__SUCC:
                getSucc().clear();
                getSucc().addAll((Collection)newValue);
                return;
            case MapPackage.TIMER__PRED:
                getPred().clear();
                getPred().addAll((Collection)newValue);
                return;
            case MapPackage.TIMER__COMP_REF:
                setCompRef((ComponentRef)newValue);
                return;
            case MapPackage.TIMER__LABEL:
                setLabel((NodeLabel)newValue);
                return;
            case MapPackage.TIMER__WAIT_TYPE:
                setWaitType((String)newValue);
                return;
            case MapPackage.TIMER__TIMEOUT_CONDITION:
                setTimeoutCondition((Condition)newValue);
                return;
            case MapPackage.TIMER__TIMEOUT_PATH:
                setTimeoutPath((NodeConnection)newValue);
                return;
            case MapPackage.TIMER__TIMER_VAR:
                setTimerVar((Variable)newValue);
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
            case MapPackage.TIMER__URN_LINKS:
                getUrnLinks().clear();
                return;
            case MapPackage.TIMER__ID:
                setId(ID_EDEFAULT);
                return;
            case MapPackage.TIMER__NAME:
                setName(NAME_EDEFAULT);
                return;
            case MapPackage.TIMER__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case MapPackage.TIMER__X:
                setX(X_EDEFAULT);
                return;
            case MapPackage.TIMER__Y:
                setY(Y_EDEFAULT);
                return;
            case MapPackage.TIMER__SUCC:
                getSucc().clear();
                return;
            case MapPackage.TIMER__PRED:
                getPred().clear();
                return;
            case MapPackage.TIMER__COMP_REF:
                setCompRef((ComponentRef)null);
                return;
            case MapPackage.TIMER__LABEL:
                setLabel((NodeLabel)null);
                return;
            case MapPackage.TIMER__WAIT_TYPE:
                setWaitType(WAIT_TYPE_EDEFAULT);
                return;
            case MapPackage.TIMER__TIMEOUT_CONDITION:
                setTimeoutCondition((Condition)null);
                return;
            case MapPackage.TIMER__TIMEOUT_PATH:
                setTimeoutPath((NodeConnection)null);
                return;
            case MapPackage.TIMER__TIMER_VAR:
                setTimerVar((Variable)null);
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
            case MapPackage.TIMER__URN_LINKS:
                return urnLinks != null && !urnLinks.isEmpty();
            case MapPackage.TIMER__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case MapPackage.TIMER__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case MapPackage.TIMER__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case MapPackage.TIMER__X:
                return x != X_EDEFAULT;
            case MapPackage.TIMER__Y:
                return y != Y_EDEFAULT;
            case MapPackage.TIMER__SUCC:
                return succ != null && !succ.isEmpty();
            case MapPackage.TIMER__PRED:
                return pred != null && !pred.isEmpty();
            case MapPackage.TIMER__COMP_REF:
                return compRef != null;
            case MapPackage.TIMER__LABEL:
                return label != null;
            case MapPackage.TIMER__WAIT_TYPE:
                return WAIT_TYPE_EDEFAULT == null ? waitType != null : !WAIT_TYPE_EDEFAULT.equals(waitType);
            case MapPackage.TIMER__TIMEOUT_CONDITION:
                return timeoutCondition != null;
            case MapPackage.TIMER__TIMEOUT_PATH:
                return timeoutPath != null;
            case MapPackage.TIMER__TIMER_VAR:
                return timerVar != null;
        }
        return eDynamicIsSet(eFeature);
    }

} //TimerImpl
