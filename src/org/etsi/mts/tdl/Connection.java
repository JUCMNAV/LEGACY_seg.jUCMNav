/**
 */
package org.etsi.mts.tdl;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.Connection#getEndPoints <em>End Point</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etsi.mts.tdl.TdlPackage#getConnection()
 * @model
 * @generated
 */
public interface Connection extends Element {
	/**
	 * Returns the value of the '<em><b>End Point</b></em>' reference list.
	 * The list contents are of type {@link org.etsi.mts.tdl.GateInstance}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End Point</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End Point</em>' reference list.
	 * @see org.etsi.mts.tdl.TdlPackage#getConnection_EndPoint()
	 * @model lower="2" upper="2" ordered="false"
	 * @generated
	 */
	EList<GateInstance> getEndPoints();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean invSelfloopedConnections(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean invOnlyoneconnectionallowed(DiagnosticChain diagnostics, Map<Object, Object> context);

} // Connection
