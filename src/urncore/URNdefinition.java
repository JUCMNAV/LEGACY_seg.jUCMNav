/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import urn.URNspec;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>UR Ndefinition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link urncore.URNdefinition#getUrnspec <em>Urnspec</em>}</li>
 *   <li>{@link urncore.URNdefinition#getResponsibilities <em>Responsibilities</em>}</li>
 *   <li>{@link urncore.URNdefinition#getComponents <em>Components</em>}</li>
 *   <li>{@link urncore.URNdefinition#getSpecDiagrams <em>Spec Diagrams</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getURNdefinition()
 * @model
 * @generated
 */
public interface URNdefinition extends EObject {
	/**
	 * Returns the value of the '<em><b>Urnspec</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link urn.URNspec#getUrndef <em>Urndef</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Urnspec</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Urnspec</em>' container reference.
	 * @see #setUrnspec(URNspec)
	 * @see urncore.UrncorePackage#getURNdefinition_Urnspec()
	 * @see urn.URNspec#getUrndef
	 * @model opposite="urndef" required="true"
	 * @generated
	 */
    URNspec getUrnspec();

	/**
	 * Sets the value of the '{@link urncore.URNdefinition#getUrnspec <em>Urnspec</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Urnspec</em>' container reference.
	 * @see #getUrnspec()
	 * @generated
	 */
    void setUrnspec(URNspec value);

	/**
	 * Returns the value of the '<em><b>Responsibilities</b></em>' containment reference list.
	 * The list contents are of type {@link urncore.Responsibility}.
	 * It is bidirectional and its opposite is '{@link urncore.Responsibility#getUrndefinition <em>Urndefinition</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Responsibilities</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Responsibilities</em>' containment reference list.
	 * @see urncore.UrncorePackage#getURNdefinition_Responsibilities()
	 * @see urncore.Responsibility#getUrndefinition
	 * @model type="urncore.Responsibility" opposite="urndefinition" containment="true"
	 * @generated
	 */
    EList getResponsibilities();

	/**
	 * Returns the value of the '<em><b>Components</b></em>' containment reference list.
	 * The list contents are of type {@link urncore.ComponentElement}.
	 * It is bidirectional and its opposite is '{@link urncore.ComponentElement#getUrndefinition <em>Urndefinition</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Components</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Components</em>' containment reference list.
	 * @see urncore.UrncorePackage#getURNdefinition_Components()
	 * @see urncore.ComponentElement#getUrndefinition
	 * @model type="urncore.ComponentElement" opposite="urndefinition" containment="true"
	 * @generated
	 */
    EList getComponents();

	/**
	 * Returns the value of the '<em><b>Spec Diagrams</b></em>' containment reference list.
	 * The list contents are of type {@link urncore.IURNDiagram}.
	 * It is bidirectional and its opposite is '{@link urncore.IURNDiagram#getUrndefinition <em>Urndefinition</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Spec Diagrams</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Spec Diagrams</em>' containment reference list.
	 * @see urncore.UrncorePackage#getURNdefinition_SpecDiagrams()
	 * @see urncore.IURNDiagram#getUrndefinition
	 * @model type="urncore.IURNDiagram" opposite="urndefinition" containment="true"
	 * @generated
	 */
    EList getSpecDiagrams();

} // URNdefinition
