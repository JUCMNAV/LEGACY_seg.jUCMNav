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
 * A representation of the model object '<em><b>Diagram</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link seg.jUCMNav.model.ucm.UcmDiagram#getPaths <em>Paths</em>}</li>
 *   <li>{@link seg.jUCMNav.model.ucm.UcmDiagram#getForks <em>Forks</em>}</li>
 *   <li>{@link seg.jUCMNav.model.ucm.UcmDiagram#getComponents <em>Components</em>}</li>
 * </ul>
 * </p>
 *
 * @see seg.jUCMNav.model.ucm.UcmPackage#getUcmDiagram()
 * @model 
 * @generated
 */
public interface UcmDiagram extends EObject{
	/**
	 * Returns the value of the '<em><b>Paths</b></em>' containment reference list.
	 * The list contents are of type {@link seg.jUCMNav.model.ucm.Path}.
	 * It is bidirectional and its opposite is '{@link seg.jUCMNav.model.ucm.Path#getDiagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Paths</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Paths</em>' containment reference list.
	 * @see seg.jUCMNav.model.ucm.UcmPackage#getUcmDiagram_Paths()
	 * @see seg.jUCMNav.model.ucm.Path#getDiagram
	 * @model type="seg.jUCMNav.model.ucm.Path" opposite="diagram" containment="true"
	 * @generated
	 */
	EList getPaths();

	/**
	 * Returns the value of the '<em><b>Forks</b></em>' containment reference list.
	 * The list contents are of type {@link seg.jUCMNav.model.ucm.Fork}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Forks</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Forks</em>' containment reference list.
	 * @see seg.jUCMNav.model.ucm.UcmPackage#getUcmDiagram_Forks()
	 * @model type="seg.jUCMNav.model.ucm.Fork" containment="true"
	 * @generated
	 */
	EList getForks();

	/**
	 * Returns the value of the '<em><b>Components</b></em>' containment reference list.
	 * The list contents are of type {@link seg.jUCMNav.model.ucm.Component}.
	 * It is bidirectional and its opposite is '{@link seg.jUCMNav.model.ucm.Component#getDiagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Components</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Components</em>' containment reference list.
	 * @see seg.jUCMNav.model.ucm.UcmPackage#getUcmDiagram_Components()
	 * @see seg.jUCMNav.model.ucm.Component#getDiagram
	 * @model type="seg.jUCMNav.model.ucm.Component" opposite="diagram" containment="true"
	 * @generated
	 */
	EList getComponents();

} // UcmDiagram
