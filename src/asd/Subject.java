/**
 */
package asd;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Subject</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link asd.Subject#getAsdSpec <em>Asd Spec</em>}</li>
 *   <li>{@link asd.Subject#getMemberOf <em>Member Of</em>}</li>
 *   <li>{@link asd.Subject#getObjects <em>Objects</em>}</li>
 * </ul>
 * </p>
 *
 * @see asd.AsdPackage#getSubject()
 * @model
 * @generated
 */
public interface Subject extends MediatedElement {
	/**
	 * Returns the value of the '<em><b>Asd Spec</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link asd.ASDspec#getSubjects <em>Subjects</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Asd Spec</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Asd Spec</em>' container reference.
	 * @see #setAsdSpec(ASDspec)
	 * @see asd.AsdPackage#getSubject_AsdSpec()
	 * @see asd.ASDspec#getSubjects
	 * @model opposite="subjects" required="true"
	 * @generated
	 */
	ASDspec getAsdSpec();

	/**
	 * Sets the value of the '{@link asd.Subject#getAsdSpec <em>Asd Spec</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Asd Spec</em>' container reference.
	 * @see #getAsdSpec()
	 * @generated
	 */
	void setAsdSpec(ASDspec value);

	/**
	 * Returns the value of the '<em><b>Member Of</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link asd.Community#getSubject <em>Subject</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Member Of</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Member Of</em>' reference.
	 * @see #setMemberOf(Community)
	 * @see asd.AsdPackage#getSubject_MemberOf()
	 * @see asd.Community#getSubject
	 * @model opposite="subject" required="true"
	 * @generated
	 */
	Community getMemberOf();

	/**
	 * Sets the value of the '{@link asd.Subject#getMemberOf <em>Member Of</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Member Of</em>' reference.
	 * @see #getMemberOf()
	 * @generated
	 */
	void setMemberOf(Community value);

	/**
	 * Returns the value of the '<em><b>Objects</b></em>' reference list.
	 * The list contents are of type {@link asd.Object}.
	 * It is bidirectional and its opposite is '{@link asd.Object#getSubjects <em>Subjects</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Objects</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Objects</em>' reference list.
	 * @see asd.AsdPackage#getSubject_Objects()
	 * @see asd.Object#getSubjects
	 * @model type="asd.Object" opposite="subjects" required="true"
	 * @generated
	 */
	EList getObjects();

} // Subject
