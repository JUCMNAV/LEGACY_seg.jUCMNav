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
import ucmscenarios.Message;
import ucmscenarios.ScenarioDef;
import ucmscenarios.SequenceElement;
import ucmscenarios.UcmscenariosPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucmscenarios.impl.InstanceImpl#getScenario <em>Scenario</em>}</li>
 *   <li>{@link ucmscenarios.impl.InstanceImpl#getDefinition <em>Definition</em>}</li>
 *   <li>{@link ucmscenarios.impl.InstanceImpl#getElements <em>Elements</em>}</li>
 *   <li>{@link ucmscenarios.impl.InstanceImpl#getSent <em>Sent</em>}</li>
 *   <li>{@link ucmscenarios.impl.InstanceImpl#getReceived <em>Received</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InstanceImpl extends ModelElementImpl implements Instance {
	/**
	 * The cached value of the '{@link #getDefinition() <em>Definition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefinition()
	 * @generated
	 * @ordered
	 */
	protected Component definition;

	/**
	 * The cached value of the '{@link #getElements() <em>Elements</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElements()
	 * @generated
	 * @ordered
	 */
	protected EList elements;

	/**
	 * The cached value of the '{@link #getSent() <em>Sent</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSent()
	 * @generated
	 * @ordered
	 */
	protected EList sent;

	/**
	 * The cached value of the '{@link #getReceived() <em>Received</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReceived()
	 * @generated
	 * @ordered
	 */
	protected EList received;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return UcmscenariosPackage.Literals.INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScenarioDef getScenario() {
		if (eContainerFeatureID() != UcmscenariosPackage.INSTANCE__SCENARIO) return null;
		return (ScenarioDef)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetScenario(ScenarioDef newScenario, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newScenario, UcmscenariosPackage.INSTANCE__SCENARIO, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScenario(ScenarioDef newScenario) {
		if (newScenario != eInternalContainer() || (eContainerFeatureID() != UcmscenariosPackage.INSTANCE__SCENARIO && newScenario != null)) {
			if (EcoreUtil.isAncestor(this, newScenario))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newScenario != null)
				msgs = ((InternalEObject)newScenario).eInverseAdd(this, UcmscenariosPackage.SCENARIO_DEF__INSTANCES, ScenarioDef.class, msgs);
			msgs = basicSetScenario(newScenario, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UcmscenariosPackage.INSTANCE__SCENARIO, newScenario, newScenario));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Component getDefinition() {
		if (definition != null && definition.eIsProxy()) {
			InternalEObject oldDefinition = (InternalEObject)definition;
			definition = (Component)eResolveProxy(oldDefinition);
			if (definition != oldDefinition) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UcmscenariosPackage.INSTANCE__DEFINITION, oldDefinition, definition));
			}
		}
		return definition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Component basicGetDefinition() {
		return definition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDefinition(Component newDefinition, NotificationChain msgs) {
		Component oldDefinition = definition;
		definition = newDefinition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UcmscenariosPackage.INSTANCE__DEFINITION, oldDefinition, newDefinition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefinition(Component newDefinition) {
		if (newDefinition != definition) {
			NotificationChain msgs = null;
			if (definition != null)
				msgs = ((InternalEObject)definition).eInverseRemove(this, UcmscenariosPackage.COMPONENT__INSTANCES, Component.class, msgs);
			if (newDefinition != null)
				msgs = ((InternalEObject)newDefinition).eInverseAdd(this, UcmscenariosPackage.COMPONENT__INSTANCES, Component.class, msgs);
			msgs = basicSetDefinition(newDefinition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UcmscenariosPackage.INSTANCE__DEFINITION, newDefinition, newDefinition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getElements() {
		if (elements == null) {
			elements = new EObjectWithInverseResolvingEList(SequenceElement.class, this, UcmscenariosPackage.INSTANCE__ELEMENTS, UcmscenariosPackage.SEQUENCE_ELEMENT__INSTANCE);
		}
		return elements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getSent() {
		if (sent == null) {
			sent = new EObjectWithInverseResolvingEList(Message.class, this, UcmscenariosPackage.INSTANCE__SENT, UcmscenariosPackage.MESSAGE__SOURCE);
		}
		return sent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getReceived() {
		if (received == null) {
			received = new EObjectWithInverseResolvingEList(Message.class, this, UcmscenariosPackage.INSTANCE__RECEIVED, UcmscenariosPackage.MESSAGE__TARGET);
		}
		return received;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UcmscenariosPackage.INSTANCE__SCENARIO:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetScenario((ScenarioDef)otherEnd, msgs);
			case UcmscenariosPackage.INSTANCE__DEFINITION:
				if (definition != null)
					msgs = ((InternalEObject)definition).eInverseRemove(this, UcmscenariosPackage.COMPONENT__INSTANCES, Component.class, msgs);
				return basicSetDefinition((Component)otherEnd, msgs);
			case UcmscenariosPackage.INSTANCE__ELEMENTS:
				return ((InternalEList)getElements()).basicAdd(otherEnd, msgs);
			case UcmscenariosPackage.INSTANCE__SENT:
				return ((InternalEList)getSent()).basicAdd(otherEnd, msgs);
			case UcmscenariosPackage.INSTANCE__RECEIVED:
				return ((InternalEList)getReceived()).basicAdd(otherEnd, msgs);
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
			case UcmscenariosPackage.INSTANCE__SCENARIO:
				return basicSetScenario(null, msgs);
			case UcmscenariosPackage.INSTANCE__DEFINITION:
				return basicSetDefinition(null, msgs);
			case UcmscenariosPackage.INSTANCE__ELEMENTS:
				return ((InternalEList)getElements()).basicRemove(otherEnd, msgs);
			case UcmscenariosPackage.INSTANCE__SENT:
				return ((InternalEList)getSent()).basicRemove(otherEnd, msgs);
			case UcmscenariosPackage.INSTANCE__RECEIVED:
				return ((InternalEList)getReceived()).basicRemove(otherEnd, msgs);
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
			case UcmscenariosPackage.INSTANCE__SCENARIO:
				return eInternalContainer().eInverseRemove(this, UcmscenariosPackage.SCENARIO_DEF__INSTANCES, ScenarioDef.class, msgs);
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
			case UcmscenariosPackage.INSTANCE__SCENARIO:
				return getScenario();
			case UcmscenariosPackage.INSTANCE__DEFINITION:
				if (resolve) return getDefinition();
				return basicGetDefinition();
			case UcmscenariosPackage.INSTANCE__ELEMENTS:
				return getElements();
			case UcmscenariosPackage.INSTANCE__SENT:
				return getSent();
			case UcmscenariosPackage.INSTANCE__RECEIVED:
				return getReceived();
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
			case UcmscenariosPackage.INSTANCE__SCENARIO:
				setScenario((ScenarioDef)newValue);
				return;
			case UcmscenariosPackage.INSTANCE__DEFINITION:
				setDefinition((Component)newValue);
				return;
			case UcmscenariosPackage.INSTANCE__ELEMENTS:
				getElements().clear();
				getElements().addAll((Collection)newValue);
				return;
			case UcmscenariosPackage.INSTANCE__SENT:
				getSent().clear();
				getSent().addAll((Collection)newValue);
				return;
			case UcmscenariosPackage.INSTANCE__RECEIVED:
				getReceived().clear();
				getReceived().addAll((Collection)newValue);
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
			case UcmscenariosPackage.INSTANCE__SCENARIO:
				setScenario((ScenarioDef)null);
				return;
			case UcmscenariosPackage.INSTANCE__DEFINITION:
				setDefinition((Component)null);
				return;
			case UcmscenariosPackage.INSTANCE__ELEMENTS:
				getElements().clear();
				return;
			case UcmscenariosPackage.INSTANCE__SENT:
				getSent().clear();
				return;
			case UcmscenariosPackage.INSTANCE__RECEIVED:
				getReceived().clear();
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
			case UcmscenariosPackage.INSTANCE__SCENARIO:
				return getScenario() != null;
			case UcmscenariosPackage.INSTANCE__DEFINITION:
				return definition != null;
			case UcmscenariosPackage.INSTANCE__ELEMENTS:
				return elements != null && !elements.isEmpty();
			case UcmscenariosPackage.INSTANCE__SENT:
				return sent != null && !sent.isEmpty();
			case UcmscenariosPackage.INSTANCE__RECEIVED:
				return received != null && !received.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //InstanceImpl