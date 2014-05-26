/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucmscenarios.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import ucmscenarios.ScenarioDef;
import ucmscenarios.ScenarioGroup;
import ucmscenarios.ScenarioSpec;
import ucmscenarios.UcmscenariosPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Scenario Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucmscenarios.impl.ScenarioGroupImpl#getScenarioSpec <em>Scenario Spec</em>}</li>
 *   <li>{@link ucmscenarios.impl.ScenarioGroupImpl#getScenarios <em>Scenarios</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScenarioGroupImpl extends ModelElementImpl implements ScenarioGroup {
	/**
	 * The cached value of the '{@link #getScenarios() <em>Scenarios</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScenarios()
	 * @generated
	 * @ordered
	 */
	protected EList scenarios;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScenarioGroupImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return UcmscenariosPackage.Literals.SCENARIO_GROUP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScenarioSpec getScenarioSpec() {
		if (eContainerFeatureID() != UcmscenariosPackage.SCENARIO_GROUP__SCENARIO_SPEC) return null;
		return (ScenarioSpec)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetScenarioSpec(ScenarioSpec newScenarioSpec, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newScenarioSpec, UcmscenariosPackage.SCENARIO_GROUP__SCENARIO_SPEC, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScenarioSpec(ScenarioSpec newScenarioSpec) {
		if (newScenarioSpec != eInternalContainer() || (eContainerFeatureID() != UcmscenariosPackage.SCENARIO_GROUP__SCENARIO_SPEC && newScenarioSpec != null)) {
			if (EcoreUtil.isAncestor(this, newScenarioSpec))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newScenarioSpec != null)
				msgs = ((InternalEObject)newScenarioSpec).eInverseAdd(this, UcmscenariosPackage.SCENARIO_SPEC__GROUPS, ScenarioSpec.class, msgs);
			msgs = basicSetScenarioSpec(newScenarioSpec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UcmscenariosPackage.SCENARIO_GROUP__SCENARIO_SPEC, newScenarioSpec, newScenarioSpec));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getScenarios() {
		if (scenarios == null) {
			scenarios = new EObjectContainmentWithInverseEList(ScenarioDef.class, this, UcmscenariosPackage.SCENARIO_GROUP__SCENARIOS, UcmscenariosPackage.SCENARIO_DEF__GROUP);
		}
		return scenarios;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UcmscenariosPackage.SCENARIO_GROUP__SCENARIO_SPEC:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetScenarioSpec((ScenarioSpec)otherEnd, msgs);
			case UcmscenariosPackage.SCENARIO_GROUP__SCENARIOS:
				return ((InternalEList)getScenarios()).basicAdd(otherEnd, msgs);
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
			case UcmscenariosPackage.SCENARIO_GROUP__SCENARIO_SPEC:
				return basicSetScenarioSpec(null, msgs);
			case UcmscenariosPackage.SCENARIO_GROUP__SCENARIOS:
				return ((InternalEList)getScenarios()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case UcmscenariosPackage.SCENARIO_GROUP__SCENARIO_SPEC:
				return eInternalContainer().eInverseRemove(this, UcmscenariosPackage.SCENARIO_SPEC__GROUPS, ScenarioSpec.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UcmscenariosPackage.SCENARIO_GROUP__SCENARIO_SPEC:
				return getScenarioSpec();
			case UcmscenariosPackage.SCENARIO_GROUP__SCENARIOS:
				return getScenarios();
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
			case UcmscenariosPackage.SCENARIO_GROUP__SCENARIO_SPEC:
				setScenarioSpec((ScenarioSpec)newValue);
				return;
			case UcmscenariosPackage.SCENARIO_GROUP__SCENARIOS:
				getScenarios().clear();
				getScenarios().addAll((Collection)newValue);
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
			case UcmscenariosPackage.SCENARIO_GROUP__SCENARIO_SPEC:
				setScenarioSpec((ScenarioSpec)null);
				return;
			case UcmscenariosPackage.SCENARIO_GROUP__SCENARIOS:
				getScenarios().clear();
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
			case UcmscenariosPackage.SCENARIO_GROUP__SCENARIO_SPEC:
				return getScenarioSpec() != null;
			case UcmscenariosPackage.SCENARIO_GROUP__SCENARIOS:
				return scenarios != null && !scenarios.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ScenarioGroupImpl