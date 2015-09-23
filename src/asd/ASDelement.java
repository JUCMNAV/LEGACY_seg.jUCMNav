/**
 */
package asd;

import org.eclipse.emf.common.util.EList;

import urncore.URNmodelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>AS Delement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link asd.ASDelement#getDiagrams <em>Diagrams</em>}</li>
 *   <li>{@link asd.ASDelement#getParentElement <em>Parent Element</em>}</li>
 *   <li>{@link asd.ASDelement#getRefinedElements <em>Refined Elements</em>}</li>
 *   <li>{@link asd.ASDelement#getRequiredOutcomes <em>Required Outcomes</em>}</li>
 *   <li>{@link asd.ASDelement#getContradictions <em>Contradictions</em>}</li>
 * </ul>
 *
 * @see asd.AsdPackage#getASDelement()
 * @model abstract="true"
 * @generated
 */
public interface ASDelement extends ASDmodelElement, URNmodelElement {
	/**
	 * Returns the value of the '<em><b>Diagrams</b></em>' reference list.
	 * The list contents are of type {@link asd.ASDiagram}.
	 * It is bidirectional and its opposite is '{@link asd.ASDiagram#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Diagrams</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Diagrams</em>' reference list.
	 * @see asd.AsdPackage#getASDelement_Diagrams()
	 * @see asd.ASDiagram#getElements
	 * @model type="asd.ASDiagram" opposite="elements"
	 * @generated
	 */
	EList getDiagrams();

	/**
	 * Returns the value of the '<em><b>Parent Element</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link asd.ASDelement#getRefinedElements <em>Refined Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Element</em>' reference.
	 * @see #setParentElement(ASDelement)
	 * @see asd.AsdPackage#getASDelement_ParentElement()
	 * @see asd.ASDelement#getRefinedElements
	 * @model opposite="refinedElements"
	 * @generated
	 */
	ASDelement getParentElement();

	/**
	 * Sets the value of the '{@link asd.ASDelement#getParentElement <em>Parent Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent Element</em>' reference.
	 * @see #getParentElement()
	 * @generated
	 */
	void setParentElement(ASDelement value);

	/**
	 * Returns the value of the '<em><b>Refined Elements</b></em>' reference list.
	 * The list contents are of type {@link asd.ASDelement}.
	 * It is bidirectional and its opposite is '{@link asd.ASDelement#getParentElement <em>Parent Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Refined Elements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Refined Elements</em>' reference list.
	 * @see asd.AsdPackage#getASDelement_RefinedElements()
	 * @see asd.ASDelement#getParentElement
	 * @model type="asd.ASDelement" opposite="parentElement"
	 * @generated
	 */
	EList getRefinedElements();

	/**
	 * Returns the value of the '<em><b>Required Outcomes</b></em>' reference list.
	 * The list contents are of type {@link asd.Outcome}.
	 * It is bidirectional and its opposite is '{@link asd.Outcome#getEnabledElements <em>Enabled Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Required Outcomes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Required Outcomes</em>' reference list.
	 * @see asd.AsdPackage#getASDelement_RequiredOutcomes()
	 * @see asd.Outcome#getEnabledElements
	 * @model type="asd.Outcome" opposite="enabledElements"
	 * @generated
	 */
	EList getRequiredOutcomes();

	/**
	 * Returns the value of the '<em><b>Contradictions</b></em>' reference list.
	 * The list contents are of type {@link asd.Contradiction}.
	 * It is bidirectional and its opposite is '{@link asd.Contradiction#getConsideredAE <em>Considered AE</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contradictions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contradictions</em>' reference list.
	 * @see asd.AsdPackage#getASDelement_Contradictions()
	 * @see asd.Contradiction#getConsideredAE
	 * @model type="asd.Contradiction" opposite="consideredAE"
	 * @generated
	 */
	EList getContradictions();

} // ASDelement
