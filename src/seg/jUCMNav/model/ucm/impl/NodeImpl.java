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
import seg.jUCMNav.model.ucm.Link;
import seg.jUCMNav.model.ucm.Node;
import seg.jUCMNav.model.ucm.Path;
import seg.jUCMNav.model.ucm.UcmDiagram;
import seg.jUCMNav.model.ucm.UcmPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link seg.jUCMNav.model.ucm.impl.NodeImpl#getDiagram <em>Diagram</em>}</li>
 *   <li>{@link seg.jUCMNav.model.ucm.impl.NodeImpl#getUpLink <em>Up Link</em>}</li>
 *   <li>{@link seg.jUCMNav.model.ucm.impl.NodeImpl#getDownLink <em>Down Link</em>}</li>
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
	 * The cached value of the '{@link #getUpLink() <em>Up Link</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUpLink()
	 * @generated
	 * @ordered
	 */
	protected Link upLink = null;

	/**
	 * The cached value of the '{@link #getDownLink() <em>Down Link</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDownLink()
	 * @generated
	 * @ordered
	 */
	protected Link downLink = null;

	/**
	 * The cached value of the '{@link #getPath() <em>Path</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected Path path = null;

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
	public UcmDiagram getDiagram() {
		if (eContainerFeatureID != UcmPackage.NODE__DIAGRAM) return null;
		return (UcmDiagram)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDiagram(UcmDiagram newDiagram) {
		if (newDiagram != eContainer || (eContainerFeatureID != UcmPackage.NODE__DIAGRAM && newDiagram != null)) {
			if (EcoreUtil.isAncestor(this, newDiagram))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newDiagram != null)
				msgs = ((InternalEObject)newDiagram).eInverseAdd(this, UcmPackage.UCM_DIAGRAM__NODES, UcmDiagram.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newDiagram, UcmPackage.NODE__DIAGRAM, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UcmPackage.NODE__DIAGRAM, newDiagram, newDiagram));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Link getUpLink() {
		if (upLink != null && upLink.eIsProxy()) {
			Link oldUpLink = upLink;
			upLink = (Link)eResolveProxy((InternalEObject)upLink);
			if (upLink != oldUpLink) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UcmPackage.NODE__UP_LINK, oldUpLink, upLink));
			}
		}
		return upLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Link basicGetUpLink() {
		return upLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUpLink(Link newUpLink, NotificationChain msgs) {
		Link oldUpLink = upLink;
		upLink = newUpLink;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UcmPackage.NODE__UP_LINK, oldUpLink, newUpLink);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUpLink(Link newUpLink) {
		if (newUpLink != upLink) {
			NotificationChain msgs = null;
			if (upLink != null)
				msgs = ((InternalEObject)upLink).eInverseRemove(this, UcmPackage.LINK__TARGET, Link.class, msgs);
			if (newUpLink != null)
				msgs = ((InternalEObject)newUpLink).eInverseAdd(this, UcmPackage.LINK__TARGET, Link.class, msgs);
			msgs = basicSetUpLink(newUpLink, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UcmPackage.NODE__UP_LINK, newUpLink, newUpLink));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Link getDownLink() {
		if (downLink != null && downLink.eIsProxy()) {
			Link oldDownLink = downLink;
			downLink = (Link)eResolveProxy((InternalEObject)downLink);
			if (downLink != oldDownLink) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UcmPackage.NODE__DOWN_LINK, oldDownLink, downLink));
			}
		}
		return downLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Link basicGetDownLink() {
		return downLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDownLink(Link newDownLink, NotificationChain msgs) {
		Link oldDownLink = downLink;
		downLink = newDownLink;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UcmPackage.NODE__DOWN_LINK, oldDownLink, newDownLink);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDownLink(Link newDownLink) {
		if (newDownLink != downLink) {
			NotificationChain msgs = null;
			if (downLink != null)
				msgs = ((InternalEObject)downLink).eInverseRemove(this, UcmPackage.LINK__SOURCE, Link.class, msgs);
			if (newDownLink != null)
				msgs = ((InternalEObject)newDownLink).eInverseAdd(this, UcmPackage.LINK__SOURCE, Link.class, msgs);
			msgs = basicSetDownLink(newDownLink, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UcmPackage.NODE__DOWN_LINK, newDownLink, newDownLink));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Path getPath() {
		if (path != null && path.eIsProxy()) {
			Path oldPath = path;
			path = (Path)eResolveProxy((InternalEObject)path);
			if (path != oldPath) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UcmPackage.NODE__PATH, oldPath, path));
			}
		}
		return path;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Path basicGetPath() {
		return path;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPath(Path newPath, NotificationChain msgs) {
		Path oldPath = path;
		path = newPath;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UcmPackage.NODE__PATH, oldPath, newPath);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPath(Path newPath) {
		if (newPath != path) {
			NotificationChain msgs = null;
			if (path != null)
				msgs = ((InternalEObject)path).eInverseRemove(this, UcmPackage.PATH__NODES, Path.class, msgs);
			if (newPath != null)
				msgs = ((InternalEObject)newPath).eInverseAdd(this, UcmPackage.PATH__NODES, Path.class, msgs);
			msgs = basicSetPath(newPath, msgs);
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
				case UcmPackage.NODE__DIAGRAM:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, UcmPackage.NODE__DIAGRAM, msgs);
				case UcmPackage.NODE__UP_LINK:
					if (upLink != null)
						msgs = ((InternalEObject)upLink).eInverseRemove(this, UcmPackage.LINK__TARGET, Link.class, msgs);
					return basicSetUpLink((Link)otherEnd, msgs);
				case UcmPackage.NODE__DOWN_LINK:
					if (downLink != null)
						msgs = ((InternalEObject)downLink).eInverseRemove(this, UcmPackage.LINK__SOURCE, Link.class, msgs);
					return basicSetDownLink((Link)otherEnd, msgs);
				case UcmPackage.NODE__PATH:
					if (path != null)
						msgs = ((InternalEObject)path).eInverseRemove(this, UcmPackage.PATH__NODES, Path.class, msgs);
					return basicSetPath((Path)otherEnd, msgs);
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
				case UcmPackage.NODE__DIAGRAM:
					return eBasicSetContainer(null, UcmPackage.NODE__DIAGRAM, msgs);
				case UcmPackage.NODE__UP_LINK:
					return basicSetUpLink(null, msgs);
				case UcmPackage.NODE__DOWN_LINK:
					return basicSetDownLink(null, msgs);
				case UcmPackage.NODE__PATH:
					return basicSetPath(null, msgs);
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
				case UcmPackage.NODE__DIAGRAM:
					return ((InternalEObject)eContainer).eInverseRemove(this, UcmPackage.UCM_DIAGRAM__NODES, UcmDiagram.class, msgs);
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
			case UcmPackage.NODE__DIAGRAM:
				return getDiagram();
			case UcmPackage.NODE__UP_LINK:
				if (resolve) return getUpLink();
				return basicGetUpLink();
			case UcmPackage.NODE__DOWN_LINK:
				if (resolve) return getDownLink();
				return basicGetDownLink();
			case UcmPackage.NODE__PATH:
				if (resolve) return getPath();
				return basicGetPath();
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
			case UcmPackage.NODE__DIAGRAM:
				setDiagram((UcmDiagram)newValue);
				return;
			case UcmPackage.NODE__UP_LINK:
				setUpLink((Link)newValue);
				return;
			case UcmPackage.NODE__DOWN_LINK:
				setDownLink((Link)newValue);
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
			case UcmPackage.NODE__DIAGRAM:
				setDiagram((UcmDiagram)null);
				return;
			case UcmPackage.NODE__UP_LINK:
				setUpLink((Link)null);
				return;
			case UcmPackage.NODE__DOWN_LINK:
				setDownLink((Link)null);
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
			case UcmPackage.NODE__DIAGRAM:
				return getDiagram() != null;
			case UcmPackage.NODE__UP_LINK:
				return upLink != null;
			case UcmPackage.NODE__DOWN_LINK:
				return downLink != null;
			case UcmPackage.NODE__PATH:
				return path != null;
			case UcmPackage.NODE__NEXT:
				return next != null;
			case UcmPackage.NODE__PREVIOUS:
				return previous != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //NodeImpl
