/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package seg.jUCMNav.model.ucm;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link seg.jUCMNav.model.ucm.Link#getTarget <em>Target</em>}</li>
 *   <li>{@link seg.jUCMNav.model.ucm.Link#getSource <em>Source</em>}</li>
 * </ul>
 * </p>
 *
 * @see seg.jUCMNav.model.ucm.UcmPackage#getLink()
 * @model 
 * @generated
 */
public interface Link extends EObject {
	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link seg.jUCMNav.model.ucm.Node#getUpLink <em>Up Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(Node)
	 * @see seg.jUCMNav.model.ucm.UcmPackage#getLink_Target()
	 * @see seg.jUCMNav.model.ucm.Node#getUpLink
	 * @model opposite="upLink" required="true"
	 * @generated
	 */
	Node getTarget();

	/**
	 * Sets the value of the '{@link seg.jUCMNav.model.ucm.Link#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(Node value);

	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link seg.jUCMNav.model.ucm.Node#getDownLink <em>Down Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(Node)
	 * @see seg.jUCMNav.model.ucm.UcmPackage#getLink_Source()
	 * @see seg.jUCMNav.model.ucm.Node#getDownLink
	 * @model opposite="downLink" required="true"
	 * @generated
	 */
	Node getSource();

	/**
	 * Sets the value of the '{@link seg.jUCMNav.model.ucm.Link#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(Node value);

} // Link
