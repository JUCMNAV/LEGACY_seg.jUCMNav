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
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import ucmscenarios.Component;
import ucmscenarios.Instance;
import ucmscenarios.ScenarioSpec;
import ucmscenarios.UcmscenariosPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucmscenarios.impl.ComponentImpl#getScenarioSpec <em>Scenario Spec</em>}</li>
 *   <li>{@link ucmscenarios.impl.ComponentImpl#getInstances <em>Instances</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComponentImpl extends ModelElementImpl implements Component {
	/**
	 * The cached value of the '{@link #getInstances() <em>Instances</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstances()
	 * @generated
	 * @ordered
	 */
	protected EList instances;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return UcmscenariosPackage.Literals.COMPONENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScenarioSpec getScenarioSpec() {
		if (eContainerFeatureID() != UcmscenariosPackage.COMPONENT__SCENARIO_SPEC) return null;
		return (ScenarioSpec)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetScenarioSpec(ScenarioSpec newScenarioSpec, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newScenarioSpec, UcmscenariosPackage.COMPONENT__SCENARIO_SPEC, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScenarioSpec(ScenarioSpec newScenarioSpec) {
		if (newScenarioSpec != eInternalContainer() || (eContainerFeatureID() != UcmscenariosPackage.COMPONENT__SCENARIO_SPEC && newScenarioSpec != null)) {
			if (EcoreUtil.isAncestor(this, newScenarioSpec))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newScenarioSpec != null)
				msgs = ((InternalEObject)newScenarioSpec).eInverseAdd(this, UcmscenariosPackage.SCENARIO_SPEC__COMPONENTS, ScenarioSpec.class, msgs);
			msgs = basicSetScenarioSpec(newScenarioSpec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UcmscenariosPackage.COMPONENT__SCENARIO_SPEC, newScenarioSpec, newScenarioSpec));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getInstances() {
		if (instances == null) {
			instances = new EObjectWithInverseResolvingEList(Instance.class, this, UcmscenariosPackage.COMPONENT__INSTANCES, UcmscenariosPackage.INSTANCE__DEFINITION);
		}
		return instances;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UcmscenariosPackage.COMPONENT__SCENARIO_SPEC:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetScenarioSpec((ScenarioSpec)otherEnd, msgs);
			case UcmscenariosPackage.COMPONENT__INSTANCES:
				return ((InternalEList)getInstances()).basicAdd(otherEnd, msgs);
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
			case UcmscenariosPackage.COMPONENT__SCENARIO_SPEC:
				return basicSetScenarioSpec(null, msgs);
			case UcmscenariosPackage.COMPONENT__INSTANCES:
				return ((InternalEList)getInstances()).basicRemove(otherEnd, msgs);
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
			case UcmscenariosPackage.COMPONENT__SCENARIO_SPEC:
				return eInternalContainer().eInverseRemove(this, UcmscenariosPackage.SCENARIO_SPEC__COMPONENTS, ScenarioSpec.class, msgs);
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
			case UcmscenariosPackage.COMPONENT__SCENARIO_SPEC:
				return getScenarioSpec();
			case UcmscenariosPackage.COMPONENT__INSTANCES:
				return getInstances();
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
			case UcmscenariosPackage.COMPONENT__SCENARIO_SPEC:
				setScenarioSpec((ScenarioSpec)newValue);
				return;
			case UcmscenariosPackage.COMPONENT__INSTANCES:
				getInstances().clear();
				getInstances().addAll((Collection)newValue);
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
			case UcmscenariosPackage.COMPONENT__SCENARIO_SPEC:
				setScenarioSpec((ScenarioSpec)null);
				return;
			case UcmscenariosPackage.COMPONENT__INSTANCES:
				getInstances().clear();
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
			case UcmscenariosPackage.COMPONENT__SCENARIO_SPEC:
				return getScenarioSpec() != null;
			case UcmscenariosPackage.COMPONENT__INSTANCES:
				return instances != null && !instances.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ComponentImpl