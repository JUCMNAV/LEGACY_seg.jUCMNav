/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package seg.jUCMNav.model.ucm;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link seg.jUCMNav.model.ucm.Component#getElements <em>Elements</em>}</li>
 *   <li>{@link seg.jUCMNav.model.ucm.Component#getDiagram <em>Diagram</em>}</li>
 * </ul>
 * </p>
 *
 * @see seg.jUCMNav.model.ucm.UcmPackage#getComponent()
 * @model 
 * @generated
 */
public interface Component extends SizedElement {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' reference list.
	 * The list contents are of type {@link seg.jUCMNav.model.ucm.XYElement}.
	 * It is bidirectional and its opposite is '{@link seg.jUCMNav.model.ucm.XYElement#getComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' reference list.
	 * @see seg.jUCMNav.model.ucm.UcmPackage#getComponent_Elements()
	 * @see seg.jUCMNav.model.ucm.XYElement#getComponent
	 * @model type="seg.jUCMNav.model.ucm.XYElement" opposite="component"
	 * @generated
	 */
	EList getElements();

	/**
	 * Returns the value of the '<em><b>Diagram</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link seg.jUCMNav.model.ucm.UcmDiagram#getComponents <em>Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Diagram</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Diagram</em>' container reference.
	 * @see #setDiagram(UcmDiagram)
	 * @see seg.jUCMNav.model.ucm.UcmPackage#getComponent_Diagram()
	 * @see seg.jUCMNav.model.ucm.UcmDiagram#getComponents
	 * @model opposite="components" required="true"
	 * @generated
	 */
	UcmDiagram getDiagram();

	/**
	 * Sets the value of the '{@link seg.jUCMNav.model.ucm.Component#getDiagram <em>Diagram</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Diagram</em>' container reference.
	 * @see #getDiagram()
	 * @generated
	 */
	void setDiagram(UcmDiagram value);

} // Component
