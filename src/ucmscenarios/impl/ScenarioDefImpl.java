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

import ucmscenarios.Instance;
import ucmscenarios.ScenarioDef;
import ucmscenarios.ScenarioGroup;
import ucmscenarios.SequenceElement;
import ucmscenarios.UcmscenariosPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Scenario Def</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucmscenarios.impl.ScenarioDefImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link ucmscenarios.impl.ScenarioDefImpl#getInstances <em>Instances</em>}</li>
 *   <li>{@link ucmscenarios.impl.ScenarioDefImpl#getChildren <em>Children</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScenarioDefImpl extends ModelElementImpl implements ScenarioDef {
	/**
	 * The cached value of the '{@link #getInstances() <em>Instances</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstances()
	 * @generated
	 * @ordered
	 */
	protected EList instances;

	/**
	 * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildren()
	 * @generated
	 * @ordered
	 */
	protected EList children;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScenarioDefImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return UcmscenariosPackage.Literals.SCENARIO_DEF;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScenarioGroup getGroup() {
		if (eContainerFeatureID() != UcmscenariosPackage.SCENARIO_DEF__GROUP) return null;
		return (ScenarioGroup)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGroup(ScenarioGroup newGroup, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newGroup, UcmscenariosPackage.SCENARIO_DEF__GROUP, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroup(ScenarioGroup newGroup) {
		if (newGroup != eInternalContainer() || (eContainerFeatureID() != UcmscenariosPackage.SCENARIO_DEF__GROUP && newGroup != null)) {
			if (EcoreUtil.isAncestor(this, newGroup))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newGroup != null)
				msgs = ((InternalEObject)newGroup).eInverseAdd(this, UcmscenariosPackage.SCENARIO_GROUP__SCENARIOS, ScenarioGroup.class, msgs);
			msgs = basicSetGroup(newGroup, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UcmscenariosPackage.SCENARIO_DEF__GROUP, newGroup, newGroup));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getInstances() {
		if (instances == null) {
			instances = new EObjectContainmentWithInverseEList(Instance.class, this, UcmscenariosPackage.SCENARIO_DEF__INSTANCES, UcmscenariosPackage.INSTANCE__SCENARIO);
		}
		return instances;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getChildren() {
		if (children == null) {
			children = new EObjectContainmentWithInverseEList(SequenceElement.class, this, UcmscenariosPackage.SCENARIO_DEF__CHILDREN, UcmscenariosPackage.SEQUENCE_ELEMENT__PARENT_SCENARIO);
		}
		return children;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UcmscenariosPackage.SCENARIO_DEF__GROUP:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetGroup((ScenarioGroup)otherEnd, msgs);
			case UcmscenariosPackage.SCENARIO_DEF__INSTANCES:
				return ((InternalEList)getInstances()).basicAdd(otherEnd, msgs);
			case UcmscenariosPackage.SCENARIO_DEF__CHILDREN:
				return ((InternalEList)getChildren()).basicAdd(otherEnd, msgs);
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
			case UcmscenariosPackage.SCENARIO_DEF__GROUP:
				return basicSetGroup(null, msgs);
			case UcmscenariosPackage.SCENARIO_DEF__INSTANCES:
				return ((InternalEList)getInstances()).basicRemove(otherEnd, msgs);
			case UcmscenariosPackage.SCENARIO_DEF__CHILDREN:
				return ((InternalEList)getChildren()).basicRemove(otherEnd, msgs);
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
			case UcmscenariosPackage.SCENARIO_DEF__GROUP:
				return eInternalContainer().eInverseRemove(this, UcmscenariosPackage.SCENARIO_GROUP__SCENARIOS, ScenarioGroup.class, msgs);
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
			case UcmscenariosPackage.SCENARIO_DEF__GROUP:
				return getGroup();
			case UcmscenariosPackage.SCENARIO_DEF__INSTANCES:
				return getInstances();
			case UcmscenariosPackage.SCENARIO_DEF__CHILDREN:
				return getChildren();
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
			case UcmscenariosPackage.SCENARIO_DEF__GROUP:
				setGroup((ScenarioGroup)newValue);
				return;
			case UcmscenariosPackage.SCENARIO_DEF__INSTANCES:
				getInstances().clear();
				getInstances().addAll((Collection)newValue);
				return;
			case UcmscenariosPackage.SCENARIO_DEF__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection)newValue);
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
			case UcmscenariosPackage.SCENARIO_DEF__GROUP:
				setGroup((ScenarioGroup)null);
				return;
			case UcmscenariosPackage.SCENARIO_DEF__INSTANCES:
				getInstances().clear();
				return;
			case UcmscenariosPackage.SCENARIO_DEF__CHILDREN:
				getChildren().clear();
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
			case UcmscenariosPackage.SCENARIO_DEF__GROUP:
				return getGroup() != null;
			case UcmscenariosPackage.SCENARIO_DEF__INSTANCES:
				return instances != null && !instances.isEmpty();
			case UcmscenariosPackage.SCENARIO_DEF__CHILDREN:
				return children != null && !children.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ScenarioDefImpl