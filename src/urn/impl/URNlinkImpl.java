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
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import urn.URNlink;
import urn.URNspec;
import urn.UrnPackage;
import urncore.Metadata;
import urncore.URNmodelElement;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>UR Nlink</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link urn.impl.URNlinkImpl#getType <em>Type</em>}</li>
 *   <li>{@link urn.impl.URNlinkImpl#getUrnspec <em>Urnspec</em>}</li>
 *   <li>{@link urn.impl.URNlinkImpl#getFromElem <em>From Elem</em>}</li>
 *   <li>{@link urn.impl.URNlinkImpl#getToElem <em>To Elem</em>}</li>
 *   <li>{@link urn.impl.URNlinkImpl#getMetadata <em>Metadata</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class URNlinkImpl extends MinimalEObjectImpl.Container implements URNlink {
    /**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
    protected static final String TYPE_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
    protected String type = TYPE_EDEFAULT;

    /**
	 * The cached value of the '{@link #getFromElem() <em>From Elem</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getFromElem()
	 * @generated
	 * @ordered
	 */
    protected URNmodelElement fromElem;

    /**
	 * The cached value of the '{@link #getToElem() <em>To Elem</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getToElem()
	 * @generated
	 * @ordered
	 */
    protected URNmodelElement toElem;

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
		return UrnPackage.Literals.UR_NLINK;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getType() {
		return type;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrnPackage.UR_NLINK__TYPE, oldType, type));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public URNspec getUrnspec() {
		if (eContainerFeatureID() != UrnPackage.UR_NLINK__URNSPEC) return null;
		return (URNspec)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUrnspec(URNspec newUrnspec, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newUrnspec, UrnPackage.UR_NLINK__URNSPEC, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setUrnspec(URNspec newUrnspec) {
		if (newUrnspec != eInternalContainer() || (eContainerFeatureID() != UrnPackage.UR_NLINK__URNSPEC && newUrnspec != null)) {
			if (EcoreUtil.isAncestor(this, newUrnspec))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newUrnspec != null)
				msgs = ((InternalEObject)newUrnspec).eInverseAdd(this, UrnPackage.UR_NSPEC__URN_LINKS, URNspec.class, msgs);
			msgs = basicSetUrnspec(newUrnspec, msgs);
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
			InternalEObject oldFromElem = (InternalEObject)fromElem;
			fromElem = (URNmodelElement)eResolveProxy(oldFromElem);
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
			InternalEObject oldToElem = (InternalEObject)toElem;
			toElem = (URNmodelElement)eResolveProxy(oldToElem);
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
	public EList getMetadata() {
		if (metadata == null) {
			metadata = new EObjectContainmentEList(Metadata.class, this, UrnPackage.UR_NLINK__METADATA);
		}
		return metadata;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UrnPackage.UR_NLINK__URNSPEC:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetUrnspec((URNspec)otherEnd, msgs);
			case UrnPackage.UR_NLINK__FROM_ELEM:
				if (fromElem != null)
					msgs = ((InternalEObject)fromElem).eInverseRemove(this, UrncorePackage.UR_NMODEL_ELEMENT__FROM_LINKS, URNmodelElement.class, msgs);
				return basicSetFromElem((URNmodelElement)otherEnd, msgs);
			case UrnPackage.UR_NLINK__TO_ELEM:
				if (toElem != null)
					msgs = ((InternalEObject)toElem).eInverseRemove(this, UrncorePackage.UR_NMODEL_ELEMENT__TO_LINKS, URNmodelElement.class, msgs);
				return basicSetToElem((URNmodelElement)otherEnd, msgs);
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
			case UrnPackage.UR_NLINK__URNSPEC:
				return basicSetUrnspec(null, msgs);
			case UrnPackage.UR_NLINK__FROM_ELEM:
				return basicSetFromElem(null, msgs);
			case UrnPackage.UR_NLINK__TO_ELEM:
				return basicSetToElem(null, msgs);
			case UrnPackage.UR_NLINK__METADATA:
				return ((InternalEList)getMetadata()).basicRemove(otherEnd, msgs);
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
			case UrnPackage.UR_NLINK__URNSPEC:
				return eInternalContainer().eInverseRemove(this, UrnPackage.UR_NSPEC__URN_LINKS, URNspec.class, msgs);
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
			case UrnPackage.UR_NLINK__TYPE:
				return getType();
			case UrnPackage.UR_NLINK__URNSPEC:
				return getUrnspec();
			case UrnPackage.UR_NLINK__FROM_ELEM:
				if (resolve) return getFromElem();
				return basicGetFromElem();
			case UrnPackage.UR_NLINK__TO_ELEM:
				if (resolve) return getToElem();
				return basicGetToElem();
			case UrnPackage.UR_NLINK__METADATA:
				return getMetadata();
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
			case UrnPackage.UR_NLINK__TYPE:
				setType((String)newValue);
				return;
			case UrnPackage.UR_NLINK__URNSPEC:
				setUrnspec((URNspec)newValue);
				return;
			case UrnPackage.UR_NLINK__FROM_ELEM:
				setFromElem((URNmodelElement)newValue);
				return;
			case UrnPackage.UR_NLINK__TO_ELEM:
				setToElem((URNmodelElement)newValue);
				return;
			case UrnPackage.UR_NLINK__METADATA:
				getMetadata().clear();
				getMetadata().addAll((Collection)newValue);
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
			case UrnPackage.UR_NLINK__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case UrnPackage.UR_NLINK__URNSPEC:
				setUrnspec((URNspec)null);
				return;
			case UrnPackage.UR_NLINK__FROM_ELEM:
				setFromElem((URNmodelElement)null);
				return;
			case UrnPackage.UR_NLINK__TO_ELEM:
				setToElem((URNmodelElement)null);
				return;
			case UrnPackage.UR_NLINK__METADATA:
				getMetadata().clear();
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
			case UrnPackage.UR_NLINK__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case UrnPackage.UR_NLINK__URNSPEC:
				return getUrnspec() != null;
			case UrnPackage.UR_NLINK__FROM_ELEM:
				return fromElem != null;
			case UrnPackage.UR_NLINK__TO_ELEM:
				return toElem != null;
			case UrnPackage.UR_NLINK__METADATA:
				return metadata != null && !metadata.isEmpty();
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
		result.append(" (type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}
    
} //URNlinkImpl
