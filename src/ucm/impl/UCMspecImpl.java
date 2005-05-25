/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import ucm.UCMspec;
import ucm.UcmPackage;

import ucm.map.Map;
import ucm.map.MapPackage;

import ucm.performance.GeneralResource;
import ucm.performance.PerfMeasure;
import ucm.performance.PerformancePackage;
import ucm.performance.ResponseTimeReq;

import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;
import ucm.scenario.ScenarioPackage;
import ucm.scenario.Variable;

import urn.URNspec;
import urn.UrnPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>UC Mspec</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.impl.UCMspecImpl#getUrnspec <em>Urnspec</em>}</li>
 *   <li>{@link ucm.impl.UCMspecImpl#getResptimereq <em>Resptimereq</em>}</li>
 *   <li>{@link ucm.impl.UCMspecImpl#getPerfMeasures <em>Perf Measures</em>}</li>
 *   <li>{@link ucm.impl.UCMspecImpl#getResources <em>Resources</em>}</li>
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
	 * The cached value of the '{@link #getResptimereq() <em>Resptimereq</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResptimereq()
	 * @generated
	 * @ordered
	 */
	protected EList resptimereq = null;

	/**
	 * The cached value of the '{@link #getPerfMeasures() <em>Perf Measures</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPerfMeasures()
	 * @generated
	 * @ordered
	 */
	protected EList perfMeasures = null;

	/**
	 * The cached value of the '{@link #getResources() <em>Resources</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResources()
	 * @generated
	 * @ordered
	 */
	protected EList resources = null;

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
	public URNspec getUrnspec() {
		if (eContainerFeatureID != UcmPackage.UC_MSPEC__URNSPEC) return null;
		return (URNspec)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUrnspec(URNspec newUrnspec) {
		if (newUrnspec != eContainer || (eContainerFeatureID != UcmPackage.UC_MSPEC__URNSPEC && newUrnspec != null)) {
			if (EcoreUtil.isAncestor(this, newUrnspec))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newUrnspec != null)
				msgs = ((InternalEObject)newUrnspec).eInverseAdd(this, UrnPackage.UR_NSPEC__UCMSPEC, URNspec.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newUrnspec, UcmPackage.UC_MSPEC__URNSPEC, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UcmPackage.UC_MSPEC__URNSPEC, newUrnspec, newUrnspec));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getResptimereq() {
		if (resptimereq == null) {
			resptimereq = new EObjectContainmentWithInverseEList(ResponseTimeReq.class, this, UcmPackage.UC_MSPEC__RESPTIMEREQ, PerformancePackage.RESPONSE_TIME_REQ__UCMSPEC);
		}
		return resptimereq;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getPerfMeasures() {
		if (perfMeasures == null) {
			perfMeasures = new EObjectContainmentWithInverseEList(PerfMeasure.class, this, UcmPackage.UC_MSPEC__PERF_MEASURES, PerformancePackage.PERF_MEASURE__UCMSPEC);
		}
		return perfMeasures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getResources() {
		if (resources == null) {
			resources = new EObjectContainmentWithInverseEList(GeneralResource.class, this, UcmPackage.UC_MSPEC__RESOURCES, PerformancePackage.GENERAL_RESOURCE__UCMSPEC);
		}
		return resources;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getMaps() {
		if (maps == null) {
			maps = new EObjectContainmentWithInverseEList(Map.class, this, UcmPackage.UC_MSPEC__MAPS, MapPackage.MAP__UCMSPEC);
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
			scenarioGroups = new EObjectContainmentWithInverseEList(ScenarioGroup.class, this, UcmPackage.UC_MSPEC__SCENARIO_GROUPS, ScenarioPackage.SCENARIO_GROUP__URNSPEC);
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
			variables = new EObjectContainmentWithInverseEList(Variable.class, this, UcmPackage.UC_MSPEC__VARIABLES, ScenarioPackage.VARIABLE__URNSPEC);
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
			scenarioDefs = new EObjectContainmentWithInverseEList(ScenarioDef.class, this, UcmPackage.UC_MSPEC__SCENARIO_DEFS, ScenarioPackage.SCENARIO_DEF__URNSPEC);
		}
		return scenarioDefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case UcmPackage.UC_MSPEC__URNSPEC:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, UcmPackage.UC_MSPEC__URNSPEC, msgs);
				case UcmPackage.UC_MSPEC__RESPTIMEREQ:
					return ((InternalEList)getResptimereq()).basicAdd(otherEnd, msgs);
				case UcmPackage.UC_MSPEC__PERF_MEASURES:
					return ((InternalEList)getPerfMeasures()).basicAdd(otherEnd, msgs);
				case UcmPackage.UC_MSPEC__RESOURCES:
					return ((InternalEList)getResources()).basicAdd(otherEnd, msgs);
				case UcmPackage.UC_MSPEC__MAPS:
					return ((InternalEList)getMaps()).basicAdd(otherEnd, msgs);
				case UcmPackage.UC_MSPEC__SCENARIO_GROUPS:
					return ((InternalEList)getScenarioGroups()).basicAdd(otherEnd, msgs);
				case UcmPackage.UC_MSPEC__VARIABLES:
					return ((InternalEList)getVariables()).basicAdd(otherEnd, msgs);
				case UcmPackage.UC_MSPEC__SCENARIO_DEFS:
					return ((InternalEList)getScenarioDefs()).basicAdd(otherEnd, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case UcmPackage.UC_MSPEC__URNSPEC:
					return eBasicSetContainer(null, UcmPackage.UC_MSPEC__URNSPEC, msgs);
				case UcmPackage.UC_MSPEC__RESPTIMEREQ:
					return ((InternalEList)getResptimereq()).basicRemove(otherEnd, msgs);
				case UcmPackage.UC_MSPEC__PERF_MEASURES:
					return ((InternalEList)getPerfMeasures()).basicRemove(otherEnd, msgs);
				case UcmPackage.UC_MSPEC__RESOURCES:
					return ((InternalEList)getResources()).basicRemove(otherEnd, msgs);
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
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		if (eContainerFeatureID >= 0) {
			switch (eContainerFeatureID) {
				case UcmPackage.UC_MSPEC__URNSPEC:
					return ((InternalEObject)eContainer).eInverseRemove(this, UrnPackage.UR_NSPEC__UCMSPEC, URNspec.class, msgs);
				default:
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return ((InternalEObject)eContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case UcmPackage.UC_MSPEC__URNSPEC:
				return getUrnspec();
			case UcmPackage.UC_MSPEC__RESPTIMEREQ:
				return getResptimereq();
			case UcmPackage.UC_MSPEC__PERF_MEASURES:
				return getPerfMeasures();
			case UcmPackage.UC_MSPEC__RESOURCES:
				return getResources();
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
			case UcmPackage.UC_MSPEC__URNSPEC:
				setUrnspec((URNspec)newValue);
				return;
			case UcmPackage.UC_MSPEC__RESPTIMEREQ:
				getResptimereq().clear();
				getResptimereq().addAll((Collection)newValue);
				return;
			case UcmPackage.UC_MSPEC__PERF_MEASURES:
				getPerfMeasures().clear();
				getPerfMeasures().addAll((Collection)newValue);
				return;
			case UcmPackage.UC_MSPEC__RESOURCES:
				getResources().clear();
				getResources().addAll((Collection)newValue);
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
			case UcmPackage.UC_MSPEC__URNSPEC:
				setUrnspec((URNspec)null);
				return;
			case UcmPackage.UC_MSPEC__RESPTIMEREQ:
				getResptimereq().clear();
				return;
			case UcmPackage.UC_MSPEC__PERF_MEASURES:
				getPerfMeasures().clear();
				return;
			case UcmPackage.UC_MSPEC__RESOURCES:
				getResources().clear();
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
			case UcmPackage.UC_MSPEC__URNSPEC:
				return getUrnspec() != null;
			case UcmPackage.UC_MSPEC__RESPTIMEREQ:
				return resptimereq != null && !resptimereq.isEmpty();
			case UcmPackage.UC_MSPEC__PERF_MEASURES:
				return perfMeasures != null && !perfMeasures.isEmpty();
			case UcmPackage.UC_MSPEC__RESOURCES:
				return resources != null && !resources.isEmpty();
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
