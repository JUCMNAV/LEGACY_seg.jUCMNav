/**
 */
package org.etsi.mts.tdl.impl;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.ecore.util.InternalEList;

import org.etsi.mts.tdl.ComponentInstance;
import org.etsi.mts.tdl.ComponentInstanceRole;
import org.etsi.mts.tdl.ComponentType;
import org.etsi.mts.tdl.GateInstance;
import org.etsi.mts.tdl.TdlPackage;
import org.etsi.mts.tdl.Timer;

import org.etsi.mts.tdl.util.TdlValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.impl.ComponentInstanceImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.impl.ComponentInstanceImpl#getRole <em>Role</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.impl.ComponentInstanceImpl#getTimers <em>Timer</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.impl.ComponentInstanceImpl#getGateInstances <em>Gate Instance</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComponentInstanceImpl extends ElementImpl implements ComponentInstance {
	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected ComponentType type;

	/**
	 * The default value of the '{@link #getRole() <em>Role</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRole()
	 * @generated
	 * @ordered
	 */
	protected static final ComponentInstanceRole ROLE_EDEFAULT = ComponentInstanceRole.SUT;

	/**
	 * The cached value of the '{@link #getRole() <em>Role</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRole()
	 * @generated
	 * @ordered
	 */
	protected ComponentInstanceRole role = ROLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getGateInstances() <em>Gate Instance</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGateInstances()
	 * @generated
	 * @ordered
	 */
	protected EList<GateInstance> gateInstances;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TdlPackage.Literals.COMPONENT_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentType getType() {
		if (type != null && type.eIsProxy()) {
			InternalEObject oldType = (InternalEObject)type;
			type = (ComponentType)eResolveProxy(oldType);
			if (type != oldType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TdlPackage.COMPONENT_INSTANCE__TYPE, oldType, type));
			}
		}
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentType basicGetType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(ComponentType newType) {
		ComponentType oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TdlPackage.COMPONENT_INSTANCE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentInstanceRole getRole() {
		return role;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRole(ComponentInstanceRole newRole) {
		ComponentInstanceRole oldRole = role;
		role = newRole == null ? ROLE_EDEFAULT : newRole;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TdlPackage.COMPONENT_INSTANCE__ROLE, oldRole, role));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Timer> getTimers() {
		// TODO: implement this method to return the 'Timer' reference list
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GateInstance> getGateInstances() {
		if (gateInstances == null) {
			gateInstances = new EObjectContainmentWithInverseEList<GateInstance>(GateInstance.class, this, TdlPackage.COMPONENT_INSTANCE__GATE_INSTANCE, TdlPackage.GATE_INSTANCE__COMPONENT_INSTANCE);
		}
		return gateInstances;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GateInstance createGateInstance() {
		GateInstance newGateInstance = (GateInstance) create(TdlPackage.Literals.GATE_INSTANCE);
		getGateInstances().add(newGateInstance);
		return newGateInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean invNumberofGateInstances(DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 TdlValidator.COMPONENT_INSTANCE__INV_NUMBEROF_GATE_INSTANCES,
						 EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[] { "invNumberofGateInstances", EObjectValidator.getObjectLabel(this, context) }),
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
	public boolean invTimersofComponentInstances(DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 TdlValidator.COMPONENT_INSTANCE__INV_TIMERSOF_COMPONENT_INSTANCES,
						 EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[] { "invTimersofComponentInstances", EObjectValidator.getObjectLabel(this, context) }),
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
			case TdlPackage.COMPONENT_INSTANCE__GATE_INSTANCE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getGateInstances()).basicAdd(otherEnd, msgs);
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
			case TdlPackage.COMPONENT_INSTANCE__GATE_INSTANCE:
				return ((InternalEList<?>)getGateInstances()).basicRemove(otherEnd, msgs);
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
			case TdlPackage.COMPONENT_INSTANCE__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case TdlPackage.COMPONENT_INSTANCE__ROLE:
				return getRole();
			case TdlPackage.COMPONENT_INSTANCE__TIMER:
				return getTimers();
			case TdlPackage.COMPONENT_INSTANCE__GATE_INSTANCE:
				return getGateInstances();
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
			case TdlPackage.COMPONENT_INSTANCE__TYPE:
				setType((ComponentType)newValue);
				return;
			case TdlPackage.COMPONENT_INSTANCE__ROLE:
				setRole((ComponentInstanceRole)newValue);
				return;
			case TdlPackage.COMPONENT_INSTANCE__GATE_INSTANCE:
				getGateInstances().clear();
				getGateInstances().addAll((Collection<? extends GateInstance>)newValue);
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
			case TdlPackage.COMPONENT_INSTANCE__TYPE:
				setType((ComponentType)null);
				return;
			case TdlPackage.COMPONENT_INSTANCE__ROLE:
				setRole(ROLE_EDEFAULT);
				return;
			case TdlPackage.COMPONENT_INSTANCE__GATE_INSTANCE:
				getGateInstances().clear();
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
			case TdlPackage.COMPONENT_INSTANCE__TYPE:
				return type != null;
			case TdlPackage.COMPONENT_INSTANCE__ROLE:
				return role != ROLE_EDEFAULT;
			case TdlPackage.COMPONENT_INSTANCE__TIMER:
				return !getTimers().isEmpty();
			case TdlPackage.COMPONENT_INSTANCE__GATE_INSTANCE:
				return gateInstances != null && !gateInstances.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (role: ");
		result.append(role);
		result.append(')');
		return result.toString();
	}

} //ComponentInstanceImpl
