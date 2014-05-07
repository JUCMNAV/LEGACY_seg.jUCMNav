/**
 */
package org.etsi.mts.tdl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Comment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.Comment#getBody <em>Body</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.Comment#getCommentedElement <em>Commented Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etsi.mts.tdl.TdlPackage#getComment()
 * @model
 * @generated
 */
public interface Comment extends Element {
	/**
	 * Returns the value of the '<em><b>Body</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Body</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Body</em>' attribute.
	 * @see #setBody(String)
	 * @see org.etsi.mts.tdl.TdlPackage#getComment_Body()
	 * @model unique="false" dataType="org.eclipse.uml2.types.String" required="true" ordered="false"
	 * @generated
	 */
	String getBody();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.Comment#getBody <em>Body</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Body</em>' attribute.
	 * @see #getBody()
	 * @generated
	 */
	void setBody(String value);

	/**
	 * Returns the value of the '<em><b>Commented Element</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.etsi.mts.tdl.Element#getComments <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Commented Element</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Commented Element</em>' container reference.
	 * @see #setCommentedElement(Element)
	 * @see org.etsi.mts.tdl.TdlPackage#getComment_CommentedElement()
	 * @see org.etsi.mts.tdl.Element#getComments
	 * @model opposite="comment" required="true" transient="false" ordered="false"
	 * @generated
	 */
	Element getCommentedElement();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.Comment#getCommentedElement <em>Commented Element</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Commented Element</em>' container reference.
	 * @see #getCommentedElement()
	 * @generated
	 */
	void setCommentedElement(Element value);

} // Comment
