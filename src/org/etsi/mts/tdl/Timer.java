/**
 */
package org.etsi.mts.tdl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Timer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.Timer#getComponentType <em>Component Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etsi.mts.tdl.TdlPackage#getTimer()
 * @model
 * @generated
 */
public interface Timer extends Element {
	/**
	 * Returns the value of the '<em><b>Component Type</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.etsi.mts.tdl.ComponentType#getTimers <em>Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component Type</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component Type</em>' container reference.
	 * @see #setComponentType(ComponentType)
	 * @see org.etsi.mts.tdl.TdlPackage#getTimer_ComponentType()
	 * @see org.etsi.mts.tdl.ComponentType#getTimers
	 * @model opposite="timer" required="true" transient="false" ordered="false"
	 * @generated
	 */
	ComponentType getComponentType();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.Timer#getComponentType <em>Component Type</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component Type</em>' container reference.
	 * @see #getComponentType()
	 * @generated
	 */
	void setComponentType(ComponentType value);

} // Timer
