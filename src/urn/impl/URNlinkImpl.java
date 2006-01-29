/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urn.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import urn.URNlink;
import urn.URNspec;
import urn.UrnPackage;
import urncore.URNmodelElement;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>UR Nlink</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link urn.impl.URNlinkImpl#getId <em>Id</em>}</li>
 *   <li>{@link urn.impl.URNlinkImpl#getName <em>Name</em>}</li>
 *   <li>{@link urn.impl.URNlinkImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link urn.impl.URNlinkImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link urn.impl.URNlinkImpl#getUrnspec <em>Urnspec</em>}</li>
 *   <li>{@link urn.impl.URNlinkImpl#getFromElems <em>From Elems</em>}</li>
 *   <li>{@link urn.impl.URNlinkImpl#getToElems <em>To Elems</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class URNlinkImpl extends EObjectImpl implements URNlink {
    /**
     * The default value of the '{@link #getId() <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
    protected static final String ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getId()
     * @generated
     * @ordered
     */
    protected String id = ID_EDEFAULT;

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected static final String DESCRIPTION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected String description = DESCRIPTION_EDEFAULT;

    /**
     * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getKind()
     * @generated
     * @ordered
     */
    protected static final String KIND_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getKind()
     * @generated
     * @ordered
     */
    protected String kind = KIND_EDEFAULT;

    /**
     * The cached value of the '{@link #getFromElems() <em>From Elems</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFromElems()
     * @generated
     * @ordered
     */
    protected EList fromElems = null;

    /**
     * The cached value of the '{@link #getToElems() <em>To Elems</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getToElems()
     * @generated
     * @ordered
     */
    protected EList toElems = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected URNlinkImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return UrnPackage.eINSTANCE.getURNlink();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getId() {
        return id;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setId(String newId) {
        String oldId = id;
        id = newId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UrnPackage.UR_NLINK__ID, oldId, id));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UrnPackage.UR_NLINK__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDescription() {
        return description;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDescription(String newDescription) {
        String oldDescription = description;
        description = newDescription;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UrnPackage.UR_NLINK__DESCRIPTION, oldDescription, description));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getKind() {
        return kind;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setKind(String newKind) {
        String oldKind = kind;
        kind = newKind;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UrnPackage.UR_NLINK__KIND, oldKind, kind));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public URNspec getUrnspec() {
        if (eContainerFeatureID != UrnPackage.UR_NLINK__URNSPEC) return null;
        return (URNspec)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUrnspec(URNspec newUrnspec) {
        if (newUrnspec != eContainer || (eContainerFeatureID != UrnPackage.UR_NLINK__URNSPEC && newUrnspec != null)) {
            if (EcoreUtil.isAncestor(this, newUrnspec))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newUrnspec != null)
                msgs = ((InternalEObject)newUrnspec).eInverseAdd(this, UrnPackage.UR_NSPEC__URN_LINKS, URNspec.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newUrnspec, UrnPackage.UR_NLINK__URNSPEC, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UrnPackage.UR_NLINK__URNSPEC, newUrnspec, newUrnspec));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getFromElems() {
        if (fromElems == null) {
            fromElems = new EObjectWithInverseResolvingEList.ManyInverse(URNmodelElement.class, this, UrnPackage.UR_NLINK__FROM_ELEMS, UrncorePackage.UR_NMODEL_ELEMENT__FROM_LINKS);
        }
        return fromElems;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getToElems() {
        if (toElems == null) {
            toElems = new EObjectWithInverseResolvingEList.ManyInverse(URNmodelElement.class, this, UrnPackage.UR_NLINK__TO_ELEMS, UrncorePackage.UR_NMODEL_ELEMENT__TO_LINKS);
        }
        return toElems;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case UrnPackage.UR_NLINK__URNSPEC:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, UrnPackage.UR_NLINK__URNSPEC, msgs);
                case UrnPackage.UR_NLINK__FROM_ELEMS:
                    return ((InternalEList)getFromElems()).basicAdd(otherEnd, msgs);
                case UrnPackage.UR_NLINK__TO_ELEMS:
                    return ((InternalEList)getToElems()).basicAdd(otherEnd, msgs);
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
                case UrnPackage.UR_NLINK__URNSPEC:
                    return eBasicSetContainer(null, UrnPackage.UR_NLINK__URNSPEC, msgs);
                case UrnPackage.UR_NLINK__FROM_ELEMS:
                    return ((InternalEList)getFromElems()).basicRemove(otherEnd, msgs);
                case UrnPackage.UR_NLINK__TO_ELEMS:
                    return ((InternalEList)getToElems()).basicRemove(otherEnd, msgs);
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
                case UrnPackage.UR_NLINK__URNSPEC:
                    return eContainer.eInverseRemove(this, UrnPackage.UR_NSPEC__URN_LINKS, URNspec.class, msgs);
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
            case UrnPackage.UR_NLINK__ID:
                return getId();
            case UrnPackage.UR_NLINK__NAME:
                return getName();
            case UrnPackage.UR_NLINK__DESCRIPTION:
                return getDescription();
            case UrnPackage.UR_NLINK__KIND:
                return getKind();
            case UrnPackage.UR_NLINK__URNSPEC:
                return getUrnspec();
            case UrnPackage.UR_NLINK__FROM_ELEMS:
                return getFromElems();
            case UrnPackage.UR_NLINK__TO_ELEMS:
                return getToElems();
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
            case UrnPackage.UR_NLINK__ID:
                setId((String)newValue);
                return;
            case UrnPackage.UR_NLINK__NAME:
                setName((String)newValue);
                return;
            case UrnPackage.UR_NLINK__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case UrnPackage.UR_NLINK__KIND:
                setKind((String)newValue);
                return;
            case UrnPackage.UR_NLINK__URNSPEC:
                setUrnspec((URNspec)newValue);
                return;
            case UrnPackage.UR_NLINK__FROM_ELEMS:
                getFromElems().clear();
                getFromElems().addAll((Collection)newValue);
                return;
            case UrnPackage.UR_NLINK__TO_ELEMS:
                getToElems().clear();
                getToElems().addAll((Collection)newValue);
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
            case UrnPackage.UR_NLINK__ID:
                setId(ID_EDEFAULT);
                return;
            case UrnPackage.UR_NLINK__NAME:
                setName(NAME_EDEFAULT);
                return;
            case UrnPackage.UR_NLINK__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case UrnPackage.UR_NLINK__KIND:
                setKind(KIND_EDEFAULT);
                return;
            case UrnPackage.UR_NLINK__URNSPEC:
                setUrnspec((URNspec)null);
                return;
            case UrnPackage.UR_NLINK__FROM_ELEMS:
                getFromElems().clear();
                return;
            case UrnPackage.UR_NLINK__TO_ELEMS:
                getToElems().clear();
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
            case UrnPackage.UR_NLINK__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case UrnPackage.UR_NLINK__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case UrnPackage.UR_NLINK__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case UrnPackage.UR_NLINK__KIND:
                return KIND_EDEFAULT == null ? kind != null : !KIND_EDEFAULT.equals(kind);
            case UrnPackage.UR_NLINK__URNSPEC:
                return getUrnspec() != null;
            case UrnPackage.UR_NLINK__FROM_ELEMS:
                return fromElems != null && !fromElems.isEmpty();
            case UrnPackage.UR_NLINK__TO_ELEMS:
                return toElems != null && !toElems.isEmpty();
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
        result.append(" (id: ");
        result.append(id);
        result.append(", name: ");
        result.append(name);
        result.append(", description: ");
        result.append(description);
        result.append(", kind: ");
        result.append(kind);
        result.append(')');
        return result.toString();
    }

} //URNlinkImpl
