/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package seg.jUCMNav.model.ucm.impl;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import seg.jUCMNav.model.ucm.Component;
import seg.jUCMNav.model.ucm.Node;
import seg.jUCMNav.model.ucm.Path;
import seg.jUCMNav.model.ucm.Responsibility;
import seg.jUCMNav.model.ucm.UcmPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Responsibility</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class ResponsibilityImpl extends NodeImpl implements Responsibility {
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
		return UcmPackage.eINSTANCE.getResponsibility();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case UcmPackage.RESPONSIBILITY__COMPONENT:
					if (component != null)
						msgs = ((InternalEObject)component).eInverseRemove(this, UcmPackage.COMPONENT__ELEMENTS, Component.class, msgs);
					return basicSetComponent((Component)otherEnd, msgs);
				case UcmPackage.RESPONSIBILITY__PATH:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, UcmPackage.RESPONSIBILITY__PATH, msgs);
				case UcmPackage.RESPONSIBILITY__NEXT:
					if (next != null)
						msgs = ((InternalEObject)next).eInverseRemove(this, UcmPackage.NODE__PREVIOUS, Node.class, msgs);
					return basicSetNext((Node)otherEnd, msgs);
				case UcmPackage.RESPONSIBILITY__PREVIOUS:
					if (previous != null)
						msgs = ((InternalEObject)previous).eInverseRemove(this, UcmPackage.NODE__NEXT, Node.class, msgs);
					return basicSetPrevious((Node)otherEnd, msgs);
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
				case UcmPackage.RESPONSIBILITY__COMPONENT:
					return basicSetComponent(null, msgs);
				case UcmPackage.RESPONSIBILITY__PATH:
					return eBasicSetContainer(null, UcmPackage.RESPONSIBILITY__PATH, msgs);
				case UcmPackage.RESPONSIBILITY__NEXT:
					return basicSetNext(null, msgs);
				case UcmPackage.RESPONSIBILITY__PREVIOUS:
					return basicSetPrevious(null, msgs);
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
				case UcmPackage.RESPONSIBILITY__PATH:
					return ((InternalEObject)eContainer).eInverseRemove(this, UcmPackage.PATH__NODES, Path.class, msgs);
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
			case UcmPackage.RESPONSIBILITY__X:
				return new Integer(getX());
			case UcmPackage.RESPONSIBILITY__Y:
				return new Integer(getY());
			case UcmPackage.RESPONSIBILITY__COMPONENT:
				if (resolve) return getComponent();
				return basicGetComponent();
			case UcmPackage.RESPONSIBILITY__PATH:
				return getPath();
			case UcmPackage.RESPONSIBILITY__NEXT:
				if (resolve) return getNext();
				return basicGetNext();
			case UcmPackage.RESPONSIBILITY__PREVIOUS:
				if (resolve) return getPrevious();
				return basicGetPrevious();
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
			case UcmPackage.RESPONSIBILITY__X:
				setX(((Integer)newValue).intValue());
				return;
			case UcmPackage.RESPONSIBILITY__Y:
				setY(((Integer)newValue).intValue());
				return;
			case UcmPackage.RESPONSIBILITY__COMPONENT:
				setComponent((Component)newValue);
				return;
			case UcmPackage.RESPONSIBILITY__PATH:
				setPath((Path)newValue);
				return;
			case UcmPackage.RESPONSIBILITY__NEXT:
				setNext((Node)newValue);
				return;
			case UcmPackage.RESPONSIBILITY__PREVIOUS:
				setPrevious((Node)newValue);
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
			case UcmPackage.RESPONSIBILITY__X:
				setX(X_EDEFAULT);
				return;
			case UcmPackage.RESPONSIBILITY__Y:
				setY(Y_EDEFAULT);
				return;
			case UcmPackage.RESPONSIBILITY__COMPONENT:
				setComponent((Component)null);
				return;
			case UcmPackage.RESPONSIBILITY__PATH:
				setPath((Path)null);
				return;
			case UcmPackage.RESPONSIBILITY__NEXT:
				setNext((Node)null);
				return;
			case UcmPackage.RESPONSIBILITY__PREVIOUS:
				setPrevious((Node)null);
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
			case UcmPackage.RESPONSIBILITY__X:
				return x != X_EDEFAULT;
			case UcmPackage.RESPONSIBILITY__Y:
				return y != Y_EDEFAULT;
			case UcmPackage.RESPONSIBILITY__COMPONENT:
				return component != null;
			case UcmPackage.RESPONSIBILITY__PATH:
				return getPath() != null;
			case UcmPackage.RESPONSIBILITY__NEXT:
				return next != null;
			case UcmPackage.RESPONSIBILITY__PREVIOUS:
				return previous != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //ResponsibilityImpl
