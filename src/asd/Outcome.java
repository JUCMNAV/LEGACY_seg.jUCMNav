/**
 */
package asd;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Outcome</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link asd.Outcome#getEnabledElements <em>Enabled Elements</em>}</li>
 *   <li>{@link asd.Outcome#getAsdSpec <em>Asd Spec</em>}</li>
 *   <li>{@link asd.Outcome#getObjects <em>Objects</em>}</li>
 * </ul>
 * </p>
 *
 * @see asd.AsdPackage#getOutcome()
 * @model
 * @generated
 */
public interface Outcome extends ASDelement {
	/**
	 * Returns the value of the '<em><b>Enabled Elements</b></em>' reference list.
	 * The list contents are of type {@link asd.ASDelement}.
	 * It is bidirectional and its opposite is '{@link asd.ASDelement#getRequiredOutcomes <em>Required Outcomes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enabled Elements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enabled Elements</em>' reference list.
	 * @see asd.AsdPackage#getOutcome_EnabledElements()
	 * @see asd.ASDelement#getRequiredOutcomes
	 * @model type="asd.ASDelement" opposite="requiredOutcomes"
	 * @generated
	 */
	EList getEnabledElements();

	/**
	 * Returns the value of the '<em><b>Asd Spec</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link asd.ASDspec#getOutcomes <em>Outcomes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Asd Spec</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Asd Spec</em>' container reference.
	 * @see #setAsdSpec(ASDspec)
	 * @see asd.AsdPackage#getOutcome_AsdSpec()
	 * @see asd.ASDspec#getOutcomes
	 * @model opposite="outcomes" required="true"
	 * @generated
	 */
	ASDspec getAsdSpec();

	/**
	 * Sets the value of the '{@link asd.Outcome#getAsdSpec <em>Asd Spec</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Asd Spec</em>' container reference.
	 * @see #getAsdSpec()
	 * @generated
	 */
	void setAsdSpec(ASDspec value);

	/**
	 * Returns the value of the '<em><b>Objects</b></em>' reference list.
	 * The list contents are of type {@link asd.Object}.
	 * It is bidirectional and its opposite is '{@link asd.Object#getOutcomes <em>Outcomes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Objects</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Objects</em>' reference list.
	 * @see asd.AsdPackage#getOutcome_Objects()
	 * @see asd.Object#getOutcomes
	 * @model type="asd.Object" opposite="outcomes"
	 * @generated
	 */
	EList getObjects();

} // Outcome
