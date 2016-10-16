/**
 */
package fm;

import ca.mcgill.sel.core.COREFeatureModel;

import grl.GRLspec;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fm.FeatureModel#getGrlspec <em>Grlspec</em>}</li>
 *   <li>{@link fm.FeatureModel#getCoreFeatureModel <em>Core Feature Model</em>}</li>
 * </ul>
 *
 * @see fm.FmPackage#getFeatureModel()
 * @model
 * @generated
 */
public interface FeatureModel extends EObject {
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

	/**
	 * Returns the value of the '<em><b>Core Feature Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Core Feature Model</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Core Feature Model</em>' reference.
	 * @see #setCoreFeatureModel(COREFeatureModel)
	 * @see fm.FmPackage#getFeatureModel_CoreFeatureModel()
	 * @model
	 * @generated
	 */
	COREFeatureModel getCoreFeatureModel();

	/**
	 * Sets the value of the '{@link fm.FeatureModel#getCoreFeatureModel <em>Core Feature Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Core Feature Model</em>' reference.
	 * @see #getCoreFeatureModel()
	 * @generated
	 */
	void setCoreFeatureModel(COREFeatureModel value);

} // FeatureModel
