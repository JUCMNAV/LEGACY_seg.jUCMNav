/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.performance.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import ucm.map.ComponentRef;
import ucm.map.MapPackage;
import ucm.map.PathGraph;

import ucm.map.impl.PathNodeImpl;

import ucm.performance.PerformancePackage;
import ucm.performance.ResponseTimeReq;
import ucm.performance.Timestamp;

import urncore.NodeLabel;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Timestamp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.performance.impl.TimestampImpl#getOrientation <em>Orientation</em>}</li>
 *   <li>{@link ucm.performance.impl.TimestampImpl#getTargets <em>Targets</em>}</li>
 *   <li>{@link ucm.performance.impl.TimestampImpl#getSources <em>Sources</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TimestampImpl extends PathNodeImpl implements Timestamp {
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
     * The cached value of the '{@link #getTargets() <em>Targets</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTargets()
     * @generated
     * @ordered
     */
	protected EList targets = null;

    /**
     * The cached value of the '{@link #getSources() <em>Sources</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getSources()
     * @generated
     * @ordered
     */
	protected EList sources = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected TimestampImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return PerformancePackage.eINSTANCE.getTimestamp();
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
            eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.TIMESTAMP__ORIENTATION, oldOrientation, orientation));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getTargets() {
        if (targets == null) {
            targets = new EObjectWithInverseResolvingEList(ResponseTimeReq.class, this, PerformancePackage.TIMESTAMP__TARGETS, PerformancePackage.RESPONSE_TIME_REQ__TS1);
        }
        return targets;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getSources() {
        if (sources == null) {
            sources = new EObjectWithInverseResolvingEList(ResponseTimeReq.class, this, PerformancePackage.TIMESTAMP__SOURCES, PerformancePackage.RESPONSE_TIME_REQ__TS2);
        }
        return sources;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case PerformancePackage.TIMESTAMP__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicAdd(otherEnd, msgs);
                case PerformancePackage.TIMESTAMP__PATH_GRAPH:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, PerformancePackage.TIMESTAMP__PATH_GRAPH, msgs);
                case PerformancePackage.TIMESTAMP__SUCC:
                    return ((InternalEList)getSucc()).basicAdd(otherEnd, msgs);
                case PerformancePackage.TIMESTAMP__PRED:
                    return ((InternalEList)getPred()).basicAdd(otherEnd, msgs);
                case PerformancePackage.TIMESTAMP__COMP_REF:
                    if (compRef != null)
                        msgs = ((InternalEObject)compRef).eInverseRemove(this, MapPackage.COMPONENT_REF__PATH_NODES, ComponentRef.class, msgs);
                    return basicSetCompRef((ComponentRef)otherEnd, msgs);
                case PerformancePackage.TIMESTAMP__LABEL:
                    if (label != null)
                        msgs = ((InternalEObject)label).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PerformancePackage.TIMESTAMP__LABEL, null, msgs);
                    return basicSetLabel((NodeLabel)otherEnd, msgs);
                case PerformancePackage.TIMESTAMP__TARGETS:
                    return ((InternalEList)getTargets()).basicAdd(otherEnd, msgs);
                case PerformancePackage.TIMESTAMP__SOURCES:
                    return ((InternalEList)getSources()).basicAdd(otherEnd, msgs);
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
                case PerformancePackage.TIMESTAMP__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
                case PerformancePackage.TIMESTAMP__PATH_GRAPH:
                    return eBasicSetContainer(null, PerformancePackage.TIMESTAMP__PATH_GRAPH, msgs);
                case PerformancePackage.TIMESTAMP__SUCC:
                    return ((InternalEList)getSucc()).basicRemove(otherEnd, msgs);
                case PerformancePackage.TIMESTAMP__PRED:
                    return ((InternalEList)getPred()).basicRemove(otherEnd, msgs);
                case PerformancePackage.TIMESTAMP__COMP_REF:
                    return basicSetCompRef(null, msgs);
                case PerformancePackage.TIMESTAMP__LABEL:
                    return basicSetLabel(null, msgs);
                case PerformancePackage.TIMESTAMP__TARGETS:
                    return ((InternalEList)getTargets()).basicRemove(otherEnd, msgs);
                case PerformancePackage.TIMESTAMP__SOURCES:
                    return ((InternalEList)getSources()).basicRemove(otherEnd, msgs);
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
                case PerformancePackage.TIMESTAMP__PATH_GRAPH:
                    return ((InternalEObject)eContainer).eInverseRemove(this, MapPackage.PATH_GRAPH__PATH_NODES, PathGraph.class, msgs);
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
            case PerformancePackage.TIMESTAMP__URN_LINKS:
                return getUrnLinks();
            case PerformancePackage.TIMESTAMP__ID:
                return getId();
            case PerformancePackage.TIMESTAMP__NAME:
                return getName();
            case PerformancePackage.TIMESTAMP__DESCRIPTION:
                return getDescription();
            case PerformancePackage.TIMESTAMP__X:
                return new Integer(getX());
            case PerformancePackage.TIMESTAMP__Y:
                return new Integer(getY());
            case PerformancePackage.TIMESTAMP__PATH_GRAPH:
                return getPathGraph();
            case PerformancePackage.TIMESTAMP__SUCC:
                return getSucc();
            case PerformancePackage.TIMESTAMP__PRED:
                return getPred();
            case PerformancePackage.TIMESTAMP__COMP_REF:
                if (resolve) return getCompRef();
                return basicGetCompRef();
            case PerformancePackage.TIMESTAMP__LABEL:
                return getLabel();
            case PerformancePackage.TIMESTAMP__ORIENTATION:
                return getOrientation();
            case PerformancePackage.TIMESTAMP__TARGETS:
                return getTargets();
            case PerformancePackage.TIMESTAMP__SOURCES:
                return getSources();
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
            case PerformancePackage.TIMESTAMP__URN_LINKS:
                getUrnLinks().clear();
                getUrnLinks().addAll((Collection)newValue);
                return;
            case PerformancePackage.TIMESTAMP__ID:
                setId((String)newValue);
                return;
            case PerformancePackage.TIMESTAMP__NAME:
                setName((String)newValue);
                return;
            case PerformancePackage.TIMESTAMP__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case PerformancePackage.TIMESTAMP__X:
                setX(((Integer)newValue).intValue());
                return;
            case PerformancePackage.TIMESTAMP__Y:
                setY(((Integer)newValue).intValue());
                return;
            case PerformancePackage.TIMESTAMP__PATH_GRAPH:
                setPathGraph((PathGraph)newValue);
                return;
            case PerformancePackage.TIMESTAMP__SUCC:
                getSucc().clear();
                getSucc().addAll((Collection)newValue);
                return;
            case PerformancePackage.TIMESTAMP__PRED:
                getPred().clear();
                getPred().addAll((Collection)newValue);
                return;
            case PerformancePackage.TIMESTAMP__COMP_REF:
                setCompRef((ComponentRef)newValue);
                return;
            case PerformancePackage.TIMESTAMP__LABEL:
                setLabel((NodeLabel)newValue);
                return;
            case PerformancePackage.TIMESTAMP__ORIENTATION:
                setOrientation((String)newValue);
                return;
            case PerformancePackage.TIMESTAMP__TARGETS:
                getTargets().clear();
                getTargets().addAll((Collection)newValue);
                return;
            case PerformancePackage.TIMESTAMP__SOURCES:
                getSources().clear();
                getSources().addAll((Collection)newValue);
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
            case PerformancePackage.TIMESTAMP__URN_LINKS:
                getUrnLinks().clear();
                return;
            case PerformancePackage.TIMESTAMP__ID:
                setId(ID_EDEFAULT);
                return;
            case PerformancePackage.TIMESTAMP__NAME:
                setName(NAME_EDEFAULT);
                return;
            case PerformancePackage.TIMESTAMP__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case PerformancePackage.TIMESTAMP__X:
                setX(X_EDEFAULT);
                return;
            case PerformancePackage.TIMESTAMP__Y:
                setY(Y_EDEFAULT);
                return;
            case PerformancePackage.TIMESTAMP__PATH_GRAPH:
                setPathGraph((PathGraph)null);
                return;
            case PerformancePackage.TIMESTAMP__SUCC:
                getSucc().clear();
                return;
            case PerformancePackage.TIMESTAMP__PRED:
                getPred().clear();
                return;
            case PerformancePackage.TIMESTAMP__COMP_REF:
                setCompRef((ComponentRef)null);
                return;
            case PerformancePackage.TIMESTAMP__LABEL:
                setLabel((NodeLabel)null);
                return;
            case PerformancePackage.TIMESTAMP__ORIENTATION:
                setOrientation(ORIENTATION_EDEFAULT);
                return;
            case PerformancePackage.TIMESTAMP__TARGETS:
                getTargets().clear();
                return;
            case PerformancePackage.TIMESTAMP__SOURCES:
                getSources().clear();
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
            case PerformancePackage.TIMESTAMP__URN_LINKS:
                return urnLinks != null && !urnLinks.isEmpty();
            case PerformancePackage.TIMESTAMP__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case PerformancePackage.TIMESTAMP__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case PerformancePackage.TIMESTAMP__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case PerformancePackage.TIMESTAMP__X:
                return x != X_EDEFAULT;
            case PerformancePackage.TIMESTAMP__Y:
                return y != Y_EDEFAULT;
            case PerformancePackage.TIMESTAMP__PATH_GRAPH:
                return getPathGraph() != null;
            case PerformancePackage.TIMESTAMP__SUCC:
                return succ != null && !succ.isEmpty();
            case PerformancePackage.TIMESTAMP__PRED:
                return pred != null && !pred.isEmpty();
            case PerformancePackage.TIMESTAMP__COMP_REF:
                return compRef != null;
            case PerformancePackage.TIMESTAMP__LABEL:
                return label != null;
            case PerformancePackage.TIMESTAMP__ORIENTATION:
                return ORIENTATION_EDEFAULT == null ? orientation != null : !ORIENTATION_EDEFAULT.equals(orientation);
            case PerformancePackage.TIMESTAMP__TARGETS:
                return targets != null && !targets.isEmpty();
            case PerformancePackage.TIMESTAMP__SOURCES:
                return sources != null && !sources.isEmpty();
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

} //TimestampImpl
