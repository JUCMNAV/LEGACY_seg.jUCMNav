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
import ucm.map.EmptyPoint;
import ucm.map.MapPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Empty Point</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class EmptyPointImpl extends PathNodeImpl implements EmptyPoint {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EmptyPointImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return MapPackage.eINSTANCE.getEmptyPoint();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case MapPackage.EMPTY_POINT__URN_LINKS:
					return ((InternalEList)getUrnLinks()).basicAdd(otherEnd, msgs);
				case MapPackage.EMPTY_POINT__SUCC:
					return ((InternalEList)getSucc()).basicAdd(otherEnd, msgs);
				case MapPackage.EMPTY_POINT__PRED:
					return ((InternalEList)getPred()).basicAdd(otherEnd, msgs);
				case MapPackage.EMPTY_POINT__COMP_REF:
					if (compRef != null)
						msgs = ((InternalEObject)compRef).eInverseRemove(this, MapPackage.COMPONENT_REF__PATH_NODES, ComponentRef.class, msgs);
					return basicSetCompRef((ComponentRef)otherEnd, msgs);
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
				case MapPackage.EMPTY_POINT__URN_LINKS:
					return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
				case MapPackage.EMPTY_POINT__SUCC:
					return ((InternalEList)getSucc()).basicRemove(otherEnd, msgs);
				case MapPackage.EMPTY_POINT__PRED:
					return ((InternalEList)getPred()).basicRemove(otherEnd, msgs);
				case MapPackage.EMPTY_POINT__COMP_REF:
					return basicSetCompRef(null, msgs);
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
			case MapPackage.EMPTY_POINT__URN_LINKS:
				return getUrnLinks();
			case MapPackage.EMPTY_POINT__ID:
				return getId();
			case MapPackage.EMPTY_POINT__NAME:
				return getName();
			case MapPackage.EMPTY_POINT__DESCRIPTION:
				return getDescription();
			case MapPackage.EMPTY_POINT__X:
				return new Integer(getX());
			case MapPackage.EMPTY_POINT__Y:
				return new Integer(getY());
			case MapPackage.EMPTY_POINT__LABEL_X:
				return new Integer(getLabelX());
			case MapPackage.EMPTY_POINT__LABEL_Y:
				return new Integer(getLabelY());
			case MapPackage.EMPTY_POINT__SUCC:
				return getSucc();
			case MapPackage.EMPTY_POINT__PRED:
				return getPred();
			case MapPackage.EMPTY_POINT__COMP_REF:
				if (resolve) return getCompRef();
				return basicGetCompRef();
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
			case MapPackage.EMPTY_POINT__URN_LINKS:
				getUrnLinks().clear();
				getUrnLinks().addAll((Collection)newValue);
				return;
			case MapPackage.EMPTY_POINT__ID:
				setId((String)newValue);
				return;
			case MapPackage.EMPTY_POINT__NAME:
				setName((String)newValue);
				return;
			case MapPackage.EMPTY_POINT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case MapPackage.EMPTY_POINT__X:
				setX(((Integer)newValue).intValue());
				return;
			case MapPackage.EMPTY_POINT__Y:
				setY(((Integer)newValue).intValue());
				return;
			case MapPackage.EMPTY_POINT__LABEL_X:
				setLabelX(((Integer)newValue).intValue());
				return;
			case MapPackage.EMPTY_POINT__LABEL_Y:
				setLabelY(((Integer)newValue).intValue());
				return;
			case MapPackage.EMPTY_POINT__SUCC:
				getSucc().clear();
				getSucc().addAll((Collection)newValue);
				return;
			case MapPackage.EMPTY_POINT__PRED:
				getPred().clear();
				getPred().addAll((Collection)newValue);
				return;
			case MapPackage.EMPTY_POINT__COMP_REF:
				setCompRef((ComponentRef)newValue);
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
			case MapPackage.EMPTY_POINT__URN_LINKS:
				getUrnLinks().clear();
				return;
			case MapPackage.EMPTY_POINT__ID:
				setId(ID_EDEFAULT);
				return;
			case MapPackage.EMPTY_POINT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case MapPackage.EMPTY_POINT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case MapPackage.EMPTY_POINT__X:
				setX(X_EDEFAULT);
				return;
			case MapPackage.EMPTY_POINT__Y:
				setY(Y_EDEFAULT);
				return;
			case MapPackage.EMPTY_POINT__LABEL_X:
				setLabelX(LABEL_X_EDEFAULT);
				return;
			case MapPackage.EMPTY_POINT__LABEL_Y:
				setLabelY(LABEL_Y_EDEFAULT);
				return;
			case MapPackage.EMPTY_POINT__SUCC:
				getSucc().clear();
				return;
			case MapPackage.EMPTY_POINT__PRED:
				getPred().clear();
				return;
			case MapPackage.EMPTY_POINT__COMP_REF:
				setCompRef((ComponentRef)null);
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
			case MapPackage.EMPTY_POINT__URN_LINKS:
				return urnLinks != null && !urnLinks.isEmpty();
			case MapPackage.EMPTY_POINT__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case MapPackage.EMPTY_POINT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case MapPackage.EMPTY_POINT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case MapPackage.EMPTY_POINT__X:
				return x != X_EDEFAULT;
			case MapPackage.EMPTY_POINT__Y:
				return y != Y_EDEFAULT;
			case MapPackage.EMPTY_POINT__LABEL_X:
				return labelX != LABEL_X_EDEFAULT;
			case MapPackage.EMPTY_POINT__LABEL_Y:
				return labelY != LABEL_Y_EDEFAULT;
			case MapPackage.EMPTY_POINT__SUCC:
				return succ != null && !succ.isEmpty();
			case MapPackage.EMPTY_POINT__PRED:
				return pred != null && !pred.isEmpty();
			case MapPackage.EMPTY_POINT__COMP_REF:
				return compRef != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //EmptyPointImpl
