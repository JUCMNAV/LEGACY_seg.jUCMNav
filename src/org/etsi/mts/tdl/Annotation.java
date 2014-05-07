/**
 */
package org.etsi.mts.tdl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Annotation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.Annotation#getValue <em>Value</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.Annotation#getKey <em>Key</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.Annotation#getAnnotatedElement <em>Annotated Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etsi.mts.tdl.TdlPackage#getAnnotation()
 * @model
 * @generated
 */
public interface Annotation extends Element {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see org.etsi.mts.tdl.TdlPackage#getAnnotation_Value()
	 * @model unique="false" dataType="org.eclipse.uml2.types.String" ordered="false"
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.Annotation#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

	/**
	 * Returns the value of the '<em><b>Key</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Key</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Key</em>' reference.
	 * @see #setKey(AnnotationType)
	 * @see org.etsi.mts.tdl.TdlPackage#getAnnotation_Key()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	AnnotationType getKey();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.Annotation#getKey <em>Key</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Key</em>' reference.
	 * @see #getKey()
	 * @generated
	 */
	void setKey(AnnotationType value);

	/**
	 * Returns the value of the '<em><b>Annotated Element</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.etsi.mts.tdl.Element#getAnnotations <em>Annotation</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Annotated Element</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Annotated Element</em>' container reference.
	 * @see #setAnnotatedElement(Element)
	 * @see org.etsi.mts.tdl.TdlPackage#getAnnotation_AnnotatedElement()
	 * @see org.etsi.mts.tdl.Element#getAnnotations
	 * @model opposite="annotation" required="true" transient="false" ordered="false"
	 * @generated
	 */
	Element getAnnotatedElement();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.Annotation#getAnnotatedElement <em>Annotated Element</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Annotated Element</em>' container reference.
	 * @see #getAnnotatedElement()
	 * @generated
	 */
	void setAnnotatedElement(Element value);

} // Annotation
