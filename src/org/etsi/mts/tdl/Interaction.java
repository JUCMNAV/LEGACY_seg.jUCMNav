/**
 */
package org.etsi.mts.tdl;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Interaction</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.Interaction#getArgument <em>Argument</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.Interaction#getSource <em>Source</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.Interaction#getTargets <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etsi.mts.tdl.TdlPackage#getInteraction()
 * @model
 * @generated
 */
public interface Interaction extends AtomicBehaviour {
	/**
	 * Returns the value of the '<em><b>Argument</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Argument</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Argument</em>' containment reference.
	 * @see #setArgument(ArgumentSpecification)
	 * @see org.etsi.mts.tdl.TdlPackage#getInteraction_Argument()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	ArgumentSpecification getArgument();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.Interaction#getArgument <em>Argument</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Argument</em>' containment reference.
	 * @see #getArgument()
	 * @generated
	 */
	void setArgument(ArgumentSpecification value);

	/**
	 * Creates a new {@link org.etsi.mts.tdl.ArgumentSpecification} and sets the '<em><b>Argument</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param eClass The Ecore class of the {@link org.etsi.mts.tdl.ArgumentSpecification} to create.
	 * @return The new {@link org.etsi.mts.tdl.ArgumentSpecification}.
	 * @see #getArgument()
	 * @generated
	 */
	ArgumentSpecification createArgument(EClass eClass);

	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(GateInstance)
	 * @see org.etsi.mts.tdl.TdlPackage#getInteraction_Source()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	GateInstance getSource();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.Interaction#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(GateInstance value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference list.
	 * The list contents are of type {@link org.etsi.mts.tdl.GateInstance}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference list.
	 * @see org.etsi.mts.tdl.TdlPackage#getInteraction_Target()
	 * @model required="true"
	 * @generated
	 */
	EList<GateInstance> getTargets();

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
	boolean invConstraint1(DiagnosticChain diagnostics, Map<Object, Object> context);

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
	boolean invConstraint2(DiagnosticChain diagnostics, Map<Object, Object> context);

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
	boolean invConstraint3(DiagnosticChain diagnostics, Map<Object, Object> context);

} // Interaction
