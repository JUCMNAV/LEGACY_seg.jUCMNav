/**
 */
package asd;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link asd.Rule#getDols <em>Dols</em>}</li>
 *   <li>{@link asd.Rule#getAsdSpec <em>Asd Spec</em>}</li>
 * </ul>
 * </p>
 *
 * @see asd.AsdPackage#getRule()
 * @model
 * @generated
 */
public interface Rule extends MediatingElement {
	/**
	 * Returns the value of the '<em><b>Dols</b></em>' reference list.
	 * The list contents are of type {@link asd.DivisionOfLabour}.
	 * It is bidirectional and its opposite is '{@link asd.DivisionOfLabour#getRules <em>Rules</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dols</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dols</em>' reference list.
	 * @see asd.AsdPackage#getRule_Dols()
	 * @see asd.DivisionOfLabour#getRules
	 * @model type="asd.DivisionOfLabour" opposite="rules" required="true"
	 * @generated
	 */
	EList getDols();

	/**
	 * Returns the value of the '<em><b>Asd Spec</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link asd.ASDspec#getRules <em>Rules</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Asd Spec</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Asd Spec</em>' container reference.
	 * @see #setAsdSpec(ASDspec)
	 * @see asd.AsdPackage#getRule_AsdSpec()
	 * @see asd.ASDspec#getRules
	 * @model opposite="rules" required="true"
	 * @generated
	 */
	ASDspec getAsdSpec();

	/**
	 * Sets the value of the '{@link asd.Rule#getAsdSpec <em>Asd Spec</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Asd Spec</em>' container reference.
	 * @see #getAsdSpec()
	 * @generated
	 */
	void setAsdSpec(ASDspec value);

} // Rule
