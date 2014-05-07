/**
 */
package org.etsi.mts.tdl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Proxy Argument Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.DataProxyArgumentSpecification#getDataProxy <em>Data Proxy</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etsi.mts.tdl.TdlPackage#getDataProxyArgumentSpecification()
 * @model
 * @generated
 */
public interface DataProxyArgumentSpecification extends ArgumentSpecification {
	/**
	 * Returns the value of the '<em><b>Data Proxy</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Proxy</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Proxy</em>' reference.
	 * @see #setDataProxy(DataProxy)
	 * @see org.etsi.mts.tdl.TdlPackage#getDataProxyArgumentSpecification_DataProxy()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	DataProxy getDataProxy();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.DataProxyArgumentSpecification#getDataProxy <em>Data Proxy</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Proxy</em>' reference.
	 * @see #getDataProxy()
	 * @generated
	 */
	void setDataProxy(DataProxy value);

} // DataProxyArgumentSpecification
