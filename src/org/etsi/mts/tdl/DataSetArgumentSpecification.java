/**
 */
package org.etsi.mts.tdl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Set Argument Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.DataSetArgumentSpecification#getDataSet <em>Data Set</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etsi.mts.tdl.TdlPackage#getDataSetArgumentSpecification()
 * @model
 * @generated
 */
public interface DataSetArgumentSpecification extends ArgumentSpecification {
	/**
	 * Returns the value of the '<em><b>Data Set</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Set</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Set</em>' reference.
	 * @see #setDataSet(DataSet)
	 * @see org.etsi.mts.tdl.TdlPackage#getDataSetArgumentSpecification_DataSet()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	DataSet getDataSet();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.DataSetArgumentSpecification#getDataSet <em>Data Set</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Set</em>' reference.
	 * @see #getDataSet()
	 * @generated
	 */
	void setDataSet(DataSet value);

} // DataSetArgumentSpecification
