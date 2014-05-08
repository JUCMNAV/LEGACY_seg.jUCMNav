/**
 */
package asd;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mediating Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link asd.MediatingElement#getMediations <em>Mediations</em>}</li>
 * </ul>
 * </p>
 *
 * @see asd.AsdPackage#getMediatingElement()
 * @model abstract="true"
 * @generated
 */
public interface MediatingElement extends ASDelement {
	/**
	 * Returns the value of the '<em><b>Mediations</b></em>' reference list.
	 * The list contents are of type {@link asd.Mediation}.
	 * It is bidirectional and its opposite is '{@link asd.Mediation#getMediatedBy <em>Mediated By</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mediations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mediations</em>' reference list.
	 * @see asd.AsdPackage#getMediatingElement_Mediations()
	 * @see asd.Mediation#getMediatedBy
	 * @model type="asd.Mediation" opposite="mediatedBy"
	 * @generated
	 */
	EList getMediations();

} // MediatingElement
