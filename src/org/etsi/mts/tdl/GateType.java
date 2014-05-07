/**
 */
package org.etsi.mts.tdl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gate Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.GateType#getDataSets <em>Data Set</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etsi.mts.tdl.TdlPackage#getGateType()
 * @model
 * @generated
 */
public interface GateType extends PackageableElement {
	/**
	 * Returns the value of the '<em><b>Data Set</b></em>' reference list.
	 * The list contents are of type {@link org.etsi.mts.tdl.DataSet}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Set</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Set</em>' reference list.
	 * @see org.etsi.mts.tdl.TdlPackage#getGateType_DataSet()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	EList<DataSet> getDataSets();

} // GateType
