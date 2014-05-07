/**
 */
package org.etsi.mts.tdl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Block</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.Block#getBehaviours <em>Behaviour</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.Block#getGuard <em>Guard</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etsi.mts.tdl.TdlPackage#getBlock()
 * @model
 * @generated
 */
public interface Block extends Element {
	/**
	 * Returns the value of the '<em><b>Behaviour</b></em>' containment reference list.
	 * The list contents are of type {@link org.etsi.mts.tdl.Behaviour}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Behaviour</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Behaviour</em>' containment reference list.
	 * @see org.etsi.mts.tdl.TdlPackage#getBlock_Behaviour()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Behaviour> getBehaviours();

	/**
	 * Creates a new {@link org.etsi.mts.tdl.Behaviour} and appends it to the '<em><b>Behaviour</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param eClass The Ecore class of the {@link org.etsi.mts.tdl.Behaviour} to create.
	 * @return The new {@link org.etsi.mts.tdl.Behaviour}.
	 * @see #getBehaviours()
	 * @generated
	 */
	Behaviour createBehaviour(EClass eClass);

	/**
	 * Returns the value of the '<em><b>Guard</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Guard</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Guard</em>' attribute.
	 * @see #setGuard(String)
	 * @see org.etsi.mts.tdl.TdlPackage#getBlock_Guard()
	 * @model unique="false" dataType="org.eclipse.uml2.types.String" ordered="false"
	 * @generated
	 */
	String getGuard();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.Block#getGuard <em>Guard</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Guard</em>' attribute.
	 * @see #getGuard()
	 * @generated
	 */
	void setGuard(String value);

} // Block
