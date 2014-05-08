/**
 */
package core;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CORE Composition Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link core.CORECompositionSpecification#getSource <em>Source</em>}</li>
 * </ul>
 * </p>
 *
 * @see core.CorePackage#getCORECompositionSpecification()
 * @model abstract="true"
 * @generated
 */
public interface CORECompositionSpecification extends EObject {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(COREModel)
	 * @see core.CorePackage#getCORECompositionSpecification_Source()
	 * @model required="true"
	 * @generated
	 */
	COREModel getSource();

	/**
	 * Sets the value of the '{@link core.CORECompositionSpecification#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(COREModel value);

} // CORECompositionSpecification
