/**
 */
package fm;

import ca.mcgill.sel.core.COREFeatureModel;

import grl.GRLspec;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link fm.FeatureModel#getGrlspec <em>Grlspec</em>}</li>
 * </ul>
 * </p>
 *
 * @see fm.FmPackage#getFeatureModel()
 * @model
 * @generated
 */
public interface FeatureModel extends COREFeatureModel {
	/**
	 * Returns the value of the '<em><b>Grlspec</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link grl.GRLspec#getFeatureModel <em>Feature Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Grlspec</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grlspec</em>' container reference.
	 * @see #setGrlspec(GRLspec)
	 * @see fm.FmPackage#getFeatureModel_Grlspec()
	 * @see grl.GRLspec#getFeatureModel
	 * @model opposite="featureModel" required="true"
	 * @generated
	 */
	GRLspec getGrlspec();

	/**
	 * Sets the value of the '{@link fm.FeatureModel#getGrlspec <em>Grlspec</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grlspec</em>' container reference.
	 * @see #getGrlspec()
	 * @generated
	 */
	void setGrlspec(GRLspec value);

} // FeatureModel
