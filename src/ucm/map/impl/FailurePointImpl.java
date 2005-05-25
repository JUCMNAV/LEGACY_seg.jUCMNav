/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

import ucm.map.ComponentRef;
import ucm.map.FailurePoint;
import ucm.map.MapPackage;
import ucm.map.PathGraph;

import urncore.NodeLabel;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Failure Point</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class FailurePointImpl extends PathNodeImpl implements FailurePoint {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FailurePointImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return MapPackage.eINSTANCE.getFailurePoint();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case MapPackage.FAILURE_POINT__URN_LINKS:
					return ((InternalEList)getUrnLinks()).basicAdd(otherEnd, msgs);
				case MapPackage.FAILURE_POINT__PATH_GRAPH:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, MapPackage.FAILURE_POINT__PATH_GRAPH, msgs);
				case MapPackage.FAILURE_POINT__SUCC:
					return ((InternalEList)getSucc()).basicAdd(otherEnd, msgs);
				case MapPackage.FAILURE_POINT__PRED:
					return ((InternalEList)getPred()).basicAdd(otherEnd, msgs);
				case MapPackage.FAILURE_POINT__COMP_REF:
					if (compRef != null)
						msgs = ((InternalEObject)compRef).eInverseRemove(this, MapPackage.COMPONENT_REF__PATH_NODES, ComponentRef.class, msgs);
					return basicSetCompRef((ComponentRef)otherEnd, msgs);
				case MapPackage.FAILURE_POINT__LABEL:
					if (label != null)
						msgs = ((InternalEObject)label).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MapPackage.FAILURE_POINT__LABEL, null, msgs);
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
				case MapPackage.FAILURE_POINT__URN_LINKS:
					return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
				case MapPackage.FAILURE_POINT__PATH_GRAPH:
					return eBasicSetContainer(null, MapPackage.FAILURE_POINT__PATH_GRAPH, msgs);
				case MapPackage.FAILURE_POINT__SUCC:
					return ((InternalEList)getSucc()).basicRemove(otherEnd, msgs);
				case MapPackage.FAILURE_POINT__PRED:
					return ((InternalEList)getPred()).basicRemove(otherEnd, msgs);
				case MapPackage.FAILURE_POINT__COMP_REF:
					return basicSetCompRef(null, msgs);
				case MapPackage.FAILURE_POINT__LABEL:
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
				case MapPackage.FAILURE_POINT__PATH_GRAPH:
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
			case MapPackage.FAILURE_POINT__URN_LINKS:
				return getUrnLinks();
			case MapPackage.FAILURE_POINT__ID:
				return getId();
			case MapPackage.FAILURE_POINT__NAME:
				return getName();
			case MapPackage.FAILURE_POINT__DESCRIPTION:
				return getDescription();
			case MapPackage.FAILURE_POINT__X:
				return new Integer(getX());
			case MapPackage.FAILURE_POINT__Y:
				return new Integer(getY());
			case MapPackage.FAILURE_POINT__PATH_GRAPH:
				return getPathGraph();
			case MapPackage.FAILURE_POINT__SUCC:
				return getSucc();
			case MapPackage.FAILURE_POINT__PRED:
				return getPred();
			case MapPackage.FAILURE_POINT__COMP_REF:
				if (resolve) return getCompRef();
				return basicGetCompRef();
			case MapPackage.FAILURE_POINT__LABEL:
				return getLabel();
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
			case MapPackage.FAILURE_POINT__URN_LINKS:
				getUrnLinks().clear();
				getUrnLinks().addAll((Collection)newValue);
				return;
			case MapPackage.FAILURE_POINT__ID:
				setId((String)newValue);
				return;
			case MapPackage.FAILURE_POINT__NAME:
				setName((String)newValue);
				return;
			case MapPackage.FAILURE_POINT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case MapPackage.FAILURE_POINT__X:
				setX(((Integer)newValue).intValue());
				return;
			case MapPackage.FAILURE_POINT__Y:
				setY(((Integer)newValue).intValue());
				return;
			case MapPackage.FAILURE_POINT__PATH_GRAPH:
				setPathGraph((PathGraph)newValue);
				return;
			case MapPackage.FAILURE_POINT__SUCC:
				getSucc().clear();
				getSucc().addAll((Collection)newValue);
				return;
			case MapPackage.FAILURE_POINT__PRED:
				getPred().clear();
				getPred().addAll((Collection)newValue);
				return;
			case MapPackage.FAILURE_POINT__COMP_REF:
				setCompRef((ComponentRef)newValue);
				return;
			case MapPackage.FAILURE_POINT__LABEL:
				setLabel((NodeLabel)newValue);
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
			case MapPackage.FAILURE_POINT__URN_LINKS:
				getUrnLinks().clear();
				return;
			case MapPackage.FAILURE_POINT__ID:
				setId(ID_EDEFAULT);
				return;
			case MapPackage.FAILURE_POINT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case MapPackage.FAILURE_POINT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case MapPackage.FAILURE_POINT__X:
				setX(X_EDEFAULT);
				return;
			case MapPackage.FAILURE_POINT__Y:
				setY(Y_EDEFAULT);
				return;
			case MapPackage.FAILURE_POINT__PATH_GRAPH:
				setPathGraph((PathGraph)null);
				return;
			case MapPackage.FAILURE_POINT__SUCC:
				getSucc().clear();
				return;
			case MapPackage.FAILURE_POINT__PRED:
				getPred().clear();
				return;
			case MapPackage.FAILURE_POINT__COMP_REF:
				setCompRef((ComponentRef)null);
				return;
			case MapPackage.FAILURE_POINT__LABEL:
				setLabel((NodeLabel)null);
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
			case MapPackage.FAILURE_POINT__URN_LINKS:
				return urnLinks != null && !urnLinks.isEmpty();
			case MapPackage.FAILURE_POINT__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case MapPackage.FAILURE_POINT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case MapPackage.FAILURE_POINT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case MapPackage.FAILURE_POINT__X:
				return x != X_EDEFAULT;
			case MapPackage.FAILURE_POINT__Y:
				return y != Y_EDEFAULT;
			case MapPackage.FAILURE_POINT__PATH_GRAPH:
				return getPathGraph() != null;
			case MapPackage.FAILURE_POINT__SUCC:
				return succ != null && !succ.isEmpty();
			case MapPackage.FAILURE_POINT__PRED:
				return pred != null && !pred.isEmpty();
			case MapPackage.FAILURE_POINT__COMP_REF:
				return compRef != null;
			case MapPackage.FAILURE_POINT__LABEL:
				return label != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //FailurePointImpl
