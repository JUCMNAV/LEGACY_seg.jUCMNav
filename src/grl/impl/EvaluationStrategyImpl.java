/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.GRLspec;
import grl.GrlPackage;
import grl.StrategiesGroup;
import grl.kpimodel.KPIInformationConfig;
import grl.kpimodel.KpimodelPackage;

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
 * An implementation of the model object '<em><b>Evaluation Strategy</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.EvaluationStrategyImpl#getAuthor <em>Author</em>}</li>
 *   <li>{@link grl.impl.EvaluationStrategyImpl#getEvaluations <em>Evaluations</em>}</li>
 *   <li>{@link grl.impl.EvaluationStrategyImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link grl.impl.EvaluationStrategyImpl#getGrlspec <em>Grlspec</em>}</li>
 *   <li>{@link grl.impl.EvaluationStrategyImpl#getIncludedStrategies <em>Included Strategies</em>}</li>
 *   <li>{@link grl.impl.EvaluationStrategyImpl#getParentStrategies <em>Parent Strategies</em>}</li>
 *   <li>{@link grl.impl.EvaluationStrategyImpl#getKpiInfoConfig <em>Kpi Info Config</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EvaluationStrategyImpl extends GRLmodelElementImpl implements EvaluationStrategy{
    /**
	 * The default value of the '{@link #getAuthor() <em>Author</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getAuthor()
	 * @generated
	 * @ordered
	 */
    protected static final String AUTHOR_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getAuthor() <em>Author</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getAuthor()
	 * @generated
	 * @ordered
	 */
    protected String author = AUTHOR_EDEFAULT;

    /**
	 * The cached value of the '{@link #getEvaluations() <em>Evaluations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getEvaluations()
	 * @generated
	 * @ordered
	 */
    protected EList evaluations;

    /**
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
    protected StrategiesGroup group;

    /**
	 * The cached value of the '{@link #getIncludedStrategies() <em>Included Strategies</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncludedStrategies()
	 * @generated
	 * @ordered
	 */
	protected EList includedStrategies;

				/**
	 * The cached value of the '{@link #getParentStrategies() <em>Parent Strategies</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentStrategies()
	 * @generated
	 * @ordered
	 */
	protected EList parentStrategies;

				/**
	 * The cached value of the '{@link #getKpiInfoConfig() <em>Kpi Info Config</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getKpiInfoConfig()
	 * @generated
	 * @ordered
	 */
    protected EList kpiInfoConfig;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EvaluationStrategyImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return GrlPackage.Literals.EVALUATION_STRATEGY;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getAuthor() {
		return author;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setAuthor(String newAuthor) {
		String oldAuthor = author;
		author = newAuthor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.EVALUATION_STRATEGY__AUTHOR, oldAuthor, author));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getEvaluations() {
		if (evaluations == null) {
			evaluations = new EObjectContainmentWithInverseEList(Evaluation.class, this, GrlPackage.EVALUATION_STRATEGY__EVALUATIONS, GrlPackage.EVALUATION__STRATEGIES);
		}
		return evaluations;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public StrategiesGroup getGroup() {
		if (group != null && group.eIsProxy()) {
			InternalEObject oldGroup = (InternalEObject)group;
			group = (StrategiesGroup)eResolveProxy(oldGroup);
			if (group != oldGroup) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GrlPackage.EVALUATION_STRATEGY__GROUP, oldGroup, group));
			}
		}
		return group;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public StrategiesGroup basicGetGroup() {
		return group;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetGroup(StrategiesGroup newGroup, NotificationChain msgs) {
		StrategiesGroup oldGroup = group;
		group = newGroup;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.EVALUATION_STRATEGY__GROUP, oldGroup, newGroup);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setGroup(StrategiesGroup newGroup) {
		if (newGroup != group) {
			NotificationChain msgs = null;
			if (group != null)
				msgs = ((InternalEObject)group).eInverseRemove(this, GrlPackage.STRATEGIES_GROUP__STRATEGIES, StrategiesGroup.class, msgs);
			if (newGroup != null)
				msgs = ((InternalEObject)newGroup).eInverseAdd(this, GrlPackage.STRATEGIES_GROUP__STRATEGIES, StrategiesGroup.class, msgs);
			msgs = basicSetGroup(newGroup, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.EVALUATION_STRATEGY__GROUP, newGroup, newGroup));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public GRLspec getGrlspec() {
		if (eContainerFeatureID() != GrlPackage.EVALUATION_STRATEGY__GRLSPEC) return null;
		return (GRLspec)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGrlspec(GRLspec newGrlspec, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newGrlspec, GrlPackage.EVALUATION_STRATEGY__GRLSPEC, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setGrlspec(GRLspec newGrlspec) {
		if (newGrlspec != eInternalContainer() || (eContainerFeatureID() != GrlPackage.EVALUATION_STRATEGY__GRLSPEC && newGrlspec != null)) {
			if (EcoreUtil.isAncestor(this, newGrlspec))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newGrlspec != null)
				msgs = ((InternalEObject)newGrlspec).eInverseAdd(this, GrlPackage.GR_LSPEC__STRATEGIES, GRLspec.class, msgs);
			msgs = basicSetGrlspec(newGrlspec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.EVALUATION_STRATEGY__GRLSPEC, newGrlspec, newGrlspec));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getIncludedStrategies() {
		if (includedStrategies == null) {
			includedStrategies = new EObjectWithInverseResolvingEList.ManyInverse(EvaluationStrategy.class, this, GrlPackage.EVALUATION_STRATEGY__INCLUDED_STRATEGIES, GrlPackage.EVALUATION_STRATEGY__PARENT_STRATEGIES);
		}
		return includedStrategies;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getParentStrategies() {
		if (parentStrategies == null) {
			parentStrategies = new EObjectWithInverseResolvingEList.ManyInverse(EvaluationStrategy.class, this, GrlPackage.EVALUATION_STRATEGY__PARENT_STRATEGIES, GrlPackage.EVALUATION_STRATEGY__INCLUDED_STRATEGIES);
		}
		return parentStrategies;
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getKpiInfoConfig() {
		if (kpiInfoConfig == null) {
			kpiInfoConfig = new EObjectContainmentWithInverseEList(KPIInformationConfig.class, this, GrlPackage.EVALUATION_STRATEGY__KPI_INFO_CONFIG, KpimodelPackage.KPI_INFORMATION_CONFIG__STRATEGIES);
		}
		return kpiInfoConfig;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GrlPackage.EVALUATION_STRATEGY__EVALUATIONS:
				return ((InternalEList)getEvaluations()).basicAdd(otherEnd, msgs);
			case GrlPackage.EVALUATION_STRATEGY__GROUP:
				if (group != null)
					msgs = ((InternalEObject)group).eInverseRemove(this, GrlPackage.STRATEGIES_GROUP__STRATEGIES, StrategiesGroup.class, msgs);
				return basicSetGroup((StrategiesGroup)otherEnd, msgs);
			case GrlPackage.EVALUATION_STRATEGY__GRLSPEC:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetGrlspec((GRLspec)otherEnd, msgs);
			case GrlPackage.EVALUATION_STRATEGY__INCLUDED_STRATEGIES:
				return ((InternalEList)getIncludedStrategies()).basicAdd(otherEnd, msgs);
			case GrlPackage.EVALUATION_STRATEGY__PARENT_STRATEGIES:
				return ((InternalEList)getParentStrategies()).basicAdd(otherEnd, msgs);
			case GrlPackage.EVALUATION_STRATEGY__KPI_INFO_CONFIG:
				return ((InternalEList)getKpiInfoConfig()).basicAdd(otherEnd, msgs);
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
			case GrlPackage.EVALUATION_STRATEGY__EVALUATIONS:
				return ((InternalEList)getEvaluations()).basicRemove(otherEnd, msgs);
			case GrlPackage.EVALUATION_STRATEGY__GROUP:
				return basicSetGroup(null, msgs);
			case GrlPackage.EVALUATION_STRATEGY__GRLSPEC:
				return basicSetGrlspec(null, msgs);
			case GrlPackage.EVALUATION_STRATEGY__INCLUDED_STRATEGIES:
				return ((InternalEList)getIncludedStrategies()).basicRemove(otherEnd, msgs);
			case GrlPackage.EVALUATION_STRATEGY__PARENT_STRATEGIES:
				return ((InternalEList)getParentStrategies()).basicRemove(otherEnd, msgs);
			case GrlPackage.EVALUATION_STRATEGY__KPI_INFO_CONFIG:
				return ((InternalEList)getKpiInfoConfig()).basicRemove(otherEnd, msgs);
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
			case GrlPackage.EVALUATION_STRATEGY__GRLSPEC:
				return eInternalContainer().eInverseRemove(this, GrlPackage.GR_LSPEC__STRATEGIES, GRLspec.class, msgs);
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
			case GrlPackage.EVALUATION_STRATEGY__AUTHOR:
				return getAuthor();
			case GrlPackage.EVALUATION_STRATEGY__EVALUATIONS:
				return getEvaluations();
			case GrlPackage.EVALUATION_STRATEGY__GROUP:
				if (resolve) return getGroup();
				return basicGetGroup();
			case GrlPackage.EVALUATION_STRATEGY__GRLSPEC:
				return getGrlspec();
			case GrlPackage.EVALUATION_STRATEGY__INCLUDED_STRATEGIES:
				return getIncludedStrategies();
			case GrlPackage.EVALUATION_STRATEGY__PARENT_STRATEGIES:
				return getParentStrategies();
			case GrlPackage.EVALUATION_STRATEGY__KPI_INFO_CONFIG:
				return getKpiInfoConfig();
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
			case GrlPackage.EVALUATION_STRATEGY__AUTHOR:
				setAuthor((String)newValue);
				return;
			case GrlPackage.EVALUATION_STRATEGY__EVALUATIONS:
				getEvaluations().clear();
				getEvaluations().addAll((Collection)newValue);
				return;
			case GrlPackage.EVALUATION_STRATEGY__GROUP:
				setGroup((StrategiesGroup)newValue);
				return;
			case GrlPackage.EVALUATION_STRATEGY__GRLSPEC:
				setGrlspec((GRLspec)newValue);
				return;
			case GrlPackage.EVALUATION_STRATEGY__INCLUDED_STRATEGIES:
				getIncludedStrategies().clear();
				getIncludedStrategies().addAll((Collection)newValue);
				return;
			case GrlPackage.EVALUATION_STRATEGY__PARENT_STRATEGIES:
				getParentStrategies().clear();
				getParentStrategies().addAll((Collection)newValue);
				return;
			case GrlPackage.EVALUATION_STRATEGY__KPI_INFO_CONFIG:
				getKpiInfoConfig().clear();
				getKpiInfoConfig().addAll((Collection)newValue);
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
			case GrlPackage.EVALUATION_STRATEGY__AUTHOR:
				setAuthor(AUTHOR_EDEFAULT);
				return;
			case GrlPackage.EVALUATION_STRATEGY__EVALUATIONS:
				getEvaluations().clear();
				return;
			case GrlPackage.EVALUATION_STRATEGY__GROUP:
				setGroup((StrategiesGroup)null);
				return;
			case GrlPackage.EVALUATION_STRATEGY__GRLSPEC:
				setGrlspec((GRLspec)null);
				return;
			case GrlPackage.EVALUATION_STRATEGY__INCLUDED_STRATEGIES:
				getIncludedStrategies().clear();
				return;
			case GrlPackage.EVALUATION_STRATEGY__PARENT_STRATEGIES:
				getParentStrategies().clear();
				return;
			case GrlPackage.EVALUATION_STRATEGY__KPI_INFO_CONFIG:
				getKpiInfoConfig().clear();
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
			case GrlPackage.EVALUATION_STRATEGY__AUTHOR:
				return AUTHOR_EDEFAULT == null ? author != null : !AUTHOR_EDEFAULT.equals(author);
			case GrlPackage.EVALUATION_STRATEGY__EVALUATIONS:
				return evaluations != null && !evaluations.isEmpty();
			case GrlPackage.EVALUATION_STRATEGY__GROUP:
				return group != null;
			case GrlPackage.EVALUATION_STRATEGY__GRLSPEC:
				return getGrlspec() != null;
			case GrlPackage.EVALUATION_STRATEGY__INCLUDED_STRATEGIES:
				return includedStrategies != null && !includedStrategies.isEmpty();
			case GrlPackage.EVALUATION_STRATEGY__PARENT_STRATEGIES:
				return parentStrategies != null && !parentStrategies.isEmpty();
			case GrlPackage.EVALUATION_STRATEGY__KPI_INFO_CONFIG:
				return kpiInfoConfig != null && !kpiInfoConfig.isEmpty();
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
		result.append(" (author: ");
		result.append(author);
		result.append(')');
		return result.toString();
	}
    
    public int compareTo(EvaluationStrategy strat){
    	return (this.getName()).compareTo(strat.getName());
    }

} //EvaluationStrategyImpl
