/**
 */
package org.etsi.mts.tdl.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.etsi.mts.tdl.DataProxy;
import org.etsi.mts.tdl.DataProxyArgumentSpecification;
import org.etsi.mts.tdl.TdlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Proxy Argument Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.impl.DataProxyArgumentSpecificationImpl#getDataProxy <em>Data Proxy</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataProxyArgumentSpecificationImpl extends ArgumentSpecificationImpl implements DataProxyArgumentSpecification {
	/**
	 * The cached value of the '{@link #getDataProxy() <em>Data Proxy</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataProxy()
	 * @generated
	 * @ordered
	 */
	protected DataProxy dataProxy;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataProxyArgumentSpecificationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TdlPackage.Literals.DATA_PROXY_ARGUMENT_SPECIFICATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataProxy getDataProxy() {
		if (dataProxy != null && dataProxy.eIsProxy()) {
			InternalEObject oldDataProxy = (InternalEObject)dataProxy;
			dataProxy = (DataProxy)eResolveProxy(oldDataProxy);
			if (dataProxy != oldDataProxy) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TdlPackage.DATA_PROXY_ARGUMENT_SPECIFICATION__DATA_PROXY, oldDataProxy, dataProxy));
			}
		}
		return dataProxy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataProxy basicGetDataProxy() {
		return dataProxy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataProxy(DataProxy newDataProxy) {
		DataProxy oldDataProxy = dataProxy;
		dataProxy = newDataProxy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TdlPackage.DATA_PROXY_ARGUMENT_SPECIFICATION__DATA_PROXY, oldDataProxy, dataProxy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TdlPackage.DATA_PROXY_ARGUMENT_SPECIFICATION__DATA_PROXY:
				if (resolve) return getDataProxy();
				return basicGetDataProxy();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TdlPackage.DATA_PROXY_ARGUMENT_SPECIFICATION__DATA_PROXY:
				setDataProxy((DataProxy)newValue);
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
			case TdlPackage.DATA_PROXY_ARGUMENT_SPECIFICATION__DATA_PROXY:
				setDataProxy((DataProxy)null);
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
			case TdlPackage.DATA_PROXY_ARGUMENT_SPECIFICATION__DATA_PROXY:
				return dataProxy != null;
		}
		return super.eIsSet(featureID);
	}

} //DataProxyArgumentSpecificationImpl
