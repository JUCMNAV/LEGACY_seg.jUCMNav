/**
 */
package org.etsi.mts.tdl;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.Package#getImports <em>Import</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.Package#getPackagedElements <em>Packaged Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etsi.mts.tdl.TdlPackage#getPackage()
 * @model
 * @generated
 */
public interface Package extends PackageableElement {
	/**
	 * Returns the value of the '<em><b>Import</b></em>' containment reference list.
	 * The list contents are of type {@link org.etsi.mts.tdl.ElementImport}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Import</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Import</em>' containment reference list.
	 * @see org.etsi.mts.tdl.TdlPackage#getPackage_Import()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<ElementImport> getImports();

	/**
	 * Creates a new {@link org.etsi.mts.tdl.ElementImport} and appends it to the '<em><b>Import</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return The new {@link org.etsi.mts.tdl.ElementImport}.
	 * @see #getImports()
	 * @generated
	 */
	ElementImport createImport();

	/**
	 * Returns the value of the '<em><b>Packaged Elements</b></em>' containment reference list.
	 * The list contents are of type {@link org.etsi.mts.tdl.PackageableElement}.
	 * It is bidirectional and its opposite is '{@link org.etsi.mts.tdl.PackageableElement#getOwningPackage <em>Owning Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Packaged Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Packaged Elements</em>' containment reference list.
	 * @see org.etsi.mts.tdl.TdlPackage#getPackage_PackagedElements()
	 * @see org.etsi.mts.tdl.PackageableElement#getOwningPackage
	 * @model opposite="owningPackage" containment="true" ordered="false"
	 * @generated
	 */
	EList<PackageableElement> getPackagedElements();

	/**
	 * Creates a new {@link org.etsi.mts.tdl.PackageableElement} and appends it to the '<em><b>Packaged Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param eClass The Ecore class of the {@link org.etsi.mts.tdl.PackageableElement} to create.
	 * @return The new {@link org.etsi.mts.tdl.PackageableElement}.
	 * @see #getPackagedElements()
	 * @generated
	 */
	PackageableElement createPackagedElements(EClass eClass);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Cyclic imports are not allowed.
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean invCyclicImportsNotAllowed(DiagnosticChain diagnostics, Map<Object, Object> context);

} // Package
