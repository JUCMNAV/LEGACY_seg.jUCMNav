/**
 */
package org.etsi.mts.tdl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gate Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.GateInstance#getType <em>Type</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.GateInstance#getComponentInstance <em>Component Instance</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etsi.mts.tdl.TdlPackage#getGateInstance()
 * @model
 * @generated
 */
public interface GateInstance extends Element {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(GateType)
	 * @see org.etsi.mts.tdl.TdlPackage#getGateInstance_Type()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	GateType getType();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.GateInstance#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(GateType value);

	/**
	 * Returns the value of the '<em><b>Component Instance</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.etsi.mts.tdl.ComponentInstance#getGateInstances <em>Gate Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component Instance</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component Instance</em>' container reference.
	 * @see #setComponentInstance(ComponentInstance)
	 * @see org.etsi.mts.tdl.TdlPackage#getGateInstance_ComponentInstance()
	 * @see org.etsi.mts.tdl.ComponentInstance#getGateInstances
	 * @model opposite="gateInstance" required="true" transient="false" ordered="false"
	 * @generated
	 */
	ComponentInstance getComponentInstance();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.GateInstance#getComponentInstance <em>Component Instance</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component Instance</em>' container reference.
	 * @see #getComponentInstance()
	 * @generated
	 */
	void setComponentInstance(ComponentInstance value);

} // GateInstance
