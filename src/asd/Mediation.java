/**
 */
package asd;

import org.eclipse.emf.common.util.EList;

import urncore.URNmodelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mediation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link asd.Mediation#getMediatedBy <em>Mediated By</em>}</li>
 *   <li>{@link asd.Mediation#getMediates <em>Mediates</em>}</li>
 *   <li>{@link asd.Mediation#getRelevantASD <em>Relevant ASD</em>}</li>
 * </ul>
 * </p>
 *
 * @see asd.AsdPackage#getMediation()
 * @model
 * @generated
 */
public interface Mediation extends ASDmodelElement, URNmodelElement {
	/**
	 * Returns the value of the '<em><b>Mediated By</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link asd.MediatingElement#getMediations <em>Mediations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mediated By</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mediated By</em>' reference.
	 * @see #setMediatedBy(MediatingElement)
	 * @see asd.AsdPackage#getMediation_MediatedBy()
	 * @see asd.MediatingElement#getMediations
	 * @model opposite="mediations" required="true"
	 * @generated
	 */
	MediatingElement getMediatedBy();

	/**
	 * Sets the value of the '{@link asd.Mediation#getMediatedBy <em>Mediated By</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mediated By</em>' reference.
	 * @see #getMediatedBy()
	 * @generated
	 */
	void setMediatedBy(MediatingElement value);

	/**
	 * Returns the value of the '<em><b>Mediates</b></em>' reference list.
	 * The list contents are of type {@link asd.MediatedElement}.
	 * It is bidirectional and its opposite is '{@link asd.MediatedElement#getMediations <em>Mediations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mediates</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mediates</em>' reference list.
	 * @see asd.AsdPackage#getMediation_Mediates()
	 * @see asd.MediatedElement#getMediations
	 * @model type="asd.MediatedElement" opposite="mediations" lower="2" upper="2"
	 * @generated
	 */
	EList getMediates();

	/**
	 * Returns the value of the '<em><b>Relevant ASD</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link asd.ASDiagram#getMediations <em>Mediations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Relevant ASD</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relevant ASD</em>' container reference.
	 * @see #setRelevantASD(ASDiagram)
	 * @see asd.AsdPackage#getMediation_RelevantASD()
	 * @see asd.ASDiagram#getMediations
	 * @model opposite="mediations" required="true"
	 * @generated
	 */
	ASDiagram getRelevantASD();

	/**
	 * Sets the value of the '{@link asd.Mediation#getRelevantASD <em>Relevant ASD</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Relevant ASD</em>' container reference.
	 * @see #getRelevantASD()
	 * @generated
	 */
	void setRelevantASD(ASDiagram value);

} // Mediation
