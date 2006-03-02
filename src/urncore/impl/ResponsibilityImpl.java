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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import ucm.map.MapPackage;
import ucm.map.RespRef;

import ucm.performance.Demand;
import ucm.performance.PerformancePackage;

import urncore.Responsibility;
import urncore.URNdefinition;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Responsibility</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link urncore.impl.ResponsibilityImpl#isEmpty <em>Empty</em>}</li>
 *   <li>{@link urncore.impl.ResponsibilityImpl#getUrndefinition <em>Urndefinition</em>}</li>
 *   <li>{@link urncore.impl.ResponsibilityImpl#getDemands <em>Demands</em>}</li>
 *   <li>{@link urncore.impl.ResponsibilityImpl#getRespRefs <em>Resp Refs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResponsibilityImpl extends UCMmodelElementImpl implements Responsibility {
    /**
     * The default value of the '{@link #isEmpty() <em>Empty</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isEmpty()
     * @generated
     * @ordered
     */
    protected static final boolean EMPTY_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isEmpty() <em>Empty</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isEmpty()
     * @generated
     * @ordered
     */
    protected boolean empty = EMPTY_EDEFAULT;

    /**
     * The cached value of the '{@link #getDemands() <em>Demands</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDemands()
     * @generated
     * @ordered
     */
    protected EList demands = null;

    /**
     * The cached value of the '{@link #getRespRefs() <em>Resp Refs</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRespRefs()
     * @generated
     * @ordered
     */
    protected EList respRefs = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ResponsibilityImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return UrncorePackage.eINSTANCE.getResponsibility();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isEmpty() {
        return empty;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEmpty(boolean newEmpty) {
        boolean oldEmpty = empty;
        empty = newEmpty;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.RESPONSIBILITY__EMPTY, oldEmpty, empty));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public URNdefinition getUrndefinition() {
        if (eContainerFeatureID != UrncorePackage.RESPONSIBILITY__URNDEFINITION) return null;
        return (URNdefinition)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUrndefinition(URNdefinition newUrndefinition) {
        if (newUrndefinition != eContainer || (eContainerFeatureID != UrncorePackage.RESPONSIBILITY__URNDEFINITION && newUrndefinition != null)) {
            if (EcoreUtil.isAncestor(this, newUrndefinition))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newUrndefinition != null)
                msgs = ((InternalEObject)newUrndefinition).eInverseAdd(this, UrncorePackage.UR_NDEFINITION__RESPONSIBILITIES, URNdefinition.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newUrndefinition, UrncorePackage.RESPONSIBILITY__URNDEFINITION, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.RESPONSIBILITY__URNDEFINITION, newUrndefinition, newUrndefinition));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getDemands() {
        if (demands == null) {
            demands = new EObjectContainmentWithInverseEList(Demand.class, this, UrncorePackage.RESPONSIBILITY__DEMANDS, PerformancePackage.DEMAND__RESPONSIBILITY);
        }
        return demands;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getRespRefs() {
        if (respRefs == null) {
            respRefs = new EObjectWithInverseResolvingEList(RespRef.class, this, UrncorePackage.RESPONSIBILITY__RESP_REFS, MapPackage.RESP_REF__RESP_DEF);
        }
        return respRefs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case UrncorePackage.RESPONSIBILITY__FROM_LINKS:
                    return ((InternalEList)getFromLinks()).basicAdd(otherEnd, msgs);
                case UrncorePackage.RESPONSIBILITY__TO_LINKS:
                    return ((InternalEList)getToLinks()).basicAdd(otherEnd, msgs);
                case UrncorePackage.RESPONSIBILITY__URNDEFINITION:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, UrncorePackage.RESPONSIBILITY__URNDEFINITION, msgs);
                case UrncorePackage.RESPONSIBILITY__DEMANDS:
                    return ((InternalEList)getDemands()).basicAdd(otherEnd, msgs);
                case UrncorePackage.RESPONSIBILITY__RESP_REFS:
                    return ((InternalEList)getRespRefs()).basicAdd(otherEnd, msgs);
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
                case UrncorePackage.RESPONSIBILITY__FROM_LINKS:
                    return ((InternalEList)getFromLinks()).basicRemove(otherEnd, msgs);
                case UrncorePackage.RESPONSIBILITY__TO_LINKS:
                    return ((InternalEList)getToLinks()).basicRemove(otherEnd, msgs);
                case UrncorePackage.RESPONSIBILITY__URNDEFINITION:
                    return eBasicSetContainer(null, UrncorePackage.RESPONSIBILITY__URNDEFINITION, msgs);
                case UrncorePackage.RESPONSIBILITY__DEMANDS:
                    return ((InternalEList)getDemands()).basicRemove(otherEnd, msgs);
                case UrncorePackage.RESPONSIBILITY__RESP_REFS:
                    return ((InternalEList)getRespRefs()).basicRemove(otherEnd, msgs);
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
                case UrncorePackage.RESPONSIBILITY__URNDEFINITION:
                    return eContainer.eInverseRemove(this, UrncorePackage.UR_NDEFINITION__RESPONSIBILITIES, URNdefinition.class, msgs);
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
            case UrncorePackage.RESPONSIBILITY__FROM_LINKS:
                return getFromLinks();
            case UrncorePackage.RESPONSIBILITY__TO_LINKS:
                return getToLinks();
            case UrncorePackage.RESPONSIBILITY__ID:
                return getId();
            case UrncorePackage.RESPONSIBILITY__NAME:
                return getName();
            case UrncorePackage.RESPONSIBILITY__DESCRIPTION:
                return getDescription();
            case UrncorePackage.RESPONSIBILITY__EMPTY:
                return isEmpty() ? Boolean.TRUE : Boolean.FALSE;
            case UrncorePackage.RESPONSIBILITY__URNDEFINITION:
                return getUrndefinition();
            case UrncorePackage.RESPONSIBILITY__DEMANDS:
                return getDemands();
            case UrncorePackage.RESPONSIBILITY__RESP_REFS:
                return getRespRefs();
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
            case UrncorePackage.RESPONSIBILITY__FROM_LINKS:
                getFromLinks().clear();
                getFromLinks().addAll((Collection)newValue);
                return;
            case UrncorePackage.RESPONSIBILITY__TO_LINKS:
                getToLinks().clear();
                getToLinks().addAll((Collection)newValue);
                return;
            case UrncorePackage.RESPONSIBILITY__ID:
                setId((String)newValue);
                return;
            case UrncorePackage.RESPONSIBILITY__NAME:
                setName((String)newValue);
                return;
            case UrncorePackage.RESPONSIBILITY__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case UrncorePackage.RESPONSIBILITY__EMPTY:
                setEmpty(((Boolean)newValue).booleanValue());
                return;
            case UrncorePackage.RESPONSIBILITY__URNDEFINITION:
                setUrndefinition((URNdefinition)newValue);
                return;
            case UrncorePackage.RESPONSIBILITY__DEMANDS:
                getDemands().clear();
                getDemands().addAll((Collection)newValue);
                return;
            case UrncorePackage.RESPONSIBILITY__RESP_REFS:
                getRespRefs().clear();
                getRespRefs().addAll((Collection)newValue);
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
            case UrncorePackage.RESPONSIBILITY__FROM_LINKS:
                getFromLinks().clear();
                return;
            case UrncorePackage.RESPONSIBILITY__TO_LINKS:
                getToLinks().clear();
                return;
            case UrncorePackage.RESPONSIBILITY__ID:
                setId(ID_EDEFAULT);
                return;
            case UrncorePackage.RESPONSIBILITY__NAME:
                setName(NAME_EDEFAULT);
                return;
            case UrncorePackage.RESPONSIBILITY__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case UrncorePackage.RESPONSIBILITY__EMPTY:
                setEmpty(EMPTY_EDEFAULT);
                return;
            case UrncorePackage.RESPONSIBILITY__URNDEFINITION:
                setUrndefinition((URNdefinition)null);
                return;
            case UrncorePackage.RESPONSIBILITY__DEMANDS:
                getDemands().clear();
                return;
            case UrncorePackage.RESPONSIBILITY__RESP_REFS:
                getRespRefs().clear();
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
            case UrncorePackage.RESPONSIBILITY__FROM_LINKS:
                return fromLinks != null && !fromLinks.isEmpty();
            case UrncorePackage.RESPONSIBILITY__TO_LINKS:
                return toLinks != null && !toLinks.isEmpty();
            case UrncorePackage.RESPONSIBILITY__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case UrncorePackage.RESPONSIBILITY__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case UrncorePackage.RESPONSIBILITY__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case UrncorePackage.RESPONSIBILITY__EMPTY:
                return empty != EMPTY_EDEFAULT;
            case UrncorePackage.RESPONSIBILITY__URNDEFINITION:
                return getUrndefinition() != null;
            case UrncorePackage.RESPONSIBILITY__DEMANDS:
                return demands != null && !demands.isEmpty();
            case UrncorePackage.RESPONSIBILITY__RESP_REFS:
                return respRefs != null && !respRefs.isEmpty();
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
        result.append(" (empty: ");
        result.append(empty);
        result.append(')');
        return result.toString();
    }

} //ResponsibilityImpl
