/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package seg.jUCMNav.model.ucm.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import seg.jUCMNav.model.ucm.Component;
import seg.jUCMNav.model.ucm.UcmDiagram;
import seg.jUCMNav.model.ucm.UcmPackage;
import seg.jUCMNav.model.ucm.XYElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link seg.jUCMNav.model.ucm.impl.ComponentImpl#getElements <em>Elements</em>}</li>
 *   <li>{@link seg.jUCMNav.model.ucm.impl.ComponentImpl#getDiagram <em>Diagram</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComponentImpl extends SizedElementImpl implements Component {
	/**
	 * The cached value of the '{@link #getElements() <em>Elements</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElements()
	 * @generated
	 * @ordered
	 */
	protected EList elements = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return UcmPackage.eINSTANCE.getComponent();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getElements() {
		if (elements == null) {
			elements = new EObjectWithInverseResolvingEList(XYElement.class, this, UcmPackage.COMPONENT__ELEMENTS, UcmPackage.XY_ELEMENT__COMPONENT);
		}
		return elements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UcmDiagram getDiagram() {
		if (eContainerFeatureID != UcmPackage.COMPONENT__DIAGRAM) return null;
		return (UcmDiagram)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDiagram(UcmDiagram newDiagram) {
		if (newDiagram != eContainer || (eContainerFeatureID != UcmPackage.COMPONENT__DIAGRAM && newDiagram != null)) {
			if (EcoreUtil.isAncestor(this, newDiagram))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newDiagram != null)
				msgs = ((InternalEObject)newDiagram).eInverseAdd(this, UcmPackage.UCM_DIAGRAM__COMPONENTS, UcmDiagram.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newDiagram, UcmPackage.COMPONENT__DIAGRAM, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UcmPackage.COMPONENT__DIAGRAM, newDiagram, newDiagram));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case UcmPackage.COMPONENT__COMPONENT:
					if (component != null)
						msgs = ((InternalEObject)component).eInverseRemove(this, UcmPackage.COMPONENT__ELEMENTS, Component.class, msgs);
					return basicSetComponent((Component)otherEnd, msgs);
				case UcmPackage.COMPONENT__ELEMENTS:
					return ((InternalEList)getElements()).basicAdd(otherEnd, msgs);
				case UcmPackage.COMPONENT__DIAGRAM:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, UcmPackage.COMPONENT__DIAGRAM, msgs);
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
				case UcmPackage.COMPONENT__COMPONENT:
					return basicSetComponent(null, msgs);
				case UcmPackage.COMPONENT__ELEMENTS:
					return ((InternalEList)getElements()).basicRemove(otherEnd, msgs);
				case UcmPackage.COMPONENT__DIAGRAM:
					return eBasicSetContainer(null, UcmPackage.COMPONENT__DIAGRAM, msgs);
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
				case UcmPackage.COMPONENT__DIAGRAM:
					return ((InternalEObject)eContainer).eInverseRemove(this, UcmPackage.UCM_DIAGRAM__COMPONENTS, UcmDiagram.class, msgs);
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
			case UcmPackage.COMPONENT__X:
				return new Integer(getX());
			case UcmPackage.COMPONENT__Y:
				return new Integer(getY());
			case UcmPackage.COMPONENT__COMPONENT:
				if (resolve) return getComponent();
				return basicGetComponent();
			case UcmPackage.COMPONENT__WIDTH:
				return new Integer(getWidth());
			case UcmPackage.COMPONENT__HEIGHT:
				return new Integer(getHeight());
			case UcmPackage.COMPONENT__ELEMENTS:
				return getElements();
			case UcmPackage.COMPONENT__DIAGRAM:
				return getDiagram();
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
			case UcmPackage.COMPONENT__X:
				setX(((Integer)newValue).intValue());
				return;
			case UcmPackage.COMPONENT__Y:
				setY(((Integer)newValue).intValue());
				return;
			case UcmPackage.COMPONENT__COMPONENT:
				setComponent((Component)newValue);
				return;
			case UcmPackage.COMPONENT__WIDTH:
				setWidth(((Integer)newValue).intValue());
				return;
			case UcmPackage.COMPONENT__HEIGHT:
				setHeight(((Integer)newValue).intValue());
				return;
			case UcmPackage.COMPONENT__ELEMENTS:
				getElements().clear();
				getElements().addAll((Collection)newValue);
				return;
			case UcmPackage.COMPONENT__DIAGRAM:
				setDiagram((UcmDiagram)newValue);
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
			case UcmPackage.COMPONENT__X:
				setX(X_EDEFAULT);
				return;
			case UcmPackage.COMPONENT__Y:
				setY(Y_EDEFAULT);
				return;
			case UcmPackage.COMPONENT__COMPONENT:
				setComponent((Component)null);
				return;
			case UcmPackage.COMPONENT__WIDTH:
				setWidth(WIDTH_EDEFAULT);
				return;
			case UcmPackage.COMPONENT__HEIGHT:
				setHeight(HEIGHT_EDEFAULT);
				return;
			case UcmPackage.COMPONENT__ELEMENTS:
				getElements().clear();
				return;
			case UcmPackage.COMPONENT__DIAGRAM:
				setDiagram((UcmDiagram)null);
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
			case UcmPackage.COMPONENT__X:
				return x != X_EDEFAULT;
			case UcmPackage.COMPONENT__Y:
				return y != Y_EDEFAULT;
			case UcmPackage.COMPONENT__COMPONENT:
				return component != null;
			case UcmPackage.COMPONENT__WIDTH:
				return width != WIDTH_EDEFAULT;
			case UcmPackage.COMPONENT__HEIGHT:
				return height != HEIGHT_EDEFAULT;
			case UcmPackage.COMPONENT__ELEMENTS:
				return elements != null && !elements.isEmpty();
			case UcmPackage.COMPONENT__DIAGRAM:
				return getDiagram() != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //ComponentImpl
