/**
 */
package core;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CORE Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link core.COREMapping#getMappedTo <em>Mapped To</em>}</li>
 *   <li>{@link core.COREMapping#getMappedFrom <em>Mapped From</em>}</li>
 * </ul>
 * </p>
 *
 * @see core.CorePackage#getCOREMapping()
 * @model
 * @generated
 */
public interface COREMapping extends EObject {
	/**
	 * Returns the value of the '<em><b>Mapped To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mapped To</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mapped To</em>' reference.
	 * @see #setMappedTo(COREModelElement)
	 * @see core.CorePackage#getCOREMapping_MappedTo()
	 * @model required="true"
	 * @generated
	 */
	COREModelElement getMappedTo();

	/**
	 * Sets the value of the '{@link core.COREMapping#getMappedTo <em>Mapped To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mapped To</em>' reference.
	 * @see #getMappedTo()
	 * @generated
	 */
	void setMappedTo(COREModelElement value);

	/**
	 * Returns the value of the '<em><b>Mapped From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mapped From</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mapped From</em>' reference.
	 * @see #setMappedFrom(COREModelElement)
	 * @see core.CorePackage#getCOREMapping_MappedFrom()
	 * @model required="true"
	 * @generated
	 */
	COREModelElement getMappedFrom();

	/**
	 * Sets the value of the '{@link core.COREMapping#getMappedFrom <em>Mapped From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mapped From</em>' reference.
	 * @see #getMappedFrom()
	 * @generated
	 */
	void setMappedFrom(COREModelElement value);

} // COREMapping
