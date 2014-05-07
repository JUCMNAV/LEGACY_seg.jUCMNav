/**
 */
package org.etsi.mts.tdl.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.etsi.mts.tdl.DataElement;
import org.etsi.mts.tdl.DataInstance;
import org.etsi.mts.tdl.DataInstanceArgumentSpecification;
import org.etsi.mts.tdl.TdlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Instance Argument Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.impl.DataInstanceArgumentSpecificationImpl#getDataInstance <em>Data Instance</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.impl.DataInstanceArgumentSpecificationImpl#getActualParameters <em>Actual Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataInstanceArgumentSpecificationImpl extends ArgumentSpecificationImpl implements DataInstanceArgumentSpecification {
	/**
	 * The cached value of the '{@link #getDataInstance() <em>Data Instance</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataInstance()
	 * @generated
	 * @ordered
	 */
	protected DataInstance dataInstance;

	/**
	 * The cached value of the '{@link #getActualParameters() <em>Actual Parameter</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<DataElement> actualParameters;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataInstanceArgumentSpecificationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TdlPackage.Literals.DATA_INSTANCE_ARGUMENT_SPECIFICATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataInstance getDataInstance() {
		if (dataInstance != null && dataInstance.eIsProxy()) {
			InternalEObject oldDataInstance = (InternalEObject)dataInstance;
			dataInstance = (DataInstance)eResolveProxy(oldDataInstance);
			if (dataInstance != oldDataInstance) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TdlPackage.DATA_INSTANCE_ARGUMENT_SPECIFICATION__DATA_INSTANCE, oldDataInstance, dataInstance));
			}
		}
		return dataInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataInstance basicGetDataInstance() {
		return dataInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataInstance(DataInstance newDataInstance) {
		DataInstance oldDataInstance = dataInstance;
		dataInstance = newDataInstance;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TdlPackage.DATA_INSTANCE_ARGUMENT_SPECIFICATION__DATA_INSTANCE, oldDataInstance, dataInstance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DataElement> getActualParameters() {
		if (actualParameters == null) {
			actualParameters = new EObjectResolvingEList<DataElement>(DataElement.class, this, TdlPackage.DATA_INSTANCE_ARGUMENT_SPECIFICATION__ACTUAL_PARAMETER);
		}
		return actualParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TdlPackage.DATA_INSTANCE_ARGUMENT_SPECIFICATION__DATA_INSTANCE:
				if (resolve) return getDataInstance();
				return basicGetDataInstance();
			case TdlPackage.DATA_INSTANCE_ARGUMENT_SPECIFICATION__ACTUAL_PARAMETER:
				return getActualParameters();
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
			case TdlPackage.DATA_INSTANCE_ARGUMENT_SPECIFICATION__DATA_INSTANCE:
				setDataInstance((DataInstance)newValue);
				return;
			case TdlPackage.DATA_INSTANCE_ARGUMENT_SPECIFICATION__ACTUAL_PARAMETER:
				getActualParameters().clear();
				getActualParameters().addAll((Collection<? extends DataElement>)newValue);
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
			case TdlPackage.DATA_INSTANCE_ARGUMENT_SPECIFICATION__DATA_INSTANCE:
				setDataInstance((DataInstance)null);
				return;
			case TdlPackage.DATA_INSTANCE_ARGUMENT_SPECIFICATION__ACTUAL_PARAMETER:
				getActualParameters().clear();
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
			case TdlPackage.DATA_INSTANCE_ARGUMENT_SPECIFICATION__DATA_INSTANCE:
				return dataInstance != null;
			case TdlPackage.DATA_INSTANCE_ARGUMENT_SPECIFICATION__ACTUAL_PARAMETER:
				return actualParameters != null && !actualParameters.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //DataInstanceArgumentSpecificationImpl
