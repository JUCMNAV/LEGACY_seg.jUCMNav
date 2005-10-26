/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import ucm.map.InBinding;
import ucm.map.MapPackage;
import ucm.map.NodeConnection;
import ucm.map.OutBinding;

import urncore.Condition;
import urncore.SpecificationDiagram;
import urncore.SpecificationNode;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node Connection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.map.impl.NodeConnectionImpl#getSource <em>Source</em>}</li>
 *   <li>{@link ucm.map.impl.NodeConnectionImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link ucm.map.impl.NodeConnectionImpl#getSpecDiagram <em>Spec Diagram</em>}</li>
 *   <li>{@link ucm.map.impl.NodeConnectionImpl#getProbability <em>Probability</em>}</li>
 *   <li>{@link ucm.map.impl.NodeConnectionImpl#getInBindings <em>In Bindings</em>}</li>
 *   <li>{@link ucm.map.impl.NodeConnectionImpl#getOutBindings <em>Out Bindings</em>}</li>
 *   <li>{@link ucm.map.impl.NodeConnectionImpl#getCondition <em>Condition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NodeConnectionImpl extends EObjectImpl implements NodeConnection {
    /**
     * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSource()
     * @generated
     * @ordered
     */
    protected SpecificationNode source = null;

    /**
     * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTarget()
     * @generated
     * @ordered
     */
    protected SpecificationNode target = null;

    /**
     * The default value of the '{@link #getProbability() <em>Probability</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getProbability()
     * @generated
     * @ordered
     */
    protected static final double PROBABILITY_EDEFAULT = 1.0;

    /**
     * The cached value of the '{@link #getProbability() <em>Probability</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getProbability()
     * @generated
     * @ordered
     */
    protected double probability = PROBABILITY_EDEFAULT;

    /**
     * The cached value of the '{@link #getInBindings() <em>In Bindings</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getInBindings()
     * @generated
     * @ordered
     */
    protected EList inBindings = null;

    /**
     * The cached value of the '{@link #getOutBindings() <em>Out Bindings</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOutBindings()
     * @generated
     * @ordered
     */
    protected EList outBindings = null;

    /**
     * The cached value of the '{@link #getCondition() <em>Condition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCondition()
     * @generated
     * @ordered
     */
    protected Condition condition = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected NodeConnectionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return MapPackage.eINSTANCE.getNodeConnection();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SpecificationNode getSource() {
        if (source != null && source.eIsProxy()) {
            SpecificationNode oldSource = source;
            source = (SpecificationNode)eResolveProxy((InternalEObject)source);
            if (source != oldSource) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, MapPackage.NODE_CONNECTION__SOURCE, oldSource, source));
            }
        }
        return source;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SpecificationNode basicGetSource() {
        return source;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSource(SpecificationNode newSource, NotificationChain msgs) {
        SpecificationNode oldSource = source;
        source = newSource;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.NODE_CONNECTION__SOURCE, oldSource, newSource);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSource(SpecificationNode newSource) {
        if (newSource != source) {
            NotificationChain msgs = null;
            if (source != null)
                msgs = ((InternalEObject)source).eInverseRemove(this, UrncorePackage.SPECIFICATION_NODE__SUCC, SpecificationNode.class, msgs);
            if (newSource != null)
                msgs = ((InternalEObject)newSource).eInverseAdd(this, UrncorePackage.SPECIFICATION_NODE__SUCC, SpecificationNode.class, msgs);
            msgs = basicSetSource(newSource, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.NODE_CONNECTION__SOURCE, newSource, newSource));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SpecificationNode getTarget() {
        if (target != null && target.eIsProxy()) {
            SpecificationNode oldTarget = target;
            target = (SpecificationNode)eResolveProxy((InternalEObject)target);
            if (target != oldTarget) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, MapPackage.NODE_CONNECTION__TARGET, oldTarget, target));
            }
        }
        return target;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SpecificationNode basicGetTarget() {
        return target;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTarget(SpecificationNode newTarget, NotificationChain msgs) {
        SpecificationNode oldTarget = target;
        target = newTarget;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.NODE_CONNECTION__TARGET, oldTarget, newTarget);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTarget(SpecificationNode newTarget) {
        if (newTarget != target) {
            NotificationChain msgs = null;
            if (target != null)
                msgs = ((InternalEObject)target).eInverseRemove(this, UrncorePackage.SPECIFICATION_NODE__PRED, SpecificationNode.class, msgs);
            if (newTarget != null)
                msgs = ((InternalEObject)newTarget).eInverseAdd(this, UrncorePackage.SPECIFICATION_NODE__PRED, SpecificationNode.class, msgs);
            msgs = basicSetTarget(newTarget, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.NODE_CONNECTION__TARGET, newTarget, newTarget));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SpecificationDiagram getSpecDiagram() {
        if (eContainerFeatureID != MapPackage.NODE_CONNECTION__SPEC_DIAGRAM) return null;
        return (SpecificationDiagram)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSpecDiagram(SpecificationDiagram newSpecDiagram) {
        if (newSpecDiagram != eContainer || (eContainerFeatureID != MapPackage.NODE_CONNECTION__SPEC_DIAGRAM && newSpecDiagram != null)) {
            if (EcoreUtil.isAncestor(this, newSpecDiagram))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newSpecDiagram != null)
                msgs = ((InternalEObject)newSpecDiagram).eInverseAdd(this, UrncorePackage.SPECIFICATION_DIAGRAM__CONNECTIONS, SpecificationDiagram.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newSpecDiagram, MapPackage.NODE_CONNECTION__SPEC_DIAGRAM, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.NODE_CONNECTION__SPEC_DIAGRAM, newSpecDiagram, newSpecDiagram));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public double getProbability() {
        return probability;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setProbability(double newProbability) {
        double oldProbability = probability;
        probability = newProbability;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.NODE_CONNECTION__PROBABILITY, oldProbability, probability));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getInBindings() {
        if (inBindings == null) {
            inBindings = new EObjectWithInverseResolvingEList(InBinding.class, this, MapPackage.NODE_CONNECTION__IN_BINDINGS, MapPackage.IN_BINDING__STUB_ENTRY);
        }
        return inBindings;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getOutBindings() {
        if (outBindings == null) {
            outBindings = new EObjectWithInverseResolvingEList(OutBinding.class, this, MapPackage.NODE_CONNECTION__OUT_BINDINGS, MapPackage.OUT_BINDING__STUB_EXIT);
        }
        return outBindings;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Condition getCondition() {
        return condition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCondition(Condition newCondition, NotificationChain msgs) {
        Condition oldCondition = condition;
        condition = newCondition;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.NODE_CONNECTION__CONDITION, oldCondition, newCondition);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCondition(Condition newCondition) {
        if (newCondition != condition) {
            NotificationChain msgs = null;
            if (condition != null)
                msgs = ((InternalEObject)condition).eInverseRemove(this, UrncorePackage.CONDITION__NODE_CONNECTION, Condition.class, msgs);
            if (newCondition != null)
                msgs = ((InternalEObject)newCondition).eInverseAdd(this, UrncorePackage.CONDITION__NODE_CONNECTION, Condition.class, msgs);
            msgs = basicSetCondition(newCondition, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.NODE_CONNECTION__CONDITION, newCondition, newCondition));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case MapPackage.NODE_CONNECTION__SOURCE:
                    if (source != null)
                        msgs = ((InternalEObject)source).eInverseRemove(this, UrncorePackage.SPECIFICATION_NODE__SUCC, SpecificationNode.class, msgs);
                    return basicSetSource((SpecificationNode)otherEnd, msgs);
                case MapPackage.NODE_CONNECTION__TARGET:
                    if (target != null)
                        msgs = ((InternalEObject)target).eInverseRemove(this, UrncorePackage.SPECIFICATION_NODE__PRED, SpecificationNode.class, msgs);
                    return basicSetTarget((SpecificationNode)otherEnd, msgs);
                case MapPackage.NODE_CONNECTION__SPEC_DIAGRAM:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, MapPackage.NODE_CONNECTION__SPEC_DIAGRAM, msgs);
                case MapPackage.NODE_CONNECTION__IN_BINDINGS:
                    return ((InternalEList)getInBindings()).basicAdd(otherEnd, msgs);
                case MapPackage.NODE_CONNECTION__OUT_BINDINGS:
                    return ((InternalEList)getOutBindings()).basicAdd(otherEnd, msgs);
                case MapPackage.NODE_CONNECTION__CONDITION:
                    if (condition != null)
                        msgs = ((InternalEObject)condition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MapPackage.NODE_CONNECTION__CONDITION, null, msgs);
                    return basicSetCondition((Condition)otherEnd, msgs);
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
                case MapPackage.NODE_CONNECTION__SOURCE:
                    return basicSetSource(null, msgs);
                case MapPackage.NODE_CONNECTION__TARGET:
                    return basicSetTarget(null, msgs);
                case MapPackage.NODE_CONNECTION__SPEC_DIAGRAM:
                    return eBasicSetContainer(null, MapPackage.NODE_CONNECTION__SPEC_DIAGRAM, msgs);
                case MapPackage.NODE_CONNECTION__IN_BINDINGS:
                    return ((InternalEList)getInBindings()).basicRemove(otherEnd, msgs);
                case MapPackage.NODE_CONNECTION__OUT_BINDINGS:
                    return ((InternalEList)getOutBindings()).basicRemove(otherEnd, msgs);
                case MapPackage.NODE_CONNECTION__CONDITION:
                    return basicSetCondition(null, msgs);
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
                case MapPackage.NODE_CONNECTION__SPEC_DIAGRAM:
                    return ((InternalEObject)eContainer).eInverseRemove(this, UrncorePackage.SPECIFICATION_DIAGRAM__CONNECTIONS, SpecificationDiagram.class, msgs);
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
            case MapPackage.NODE_CONNECTION__SOURCE:
                if (resolve) return getSource();
                return basicGetSource();
            case MapPackage.NODE_CONNECTION__TARGET:
                if (resolve) return getTarget();
                return basicGetTarget();
            case MapPackage.NODE_CONNECTION__SPEC_DIAGRAM:
                return getSpecDiagram();
            case MapPackage.NODE_CONNECTION__PROBABILITY:
                return new Double(getProbability());
            case MapPackage.NODE_CONNECTION__IN_BINDINGS:
                return getInBindings();
            case MapPackage.NODE_CONNECTION__OUT_BINDINGS:
                return getOutBindings();
            case MapPackage.NODE_CONNECTION__CONDITION:
                return getCondition();
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
            case MapPackage.NODE_CONNECTION__SOURCE:
                setSource((SpecificationNode)newValue);
                return;
            case MapPackage.NODE_CONNECTION__TARGET:
                setTarget((SpecificationNode)newValue);
                return;
            case MapPackage.NODE_CONNECTION__SPEC_DIAGRAM:
                setSpecDiagram((SpecificationDiagram)newValue);
                return;
            case MapPackage.NODE_CONNECTION__PROBABILITY:
                setProbability(((Double)newValue).doubleValue());
                return;
            case MapPackage.NODE_CONNECTION__IN_BINDINGS:
                getInBindings().clear();
                getInBindings().addAll((Collection)newValue);
                return;
            case MapPackage.NODE_CONNECTION__OUT_BINDINGS:
                getOutBindings().clear();
                getOutBindings().addAll((Collection)newValue);
                return;
            case MapPackage.NODE_CONNECTION__CONDITION:
                setCondition((Condition)newValue);
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
            case MapPackage.NODE_CONNECTION__SOURCE:
                setSource((SpecificationNode)null);
                return;
            case MapPackage.NODE_CONNECTION__TARGET:
                setTarget((SpecificationNode)null);
                return;
            case MapPackage.NODE_CONNECTION__SPEC_DIAGRAM:
                setSpecDiagram((SpecificationDiagram)null);
                return;
            case MapPackage.NODE_CONNECTION__PROBABILITY:
                setProbability(PROBABILITY_EDEFAULT);
                return;
            case MapPackage.NODE_CONNECTION__IN_BINDINGS:
                getInBindings().clear();
                return;
            case MapPackage.NODE_CONNECTION__OUT_BINDINGS:
                getOutBindings().clear();
                return;
            case MapPackage.NODE_CONNECTION__CONDITION:
                setCondition((Condition)null);
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
            case MapPackage.NODE_CONNECTION__SOURCE:
                return source != null;
            case MapPackage.NODE_CONNECTION__TARGET:
                return target != null;
            case MapPackage.NODE_CONNECTION__SPEC_DIAGRAM:
                return getSpecDiagram() != null;
            case MapPackage.NODE_CONNECTION__PROBABILITY:
                return probability != PROBABILITY_EDEFAULT;
            case MapPackage.NODE_CONNECTION__IN_BINDINGS:
                return inBindings != null && !inBindings.isEmpty();
            case MapPackage.NODE_CONNECTION__OUT_BINDINGS:
                return outBindings != null && !outBindings.isEmpty();
            case MapPackage.NODE_CONNECTION__CONDITION:
                return condition != null;
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
        result.append(" (probability: ");
        result.append(probability);
        result.append(')');
        return result.toString();
    }

} //NodeConnectionImpl
