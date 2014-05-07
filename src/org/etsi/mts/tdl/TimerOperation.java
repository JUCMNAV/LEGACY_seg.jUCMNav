/**
 */
package org.etsi.mts.tdl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Timer Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.TimerOperation#getTimer <em>Timer</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etsi.mts.tdl.TdlPackage#getTimerOperation()
 * @model abstract="true"
 * @generated
 */
public interface TimerOperation extends AtomicBehaviour {
	/**
	 * Returns the value of the '<em><b>Timer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timer</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timer</em>' reference.
	 * @see #setTimer(Timer)
	 * @see org.etsi.mts.tdl.TdlPackage#getTimerOperation_Timer()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Timer getTimer();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.TimerOperation#getTimer <em>Timer</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timer</em>' reference.
	 * @see #getTimer()
	 * @generated
	 */
	void setTimer(Timer value);

} // TimerOperation
