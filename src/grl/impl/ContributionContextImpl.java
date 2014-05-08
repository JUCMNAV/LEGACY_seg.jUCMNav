/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.ContributionChange;
import grl.ContributionContext;
import grl.ContributionContextGroup;
import grl.GRLspec;
import grl.GrlPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import urncore.impl.GRLmodelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Contribution Context</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.ContributionContextImpl#getGrlspec <em>Grlspec</em>}</li>
 *   <li>{@link grl.impl.ContributionContextImpl#getGroups <em>Groups</em>}</li>
 *   <li>{@link grl.impl.ContributionContextImpl#getChanges <em>Changes</em>}</li>
 *   <li>{@link grl.impl.ContributionContextImpl#getParentContexts <em>Parent Contexts</em>}</li>
 *   <li>{@link grl.impl.ContributionContextImpl#getIncludedContexts <em>Included Contexts</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ContributionContextImpl extends GRLmodelElementImpl implements ContributionContext {
	/**
	 * The cached value of the '{@link #getGroups() <em>Groups</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroups()
	 * @generated
	 * @ordered
	 */
	protected EList groups;

	/**
	 * The cached value of the '{@link #getChanges() <em>Changes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChanges()
	 * @generated
	 * @ordered
	 */
	protected EList changes;

	/**
	 * The cached value of the '{@link #getParentContexts() <em>Parent Contexts</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentContexts()
	 * @generated
	 * @ordered
	 */
	protected EList parentContexts;

	/**
	 * The cached value of the '{@link #getIncludedContexts() <em>Included Contexts</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncludedContexts()
	 * @generated
	 * @ordered
	 */
	protected EList includedContexts;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContributionContextImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return GrlPackage.Literals.CONTRIBUTION_CONTEXT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GRLspec getGrlspec() {
		if (eContainerFeatureID() != GrlPackage.CONTRIBUTION_CONTEXT__GRLSPEC) return null;
		return (GRLspec)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGrlspec(GRLspec newGrlspec, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newGrlspec, GrlPackage.CONTRIBUTION_CONTEXT__GRLSPEC, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGrlspec(GRLspec newGrlspec) {
		if (newGrlspec != eInternalContainer() || (eContainerFeatureID() != GrlPackage.CONTRIBUTION_CONTEXT__GRLSPEC && newGrlspec != null)) {
			if (EcoreUtil.isAncestor(this, newGrlspec))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newGrlspec != null)
				msgs = ((InternalEObject)newGrlspec).eInverseAdd(this, GrlPackage.GR_LSPEC__CONTRIBUTION_CONTEXTS, GRLspec.class, msgs);
			msgs = basicSetGrlspec(newGrlspec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.CONTRIBUTION_CONTEXT__GRLSPEC, newGrlspec, newGrlspec));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getGroups() {
		if (groups == null) {
			groups = new EObjectWithInverseResolvingEList.ManyInverse(ContributionContextGroup.class, this, GrlPackage.CONTRIBUTION_CONTEXT__GROUPS, GrlPackage.CONTRIBUTION_CONTEXT_GROUP__CONTRIBS);
		}
		return groups;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getChanges() {
		if (changes == null) {
			changes = new EObjectContainmentWithInverseEList(ContributionChange.class, this, GrlPackage.CONTRIBUTION_CONTEXT__CHANGES, GrlPackage.CONTRIBUTION_CHANGE__CONTEXT);
		}
		return changes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getParentContexts() {
		if (parentContexts == null) {
			parentContexts = new EObjectWithInverseResolvingEList.ManyInverse(ContributionContext.class, this, GrlPackage.CONTRIBUTION_CONTEXT__PARENT_CONTEXTS, GrlPackage.CONTRIBUTION_CONTEXT__INCLUDED_CONTEXTS);
		}
		return parentContexts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getIncludedContexts() {
		if (includedContexts == null) {
			includedContexts = new EObjectWithInverseResolvingEList.ManyInverse(ContributionContext.class, this, GrlPackage.CONTRIBUTION_CONTEXT__INCLUDED_CONTEXTS, GrlPackage.CONTRIBUTION_CONTEXT__PARENT_CONTEXTS);
		}
		return includedContexts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GrlPackage.CONTRIBUTION_CONTEXT__GRLSPEC:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetGrlspec((GRLspec)otherEnd, msgs);
			case GrlPackage.CONTRIBUTION_CONTEXT__GROUPS:
				return ((InternalEList)getGroups()).basicAdd(otherEnd, msgs);
			case GrlPackage.CONTRIBUTION_CONTEXT__CHANGES:
				return ((InternalEList)getChanges()).basicAdd(otherEnd, msgs);
			case GrlPackage.CONTRIBUTION_CONTEXT__PARENT_CONTEXTS:
				return ((InternalEList)getParentContexts()).basicAdd(otherEnd, msgs);
			case GrlPackage.CONTRIBUTION_CONTEXT__INCLUDED_CONTEXTS:
				return ((InternalEList)getIncludedContexts()).basicAdd(otherEnd, msgs);
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
			case GrlPackage.CONTRIBUTION_CONTEXT__GRLSPEC:
				return basicSetGrlspec(null, msgs);
			case GrlPackage.CONTRIBUTION_CONTEXT__GROUPS:
				return ((InternalEList)getGroups()).basicRemove(otherEnd, msgs);
			case GrlPackage.CONTRIBUTION_CONTEXT__CHANGES:
				return ((InternalEList)getChanges()).basicRemove(otherEnd, msgs);
			case GrlPackage.CONTRIBUTION_CONTEXT__PARENT_CONTEXTS:
				return ((InternalEList)getParentContexts()).basicRemove(otherEnd, msgs);
			case GrlPackage.CONTRIBUTION_CONTEXT__INCLUDED_CONTEXTS:
				return ((InternalEList)getIncludedContexts()).basicRemove(otherEnd, msgs);
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
			case GrlPackage.CONTRIBUTION_CONTEXT__GRLSPEC:
				return eInternalContainer().eInverseRemove(this, GrlPackage.GR_LSPEC__CONTRIBUTION_CONTEXTS, GRLspec.class, msgs);
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
			case GrlPackage.CONTRIBUTION_CONTEXT__GRLSPEC:
				return getGrlspec();
			case GrlPackage.CONTRIBUTION_CONTEXT__GROUPS:
				return getGroups();
			case GrlPackage.CONTRIBUTION_CONTEXT__CHANGES:
				return getChanges();
			case GrlPackage.CONTRIBUTION_CONTEXT__PARENT_CONTEXTS:
				return getParentContexts();
			case GrlPackage.CONTRIBUTION_CONTEXT__INCLUDED_CONTEXTS:
				return getIncludedContexts();
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
			case GrlPackage.CONTRIBUTION_CONTEXT__GRLSPEC:
				setGrlspec((GRLspec)newValue);
				return;
			case GrlPackage.CONTRIBUTION_CONTEXT__GROUPS:
				getGroups().clear();
				getGroups().addAll((Collection)newValue);
				return;
			case GrlPackage.CONTRIBUTION_CONTEXT__CHANGES:
				getChanges().clear();
				getChanges().addAll((Collection)newValue);
				return;
			case GrlPackage.CONTRIBUTION_CONTEXT__PARENT_CONTEXTS:
				getParentContexts().clear();
				getParentContexts().addAll((Collection)newValue);
				return;
			case GrlPackage.CONTRIBUTION_CONTEXT__INCLUDED_CONTEXTS:
				getIncludedContexts().clear();
				getIncludedContexts().addAll((Collection)newValue);
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
			case GrlPackage.CONTRIBUTION_CONTEXT__GRLSPEC:
				setGrlspec((GRLspec)null);
				return;
			case GrlPackage.CONTRIBUTION_CONTEXT__GROUPS:
				getGroups().clear();
				return;
			case GrlPackage.CONTRIBUTION_CONTEXT__CHANGES:
				getChanges().clear();
				return;
			case GrlPackage.CONTRIBUTION_CONTEXT__PARENT_CONTEXTS:
				getParentContexts().clear();
				return;
			case GrlPackage.CONTRIBUTION_CONTEXT__INCLUDED_CONTEXTS:
				getIncludedContexts().clear();
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
			case GrlPackage.CONTRIBUTION_CONTEXT__GRLSPEC:
				return getGrlspec() != null;
			case GrlPackage.CONTRIBUTION_CONTEXT__GROUPS:
				return groups != null && !groups.isEmpty();
			case GrlPackage.CONTRIBUTION_CONTEXT__CHANGES:
				return changes != null && !changes.isEmpty();
			case GrlPackage.CONTRIBUTION_CONTEXT__PARENT_CONTEXTS:
				return parentContexts != null && !parentContexts.isEmpty();
			case GrlPackage.CONTRIBUTION_CONTEXT__INCLUDED_CONTEXTS:
				return includedContexts != null && !includedContexts.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ContributionContextImpl
