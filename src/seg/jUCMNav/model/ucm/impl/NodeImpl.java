/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package seg.jUCMNav.model.ucm.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import seg.jUCMNav.model.ucm.Component;
import seg.jUCMNav.model.ucm.Node;
import seg.jUCMNav.model.ucm.Path;
import seg.jUCMNav.model.ucm.UcmPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link seg.jUCMNav.model.ucm.impl.NodeImpl#getPath <em>Path</em>}</li>
 *   <li>{@link seg.jUCMNav.model.ucm.impl.NodeImpl#getNext <em>Next</em>}</li>
 *   <li>{@link seg.jUCMNav.model.ucm.impl.NodeImpl#getPrevious <em>Previous</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NodeImpl extends XYElementImpl implements Node {
	/**
	 * The cached value of the '{@link #getNext() <em>Next</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNext()
	 * @generated
	 * @ordered
	 */
	protected Node next = null;

	/**
	 * The cached value of the '{@link #getPrevious() <em>Previous</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrevious()
	 * @generated
	 * @ordered
	 */
	protected Node previous = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return UcmPackage.eINSTANCE.getNode();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Path getPath() {
		if (eContainerFeatureID != UcmPackage.NODE__PATH) return null;
		return (Path)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPath(Path newPath) {
		if (newPath != eContainer || (eContainerFeatureID != UcmPackage.NODE__PATH && newPath != null)) {
			if (EcoreUtil.isAncestor(this, newPath))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPath != null)
				msgs = ((InternalEObject)newPath).eInverseAdd(this, UcmPackage.PATH__NODES, Path.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newPath, UcmPackage.NODE__PATH, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UcmPackage.NODE__PATH, newPath, newPath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node getNext() {
		if (next != null && next.eIsProxy()) {
			Node oldNext = next;
			next = (Node)eResolveProxy((InternalEObject)next);
			if (next != oldNext) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UcmPackage.NODE__NEXT, oldNext, next));
			}
		}
		return next;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node basicGetNext() {
		return next;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNext(Node newNext, NotificationChain msgs) {
		Node oldNext = next;
		next = newNext;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UcmPackage.NODE__NEXT, oldNext, newNext);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNext(Node newNext) {
		if (newNext != next) {
			NotificationChain msgs = null;
			if (next != null)
				msgs = ((InternalEObject)next).eInverseRemove(this, UcmPackage.NODE__PREVIOUS, Node.class, msgs);
			if (newNext != null)
				msgs = ((InternalEObject)newNext).eInverseAdd(this, UcmPackage.NODE__PREVIOUS, Node.class, msgs);
			msgs = basicSetNext(newNext, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UcmPackage.NODE__NEXT, newNext, newNext));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node getPrevious() {
		if (previous != null && previous.eIsProxy()) {
			Node oldPrevious = previous;
			previous = (Node)eResolveProxy((InternalEObject)previous);
			if (previous != oldPrevious) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UcmPackage.NODE__PREVIOUS, oldPrevious, previous));
			}
		}
		return previous;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node basicGetPrevious() {
		return previous;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPrevious(Node newPrevious, NotificationChain msgs) {
		Node oldPrevious = previous;
		previous = newPrevious;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UcmPackage.NODE__PREVIOUS, oldPrevious, newPrevious);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrevious(Node newPrevious) {
		if (newPrevious != previous) {
			NotificationChain msgs = null;
			if (previous != null)
				msgs = ((InternalEObject)previous).eInverseRemove(this, UcmPackage.NODE__NEXT, Node.class, msgs);
			if (newPrevious != null)
				msgs = ((InternalEObject)newPrevious).eInverseAdd(this, UcmPackage.NODE__NEXT, Node.class, msgs);
			msgs = basicSetPrevious(newPrevious, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UcmPackage.NODE__PREVIOUS, newPrevious, newPrevious));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case UcmPackage.NODE__COMPONENT:
					if (component != null)
						msgs = ((InternalEObject)component).eInverseRemove(this, UcmPackage.COMPONENT__ELEMENTS, Component.class, msgs);
					return basicSetComponent((Component)otherEnd, msgs);
				case UcmPackage.NODE__PATH:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, UcmPackage.NODE__PATH, msgs);
				case UcmPackage.NODE__NEXT:
					if (next != null)
						msgs = ((InternalEObject)next).eInverseRemove(this, UcmPackage.NODE__PREVIOUS, Node.class, msgs);
					return basicSetNext((Node)otherEnd, msgs);
				case UcmPackage.NODE__PREVIOUS:
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
				case UcmPackage.NODE__COMPONENT:
					return basicSetComponent(null, msgs);
				case UcmPackage.NODE__PATH:
					return eBasicSetContainer(null, UcmPackage.NODE__PATH, msgs);
				case UcmPackage.NODE__NEXT:
					return basicSetNext(null, msgs);
				case UcmPackage.NODE__PREVIOUS:
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
				case UcmPackage.NODE__PATH:
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
			case UcmPackage.NODE__X:
				return new Integer(getX());
			case UcmPackage.NODE__Y:
				return new Integer(getY());
			case UcmPackage.NODE__COMPONENT:
				if (resolve) return getComponent();
				return basicGetComponent();
			case UcmPackage.NODE__PATH:
				return getPath();
			case UcmPackage.NODE__NEXT:
				if (resolve) return getNext();
				return basicGetNext();
			case UcmPackage.NODE__PREVIOUS:
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
			case UcmPackage.NODE__X:
				setX(((Integer)newValue).intValue());
				return;
			case UcmPackage.NODE__Y:
				setY(((Integer)newValue).intValue());
				return;
			case UcmPackage.NODE__COMPONENT:
				setComponent((Component)newValue);
				return;
			case UcmPackage.NODE__PATH:
				setPath((Path)newValue);
				return;
			case UcmPackage.NODE__NEXT:
				setNext((Node)newValue);
				return;
			case UcmPackage.NODE__PREVIOUS:
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
			case UcmPackage.NODE__X:
				setX(X_EDEFAULT);
				return;
			case UcmPackage.NODE__Y:
				setY(Y_EDEFAULT);
				return;
			case UcmPackage.NODE__COMPONENT:
				setComponent((Component)null);
				return;
			case UcmPackage.NODE__PATH:
				setPath((Path)null);
				return;
			case UcmPackage.NODE__NEXT:
				setNext((Node)null);
				return;
			case UcmPackage.NODE__PREVIOUS:
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
			case UcmPackage.NODE__X:
				return x != X_EDEFAULT;
			case UcmPackage.NODE__Y:
				return y != Y_EDEFAULT;
			case UcmPackage.NODE__COMPONENT:
				return component != null;
			case UcmPackage.NODE__PATH:
				return getPath() != null;
			case UcmPackage.NODE__NEXT:
				return next != null;
			case UcmPackage.NODE__PREVIOUS:
				return previous != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //NodeImpl
