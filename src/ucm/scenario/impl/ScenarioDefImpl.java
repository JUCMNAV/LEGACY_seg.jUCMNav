/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.scenario.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import ucm.UCMspec;
import ucm.UcmPackage;
import ucm.map.EndPoint;
import ucm.map.MapPackage;
import ucm.map.StartPoint;
import ucm.scenario.Initialization;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;
import ucm.scenario.ScenarioPackage;
import ucm.scenario.Variable;
import urncore.Condition;
import urncore.UrncorePackage;
import urncore.impl.UCMmodelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Def</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.scenario.impl.ScenarioDefImpl#getStartPoints <em>Start Points</em>}</li>
 *   <li>{@link ucm.scenario.impl.ScenarioDefImpl#getUcmspec <em>Ucmspec</em>}</li>
 *   <li>{@link ucm.scenario.impl.ScenarioDefImpl#getGroups <em>Groups</em>}</li>
 *   <li>{@link ucm.scenario.impl.ScenarioDefImpl#getParentScenarios <em>Parent Scenarios</em>}</li>
 *   <li>{@link ucm.scenario.impl.ScenarioDefImpl#getIncludedScenarios <em>Included Scenarios</em>}</li>
 *   <li>{@link ucm.scenario.impl.ScenarioDefImpl#getEndPoints <em>End Points</em>}</li>
 *   <li>{@link ucm.scenario.impl.ScenarioDefImpl#getPreconditions <em>Preconditions</em>}</li>
 *   <li>{@link ucm.scenario.impl.ScenarioDefImpl#getPostconditions <em>Postconditions</em>}</li>
 *   <li>{@link ucm.scenario.impl.ScenarioDefImpl#getVariable <em>Variable</em>}</li>
 *   <li>{@link ucm.scenario.impl.ScenarioDefImpl#getInitializations <em>Initializations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScenarioDefImpl extends UCMmodelElementImpl implements ScenarioDef {
	/**
	 * The cached value of the '{@link #getStartPoints() <em>Start Points</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getStartPoints()
	 * @generated
	 * @ordered
	 */
    protected EList startPoints = null;

	/**
	 * The cached value of the '{@link #getParentScenarios() <em>Parent Scenarios</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentScenarios()
	 * @generated
	 * @ordered
	 */
	protected EList parentScenarios = null;

	/**
	 * The cached value of the '{@link #getIncludedScenarios() <em>Included Scenarios</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncludedScenarios()
	 * @generated
	 * @ordered
	 */
	protected EList includedScenarios = null;

	/**
	 * The cached value of the '{@link #getEndPoints() <em>End Points</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndPoints()
	 * @generated
	 * @ordered
	 */
	protected EList endPoints = null;

	/**
	 * The cached value of the '{@link #getPreconditions() <em>Preconditions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreconditions()
	 * @generated
	 * @ordered
	 */
	protected EList preconditions = null;

	/**
	 * The cached value of the '{@link #getPostconditions() <em>Postconditions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPostconditions()
	 * @generated
	 * @ordered
	 */
	protected EList postconditions = null;

	/**
	 * The cached value of the '{@link #getVariable() <em>Variable</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariable()
	 * @generated
	 * @ordered
	 */
	protected EList variable = null;

	/**
	 * The cached value of the '{@link #getInitializations() <em>Initializations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitializations()
	 * @generated
	 * @ordered
	 */
	protected EList initializations = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected ScenarioDefImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return ScenarioPackage.Literals.SCENARIO_DEF;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getStartPoints() {
		if (startPoints == null) {
			startPoints = new EObjectWithInverseResolvingEList.ManyInverse(StartPoint.class, this, ScenarioPackage.SCENARIO_DEF__START_POINTS, MapPackage.START_POINT__SCENARIO_DEFS);
		}
		return startPoints;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public UCMspec getUcmspec() {
		if (eContainerFeatureID != ScenarioPackage.SCENARIO_DEF__UCMSPEC) return null;
		return (UCMspec)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUcmspec(UCMspec newUcmspec, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newUcmspec, ScenarioPackage.SCENARIO_DEF__UCMSPEC, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setUcmspec(UCMspec newUcmspec) {
		if (newUcmspec != eInternalContainer() || (eContainerFeatureID != ScenarioPackage.SCENARIO_DEF__UCMSPEC && newUcmspec != null)) {
			if (EcoreUtil.isAncestor(this, newUcmspec))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newUcmspec != null)
				msgs = ((InternalEObject)newUcmspec).eInverseAdd(this, UcmPackage.UC_MSPEC__SCENARIO_DEFS, UCMspec.class, msgs);
			msgs = basicSetUcmspec(newUcmspec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.SCENARIO_DEF__UCMSPEC, newUcmspec, newUcmspec));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ScenarioGroup getGroups() {
		if (eContainerFeatureID != ScenarioPackage.SCENARIO_DEF__GROUPS) return null;
		return (ScenarioGroup)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGroups(ScenarioGroup newGroups, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newGroups, ScenarioPackage.SCENARIO_DEF__GROUPS, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroups(ScenarioGroup newGroups) {
		if (newGroups != eInternalContainer() || (eContainerFeatureID != ScenarioPackage.SCENARIO_DEF__GROUPS && newGroups != null)) {
			if (EcoreUtil.isAncestor(this, newGroups))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newGroups != null)
				msgs = ((InternalEObject)newGroups).eInverseAdd(this, ScenarioPackage.SCENARIO_GROUP__SCENARIOS, ScenarioGroup.class, msgs);
			msgs = basicSetGroups(newGroups, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.SCENARIO_DEF__GROUPS, newGroups, newGroups));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getParentScenarios() {
		if (parentScenarios == null) {
			parentScenarios = new EObjectWithInverseResolvingEList.ManyInverse(ScenarioDef.class, this, ScenarioPackage.SCENARIO_DEF__PARENT_SCENARIOS, ScenarioPackage.SCENARIO_DEF__INCLUDED_SCENARIOS);
		}
		return parentScenarios;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getIncludedScenarios() {
		if (includedScenarios == null) {
			includedScenarios = new EObjectWithInverseResolvingEList.ManyInverse(ScenarioDef.class, this, ScenarioPackage.SCENARIO_DEF__INCLUDED_SCENARIOS, ScenarioPackage.SCENARIO_DEF__PARENT_SCENARIOS);
		}
		return includedScenarios;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getEndPoints() {
		if (endPoints == null) {
			endPoints = new EObjectWithInverseResolvingEList.ManyInverse(EndPoint.class, this, ScenarioPackage.SCENARIO_DEF__END_POINTS, MapPackage.END_POINT__SCENARIO_DEFS);
		}
		return endPoints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getPreconditions() {
		if (preconditions == null) {
			preconditions = new EObjectContainmentWithInverseEList(Condition.class, this, ScenarioPackage.SCENARIO_DEF__PRECONDITIONS, UrncorePackage.CONDITION__SCENARIO_DEF_PRE);
		}
		return preconditions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getPostconditions() {
		if (postconditions == null) {
			postconditions = new EObjectContainmentWithInverseEList(Condition.class, this, ScenarioPackage.SCENARIO_DEF__POSTCONDITIONS, UrncorePackage.CONDITION__SCENARIO_DEF_POST);
		}
		return postconditions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getVariable() {
		if (variable == null) {
			variable = new EObjectResolvingEList(Variable.class, this, ScenarioPackage.SCENARIO_DEF__VARIABLE);
		}
		return variable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getInitializations() {
		if (initializations == null) {
			initializations = new EObjectContainmentWithInverseEList(Initialization.class, this, ScenarioPackage.SCENARIO_DEF__INITIALIZATIONS, ScenarioPackage.INITIALIZATION__SCENARIO_DEF);
		}
		return initializations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ScenarioPackage.SCENARIO_DEF__START_POINTS:
				return ((InternalEList)getStartPoints()).basicAdd(otherEnd, msgs);
			case ScenarioPackage.SCENARIO_DEF__UCMSPEC:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetUcmspec((UCMspec)otherEnd, msgs);
			case ScenarioPackage.SCENARIO_DEF__GROUPS:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetGroups((ScenarioGroup)otherEnd, msgs);
			case ScenarioPackage.SCENARIO_DEF__PARENT_SCENARIOS:
				return ((InternalEList)getParentScenarios()).basicAdd(otherEnd, msgs);
			case ScenarioPackage.SCENARIO_DEF__INCLUDED_SCENARIOS:
				return ((InternalEList)getIncludedScenarios()).basicAdd(otherEnd, msgs);
			case ScenarioPackage.SCENARIO_DEF__END_POINTS:
				return ((InternalEList)getEndPoints()).basicAdd(otherEnd, msgs);
			case ScenarioPackage.SCENARIO_DEF__PRECONDITIONS:
				return ((InternalEList)getPreconditions()).basicAdd(otherEnd, msgs);
			case ScenarioPackage.SCENARIO_DEF__POSTCONDITIONS:
				return ((InternalEList)getPostconditions()).basicAdd(otherEnd, msgs);
			case ScenarioPackage.SCENARIO_DEF__INITIALIZATIONS:
				return ((InternalEList)getInitializations()).basicAdd(otherEnd, msgs);
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
			case ScenarioPackage.SCENARIO_DEF__START_POINTS:
				return ((InternalEList)getStartPoints()).basicRemove(otherEnd, msgs);
			case ScenarioPackage.SCENARIO_DEF__UCMSPEC:
				return basicSetUcmspec(null, msgs);
			case ScenarioPackage.SCENARIO_DEF__GROUPS:
				return basicSetGroups(null, msgs);
			case ScenarioPackage.SCENARIO_DEF__PARENT_SCENARIOS:
				return ((InternalEList)getParentScenarios()).basicRemove(otherEnd, msgs);
			case ScenarioPackage.SCENARIO_DEF__INCLUDED_SCENARIOS:
				return ((InternalEList)getIncludedScenarios()).basicRemove(otherEnd, msgs);
			case ScenarioPackage.SCENARIO_DEF__END_POINTS:
				return ((InternalEList)getEndPoints()).basicRemove(otherEnd, msgs);
			case ScenarioPackage.SCENARIO_DEF__PRECONDITIONS:
				return ((InternalEList)getPreconditions()).basicRemove(otherEnd, msgs);
			case ScenarioPackage.SCENARIO_DEF__POSTCONDITIONS:
				return ((InternalEList)getPostconditions()).basicRemove(otherEnd, msgs);
			case ScenarioPackage.SCENARIO_DEF__INITIALIZATIONS:
				return ((InternalEList)getInitializations()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID) {
			case ScenarioPackage.SCENARIO_DEF__UCMSPEC:
				return eInternalContainer().eInverseRemove(this, UcmPackage.UC_MSPEC__SCENARIO_DEFS, UCMspec.class, msgs);
			case ScenarioPackage.SCENARIO_DEF__GROUPS:
				return eInternalContainer().eInverseRemove(this, ScenarioPackage.SCENARIO_GROUP__SCENARIOS, ScenarioGroup.class, msgs);
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
			case ScenarioPackage.SCENARIO_DEF__START_POINTS:
				return getStartPoints();
			case ScenarioPackage.SCENARIO_DEF__UCMSPEC:
				return getUcmspec();
			case ScenarioPackage.SCENARIO_DEF__GROUPS:
				return getGroups();
			case ScenarioPackage.SCENARIO_DEF__PARENT_SCENARIOS:
				return getParentScenarios();
			case ScenarioPackage.SCENARIO_DEF__INCLUDED_SCENARIOS:
				return getIncludedScenarios();
			case ScenarioPackage.SCENARIO_DEF__END_POINTS:
				return getEndPoints();
			case ScenarioPackage.SCENARIO_DEF__PRECONDITIONS:
				return getPreconditions();
			case ScenarioPackage.SCENARIO_DEF__POSTCONDITIONS:
				return getPostconditions();
			case ScenarioPackage.SCENARIO_DEF__VARIABLE:
				return getVariable();
			case ScenarioPackage.SCENARIO_DEF__INITIALIZATIONS:
				return getInitializations();
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
			case ScenarioPackage.SCENARIO_DEF__START_POINTS:
				getStartPoints().clear();
				getStartPoints().addAll((Collection)newValue);
				return;
			case ScenarioPackage.SCENARIO_DEF__UCMSPEC:
				setUcmspec((UCMspec)newValue);
				return;
			case ScenarioPackage.SCENARIO_DEF__GROUPS:
				setGroups((ScenarioGroup)newValue);
				return;
			case ScenarioPackage.SCENARIO_DEF__PARENT_SCENARIOS:
				getParentScenarios().clear();
				getParentScenarios().addAll((Collection)newValue);
				return;
			case ScenarioPackage.SCENARIO_DEF__INCLUDED_SCENARIOS:
				getIncludedScenarios().clear();
				getIncludedScenarios().addAll((Collection)newValue);
				return;
			case ScenarioPackage.SCENARIO_DEF__END_POINTS:
				getEndPoints().clear();
				getEndPoints().addAll((Collection)newValue);
				return;
			case ScenarioPackage.SCENARIO_DEF__PRECONDITIONS:
				getPreconditions().clear();
				getPreconditions().addAll((Collection)newValue);
				return;
			case ScenarioPackage.SCENARIO_DEF__POSTCONDITIONS:
				getPostconditions().clear();
				getPostconditions().addAll((Collection)newValue);
				return;
			case ScenarioPackage.SCENARIO_DEF__VARIABLE:
				getVariable().clear();
				getVariable().addAll((Collection)newValue);
				return;
			case ScenarioPackage.SCENARIO_DEF__INITIALIZATIONS:
				getInitializations().clear();
				getInitializations().addAll((Collection)newValue);
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
			case ScenarioPackage.SCENARIO_DEF__START_POINTS:
				getStartPoints().clear();
				return;
			case ScenarioPackage.SCENARIO_DEF__UCMSPEC:
				setUcmspec((UCMspec)null);
				return;
			case ScenarioPackage.SCENARIO_DEF__GROUPS:
				setGroups((ScenarioGroup)null);
				return;
			case ScenarioPackage.SCENARIO_DEF__PARENT_SCENARIOS:
				getParentScenarios().clear();
				return;
			case ScenarioPackage.SCENARIO_DEF__INCLUDED_SCENARIOS:
				getIncludedScenarios().clear();
				return;
			case ScenarioPackage.SCENARIO_DEF__END_POINTS:
				getEndPoints().clear();
				return;
			case ScenarioPackage.SCENARIO_DEF__PRECONDITIONS:
				getPreconditions().clear();
				return;
			case ScenarioPackage.SCENARIO_DEF__POSTCONDITIONS:
				getPostconditions().clear();
				return;
			case ScenarioPackage.SCENARIO_DEF__VARIABLE:
				getVariable().clear();
				return;
			case ScenarioPackage.SCENARIO_DEF__INITIALIZATIONS:
				getInitializations().clear();
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
			case ScenarioPackage.SCENARIO_DEF__START_POINTS:
				return startPoints != null && !startPoints.isEmpty();
			case ScenarioPackage.SCENARIO_DEF__UCMSPEC:
				return getUcmspec() != null;
			case ScenarioPackage.SCENARIO_DEF__GROUPS:
				return getGroups() != null;
			case ScenarioPackage.SCENARIO_DEF__PARENT_SCENARIOS:
				return parentScenarios != null && !parentScenarios.isEmpty();
			case ScenarioPackage.SCENARIO_DEF__INCLUDED_SCENARIOS:
				return includedScenarios != null && !includedScenarios.isEmpty();
			case ScenarioPackage.SCENARIO_DEF__END_POINTS:
				return endPoints != null && !endPoints.isEmpty();
			case ScenarioPackage.SCENARIO_DEF__PRECONDITIONS:
				return preconditions != null && !preconditions.isEmpty();
			case ScenarioPackage.SCENARIO_DEF__POSTCONDITIONS:
				return postconditions != null && !postconditions.isEmpty();
			case ScenarioPackage.SCENARIO_DEF__VARIABLE:
				return variable != null && !variable.isEmpty();
			case ScenarioPackage.SCENARIO_DEF__INITIALIZATIONS:
				return initializations != null && !initializations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ScenarioDefImpl
