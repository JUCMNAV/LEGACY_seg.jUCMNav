/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import ucm.map.Abort;
import ucm.map.MapPackage;

import urncore.Condition;
import urncore.NodeLabel;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abort</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.map.impl.AbortImpl#getCondition <em>Condition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AbortImpl extends PathNodeImpl implements Abort {
    /**
     * The cached value of the '{@link #getCondition() <em>Condition</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCondition()
     * @generated
     * @ordered
     */
    protected EList condition = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AbortImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return MapPackage.eINSTANCE.getAbort();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getCondition() {
        if (condition == null) {
            condition = new EObjectResolvingEList(Condition.class, this, MapPackage.ABORT__CONDITION);
        }
        return condition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case MapPackage.ABORT__FROM_LINKS:
                    return ((InternalEList)getFromLinks()).basicAdd(otherEnd, msgs);
                case MapPackage.ABORT__TO_LINKS:
                    return ((InternalEList)getToLinks()).basicAdd(otherEnd, msgs);
                case MapPackage.ABORT__DIAGRAM:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, MapPackage.ABORT__DIAGRAM, msgs);
                case MapPackage.ABORT__CONT_REF:
                    if (contRef != null)
                        msgs = ((InternalEObject)contRef).eInverseRemove(this, UrncorePackage.IURN_CONTAINER_REF__NODES, IURNContainerRef.class, msgs);
                    return basicSetContRef((IURNContainerRef)otherEnd, msgs);
                case MapPackage.ABORT__SUCC:
                    return ((InternalEList)getSucc()).basicAdd(otherEnd, msgs);
                case MapPackage.ABORT__PRED:
                    return ((InternalEList)getPred()).basicAdd(otherEnd, msgs);
                case MapPackage.ABORT__LABEL:
                    if (label != null)
                        msgs = ((InternalEObject)label).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MapPackage.ABORT__LABEL, null, msgs);
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
                case MapPackage.ABORT__FROM_LINKS:
                    return ((InternalEList)getFromLinks()).basicRemove(otherEnd, msgs);
                case MapPackage.ABORT__TO_LINKS:
                    return ((InternalEList)getToLinks()).basicRemove(otherEnd, msgs);
                case MapPackage.ABORT__DIAGRAM:
                    return eBasicSetContainer(null, MapPackage.ABORT__DIAGRAM, msgs);
                case MapPackage.ABORT__CONT_REF:
                    return basicSetContRef(null, msgs);
                case MapPackage.ABORT__SUCC:
                    return ((InternalEList)getSucc()).basicRemove(otherEnd, msgs);
                case MapPackage.ABORT__PRED:
                    return ((InternalEList)getPred()).basicRemove(otherEnd, msgs);
                case MapPackage.ABORT__LABEL:
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
                case MapPackage.ABORT__DIAGRAM:
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
            case MapPackage.ABORT__FROM_LINKS:
                return getFromLinks();
            case MapPackage.ABORT__TO_LINKS:
                return getToLinks();
            case MapPackage.ABORT__ID:
                return getId();
            case MapPackage.ABORT__NAME:
                return getName();
            case MapPackage.ABORT__DESCRIPTION:
                return getDescription();
            case MapPackage.ABORT__X:
                return new Integer(getX());
            case MapPackage.ABORT__Y:
                return new Integer(getY());
            case MapPackage.ABORT__DIAGRAM:
                return getDiagram();
            case MapPackage.ABORT__CONT_REF:
                if (resolve) return getContRef();
                return basicGetContRef();
            case MapPackage.ABORT__SUCC:
                return getSucc();
            case MapPackage.ABORT__PRED:
                return getPred();
            case MapPackage.ABORT__LABEL:
                return getLabel();
            case MapPackage.ABORT__CONDITION:
                return getCondition();
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
            case MapPackage.ABORT__FROM_LINKS:
                getFromLinks().clear();
                getFromLinks().addAll((Collection)newValue);
                return;
            case MapPackage.ABORT__TO_LINKS:
                getToLinks().clear();
                getToLinks().addAll((Collection)newValue);
                return;
            case MapPackage.ABORT__ID:
                setId((String)newValue);
                return;
            case MapPackage.ABORT__NAME:
                setName((String)newValue);
                return;
            case MapPackage.ABORT__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case MapPackage.ABORT__X:
                setX(((Integer)newValue).intValue());
                return;
            case MapPackage.ABORT__Y:
                setY(((Integer)newValue).intValue());
                return;
            case MapPackage.ABORT__DIAGRAM:
                setDiagram((IURNDiagram)newValue);
                return;
            case MapPackage.ABORT__CONT_REF:
                setContRef((IURNContainerRef)newValue);
                return;
            case MapPackage.ABORT__SUCC:
                getSucc().clear();
                getSucc().addAll((Collection)newValue);
                return;
            case MapPackage.ABORT__PRED:
                getPred().clear();
                getPred().addAll((Collection)newValue);
                return;
            case MapPackage.ABORT__LABEL:
                setLabel((NodeLabel)newValue);
                return;
            case MapPackage.ABORT__CONDITION:
                getCondition().clear();
                getCondition().addAll((Collection)newValue);
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
            case MapPackage.ABORT__FROM_LINKS:
                getFromLinks().clear();
                return;
            case MapPackage.ABORT__TO_LINKS:
                getToLinks().clear();
                return;
            case MapPackage.ABORT__ID:
                setId(ID_EDEFAULT);
                return;
            case MapPackage.ABORT__NAME:
                setName(NAME_EDEFAULT);
                return;
            case MapPackage.ABORT__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case MapPackage.ABORT__X:
                setX(X_EDEFAULT);
                return;
            case MapPackage.ABORT__Y:
                setY(Y_EDEFAULT);
                return;
            case MapPackage.ABORT__DIAGRAM:
                setDiagram((IURNDiagram)null);
                return;
            case MapPackage.ABORT__CONT_REF:
                setContRef((IURNContainerRef)null);
                return;
            case MapPackage.ABORT__SUCC:
                getSucc().clear();
                return;
            case MapPackage.ABORT__PRED:
                getPred().clear();
                return;
            case MapPackage.ABORT__LABEL:
                setLabel((NodeLabel)null);
                return;
            case MapPackage.ABORT__CONDITION:
                getCondition().clear();
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
            case MapPackage.ABORT__FROM_LINKS:
                return fromLinks != null && !fromLinks.isEmpty();
            case MapPackage.ABORT__TO_LINKS:
                return toLinks != null && !toLinks.isEmpty();
            case MapPackage.ABORT__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case MapPackage.ABORT__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case MapPackage.ABORT__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case MapPackage.ABORT__X:
                return x != X_EDEFAULT;
            case MapPackage.ABORT__Y:
                return y != Y_EDEFAULT;
            case MapPackage.ABORT__DIAGRAM:
                return getDiagram() != null;
            case MapPackage.ABORT__CONT_REF:
                return contRef != null;
            case MapPackage.ABORT__SUCC:
                return succ != null && !succ.isEmpty();
            case MapPackage.ABORT__PRED:
                return pred != null && !pred.isEmpty();
            case MapPackage.ABORT__LABEL:
                return label != null;
            case MapPackage.ABORT__CONDITION:
                return condition != null && !condition.isEmpty();
        }
        return eDynamicIsSet(eFeature);
    }

} //AbortImpl
