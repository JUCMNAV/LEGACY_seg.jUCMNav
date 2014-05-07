/**
 */
package org.etsi.mts.tdl.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.etsi.mts.tdl.DataInstance;
import org.etsi.mts.tdl.DataSet;
import org.etsi.mts.tdl.TdlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Set</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.impl.DataSetImpl#getDataInstances <em>Data Instance</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataSetImpl extends PackageableElementImpl implements DataSet {
	/**
	 * The cached value of the '{@link #getDataInstances() <em>Data Instance</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataInstances()
	 * @generated
	 * @ordered
	 */
	protected EList<DataInstance> dataInstances;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataSetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TdlPackage.Literals.DATA_SET;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DataInstance> getDataInstances() {
		if (dataInstances == null) {
			dataInstances = new EObjectContainmentEList<DataInstance>(DataInstance.class, this, TdlPackage.DATA_SET__DATA_INSTANCE);
		}
		return dataInstances;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataInstance createDataInstance() {
		DataInstance newDataInstance = (DataInstance) create(TdlPackage.Literals.DATA_INSTANCE);
		getDataInstances().add(newDataInstance);
		return newDataInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TdlPackage.DATA_SET__DATA_INSTANCE:
				return ((InternalEList<?>)getDataInstances()).basicRemove(otherEnd, msgs);
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
			case TdlPackage.DATA_SET__DATA_INSTANCE:
				return getDataInstances();
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
			case TdlPackage.DATA_SET__DATA_INSTANCE:
				getDataInstances().clear();
				getDataInstances().addAll((Collection<? extends DataInstance>)newValue);
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
			case TdlPackage.DATA_SET__DATA_INSTANCE:
				getDataInstances().clear();
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
			case TdlPackage.DATA_SET__DATA_INSTANCE:
				return dataInstances != null && !dataInstances.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //DataSetImpl
