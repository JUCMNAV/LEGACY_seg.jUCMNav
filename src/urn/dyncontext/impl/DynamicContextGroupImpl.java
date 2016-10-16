/**
 */
package urn.dyncontext.impl;

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

import urn.URNspec;
import urn.UrnPackage;

import urn.dyncontext.DynamicContext;
import urn.dyncontext.DynamicContextGroup;
import urn.dyncontext.DyncontextPackage;

import urncore.impl.URNmodelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Dynamic Context Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link urn.dyncontext.impl.DynamicContextGroupImpl#getUrnspec <em>Urnspec</em>}</li>
 *   <li>{@link urn.dyncontext.impl.DynamicContextGroupImpl#getContexts <em>Contexts</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DynamicContextGroupImpl extends URNmodelElementImpl implements DynamicContextGroup {
	/**
	 * The cached value of the '{@link #getContexts() <em>Contexts</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContexts()
	 * @generated
	 * @ordered
	 */
	protected EList contexts;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DynamicContextGroupImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DyncontextPackage.Literals.DYNAMIC_CONTEXT_GROUP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public URNspec getUrnspec() {
		if (eContainerFeatureID() != DyncontextPackage.DYNAMIC_CONTEXT_GROUP__URNSPEC) return null;
		return (URNspec)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUrnspec(URNspec newUrnspec, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newUrnspec, DyncontextPackage.DYNAMIC_CONTEXT_GROUP__URNSPEC, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUrnspec(URNspec newUrnspec) {
		if (newUrnspec != eInternalContainer() || (eContainerFeatureID() != DyncontextPackage.DYNAMIC_CONTEXT_GROUP__URNSPEC && newUrnspec != null)) {
			if (EcoreUtil.isAncestor(this, newUrnspec))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newUrnspec != null)
				msgs = ((InternalEObject)newUrnspec).eInverseAdd(this, UrnPackage.UR_NSPEC__DYNAMIC_CONTEXT_GROUPS, URNspec.class, msgs);
			msgs = basicSetUrnspec(newUrnspec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DyncontextPackage.DYNAMIC_CONTEXT_GROUP__URNSPEC, newUrnspec, newUrnspec));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getContexts() {
		if (contexts == null) {
			contexts = new EObjectWithInverseResolvingEList.ManyInverse(DynamicContext.class, this, DyncontextPackage.DYNAMIC_CONTEXT_GROUP__CONTEXTS, DyncontextPackage.DYNAMIC_CONTEXT__GROUPS);
		}
		return contexts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DyncontextPackage.DYNAMIC_CONTEXT_GROUP__URNSPEC:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetUrnspec((URNspec)otherEnd, msgs);
			case DyncontextPackage.DYNAMIC_CONTEXT_GROUP__CONTEXTS:
				return ((InternalEList)getContexts()).basicAdd(otherEnd, msgs);
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
			case DyncontextPackage.DYNAMIC_CONTEXT_GROUP__URNSPEC:
				return basicSetUrnspec(null, msgs);
			case DyncontextPackage.DYNAMIC_CONTEXT_GROUP__CONTEXTS:
				return ((InternalEList)getContexts()).basicRemove(otherEnd, msgs);
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
			case DyncontextPackage.DYNAMIC_CONTEXT_GROUP__URNSPEC:
				return eInternalContainer().eInverseRemove(this, UrnPackage.UR_NSPEC__DYNAMIC_CONTEXT_GROUPS, URNspec.class, msgs);
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
			case DyncontextPackage.DYNAMIC_CONTEXT_GROUP__URNSPEC:
				return getUrnspec();
			case DyncontextPackage.DYNAMIC_CONTEXT_GROUP__CONTEXTS:
				return getContexts();
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
			case DyncontextPackage.DYNAMIC_CONTEXT_GROUP__URNSPEC:
				setUrnspec((URNspec)newValue);
				return;
			case DyncontextPackage.DYNAMIC_CONTEXT_GROUP__CONTEXTS:
				getContexts().clear();
				getContexts().addAll((Collection)newValue);
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
			case DyncontextPackage.DYNAMIC_CONTEXT_GROUP__URNSPEC:
				setUrnspec((URNspec)null);
				return;
			case DyncontextPackage.DYNAMIC_CONTEXT_GROUP__CONTEXTS:
				getContexts().clear();
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
			case DyncontextPackage.DYNAMIC_CONTEXT_GROUP__URNSPEC:
				return getUrnspec() != null;
			case DyncontextPackage.DYNAMIC_CONTEXT_GROUP__CONTEXTS:
				return contexts != null && !contexts.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //DynamicContextGroupImpl
