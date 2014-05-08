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
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import ucm.performance.PassiveResource;
import ucm.performance.PerformancePackage;
import ucm.performance.ProcessingResource;
import urncore.Component;
import urncore.ComponentKind;
import urncore.ComponentType;
import urncore.IURNContainer;
import urncore.IURNContainerRef;
import urncore.URNdefinition;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link urncore.impl.ComponentImpl#getLineColor <em>Line Color</em>}</li>
 *   <li>{@link urncore.impl.ComponentImpl#getFillColor <em>Fill Color</em>}</li>
 *   <li>{@link urncore.impl.ComponentImpl#isFilled <em>Filled</em>}</li>
 *   <li>{@link urncore.impl.ComponentImpl#getContRefs <em>Cont Refs</em>}</li>
 *   <li>{@link urncore.impl.ComponentImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link urncore.impl.ComponentImpl#isProtected <em>Protected</em>}</li>
 *   <li>{@link urncore.impl.ComponentImpl#isSlot <em>Slot</em>}</li>
 *   <li>{@link urncore.impl.ComponentImpl#isContext <em>Context</em>}</li>
 *   <li>{@link urncore.impl.ComponentImpl#getType <em>Type</em>}</li>
 *   <li>{@link urncore.impl.ComponentImpl#getUrndefinition <em>Urndefinition</em>}</li>
 *   <li>{@link urncore.impl.ComponentImpl#getIncludedComponent <em>Included Component</em>}</li>
 *   <li>{@link urncore.impl.ComponentImpl#getIncludingComponent <em>Including Component</em>}</li>
 *   <li>{@link urncore.impl.ComponentImpl#getResource <em>Resource</em>}</li>
 *   <li>{@link urncore.impl.ComponentImpl#getHost <em>Host</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComponentImpl extends UCMmodelElementImpl implements Component {
    /**
	 * The default value of the '{@link #getLineColor() <em>Line Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineColor()
	 * @generated
	 * @ordered
	 */
	protected static final String LINE_COLOR_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getLineColor() <em>Line Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineColor()
	 * @generated
	 * @ordered
	 */
	protected String lineColor = LINE_COLOR_EDEFAULT;
	/**
	 * The default value of the '{@link #getFillColor() <em>Fill Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFillColor()
	 * @generated
	 * @ordered
	 */
	protected static final String FILL_COLOR_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getFillColor() <em>Fill Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFillColor()
	 * @generated
	 * @ordered
	 */
	protected String fillColor = FILL_COLOR_EDEFAULT;
	/**
	 * The default value of the '{@link #isFilled() <em>Filled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFilled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FILLED_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isFilled() <em>Filled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFilled()
	 * @generated
	 * @ordered
	 */
	protected boolean filled = FILLED_EDEFAULT;
	/**
	 * The cached value of the '{@link #getContRefs() <em>Cont Refs</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContRefs()
	 * @generated
	 * @ordered
	 */
	protected EList contRefs;
				/**
	 * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected static final ComponentKind KIND_EDEFAULT = ComponentKind.TEAM_LITERAL;
	/**
	 * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected ComponentKind kind = KIND_EDEFAULT;
	/**
	 * The default value of the '{@link #isProtected() <em>Protected</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isProtected()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PROTECTED_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isProtected() <em>Protected</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isProtected()
	 * @generated
	 * @ordered
	 */
	protected boolean protected_ = PROTECTED_EDEFAULT;
	/**
	 * The default value of the '{@link #isSlot() <em>Slot</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSlot()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SLOT_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isSlot() <em>Slot</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSlot()
	 * @generated
	 * @ordered
	 */
	protected boolean slot = SLOT_EDEFAULT;
				/**
	 * The default value of the '{@link #isContext() <em>Context</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isContext()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CONTEXT_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isContext() <em>Context</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isContext()
	 * @generated
	 * @ordered
	 */
	protected boolean context = CONTEXT_EDEFAULT;
				/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
    protected ComponentType type;

    /**
	 * The cached value of the '{@link #getIncludedComponent() <em>Included Component</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncludedComponent()
	 * @generated
	 * @ordered
	 */
	protected EList includedComponent;
				/**
	 * The cached value of the '{@link #getIncludingComponent() <em>Including Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncludingComponent()
	 * @generated
	 * @ordered
	 */
	protected Component includingComponent;
				/**
	 * The cached value of the '{@link #getResource() <em>Resource</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResource()
	 * @generated
	 * @ordered
	 */
	protected PassiveResource resource;
				/**
	 * The cached value of the '{@link #getHost() <em>Host</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHost()
	 * @generated
	 * @ordered
	 */
	protected ProcessingResource host;

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected ComponentImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return UrncorePackage.Literals.COMPONENT;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLineColor() {
		return lineColor;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLineColor(String newLineColor) {
		String oldLineColor = lineColor;
		lineColor = newLineColor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT__LINE_COLOR, oldLineColor, lineColor));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFillColor() {
		return fillColor;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFillColor(String newFillColor) {
		String oldFillColor = fillColor;
		fillColor = newFillColor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT__FILL_COLOR, oldFillColor, fillColor));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFilled() {
		return filled;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFilled(boolean newFilled) {
		boolean oldFilled = filled;
		filled = newFilled;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT__FILLED, oldFilled, filled));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getContRefs() {
		if (contRefs == null) {
			contRefs = new EObjectWithInverseResolvingEList(IURNContainerRef.class, this, UrncorePackage.COMPONENT__CONT_REFS, UrncorePackage.IURN_CONTAINER_REF__CONT_DEF);
		}
		return contRefs;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentKind getKind() {
		return kind;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKind(ComponentKind newKind) {
		ComponentKind oldKind = kind;
		kind = newKind == null ? KIND_EDEFAULT : newKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT__KIND, oldKind, kind));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isProtected() {
		return protected_;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProtected(boolean newProtected) {
		boolean oldProtected = protected_;
		protected_ = newProtected;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT__PROTECTED, oldProtected, protected_));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSlot() {
		return slot;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSlot(boolean newSlot) {
		boolean oldSlot = slot;
		slot = newSlot;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT__SLOT, oldSlot, slot));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isContext() {
		return context;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContext(boolean newContext) {
		boolean oldContext = context;
		context = newContext;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT__CONTEXT, oldContext, context));
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ComponentType getType() {
		if (type != null && type.eIsProxy()) {
			InternalEObject oldType = (InternalEObject)type;
			type = (ComponentType)eResolveProxy(oldType);
			if (type != oldType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UrncorePackage.COMPONENT__TYPE, oldType, type));
			}
		}
		return type;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ComponentType basicGetType() {
		return type;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetType(ComponentType newType, NotificationChain msgs) {
		ComponentType oldType = type;
		type = newType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT__TYPE, oldType, newType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setType(ComponentType newType) {
		if (newType != type) {
			NotificationChain msgs = null;
			if (type != null)
				msgs = ((InternalEObject)type).eInverseRemove(this, UrncorePackage.COMPONENT_TYPE__INSTANCES, ComponentType.class, msgs);
			if (newType != null)
				msgs = ((InternalEObject)newType).eInverseAdd(this, UrncorePackage.COMPONENT_TYPE__INSTANCES, ComponentType.class, msgs);
			msgs = basicSetType(newType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT__TYPE, newType, newType));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public URNdefinition getUrndefinition() {
		if (eContainerFeatureID() != UrncorePackage.COMPONENT__URNDEFINITION) return null;
		return (URNdefinition)eInternalContainer();
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUrndefinition(URNdefinition newUrndefinition, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newUrndefinition, UrncorePackage.COMPONENT__URNDEFINITION, msgs);
		return msgs;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUrndefinition(URNdefinition newUrndefinition) {
		if (newUrndefinition != eInternalContainer() || (eContainerFeatureID() != UrncorePackage.COMPONENT__URNDEFINITION && newUrndefinition != null)) {
			if (EcoreUtil.isAncestor(this, newUrndefinition))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newUrndefinition != null)
				msgs = ((InternalEObject)newUrndefinition).eInverseAdd(this, UrncorePackage.UR_NDEFINITION__COMPONENTS, URNdefinition.class, msgs);
			msgs = basicSetUrndefinition(newUrndefinition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT__URNDEFINITION, newUrndefinition, newUrndefinition));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getIncludedComponent() {
		if (includedComponent == null) {
			includedComponent = new EObjectWithInverseResolvingEList(Component.class, this, UrncorePackage.COMPONENT__INCLUDED_COMPONENT, UrncorePackage.COMPONENT__INCLUDING_COMPONENT);
		}
		return includedComponent;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Component getIncludingComponent() {
		if (includingComponent != null && includingComponent.eIsProxy()) {
			InternalEObject oldIncludingComponent = (InternalEObject)includingComponent;
			includingComponent = (Component)eResolveProxy(oldIncludingComponent);
			if (includingComponent != oldIncludingComponent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UrncorePackage.COMPONENT__INCLUDING_COMPONENT, oldIncludingComponent, includingComponent));
			}
		}
		return includingComponent;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Component basicGetIncludingComponent() {
		return includingComponent;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIncludingComponent(Component newIncludingComponent, NotificationChain msgs) {
		Component oldIncludingComponent = includingComponent;
		includingComponent = newIncludingComponent;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT__INCLUDING_COMPONENT, oldIncludingComponent, newIncludingComponent);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIncludingComponent(Component newIncludingComponent) {
		if (newIncludingComponent != includingComponent) {
			NotificationChain msgs = null;
			if (includingComponent != null)
				msgs = ((InternalEObject)includingComponent).eInverseRemove(this, UrncorePackage.COMPONENT__INCLUDED_COMPONENT, Component.class, msgs);
			if (newIncludingComponent != null)
				msgs = ((InternalEObject)newIncludingComponent).eInverseAdd(this, UrncorePackage.COMPONENT__INCLUDED_COMPONENT, Component.class, msgs);
			msgs = basicSetIncludingComponent(newIncludingComponent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT__INCLUDING_COMPONENT, newIncludingComponent, newIncludingComponent));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PassiveResource getResource() {
		if (resource != null && resource.eIsProxy()) {
			InternalEObject oldResource = (InternalEObject)resource;
			resource = (PassiveResource)eResolveProxy(oldResource);
			if (resource != oldResource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UrncorePackage.COMPONENT__RESOURCE, oldResource, resource));
			}
		}
		return resource;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PassiveResource basicGetResource() {
		return resource;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResource(PassiveResource newResource, NotificationChain msgs) {
		PassiveResource oldResource = resource;
		resource = newResource;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT__RESOURCE, oldResource, newResource);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResource(PassiveResource newResource) {
		if (newResource != resource) {
			NotificationChain msgs = null;
			if (resource != null)
				msgs = ((InternalEObject)resource).eInverseRemove(this, PerformancePackage.PASSIVE_RESOURCE__COMPONENT, PassiveResource.class, msgs);
			if (newResource != null)
				msgs = ((InternalEObject)newResource).eInverseAdd(this, PerformancePackage.PASSIVE_RESOURCE__COMPONENT, PassiveResource.class, msgs);
			msgs = basicSetResource(newResource, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT__RESOURCE, newResource, newResource));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessingResource getHost() {
		if (host != null && host.eIsProxy()) {
			InternalEObject oldHost = (InternalEObject)host;
			host = (ProcessingResource)eResolveProxy(oldHost);
			if (host != oldHost) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UrncorePackage.COMPONENT__HOST, oldHost, host));
			}
		}
		return host;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessingResource basicGetHost() {
		return host;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHost(ProcessingResource newHost, NotificationChain msgs) {
		ProcessingResource oldHost = host;
		host = newHost;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT__HOST, oldHost, newHost);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHost(ProcessingResource newHost) {
		if (newHost != host) {
			NotificationChain msgs = null;
			if (host != null)
				msgs = ((InternalEObject)host).eInverseRemove(this, PerformancePackage.PROCESSING_RESOURCE__COMPONENTS, ProcessingResource.class, msgs);
			if (newHost != null)
				msgs = ((InternalEObject)newHost).eInverseAdd(this, PerformancePackage.PROCESSING_RESOURCE__COMPONENTS, ProcessingResource.class, msgs);
			msgs = basicSetHost(newHost, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT__HOST, newHost, newHost));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UrncorePackage.COMPONENT__CONT_REFS:
				return ((InternalEList)getContRefs()).basicAdd(otherEnd, msgs);
			case UrncorePackage.COMPONENT__TYPE:
				if (type != null)
					msgs = ((InternalEObject)type).eInverseRemove(this, UrncorePackage.COMPONENT_TYPE__INSTANCES, ComponentType.class, msgs);
				return basicSetType((ComponentType)otherEnd, msgs);
			case UrncorePackage.COMPONENT__URNDEFINITION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetUrndefinition((URNdefinition)otherEnd, msgs);
			case UrncorePackage.COMPONENT__INCLUDED_COMPONENT:
				return ((InternalEList)getIncludedComponent()).basicAdd(otherEnd, msgs);
			case UrncorePackage.COMPONENT__INCLUDING_COMPONENT:
				if (includingComponent != null)
					msgs = ((InternalEObject)includingComponent).eInverseRemove(this, UrncorePackage.COMPONENT__INCLUDED_COMPONENT, Component.class, msgs);
				return basicSetIncludingComponent((Component)otherEnd, msgs);
			case UrncorePackage.COMPONENT__RESOURCE:
				if (resource != null)
					msgs = ((InternalEObject)resource).eInverseRemove(this, PerformancePackage.PASSIVE_RESOURCE__COMPONENT, PassiveResource.class, msgs);
				return basicSetResource((PassiveResource)otherEnd, msgs);
			case UrncorePackage.COMPONENT__HOST:
				if (host != null)
					msgs = ((InternalEObject)host).eInverseRemove(this, PerformancePackage.PROCESSING_RESOURCE__COMPONENTS, ProcessingResource.class, msgs);
				return basicSetHost((ProcessingResource)otherEnd, msgs);
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
			case UrncorePackage.COMPONENT__CONT_REFS:
				return ((InternalEList)getContRefs()).basicRemove(otherEnd, msgs);
			case UrncorePackage.COMPONENT__TYPE:
				return basicSetType(null, msgs);
			case UrncorePackage.COMPONENT__URNDEFINITION:
				return basicSetUrndefinition(null, msgs);
			case UrncorePackage.COMPONENT__INCLUDED_COMPONENT:
				return ((InternalEList)getIncludedComponent()).basicRemove(otherEnd, msgs);
			case UrncorePackage.COMPONENT__INCLUDING_COMPONENT:
				return basicSetIncludingComponent(null, msgs);
			case UrncorePackage.COMPONENT__RESOURCE:
				return basicSetResource(null, msgs);
			case UrncorePackage.COMPONENT__HOST:
				return basicSetHost(null, msgs);
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
			case UrncorePackage.COMPONENT__URNDEFINITION:
				return eInternalContainer().eInverseRemove(this, UrncorePackage.UR_NDEFINITION__COMPONENTS, URNdefinition.class, msgs);
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
			case UrncorePackage.COMPONENT__LINE_COLOR:
				return getLineColor();
			case UrncorePackage.COMPONENT__FILL_COLOR:
				return getFillColor();
			case UrncorePackage.COMPONENT__FILLED:
				return isFilled() ? Boolean.TRUE : Boolean.FALSE;
			case UrncorePackage.COMPONENT__CONT_REFS:
				return getContRefs();
			case UrncorePackage.COMPONENT__KIND:
				return getKind();
			case UrncorePackage.COMPONENT__PROTECTED:
				return isProtected() ? Boolean.TRUE : Boolean.FALSE;
			case UrncorePackage.COMPONENT__SLOT:
				return isSlot() ? Boolean.TRUE : Boolean.FALSE;
			case UrncorePackage.COMPONENT__CONTEXT:
				return isContext() ? Boolean.TRUE : Boolean.FALSE;
			case UrncorePackage.COMPONENT__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case UrncorePackage.COMPONENT__URNDEFINITION:
				return getUrndefinition();
			case UrncorePackage.COMPONENT__INCLUDED_COMPONENT:
				return getIncludedComponent();
			case UrncorePackage.COMPONENT__INCLUDING_COMPONENT:
				if (resolve) return getIncludingComponent();
				return basicGetIncludingComponent();
			case UrncorePackage.COMPONENT__RESOURCE:
				if (resolve) return getResource();
				return basicGetResource();
			case UrncorePackage.COMPONENT__HOST:
				if (resolve) return getHost();
				return basicGetHost();
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
			case UrncorePackage.COMPONENT__LINE_COLOR:
				setLineColor((String)newValue);
				return;
			case UrncorePackage.COMPONENT__FILL_COLOR:
				setFillColor((String)newValue);
				return;
			case UrncorePackage.COMPONENT__FILLED:
				setFilled(((Boolean)newValue).booleanValue());
				return;
			case UrncorePackage.COMPONENT__CONT_REFS:
				getContRefs().clear();
				getContRefs().addAll((Collection)newValue);
				return;
			case UrncorePackage.COMPONENT__KIND:
				setKind((ComponentKind)newValue);
				return;
			case UrncorePackage.COMPONENT__PROTECTED:
				setProtected(((Boolean)newValue).booleanValue());
				return;
			case UrncorePackage.COMPONENT__SLOT:
				setSlot(((Boolean)newValue).booleanValue());
				return;
			case UrncorePackage.COMPONENT__CONTEXT:
				setContext(((Boolean)newValue).booleanValue());
				return;
			case UrncorePackage.COMPONENT__TYPE:
				setType((ComponentType)newValue);
				return;
			case UrncorePackage.COMPONENT__URNDEFINITION:
				setUrndefinition((URNdefinition)newValue);
				return;
			case UrncorePackage.COMPONENT__INCLUDED_COMPONENT:
				getIncludedComponent().clear();
				getIncludedComponent().addAll((Collection)newValue);
				return;
			case UrncorePackage.COMPONENT__INCLUDING_COMPONENT:
				setIncludingComponent((Component)newValue);
				return;
			case UrncorePackage.COMPONENT__RESOURCE:
				setResource((PassiveResource)newValue);
				return;
			case UrncorePackage.COMPONENT__HOST:
				setHost((ProcessingResource)newValue);
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
			case UrncorePackage.COMPONENT__LINE_COLOR:
				setLineColor(LINE_COLOR_EDEFAULT);
				return;
			case UrncorePackage.COMPONENT__FILL_COLOR:
				setFillColor(FILL_COLOR_EDEFAULT);
				return;
			case UrncorePackage.COMPONENT__FILLED:
				setFilled(FILLED_EDEFAULT);
				return;
			case UrncorePackage.COMPONENT__CONT_REFS:
				getContRefs().clear();
				return;
			case UrncorePackage.COMPONENT__KIND:
				setKind(KIND_EDEFAULT);
				return;
			case UrncorePackage.COMPONENT__PROTECTED:
				setProtected(PROTECTED_EDEFAULT);
				return;
			case UrncorePackage.COMPONENT__SLOT:
				setSlot(SLOT_EDEFAULT);
				return;
			case UrncorePackage.COMPONENT__CONTEXT:
				setContext(CONTEXT_EDEFAULT);
				return;
			case UrncorePackage.COMPONENT__TYPE:
				setType((ComponentType)null);
				return;
			case UrncorePackage.COMPONENT__URNDEFINITION:
				setUrndefinition((URNdefinition)null);
				return;
			case UrncorePackage.COMPONENT__INCLUDED_COMPONENT:
				getIncludedComponent().clear();
				return;
			case UrncorePackage.COMPONENT__INCLUDING_COMPONENT:
				setIncludingComponent((Component)null);
				return;
			case UrncorePackage.COMPONENT__RESOURCE:
				setResource((PassiveResource)null);
				return;
			case UrncorePackage.COMPONENT__HOST:
				setHost((ProcessingResource)null);
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
			case UrncorePackage.COMPONENT__LINE_COLOR:
				return LINE_COLOR_EDEFAULT == null ? lineColor != null : !LINE_COLOR_EDEFAULT.equals(lineColor);
			case UrncorePackage.COMPONENT__FILL_COLOR:
				return FILL_COLOR_EDEFAULT == null ? fillColor != null : !FILL_COLOR_EDEFAULT.equals(fillColor);
			case UrncorePackage.COMPONENT__FILLED:
				return filled != FILLED_EDEFAULT;
			case UrncorePackage.COMPONENT__CONT_REFS:
				return contRefs != null && !contRefs.isEmpty();
			case UrncorePackage.COMPONENT__KIND:
				return kind != KIND_EDEFAULT;
			case UrncorePackage.COMPONENT__PROTECTED:
				return protected_ != PROTECTED_EDEFAULT;
			case UrncorePackage.COMPONENT__SLOT:
				return slot != SLOT_EDEFAULT;
			case UrncorePackage.COMPONENT__CONTEXT:
				return context != CONTEXT_EDEFAULT;
			case UrncorePackage.COMPONENT__TYPE:
				return type != null;
			case UrncorePackage.COMPONENT__URNDEFINITION:
				return getUrndefinition() != null;
			case UrncorePackage.COMPONENT__INCLUDED_COMPONENT:
				return includedComponent != null && !includedComponent.isEmpty();
			case UrncorePackage.COMPONENT__INCLUDING_COMPONENT:
				return includingComponent != null;
			case UrncorePackage.COMPONENT__RESOURCE:
				return resource != null;
			case UrncorePackage.COMPONENT__HOST:
				return host != null;
		}
		return super.eIsSet(featureID);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
		if (baseClass == IURNContainer.class) {
			switch (derivedFeatureID) {
				case UrncorePackage.COMPONENT__LINE_COLOR: return UrncorePackage.IURN_CONTAINER__LINE_COLOR;
				case UrncorePackage.COMPONENT__FILL_COLOR: return UrncorePackage.IURN_CONTAINER__FILL_COLOR;
				case UrncorePackage.COMPONENT__FILLED: return UrncorePackage.IURN_CONTAINER__FILLED;
				case UrncorePackage.COMPONENT__CONT_REFS: return UrncorePackage.IURN_CONTAINER__CONT_REFS;
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
		if (baseClass == IURNContainer.class) {
			switch (baseFeatureID) {
				case UrncorePackage.IURN_CONTAINER__LINE_COLOR: return UrncorePackage.COMPONENT__LINE_COLOR;
				case UrncorePackage.IURN_CONTAINER__FILL_COLOR: return UrncorePackage.COMPONENT__FILL_COLOR;
				case UrncorePackage.IURN_CONTAINER__FILLED: return UrncorePackage.COMPONENT__FILLED;
				case UrncorePackage.IURN_CONTAINER__CONT_REFS: return UrncorePackage.COMPONENT__CONT_REFS;
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
		result.append(" (lineColor: ");
		result.append(lineColor);
		result.append(", fillColor: ");
		result.append(fillColor);
		result.append(", filled: ");
		result.append(filled);
		result.append(", kind: ");
		result.append(kind);
		result.append(", protected: ");
		result.append(protected_);
		result.append(", slot: ");
		result.append(slot);
		result.append(", context: ");
		result.append(context);
		result.append(')');
		return result.toString();
	}

} //ComponentImpl
