/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import urncore.DynamicRespKind;
import urncore.DynamicResponsibility;
import urncore.Pool;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Dynamic Responsibility</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link urncore.impl.DynamicResponsibilityImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link urncore.impl.DynamicResponsibilityImpl#isToPath <em>To Path</em>}</li>
 *   <li>{@link urncore.impl.DynamicResponsibilityImpl#getArrowLength <em>Arrow Length</em>}</li>
 *   <li>{@link urncore.impl.DynamicResponsibilityImpl#getPool <em>Pool</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DynamicResponsibilityImpl extends ResponsibilityImpl implements DynamicResponsibility {
    /**
     * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getKind()
     * @generated
     * @ordered
     */
    protected static final DynamicRespKind KIND_EDEFAULT = DynamicRespKind.MOVE_LITERAL;

    /**
     * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getKind()
     * @generated
     * @ordered
     */
    protected DynamicRespKind kind = KIND_EDEFAULT;

    /**
     * The default value of the '{@link #isToPath() <em>To Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isToPath()
     * @generated
     * @ordered
     */
    protected static final boolean TO_PATH_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isToPath() <em>To Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isToPath()
     * @generated
     * @ordered
     */
    protected boolean toPath = TO_PATH_EDEFAULT;

    /**
     * The default value of the '{@link #getArrowLength() <em>Arrow Length</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getArrowLength()
     * @generated
     * @ordered
     */
    protected static final int ARROW_LENGTH_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getArrowLength() <em>Arrow Length</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getArrowLength()
     * @generated
     * @ordered
     */
    protected int arrowLength = ARROW_LENGTH_EDEFAULT;

    /**
     * The cached value of the '{@link #getPool() <em>Pool</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPool()
     * @generated
     * @ordered
     */
    protected Pool pool = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DynamicResponsibilityImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return UrncorePackage.Literals.DYNAMIC_RESPONSIBILITY;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DynamicRespKind getKind() {
        return kind;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setKind(DynamicRespKind newKind) {
        DynamicRespKind oldKind = kind;
        kind = newKind == null ? KIND_EDEFAULT : newKind;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.DYNAMIC_RESPONSIBILITY__KIND, oldKind, kind));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isToPath() {
        return toPath;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setToPath(boolean newToPath) {
        boolean oldToPath = toPath;
        toPath = newToPath;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.DYNAMIC_RESPONSIBILITY__TO_PATH, oldToPath, toPath));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getArrowLength() {
        return arrowLength;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setArrowLength(int newArrowLength) {
        int oldArrowLength = arrowLength;
        arrowLength = newArrowLength;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.DYNAMIC_RESPONSIBILITY__ARROW_LENGTH, oldArrowLength, arrowLength));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Pool getPool() {
        if (pool != null && pool.eIsProxy()) {
            InternalEObject oldPool = (InternalEObject)pool;
            pool = (Pool)eResolveProxy(oldPool);
            if (pool != oldPool) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, UrncorePackage.DYNAMIC_RESPONSIBILITY__POOL, oldPool, pool));
            }
        }
        return pool;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Pool basicGetPool() {
        return pool;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetPool(Pool newPool, NotificationChain msgs) {
        Pool oldPool = pool;
        pool = newPool;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UrncorePackage.DYNAMIC_RESPONSIBILITY__POOL, oldPool, newPool);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPool(Pool newPool) {
        if (newPool != pool) {
            NotificationChain msgs = null;
            if (pool != null)
                msgs = ((InternalEObject)pool).eInverseRemove(this, UrncorePackage.POOL__DYN_RESPONSIBILITIES, Pool.class, msgs);
            if (newPool != null)
                msgs = ((InternalEObject)newPool).eInverseAdd(this, UrncorePackage.POOL__DYN_RESPONSIBILITIES, Pool.class, msgs);
            msgs = basicSetPool(newPool, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.DYNAMIC_RESPONSIBILITY__POOL, newPool, newPool));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__POOL:
                if (pool != null)
                    msgs = ((InternalEObject)pool).eInverseRemove(this, UrncorePackage.POOL__DYN_RESPONSIBILITIES, Pool.class, msgs);
                return basicSetPool((Pool)otherEnd, msgs);
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
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__POOL:
                return basicSetPool(null, msgs);
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
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__KIND:
                return getKind();
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__TO_PATH:
                return isToPath() ? Boolean.TRUE : Boolean.FALSE;
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__ARROW_LENGTH:
                return new Integer(getArrowLength());
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__POOL:
                if (resolve) return getPool();
                return basicGetPool();
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
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__KIND:
                setKind((DynamicRespKind)newValue);
                return;
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__TO_PATH:
                setToPath(((Boolean)newValue).booleanValue());
                return;
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__ARROW_LENGTH:
                setArrowLength(((Integer)newValue).intValue());
                return;
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__POOL:
                setPool((Pool)newValue);
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
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__KIND:
                setKind(KIND_EDEFAULT);
                return;
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__TO_PATH:
                setToPath(TO_PATH_EDEFAULT);
                return;
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__ARROW_LENGTH:
                setArrowLength(ARROW_LENGTH_EDEFAULT);
                return;
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__POOL:
                setPool((Pool)null);
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
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__KIND:
                return kind != KIND_EDEFAULT;
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__TO_PATH:
                return toPath != TO_PATH_EDEFAULT;
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__ARROW_LENGTH:
                return arrowLength != ARROW_LENGTH_EDEFAULT;
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__POOL:
                return pool != null;
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
        result.append(" (kind: ");
        result.append(kind);
        result.append(", toPath: ");
        result.append(toPath);
        result.append(", arrowLength: ");
        result.append(arrowLength);
        result.append(')');
        return result.toString();
    }

} //DynamicResponsibilityImpl
