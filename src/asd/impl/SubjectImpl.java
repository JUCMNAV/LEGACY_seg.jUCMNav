/**
 */
package asd.impl;

import asd.ASDspec;
import asd.AsdPackage;
import asd.Community;
import asd.Subject;

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

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Subject</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link asd.impl.SubjectImpl#getAsdSpec <em>Asd Spec</em>}</li>
 *   <li>{@link asd.impl.SubjectImpl#getMemberOf <em>Member Of</em>}</li>
 *   <li>{@link asd.impl.SubjectImpl#getObjects <em>Objects</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SubjectImpl extends MediatedElementImpl implements Subject {
	/**
	 * The cached value of the '{@link #getMemberOf() <em>Member Of</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMemberOf()
	 * @generated
	 * @ordered
	 */
	protected Community memberOf;

	/**
	 * The cached value of the '{@link #getObjects() <em>Objects</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getObjects()
	 * @generated
	 * @ordered
	 */
	protected EList objects;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SubjectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return AsdPackage.Literals.SUBJECT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ASDspec getAsdSpec() {
		if (eContainerFeatureID() != AsdPackage.SUBJECT__ASD_SPEC) return null;
		return (ASDspec)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAsdSpec(ASDspec newAsdSpec, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newAsdSpec, AsdPackage.SUBJECT__ASD_SPEC, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAsdSpec(ASDspec newAsdSpec) {
		if (newAsdSpec != eInternalContainer() || (eContainerFeatureID() != AsdPackage.SUBJECT__ASD_SPEC && newAsdSpec != null)) {
			if (EcoreUtil.isAncestor(this, newAsdSpec))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newAsdSpec != null)
				msgs = ((InternalEObject)newAsdSpec).eInverseAdd(this, AsdPackage.AS_DSPEC__SUBJECTS, ASDspec.class, msgs);
			msgs = basicSetAsdSpec(newAsdSpec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsdPackage.SUBJECT__ASD_SPEC, newAsdSpec, newAsdSpec));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Community getMemberOf() {
		if (memberOf != null && memberOf.eIsProxy()) {
			InternalEObject oldMemberOf = (InternalEObject)memberOf;
			memberOf = (Community)eResolveProxy(oldMemberOf);
			if (memberOf != oldMemberOf) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AsdPackage.SUBJECT__MEMBER_OF, oldMemberOf, memberOf));
			}
		}
		return memberOf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Community basicGetMemberOf() {
		return memberOf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMemberOf(Community newMemberOf, NotificationChain msgs) {
		Community oldMemberOf = memberOf;
		memberOf = newMemberOf;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AsdPackage.SUBJECT__MEMBER_OF, oldMemberOf, newMemberOf);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMemberOf(Community newMemberOf) {
		if (newMemberOf != memberOf) {
			NotificationChain msgs = null;
			if (memberOf != null)
				msgs = ((InternalEObject)memberOf).eInverseRemove(this, AsdPackage.COMMUNITY__SUBJECT, Community.class, msgs);
			if (newMemberOf != null)
				msgs = ((InternalEObject)newMemberOf).eInverseAdd(this, AsdPackage.COMMUNITY__SUBJECT, Community.class, msgs);
			msgs = basicSetMemberOf(newMemberOf, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsdPackage.SUBJECT__MEMBER_OF, newMemberOf, newMemberOf));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getObjects() {
		if (objects == null) {
			objects = new EObjectWithInverseResolvingEList.ManyInverse(asd.Object.class, this, AsdPackage.SUBJECT__OBJECTS, AsdPackage.OBJECT__SUBJECTS);
		}
		return objects;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AsdPackage.SUBJECT__ASD_SPEC:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetAsdSpec((ASDspec)otherEnd, msgs);
			case AsdPackage.SUBJECT__MEMBER_OF:
				if (memberOf != null)
					msgs = ((InternalEObject)memberOf).eInverseRemove(this, AsdPackage.COMMUNITY__SUBJECT, Community.class, msgs);
				return basicSetMemberOf((Community)otherEnd, msgs);
			case AsdPackage.SUBJECT__OBJECTS:
				return ((InternalEList)getObjects()).basicAdd(otherEnd, msgs);
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
			case AsdPackage.SUBJECT__ASD_SPEC:
				return basicSetAsdSpec(null, msgs);
			case AsdPackage.SUBJECT__MEMBER_OF:
				return basicSetMemberOf(null, msgs);
			case AsdPackage.SUBJECT__OBJECTS:
				return ((InternalEList)getObjects()).basicRemove(otherEnd, msgs);
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
			case AsdPackage.SUBJECT__ASD_SPEC:
				return eInternalContainer().eInverseRemove(this, AsdPackage.AS_DSPEC__SUBJECTS, ASDspec.class, msgs);
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
			case AsdPackage.SUBJECT__ASD_SPEC:
				return getAsdSpec();
			case AsdPackage.SUBJECT__MEMBER_OF:
				if (resolve) return getMemberOf();
				return basicGetMemberOf();
			case AsdPackage.SUBJECT__OBJECTS:
				return getObjects();
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
			case AsdPackage.SUBJECT__ASD_SPEC:
				setAsdSpec((ASDspec)newValue);
				return;
			case AsdPackage.SUBJECT__MEMBER_OF:
				setMemberOf((Community)newValue);
				return;
			case AsdPackage.SUBJECT__OBJECTS:
				getObjects().clear();
				getObjects().addAll((Collection)newValue);
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
			case AsdPackage.SUBJECT__ASD_SPEC:
				setAsdSpec((ASDspec)null);
				return;
			case AsdPackage.SUBJECT__MEMBER_OF:
				setMemberOf((Community)null);
				return;
			case AsdPackage.SUBJECT__OBJECTS:
				getObjects().clear();
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
			case AsdPackage.SUBJECT__ASD_SPEC:
				return getAsdSpec() != null;
			case AsdPackage.SUBJECT__MEMBER_OF:
				return memberOf != null;
			case AsdPackage.SUBJECT__OBJECTS:
				return objects != null && !objects.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //SubjectImpl
