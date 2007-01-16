/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucmscenarios;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Message</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucmscenarios.Message#getSource <em>Source</em>}</li>
 *   <li>{@link ucmscenarios.Message#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucmscenarios.UcmscenariosPackage#getMessage()
 * @model
 * @generated
 */
public interface Message extends SequenceElement {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link ucmscenarios.Instance#getSent <em>Sent</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(Instance)
	 * @see ucmscenarios.UcmscenariosPackage#getMessage_Source()
	 * @see ucmscenarios.Instance#getSent
	 * @model opposite="sent" required="true"
	 * @generated
	 */
	Instance getSource();

	/**
	 * Sets the value of the '{@link ucmscenarios.Message#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(Instance value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link ucmscenarios.Instance#getReceived <em>Received</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(Instance)
	 * @see ucmscenarios.UcmscenariosPackage#getMessage_Target()
	 * @see ucmscenarios.Instance#getReceived
	 * @model opposite="received" required="true"
	 * @generated
	 */
	Instance getTarget();

	/**
	 * Sets the value of the '{@link ucmscenarios.Message#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(Instance value);

} // Message