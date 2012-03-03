/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Specification Diagram</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link urncore.IURNDiagram#getUrndefinition <em>Urndefinition</em>}</li>
 *   <li>{@link urncore.IURNDiagram#getNodes <em>Nodes</em>}</li>
 *   <li>{@link urncore.IURNDiagram#getContRefs <em>Cont Refs</em>}</li>
 *   <li>{@link urncore.IURNDiagram#getConnections <em>Connections</em>}</li>
 *   <li>{@link urncore.IURNDiagram#getConcern <em>Concern</em>}</li>
 *   <li>{@link urncore.IURNDiagram#getComments <em>Comments</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getIURNDiagram()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IURNDiagram extends EObject {
    /**
	 * Returns the value of the '<em><b>Urndefinition</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link urncore.URNdefinition#getSpecDiagrams <em>Spec Diagrams</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Urndefinition</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Urndefinition</em>' container reference.
	 * @see #setUrndefinition(URNdefinition)
	 * @see urncore.UrncorePackage#getIURNDiagram_Urndefinition()
	 * @see urncore.URNdefinition#getSpecDiagrams
	 * @model opposite="specDiagrams" required="true"
	 * @generated
	 */
    URNdefinition getUrndefinition();

    /**
	 * Sets the value of the '{@link urncore.IURNDiagram#getUrndefinition <em>Urndefinition</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Urndefinition</em>' container reference.
	 * @see #getUrndefinition()
	 * @generated
	 */
    void setUrndefinition(URNdefinition value);

    /**
	 * Returns the value of the '<em><b>Nodes</b></em>' containment reference list.
	 * The list contents are of type {@link urncore.IURNNode}.
	 * It is bidirectional and its opposite is '{@link urncore.IURNNode#getDiagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Nodes</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Nodes</em>' containment reference list.
	 * @see urncore.UrncorePackage#getIURNDiagram_Nodes()
	 * @see urncore.IURNNode#getDiagram
	 * @model type="urncore.IURNNode" opposite="diagram" containment="true"
	 * @generated
	 */
    EList getNodes();

    /**
	 * Returns the value of the '<em><b>Cont Refs</b></em>' containment reference list.
	 * The list contents are of type {@link urncore.IURNContainerRef}.
	 * It is bidirectional and its opposite is '{@link urncore.IURNContainerRef#getDiagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Comp Refs</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Cont Refs</em>' containment reference list.
	 * @see urncore.UrncorePackage#getIURNDiagram_ContRefs()
	 * @see urncore.IURNContainerRef#getDiagram
	 * @model type="urncore.IURNContainerRef" opposite="diagram" containment="true"
	 * @generated
	 */
    EList getContRefs();

    /**
	 * Returns the value of the '<em><b>Connections</b></em>' containment reference list.
	 * The list contents are of type {@link urncore.IURNConnection}.
	 * It is bidirectional and its opposite is '{@link urncore.IURNConnection#getDiagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Connections</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Connections</em>' containment reference list.
	 * @see urncore.UrncorePackage#getIURNDiagram_Connections()
	 * @see urncore.IURNConnection#getDiagram
	 * @model type="urncore.IURNConnection" opposite="diagram" containment="true"
	 * @generated
	 */
    EList getConnections();

    /**
	 * Returns the value of the '<em><b>Concern</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link urncore.Concern#getSpecDiagrams <em>Spec Diagrams</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Concern</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Concern</em>' reference.
	 * @see #setConcern(Concern)
	 * @see urncore.UrncorePackage#getIURNDiagram_Concern()
	 * @see urncore.Concern#getSpecDiagrams
	 * @model opposite="specDiagrams"
	 * @generated
	 */
	Concern getConcern();

    /**
	 * Sets the value of the '{@link urncore.IURNDiagram#getConcern <em>Concern</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Concern</em>' reference.
	 * @see #getConcern()
	 * @generated
	 */
	void setConcern(Concern value);

				/**
	 * Returns the value of the '<em><b>Comments</b></em>' containment reference list.
	 * The list contents are of type {@link urncore.Comment}.
	 * It is bidirectional and its opposite is '{@link urncore.Comment#getDiagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Comments</em>' containment reference list.
	 * @see urncore.UrncorePackage#getIURNDiagram_Comments()
	 * @see urncore.Comment#getDiagram
	 * @model type="urncore.Comment" opposite="diagram" containment="true"
	 * @generated
	 */
	EList getComments();

} // SpecificationDiagram
