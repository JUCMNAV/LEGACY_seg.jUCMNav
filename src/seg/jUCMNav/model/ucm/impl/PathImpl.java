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
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import seg.jUCMNav.model.ucm.EndPoint;
import seg.jUCMNav.model.ucm.Fork;
import seg.jUCMNav.model.ucm.Node;
import seg.jUCMNav.model.ucm.Path;
import seg.jUCMNav.model.ucm.StartPoint;
import seg.jUCMNav.model.ucm.UcmDiagram;
import seg.jUCMNav.model.ucm.UcmPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Path</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link seg.jUCMNav.model.ucm.impl.PathImpl#getNodes <em>Nodes</em>}</li>
 *   <li>{@link seg.jUCMNav.model.ucm.impl.PathImpl#getDiagram <em>Diagram</em>}</li>
 *   <li>{@link seg.jUCMNav.model.ucm.impl.PathImpl#getInFork <em>In Fork</em>}</li>
 *   <li>{@link seg.jUCMNav.model.ucm.impl.PathImpl#getOutFork <em>Out Fork</em>}</li>
 *   <li>{@link seg.jUCMNav.model.ucm.impl.PathImpl#getEndpoint <em>Endpoint</em>}</li>
 *   <li>{@link seg.jUCMNav.model.ucm.impl.PathImpl#getStartpoint <em>Startpoint</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PathImpl extends EObjectImpl implements Path {
	/**
	 * The cached value of the '{@link #getNodes() <em>Nodes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodes()
	 * @generated
	 * @ordered
	 */
	protected EList nodes = null;

	/**
	 * The cached value of the '{@link #getEndpoint() <em>Endpoint</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndpoint()
	 * @generated
	 * @ordered
	 */
	protected EndPoint endpoint = null;

	/**
	 * The cached value of the '{@link #getStartpoint() <em>Startpoint</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartpoint()
	 * @generated
	 * @ordered
	 */
	protected StartPoint startpoint = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PathImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return UcmPackage.eINSTANCE.getPath();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getNodes() {
		if (nodes == null) {
			nodes = new EObjectContainmentWithInverseEList(Node.class, this, UcmPackage.PATH__NODES, UcmPackage.NODE__PATH);
		}
		return nodes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UcmDiagram getDiagram() {
		if (eContainerFeatureID != UcmPackage.PATH__DIAGRAM) return null;
		return (UcmDiagram)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDiagram(UcmDiagram newDiagram) {
		if (newDiagram != eContainer || (eContainerFeatureID != UcmPackage.PATH__DIAGRAM && newDiagram != null)) {
			if (EcoreUtil.isAncestor(this, newDiagram))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newDiagram != null)
				msgs = ((InternalEObject)newDiagram).eInverseAdd(this, UcmPackage.UCM_DIAGRAM__PATHS, UcmDiagram.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newDiagram, UcmPackage.PATH__DIAGRAM, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UcmPackage.PATH__DIAGRAM, newDiagram, newDiagram));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Fork getInFork() {
		if (eContainerFeatureID != UcmPackage.PATH__IN_FORK) return null;
		return (Fork)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInFork(Fork newInFork) {
		if (newInFork != eContainer || (eContainerFeatureID != UcmPackage.PATH__IN_FORK && newInFork != null)) {
			if (EcoreUtil.isAncestor(this, newInFork))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newInFork != null)
				msgs = ((InternalEObject)newInFork).eInverseAdd(this, UcmPackage.FORK__IN_PATHS, Fork.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newInFork, UcmPackage.PATH__IN_FORK, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UcmPackage.PATH__IN_FORK, newInFork, newInFork));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Fork getOutFork() {
		if (eContainerFeatureID != UcmPackage.PATH__OUT_FORK) return null;
		return (Fork)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutFork(Fork newOutFork) {
		if (newOutFork != eContainer || (eContainerFeatureID != UcmPackage.PATH__OUT_FORK && newOutFork != null)) {
			if (EcoreUtil.isAncestor(this, newOutFork))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOutFork != null)
				msgs = ((InternalEObject)newOutFork).eInverseAdd(this, UcmPackage.FORK__OUT_PATH, Fork.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newOutFork, UcmPackage.PATH__OUT_FORK, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UcmPackage.PATH__OUT_FORK, newOutFork, newOutFork));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EndPoint getEndpoint() {
		return endpoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEndpoint(EndPoint newEndpoint, NotificationChain msgs) {
		EndPoint oldEndpoint = endpoint;
		endpoint = newEndpoint;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UcmPackage.PATH__ENDPOINT, oldEndpoint, newEndpoint);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEndpoint(EndPoint newEndpoint) {
		if (newEndpoint != endpoint) {
			NotificationChain msgs = null;
			if (endpoint != null)
				msgs = ((InternalEObject)endpoint).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UcmPackage.PATH__ENDPOINT, null, msgs);
			if (newEndpoint != null)
				msgs = ((InternalEObject)newEndpoint).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UcmPackage.PATH__ENDPOINT, null, msgs);
			msgs = basicSetEndpoint(newEndpoint, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UcmPackage.PATH__ENDPOINT, newEndpoint, newEndpoint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StartPoint getStartpoint() {
		return startpoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStartpoint(StartPoint newStartpoint, NotificationChain msgs) {
		StartPoint oldStartpoint = startpoint;
		startpoint = newStartpoint;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UcmPackage.PATH__STARTPOINT, oldStartpoint, newStartpoint);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartpoint(StartPoint newStartpoint) {
		if (newStartpoint != startpoint) {
			NotificationChain msgs = null;
			if (startpoint != null)
				msgs = ((InternalEObject)startpoint).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UcmPackage.PATH__STARTPOINT, null, msgs);
			if (newStartpoint != null)
				msgs = ((InternalEObject)newStartpoint).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UcmPackage.PATH__STARTPOINT, null, msgs);
			msgs = basicSetStartpoint(newStartpoint, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UcmPackage.PATH__STARTPOINT, newStartpoint, newStartpoint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case UcmPackage.PATH__NODES:
					return ((InternalEList)getNodes()).basicAdd(otherEnd, msgs);
				case UcmPackage.PATH__DIAGRAM:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, UcmPackage.PATH__DIAGRAM, msgs);
				case UcmPackage.PATH__IN_FORK:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, UcmPackage.PATH__IN_FORK, msgs);
				case UcmPackage.PATH__OUT_FORK:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, UcmPackage.PATH__OUT_FORK, msgs);
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
				case UcmPackage.PATH__NODES:
					return ((InternalEList)getNodes()).basicRemove(otherEnd, msgs);
				case UcmPackage.PATH__DIAGRAM:
					return eBasicSetContainer(null, UcmPackage.PATH__DIAGRAM, msgs);
				case UcmPackage.PATH__IN_FORK:
					return eBasicSetContainer(null, UcmPackage.PATH__IN_FORK, msgs);
				case UcmPackage.PATH__OUT_FORK:
					return eBasicSetContainer(null, UcmPackage.PATH__OUT_FORK, msgs);
				case UcmPackage.PATH__ENDPOINT:
					return basicSetEndpoint(null, msgs);
				case UcmPackage.PATH__STARTPOINT:
					return basicSetStartpoint(null, msgs);
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
				case UcmPackage.PATH__DIAGRAM:
					return ((InternalEObject)eContainer).eInverseRemove(this, UcmPackage.UCM_DIAGRAM__PATHS, UcmDiagram.class, msgs);
				case UcmPackage.PATH__IN_FORK:
					return ((InternalEObject)eContainer).eInverseRemove(this, UcmPackage.FORK__IN_PATHS, Fork.class, msgs);
				case UcmPackage.PATH__OUT_FORK:
					return ((InternalEObject)eContainer).eInverseRemove(this, UcmPackage.FORK__OUT_PATH, Fork.class, msgs);
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
			case UcmPackage.PATH__NODES:
				return getNodes();
			case UcmPackage.PATH__DIAGRAM:
				return getDiagram();
			case UcmPackage.PATH__IN_FORK:
				return getInFork();
			case UcmPackage.PATH__OUT_FORK:
				return getOutFork();
			case UcmPackage.PATH__ENDPOINT:
				return getEndpoint();
			case UcmPackage.PATH__STARTPOINT:
				return getStartpoint();
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
			case UcmPackage.PATH__NODES:
				getNodes().clear();
				getNodes().addAll((Collection)newValue);
				return;
			case UcmPackage.PATH__DIAGRAM:
				setDiagram((UcmDiagram)newValue);
				return;
			case UcmPackage.PATH__IN_FORK:
				setInFork((Fork)newValue);
				return;
			case UcmPackage.PATH__OUT_FORK:
				setOutFork((Fork)newValue);
				return;
			case UcmPackage.PATH__ENDPOINT:
				setEndpoint((EndPoint)newValue);
				return;
			case UcmPackage.PATH__STARTPOINT:
				setStartpoint((StartPoint)newValue);
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
			case UcmPackage.PATH__NODES:
				getNodes().clear();
				return;
			case UcmPackage.PATH__DIAGRAM:
				setDiagram((UcmDiagram)null);
				return;
			case UcmPackage.PATH__IN_FORK:
				setInFork((Fork)null);
				return;
			case UcmPackage.PATH__OUT_FORK:
				setOutFork((Fork)null);
				return;
			case UcmPackage.PATH__ENDPOINT:
				setEndpoint((EndPoint)null);
				return;
			case UcmPackage.PATH__STARTPOINT:
				setStartpoint((StartPoint)null);
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
			case UcmPackage.PATH__NODES:
				return nodes != null && !nodes.isEmpty();
			case UcmPackage.PATH__DIAGRAM:
				return getDiagram() != null;
			case UcmPackage.PATH__IN_FORK:
				return getInFork() != null;
			case UcmPackage.PATH__OUT_FORK:
				return getOutFork() != null;
			case UcmPackage.PATH__ENDPOINT:
				return endpoint != null;
			case UcmPackage.PATH__STARTPOINT:
				return startpoint != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //PathImpl
