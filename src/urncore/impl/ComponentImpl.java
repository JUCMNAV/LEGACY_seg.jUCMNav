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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.InternalEList;

import ucm.performance.PassiveResource;
import ucm.performance.PerformancePackage;
import ucm.performance.ProcessingResource;

import urncore.Component;
import urncore.ComponentKind;
import urncore.ComponentRegular;
import urncore.ComponentType;
import urncore.URNdefinition;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link urncore.impl.ComponentImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComponentImpl extends ComponentRegularImpl implements Component {
	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected ComponentType type = null;

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
		return UrncorePackage.eINSTANCE.getComponent();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentType getType() {
		if (type != null && type.eIsProxy()) {
			ComponentType oldType = type;
			type = (ComponentType)eResolveProxy((InternalEObject)type);
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
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case UrncorePackage.COMPONENT__URN_LINKS:
					return ((InternalEList)getUrnLinks()).basicAdd(otherEnd, msgs);
				case UrncorePackage.COMPONENT__URNDEFINITION:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, UrncorePackage.COMPONENT__URNDEFINITION, msgs);
				case UrncorePackage.COMPONENT__INCLUDING_COMPONENT:
					if (includingComponent != null)
						msgs = ((InternalEObject)includingComponent).eInverseRemove(this, UrncorePackage.COMPONENT_REGULAR__INCLUDED_COMPONENT, ComponentRegular.class, msgs);
					return basicSetIncludingComponent((ComponentRegular)otherEnd, msgs);
				case UrncorePackage.COMPONENT__RESOURCE:
					if (resource != null)
						msgs = ((InternalEObject)resource).eInverseRemove(this, PerformancePackage.PASSIVE_RESOURCE__COMPONENT, PassiveResource.class, msgs);
					return basicSetResource((PassiveResource)otherEnd, msgs);
				case UrncorePackage.COMPONENT__COMP_REFS:
					return ((InternalEList)getCompRefs()).basicAdd(otherEnd, msgs);
				case UrncorePackage.COMPONENT__INCLUDED_COMPONENT:
					return ((InternalEList)getIncludedComponent()).basicAdd(otherEnd, msgs);
				case UrncorePackage.COMPONENT__HOST:
					if (host != null)
						msgs = ((InternalEObject)host).eInverseRemove(this, PerformancePackage.PROCESSING_RESOURCE__COMPONENTS, ProcessingResource.class, msgs);
					return basicSetHost((ProcessingResource)otherEnd, msgs);
				case UrncorePackage.COMPONENT__TYPE:
					if (type != null)
						msgs = ((InternalEObject)type).eInverseRemove(this, UrncorePackage.COMPONENT_TYPE__INSTANCES, ComponentType.class, msgs);
					return basicSetType((ComponentType)otherEnd, msgs);
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
				case UrncorePackage.COMPONENT__URN_LINKS:
					return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
				case UrncorePackage.COMPONENT__URNDEFINITION:
					return eBasicSetContainer(null, UrncorePackage.COMPONENT__URNDEFINITION, msgs);
				case UrncorePackage.COMPONENT__INCLUDING_COMPONENT:
					return basicSetIncludingComponent(null, msgs);
				case UrncorePackage.COMPONENT__RESOURCE:
					return basicSetResource(null, msgs);
				case UrncorePackage.COMPONENT__COMP_REFS:
					return ((InternalEList)getCompRefs()).basicRemove(otherEnd, msgs);
				case UrncorePackage.COMPONENT__INCLUDED_COMPONENT:
					return ((InternalEList)getIncludedComponent()).basicRemove(otherEnd, msgs);
				case UrncorePackage.COMPONENT__HOST:
					return basicSetHost(null, msgs);
				case UrncorePackage.COMPONENT__TYPE:
					return basicSetType(null, msgs);
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
				case UrncorePackage.COMPONENT__URNDEFINITION:
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
			case UrncorePackage.COMPONENT__URN_LINKS:
				return getUrnLinks();
			case UrncorePackage.COMPONENT__ID:
				return getId();
			case UrncorePackage.COMPONENT__NAME:
				return getName();
			case UrncorePackage.COMPONENT__DESCRIPTION:
				return getDescription();
			case UrncorePackage.COMPONENT__LINE_COLOR:
				return getLineColor();
			case UrncorePackage.COMPONENT__FILL_COLOR:
				return getFillColor();
			case UrncorePackage.COMPONENT__FILLED:
				return isFilled() ? Boolean.TRUE : Boolean.FALSE;
			case UrncorePackage.COMPONENT__URNDEFINITION:
				return getUrndefinition();
			case UrncorePackage.COMPONENT__INCLUDING_COMPONENT:
				if (resolve) return getIncludingComponent();
				return basicGetIncludingComponent();
			case UrncorePackage.COMPONENT__RESOURCE:
				if (resolve) return getResource();
				return basicGetResource();
			case UrncorePackage.COMPONENT__COMP_REFS:
				return getCompRefs();
			case UrncorePackage.COMPONENT__KIND:
				return getKind();
			case UrncorePackage.COMPONENT__PROTECTED:
				return isProtected() ? Boolean.TRUE : Boolean.FALSE;
			case UrncorePackage.COMPONENT__SLOT:
				return isSlot() ? Boolean.TRUE : Boolean.FALSE;
			case UrncorePackage.COMPONENT__INCLUDED_COMPONENT:
				return getIncludedComponent();
			case UrncorePackage.COMPONENT__HOST:
				if (resolve) return getHost();
				return basicGetHost();
			case UrncorePackage.COMPONENT__TYPE:
				if (resolve) return getType();
				return basicGetType();
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
			case UrncorePackage.COMPONENT__URN_LINKS:
				getUrnLinks().clear();
				getUrnLinks().addAll((Collection)newValue);
				return;
			case UrncorePackage.COMPONENT__ID:
				setId((String)newValue);
				return;
			case UrncorePackage.COMPONENT__NAME:
				setName((String)newValue);
				return;
			case UrncorePackage.COMPONENT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case UrncorePackage.COMPONENT__LINE_COLOR:
				setLineColor((String)newValue);
				return;
			case UrncorePackage.COMPONENT__FILL_COLOR:
				setFillColor((String)newValue);
				return;
			case UrncorePackage.COMPONENT__FILLED:
				setFilled(((Boolean)newValue).booleanValue());
				return;
			case UrncorePackage.COMPONENT__URNDEFINITION:
				setUrndefinition((URNdefinition)newValue);
				return;
			case UrncorePackage.COMPONENT__INCLUDING_COMPONENT:
				setIncludingComponent((ComponentRegular)newValue);
				return;
			case UrncorePackage.COMPONENT__RESOURCE:
				setResource((PassiveResource)newValue);
				return;
			case UrncorePackage.COMPONENT__COMP_REFS:
				getCompRefs().clear();
				getCompRefs().addAll((Collection)newValue);
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
			case UrncorePackage.COMPONENT__INCLUDED_COMPONENT:
				getIncludedComponent().clear();
				getIncludedComponent().addAll((Collection)newValue);
				return;
			case UrncorePackage.COMPONENT__HOST:
				setHost((ProcessingResource)newValue);
				return;
			case UrncorePackage.COMPONENT__TYPE:
				setType((ComponentType)newValue);
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
			case UrncorePackage.COMPONENT__URN_LINKS:
				getUrnLinks().clear();
				return;
			case UrncorePackage.COMPONENT__ID:
				setId(ID_EDEFAULT);
				return;
			case UrncorePackage.COMPONENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case UrncorePackage.COMPONENT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case UrncorePackage.COMPONENT__LINE_COLOR:
				setLineColor(LINE_COLOR_EDEFAULT);
				return;
			case UrncorePackage.COMPONENT__FILL_COLOR:
				setFillColor(FILL_COLOR_EDEFAULT);
				return;
			case UrncorePackage.COMPONENT__FILLED:
				setFilled(FILLED_EDEFAULT);
				return;
			case UrncorePackage.COMPONENT__URNDEFINITION:
				setUrndefinition((URNdefinition)null);
				return;
			case UrncorePackage.COMPONENT__INCLUDING_COMPONENT:
				setIncludingComponent((ComponentRegular)null);
				return;
			case UrncorePackage.COMPONENT__RESOURCE:
				setResource((PassiveResource)null);
				return;
			case UrncorePackage.COMPONENT__COMP_REFS:
				getCompRefs().clear();
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
			case UrncorePackage.COMPONENT__INCLUDED_COMPONENT:
				getIncludedComponent().clear();
				return;
			case UrncorePackage.COMPONENT__HOST:
				setHost((ProcessingResource)null);
				return;
			case UrncorePackage.COMPONENT__TYPE:
				setType((ComponentType)null);
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
			case UrncorePackage.COMPONENT__URN_LINKS:
				return urnLinks != null && !urnLinks.isEmpty();
			case UrncorePackage.COMPONENT__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case UrncorePackage.COMPONENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case UrncorePackage.COMPONENT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case UrncorePackage.COMPONENT__LINE_COLOR:
				return LINE_COLOR_EDEFAULT == null ? lineColor != null : !LINE_COLOR_EDEFAULT.equals(lineColor);
			case UrncorePackage.COMPONENT__FILL_COLOR:
				return FILL_COLOR_EDEFAULT == null ? fillColor != null : !FILL_COLOR_EDEFAULT.equals(fillColor);
			case UrncorePackage.COMPONENT__FILLED:
				return filled != FILLED_EDEFAULT;
			case UrncorePackage.COMPONENT__URNDEFINITION:
				return getUrndefinition() != null;
			case UrncorePackage.COMPONENT__INCLUDING_COMPONENT:
				return includingComponent != null;
			case UrncorePackage.COMPONENT__RESOURCE:
				return resource != null;
			case UrncorePackage.COMPONENT__COMP_REFS:
				return compRefs != null && !compRefs.isEmpty();
			case UrncorePackage.COMPONENT__KIND:
				return kind != KIND_EDEFAULT;
			case UrncorePackage.COMPONENT__PROTECTED:
				return protected_ != PROTECTED_EDEFAULT;
			case UrncorePackage.COMPONENT__SLOT:
				return slot != SLOT_EDEFAULT;
			case UrncorePackage.COMPONENT__INCLUDED_COMPONENT:
				return includedComponent != null && !includedComponent.isEmpty();
			case UrncorePackage.COMPONENT__HOST:
				return host != null;
			case UrncorePackage.COMPONENT__TYPE:
				return type != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //ComponentImpl
