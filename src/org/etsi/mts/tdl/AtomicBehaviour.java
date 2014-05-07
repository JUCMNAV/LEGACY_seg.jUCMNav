/**
 */
package org.etsi.mts.tdl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Atomic Behaviour</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.AtomicBehaviour#getTimeConstraints <em>Time Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etsi.mts.tdl.TdlPackage#getAtomicBehaviour()
 * @model abstract="true"
 * @generated
 */
public interface AtomicBehaviour extends Behaviour {
	/**
	 * Returns the value of the '<em><b>Time Constraint</b></em>' reference list.
	 * The list contents are of type {@link org.etsi.mts.tdl.TimeConstraint}.
	 * It is bidirectional and its opposite is '{@link org.etsi.mts.tdl.TimeConstraint#getAtomicBehaviours <em>Atomic Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time Constraint</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time Constraint</em>' reference list.
	 * @see org.etsi.mts.tdl.TdlPackage#getAtomicBehaviour_TimeConstraint()
	 * @see org.etsi.mts.tdl.TimeConstraint#getAtomicBehaviours
	 * @model opposite="atomicBehaviour" ordered="false"
	 * @generated
	 */
	EList<TimeConstraint> getTimeConstraints();

} // AtomicBehaviour
