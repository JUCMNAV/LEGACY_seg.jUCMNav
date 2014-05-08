/**
 */
package asd;

import org.eclipse.emf.common.util.EList;

import urncore.IURNDiagram;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>AS Network</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link asd.ASNetwork#getASDspec <em>AS Dspec</em>}</li>
 *   <li>{@link asd.ASNetwork#getAsdLayouts <em>Asd Layouts</em>}</li>
 * </ul>
 * </p>
 *
 * @see asd.AsdPackage#getASNetwork()
 * @model
 * @generated
 */
public interface ASNetwork extends IURNDiagram {
	/**
	 * Returns the value of the '<em><b>AS Dspec</b></em>' reference list.
	 * The list contents are of type {@link asd.ASDspec}.
	 * It is bidirectional and its opposite is '{@link asd.ASDspec#getASNetwork <em>AS Network</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>AS Dspec</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>AS Dspec</em>' reference list.
	 * @see asd.AsdPackage#getASNetwork_ASDspec()
	 * @see asd.ASDspec#getASNetwork
	 * @model type="asd.ASDspec" opposite="ASNetwork"
	 * @generated
	 */
	EList getASDspec();

	/**
	 * Returns the value of the '<em><b>Asd Layouts</b></em>' containment reference list.
	 * The list contents are of type {@link asd.ASDlayout}.
	 * It is bidirectional and its opposite is '{@link asd.ASDlayout#getAsNetwork <em>As Network</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Asd Layouts</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Asd Layouts</em>' containment reference list.
	 * @see asd.AsdPackage#getASNetwork_AsdLayouts()
	 * @see asd.ASDlayout#getAsNetwork
	 * @model type="asd.ASDlayout" opposite="asNetwork" containment="true"
	 * @generated
	 */
	EList getAsdLayouts();

} // ASNetwork
