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
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import urncore.ComponentLabel;
import urncore.IURNContainer;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.IURNNode;
import urncore.UrncorePackage;
import urncore.impl.GRLmodelElementImpl;

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
 *   <li>{@link grl.impl.ActorRefImpl#getDiagram <em>Diagram</em>}</li>
 *   <li>{@link grl.impl.ActorRefImpl#getContDef <em>Cont Def</em>}</li>
 *   <li>{@link grl.impl.ActorRefImpl#getNodes <em>Nodes</em>}</li>
 *   <li>{@link grl.impl.ActorRefImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link grl.impl.ActorRefImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link grl.impl.ActorRefImpl#getChildren <em>Children</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActorRefImpl extends GRLmodelElementImpl implements ActorRef {
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
    protected IURNContainer contDef;

    /**
	 * The cached value of the '{@link #getNodes() <em>Nodes</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getNodes()
	 * @generated
	 * @ordered
	 */
    protected EList nodes;

    /**
	 * The cached value of the '{@link #getLabel() <em>Label</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
    protected ComponentLabel label;

    /**
	 * The cached value of the '{@link #getParent() <em>Parent</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getParent()
	 * @generated
	 * @ordered
	 */
    protected IURNContainerRef parent;

    /**
	 * The cached value of the '{@link #getChildren() <em>Children</em>}' reference list.
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
    protected ActorRefImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return GrlPackage.Literals.ACTOR_REF;
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
    public IURNDiagram getDiagram() {
		if (eContainerFeatureID() != GrlPackage.ACTOR_REF__DIAGRAM) return null;
		return (IURNDiagram)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDiagram(IURNDiagram newDiagram, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newDiagram, GrlPackage.ACTOR_REF__DIAGRAM, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setDiagram(IURNDiagram newDiagram) {
		if (newDiagram != eInternalContainer() || (eContainerFeatureID() != GrlPackage.ACTOR_REF__DIAGRAM && newDiagram != null)) {
			if (EcoreUtil.isAncestor(this, newDiagram))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newDiagram != null)
				msgs = ((InternalEObject)newDiagram).eInverseAdd(this, UrncorePackage.IURN_DIAGRAM__CONT_REFS, IURNDiagram.class, msgs);
			msgs = basicSetDiagram(newDiagram, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.ACTOR_REF__DIAGRAM, newDiagram, newDiagram));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public IURNContainer getContDef() {
		if (contDef != null && contDef.eIsProxy()) {
			InternalEObject oldContDef = (InternalEObject)contDef;
			contDef = (IURNContainer)eResolveProxy(oldContDef);
			if (contDef != oldContDef) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GrlPackage.ACTOR_REF__CONT_DEF, oldContDef, contDef));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.ACTOR_REF__CONT_DEF, oldContDef, newContDef);
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
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.ACTOR_REF__CONT_DEF, newContDef, newContDef));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getNodes() {
		if (nodes == null) {
			nodes = new EObjectWithInverseResolvingEList(IURNNode.class, this, GrlPackage.ACTOR_REF__NODES, UrncorePackage.IURN_NODE__CONT_REF);
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
				msgs = ((InternalEObject)label).eInverseRemove(this, UrncorePackage.COMPONENT_LABEL__CONT_REF, ComponentLabel.class, msgs);
			if (newLabel != null)
				msgs = ((InternalEObject)newLabel).eInverseAdd(this, UrncorePackage.COMPONENT_LABEL__CONT_REF, ComponentLabel.class, msgs);
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
    public IURNContainerRef getParent() {
		if (parent != null && parent.eIsProxy()) {
			InternalEObject oldParent = (InternalEObject)parent;
			parent = (IURNContainerRef)eResolveProxy(oldParent);
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
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.ACTOR_REF__PARENT, newParent, newParent));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getChildren() {
		if (children == null) {
			children = new EObjectWithInverseResolvingEList(IURNContainerRef.class, this, GrlPackage.ACTOR_REF__CHILDREN, UrncorePackage.IURN_CONTAINER_REF__PARENT);
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
			case GrlPackage.ACTOR_REF__DIAGRAM:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetDiagram((IURNDiagram)otherEnd, msgs);
			case GrlPackage.ACTOR_REF__CONT_DEF:
				if (contDef != null)
					msgs = ((InternalEObject)contDef).eInverseRemove(this, UrncorePackage.IURN_CONTAINER__CONT_REFS, IURNContainer.class, msgs);
				return basicSetContDef((IURNContainer)otherEnd, msgs);
			case GrlPackage.ACTOR_REF__NODES:
				return ((InternalEList)getNodes()).basicAdd(otherEnd, msgs);
			case GrlPackage.ACTOR_REF__LABEL:
				if (label != null)
					msgs = ((InternalEObject)label).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - GrlPackage.ACTOR_REF__LABEL, null, msgs);
				return basicSetLabel((ComponentLabel)otherEnd, msgs);
			case GrlPackage.ACTOR_REF__PARENT:
				if (parent != null)
					msgs = ((InternalEObject)parent).eInverseRemove(this, UrncorePackage.IURN_CONTAINER_REF__CHILDREN, IURNContainerRef.class, msgs);
				return basicSetParent((IURNContainerRef)otherEnd, msgs);
			case GrlPackage.ACTOR_REF__CHILDREN:
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
			case GrlPackage.ACTOR_REF__DIAGRAM:
				return basicSetDiagram(null, msgs);
			case GrlPackage.ACTOR_REF__CONT_DEF:
				return basicSetContDef(null, msgs);
			case GrlPackage.ACTOR_REF__NODES:
				return ((InternalEList)getNodes()).basicRemove(otherEnd, msgs);
			case GrlPackage.ACTOR_REF__LABEL:
				return basicSetLabel(null, msgs);
			case GrlPackage.ACTOR_REF__PARENT:
				return basicSetParent(null, msgs);
			case GrlPackage.ACTOR_REF__CHILDREN:
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
			case GrlPackage.ACTOR_REF__DIAGRAM:
				return eInternalContainer().eInverseRemove(this, UrncorePackage.IURN_DIAGRAM__CONT_REFS, IURNDiagram.class, msgs);
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
			case GrlPackage.ACTOR_REF__DIAGRAM:
				return getDiagram();
			case GrlPackage.ACTOR_REF__CONT_DEF:
				if (resolve) return getContDef();
				return basicGetContDef();
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
		return super.eGet(featureID, resolve, coreType);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
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
			case GrlPackage.ACTOR_REF__DIAGRAM:
				setDiagram((IURNDiagram)newValue);
				return;
			case GrlPackage.ACTOR_REF__CONT_DEF:
				setContDef((IURNContainer)newValue);
				return;
			case GrlPackage.ACTOR_REF__NODES:
				getNodes().clear();
				getNodes().addAll((Collection)newValue);
				return;
			case GrlPackage.ACTOR_REF__LABEL:
				setLabel((ComponentLabel)newValue);
				return;
			case GrlPackage.ACTOR_REF__PARENT:
				setParent((IURNContainerRef)newValue);
				return;
			case GrlPackage.ACTOR_REF__CHILDREN:
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
			case GrlPackage.ACTOR_REF__DIAGRAM:
				setDiagram((IURNDiagram)null);
				return;
			case GrlPackage.ACTOR_REF__CONT_DEF:
				setContDef((IURNContainer)null);
				return;
			case GrlPackage.ACTOR_REF__NODES:
				getNodes().clear();
				return;
			case GrlPackage.ACTOR_REF__LABEL:
				setLabel((ComponentLabel)null);
				return;
			case GrlPackage.ACTOR_REF__PARENT:
				setParent((IURNContainerRef)null);
				return;
			case GrlPackage.ACTOR_REF__CHILDREN:
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
			case GrlPackage.ACTOR_REF__DIAGRAM:
				return getDiagram() != null;
			case GrlPackage.ACTOR_REF__CONT_DEF:
				return contDef != null;
			case GrlPackage.ACTOR_REF__NODES:
				return nodes != null && !nodes.isEmpty();
			case GrlPackage.ACTOR_REF__LABEL:
				return label != null;
			case GrlPackage.ACTOR_REF__PARENT:
				return parent != null;
			case GrlPackage.ACTOR_REF__CHILDREN:
				return children != null && !children.isEmpty();
		}
		return super.eIsSet(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
		if (baseClass == IURNContainerRef.class) {
			switch (derivedFeatureID) {
				case GrlPackage.ACTOR_REF__X: return UrncorePackage.IURN_CONTAINER_REF__X;
				case GrlPackage.ACTOR_REF__Y: return UrncorePackage.IURN_CONTAINER_REF__Y;
				case GrlPackage.ACTOR_REF__WIDTH: return UrncorePackage.IURN_CONTAINER_REF__WIDTH;
				case GrlPackage.ACTOR_REF__HEIGHT: return UrncorePackage.IURN_CONTAINER_REF__HEIGHT;
				case GrlPackage.ACTOR_REF__FIXED: return UrncorePackage.IURN_CONTAINER_REF__FIXED;
				case GrlPackage.ACTOR_REF__DIAGRAM: return UrncorePackage.IURN_CONTAINER_REF__DIAGRAM;
				case GrlPackage.ACTOR_REF__CONT_DEF: return UrncorePackage.IURN_CONTAINER_REF__CONT_DEF;
				case GrlPackage.ACTOR_REF__NODES: return UrncorePackage.IURN_CONTAINER_REF__NODES;
				case GrlPackage.ACTOR_REF__LABEL: return UrncorePackage.IURN_CONTAINER_REF__LABEL;
				case GrlPackage.ACTOR_REF__PARENT: return UrncorePackage.IURN_CONTAINER_REF__PARENT;
				case GrlPackage.ACTOR_REF__CHILDREN: return UrncorePackage.IURN_CONTAINER_REF__CHILDREN;
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
				case UrncorePackage.IURN_CONTAINER_REF__X: return GrlPackage.ACTOR_REF__X;
				case UrncorePackage.IURN_CONTAINER_REF__Y: return GrlPackage.ACTOR_REF__Y;
				case UrncorePackage.IURN_CONTAINER_REF__WIDTH: return GrlPackage.ACTOR_REF__WIDTH;
				case UrncorePackage.IURN_CONTAINER_REF__HEIGHT: return GrlPackage.ACTOR_REF__HEIGHT;
				case UrncorePackage.IURN_CONTAINER_REF__FIXED: return GrlPackage.ACTOR_REF__FIXED;
				case UrncorePackage.IURN_CONTAINER_REF__DIAGRAM: return GrlPackage.ACTOR_REF__DIAGRAM;
				case UrncorePackage.IURN_CONTAINER_REF__CONT_DEF: return GrlPackage.ACTOR_REF__CONT_DEF;
				case UrncorePackage.IURN_CONTAINER_REF__NODES: return GrlPackage.ACTOR_REF__NODES;
				case UrncorePackage.IURN_CONTAINER_REF__LABEL: return GrlPackage.ACTOR_REF__LABEL;
				case UrncorePackage.IURN_CONTAINER_REF__PARENT: return GrlPackage.ACTOR_REF__PARENT;
				case UrncorePackage.IURN_CONTAINER_REF__CHILDREN: return GrlPackage.ACTOR_REF__CHILDREN;
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
		result.append(')');
		return result.toString();
	}

} //ActorRefImpl
