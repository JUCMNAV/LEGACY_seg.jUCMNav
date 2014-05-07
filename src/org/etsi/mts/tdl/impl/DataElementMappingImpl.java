/**
 */
package org.etsi.mts.tdl.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.etsi.mts.tdl.DataElementMapping;
import org.etsi.mts.tdl.DataResourceMapping;
import org.etsi.mts.tdl.MappableDataElement;
import org.etsi.mts.tdl.TdlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Element Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.impl.DataElementMappingImpl#getMappableDataElement <em>Mappable Data Element</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.impl.DataElementMappingImpl#getElementURI <em>Element URI</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.impl.DataElementMappingImpl#getDataResourceMapping <em>Data Resource Mapping</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataElementMappingImpl extends PackageableElementImpl implements DataElementMapping {
	/**
	 * The cached value of the '{@link #getMappableDataElement() <em>Mappable Data Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMappableDataElement()
	 * @generated
	 * @ordered
	 */
	protected MappableDataElement mappableDataElement;

	/**
	 * The default value of the '{@link #getElementURI() <em>Element URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementURI()
	 * @generated
	 * @ordered
	 */
	protected static final String ELEMENT_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getElementURI() <em>Element URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementURI()
	 * @generated
	 * @ordered
	 */
	protected String elementURI = ELEMENT_URI_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDataResourceMapping() <em>Data Resource Mapping</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataResourceMapping()
	 * @generated
	 * @ordered
	 */
	protected DataResourceMapping dataResourceMapping;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataElementMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TdlPackage.Literals.DATA_ELEMENT_MAPPING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MappableDataElement getMappableDataElement() {
		if (mappableDataElement != null && mappableDataElement.eIsProxy()) {
			InternalEObject oldMappableDataElement = (InternalEObject)mappableDataElement;
			mappableDataElement = (MappableDataElement)eResolveProxy(oldMappableDataElement);
			if (mappableDataElement != oldMappableDataElement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TdlPackage.DATA_ELEMENT_MAPPING__MAPPABLE_DATA_ELEMENT, oldMappableDataElement, mappableDataElement));
			}
		}
		return mappableDataElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MappableDataElement basicGetMappableDataElement() {
		return mappableDataElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMappableDataElement(MappableDataElement newMappableDataElement) {
		MappableDataElement oldMappableDataElement = mappableDataElement;
		mappableDataElement = newMappableDataElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TdlPackage.DATA_ELEMENT_MAPPING__MAPPABLE_DATA_ELEMENT, oldMappableDataElement, mappableDataElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getElementURI() {
		return elementURI;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElementURI(String newElementURI) {
		String oldElementURI = elementURI;
		elementURI = newElementURI;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TdlPackage.DATA_ELEMENT_MAPPING__ELEMENT_URI, oldElementURI, elementURI));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataResourceMapping getDataResourceMapping() {
		if (dataResourceMapping != null && dataResourceMapping.eIsProxy()) {
			InternalEObject oldDataResourceMapping = (InternalEObject)dataResourceMapping;
			dataResourceMapping = (DataResourceMapping)eResolveProxy(oldDataResourceMapping);
			if (dataResourceMapping != oldDataResourceMapping) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TdlPackage.DATA_ELEMENT_MAPPING__DATA_RESOURCE_MAPPING, oldDataResourceMapping, dataResourceMapping));
			}
		}
		return dataResourceMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataResourceMapping basicGetDataResourceMapping() {
		return dataResourceMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataResourceMapping(DataResourceMapping newDataResourceMapping) {
		DataResourceMapping oldDataResourceMapping = dataResourceMapping;
		dataResourceMapping = newDataResourceMapping;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TdlPackage.DATA_ELEMENT_MAPPING__DATA_RESOURCE_MAPPING, oldDataResourceMapping, dataResourceMapping));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TdlPackage.DATA_ELEMENT_MAPPING__MAPPABLE_DATA_ELEMENT:
				if (resolve) return getMappableDataElement();
				return basicGetMappableDataElement();
			case TdlPackage.DATA_ELEMENT_MAPPING__ELEMENT_URI:
				return getElementURI();
			case TdlPackage.DATA_ELEMENT_MAPPING__DATA_RESOURCE_MAPPING:
				if (resolve) return getDataResourceMapping();
				return basicGetDataResourceMapping();
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
			case TdlPackage.DATA_ELEMENT_MAPPING__MAPPABLE_DATA_ELEMENT:
				setMappableDataElement((MappableDataElement)newValue);
				return;
			case TdlPackage.DATA_ELEMENT_MAPPING__ELEMENT_URI:
				setElementURI((String)newValue);
				return;
			case TdlPackage.DATA_ELEMENT_MAPPING__DATA_RESOURCE_MAPPING:
				setDataResourceMapping((DataResourceMapping)newValue);
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
			case TdlPackage.DATA_ELEMENT_MAPPING__MAPPABLE_DATA_ELEMENT:
				setMappableDataElement((MappableDataElement)null);
				return;
			case TdlPackage.DATA_ELEMENT_MAPPING__ELEMENT_URI:
				setElementURI(ELEMENT_URI_EDEFAULT);
				return;
			case TdlPackage.DATA_ELEMENT_MAPPING__DATA_RESOURCE_MAPPING:
				setDataResourceMapping((DataResourceMapping)null);
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
			case TdlPackage.DATA_ELEMENT_MAPPING__MAPPABLE_DATA_ELEMENT:
				return mappableDataElement != null;
			case TdlPackage.DATA_ELEMENT_MAPPING__ELEMENT_URI:
				return ELEMENT_URI_EDEFAULT == null ? elementURI != null : !ELEMENT_URI_EDEFAULT.equals(elementURI);
			case TdlPackage.DATA_ELEMENT_MAPPING__DATA_RESOURCE_MAPPING:
				return dataResourceMapping != null;
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
		result.append(" (elementURI: ");
		result.append(elementURI);
		result.append(')');
		return result.toString();
	}

} //DataElementMappingImpl
