/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.ElementLink;
import grl.GRLspec;
import grl.GrlPackage;
import grl.IntentionalElement;
import grl.LinkRef;

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

import urncore.impl.GRLmodelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.ElementLinkImpl#getRefs <em>Refs</em>}</li>
 *   <li>{@link grl.impl.ElementLinkImpl#getGrlspec <em>Grlspec</em>}</li>
 *   <li>{@link grl.impl.ElementLinkImpl#getSrc <em>Src</em>}</li>
 *   <li>{@link grl.impl.ElementLinkImpl#getDest <em>Dest</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ElementLinkImpl extends GRLmodelElementImpl implements ElementLink {
    /**
     * The cached value of the '{@link #getRefs() <em>Refs</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRefs()
     * @generated
     * @ordered
     */
    protected EList refs = null;

    /**
     * The cached value of the '{@link #getSrc() <em>Src</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSrc()
     * @generated
     * @ordered
     */
    protected IntentionalElement src = null;

    /**
     * The cached value of the '{@link #getDest() <em>Dest</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDest()
     * @generated
     * @ordered
     */
    protected IntentionalElement dest = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ElementLinkImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return GrlPackage.eINSTANCE.getElementLink();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getRefs() {
        if (refs == null) {
            refs = new EObjectWithInverseResolvingEList(LinkRef.class, this, GrlPackage.ELEMENT_LINK__REFS, GrlPackage.LINK_REF__LINK);
        }
        return refs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GRLspec getGrlspec() {
        if (eContainerFeatureID != GrlPackage.ELEMENT_LINK__GRLSPEC) return null;
        return (GRLspec)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGrlspec(GRLspec newGrlspec) {
        if (newGrlspec != eContainer || (eContainerFeatureID != GrlPackage.ELEMENT_LINK__GRLSPEC && newGrlspec != null)) {
            if (EcoreUtil.isAncestor(this, newGrlspec))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newGrlspec != null)
                msgs = ((InternalEObject)newGrlspec).eInverseAdd(this, GrlPackage.GR_LSPEC__LINKS, GRLspec.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newGrlspec, GrlPackage.ELEMENT_LINK__GRLSPEC, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.ELEMENT_LINK__GRLSPEC, newGrlspec, newGrlspec));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntentionalElement getSrc() {
        if (src != null && src.eIsProxy()) {
            IntentionalElement oldSrc = src;
            src = (IntentionalElement)eResolveProxy((InternalEObject)src);
            if (src != oldSrc) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, GrlPackage.ELEMENT_LINK__SRC, oldSrc, src));
            }
        }
        return src;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntentionalElement basicGetSrc() {
        return src;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSrc(IntentionalElement newSrc, NotificationChain msgs) {
        IntentionalElement oldSrc = src;
        src = newSrc;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.ELEMENT_LINK__SRC, oldSrc, newSrc);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSrc(IntentionalElement newSrc) {
        if (newSrc != src) {
            NotificationChain msgs = null;
            if (src != null)
                msgs = ((InternalEObject)src).eInverseRemove(this, GrlPackage.INTENTIONAL_ELEMENT__LINKS_SRC, IntentionalElement.class, msgs);
            if (newSrc != null)
                msgs = ((InternalEObject)newSrc).eInverseAdd(this, GrlPackage.INTENTIONAL_ELEMENT__LINKS_SRC, IntentionalElement.class, msgs);
            msgs = basicSetSrc(newSrc, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.ELEMENT_LINK__SRC, newSrc, newSrc));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntentionalElement getDest() {
        if (dest != null && dest.eIsProxy()) {
            IntentionalElement oldDest = dest;
            dest = (IntentionalElement)eResolveProxy((InternalEObject)dest);
            if (dest != oldDest) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, GrlPackage.ELEMENT_LINK__DEST, oldDest, dest));
            }
        }
        return dest;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntentionalElement basicGetDest() {
        return dest;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDest(IntentionalElement newDest, NotificationChain msgs) {
        IntentionalElement oldDest = dest;
        dest = newDest;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.ELEMENT_LINK__DEST, oldDest, newDest);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDest(IntentionalElement newDest) {
        if (newDest != dest) {
            NotificationChain msgs = null;
            if (dest != null)
                msgs = ((InternalEObject)dest).eInverseRemove(this, GrlPackage.INTENTIONAL_ELEMENT__LINKS_DEST, IntentionalElement.class, msgs);
            if (newDest != null)
                msgs = ((InternalEObject)newDest).eInverseAdd(this, GrlPackage.INTENTIONAL_ELEMENT__LINKS_DEST, IntentionalElement.class, msgs);
            msgs = basicSetDest(newDest, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.ELEMENT_LINK__DEST, newDest, newDest));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case GrlPackage.ELEMENT_LINK__URNLINKS:
                    return ((InternalEList)getUrnlinks()).basicAdd(otherEnd, msgs);
                case GrlPackage.ELEMENT_LINK__REFS:
                    return ((InternalEList)getRefs()).basicAdd(otherEnd, msgs);
                case GrlPackage.ELEMENT_LINK__GRLSPEC:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, GrlPackage.ELEMENT_LINK__GRLSPEC, msgs);
                case GrlPackage.ELEMENT_LINK__SRC:
                    if (src != null)
                        msgs = ((InternalEObject)src).eInverseRemove(this, GrlPackage.INTENTIONAL_ELEMENT__LINKS_SRC, IntentionalElement.class, msgs);
                    return basicSetSrc((IntentionalElement)otherEnd, msgs);
                case GrlPackage.ELEMENT_LINK__DEST:
                    if (dest != null)
                        msgs = ((InternalEObject)dest).eInverseRemove(this, GrlPackage.INTENTIONAL_ELEMENT__LINKS_DEST, IntentionalElement.class, msgs);
                    return basicSetDest((IntentionalElement)otherEnd, msgs);
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
                case GrlPackage.ELEMENT_LINK__URNLINKS:
                    return ((InternalEList)getUrnlinks()).basicRemove(otherEnd, msgs);
                case GrlPackage.ELEMENT_LINK__REFS:
                    return ((InternalEList)getRefs()).basicRemove(otherEnd, msgs);
                case GrlPackage.ELEMENT_LINK__GRLSPEC:
                    return eBasicSetContainer(null, GrlPackage.ELEMENT_LINK__GRLSPEC, msgs);
                case GrlPackage.ELEMENT_LINK__SRC:
                    return basicSetSrc(null, msgs);
                case GrlPackage.ELEMENT_LINK__DEST:
                    return basicSetDest(null, msgs);
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
                case GrlPackage.ELEMENT_LINK__GRLSPEC:
                    return eContainer.eInverseRemove(this, GrlPackage.GR_LSPEC__LINKS, GRLspec.class, msgs);
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
            case GrlPackage.ELEMENT_LINK__ID:
                return getId();
            case GrlPackage.ELEMENT_LINK__NAME:
                return getName();
            case GrlPackage.ELEMENT_LINK__DESCRIPTION:
                return getDescription();
            case GrlPackage.ELEMENT_LINK__URNLINKS:
                return getUrnlinks();
            case GrlPackage.ELEMENT_LINK__REFS:
                return getRefs();
            case GrlPackage.ELEMENT_LINK__GRLSPEC:
                return getGrlspec();
            case GrlPackage.ELEMENT_LINK__SRC:
                if (resolve) return getSrc();
                return basicGetSrc();
            case GrlPackage.ELEMENT_LINK__DEST:
                if (resolve) return getDest();
                return basicGetDest();
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
            case GrlPackage.ELEMENT_LINK__ID:
                setId((String)newValue);
                return;
            case GrlPackage.ELEMENT_LINK__NAME:
                setName((String)newValue);
                return;
            case GrlPackage.ELEMENT_LINK__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case GrlPackage.ELEMENT_LINK__URNLINKS:
                getUrnlinks().clear();
                getUrnlinks().addAll((Collection)newValue);
                return;
            case GrlPackage.ELEMENT_LINK__REFS:
                getRefs().clear();
                getRefs().addAll((Collection)newValue);
                return;
            case GrlPackage.ELEMENT_LINK__GRLSPEC:
                setGrlspec((GRLspec)newValue);
                return;
            case GrlPackage.ELEMENT_LINK__SRC:
                setSrc((IntentionalElement)newValue);
                return;
            case GrlPackage.ELEMENT_LINK__DEST:
                setDest((IntentionalElement)newValue);
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
            case GrlPackage.ELEMENT_LINK__ID:
                setId(ID_EDEFAULT);
                return;
            case GrlPackage.ELEMENT_LINK__NAME:
                setName(NAME_EDEFAULT);
                return;
            case GrlPackage.ELEMENT_LINK__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case GrlPackage.ELEMENT_LINK__URNLINKS:
                getUrnlinks().clear();
                return;
            case GrlPackage.ELEMENT_LINK__REFS:
                getRefs().clear();
                return;
            case GrlPackage.ELEMENT_LINK__GRLSPEC:
                setGrlspec((GRLspec)null);
                return;
            case GrlPackage.ELEMENT_LINK__SRC:
                setSrc((IntentionalElement)null);
                return;
            case GrlPackage.ELEMENT_LINK__DEST:
                setDest((IntentionalElement)null);
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
            case GrlPackage.ELEMENT_LINK__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case GrlPackage.ELEMENT_LINK__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case GrlPackage.ELEMENT_LINK__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case GrlPackage.ELEMENT_LINK__URNLINKS:
                return urnlinks != null && !urnlinks.isEmpty();
            case GrlPackage.ELEMENT_LINK__REFS:
                return refs != null && !refs.isEmpty();
            case GrlPackage.ELEMENT_LINK__GRLSPEC:
                return getGrlspec() != null;
            case GrlPackage.ELEMENT_LINK__SRC:
                return src != null;
            case GrlPackage.ELEMENT_LINK__DEST:
                return dest != null;
        }
        return eDynamicIsSet(eFeature);
    }

} //ElementLinkImpl
