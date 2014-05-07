/**
 */
package org.etsi.mts.tdl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Combined Behaviour</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.CombinedBehaviour#getPeriodics <em>Periodic</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.CombinedBehaviour#getExceptionals <em>Exceptional</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etsi.mts.tdl.TdlPackage#getCombinedBehaviour()
 * @model abstract="true"
 * @generated
 */
public interface CombinedBehaviour extends Behaviour {
	/**
	 * Returns the value of the '<em><b>Periodic</b></em>' containment reference list.
	 * The list contents are of type {@link org.etsi.mts.tdl.PeriodicBehaviour}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Periodic</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Periodic</em>' containment reference list.
	 * @see org.etsi.mts.tdl.TdlPackage#getCombinedBehaviour_Periodic()
	 * @model containment="true"
	 * @generated
	 */
	EList<PeriodicBehaviour> getPeriodics();

	/**
	 * Creates a new {@link org.etsi.mts.tdl.PeriodicBehaviour} and appends it to the '<em><b>Periodic</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return The new {@link org.etsi.mts.tdl.PeriodicBehaviour}.
	 * @see #getPeriodics()
	 * @generated
	 */
	PeriodicBehaviour createPeriodic();

	/**
	 * Returns the value of the '<em><b>Exceptional</b></em>' containment reference list.
	 * The list contents are of type {@link org.etsi.mts.tdl.ExceptionalBehaviour}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exceptional</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exceptional</em>' containment reference list.
	 * @see org.etsi.mts.tdl.TdlPackage#getCombinedBehaviour_Exceptional()
	 * @model containment="true"
	 * @generated
	 */
	EList<ExceptionalBehaviour> getExceptionals();

	/**
	 * Creates a new {@link org.etsi.mts.tdl.ExceptionalBehaviour} and appends it to the '<em><b>Exceptional</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param eClass The Ecore class of the {@link org.etsi.mts.tdl.ExceptionalBehaviour} to create.
	 * @return The new {@link org.etsi.mts.tdl.ExceptionalBehaviour}.
	 * @see #getExceptionals()
	 * @generated
	 */
	ExceptionalBehaviour createExceptional(EClass eClass);

} // CombinedBehaviour
