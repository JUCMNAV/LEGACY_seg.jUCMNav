/**
 */
package core;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CORE Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link core.COREModel#getReuses <em>Reuses</em>}</li>
 *   <li>{@link core.COREModel#getModelElements <em>Model Elements</em>}</li>
 *   <li>{@link core.COREModel#getRealizes <em>Realizes</em>}</li>
 * </ul>
 * </p>
 *
 * @see core.CorePackage#getCOREModel()
 * @model abstract="true"
 *        extendedMetaData="name='COREFeatureModel'"
 * @generated
 */
public interface COREModel extends CORENamedElement {
	/**
	 * Returns the value of the '<em><b>Reuses</b></em>' containment reference list.
	 * The list contents are of type {@link core.COREReuse}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reuses</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reuses</em>' containment reference list.
	 * @see core.CorePackage#getCOREModel_Reuses()
	 * @model type="core.COREReuse" containment="true"
	 * @generated
	 */
	EList getReuses();

	/**
	 * Returns the value of the '<em><b>Model Elements</b></em>' reference list.
	 * The list contents are of type {@link core.COREModelElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model Elements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model Elements</em>' reference list.
	 * @see core.CorePackage#getCOREModel_ModelElements()
	 * @model type="core.COREModelElement" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList getModelElements();

	/**
	 * Returns the value of the '<em><b>Realizes</b></em>' reference list.
	 * The list contents are of type {@link core.COREFeature}.
	 * It is bidirectional and its opposite is '{@link core.COREFeature#getRealizedBy <em>Realized By</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Realizes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Realizes</em>' reference list.
	 * @see core.CorePackage#getCOREModel_Realizes()
	 * @see core.COREFeature#getRealizedBy
	 * @model type="core.COREFeature" opposite="realizedBy"
	 * @generated
	 */
	EList getRealizes();

} // COREModel
