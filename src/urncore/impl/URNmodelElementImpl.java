/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore.impl;

import ca.mcgill.sel.core.impl.CORENamedElementImpl;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import urn.URNlink;
import urn.UrnPackage;
import urncore.Concern;
import urncore.Metadata;
import urncore.URNmodelElement;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>UR Nmodel Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link urncore.impl.URNmodelElementImpl#getFromLinks <em>From Links</em>}</li>
 *   <li>{@link urncore.impl.URNmodelElementImpl#getToLinks <em>To Links</em>}</li>
 *   <li>{@link urncore.impl.URNmodelElementImpl#getId <em>Id</em>}</li>
 *   <li>{@link urncore.impl.URNmodelElementImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link urncore.impl.URNmodelElementImpl#getMetadata <em>Metadata</em>}</li>
 *   <li>{@link urncore.impl.URNmodelElementImpl#getInconcern <em>Inconcern</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class URNmodelElementImpl extends CORENamedElementImpl implements URNmodelElement {
    /**
	 * The cached value of the '{@link #getFromLinks() <em>From Links</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getFromLinks()
	 * @generated
	 * @ordered
	 */
    protected EList fromLinks;

    /**
	 * The cached value of the '{@link #getToLinks() <em>To Links</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getToLinks()
	 * @generated
	 * @ordered
	 */
    protected EList toLinks;

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
	 * The cached value of the '{@link #getMetadata() <em>Metadata</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetadata()
	 * @generated
	 * @ordered
	 */
	protected EList metadata;

    /**
	 * The cached value of the '{@link #getInconcern() <em>Inconcern</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getInconcern()
	 * @generated
	 * @ordered
	 */
    protected Concern inconcern;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected URNmodelElementImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return UrncorePackage.Literals.UR_NMODEL_ELEMENT;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getFromLinks() {
		if (fromLinks == null) {
			fromLinks = new EObjectWithInverseResolvingEList(URNlink.class, this, UrncorePackage.UR_NMODEL_ELEMENT__FROM_LINKS, UrnPackage.UR_NLINK__FROM_ELEM);
		}
		return fromLinks;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getToLinks() {
		if (toLinks == null) {
			toLinks = new EObjectWithInverseResolvingEList(URNlink.class, this, UrncorePackage.UR_NMODEL_ELEMENT__TO_LINKS, UrnPackage.UR_NLINK__TO_ELEM);
		}
		return toLinks;
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
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.UR_NMODEL_ELEMENT__ID, oldId, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.UR_NMODEL_ELEMENT__DESCRIPTION, oldDescription, description));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getMetadata() {
		if (metadata == null) {
			metadata = new EObjectContainmentEList(Metadata.class, this, UrncorePackage.UR_NMODEL_ELEMENT__METADATA);
		}
		return metadata;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Concern getInconcern() {
		if (inconcern != null && inconcern.eIsProxy()) {
			InternalEObject oldInconcern = (InternalEObject)inconcern;
			inconcern = (Concern)eResolveProxy(oldInconcern);
			if (inconcern != oldInconcern) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UrncorePackage.UR_NMODEL_ELEMENT__INCONCERN, oldInconcern, inconcern));
			}
		}
		return inconcern;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Concern basicGetInconcern() {
		return inconcern;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetInconcern(Concern newInconcern, NotificationChain msgs) {
		Concern oldInconcern = inconcern;
		inconcern = newInconcern;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UrncorePackage.UR_NMODEL_ELEMENT__INCONCERN, oldInconcern, newInconcern);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setInconcern(Concern newInconcern) {
		if (newInconcern != inconcern) {
			NotificationChain msgs = null;
			if (inconcern != null)
				msgs = ((InternalEObject)inconcern).eInverseRemove(this, UrncorePackage.CONCERN__ELEMENTS, Concern.class, msgs);
			if (newInconcern != null)
				msgs = ((InternalEObject)newInconcern).eInverseAdd(this, UrncorePackage.CONCERN__ELEMENTS, Concern.class, msgs);
			msgs = basicSetInconcern(newInconcern, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.UR_NMODEL_ELEMENT__INCONCERN, newInconcern, newInconcern));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UrncorePackage.UR_NMODEL_ELEMENT__FROM_LINKS:
				return ((InternalEList)getFromLinks()).basicAdd(otherEnd, msgs);
			case UrncorePackage.UR_NMODEL_ELEMENT__TO_LINKS:
				return ((InternalEList)getToLinks()).basicAdd(otherEnd, msgs);
			case UrncorePackage.UR_NMODEL_ELEMENT__INCONCERN:
				if (inconcern != null)
					msgs = ((InternalEObject)inconcern).eInverseRemove(this, UrncorePackage.CONCERN__ELEMENTS, Concern.class, msgs);
				return basicSetInconcern((Concern)otherEnd, msgs);
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
			case UrncorePackage.UR_NMODEL_ELEMENT__FROM_LINKS:
				return ((InternalEList)getFromLinks()).basicRemove(otherEnd, msgs);
			case UrncorePackage.UR_NMODEL_ELEMENT__TO_LINKS:
				return ((InternalEList)getToLinks()).basicRemove(otherEnd, msgs);
			case UrncorePackage.UR_NMODEL_ELEMENT__METADATA:
				return ((InternalEList)getMetadata()).basicRemove(otherEnd, msgs);
			case UrncorePackage.UR_NMODEL_ELEMENT__INCONCERN:
				return basicSetInconcern(null, msgs);
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
			case UrncorePackage.UR_NMODEL_ELEMENT__FROM_LINKS:
				return getFromLinks();
			case UrncorePackage.UR_NMODEL_ELEMENT__TO_LINKS:
				return getToLinks();
			case UrncorePackage.UR_NMODEL_ELEMENT__ID:
				return getId();
			case UrncorePackage.UR_NMODEL_ELEMENT__DESCRIPTION:
				return getDescription();
			case UrncorePackage.UR_NMODEL_ELEMENT__METADATA:
				return getMetadata();
			case UrncorePackage.UR_NMODEL_ELEMENT__INCONCERN:
				if (resolve) return getInconcern();
				return basicGetInconcern();
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
			case UrncorePackage.UR_NMODEL_ELEMENT__FROM_LINKS:
				getFromLinks().clear();
				getFromLinks().addAll((Collection)newValue);
				return;
			case UrncorePackage.UR_NMODEL_ELEMENT__TO_LINKS:
				getToLinks().clear();
				getToLinks().addAll((Collection)newValue);
				return;
			case UrncorePackage.UR_NMODEL_ELEMENT__ID:
				setId((String)newValue);
				return;
			case UrncorePackage.UR_NMODEL_ELEMENT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case UrncorePackage.UR_NMODEL_ELEMENT__METADATA:
				getMetadata().clear();
				getMetadata().addAll((Collection)newValue);
				return;
			case UrncorePackage.UR_NMODEL_ELEMENT__INCONCERN:
				setInconcern((Concern)newValue);
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
			case UrncorePackage.UR_NMODEL_ELEMENT__FROM_LINKS:
				getFromLinks().clear();
				return;
			case UrncorePackage.UR_NMODEL_ELEMENT__TO_LINKS:
				getToLinks().clear();
				return;
			case UrncorePackage.UR_NMODEL_ELEMENT__ID:
				setId(ID_EDEFAULT);
				return;
			case UrncorePackage.UR_NMODEL_ELEMENT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case UrncorePackage.UR_NMODEL_ELEMENT__METADATA:
				getMetadata().clear();
				return;
			case UrncorePackage.UR_NMODEL_ELEMENT__INCONCERN:
				setInconcern((Concern)null);
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
			case UrncorePackage.UR_NMODEL_ELEMENT__FROM_LINKS:
				return fromLinks != null && !fromLinks.isEmpty();
			case UrncorePackage.UR_NMODEL_ELEMENT__TO_LINKS:
				return toLinks != null && !toLinks.isEmpty();
			case UrncorePackage.UR_NMODEL_ELEMENT__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case UrncorePackage.UR_NMODEL_ELEMENT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case UrncorePackage.UR_NMODEL_ELEMENT__METADATA:
				return metadata != null && !metadata.isEmpty();
			case UrncorePackage.UR_NMODEL_ELEMENT__INCONCERN:
				return inconcern != null;
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
		result.append(" (id: ");
		result.append(id);
		result.append(", description: ");
		result.append(description);
		result.append(')');
		return result.toString();
	}

} //URNmodelElementImpl
