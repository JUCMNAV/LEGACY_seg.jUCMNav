/**
 */
package org.etsi.mts.tdl;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Packageable Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.PackageableElement#getQualifiedName <em>Qualified Name</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.PackageableElement#getOwningPackage <em>Owning Package</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etsi.mts.tdl.TdlPackage#getPackageableElement()
 * @model abstract="true"
 * @generated
 */
public interface PackageableElement extends Element {
	/**
	 * Returns the value of the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qualified Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Qualified Name</em>' attribute.
	 * @see org.etsi.mts.tdl.TdlPackage#getPackageableElement_QualifiedName()
	 * @model unique="false" dataType="org.eclipse.uml2.types.String" required="true" transient="true" changeable="false" volatile="true" derived="true" ordered="false"
	 * @generated
	 */
	String getQualifiedName();

	/**
	 * Returns the value of the '<em><b>Owning Package</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.etsi.mts.tdl.Package#getPackagedElements <em>Packaged Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owning Package</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owning Package</em>' container reference.
	 * @see #setOwningPackage(org.etsi.mts.tdl.Package)
	 * @see org.etsi.mts.tdl.TdlPackage#getPackageableElement_OwningPackage()
	 * @see org.etsi.mts.tdl.Package#getPackagedElements
	 * @model opposite="packagedElements" transient="false" ordered="false"
	 * @generated
	 */
	org.etsi.mts.tdl.Package getOwningPackage();

	/**
	 * Sets the value of the '{@link org.etsi.mts.tdl.PackageableElement#getOwningPackage <em>Owning Package</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Package</em>' container reference.
	 * @see #getOwningPackage()
	 * @generated
	 */
	void setOwningPackage(org.etsi.mts.tdl.Package value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean invQualifiednamesmustbedistinguishable(DiagnosticChain diagnostics, Map<Object, Object> context);

} // PackageableElement
