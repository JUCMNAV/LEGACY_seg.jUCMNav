/**
 */
package org.etsi.mts.tdl;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Bounded Loop Behaviour</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.BoundedLoopBehaviour#getNumIteration <em>Num Iteration</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etsi.mts.tdl.TdlPackage#getBoundedLoopBehaviour()
 * @model
 * @generated
 */
public interface BoundedLoopBehaviour extends SingleCombinedBehaviour {
	/**
	 * Returns the value of the '<em><b>Num Iteration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Num Iteration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Num Iteration</em>' attribute.
	 * @see #setNumIteration(int)
	 * @see org.etsi.mts.tdl.TdlPackage#getBoundedLoopBehaviour_NumIteration()
	 * @model unique="false" dataType="org.eclipse.uml2.types.Integer" required="true" ordered="false"
	 * @generated
	 */
	int getNumIteration();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.BoundedLoopBehaviour#getNumIteration <em>Num Iteration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Num Iteration</em>' attribute.
	 * @see #getNumIteration()
	 * @generated
	 */
	void setNumIteration(int value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * self.block.guard.oclIsUndefined()
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/Ecore/OCL body='self.block.guard.oclIsUndefined()'"
	 * @generated
	 */
	boolean invBoundedLoopmustnothaveaguard(DiagnosticChain diagnostics, Map<Object, Object> context);

} // BoundedLoopBehaviour
