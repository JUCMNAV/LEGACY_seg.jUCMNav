/**
 */
package asd;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Motivation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link asd.Motivation#getAsdSpec <em>Asd Spec</em>}</li>
 * </ul>
 * </p>
 *
 * @see asd.AsdPackage#getMotivation()
 * @model
 * @generated
 */
public interface Motivation extends ASDelement {
	/**
	 * Returns the value of the '<em><b>Asd Spec</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link asd.ASDspec#getMotivations <em>Motivations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Asd Spec</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Asd Spec</em>' container reference.
	 * @see #setAsdSpec(ASDspec)
	 * @see asd.AsdPackage#getMotivation_AsdSpec()
	 * @see asd.ASDspec#getMotivations
	 * @model opposite="motivations" required="true"
	 * @generated
	 */
	ASDspec getAsdSpec();

	/**
	 * Sets the value of the '{@link asd.Motivation#getAsdSpec <em>Asd Spec</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Asd Spec</em>' container reference.
	 * @see #getAsdSpec()
	 * @generated
	 */
	void setAsdSpec(ASDspec value);

} // Motivation
