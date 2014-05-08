/**
 */
package core.impl;

import core.COREMapping;
import core.COREModelElement;
import core.CorePackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CORE Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link core.impl.COREMappingImpl#getMappedTo <em>Mapped To</em>}</li>
 *   <li>{@link core.impl.COREMappingImpl#getMappedFrom <em>Mapped From</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class COREMappingImpl extends EObjectImpl implements COREMapping {
	/**
	 * The cached value of the '{@link #getMappedTo() <em>Mapped To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMappedTo()
	 * @generated
	 * @ordered
	 */
	protected COREModelElement mappedTo;

	/**
	 * The cached value of the '{@link #getMappedFrom() <em>Mapped From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMappedFrom()
	 * @generated
	 * @ordered
	 */
	protected COREModelElement mappedFrom;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected COREMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return CorePackage.Literals.CORE_MAPPING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public COREModelElement getMappedTo() {
		if (mappedTo != null && mappedTo.eIsProxy()) {
			InternalEObject oldMappedTo = (InternalEObject)mappedTo;
			mappedTo = (COREModelElement)eResolveProxy(oldMappedTo);
			if (mappedTo != oldMappedTo) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CorePackage.CORE_MAPPING__MAPPED_TO, oldMappedTo, mappedTo));
			}
		}
		return mappedTo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public COREModelElement basicGetMappedTo() {
		return mappedTo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMappedTo(COREModelElement newMappedTo) {
		COREModelElement oldMappedTo = mappedTo;
		mappedTo = newMappedTo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.CORE_MAPPING__MAPPED_TO, oldMappedTo, mappedTo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public COREModelElement getMappedFrom() {
		if (mappedFrom != null && mappedFrom.eIsProxy()) {
			InternalEObject oldMappedFrom = (InternalEObject)mappedFrom;
			mappedFrom = (COREModelElement)eResolveProxy(oldMappedFrom);
			if (mappedFrom != oldMappedFrom) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CorePackage.CORE_MAPPING__MAPPED_FROM, oldMappedFrom, mappedFrom));
			}
		}
		return mappedFrom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public COREModelElement basicGetMappedFrom() {
		return mappedFrom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMappedFrom(COREModelElement newMappedFrom) {
		COREModelElement oldMappedFrom = mappedFrom;
		mappedFrom = newMappedFrom;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.CORE_MAPPING__MAPPED_FROM, oldMappedFrom, mappedFrom));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CorePackage.CORE_MAPPING__MAPPED_TO:
				if (resolve) return getMappedTo();
				return basicGetMappedTo();
			case CorePackage.CORE_MAPPING__MAPPED_FROM:
				if (resolve) return getMappedFrom();
				return basicGetMappedFrom();
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
			case CorePackage.CORE_MAPPING__MAPPED_TO:
				setMappedTo((COREModelElement)newValue);
				return;
			case CorePackage.CORE_MAPPING__MAPPED_FROM:
				setMappedFrom((COREModelElement)newValue);
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
			case CorePackage.CORE_MAPPING__MAPPED_TO:
				setMappedTo((COREModelElement)null);
				return;
			case CorePackage.CORE_MAPPING__MAPPED_FROM:
				setMappedFrom((COREModelElement)null);
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
			case CorePackage.CORE_MAPPING__MAPPED_TO:
				return mappedTo != null;
			case CorePackage.CORE_MAPPING__MAPPED_FROM:
				return mappedFrom != null;
		}
		return super.eIsSet(featureID);
	}

} //COREMappingImpl
