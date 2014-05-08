/**
 */
package asd;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Division Of Labour</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link asd.DivisionOfLabour#getRules <em>Rules</em>}</li>
 *   <li>{@link asd.DivisionOfLabour#getRefinedDiagrams <em>Refined Diagrams</em>}</li>
 *   <li>{@link asd.DivisionOfLabour#getPerformedBy <em>Performed By</em>}</li>
 *   <li>{@link asd.DivisionOfLabour#getAsdSpec <em>Asd Spec</em>}</li>
 * </ul>
 * </p>
 *
 * @see asd.AsdPackage#getDivisionOfLabour()
 * @model
 * @generated
 */
public interface DivisionOfLabour extends MediatingElement {
	/**
	 * Returns the value of the '<em><b>Rules</b></em>' reference list.
	 * The list contents are of type {@link asd.Rule}.
	 * It is bidirectional and its opposite is '{@link asd.Rule#getDols <em>Dols</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rules</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rules</em>' reference list.
	 * @see asd.AsdPackage#getDivisionOfLabour_Rules()
	 * @see asd.Rule#getDols
	 * @model type="asd.Rule" opposite="dols"
	 * @generated
	 */
	EList getRules();

	/**
	 * Returns the value of the '<em><b>Refined Diagrams</b></em>' reference list.
	 * The list contents are of type {@link asd.ASDiagram}.
	 * It is bidirectional and its opposite is '{@link asd.ASDiagram#getParentDoLs <em>Parent Do Ls</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Refined Diagrams</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Refined Diagrams</em>' reference list.
	 * @see asd.AsdPackage#getDivisionOfLabour_RefinedDiagrams()
	 * @see asd.ASDiagram#getParentDoLs
	 * @model type="asd.ASDiagram" opposite="parentDoLs"
	 * @generated
	 */
	EList getRefinedDiagrams();

	/**
	 * Returns the value of the '<em><b>Performed By</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link asd.Community#getPerforms <em>Performs</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Performed By</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Performed By</em>' reference.
	 * @see #setPerformedBy(Community)
	 * @see asd.AsdPackage#getDivisionOfLabour_PerformedBy()
	 * @see asd.Community#getPerforms
	 * @model opposite="performs" required="true"
	 * @generated
	 */
	Community getPerformedBy();

	/**
	 * Sets the value of the '{@link asd.DivisionOfLabour#getPerformedBy <em>Performed By</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Performed By</em>' reference.
	 * @see #getPerformedBy()
	 * @generated
	 */
	void setPerformedBy(Community value);

	/**
	 * Returns the value of the '<em><b>Asd Spec</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link asd.ASDspec#getDols <em>Dols</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Asd Spec</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Asd Spec</em>' container reference.
	 * @see #setAsdSpec(ASDspec)
	 * @see asd.AsdPackage#getDivisionOfLabour_AsdSpec()
	 * @see asd.ASDspec#getDols
	 * @model opposite="dols" required="true"
	 * @generated
	 */
	ASDspec getAsdSpec();

	/**
	 * Sets the value of the '{@link asd.DivisionOfLabour#getAsdSpec <em>Asd Spec</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Asd Spec</em>' container reference.
	 * @see #getAsdSpec()
	 * @generated
	 */
	void setAsdSpec(ASDspec value);

} // DivisionOfLabour
