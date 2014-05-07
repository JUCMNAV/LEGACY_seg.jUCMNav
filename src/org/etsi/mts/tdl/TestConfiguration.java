/**
 */
package org.etsi.mts.tdl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.TestConfiguration#getComponentInstances <em>Component Instance</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.TestConfiguration#getConnections <em>Connection</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etsi.mts.tdl.TdlPackage#getTestConfiguration()
 * @model
 * @generated
 */
public interface TestConfiguration extends PackageableElement {
	/**
	 * Returns the value of the '<em><b>Component Instance</b></em>' containment reference list.
	 * The list contents are of type {@link org.etsi.mts.tdl.ComponentInstance}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component Instance</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component Instance</em>' containment reference list.
	 * @see org.etsi.mts.tdl.TdlPackage#getTestConfiguration_ComponentInstance()
	 * @model containment="true" lower="2" ordered="false"
	 * @generated
	 */
	EList<ComponentInstance> getComponentInstances();

	/**
	 * Creates a new {@link org.etsi.mts.tdl.ComponentInstance} and appends it to the '<em><b>Component Instance</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return The new {@link org.etsi.mts.tdl.ComponentInstance}.
	 * @see #getComponentInstances()
	 * @generated
	 */
	ComponentInstance createComponentInstance();

	/**
	 * Returns the value of the '<em><b>Connection</b></em>' containment reference list.
	 * The list contents are of type {@link org.etsi.mts.tdl.Connection}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connection</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connection</em>' containment reference list.
	 * @see org.etsi.mts.tdl.TdlPackage#getTestConfiguration_Connection()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	EList<Connection> getConnections();

	/**
	 * Creates a new {@link org.etsi.mts.tdl.Connection} and appends it to the '<em><b>Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return The new {@link org.etsi.mts.tdl.Connection}.
	 * @see #getConnections()
	 * @generated
	 */
	Connection createConnection();

} // TestConfiguration
