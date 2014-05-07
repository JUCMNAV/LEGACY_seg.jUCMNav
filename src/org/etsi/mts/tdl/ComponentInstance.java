/**
 */
package org.etsi.mts.tdl;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.ComponentInstance#getType <em>Type</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.ComponentInstance#getRole <em>Role</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.ComponentInstance#getTimers <em>Timer</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.ComponentInstance#getGateInstances <em>Gate Instance</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etsi.mts.tdl.TdlPackage#getComponentInstance()
 * @model
 * @generated
 */
public interface ComponentInstance extends Element {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(ComponentType)
	 * @see org.etsi.mts.tdl.TdlPackage#getComponentInstance_Type()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	ComponentType getType();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.ComponentInstance#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(ComponentType value);

	/**
	 * Returns the value of the '<em><b>Role</b></em>' attribute.
	 * The literals are from the enumeration {@link org.etsi.mts.tdl.ComponentInstanceRole}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Role</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Role</em>' attribute.
	 * @see org.etsi.mts.tdl.ComponentInstanceRole
	 * @see #setRole(ComponentInstanceRole)
	 * @see org.etsi.mts.tdl.TdlPackage#getComponentInstance_Role()
	 * @model unique="false" required="true" ordered="false"
	 * @generated
	 */
	ComponentInstanceRole getRole();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.ComponentInstance#getRole <em>Role</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Role</em>' attribute.
	 * @see org.etsi.mts.tdl.ComponentInstanceRole
	 * @see #getRole()
	 * @generated
	 */
	void setRole(ComponentInstanceRole value);

	/**
	 * Returns the value of the '<em><b>Timer</b></em>' reference list.
	 * The list contents are of type {@link org.etsi.mts.tdl.Timer}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timer</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timer</em>' reference list.
	 * @see org.etsi.mts.tdl.TdlPackage#getComponentInstance_Timer()
	 * @model transient="true" changeable="false" volatile="true" derived="true" ordered="false"
	 * @generated
	 */
	EList<Timer> getTimers();

	/**
	 * Returns the value of the '<em><b>Gate Instance</b></em>' containment reference list.
	 * The list contents are of type {@link org.etsi.mts.tdl.GateInstance}.
	 * It is bidirectional and its opposite is '{@link org.etsi.mts.tdl.GateInstance#getComponentInstance <em>Component Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Gate Instance</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Gate Instance</em>' containment reference list.
	 * @see org.etsi.mts.tdl.TdlPackage#getComponentInstance_GateInstance()
	 * @see org.etsi.mts.tdl.GateInstance#getComponentInstance
	 * @model opposite="componentInstance" containment="true" required="true"
	 * @generated
	 */
	EList<GateInstance> getGateInstances();

	/**
	 * Creates a new {@link org.etsi.mts.tdl.GateInstance} and appends it to the '<em><b>Gate Instance</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return The new {@link org.etsi.mts.tdl.GateInstance}.
	 * @see #getGateInstances()
	 * @generated
	 */
	GateInstance createGateInstance();

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
	boolean invNumberofGateInstances(DiagnosticChain diagnostics, Map<Object, Object> context);

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
	boolean invTimersofComponentInstances(DiagnosticChain diagnostics, Map<Object, Object> context);

} // ComponentInstance
