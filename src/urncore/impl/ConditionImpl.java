/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import ucm.map.EndPoint;
import ucm.map.MapPackage;
import ucm.map.NodeConnection;
import ucm.map.PluginBinding;
import ucm.map.StartPoint;

import ucm.scenario.ScenarioPackage;
import ucm.scenario.Variable;

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
 *   <li>{@link urncore.impl.ConditionImpl#getVariables <em>Variables</em>}</li>
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
     * The cached value of the '{@link #getVariables() <em>Variables</em>}' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getVariables()
     * @generated
     * @ordered
     */
	protected EList variables = null;

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
        return UrncorePackage.eINSTANCE.getCondition();
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
        if (eContainerFeatureID != UrncorePackage.CONDITION__START_POINT) return null;
        return (StartPoint)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setStartPoint(StartPoint newStartPoint) {
        if (newStartPoint != eContainer || (eContainerFeatureID != UrncorePackage.CONDITION__START_POINT && newStartPoint != null)) {
            if (EcoreUtil.isAncestor(this, newStartPoint))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newStartPoint != null)
                msgs = ((InternalEObject)newStartPoint).eInverseAdd(this, MapPackage.START_POINT__PRECONDITION, StartPoint.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newStartPoint, UrncorePackage.CONDITION__START_POINT, msgs);
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
        if (eContainerFeatureID != UrncorePackage.CONDITION__END_POINT) return null;
        return (EndPoint)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setEndPoint(EndPoint newEndPoint) {
        if (newEndPoint != eContainer || (eContainerFeatureID != UrncorePackage.CONDITION__END_POINT && newEndPoint != null)) {
            if (EcoreUtil.isAncestor(this, newEndPoint))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newEndPoint != null)
                msgs = ((InternalEObject)newEndPoint).eInverseAdd(this, MapPackage.END_POINT__POSTCONDITION, EndPoint.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newEndPoint, UrncorePackage.CONDITION__END_POINT, msgs);
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
        if (eContainerFeatureID != UrncorePackage.CONDITION__PLUGIN_BINDING) return null;
        return (PluginBinding)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setPluginBinding(PluginBinding newPluginBinding) {
        if (newPluginBinding != eContainer || (eContainerFeatureID != UrncorePackage.CONDITION__PLUGIN_BINDING && newPluginBinding != null)) {
            if (EcoreUtil.isAncestor(this, newPluginBinding))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newPluginBinding != null)
                msgs = ((InternalEObject)newPluginBinding).eInverseAdd(this, MapPackage.PLUGIN_BINDING__PRECONDITION, PluginBinding.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newPluginBinding, UrncorePackage.CONDITION__PLUGIN_BINDING, msgs);
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
        if (eContainerFeatureID != UrncorePackage.CONDITION__NODE_CONNECTION) return null;
        return (NodeConnection)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setNodeConnection(NodeConnection newNodeConnection) {
        if (newNodeConnection != eContainer || (eContainerFeatureID != UrncorePackage.CONDITION__NODE_CONNECTION && newNodeConnection != null)) {
            if (EcoreUtil.isAncestor(this, newNodeConnection))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newNodeConnection != null)
                msgs = ((InternalEObject)newNodeConnection).eInverseAdd(this, MapPackage.NODE_CONNECTION__CONDITION, NodeConnection.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newNodeConnection, UrncorePackage.CONDITION__NODE_CONNECTION, msgs);
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
	public EList getVariables() {
        if (variables == null) {
            variables = new EObjectWithInverseResolvingEList.ManyInverse(Variable.class, this, UrncorePackage.CONDITION__VARIABLES, ScenarioPackage.VARIABLE__USAGES);
        }
        return variables;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case UrncorePackage.CONDITION__START_POINT:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, UrncorePackage.CONDITION__START_POINT, msgs);
                case UrncorePackage.CONDITION__END_POINT:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, UrncorePackage.CONDITION__END_POINT, msgs);
                case UrncorePackage.CONDITION__PLUGIN_BINDING:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, UrncorePackage.CONDITION__PLUGIN_BINDING, msgs);
                case UrncorePackage.CONDITION__NODE_CONNECTION:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, UrncorePackage.CONDITION__NODE_CONNECTION, msgs);
                case UrncorePackage.CONDITION__VARIABLES:
                    return ((InternalEList)getVariables()).basicAdd(otherEnd, msgs);
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
                case UrncorePackage.CONDITION__START_POINT:
                    return eBasicSetContainer(null, UrncorePackage.CONDITION__START_POINT, msgs);
                case UrncorePackage.CONDITION__END_POINT:
                    return eBasicSetContainer(null, UrncorePackage.CONDITION__END_POINT, msgs);
                case UrncorePackage.CONDITION__PLUGIN_BINDING:
                    return eBasicSetContainer(null, UrncorePackage.CONDITION__PLUGIN_BINDING, msgs);
                case UrncorePackage.CONDITION__NODE_CONNECTION:
                    return eBasicSetContainer(null, UrncorePackage.CONDITION__NODE_CONNECTION, msgs);
                case UrncorePackage.CONDITION__VARIABLES:
                    return ((InternalEList)getVariables()).basicRemove(otherEnd, msgs);
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
                case UrncorePackage.CONDITION__START_POINT:
                    return eContainer.eInverseRemove(this, MapPackage.START_POINT__PRECONDITION, StartPoint.class, msgs);
                case UrncorePackage.CONDITION__END_POINT:
                    return eContainer.eInverseRemove(this, MapPackage.END_POINT__POSTCONDITION, EndPoint.class, msgs);
                case UrncorePackage.CONDITION__PLUGIN_BINDING:
                    return eContainer.eInverseRemove(this, MapPackage.PLUGIN_BINDING__PRECONDITION, PluginBinding.class, msgs);
                case UrncorePackage.CONDITION__NODE_CONNECTION:
                    return eContainer.eInverseRemove(this, MapPackage.NODE_CONNECTION__CONDITION, NodeConnection.class, msgs);
                default:
                    return eDynamicBasicRemoveFromContainer(msgs);
            }
        }
        return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case UrncorePackage.CONDITION__DELTA_X:
                return new Integer(getDeltaX());
            case UrncorePackage.CONDITION__DELTA_Y:
                return new Integer(getDeltaY());
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
            case UrncorePackage.CONDITION__VARIABLES:
                return getVariables();
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
            case UrncorePackage.CONDITION__DELTA_X:
                setDeltaX(((Integer)newValue).intValue());
                return;
            case UrncorePackage.CONDITION__DELTA_Y:
                setDeltaY(((Integer)newValue).intValue());
                return;
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
            case UrncorePackage.CONDITION__VARIABLES:
                getVariables().clear();
                getVariables().addAll((Collection)newValue);
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
            case UrncorePackage.CONDITION__DELTA_X:
                setDeltaX(DELTA_X_EDEFAULT);
                return;
            case UrncorePackage.CONDITION__DELTA_Y:
                setDeltaY(DELTA_Y_EDEFAULT);
                return;
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
            case UrncorePackage.CONDITION__VARIABLES:
                getVariables().clear();
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
            case UrncorePackage.CONDITION__DELTA_X:
                return deltaX != DELTA_X_EDEFAULT;
            case UrncorePackage.CONDITION__DELTA_Y:
                return deltaY != DELTA_Y_EDEFAULT;
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
            case UrncorePackage.CONDITION__VARIABLES:
                return variables != null && !variables.isEmpty();
        }
        return eDynamicIsSet(eFeature);
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
