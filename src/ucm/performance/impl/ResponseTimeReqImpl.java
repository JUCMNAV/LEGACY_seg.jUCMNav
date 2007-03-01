/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.performance.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import ucm.UCMspec;
import ucm.UcmPackage;
import ucm.performance.PerformancePackage;
import ucm.performance.ResponseTimeReq;
import ucm.performance.Timestamp;
import urncore.impl.UCMmodelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Response Time Req</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.performance.impl.ResponseTimeReqImpl#getResponseTime <em>Response Time</em>}</li>
 *   <li>{@link ucm.performance.impl.ResponseTimeReqImpl#getPercentage <em>Percentage</em>}</li>
 *   <li>{@link ucm.performance.impl.ResponseTimeReqImpl#getUcmspec <em>Ucmspec</em>}</li>
 *   <li>{@link ucm.performance.impl.ResponseTimeReqImpl#getTs1 <em>Ts1</em>}</li>
 *   <li>{@link ucm.performance.impl.ResponseTimeReqImpl#getTs2 <em>Ts2</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResponseTimeReqImpl extends UCMmodelElementImpl implements ResponseTimeReq {
    /**
     * The default value of the '{@link #getResponseTime() <em>Response Time</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getResponseTime()
     * @generated
     * @ordered
     */
    protected static final String RESPONSE_TIME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getResponseTime() <em>Response Time</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getResponseTime()
     * @generated
     * @ordered
     */
    protected String responseTime = RESPONSE_TIME_EDEFAULT;

    /**
     * The default value of the '{@link #getPercentage() <em>Percentage</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPercentage()
     * @generated
     * @ordered
     */
    protected static final String PERCENTAGE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPercentage() <em>Percentage</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPercentage()
     * @generated
     * @ordered
     */
    protected String percentage = PERCENTAGE_EDEFAULT;

    /**
     * The cached value of the '{@link #getTs1() <em>Ts1</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTs1()
     * @generated
     * @ordered
     */
    protected Timestamp ts1 = null;

    /**
     * The cached value of the '{@link #getTs2() <em>Ts2</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTs2()
     * @generated
     * @ordered
     */
    protected Timestamp ts2 = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ResponseTimeReqImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return PerformancePackage.Literals.RESPONSE_TIME_REQ;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getResponseTime() {
        return responseTime;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setResponseTime(String newResponseTime) {
        String oldResponseTime = responseTime;
        responseTime = newResponseTime;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.RESPONSE_TIME_REQ__RESPONSE_TIME, oldResponseTime, responseTime));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getPercentage() {
        return percentage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPercentage(String newPercentage) {
        String oldPercentage = percentage;
        percentage = newPercentage;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.RESPONSE_TIME_REQ__PERCENTAGE, oldPercentage, percentage));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UCMspec getUcmspec() {
        if (eContainerFeatureID != PerformancePackage.RESPONSE_TIME_REQ__UCMSPEC) return null;
        return (UCMspec)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetUcmspec(UCMspec newUcmspec, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newUcmspec, PerformancePackage.RESPONSE_TIME_REQ__UCMSPEC, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUcmspec(UCMspec newUcmspec) {
        if (newUcmspec != eInternalContainer() || (eContainerFeatureID != PerformancePackage.RESPONSE_TIME_REQ__UCMSPEC && newUcmspec != null)) {
            if (EcoreUtil.isAncestor(this, newUcmspec))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newUcmspec != null)
                msgs = ((InternalEObject)newUcmspec).eInverseAdd(this, UcmPackage.UC_MSPEC__RESPTIMEREQ, UCMspec.class, msgs);
            msgs = basicSetUcmspec(newUcmspec, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.RESPONSE_TIME_REQ__UCMSPEC, newUcmspec, newUcmspec));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Timestamp getTs1() {
        if (ts1 != null && ts1.eIsProxy()) {
            InternalEObject oldTs1 = (InternalEObject)ts1;
            ts1 = (Timestamp)eResolveProxy(oldTs1);
            if (ts1 != oldTs1) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PerformancePackage.RESPONSE_TIME_REQ__TS1, oldTs1, ts1));
            }
        }
        return ts1;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Timestamp basicGetTs1() {
        return ts1;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTs1(Timestamp newTs1, NotificationChain msgs) {
        Timestamp oldTs1 = ts1;
        ts1 = newTs1;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PerformancePackage.RESPONSE_TIME_REQ__TS1, oldTs1, newTs1);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTs1(Timestamp newTs1) {
        if (newTs1 != ts1) {
            NotificationChain msgs = null;
            if (ts1 != null)
                msgs = ((InternalEObject)ts1).eInverseRemove(this, PerformancePackage.TIMESTAMP__TARGETS, Timestamp.class, msgs);
            if (newTs1 != null)
                msgs = ((InternalEObject)newTs1).eInverseAdd(this, PerformancePackage.TIMESTAMP__TARGETS, Timestamp.class, msgs);
            msgs = basicSetTs1(newTs1, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.RESPONSE_TIME_REQ__TS1, newTs1, newTs1));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Timestamp getTs2() {
        if (ts2 != null && ts2.eIsProxy()) {
            InternalEObject oldTs2 = (InternalEObject)ts2;
            ts2 = (Timestamp)eResolveProxy(oldTs2);
            if (ts2 != oldTs2) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PerformancePackage.RESPONSE_TIME_REQ__TS2, oldTs2, ts2));
            }
        }
        return ts2;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Timestamp basicGetTs2() {
        return ts2;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTs2(Timestamp newTs2, NotificationChain msgs) {
        Timestamp oldTs2 = ts2;
        ts2 = newTs2;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PerformancePackage.RESPONSE_TIME_REQ__TS2, oldTs2, newTs2);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTs2(Timestamp newTs2) {
        if (newTs2 != ts2) {
            NotificationChain msgs = null;
            if (ts2 != null)
                msgs = ((InternalEObject)ts2).eInverseRemove(this, PerformancePackage.TIMESTAMP__SOURCES, Timestamp.class, msgs);
            if (newTs2 != null)
                msgs = ((InternalEObject)newTs2).eInverseAdd(this, PerformancePackage.TIMESTAMP__SOURCES, Timestamp.class, msgs);
            msgs = basicSetTs2(newTs2, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.RESPONSE_TIME_REQ__TS2, newTs2, newTs2));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case PerformancePackage.RESPONSE_TIME_REQ__UCMSPEC:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetUcmspec((UCMspec)otherEnd, msgs);
            case PerformancePackage.RESPONSE_TIME_REQ__TS1:
                if (ts1 != null)
                    msgs = ((InternalEObject)ts1).eInverseRemove(this, PerformancePackage.TIMESTAMP__TARGETS, Timestamp.class, msgs);
                return basicSetTs1((Timestamp)otherEnd, msgs);
            case PerformancePackage.RESPONSE_TIME_REQ__TS2:
                if (ts2 != null)
                    msgs = ((InternalEObject)ts2).eInverseRemove(this, PerformancePackage.TIMESTAMP__SOURCES, Timestamp.class, msgs);
                return basicSetTs2((Timestamp)otherEnd, msgs);
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
            case PerformancePackage.RESPONSE_TIME_REQ__UCMSPEC:
                return basicSetUcmspec(null, msgs);
            case PerformancePackage.RESPONSE_TIME_REQ__TS1:
                return basicSetTs1(null, msgs);
            case PerformancePackage.RESPONSE_TIME_REQ__TS2:
                return basicSetTs2(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID) {
            case PerformancePackage.RESPONSE_TIME_REQ__UCMSPEC:
                return eInternalContainer().eInverseRemove(this, UcmPackage.UC_MSPEC__RESPTIMEREQ, UCMspec.class, msgs);
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
            case PerformancePackage.RESPONSE_TIME_REQ__RESPONSE_TIME:
                return getResponseTime();
            case PerformancePackage.RESPONSE_TIME_REQ__PERCENTAGE:
                return getPercentage();
            case PerformancePackage.RESPONSE_TIME_REQ__UCMSPEC:
                return getUcmspec();
            case PerformancePackage.RESPONSE_TIME_REQ__TS1:
                if (resolve) return getTs1();
                return basicGetTs1();
            case PerformancePackage.RESPONSE_TIME_REQ__TS2:
                if (resolve) return getTs2();
                return basicGetTs2();
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
            case PerformancePackage.RESPONSE_TIME_REQ__RESPONSE_TIME:
                setResponseTime((String)newValue);
                return;
            case PerformancePackage.RESPONSE_TIME_REQ__PERCENTAGE:
                setPercentage((String)newValue);
                return;
            case PerformancePackage.RESPONSE_TIME_REQ__UCMSPEC:
                setUcmspec((UCMspec)newValue);
                return;
            case PerformancePackage.RESPONSE_TIME_REQ__TS1:
                setTs1((Timestamp)newValue);
                return;
            case PerformancePackage.RESPONSE_TIME_REQ__TS2:
                setTs2((Timestamp)newValue);
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
            case PerformancePackage.RESPONSE_TIME_REQ__RESPONSE_TIME:
                setResponseTime(RESPONSE_TIME_EDEFAULT);
                return;
            case PerformancePackage.RESPONSE_TIME_REQ__PERCENTAGE:
                setPercentage(PERCENTAGE_EDEFAULT);
                return;
            case PerformancePackage.RESPONSE_TIME_REQ__UCMSPEC:
                setUcmspec((UCMspec)null);
                return;
            case PerformancePackage.RESPONSE_TIME_REQ__TS1:
                setTs1((Timestamp)null);
                return;
            case PerformancePackage.RESPONSE_TIME_REQ__TS2:
                setTs2((Timestamp)null);
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
            case PerformancePackage.RESPONSE_TIME_REQ__RESPONSE_TIME:
                return RESPONSE_TIME_EDEFAULT == null ? responseTime != null : !RESPONSE_TIME_EDEFAULT.equals(responseTime);
            case PerformancePackage.RESPONSE_TIME_REQ__PERCENTAGE:
                return PERCENTAGE_EDEFAULT == null ? percentage != null : !PERCENTAGE_EDEFAULT.equals(percentage);
            case PerformancePackage.RESPONSE_TIME_REQ__UCMSPEC:
                return getUcmspec() != null;
            case PerformancePackage.RESPONSE_TIME_REQ__TS1:
                return ts1 != null;
            case PerformancePackage.RESPONSE_TIME_REQ__TS2:
                return ts2 != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (responseTime: ");
        result.append(responseTime);
        result.append(", percentage: ");
        result.append(percentage);
        result.append(')');
        return result.toString();
    }

} //ResponseTimeReqImpl
