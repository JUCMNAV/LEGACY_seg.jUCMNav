/**
 */
package org.etsi.mts.tdl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Instance Argument Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.DataInstanceArgumentSpecification#getDataInstance <em>Data Instance</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.DataInstanceArgumentSpecification#getActualParameters <em>Actual Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etsi.mts.tdl.TdlPackage#getDataInstanceArgumentSpecification()
 * @model
 * @generated
 */
public interface DataInstanceArgumentSpecification extends ArgumentSpecification {
	/**
	 * Returns the value of the '<em><b>Data Instance</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Instance</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Instance</em>' reference.
	 * @see #setDataInstance(DataInstance)
	 * @see org.etsi.mts.tdl.TdlPackage#getDataInstanceArgumentSpecification_DataInstance()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	DataInstance getDataInstance();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.DataInstanceArgumentSpecification#getDataInstance <em>Data Instance</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Instance</em>' reference.
	 * @see #getDataInstance()
	 * @generated
	 */
	void setDataInstance(DataInstance value);

	/**
	 * Returns the value of the '<em><b>Actual Parameter</b></em>' reference list.
	 * The list contents are of type {@link org.etsi.mts.tdl.DataElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actual Parameter</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Actual Parameter</em>' reference list.
	 * @see org.etsi.mts.tdl.TdlPackage#getDataInstanceArgumentSpecification_ActualParameter()
	 * @model
	 * @generated
	 */
	EList<DataElement> getActualParameters();

} // DataInstanceArgumentSpecification
