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

import ucm.map.AndFork;
import ucm.map.ComponentRef;
import ucm.map.MapPackage;

import urncore.NodeLabel;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>And Fork</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.map.impl.AndForkImpl#getOrientation <em>Orientation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AndForkImpl extends PathNodeImpl implements AndFork {
    /**
     * The default value of the '{@link #getOrientation() <em>Orientation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOrientation()
     * @generated
     * @ordered
     */
    protected static final String ORIENTATION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getOrientation() <em>Orientation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOrientation()
     * @generated
     * @ordered
     */
    protected String orientation = ORIENTATION_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AndForkImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return MapPackage.eINSTANCE.getAndFork();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getOrientation() {
        return orientation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setOrientation(String newOrientation) {
        String oldOrientation = orientation;
        orientation = newOrientation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.AND_FORK__ORIENTATION, oldOrientation, orientation));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case MapPackage.AND_FORK__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicAdd(otherEnd, msgs);
                case MapPackage.AND_FORK__SUCC:
                    return ((InternalEList)getSucc()).basicAdd(otherEnd, msgs);
                case MapPackage.AND_FORK__PRED:
                    return ((InternalEList)getPred()).basicAdd(otherEnd, msgs);
                case MapPackage.AND_FORK__COMP_REF:
                    if (compRef != null)
                        msgs = ((InternalEObject)compRef).eInverseRemove(this, MapPackage.COMPONENT_REF__PATH_NODES, ComponentRef.class, msgs);
                    return basicSetCompRef((ComponentRef)otherEnd, msgs);
                case MapPackage.AND_FORK__LABEL:
                    if (label != null)
                        msgs = ((InternalEObject)label).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MapPackage.AND_FORK__LABEL, null, msgs);
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
                case MapPackage.AND_FORK__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
                case MapPackage.AND_FORK__SUCC:
                    return ((InternalEList)getSucc()).basicRemove(otherEnd, msgs);
                case MapPackage.AND_FORK__PRED:
                    return ((InternalEList)getPred()).basicRemove(otherEnd, msgs);
                case MapPackage.AND_FORK__COMP_REF:
                    return basicSetCompRef(null, msgs);
                case MapPackage.AND_FORK__LABEL:
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
    public Object eGet(EStructuralFeature eFeature, boolean resolve) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case MapPackage.AND_FORK__URN_LINKS:
                return getUrnLinks();
            case MapPackage.AND_FORK__ID:
                return getId();
            case MapPackage.AND_FORK__NAME:
                return getName();
            case MapPackage.AND_FORK__DESCRIPTION:
                return getDescription();
            case MapPackage.AND_FORK__X:
                return new Integer(getX());
            case MapPackage.AND_FORK__Y:
                return new Integer(getY());
            case MapPackage.AND_FORK__SUCC:
                return getSucc();
            case MapPackage.AND_FORK__PRED:
                return getPred();
            case MapPackage.AND_FORK__COMP_REF:
                if (resolve) return getCompRef();
                return basicGetCompRef();
            case MapPackage.AND_FORK__LABEL:
                return getLabel();
            case MapPackage.AND_FORK__ORIENTATION:
                return getOrientation();
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
            case MapPackage.AND_FORK__URN_LINKS:
                getUrnLinks().clear();
                getUrnLinks().addAll((Collection)newValue);
                return;
            case MapPackage.AND_FORK__ID:
                setId((String)newValue);
                return;
            case MapPackage.AND_FORK__NAME:
                setName((String)newValue);
                return;
            case MapPackage.AND_FORK__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case MapPackage.AND_FORK__X:
                setX(((Integer)newValue).intValue());
                return;
            case MapPackage.AND_FORK__Y:
                setY(((Integer)newValue).intValue());
                return;
            case MapPackage.AND_FORK__SUCC:
                getSucc().clear();
                getSucc().addAll((Collection)newValue);
                return;
            case MapPackage.AND_FORK__PRED:
                getPred().clear();
                getPred().addAll((Collection)newValue);
                return;
            case MapPackage.AND_FORK__COMP_REF:
                setCompRef((ComponentRef)newValue);
                return;
            case MapPackage.AND_FORK__LABEL:
                setLabel((NodeLabel)newValue);
                return;
            case MapPackage.AND_FORK__ORIENTATION:
                setOrientation((String)newValue);
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
            case MapPackage.AND_FORK__URN_LINKS:
                getUrnLinks().clear();
                return;
            case MapPackage.AND_FORK__ID:
                setId(ID_EDEFAULT);
                return;
            case MapPackage.AND_FORK__NAME:
                setName(NAME_EDEFAULT);
                return;
            case MapPackage.AND_FORK__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case MapPackage.AND_FORK__X:
                setX(X_EDEFAULT);
                return;
            case MapPackage.AND_FORK__Y:
                setY(Y_EDEFAULT);
                return;
            case MapPackage.AND_FORK__SUCC:
                getSucc().clear();
                return;
            case MapPackage.AND_FORK__PRED:
                getPred().clear();
                return;
            case MapPackage.AND_FORK__COMP_REF:
                setCompRef((ComponentRef)null);
                return;
            case MapPackage.AND_FORK__LABEL:
                setLabel((NodeLabel)null);
                return;
            case MapPackage.AND_FORK__ORIENTATION:
                setOrientation(ORIENTATION_EDEFAULT);
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
            case MapPackage.AND_FORK__URN_LINKS:
                return urnLinks != null && !urnLinks.isEmpty();
            case MapPackage.AND_FORK__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case MapPackage.AND_FORK__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case MapPackage.AND_FORK__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case MapPackage.AND_FORK__X:
                return x != X_EDEFAULT;
            case MapPackage.AND_FORK__Y:
                return y != Y_EDEFAULT;
            case MapPackage.AND_FORK__SUCC:
                return succ != null && !succ.isEmpty();
            case MapPackage.AND_FORK__PRED:
                return pred != null && !pred.isEmpty();
            case MapPackage.AND_FORK__COMP_REF:
                return compRef != null;
            case MapPackage.AND_FORK__LABEL:
                return label != null;
            case MapPackage.AND_FORK__ORIENTATION:
                return ORIENTATION_EDEFAULT == null ? orientation != null : !ORIENTATION_EDEFAULT.equals(orientation);
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
        result.append(" (orientation: ");
        result.append(orientation);
        result.append(')');
        return result.toString();
    }

} //AndForkImpl
