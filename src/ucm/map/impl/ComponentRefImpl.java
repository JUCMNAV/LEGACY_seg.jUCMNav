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

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import ucm.map.ComponentRef;
import ucm.map.MapPackage;

import urncore.ComponentLabel;
import urncore.IURNContainer;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.IURNNode;
import urncore.UrncorePackage;

import urncore.impl.UCMmodelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.map.impl.ComponentRefImpl#getX <em>X</em>}</li>
 *   <li>{@link ucm.map.impl.ComponentRefImpl#getY <em>Y</em>}</li>
 *   <li>{@link ucm.map.impl.ComponentRefImpl#getWidth <em>Width</em>}</li>
 *   <li>{@link ucm.map.impl.ComponentRefImpl#getHeight <em>Height</em>}</li>
 *   <li>{@link ucm.map.impl.ComponentRefImpl#isFixed <em>Fixed</em>}</li>
 *   <li>{@link ucm.map.impl.ComponentRefImpl#getDiagram <em>Diagram</em>}</li>
 *   <li>{@link ucm.map.impl.ComponentRefImpl#getContDef <em>Cont Def</em>}</li>
 *   <li>{@link ucm.map.impl.ComponentRefImpl#getNodes <em>Nodes</em>}</li>
 *   <li>{@link ucm.map.impl.ComponentRefImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link ucm.map.impl.ComponentRefImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link ucm.map.impl.ComponentRefImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link ucm.map.impl.ComponentRefImpl#getRole <em>Role</em>}</li>
 *   <li>{@link ucm.map.impl.ComponentRefImpl#getReplicationFactor <em>Replication Factor</em>}</li>
 *   <li>{@link ucm.map.impl.ComponentRefImpl#isAnchored <em>Anchored</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComponentRefImpl extends UCMmodelElementImpl implements ComponentRef {
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
     * The cached value of the '{@link #getContDef() <em>Cont Def</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getContDef()
     * @generated
     * @ordered
     */
    protected IURNContainer contDef = null;

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
    protected IURNContainerRef parent = null;

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
     * The default value of the '{@link #getRole() <em>Role</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRole()
     * @generated
     * @ordered
     */
    protected static final String ROLE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRole() <em>Role</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRole()
     * @generated
     * @ordered
     */
    protected String role = ROLE_EDEFAULT;

    /**
     * The default value of the '{@link #getReplicationFactor() <em>Replication Factor</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReplicationFactor()
     * @generated
     * @ordered
     */
    protected static final int REPLICATION_FACTOR_EDEFAULT = 1;

    /**
     * The cached value of the '{@link #getReplicationFactor() <em>Replication Factor</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getReplicationFactor()
     * @generated
     * @ordered
     */
    protected int replicationFactor = REPLICATION_FACTOR_EDEFAULT;

    /**
     * The default value of the '{@link #isAnchored() <em>Anchored</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isAnchored()
     * @generated
     * @ordered
     */
    protected static final boolean ANCHORED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isAnchored() <em>Anchored</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isAnchored()
     * @generated
     * @ordered
     */
    protected boolean anchored = ANCHORED_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ComponentRefImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return MapPackage.eINSTANCE.getComponentRef();
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
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.COMPONENT_REF__X, oldX, x));
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
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.COMPONENT_REF__Y, oldY, y));
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
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.COMPONENT_REF__WIDTH, oldWidth, width));
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
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.COMPONENT_REF__HEIGHT, oldHeight, height));
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
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.COMPONENT_REF__FIXED, oldFixed, fixed));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IURNDiagram getDiagram() {
        if (eContainerFeatureID != MapPackage.COMPONENT_REF__DIAGRAM) return null;
        return (IURNDiagram)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDiagram(IURNDiagram newDiagram) {
        if (newDiagram != eContainer || (eContainerFeatureID != MapPackage.COMPONENT_REF__DIAGRAM && newDiagram != null)) {
            if (EcoreUtil.isAncestor(this, newDiagram))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newDiagram != null)
                msgs = ((InternalEObject)newDiagram).eInverseAdd(this, UrncorePackage.IURN_DIAGRAM__CONT_REFS, IURNDiagram.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newDiagram, MapPackage.COMPONENT_REF__DIAGRAM, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.COMPONENT_REF__DIAGRAM, newDiagram, newDiagram));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IURNContainer getContDef() {
        if (contDef != null && contDef.eIsProxy()) {
            IURNContainer oldContDef = contDef;
            contDef = (IURNContainer)eResolveProxy((InternalEObject)contDef);
            if (contDef != oldContDef) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, MapPackage.COMPONENT_REF__CONT_DEF, oldContDef, contDef));
            }
        }
        return contDef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IURNContainer basicGetContDef() {
        return contDef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetContDef(IURNContainer newContDef, NotificationChain msgs) {
        IURNContainer oldContDef = contDef;
        contDef = newContDef;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.COMPONENT_REF__CONT_DEF, oldContDef, newContDef);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setContDef(IURNContainer newContDef) {
        if (newContDef != contDef) {
            NotificationChain msgs = null;
            if (contDef != null)
                msgs = ((InternalEObject)contDef).eInverseRemove(this, UrncorePackage.IURN_CONTAINER__CONT_REFS, IURNContainer.class, msgs);
            if (newContDef != null)
                msgs = ((InternalEObject)newContDef).eInverseAdd(this, UrncorePackage.IURN_CONTAINER__CONT_REFS, IURNContainer.class, msgs);
            msgs = basicSetContDef(newContDef, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.COMPONENT_REF__CONT_DEF, newContDef, newContDef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getNodes() {
        if (nodes == null) {
            nodes = new EObjectWithInverseResolvingEList(IURNNode.class, this, MapPackage.COMPONENT_REF__NODES, UrncorePackage.IURN_NODE__CONT_REF);
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.COMPONENT_REF__LABEL, oldLabel, newLabel);
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
                msgs = ((InternalEObject)label).eInverseRemove(this, UrncorePackage.COMPONENT_LABEL__CONT_REF, ComponentLabel.class, msgs);
            if (newLabel != null)
                msgs = ((InternalEObject)newLabel).eInverseAdd(this, UrncorePackage.COMPONENT_LABEL__CONT_REF, ComponentLabel.class, msgs);
            msgs = basicSetLabel(newLabel, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.COMPONENT_REF__LABEL, newLabel, newLabel));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IURNContainerRef getParent() {
        if (parent != null && parent.eIsProxy()) {
            IURNContainerRef oldParent = parent;
            parent = (IURNContainerRef)eResolveProxy((InternalEObject)parent);
            if (parent != oldParent) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, MapPackage.COMPONENT_REF__PARENT, oldParent, parent));
            }
        }
        return parent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IURNContainerRef basicGetParent() {
        return parent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetParent(IURNContainerRef newParent, NotificationChain msgs) {
        IURNContainerRef oldParent = parent;
        parent = newParent;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.COMPONENT_REF__PARENT, oldParent, newParent);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setParent(IURNContainerRef newParent) {
        if (newParent != parent) {
            NotificationChain msgs = null;
            if (parent != null)
                msgs = ((InternalEObject)parent).eInverseRemove(this, UrncorePackage.IURN_CONTAINER_REF__CHILDREN, IURNContainerRef.class, msgs);
            if (newParent != null)
                msgs = ((InternalEObject)newParent).eInverseAdd(this, UrncorePackage.IURN_CONTAINER_REF__CHILDREN, IURNContainerRef.class, msgs);
            msgs = basicSetParent(newParent, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.COMPONENT_REF__PARENT, newParent, newParent));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getChildren() {
        if (children == null) {
            children = new EObjectWithInverseResolvingEList(IURNContainerRef.class, this, MapPackage.COMPONENT_REF__CHILDREN, UrncorePackage.IURN_CONTAINER_REF__PARENT);
        }
        return children;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getRole() {
        return role;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRole(String newRole) {
        String oldRole = role;
        role = newRole;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.COMPONENT_REF__ROLE, oldRole, role));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getReplicationFactor() {
        return replicationFactor;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setReplicationFactor(int newReplicationFactor) {
        int oldReplicationFactor = replicationFactor;
        replicationFactor = newReplicationFactor;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.COMPONENT_REF__REPLICATION_FACTOR, oldReplicationFactor, replicationFactor));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isAnchored() {
        return anchored;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAnchored(boolean newAnchored) {
        boolean oldAnchored = anchored;
        anchored = newAnchored;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.COMPONENT_REF__ANCHORED, oldAnchored, anchored));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case MapPackage.COMPONENT_REF__FROM_LINKS:
                    return ((InternalEList)getFromLinks()).basicAdd(otherEnd, msgs);
                case MapPackage.COMPONENT_REF__TO_LINKS:
                    return ((InternalEList)getToLinks()).basicAdd(otherEnd, msgs);
                case MapPackage.COMPONENT_REF__DIAGRAM:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, MapPackage.COMPONENT_REF__DIAGRAM, msgs);
                case MapPackage.COMPONENT_REF__CONT_DEF:
                    if (contDef != null)
                        msgs = ((InternalEObject)contDef).eInverseRemove(this, UrncorePackage.IURN_CONTAINER__CONT_REFS, IURNContainer.class, msgs);
                    return basicSetContDef((IURNContainer)otherEnd, msgs);
                case MapPackage.COMPONENT_REF__NODES:
                    return ((InternalEList)getNodes()).basicAdd(otherEnd, msgs);
                case MapPackage.COMPONENT_REF__LABEL:
                    if (label != null)
                        msgs = ((InternalEObject)label).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MapPackage.COMPONENT_REF__LABEL, null, msgs);
                    return basicSetLabel((ComponentLabel)otherEnd, msgs);
                case MapPackage.COMPONENT_REF__PARENT:
                    if (parent != null)
                        msgs = ((InternalEObject)parent).eInverseRemove(this, UrncorePackage.IURN_CONTAINER_REF__CHILDREN, IURNContainerRef.class, msgs);
                    return basicSetParent((IURNContainerRef)otherEnd, msgs);
                case MapPackage.COMPONENT_REF__CHILDREN:
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
                case MapPackage.COMPONENT_REF__FROM_LINKS:
                    return ((InternalEList)getFromLinks()).basicRemove(otherEnd, msgs);
                case MapPackage.COMPONENT_REF__TO_LINKS:
                    return ((InternalEList)getToLinks()).basicRemove(otherEnd, msgs);
                case MapPackage.COMPONENT_REF__DIAGRAM:
                    return eBasicSetContainer(null, MapPackage.COMPONENT_REF__DIAGRAM, msgs);
                case MapPackage.COMPONENT_REF__CONT_DEF:
                    return basicSetContDef(null, msgs);
                case MapPackage.COMPONENT_REF__NODES:
                    return ((InternalEList)getNodes()).basicRemove(otherEnd, msgs);
                case MapPackage.COMPONENT_REF__LABEL:
                    return basicSetLabel(null, msgs);
                case MapPackage.COMPONENT_REF__PARENT:
                    return basicSetParent(null, msgs);
                case MapPackage.COMPONENT_REF__CHILDREN:
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
                case MapPackage.COMPONENT_REF__DIAGRAM:
                    return eContainer.eInverseRemove(this, UrncorePackage.IURN_DIAGRAM__CONT_REFS, IURNDiagram.class, msgs);
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
            case MapPackage.COMPONENT_REF__FROM_LINKS:
                return getFromLinks();
            case MapPackage.COMPONENT_REF__TO_LINKS:
                return getToLinks();
            case MapPackage.COMPONENT_REF__ID:
                return getId();
            case MapPackage.COMPONENT_REF__NAME:
                return getName();
            case MapPackage.COMPONENT_REF__DESCRIPTION:
                return getDescription();
            case MapPackage.COMPONENT_REF__X:
                return new Integer(getX());
            case MapPackage.COMPONENT_REF__Y:
                return new Integer(getY());
            case MapPackage.COMPONENT_REF__WIDTH:
                return new Integer(getWidth());
            case MapPackage.COMPONENT_REF__HEIGHT:
                return new Integer(getHeight());
            case MapPackage.COMPONENT_REF__FIXED:
                return isFixed() ? Boolean.TRUE : Boolean.FALSE;
            case MapPackage.COMPONENT_REF__DIAGRAM:
                return getDiagram();
            case MapPackage.COMPONENT_REF__CONT_DEF:
                if (resolve) return getContDef();
                return basicGetContDef();
            case MapPackage.COMPONENT_REF__NODES:
                return getNodes();
            case MapPackage.COMPONENT_REF__LABEL:
                return getLabel();
            case MapPackage.COMPONENT_REF__PARENT:
                if (resolve) return getParent();
                return basicGetParent();
            case MapPackage.COMPONENT_REF__CHILDREN:
                return getChildren();
            case MapPackage.COMPONENT_REF__ROLE:
                return getRole();
            case MapPackage.COMPONENT_REF__REPLICATION_FACTOR:
                return new Integer(getReplicationFactor());
            case MapPackage.COMPONENT_REF__ANCHORED:
                return isAnchored() ? Boolean.TRUE : Boolean.FALSE;
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
            case MapPackage.COMPONENT_REF__FROM_LINKS:
                getFromLinks().clear();
                getFromLinks().addAll((Collection)newValue);
                return;
            case MapPackage.COMPONENT_REF__TO_LINKS:
                getToLinks().clear();
                getToLinks().addAll((Collection)newValue);
                return;
            case MapPackage.COMPONENT_REF__ID:
                setId((String)newValue);
                return;
            case MapPackage.COMPONENT_REF__NAME:
                setName((String)newValue);
                return;
            case MapPackage.COMPONENT_REF__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case MapPackage.COMPONENT_REF__X:
                setX(((Integer)newValue).intValue());
                return;
            case MapPackage.COMPONENT_REF__Y:
                setY(((Integer)newValue).intValue());
                return;
            case MapPackage.COMPONENT_REF__WIDTH:
                setWidth(((Integer)newValue).intValue());
                return;
            case MapPackage.COMPONENT_REF__HEIGHT:
                setHeight(((Integer)newValue).intValue());
                return;
            case MapPackage.COMPONENT_REF__FIXED:
                setFixed(((Boolean)newValue).booleanValue());
                return;
            case MapPackage.COMPONENT_REF__DIAGRAM:
                setDiagram((IURNDiagram)newValue);
                return;
            case MapPackage.COMPONENT_REF__CONT_DEF:
                setContDef((IURNContainer)newValue);
                return;
            case MapPackage.COMPONENT_REF__NODES:
                getNodes().clear();
                getNodes().addAll((Collection)newValue);
                return;
            case MapPackage.COMPONENT_REF__LABEL:
                setLabel((ComponentLabel)newValue);
                return;
            case MapPackage.COMPONENT_REF__PARENT:
                setParent((IURNContainerRef)newValue);
                return;
            case MapPackage.COMPONENT_REF__CHILDREN:
                getChildren().clear();
                getChildren().addAll((Collection)newValue);
                return;
            case MapPackage.COMPONENT_REF__ROLE:
                setRole((String)newValue);
                return;
            case MapPackage.COMPONENT_REF__REPLICATION_FACTOR:
                setReplicationFactor(((Integer)newValue).intValue());
                return;
            case MapPackage.COMPONENT_REF__ANCHORED:
                setAnchored(((Boolean)newValue).booleanValue());
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
            case MapPackage.COMPONENT_REF__FROM_LINKS:
                getFromLinks().clear();
                return;
            case MapPackage.COMPONENT_REF__TO_LINKS:
                getToLinks().clear();
                return;
            case MapPackage.COMPONENT_REF__ID:
                setId(ID_EDEFAULT);
                return;
            case MapPackage.COMPONENT_REF__NAME:
                setName(NAME_EDEFAULT);
                return;
            case MapPackage.COMPONENT_REF__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case MapPackage.COMPONENT_REF__X:
                setX(X_EDEFAULT);
                return;
            case MapPackage.COMPONENT_REF__Y:
                setY(Y_EDEFAULT);
                return;
            case MapPackage.COMPONENT_REF__WIDTH:
                setWidth(WIDTH_EDEFAULT);
                return;
            case MapPackage.COMPONENT_REF__HEIGHT:
                setHeight(HEIGHT_EDEFAULT);
                return;
            case MapPackage.COMPONENT_REF__FIXED:
                setFixed(FIXED_EDEFAULT);
                return;
            case MapPackage.COMPONENT_REF__DIAGRAM:
                setDiagram((IURNDiagram)null);
                return;
            case MapPackage.COMPONENT_REF__CONT_DEF:
                setContDef((IURNContainer)null);
                return;
            case MapPackage.COMPONENT_REF__NODES:
                getNodes().clear();
                return;
            case MapPackage.COMPONENT_REF__LABEL:
                setLabel((ComponentLabel)null);
                return;
            case MapPackage.COMPONENT_REF__PARENT:
                setParent((IURNContainerRef)null);
                return;
            case MapPackage.COMPONENT_REF__CHILDREN:
                getChildren().clear();
                return;
            case MapPackage.COMPONENT_REF__ROLE:
                setRole(ROLE_EDEFAULT);
                return;
            case MapPackage.COMPONENT_REF__REPLICATION_FACTOR:
                setReplicationFactor(REPLICATION_FACTOR_EDEFAULT);
                return;
            case MapPackage.COMPONENT_REF__ANCHORED:
                setAnchored(ANCHORED_EDEFAULT);
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
            case MapPackage.COMPONENT_REF__FROM_LINKS:
                return fromLinks != null && !fromLinks.isEmpty();
            case MapPackage.COMPONENT_REF__TO_LINKS:
                return toLinks != null && !toLinks.isEmpty();
            case MapPackage.COMPONENT_REF__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case MapPackage.COMPONENT_REF__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case MapPackage.COMPONENT_REF__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case MapPackage.COMPONENT_REF__X:
                return x != X_EDEFAULT;
            case MapPackage.COMPONENT_REF__Y:
                return y != Y_EDEFAULT;
            case MapPackage.COMPONENT_REF__WIDTH:
                return width != WIDTH_EDEFAULT;
            case MapPackage.COMPONENT_REF__HEIGHT:
                return height != HEIGHT_EDEFAULT;
            case MapPackage.COMPONENT_REF__FIXED:
                return fixed != FIXED_EDEFAULT;
            case MapPackage.COMPONENT_REF__DIAGRAM:
                return getDiagram() != null;
            case MapPackage.COMPONENT_REF__CONT_DEF:
                return contDef != null;
            case MapPackage.COMPONENT_REF__NODES:
                return nodes != null && !nodes.isEmpty();
            case MapPackage.COMPONENT_REF__LABEL:
                return label != null;
            case MapPackage.COMPONENT_REF__PARENT:
                return parent != null;
            case MapPackage.COMPONENT_REF__CHILDREN:
                return children != null && !children.isEmpty();
            case MapPackage.COMPONENT_REF__ROLE:
                return ROLE_EDEFAULT == null ? role != null : !ROLE_EDEFAULT.equals(role);
            case MapPackage.COMPONENT_REF__REPLICATION_FACTOR:
                return replicationFactor != REPLICATION_FACTOR_EDEFAULT;
            case MapPackage.COMPONENT_REF__ANCHORED:
                return anchored != ANCHORED_EDEFAULT;
        }
        return eDynamicIsSet(eFeature);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
        if (baseClass == IURNContainerRef.class) {
            switch (derivedFeatureID) {
                case MapPackage.COMPONENT_REF__X: return UrncorePackage.IURN_CONTAINER_REF__X;
                case MapPackage.COMPONENT_REF__Y: return UrncorePackage.IURN_CONTAINER_REF__Y;
                case MapPackage.COMPONENT_REF__WIDTH: return UrncorePackage.IURN_CONTAINER_REF__WIDTH;
                case MapPackage.COMPONENT_REF__HEIGHT: return UrncorePackage.IURN_CONTAINER_REF__HEIGHT;
                case MapPackage.COMPONENT_REF__FIXED: return UrncorePackage.IURN_CONTAINER_REF__FIXED;
                case MapPackage.COMPONENT_REF__DIAGRAM: return UrncorePackage.IURN_CONTAINER_REF__DIAGRAM;
                case MapPackage.COMPONENT_REF__CONT_DEF: return UrncorePackage.IURN_CONTAINER_REF__CONT_DEF;
                case MapPackage.COMPONENT_REF__NODES: return UrncorePackage.IURN_CONTAINER_REF__NODES;
                case MapPackage.COMPONENT_REF__LABEL: return UrncorePackage.IURN_CONTAINER_REF__LABEL;
                case MapPackage.COMPONENT_REF__PARENT: return UrncorePackage.IURN_CONTAINER_REF__PARENT;
                case MapPackage.COMPONENT_REF__CHILDREN: return UrncorePackage.IURN_CONTAINER_REF__CHILDREN;
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
        if (baseClass == IURNContainerRef.class) {
            switch (baseFeatureID) {
                case UrncorePackage.IURN_CONTAINER_REF__X: return MapPackage.COMPONENT_REF__X;
                case UrncorePackage.IURN_CONTAINER_REF__Y: return MapPackage.COMPONENT_REF__Y;
                case UrncorePackage.IURN_CONTAINER_REF__WIDTH: return MapPackage.COMPONENT_REF__WIDTH;
                case UrncorePackage.IURN_CONTAINER_REF__HEIGHT: return MapPackage.COMPONENT_REF__HEIGHT;
                case UrncorePackage.IURN_CONTAINER_REF__FIXED: return MapPackage.COMPONENT_REF__FIXED;
                case UrncorePackage.IURN_CONTAINER_REF__DIAGRAM: return MapPackage.COMPONENT_REF__DIAGRAM;
                case UrncorePackage.IURN_CONTAINER_REF__CONT_DEF: return MapPackage.COMPONENT_REF__CONT_DEF;
                case UrncorePackage.IURN_CONTAINER_REF__NODES: return MapPackage.COMPONENT_REF__NODES;
                case UrncorePackage.IURN_CONTAINER_REF__LABEL: return MapPackage.COMPONENT_REF__LABEL;
                case UrncorePackage.IURN_CONTAINER_REF__PARENT: return MapPackage.COMPONENT_REF__PARENT;
                case UrncorePackage.IURN_CONTAINER_REF__CHILDREN: return MapPackage.COMPONENT_REF__CHILDREN;
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
        result.append(", width: ");
        result.append(width);
        result.append(", height: ");
        result.append(height);
        result.append(", fixed: ");
        result.append(fixed);
        result.append(", role: ");
        result.append(role);
        result.append(", replicationFactor: ");
        result.append(replicationFactor);
        result.append(", anchored: ");
        result.append(anchored);
        result.append(')');
        return result.toString();
    }

} //ComponentRefImpl
