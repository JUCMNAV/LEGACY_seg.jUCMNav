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
 *   <li>{@link seg.jUCMNav.model.ucm.Node#getDiagram <em>Diagram</em>}</li>
 *   <li>{@link seg.jUCMNav.model.ucm.Node#getUpLink <em>Up Link</em>}</li>
 *   <li>{@link seg.jUCMNav.model.ucm.Node#getDownLink <em>Down Link</em>}</li>
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
	 * Returns the value of the '<em><b>Diagram</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link seg.jUCMNav.model.ucm.UcmDiagram#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Diagram</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Diagram</em>' container reference.
	 * @see #setDiagram(UcmDiagram)
	 * @see seg.jUCMNav.model.ucm.UcmPackage#getNode_Diagram()
	 * @see seg.jUCMNav.model.ucm.UcmDiagram#getNodes
	 * @model opposite="nodes" required="true"
	 * @generated
	 */
	UcmDiagram getDiagram();

	/**
	 * Sets the value of the '{@link seg.jUCMNav.model.ucm.Node#getDiagram <em>Diagram</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Diagram</em>' container reference.
	 * @see #getDiagram()
	 * @generated
	 */
	void setDiagram(UcmDiagram value);

	/**
	 * Returns the value of the '<em><b>Up Link</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link seg.jUCMNav.model.ucm.Link#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Up Link</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Up Link</em>' reference.
	 * @see #setUpLink(Link)
	 * @see seg.jUCMNav.model.ucm.UcmPackage#getNode_UpLink()
	 * @see seg.jUCMNav.model.ucm.Link#getTarget
	 * @model opposite="target"
	 * @generated
	 */
	Link getUpLink();

	/**
	 * Sets the value of the '{@link seg.jUCMNav.model.ucm.Node#getUpLink <em>Up Link</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Up Link</em>' reference.
	 * @see #getUpLink()
	 * @generated
	 */
	void setUpLink(Link value);

	/**
	 * Returns the value of the '<em><b>Down Link</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link seg.jUCMNav.model.ucm.Link#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Down Link</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Down Link</em>' reference.
	 * @see #setDownLink(Link)
	 * @see seg.jUCMNav.model.ucm.UcmPackage#getNode_DownLink()
	 * @see seg.jUCMNav.model.ucm.Link#getSource
	 * @model opposite="source"
	 * @generated
	 */
	Link getDownLink();

	/**
	 * Sets the value of the '{@link seg.jUCMNav.model.ucm.Node#getDownLink <em>Down Link</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Down Link</em>' reference.
	 * @see #getDownLink()
	 * @generated
	 */
	void setDownLink(Link value);

	/**
	 * Returns the value of the '<em><b>Path</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link seg.jUCMNav.model.ucm.Path#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Path</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Path</em>' reference.
	 * @see #setPath(Path)
	 * @see seg.jUCMNav.model.ucm.UcmPackage#getNode_Path()
	 * @see seg.jUCMNav.model.ucm.Path#getNodes
	 * @model opposite="nodes" required="true"
	 * @generated
	 */
	Path getPath();

	/**
	 * Sets the value of the '{@link seg.jUCMNav.model.ucm.Node#getPath <em>Path</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path</em>' reference.
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
