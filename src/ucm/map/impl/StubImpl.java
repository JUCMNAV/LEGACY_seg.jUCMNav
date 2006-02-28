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
import org.eclipse.emf.ecore.util.InternalEList;

import ucm.map.MapPackage;
import ucm.map.PluginBinding;
import ucm.map.Stub;

import urncore.NodeLabel;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Stub</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.map.impl.StubImpl#isDynamic <em>Dynamic</em>}</li>
 *   <li>{@link ucm.map.impl.StubImpl#isShared <em>Shared</em>}</li>
 *   <li>{@link ucm.map.impl.StubImpl#getBindings <em>Bindings</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StubImpl extends PathNodeImpl implements Stub {
    /**
     * The default value of the '{@link #isDynamic() <em>Dynamic</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isDynamic()
     * @generated
     * @ordered
     */
    protected static final boolean DYNAMIC_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isDynamic() <em>Dynamic</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isDynamic()
     * @generated
     * @ordered
     */
    protected boolean dynamic = DYNAMIC_EDEFAULT;

    /**
     * The default value of the '{@link #isShared() <em>Shared</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isShared()
     * @generated
     * @ordered
     */
    protected static final boolean SHARED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isShared() <em>Shared</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isShared()
     * @generated
     * @ordered
     */
    protected boolean shared = SHARED_EDEFAULT;

    /**
     * The cached value of the '{@link #getBindings() <em>Bindings</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBindings()
     * @generated
     * @ordered
     */
    protected EList bindings = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected StubImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return MapPackage.eINSTANCE.getStub();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isDynamic() {
        return dynamic;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDynamic(boolean newDynamic) {
        boolean oldDynamic = dynamic;
        dynamic = newDynamic;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.STUB__DYNAMIC, oldDynamic, dynamic));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isShared() {
        return shared;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setShared(boolean newShared) {
        boolean oldShared = shared;
        shared = newShared;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.STUB__SHARED, oldShared, shared));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getBindings() {
        if (bindings == null) {
            bindings = new EObjectContainmentWithInverseEList(PluginBinding.class, this, MapPackage.STUB__BINDINGS, MapPackage.PLUGIN_BINDING__STUB);
        }
        return bindings;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case MapPackage.STUB__URNLINKS:
                    return ((InternalEList)getUrnlinks()).basicAdd(otherEnd, msgs);
                case MapPackage.STUB__DIAGRAM:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, MapPackage.STUB__DIAGRAM, msgs);
                case MapPackage.STUB__CONT_REF:
                    if (contRef != null)
                        msgs = ((InternalEObject)contRef).eInverseRemove(this, UrncorePackage.IURN_CONTAINER_REF__NODES, IURNContainerRef.class, msgs);
                    return basicSetContRef((IURNContainerRef)otherEnd, msgs);
                case MapPackage.STUB__SUCC:
                    return ((InternalEList)getSucc()).basicAdd(otherEnd, msgs);
                case MapPackage.STUB__PRED:
                    return ((InternalEList)getPred()).basicAdd(otherEnd, msgs);
                case MapPackage.STUB__LABEL:
                    if (label != null)
                        msgs = ((InternalEObject)label).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MapPackage.STUB__LABEL, null, msgs);
                    return basicSetLabel((NodeLabel)otherEnd, msgs);
                case MapPackage.STUB__BINDINGS:
                    return ((InternalEList)getBindings()).basicAdd(otherEnd, msgs);
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
                case MapPackage.STUB__URNLINKS:
                    return ((InternalEList)getUrnlinks()).basicRemove(otherEnd, msgs);
                case MapPackage.STUB__DIAGRAM:
                    return eBasicSetContainer(null, MapPackage.STUB__DIAGRAM, msgs);
                case MapPackage.STUB__CONT_REF:
                    return basicSetContRef(null, msgs);
                case MapPackage.STUB__SUCC:
                    return ((InternalEList)getSucc()).basicRemove(otherEnd, msgs);
                case MapPackage.STUB__PRED:
                    return ((InternalEList)getPred()).basicRemove(otherEnd, msgs);
                case MapPackage.STUB__LABEL:
                    return basicSetLabel(null, msgs);
                case MapPackage.STUB__BINDINGS:
                    return ((InternalEList)getBindings()).basicRemove(otherEnd, msgs);
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
                case MapPackage.STUB__DIAGRAM:
                    return eContainer.eInverseRemove(this, UrncorePackage.IURN_DIAGRAM__NODES, IURNDiagram.class, msgs);
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
            case MapPackage.STUB__ID:
                return getId();
            case MapPackage.STUB__NAME:
                return getName();
            case MapPackage.STUB__DESCRIPTION:
                return getDescription();
            case MapPackage.STUB__URNLINKS:
                return getUrnlinks();
            case MapPackage.STUB__X:
                return new Integer(getX());
            case MapPackage.STUB__Y:
                return new Integer(getY());
            case MapPackage.STUB__DIAGRAM:
                return getDiagram();
            case MapPackage.STUB__CONT_REF:
                if (resolve) return getContRef();
                return basicGetContRef();
            case MapPackage.STUB__SUCC:
                return getSucc();
            case MapPackage.STUB__PRED:
                return getPred();
            case MapPackage.STUB__LABEL:
                return getLabel();
            case MapPackage.STUB__DYNAMIC:
                return isDynamic() ? Boolean.TRUE : Boolean.FALSE;
            case MapPackage.STUB__SHARED:
                return isShared() ? Boolean.TRUE : Boolean.FALSE;
            case MapPackage.STUB__BINDINGS:
                return getBindings();
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
            case MapPackage.STUB__ID:
                setId((String)newValue);
                return;
            case MapPackage.STUB__NAME:
                setName((String)newValue);
                return;
            case MapPackage.STUB__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case MapPackage.STUB__URNLINKS:
                getUrnlinks().clear();
                getUrnlinks().addAll((Collection)newValue);
                return;
            case MapPackage.STUB__X:
                setX(((Integer)newValue).intValue());
                return;
            case MapPackage.STUB__Y:
                setY(((Integer)newValue).intValue());
                return;
            case MapPackage.STUB__DIAGRAM:
                setDiagram((IURNDiagram)newValue);
                return;
            case MapPackage.STUB__CONT_REF:
                setContRef((IURNContainerRef)newValue);
                return;
            case MapPackage.STUB__SUCC:
                getSucc().clear();
                getSucc().addAll((Collection)newValue);
                return;
            case MapPackage.STUB__PRED:
                getPred().clear();
                getPred().addAll((Collection)newValue);
                return;
            case MapPackage.STUB__LABEL:
                setLabel((NodeLabel)newValue);
                return;
            case MapPackage.STUB__DYNAMIC:
                setDynamic(((Boolean)newValue).booleanValue());
                return;
            case MapPackage.STUB__SHARED:
                setShared(((Boolean)newValue).booleanValue());
                return;
            case MapPackage.STUB__BINDINGS:
                getBindings().clear();
                getBindings().addAll((Collection)newValue);
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
            case MapPackage.STUB__ID:
                setId(ID_EDEFAULT);
                return;
            case MapPackage.STUB__NAME:
                setName(NAME_EDEFAULT);
                return;
            case MapPackage.STUB__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case MapPackage.STUB__URNLINKS:
                getUrnlinks().clear();
                return;
            case MapPackage.STUB__X:
                setX(X_EDEFAULT);
                return;
            case MapPackage.STUB__Y:
                setY(Y_EDEFAULT);
                return;
            case MapPackage.STUB__DIAGRAM:
                setDiagram((IURNDiagram)null);
                return;
            case MapPackage.STUB__CONT_REF:
                setContRef((IURNContainerRef)null);
                return;
            case MapPackage.STUB__SUCC:
                getSucc().clear();
                return;
            case MapPackage.STUB__PRED:
                getPred().clear();
                return;
            case MapPackage.STUB__LABEL:
                setLabel((NodeLabel)null);
                return;
            case MapPackage.STUB__DYNAMIC:
                setDynamic(DYNAMIC_EDEFAULT);
                return;
            case MapPackage.STUB__SHARED:
                setShared(SHARED_EDEFAULT);
                return;
            case MapPackage.STUB__BINDINGS:
                getBindings().clear();
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
            case MapPackage.STUB__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case MapPackage.STUB__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case MapPackage.STUB__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case MapPackage.STUB__URNLINKS:
                return urnlinks != null && !urnlinks.isEmpty();
            case MapPackage.STUB__X:
                return x != X_EDEFAULT;
            case MapPackage.STUB__Y:
                return y != Y_EDEFAULT;
            case MapPackage.STUB__DIAGRAM:
                return getDiagram() != null;
            case MapPackage.STUB__CONT_REF:
                return contRef != null;
            case MapPackage.STUB__SUCC:
                return succ != null && !succ.isEmpty();
            case MapPackage.STUB__PRED:
                return pred != null && !pred.isEmpty();
            case MapPackage.STUB__LABEL:
                return label != null;
            case MapPackage.STUB__DYNAMIC:
                return dynamic != DYNAMIC_EDEFAULT;
            case MapPackage.STUB__SHARED:
                return shared != SHARED_EDEFAULT;
            case MapPackage.STUB__BINDINGS:
                return bindings != null && !bindings.isEmpty();
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
        result.append(" (dynamic: ");
        result.append(dynamic);
        result.append(", shared: ");
        result.append(shared);
        result.append(')');
        return result.toString();
    }

} //StubImpl
