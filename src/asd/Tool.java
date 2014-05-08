/**
 */
package asd;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tool</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link asd.Tool#getAsdSpec <em>Asd Spec</em>}</li>
 * </ul>
 * </p>
 *
 * @see asd.AsdPackage#getTool()
 * @model
 * @generated
 */
public interface Tool extends MediatingElement {
	/**
	 * Returns the value of the '<em><b>Asd Spec</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link asd.ASDspec#getTools <em>Tools</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Asd Spec</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Asd Spec</em>' container reference.
	 * @see #setAsdSpec(ASDspec)
	 * @see asd.AsdPackage#getTool_AsdSpec()
	 * @see asd.ASDspec#getTools
	 * @model opposite="tools" required="true"
	 * @generated
	 */
	ASDspec getAsdSpec();

	/**
	 * Sets the value of the '{@link asd.Tool#getAsdSpec <em>Asd Spec</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Asd Spec</em>' container reference.
	 * @see #getAsdSpec()
	 * @generated
	 */
	void setAsdSpec(ASDspec value);

} // Tool
