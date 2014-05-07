/**
 */
package org.etsi.mts.tdl;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.ComponentType#getGateTypes <em>Gate Type</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.ComponentType#getTimers <em>Timer</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etsi.mts.tdl.TdlPackage#getComponentType()
 * @model
 * @generated
 */
public interface ComponentType extends PackageableElement {
	/**
	 * Returns the value of the '<em><b>Gate Type</b></em>' reference list.
	 * The list contents are of type {@link org.etsi.mts.tdl.GateType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Gate Type</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Gate Type</em>' reference list.
	 * @see org.etsi.mts.tdl.TdlPackage#getComponentType_GateType()
	 * @model required="true"
	 * @generated
	 */
	EList<GateType> getGateTypes();

	/**
	 * Returns the value of the '<em><b>Timer</b></em>' containment reference list.
	 * The list contents are of type {@link org.etsi.mts.tdl.Timer}.
	 * It is bidirectional and its opposite is '{@link org.etsi.mts.tdl.Timer#getComponentType <em>Component Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timer</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timer</em>' containment reference list.
	 * @see org.etsi.mts.tdl.TdlPackage#getComponentType_Timer()
	 * @see org.etsi.mts.tdl.Timer#getComponentType
	 * @model opposite="componentType" containment="true" ordered="false"
	 * @generated
	 */
	EList<Timer> getTimers();

	/**
	 * Creates a new {@link org.etsi.mts.tdl.Timer} and appends it to the '<em><b>Timer</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return The new {@link org.etsi.mts.tdl.Timer}.
	 * @see #getTimers()
	 * @generated
	 */
	Timer createTimer();

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
	boolean invTestConfigurationandComponentsRoles(DiagnosticChain diagnostics, Map<Object, Object> context);

} // ComponentType
