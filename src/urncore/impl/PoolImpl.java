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

import urncore.ComponentRegular;
import urncore.ComponentType;
import urncore.DynamicResponsibility;
import urncore.Pool;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pool</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link urncore.impl.PoolImpl#isOfComponents <em>Of Components</em>}</li>
 *   <li>{@link urncore.impl.PoolImpl#getContent <em>Content</em>}</li>
 *   <li>{@link urncore.impl.PoolImpl#getComponentType <em>Component Type</em>}</li>
 *   <li>{@link urncore.impl.PoolImpl#getDynResponsibilities <em>Dyn Responsibilities</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PoolImpl extends ComponentElementImpl implements Pool {
	/**
	 * The default value of the '{@link #isOfComponents() <em>Of Components</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOfComponents()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OF_COMPONENTS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isOfComponents() <em>Of Components</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOfComponents()
	 * @generated
	 * @ordered
	 */
	protected boolean ofComponents = OF_COMPONENTS_EDEFAULT;

	/**
	 * The default value of the '{@link #getContent() <em>Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContent()
	 * @generated
	 * @ordered
	 */
	protected static final String CONTENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getContent() <em>Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContent()
	 * @generated
	 * @ordered
	 */
	protected String content = CONTENT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getComponentType() <em>Component Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponentType()
	 * @generated
	 * @ordered
	 */
	protected ComponentType componentType = null;

	/**
	 * The cached value of the '{@link #getDynResponsibilities() <em>Dyn Responsibilities</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDynResponsibilities()
	 * @generated
	 * @ordered
	 */
	protected EList dynResponsibilities = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PoolImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return UrncorePackage.eINSTANCE.getPool();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOfComponents() {
		return ofComponents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOfComponents(boolean newOfComponents) {
		boolean oldOfComponents = ofComponents;
		ofComponents = newOfComponents;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.POOL__OF_COMPONENTS, oldOfComponents, ofComponents));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getContent() {
		return content;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContent(String newContent) {
		String oldContent = content;
		content = newContent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.POOL__CONTENT, oldContent, content));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentType getComponentType() {
		if (componentType != null && componentType.eIsProxy()) {
			ComponentType oldComponentType = componentType;
			componentType = (ComponentType)eResolveProxy((InternalEObject)componentType);
			if (componentType != oldComponentType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UrncorePackage.POOL__COMPONENT_TYPE, oldComponentType, componentType));
			}
		}
		return componentType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentType basicGetComponentType() {
		return componentType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetComponentType(ComponentType newComponentType, NotificationChain msgs) {
		ComponentType oldComponentType = componentType;
		componentType = newComponentType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UrncorePackage.POOL__COMPONENT_TYPE, oldComponentType, newComponentType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponentType(ComponentType newComponentType) {
		if (newComponentType != componentType) {
			NotificationChain msgs = null;
			if (componentType != null)
				msgs = ((InternalEObject)componentType).eInverseRemove(this, UrncorePackage.COMPONENT_TYPE__POOLS, ComponentType.class, msgs);
			if (newComponentType != null)
				msgs = ((InternalEObject)newComponentType).eInverseAdd(this, UrncorePackage.COMPONENT_TYPE__POOLS, ComponentType.class, msgs);
			msgs = basicSetComponentType(newComponentType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.POOL__COMPONENT_TYPE, newComponentType, newComponentType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDynResponsibilities() {
		if (dynResponsibilities == null) {
			dynResponsibilities = new EObjectWithInverseResolvingEList(DynamicResponsibility.class, this, UrncorePackage.POOL__DYN_RESPONSIBILITIES, UrncorePackage.DYNAMIC_RESPONSIBILITY__POOL);
		}
		return dynResponsibilities;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case UrncorePackage.POOL__URN_LINKS:
					return ((InternalEList)getUrnLinks()).basicAdd(otherEnd, msgs);
				case UrncorePackage.POOL__INCLUDING_COMPONENT:
					if (includingComponent != null)
						msgs = ((InternalEObject)includingComponent).eInverseRemove(this, UrncorePackage.COMPONENT_REGULAR__INCLUDED_COMPONENT, ComponentRegular.class, msgs);
					return basicSetIncludingComponent((ComponentRegular)otherEnd, msgs);
				case UrncorePackage.POOL__COMP_REFS:
					return ((InternalEList)getCompRefs()).basicAdd(otherEnd, msgs);
				case UrncorePackage.POOL__COMPONENT_TYPE:
					if (componentType != null)
						msgs = ((InternalEObject)componentType).eInverseRemove(this, UrncorePackage.COMPONENT_TYPE__POOLS, ComponentType.class, msgs);
					return basicSetComponentType((ComponentType)otherEnd, msgs);
				case UrncorePackage.POOL__DYN_RESPONSIBILITIES:
					return ((InternalEList)getDynResponsibilities()).basicAdd(otherEnd, msgs);
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
				case UrncorePackage.POOL__URN_LINKS:
					return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
				case UrncorePackage.POOL__INCLUDING_COMPONENT:
					return basicSetIncludingComponent(null, msgs);
				case UrncorePackage.POOL__COMP_REFS:
					return ((InternalEList)getCompRefs()).basicRemove(otherEnd, msgs);
				case UrncorePackage.POOL__COMPONENT_TYPE:
					return basicSetComponentType(null, msgs);
				case UrncorePackage.POOL__DYN_RESPONSIBILITIES:
					return ((InternalEList)getDynResponsibilities()).basicRemove(otherEnd, msgs);
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
			case UrncorePackage.POOL__URN_LINKS:
				return getUrnLinks();
			case UrncorePackage.POOL__ID:
				return getId();
			case UrncorePackage.POOL__NAME:
				return getName();
			case UrncorePackage.POOL__DESCRIPTION:
				return getDescription();
			case UrncorePackage.POOL__LINE_COLOR:
				return getLineColor();
			case UrncorePackage.POOL__FILL_COLOR:
				return getFillColor();
			case UrncorePackage.POOL__INCLUDING_COMPONENT:
				if (resolve) return getIncludingComponent();
				return basicGetIncludingComponent();
			case UrncorePackage.POOL__COMP_REFS:
				return getCompRefs();
			case UrncorePackage.POOL__OF_COMPONENTS:
				return isOfComponents() ? Boolean.TRUE : Boolean.FALSE;
			case UrncorePackage.POOL__CONTENT:
				return getContent();
			case UrncorePackage.POOL__COMPONENT_TYPE:
				if (resolve) return getComponentType();
				return basicGetComponentType();
			case UrncorePackage.POOL__DYN_RESPONSIBILITIES:
				return getDynResponsibilities();
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
			case UrncorePackage.POOL__URN_LINKS:
				getUrnLinks().clear();
				getUrnLinks().addAll((Collection)newValue);
				return;
			case UrncorePackage.POOL__ID:
				setId((String)newValue);
				return;
			case UrncorePackage.POOL__NAME:
				setName((String)newValue);
				return;
			case UrncorePackage.POOL__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case UrncorePackage.POOL__LINE_COLOR:
				setLineColor((String)newValue);
				return;
			case UrncorePackage.POOL__FILL_COLOR:
				setFillColor((String)newValue);
				return;
			case UrncorePackage.POOL__INCLUDING_COMPONENT:
				setIncludingComponent((ComponentRegular)newValue);
				return;
			case UrncorePackage.POOL__COMP_REFS:
				getCompRefs().clear();
				getCompRefs().addAll((Collection)newValue);
				return;
			case UrncorePackage.POOL__OF_COMPONENTS:
				setOfComponents(((Boolean)newValue).booleanValue());
				return;
			case UrncorePackage.POOL__CONTENT:
				setContent((String)newValue);
				return;
			case UrncorePackage.POOL__COMPONENT_TYPE:
				setComponentType((ComponentType)newValue);
				return;
			case UrncorePackage.POOL__DYN_RESPONSIBILITIES:
				getDynResponsibilities().clear();
				getDynResponsibilities().addAll((Collection)newValue);
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
			case UrncorePackage.POOL__URN_LINKS:
				getUrnLinks().clear();
				return;
			case UrncorePackage.POOL__ID:
				setId(ID_EDEFAULT);
				return;
			case UrncorePackage.POOL__NAME:
				setName(NAME_EDEFAULT);
				return;
			case UrncorePackage.POOL__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case UrncorePackage.POOL__LINE_COLOR:
				setLineColor(LINE_COLOR_EDEFAULT);
				return;
			case UrncorePackage.POOL__FILL_COLOR:
				setFillColor(FILL_COLOR_EDEFAULT);
				return;
			case UrncorePackage.POOL__INCLUDING_COMPONENT:
				setIncludingComponent((ComponentRegular)null);
				return;
			case UrncorePackage.POOL__COMP_REFS:
				getCompRefs().clear();
				return;
			case UrncorePackage.POOL__OF_COMPONENTS:
				setOfComponents(OF_COMPONENTS_EDEFAULT);
				return;
			case UrncorePackage.POOL__CONTENT:
				setContent(CONTENT_EDEFAULT);
				return;
			case UrncorePackage.POOL__COMPONENT_TYPE:
				setComponentType((ComponentType)null);
				return;
			case UrncorePackage.POOL__DYN_RESPONSIBILITIES:
				getDynResponsibilities().clear();
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
			case UrncorePackage.POOL__URN_LINKS:
				return urnLinks != null && !urnLinks.isEmpty();
			case UrncorePackage.POOL__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case UrncorePackage.POOL__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case UrncorePackage.POOL__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case UrncorePackage.POOL__LINE_COLOR:
				return LINE_COLOR_EDEFAULT == null ? lineColor != null : !LINE_COLOR_EDEFAULT.equals(lineColor);
			case UrncorePackage.POOL__FILL_COLOR:
				return FILL_COLOR_EDEFAULT == null ? fillColor != null : !FILL_COLOR_EDEFAULT.equals(fillColor);
			case UrncorePackage.POOL__INCLUDING_COMPONENT:
				return includingComponent != null;
			case UrncorePackage.POOL__COMP_REFS:
				return compRefs != null && !compRefs.isEmpty();
			case UrncorePackage.POOL__OF_COMPONENTS:
				return ofComponents != OF_COMPONENTS_EDEFAULT;
			case UrncorePackage.POOL__CONTENT:
				return CONTENT_EDEFAULT == null ? content != null : !CONTENT_EDEFAULT.equals(content);
			case UrncorePackage.POOL__COMPONENT_TYPE:
				return componentType != null;
			case UrncorePackage.POOL__DYN_RESPONSIBILITIES:
				return dynResponsibilities != null && !dynResponsibilities.isEmpty();
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
		result.append(" (ofComponents: ");
		result.append(ofComponents);
		result.append(", content: ");
		result.append(content);
		result.append(')');
		return result.toString();
	}

} //PoolImpl
