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
 *   <li>{@link urncore.SpecificationDiagram#getUrndefinition <em>Urndefinition</em>}</li>
 *   <li>{@link urncore.SpecificationDiagram#getNodes <em>Nodes</em>}</li>
 *   <li>{@link urncore.SpecificationDiagram#getCompRefs <em>Comp Refs</em>}</li>
 *   <li>{@link urncore.SpecificationDiagram#getConnections <em>Connections</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getSpecificationDiagram()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface SpecificationDiagram extends EObject {
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
     * @see urncore.UrncorePackage#getSpecificationDiagram_Urndefinition()
     * @see urncore.URNdefinition#getSpecDiagrams
     * @model opposite="specDiagrams" required="true"
     * @generated
     */
    URNdefinition getUrndefinition();

    /**
     * Sets the value of the '{@link urncore.SpecificationDiagram#getUrndefinition <em>Urndefinition</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Urndefinition</em>' container reference.
     * @see #getUrndefinition()
     * @generated
     */
    void setUrndefinition(URNdefinition value);

    /**
     * Returns the value of the '<em><b>Nodes</b></em>' containment reference list.
     * The list contents are of type {@link urncore.SpecificationNode}.
     * It is bidirectional and its opposite is '{@link urncore.SpecificationNode#getSpecDiagram <em>Spec Diagram</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Nodes</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Nodes</em>' containment reference list.
     * @see urncore.UrncorePackage#getSpecificationDiagram_Nodes()
     * @see urncore.SpecificationNode#getSpecDiagram
     * @model type="urncore.SpecificationNode" opposite="specDiagram" containment="true"
     * @generated
     */
    EList getNodes();

    /**
     * Returns the value of the '<em><b>Comp Refs</b></em>' containment reference list.
     * The list contents are of type {@link urncore.SpecificationComponentRef}.
     * It is bidirectional and its opposite is '{@link urncore.SpecificationComponentRef#getSpecDiagram <em>Spec Diagram</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Comp Refs</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Comp Refs</em>' containment reference list.
     * @see urncore.UrncorePackage#getSpecificationDiagram_CompRefs()
     * @see urncore.SpecificationComponentRef#getSpecDiagram
     * @model type="urncore.SpecificationComponentRef" opposite="specDiagram" containment="true"
     * @generated
     */
    EList getCompRefs();

    /**
     * Returns the value of the '<em><b>Connections</b></em>' containment reference list.
     * The list contents are of type {@link urncore.SpecificationConnection}.
     * It is bidirectional and its opposite is '{@link urncore.SpecificationConnection#getSpecDiagram <em>Spec Diagram</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Connections</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Connections</em>' containment reference list.
     * @see urncore.UrncorePackage#getSpecificationDiagram_Connections()
     * @see urncore.SpecificationConnection#getSpecDiagram
     * @model type="urncore.SpecificationConnection" opposite="specDiagram" containment="true"
     * @generated
     */
    EList getConnections();

} // SpecificationDiagram
