/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package seg.jUCMNav.model.ucm;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Path</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link seg.jUCMNav.model.ucm.Path#getNodes <em>Nodes</em>}</li>
 *   <li>{@link seg.jUCMNav.model.ucm.Path#getInFork <em>In Fork</em>}</li>
 *   <li>{@link seg.jUCMNav.model.ucm.Path#getOutFork <em>Out Fork</em>}</li>
 *   <li>{@link seg.jUCMNav.model.ucm.Path#getDiagram <em>Diagram</em>}</li>
 *   <li>{@link seg.jUCMNav.model.ucm.Path#getEndpoint <em>Endpoint</em>}</li>
 *   <li>{@link seg.jUCMNav.model.ucm.Path#getStartpoint <em>Startpoint</em>}</li>
 * </ul>
 * </p>
 *
 * @see seg.jUCMNav.model.ucm.UcmPackage#getPath()
 * @model 
 * @generated
 */
public interface Path extends EObject {
	/**
	 * Returns the value of the '<em><b>Nodes</b></em>' reference list.
	 * The list contents are of type {@link seg.jUCMNav.model.ucm.Node}.
	 * It is bidirectional and its opposite is '{@link seg.jUCMNav.model.ucm.Node#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nodes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nodes</em>' reference list.
	 * @see seg.jUCMNav.model.ucm.UcmPackage#getPath_Nodes()
	 * @see seg.jUCMNav.model.ucm.Node#getPath
	 * @model type="seg.jUCMNav.model.ucm.Node" opposite="path" required="true"
	 * @generated
	 */
	EList getNodes();

	/**
	 * Returns the value of the '<em><b>In Fork</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link seg.jUCMNav.model.ucm.Fork#getInPaths <em>In Paths</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Fork</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In Fork</em>' reference.
	 * @see #setInFork(Fork)
	 * @see seg.jUCMNav.model.ucm.UcmPackage#getPath_InFork()
	 * @see seg.jUCMNav.model.ucm.Fork#getInPaths
	 * @model opposite="inPaths" required="true"
	 * @generated
	 */
	Fork getInFork();

	/**
	 * Sets the value of the '{@link seg.jUCMNav.model.ucm.Path#getInFork <em>In Fork</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>In Fork</em>' reference.
	 * @see #getInFork()
	 * @generated
	 */
	void setInFork(Fork value);

	/**
	 * Returns the value of the '<em><b>Out Fork</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link seg.jUCMNav.model.ucm.Fork#getOutPath <em>Out Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Out Fork</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Out Fork</em>' reference.
	 * @see #setOutFork(Fork)
	 * @see seg.jUCMNav.model.ucm.UcmPackage#getPath_OutFork()
	 * @see seg.jUCMNav.model.ucm.Fork#getOutPath
	 * @model opposite="outPath" required="true"
	 * @generated
	 */
	Fork getOutFork();

	/**
	 * Sets the value of the '{@link seg.jUCMNav.model.ucm.Path#getOutFork <em>Out Fork</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Out Fork</em>' reference.
	 * @see #getOutFork()
	 * @generated
	 */
	void setOutFork(Fork value);

	/**
	 * Returns the value of the '<em><b>Diagram</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link seg.jUCMNav.model.ucm.UcmDiagram#getPaths <em>Paths</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Diagram</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Diagram</em>' container reference.
	 * @see #setDiagram(UcmDiagram)
	 * @see seg.jUCMNav.model.ucm.UcmPackage#getPath_Diagram()
	 * @see seg.jUCMNav.model.ucm.UcmDiagram#getPaths
	 * @model opposite="paths" required="true"
	 * @generated
	 */
	UcmDiagram getDiagram();

	/**
	 * Sets the value of the '{@link seg.jUCMNav.model.ucm.Path#getDiagram <em>Diagram</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Diagram</em>' container reference.
	 * @see #getDiagram()
	 * @generated
	 */
	void setDiagram(UcmDiagram value);

	/**
	 * Returns the value of the '<em><b>Endpoint</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Endpoint</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Endpoint</em>' reference.
	 * @see #setEndpoint(EndPoint)
	 * @see seg.jUCMNav.model.ucm.UcmPackage#getPath_Endpoint()
	 * @model required="true"
	 * @generated
	 */
	EndPoint getEndpoint();

	/**
	 * Sets the value of the '{@link seg.jUCMNav.model.ucm.Path#getEndpoint <em>Endpoint</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Endpoint</em>' reference.
	 * @see #getEndpoint()
	 * @generated
	 */
	void setEndpoint(EndPoint value);

	/**
	 * Returns the value of the '<em><b>Startpoint</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Startpoint</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Startpoint</em>' reference.
	 * @see #setStartpoint(StartPoint)
	 * @see seg.jUCMNav.model.ucm.UcmPackage#getPath_Startpoint()
	 * @model required="true"
	 * @generated
	 */
	StartPoint getStartpoint();

	/**
	 * Sets the value of the '{@link seg.jUCMNav.model.ucm.Path#getStartpoint <em>Startpoint</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Startpoint</em>' reference.
	 * @see #getStartpoint()
	 * @generated
	 */
	void setStartpoint(StartPoint value);

} // Path
