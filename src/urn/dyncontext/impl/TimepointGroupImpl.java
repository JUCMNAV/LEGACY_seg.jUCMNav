/**
 */
package urn.dyncontext.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import urn.URNspec;
import urn.UrnPackage;

import urn.dyncontext.DyncontextPackage;
import urn.dyncontext.Timepoint;
import urn.dyncontext.TimepointGroup;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Timepoint Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link urn.dyncontext.impl.TimepointGroupImpl#getTimepoints <em>Timepoints</em>}</li>
 *   <li>{@link urn.dyncontext.impl.TimepointGroupImpl#getUrnspec <em>Urnspec</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TimepointGroupImpl extends MinimalEObjectImpl.Container implements TimepointGroup {
	/**
	 * The cached value of the '{@link #getTimepoints() <em>Timepoints</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimepoints()
	 * @generated
	 * @ordered
	 */
	protected EList timepoints;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TimepointGroupImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DyncontextPackage.Literals.TIMEPOINT_GROUP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getTimepoints() {
		if (timepoints == null) {
			timepoints = new EObjectContainmentWithInverseEList(Timepoint.class, this, DyncontextPackage.TIMEPOINT_GROUP__TIMEPOINTS, DyncontextPackage.TIMEPOINT__GROUP);
		}
		return timepoints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public URNspec getUrnspec() {
		if (eContainerFeatureID() != DyncontextPackage.TIMEPOINT_GROUP__URNSPEC) return null;
		return (URNspec)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUrnspec(URNspec newUrnspec, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newUrnspec, DyncontextPackage.TIMEPOINT_GROUP__URNSPEC, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUrnspec(URNspec newUrnspec) {
		if (newUrnspec != eInternalContainer() || (eContainerFeatureID() != DyncontextPackage.TIMEPOINT_GROUP__URNSPEC && newUrnspec != null)) {
			if (EcoreUtil.isAncestor(this, newUrnspec))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newUrnspec != null)
				msgs = ((InternalEObject)newUrnspec).eInverseAdd(this, UrnPackage.UR_NSPEC__TIMEPOINT_GROUPS, URNspec.class, msgs);
			msgs = basicSetUrnspec(newUrnspec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DyncontextPackage.TIMEPOINT_GROUP__URNSPEC, newUrnspec, newUrnspec));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DyncontextPackage.TIMEPOINT_GROUP__TIMEPOINTS:
				return ((InternalEList)getTimepoints()).basicAdd(otherEnd, msgs);
			case DyncontextPackage.TIMEPOINT_GROUP__URNSPEC:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetUrnspec((URNspec)otherEnd, msgs);
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
			case DyncontextPackage.TIMEPOINT_GROUP__TIMEPOINTS:
				return ((InternalEList)getTimepoints()).basicRemove(otherEnd, msgs);
			case DyncontextPackage.TIMEPOINT_GROUP__URNSPEC:
				return basicSetUrnspec(null, msgs);
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
			case DyncontextPackage.TIMEPOINT_GROUP__URNSPEC:
				return eInternalContainer().eInverseRemove(this, UrnPackage.UR_NSPEC__TIMEPOINT_GROUPS, URNspec.class, msgs);
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
			case DyncontextPackage.TIMEPOINT_GROUP__TIMEPOINTS:
				return getTimepoints();
			case DyncontextPackage.TIMEPOINT_GROUP__URNSPEC:
				return getUrnspec();
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
			case DyncontextPackage.TIMEPOINT_GROUP__TIMEPOINTS:
				getTimepoints().clear();
				getTimepoints().addAll((Collection)newValue);
				return;
			case DyncontextPackage.TIMEPOINT_GROUP__URNSPEC:
				setUrnspec((URNspec)newValue);
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
			case DyncontextPackage.TIMEPOINT_GROUP__TIMEPOINTS:
				getTimepoints().clear();
				return;
			case DyncontextPackage.TIMEPOINT_GROUP__URNSPEC:
				setUrnspec((URNspec)null);
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
			case DyncontextPackage.TIMEPOINT_GROUP__TIMEPOINTS:
				return timepoints != null && !timepoints.isEmpty();
			case DyncontextPackage.TIMEPOINT_GROUP__URNSPEC:
				return getUrnspec() != null;
		}
		return super.eIsSet(featureID);
	}

} //TimepointGroupImpl
