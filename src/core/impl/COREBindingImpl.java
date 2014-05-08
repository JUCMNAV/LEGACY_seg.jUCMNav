/**
 */
package core.impl;

import core.COREBinding;
import core.COREMapping;
import core.CorePackage;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CORE Binding</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link core.impl.COREBindingImpl#getCoreMappings <em>Core Mappings</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class COREBindingImpl extends CORECompositionSpecificationImpl implements COREBinding {
	/**
	 * The cached value of the '{@link #getCoreMappings() <em>Core Mappings</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoreMappings()
	 * @generated
	 * @ordered
	 */
	protected EList coreMappings;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected COREBindingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return CorePackage.Literals.CORE_BINDING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getCoreMappings() {
		if (coreMappings == null) {
			coreMappings = new EObjectResolvingEList(COREMapping.class, this, CorePackage.CORE_BINDING__CORE_MAPPINGS);
		}
		return coreMappings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CorePackage.CORE_BINDING__CORE_MAPPINGS:
				return getCoreMappings();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CorePackage.CORE_BINDING__CORE_MAPPINGS:
				getCoreMappings().clear();
				getCoreMappings().addAll((Collection)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case CorePackage.CORE_BINDING__CORE_MAPPINGS:
				getCoreMappings().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CorePackage.CORE_BINDING__CORE_MAPPINGS:
				return coreMappings != null && !coreMappings.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //COREBindingImpl
