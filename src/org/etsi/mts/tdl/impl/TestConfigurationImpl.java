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

import org.etsi.mts.tdl.ComponentInstance;
import org.etsi.mts.tdl.Connection;
import org.etsi.mts.tdl.TdlPackage;
import org.etsi.mts.tdl.TestConfiguration;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.impl.TestConfigurationImpl#getComponentInstances <em>Component Instance</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.impl.TestConfigurationImpl#getConnections <em>Connection</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TestConfigurationImpl extends PackageableElementImpl implements TestConfiguration {
	/**
	 * The cached value of the '{@link #getComponentInstances() <em>Component Instance</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponentInstances()
	 * @generated
	 * @ordered
	 */
	protected EList<ComponentInstance> componentInstances;

	/**
	 * The cached value of the '{@link #getConnections() <em>Connection</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnections()
	 * @generated
	 * @ordered
	 */
	protected EList<Connection> connections;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestConfigurationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TdlPackage.Literals.TEST_CONFIGURATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ComponentInstance> getComponentInstances() {
		if (componentInstances == null) {
			componentInstances = new EObjectContainmentEList<ComponentInstance>(ComponentInstance.class, this, TdlPackage.TEST_CONFIGURATION__COMPONENT_INSTANCE);
		}
		return componentInstances;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentInstance createComponentInstance() {
		ComponentInstance newComponentInstance = (ComponentInstance) create(TdlPackage.Literals.COMPONENT_INSTANCE);
		getComponentInstances().add(newComponentInstance);
		return newComponentInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Connection> getConnections() {
		if (connections == null) {
			connections = new EObjectContainmentEList<Connection>(Connection.class, this, TdlPackage.TEST_CONFIGURATION__CONNECTION);
		}
		return connections;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Connection createConnection() {
		Connection newConnection = (Connection) create(TdlPackage.Literals.CONNECTION);
		getConnections().add(newConnection);
		return newConnection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TdlPackage.TEST_CONFIGURATION__COMPONENT_INSTANCE:
				return ((InternalEList<?>)getComponentInstances()).basicRemove(otherEnd, msgs);
			case TdlPackage.TEST_CONFIGURATION__CONNECTION:
				return ((InternalEList<?>)getConnections()).basicRemove(otherEnd, msgs);
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
			case TdlPackage.TEST_CONFIGURATION__COMPONENT_INSTANCE:
				return getComponentInstances();
			case TdlPackage.TEST_CONFIGURATION__CONNECTION:
				return getConnections();
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
			case TdlPackage.TEST_CONFIGURATION__COMPONENT_INSTANCE:
				getComponentInstances().clear();
				getComponentInstances().addAll((Collection<? extends ComponentInstance>)newValue);
				return;
			case TdlPackage.TEST_CONFIGURATION__CONNECTION:
				getConnections().clear();
				getConnections().addAll((Collection<? extends Connection>)newValue);
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
			case TdlPackage.TEST_CONFIGURATION__COMPONENT_INSTANCE:
				getComponentInstances().clear();
				return;
			case TdlPackage.TEST_CONFIGURATION__CONNECTION:
				getConnections().clear();
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
			case TdlPackage.TEST_CONFIGURATION__COMPONENT_INSTANCE:
				return componentInstances != null && !componentInstances.isEmpty();
			case TdlPackage.TEST_CONFIGURATION__CONNECTION:
				return connections != null && !connections.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TestConfigurationImpl
