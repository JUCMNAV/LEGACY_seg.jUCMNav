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
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import ucm.map.Map;
import ucm.map.MapPackage;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.PathNode;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Path Graph</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.map.impl.PathGraphImpl#getPathNodes <em>Path Nodes</em>}</li>
 *   <li>{@link ucm.map.impl.PathGraphImpl#getNodeConnections <em>Node Connections</em>}</li>
 *   <li>{@link ucm.map.impl.PathGraphImpl#getMap <em>Map</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PathGraphImpl extends EObjectImpl implements PathGraph {
    /**
     * The cached value of the '{@link #getPathNodes() <em>Path Nodes</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getPathNodes()
     * @generated
     * @ordered
     */
	protected EList pathNodes = null;

    /**
     * The cached value of the '{@link #getNodeConnections() <em>Node Connections</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getNodeConnections()
     * @generated
     * @ordered
     */
	protected EList nodeConnections = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected PathGraphImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return MapPackage.eINSTANCE.getPathGraph();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getPathNodes() {
        if (pathNodes == null) {
            pathNodes = new EObjectContainmentWithInverseEList(PathNode.class, this, MapPackage.PATH_GRAPH__PATH_NODES, MapPackage.PATH_NODE__PATH_GRAPH);
        }
        return pathNodes;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getNodeConnections() {
        if (nodeConnections == null) {
            nodeConnections = new EObjectContainmentWithInverseEList(NodeConnection.class, this, MapPackage.PATH_GRAPH__NODE_CONNECTIONS, MapPackage.NODE_CONNECTION__PATH_GRAPH);
        }
        return nodeConnections;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Map getMap() {
        if (eContainerFeatureID != MapPackage.PATH_GRAPH__MAP) return null;
        return (Map)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setMap(Map newMap) {
        if (newMap != eContainer || (eContainerFeatureID != MapPackage.PATH_GRAPH__MAP && newMap != null)) {
            if (EcoreUtil.isAncestor(this, newMap))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newMap != null)
                msgs = ((InternalEObject)newMap).eInverseAdd(this, MapPackage.MAP__PATH_GRAPH, Map.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newMap, MapPackage.PATH_GRAPH__MAP, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.PATH_GRAPH__MAP, newMap, newMap));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case MapPackage.PATH_GRAPH__PATH_NODES:
                    return ((InternalEList)getPathNodes()).basicAdd(otherEnd, msgs);
                case MapPackage.PATH_GRAPH__NODE_CONNECTIONS:
                    return ((InternalEList)getNodeConnections()).basicAdd(otherEnd, msgs);
                case MapPackage.PATH_GRAPH__MAP:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, MapPackage.PATH_GRAPH__MAP, msgs);
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
                case MapPackage.PATH_GRAPH__PATH_NODES:
                    return ((InternalEList)getPathNodes()).basicRemove(otherEnd, msgs);
                case MapPackage.PATH_GRAPH__NODE_CONNECTIONS:
                    return ((InternalEList)getNodeConnections()).basicRemove(otherEnd, msgs);
                case MapPackage.PATH_GRAPH__MAP:
                    return eBasicSetContainer(null, MapPackage.PATH_GRAPH__MAP, msgs);
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
                case MapPackage.PATH_GRAPH__MAP:
                    return eContainer.eInverseRemove(this, MapPackage.MAP__PATH_GRAPH, Map.class, msgs);
                default:
                    return eDynamicBasicRemoveFromContainer(msgs);
            }
        }
        return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case MapPackage.PATH_GRAPH__PATH_NODES:
                return getPathNodes();
            case MapPackage.PATH_GRAPH__NODE_CONNECTIONS:
                return getNodeConnections();
            case MapPackage.PATH_GRAPH__MAP:
                return getMap();
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
            case MapPackage.PATH_GRAPH__PATH_NODES:
                getPathNodes().clear();
                getPathNodes().addAll((Collection)newValue);
                return;
            case MapPackage.PATH_GRAPH__NODE_CONNECTIONS:
                getNodeConnections().clear();
                getNodeConnections().addAll((Collection)newValue);
                return;
            case MapPackage.PATH_GRAPH__MAP:
                setMap((Map)newValue);
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
            case MapPackage.PATH_GRAPH__PATH_NODES:
                getPathNodes().clear();
                return;
            case MapPackage.PATH_GRAPH__NODE_CONNECTIONS:
                getNodeConnections().clear();
                return;
            case MapPackage.PATH_GRAPH__MAP:
                setMap((Map)null);
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
            case MapPackage.PATH_GRAPH__PATH_NODES:
                return pathNodes != null && !pathNodes.isEmpty();
            case MapPackage.PATH_GRAPH__NODE_CONNECTIONS:
                return nodeConnections != null && !nodeConnections.isEmpty();
            case MapPackage.PATH_GRAPH__MAP:
                return getMap() != null;
        }
        return eDynamicIsSet(eFeature);
    }

} //PathGraphImpl
