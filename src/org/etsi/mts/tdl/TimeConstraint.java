/**
 */
package org.etsi.mts.tdl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Time Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.TimeConstraint#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.TimeConstraint#getAtomicBehaviours <em>Atomic Behaviour</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etsi.mts.tdl.TdlPackage#getTimeConstraint()
 * @model
 * @generated
 */
public interface TimeConstraint extends Element {
	/**
	 * Returns the value of the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression</em>' attribute.
	 * @see #setExpression(String)
	 * @see org.etsi.mts.tdl.TdlPackage#getTimeConstraint_Expression()
	 * @model unique="false" dataType="org.eclipse.uml2.types.String" required="true" ordered="false"
	 * @generated
	 */
	String getExpression();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.TimeConstraint#getExpression <em>Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' attribute.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(String value);

	/**
	 * Returns the value of the '<em><b>Atomic Behaviour</b></em>' reference list.
	 * The list contents are of type {@link org.etsi.mts.tdl.AtomicBehaviour}.
	 * It is bidirectional and its opposite is '{@link org.etsi.mts.tdl.AtomicBehaviour#getTimeConstraints <em>Time Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Atomic Behaviour</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Atomic Behaviour</em>' reference list.
	 * @see org.etsi.mts.tdl.TdlPackage#getTimeConstraint_AtomicBehaviour()
	 * @see org.etsi.mts.tdl.AtomicBehaviour#getTimeConstraints
	 * @model opposite="timeConstraint" required="true" ordered="false"
	 * @generated
	 */
	EList<AtomicBehaviour> getAtomicBehaviours();

} // TimeConstraint
