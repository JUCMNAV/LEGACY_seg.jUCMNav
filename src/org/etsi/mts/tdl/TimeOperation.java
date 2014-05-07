/**
 */
package org.etsi.mts.tdl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Time Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.TimeOperation#getGateInstance <em>Gate Instance</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.TimeOperation#getPeriod <em>Period</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.TimeOperation#getComponentInstance <em>Component Instance</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etsi.mts.tdl.TdlPackage#getTimeOperation()
 * @model abstract="true"
 * @generated
 */
public interface TimeOperation extends AtomicBehaviour {
	/**
	 * Returns the value of the '<em><b>Gate Instance</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Gate Instance</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Gate Instance</em>' reference.
	 * @see #setGateInstance(GateInstance)
	 * @see org.etsi.mts.tdl.TdlPackage#getTimeOperation_GateInstance()
	 * @model ordered="false"
	 * @generated
	 */
	GateInstance getGateInstance();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.TimeOperation#getGateInstance <em>Gate Instance</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Gate Instance</em>' reference.
	 * @see #getGateInstance()
	 * @generated
	 */
	void setGateInstance(GateInstance value);

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
	 * @see org.etsi.mts.tdl.TdlPackage#getTimeOperation_Period()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Time getPeriod();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.TimeOperation#getPeriod <em>Period</em>}' containment reference.
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

	/**
	 * Returns the value of the '<em><b>Component Instance</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component Instance</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component Instance</em>' reference.
	 * @see #setComponentInstance(ComponentInstance)
	 * @see org.etsi.mts.tdl.TdlPackage#getTimeOperation_ComponentInstance()
	 * @model ordered="false"
	 * @generated
	 */
	ComponentInstance getComponentInstance();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.TimeOperation#getComponentInstance <em>Component Instance</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component Instance</em>' reference.
	 * @see #getComponentInstance()
	 * @generated
	 */
	void setComponentInstance(ComponentInstance value);

} // TimeOperation
