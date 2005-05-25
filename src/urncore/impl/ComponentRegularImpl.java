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
import org.eclipse.emf.ecore.util.InternalEList;

import ucm.performance.PassiveResource;
import ucm.performance.PerformancePackage;
import ucm.performance.ProcessingResource;

import urncore.ComponentElement;
import urncore.ComponentKind;
import urncore.ComponentRegular;
import urncore.URNdefinition;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component Regular</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link urncore.impl.ComponentRegularImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link urncore.impl.ComponentRegularImpl#isProtected <em>Protected</em>}</li>
 *   <li>{@link urncore.impl.ComponentRegularImpl#isSlot <em>Slot</em>}</li>
 *   <li>{@link urncore.impl.ComponentRegularImpl#getIncludedComponent <em>Included Component</em>}</li>
 *   <li>{@link urncore.impl.ComponentRegularImpl#getHost <em>Host</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ComponentRegularImpl extends ComponentElementImpl implements ComponentRegular {
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
	 * The cached value of the '{@link #getIncludedComponent() <em>Included Component</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncludedComponent()
	 * @generated
	 * @ordered
	 */
	protected EList includedComponent = null;

	/**
	 * The cached value of the '{@link #getHost() <em>Host</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHost()
	 * @generated
	 * @ordered
	 */
	protected ProcessingResource host = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentRegularImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return UrncorePackage.eINSTANCE.getComponentRegular();
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
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT_REGULAR__KIND, oldKind, kind));
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
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT_REGULAR__PROTECTED, oldProtected, protected_));
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
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT_REGULAR__SLOT, oldSlot, slot));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getIncludedComponent() {
		if (includedComponent == null) {
			includedComponent = new EObjectWithInverseResolvingEList(ComponentElement.class, this, UrncorePackage.COMPONENT_REGULAR__INCLUDED_COMPONENT, UrncorePackage.COMPONENT_ELEMENT__INCLUDING_COMPONENT);
		}
		return includedComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessingResource getHost() {
		if (host != null && host.eIsProxy()) {
			ProcessingResource oldHost = host;
			host = (ProcessingResource)eResolveProxy((InternalEObject)host);
			if (host != oldHost) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UrncorePackage.COMPONENT_REGULAR__HOST, oldHost, host));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT_REGULAR__HOST, oldHost, newHost);
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
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT_REGULAR__HOST, newHost, newHost));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case UrncorePackage.COMPONENT_REGULAR__URN_LINKS:
					return ((InternalEList)getUrnLinks()).basicAdd(otherEnd, msgs);
				case UrncorePackage.COMPONENT_REGULAR__URNDEFINITION:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, UrncorePackage.COMPONENT_REGULAR__URNDEFINITION, msgs);
				case UrncorePackage.COMPONENT_REGULAR__INCLUDING_COMPONENT:
					if (includingComponent != null)
						msgs = ((InternalEObject)includingComponent).eInverseRemove(this, UrncorePackage.COMPONENT_REGULAR__INCLUDED_COMPONENT, ComponentRegular.class, msgs);
					return basicSetIncludingComponent((ComponentRegular)otherEnd, msgs);
				case UrncorePackage.COMPONENT_REGULAR__RESOURCE:
					if (resource != null)
						msgs = ((InternalEObject)resource).eInverseRemove(this, PerformancePackage.PASSIVE_RESOURCE__COMPONENT, PassiveResource.class, msgs);
					return basicSetResource((PassiveResource)otherEnd, msgs);
				case UrncorePackage.COMPONENT_REGULAR__COMP_REFS:
					return ((InternalEList)getCompRefs()).basicAdd(otherEnd, msgs);
				case UrncorePackage.COMPONENT_REGULAR__INCLUDED_COMPONENT:
					return ((InternalEList)getIncludedComponent()).basicAdd(otherEnd, msgs);
				case UrncorePackage.COMPONENT_REGULAR__HOST:
					if (host != null)
						msgs = ((InternalEObject)host).eInverseRemove(this, PerformancePackage.PROCESSING_RESOURCE__COMPONENTS, ProcessingResource.class, msgs);
					return basicSetHost((ProcessingResource)otherEnd, msgs);
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
				case UrncorePackage.COMPONENT_REGULAR__URN_LINKS:
					return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
				case UrncorePackage.COMPONENT_REGULAR__URNDEFINITION:
					return eBasicSetContainer(null, UrncorePackage.COMPONENT_REGULAR__URNDEFINITION, msgs);
				case UrncorePackage.COMPONENT_REGULAR__INCLUDING_COMPONENT:
					return basicSetIncludingComponent(null, msgs);
				case UrncorePackage.COMPONENT_REGULAR__RESOURCE:
					return basicSetResource(null, msgs);
				case UrncorePackage.COMPONENT_REGULAR__COMP_REFS:
					return ((InternalEList)getCompRefs()).basicRemove(otherEnd, msgs);
				case UrncorePackage.COMPONENT_REGULAR__INCLUDED_COMPONENT:
					return ((InternalEList)getIncludedComponent()).basicRemove(otherEnd, msgs);
				case UrncorePackage.COMPONENT_REGULAR__HOST:
					return basicSetHost(null, msgs);
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
				case UrncorePackage.COMPONENT_REGULAR__URNDEFINITION:
					return ((InternalEObject)eContainer).eInverseRemove(this, UrncorePackage.UR_NDEFINITION__COMPONENTS, URNdefinition.class, msgs);
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
			case UrncorePackage.COMPONENT_REGULAR__URN_LINKS:
				return getUrnLinks();
			case UrncorePackage.COMPONENT_REGULAR__ID:
				return getId();
			case UrncorePackage.COMPONENT_REGULAR__NAME:
				return getName();
			case UrncorePackage.COMPONENT_REGULAR__DESCRIPTION:
				return getDescription();
			case UrncorePackage.COMPONENT_REGULAR__LINE_COLOR:
				return getLineColor();
			case UrncorePackage.COMPONENT_REGULAR__FILL_COLOR:
				return getFillColor();
			case UrncorePackage.COMPONENT_REGULAR__FILLED:
				return isFilled() ? Boolean.TRUE : Boolean.FALSE;
			case UrncorePackage.COMPONENT_REGULAR__URNDEFINITION:
				return getUrndefinition();
			case UrncorePackage.COMPONENT_REGULAR__INCLUDING_COMPONENT:
				if (resolve) return getIncludingComponent();
				return basicGetIncludingComponent();
			case UrncorePackage.COMPONENT_REGULAR__RESOURCE:
				if (resolve) return getResource();
				return basicGetResource();
			case UrncorePackage.COMPONENT_REGULAR__COMP_REFS:
				return getCompRefs();
			case UrncorePackage.COMPONENT_REGULAR__KIND:
				return getKind();
			case UrncorePackage.COMPONENT_REGULAR__PROTECTED:
				return isProtected() ? Boolean.TRUE : Boolean.FALSE;
			case UrncorePackage.COMPONENT_REGULAR__SLOT:
				return isSlot() ? Boolean.TRUE : Boolean.FALSE;
			case UrncorePackage.COMPONENT_REGULAR__INCLUDED_COMPONENT:
				return getIncludedComponent();
			case UrncorePackage.COMPONENT_REGULAR__HOST:
				if (resolve) return getHost();
				return basicGetHost();
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
			case UrncorePackage.COMPONENT_REGULAR__URN_LINKS:
				getUrnLinks().clear();
				getUrnLinks().addAll((Collection)newValue);
				return;
			case UrncorePackage.COMPONENT_REGULAR__ID:
				setId((String)newValue);
				return;
			case UrncorePackage.COMPONENT_REGULAR__NAME:
				setName((String)newValue);
				return;
			case UrncorePackage.COMPONENT_REGULAR__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case UrncorePackage.COMPONENT_REGULAR__LINE_COLOR:
				setLineColor((String)newValue);
				return;
			case UrncorePackage.COMPONENT_REGULAR__FILL_COLOR:
				setFillColor((String)newValue);
				return;
			case UrncorePackage.COMPONENT_REGULAR__FILLED:
				setFilled(((Boolean)newValue).booleanValue());
				return;
			case UrncorePackage.COMPONENT_REGULAR__URNDEFINITION:
				setUrndefinition((URNdefinition)newValue);
				return;
			case UrncorePackage.COMPONENT_REGULAR__INCLUDING_COMPONENT:
				setIncludingComponent((ComponentRegular)newValue);
				return;
			case UrncorePackage.COMPONENT_REGULAR__RESOURCE:
				setResource((PassiveResource)newValue);
				return;
			case UrncorePackage.COMPONENT_REGULAR__COMP_REFS:
				getCompRefs().clear();
				getCompRefs().addAll((Collection)newValue);
				return;
			case UrncorePackage.COMPONENT_REGULAR__KIND:
				setKind((ComponentKind)newValue);
				return;
			case UrncorePackage.COMPONENT_REGULAR__PROTECTED:
				setProtected(((Boolean)newValue).booleanValue());
				return;
			case UrncorePackage.COMPONENT_REGULAR__SLOT:
				setSlot(((Boolean)newValue).booleanValue());
				return;
			case UrncorePackage.COMPONENT_REGULAR__INCLUDED_COMPONENT:
				getIncludedComponent().clear();
				getIncludedComponent().addAll((Collection)newValue);
				return;
			case UrncorePackage.COMPONENT_REGULAR__HOST:
				setHost((ProcessingResource)newValue);
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
			case UrncorePackage.COMPONENT_REGULAR__URN_LINKS:
				getUrnLinks().clear();
				return;
			case UrncorePackage.COMPONENT_REGULAR__ID:
				setId(ID_EDEFAULT);
				return;
			case UrncorePackage.COMPONENT_REGULAR__NAME:
				setName(NAME_EDEFAULT);
				return;
			case UrncorePackage.COMPONENT_REGULAR__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case UrncorePackage.COMPONENT_REGULAR__LINE_COLOR:
				setLineColor(LINE_COLOR_EDEFAULT);
				return;
			case UrncorePackage.COMPONENT_REGULAR__FILL_COLOR:
				setFillColor(FILL_COLOR_EDEFAULT);
				return;
			case UrncorePackage.COMPONENT_REGULAR__FILLED:
				setFilled(FILLED_EDEFAULT);
				return;
			case UrncorePackage.COMPONENT_REGULAR__URNDEFINITION:
				setUrndefinition((URNdefinition)null);
				return;
			case UrncorePackage.COMPONENT_REGULAR__INCLUDING_COMPONENT:
				setIncludingComponent((ComponentRegular)null);
				return;
			case UrncorePackage.COMPONENT_REGULAR__RESOURCE:
				setResource((PassiveResource)null);
				return;
			case UrncorePackage.COMPONENT_REGULAR__COMP_REFS:
				getCompRefs().clear();
				return;
			case UrncorePackage.COMPONENT_REGULAR__KIND:
				setKind(KIND_EDEFAULT);
				return;
			case UrncorePackage.COMPONENT_REGULAR__PROTECTED:
				setProtected(PROTECTED_EDEFAULT);
				return;
			case UrncorePackage.COMPONENT_REGULAR__SLOT:
				setSlot(SLOT_EDEFAULT);
				return;
			case UrncorePackage.COMPONENT_REGULAR__INCLUDED_COMPONENT:
				getIncludedComponent().clear();
				return;
			case UrncorePackage.COMPONENT_REGULAR__HOST:
				setHost((ProcessingResource)null);
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
			case UrncorePackage.COMPONENT_REGULAR__URN_LINKS:
				return urnLinks != null && !urnLinks.isEmpty();
			case UrncorePackage.COMPONENT_REGULAR__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case UrncorePackage.COMPONENT_REGULAR__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case UrncorePackage.COMPONENT_REGULAR__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case UrncorePackage.COMPONENT_REGULAR__LINE_COLOR:
				return LINE_COLOR_EDEFAULT == null ? lineColor != null : !LINE_COLOR_EDEFAULT.equals(lineColor);
			case UrncorePackage.COMPONENT_REGULAR__FILL_COLOR:
				return FILL_COLOR_EDEFAULT == null ? fillColor != null : !FILL_COLOR_EDEFAULT.equals(fillColor);
			case UrncorePackage.COMPONENT_REGULAR__FILLED:
				return filled != FILLED_EDEFAULT;
			case UrncorePackage.COMPONENT_REGULAR__URNDEFINITION:
				return getUrndefinition() != null;
			case UrncorePackage.COMPONENT_REGULAR__INCLUDING_COMPONENT:
				return includingComponent != null;
			case UrncorePackage.COMPONENT_REGULAR__RESOURCE:
				return resource != null;
			case UrncorePackage.COMPONENT_REGULAR__COMP_REFS:
				return compRefs != null && !compRefs.isEmpty();
			case UrncorePackage.COMPONENT_REGULAR__KIND:
				return kind != KIND_EDEFAULT;
			case UrncorePackage.COMPONENT_REGULAR__PROTECTED:
				return protected_ != PROTECTED_EDEFAULT;
			case UrncorePackage.COMPONENT_REGULAR__SLOT:
				return slot != SLOT_EDEFAULT;
			case UrncorePackage.COMPONENT_REGULAR__INCLUDED_COMPONENT:
				return includedComponent != null && !includedComponent.isEmpty();
			case UrncorePackage.COMPONENT_REGULAR__HOST:
				return host != null;
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
		result.append(" (kind: ");
		result.append(kind);
		result.append(", protected: ");
		result.append(protected_);
		result.append(", slot: ");
		result.append(slot);
		result.append(')');
		return result.toString();
	}

} //ComponentRegularImpl
