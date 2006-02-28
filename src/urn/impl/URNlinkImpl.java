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
import urncore.GRLmodelElement;
import urncore.UCMmodelElement;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>UR Nlink</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link urn.impl.URNlinkImpl#getUrnspec <em>Urnspec</em>}</li>
 *   <li>{@link urn.impl.URNlinkImpl#getGrlModelElements <em>Grl Model Elements</em>}</li>
 *   <li>{@link urn.impl.URNlinkImpl#getUcmModelElements <em>Ucm Model Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class URNlinkImpl extends EObjectImpl implements URNlink {
    /**
     * The cached value of the '{@link #getGrlModelElements() <em>Grl Model Elements</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getGrlModelElements()
     * @generated
     * @ordered
     */
    protected GRLmodelElement grlModelElements = null;

    /**
     * The cached value of the '{@link #getUcmModelElements() <em>Ucm Model Elements</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUcmModelElements()
     * @generated
     * @ordered
     */
    protected UCMmodelElement ucmModelElements = null;

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
    public GRLmodelElement getGrlModelElements() {
        if (grlModelElements != null && grlModelElements.eIsProxy()) {
            GRLmodelElement oldGrlModelElements = grlModelElements;
            grlModelElements = (GRLmodelElement)eResolveProxy((InternalEObject)grlModelElements);
            if (grlModelElements != oldGrlModelElements) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, UrnPackage.UR_NLINK__GRL_MODEL_ELEMENTS, oldGrlModelElements, grlModelElements));
            }
        }
        return grlModelElements;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GRLmodelElement basicGetGrlModelElements() {
        return grlModelElements;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetGrlModelElements(GRLmodelElement newGrlModelElements, NotificationChain msgs) {
        GRLmodelElement oldGrlModelElements = grlModelElements;
        grlModelElements = newGrlModelElements;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UrnPackage.UR_NLINK__GRL_MODEL_ELEMENTS, oldGrlModelElements, newGrlModelElements);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGrlModelElements(GRLmodelElement newGrlModelElements) {
        if (newGrlModelElements != grlModelElements) {
            NotificationChain msgs = null;
            if (grlModelElements != null)
                msgs = ((InternalEObject)grlModelElements).eInverseRemove(this, UrncorePackage.GR_LMODEL_ELEMENT__URNLINKS, GRLmodelElement.class, msgs);
            if (newGrlModelElements != null)
                msgs = ((InternalEObject)newGrlModelElements).eInverseAdd(this, UrncorePackage.GR_LMODEL_ELEMENT__URNLINKS, GRLmodelElement.class, msgs);
            msgs = basicSetGrlModelElements(newGrlModelElements, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UrnPackage.UR_NLINK__GRL_MODEL_ELEMENTS, newGrlModelElements, newGrlModelElements));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UCMmodelElement getUcmModelElements() {
        if (ucmModelElements != null && ucmModelElements.eIsProxy()) {
            UCMmodelElement oldUcmModelElements = ucmModelElements;
            ucmModelElements = (UCMmodelElement)eResolveProxy((InternalEObject)ucmModelElements);
            if (ucmModelElements != oldUcmModelElements) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, UrnPackage.UR_NLINK__UCM_MODEL_ELEMENTS, oldUcmModelElements, ucmModelElements));
            }
        }
        return ucmModelElements;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UCMmodelElement basicGetUcmModelElements() {
        return ucmModelElements;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetUcmModelElements(UCMmodelElement newUcmModelElements, NotificationChain msgs) {
        UCMmodelElement oldUcmModelElements = ucmModelElements;
        ucmModelElements = newUcmModelElements;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UrnPackage.UR_NLINK__UCM_MODEL_ELEMENTS, oldUcmModelElements, newUcmModelElements);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUcmModelElements(UCMmodelElement newUcmModelElements) {
        if (newUcmModelElements != ucmModelElements) {
            NotificationChain msgs = null;
            if (ucmModelElements != null)
                msgs = ((InternalEObject)ucmModelElements).eInverseRemove(this, UrncorePackage.UC_MMODEL_ELEMENT__URNLINKS, UCMmodelElement.class, msgs);
            if (newUcmModelElements != null)
                msgs = ((InternalEObject)newUcmModelElements).eInverseAdd(this, UrncorePackage.UC_MMODEL_ELEMENT__URNLINKS, UCMmodelElement.class, msgs);
            msgs = basicSetUcmModelElements(newUcmModelElements, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UrnPackage.UR_NLINK__UCM_MODEL_ELEMENTS, newUcmModelElements, newUcmModelElements));
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
                case UrnPackage.UR_NLINK__GRL_MODEL_ELEMENTS:
                    if (grlModelElements != null)
                        msgs = ((InternalEObject)grlModelElements).eInverseRemove(this, UrncorePackage.GR_LMODEL_ELEMENT__URNLINKS, GRLmodelElement.class, msgs);
                    return basicSetGrlModelElements((GRLmodelElement)otherEnd, msgs);
                case UrnPackage.UR_NLINK__UCM_MODEL_ELEMENTS:
                    if (ucmModelElements != null)
                        msgs = ((InternalEObject)ucmModelElements).eInverseRemove(this, UrncorePackage.UC_MMODEL_ELEMENT__URNLINKS, UCMmodelElement.class, msgs);
                    return basicSetUcmModelElements((UCMmodelElement)otherEnd, msgs);
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
                case UrnPackage.UR_NLINK__GRL_MODEL_ELEMENTS:
                    return basicSetGrlModelElements(null, msgs);
                case UrnPackage.UR_NLINK__UCM_MODEL_ELEMENTS:
                    return basicSetUcmModelElements(null, msgs);
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
            case UrnPackage.UR_NLINK__GRL_MODEL_ELEMENTS:
                if (resolve) return getGrlModelElements();
                return basicGetGrlModelElements();
            case UrnPackage.UR_NLINK__UCM_MODEL_ELEMENTS:
                if (resolve) return getUcmModelElements();
                return basicGetUcmModelElements();
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
            case UrnPackage.UR_NLINK__GRL_MODEL_ELEMENTS:
                setGrlModelElements((GRLmodelElement)newValue);
                return;
            case UrnPackage.UR_NLINK__UCM_MODEL_ELEMENTS:
                setUcmModelElements((UCMmodelElement)newValue);
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
            case UrnPackage.UR_NLINK__GRL_MODEL_ELEMENTS:
                setGrlModelElements((GRLmodelElement)null);
                return;
            case UrnPackage.UR_NLINK__UCM_MODEL_ELEMENTS:
                setUcmModelElements((UCMmodelElement)null);
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
            case UrnPackage.UR_NLINK__GRL_MODEL_ELEMENTS:
                return grlModelElements != null;
            case UrnPackage.UR_NLINK__UCM_MODEL_ELEMENTS:
                return ucmModelElements != null;
        }
        return eDynamicIsSet(eFeature);
    }

} //URNlinkImpl
