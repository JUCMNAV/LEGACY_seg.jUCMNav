/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.InternalEList;

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
        return UrncorePackage.eINSTANCE.getDynamicResponsibility();
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
            Pool oldPool = pool;
            pool = (Pool)eResolveProxy((InternalEObject)pool);
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
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case UrncorePackage.DYNAMIC_RESPONSIBILITY__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicAdd(otherEnd, msgs);
                case UrncorePackage.DYNAMIC_RESPONSIBILITY__RESP_REFS:
                    return ((InternalEList)getRespRefs()).basicAdd(otherEnd, msgs);
                case UrncorePackage.DYNAMIC_RESPONSIBILITY__POOL:
                    if (pool != null)
                        msgs = ((InternalEObject)pool).eInverseRemove(this, UrncorePackage.POOL__DYN_RESPONSIBILITIES, Pool.class, msgs);
                    return basicSetPool((Pool)otherEnd, msgs);
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
                case UrncorePackage.DYNAMIC_RESPONSIBILITY__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
                case UrncorePackage.DYNAMIC_RESPONSIBILITY__DEMANDS:
                    return ((InternalEList)getDemands()).basicRemove(otherEnd, msgs);
                case UrncorePackage.DYNAMIC_RESPONSIBILITY__RESP_REFS:
                    return ((InternalEList)getRespRefs()).basicRemove(otherEnd, msgs);
                case UrncorePackage.DYNAMIC_RESPONSIBILITY__POOL:
                    return basicSetPool(null, msgs);
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
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__URN_LINKS:
                return getUrnLinks();
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__ID:
                return getId();
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__NAME:
                return getName();
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__DESCRIPTION:
                return getDescription();
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__DEMANDS:
                return getDemands();
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__RESP_REFS:
                return getRespRefs();
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
        return eDynamicGet(eFeature, resolve);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eSet(EStructuralFeature eFeature, Object newValue) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__URN_LINKS:
                getUrnLinks().clear();
                getUrnLinks().addAll((Collection)newValue);
                return;
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__ID:
                setId((String)newValue);
                return;
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__NAME:
                setName((String)newValue);
                return;
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__DEMANDS:
                getDemands().clear();
                getDemands().addAll((Collection)newValue);
                return;
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__RESP_REFS:
                getRespRefs().clear();
                getRespRefs().addAll((Collection)newValue);
                return;
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
        eDynamicSet(eFeature, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset(EStructuralFeature eFeature) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__URN_LINKS:
                getUrnLinks().clear();
                return;
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__ID:
                setId(ID_EDEFAULT);
                return;
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__NAME:
                setName(NAME_EDEFAULT);
                return;
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__DEMANDS:
                getDemands().clear();
                return;
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__RESP_REFS:
                getRespRefs().clear();
                return;
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
        eDynamicUnset(eFeature);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet(EStructuralFeature eFeature) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__URN_LINKS:
                return urnLinks != null && !urnLinks.isEmpty();
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__DEMANDS:
                return demands != null && !demands.isEmpty();
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__RESP_REFS:
                return respRefs != null && !respRefs.isEmpty();
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__KIND:
                return kind != KIND_EDEFAULT;
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__TO_PATH:
                return toPath != TO_PATH_EDEFAULT;
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__ARROW_LENGTH:
                return arrowLength != ARROW_LENGTH_EDEFAULT;
            case UrncorePackage.DYNAMIC_RESPONSIBILITY__POOL:
                return pool != null;
        }
        return eDynamicIsSet(eFeature);
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
