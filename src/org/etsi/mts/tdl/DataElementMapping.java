/**
 */
package org.etsi.mts.tdl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Element Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.DataElementMapping#getMappableDataElement <em>Mappable Data Element</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.DataElementMapping#getElementURI <em>Element URI</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.DataElementMapping#getDataResourceMapping <em>Data Resource Mapping</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etsi.mts.tdl.TdlPackage#getDataElementMapping()
 * @model
 * @generated
 */
public interface DataElementMapping extends PackageableElement {
	/**
	 * Returns the value of the '<em><b>Mappable Data Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mappable Data Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mappable Data Element</em>' reference.
	 * @see #setMappableDataElement(MappableDataElement)
	 * @see org.etsi.mts.tdl.TdlPackage#getDataElementMapping_MappableDataElement()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	MappableDataElement getMappableDataElement();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.DataElementMapping#getMappableDataElement <em>Mappable Data Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mappable Data Element</em>' reference.
	 * @see #getMappableDataElement()
	 * @generated
	 */
	void setMappableDataElement(MappableDataElement value);

	/**
	 * Returns the value of the '<em><b>Element URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element URI</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element URI</em>' attribute.
	 * @see #setElementURI(String)
	 * @see org.etsi.mts.tdl.TdlPackage#getDataElementMapping_ElementURI()
	 * @model unique="false" dataType="org.eclipse.uml2.types.String" ordered="false"
	 * @generated
	 */
	String getElementURI();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.DataElementMapping#getElementURI <em>Element URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element URI</em>' attribute.
	 * @see #getElementURI()
	 * @generated
	 */
	void setElementURI(String value);

	/**
	 * Returns the value of the '<em><b>Data Resource Mapping</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Resource Mapping</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Resource Mapping</em>' reference.
	 * @see #setDataResourceMapping(DataResourceMapping)
	 * @see org.etsi.mts.tdl.TdlPackage#getDataElementMapping_DataResourceMapping()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	DataResourceMapping getDataResourceMapping();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.DataElementMapping#getDataResourceMapping <em>Data Resource Mapping</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Resource Mapping</em>' reference.
	 * @see #getDataResourceMapping()
	 * @generated
	 */
	void setDataResourceMapping(DataResourceMapping value);

} // DataElementMapping
