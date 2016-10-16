/**
 */
package urn.dyncontext.impl;

import grl.ContributionContext;
import grl.EvaluationStrategy;

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

import ucm.scenario.ScenarioDef;

import urn.URNspec;
import urn.UrnPackage;

import urn.dyncontext.Change;
import urn.dyncontext.DynamicContext;
import urn.dyncontext.DynamicContextGroup;
import urn.dyncontext.DyncontextPackage;

import urncore.impl.URNmodelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Dynamic Context</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link urn.dyncontext.impl.DynamicContextImpl#getParentContexts <em>Parent Contexts</em>}</li>
 *   <li>{@link urn.dyncontext.impl.DynamicContextImpl#getIncludedContexts <em>Included Contexts</em>}</li>
 *   <li>{@link urn.dyncontext.impl.DynamicContextImpl#getStrategy <em>Strategy</em>}</li>
 *   <li>{@link urn.dyncontext.impl.DynamicContextImpl#getContributionContext <em>Contribution Context</em>}</li>
 *   <li>{@link urn.dyncontext.impl.DynamicContextImpl#getScenario <em>Scenario</em>}</li>
 *   <li>{@link urn.dyncontext.impl.DynamicContextImpl#getUrnspec <em>Urnspec</em>}</li>
 *   <li>{@link urn.dyncontext.impl.DynamicContextImpl#getGroups <em>Groups</em>}</li>
 *   <li>{@link urn.dyncontext.impl.DynamicContextImpl#getChanges <em>Changes</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DynamicContextImpl extends URNmodelElementImpl implements DynamicContext {
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
	 * The cached value of the '{@link #getStrategy() <em>Strategy</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStrategy()
	 * @generated
	 * @ordered
	 */
	protected EvaluationStrategy strategy;

	/**
	 * The cached value of the '{@link #getContributionContext() <em>Contribution Context</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContributionContext()
	 * @generated
	 * @ordered
	 */
	protected ContributionContext contributionContext;

	/**
	 * The cached value of the '{@link #getScenario() <em>Scenario</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScenario()
	 * @generated
	 * @ordered
	 */
	protected ScenarioDef scenario;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DynamicContextImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DyncontextPackage.Literals.DYNAMIC_CONTEXT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getParentContexts() {
		if (parentContexts == null) {
			parentContexts = new EObjectWithInverseResolvingEList.ManyInverse(DynamicContext.class, this, DyncontextPackage.DYNAMIC_CONTEXT__PARENT_CONTEXTS, DyncontextPackage.DYNAMIC_CONTEXT__INCLUDED_CONTEXTS);
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
			includedContexts = new EObjectWithInverseResolvingEList.ManyInverse(DynamicContext.class, this, DyncontextPackage.DYNAMIC_CONTEXT__INCLUDED_CONTEXTS, DyncontextPackage.DYNAMIC_CONTEXT__PARENT_CONTEXTS);
		}
		return includedContexts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EvaluationStrategy getStrategy() {
		if (strategy != null && strategy.eIsProxy()) {
			InternalEObject oldStrategy = (InternalEObject)strategy;
			strategy = (EvaluationStrategy)eResolveProxy(oldStrategy);
			if (strategy != oldStrategy) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DyncontextPackage.DYNAMIC_CONTEXT__STRATEGY, oldStrategy, strategy));
			}
		}
		return strategy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EvaluationStrategy basicGetStrategy() {
		return strategy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStrategy(EvaluationStrategy newStrategy) {
		EvaluationStrategy oldStrategy = strategy;
		strategy = newStrategy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DyncontextPackage.DYNAMIC_CONTEXT__STRATEGY, oldStrategy, strategy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContributionContext getContributionContext() {
		if (contributionContext != null && contributionContext.eIsProxy()) {
			InternalEObject oldContributionContext = (InternalEObject)contributionContext;
			contributionContext = (ContributionContext)eResolveProxy(oldContributionContext);
			if (contributionContext != oldContributionContext) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DyncontextPackage.DYNAMIC_CONTEXT__CONTRIBUTION_CONTEXT, oldContributionContext, contributionContext));
			}
		}
		return contributionContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContributionContext basicGetContributionContext() {
		return contributionContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContributionContext(ContributionContext newContributionContext) {
		ContributionContext oldContributionContext = contributionContext;
		contributionContext = newContributionContext;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DyncontextPackage.DYNAMIC_CONTEXT__CONTRIBUTION_CONTEXT, oldContributionContext, contributionContext));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScenarioDef getScenario() {
		if (scenario != null && scenario.eIsProxy()) {
			InternalEObject oldScenario = (InternalEObject)scenario;
			scenario = (ScenarioDef)eResolveProxy(oldScenario);
			if (scenario != oldScenario) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DyncontextPackage.DYNAMIC_CONTEXT__SCENARIO, oldScenario, scenario));
			}
		}
		return scenario;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScenarioDef basicGetScenario() {
		return scenario;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScenario(ScenarioDef newScenario) {
		ScenarioDef oldScenario = scenario;
		scenario = newScenario;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DyncontextPackage.DYNAMIC_CONTEXT__SCENARIO, oldScenario, scenario));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public URNspec getUrnspec() {
		if (eContainerFeatureID() != DyncontextPackage.DYNAMIC_CONTEXT__URNSPEC) return null;
		return (URNspec)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUrnspec(URNspec newUrnspec, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newUrnspec, DyncontextPackage.DYNAMIC_CONTEXT__URNSPEC, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUrnspec(URNspec newUrnspec) {
		if (newUrnspec != eInternalContainer() || (eContainerFeatureID() != DyncontextPackage.DYNAMIC_CONTEXT__URNSPEC && newUrnspec != null)) {
			if (EcoreUtil.isAncestor(this, newUrnspec))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newUrnspec != null)
				msgs = ((InternalEObject)newUrnspec).eInverseAdd(this, UrnPackage.UR_NSPEC__DYNAMIC_CONTEXTS, URNspec.class, msgs);
			msgs = basicSetUrnspec(newUrnspec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DyncontextPackage.DYNAMIC_CONTEXT__URNSPEC, newUrnspec, newUrnspec));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getGroups() {
		if (groups == null) {
			groups = new EObjectWithInverseResolvingEList.ManyInverse(DynamicContextGroup.class, this, DyncontextPackage.DYNAMIC_CONTEXT__GROUPS, DyncontextPackage.DYNAMIC_CONTEXT_GROUP__CONTEXTS);
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
			changes = new EObjectContainmentWithInverseEList(Change.class, this, DyncontextPackage.DYNAMIC_CONTEXT__CHANGES, DyncontextPackage.CHANGE__CONTEXT);
		}
		return changes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DyncontextPackage.DYNAMIC_CONTEXT__PARENT_CONTEXTS:
				return ((InternalEList)getParentContexts()).basicAdd(otherEnd, msgs);
			case DyncontextPackage.DYNAMIC_CONTEXT__INCLUDED_CONTEXTS:
				return ((InternalEList)getIncludedContexts()).basicAdd(otherEnd, msgs);
			case DyncontextPackage.DYNAMIC_CONTEXT__URNSPEC:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetUrnspec((URNspec)otherEnd, msgs);
			case DyncontextPackage.DYNAMIC_CONTEXT__GROUPS:
				return ((InternalEList)getGroups()).basicAdd(otherEnd, msgs);
			case DyncontextPackage.DYNAMIC_CONTEXT__CHANGES:
				return ((InternalEList)getChanges()).basicAdd(otherEnd, msgs);
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
			case DyncontextPackage.DYNAMIC_CONTEXT__PARENT_CONTEXTS:
				return ((InternalEList)getParentContexts()).basicRemove(otherEnd, msgs);
			case DyncontextPackage.DYNAMIC_CONTEXT__INCLUDED_CONTEXTS:
				return ((InternalEList)getIncludedContexts()).basicRemove(otherEnd, msgs);
			case DyncontextPackage.DYNAMIC_CONTEXT__URNSPEC:
				return basicSetUrnspec(null, msgs);
			case DyncontextPackage.DYNAMIC_CONTEXT__GROUPS:
				return ((InternalEList)getGroups()).basicRemove(otherEnd, msgs);
			case DyncontextPackage.DYNAMIC_CONTEXT__CHANGES:
				return ((InternalEList)getChanges()).basicRemove(otherEnd, msgs);
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
			case DyncontextPackage.DYNAMIC_CONTEXT__URNSPEC:
				return eInternalContainer().eInverseRemove(this, UrnPackage.UR_NSPEC__DYNAMIC_CONTEXTS, URNspec.class, msgs);
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
			case DyncontextPackage.DYNAMIC_CONTEXT__PARENT_CONTEXTS:
				return getParentContexts();
			case DyncontextPackage.DYNAMIC_CONTEXT__INCLUDED_CONTEXTS:
				return getIncludedContexts();
			case DyncontextPackage.DYNAMIC_CONTEXT__STRATEGY:
				if (resolve) return getStrategy();
				return basicGetStrategy();
			case DyncontextPackage.DYNAMIC_CONTEXT__CONTRIBUTION_CONTEXT:
				if (resolve) return getContributionContext();
				return basicGetContributionContext();
			case DyncontextPackage.DYNAMIC_CONTEXT__SCENARIO:
				if (resolve) return getScenario();
				return basicGetScenario();
			case DyncontextPackage.DYNAMIC_CONTEXT__URNSPEC:
				return getUrnspec();
			case DyncontextPackage.DYNAMIC_CONTEXT__GROUPS:
				return getGroups();
			case DyncontextPackage.DYNAMIC_CONTEXT__CHANGES:
				return getChanges();
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
			case DyncontextPackage.DYNAMIC_CONTEXT__PARENT_CONTEXTS:
				getParentContexts().clear();
				getParentContexts().addAll((Collection)newValue);
				return;
			case DyncontextPackage.DYNAMIC_CONTEXT__INCLUDED_CONTEXTS:
				getIncludedContexts().clear();
				getIncludedContexts().addAll((Collection)newValue);
				return;
			case DyncontextPackage.DYNAMIC_CONTEXT__STRATEGY:
				setStrategy((EvaluationStrategy)newValue);
				return;
			case DyncontextPackage.DYNAMIC_CONTEXT__CONTRIBUTION_CONTEXT:
				setContributionContext((ContributionContext)newValue);
				return;
			case DyncontextPackage.DYNAMIC_CONTEXT__SCENARIO:
				setScenario((ScenarioDef)newValue);
				return;
			case DyncontextPackage.DYNAMIC_CONTEXT__URNSPEC:
				setUrnspec((URNspec)newValue);
				return;
			case DyncontextPackage.DYNAMIC_CONTEXT__GROUPS:
				getGroups().clear();
				getGroups().addAll((Collection)newValue);
				return;
			case DyncontextPackage.DYNAMIC_CONTEXT__CHANGES:
				getChanges().clear();
				getChanges().addAll((Collection)newValue);
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
			case DyncontextPackage.DYNAMIC_CONTEXT__PARENT_CONTEXTS:
				getParentContexts().clear();
				return;
			case DyncontextPackage.DYNAMIC_CONTEXT__INCLUDED_CONTEXTS:
				getIncludedContexts().clear();
				return;
			case DyncontextPackage.DYNAMIC_CONTEXT__STRATEGY:
				setStrategy((EvaluationStrategy)null);
				return;
			case DyncontextPackage.DYNAMIC_CONTEXT__CONTRIBUTION_CONTEXT:
				setContributionContext((ContributionContext)null);
				return;
			case DyncontextPackage.DYNAMIC_CONTEXT__SCENARIO:
				setScenario((ScenarioDef)null);
				return;
			case DyncontextPackage.DYNAMIC_CONTEXT__URNSPEC:
				setUrnspec((URNspec)null);
				return;
			case DyncontextPackage.DYNAMIC_CONTEXT__GROUPS:
				getGroups().clear();
				return;
			case DyncontextPackage.DYNAMIC_CONTEXT__CHANGES:
				getChanges().clear();
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
			case DyncontextPackage.DYNAMIC_CONTEXT__PARENT_CONTEXTS:
				return parentContexts != null && !parentContexts.isEmpty();
			case DyncontextPackage.DYNAMIC_CONTEXT__INCLUDED_CONTEXTS:
				return includedContexts != null && !includedContexts.isEmpty();
			case DyncontextPackage.DYNAMIC_CONTEXT__STRATEGY:
				return strategy != null;
			case DyncontextPackage.DYNAMIC_CONTEXT__CONTRIBUTION_CONTEXT:
				return contributionContext != null;
			case DyncontextPackage.DYNAMIC_CONTEXT__SCENARIO:
				return scenario != null;
			case DyncontextPackage.DYNAMIC_CONTEXT__URNSPEC:
				return getUrnspec() != null;
			case DyncontextPackage.DYNAMIC_CONTEXT__GROUPS:
				return groups != null && !groups.isEmpty();
			case DyncontextPackage.DYNAMIC_CONTEXT__CHANGES:
				return changes != null && !changes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //DynamicContextImpl
