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
import org.eclipse.emf.ecore.util.InternalEList;

import ucm.map.ComponentRef;
import ucm.map.MapPackage;
import ucm.map.PathNode;

import urncore.ComponentElement;
import urncore.UrncorePackage;

import urncore.impl.UCMmodelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.map.impl.ComponentRefImpl#getRole <em>Role</em>}</li>
 *   <li>{@link ucm.map.impl.ComponentRefImpl#getReplicationFactor <em>Replication Factor</em>}</li>
 *   <li>{@link ucm.map.impl.ComponentRefImpl#isAnchored <em>Anchored</em>}</li>
 *   <li>{@link ucm.map.impl.ComponentRefImpl#isFixed <em>Fixed</em>}</li>
 *   <li>{@link ucm.map.impl.ComponentRefImpl#getX <em>X</em>}</li>
 *   <li>{@link ucm.map.impl.ComponentRefImpl#getY <em>Y</em>}</li>
 *   <li>{@link ucm.map.impl.ComponentRefImpl#getWidth <em>Width</em>}</li>
 *   <li>{@link ucm.map.impl.ComponentRefImpl#getHeight <em>Height</em>}</li>
 *   <li>{@link ucm.map.impl.ComponentRefImpl#getLabelX <em>Label X</em>}</li>
 *   <li>{@link ucm.map.impl.ComponentRefImpl#getLabely <em>Labely</em>}</li>
 *   <li>{@link ucm.map.impl.ComponentRefImpl#getCompDef <em>Comp Def</em>}</li>
 *   <li>{@link ucm.map.impl.ComponentRefImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link ucm.map.impl.ComponentRefImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link ucm.map.impl.ComponentRefImpl#getPathNodes <em>Path Nodes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComponentRefImpl extends UCMmodelElementImpl implements ComponentRef {
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
	 * The default value of the '{@link #getLabelX() <em>Label X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabelX()
	 * @generated
	 * @ordered
	 */
	protected static final int LABEL_X_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLabelX() <em>Label X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabelX()
	 * @generated
	 * @ordered
	 */
	protected int labelX = LABEL_X_EDEFAULT;

	/**
	 * The default value of the '{@link #getLabely() <em>Labely</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabely()
	 * @generated
	 * @ordered
	 */
	protected static final int LABELY_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLabely() <em>Labely</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabely()
	 * @generated
	 * @ordered
	 */
	protected int labely = LABELY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCompDef() <em>Comp Def</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompDef()
	 * @generated
	 * @ordered
	 */
	protected ComponentElement compDef = null;

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
	 * The cached value of the '{@link #getParent() <em>Parent</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParent()
	 * @generated
	 * @ordered
	 */
	protected ComponentRef parent = null;

	/**
	 * The cached value of the '{@link #getPathNodes() <em>Path Nodes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPathNodes()
	 * @generated
	 * @ordered
	 */
	protected EList pathNodes = null;

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
	public int getLabelX() {
		return labelX;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabelX(int newLabelX) {
		int oldLabelX = labelX;
		labelX = newLabelX;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.COMPONENT_REF__LABEL_X, oldLabelX, labelX));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getLabely() {
		return labely;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabely(int newLabely) {
		int oldLabely = labely;
		labely = newLabely;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.COMPONENT_REF__LABELY, oldLabely, labely));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentElement getCompDef() {
		if (compDef != null && compDef.eIsProxy()) {
			ComponentElement oldCompDef = compDef;
			compDef = (ComponentElement)eResolveProxy((InternalEObject)compDef);
			if (compDef != oldCompDef) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MapPackage.COMPONENT_REF__COMP_DEF, oldCompDef, compDef));
			}
		}
		return compDef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentElement basicGetCompDef() {
		return compDef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCompDef(ComponentElement newCompDef, NotificationChain msgs) {
		ComponentElement oldCompDef = compDef;
		compDef = newCompDef;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.COMPONENT_REF__COMP_DEF, oldCompDef, newCompDef);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCompDef(ComponentElement newCompDef) {
		if (newCompDef != compDef) {
			NotificationChain msgs = null;
			if (compDef != null)
				msgs = ((InternalEObject)compDef).eInverseRemove(this, UrncorePackage.COMPONENT_ELEMENT__COMP_REFS, ComponentElement.class, msgs);
			if (newCompDef != null)
				msgs = ((InternalEObject)newCompDef).eInverseAdd(this, UrncorePackage.COMPONENT_ELEMENT__COMP_REFS, ComponentElement.class, msgs);
			msgs = basicSetCompDef(newCompDef, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.COMPONENT_REF__COMP_DEF, newCompDef, newCompDef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getChildren() {
		if (children == null) {
			children = new EObjectWithInverseResolvingEList(ComponentRef.class, this, MapPackage.COMPONENT_REF__CHILDREN, MapPackage.COMPONENT_REF__PARENT);
		}
		return children;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentRef getParent() {
		if (parent != null && parent.eIsProxy()) {
			ComponentRef oldParent = parent;
			parent = (ComponentRef)eResolveProxy((InternalEObject)parent);
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
	public ComponentRef basicGetParent() {
		return parent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParent(ComponentRef newParent, NotificationChain msgs) {
		ComponentRef oldParent = parent;
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
	public void setParent(ComponentRef newParent) {
		if (newParent != parent) {
			NotificationChain msgs = null;
			if (parent != null)
				msgs = ((InternalEObject)parent).eInverseRemove(this, MapPackage.COMPONENT_REF__CHILDREN, ComponentRef.class, msgs);
			if (newParent != null)
				msgs = ((InternalEObject)newParent).eInverseAdd(this, MapPackage.COMPONENT_REF__CHILDREN, ComponentRef.class, msgs);
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
	public EList getPathNodes() {
		if (pathNodes == null) {
			pathNodes = new EObjectWithInverseResolvingEList(PathNode.class, this, MapPackage.COMPONENT_REF__PATH_NODES, MapPackage.PATH_NODE__COMP_REF);
		}
		return pathNodes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case MapPackage.COMPONENT_REF__URN_LINKS:
					return ((InternalEList)getUrnLinks()).basicAdd(otherEnd, msgs);
				case MapPackage.COMPONENT_REF__COMP_DEF:
					if (compDef != null)
						msgs = ((InternalEObject)compDef).eInverseRemove(this, UrncorePackage.COMPONENT_ELEMENT__COMP_REFS, ComponentElement.class, msgs);
					return basicSetCompDef((ComponentElement)otherEnd, msgs);
				case MapPackage.COMPONENT_REF__CHILDREN:
					return ((InternalEList)getChildren()).basicAdd(otherEnd, msgs);
				case MapPackage.COMPONENT_REF__PARENT:
					if (parent != null)
						msgs = ((InternalEObject)parent).eInverseRemove(this, MapPackage.COMPONENT_REF__CHILDREN, ComponentRef.class, msgs);
					return basicSetParent((ComponentRef)otherEnd, msgs);
				case MapPackage.COMPONENT_REF__PATH_NODES:
					return ((InternalEList)getPathNodes()).basicAdd(otherEnd, msgs);
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
				case MapPackage.COMPONENT_REF__URN_LINKS:
					return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
				case MapPackage.COMPONENT_REF__COMP_DEF:
					return basicSetCompDef(null, msgs);
				case MapPackage.COMPONENT_REF__CHILDREN:
					return ((InternalEList)getChildren()).basicRemove(otherEnd, msgs);
				case MapPackage.COMPONENT_REF__PARENT:
					return basicSetParent(null, msgs);
				case MapPackage.COMPONENT_REF__PATH_NODES:
					return ((InternalEList)getPathNodes()).basicRemove(otherEnd, msgs);
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
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MapPackage.COMPONENT_REF__URN_LINKS:
				return getUrnLinks();
			case MapPackage.COMPONENT_REF__ID:
				return getId();
			case MapPackage.COMPONENT_REF__NAME:
				return getName();
			case MapPackage.COMPONENT_REF__DESCRIPTION:
				return getDescription();
			case MapPackage.COMPONENT_REF__ROLE:
				return getRole();
			case MapPackage.COMPONENT_REF__REPLICATION_FACTOR:
				return new Integer(getReplicationFactor());
			case MapPackage.COMPONENT_REF__ANCHORED:
				return isAnchored() ? Boolean.TRUE : Boolean.FALSE;
			case MapPackage.COMPONENT_REF__FIXED:
				return isFixed() ? Boolean.TRUE : Boolean.FALSE;
			case MapPackage.COMPONENT_REF__X:
				return new Integer(getX());
			case MapPackage.COMPONENT_REF__Y:
				return new Integer(getY());
			case MapPackage.COMPONENT_REF__WIDTH:
				return new Integer(getWidth());
			case MapPackage.COMPONENT_REF__HEIGHT:
				return new Integer(getHeight());
			case MapPackage.COMPONENT_REF__LABEL_X:
				return new Integer(getLabelX());
			case MapPackage.COMPONENT_REF__LABELY:
				return new Integer(getLabely());
			case MapPackage.COMPONENT_REF__COMP_DEF:
				if (resolve) return getCompDef();
				return basicGetCompDef();
			case MapPackage.COMPONENT_REF__CHILDREN:
				return getChildren();
			case MapPackage.COMPONENT_REF__PARENT:
				if (resolve) return getParent();
				return basicGetParent();
			case MapPackage.COMPONENT_REF__PATH_NODES:
				return getPathNodes();
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
			case MapPackage.COMPONENT_REF__URN_LINKS:
				getUrnLinks().clear();
				getUrnLinks().addAll((Collection)newValue);
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
			case MapPackage.COMPONENT_REF__ROLE:
				setRole((String)newValue);
				return;
			case MapPackage.COMPONENT_REF__REPLICATION_FACTOR:
				setReplicationFactor(((Integer)newValue).intValue());
				return;
			case MapPackage.COMPONENT_REF__ANCHORED:
				setAnchored(((Boolean)newValue).booleanValue());
				return;
			case MapPackage.COMPONENT_REF__FIXED:
				setFixed(((Boolean)newValue).booleanValue());
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
			case MapPackage.COMPONENT_REF__LABEL_X:
				setLabelX(((Integer)newValue).intValue());
				return;
			case MapPackage.COMPONENT_REF__LABELY:
				setLabely(((Integer)newValue).intValue());
				return;
			case MapPackage.COMPONENT_REF__COMP_DEF:
				setCompDef((ComponentElement)newValue);
				return;
			case MapPackage.COMPONENT_REF__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection)newValue);
				return;
			case MapPackage.COMPONENT_REF__PARENT:
				setParent((ComponentRef)newValue);
				return;
			case MapPackage.COMPONENT_REF__PATH_NODES:
				getPathNodes().clear();
				getPathNodes().addAll((Collection)newValue);
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
			case MapPackage.COMPONENT_REF__URN_LINKS:
				getUrnLinks().clear();
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
			case MapPackage.COMPONENT_REF__ROLE:
				setRole(ROLE_EDEFAULT);
				return;
			case MapPackage.COMPONENT_REF__REPLICATION_FACTOR:
				setReplicationFactor(REPLICATION_FACTOR_EDEFAULT);
				return;
			case MapPackage.COMPONENT_REF__ANCHORED:
				setAnchored(ANCHORED_EDEFAULT);
				return;
			case MapPackage.COMPONENT_REF__FIXED:
				setFixed(FIXED_EDEFAULT);
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
			case MapPackage.COMPONENT_REF__LABEL_X:
				setLabelX(LABEL_X_EDEFAULT);
				return;
			case MapPackage.COMPONENT_REF__LABELY:
				setLabely(LABELY_EDEFAULT);
				return;
			case MapPackage.COMPONENT_REF__COMP_DEF:
				setCompDef((ComponentElement)null);
				return;
			case MapPackage.COMPONENT_REF__CHILDREN:
				getChildren().clear();
				return;
			case MapPackage.COMPONENT_REF__PARENT:
				setParent((ComponentRef)null);
				return;
			case MapPackage.COMPONENT_REF__PATH_NODES:
				getPathNodes().clear();
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
			case MapPackage.COMPONENT_REF__URN_LINKS:
				return urnLinks != null && !urnLinks.isEmpty();
			case MapPackage.COMPONENT_REF__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case MapPackage.COMPONENT_REF__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case MapPackage.COMPONENT_REF__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case MapPackage.COMPONENT_REF__ROLE:
				return ROLE_EDEFAULT == null ? role != null : !ROLE_EDEFAULT.equals(role);
			case MapPackage.COMPONENT_REF__REPLICATION_FACTOR:
				return replicationFactor != REPLICATION_FACTOR_EDEFAULT;
			case MapPackage.COMPONENT_REF__ANCHORED:
				return anchored != ANCHORED_EDEFAULT;
			case MapPackage.COMPONENT_REF__FIXED:
				return fixed != FIXED_EDEFAULT;
			case MapPackage.COMPONENT_REF__X:
				return x != X_EDEFAULT;
			case MapPackage.COMPONENT_REF__Y:
				return y != Y_EDEFAULT;
			case MapPackage.COMPONENT_REF__WIDTH:
				return width != WIDTH_EDEFAULT;
			case MapPackage.COMPONENT_REF__HEIGHT:
				return height != HEIGHT_EDEFAULT;
			case MapPackage.COMPONENT_REF__LABEL_X:
				return labelX != LABEL_X_EDEFAULT;
			case MapPackage.COMPONENT_REF__LABELY:
				return labely != LABELY_EDEFAULT;
			case MapPackage.COMPONENT_REF__COMP_DEF:
				return compDef != null;
			case MapPackage.COMPONENT_REF__CHILDREN:
				return children != null && !children.isEmpty();
			case MapPackage.COMPONENT_REF__PARENT:
				return parent != null;
			case MapPackage.COMPONENT_REF__PATH_NODES:
				return pathNodes != null && !pathNodes.isEmpty();
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
		result.append(" (role: ");
		result.append(role);
		result.append(", replicationFactor: ");
		result.append(replicationFactor);
		result.append(", anchored: ");
		result.append(anchored);
		result.append(", fixed: ");
		result.append(fixed);
		result.append(", x: ");
		result.append(x);
		result.append(", y: ");
		result.append(y);
		result.append(", width: ");
		result.append(width);
		result.append(", height: ");
		result.append(height);
		result.append(", labelX: ");
		result.append(labelX);
		result.append(", labely: ");
		result.append(labely);
		result.append(')');
		return result.toString();
	}

} //ComponentRefImpl
