/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import ucm.map.InBinding;
import ucm.map.MapPackage;
import ucm.map.StartPoint;
import ucm.performance.PerformancePackage;
import ucm.performance.Workload;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioPackage;
import urncore.Condition;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Start Point</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.map.impl.StartPointImpl#getWorkload <em>Workload</em>}</li>
 *   <li>{@link ucm.map.impl.StartPointImpl#getScenarioDefs <em>Scenario Defs</em>}</li>
 *   <li>{@link ucm.map.impl.StartPointImpl#getInBindings <em>In Bindings</em>}</li>
 *   <li>{@link ucm.map.impl.StartPointImpl#getPrecondition <em>Precondition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StartPointImpl extends PathNodeImpl implements StartPoint {
	/**
	 * The cached value of the '{@link #getWorkload() <em>Workload</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getWorkload()
	 * @generated
	 * @ordered
	 */
    protected Workload workload = null;

	/**
	 * The cached value of the '{@link #getScenarioDefs() <em>Scenario Defs</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScenarioDefs()
	 * @generated
	 * @ordered
	 */
	protected EList scenarioDefs = null;

	/**
	 * The cached value of the '{@link #getInBindings() <em>In Bindings</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getInBindings()
	 * @generated
	 * @ordered
	 */
    protected EList inBindings = null;

	/**
	 * The cached value of the '{@link #getPrecondition() <em>Precondition</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPrecondition()
	 * @generated
	 * @ordered
	 */
    protected Condition precondition = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected StartPointImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return MapPackage.Literals.START_POINT;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Workload getWorkload() {
		return workload;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetWorkload(Workload newWorkload, NotificationChain msgs) {
		Workload oldWorkload = workload;
		workload = newWorkload;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.START_POINT__WORKLOAD, oldWorkload, newWorkload);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setWorkload(Workload newWorkload) {
		if (newWorkload != workload) {
			NotificationChain msgs = null;
			if (workload != null)
				msgs = ((InternalEObject)workload).eInverseRemove(this, PerformancePackage.WORKLOAD__START_POINT, Workload.class, msgs);
			if (newWorkload != null)
				msgs = ((InternalEObject)newWorkload).eInverseAdd(this, PerformancePackage.WORKLOAD__START_POINT, Workload.class, msgs);
			msgs = basicSetWorkload(newWorkload, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.START_POINT__WORKLOAD, newWorkload, newWorkload));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getScenarioDefs() {
		if (scenarioDefs == null) {
			scenarioDefs = new EObjectWithInverseResolvingEList.ManyInverse(ScenarioDef.class, this, MapPackage.START_POINT__SCENARIO_DEFS, ScenarioPackage.SCENARIO_DEF__START_POINTS);
		}
		return scenarioDefs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getInBindings() {
		if (inBindings == null) {
			inBindings = new EObjectWithInverseResolvingEList(InBinding.class, this, MapPackage.START_POINT__IN_BINDINGS, MapPackage.IN_BINDING__START_POINT);
		}
		return inBindings;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Condition getPrecondition() {
		return precondition;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetPrecondition(Condition newPrecondition, NotificationChain msgs) {
		Condition oldPrecondition = precondition;
		precondition = newPrecondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.START_POINT__PRECONDITION, oldPrecondition, newPrecondition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setPrecondition(Condition newPrecondition) {
		if (newPrecondition != precondition) {
			NotificationChain msgs = null;
			if (precondition != null)
				msgs = ((InternalEObject)precondition).eInverseRemove(this, UrncorePackage.CONDITION__START_POINT, Condition.class, msgs);
			if (newPrecondition != null)
				msgs = ((InternalEObject)newPrecondition).eInverseAdd(this, UrncorePackage.CONDITION__START_POINT, Condition.class, msgs);
			msgs = basicSetPrecondition(newPrecondition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.START_POINT__PRECONDITION, newPrecondition, newPrecondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MapPackage.START_POINT__WORKLOAD:
				if (workload != null)
					msgs = ((InternalEObject)workload).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MapPackage.START_POINT__WORKLOAD, null, msgs);
				return basicSetWorkload((Workload)otherEnd, msgs);
			case MapPackage.START_POINT__SCENARIO_DEFS:
				return ((InternalEList)getScenarioDefs()).basicAdd(otherEnd, msgs);
			case MapPackage.START_POINT__IN_BINDINGS:
				return ((InternalEList)getInBindings()).basicAdd(otherEnd, msgs);
			case MapPackage.START_POINT__PRECONDITION:
				if (precondition != null)
					msgs = ((InternalEObject)precondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MapPackage.START_POINT__PRECONDITION, null, msgs);
				return basicSetPrecondition((Condition)otherEnd, msgs);
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
			case MapPackage.START_POINT__WORKLOAD:
				return basicSetWorkload(null, msgs);
			case MapPackage.START_POINT__SCENARIO_DEFS:
				return ((InternalEList)getScenarioDefs()).basicRemove(otherEnd, msgs);
			case MapPackage.START_POINT__IN_BINDINGS:
				return ((InternalEList)getInBindings()).basicRemove(otherEnd, msgs);
			case MapPackage.START_POINT__PRECONDITION:
				return basicSetPrecondition(null, msgs);
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
			case MapPackage.START_POINT__WORKLOAD:
				return getWorkload();
			case MapPackage.START_POINT__SCENARIO_DEFS:
				return getScenarioDefs();
			case MapPackage.START_POINT__IN_BINDINGS:
				return getInBindings();
			case MapPackage.START_POINT__PRECONDITION:
				return getPrecondition();
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
			case MapPackage.START_POINT__WORKLOAD:
				setWorkload((Workload)newValue);
				return;
			case MapPackage.START_POINT__SCENARIO_DEFS:
				getScenarioDefs().clear();
				getScenarioDefs().addAll((Collection)newValue);
				return;
			case MapPackage.START_POINT__IN_BINDINGS:
				getInBindings().clear();
				getInBindings().addAll((Collection)newValue);
				return;
			case MapPackage.START_POINT__PRECONDITION:
				setPrecondition((Condition)newValue);
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
			case MapPackage.START_POINT__WORKLOAD:
				setWorkload((Workload)null);
				return;
			case MapPackage.START_POINT__SCENARIO_DEFS:
				getScenarioDefs().clear();
				return;
			case MapPackage.START_POINT__IN_BINDINGS:
				getInBindings().clear();
				return;
			case MapPackage.START_POINT__PRECONDITION:
				setPrecondition((Condition)null);
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
			case MapPackage.START_POINT__WORKLOAD:
				return workload != null;
			case MapPackage.START_POINT__SCENARIO_DEFS:
				return scenarioDefs != null && !scenarioDefs.isEmpty();
			case MapPackage.START_POINT__IN_BINDINGS:
				return inBindings != null && !inBindings.isEmpty();
			case MapPackage.START_POINT__PRECONDITION:
				return precondition != null;
		}
		return super.eIsSet(featureID);
	}

} //StartPointImpl
