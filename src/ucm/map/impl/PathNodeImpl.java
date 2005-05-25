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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import ucm.map.ComponentRef;
import ucm.map.MapPackage;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.PathNode;

import urncore.NodeLabel;
import urncore.UrncorePackage;

import urncore.impl.UCMmodelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Path Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.map.impl.PathNodeImpl#getX <em>X</em>}</li>
 *   <li>{@link ucm.map.impl.PathNodeImpl#getY <em>Y</em>}</li>
 *   <li>{@link ucm.map.impl.PathNodeImpl#getPathGraph <em>Path Graph</em>}</li>
 *   <li>{@link ucm.map.impl.PathNodeImpl#getSucc <em>Succ</em>}</li>
 *   <li>{@link ucm.map.impl.PathNodeImpl#getPred <em>Pred</em>}</li>
 *   <li>{@link ucm.map.impl.PathNodeImpl#getCompRef <em>Comp Ref</em>}</li>
 *   <li>{@link ucm.map.impl.PathNodeImpl#getLabel <em>Label</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class PathNodeImpl extends UCMmodelElementImpl implements PathNode {
	/**
	 * The default value of the '{@link #getX() <em>X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getX()
	 * @generated
	 * @ordered
	 */
	protected static final int X_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getX() <em>X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getX()
	 * @generated
	 * @ordered
	 */
	protected int x = X_EDEFAULT;

	/**
	 * The default value of the '{@link #getY() <em>Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getY()
	 * @generated
	 * @ordered
	 */
	protected static final int Y_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getY() <em>Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getY()
	 * @generated
	 * @ordered
	 */
	protected int y = Y_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSucc() <em>Succ</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSucc()
	 * @generated
	 * @ordered
	 */
	protected EList succ = null;

	/**
	 * The cached value of the '{@link #getPred() <em>Pred</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPred()
	 * @generated
	 * @ordered
	 */
	protected EList pred = null;

	/**
	 * The cached value of the '{@link #getCompRef() <em>Comp Ref</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompRef()
	 * @generated
	 * @ordered
	 */
	protected ComponentRef compRef = null;

	/**
	 * The cached value of the '{@link #getLabel() <em>Label</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected NodeLabel label = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PathNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return MapPackage.eINSTANCE.getPathNode();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getX() {
		return x;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setX(int newX) {
		int oldX = x;
		x = newX;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.PATH_NODE__X, oldX, x));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getY() {
		return y;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setY(int newY) {
		int oldY = y;
		y = newY;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.PATH_NODE__Y, oldY, y));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PathGraph getPathGraph() {
		if (eContainerFeatureID != MapPackage.PATH_NODE__PATH_GRAPH) return null;
		return (PathGraph)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPathGraph(PathGraph newPathGraph) {
		if (newPathGraph != eContainer || (eContainerFeatureID != MapPackage.PATH_NODE__PATH_GRAPH && newPathGraph != null)) {
			if (EcoreUtil.isAncestor(this, newPathGraph))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPathGraph != null)
				msgs = ((InternalEObject)newPathGraph).eInverseAdd(this, MapPackage.PATH_GRAPH__PATH_NODES, PathGraph.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newPathGraph, MapPackage.PATH_NODE__PATH_GRAPH, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.PATH_NODE__PATH_GRAPH, newPathGraph, newPathGraph));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getSucc() {
		if (succ == null) {
			succ = new EObjectWithInverseResolvingEList(NodeConnection.class, this, MapPackage.PATH_NODE__SUCC, MapPackage.NODE_CONNECTION__SOURCE);
		}
		return succ;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getPred() {
		if (pred == null) {
			pred = new EObjectWithInverseResolvingEList(NodeConnection.class, this, MapPackage.PATH_NODE__PRED, MapPackage.NODE_CONNECTION__TARGET);
		}
		return pred;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentRef getCompRef() {
		if (compRef != null && compRef.eIsProxy()) {
			ComponentRef oldCompRef = compRef;
			compRef = (ComponentRef)eResolveProxy((InternalEObject)compRef);
			if (compRef != oldCompRef) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MapPackage.PATH_NODE__COMP_REF, oldCompRef, compRef));
			}
		}
		return compRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentRef basicGetCompRef() {
		return compRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCompRef(ComponentRef newCompRef, NotificationChain msgs) {
		ComponentRef oldCompRef = compRef;
		compRef = newCompRef;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.PATH_NODE__COMP_REF, oldCompRef, newCompRef);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCompRef(ComponentRef newCompRef) {
		if (newCompRef != compRef) {
			NotificationChain msgs = null;
			if (compRef != null)
				msgs = ((InternalEObject)compRef).eInverseRemove(this, MapPackage.COMPONENT_REF__PATH_NODES, ComponentRef.class, msgs);
			if (newCompRef != null)
				msgs = ((InternalEObject)newCompRef).eInverseAdd(this, MapPackage.COMPONENT_REF__PATH_NODES, ComponentRef.class, msgs);
			msgs = basicSetCompRef(newCompRef, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.PATH_NODE__COMP_REF, newCompRef, newCompRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NodeLabel getLabel() {
		return label;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLabel(NodeLabel newLabel, NotificationChain msgs) {
		NodeLabel oldLabel = label;
		label = newLabel;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.PATH_NODE__LABEL, oldLabel, newLabel);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabel(NodeLabel newLabel) {
		if (newLabel != label) {
			NotificationChain msgs = null;
			if (label != null)
				msgs = ((InternalEObject)label).eInverseRemove(this, UrncorePackage.NODE_LABEL__PATH_NODE, NodeLabel.class, msgs);
			if (newLabel != null)
				msgs = ((InternalEObject)newLabel).eInverseAdd(this, UrncorePackage.NODE_LABEL__PATH_NODE, NodeLabel.class, msgs);
			msgs = basicSetLabel(newLabel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.PATH_NODE__LABEL, newLabel, newLabel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case MapPackage.PATH_NODE__URN_LINKS:
					return ((InternalEList)getUrnLinks()).basicAdd(otherEnd, msgs);
				case MapPackage.PATH_NODE__PATH_GRAPH:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, MapPackage.PATH_NODE__PATH_GRAPH, msgs);
				case MapPackage.PATH_NODE__SUCC:
					return ((InternalEList)getSucc()).basicAdd(otherEnd, msgs);
				case MapPackage.PATH_NODE__PRED:
					return ((InternalEList)getPred()).basicAdd(otherEnd, msgs);
				case MapPackage.PATH_NODE__COMP_REF:
					if (compRef != null)
						msgs = ((InternalEObject)compRef).eInverseRemove(this, MapPackage.COMPONENT_REF__PATH_NODES, ComponentRef.class, msgs);
					return basicSetCompRef((ComponentRef)otherEnd, msgs);
				case MapPackage.PATH_NODE__LABEL:
					if (label != null)
						msgs = ((InternalEObject)label).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MapPackage.PATH_NODE__LABEL, null, msgs);
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
				case MapPackage.PATH_NODE__URN_LINKS:
					return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
				case MapPackage.PATH_NODE__PATH_GRAPH:
					return eBasicSetContainer(null, MapPackage.PATH_NODE__PATH_GRAPH, msgs);
				case MapPackage.PATH_NODE__SUCC:
					return ((InternalEList)getSucc()).basicRemove(otherEnd, msgs);
				case MapPackage.PATH_NODE__PRED:
					return ((InternalEList)getPred()).basicRemove(otherEnd, msgs);
				case MapPackage.PATH_NODE__COMP_REF:
					return basicSetCompRef(null, msgs);
				case MapPackage.PATH_NODE__LABEL:
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
				case MapPackage.PATH_NODE__PATH_GRAPH:
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
			case MapPackage.PATH_NODE__URN_LINKS:
				return getUrnLinks();
			case MapPackage.PATH_NODE__ID:
				return getId();
			case MapPackage.PATH_NODE__NAME:
				return getName();
			case MapPackage.PATH_NODE__DESCRIPTION:
				return getDescription();
			case MapPackage.PATH_NODE__X:
				return new Integer(getX());
			case MapPackage.PATH_NODE__Y:
				return new Integer(getY());
			case MapPackage.PATH_NODE__PATH_GRAPH:
				return getPathGraph();
			case MapPackage.PATH_NODE__SUCC:
				return getSucc();
			case MapPackage.PATH_NODE__PRED:
				return getPred();
			case MapPackage.PATH_NODE__COMP_REF:
				if (resolve) return getCompRef();
				return basicGetCompRef();
			case MapPackage.PATH_NODE__LABEL:
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
			case MapPackage.PATH_NODE__URN_LINKS:
				getUrnLinks().clear();
				getUrnLinks().addAll((Collection)newValue);
				return;
			case MapPackage.PATH_NODE__ID:
				setId((String)newValue);
				return;
			case MapPackage.PATH_NODE__NAME:
				setName((String)newValue);
				return;
			case MapPackage.PATH_NODE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case MapPackage.PATH_NODE__X:
				setX(((Integer)newValue).intValue());
				return;
			case MapPackage.PATH_NODE__Y:
				setY(((Integer)newValue).intValue());
				return;
			case MapPackage.PATH_NODE__PATH_GRAPH:
				setPathGraph((PathGraph)newValue);
				return;
			case MapPackage.PATH_NODE__SUCC:
				getSucc().clear();
				getSucc().addAll((Collection)newValue);
				return;
			case MapPackage.PATH_NODE__PRED:
				getPred().clear();
				getPred().addAll((Collection)newValue);
				return;
			case MapPackage.PATH_NODE__COMP_REF:
				setCompRef((ComponentRef)newValue);
				return;
			case MapPackage.PATH_NODE__LABEL:
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
			case MapPackage.PATH_NODE__URN_LINKS:
				getUrnLinks().clear();
				return;
			case MapPackage.PATH_NODE__ID:
				setId(ID_EDEFAULT);
				return;
			case MapPackage.PATH_NODE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case MapPackage.PATH_NODE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case MapPackage.PATH_NODE__X:
				setX(X_EDEFAULT);
				return;
			case MapPackage.PATH_NODE__Y:
				setY(Y_EDEFAULT);
				return;
			case MapPackage.PATH_NODE__PATH_GRAPH:
				setPathGraph((PathGraph)null);
				return;
			case MapPackage.PATH_NODE__SUCC:
				getSucc().clear();
				return;
			case MapPackage.PATH_NODE__PRED:
				getPred().clear();
				return;
			case MapPackage.PATH_NODE__COMP_REF:
				setCompRef((ComponentRef)null);
				return;
			case MapPackage.PATH_NODE__LABEL:
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
			case MapPackage.PATH_NODE__URN_LINKS:
				return urnLinks != null && !urnLinks.isEmpty();
			case MapPackage.PATH_NODE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case MapPackage.PATH_NODE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case MapPackage.PATH_NODE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case MapPackage.PATH_NODE__X:
				return x != X_EDEFAULT;
			case MapPackage.PATH_NODE__Y:
				return y != Y_EDEFAULT;
			case MapPackage.PATH_NODE__PATH_GRAPH:
				return getPathGraph() != null;
			case MapPackage.PATH_NODE__SUCC:
				return succ != null && !succ.isEmpty();
			case MapPackage.PATH_NODE__PRED:
				return pred != null && !pred.isEmpty();
			case MapPackage.PATH_NODE__COMP_REF:
				return compRef != null;
			case MapPackage.PATH_NODE__LABEL:
				return label != null;
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
		result.append(" (x: ");
		result.append(x);
		result.append(", y: ");
		result.append(y);
		result.append(')');
		return result.toString();
	}

} //PathNodeImpl
