/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import ucm.map.MapPackage;
import ucm.map.NodeConnection;
import ucm.map.Timer;
import ucm.scenario.Variable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Timer</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.map.impl.TimerImpl#getTimeoutPath <em>Timeout Path</em>}</li>
 *   <li>{@link ucm.map.impl.TimerImpl#getTimerVar <em>Timer Var</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TimerImpl extends WaitingPlaceImpl implements Timer {
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
		return MapPackage.Literals.TIMER;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NodeConnection getTimeoutPath() {
		if (timeoutPath != null && timeoutPath.eIsProxy()) {
			InternalEObject oldTimeoutPath = (InternalEObject)timeoutPath;
			timeoutPath = (NodeConnection)eResolveProxy(oldTimeoutPath);
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
			InternalEObject oldTimerVar = (InternalEObject)timerVar;
			timerVar = (Variable)eResolveProxy(oldTimerVar);
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
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MapPackage.TIMER__TIMEOUT_PATH:
				if (resolve) return getTimeoutPath();
				return basicGetTimeoutPath();
			case MapPackage.TIMER__TIMER_VAR:
				if (resolve) return getTimerVar();
				return basicGetTimerVar();
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
			case MapPackage.TIMER__TIMEOUT_PATH:
				setTimeoutPath((NodeConnection)newValue);
				return;
			case MapPackage.TIMER__TIMER_VAR:
				setTimerVar((Variable)newValue);
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
			case MapPackage.TIMER__TIMEOUT_PATH:
				setTimeoutPath((NodeConnection)null);
				return;
			case MapPackage.TIMER__TIMER_VAR:
				setTimerVar((Variable)null);
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
			case MapPackage.TIMER__TIMEOUT_PATH:
				return timeoutPath != null;
			case MapPackage.TIMER__TIMER_VAR:
				return timerVar != null;
		}
		return super.eIsSet(featureID);
	}

} //TimerImpl
