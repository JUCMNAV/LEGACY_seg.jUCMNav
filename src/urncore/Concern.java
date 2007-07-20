/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Concern</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link urncore.Concern#getUrndefinition <em>Urndefinition</em>}</li>
 *   <li>{@link urncore.Concern#getSpecDiagrams <em>Spec Diagrams</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getConcern()
 * @model
 * @generated
 */
public interface Concern extends URNmodelElement {
    /**
     * Returns the value of the '<em><b>Urndefinition</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link urncore.URNdefinition#getConcerns <em>Concerns</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Urndefinition</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Urndefinition</em>' container reference.
     * @see #setUrndefinition(URNdefinition)
     * @see urncore.UrncorePackage#getConcern_Urndefinition()
     * @see urncore.URNdefinition#getConcerns
     * @model opposite="concerns" required="true"
     * @generated
     */
	URNdefinition getUrndefinition();

    /**
     * Sets the value of the '{@link urncore.Concern#getUrndefinition <em>Urndefinition</em>}' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Urndefinition</em>' container reference.
     * @see #getUrndefinition()
     * @generated
     */
	void setUrndefinition(URNdefinition value);

    /**
     * Returns the value of the '<em><b>Spec Diagrams</b></em>' reference list.
     * The list contents are of type {@link urncore.IURNDiagram}.
     * It is bidirectional and its opposite is '{@link urncore.IURNDiagram#getConcern <em>Concern</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Spec Diagrams</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Spec Diagrams</em>' reference list.
     * @see urncore.UrncorePackage#getConcern_SpecDiagrams()
     * @see urncore.IURNDiagram#getConcern
     * @model type="urncore.IURNDiagram" opposite="concern"
     * @generated
     */
	EList getSpecDiagrams();

} // Concern