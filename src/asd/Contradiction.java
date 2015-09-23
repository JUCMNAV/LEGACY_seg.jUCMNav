/**
 */
package asd;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Contradiction</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link asd.Contradiction#getContradictingAEs <em>Contradicting AEs</em>}</li>
 *   <li>{@link asd.Contradiction#getConsideredAE <em>Considered AE</em>}</li>
 *   <li>{@link asd.Contradiction#getDiagram <em>Diagram</em>}</li>
 * </ul>
 *
 * @see asd.AsdPackage#getContradiction()
 * @model
 * @generated
 */
public interface Contradiction extends ASDmodelElement {
	/**
	 * Returns the value of the '<em><b>Contradicting AEs</b></em>' reference list.
	 * The list contents are of type {@link asd.ASDelement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contradicting AEs</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contradicting AEs</em>' reference list.
	 * @see asd.AsdPackage#getContradiction_ContradictingAEs()
	 * @model type="asd.ASDelement"
	 * @generated
	 */
	EList getContradictingAEs();

	/**
	 * Returns the value of the '<em><b>Considered AE</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link asd.ASDelement#getContradictions <em>Contradictions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Considered AE</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Considered AE</em>' reference.
	 * @see #setConsideredAE(ASDelement)
	 * @see asd.AsdPackage#getContradiction_ConsideredAE()
	 * @see asd.ASDelement#getContradictions
	 * @model opposite="contradictions" required="true"
	 * @generated
	 */
	ASDelement getConsideredAE();

	/**
	 * Sets the value of the '{@link asd.Contradiction#getConsideredAE <em>Considered AE</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Considered AE</em>' reference.
	 * @see #getConsideredAE()
	 * @generated
	 */
	void setConsideredAE(ASDelement value);

	/**
	 * Returns the value of the '<em><b>Diagram</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link asd.ASDiagram#getContradictions <em>Contradictions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Diagram</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Diagram</em>' reference.
	 * @see #setDiagram(ASDiagram)
	 * @see asd.AsdPackage#getContradiction_Diagram()
	 * @see asd.ASDiagram#getContradictions
	 * @model opposite="contradictions" required="true"
	 * @generated
	 */
	ASDiagram getDiagram();

	/**
	 * Sets the value of the '{@link asd.Contradiction#getDiagram <em>Diagram</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Diagram</em>' reference.
	 * @see #getDiagram()
	 * @generated
	 */
	void setDiagram(ASDiagram value);

} // Contradiction
