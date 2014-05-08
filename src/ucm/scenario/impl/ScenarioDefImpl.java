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
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import ucm.scenario.Initialization;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioEndPoint;
import ucm.scenario.ScenarioGroup;
import ucm.scenario.ScenarioPackage;
import ucm.scenario.ScenarioStartPoint;
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
 *   <li>{@link ucm.scenario.impl.ScenarioDefImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link ucm.scenario.impl.ScenarioDefImpl#getParentScenarios <em>Parent Scenarios</em>}</li>
 *   <li>{@link ucm.scenario.impl.ScenarioDefImpl#getIncludedScenarios <em>Included Scenarios</em>}</li>
 *   <li>{@link ucm.scenario.impl.ScenarioDefImpl#getPreconditions <em>Preconditions</em>}</li>
 *   <li>{@link ucm.scenario.impl.ScenarioDefImpl#getPostconditions <em>Postconditions</em>}</li>
 *   <li>{@link ucm.scenario.impl.ScenarioDefImpl#getInitializations <em>Initializations</em>}</li>
 *   <li>{@link ucm.scenario.impl.ScenarioDefImpl#getStartPoints <em>Start Points</em>}</li>
 *   <li>{@link ucm.scenario.impl.ScenarioDefImpl#getEndPoints <em>End Points</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScenarioDefImpl extends UCMmodelElementImpl implements ScenarioDef {
    /**
	 * The cached value of the '{@link #getParentScenarios() <em>Parent Scenarios</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentScenarios()
	 * @generated
	 * @ordered
	 */
	protected EList parentScenarios;

    /**
	 * The cached value of the '{@link #getIncludedScenarios() <em>Included Scenarios</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncludedScenarios()
	 * @generated
	 * @ordered
	 */
	protected EList includedScenarios;

    /**
	 * The cached value of the '{@link #getPreconditions() <em>Preconditions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreconditions()
	 * @generated
	 * @ordered
	 */
	protected EList preconditions;

    /**
	 * The cached value of the '{@link #getPostconditions() <em>Postconditions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPostconditions()
	 * @generated
	 * @ordered
	 */
	protected EList postconditions;

    /**
	 * The cached value of the '{@link #getInitializations() <em>Initializations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitializations()
	 * @generated
	 * @ordered
	 */
	protected EList initializations;

    /**
	 * The cached value of the '{@link #getStartPoints() <em>Start Points</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getStartPoints()
	 * @generated
	 * @ordered
	 */
    protected EList startPoints;

    /**
	 * The cached value of the '{@link #getEndPoints() <em>End Points</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndPoints()
	 * @generated
	 * @ordered
	 */
	protected EList endPoints;

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
			startPoints = new EObjectContainmentWithInverseEList(ScenarioStartPoint.class, this, ScenarioPackage.SCENARIO_DEF__START_POINTS, ScenarioPackage.SCENARIO_START_POINT__SCENARIO_DEF);
		}
		return startPoints;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScenarioGroup getGroup() {
		if (eContainerFeatureID() != ScenarioPackage.SCENARIO_DEF__GROUP) return null;
		return (ScenarioGroup)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGroup(ScenarioGroup newGroup, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newGroup, ScenarioPackage.SCENARIO_DEF__GROUP, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroup(ScenarioGroup newGroup) {
		if (newGroup != eInternalContainer() || (eContainerFeatureID() != ScenarioPackage.SCENARIO_DEF__GROUP && newGroup != null)) {
			if (EcoreUtil.isAncestor(this, newGroup))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newGroup != null)
				msgs = ((InternalEObject)newGroup).eInverseAdd(this, ScenarioPackage.SCENARIO_GROUP__SCENARIOS, ScenarioGroup.class, msgs);
			msgs = basicSetGroup(newGroup, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.SCENARIO_DEF__GROUP, newGroup, newGroup));
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
			endPoints = new EObjectContainmentWithInverseEList(ScenarioEndPoint.class, this, ScenarioPackage.SCENARIO_DEF__END_POINTS, ScenarioPackage.SCENARIO_END_POINT__SCENARIO_DEF);
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
			case ScenarioPackage.SCENARIO_DEF__GROUP:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetGroup((ScenarioGroup)otherEnd, msgs);
			case ScenarioPackage.SCENARIO_DEF__PARENT_SCENARIOS:
				return ((InternalEList)getParentScenarios()).basicAdd(otherEnd, msgs);
			case ScenarioPackage.SCENARIO_DEF__INCLUDED_SCENARIOS:
				return ((InternalEList)getIncludedScenarios()).basicAdd(otherEnd, msgs);
			case ScenarioPackage.SCENARIO_DEF__PRECONDITIONS:
				return ((InternalEList)getPreconditions()).basicAdd(otherEnd, msgs);
			case ScenarioPackage.SCENARIO_DEF__POSTCONDITIONS:
				return ((InternalEList)getPostconditions()).basicAdd(otherEnd, msgs);
			case ScenarioPackage.SCENARIO_DEF__INITIALIZATIONS:
				return ((InternalEList)getInitializations()).basicAdd(otherEnd, msgs);
			case ScenarioPackage.SCENARIO_DEF__START_POINTS:
				return ((InternalEList)getStartPoints()).basicAdd(otherEnd, msgs);
			case ScenarioPackage.SCENARIO_DEF__END_POINTS:
				return ((InternalEList)getEndPoints()).basicAdd(otherEnd, msgs);
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
			case ScenarioPackage.SCENARIO_DEF__GROUP:
				return basicSetGroup(null, msgs);
			case ScenarioPackage.SCENARIO_DEF__PARENT_SCENARIOS:
				return ((InternalEList)getParentScenarios()).basicRemove(otherEnd, msgs);
			case ScenarioPackage.SCENARIO_DEF__INCLUDED_SCENARIOS:
				return ((InternalEList)getIncludedScenarios()).basicRemove(otherEnd, msgs);
			case ScenarioPackage.SCENARIO_DEF__PRECONDITIONS:
				return ((InternalEList)getPreconditions()).basicRemove(otherEnd, msgs);
			case ScenarioPackage.SCENARIO_DEF__POSTCONDITIONS:
				return ((InternalEList)getPostconditions()).basicRemove(otherEnd, msgs);
			case ScenarioPackage.SCENARIO_DEF__INITIALIZATIONS:
				return ((InternalEList)getInitializations()).basicRemove(otherEnd, msgs);
			case ScenarioPackage.SCENARIO_DEF__START_POINTS:
				return ((InternalEList)getStartPoints()).basicRemove(otherEnd, msgs);
			case ScenarioPackage.SCENARIO_DEF__END_POINTS:
				return ((InternalEList)getEndPoints()).basicRemove(otherEnd, msgs);
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
			case ScenarioPackage.SCENARIO_DEF__GROUP:
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
			case ScenarioPackage.SCENARIO_DEF__GROUP:
				return getGroup();
			case ScenarioPackage.SCENARIO_DEF__PARENT_SCENARIOS:
				return getParentScenarios();
			case ScenarioPackage.SCENARIO_DEF__INCLUDED_SCENARIOS:
				return getIncludedScenarios();
			case ScenarioPackage.SCENARIO_DEF__PRECONDITIONS:
				return getPreconditions();
			case ScenarioPackage.SCENARIO_DEF__POSTCONDITIONS:
				return getPostconditions();
			case ScenarioPackage.SCENARIO_DEF__INITIALIZATIONS:
				return getInitializations();
			case ScenarioPackage.SCENARIO_DEF__START_POINTS:
				return getStartPoints();
			case ScenarioPackage.SCENARIO_DEF__END_POINTS:
				return getEndPoints();
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
			case ScenarioPackage.SCENARIO_DEF__GROUP:
				setGroup((ScenarioGroup)newValue);
				return;
			case ScenarioPackage.SCENARIO_DEF__PARENT_SCENARIOS:
				getParentScenarios().clear();
				getParentScenarios().addAll((Collection)newValue);
				return;
			case ScenarioPackage.SCENARIO_DEF__INCLUDED_SCENARIOS:
				getIncludedScenarios().clear();
				getIncludedScenarios().addAll((Collection)newValue);
				return;
			case ScenarioPackage.SCENARIO_DEF__PRECONDITIONS:
				getPreconditions().clear();
				getPreconditions().addAll((Collection)newValue);
				return;
			case ScenarioPackage.SCENARIO_DEF__POSTCONDITIONS:
				getPostconditions().clear();
				getPostconditions().addAll((Collection)newValue);
				return;
			case ScenarioPackage.SCENARIO_DEF__INITIALIZATIONS:
				getInitializations().clear();
				getInitializations().addAll((Collection)newValue);
				return;
			case ScenarioPackage.SCENARIO_DEF__START_POINTS:
				getStartPoints().clear();
				getStartPoints().addAll((Collection)newValue);
				return;
			case ScenarioPackage.SCENARIO_DEF__END_POINTS:
				getEndPoints().clear();
				getEndPoints().addAll((Collection)newValue);
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
			case ScenarioPackage.SCENARIO_DEF__GROUP:
				setGroup((ScenarioGroup)null);
				return;
			case ScenarioPackage.SCENARIO_DEF__PARENT_SCENARIOS:
				getParentScenarios().clear();
				return;
			case ScenarioPackage.SCENARIO_DEF__INCLUDED_SCENARIOS:
				getIncludedScenarios().clear();
				return;
			case ScenarioPackage.SCENARIO_DEF__PRECONDITIONS:
				getPreconditions().clear();
				return;
			case ScenarioPackage.SCENARIO_DEF__POSTCONDITIONS:
				getPostconditions().clear();
				return;
			case ScenarioPackage.SCENARIO_DEF__INITIALIZATIONS:
				getInitializations().clear();
				return;
			case ScenarioPackage.SCENARIO_DEF__START_POINTS:
				getStartPoints().clear();
				return;
			case ScenarioPackage.SCENARIO_DEF__END_POINTS:
				getEndPoints().clear();
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
			case ScenarioPackage.SCENARIO_DEF__GROUP:
				return getGroup() != null;
			case ScenarioPackage.SCENARIO_DEF__PARENT_SCENARIOS:
				return parentScenarios != null && !parentScenarios.isEmpty();
			case ScenarioPackage.SCENARIO_DEF__INCLUDED_SCENARIOS:
				return includedScenarios != null && !includedScenarios.isEmpty();
			case ScenarioPackage.SCENARIO_DEF__PRECONDITIONS:
				return preconditions != null && !preconditions.isEmpty();
			case ScenarioPackage.SCENARIO_DEF__POSTCONDITIONS:
				return postconditions != null && !postconditions.isEmpty();
			case ScenarioPackage.SCENARIO_DEF__INITIALIZATIONS:
				return initializations != null && !initializations.isEmpty();
			case ScenarioPackage.SCENARIO_DEF__START_POINTS:
				return startPoints != null && !startPoints.isEmpty();
			case ScenarioPackage.SCENARIO_DEF__END_POINTS:
				return endPoints != null && !endPoints.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ScenarioDefImpl
