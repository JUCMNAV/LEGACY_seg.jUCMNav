/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urn.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

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
 *   <li>{@link urn.impl.URNlinkImpl#getUrnspec <em>Urnspec</em>}</li>
 *   <li>{@link urn.impl.URNlinkImpl#getFromElem <em>From Elem</em>}</li>
 *   <li>{@link urn.impl.URNlinkImpl#getToElem <em>To Elem</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class URNlinkImpl extends EObjectImpl implements URNlink {
    /**
     * The cached value of the '{@link #getFromElem() <em>From Elem</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFromElem()
     * @generated
     * @ordered
     */
    protected URNmodelElement fromElem = null;

    /**
     * The cached value of the '{@link #getToElem() <em>To Elem</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getToElem()
     * @generated
     * @ordered
     */
    protected URNmodelElement toElem = null;

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
    public URNmodelElement getFromElem() {
        if (fromElem != null && fromElem.eIsProxy()) {
            URNmodelElement oldFromElem = fromElem;
            fromElem = (URNmodelElement)eResolveProxy((InternalEObject)fromElem);
            if (fromElem != oldFromElem) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, UrnPackage.UR_NLINK__FROM_ELEM, oldFromElem, fromElem));
            }
        }
        return fromElem;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public URNmodelElement basicGetFromElem() {
        return fromElem;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetFromElem(URNmodelElement newFromElem, NotificationChain msgs) {
        URNmodelElement oldFromElem = fromElem;
        fromElem = newFromElem;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UrnPackage.UR_NLINK__FROM_ELEM, oldFromElem, newFromElem);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFromElem(URNmodelElement newFromElem) {
        if (newFromElem != fromElem) {
            NotificationChain msgs = null;
            if (fromElem != null)
                msgs = ((InternalEObject)fromElem).eInverseRemove(this, UrncorePackage.UR_NMODEL_ELEMENT__FROM_LINKS, URNmodelElement.class, msgs);
            if (newFromElem != null)
                msgs = ((InternalEObject)newFromElem).eInverseAdd(this, UrncorePackage.UR_NMODEL_ELEMENT__FROM_LINKS, URNmodelElement.class, msgs);
            msgs = basicSetFromElem(newFromElem, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UrnPackage.UR_NLINK__FROM_ELEM, newFromElem, newFromElem));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public URNmodelElement getToElem() {
        if (toElem != null && toElem.eIsProxy()) {
            URNmodelElement oldToElem = toElem;
            toElem = (URNmodelElement)eResolveProxy((InternalEObject)toElem);
            if (toElem != oldToElem) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, UrnPackage.UR_NLINK__TO_ELEM, oldToElem, toElem));
            }
        }
        return toElem;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public URNmodelElement basicGetToElem() {
        return toElem;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetToElem(URNmodelElement newToElem, NotificationChain msgs) {
        URNmodelElement oldToElem = toElem;
        toElem = newToElem;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UrnPackage.UR_NLINK__TO_ELEM, oldToElem, newToElem);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setToElem(URNmodelElement newToElem) {
        if (newToElem != toElem) {
            NotificationChain msgs = null;
            if (toElem != null)
                msgs = ((InternalEObject)toElem).eInverseRemove(this, UrncorePackage.UR_NMODEL_ELEMENT__TO_LINKS, URNmodelElement.class, msgs);
            if (newToElem != null)
                msgs = ((InternalEObject)newToElem).eInverseAdd(this, UrncorePackage.UR_NMODEL_ELEMENT__TO_LINKS, URNmodelElement.class, msgs);
            msgs = basicSetToElem(newToElem, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UrnPackage.UR_NLINK__TO_ELEM, newToElem, newToElem));
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
                case UrnPackage.UR_NLINK__FROM_ELEM:
                    if (fromElem != null)
                        msgs = ((InternalEObject)fromElem).eInverseRemove(this, UrncorePackage.UR_NMODEL_ELEMENT__FROM_LINKS, URNmodelElement.class, msgs);
                    return basicSetFromElem((URNmodelElement)otherEnd, msgs);
                case UrnPackage.UR_NLINK__TO_ELEM:
                    if (toElem != null)
                        msgs = ((InternalEObject)toElem).eInverseRemove(this, UrncorePackage.UR_NMODEL_ELEMENT__TO_LINKS, URNmodelElement.class, msgs);
                    return basicSetToElem((URNmodelElement)otherEnd, msgs);
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
                case UrnPackage.UR_NLINK__FROM_ELEM:
                    return basicSetFromElem(null, msgs);
                case UrnPackage.UR_NLINK__TO_ELEM:
                    return basicSetToElem(null, msgs);
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
            case UrnPackage.UR_NLINK__URNSPEC:
                return getUrnspec();
            case UrnPackage.UR_NLINK__FROM_ELEM:
                if (resolve) return getFromElem();
                return basicGetFromElem();
            case UrnPackage.UR_NLINK__TO_ELEM:
                if (resolve) return getToElem();
                return basicGetToElem();
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
            case UrnPackage.UR_NLINK__URNSPEC:
                setUrnspec((URNspec)newValue);
                return;
            case UrnPackage.UR_NLINK__FROM_ELEM:
                setFromElem((URNmodelElement)newValue);
                return;
            case UrnPackage.UR_NLINK__TO_ELEM:
                setToElem((URNmodelElement)newValue);
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
            case UrnPackage.UR_NLINK__URNSPEC:
                setUrnspec((URNspec)null);
                return;
            case UrnPackage.UR_NLINK__FROM_ELEM:
                setFromElem((URNmodelElement)null);
                return;
            case UrnPackage.UR_NLINK__TO_ELEM:
                setToElem((URNmodelElement)null);
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
            case UrnPackage.UR_NLINK__URNSPEC:
                return getUrnspec() != null;
            case UrnPackage.UR_NLINK__FROM_ELEM:
                return fromElem != null;
            case UrnPackage.UR_NLINK__TO_ELEM:
                return toElem != null;
        }
        return eDynamicIsSet(eFeature);
    }

} //URNlinkImpl
