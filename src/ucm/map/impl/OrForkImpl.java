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
import ucm.map.OrFork;

import urncore.NodeLabel;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Or Fork</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.map.impl.OrForkImpl#getOrientation <em>Orientation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OrForkImpl extends PathNodeImpl implements OrFork {
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
    protected OrForkImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return MapPackage.eINSTANCE.getOrFork();
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
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.OR_FORK__ORIENTATION, oldOrientation, orientation));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case MapPackage.OR_FORK__URNLINKS:
                    return ((InternalEList)getUrnlinks()).basicAdd(otherEnd, msgs);
                case MapPackage.OR_FORK__DIAGRAM:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, MapPackage.OR_FORK__DIAGRAM, msgs);
                case MapPackage.OR_FORK__CONT_REF:
                    if (contRef != null)
                        msgs = ((InternalEObject)contRef).eInverseRemove(this, UrncorePackage.IURN_CONTAINER_REF__NODES, IURNContainerRef.class, msgs);
                    return basicSetContRef((IURNContainerRef)otherEnd, msgs);
                case MapPackage.OR_FORK__SUCC:
                    return ((InternalEList)getSucc()).basicAdd(otherEnd, msgs);
                case MapPackage.OR_FORK__PRED:
                    return ((InternalEList)getPred()).basicAdd(otherEnd, msgs);
                case MapPackage.OR_FORK__LABEL:
                    if (label != null)
                        msgs = ((InternalEObject)label).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MapPackage.OR_FORK__LABEL, null, msgs);
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
                case MapPackage.OR_FORK__URNLINKS:
                    return ((InternalEList)getUrnlinks()).basicRemove(otherEnd, msgs);
                case MapPackage.OR_FORK__DIAGRAM:
                    return eBasicSetContainer(null, MapPackage.OR_FORK__DIAGRAM, msgs);
                case MapPackage.OR_FORK__CONT_REF:
                    return basicSetContRef(null, msgs);
                case MapPackage.OR_FORK__SUCC:
                    return ((InternalEList)getSucc()).basicRemove(otherEnd, msgs);
                case MapPackage.OR_FORK__PRED:
                    return ((InternalEList)getPred()).basicRemove(otherEnd, msgs);
                case MapPackage.OR_FORK__LABEL:
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
                case MapPackage.OR_FORK__DIAGRAM:
                    return eContainer.eInverseRemove(this, UrncorePackage.IURN_DIAGRAM__NODES, IURNDiagram.class, msgs);
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
            case MapPackage.OR_FORK__ID:
                return getId();
            case MapPackage.OR_FORK__NAME:
                return getName();
            case MapPackage.OR_FORK__DESCRIPTION:
                return getDescription();
            case MapPackage.OR_FORK__URNLINKS:
                return getUrnlinks();
            case MapPackage.OR_FORK__X:
                return new Integer(getX());
            case MapPackage.OR_FORK__Y:
                return new Integer(getY());
            case MapPackage.OR_FORK__DIAGRAM:
                return getDiagram();
            case MapPackage.OR_FORK__CONT_REF:
                if (resolve) return getContRef();
                return basicGetContRef();
            case MapPackage.OR_FORK__SUCC:
                return getSucc();
            case MapPackage.OR_FORK__PRED:
                return getPred();
            case MapPackage.OR_FORK__LABEL:
                return getLabel();
            case MapPackage.OR_FORK__ORIENTATION:
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
            case MapPackage.OR_FORK__ID:
                setId((String)newValue);
                return;
            case MapPackage.OR_FORK__NAME:
                setName((String)newValue);
                return;
            case MapPackage.OR_FORK__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case MapPackage.OR_FORK__URNLINKS:
                getUrnlinks().clear();
                getUrnlinks().addAll((Collection)newValue);
                return;
            case MapPackage.OR_FORK__X:
                setX(((Integer)newValue).intValue());
                return;
            case MapPackage.OR_FORK__Y:
                setY(((Integer)newValue).intValue());
                return;
            case MapPackage.OR_FORK__DIAGRAM:
                setDiagram((IURNDiagram)newValue);
                return;
            case MapPackage.OR_FORK__CONT_REF:
                setContRef((IURNContainerRef)newValue);
                return;
            case MapPackage.OR_FORK__SUCC:
                getSucc().clear();
                getSucc().addAll((Collection)newValue);
                return;
            case MapPackage.OR_FORK__PRED:
                getPred().clear();
                getPred().addAll((Collection)newValue);
                return;
            case MapPackage.OR_FORK__LABEL:
                setLabel((NodeLabel)newValue);
                return;
            case MapPackage.OR_FORK__ORIENTATION:
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
            case MapPackage.OR_FORK__ID:
                setId(ID_EDEFAULT);
                return;
            case MapPackage.OR_FORK__NAME:
                setName(NAME_EDEFAULT);
                return;
            case MapPackage.OR_FORK__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case MapPackage.OR_FORK__URNLINKS:
                getUrnlinks().clear();
                return;
            case MapPackage.OR_FORK__X:
                setX(X_EDEFAULT);
                return;
            case MapPackage.OR_FORK__Y:
                setY(Y_EDEFAULT);
                return;
            case MapPackage.OR_FORK__DIAGRAM:
                setDiagram((IURNDiagram)null);
                return;
            case MapPackage.OR_FORK__CONT_REF:
                setContRef((IURNContainerRef)null);
                return;
            case MapPackage.OR_FORK__SUCC:
                getSucc().clear();
                return;
            case MapPackage.OR_FORK__PRED:
                getPred().clear();
                return;
            case MapPackage.OR_FORK__LABEL:
                setLabel((NodeLabel)null);
                return;
            case MapPackage.OR_FORK__ORIENTATION:
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
            case MapPackage.OR_FORK__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case MapPackage.OR_FORK__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case MapPackage.OR_FORK__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case MapPackage.OR_FORK__URNLINKS:
                return urnlinks != null && !urnlinks.isEmpty();
            case MapPackage.OR_FORK__X:
                return x != X_EDEFAULT;
            case MapPackage.OR_FORK__Y:
                return y != Y_EDEFAULT;
            case MapPackage.OR_FORK__DIAGRAM:
                return getDiagram() != null;
            case MapPackage.OR_FORK__CONT_REF:
                return contRef != null;
            case MapPackage.OR_FORK__SUCC:
                return succ != null && !succ.isEmpty();
            case MapPackage.OR_FORK__PRED:
                return pred != null && !pred.isEmpty();
            case MapPackage.OR_FORK__LABEL:
                return label != null;
            case MapPackage.OR_FORK__ORIENTATION:
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

} //OrForkImpl
