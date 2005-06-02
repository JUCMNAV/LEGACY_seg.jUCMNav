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

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import ucm.UCMspec;
import ucm.UcmPackage;

import ucm.map.ComponentRef;
import ucm.map.Map;
import ucm.map.MapPackage;
import ucm.map.PathGraph;
import ucm.map.PluginBinding;

import urncore.impl.UCMmodelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Map</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.map.impl.MapImpl#getPathGraph <em>Path Graph</em>}</li>
 *   <li>{@link ucm.map.impl.MapImpl#getCompRefs <em>Comp Refs</em>}</li>
 *   <li>{@link ucm.map.impl.MapImpl#getUcmspec <em>Ucmspec</em>}</li>
 *   <li>{@link ucm.map.impl.MapImpl#getParentStub <em>Parent Stub</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MapImpl extends UCMmodelElementImpl implements Map {
    /**
     * The cached value of the '{@link #getPathGraph() <em>Path Graph</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getPathGraph()
     * @generated
     * @ordered
     */
	protected PathGraph pathGraph = null;

    /**
     * The cached value of the '{@link #getCompRefs() <em>Comp Refs</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getCompRefs()
     * @generated
     * @ordered
     */
	protected EList compRefs = null;

    /**
     * The cached value of the '{@link #getParentStub() <em>Parent Stub</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getParentStub()
     * @generated
     * @ordered
     */
	protected EList parentStub = null;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected MapImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected EClass eStaticClass() {
        return MapPackage.eINSTANCE.getMap();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public PathGraph getPathGraph() {
        return pathGraph;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetPathGraph(PathGraph newPathGraph, NotificationChain msgs) {
        PathGraph oldPathGraph = pathGraph;
        pathGraph = newPathGraph;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.MAP__PATH_GRAPH, oldPathGraph, newPathGraph);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setPathGraph(PathGraph newPathGraph) {
        if (newPathGraph != pathGraph) {
            NotificationChain msgs = null;
            if (pathGraph != null)
                msgs = ((InternalEObject)pathGraph).eInverseRemove(this, MapPackage.PATH_GRAPH__MAP, PathGraph.class, msgs);
            if (newPathGraph != null)
                msgs = ((InternalEObject)newPathGraph).eInverseAdd(this, MapPackage.PATH_GRAPH__MAP, PathGraph.class, msgs);
            msgs = basicSetPathGraph(newPathGraph, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.MAP__PATH_GRAPH, newPathGraph, newPathGraph));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getCompRefs() {
        if (compRefs == null) {
            compRefs = new EObjectContainmentWithInverseEList(ComponentRef.class, this, MapPackage.MAP__COMP_REFS, MapPackage.COMPONENT_REF__MAP);
        }
        return compRefs;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public UCMspec getUcmspec() {
        if (eContainerFeatureID != MapPackage.MAP__UCMSPEC) return null;
        return (UCMspec)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setUcmspec(UCMspec newUcmspec) {
        if (newUcmspec != eContainer || (eContainerFeatureID != MapPackage.MAP__UCMSPEC && newUcmspec != null)) {
            if (EcoreUtil.isAncestor(this, newUcmspec))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newUcmspec != null)
                msgs = ((InternalEObject)newUcmspec).eInverseAdd(this, UcmPackage.UC_MSPEC__MAPS, UCMspec.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newUcmspec, MapPackage.MAP__UCMSPEC, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.MAP__UCMSPEC, newUcmspec, newUcmspec));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getParentStub() {
        if (parentStub == null) {
            parentStub = new EObjectWithInverseResolvingEList(PluginBinding.class, this, MapPackage.MAP__PARENT_STUB, MapPackage.PLUGIN_BINDING__PLUGIN);
        }
        return parentStub;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case MapPackage.MAP__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicAdd(otherEnd, msgs);
                case MapPackage.MAP__PATH_GRAPH:
                    if (pathGraph != null)
                        msgs = ((InternalEObject)pathGraph).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MapPackage.MAP__PATH_GRAPH, null, msgs);
                    return basicSetPathGraph((PathGraph)otherEnd, msgs);
                case MapPackage.MAP__COMP_REFS:
                    return ((InternalEList)getCompRefs()).basicAdd(otherEnd, msgs);
                case MapPackage.MAP__UCMSPEC:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, MapPackage.MAP__UCMSPEC, msgs);
                case MapPackage.MAP__PARENT_STUB:
                    return ((InternalEList)getParentStub()).basicAdd(otherEnd, msgs);
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
                case MapPackage.MAP__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
                case MapPackage.MAP__PATH_GRAPH:
                    return basicSetPathGraph(null, msgs);
                case MapPackage.MAP__COMP_REFS:
                    return ((InternalEList)getCompRefs()).basicRemove(otherEnd, msgs);
                case MapPackage.MAP__UCMSPEC:
                    return eBasicSetContainer(null, MapPackage.MAP__UCMSPEC, msgs);
                case MapPackage.MAP__PARENT_STUB:
                    return ((InternalEList)getParentStub()).basicRemove(otherEnd, msgs);
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
                case MapPackage.MAP__UCMSPEC:
                    return ((InternalEObject)eContainer).eInverseRemove(this, UcmPackage.UC_MSPEC__MAPS, UCMspec.class, msgs);
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
            case MapPackage.MAP__URN_LINKS:
                return getUrnLinks();
            case MapPackage.MAP__ID:
                return getId();
            case MapPackage.MAP__NAME:
                return getName();
            case MapPackage.MAP__DESCRIPTION:
                return getDescription();
            case MapPackage.MAP__PATH_GRAPH:
                return getPathGraph();
            case MapPackage.MAP__COMP_REFS:
                return getCompRefs();
            case MapPackage.MAP__UCMSPEC:
                return getUcmspec();
            case MapPackage.MAP__PARENT_STUB:
                return getParentStub();
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
            case MapPackage.MAP__URN_LINKS:
                getUrnLinks().clear();
                getUrnLinks().addAll((Collection)newValue);
                return;
            case MapPackage.MAP__ID:
                setId((String)newValue);
                return;
            case MapPackage.MAP__NAME:
                setName((String)newValue);
                return;
            case MapPackage.MAP__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case MapPackage.MAP__PATH_GRAPH:
                setPathGraph((PathGraph)newValue);
                return;
            case MapPackage.MAP__COMP_REFS:
                getCompRefs().clear();
                getCompRefs().addAll((Collection)newValue);
                return;
            case MapPackage.MAP__UCMSPEC:
                setUcmspec((UCMspec)newValue);
                return;
            case MapPackage.MAP__PARENT_STUB:
                getParentStub().clear();
                getParentStub().addAll((Collection)newValue);
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
            case MapPackage.MAP__URN_LINKS:
                getUrnLinks().clear();
                return;
            case MapPackage.MAP__ID:
                setId(ID_EDEFAULT);
                return;
            case MapPackage.MAP__NAME:
                setName(NAME_EDEFAULT);
                return;
            case MapPackage.MAP__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case MapPackage.MAP__PATH_GRAPH:
                setPathGraph((PathGraph)null);
                return;
            case MapPackage.MAP__COMP_REFS:
                getCompRefs().clear();
                return;
            case MapPackage.MAP__UCMSPEC:
                setUcmspec((UCMspec)null);
                return;
            case MapPackage.MAP__PARENT_STUB:
                getParentStub().clear();
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
            case MapPackage.MAP__URN_LINKS:
                return urnLinks != null && !urnLinks.isEmpty();
            case MapPackage.MAP__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case MapPackage.MAP__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case MapPackage.MAP__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case MapPackage.MAP__PATH_GRAPH:
                return pathGraph != null;
            case MapPackage.MAP__COMP_REFS:
                return compRefs != null && !compRefs.isEmpty();
            case MapPackage.MAP__UCMSPEC:
                return getUcmspec() != null;
            case MapPackage.MAP__PARENT_STUB:
                return parentStub != null && !parentStub.isEmpty();
        }
        return eDynamicIsSet(eFeature);
    }

} //MapImpl
