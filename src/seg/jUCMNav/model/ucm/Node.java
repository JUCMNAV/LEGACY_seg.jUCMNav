/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package seg.jUCMNav.model.ucm;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link seg.jUCMNav.model.ucm.Node#getPath <em>Path</em>}</li>
 *   <li>{@link seg.jUCMNav.model.ucm.Node#getNext <em>Next</em>}</li>
 *   <li>{@link seg.jUCMNav.model.ucm.Node#getPrevious <em>Previous</em>}</li>
 * </ul>
 * </p>
 *
 * @see seg.jUCMNav.model.ucm.UcmPackage#getNode()
 * @model 
 * @generated
 */
public interface Node extends XYElement {
	/**
	 * Returns the value of the '<em><b>Path</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link seg.jUCMNav.model.ucm.Path#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Path</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Path</em>' container reference.
	 * @see #setPath(Path)
	 * @see seg.jUCMNav.model.ucm.UcmPackage#getNode_Path()
	 * @see seg.jUCMNav.model.ucm.Path#getNodes
	 * @model opposite="nodes" required="true"
	 * @generated
	 */
	Path getPath();

	/**
	 * Sets the value of the '{@link seg.jUCMNav.model.ucm.Node#getPath <em>Path</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path</em>' container reference.
	 * @see #getPath()
	 * @generated
	 */
	void setPath(Path value);

	/**
	 * Returns the value of the '<em><b>Next</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link seg.jUCMNav.model.ucm.Node#getPrevious <em>Previous</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Next</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Next</em>' reference.
	 * @see #setNext(Node)
	 * @see seg.jUCMNav.model.ucm.UcmPackage#getNode_Next()
	 * @see seg.jUCMNav.model.ucm.Node#getPrevious
	 * @model opposite="previous" required="true"
	 * @generated
	 */
	Node getNext();

	/**
	 * Sets the value of the '{@link seg.jUCMNav.model.ucm.Node#getNext <em>Next</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Next</em>' reference.
	 * @see #getNext()
	 * @generated
	 */
	void setNext(Node value);

	/**
	 * Returns the value of the '<em><b>Previous</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link seg.jUCMNav.model.ucm.Node#getNext <em>Next</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Previous</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Previous</em>' reference.
	 * @see #setPrevious(Node)
	 * @see seg.jUCMNav.model.ucm.UcmPackage#getNode_Previous()
	 * @see seg.jUCMNav.model.ucm.Node#getNext
	 * @model opposite="next" required="true"
	 * @generated
	 */
	Node getPrevious();

	/**
	 * Sets the value of the '{@link seg.jUCMNav.model.ucm.Node#getPrevious <em>Previous</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Previous</em>' reference.
	 * @see #getPrevious()
	 * @generated
	 */
	void setPrevious(Node value);

} // Node
