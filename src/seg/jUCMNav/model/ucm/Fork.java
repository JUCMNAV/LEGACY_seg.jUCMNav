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
 * A representation of the model object '<em><b>Fork</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link seg.jUCMNav.model.ucm.Fork#getInPaths <em>In Paths</em>}</li>
 *   <li>{@link seg.jUCMNav.model.ucm.Fork#getOutPath <em>Out Path</em>}</li>
 * </ul>
 * </p>
 *
 * @see seg.jUCMNav.model.ucm.UcmPackage#getFork()
 * @model 
 * @generated
 */
public interface Fork extends EObject {
	/**
	 * Returns the value of the '<em><b>In Paths</b></em>' containment reference list.
	 * The list contents are of type {@link seg.jUCMNav.model.ucm.Path}.
	 * It is bidirectional and its opposite is '{@link seg.jUCMNav.model.ucm.Path#getInFork <em>In Fork</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Paths</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In Paths</em>' containment reference list.
	 * @see seg.jUCMNav.model.ucm.UcmPackage#getFork_InPaths()
	 * @see seg.jUCMNav.model.ucm.Path#getInFork
	 * @model type="seg.jUCMNav.model.ucm.Path" opposite="inFork" containment="true" required="true"
	 * @generated
	 */
	EList getInPaths();

	/**
	 * Returns the value of the '<em><b>Out Path</b></em>' containment reference list.
	 * The list contents are of type {@link seg.jUCMNav.model.ucm.Path}.
	 * It is bidirectional and its opposite is '{@link seg.jUCMNav.model.ucm.Path#getOutFork <em>Out Fork</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Out Path</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Out Path</em>' containment reference list.
	 * @see seg.jUCMNav.model.ucm.UcmPackage#getFork_OutPath()
	 * @see seg.jUCMNav.model.ucm.Path#getOutFork
	 * @model type="seg.jUCMNav.model.ucm.Path" opposite="outFork" containment="true"
	 * @generated
	 */
	EList getOutPath();

} // Fork
