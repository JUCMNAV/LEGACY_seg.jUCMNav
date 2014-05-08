/**
 */
package core.impl;

import core.COREFeature;
import core.COREModel;
import core.COREModelElement;
import core.COREReuse;
import core.CorePackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CORE Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link core.impl.COREModelImpl#getReuses <em>Reuses</em>}</li>
 *   <li>{@link core.impl.COREModelImpl#getModelElements <em>Model Elements</em>}</li>
 *   <li>{@link core.impl.COREModelImpl#getRealizes <em>Realizes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class COREModelImpl extends CORENamedElementImpl implements COREModel {
	/**
	 * The cached value of the '{@link #getReuses() <em>Reuses</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReuses()
	 * @generated
	 * @ordered
	 */
	protected EList reuses;

	/**
	 * The cached value of the '{@link #getModelElements() <em>Model Elements</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelElements()
	 * @generated
	 * @ordered
	 */
	protected EList modelElements;

	/**
	 * The cached value of the '{@link #getRealizes() <em>Realizes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRealizes()
	 * @generated
	 * @ordered
	 */
	protected EList realizes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected COREModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return CorePackage.Literals.CORE_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getReuses() {
		if (reuses == null) {
			reuses = new EObjectContainmentEList(COREReuse.class, this, CorePackage.CORE_MODEL__REUSES);
		}
		return reuses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getModelElements() {
		if (modelElements == null) {
			modelElements = new EObjectResolvingEList(COREModelElement.class, this, CorePackage.CORE_MODEL__MODEL_ELEMENTS);
		}
		return modelElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getRealizes() {
		if (realizes == null) {
			realizes = new EObjectWithInverseResolvingEList.ManyInverse(COREFeature.class, this, CorePackage.CORE_MODEL__REALIZES, CorePackage.CORE_FEATURE__REALIZED_BY);
		}
		return realizes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CorePackage.CORE_MODEL__REALIZES:
				return ((InternalEList)getRealizes()).basicAdd(otherEnd, msgs);
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
			case CorePackage.CORE_MODEL__REUSES:
				return ((InternalEList)getReuses()).basicRemove(otherEnd, msgs);
			case CorePackage.CORE_MODEL__REALIZES:
				return ((InternalEList)getRealizes()).basicRemove(otherEnd, msgs);
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
			case CorePackage.CORE_MODEL__REUSES:
				return getReuses();
			case CorePackage.CORE_MODEL__MODEL_ELEMENTS:
				return getModelElements();
			case CorePackage.CORE_MODEL__REALIZES:
				return getRealizes();
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
			case CorePackage.CORE_MODEL__REUSES:
				getReuses().clear();
				getReuses().addAll((Collection)newValue);
				return;
			case CorePackage.CORE_MODEL__REALIZES:
				getRealizes().clear();
				getRealizes().addAll((Collection)newValue);
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
			case CorePackage.CORE_MODEL__REUSES:
				getReuses().clear();
				return;
			case CorePackage.CORE_MODEL__REALIZES:
				getRealizes().clear();
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
			case CorePackage.CORE_MODEL__REUSES:
				return reuses != null && !reuses.isEmpty();
			case CorePackage.CORE_MODEL__MODEL_ELEMENTS:
				return modelElements != null && !modelElements.isEmpty();
			case CorePackage.CORE_MODEL__REALIZES:
				return realizes != null && !realizes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //COREModelImpl
