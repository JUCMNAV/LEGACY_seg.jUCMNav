/**
 */
package org.etsi.mts.tdl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element Import</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.ElementImport#getImportedElements <em>Imported Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etsi.mts.tdl.TdlPackage#getElementImport()
 * @model
 * @generated
 */
public interface ElementImport extends Element {
	/**
	 * Returns the value of the '<em><b>Imported Element</b></em>' reference list.
	 * The list contents are of type {@link org.etsi.mts.tdl.PackageableElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Imported Element</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Imported Element</em>' reference list.
	 * @see org.etsi.mts.tdl.TdlPackage#getElementImport_ImportedElement()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	EList<PackageableElement> getImportedElements();

} // ElementImport
