/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucmscenarios.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import ucmscenarios.Instance;
import ucmscenarios.ScenarioDef;
import ucmscenarios.Sequence;
import ucmscenarios.SequenceElement;
import ucmscenarios.UcmscenariosPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sequence Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucmscenarios.impl.SequenceElementImpl#getParentScenario <em>Parent Scenario</em>}</li>
 *   <li>{@link ucmscenarios.impl.SequenceElementImpl#getSequence <em>Sequence</em>}</li>
 *   <li>{@link ucmscenarios.impl.SequenceElementImpl#getInstance <em>Instance</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SequenceElementImpl extends ModelElementImpl implements SequenceElement {
	/**
	 * The cached value of the '{@link #getInstance() <em>Instance</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstance()
	 * @generated
	 * @ordered
	 */
	protected Instance instance;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SequenceElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return UcmscenariosPackage.Literals.SEQUENCE_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScenarioDef getParentScenario() {
		if (eContainerFeatureID() != UcmscenariosPackage.SEQUENCE_ELEMENT__PARENT_SCENARIO) return null;
		return (ScenarioDef)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParentScenario(ScenarioDef newParentScenario, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newParentScenario, UcmscenariosPackage.SEQUENCE_ELEMENT__PARENT_SCENARIO, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentScenario(ScenarioDef newParentScenario) {
		if (newParentScenario != eInternalContainer() || (eContainerFeatureID() != UcmscenariosPackage.SEQUENCE_ELEMENT__PARENT_SCENARIO && newParentScenario != null)) {
			if (EcoreUtil.isAncestor(this, newParentScenario))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newParentScenario != null)
				msgs = ((InternalEObject)newParentScenario).eInverseAdd(this, UcmscenariosPackage.SCENARIO_DEF__CHILDREN, ScenarioDef.class, msgs);
			msgs = basicSetParentScenario(newParentScenario, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UcmscenariosPackage.SEQUENCE_ELEMENT__PARENT_SCENARIO, newParentScenario, newParentScenario));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sequence getSequence() {
		if (eContainerFeatureID() != UcmscenariosPackage.SEQUENCE_ELEMENT__SEQUENCE) return null;
		return (Sequence)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSequence(Sequence newSequence, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newSequence, UcmscenariosPackage.SEQUENCE_ELEMENT__SEQUENCE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSequence(Sequence newSequence) {
		if (newSequence != eInternalContainer() || (eContainerFeatureID() != UcmscenariosPackage.SEQUENCE_ELEMENT__SEQUENCE && newSequence != null)) {
			if (EcoreUtil.isAncestor(this, newSequence))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newSequence != null)
				msgs = ((InternalEObject)newSequence).eInverseAdd(this, UcmscenariosPackage.SEQUENCE__CHILDREN, Sequence.class, msgs);
			msgs = basicSetSequence(newSequence, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UcmscenariosPackage.SEQUENCE_ELEMENT__SEQUENCE, newSequence, newSequence));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Instance getInstance() {
		if (instance != null && instance.eIsProxy()) {
			InternalEObject oldInstance = (InternalEObject)instance;
			instance = (Instance)eResolveProxy(oldInstance);
			if (instance != oldInstance) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UcmscenariosPackage.SEQUENCE_ELEMENT__INSTANCE, oldInstance, instance));
			}
		}
		return instance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Instance basicGetInstance() {
		return instance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInstance(Instance newInstance, NotificationChain msgs) {
		Instance oldInstance = instance;
		instance = newInstance;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UcmscenariosPackage.SEQUENCE_ELEMENT__INSTANCE, oldInstance, newInstance);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInstance(Instance newInstance) {
		if (newInstance != instance) {
			NotificationChain msgs = null;
			if (instance != null)
				msgs = ((InternalEObject)instance).eInverseRemove(this, UcmscenariosPackage.INSTANCE__ELEMENTS, Instance.class, msgs);
			if (newInstance != null)
				msgs = ((InternalEObject)newInstance).eInverseAdd(this, UcmscenariosPackage.INSTANCE__ELEMENTS, Instance.class, msgs);
			msgs = basicSetInstance(newInstance, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UcmscenariosPackage.SEQUENCE_ELEMENT__INSTANCE, newInstance, newInstance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UcmscenariosPackage.SEQUENCE_ELEMENT__PARENT_SCENARIO:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetParentScenario((ScenarioDef)otherEnd, msgs);
			case UcmscenariosPackage.SEQUENCE_ELEMENT__SEQUENCE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetSequence((Sequence)otherEnd, msgs);
			case UcmscenariosPackage.SEQUENCE_ELEMENT__INSTANCE:
				if (instance != null)
					msgs = ((InternalEObject)instance).eInverseRemove(this, UcmscenariosPackage.INSTANCE__ELEMENTS, Instance.class, msgs);
				return basicSetInstance((Instance)otherEnd, msgs);
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
			case UcmscenariosPackage.SEQUENCE_ELEMENT__PARENT_SCENARIO:
				return basicSetParentScenario(null, msgs);
			case UcmscenariosPackage.SEQUENCE_ELEMENT__SEQUENCE:
				return basicSetSequence(null, msgs);
			case UcmscenariosPackage.SEQUENCE_ELEMENT__INSTANCE:
				return basicSetInstance(null, msgs);
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
			case UcmscenariosPackage.SEQUENCE_ELEMENT__PARENT_SCENARIO:
				return eInternalContainer().eInverseRemove(this, UcmscenariosPackage.SCENARIO_DEF__CHILDREN, ScenarioDef.class, msgs);
			case UcmscenariosPackage.SEQUENCE_ELEMENT__SEQUENCE:
				return eInternalContainer().eInverseRemove(this, UcmscenariosPackage.SEQUENCE__CHILDREN, Sequence.class, msgs);
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
			case UcmscenariosPackage.SEQUENCE_ELEMENT__PARENT_SCENARIO:
				return getParentScenario();
			case UcmscenariosPackage.SEQUENCE_ELEMENT__SEQUENCE:
				return getSequence();
			case UcmscenariosPackage.SEQUENCE_ELEMENT__INSTANCE:
				if (resolve) return getInstance();
				return basicGetInstance();
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
			case UcmscenariosPackage.SEQUENCE_ELEMENT__PARENT_SCENARIO:
				setParentScenario((ScenarioDef)newValue);
				return;
			case UcmscenariosPackage.SEQUENCE_ELEMENT__SEQUENCE:
				setSequence((Sequence)newValue);
				return;
			case UcmscenariosPackage.SEQUENCE_ELEMENT__INSTANCE:
				setInstance((Instance)newValue);
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
			case UcmscenariosPackage.SEQUENCE_ELEMENT__PARENT_SCENARIO:
				setParentScenario((ScenarioDef)null);
				return;
			case UcmscenariosPackage.SEQUENCE_ELEMENT__SEQUENCE:
				setSequence((Sequence)null);
				return;
			case UcmscenariosPackage.SEQUENCE_ELEMENT__INSTANCE:
				setInstance((Instance)null);
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
			case UcmscenariosPackage.SEQUENCE_ELEMENT__PARENT_SCENARIO:
				return getParentScenario() != null;
			case UcmscenariosPackage.SEQUENCE_ELEMENT__SEQUENCE:
				return getSequence() != null;
			case UcmscenariosPackage.SEQUENCE_ELEMENT__INSTANCE:
				return instance != null;
		}
		return super.eIsSet(featureID);
	}

} //SequenceElementImpl