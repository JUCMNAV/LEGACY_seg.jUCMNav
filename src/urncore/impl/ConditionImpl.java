/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import ucm.map.EndPoint;
import ucm.map.MapPackage;
import ucm.map.NodeConnection;
import ucm.map.PluginBinding;
import ucm.map.StartPoint;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioPackage;
import urncore.Concern;
import urncore.Condition;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Condition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link urncore.impl.ConditionImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link urncore.impl.ConditionImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link urncore.impl.ConditionImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link urncore.impl.ConditionImpl#getStartPoint <em>Start Point</em>}</li>
 *   <li>{@link urncore.impl.ConditionImpl#getEndPoint <em>End Point</em>}</li>
 *   <li>{@link urncore.impl.ConditionImpl#getPluginBinding <em>Plugin Binding</em>}</li>
 *   <li>{@link urncore.impl.ConditionImpl#getNodeConnection <em>Node Connection</em>}</li>
 *   <li>{@link urncore.impl.ConditionImpl#getConcern <em>Concern</em>}</li>
 *   <li>{@link urncore.impl.ConditionImpl#getScenarioDefPre <em>Scenario Def Pre</em>}</li>
 *   <li>{@link urncore.impl.ConditionImpl#getScenarioDefPost <em>Scenario Def Post</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConditionImpl extends LabelImpl implements Condition {
    /**
	 * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
    protected static final String LABEL_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
    protected String label = LABEL_EDEFAULT;

    /**
	 * The default value of the '{@link #getExpression() <em>Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getExpression()
	 * @generated
	 * @ordered
	 */
    protected static final String EXPRESSION_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getExpression() <em>Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getExpression()
	 * @generated
	 * @ordered
	 */
    protected String expression = EXPRESSION_EDEFAULT;

    /**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
    protected static final String DESCRIPTION_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
    protected String description = DESCRIPTION_EDEFAULT;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected ConditionImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return UrncorePackage.Literals.CONDITION;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getLabel() {
		return label;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setLabel(String newLabel) {
		String oldLabel = label;
		label = newLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.CONDITION__LABEL, oldLabel, label));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getExpression() {
		return expression;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setExpression(String newExpression) {
		String oldExpression = expression;
		expression = newExpression;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.CONDITION__EXPRESSION, oldExpression, expression));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getDescription() {
		return description;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.CONDITION__DESCRIPTION, oldDescription, description));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public StartPoint getStartPoint() {
		if (eContainerFeatureID() != UrncorePackage.CONDITION__START_POINT) return null;
		return (StartPoint)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetStartPoint(StartPoint newStartPoint, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newStartPoint, UrncorePackage.CONDITION__START_POINT, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setStartPoint(StartPoint newStartPoint) {
		if (newStartPoint != eInternalContainer() || (eContainerFeatureID() != UrncorePackage.CONDITION__START_POINT && newStartPoint != null)) {
			if (EcoreUtil.isAncestor(this, newStartPoint))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newStartPoint != null)
				msgs = ((InternalEObject)newStartPoint).eInverseAdd(this, MapPackage.START_POINT__PRECONDITION, StartPoint.class, msgs);
			msgs = basicSetStartPoint(newStartPoint, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.CONDITION__START_POINT, newStartPoint, newStartPoint));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EndPoint getEndPoint() {
		if (eContainerFeatureID() != UrncorePackage.CONDITION__END_POINT) return null;
		return (EndPoint)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetEndPoint(EndPoint newEndPoint, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newEndPoint, UrncorePackage.CONDITION__END_POINT, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setEndPoint(EndPoint newEndPoint) {
		if (newEndPoint != eInternalContainer() || (eContainerFeatureID() != UrncorePackage.CONDITION__END_POINT && newEndPoint != null)) {
			if (EcoreUtil.isAncestor(this, newEndPoint))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newEndPoint != null)
				msgs = ((InternalEObject)newEndPoint).eInverseAdd(this, MapPackage.END_POINT__POSTCONDITION, EndPoint.class, msgs);
			msgs = basicSetEndPoint(newEndPoint, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.CONDITION__END_POINT, newEndPoint, newEndPoint));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PluginBinding getPluginBinding() {
		if (eContainerFeatureID() != UrncorePackage.CONDITION__PLUGIN_BINDING) return null;
		return (PluginBinding)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetPluginBinding(PluginBinding newPluginBinding, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newPluginBinding, UrncorePackage.CONDITION__PLUGIN_BINDING, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setPluginBinding(PluginBinding newPluginBinding) {
		if (newPluginBinding != eInternalContainer() || (eContainerFeatureID() != UrncorePackage.CONDITION__PLUGIN_BINDING && newPluginBinding != null)) {
			if (EcoreUtil.isAncestor(this, newPluginBinding))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPluginBinding != null)
				msgs = ((InternalEObject)newPluginBinding).eInverseAdd(this, MapPackage.PLUGIN_BINDING__PRECONDITION, PluginBinding.class, msgs);
			msgs = basicSetPluginBinding(newPluginBinding, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.CONDITION__PLUGIN_BINDING, newPluginBinding, newPluginBinding));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NodeConnection getNodeConnection() {
		if (eContainerFeatureID() != UrncorePackage.CONDITION__NODE_CONNECTION) return null;
		return (NodeConnection)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetNodeConnection(NodeConnection newNodeConnection, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newNodeConnection, UrncorePackage.CONDITION__NODE_CONNECTION, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setNodeConnection(NodeConnection newNodeConnection) {
		if (newNodeConnection != eInternalContainer() || (eContainerFeatureID() != UrncorePackage.CONDITION__NODE_CONNECTION && newNodeConnection != null)) {
			if (EcoreUtil.isAncestor(this, newNodeConnection))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newNodeConnection != null)
				msgs = ((InternalEObject)newNodeConnection).eInverseAdd(this, MapPackage.NODE_CONNECTION__CONDITION, NodeConnection.class, msgs);
			msgs = basicSetNodeConnection(newNodeConnection, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.CONDITION__NODE_CONNECTION, newNodeConnection, newNodeConnection));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Concern getConcern() {
		if (eContainerFeatureID() != UrncorePackage.CONDITION__CONCERN) return null;
		return (Concern)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetConcern(Concern newConcern, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newConcern, UrncorePackage.CONDITION__CONCERN, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setConcern(Concern newConcern) {
		if (newConcern != eInternalContainer() || (eContainerFeatureID() != UrncorePackage.CONDITION__CONCERN && newConcern != null)) {
			if (EcoreUtil.isAncestor(this, newConcern))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newConcern != null)
				msgs = ((InternalEObject)newConcern).eInverseAdd(this, UrncorePackage.CONCERN__CONDITION, Concern.class, msgs);
			msgs = basicSetConcern(newConcern, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.CONDITION__CONCERN, newConcern, newConcern));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ScenarioDef getScenarioDefPre() {
		if (eContainerFeatureID() != UrncorePackage.CONDITION__SCENARIO_DEF_PRE) return null;
		return (ScenarioDef)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetScenarioDefPre(ScenarioDef newScenarioDefPre, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newScenarioDefPre, UrncorePackage.CONDITION__SCENARIO_DEF_PRE, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setScenarioDefPre(ScenarioDef newScenarioDefPre) {
		if (newScenarioDefPre != eInternalContainer() || (eContainerFeatureID() != UrncorePackage.CONDITION__SCENARIO_DEF_PRE && newScenarioDefPre != null)) {
			if (EcoreUtil.isAncestor(this, newScenarioDefPre))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newScenarioDefPre != null)
				msgs = ((InternalEObject)newScenarioDefPre).eInverseAdd(this, ScenarioPackage.SCENARIO_DEF__PRECONDITIONS, ScenarioDef.class, msgs);
			msgs = basicSetScenarioDefPre(newScenarioDefPre, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.CONDITION__SCENARIO_DEF_PRE, newScenarioDefPre, newScenarioDefPre));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ScenarioDef getScenarioDefPost() {
		if (eContainerFeatureID() != UrncorePackage.CONDITION__SCENARIO_DEF_POST) return null;
		return (ScenarioDef)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetScenarioDefPost(ScenarioDef newScenarioDefPost, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newScenarioDefPost, UrncorePackage.CONDITION__SCENARIO_DEF_POST, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setScenarioDefPost(ScenarioDef newScenarioDefPost) {
		if (newScenarioDefPost != eInternalContainer() || (eContainerFeatureID() != UrncorePackage.CONDITION__SCENARIO_DEF_POST && newScenarioDefPost != null)) {
			if (EcoreUtil.isAncestor(this, newScenarioDefPost))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newScenarioDefPost != null)
				msgs = ((InternalEObject)newScenarioDefPost).eInverseAdd(this, ScenarioPackage.SCENARIO_DEF__POSTCONDITIONS, ScenarioDef.class, msgs);
			msgs = basicSetScenarioDefPost(newScenarioDefPost, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.CONDITION__SCENARIO_DEF_POST, newScenarioDefPost, newScenarioDefPost));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UrncorePackage.CONDITION__START_POINT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetStartPoint((StartPoint)otherEnd, msgs);
			case UrncorePackage.CONDITION__END_POINT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetEndPoint((EndPoint)otherEnd, msgs);
			case UrncorePackage.CONDITION__PLUGIN_BINDING:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetPluginBinding((PluginBinding)otherEnd, msgs);
			case UrncorePackage.CONDITION__NODE_CONNECTION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetNodeConnection((NodeConnection)otherEnd, msgs);
			case UrncorePackage.CONDITION__CONCERN:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetConcern((Concern)otherEnd, msgs);
			case UrncorePackage.CONDITION__SCENARIO_DEF_PRE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetScenarioDefPre((ScenarioDef)otherEnd, msgs);
			case UrncorePackage.CONDITION__SCENARIO_DEF_POST:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetScenarioDefPost((ScenarioDef)otherEnd, msgs);
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
			case UrncorePackage.CONDITION__START_POINT:
				return basicSetStartPoint(null, msgs);
			case UrncorePackage.CONDITION__END_POINT:
				return basicSetEndPoint(null, msgs);
			case UrncorePackage.CONDITION__PLUGIN_BINDING:
				return basicSetPluginBinding(null, msgs);
			case UrncorePackage.CONDITION__NODE_CONNECTION:
				return basicSetNodeConnection(null, msgs);
			case UrncorePackage.CONDITION__CONCERN:
				return basicSetConcern(null, msgs);
			case UrncorePackage.CONDITION__SCENARIO_DEF_PRE:
				return basicSetScenarioDefPre(null, msgs);
			case UrncorePackage.CONDITION__SCENARIO_DEF_POST:
				return basicSetScenarioDefPost(null, msgs);
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
			case UrncorePackage.CONDITION__START_POINT:
				return eInternalContainer().eInverseRemove(this, MapPackage.START_POINT__PRECONDITION, StartPoint.class, msgs);
			case UrncorePackage.CONDITION__END_POINT:
				return eInternalContainer().eInverseRemove(this, MapPackage.END_POINT__POSTCONDITION, EndPoint.class, msgs);
			case UrncorePackage.CONDITION__PLUGIN_BINDING:
				return eInternalContainer().eInverseRemove(this, MapPackage.PLUGIN_BINDING__PRECONDITION, PluginBinding.class, msgs);
			case UrncorePackage.CONDITION__NODE_CONNECTION:
				return eInternalContainer().eInverseRemove(this, MapPackage.NODE_CONNECTION__CONDITION, NodeConnection.class, msgs);
			case UrncorePackage.CONDITION__CONCERN:
				return eInternalContainer().eInverseRemove(this, UrncorePackage.CONCERN__CONDITION, Concern.class, msgs);
			case UrncorePackage.CONDITION__SCENARIO_DEF_PRE:
				return eInternalContainer().eInverseRemove(this, ScenarioPackage.SCENARIO_DEF__PRECONDITIONS, ScenarioDef.class, msgs);
			case UrncorePackage.CONDITION__SCENARIO_DEF_POST:
				return eInternalContainer().eInverseRemove(this, ScenarioPackage.SCENARIO_DEF__POSTCONDITIONS, ScenarioDef.class, msgs);
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
			case UrncorePackage.CONDITION__LABEL:
				return getLabel();
			case UrncorePackage.CONDITION__EXPRESSION:
				return getExpression();
			case UrncorePackage.CONDITION__DESCRIPTION:
				return getDescription();
			case UrncorePackage.CONDITION__START_POINT:
				return getStartPoint();
			case UrncorePackage.CONDITION__END_POINT:
				return getEndPoint();
			case UrncorePackage.CONDITION__PLUGIN_BINDING:
				return getPluginBinding();
			case UrncorePackage.CONDITION__NODE_CONNECTION:
				return getNodeConnection();
			case UrncorePackage.CONDITION__CONCERN:
				return getConcern();
			case UrncorePackage.CONDITION__SCENARIO_DEF_PRE:
				return getScenarioDefPre();
			case UrncorePackage.CONDITION__SCENARIO_DEF_POST:
				return getScenarioDefPost();
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
			case UrncorePackage.CONDITION__LABEL:
				setLabel((String)newValue);
				return;
			case UrncorePackage.CONDITION__EXPRESSION:
				setExpression((String)newValue);
				return;
			case UrncorePackage.CONDITION__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case UrncorePackage.CONDITION__START_POINT:
				setStartPoint((StartPoint)newValue);
				return;
			case UrncorePackage.CONDITION__END_POINT:
				setEndPoint((EndPoint)newValue);
				return;
			case UrncorePackage.CONDITION__PLUGIN_BINDING:
				setPluginBinding((PluginBinding)newValue);
				return;
			case UrncorePackage.CONDITION__NODE_CONNECTION:
				setNodeConnection((NodeConnection)newValue);
				return;
			case UrncorePackage.CONDITION__CONCERN:
				setConcern((Concern)newValue);
				return;
			case UrncorePackage.CONDITION__SCENARIO_DEF_PRE:
				setScenarioDefPre((ScenarioDef)newValue);
				return;
			case UrncorePackage.CONDITION__SCENARIO_DEF_POST:
				setScenarioDefPost((ScenarioDef)newValue);
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
			case UrncorePackage.CONDITION__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case UrncorePackage.CONDITION__EXPRESSION:
				setExpression(EXPRESSION_EDEFAULT);
				return;
			case UrncorePackage.CONDITION__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case UrncorePackage.CONDITION__START_POINT:
				setStartPoint((StartPoint)null);
				return;
			case UrncorePackage.CONDITION__END_POINT:
				setEndPoint((EndPoint)null);
				return;
			case UrncorePackage.CONDITION__PLUGIN_BINDING:
				setPluginBinding((PluginBinding)null);
				return;
			case UrncorePackage.CONDITION__NODE_CONNECTION:
				setNodeConnection((NodeConnection)null);
				return;
			case UrncorePackage.CONDITION__CONCERN:
				setConcern((Concern)null);
				return;
			case UrncorePackage.CONDITION__SCENARIO_DEF_PRE:
				setScenarioDefPre((ScenarioDef)null);
				return;
			case UrncorePackage.CONDITION__SCENARIO_DEF_POST:
				setScenarioDefPost((ScenarioDef)null);
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
			case UrncorePackage.CONDITION__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case UrncorePackage.CONDITION__EXPRESSION:
				return EXPRESSION_EDEFAULT == null ? expression != null : !EXPRESSION_EDEFAULT.equals(expression);
			case UrncorePackage.CONDITION__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case UrncorePackage.CONDITION__START_POINT:
				return getStartPoint() != null;
			case UrncorePackage.CONDITION__END_POINT:
				return getEndPoint() != null;
			case UrncorePackage.CONDITION__PLUGIN_BINDING:
				return getPluginBinding() != null;
			case UrncorePackage.CONDITION__NODE_CONNECTION:
				return getNodeConnection() != null;
			case UrncorePackage.CONDITION__CONCERN:
				return getConcern() != null;
			case UrncorePackage.CONDITION__SCENARIO_DEF_PRE:
				return getScenarioDefPre() != null;
			case UrncorePackage.CONDITION__SCENARIO_DEF_POST:
				return getScenarioDefPost() != null;
		}
		return super.eIsSet(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (label: ");
		result.append(label);
		result.append(", expression: ");
		result.append(expression);
		result.append(", description: ");
		result.append(description);
		result.append(')');
		return result.toString();
	}

} //ConditionImpl
