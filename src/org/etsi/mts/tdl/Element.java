/**
 */
package org.etsi.mts.tdl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.Element#getComments <em>Comment</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.Element#getAnnotations <em>Annotation</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.Element#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etsi.mts.tdl.TdlPackage#getElement()
 * @model abstract="true"
 * @generated
 */
public interface Element extends EObject {
	/**
	 * Returns the value of the '<em><b>Comment</b></em>' containment reference list.
	 * The list contents are of type {@link org.etsi.mts.tdl.Comment}.
	 * It is bidirectional and its opposite is '{@link org.etsi.mts.tdl.Comment#getCommentedElement <em>Commented Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comment</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Comment</em>' containment reference list.
	 * @see org.etsi.mts.tdl.TdlPackage#getElement_Comment()
	 * @see org.etsi.mts.tdl.Comment#getCommentedElement
	 * @model opposite="commentedElement" containment="true" ordered="false"
	 * @generated
	 */
	EList<Comment> getComments();

	/**
	 * Creates a new {@link org.etsi.mts.tdl.Comment} and appends it to the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return The new {@link org.etsi.mts.tdl.Comment}.
	 * @see #getComments()
	 * @generated
	 */
	Comment createComment();

	/**
	 * Returns the value of the '<em><b>Annotation</b></em>' containment reference list.
	 * The list contents are of type {@link org.etsi.mts.tdl.Annotation}.
	 * It is bidirectional and its opposite is '{@link org.etsi.mts.tdl.Annotation#getAnnotatedElement <em>Annotated Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Annotation</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Annotation</em>' containment reference list.
	 * @see org.etsi.mts.tdl.TdlPackage#getElement_Annotation()
	 * @see org.etsi.mts.tdl.Annotation#getAnnotatedElement
	 * @model opposite="annotatedElement" containment="true" ordered="false"
	 * @generated
	 */
	EList<Annotation> getAnnotations();

	/**
	 * Creates a new {@link org.etsi.mts.tdl.Annotation} and appends it to the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return The new {@link org.etsi.mts.tdl.Annotation}.
	 * @see #getAnnotations()
	 * @generated
	 */
	Annotation createAnnotation();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.etsi.mts.tdl.TdlPackage#getElement_Name()
	 * @model unique="false" dataType="org.eclipse.uml2.types.String" ordered="false"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.Element#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // Element
