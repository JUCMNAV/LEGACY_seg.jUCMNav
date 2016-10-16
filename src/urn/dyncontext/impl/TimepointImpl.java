/**
 */
package urn.dyncontext.impl;

import java.util.Date;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import urn.dyncontext.DyncontextPackage;
import urn.dyncontext.Timepoint;
import urn.dyncontext.TimepointGroup;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Timepoint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link urn.dyncontext.impl.TimepointImpl#getTimepoint <em>Timepoint</em>}</li>
 *   <li>{@link urn.dyncontext.impl.TimepointImpl#getGroup <em>Group</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TimepointImpl extends MinimalEObjectImpl.Container implements Timepoint {
	/**
	 * The default value of the '{@link #getTimepoint() <em>Timepoint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimepoint()
	 * @generated
	 * @ordered
	 */
	protected static final Date TIMEPOINT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTimepoint() <em>Timepoint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimepoint()
	 * @generated
	 * @ordered
	 */
	protected Date timepoint = TIMEPOINT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TimepointImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DyncontextPackage.Literals.TIMEPOINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Date getTimepoint() {
		return timepoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimepoint(Date newTimepoint) {
		Date oldTimepoint = timepoint;
		timepoint = newTimepoint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DyncontextPackage.TIMEPOINT__TIMEPOINT, oldTimepoint, timepoint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimepointGroup getGroup() {
		if (eContainerFeatureID() != DyncontextPackage.TIMEPOINT__GROUP) return null;
		return (TimepointGroup)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGroup(TimepointGroup newGroup, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newGroup, DyncontextPackage.TIMEPOINT__GROUP, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroup(TimepointGroup newGroup) {
		if (newGroup != eInternalContainer() || (eContainerFeatureID() != DyncontextPackage.TIMEPOINT__GROUP && newGroup != null)) {
			if (EcoreUtil.isAncestor(this, newGroup))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newGroup != null)
				msgs = ((InternalEObject)newGroup).eInverseAdd(this, DyncontextPackage.TIMEPOINT_GROUP__TIMEPOINTS, TimepointGroup.class, msgs);
			msgs = basicSetGroup(newGroup, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DyncontextPackage.TIMEPOINT__GROUP, newGroup, newGroup));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DyncontextPackage.TIMEPOINT__GROUP:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetGroup((TimepointGroup)otherEnd, msgs);
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
			case DyncontextPackage.TIMEPOINT__GROUP:
				return basicSetGroup(null, msgs);
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
			case DyncontextPackage.TIMEPOINT__GROUP:
				return eInternalContainer().eInverseRemove(this, DyncontextPackage.TIMEPOINT_GROUP__TIMEPOINTS, TimepointGroup.class, msgs);
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
			case DyncontextPackage.TIMEPOINT__TIMEPOINT:
				return getTimepoint();
			case DyncontextPackage.TIMEPOINT__GROUP:
				return getGroup();
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
			case DyncontextPackage.TIMEPOINT__TIMEPOINT:
				setTimepoint((Date)newValue);
				return;
			case DyncontextPackage.TIMEPOINT__GROUP:
				setGroup((TimepointGroup)newValue);
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
			case DyncontextPackage.TIMEPOINT__TIMEPOINT:
				setTimepoint(TIMEPOINT_EDEFAULT);
				return;
			case DyncontextPackage.TIMEPOINT__GROUP:
				setGroup((TimepointGroup)null);
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
			case DyncontextPackage.TIMEPOINT__TIMEPOINT:
				return TIMEPOINT_EDEFAULT == null ? timepoint != null : !TIMEPOINT_EDEFAULT.equals(timepoint);
			case DyncontextPackage.TIMEPOINT__GROUP:
				return getGroup() != null;
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
		result.append(" (timepoint: ");
		result.append(timepoint);
		result.append(')');
		return result.toString();
	}

} //TimepointImpl
