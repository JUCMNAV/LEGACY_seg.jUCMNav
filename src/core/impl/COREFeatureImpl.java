/**
 */
package core.impl;

import core.COREConfiguration;
import core.COREFeature;
import core.COREModel;
import core.COREStrategy;
import core.CorePackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CORE Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link core.impl.COREFeatureImpl#getRealizedBy <em>Realized By</em>}</li>
 *   <li>{@link core.impl.COREFeatureImpl#getStrategies <em>Strategies</em>}</li>
 *   <li>{@link core.impl.COREFeatureImpl#getConfigurations <em>Configurations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class COREFeatureImpl extends COREModelElementImpl implements COREFeature {
	/**
	 * The cached value of the '{@link #getRealizedBy() <em>Realized By</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRealizedBy()
	 * @generated
	 * @ordered
	 */
	protected EList realizedBy;

	/**
	 * The cached value of the '{@link #getStrategies() <em>Strategies</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStrategies()
	 * @generated
	 * @ordered
	 */
	protected EList strategies;

	/**
	 * The cached value of the '{@link #getConfigurations() <em>Configurations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfigurations()
	 * @generated
	 * @ordered
	 */
	protected EList configurations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected COREFeatureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return CorePackage.Literals.CORE_FEATURE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getRealizedBy() {
		if (realizedBy == null) {
			realizedBy = new EObjectWithInverseResolvingEList.ManyInverse(COREModel.class, this, CorePackage.CORE_FEATURE__REALIZED_BY, CorePackage.CORE_MODEL__REALIZES);
		}
		return realizedBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getStrategies() {
		if (strategies == null) {
			strategies = new EObjectContainmentEList(COREStrategy.class, this, CorePackage.CORE_FEATURE__STRATEGIES);
		}
		return strategies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getConfigurations() {
		if (configurations == null) {
			configurations = new EObjectContainmentEList(COREConfiguration.class, this, CorePackage.CORE_FEATURE__CONFIGURATIONS);
		}
		return configurations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CorePackage.CORE_FEATURE__REALIZED_BY:
				return ((InternalEList)getRealizedBy()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CorePackage.CORE_FEATURE__REALIZED_BY:
				return ((InternalEList)getRealizedBy()).basicRemove(otherEnd, msgs);
			case CorePackage.CORE_FEATURE__STRATEGIES:
				return ((InternalEList)getStrategies()).basicRemove(otherEnd, msgs);
			case CorePackage.CORE_FEATURE__CONFIGURATIONS:
				return ((InternalEList)getConfigurations()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CorePackage.CORE_FEATURE__REALIZED_BY:
				return getRealizedBy();
			case CorePackage.CORE_FEATURE__STRATEGIES:
				return getStrategies();
			case CorePackage.CORE_FEATURE__CONFIGURATIONS:
				return getConfigurations();
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
			case CorePackage.CORE_FEATURE__REALIZED_BY:
				getRealizedBy().clear();
				getRealizedBy().addAll((Collection)newValue);
				return;
			case CorePackage.CORE_FEATURE__STRATEGIES:
				getStrategies().clear();
				getStrategies().addAll((Collection)newValue);
				return;
			case CorePackage.CORE_FEATURE__CONFIGURATIONS:
				getConfigurations().clear();
				getConfigurations().addAll((Collection)newValue);
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
			case CorePackage.CORE_FEATURE__REALIZED_BY:
				getRealizedBy().clear();
				return;
			case CorePackage.CORE_FEATURE__STRATEGIES:
				getStrategies().clear();
				return;
			case CorePackage.CORE_FEATURE__CONFIGURATIONS:
				getConfigurations().clear();
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
			case CorePackage.CORE_FEATURE__REALIZED_BY:
				return realizedBy != null && !realizedBy.isEmpty();
			case CorePackage.CORE_FEATURE__STRATEGIES:
				return strategies != null && !strategies.isEmpty();
			case CorePackage.CORE_FEATURE__CONFIGURATIONS:
				return configurations != null && !configurations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //COREFeatureImpl
