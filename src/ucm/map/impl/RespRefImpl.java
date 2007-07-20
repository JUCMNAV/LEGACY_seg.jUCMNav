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

import ucm.map.MapPackage;
import ucm.map.RespRef;
import urncore.Responsibility;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resp Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.map.impl.RespRefImpl#getRepetitionCount <em>Repetition Count</em>}</li>
 *   <li>{@link ucm.map.impl.RespRefImpl#getHostDemand <em>Host Demand</em>}</li>
 *   <li>{@link ucm.map.impl.RespRefImpl#getRespDef <em>Resp Def</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RespRefImpl extends PathNodeImpl implements RespRef {
    /**
     * The default value of the '{@link #getRepetitionCount() <em>Repetition Count</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRepetitionCount()
     * @generated
     * @ordered
     */
    protected static final String REPETITION_COUNT_EDEFAULT = "1";

    /**
     * The cached value of the '{@link #getRepetitionCount() <em>Repetition Count</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRepetitionCount()
     * @generated
     * @ordered
     */
    protected String repetitionCount = REPETITION_COUNT_EDEFAULT;

    /**
     * The default value of the '{@link #getHostDemand() <em>Host Demand</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getHostDemand()
     * @generated
     * @ordered
     */
	protected static final String HOST_DEMAND_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getHostDemand() <em>Host Demand</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getHostDemand()
     * @generated
     * @ordered
     */
	protected String hostDemand = HOST_DEMAND_EDEFAULT;

    /**
     * The cached value of the '{@link #getRespDef() <em>Resp Def</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRespDef()
     * @generated
     * @ordered
     */
    protected Responsibility respDef = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected RespRefImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return MapPackage.Literals.RESP_REF;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getRepetitionCount() {
        return repetitionCount;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setRepetitionCount(String newRepetitionCount) {
        String oldRepetitionCount = repetitionCount;
        repetitionCount = newRepetitionCount;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.RESP_REF__REPETITION_COUNT, oldRepetitionCount, repetitionCount));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getHostDemand() {
        return hostDemand;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setHostDemand(String newHostDemand) {
        String oldHostDemand = hostDemand;
        hostDemand = newHostDemand;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.RESP_REF__HOST_DEMAND, oldHostDemand, hostDemand));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Responsibility getRespDef() {
        if (respDef != null && respDef.eIsProxy()) {
            InternalEObject oldRespDef = (InternalEObject)respDef;
            respDef = (Responsibility)eResolveProxy(oldRespDef);
            if (respDef != oldRespDef) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, MapPackage.RESP_REF__RESP_DEF, oldRespDef, respDef));
            }
        }
        return respDef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Responsibility basicGetRespDef() {
        return respDef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetRespDef(Responsibility newRespDef, NotificationChain msgs) {
        Responsibility oldRespDef = respDef;
        respDef = newRespDef;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.RESP_REF__RESP_DEF, oldRespDef, newRespDef);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRespDef(Responsibility newRespDef) {
        if (newRespDef != respDef) {
            NotificationChain msgs = null;
            if (respDef != null)
                msgs = ((InternalEObject)respDef).eInverseRemove(this, UrncorePackage.RESPONSIBILITY__RESP_REFS, Responsibility.class, msgs);
            if (newRespDef != null)
                msgs = ((InternalEObject)newRespDef).eInverseAdd(this, UrncorePackage.RESPONSIBILITY__RESP_REFS, Responsibility.class, msgs);
            msgs = basicSetRespDef(newRespDef, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.RESP_REF__RESP_DEF, newRespDef, newRespDef));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case MapPackage.RESP_REF__RESP_DEF:
                if (respDef != null)
                    msgs = ((InternalEObject)respDef).eInverseRemove(this, UrncorePackage.RESPONSIBILITY__RESP_REFS, Responsibility.class, msgs);
                return basicSetRespDef((Responsibility)otherEnd, msgs);
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
            case MapPackage.RESP_REF__RESP_DEF:
                return basicSetRespDef(null, msgs);
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
            case MapPackage.RESP_REF__REPETITION_COUNT:
                return getRepetitionCount();
            case MapPackage.RESP_REF__HOST_DEMAND:
                return getHostDemand();
            case MapPackage.RESP_REF__RESP_DEF:
                if (resolve) return getRespDef();
                return basicGetRespDef();
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
            case MapPackage.RESP_REF__REPETITION_COUNT:
                setRepetitionCount((String)newValue);
                return;
            case MapPackage.RESP_REF__HOST_DEMAND:
                setHostDemand((String)newValue);
                return;
            case MapPackage.RESP_REF__RESP_DEF:
                setRespDef((Responsibility)newValue);
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
            case MapPackage.RESP_REF__REPETITION_COUNT:
                setRepetitionCount(REPETITION_COUNT_EDEFAULT);
                return;
            case MapPackage.RESP_REF__HOST_DEMAND:
                setHostDemand(HOST_DEMAND_EDEFAULT);
                return;
            case MapPackage.RESP_REF__RESP_DEF:
                setRespDef((Responsibility)null);
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
            case MapPackage.RESP_REF__REPETITION_COUNT:
                return REPETITION_COUNT_EDEFAULT == null ? repetitionCount != null : !REPETITION_COUNT_EDEFAULT.equals(repetitionCount);
            case MapPackage.RESP_REF__HOST_DEMAND:
                return HOST_DEMAND_EDEFAULT == null ? hostDemand != null : !HOST_DEMAND_EDEFAULT.equals(hostDemand);
            case MapPackage.RESP_REF__RESP_DEF:
                return respDef != null;
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
        result.append(" (repetitionCount: ");
        result.append(repetitionCount);
        result.append(", hostDemand: ");
        result.append(hostDemand);
        result.append(')');
        return result.toString();
    }

} //RespRefImpl
