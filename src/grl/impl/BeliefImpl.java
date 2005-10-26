/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.Belief;
import grl.GrlPackage;
import grl.LinkRef;

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

import urncore.NodeLabel;
import urncore.SpecificationComponentRef;
import urncore.SpecificationConnection;
import urncore.SpecificationDiagram;
import urncore.SpecificationNode;
import urncore.UrncorePackage;

import urncore.impl.GRLmodelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Belief</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.BeliefImpl#getX <em>X</em>}</li>
 *   <li>{@link grl.impl.BeliefImpl#getY <em>Y</em>}</li>
 *   <li>{@link grl.impl.BeliefImpl#getSpecDiagram <em>Spec Diagram</em>}</li>
 *   <li>{@link grl.impl.BeliefImpl#getCompRef <em>Comp Ref</em>}</li>
 *   <li>{@link grl.impl.BeliefImpl#getSucc <em>Succ</em>}</li>
 *   <li>{@link grl.impl.BeliefImpl#getPred <em>Pred</em>}</li>
 *   <li>{@link grl.impl.BeliefImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link grl.impl.BeliefImpl#getConnection <em>Connection</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BeliefImpl extends GRLmodelElementImpl implements Belief {
    /**
     * The default value of the '{@link #getX() <em>X</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getX()
     * @generated
     * @ordered
     */
    protected static final int X_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getX() <em>X</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getX()
     * @generated
     * @ordered
     */
    protected int x = X_EDEFAULT;

    /**
     * The default value of the '{@link #getY() <em>Y</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getY()
     * @generated
     * @ordered
     */
    protected static final int Y_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getY() <em>Y</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getY()
     * @generated
     * @ordered
     */
    protected int y = Y_EDEFAULT;

    /**
     * The cached value of the '{@link #getCompRef() <em>Comp Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCompRef()
     * @generated
     * @ordered
     */
    protected SpecificationComponentRef compRef = null;

    /**
     * The cached value of the '{@link #getSucc() <em>Succ</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSucc()
     * @generated
     * @ordered
     */
    protected EList succ = null;

    /**
     * The cached value of the '{@link #getPred() <em>Pred</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPred()
     * @generated
     * @ordered
     */
    protected EList pred = null;

    /**
     * The cached value of the '{@link #getLabel() <em>Label</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLabel()
     * @generated
     * @ordered
     */
    protected NodeLabel label = null;

    /**
     * The cached value of the '{@link #getConnection() <em>Connection</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getConnection()
     * @generated
     * @ordered
     */
    protected LinkRef connection = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected BeliefImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return GrlPackage.eINSTANCE.getBelief();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getX() {
        return x;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setX(int newX) {
        int oldX = x;
        x = newX;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.BELIEF__X, oldX, x));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getY() {
        return y;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setY(int newY) {
        int oldY = y;
        y = newY;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.BELIEF__Y, oldY, y));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SpecificationDiagram getSpecDiagram() {
        if (eContainerFeatureID != GrlPackage.BELIEF__SPEC_DIAGRAM) return null;
        return (SpecificationDiagram)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSpecDiagram(SpecificationDiagram newSpecDiagram) {
        if (newSpecDiagram != eContainer || (eContainerFeatureID != GrlPackage.BELIEF__SPEC_DIAGRAM && newSpecDiagram != null)) {
            if (EcoreUtil.isAncestor(this, newSpecDiagram))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newSpecDiagram != null)
                msgs = ((InternalEObject)newSpecDiagram).eInverseAdd(this, UrncorePackage.SPECIFICATION_DIAGRAM__NODES, SpecificationDiagram.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newSpecDiagram, GrlPackage.BELIEF__SPEC_DIAGRAM, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.BELIEF__SPEC_DIAGRAM, newSpecDiagram, newSpecDiagram));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SpecificationComponentRef getCompRef() {
        if (compRef != null && compRef.eIsProxy()) {
            SpecificationComponentRef oldCompRef = compRef;
            compRef = (SpecificationComponentRef)eResolveProxy((InternalEObject)compRef);
            if (compRef != oldCompRef) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, GrlPackage.BELIEF__COMP_REF, oldCompRef, compRef));
            }
        }
        return compRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SpecificationComponentRef basicGetCompRef() {
        return compRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCompRef(SpecificationComponentRef newCompRef, NotificationChain msgs) {
        SpecificationComponentRef oldCompRef = compRef;
        compRef = newCompRef;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.BELIEF__COMP_REF, oldCompRef, newCompRef);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCompRef(SpecificationComponentRef newCompRef) {
        if (newCompRef != compRef) {
            NotificationChain msgs = null;
            if (compRef != null)
                msgs = ((InternalEObject)compRef).eInverseRemove(this, UrncorePackage.SPECIFICATION_COMPONENT_REF__NODES, SpecificationComponentRef.class, msgs);
            if (newCompRef != null)
                msgs = ((InternalEObject)newCompRef).eInverseAdd(this, UrncorePackage.SPECIFICATION_COMPONENT_REF__NODES, SpecificationComponentRef.class, msgs);
            msgs = basicSetCompRef(newCompRef, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.BELIEF__COMP_REF, newCompRef, newCompRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getSucc() {
        if (succ == null) {
            succ = new EObjectWithInverseResolvingEList(SpecificationConnection.class, this, GrlPackage.BELIEF__SUCC, UrncorePackage.SPECIFICATION_CONNECTION__SOURCE);
        }
        return succ;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getPred() {
        if (pred == null) {
            pred = new EObjectWithInverseResolvingEList(SpecificationConnection.class, this, GrlPackage.BELIEF__PRED, UrncorePackage.SPECIFICATION_CONNECTION__TARGET);
        }
        return pred;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NodeLabel getLabel() {
        return label;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLabel(NodeLabel newLabel, NotificationChain msgs) {
        NodeLabel oldLabel = label;
        label = newLabel;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.BELIEF__LABEL, oldLabel, newLabel);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLabel(NodeLabel newLabel) {
        if (newLabel != label) {
            NotificationChain msgs = null;
            if (label != null)
                msgs = ((InternalEObject)label).eInverseRemove(this, UrncorePackage.NODE_LABEL__NODE, NodeLabel.class, msgs);
            if (newLabel != null)
                msgs = ((InternalEObject)newLabel).eInverseAdd(this, UrncorePackage.NODE_LABEL__NODE, NodeLabel.class, msgs);
            msgs = basicSetLabel(newLabel, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.BELIEF__LABEL, newLabel, newLabel));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LinkRef getConnection() {
        if (connection != null && connection.eIsProxy()) {
            LinkRef oldConnection = connection;
            connection = (LinkRef)eResolveProxy((InternalEObject)connection);
            if (connection != oldConnection) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, GrlPackage.BELIEF__CONNECTION, oldConnection, connection));
            }
        }
        return connection;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LinkRef basicGetConnection() {
        return connection;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetConnection(LinkRef newConnection, NotificationChain msgs) {
        LinkRef oldConnection = connection;
        connection = newConnection;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.BELIEF__CONNECTION, oldConnection, newConnection);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setConnection(LinkRef newConnection) {
        if (newConnection != connection) {
            NotificationChain msgs = null;
            if (connection != null)
                msgs = ((InternalEObject)connection).eInverseRemove(this, GrlPackage.LINK_REF__BELIEFS, LinkRef.class, msgs);
            if (newConnection != null)
                msgs = ((InternalEObject)newConnection).eInverseAdd(this, GrlPackage.LINK_REF__BELIEFS, LinkRef.class, msgs);
            msgs = basicSetConnection(newConnection, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.BELIEF__CONNECTION, newConnection, newConnection));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case GrlPackage.BELIEF__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicAdd(otherEnd, msgs);
                case GrlPackage.BELIEF__SPEC_DIAGRAM:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, GrlPackage.BELIEF__SPEC_DIAGRAM, msgs);
                case GrlPackage.BELIEF__COMP_REF:
                    if (compRef != null)
                        msgs = ((InternalEObject)compRef).eInverseRemove(this, UrncorePackage.SPECIFICATION_COMPONENT_REF__NODES, SpecificationComponentRef.class, msgs);
                    return basicSetCompRef((SpecificationComponentRef)otherEnd, msgs);
                case GrlPackage.BELIEF__SUCC:
                    return ((InternalEList)getSucc()).basicAdd(otherEnd, msgs);
                case GrlPackage.BELIEF__PRED:
                    return ((InternalEList)getPred()).basicAdd(otherEnd, msgs);
                case GrlPackage.BELIEF__LABEL:
                    if (label != null)
                        msgs = ((InternalEObject)label).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - GrlPackage.BELIEF__LABEL, null, msgs);
                    return basicSetLabel((NodeLabel)otherEnd, msgs);
                case GrlPackage.BELIEF__CONNECTION:
                    if (connection != null)
                        msgs = ((InternalEObject)connection).eInverseRemove(this, GrlPackage.LINK_REF__BELIEFS, LinkRef.class, msgs);
                    return basicSetConnection((LinkRef)otherEnd, msgs);
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
                case GrlPackage.BELIEF__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
                case GrlPackage.BELIEF__SPEC_DIAGRAM:
                    return eBasicSetContainer(null, GrlPackage.BELIEF__SPEC_DIAGRAM, msgs);
                case GrlPackage.BELIEF__COMP_REF:
                    return basicSetCompRef(null, msgs);
                case GrlPackage.BELIEF__SUCC:
                    return ((InternalEList)getSucc()).basicRemove(otherEnd, msgs);
                case GrlPackage.BELIEF__PRED:
                    return ((InternalEList)getPred()).basicRemove(otherEnd, msgs);
                case GrlPackage.BELIEF__LABEL:
                    return basicSetLabel(null, msgs);
                case GrlPackage.BELIEF__CONNECTION:
                    return basicSetConnection(null, msgs);
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
                case GrlPackage.BELIEF__SPEC_DIAGRAM:
                    return ((InternalEObject)eContainer).eInverseRemove(this, UrncorePackage.SPECIFICATION_DIAGRAM__NODES, SpecificationDiagram.class, msgs);
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
            case GrlPackage.BELIEF__ID:
                return getId();
            case GrlPackage.BELIEF__NAME:
                return getName();
            case GrlPackage.BELIEF__DESCRIPTION:
                return getDescription();
            case GrlPackage.BELIEF__URN_LINKS:
                return getUrnLinks();
            case GrlPackage.BELIEF__X:
                return new Integer(getX());
            case GrlPackage.BELIEF__Y:
                return new Integer(getY());
            case GrlPackage.BELIEF__SPEC_DIAGRAM:
                return getSpecDiagram();
            case GrlPackage.BELIEF__COMP_REF:
                if (resolve) return getCompRef();
                return basicGetCompRef();
            case GrlPackage.BELIEF__SUCC:
                return getSucc();
            case GrlPackage.BELIEF__PRED:
                return getPred();
            case GrlPackage.BELIEF__LABEL:
                return getLabel();
            case GrlPackage.BELIEF__CONNECTION:
                if (resolve) return getConnection();
                return basicGetConnection();
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
            case GrlPackage.BELIEF__ID:
                setId((String)newValue);
                return;
            case GrlPackage.BELIEF__NAME:
                setName((String)newValue);
                return;
            case GrlPackage.BELIEF__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case GrlPackage.BELIEF__URN_LINKS:
                getUrnLinks().clear();
                getUrnLinks().addAll((Collection)newValue);
                return;
            case GrlPackage.BELIEF__X:
                setX(((Integer)newValue).intValue());
                return;
            case GrlPackage.BELIEF__Y:
                setY(((Integer)newValue).intValue());
                return;
            case GrlPackage.BELIEF__SPEC_DIAGRAM:
                setSpecDiagram((SpecificationDiagram)newValue);
                return;
            case GrlPackage.BELIEF__COMP_REF:
                setCompRef((SpecificationComponentRef)newValue);
                return;
            case GrlPackage.BELIEF__SUCC:
                getSucc().clear();
                getSucc().addAll((Collection)newValue);
                return;
            case GrlPackage.BELIEF__PRED:
                getPred().clear();
                getPred().addAll((Collection)newValue);
                return;
            case GrlPackage.BELIEF__LABEL:
                setLabel((NodeLabel)newValue);
                return;
            case GrlPackage.BELIEF__CONNECTION:
                setConnection((LinkRef)newValue);
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
            case GrlPackage.BELIEF__ID:
                setId(ID_EDEFAULT);
                return;
            case GrlPackage.BELIEF__NAME:
                setName(NAME_EDEFAULT);
                return;
            case GrlPackage.BELIEF__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case GrlPackage.BELIEF__URN_LINKS:
                getUrnLinks().clear();
                return;
            case GrlPackage.BELIEF__X:
                setX(X_EDEFAULT);
                return;
            case GrlPackage.BELIEF__Y:
                setY(Y_EDEFAULT);
                return;
            case GrlPackage.BELIEF__SPEC_DIAGRAM:
                setSpecDiagram((SpecificationDiagram)null);
                return;
            case GrlPackage.BELIEF__COMP_REF:
                setCompRef((SpecificationComponentRef)null);
                return;
            case GrlPackage.BELIEF__SUCC:
                getSucc().clear();
                return;
            case GrlPackage.BELIEF__PRED:
                getPred().clear();
                return;
            case GrlPackage.BELIEF__LABEL:
                setLabel((NodeLabel)null);
                return;
            case GrlPackage.BELIEF__CONNECTION:
                setConnection((LinkRef)null);
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
            case GrlPackage.BELIEF__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case GrlPackage.BELIEF__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case GrlPackage.BELIEF__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case GrlPackage.BELIEF__URN_LINKS:
                return urnLinks != null && !urnLinks.isEmpty();
            case GrlPackage.BELIEF__X:
                return x != X_EDEFAULT;
            case GrlPackage.BELIEF__Y:
                return y != Y_EDEFAULT;
            case GrlPackage.BELIEF__SPEC_DIAGRAM:
                return getSpecDiagram() != null;
            case GrlPackage.BELIEF__COMP_REF:
                return compRef != null;
            case GrlPackage.BELIEF__SUCC:
                return succ != null && !succ.isEmpty();
            case GrlPackage.BELIEF__PRED:
                return pred != null && !pred.isEmpty();
            case GrlPackage.BELIEF__LABEL:
                return label != null;
            case GrlPackage.BELIEF__CONNECTION:
                return connection != null;
        }
        return eDynamicIsSet(eFeature);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
        if (baseClass == SpecificationNode.class) {
            switch (derivedFeatureID) {
                case GrlPackage.BELIEF__X: return UrncorePackage.SPECIFICATION_NODE__X;
                case GrlPackage.BELIEF__Y: return UrncorePackage.SPECIFICATION_NODE__Y;
                case GrlPackage.BELIEF__SPEC_DIAGRAM: return UrncorePackage.SPECIFICATION_NODE__SPEC_DIAGRAM;
                case GrlPackage.BELIEF__COMP_REF: return UrncorePackage.SPECIFICATION_NODE__COMP_REF;
                case GrlPackage.BELIEF__SUCC: return UrncorePackage.SPECIFICATION_NODE__SUCC;
                case GrlPackage.BELIEF__PRED: return UrncorePackage.SPECIFICATION_NODE__PRED;
                case GrlPackage.BELIEF__LABEL: return UrncorePackage.SPECIFICATION_NODE__LABEL;
                default: return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass) {
        if (baseClass == SpecificationNode.class) {
            switch (baseFeatureID) {
                case UrncorePackage.SPECIFICATION_NODE__X: return GrlPackage.BELIEF__X;
                case UrncorePackage.SPECIFICATION_NODE__Y: return GrlPackage.BELIEF__Y;
                case UrncorePackage.SPECIFICATION_NODE__SPEC_DIAGRAM: return GrlPackage.BELIEF__SPEC_DIAGRAM;
                case UrncorePackage.SPECIFICATION_NODE__COMP_REF: return GrlPackage.BELIEF__COMP_REF;
                case UrncorePackage.SPECIFICATION_NODE__SUCC: return GrlPackage.BELIEF__SUCC;
                case UrncorePackage.SPECIFICATION_NODE__PRED: return GrlPackage.BELIEF__PRED;
                case UrncorePackage.SPECIFICATION_NODE__LABEL: return GrlPackage.BELIEF__LABEL;
                default: return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (x: ");
        result.append(x);
        result.append(", y: ");
        result.append(y);
        result.append(')');
        return result.toString();
    }

} //BeliefImpl
