/**
 */
package core.impl;

import core.COREConcern;
import core.COREConfiguration;
import core.COREFeature;
import core.CorePackage;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CORE Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link core.impl.COREConfigurationImpl#getReusedConcern <em>Reused Concern</em>}</li>
 *   <li>{@link core.impl.COREConfigurationImpl#getSelected <em>Selected</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class COREConfigurationImpl extends CORENamedElementImpl implements COREConfiguration {
	/**
	 * The cached value of the '{@link #getReusedConcern() <em>Reused Concern</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReusedConcern()
	 * @generated
	 * @ordered
	 */
	protected EList reusedConcern;

	/**
	 * The cached value of the '{@link #getSelected() <em>Selected</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSelected()
	 * @generated
	 * @ordered
	 */
	protected EList selected;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected COREConfigurationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return CorePackage.Literals.CORE_CONFIGURATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getReusedConcern() {
		if (reusedConcern == null) {
			reusedConcern = new EObjectResolvingEList(COREConcern.class, this, CorePackage.CORE_CONFIGURATION__REUSED_CONCERN);
		}
		return reusedConcern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getSelected() {
		if (selected == null) {
			selected = new EObjectResolvingEList(COREFeature.class, this, CorePackage.CORE_CONFIGURATION__SELECTED);
		}
		return selected;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CorePackage.CORE_CONFIGURATION__REUSED_CONCERN:
				return getReusedConcern();
			case CorePackage.CORE_CONFIGURATION__SELECTED:
				return getSelected();
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
			case CorePackage.CORE_CONFIGURATION__REUSED_CONCERN:
				getReusedConcern().clear();
				getReusedConcern().addAll((Collection)newValue);
				return;
			case CorePackage.CORE_CONFIGURATION__SELECTED:
				getSelected().clear();
				getSelected().addAll((Collection)newValue);
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
			case CorePackage.CORE_CONFIGURATION__REUSED_CONCERN:
				getReusedConcern().clear();
				return;
			case CorePackage.CORE_CONFIGURATION__SELECTED:
				getSelected().clear();
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
			case CorePackage.CORE_CONFIGURATION__REUSED_CONCERN:
				return reusedConcern != null && !reusedConcern.isEmpty();
			case CorePackage.CORE_CONFIGURATION__SELECTED:
				return selected != null && !selected.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //COREConfigurationImpl
