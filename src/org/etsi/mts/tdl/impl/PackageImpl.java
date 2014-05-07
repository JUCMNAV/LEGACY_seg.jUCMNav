/**
 */
package org.etsi.mts.tdl.impl;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.ecore.util.InternalEList;

import org.etsi.mts.tdl.ElementImport;
import org.etsi.mts.tdl.PackageableElement;
import org.etsi.mts.tdl.TdlPackage;

import org.etsi.mts.tdl.util.TdlValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Package</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.impl.PackageImpl#getImports <em>Import</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.impl.PackageImpl#getPackagedElements <em>Packaged Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PackageImpl extends PackageableElementImpl implements org.etsi.mts.tdl.Package {
	/**
	 * The cached value of the '{@link #getImports() <em>Import</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImports()
	 * @generated
	 * @ordered
	 */
	protected EList<ElementImport> imports;

	/**
	 * The cached value of the '{@link #getPackagedElements() <em>Packaged Elements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackagedElements()
	 * @generated
	 * @ordered
	 */
	protected EList<PackageableElement> packagedElements;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PackageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TdlPackage.Literals.PACKAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ElementImport> getImports() {
		if (imports == null) {
			imports = new EObjectContainmentEList<ElementImport>(ElementImport.class, this, TdlPackage.PACKAGE__IMPORT);
		}
		return imports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ElementImport createImport() {
		ElementImport newImport = (ElementImport) create(TdlPackage.Literals.ELEMENT_IMPORT);
		getImports().add(newImport);
		return newImport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PackageableElement> getPackagedElements() {
		if (packagedElements == null) {
			packagedElements = new EObjectContainmentWithInverseEList<PackageableElement>(PackageableElement.class, this, TdlPackage.PACKAGE__PACKAGED_ELEMENTS, TdlPackage.PACKAGEABLE_ELEMENT__OWNING_PACKAGE);
		}
		return packagedElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PackageableElement createPackagedElements(EClass eClass) {
		PackageableElement newPackagedElements = (PackageableElement) create(eClass);
		getPackagedElements().add(newPackagedElements);
		return newPackagedElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean invCyclicImportsNotAllowed(DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO: implement this method
		// -> specify the condition that violates the invariant
		// -> verify the details of the diagnostic, including severity and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 TdlValidator.DIAGNOSTIC_SOURCE,
						 TdlValidator.PACKAGE__INV_CYCLIC_IMPORTS_NOT_ALLOWED,
						 EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[] { "invCyclicImportsNotAllowed", EObjectValidator.getObjectLabel(this, context) }),
						 new Object [] { this }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TdlPackage.PACKAGE__PACKAGED_ELEMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPackagedElements()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TdlPackage.PACKAGE__IMPORT:
				return ((InternalEList<?>)getImports()).basicRemove(otherEnd, msgs);
			case TdlPackage.PACKAGE__PACKAGED_ELEMENTS:
				return ((InternalEList<?>)getPackagedElements()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TdlPackage.PACKAGE__IMPORT:
				return getImports();
			case TdlPackage.PACKAGE__PACKAGED_ELEMENTS:
				return getPackagedElements();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TdlPackage.PACKAGE__IMPORT:
				getImports().clear();
				getImports().addAll((Collection<? extends ElementImport>)newValue);
				return;
			case TdlPackage.PACKAGE__PACKAGED_ELEMENTS:
				getPackagedElements().clear();
				getPackagedElements().addAll((Collection<? extends PackageableElement>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case TdlPackage.PACKAGE__IMPORT:
				getImports().clear();
				return;
			case TdlPackage.PACKAGE__PACKAGED_ELEMENTS:
				getPackagedElements().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TdlPackage.PACKAGE__IMPORT:
				return imports != null && !imports.isEmpty();
			case TdlPackage.PACKAGE__PACKAGED_ELEMENTS:
				return packagedElements != null && !packagedElements.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //PackageImpl
