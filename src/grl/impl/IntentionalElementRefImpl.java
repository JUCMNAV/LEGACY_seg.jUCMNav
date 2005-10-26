/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.GrlPackage;
import grl.IntentionalElement;
import grl.IntentionalElementRef;

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
 * An implementation of the model object '<em><b>Intentional Element Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.IntentionalElementRefImpl#getX <em>X</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementRefImpl#getY <em>Y</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementRefImpl#getSpecDiagram <em>Spec Diagram</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementRefImpl#getCompRef <em>Comp Ref</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementRefImpl#getSucc <em>Succ</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementRefImpl#getPred <em>Pred</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementRefImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementRefImpl#getDef <em>Def</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IntentionalElementRefImpl extends GRLmodelElementImpl implements IntentionalElementRef {
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
     * The cached value of the '{@link #getDef() <em>Def</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDef()
     * @generated
     * @ordered
     */
    protected IntentionalElement def = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected IntentionalElementRefImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return GrlPackage.eINSTANCE.getIntentionalElementRef();
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
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT_REF__X, oldX, x));
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
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT_REF__Y, oldY, y));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SpecificationDiagram getSpecDiagram() {
        if (eContainerFeatureID != GrlPackage.INTENTIONAL_ELEMENT_REF__SPEC_DIAGRAM) return null;
        return (SpecificationDiagram)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSpecDiagram(SpecificationDiagram newSpecDiagram) {
        if (newSpecDiagram != eContainer || (eContainerFeatureID != GrlPackage.INTENTIONAL_ELEMENT_REF__SPEC_DIAGRAM && newSpecDiagram != null)) {
            if (EcoreUtil.isAncestor(this, newSpecDiagram))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newSpecDiagram != null)
                msgs = ((InternalEObject)newSpecDiagram).eInverseAdd(this, UrncorePackage.SPECIFICATION_DIAGRAM__NODES, SpecificationDiagram.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newSpecDiagram, GrlPackage.INTENTIONAL_ELEMENT_REF__SPEC_DIAGRAM, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT_REF__SPEC_DIAGRAM, newSpecDiagram, newSpecDiagram));
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, GrlPackage.INTENTIONAL_ELEMENT_REF__COMP_REF, oldCompRef, compRef));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT_REF__COMP_REF, oldCompRef, newCompRef);
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
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT_REF__COMP_REF, newCompRef, newCompRef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getSucc() {
        if (succ == null) {
            succ = new EObjectWithInverseResolvingEList(SpecificationConnection.class, this, GrlPackage.INTENTIONAL_ELEMENT_REF__SUCC, UrncorePackage.SPECIFICATION_CONNECTION__SOURCE);
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
            pred = new EObjectWithInverseResolvingEList(SpecificationConnection.class, this, GrlPackage.INTENTIONAL_ELEMENT_REF__PRED, UrncorePackage.SPECIFICATION_CONNECTION__TARGET);
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT_REF__LABEL, oldLabel, newLabel);
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
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT_REF__LABEL, newLabel, newLabel));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntentionalElement getDef() {
        if (def != null && def.eIsProxy()) {
            IntentionalElement oldDef = def;
            def = (IntentionalElement)eResolveProxy((InternalEObject)def);
            if (def != oldDef) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, GrlPackage.INTENTIONAL_ELEMENT_REF__DEF, oldDef, def));
            }
        }
        return def;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntentionalElement basicGetDef() {
        return def;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDef(IntentionalElement newDef, NotificationChain msgs) {
        IntentionalElement oldDef = def;
        def = newDef;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT_REF__DEF, oldDef, newDef);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDef(IntentionalElement newDef) {
        if (newDef != def) {
            NotificationChain msgs = null;
            if (def != null)
                msgs = ((InternalEObject)def).eInverseRemove(this, GrlPackage.INTENTIONAL_ELEMENT__REFS, IntentionalElement.class, msgs);
            if (newDef != null)
                msgs = ((InternalEObject)newDef).eInverseAdd(this, GrlPackage.INTENTIONAL_ELEMENT__REFS, IntentionalElement.class, msgs);
            msgs = basicSetDef(newDef, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT_REF__DEF, newDef, newDef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case GrlPackage.INTENTIONAL_ELEMENT_REF__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicAdd(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT_REF__SPEC_DIAGRAM:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, GrlPackage.INTENTIONAL_ELEMENT_REF__SPEC_DIAGRAM, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT_REF__COMP_REF:
                    if (compRef != null)
                        msgs = ((InternalEObject)compRef).eInverseRemove(this, UrncorePackage.SPECIFICATION_COMPONENT_REF__NODES, SpecificationComponentRef.class, msgs);
                    return basicSetCompRef((SpecificationComponentRef)otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT_REF__SUCC:
                    return ((InternalEList)getSucc()).basicAdd(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT_REF__PRED:
                    return ((InternalEList)getPred()).basicAdd(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT_REF__LABEL:
                    if (label != null)
                        msgs = ((InternalEObject)label).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - GrlPackage.INTENTIONAL_ELEMENT_REF__LABEL, null, msgs);
                    return basicSetLabel((NodeLabel)otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT_REF__DEF:
                    if (def != null)
                        msgs = ((InternalEObject)def).eInverseRemove(this, GrlPackage.INTENTIONAL_ELEMENT__REFS, IntentionalElement.class, msgs);
                    return basicSetDef((IntentionalElement)otherEnd, msgs);
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
                case GrlPackage.INTENTIONAL_ELEMENT_REF__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT_REF__SPEC_DIAGRAM:
                    return eBasicSetContainer(null, GrlPackage.INTENTIONAL_ELEMENT_REF__SPEC_DIAGRAM, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT_REF__COMP_REF:
                    return basicSetCompRef(null, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT_REF__SUCC:
                    return ((InternalEList)getSucc()).basicRemove(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT_REF__PRED:
                    return ((InternalEList)getPred()).basicRemove(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT_REF__LABEL:
                    return basicSetLabel(null, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT_REF__DEF:
                    return basicSetDef(null, msgs);
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
                case GrlPackage.INTENTIONAL_ELEMENT_REF__SPEC_DIAGRAM:
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
            case GrlPackage.INTENTIONAL_ELEMENT_REF__ID:
                return getId();
            case GrlPackage.INTENTIONAL_ELEMENT_REF__NAME:
                return getName();
            case GrlPackage.INTENTIONAL_ELEMENT_REF__DESCRIPTION:
                return getDescription();
            case GrlPackage.INTENTIONAL_ELEMENT_REF__URN_LINKS:
                return getUrnLinks();
            case GrlPackage.INTENTIONAL_ELEMENT_REF__X:
                return new Integer(getX());
            case GrlPackage.INTENTIONAL_ELEMENT_REF__Y:
                return new Integer(getY());
            case GrlPackage.INTENTIONAL_ELEMENT_REF__SPEC_DIAGRAM:
                return getSpecDiagram();
            case GrlPackage.INTENTIONAL_ELEMENT_REF__COMP_REF:
                if (resolve) return getCompRef();
                return basicGetCompRef();
            case GrlPackage.INTENTIONAL_ELEMENT_REF__SUCC:
                return getSucc();
            case GrlPackage.INTENTIONAL_ELEMENT_REF__PRED:
                return getPred();
            case GrlPackage.INTENTIONAL_ELEMENT_REF__LABEL:
                return getLabel();
            case GrlPackage.INTENTIONAL_ELEMENT_REF__DEF:
                if (resolve) return getDef();
                return basicGetDef();
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
            case GrlPackage.INTENTIONAL_ELEMENT_REF__ID:
                setId((String)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__NAME:
                setName((String)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__URN_LINKS:
                getUrnLinks().clear();
                getUrnLinks().addAll((Collection)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__X:
                setX(((Integer)newValue).intValue());
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__Y:
                setY(((Integer)newValue).intValue());
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__SPEC_DIAGRAM:
                setSpecDiagram((SpecificationDiagram)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__COMP_REF:
                setCompRef((SpecificationComponentRef)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__SUCC:
                getSucc().clear();
                getSucc().addAll((Collection)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__PRED:
                getPred().clear();
                getPred().addAll((Collection)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__LABEL:
                setLabel((NodeLabel)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__DEF:
                setDef((IntentionalElement)newValue);
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
            case GrlPackage.INTENTIONAL_ELEMENT_REF__ID:
                setId(ID_EDEFAULT);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__NAME:
                setName(NAME_EDEFAULT);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__URN_LINKS:
                getUrnLinks().clear();
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__X:
                setX(X_EDEFAULT);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__Y:
                setY(Y_EDEFAULT);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__SPEC_DIAGRAM:
                setSpecDiagram((SpecificationDiagram)null);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__COMP_REF:
                setCompRef((SpecificationComponentRef)null);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__SUCC:
                getSucc().clear();
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__PRED:
                getPred().clear();
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__LABEL:
                setLabel((NodeLabel)null);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__DEF:
                setDef((IntentionalElement)null);
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
            case GrlPackage.INTENTIONAL_ELEMENT_REF__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case GrlPackage.INTENTIONAL_ELEMENT_REF__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case GrlPackage.INTENTIONAL_ELEMENT_REF__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case GrlPackage.INTENTIONAL_ELEMENT_REF__URN_LINKS:
                return urnLinks != null && !urnLinks.isEmpty();
            case GrlPackage.INTENTIONAL_ELEMENT_REF__X:
                return x != X_EDEFAULT;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__Y:
                return y != Y_EDEFAULT;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__SPEC_DIAGRAM:
                return getSpecDiagram() != null;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__COMP_REF:
                return compRef != null;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__SUCC:
                return succ != null && !succ.isEmpty();
            case GrlPackage.INTENTIONAL_ELEMENT_REF__PRED:
                return pred != null && !pred.isEmpty();
            case GrlPackage.INTENTIONAL_ELEMENT_REF__LABEL:
                return label != null;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__DEF:
                return def != null;
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
                case GrlPackage.INTENTIONAL_ELEMENT_REF__X: return UrncorePackage.SPECIFICATION_NODE__X;
                case GrlPackage.INTENTIONAL_ELEMENT_REF__Y: return UrncorePackage.SPECIFICATION_NODE__Y;
                case GrlPackage.INTENTIONAL_ELEMENT_REF__SPEC_DIAGRAM: return UrncorePackage.SPECIFICATION_NODE__SPEC_DIAGRAM;
                case GrlPackage.INTENTIONAL_ELEMENT_REF__COMP_REF: return UrncorePackage.SPECIFICATION_NODE__COMP_REF;
                case GrlPackage.INTENTIONAL_ELEMENT_REF__SUCC: return UrncorePackage.SPECIFICATION_NODE__SUCC;
                case GrlPackage.INTENTIONAL_ELEMENT_REF__PRED: return UrncorePackage.SPECIFICATION_NODE__PRED;
                case GrlPackage.INTENTIONAL_ELEMENT_REF__LABEL: return UrncorePackage.SPECIFICATION_NODE__LABEL;
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
                case UrncorePackage.SPECIFICATION_NODE__X: return GrlPackage.INTENTIONAL_ELEMENT_REF__X;
                case UrncorePackage.SPECIFICATION_NODE__Y: return GrlPackage.INTENTIONAL_ELEMENT_REF__Y;
                case UrncorePackage.SPECIFICATION_NODE__SPEC_DIAGRAM: return GrlPackage.INTENTIONAL_ELEMENT_REF__SPEC_DIAGRAM;
                case UrncorePackage.SPECIFICATION_NODE__COMP_REF: return GrlPackage.INTENTIONAL_ELEMENT_REF__COMP_REF;
                case UrncorePackage.SPECIFICATION_NODE__SUCC: return GrlPackage.INTENTIONAL_ELEMENT_REF__SUCC;
                case UrncorePackage.SPECIFICATION_NODE__PRED: return GrlPackage.INTENTIONAL_ELEMENT_REF__PRED;
                case UrncorePackage.SPECIFICATION_NODE__LABEL: return GrlPackage.INTENTIONAL_ELEMENT_REF__LABEL;
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

} //IntentionalElementRefImpl
