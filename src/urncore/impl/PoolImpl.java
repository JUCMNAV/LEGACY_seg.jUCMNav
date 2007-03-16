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
import org.eclipse.emf.ecore.util.InternalEList;

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
		return UrncorePackage.Literals.POOL;
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
			InternalEObject oldComponentType = (InternalEObject)componentType;
			componentType = (ComponentType)eResolveProxy(oldComponentType);
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
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UrncorePackage.POOL__COMPONENT_TYPE:
				if (componentType != null)
					msgs = ((InternalEObject)componentType).eInverseRemove(this, UrncorePackage.COMPONENT_TYPE__POOLS, ComponentType.class, msgs);
				return basicSetComponentType((ComponentType)otherEnd, msgs);
			case UrncorePackage.POOL__DYN_RESPONSIBILITIES:
				return ((InternalEList)getDynResponsibilities()).basicAdd(otherEnd, msgs);
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
			case UrncorePackage.POOL__COMPONENT_TYPE:
				return basicSetComponentType(null, msgs);
			case UrncorePackage.POOL__DYN_RESPONSIBILITIES:
				return ((InternalEList)getDynResponsibilities()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
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
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
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
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
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
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case UrncorePackage.POOL__OF_COMPONENTS:
				return ofComponents != OF_COMPONENTS_EDEFAULT;
			case UrncorePackage.POOL__CONTENT:
				return CONTENT_EDEFAULT == null ? content != null : !CONTENT_EDEFAULT.equals(content);
			case UrncorePackage.POOL__COMPONENT_TYPE:
				return componentType != null;
			case UrncorePackage.POOL__DYN_RESPONSIBILITIES:
				return dynResponsibilities != null && !dynResponsibilities.isEmpty();
		}
		return super.eIsSet(featureID);
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
