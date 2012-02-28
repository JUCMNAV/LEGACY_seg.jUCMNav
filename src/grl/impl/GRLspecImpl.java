/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.Actor;
import grl.ContributionContext;
import grl.ContributionContextGroup;
import grl.ElementLink;
import grl.EvaluationStrategy;
import grl.GRLspec;
import grl.GrlPackage;
import grl.IntentionalElement;
import grl.StrategiesGroup;
import grl.kpimodel.IndicatorGroup;
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KPIModelLink;
import grl.kpimodel.KpimodelPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import urn.URNspec;
import urn.UrnPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>GR Lspec</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.GRLspecImpl#getUrnspec <em>Urnspec</em>}</li>
 *   <li>{@link grl.impl.GRLspecImpl#getIntElements <em>Int Elements</em>}</li>
 *   <li>{@link grl.impl.GRLspecImpl#getActors <em>Actors</em>}</li>
 *   <li>{@link grl.impl.GRLspecImpl#getLinks <em>Links</em>}</li>
 *   <li>{@link grl.impl.GRLspecImpl#getGroups <em>Groups</em>}</li>
 *   <li>{@link grl.impl.GRLspecImpl#getStrategies <em>Strategies</em>}</li>
 *   <li>{@link grl.impl.GRLspecImpl#getContributionGroups <em>Contribution Groups</em>}</li>
 *   <li>{@link grl.impl.GRLspecImpl#getContributionContexts <em>Contribution Contexts</em>}</li>
 *   <li>{@link grl.impl.GRLspecImpl#getKpiInformationElements <em>Kpi Information Elements</em>}</li>
 *   <li>{@link grl.impl.GRLspecImpl#getKpiModelLinks <em>Kpi Model Links</em>}</li>
 *   <li>{@link grl.impl.GRLspecImpl#getIndicatorGroup <em>Indicator Group</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GRLspecImpl extends EObjectImpl implements GRLspec {
    /**
     * The cached value of the '{@link #getIntElements() <em>Int Elements</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIntElements()
     * @generated
     * @ordered
     */
    protected EList intElements;

    /**
     * The cached value of the '{@link #getActors() <em>Actors</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getActors()
     * @generated
     * @ordered
     */
    protected EList actors;

    /**
     * The cached value of the '{@link #getLinks() <em>Links</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLinks()
     * @generated
     * @ordered
     */
    protected EList links;

    /**
     * The cached value of the '{@link #getGroups() <em>Groups</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getGroups()
     * @generated
     * @ordered
     */
    protected EList groups;

    /**
     * The cached value of the '{@link #getStrategies() <em>Strategies</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStrategies()
     * @generated
     * @ordered
     */
    protected EList strategies;

    /**
     * The cached value of the '{@link #getContributionGroups() <em>Contribution Groups</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getContributionGroups()
     * @generated
     * @ordered
     */
	protected EList contributionGroups;

				/**
     * The cached value of the '{@link #getContributionContexts() <em>Contribution Contexts</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getContributionContexts()
     * @generated
     * @ordered
     */
	protected EList contributionContexts;

				/**
     * The cached value of the '{@link #getKpiInformationElements() <em>Kpi Information Elements</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getKpiInformationElements()
     * @generated
     * @ordered
     */
    protected EList kpiInformationElements;

    /**
     * The cached value of the '{@link #getKpiModelLinks() <em>Kpi Model Links</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getKpiModelLinks()
     * @generated
     * @ordered
     */
    protected EList kpiModelLinks;

    /**
     * The cached value of the '{@link #getIndicatorGroup() <em>Indicator Group</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIndicatorGroup()
     * @generated
     * @ordered
     */
    protected EList indicatorGroup;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected GRLspecImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return GrlPackage.Literals.GR_LSPEC;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public URNspec getUrnspec() {
        if (eContainerFeatureID() != GrlPackage.GR_LSPEC__URNSPEC) return null;
        return (URNspec)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetUrnspec(URNspec newUrnspec, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newUrnspec, GrlPackage.GR_LSPEC__URNSPEC, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUrnspec(URNspec newUrnspec) {
        if (newUrnspec != eInternalContainer() || (eContainerFeatureID() != GrlPackage.GR_LSPEC__URNSPEC && newUrnspec != null)) {
            if (EcoreUtil.isAncestor(this, newUrnspec))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newUrnspec != null)
                msgs = ((InternalEObject)newUrnspec).eInverseAdd(this, UrnPackage.UR_NSPEC__GRLSPEC, URNspec.class, msgs);
            msgs = basicSetUrnspec(newUrnspec, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.GR_LSPEC__URNSPEC, newUrnspec, newUrnspec));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getIntElements() {
        if (intElements == null) {
            intElements = new EObjectContainmentWithInverseEList(IntentionalElement.class, this, GrlPackage.GR_LSPEC__INT_ELEMENTS, GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC);
        }
        return intElements;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getActors() {
        if (actors == null) {
            actors = new EObjectContainmentWithInverseEList(Actor.class, this, GrlPackage.GR_LSPEC__ACTORS, GrlPackage.ACTOR__GRLSPEC);
        }
        return actors;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getLinks() {
        if (links == null) {
            links = new EObjectContainmentWithInverseEList(ElementLink.class, this, GrlPackage.GR_LSPEC__LINKS, GrlPackage.ELEMENT_LINK__GRLSPEC);
        }
        return links;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getGroups() {
        if (groups == null) {
            groups = new EObjectContainmentWithInverseEList(StrategiesGroup.class, this, GrlPackage.GR_LSPEC__GROUPS, GrlPackage.STRATEGIES_GROUP__GRLSPEC);
        }
        return groups;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getStrategies() {
        if (strategies == null) {
            strategies = new EObjectContainmentWithInverseEList(EvaluationStrategy.class, this, GrlPackage.GR_LSPEC__STRATEGIES, GrlPackage.EVALUATION_STRATEGY__GRLSPEC);
        }
        return strategies;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getContributionGroups() {
        if (contributionGroups == null) {
            contributionGroups = new EObjectContainmentWithInverseEList(ContributionContextGroup.class, this, GrlPackage.GR_LSPEC__CONTRIBUTION_GROUPS, GrlPackage.CONTRIBUTION_CONTEXT_GROUP__GRLSPEC);
        }
        return contributionGroups;
    }

				/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getContributionContexts() {
        if (contributionContexts == null) {
            contributionContexts = new EObjectContainmentWithInverseEList(ContributionContext.class, this, GrlPackage.GR_LSPEC__CONTRIBUTION_CONTEXTS, GrlPackage.CONTRIBUTION_CONTEXT__GRLSPEC);
        }
        return contributionContexts;
    }

				/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getKpiInformationElements() {
        if (kpiInformationElements == null) {
            kpiInformationElements = new EObjectContainmentWithInverseEList(KPIInformationElement.class, this, GrlPackage.GR_LSPEC__KPI_INFORMATION_ELEMENTS, KpimodelPackage.KPI_INFORMATION_ELEMENT__GRLSPEC);
        }
        return kpiInformationElements;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getKpiModelLinks() {
        if (kpiModelLinks == null) {
            kpiModelLinks = new EObjectContainmentWithInverseEList(KPIModelLink.class, this, GrlPackage.GR_LSPEC__KPI_MODEL_LINKS, KpimodelPackage.KPI_MODEL_LINK__GRLSPEC);
        }
        return kpiModelLinks;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getIndicatorGroup() {
        if (indicatorGroup == null) {
            indicatorGroup = new EObjectContainmentWithInverseEList(IndicatorGroup.class, this, GrlPackage.GR_LSPEC__INDICATOR_GROUP, KpimodelPackage.INDICATOR_GROUP__GRLSPEC);
        }
        return indicatorGroup;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case GrlPackage.GR_LSPEC__URNSPEC:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetUrnspec((URNspec)otherEnd, msgs);
            case GrlPackage.GR_LSPEC__INT_ELEMENTS:
                return ((InternalEList)getIntElements()).basicAdd(otherEnd, msgs);
            case GrlPackage.GR_LSPEC__ACTORS:
                return ((InternalEList)getActors()).basicAdd(otherEnd, msgs);
            case GrlPackage.GR_LSPEC__LINKS:
                return ((InternalEList)getLinks()).basicAdd(otherEnd, msgs);
            case GrlPackage.GR_LSPEC__GROUPS:
                return ((InternalEList)getGroups()).basicAdd(otherEnd, msgs);
            case GrlPackage.GR_LSPEC__STRATEGIES:
                return ((InternalEList)getStrategies()).basicAdd(otherEnd, msgs);
            case GrlPackage.GR_LSPEC__CONTRIBUTION_GROUPS:
                return ((InternalEList)getContributionGroups()).basicAdd(otherEnd, msgs);
            case GrlPackage.GR_LSPEC__CONTRIBUTION_CONTEXTS:
                return ((InternalEList)getContributionContexts()).basicAdd(otherEnd, msgs);
            case GrlPackage.GR_LSPEC__KPI_INFORMATION_ELEMENTS:
                return ((InternalEList)getKpiInformationElements()).basicAdd(otherEnd, msgs);
            case GrlPackage.GR_LSPEC__KPI_MODEL_LINKS:
                return ((InternalEList)getKpiModelLinks()).basicAdd(otherEnd, msgs);
            case GrlPackage.GR_LSPEC__INDICATOR_GROUP:
                return ((InternalEList)getIndicatorGroup()).basicAdd(otherEnd, msgs);
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
            case GrlPackage.GR_LSPEC__URNSPEC:
                return basicSetUrnspec(null, msgs);
            case GrlPackage.GR_LSPEC__INT_ELEMENTS:
                return ((InternalEList)getIntElements()).basicRemove(otherEnd, msgs);
            case GrlPackage.GR_LSPEC__ACTORS:
                return ((InternalEList)getActors()).basicRemove(otherEnd, msgs);
            case GrlPackage.GR_LSPEC__LINKS:
                return ((InternalEList)getLinks()).basicRemove(otherEnd, msgs);
            case GrlPackage.GR_LSPEC__GROUPS:
                return ((InternalEList)getGroups()).basicRemove(otherEnd, msgs);
            case GrlPackage.GR_LSPEC__STRATEGIES:
                return ((InternalEList)getStrategies()).basicRemove(otherEnd, msgs);
            case GrlPackage.GR_LSPEC__CONTRIBUTION_GROUPS:
                return ((InternalEList)getContributionGroups()).basicRemove(otherEnd, msgs);
            case GrlPackage.GR_LSPEC__CONTRIBUTION_CONTEXTS:
                return ((InternalEList)getContributionContexts()).basicRemove(otherEnd, msgs);
            case GrlPackage.GR_LSPEC__KPI_INFORMATION_ELEMENTS:
                return ((InternalEList)getKpiInformationElements()).basicRemove(otherEnd, msgs);
            case GrlPackage.GR_LSPEC__KPI_MODEL_LINKS:
                return ((InternalEList)getKpiModelLinks()).basicRemove(otherEnd, msgs);
            case GrlPackage.GR_LSPEC__INDICATOR_GROUP:
                return ((InternalEList)getIndicatorGroup()).basicRemove(otherEnd, msgs);
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
            case GrlPackage.GR_LSPEC__URNSPEC:
                return eInternalContainer().eInverseRemove(this, UrnPackage.UR_NSPEC__GRLSPEC, URNspec.class, msgs);
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
            case GrlPackage.GR_LSPEC__URNSPEC:
                return getUrnspec();
            case GrlPackage.GR_LSPEC__INT_ELEMENTS:
                return getIntElements();
            case GrlPackage.GR_LSPEC__ACTORS:
                return getActors();
            case GrlPackage.GR_LSPEC__LINKS:
                return getLinks();
            case GrlPackage.GR_LSPEC__GROUPS:
                return getGroups();
            case GrlPackage.GR_LSPEC__STRATEGIES:
                return getStrategies();
            case GrlPackage.GR_LSPEC__CONTRIBUTION_GROUPS:
                return getContributionGroups();
            case GrlPackage.GR_LSPEC__CONTRIBUTION_CONTEXTS:
                return getContributionContexts();
            case GrlPackage.GR_LSPEC__KPI_INFORMATION_ELEMENTS:
                return getKpiInformationElements();
            case GrlPackage.GR_LSPEC__KPI_MODEL_LINKS:
                return getKpiModelLinks();
            case GrlPackage.GR_LSPEC__INDICATOR_GROUP:
                return getIndicatorGroup();
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
            case GrlPackage.GR_LSPEC__URNSPEC:
                setUrnspec((URNspec)newValue);
                return;
            case GrlPackage.GR_LSPEC__INT_ELEMENTS:
                getIntElements().clear();
                getIntElements().addAll((Collection)newValue);
                return;
            case GrlPackage.GR_LSPEC__ACTORS:
                getActors().clear();
                getActors().addAll((Collection)newValue);
                return;
            case GrlPackage.GR_LSPEC__LINKS:
                getLinks().clear();
                getLinks().addAll((Collection)newValue);
                return;
            case GrlPackage.GR_LSPEC__GROUPS:
                getGroups().clear();
                getGroups().addAll((Collection)newValue);
                return;
            case GrlPackage.GR_LSPEC__STRATEGIES:
                getStrategies().clear();
                getStrategies().addAll((Collection)newValue);
                return;
            case GrlPackage.GR_LSPEC__CONTRIBUTION_GROUPS:
                getContributionGroups().clear();
                getContributionGroups().addAll((Collection)newValue);
                return;
            case GrlPackage.GR_LSPEC__CONTRIBUTION_CONTEXTS:
                getContributionContexts().clear();
                getContributionContexts().addAll((Collection)newValue);
                return;
            case GrlPackage.GR_LSPEC__KPI_INFORMATION_ELEMENTS:
                getKpiInformationElements().clear();
                getKpiInformationElements().addAll((Collection)newValue);
                return;
            case GrlPackage.GR_LSPEC__KPI_MODEL_LINKS:
                getKpiModelLinks().clear();
                getKpiModelLinks().addAll((Collection)newValue);
                return;
            case GrlPackage.GR_LSPEC__INDICATOR_GROUP:
                getIndicatorGroup().clear();
                getIndicatorGroup().addAll((Collection)newValue);
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
            case GrlPackage.GR_LSPEC__URNSPEC:
                setUrnspec((URNspec)null);
                return;
            case GrlPackage.GR_LSPEC__INT_ELEMENTS:
                getIntElements().clear();
                return;
            case GrlPackage.GR_LSPEC__ACTORS:
                getActors().clear();
                return;
            case GrlPackage.GR_LSPEC__LINKS:
                getLinks().clear();
                return;
            case GrlPackage.GR_LSPEC__GROUPS:
                getGroups().clear();
                return;
            case GrlPackage.GR_LSPEC__STRATEGIES:
                getStrategies().clear();
                return;
            case GrlPackage.GR_LSPEC__CONTRIBUTION_GROUPS:
                getContributionGroups().clear();
                return;
            case GrlPackage.GR_LSPEC__CONTRIBUTION_CONTEXTS:
                getContributionContexts().clear();
                return;
            case GrlPackage.GR_LSPEC__KPI_INFORMATION_ELEMENTS:
                getKpiInformationElements().clear();
                return;
            case GrlPackage.GR_LSPEC__KPI_MODEL_LINKS:
                getKpiModelLinks().clear();
                return;
            case GrlPackage.GR_LSPEC__INDICATOR_GROUP:
                getIndicatorGroup().clear();
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
            case GrlPackage.GR_LSPEC__URNSPEC:
                return getUrnspec() != null;
            case GrlPackage.GR_LSPEC__INT_ELEMENTS:
                return intElements != null && !intElements.isEmpty();
            case GrlPackage.GR_LSPEC__ACTORS:
                return actors != null && !actors.isEmpty();
            case GrlPackage.GR_LSPEC__LINKS:
                return links != null && !links.isEmpty();
            case GrlPackage.GR_LSPEC__GROUPS:
                return groups != null && !groups.isEmpty();
            case GrlPackage.GR_LSPEC__STRATEGIES:
                return strategies != null && !strategies.isEmpty();
            case GrlPackage.GR_LSPEC__CONTRIBUTION_GROUPS:
                return contributionGroups != null && !contributionGroups.isEmpty();
            case GrlPackage.GR_LSPEC__CONTRIBUTION_CONTEXTS:
                return contributionContexts != null && !contributionContexts.isEmpty();
            case GrlPackage.GR_LSPEC__KPI_INFORMATION_ELEMENTS:
                return kpiInformationElements != null && !kpiInformationElements.isEmpty();
            case GrlPackage.GR_LSPEC__KPI_MODEL_LINKS:
                return kpiModelLinks != null && !kpiModelLinks.isEmpty();
            case GrlPackage.GR_LSPEC__INDICATOR_GROUP:
                return indicatorGroup != null && !indicatorGroup.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //GRLspecImpl
