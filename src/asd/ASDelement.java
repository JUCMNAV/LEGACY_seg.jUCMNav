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
 * <ul>
 *   <li>{@link asd.ASDelement#getDiagrams <em>Diagrams</em>}</li>
 *   <li>{@link asd.ASDelement#getParentElements <em>Parent Elements</em>}</li>
 *   <li>{@link asd.ASDelement#getRefinedElements <em>Refined Elements</em>}</li>
 *   <li>{@link asd.ASDelement#getRequiredOutcomes <em>Required Outcomes</em>}</li>
 * </ul>
 * </p>
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
	 * Returns the value of the '<em><b>Parent Elements</b></em>' reference list.
	 * The list contents are of type {@link asd.ASDelement}.
	 * It is bidirectional and its opposite is '{@link asd.ASDelement#getRefinedElements <em>Refined Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Elements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Elements</em>' reference list.
	 * @see asd.AsdPackage#getASDelement_ParentElements()
	 * @see asd.ASDelement#getRefinedElements
	 * @model type="asd.ASDelement" opposite="refinedElements"
	 * @generated
	 */
	EList getParentElements();

	/**
	 * Returns the value of the '<em><b>Refined Elements</b></em>' reference list.
	 * The list contents are of type {@link asd.ASDelement}.
	 * It is bidirectional and its opposite is '{@link asd.ASDelement#getParentElements <em>Parent Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Refined Elements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Refined Elements</em>' reference list.
	 * @see asd.AsdPackage#getASDelement_RefinedElements()
	 * @see asd.ASDelement#getParentElements
	 * @model type="asd.ASDelement" opposite="parentElements"
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

} // ASDelement
