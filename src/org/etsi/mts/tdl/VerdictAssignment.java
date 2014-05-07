/**
 */
package org.etsi.mts.tdl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Verdict Assignment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.VerdictAssignment#getVerdictType <em>Verdict Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etsi.mts.tdl.TdlPackage#getVerdictAssignment()
 * @model
 * @generated
 */
public interface VerdictAssignment extends AtomicBehaviour {
	/**
	 * Returns the value of the '<em><b>Verdict Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Verdict Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Verdict Type</em>' reference.
	 * @see #setVerdictType(VerdictType)
	 * @see org.etsi.mts.tdl.TdlPackage#getVerdictAssignment_VerdictType()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	VerdictType getVerdictType();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.VerdictAssignment#getVerdictType <em>Verdict Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Verdict Type</em>' reference.
	 * @see #getVerdictType()
	 * @generated
	 */
	void setVerdictType(VerdictType value);

} // VerdictAssignment
