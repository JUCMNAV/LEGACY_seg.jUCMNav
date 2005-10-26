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

import ucm.map.Loop;
import ucm.map.MapPackage;

import urncore.NodeLabel;
import urncore.SpecificationComponentRef;
import urncore.SpecificationDiagram;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Loop</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.map.impl.LoopImpl#getOrientation <em>Orientation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LoopImpl extends PathNodeImpl implements Loop {
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
    protected LoopImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return MapPackage.eINSTANCE.getLoop();
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
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.LOOP__ORIENTATION, oldOrientation, orientation));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case MapPackage.LOOP__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicAdd(otherEnd, msgs);
                case MapPackage.LOOP__SPEC_DIAGRAM:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, MapPackage.LOOP__SPEC_DIAGRAM, msgs);
                case MapPackage.LOOP__COMP_REF:
                    if (compRef != null)
                        msgs = ((InternalEObject)compRef).eInverseRemove(this, UrncorePackage.SPECIFICATION_COMPONENT_REF__NODES, SpecificationComponentRef.class, msgs);
                    return basicSetCompRef((SpecificationComponentRef)otherEnd, msgs);
                case MapPackage.LOOP__SUCC:
                    return ((InternalEList)getSucc()).basicAdd(otherEnd, msgs);
                case MapPackage.LOOP__PRED:
                    return ((InternalEList)getPred()).basicAdd(otherEnd, msgs);
                case MapPackage.LOOP__LABEL:
                    if (label != null)
                        msgs = ((InternalEObject)label).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MapPackage.LOOP__LABEL, null, msgs);
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
                case MapPackage.LOOP__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
                case MapPackage.LOOP__SPEC_DIAGRAM:
                    return eBasicSetContainer(null, MapPackage.LOOP__SPEC_DIAGRAM, msgs);
                case MapPackage.LOOP__COMP_REF:
                    return basicSetCompRef(null, msgs);
                case MapPackage.LOOP__SUCC:
                    return ((InternalEList)getSucc()).basicRemove(otherEnd, msgs);
                case MapPackage.LOOP__PRED:
                    return ((InternalEList)getPred()).basicRemove(otherEnd, msgs);
                case MapPackage.LOOP__LABEL:
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
                case MapPackage.LOOP__SPEC_DIAGRAM:
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
            case MapPackage.LOOP__ID:
                return getId();
            case MapPackage.LOOP__NAME:
                return getName();
            case MapPackage.LOOP__DESCRIPTION:
                return getDescription();
            case MapPackage.LOOP__URN_LINKS:
                return getUrnLinks();
            case MapPackage.LOOP__X:
                return new Integer(getX());
            case MapPackage.LOOP__Y:
                return new Integer(getY());
            case MapPackage.LOOP__SPEC_DIAGRAM:
                return getSpecDiagram();
            case MapPackage.LOOP__COMP_REF:
                if (resolve) return getCompRef();
                return basicGetCompRef();
            case MapPackage.LOOP__SUCC:
                return getSucc();
            case MapPackage.LOOP__PRED:
                return getPred();
            case MapPackage.LOOP__LABEL:
                return getLabel();
            case MapPackage.LOOP__ORIENTATION:
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
            case MapPackage.LOOP__ID:
                setId((String)newValue);
                return;
            case MapPackage.LOOP__NAME:
                setName((String)newValue);
                return;
            case MapPackage.LOOP__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case MapPackage.LOOP__URN_LINKS:
                getUrnLinks().clear();
                getUrnLinks().addAll((Collection)newValue);
                return;
            case MapPackage.LOOP__X:
                setX(((Integer)newValue).intValue());
                return;
            case MapPackage.LOOP__Y:
                setY(((Integer)newValue).intValue());
                return;
            case MapPackage.LOOP__SPEC_DIAGRAM:
                setSpecDiagram((SpecificationDiagram)newValue);
                return;
            case MapPackage.LOOP__COMP_REF:
                setCompRef((SpecificationComponentRef)newValue);
                return;
            case MapPackage.LOOP__SUCC:
                getSucc().clear();
                getSucc().addAll((Collection)newValue);
                return;
            case MapPackage.LOOP__PRED:
                getPred().clear();
                getPred().addAll((Collection)newValue);
                return;
            case MapPackage.LOOP__LABEL:
                setLabel((NodeLabel)newValue);
                return;
            case MapPackage.LOOP__ORIENTATION:
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
            case MapPackage.LOOP__ID:
                setId(ID_EDEFAULT);
                return;
            case MapPackage.LOOP__NAME:
                setName(NAME_EDEFAULT);
                return;
            case MapPackage.LOOP__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case MapPackage.LOOP__URN_LINKS:
                getUrnLinks().clear();
                return;
            case MapPackage.LOOP__X:
                setX(X_EDEFAULT);
                return;
            case MapPackage.LOOP__Y:
                setY(Y_EDEFAULT);
                return;
            case MapPackage.LOOP__SPEC_DIAGRAM:
                setSpecDiagram((SpecificationDiagram)null);
                return;
            case MapPackage.LOOP__COMP_REF:
                setCompRef((SpecificationComponentRef)null);
                return;
            case MapPackage.LOOP__SUCC:
                getSucc().clear();
                return;
            case MapPackage.LOOP__PRED:
                getPred().clear();
                return;
            case MapPackage.LOOP__LABEL:
                setLabel((NodeLabel)null);
                return;
            case MapPackage.LOOP__ORIENTATION:
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
            case MapPackage.LOOP__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case MapPackage.LOOP__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case MapPackage.LOOP__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case MapPackage.LOOP__URN_LINKS:
                return urnLinks != null && !urnLinks.isEmpty();
            case MapPackage.LOOP__X:
                return x != X_EDEFAULT;
            case MapPackage.LOOP__Y:
                return y != Y_EDEFAULT;
            case MapPackage.LOOP__SPEC_DIAGRAM:
                return getSpecDiagram() != null;
            case MapPackage.LOOP__COMP_REF:
                return compRef != null;
            case MapPackage.LOOP__SUCC:
                return succ != null && !succ.isEmpty();
            case MapPackage.LOOP__PRED:
                return pred != null && !pred.isEmpty();
            case MapPackage.LOOP__LABEL:
                return label != null;
            case MapPackage.LOOP__ORIENTATION:
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

} //LoopImpl
