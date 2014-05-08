/**
 */
package core;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CORE Configuration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link core.COREConfiguration#getReusedConcern <em>Reused Concern</em>}</li>
 *   <li>{@link core.COREConfiguration#getSelected <em>Selected</em>}</li>
 * </ul>
 * </p>
 *
 * @see core.CorePackage#getCOREConfiguration()
 * @model abstract="true"
 * @generated
 */
public interface COREConfiguration extends CORENamedElement {
	/**
	 * Returns the value of the '<em><b>Reused Concern</b></em>' reference list.
	 * The list contents are of type {@link core.COREConcern}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reused Concern</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reused Concern</em>' reference list.
	 * @see core.CorePackage#getCOREConfiguration_ReusedConcern()
	 * @model type="core.COREConcern"
	 * @generated
	 */
	EList getReusedConcern();

	/**
	 * Returns the value of the '<em><b>Selected</b></em>' reference list.
	 * The list contents are of type {@link core.COREFeature}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Selected</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Selected</em>' reference list.
	 * @see core.CorePackage#getCOREConfiguration_Selected()
	 * @model type="core.COREFeature" required="true"
	 * @generated
	 */
	EList getSelected();

} // COREConfiguration
