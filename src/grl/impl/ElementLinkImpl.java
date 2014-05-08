/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.ElementLink;
import grl.GRLLinkableElement;
import grl.GRLspec;
import grl.GrlPackage;
import grl.LinkRef;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
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
 *   <li>{@link grl.impl.ElementLinkImpl#getDest <em>Dest</em>}</li>
 *   <li>{@link grl.impl.ElementLinkImpl#getSrc <em>Src</em>}</li>
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
    protected EList refs;

    /**
	 * The cached value of the '{@link #getDest() <em>Dest</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDest()
	 * @generated
	 * @ordered
	 */
    protected GRLLinkableElement dest;

				/**
	 * The cached value of the '{@link #getSrc() <em>Src</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getSrc()
	 * @generated
	 * @ordered
	 */
    protected GRLLinkableElement src;

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
		return GrlPackage.Literals.ELEMENT_LINK;
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
		if (eContainerFeatureID() != GrlPackage.ELEMENT_LINK__GRLSPEC) return null;
		return (GRLspec)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGrlspec(GRLspec newGrlspec, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newGrlspec, GrlPackage.ELEMENT_LINK__GRLSPEC, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setGrlspec(GRLspec newGrlspec) {
		if (newGrlspec != eInternalContainer() || (eContainerFeatureID() != GrlPackage.ELEMENT_LINK__GRLSPEC && newGrlspec != null)) {
			if (EcoreUtil.isAncestor(this, newGrlspec))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newGrlspec != null)
				msgs = ((InternalEObject)newGrlspec).eInverseAdd(this, GrlPackage.GR_LSPEC__LINKS, GRLspec.class, msgs);
			msgs = basicSetGrlspec(newGrlspec, msgs);
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
    public GRLLinkableElement getSrc() {
		if (src != null && src.eIsProxy()) {
			InternalEObject oldSrc = (InternalEObject)src;
			src = (GRLLinkableElement)eResolveProxy(oldSrc);
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
    public GRLLinkableElement basicGetSrc() {
		return src;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSrc(GRLLinkableElement newSrc, NotificationChain msgs) {
		GRLLinkableElement oldSrc = src;
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
	public void setSrc(GRLLinkableElement newSrc) {
		if (newSrc != src) {
			NotificationChain msgs = null;
			if (src != null)
				msgs = ((InternalEObject)src).eInverseRemove(this, GrlPackage.GRL_LINKABLE_ELEMENT__LINKS_SRC, GRLLinkableElement.class, msgs);
			if (newSrc != null)
				msgs = ((InternalEObject)newSrc).eInverseAdd(this, GrlPackage.GRL_LINKABLE_ELEMENT__LINKS_SRC, GRLLinkableElement.class, msgs);
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
    public GRLLinkableElement getDest() {
		if (dest != null && dest.eIsProxy()) {
			InternalEObject oldDest = (InternalEObject)dest;
			dest = (GRLLinkableElement)eResolveProxy(oldDest);
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
    public GRLLinkableElement basicGetDest() {
		return dest;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDest(GRLLinkableElement newDest, NotificationChain msgs) {
		GRLLinkableElement oldDest = dest;
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
	public void setDest(GRLLinkableElement newDest) {
		if (newDest != dest) {
			NotificationChain msgs = null;
			if (dest != null)
				msgs = ((InternalEObject)dest).eInverseRemove(this, GrlPackage.GRL_LINKABLE_ELEMENT__LINKS_DEST, GRLLinkableElement.class, msgs);
			if (newDest != null)
				msgs = ((InternalEObject)newDest).eInverseAdd(this, GrlPackage.GRL_LINKABLE_ELEMENT__LINKS_DEST, GRLLinkableElement.class, msgs);
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
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GrlPackage.ELEMENT_LINK__REFS:
				return ((InternalEList)getRefs()).basicAdd(otherEnd, msgs);
			case GrlPackage.ELEMENT_LINK__GRLSPEC:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetGrlspec((GRLspec)otherEnd, msgs);
			case GrlPackage.ELEMENT_LINK__DEST:
				if (dest != null)
					msgs = ((InternalEObject)dest).eInverseRemove(this, GrlPackage.GRL_LINKABLE_ELEMENT__LINKS_DEST, GRLLinkableElement.class, msgs);
				return basicSetDest((GRLLinkableElement)otherEnd, msgs);
			case GrlPackage.ELEMENT_LINK__SRC:
				if (src != null)
					msgs = ((InternalEObject)src).eInverseRemove(this, GrlPackage.GRL_LINKABLE_ELEMENT__LINKS_SRC, GRLLinkableElement.class, msgs);
				return basicSetSrc((GRLLinkableElement)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GrlPackage.ELEMENT_LINK__REFS:
				return ((InternalEList)getRefs()).basicRemove(otherEnd, msgs);
			case GrlPackage.ELEMENT_LINK__GRLSPEC:
				return basicSetGrlspec(null, msgs);
			case GrlPackage.ELEMENT_LINK__DEST:
				return basicSetDest(null, msgs);
			case GrlPackage.ELEMENT_LINK__SRC:
				return basicSetSrc(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case GrlPackage.ELEMENT_LINK__GRLSPEC:
				return eInternalContainer().eInverseRemove(this, GrlPackage.GR_LSPEC__LINKS, GRLspec.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GrlPackage.ELEMENT_LINK__REFS:
				return getRefs();
			case GrlPackage.ELEMENT_LINK__GRLSPEC:
				return getGrlspec();
			case GrlPackage.ELEMENT_LINK__DEST:
				if (resolve) return getDest();
				return basicGetDest();
			case GrlPackage.ELEMENT_LINK__SRC:
				if (resolve) return getSrc();
				return basicGetSrc();
		}
		return super.eGet(featureID, resolve, coreType);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case GrlPackage.ELEMENT_LINK__REFS:
				getRefs().clear();
				getRefs().addAll((Collection)newValue);
				return;
			case GrlPackage.ELEMENT_LINK__GRLSPEC:
				setGrlspec((GRLspec)newValue);
				return;
			case GrlPackage.ELEMENT_LINK__DEST:
				setDest((GRLLinkableElement)newValue);
				return;
			case GrlPackage.ELEMENT_LINK__SRC:
				setSrc((GRLLinkableElement)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case GrlPackage.ELEMENT_LINK__REFS:
				getRefs().clear();
				return;
			case GrlPackage.ELEMENT_LINK__GRLSPEC:
				setGrlspec((GRLspec)null);
				return;
			case GrlPackage.ELEMENT_LINK__DEST:
				setDest((GRLLinkableElement)null);
				return;
			case GrlPackage.ELEMENT_LINK__SRC:
				setSrc((GRLLinkableElement)null);
				return;
		}
		super.eUnset(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case GrlPackage.ELEMENT_LINK__REFS:
				return refs != null && !refs.isEmpty();
			case GrlPackage.ELEMENT_LINK__GRLSPEC:
				return getGrlspec() != null;
			case GrlPackage.ELEMENT_LINK__DEST:
				return dest != null;
			case GrlPackage.ELEMENT_LINK__SRC:
				return src != null;
		}
		return super.eIsSet(featureID);
	}

} //ElementLinkImpl
