/**
 */
package urn.dyncontext;

import org.eclipse.emf.common.util.EList;

import urn.URNspec;

import urncore.URNmodelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dynamic Context Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link urn.dyncontext.DynamicContextGroup#getUrnspec <em>Urnspec</em>}</li>
 *   <li>{@link urn.dyncontext.DynamicContextGroup#getContexts <em>Contexts</em>}</li>
 * </ul>
 *
 * @see urn.dyncontext.DyncontextPackage#getDynamicContextGroup()
 * @model
 * @generated
 */
public interface DynamicContextGroup extends URNmodelElement {
	/**
	 * Returns the value of the '<em><b>Urnspec</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link urn.URNspec#getDynamicContextGroups <em>Dynamic Context Groups</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Urnspec</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Urnspec</em>' container reference.
	 * @see #setUrnspec(URNspec)
	 * @see urn.dyncontext.DyncontextPackage#getDynamicContextGroup_Urnspec()
	 * @see urn.URNspec#getDynamicContextGroups
	 * @model opposite="dynamicContextGroups" required="true"
	 * @generated
	 */
	URNspec getUrnspec();

	/**
	 * Sets the value of the '{@link urn.dyncontext.DynamicContextGroup#getUrnspec <em>Urnspec</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Urnspec</em>' container reference.
	 * @see #getUrnspec()
	 * @generated
	 */
	void setUrnspec(URNspec value);

	/**
	 * Returns the value of the '<em><b>Contexts</b></em>' reference list.
	 * The list contents are of type {@link urn.dyncontext.DynamicContext}.
	 * It is bidirectional and its opposite is '{@link urn.dyncontext.DynamicContext#getGroups <em>Groups</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contexts</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contexts</em>' reference list.
	 * @see urn.dyncontext.DyncontextPackage#getDynamicContextGroup_Contexts()
	 * @see urn.dyncontext.DynamicContext#getGroups
	 * @model type="urn.dyncontext.DynamicContext" opposite="groups"
	 * @generated
	 */
	EList getContexts();

} // DynamicContextGroup
