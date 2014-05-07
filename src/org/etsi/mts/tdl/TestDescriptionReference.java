/**
 */
package org.etsi.mts.tdl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Description Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.TestDescriptionReference#getReferencedTestDescription <em>Referenced Test Description</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.TestDescriptionReference#getActualParameters <em>Actual Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etsi.mts.tdl.TdlPackage#getTestDescriptionReference()
 * @model
 * @generated
 */
public interface TestDescriptionReference extends AtomicBehaviour {
	/**
	 * Returns the value of the '<em><b>Referenced Test Description</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referenced Test Description</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referenced Test Description</em>' reference.
	 * @see #setReferencedTestDescription(TestDescription)
	 * @see org.etsi.mts.tdl.TdlPackage#getTestDescriptionReference_ReferencedTestDescription()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	TestDescription getReferencedTestDescription();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.TestDescriptionReference#getReferencedTestDescription <em>Referenced Test Description</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referenced Test Description</em>' reference.
	 * @see #getReferencedTestDescription()
	 * @generated
	 */
	void setReferencedTestDescription(TestDescription value);

	/**
	 * Returns the value of the '<em><b>Actual Parameter</b></em>' containment reference list.
	 * The list contents are of type {@link org.etsi.mts.tdl.ArgumentSpecification}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actual Parameter</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Actual Parameter</em>' containment reference list.
	 * @see org.etsi.mts.tdl.TdlPackage#getTestDescriptionReference_ActualParameter()
	 * @model containment="true"
	 * @generated
	 */
	EList<ArgumentSpecification> getActualParameters();

	/**
	 * Creates a new {@link org.etsi.mts.tdl.ArgumentSpecification} and appends it to the '<em><b>Actual Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param eClass The Ecore class of the {@link org.etsi.mts.tdl.ArgumentSpecification} to create.
	 * @return The new {@link org.etsi.mts.tdl.ArgumentSpecification}.
	 * @see #getActualParameters()
	 * @generated
	 */
	ArgumentSpecification createActualParameter(EClass eClass);

} // TestDescriptionReference
