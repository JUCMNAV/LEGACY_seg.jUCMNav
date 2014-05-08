/**
 */
package asd;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link asd.Object#getAsdSpec <em>Asd Spec</em>}</li>
 *   <li>{@link asd.Object#getOutcomes <em>Outcomes</em>}</li>
 *   <li>{@link asd.Object#getSubjects <em>Subjects</em>}</li>
 * </ul>
 * </p>
 *
 * @see asd.AsdPackage#getObject()
 * @model
 * @generated
 */
public interface Object extends MediatedElement {
	/**
	 * Returns the value of the '<em><b>Asd Spec</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link asd.ASDspec#getObjects <em>Objects</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Asd Spec</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Asd Spec</em>' container reference.
	 * @see #setAsdSpec(ASDspec)
	 * @see asd.AsdPackage#getObject_AsdSpec()
	 * @see asd.ASDspec#getObjects
	 * @model opposite="objects" required="true"
	 * @generated
	 */
	ASDspec getAsdSpec();

	/**
	 * Sets the value of the '{@link asd.Object#getAsdSpec <em>Asd Spec</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Asd Spec</em>' container reference.
	 * @see #getAsdSpec()
	 * @generated
	 */
	void setAsdSpec(ASDspec value);

	/**
	 * Returns the value of the '<em><b>Outcomes</b></em>' reference list.
	 * The list contents are of type {@link asd.Outcome}.
	 * It is bidirectional and its opposite is '{@link asd.Outcome#getObjects <em>Objects</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outcomes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outcomes</em>' reference list.
	 * @see asd.AsdPackage#getObject_Outcomes()
	 * @see asd.Outcome#getObjects
	 * @model type="asd.Outcome" opposite="objects"
	 * @generated
	 */
	EList getOutcomes();

	/**
	 * Returns the value of the '<em><b>Subjects</b></em>' reference list.
	 * The list contents are of type {@link asd.Subject}.
	 * It is bidirectional and its opposite is '{@link asd.Subject#getObjects <em>Objects</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subjects</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subjects</em>' reference list.
	 * @see asd.AsdPackage#getObject_Subjects()
	 * @see asd.Subject#getObjects
	 * @model type="asd.Subject" opposite="objects" required="true"
	 * @generated
	 */
	EList getSubjects();

} // Object
