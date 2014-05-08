/**
 */
package asd;

import org.eclipse.emf.common.util.EList;

import urncore.IURNDiagram;
import urncore.URNmodelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>AS Diagram</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link asd.ASDiagram#getElements <em>Elements</em>}</li>
 *   <li>{@link asd.ASDiagram#getASD <em>ASD</em>}</li>
 *   <li>{@link asd.ASDiagram#getRelatedASD <em>Related ASD</em>}</li>
 *   <li>{@link asd.ASDiagram#getMediations <em>Mediations</em>}</li>
 *   <li>{@link asd.ASDiagram#getAsdLayouts <em>Asd Layouts</em>}</li>
 *   <li>{@link asd.ASDiagram#getParentDoLs <em>Parent Do Ls</em>}</li>
 * </ul>
 * </p>
 *
 * @see asd.AsdPackage#getASDiagram()
 * @model
 * @generated
 */
public interface ASDiagram extends ASDmodelElement, URNmodelElement, IURNDiagram {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' reference list.
	 * The list contents are of type {@link asd.ASDelement}.
	 * It is bidirectional and its opposite is '{@link asd.ASDelement#getDiagrams <em>Diagrams</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' reference list.
	 * @see asd.AsdPackage#getASDiagram_Elements()
	 * @see asd.ASDelement#getDiagrams
	 * @model type="asd.ASDelement" opposite="diagrams" lower="7"
	 * @generated
	 */
	EList getElements();

	/**
	 * Returns the value of the '<em><b>ASD</b></em>' reference list.
	 * The list contents are of type {@link asd.ASDiagram}.
	 * It is bidirectional and its opposite is '{@link asd.ASDiagram#getRelatedASD <em>Related ASD</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>ASD</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>ASD</em>' reference list.
	 * @see asd.AsdPackage#getASDiagram_ASD()
	 * @see asd.ASDiagram#getRelatedASD
	 * @model type="asd.ASDiagram" opposite="relatedASD"
	 * @generated
	 */
	EList getASD();

	/**
	 * Returns the value of the '<em><b>Related ASD</b></em>' reference list.
	 * The list contents are of type {@link asd.ASDiagram}.
	 * It is bidirectional and its opposite is '{@link asd.ASDiagram#getASD <em>ASD</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Related ASD</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Related ASD</em>' reference list.
	 * @see asd.AsdPackage#getASDiagram_RelatedASD()
	 * @see asd.ASDiagram#getASD
	 * @model type="asd.ASDiagram" opposite="ASD"
	 * @generated
	 */
	EList getRelatedASD();

	/**
	 * Returns the value of the '<em><b>Mediations</b></em>' containment reference list.
	 * The list contents are of type {@link asd.Mediation}.
	 * It is bidirectional and its opposite is '{@link asd.Mediation#getRelevantASD <em>Relevant ASD</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mediations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mediations</em>' containment reference list.
	 * @see asd.AsdPackage#getASDiagram_Mediations()
	 * @see asd.Mediation#getRelevantASD
	 * @model type="asd.Mediation" opposite="relevantASD" containment="true"
	 * @generated
	 */
	EList getMediations();

	/**
	 * Returns the value of the '<em><b>Asd Layouts</b></em>' reference list.
	 * The list contents are of type {@link asd.ASDlayout}.
	 * It is bidirectional and its opposite is '{@link asd.ASDlayout#getAsDiagram <em>As Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Asd Layouts</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Asd Layouts</em>' reference list.
	 * @see asd.AsdPackage#getASDiagram_AsdLayouts()
	 * @see asd.ASDlayout#getAsDiagram
	 * @model type="asd.ASDlayout" opposite="asDiagram"
	 * @generated
	 */
	EList getAsdLayouts();

	/**
	 * Returns the value of the '<em><b>Parent Do Ls</b></em>' reference list.
	 * The list contents are of type {@link asd.DivisionOfLabour}.
	 * It is bidirectional and its opposite is '{@link asd.DivisionOfLabour#getRefinedDiagrams <em>Refined Diagrams</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Do Ls</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Do Ls</em>' reference list.
	 * @see asd.AsdPackage#getASDiagram_ParentDoLs()
	 * @see asd.DivisionOfLabour#getRefinedDiagrams
	 * @model type="asd.DivisionOfLabour" opposite="refinedDiagrams"
	 * @generated
	 */
	EList getParentDoLs();

} // ASDiagram
