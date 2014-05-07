/**
 */
package org.etsi.mts.tdl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.DataInstance#getParameters <em>Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etsi.mts.tdl.TdlPackage#getDataInstance()
 * @model
 * @generated
 */
public interface DataInstance extends MappableDataElement {
	/**
	 * Returns the value of the '<em><b>Parameter</b></em>' reference list.
	 * The list contents are of type {@link org.etsi.mts.tdl.MappableDataElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter</em>' reference list.
	 * @see org.etsi.mts.tdl.TdlPackage#getDataInstance_Parameter()
	 * @model
	 * @generated
	 */
	EList<MappableDataElement> getParameters();

} // DataInstance
