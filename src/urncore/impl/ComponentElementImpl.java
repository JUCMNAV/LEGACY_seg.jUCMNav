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

import ucm.map.ComponentRef;
import ucm.map.MapPackage;

import urncore.ComponentElement;
import urncore.ComponentRegular;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link urncore.impl.ComponentElementImpl#getLineColor <em>Line Color</em>}</li>
 *   <li>{@link urncore.impl.ComponentElementImpl#getFillColor <em>Fill Color</em>}</li>
 *   <li>{@link urncore.impl.ComponentElementImpl#getIncludingComponent <em>Including Component</em>}</li>
 *   <li>{@link urncore.impl.ComponentElementImpl#getCompRefs <em>Comp Refs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ComponentElementImpl extends UCMmodelElementImpl implements ComponentElement {
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
	 * The cached value of the '{@link #getIncludingComponent() <em>Including Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncludingComponent()
	 * @generated
	 * @ordered
	 */
	protected ComponentRegular includingComponent = null;

	/**
	 * The cached value of the '{@link #getCompRefs() <em>Comp Refs</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompRefs()
	 * @generated
	 * @ordered
	 */
	protected EList compRefs = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return UrncorePackage.eINSTANCE.getComponentElement();
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
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT_ELEMENT__LINE_COLOR, oldLineColor, lineColor));
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
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT_ELEMENT__FILL_COLOR, oldFillColor, fillColor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentRegular getIncludingComponent() {
		if (includingComponent != null && includingComponent.eIsProxy()) {
			ComponentRegular oldIncludingComponent = includingComponent;
			includingComponent = (ComponentRegular)eResolveProxy((InternalEObject)includingComponent);
			if (includingComponent != oldIncludingComponent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UrncorePackage.COMPONENT_ELEMENT__INCLUDING_COMPONENT, oldIncludingComponent, includingComponent));
			}
		}
		return includingComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentRegular basicGetIncludingComponent() {
		return includingComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIncludingComponent(ComponentRegular newIncludingComponent, NotificationChain msgs) {
		ComponentRegular oldIncludingComponent = includingComponent;
		includingComponent = newIncludingComponent;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT_ELEMENT__INCLUDING_COMPONENT, oldIncludingComponent, newIncludingComponent);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIncludingComponent(ComponentRegular newIncludingComponent) {
		if (newIncludingComponent != includingComponent) {
			NotificationChain msgs = null;
			if (includingComponent != null)
				msgs = ((InternalEObject)includingComponent).eInverseRemove(this, UrncorePackage.COMPONENT_REGULAR__INCLUDED_COMPONENT, ComponentRegular.class, msgs);
			if (newIncludingComponent != null)
				msgs = ((InternalEObject)newIncludingComponent).eInverseAdd(this, UrncorePackage.COMPONENT_REGULAR__INCLUDED_COMPONENT, ComponentRegular.class, msgs);
			msgs = basicSetIncludingComponent(newIncludingComponent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT_ELEMENT__INCLUDING_COMPONENT, newIncludingComponent, newIncludingComponent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getCompRefs() {
		if (compRefs == null) {
			compRefs = new EObjectWithInverseResolvingEList(ComponentRef.class, this, UrncorePackage.COMPONENT_ELEMENT__COMP_REFS, MapPackage.COMPONENT_REF__COMP_DEF);
		}
		return compRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case UrncorePackage.COMPONENT_ELEMENT__URN_LINKS:
					return ((InternalEList)getUrnLinks()).basicAdd(otherEnd, msgs);
				case UrncorePackage.COMPONENT_ELEMENT__INCLUDING_COMPONENT:
					if (includingComponent != null)
						msgs = ((InternalEObject)includingComponent).eInverseRemove(this, UrncorePackage.COMPONENT_REGULAR__INCLUDED_COMPONENT, ComponentRegular.class, msgs);
					return basicSetIncludingComponent((ComponentRegular)otherEnd, msgs);
				case UrncorePackage.COMPONENT_ELEMENT__COMP_REFS:
					return ((InternalEList)getCompRefs()).basicAdd(otherEnd, msgs);
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
				case UrncorePackage.COMPONENT_ELEMENT__URN_LINKS:
					return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
				case UrncorePackage.COMPONENT_ELEMENT__INCLUDING_COMPONENT:
					return basicSetIncludingComponent(null, msgs);
				case UrncorePackage.COMPONENT_ELEMENT__COMP_REFS:
					return ((InternalEList)getCompRefs()).basicRemove(otherEnd, msgs);
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
			case UrncorePackage.COMPONENT_ELEMENT__URN_LINKS:
				return getUrnLinks();
			case UrncorePackage.COMPONENT_ELEMENT__ID:
				return getId();
			case UrncorePackage.COMPONENT_ELEMENT__NAME:
				return getName();
			case UrncorePackage.COMPONENT_ELEMENT__DESCRIPTION:
				return getDescription();
			case UrncorePackage.COMPONENT_ELEMENT__LINE_COLOR:
				return getLineColor();
			case UrncorePackage.COMPONENT_ELEMENT__FILL_COLOR:
				return getFillColor();
			case UrncorePackage.COMPONENT_ELEMENT__INCLUDING_COMPONENT:
				if (resolve) return getIncludingComponent();
				return basicGetIncludingComponent();
			case UrncorePackage.COMPONENT_ELEMENT__COMP_REFS:
				return getCompRefs();
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
			case UrncorePackage.COMPONENT_ELEMENT__URN_LINKS:
				getUrnLinks().clear();
				getUrnLinks().addAll((Collection)newValue);
				return;
			case UrncorePackage.COMPONENT_ELEMENT__ID:
				setId((String)newValue);
				return;
			case UrncorePackage.COMPONENT_ELEMENT__NAME:
				setName((String)newValue);
				return;
			case UrncorePackage.COMPONENT_ELEMENT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case UrncorePackage.COMPONENT_ELEMENT__LINE_COLOR:
				setLineColor((String)newValue);
				return;
			case UrncorePackage.COMPONENT_ELEMENT__FILL_COLOR:
				setFillColor((String)newValue);
				return;
			case UrncorePackage.COMPONENT_ELEMENT__INCLUDING_COMPONENT:
				setIncludingComponent((ComponentRegular)newValue);
				return;
			case UrncorePackage.COMPONENT_ELEMENT__COMP_REFS:
				getCompRefs().clear();
				getCompRefs().addAll((Collection)newValue);
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
			case UrncorePackage.COMPONENT_ELEMENT__URN_LINKS:
				getUrnLinks().clear();
				return;
			case UrncorePackage.COMPONENT_ELEMENT__ID:
				setId(ID_EDEFAULT);
				return;
			case UrncorePackage.COMPONENT_ELEMENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case UrncorePackage.COMPONENT_ELEMENT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case UrncorePackage.COMPONENT_ELEMENT__LINE_COLOR:
				setLineColor(LINE_COLOR_EDEFAULT);
				return;
			case UrncorePackage.COMPONENT_ELEMENT__FILL_COLOR:
				setFillColor(FILL_COLOR_EDEFAULT);
				return;
			case UrncorePackage.COMPONENT_ELEMENT__INCLUDING_COMPONENT:
				setIncludingComponent((ComponentRegular)null);
				return;
			case UrncorePackage.COMPONENT_ELEMENT__COMP_REFS:
				getCompRefs().clear();
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
			case UrncorePackage.COMPONENT_ELEMENT__URN_LINKS:
				return urnLinks != null && !urnLinks.isEmpty();
			case UrncorePackage.COMPONENT_ELEMENT__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case UrncorePackage.COMPONENT_ELEMENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case UrncorePackage.COMPONENT_ELEMENT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case UrncorePackage.COMPONENT_ELEMENT__LINE_COLOR:
				return LINE_COLOR_EDEFAULT == null ? lineColor != null : !LINE_COLOR_EDEFAULT.equals(lineColor);
			case UrncorePackage.COMPONENT_ELEMENT__FILL_COLOR:
				return FILL_COLOR_EDEFAULT == null ? fillColor != null : !FILL_COLOR_EDEFAULT.equals(fillColor);
			case UrncorePackage.COMPONENT_ELEMENT__INCLUDING_COMPONENT:
				return includingComponent != null;
			case UrncorePackage.COMPONENT_ELEMENT__COMP_REFS:
				return compRefs != null && !compRefs.isEmpty();
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
		result.append(" (lineColor: ");
		result.append(lineColor);
		result.append(", fillColor: ");
		result.append(fillColor);
		result.append(')');
		return result.toString();
	}

} //ComponentElementImpl
