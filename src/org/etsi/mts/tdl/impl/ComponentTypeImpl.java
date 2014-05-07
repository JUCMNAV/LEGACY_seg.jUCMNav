/**
 */
package org.etsi.mts.tdl.impl;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.ecore.util.InternalEList;

import org.etsi.mts.tdl.ComponentType;
import org.etsi.mts.tdl.GateType;
import org.etsi.mts.tdl.TdlPackage;
import org.etsi.mts.tdl.Timer;

import org.etsi.mts.tdl.util.TdlValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.impl.ComponentTypeImpl#getGateTypes <em>Gate Type</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.impl.ComponentTypeImpl#getTimers <em>Timer</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComponentTypeImpl extends PackageableElementImpl implements ComponentType {
	/**
	 * The cached value of the '{@link #getGateTypes() <em>Gate Type</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGateTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<GateType> gateTypes;

	/**
	 * The cached value of the '{@link #getTimers() <em>Timer</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimers()
	 * @generated
	 * @ordered
	 */
	protected EList<Timer> timers;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TdlPackage.Literals.COMPONENT_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GateType> getGateTypes() {
		if (gateTypes == null) {
			gateTypes = new EObjectResolvingEList<GateType>(GateType.class, this, TdlPackage.COMPONENT_TYPE__GATE_TYPE);
		}
		return gateTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Timer> getTimers() {
		if (timers == null) {
			timers = new EObjectContainmentWithInverseEList<Timer>(Timer.class, this, TdlPackage.COMPONENT_TYPE__TIMER, TdlPackage.TIMER__COMPONENT_TYPE);
		}
		return timers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Timer createTimer() {
		Timer newTimer = (Timer) create(TdlPackage.Literals.TIMER);
		getTimers().add(newTimer);
		return newTimer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean invTestConfigurationandComponentsRoles(DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 TdlValidator.COMPONENT_TYPE__INV_TEST_CONFIGURATIONAND_COMPONENTS_ROLES,
						 EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[] { "invTestConfigurationandComponentsRoles", EObjectValidator.getObjectLabel(this, context) }),
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
			case TdlPackage.COMPONENT_TYPE__TIMER:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTimers()).basicAdd(otherEnd, msgs);
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
			case TdlPackage.COMPONENT_TYPE__TIMER:
				return ((InternalEList<?>)getTimers()).basicRemove(otherEnd, msgs);
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
			case TdlPackage.COMPONENT_TYPE__GATE_TYPE:
				return getGateTypes();
			case TdlPackage.COMPONENT_TYPE__TIMER:
				return getTimers();
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
			case TdlPackage.COMPONENT_TYPE__GATE_TYPE:
				getGateTypes().clear();
				getGateTypes().addAll((Collection<? extends GateType>)newValue);
				return;
			case TdlPackage.COMPONENT_TYPE__TIMER:
				getTimers().clear();
				getTimers().addAll((Collection<? extends Timer>)newValue);
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
			case TdlPackage.COMPONENT_TYPE__GATE_TYPE:
				getGateTypes().clear();
				return;
			case TdlPackage.COMPONENT_TYPE__TIMER:
				getTimers().clear();
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
			case TdlPackage.COMPONENT_TYPE__GATE_TYPE:
				return gateTypes != null && !gateTypes.isEmpty();
			case TdlPackage.COMPONENT_TYPE__TIMER:
				return timers != null && !timers.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ComponentTypeImpl
