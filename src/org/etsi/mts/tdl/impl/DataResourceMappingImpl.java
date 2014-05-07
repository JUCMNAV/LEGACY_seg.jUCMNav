/**
 */
package org.etsi.mts.tdl.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.etsi.mts.tdl.DataResourceMapping;
import org.etsi.mts.tdl.TdlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Resource Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.impl.DataResourceMappingImpl#getResourceURI <em>Resource URI</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataResourceMappingImpl extends PackageableElementImpl implements DataResourceMapping {
	/**
	 * The default value of the '{@link #getResourceURI() <em>Resource URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceURI()
	 * @generated
	 * @ordered
	 */
	protected static final String RESOURCE_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getResourceURI() <em>Resource URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceURI()
	 * @generated
	 * @ordered
	 */
	protected String resourceURI = RESOURCE_URI_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataResourceMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TdlPackage.Literals.DATA_RESOURCE_MAPPING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getResourceURI() {
		return resourceURI;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResourceURI(String newResourceURI) {
		String oldResourceURI = resourceURI;
		resourceURI = newResourceURI;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TdlPackage.DATA_RESOURCE_MAPPING__RESOURCE_URI, oldResourceURI, resourceURI));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TdlPackage.DATA_RESOURCE_MAPPING__RESOURCE_URI:
				return getResourceURI();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TdlPackage.DATA_RESOURCE_MAPPING__RESOURCE_URI:
				setResourceURI((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case TdlPackage.DATA_RESOURCE_MAPPING__RESOURCE_URI:
				setResourceURI(RESOURCE_URI_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TdlPackage.DATA_RESOURCE_MAPPING__RESOURCE_URI:
				return RESOURCE_URI_EDEFAULT == null ? resourceURI != null : !RESOURCE_URI_EDEFAULT.equals(resourceURI);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (resourceURI: ");
		result.append(resourceURI);
		result.append(')');
		return result.toString();
	}

} //DataResourceMappingImpl
