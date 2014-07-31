/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore;

import ca.mcgill.sel.core.COREConcern;
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
 *   <li>{@link urncore.Concern#getElements <em>Elements</em>}</li>
 *   <li>{@link urncore.Concern#getCoreConcern <em>Core Concern</em>}</li>
 *   <li>{@link urncore.Concern#getCondition <em>Condition</em>}</li>
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

				/**
	 * Returns the value of the '<em><b>Elements</b></em>' reference list.
	 * The list contents are of type {@link urncore.URNmodelElement}.
	 * It is bidirectional and its opposite is '{@link urncore.URNmodelElement#getInconcern <em>Inconcern</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Elements</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' reference list.
	 * @see urncore.UrncorePackage#getConcern_Elements()
	 * @see urncore.URNmodelElement#getInconcern
	 * @model type="urncore.URNmodelElement" opposite="inconcern"
	 * @generated
	 */
    EList getElements();

                /**
	 * Returns the value of the '<em><b>Core Concern</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Core Concern</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Core Concern</em>' reference.
	 * @see #setCoreConcern(COREConcern)
	 * @see urncore.UrncorePackage#getConcern_CoreConcern()
	 * @model
	 * @generated
	 */
	COREConcern getCoreConcern();

				/**
	 * Sets the value of the '{@link urncore.Concern#getCoreConcern <em>Core Concern</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Core Concern</em>' reference.
	 * @see #getCoreConcern()
	 * @generated
	 */
	void setCoreConcern(COREConcern value);

				/**
	 * Returns the value of the '<em><b>Condition</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link urncore.Condition#getConcern <em>Concern</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' containment reference.
	 * @see #setCondition(Condition)
	 * @see urncore.UrncorePackage#getConcern_Condition()
	 * @see urncore.Condition#getConcern
	 * @model opposite="concern" containment="true"
	 * @generated
	 */
	Condition getCondition();

				/**
	 * Sets the value of the '{@link urncore.Concern#getCondition <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' containment reference.
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(Condition value);

} // Concern