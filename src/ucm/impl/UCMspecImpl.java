/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import ucm.UCMspec;
import ucm.UcmPackage;

import ucm.map.Map;

import ucm.performance.Device;
import ucm.performance.ResponseTimeReq;

import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;
import ucm.scenario.Variable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>UC Mspec</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.impl.UCMspecImpl#getDevices <em>Devices</em>}</li>
 *   <li>{@link ucm.impl.UCMspecImpl#getResptimereq <em>Resptimereq</em>}</li>
 *   <li>{@link ucm.impl.UCMspecImpl#getMaps <em>Maps</em>}</li>
 *   <li>{@link ucm.impl.UCMspecImpl#getRoot <em>Root</em>}</li>
 *   <li>{@link ucm.impl.UCMspecImpl#getScenarioGroups <em>Scenario Groups</em>}</li>
 *   <li>{@link ucm.impl.UCMspecImpl#getVariables <em>Variables</em>}</li>
 *   <li>{@link ucm.impl.UCMspecImpl#getScenarioDefs <em>Scenario Defs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UCMspecImpl extends EObjectImpl implements UCMspec {
	/**
	 * The cached value of the '{@link #getDevices() <em>Devices</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDevices()
	 * @generated
	 * @ordered
	 */
	protected EList devices = null;

	/**
	 * The cached value of the '{@link #getResptimereq() <em>Resptimereq</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResptimereq()
	 * @generated
	 * @ordered
	 */
	protected EList resptimereq = null;

	/**
	 * The cached value of the '{@link #getMaps() <em>Maps</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaps()
	 * @generated
	 * @ordered
	 */
	protected EList maps = null;

	/**
	 * The cached value of the '{@link #getRoot() <em>Root</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoot()
	 * @generated
	 * @ordered
	 */
	protected EList root = null;

	/**
	 * The cached value of the '{@link #getScenarioGroups() <em>Scenario Groups</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScenarioGroups()
	 * @generated
	 * @ordered
	 */
	protected EList scenarioGroups = null;

	/**
	 * The cached value of the '{@link #getVariables() <em>Variables</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariables()
	 * @generated
	 * @ordered
	 */
	protected EList variables = null;

	/**
	 * The cached value of the '{@link #getScenarioDefs() <em>Scenario Defs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScenarioDefs()
	 * @generated
	 * @ordered
	 */
	protected EList scenarioDefs = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UCMspecImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return UcmPackage.eINSTANCE.getUCMspec();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDevices() {
		if (devices == null) {
			devices = new EObjectContainmentEList(Device.class, this, UcmPackage.UC_MSPEC__DEVICES);
		}
		return devices;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getResptimereq() {
		if (resptimereq == null) {
			resptimereq = new EObjectContainmentEList(ResponseTimeReq.class, this, UcmPackage.UC_MSPEC__RESPTIMEREQ);
		}
		return resptimereq;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getMaps() {
		if (maps == null) {
			maps = new EObjectContainmentEList(Map.class, this, UcmPackage.UC_MSPEC__MAPS);
		}
		return maps;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getRoot() {
		if (root == null) {
			root = new EObjectResolvingEList(Map.class, this, UcmPackage.UC_MSPEC__ROOT);
		}
		return root;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getScenarioGroups() {
		if (scenarioGroups == null) {
			scenarioGroups = new EObjectContainmentEList(ScenarioGroup.class, this, UcmPackage.UC_MSPEC__SCENARIO_GROUPS);
		}
		return scenarioGroups;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getVariables() {
		if (variables == null) {
			variables = new EObjectContainmentEList(Variable.class, this, UcmPackage.UC_MSPEC__VARIABLES);
		}
		return variables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getScenarioDefs() {
		if (scenarioDefs == null) {
			scenarioDefs = new EObjectContainmentEList(ScenarioDef.class, this, UcmPackage.UC_MSPEC__SCENARIO_DEFS);
		}
		return scenarioDefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case UcmPackage.UC_MSPEC__DEVICES:
					return ((InternalEList)getDevices()).basicRemove(otherEnd, msgs);
				case UcmPackage.UC_MSPEC__RESPTIMEREQ:
					return ((InternalEList)getResptimereq()).basicRemove(otherEnd, msgs);
				case UcmPackage.UC_MSPEC__MAPS:
					return ((InternalEList)getMaps()).basicRemove(otherEnd, msgs);
				case UcmPackage.UC_MSPEC__SCENARIO_GROUPS:
					return ((InternalEList)getScenarioGroups()).basicRemove(otherEnd, msgs);
				case UcmPackage.UC_MSPEC__VARIABLES:
					return ((InternalEList)getVariables()).basicRemove(otherEnd, msgs);
				case UcmPackage.UC_MSPEC__SCENARIO_DEFS:
					return ((InternalEList)getScenarioDefs()).basicRemove(otherEnd, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case UcmPackage.UC_MSPEC__DEVICES:
				return getDevices();
			case UcmPackage.UC_MSPEC__RESPTIMEREQ:
				return getResptimereq();
			case UcmPackage.UC_MSPEC__MAPS:
				return getMaps();
			case UcmPackage.UC_MSPEC__ROOT:
				return getRoot();
			case UcmPackage.UC_MSPEC__SCENARIO_GROUPS:
				return getScenarioGroups();
			case UcmPackage.UC_MSPEC__VARIABLES:
				return getVariables();
			case UcmPackage.UC_MSPEC__SCENARIO_DEFS:
				return getScenarioDefs();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case UcmPackage.UC_MSPEC__DEVICES:
				getDevices().clear();
				getDevices().addAll((Collection)newValue);
				return;
			case UcmPackage.UC_MSPEC__RESPTIMEREQ:
				getResptimereq().clear();
				getResptimereq().addAll((Collection)newValue);
				return;
			case UcmPackage.UC_MSPEC__MAPS:
				getMaps().clear();
				getMaps().addAll((Collection)newValue);
				return;
			case UcmPackage.UC_MSPEC__ROOT:
				getRoot().clear();
				getRoot().addAll((Collection)newValue);
				return;
			case UcmPackage.UC_MSPEC__SCENARIO_GROUPS:
				getScenarioGroups().clear();
				getScenarioGroups().addAll((Collection)newValue);
				return;
			case UcmPackage.UC_MSPEC__VARIABLES:
				getVariables().clear();
				getVariables().addAll((Collection)newValue);
				return;
			case UcmPackage.UC_MSPEC__SCENARIO_DEFS:
				getScenarioDefs().clear();
				getScenarioDefs().addAll((Collection)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case UcmPackage.UC_MSPEC__DEVICES:
				getDevices().clear();
				return;
			case UcmPackage.UC_MSPEC__RESPTIMEREQ:
				getResptimereq().clear();
				return;
			case UcmPackage.UC_MSPEC__MAPS:
				getMaps().clear();
				return;
			case UcmPackage.UC_MSPEC__ROOT:
				getRoot().clear();
				return;
			case UcmPackage.UC_MSPEC__SCENARIO_GROUPS:
				getScenarioGroups().clear();
				return;
			case UcmPackage.UC_MSPEC__VARIABLES:
				getVariables().clear();
				return;
			case UcmPackage.UC_MSPEC__SCENARIO_DEFS:
				getScenarioDefs().clear();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case UcmPackage.UC_MSPEC__DEVICES:
				return devices != null && !devices.isEmpty();
			case UcmPackage.UC_MSPEC__RESPTIMEREQ:
				return resptimereq != null && !resptimereq.isEmpty();
			case UcmPackage.UC_MSPEC__MAPS:
				return maps != null && !maps.isEmpty();
			case UcmPackage.UC_MSPEC__ROOT:
				return root != null && !root.isEmpty();
			case UcmPackage.UC_MSPEC__SCENARIO_GROUPS:
				return scenarioGroups != null && !scenarioGroups.isEmpty();
			case UcmPackage.UC_MSPEC__VARIABLES:
				return variables != null && !variables.isEmpty();
			case UcmPackage.UC_MSPEC__SCENARIO_DEFS:
				return scenarioDefs != null && !scenarioDefs.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //UCMspecImpl
