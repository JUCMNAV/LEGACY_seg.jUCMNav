/**
 */
package org.etsi.mts.tdl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Objective Realizer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.TestObjectiveRealizer#getTestObjectives <em>Test Objective</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etsi.mts.tdl.TdlPackage#getTestObjectiveRealizer()
 * @model abstract="true"
 * @generated
 */
public interface TestObjectiveRealizer extends Element {
	/**
	 * Returns the value of the '<em><b>Test Objective</b></em>' reference list.
	 * The list contents are of type {@link org.etsi.mts.tdl.TestObjective}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Test Objective</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test Objective</em>' reference list.
	 * @see org.etsi.mts.tdl.TdlPackage#getTestObjectiveRealizer_TestObjective()
	 * @model ordered="false"
	 * @generated
	 */
	EList<TestObjective> getTestObjectives();

} // TestObjectiveRealizer
