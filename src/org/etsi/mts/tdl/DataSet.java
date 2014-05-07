/**
 */
package org.etsi.mts.tdl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.DataSet#getDataInstances <em>Data Instance</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etsi.mts.tdl.TdlPackage#getDataSet()
 * @model
 * @generated
 */
public interface DataSet extends PackageableElement, MappableDataElement {
	/**
	 * Returns the value of the '<em><b>Data Instance</b></em>' containment reference list.
	 * The list contents are of type {@link org.etsi.mts.tdl.DataInstance}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Instance</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Instance</em>' containment reference list.
	 * @see org.etsi.mts.tdl.TdlPackage#getDataSet_DataInstance()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<DataInstance> getDataInstances();

	/**
	 * Creates a new {@link org.etsi.mts.tdl.DataInstance} and appends it to the '<em><b>Data Instance</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return The new {@link org.etsi.mts.tdl.DataInstance}.
	 * @see #getDataInstances()
	 * @generated
	 */
	DataInstance createDataInstance();

} // DataSet
