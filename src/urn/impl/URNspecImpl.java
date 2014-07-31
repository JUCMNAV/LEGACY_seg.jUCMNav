/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urn.impl;

import asd.ASDspec;
import grl.GRLspec;
import grl.GrlPackage;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import ucm.UCMspec;
import ucm.UcmPackage;
import urn.URNlink;
import urn.URNspec;
import urn.UrnPackage;
import urncore.Metadata;
import urncore.URNdefinition;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>UR Nspec</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link urn.impl.URNspecImpl#getName <em>Name</em>}</li>
 *   <li>{@link urn.impl.URNspecImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link urn.impl.URNspecImpl#getAuthor <em>Author</em>}</li>
 *   <li>{@link urn.impl.URNspecImpl#getCreated <em>Created</em>}</li>
 *   <li>{@link urn.impl.URNspecImpl#getModified <em>Modified</em>}</li>
 *   <li>{@link urn.impl.URNspecImpl#getSpecVersion <em>Spec Version</em>}</li>
 *   <li>{@link urn.impl.URNspecImpl#getUrnVersion <em>Urn Version</em>}</li>
 *   <li>{@link urn.impl.URNspecImpl#getNextGlobalID <em>Next Global ID</em>}</li>
 *   <li>{@link urn.impl.URNspecImpl#getUcmspec <em>Ucmspec</em>}</li>
 *   <li>{@link urn.impl.URNspecImpl#getGrlspec <em>Grlspec</em>}</li>
 *   <li>{@link urn.impl.URNspecImpl#getUrndef <em>Urndef</em>}</li>
 *   <li>{@link urn.impl.URNspecImpl#getUrnLinks <em>Urn Links</em>}</li>
 *   <li>{@link urn.impl.URNspecImpl#getMetadata <em>Metadata</em>}</li>
 *   <li>{@link urn.impl.URNspecImpl#getAsdspec <em>Asdspec</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class URNspecImpl extends MinimalEObjectImpl.Container implements URNspec {
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
	 * The default value of the '{@link #getAuthor() <em>Author</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getAuthor()
	 * @generated
	 * @ordered
	 */
    protected static final String AUTHOR_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getAuthor() <em>Author</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getAuthor()
	 * @generated
	 * @ordered
	 */
    protected String author = AUTHOR_EDEFAULT;

    /**
	 * The default value of the '{@link #getCreated() <em>Created</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCreated()
	 * @generated
	 * @ordered
	 */
    protected static final String CREATED_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getCreated() <em>Created</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCreated()
	 * @generated
	 * @ordered
	 */
    protected String created = CREATED_EDEFAULT;

    /**
	 * The default value of the '{@link #getModified() <em>Modified</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getModified()
	 * @generated
	 * @ordered
	 */
    protected static final String MODIFIED_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getModified() <em>Modified</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getModified()
	 * @generated
	 * @ordered
	 */
    protected String modified = MODIFIED_EDEFAULT;

    /**
	 * The default value of the '{@link #getSpecVersion() <em>Spec Version</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getSpecVersion()
	 * @generated
	 * @ordered
	 */
    protected static final String SPEC_VERSION_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getSpecVersion() <em>Spec Version</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getSpecVersion()
	 * @generated
	 * @ordered
	 */
    protected String specVersion = SPEC_VERSION_EDEFAULT;

    /**
	 * The default value of the '{@link #getUrnVersion() <em>Urn Version</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getUrnVersion()
	 * @generated
	 * @ordered
	 */
    protected static final String URN_VERSION_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getUrnVersion() <em>Urn Version</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getUrnVersion()
	 * @generated
	 * @ordered
	 */
    protected String urnVersion = URN_VERSION_EDEFAULT;

    /**
	 * The default value of the '{@link #getNextGlobalID() <em>Next Global ID</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getNextGlobalID()
	 * @generated
	 * @ordered
	 */
    protected static final String NEXT_GLOBAL_ID_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getNextGlobalID() <em>Next Global ID</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getNextGlobalID()
	 * @generated
	 * @ordered
	 */
    protected String nextGlobalID = NEXT_GLOBAL_ID_EDEFAULT;

    /**
	 * The cached value of the '{@link #getUcmspec() <em>Ucmspec</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getUcmspec()
	 * @generated
	 * @ordered
	 */
    protected UCMspec ucmspec;

    /**
	 * The cached value of the '{@link #getGrlspec() <em>Grlspec</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getGrlspec()
	 * @generated
	 * @ordered
	 */
    protected GRLspec grlspec;

    /**
	 * The cached value of the '{@link #getUrndef() <em>Urndef</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getUrndef()
	 * @generated
	 * @ordered
	 */
    protected URNdefinition urndef;

    /**
	 * The cached value of the '{@link #getUrnLinks() <em>Urn Links</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getUrnLinks()
	 * @generated
	 * @ordered
	 */
    protected EList urnLinks;

    /**
	 * The cached value of the '{@link #getMetadata() <em>Metadata</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetadata()
	 * @generated
	 * @ordered
	 */
	protected EList metadata;

				/**
	 * The cached value of the '{@link #getAsdspec() <em>Asdspec</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAsdspec()
	 * @generated
	 * @ordered
	 */
	protected ASDspec asdspec;

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected URNspecImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return UrnPackage.Literals.UR_NSPEC;
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
			eNotify(new ENotificationImpl(this, Notification.SET, UrnPackage.UR_NSPEC__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, UrnPackage.UR_NSPEC__DESCRIPTION, oldDescription, description));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getAuthor() {
		return author;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setAuthor(String newAuthor) {
		String oldAuthor = author;
		author = newAuthor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrnPackage.UR_NSPEC__AUTHOR, oldAuthor, author));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getCreated() {
		return created;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setCreated(String newCreated) {
		String oldCreated = created;
		created = newCreated;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrnPackage.UR_NSPEC__CREATED, oldCreated, created));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getModified() {
		return modified;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setModified(String newModified) {
		String oldModified = modified;
		modified = newModified;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrnPackage.UR_NSPEC__MODIFIED, oldModified, modified));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getSpecVersion() {
		return specVersion;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setSpecVersion(String newSpecVersion) {
		String oldSpecVersion = specVersion;
		specVersion = newSpecVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrnPackage.UR_NSPEC__SPEC_VERSION, oldSpecVersion, specVersion));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getUrnVersion() {
		return urnVersion;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setUrnVersion(String newUrnVersion) {
		String oldUrnVersion = urnVersion;
		urnVersion = newUrnVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrnPackage.UR_NSPEC__URN_VERSION, oldUrnVersion, urnVersion));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getNextGlobalID() {
		return nextGlobalID;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setNextGlobalID(String newNextGlobalID) {
		String oldNextGlobalID = nextGlobalID;
		nextGlobalID = newNextGlobalID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrnPackage.UR_NSPEC__NEXT_GLOBAL_ID, oldNextGlobalID, nextGlobalID));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public UCMspec getUcmspec() {
		return ucmspec;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetUcmspec(UCMspec newUcmspec, NotificationChain msgs) {
		UCMspec oldUcmspec = ucmspec;
		ucmspec = newUcmspec;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UrnPackage.UR_NSPEC__UCMSPEC, oldUcmspec, newUcmspec);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setUcmspec(UCMspec newUcmspec) {
		if (newUcmspec != ucmspec) {
			NotificationChain msgs = null;
			if (ucmspec != null)
				msgs = ((InternalEObject)ucmspec).eInverseRemove(this, UcmPackage.UC_MSPEC__URNSPEC, UCMspec.class, msgs);
			if (newUcmspec != null)
				msgs = ((InternalEObject)newUcmspec).eInverseAdd(this, UcmPackage.UC_MSPEC__URNSPEC, UCMspec.class, msgs);
			msgs = basicSetUcmspec(newUcmspec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrnPackage.UR_NSPEC__UCMSPEC, newUcmspec, newUcmspec));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public GRLspec getGrlspec() {
		return grlspec;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetGrlspec(GRLspec newGrlspec, NotificationChain msgs) {
		GRLspec oldGrlspec = grlspec;
		grlspec = newGrlspec;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UrnPackage.UR_NSPEC__GRLSPEC, oldGrlspec, newGrlspec);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setGrlspec(GRLspec newGrlspec) {
		if (newGrlspec != grlspec) {
			NotificationChain msgs = null;
			if (grlspec != null)
				msgs = ((InternalEObject)grlspec).eInverseRemove(this, GrlPackage.GR_LSPEC__URNSPEC, GRLspec.class, msgs);
			if (newGrlspec != null)
				msgs = ((InternalEObject)newGrlspec).eInverseAdd(this, GrlPackage.GR_LSPEC__URNSPEC, GRLspec.class, msgs);
			msgs = basicSetGrlspec(newGrlspec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrnPackage.UR_NSPEC__GRLSPEC, newGrlspec, newGrlspec));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public URNdefinition getUrndef() {
		return urndef;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetUrndef(URNdefinition newUrndef, NotificationChain msgs) {
		URNdefinition oldUrndef = urndef;
		urndef = newUrndef;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UrnPackage.UR_NSPEC__URNDEF, oldUrndef, newUrndef);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setUrndef(URNdefinition newUrndef) {
		if (newUrndef != urndef) {
			NotificationChain msgs = null;
			if (urndef != null)
				msgs = ((InternalEObject)urndef).eInverseRemove(this, UrncorePackage.UR_NDEFINITION__URNSPEC, URNdefinition.class, msgs);
			if (newUrndef != null)
				msgs = ((InternalEObject)newUrndef).eInverseAdd(this, UrncorePackage.UR_NDEFINITION__URNSPEC, URNdefinition.class, msgs);
			msgs = basicSetUrndef(newUrndef, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrnPackage.UR_NSPEC__URNDEF, newUrndef, newUrndef));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getUrnLinks() {
		if (urnLinks == null) {
			urnLinks = new EObjectContainmentWithInverseEList(URNlink.class, this, UrnPackage.UR_NSPEC__URN_LINKS, UrnPackage.UR_NLINK__URNSPEC);
		}
		return urnLinks;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getMetadata() {
		if (metadata == null) {
			metadata = new EObjectContainmentEList(Metadata.class, this, UrnPackage.UR_NSPEC__METADATA);
		}
		return metadata;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ASDspec getAsdspec() {
		return asdspec;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAsdspec(ASDspec newAsdspec, NotificationChain msgs) {
		ASDspec oldAsdspec = asdspec;
		asdspec = newAsdspec;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UrnPackage.UR_NSPEC__ASDSPEC, oldAsdspec, newAsdspec);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAsdspec(ASDspec newAsdspec) {
		if (newAsdspec != asdspec) {
			NotificationChain msgs = null;
			if (asdspec != null)
				msgs = ((InternalEObject)asdspec).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UrnPackage.UR_NSPEC__ASDSPEC, null, msgs);
			if (newAsdspec != null)
				msgs = ((InternalEObject)newAsdspec).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UrnPackage.UR_NSPEC__ASDSPEC, null, msgs);
			msgs = basicSetAsdspec(newAsdspec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrnPackage.UR_NSPEC__ASDSPEC, newAsdspec, newAsdspec));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UrnPackage.UR_NSPEC__UCMSPEC:
				if (ucmspec != null)
					msgs = ((InternalEObject)ucmspec).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UrnPackage.UR_NSPEC__UCMSPEC, null, msgs);
				return basicSetUcmspec((UCMspec)otherEnd, msgs);
			case UrnPackage.UR_NSPEC__GRLSPEC:
				if (grlspec != null)
					msgs = ((InternalEObject)grlspec).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UrnPackage.UR_NSPEC__GRLSPEC, null, msgs);
				return basicSetGrlspec((GRLspec)otherEnd, msgs);
			case UrnPackage.UR_NSPEC__URNDEF:
				if (urndef != null)
					msgs = ((InternalEObject)urndef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UrnPackage.UR_NSPEC__URNDEF, null, msgs);
				return basicSetUrndef((URNdefinition)otherEnd, msgs);
			case UrnPackage.UR_NSPEC__URN_LINKS:
				return ((InternalEList)getUrnLinks()).basicAdd(otherEnd, msgs);
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
			case UrnPackage.UR_NSPEC__UCMSPEC:
				return basicSetUcmspec(null, msgs);
			case UrnPackage.UR_NSPEC__GRLSPEC:
				return basicSetGrlspec(null, msgs);
			case UrnPackage.UR_NSPEC__URNDEF:
				return basicSetUrndef(null, msgs);
			case UrnPackage.UR_NSPEC__URN_LINKS:
				return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
			case UrnPackage.UR_NSPEC__METADATA:
				return ((InternalEList)getMetadata()).basicRemove(otherEnd, msgs);
			case UrnPackage.UR_NSPEC__ASDSPEC:
				return basicSetAsdspec(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UrnPackage.UR_NSPEC__NAME:
				return getName();
			case UrnPackage.UR_NSPEC__DESCRIPTION:
				return getDescription();
			case UrnPackage.UR_NSPEC__AUTHOR:
				return getAuthor();
			case UrnPackage.UR_NSPEC__CREATED:
				return getCreated();
			case UrnPackage.UR_NSPEC__MODIFIED:
				return getModified();
			case UrnPackage.UR_NSPEC__SPEC_VERSION:
				return getSpecVersion();
			case UrnPackage.UR_NSPEC__URN_VERSION:
				return getUrnVersion();
			case UrnPackage.UR_NSPEC__NEXT_GLOBAL_ID:
				return getNextGlobalID();
			case UrnPackage.UR_NSPEC__UCMSPEC:
				return getUcmspec();
			case UrnPackage.UR_NSPEC__GRLSPEC:
				return getGrlspec();
			case UrnPackage.UR_NSPEC__URNDEF:
				return getUrndef();
			case UrnPackage.UR_NSPEC__URN_LINKS:
				return getUrnLinks();
			case UrnPackage.UR_NSPEC__METADATA:
				return getMetadata();
			case UrnPackage.UR_NSPEC__ASDSPEC:
				return getAsdspec();
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
			case UrnPackage.UR_NSPEC__NAME:
				setName((String)newValue);
				return;
			case UrnPackage.UR_NSPEC__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case UrnPackage.UR_NSPEC__AUTHOR:
				setAuthor((String)newValue);
				return;
			case UrnPackage.UR_NSPEC__CREATED:
				setCreated((String)newValue);
				return;
			case UrnPackage.UR_NSPEC__MODIFIED:
				setModified((String)newValue);
				return;
			case UrnPackage.UR_NSPEC__SPEC_VERSION:
				setSpecVersion((String)newValue);
				return;
			case UrnPackage.UR_NSPEC__URN_VERSION:
				setUrnVersion((String)newValue);
				return;
			case UrnPackage.UR_NSPEC__NEXT_GLOBAL_ID:
				setNextGlobalID((String)newValue);
				return;
			case UrnPackage.UR_NSPEC__UCMSPEC:
				setUcmspec((UCMspec)newValue);
				return;
			case UrnPackage.UR_NSPEC__GRLSPEC:
				setGrlspec((GRLspec)newValue);
				return;
			case UrnPackage.UR_NSPEC__URNDEF:
				setUrndef((URNdefinition)newValue);
				return;
			case UrnPackage.UR_NSPEC__URN_LINKS:
				getUrnLinks().clear();
				getUrnLinks().addAll((Collection)newValue);
				return;
			case UrnPackage.UR_NSPEC__METADATA:
				getMetadata().clear();
				getMetadata().addAll((Collection)newValue);
				return;
			case UrnPackage.UR_NSPEC__ASDSPEC:
				setAsdspec((ASDspec)newValue);
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
			case UrnPackage.UR_NSPEC__NAME:
				setName(NAME_EDEFAULT);
				return;
			case UrnPackage.UR_NSPEC__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case UrnPackage.UR_NSPEC__AUTHOR:
				setAuthor(AUTHOR_EDEFAULT);
				return;
			case UrnPackage.UR_NSPEC__CREATED:
				setCreated(CREATED_EDEFAULT);
				return;
			case UrnPackage.UR_NSPEC__MODIFIED:
				setModified(MODIFIED_EDEFAULT);
				return;
			case UrnPackage.UR_NSPEC__SPEC_VERSION:
				setSpecVersion(SPEC_VERSION_EDEFAULT);
				return;
			case UrnPackage.UR_NSPEC__URN_VERSION:
				setUrnVersion(URN_VERSION_EDEFAULT);
				return;
			case UrnPackage.UR_NSPEC__NEXT_GLOBAL_ID:
				setNextGlobalID(NEXT_GLOBAL_ID_EDEFAULT);
				return;
			case UrnPackage.UR_NSPEC__UCMSPEC:
				setUcmspec((UCMspec)null);
				return;
			case UrnPackage.UR_NSPEC__GRLSPEC:
				setGrlspec((GRLspec)null);
				return;
			case UrnPackage.UR_NSPEC__URNDEF:
				setUrndef((URNdefinition)null);
				return;
			case UrnPackage.UR_NSPEC__URN_LINKS:
				getUrnLinks().clear();
				return;
			case UrnPackage.UR_NSPEC__METADATA:
				getMetadata().clear();
				return;
			case UrnPackage.UR_NSPEC__ASDSPEC:
				setAsdspec((ASDspec)null);
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
			case UrnPackage.UR_NSPEC__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case UrnPackage.UR_NSPEC__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case UrnPackage.UR_NSPEC__AUTHOR:
				return AUTHOR_EDEFAULT == null ? author != null : !AUTHOR_EDEFAULT.equals(author);
			case UrnPackage.UR_NSPEC__CREATED:
				return CREATED_EDEFAULT == null ? created != null : !CREATED_EDEFAULT.equals(created);
			case UrnPackage.UR_NSPEC__MODIFIED:
				return MODIFIED_EDEFAULT == null ? modified != null : !MODIFIED_EDEFAULT.equals(modified);
			case UrnPackage.UR_NSPEC__SPEC_VERSION:
				return SPEC_VERSION_EDEFAULT == null ? specVersion != null : !SPEC_VERSION_EDEFAULT.equals(specVersion);
			case UrnPackage.UR_NSPEC__URN_VERSION:
				return URN_VERSION_EDEFAULT == null ? urnVersion != null : !URN_VERSION_EDEFAULT.equals(urnVersion);
			case UrnPackage.UR_NSPEC__NEXT_GLOBAL_ID:
				return NEXT_GLOBAL_ID_EDEFAULT == null ? nextGlobalID != null : !NEXT_GLOBAL_ID_EDEFAULT.equals(nextGlobalID);
			case UrnPackage.UR_NSPEC__UCMSPEC:
				return ucmspec != null;
			case UrnPackage.UR_NSPEC__GRLSPEC:
				return grlspec != null;
			case UrnPackage.UR_NSPEC__URNDEF:
				return urndef != null;
			case UrnPackage.UR_NSPEC__URN_LINKS:
				return urnLinks != null && !urnLinks.isEmpty();
			case UrnPackage.UR_NSPEC__METADATA:
				return metadata != null && !metadata.isEmpty();
			case UrnPackage.UR_NSPEC__ASDSPEC:
				return asdspec != null;
		}
		return super.eIsSet(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", description: ");
		result.append(description);
		result.append(", author: ");
		result.append(author);
		result.append(", created: ");
		result.append(created);
		result.append(", modified: ");
		result.append(modified);
		result.append(", specVersion: ");
		result.append(specVersion);
		result.append(", urnVersion: ");
		result.append(urnVersion);
		result.append(", nextGlobalID: ");
		result.append(nextGlobalID);
		result.append(')');
		return result.toString();
	}

} //URNspecImpl
