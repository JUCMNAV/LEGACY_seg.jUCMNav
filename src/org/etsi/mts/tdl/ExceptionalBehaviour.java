/**
 */
package org.etsi.mts.tdl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Exceptional Behaviour</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.ExceptionalBehaviour#getBlock <em>Block</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.ExceptionalBehaviour#getGuardedComponent <em>Guarded Component</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etsi.mts.tdl.TdlPackage#getExceptionalBehaviour()
 * @model abstract="true"
 * @generated
 */
public interface ExceptionalBehaviour extends Behaviour {
	/**
	 * Returns the value of the '<em><b>Block</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Block</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Block</em>' containment reference.
	 * @see #setBlock(Block)
	 * @see org.etsi.mts.tdl.TdlPackage#getExceptionalBehaviour_Block()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Block getBlock();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.ExceptionalBehaviour#getBlock <em>Block</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Block</em>' containment reference.
	 * @see #getBlock()
	 * @generated
	 */
	void setBlock(Block value);

	/**
	 * Creates a new {@link org.etsi.mts.tdl.Block} and sets the '<em><b>Block</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return The new {@link org.etsi.mts.tdl.Block}.
	 * @see #getBlock()
	 * @generated
	 */
	Block createBlock();

	/**
	 * Returns the value of the '<em><b>Guarded Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Guarded Component</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Guarded Component</em>' reference.
	 * @see #setGuardedComponent(ComponentInstance)
	 * @see org.etsi.mts.tdl.TdlPackage#getExceptionalBehaviour_GuardedComponent()
	 * @model ordered="false"
	 * @generated
	 */
	ComponentInstance getGuardedComponent();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.ExceptionalBehaviour#getGuardedComponent <em>Guarded Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Guarded Component</em>' reference.
	 * @see #getGuardedComponent()
	 * @generated
	 */
	void setGuardedComponent(ComponentInstance value);

} // ExceptionalBehaviour
