/**
 */
package org.etsi.mts.tdl.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.etsi.mts.tdl.ElementImport;
import org.etsi.mts.tdl.PackageableElement;
import org.etsi.mts.tdl.TdlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element Import</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.impl.ElementImportImpl#getImportedElements <em>Imported Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ElementImportImpl extends ElementImpl implements ElementImport {
	/**
	 * The cached value of the '{@link #getImportedElements() <em>Imported Element</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImportedElements()
	 * @generated
	 * @ordered
	 */
	protected EList<PackageableElement> importedElements;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ElementImportImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TdlPackage.Literals.ELEMENT_IMPORT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PackageableElement> getImportedElements() {
		if (importedElements == null) {
			importedElements = new EObjectResolvingEList<PackageableElement>(PackageableElement.class, this, TdlPackage.ELEMENT_IMPORT__IMPORTED_ELEMENT);
		}
		return importedElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TdlPackage.ELEMENT_IMPORT__IMPORTED_ELEMENT:
				return getImportedElements();
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
			case TdlPackage.ELEMENT_IMPORT__IMPORTED_ELEMENT:
				getImportedElements().clear();
				getImportedElements().addAll((Collection<? extends PackageableElement>)newValue);
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
			case TdlPackage.ELEMENT_IMPORT__IMPORTED_ELEMENT:
				getImportedElements().clear();
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
			case TdlPackage.ELEMENT_IMPORT__IMPORTED_ELEMENT:
				return importedElements != null && !importedElements.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ElementImportImpl
