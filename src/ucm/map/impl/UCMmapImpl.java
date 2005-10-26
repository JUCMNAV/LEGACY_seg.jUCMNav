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

import ucm.map.MapPackage;
import ucm.map.PluginBinding;
import ucm.map.UCMmap;

import urncore.SpecificationComponentRef;
import urncore.SpecificationConnection;
import urncore.SpecificationDiagram;
import urncore.SpecificationNode;
import urncore.URNdefinition;
import urncore.UrncorePackage;

import urncore.impl.UCMmodelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>UC Mmap</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.map.impl.UCMmapImpl#getUrndefinition <em>Urndefinition</em>}</li>
 *   <li>{@link ucm.map.impl.UCMmapImpl#getNodes <em>Nodes</em>}</li>
 *   <li>{@link ucm.map.impl.UCMmapImpl#getCompRefs <em>Comp Refs</em>}</li>
 *   <li>{@link ucm.map.impl.UCMmapImpl#getConnections <em>Connections</em>}</li>
 *   <li>{@link ucm.map.impl.UCMmapImpl#getParentStub <em>Parent Stub</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UCMmapImpl extends UCMmodelElementImpl implements UCMmap {
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
     * The cached value of the '{@link #getCompRefs() <em>Comp Refs</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCompRefs()
     * @generated
     * @ordered
     */
    protected EList compRefs = null;

    /**
     * The cached value of the '{@link #getConnections() <em>Connections</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getConnections()
     * @generated
     * @ordered
     */
    protected EList connections = null;

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
    protected UCMmapImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return MapPackage.eINSTANCE.getUCMmap();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public URNdefinition getUrndefinition() {
        if (eContainerFeatureID != MapPackage.UC_MMAP__URNDEFINITION) return null;
        return (URNdefinition)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUrndefinition(URNdefinition newUrndefinition) {
        if (newUrndefinition != eContainer || (eContainerFeatureID != MapPackage.UC_MMAP__URNDEFINITION && newUrndefinition != null)) {
            if (EcoreUtil.isAncestor(this, newUrndefinition))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newUrndefinition != null)
                msgs = ((InternalEObject)newUrndefinition).eInverseAdd(this, UrncorePackage.UR_NDEFINITION__SPEC_DIAGRAMS, URNdefinition.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newUrndefinition, MapPackage.UC_MMAP__URNDEFINITION, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.UC_MMAP__URNDEFINITION, newUrndefinition, newUrndefinition));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getNodes() {
        if (nodes == null) {
            nodes = new EObjectContainmentWithInverseEList(SpecificationNode.class, this, MapPackage.UC_MMAP__NODES, UrncorePackage.SPECIFICATION_NODE__SPEC_DIAGRAM);
        }
        return nodes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getCompRefs() {
        if (compRefs == null) {
            compRefs = new EObjectContainmentWithInverseEList(SpecificationComponentRef.class, this, MapPackage.UC_MMAP__COMP_REFS, UrncorePackage.SPECIFICATION_COMPONENT_REF__SPEC_DIAGRAM);
        }
        return compRefs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getConnections() {
        if (connections == null) {
            connections = new EObjectContainmentWithInverseEList(SpecificationConnection.class, this, MapPackage.UC_MMAP__CONNECTIONS, UrncorePackage.SPECIFICATION_CONNECTION__SPEC_DIAGRAM);
        }
        return connections;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getParentStub() {
        if (parentStub == null) {
            parentStub = new EObjectWithInverseResolvingEList(PluginBinding.class, this, MapPackage.UC_MMAP__PARENT_STUB, MapPackage.PLUGIN_BINDING__PLUGIN);
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
                case MapPackage.UC_MMAP__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicAdd(otherEnd, msgs);
                case MapPackage.UC_MMAP__URNDEFINITION:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, MapPackage.UC_MMAP__URNDEFINITION, msgs);
                case MapPackage.UC_MMAP__NODES:
                    return ((InternalEList)getNodes()).basicAdd(otherEnd, msgs);
                case MapPackage.UC_MMAP__COMP_REFS:
                    return ((InternalEList)getCompRefs()).basicAdd(otherEnd, msgs);
                case MapPackage.UC_MMAP__CONNECTIONS:
                    return ((InternalEList)getConnections()).basicAdd(otherEnd, msgs);
                case MapPackage.UC_MMAP__PARENT_STUB:
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
                case MapPackage.UC_MMAP__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
                case MapPackage.UC_MMAP__URNDEFINITION:
                    return eBasicSetContainer(null, MapPackage.UC_MMAP__URNDEFINITION, msgs);
                case MapPackage.UC_MMAP__NODES:
                    return ((InternalEList)getNodes()).basicRemove(otherEnd, msgs);
                case MapPackage.UC_MMAP__COMP_REFS:
                    return ((InternalEList)getCompRefs()).basicRemove(otherEnd, msgs);
                case MapPackage.UC_MMAP__CONNECTIONS:
                    return ((InternalEList)getConnections()).basicRemove(otherEnd, msgs);
                case MapPackage.UC_MMAP__PARENT_STUB:
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
                case MapPackage.UC_MMAP__URNDEFINITION:
                    return ((InternalEObject)eContainer).eInverseRemove(this, UrncorePackage.UR_NDEFINITION__SPEC_DIAGRAMS, URNdefinition.class, msgs);
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
            case MapPackage.UC_MMAP__ID:
                return getId();
            case MapPackage.UC_MMAP__NAME:
                return getName();
            case MapPackage.UC_MMAP__DESCRIPTION:
                return getDescription();
            case MapPackage.UC_MMAP__URN_LINKS:
                return getUrnLinks();
            case MapPackage.UC_MMAP__URNDEFINITION:
                return getUrndefinition();
            case MapPackage.UC_MMAP__NODES:
                return getNodes();
            case MapPackage.UC_MMAP__COMP_REFS:
                return getCompRefs();
            case MapPackage.UC_MMAP__CONNECTIONS:
                return getConnections();
            case MapPackage.UC_MMAP__PARENT_STUB:
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
            case MapPackage.UC_MMAP__ID:
                setId((String)newValue);
                return;
            case MapPackage.UC_MMAP__NAME:
                setName((String)newValue);
                return;
            case MapPackage.UC_MMAP__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case MapPackage.UC_MMAP__URN_LINKS:
                getUrnLinks().clear();
                getUrnLinks().addAll((Collection)newValue);
                return;
            case MapPackage.UC_MMAP__URNDEFINITION:
                setUrndefinition((URNdefinition)newValue);
                return;
            case MapPackage.UC_MMAP__NODES:
                getNodes().clear();
                getNodes().addAll((Collection)newValue);
                return;
            case MapPackage.UC_MMAP__COMP_REFS:
                getCompRefs().clear();
                getCompRefs().addAll((Collection)newValue);
                return;
            case MapPackage.UC_MMAP__CONNECTIONS:
                getConnections().clear();
                getConnections().addAll((Collection)newValue);
                return;
            case MapPackage.UC_MMAP__PARENT_STUB:
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
            case MapPackage.UC_MMAP__ID:
                setId(ID_EDEFAULT);
                return;
            case MapPackage.UC_MMAP__NAME:
                setName(NAME_EDEFAULT);
                return;
            case MapPackage.UC_MMAP__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case MapPackage.UC_MMAP__URN_LINKS:
                getUrnLinks().clear();
                return;
            case MapPackage.UC_MMAP__URNDEFINITION:
                setUrndefinition((URNdefinition)null);
                return;
            case MapPackage.UC_MMAP__NODES:
                getNodes().clear();
                return;
            case MapPackage.UC_MMAP__COMP_REFS:
                getCompRefs().clear();
                return;
            case MapPackage.UC_MMAP__CONNECTIONS:
                getConnections().clear();
                return;
            case MapPackage.UC_MMAP__PARENT_STUB:
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
            case MapPackage.UC_MMAP__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case MapPackage.UC_MMAP__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case MapPackage.UC_MMAP__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case MapPackage.UC_MMAP__URN_LINKS:
                return urnLinks != null && !urnLinks.isEmpty();
            case MapPackage.UC_MMAP__URNDEFINITION:
                return getUrndefinition() != null;
            case MapPackage.UC_MMAP__NODES:
                return nodes != null && !nodes.isEmpty();
            case MapPackage.UC_MMAP__COMP_REFS:
                return compRefs != null && !compRefs.isEmpty();
            case MapPackage.UC_MMAP__CONNECTIONS:
                return connections != null && !connections.isEmpty();
            case MapPackage.UC_MMAP__PARENT_STUB:
                return parentStub != null && !parentStub.isEmpty();
        }
        return eDynamicIsSet(eFeature);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
        if (baseClass == SpecificationDiagram.class) {
            switch (derivedFeatureID) {
                case MapPackage.UC_MMAP__URNDEFINITION: return UrncorePackage.SPECIFICATION_DIAGRAM__URNDEFINITION;
                case MapPackage.UC_MMAP__NODES: return UrncorePackage.SPECIFICATION_DIAGRAM__NODES;
                case MapPackage.UC_MMAP__COMP_REFS: return UrncorePackage.SPECIFICATION_DIAGRAM__COMP_REFS;
                case MapPackage.UC_MMAP__CONNECTIONS: return UrncorePackage.SPECIFICATION_DIAGRAM__CONNECTIONS;
                default: return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass) {
        if (baseClass == SpecificationDiagram.class) {
            switch (baseFeatureID) {
                case UrncorePackage.SPECIFICATION_DIAGRAM__URNDEFINITION: return MapPackage.UC_MMAP__URNDEFINITION;
                case UrncorePackage.SPECIFICATION_DIAGRAM__NODES: return MapPackage.UC_MMAP__NODES;
                case UrncorePackage.SPECIFICATION_DIAGRAM__COMP_REFS: return MapPackage.UC_MMAP__COMP_REFS;
                case UrncorePackage.SPECIFICATION_DIAGRAM__CONNECTIONS: return MapPackage.UC_MMAP__CONNECTIONS;
                default: return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

} //UCMmapImpl
