/**
 */
package asd;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Community</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link asd.Community#getPerforms <em>Performs</em>}</li>
 *   <li>{@link asd.Community#getAsdSpec <em>Asd Spec</em>}</li>
 *   <li>{@link asd.Community#getSubject <em>Subject</em>}</li>
 * </ul>
 * </p>
 *
 * @see asd.AsdPackage#getCommunity()
 * @model
 * @generated
 */
public interface Community extends MediatedElement {
	/**
	 * Returns the value of the '<em><b>Performs</b></em>' reference list.
	 * The list contents are of type {@link asd.DivisionOfLabour}.
	 * It is bidirectional and its opposite is '{@link asd.DivisionOfLabour#getPerformedBy <em>Performed By</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Performs</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Performs</em>' reference list.
	 * @see asd.AsdPackage#getCommunity_Performs()
	 * @see asd.DivisionOfLabour#getPerformedBy
	 * @model type="asd.DivisionOfLabour" opposite="performedBy"
	 * @generated
	 */
	EList getPerforms();

	/**
	 * Returns the value of the '<em><b>Asd Spec</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link asd.ASDspec#getCommunities <em>Communities</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Asd Spec</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Asd Spec</em>' container reference.
	 * @see #setAsdSpec(ASDspec)
	 * @see asd.AsdPackage#getCommunity_AsdSpec()
	 * @see asd.ASDspec#getCommunities
	 * @model opposite="communities" required="true"
	 * @generated
	 */
	ASDspec getAsdSpec();

	/**
	 * Sets the value of the '{@link asd.Community#getAsdSpec <em>Asd Spec</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Asd Spec</em>' container reference.
	 * @see #getAsdSpec()
	 * @generated
	 */
	void setAsdSpec(ASDspec value);

	/**
	 * Returns the value of the '<em><b>Subject</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link asd.Subject#getMemberOf <em>Member Of</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subject</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subject</em>' reference.
	 * @see #setSubject(Subject)
	 * @see asd.AsdPackage#getCommunity_Subject()
	 * @see asd.Subject#getMemberOf
	 * @model opposite="memberOf"
	 * @generated
	 */
	Subject getSubject();

	/**
	 * Sets the value of the '{@link asd.Community#getSubject <em>Subject</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Subject</em>' reference.
	 * @see #getSubject()
	 * @generated
	 */
	void setSubject(Subject value);

} // Community
