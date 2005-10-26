/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.ActorRef;
import grl.GrlPackage;

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

import urncore.ComponentLabel;
import urncore.SpecificationComponent;
import urncore.SpecificationComponentRef;
import urncore.SpecificationDiagram;
import urncore.SpecificationNode;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Actor Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.ActorRefImpl#getX <em>X</em>}</li>
 *   <li>{@link grl.impl.ActorRefImpl#getY <em>Y</em>}</li>
 *   <li>{@link grl.impl.ActorRefImpl#getWidth <em>Width</em>}</li>
 *   <li>{@link grl.impl.ActorRefImpl#getHeight <em>Height</em>}</li>
 *   <li>{@link grl.impl.ActorRefImpl#isFixed <em>Fixed</em>}</li>
 *   <li>{@link grl.impl.ActorRefImpl#getSpecDiagram <em>Spec Diagram</em>}</li>
 *   <li>{@link grl.impl.ActorRefImpl#getCompDef <em>Comp Def</em>}</li>
 *   <li>{@link grl.impl.ActorRefImpl#getNodes <em>Nodes</em>}</li>
 *   <li>{@link grl.impl.ActorRefImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link grl.impl.ActorRefImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link grl.impl.ActorRefImpl#getChildren <em>Children</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActorRefImpl extends EObjectImpl implements ActorRef {
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
     * The default value of the '{@link #getWidth() <em>Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWidth()
     * @generated
     * @ordered
     */
    protected static final int WIDTH_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getWidth() <em>Width</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWidth()
     * @generated
     * @ordered
     */
    protected int width = WIDTH_EDEFAULT;

    /**
     * The default value of the '{@link #getHeight() <em>Height</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHeight()
     * @generated
     * @ordered
     */
    protected static final int HEIGHT_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getHeight() <em>Height</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHeight()
     * @generated
     * @ordered
     */
    protected int height = HEIGHT_EDEFAULT;

    /**
     * The default value of the '{@link #isFixed() <em>Fixed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isFixed()
     * @generated
     * @ordered
     */
    protected static final boolean FIXED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isFixed() <em>Fixed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isFixed()
     * @generated
     * @ordered
     */
    protected boolean fixed = FIXED_EDEFAULT;

    /**
     * The cached value of the '{@link #getCompDef() <em>Comp Def</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCompDef()
     * @generated
     * @ordered
     */
    protected SpecificationComponent compDef = null;

    /**
     * The cached value of the '{@link #getNodes() <em>Nodes</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getNodes()
     * @generated
     * @ordered
     */
    protected EList nodes = null;

    /**
     * The cached value of the '{@link #getLabel() <em>Label</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLabel()
     * @generated
     * @ordered
     */
    protected ComponentLabel label = null;

    /**
     * The cached value of the '{@link #getParent() <em>Parent</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getParent()
     * @generated
     * @ordered
     */
    protected SpecificationComponentRef parent = null;

    /**
     * The cached value of the '{@link #getChildren() <em>Children</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getChildren()
     * @generated
     * @ordered
     */
    protected EList children = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ActorRefImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return GrlPackage.eINSTANCE.getActorRef();
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
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.ACTOR_REF__X, oldX, x));
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
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.ACTOR_REF__Y, oldY, y));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getWidth() {
        return width;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setWidth(int newWidth) {
        int oldWidth = width;
        width = newWidth;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.ACTOR_REF__WIDTH, oldWidth, width));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getHeight() {
        return height;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setHeight(int newHeight) {
        int oldHeight = height;
        height = newHeight;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.ACTOR_REF__HEIGHT, oldHeight, height));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isFixed() {
        return fixed;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFixed(boolean newFixed) {
        boolean oldFixed = fixed;
        fixed = newFixed;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.ACTOR_REF__FIXED, oldFixed, fixed));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SpecificationDiagram getSpecDiagram() {
        if (eContainerFeatureID != GrlPackage.ACTOR_REF__SPEC_DIAGRAM) return null;
        return (SpecificationDiagram)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSpecDiagram(SpecificationDiagram newSpecDiagram) {
        if (newSpecDiagram != eContainer || (eContainerFeatureID != GrlPackage.ACTOR_REF__SPEC_DIAGRAM && newSpecDiagram != null)) {
            if (EcoreUtil.isAncestor(this, newSpecDiagram))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newSpecDiagram != null)
                msgs = ((InternalEObject)newSpecDiagram).eInverseAdd(this, UrncorePackage.SPECIFICATION_DIAGRAM__COMP_REFS, SpecificationDiagram.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newSpecDiagram, GrlPackage.ACTOR_REF__SPEC_DIAGRAM, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.ACTOR_REF__SPEC_DIAGRAM, newSpecDiagram, newSpecDiagram));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SpecificationComponent getCompDef() {
        if (compDef != null && compDef.eIsProxy()) {
            SpecificationComponent oldCompDef = compDef;
            compDef = (SpecificationComponent)eResolveProxy((InternalEObject)compDef);
            if (compDef != oldCompDef) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, GrlPackage.ACTOR_REF__COMP_DEF, oldCompDef, compDef));
            }
        }
        return compDef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SpecificationComponent basicGetCompDef() {
        return compDef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCompDef(SpecificationComponent newCompDef, NotificationChain msgs) {
        SpecificationComponent oldCompDef = compDef;
        compDef = newCompDef;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.ACTOR_REF__COMP_DEF, oldCompDef, newCompDef);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCompDef(SpecificationComponent newCompDef) {
        if (newCompDef != compDef) {
            NotificationChain msgs = null;
            if (compDef != null)
                msgs = ((InternalEObject)compDef).eInverseRemove(this, UrncorePackage.SPECIFICATION_COMPONENT__COMP_REFS, SpecificationComponent.class, msgs);
            if (newCompDef != null)
                msgs = ((InternalEObject)newCompDef).eInverseAdd(this, UrncorePackage.SPECIFICATION_COMPONENT__COMP_REFS, SpecificationComponent.class, msgs);
            msgs = basicSetCompDef(newCompDef, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.ACTOR_REF__COMP_DEF, newCompDef, newCompDef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getNodes() {
        if (nodes == null) {
            nodes = new EObjectWithInverseResolvingEList(SpecificationNode.class, this, GrlPackage.ACTOR_REF__NODES, UrncorePackage.SPECIFICATION_NODE__COMP_REF);
        }
        return nodes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ComponentLabel getLabel() {
        return label;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLabel(ComponentLabel newLabel, NotificationChain msgs) {
        ComponentLabel oldLabel = label;
        label = newLabel;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.ACTOR_REF__LABEL, oldLabel, newLabel);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLabel(ComponentLabel newLabel) {
        if (newLabel != label) {
            NotificationChain msgs = null;
            if (label != null)
                msgs = ((InternalEObject)label).eInverseRemove(this, UrncorePackage.COMPONENT_LABEL__COMP_REF, ComponentLabel.class, msgs);
            if (newLabel != null)
                msgs = ((InternalEObject)newLabel).eInverseAdd(this, UrncorePackage.COMPONENT_LABEL__COMP_REF, ComponentLabel.class, msgs);
            msgs = basicSetLabel(newLabel, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.ACTOR_REF__LABEL, newLabel, newLabel));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SpecificationComponentRef getParent() {
        if (parent != null && parent.eIsProxy()) {
            SpecificationComponentRef oldParent = parent;
            parent = (SpecificationComponentRef)eResolveProxy((InternalEObject)parent);
            if (parent != oldParent) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, GrlPackage.ACTOR_REF__PARENT, oldParent, parent));
            }
        }
        return parent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SpecificationComponentRef basicGetParent() {
        return parent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetParent(SpecificationComponentRef newParent, NotificationChain msgs) {
        SpecificationComponentRef oldParent = parent;
        parent = newParent;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.ACTOR_REF__PARENT, oldParent, newParent);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParent(SpecificationComponentRef newParent) {
        if (newParent != parent) {
            NotificationChain msgs = null;
            if (parent != null)
                msgs = ((InternalEObject)parent).eInverseRemove(this, UrncorePackage.SPECIFICATION_COMPONENT_REF__CHILDREN, SpecificationComponentRef.class, msgs);
            if (newParent != null)
                msgs = ((InternalEObject)newParent).eInverseAdd(this, UrncorePackage.SPECIFICATION_COMPONENT_REF__CHILDREN, SpecificationComponentRef.class, msgs);
            msgs = basicSetParent(newParent, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.ACTOR_REF__PARENT, newParent, newParent));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getChildren() {
        if (children == null) {
            children = new EObjectWithInverseResolvingEList(SpecificationComponentRef.class, this, GrlPackage.ACTOR_REF__CHILDREN, UrncorePackage.SPECIFICATION_COMPONENT_REF__PARENT);
        }
        return children;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case GrlPackage.ACTOR_REF__SPEC_DIAGRAM:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, GrlPackage.ACTOR_REF__SPEC_DIAGRAM, msgs);
                case GrlPackage.ACTOR_REF__COMP_DEF:
                    if (compDef != null)
                        msgs = ((InternalEObject)compDef).eInverseRemove(this, UrncorePackage.SPECIFICATION_COMPONENT__COMP_REFS, SpecificationComponent.class, msgs);
                    return basicSetCompDef((SpecificationComponent)otherEnd, msgs);
                case GrlPackage.ACTOR_REF__NODES:
                    return ((InternalEList)getNodes()).basicAdd(otherEnd, msgs);
                case GrlPackage.ACTOR_REF__LABEL:
                    if (label != null)
                        msgs = ((InternalEObject)label).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - GrlPackage.ACTOR_REF__LABEL, null, msgs);
                    return basicSetLabel((ComponentLabel)otherEnd, msgs);
                case GrlPackage.ACTOR_REF__PARENT:
                    if (parent != null)
                        msgs = ((InternalEObject)parent).eInverseRemove(this, UrncorePackage.SPECIFICATION_COMPONENT_REF__CHILDREN, SpecificationComponentRef.class, msgs);
                    return basicSetParent((SpecificationComponentRef)otherEnd, msgs);
                case GrlPackage.ACTOR_REF__CHILDREN:
                    return ((InternalEList)getChildren()).basicAdd(otherEnd, msgs);
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
                case GrlPackage.ACTOR_REF__SPEC_DIAGRAM:
                    return eBasicSetContainer(null, GrlPackage.ACTOR_REF__SPEC_DIAGRAM, msgs);
                case GrlPackage.ACTOR_REF__COMP_DEF:
                    return basicSetCompDef(null, msgs);
                case GrlPackage.ACTOR_REF__NODES:
                    return ((InternalEList)getNodes()).basicRemove(otherEnd, msgs);
                case GrlPackage.ACTOR_REF__LABEL:
                    return basicSetLabel(null, msgs);
                case GrlPackage.ACTOR_REF__PARENT:
                    return basicSetParent(null, msgs);
                case GrlPackage.ACTOR_REF__CHILDREN:
                    return ((InternalEList)getChildren()).basicRemove(otherEnd, msgs);
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
                case GrlPackage.ACTOR_REF__SPEC_DIAGRAM:
                    return ((InternalEObject)eContainer).eInverseRemove(this, UrncorePackage.SPECIFICATION_DIAGRAM__COMP_REFS, SpecificationDiagram.class, msgs);
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
            case GrlPackage.ACTOR_REF__X:
                return new Integer(getX());
            case GrlPackage.ACTOR_REF__Y:
                return new Integer(getY());
            case GrlPackage.ACTOR_REF__WIDTH:
                return new Integer(getWidth());
            case GrlPackage.ACTOR_REF__HEIGHT:
                return new Integer(getHeight());
            case GrlPackage.ACTOR_REF__FIXED:
                return isFixed() ? Boolean.TRUE : Boolean.FALSE;
            case GrlPackage.ACTOR_REF__SPEC_DIAGRAM:
                return getSpecDiagram();
            case GrlPackage.ACTOR_REF__COMP_DEF:
                if (resolve) return getCompDef();
                return basicGetCompDef();
            case GrlPackage.ACTOR_REF__NODES:
                return getNodes();
            case GrlPackage.ACTOR_REF__LABEL:
                return getLabel();
            case GrlPackage.ACTOR_REF__PARENT:
                if (resolve) return getParent();
                return basicGetParent();
            case GrlPackage.ACTOR_REF__CHILDREN:
                return getChildren();
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
            case GrlPackage.ACTOR_REF__X:
                setX(((Integer)newValue).intValue());
                return;
            case GrlPackage.ACTOR_REF__Y:
                setY(((Integer)newValue).intValue());
                return;
            case GrlPackage.ACTOR_REF__WIDTH:
                setWidth(((Integer)newValue).intValue());
                return;
            case GrlPackage.ACTOR_REF__HEIGHT:
                setHeight(((Integer)newValue).intValue());
                return;
            case GrlPackage.ACTOR_REF__FIXED:
                setFixed(((Boolean)newValue).booleanValue());
                return;
            case GrlPackage.ACTOR_REF__SPEC_DIAGRAM:
                setSpecDiagram((SpecificationDiagram)newValue);
                return;
            case GrlPackage.ACTOR_REF__COMP_DEF:
                setCompDef((SpecificationComponent)newValue);
                return;
            case GrlPackage.ACTOR_REF__NODES:
                getNodes().clear();
                getNodes().addAll((Collection)newValue);
                return;
            case GrlPackage.ACTOR_REF__LABEL:
                setLabel((ComponentLabel)newValue);
                return;
            case GrlPackage.ACTOR_REF__PARENT:
                setParent((SpecificationComponentRef)newValue);
                return;
            case GrlPackage.ACTOR_REF__CHILDREN:
                getChildren().clear();
                getChildren().addAll((Collection)newValue);
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
            case GrlPackage.ACTOR_REF__X:
                setX(X_EDEFAULT);
                return;
            case GrlPackage.ACTOR_REF__Y:
                setY(Y_EDEFAULT);
                return;
            case GrlPackage.ACTOR_REF__WIDTH:
                setWidth(WIDTH_EDEFAULT);
                return;
            case GrlPackage.ACTOR_REF__HEIGHT:
                setHeight(HEIGHT_EDEFAULT);
                return;
            case GrlPackage.ACTOR_REF__FIXED:
                setFixed(FIXED_EDEFAULT);
                return;
            case GrlPackage.ACTOR_REF__SPEC_DIAGRAM:
                setSpecDiagram((SpecificationDiagram)null);
                return;
            case GrlPackage.ACTOR_REF__COMP_DEF:
                setCompDef((SpecificationComponent)null);
                return;
            case GrlPackage.ACTOR_REF__NODES:
                getNodes().clear();
                return;
            case GrlPackage.ACTOR_REF__LABEL:
                setLabel((ComponentLabel)null);
                return;
            case GrlPackage.ACTOR_REF__PARENT:
                setParent((SpecificationComponentRef)null);
                return;
            case GrlPackage.ACTOR_REF__CHILDREN:
                getChildren().clear();
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
            case GrlPackage.ACTOR_REF__X:
                return x != X_EDEFAULT;
            case GrlPackage.ACTOR_REF__Y:
                return y != Y_EDEFAULT;
            case GrlPackage.ACTOR_REF__WIDTH:
                return width != WIDTH_EDEFAULT;
            case GrlPackage.ACTOR_REF__HEIGHT:
                return height != HEIGHT_EDEFAULT;
            case GrlPackage.ACTOR_REF__FIXED:
                return fixed != FIXED_EDEFAULT;
            case GrlPackage.ACTOR_REF__SPEC_DIAGRAM:
                return getSpecDiagram() != null;
            case GrlPackage.ACTOR_REF__COMP_DEF:
                return compDef != null;
            case GrlPackage.ACTOR_REF__NODES:
                return nodes != null && !nodes.isEmpty();
            case GrlPackage.ACTOR_REF__LABEL:
                return label != null;
            case GrlPackage.ACTOR_REF__PARENT:
                return parent != null;
            case GrlPackage.ACTOR_REF__CHILDREN:
                return children != null && !children.isEmpty();
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
        result.append(" (x: ");
        result.append(x);
        result.append(", y: ");
        result.append(y);
        result.append(", width: ");
        result.append(width);
        result.append(", height: ");
        result.append(height);
        result.append(", fixed: ");
        result.append(fixed);
        result.append(')');
        return result.toString();
    }

} //ActorRefImpl
