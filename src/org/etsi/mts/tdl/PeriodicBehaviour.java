/**
 */
package org.etsi.mts.tdl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Periodic Behaviour</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.PeriodicBehaviour#getBlock <em>Block</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.PeriodicBehaviour#getPeriod <em>Period</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etsi.mts.tdl.TdlPackage#getPeriodicBehaviour()
 * @model
 * @generated
 */
public interface PeriodicBehaviour extends Behaviour {
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
	 * @see org.etsi.mts.tdl.TdlPackage#getPeriodicBehaviour_Block()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Block getBlock();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.PeriodicBehaviour#getBlock <em>Block</em>}' containment reference.
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
	 * Returns the value of the '<em><b>Period</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Period</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Period</em>' containment reference.
	 * @see #setPeriod(Time)
	 * @see org.etsi.mts.tdl.TdlPackage#getPeriodicBehaviour_Period()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Time getPeriod();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.PeriodicBehaviour#getPeriod <em>Period</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Period</em>' containment reference.
	 * @see #getPeriod()
	 * @generated
	 */
	void setPeriod(Time value);

	/**
	 * Creates a new {@link org.etsi.mts.tdl.Time} and sets the '<em><b>Period</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return The new {@link org.etsi.mts.tdl.Time}.
	 * @see #getPeriod()
	 * @generated
	 */
	Time createPeriod();

} // PeriodicBehaviour
