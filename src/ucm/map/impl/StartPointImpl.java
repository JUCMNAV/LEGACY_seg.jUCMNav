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

import ucm.map.FailureKind;
import ucm.map.InBinding;
import ucm.map.MapPackage;
import ucm.map.StartPoint;
import ucm.performance.PerformancePackage;
import ucm.performance.Workload;
import ucm.scenario.ScenarioPackage;
import ucm.scenario.ScenarioStartPoint;
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
 *   <li>{@link ucm.map.impl.StartPointImpl#isLocal <em>Local</em>}</li>
 *   <li>{@link ucm.map.impl.StartPointImpl#getFailureKind <em>Failure Kind</em>}</li>
 *   <li>{@link ucm.map.impl.StartPointImpl#getInBindings <em>In Bindings</em>}</li>
 *   <li>{@link ucm.map.impl.StartPointImpl#getPrecondition <em>Precondition</em>}</li>
 *   <li>{@link ucm.map.impl.StartPointImpl#getScenarioStartPoints <em>Scenario Start Points</em>}</li>
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
    protected Workload workload;

    /**
	 * The default value of the '{@link #isLocal() <em>Local</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isLocal()
	 * @generated
	 * @ordered
	 */
    protected static final boolean LOCAL_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isLocal() <em>Local</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isLocal()
	 * @generated
	 * @ordered
	 */
    protected boolean local = LOCAL_EDEFAULT;

    /**
	 * The default value of the '{@link #getFailureKind() <em>Failure Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getFailureKind()
	 * @generated
	 * @ordered
	 */
    protected static final FailureKind FAILURE_KIND_EDEFAULT = FailureKind.NONE_LITERAL;

    /**
	 * The cached value of the '{@link #getFailureKind() <em>Failure Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getFailureKind()
	 * @generated
	 * @ordered
	 */
    protected FailureKind failureKind = FAILURE_KIND_EDEFAULT;

    /**
	 * The cached value of the '{@link #getInBindings() <em>In Bindings</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getInBindings()
	 * @generated
	 * @ordered
	 */
    protected EList inBindings;

    /**
	 * The cached value of the '{@link #getPrecondition() <em>Precondition</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPrecondition()
	 * @generated
	 * @ordered
	 */
    protected Condition precondition;

    /**
	 * The cached value of the '{@link #getScenarioStartPoints() <em>Scenario Start Points</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScenarioStartPoints()
	 * @generated
	 * @ordered
	 */
	protected EList scenarioStartPoints;

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
    public boolean isLocal() {
		return local;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setLocal(boolean newLocal) {
		boolean oldLocal = local;
		local = newLocal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.START_POINT__LOCAL, oldLocal, local));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public FailureKind getFailureKind() {
		return failureKind;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setFailureKind(FailureKind newFailureKind) {
		FailureKind oldFailureKind = failureKind;
		failureKind = newFailureKind == null ? FAILURE_KIND_EDEFAULT : newFailureKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.START_POINT__FAILURE_KIND, oldFailureKind, failureKind));
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
	public EList getScenarioStartPoints() {
		if (scenarioStartPoints == null) {
			scenarioStartPoints = new EObjectWithInverseResolvingEList(ScenarioStartPoint.class, this, MapPackage.START_POINT__SCENARIO_START_POINTS, ScenarioPackage.SCENARIO_START_POINT__START_POINT);
		}
		return scenarioStartPoints;
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
			case MapPackage.START_POINT__IN_BINDINGS:
				return ((InternalEList)getInBindings()).basicAdd(otherEnd, msgs);
			case MapPackage.START_POINT__PRECONDITION:
				if (precondition != null)
					msgs = ((InternalEObject)precondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MapPackage.START_POINT__PRECONDITION, null, msgs);
				return basicSetPrecondition((Condition)otherEnd, msgs);
			case MapPackage.START_POINT__SCENARIO_START_POINTS:
				return ((InternalEList)getScenarioStartPoints()).basicAdd(otherEnd, msgs);
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
			case MapPackage.START_POINT__IN_BINDINGS:
				return ((InternalEList)getInBindings()).basicRemove(otherEnd, msgs);
			case MapPackage.START_POINT__PRECONDITION:
				return basicSetPrecondition(null, msgs);
			case MapPackage.START_POINT__SCENARIO_START_POINTS:
				return ((InternalEList)getScenarioStartPoints()).basicRemove(otherEnd, msgs);
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
			case MapPackage.START_POINT__LOCAL:
				return isLocal() ? Boolean.TRUE : Boolean.FALSE;
			case MapPackage.START_POINT__FAILURE_KIND:
				return getFailureKind();
			case MapPackage.START_POINT__IN_BINDINGS:
				return getInBindings();
			case MapPackage.START_POINT__PRECONDITION:
				return getPrecondition();
			case MapPackage.START_POINT__SCENARIO_START_POINTS:
				return getScenarioStartPoints();
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
			case MapPackage.START_POINT__LOCAL:
				setLocal(((Boolean)newValue).booleanValue());
				return;
			case MapPackage.START_POINT__FAILURE_KIND:
				setFailureKind((FailureKind)newValue);
				return;
			case MapPackage.START_POINT__IN_BINDINGS:
				getInBindings().clear();
				getInBindings().addAll((Collection)newValue);
				return;
			case MapPackage.START_POINT__PRECONDITION:
				setPrecondition((Condition)newValue);
				return;
			case MapPackage.START_POINT__SCENARIO_START_POINTS:
				getScenarioStartPoints().clear();
				getScenarioStartPoints().addAll((Collection)newValue);
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
			case MapPackage.START_POINT__LOCAL:
				setLocal(LOCAL_EDEFAULT);
				return;
			case MapPackage.START_POINT__FAILURE_KIND:
				setFailureKind(FAILURE_KIND_EDEFAULT);
				return;
			case MapPackage.START_POINT__IN_BINDINGS:
				getInBindings().clear();
				return;
			case MapPackage.START_POINT__PRECONDITION:
				setPrecondition((Condition)null);
				return;
			case MapPackage.START_POINT__SCENARIO_START_POINTS:
				getScenarioStartPoints().clear();
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
			case MapPackage.START_POINT__LOCAL:
				return local != LOCAL_EDEFAULT;
			case MapPackage.START_POINT__FAILURE_KIND:
				return failureKind != FAILURE_KIND_EDEFAULT;
			case MapPackage.START_POINT__IN_BINDINGS:
				return inBindings != null && !inBindings.isEmpty();
			case MapPackage.START_POINT__PRECONDITION:
				return precondition != null;
			case MapPackage.START_POINT__SCENARIO_START_POINTS:
				return scenarioStartPoints != null && !scenarioStartPoints.isEmpty();
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
		result.append(" (local: ");
		result.append(local);
		result.append(", failureKind: ");
		result.append(failureKind);
		result.append(')');
		return result.toString();
	}

} //StartPointImpl
