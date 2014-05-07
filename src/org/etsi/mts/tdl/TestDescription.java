/**
 */
package org.etsi.mts.tdl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Description</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.TestDescription#getTestConfiguration <em>Test Configuration</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.TestDescription#getBehaviour <em>Behaviour</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.TestDescription#getTimeConstraints <em>Time Constraint</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.TestDescription#getFormalParameters <em>Formal Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etsi.mts.tdl.TdlPackage#getTestDescription()
 * @model
 * @generated
 */
public interface TestDescription extends PackageableElement, TestObjectiveRealizer {
	/**
	 * Returns the value of the '<em><b>Test Configuration</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Test Configuration</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test Configuration</em>' reference.
	 * @see #setTestConfiguration(TestConfiguration)
	 * @see org.etsi.mts.tdl.TdlPackage#getTestDescription_TestConfiguration()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	TestConfiguration getTestConfiguration();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.TestDescription#getTestConfiguration <em>Test Configuration</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Test Configuration</em>' reference.
	 * @see #getTestConfiguration()
	 * @generated
	 */
	void setTestConfiguration(TestConfiguration value);

	/**
	 * Returns the value of the '<em><b>Behaviour</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Behaviour</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Behaviour</em>' containment reference.
	 * @see #setBehaviour(CompoundBehaviour)
	 * @see org.etsi.mts.tdl.TdlPackage#getTestDescription_Behaviour()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	CompoundBehaviour getBehaviour();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.TestDescription#getBehaviour <em>Behaviour</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Behaviour</em>' containment reference.
	 * @see #getBehaviour()
	 * @generated
	 */
	void setBehaviour(CompoundBehaviour value);

	/**
	 * Creates a new {@link org.etsi.mts.tdl.CompoundBehaviour} and sets the '<em><b>Behaviour</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return The new {@link org.etsi.mts.tdl.CompoundBehaviour}.
	 * @see #getBehaviour()
	 * @generated
	 */
	CompoundBehaviour createBehaviour();

	/**
	 * Returns the value of the '<em><b>Time Constraint</b></em>' containment reference list.
	 * The list contents are of type {@link org.etsi.mts.tdl.TimeConstraint}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time Constraint</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time Constraint</em>' containment reference list.
	 * @see org.etsi.mts.tdl.TdlPackage#getTestDescription_TimeConstraint()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<TimeConstraint> getTimeConstraints();

	/**
	 * Creates a new {@link org.etsi.mts.tdl.TimeConstraint} and appends it to the '<em><b>Time Constraint</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return The new {@link org.etsi.mts.tdl.TimeConstraint}.
	 * @see #getTimeConstraints()
	 * @generated
	 */
	TimeConstraint createTimeConstraint();

	/**
	 * Returns the value of the '<em><b>Formal Parameter</b></em>' containment reference list.
	 * The list contents are of type {@link org.etsi.mts.tdl.DataProxy}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Formal Parameter</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Formal Parameter</em>' containment reference list.
	 * @see org.etsi.mts.tdl.TdlPackage#getTestDescription_FormalParameter()
	 * @model containment="true"
	 * @generated
	 */
	EList<DataProxy> getFormalParameters();

	/**
	 * Creates a new {@link org.etsi.mts.tdl.DataProxy} and appends it to the '<em><b>Formal Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return The new {@link org.etsi.mts.tdl.DataProxy}.
	 * @see #getFormalParameters()
	 * @generated
	 */
	DataProxy createFormalParameter();

} // TestDescription
