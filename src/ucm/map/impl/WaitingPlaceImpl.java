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

import ucm.map.MapPackage;
import ucm.map.WaitingPlace;

import urncore.NodeLabel;
import urncore.SpecificationComponentRef;
import urncore.SpecificationDiagram;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Waiting Place</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.map.impl.WaitingPlaceImpl#getWaitType <em>Wait Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WaitingPlaceImpl extends PathNodeImpl implements WaitingPlace {
    /**
     * The default value of the '{@link #getWaitType() <em>Wait Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWaitType()
     * @generated
     * @ordered
     */
    protected static final String WAIT_TYPE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getWaitType() <em>Wait Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWaitType()
     * @generated
     * @ordered
     */
    protected String waitType = WAIT_TYPE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected WaitingPlaceImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return MapPackage.eINSTANCE.getWaitingPlace();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getWaitType() {
        return waitType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setWaitType(String newWaitType) {
        String oldWaitType = waitType;
        waitType = newWaitType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.WAITING_PLACE__WAIT_TYPE, oldWaitType, waitType));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case MapPackage.WAITING_PLACE__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicAdd(otherEnd, msgs);
                case MapPackage.WAITING_PLACE__SPEC_DIAGRAM:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, MapPackage.WAITING_PLACE__SPEC_DIAGRAM, msgs);
                case MapPackage.WAITING_PLACE__COMP_REF:
                    if (compRef != null)
                        msgs = ((InternalEObject)compRef).eInverseRemove(this, UrncorePackage.SPECIFICATION_COMPONENT_REF__NODES, SpecificationComponentRef.class, msgs);
                    return basicSetCompRef((SpecificationComponentRef)otherEnd, msgs);
                case MapPackage.WAITING_PLACE__SUCC:
                    return ((InternalEList)getSucc()).basicAdd(otherEnd, msgs);
                case MapPackage.WAITING_PLACE__PRED:
                    return ((InternalEList)getPred()).basicAdd(otherEnd, msgs);
                case MapPackage.WAITING_PLACE__LABEL:
                    if (label != null)
                        msgs = ((InternalEObject)label).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MapPackage.WAITING_PLACE__LABEL, null, msgs);
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
                case MapPackage.WAITING_PLACE__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
                case MapPackage.WAITING_PLACE__SPEC_DIAGRAM:
                    return eBasicSetContainer(null, MapPackage.WAITING_PLACE__SPEC_DIAGRAM, msgs);
                case MapPackage.WAITING_PLACE__COMP_REF:
                    return basicSetCompRef(null, msgs);
                case MapPackage.WAITING_PLACE__SUCC:
                    return ((InternalEList)getSucc()).basicRemove(otherEnd, msgs);
                case MapPackage.WAITING_PLACE__PRED:
                    return ((InternalEList)getPred()).basicRemove(otherEnd, msgs);
                case MapPackage.WAITING_PLACE__LABEL:
                    return basicSetLabel(null, msgs);
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
                case MapPackage.WAITING_PLACE__SPEC_DIAGRAM:
                    return ((InternalEObject)eContainer).eInverseRemove(this, UrncorePackage.SPECIFICATION_DIAGRAM__NODES, SpecificationDiagram.class, msgs);
                default:
                    return eDynamicBasicRemoveFromContainer(msgs);
            }
        }
        return ((InternalEObject)eContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(EStructuralFeature eFeature, boolean resolve) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case MapPackage.WAITING_PLACE__ID:
                return getId();
            case MapPackage.WAITING_PLACE__NAME:
                return getName();
            case MapPackage.WAITING_PLACE__DESCRIPTION:
                return getDescription();
            case MapPackage.WAITING_PLACE__URN_LINKS:
                return getUrnLinks();
            case MapPackage.WAITING_PLACE__X:
                return new Integer(getX());
            case MapPackage.WAITING_PLACE__Y:
                return new Integer(getY());
            case MapPackage.WAITING_PLACE__SPEC_DIAGRAM:
                return getSpecDiagram();
            case MapPackage.WAITING_PLACE__COMP_REF:
                if (resolve) return getCompRef();
                return basicGetCompRef();
            case MapPackage.WAITING_PLACE__SUCC:
                return getSucc();
            case MapPackage.WAITING_PLACE__PRED:
                return getPred();
            case MapPackage.WAITING_PLACE__LABEL:
                return getLabel();
            case MapPackage.WAITING_PLACE__WAIT_TYPE:
                return getWaitType();
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
            case MapPackage.WAITING_PLACE__ID:
                setId((String)newValue);
                return;
            case MapPackage.WAITING_PLACE__NAME:
                setName((String)newValue);
                return;
            case MapPackage.WAITING_PLACE__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case MapPackage.WAITING_PLACE__URN_LINKS:
                getUrnLinks().clear();
                getUrnLinks().addAll((Collection)newValue);
                return;
            case MapPackage.WAITING_PLACE__X:
                setX(((Integer)newValue).intValue());
                return;
            case MapPackage.WAITING_PLACE__Y:
                setY(((Integer)newValue).intValue());
                return;
            case MapPackage.WAITING_PLACE__SPEC_DIAGRAM:
                setSpecDiagram((SpecificationDiagram)newValue);
                return;
            case MapPackage.WAITING_PLACE__COMP_REF:
                setCompRef((SpecificationComponentRef)newValue);
                return;
            case MapPackage.WAITING_PLACE__SUCC:
                getSucc().clear();
                getSucc().addAll((Collection)newValue);
                return;
            case MapPackage.WAITING_PLACE__PRED:
                getPred().clear();
                getPred().addAll((Collection)newValue);
                return;
            case MapPackage.WAITING_PLACE__LABEL:
                setLabel((NodeLabel)newValue);
                return;
            case MapPackage.WAITING_PLACE__WAIT_TYPE:
                setWaitType((String)newValue);
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
            case MapPackage.WAITING_PLACE__ID:
                setId(ID_EDEFAULT);
                return;
            case MapPackage.WAITING_PLACE__NAME:
                setName(NAME_EDEFAULT);
                return;
            case MapPackage.WAITING_PLACE__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case MapPackage.WAITING_PLACE__URN_LINKS:
                getUrnLinks().clear();
                return;
            case MapPackage.WAITING_PLACE__X:
                setX(X_EDEFAULT);
                return;
            case MapPackage.WAITING_PLACE__Y:
                setY(Y_EDEFAULT);
                return;
            case MapPackage.WAITING_PLACE__SPEC_DIAGRAM:
                setSpecDiagram((SpecificationDiagram)null);
                return;
            case MapPackage.WAITING_PLACE__COMP_REF:
                setCompRef((SpecificationComponentRef)null);
                return;
            case MapPackage.WAITING_PLACE__SUCC:
                getSucc().clear();
                return;
            case MapPackage.WAITING_PLACE__PRED:
                getPred().clear();
                return;
            case MapPackage.WAITING_PLACE__LABEL:
                setLabel((NodeLabel)null);
                return;
            case MapPackage.WAITING_PLACE__WAIT_TYPE:
                setWaitType(WAIT_TYPE_EDEFAULT);
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
            case MapPackage.WAITING_PLACE__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case MapPackage.WAITING_PLACE__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case MapPackage.WAITING_PLACE__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case MapPackage.WAITING_PLACE__URN_LINKS:
                return urnLinks != null && !urnLinks.isEmpty();
            case MapPackage.WAITING_PLACE__X:
                return x != X_EDEFAULT;
            case MapPackage.WAITING_PLACE__Y:
                return y != Y_EDEFAULT;
            case MapPackage.WAITING_PLACE__SPEC_DIAGRAM:
                return getSpecDiagram() != null;
            case MapPackage.WAITING_PLACE__COMP_REF:
                return compRef != null;
            case MapPackage.WAITING_PLACE__SUCC:
                return succ != null && !succ.isEmpty();
            case MapPackage.WAITING_PLACE__PRED:
                return pred != null && !pred.isEmpty();
            case MapPackage.WAITING_PLACE__LABEL:
                return label != null;
            case MapPackage.WAITING_PLACE__WAIT_TYPE:
                return WAIT_TYPE_EDEFAULT == null ? waitType != null : !WAIT_TYPE_EDEFAULT.equals(waitType);
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
        result.append(" (waitType: ");
        result.append(waitType);
        result.append(')');
        return result.toString();
    }

} //WaitingPlaceImpl
