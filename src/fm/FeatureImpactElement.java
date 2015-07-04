/**
 */
package fm;

import ca.mcgill.sel.core.COREFeatureImpactNode;

import grl.IntentionalElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Impact Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fm.FeatureImpactElement#getCoreFeatureImpactNode <em>Core Feature Impact Node</em>}</li>
 * </ul>
 *
 * @see fm.FmPackage#getFeatureImpactElement()
 * @model
 * @generated
 */
public interface FeatureImpactElement extends IntentionalElement {
	/**
	 * Returns the value of the '<em><b>Core Feature Impact Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Core Feature Impact Node</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Core Feature Impact Node</em>' reference.
	 * @see #setCoreFeatureImpactNode(COREFeatureImpactNode)
	 * @see fm.FmPackage#getFeatureImpactElement_CoreFeatureImpactNode()
	 * @model
	 * @generated
	 */
	COREFeatureImpactNode getCoreFeatureImpactNode();

	/**
	 * Sets the value of the '{@link fm.FeatureImpactElement#getCoreFeatureImpactNode <em>Core Feature Impact Node</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Core Feature Impact Node</em>' reference.
	 * @see #getCoreFeatureImpactNode()
	 * @generated
	 */
	void setCoreFeatureImpactNode(COREFeatureImpactNode value);

} // FeatureImpactElement
