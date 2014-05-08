/**
 */
package asd;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mediated Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link asd.MediatedElement#getMediations <em>Mediations</em>}</li>
 * </ul>
 * </p>
 *
 * @see asd.AsdPackage#getMediatedElement()
 * @model abstract="true"
 * @generated
 */
public interface MediatedElement extends ASDelement {
	/**
	 * Returns the value of the '<em><b>Mediations</b></em>' reference list.
	 * The list contents are of type {@link asd.Mediation}.
	 * It is bidirectional and its opposite is '{@link asd.Mediation#getMediates <em>Mediates</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mediations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mediations</em>' reference list.
	 * @see asd.AsdPackage#getMediatedElement_Mediations()
	 * @see asd.Mediation#getMediates
	 * @model type="asd.Mediation" opposite="mediates"
	 * @generated
	 */
	EList getMediations();

} // MediatedElement
