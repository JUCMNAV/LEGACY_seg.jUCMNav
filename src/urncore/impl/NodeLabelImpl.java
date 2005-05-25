/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import ucm.map.MapPackage;
import ucm.map.PathNode;

import urncore.NodeLabel;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node Label</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link urncore.impl.NodeLabelImpl#getPathNode <em>Path Node</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NodeLabelImpl extends LabelImpl implements NodeLabel {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NodeLabelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return UrncorePackage.eINSTANCE.getNodeLabel();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PathNode getPathNode() {
		if (eContainerFeatureID != UrncorePackage.NODE_LABEL__PATH_NODE) return null;
		return (PathNode)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPathNode(PathNode newPathNode) {
		if (newPathNode != eContainer || (eContainerFeatureID != UrncorePackage.NODE_LABEL__PATH_NODE && newPathNode != null)) {
			if (EcoreUtil.isAncestor(this, newPathNode))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPathNode != null)
				msgs = ((InternalEObject)newPathNode).eInverseAdd(this, MapPackage.PATH_NODE__LABEL, PathNode.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newPathNode, UrncorePackage.NODE_LABEL__PATH_NODE, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.NODE_LABEL__PATH_NODE, newPathNode, newPathNode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case UrncorePackage.NODE_LABEL__PATH_NODE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, UrncorePackage.NODE_LABEL__PATH_NODE, msgs);
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
				case UrncorePackage.NODE_LABEL__PATH_NODE:
					return eBasicSetContainer(null, UrncorePackage.NODE_LABEL__PATH_NODE, msgs);
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
				case UrncorePackage.NODE_LABEL__PATH_NODE:
					return ((InternalEObject)eContainer).eInverseRemove(this, MapPackage.PATH_NODE__LABEL, PathNode.class, msgs);
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
			case UrncorePackage.NODE_LABEL__DELTA_X:
				return new Integer(getDeltaX());
			case UrncorePackage.NODE_LABEL__DELTA_Y:
				return new Integer(getDeltaY());
			case UrncorePackage.NODE_LABEL__PATH_NODE:
				return getPathNode();
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
			case UrncorePackage.NODE_LABEL__DELTA_X:
				setDeltaX(((Integer)newValue).intValue());
				return;
			case UrncorePackage.NODE_LABEL__DELTA_Y:
				setDeltaY(((Integer)newValue).intValue());
				return;
			case UrncorePackage.NODE_LABEL__PATH_NODE:
				setPathNode((PathNode)newValue);
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
			case UrncorePackage.NODE_LABEL__DELTA_X:
				setDeltaX(DELTA_X_EDEFAULT);
				return;
			case UrncorePackage.NODE_LABEL__DELTA_Y:
				setDeltaY(DELTA_Y_EDEFAULT);
				return;
			case UrncorePackage.NODE_LABEL__PATH_NODE:
				setPathNode((PathNode)null);
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
			case UrncorePackage.NODE_LABEL__DELTA_X:
				return deltaX != DELTA_X_EDEFAULT;
			case UrncorePackage.NODE_LABEL__DELTA_Y:
				return deltaY != DELTA_Y_EDEFAULT;
			case UrncorePackage.NODE_LABEL__PATH_NODE:
				return getPathNode() != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //NodeLabelImpl
