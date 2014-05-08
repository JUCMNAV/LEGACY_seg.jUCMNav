/**
 */
package core;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CORE Feature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link core.COREFeature#getRealizedBy <em>Realized By</em>}</li>
 *   <li>{@link core.COREFeature#getStrategies <em>Strategies</em>}</li>
 *   <li>{@link core.COREFeature#getConfigurations <em>Configurations</em>}</li>
 * </ul>
 * </p>
 *
 * @see core.CorePackage#getCOREFeature()
 * @model abstract="true"
 * @generated
 */
public interface COREFeature extends COREModelElement {
	/**
	 * Returns the value of the '<em><b>Realized By</b></em>' reference list.
	 * The list contents are of type {@link core.COREModel}.
	 * It is bidirectional and its opposite is '{@link core.COREModel#getRealizes <em>Realizes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realized By</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Realized By</em>' reference list.
	 * @see core.CorePackage#getCOREFeature_RealizedBy()
	 * @see core.COREModel#getRealizes
	 * @model type="core.COREModel" opposite="realizes"
	 * @generated
	 */
	EList getRealizedBy();

	/**
	 * Returns the value of the '<em><b>Strategies</b></em>' containment reference list.
	 * The list contents are of type {@link core.COREStrategy}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Strategies</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Strategies</em>' containment reference list.
	 * @see core.CorePackage#getCOREFeature_Strategies()
	 * @model type="core.COREStrategy" containment="true"
	 * @generated
	 */
	EList getStrategies();

	/**
	 * Returns the value of the '<em><b>Configurations</b></em>' containment reference list.
	 * The list contents are of type {@link core.COREConfiguration}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Configurations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Configurations</em>' containment reference list.
	 * @see core.CorePackage#getCOREFeature_Configurations()
	 * @model type="core.COREConfiguration" containment="true"
	 * @generated
	 */
	EList getConfigurations();

} // COREFeature
