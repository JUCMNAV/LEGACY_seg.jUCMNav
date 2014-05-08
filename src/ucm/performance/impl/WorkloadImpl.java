/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.performance.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import ucm.map.MapPackage;
import ucm.map.StartPoint;
import ucm.performance.ArrivalProcess;
import ucm.performance.PerformancePackage;
import ucm.performance.TimeUnit;
import ucm.performance.Workload;
import urncore.impl.UCMmodelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Workload</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.performance.impl.WorkloadImpl#isClosed <em>Closed</em>}</li>
 *   <li>{@link ucm.performance.impl.WorkloadImpl#getArrivalPattern <em>Arrival Pattern</em>}</li>
 *   <li>{@link ucm.performance.impl.WorkloadImpl#getArrivalParam1 <em>Arrival Param1</em>}</li>
 *   <li>{@link ucm.performance.impl.WorkloadImpl#getArrivalParam2 <em>Arrival Param2</em>}</li>
 *   <li>{@link ucm.performance.impl.WorkloadImpl#getExternalDelay <em>External Delay</em>}</li>
 *   <li>{@link ucm.performance.impl.WorkloadImpl#getValue <em>Value</em>}</li>
 *   <li>{@link ucm.performance.impl.WorkloadImpl#getCoeffVarSeq <em>Coeff Var Seq</em>}</li>
 *   <li>{@link ucm.performance.impl.WorkloadImpl#getPopulation <em>Population</em>}</li>
 *   <li>{@link ucm.performance.impl.WorkloadImpl#getUnit <em>Unit</em>}</li>
 *   <li>{@link ucm.performance.impl.WorkloadImpl#getStartPoint <em>Start Point</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WorkloadImpl extends UCMmodelElementImpl implements Workload {
    /**
	 * The default value of the '{@link #isClosed() <em>Closed</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isClosed()
	 * @generated
	 * @ordered
	 */
    protected static final boolean CLOSED_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isClosed() <em>Closed</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isClosed()
	 * @generated
	 * @ordered
	 */
    protected boolean closed = CLOSED_EDEFAULT;

    /**
	 * The default value of the '{@link #getArrivalPattern() <em>Arrival Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getArrivalPattern()
	 * @generated
	 * @ordered
	 */
    protected static final ArrivalProcess ARRIVAL_PATTERN_EDEFAULT = ArrivalProcess.POISSON_PDF_LITERAL;

    /**
	 * The cached value of the '{@link #getArrivalPattern() <em>Arrival Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getArrivalPattern()
	 * @generated
	 * @ordered
	 */
    protected ArrivalProcess arrivalPattern = ARRIVAL_PATTERN_EDEFAULT;

    /**
	 * The default value of the '{@link #getArrivalParam1() <em>Arrival Param1</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getArrivalParam1()
	 * @generated
	 * @ordered
	 */
    protected static final String ARRIVAL_PARAM1_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getArrivalParam1() <em>Arrival Param1</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getArrivalParam1()
	 * @generated
	 * @ordered
	 */
    protected String arrivalParam1 = ARRIVAL_PARAM1_EDEFAULT;

    /**
	 * The default value of the '{@link #getArrivalParam2() <em>Arrival Param2</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getArrivalParam2()
	 * @generated
	 * @ordered
	 */
    protected static final String ARRIVAL_PARAM2_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getArrivalParam2() <em>Arrival Param2</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getArrivalParam2()
	 * @generated
	 * @ordered
	 */
    protected String arrivalParam2 = ARRIVAL_PARAM2_EDEFAULT;

    /**
	 * The default value of the '{@link #getExternalDelay() <em>External Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getExternalDelay()
	 * @generated
	 * @ordered
	 */
    protected static final String EXTERNAL_DELAY_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getExternalDelay() <em>External Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getExternalDelay()
	 * @generated
	 * @ordered
	 */
    protected String externalDelay = EXTERNAL_DELAY_EDEFAULT;

    /**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
    protected static final String VALUE_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
    protected String value = VALUE_EDEFAULT;

    /**
	 * The default value of the '{@link #getCoeffVarSeq() <em>Coeff Var Seq</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCoeffVarSeq()
	 * @generated
	 * @ordered
	 */
    protected static final String COEFF_VAR_SEQ_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getCoeffVarSeq() <em>Coeff Var Seq</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCoeffVarSeq()
	 * @generated
	 * @ordered
	 */
    protected String coeffVarSeq = COEFF_VAR_SEQ_EDEFAULT;

    /**
	 * The default value of the '{@link #getPopulation() <em>Population</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPopulation()
	 * @generated
	 * @ordered
	 */
    protected static final String POPULATION_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getPopulation() <em>Population</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPopulation()
	 * @generated
	 * @ordered
	 */
    protected String population = POPULATION_EDEFAULT;

    /**
	 * The default value of the '{@link #getUnit() <em>Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getUnit()
	 * @generated
	 * @ordered
	 */
    protected static final TimeUnit UNIT_EDEFAULT = TimeUnit.MS_LITERAL;

    /**
	 * The cached value of the '{@link #getUnit() <em>Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getUnit()
	 * @generated
	 * @ordered
	 */
    protected TimeUnit unit = UNIT_EDEFAULT;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected WorkloadImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return PerformancePackage.Literals.WORKLOAD;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isClosed() {
		return closed;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setClosed(boolean newClosed) {
		boolean oldClosed = closed;
		closed = newClosed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.WORKLOAD__CLOSED, oldClosed, closed));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ArrivalProcess getArrivalPattern() {
		return arrivalPattern;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setArrivalPattern(ArrivalProcess newArrivalPattern) {
		ArrivalProcess oldArrivalPattern = arrivalPattern;
		arrivalPattern = newArrivalPattern == null ? ARRIVAL_PATTERN_EDEFAULT : newArrivalPattern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.WORKLOAD__ARRIVAL_PATTERN, oldArrivalPattern, arrivalPattern));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getArrivalParam1() {
		return arrivalParam1;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArrivalParam1(String newArrivalParam1) {
		String oldArrivalParam1 = arrivalParam1;
		arrivalParam1 = newArrivalParam1;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.WORKLOAD__ARRIVAL_PARAM1, oldArrivalParam1, arrivalParam1));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getArrivalParam2() {
		return arrivalParam2;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArrivalParam2(String newArrivalParam2) {
		String oldArrivalParam2 = arrivalParam2;
		arrivalParam2 = newArrivalParam2;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.WORKLOAD__ARRIVAL_PARAM2, oldArrivalParam2, arrivalParam2));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getExternalDelay() {
		return externalDelay;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExternalDelay(String newExternalDelay) {
		String oldExternalDelay = externalDelay;
		externalDelay = newExternalDelay;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.WORKLOAD__EXTERNAL_DELAY, oldExternalDelay, externalDelay));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getValue() {
		return value;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(String newValue) {
		String oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.WORKLOAD__VALUE, oldValue, value));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getCoeffVarSeq() {
		return coeffVarSeq;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCoeffVarSeq(String newCoeffVarSeq) {
		String oldCoeffVarSeq = coeffVarSeq;
		coeffVarSeq = newCoeffVarSeq;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.WORKLOAD__COEFF_VAR_SEQ, oldCoeffVarSeq, coeffVarSeq));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getPopulation() {
		return population;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPopulation(String newPopulation) {
		String oldPopulation = population;
		population = newPopulation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.WORKLOAD__POPULATION, oldPopulation, population));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public TimeUnit getUnit() {
		return unit;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setUnit(TimeUnit newUnit) {
		TimeUnit oldUnit = unit;
		unit = newUnit == null ? UNIT_EDEFAULT : newUnit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.WORKLOAD__UNIT, oldUnit, unit));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public StartPoint getStartPoint() {
		if (eContainerFeatureID() != PerformancePackage.WORKLOAD__START_POINT) return null;
		return (StartPoint)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStartPoint(StartPoint newStartPoint, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newStartPoint, PerformancePackage.WORKLOAD__START_POINT, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setStartPoint(StartPoint newStartPoint) {
		if (newStartPoint != eInternalContainer() || (eContainerFeatureID() != PerformancePackage.WORKLOAD__START_POINT && newStartPoint != null)) {
			if (EcoreUtil.isAncestor(this, newStartPoint))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newStartPoint != null)
				msgs = ((InternalEObject)newStartPoint).eInverseAdd(this, MapPackage.START_POINT__WORKLOAD, StartPoint.class, msgs);
			msgs = basicSetStartPoint(newStartPoint, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.WORKLOAD__START_POINT, newStartPoint, newStartPoint));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PerformancePackage.WORKLOAD__START_POINT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetStartPoint((StartPoint)otherEnd, msgs);
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
			case PerformancePackage.WORKLOAD__START_POINT:
				return basicSetStartPoint(null, msgs);
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
			case PerformancePackage.WORKLOAD__START_POINT:
				return eInternalContainer().eInverseRemove(this, MapPackage.START_POINT__WORKLOAD, StartPoint.class, msgs);
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
			case PerformancePackage.WORKLOAD__CLOSED:
				return isClosed() ? Boolean.TRUE : Boolean.FALSE;
			case PerformancePackage.WORKLOAD__ARRIVAL_PATTERN:
				return getArrivalPattern();
			case PerformancePackage.WORKLOAD__ARRIVAL_PARAM1:
				return getArrivalParam1();
			case PerformancePackage.WORKLOAD__ARRIVAL_PARAM2:
				return getArrivalParam2();
			case PerformancePackage.WORKLOAD__EXTERNAL_DELAY:
				return getExternalDelay();
			case PerformancePackage.WORKLOAD__VALUE:
				return getValue();
			case PerformancePackage.WORKLOAD__COEFF_VAR_SEQ:
				return getCoeffVarSeq();
			case PerformancePackage.WORKLOAD__POPULATION:
				return getPopulation();
			case PerformancePackage.WORKLOAD__UNIT:
				return getUnit();
			case PerformancePackage.WORKLOAD__START_POINT:
				return getStartPoint();
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
			case PerformancePackage.WORKLOAD__CLOSED:
				setClosed(((Boolean)newValue).booleanValue());
				return;
			case PerformancePackage.WORKLOAD__ARRIVAL_PATTERN:
				setArrivalPattern((ArrivalProcess)newValue);
				return;
			case PerformancePackage.WORKLOAD__ARRIVAL_PARAM1:
				setArrivalParam1((String)newValue);
				return;
			case PerformancePackage.WORKLOAD__ARRIVAL_PARAM2:
				setArrivalParam2((String)newValue);
				return;
			case PerformancePackage.WORKLOAD__EXTERNAL_DELAY:
				setExternalDelay((String)newValue);
				return;
			case PerformancePackage.WORKLOAD__VALUE:
				setValue((String)newValue);
				return;
			case PerformancePackage.WORKLOAD__COEFF_VAR_SEQ:
				setCoeffVarSeq((String)newValue);
				return;
			case PerformancePackage.WORKLOAD__POPULATION:
				setPopulation((String)newValue);
				return;
			case PerformancePackage.WORKLOAD__UNIT:
				setUnit((TimeUnit)newValue);
				return;
			case PerformancePackage.WORKLOAD__START_POINT:
				setStartPoint((StartPoint)newValue);
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
			case PerformancePackage.WORKLOAD__CLOSED:
				setClosed(CLOSED_EDEFAULT);
				return;
			case PerformancePackage.WORKLOAD__ARRIVAL_PATTERN:
				setArrivalPattern(ARRIVAL_PATTERN_EDEFAULT);
				return;
			case PerformancePackage.WORKLOAD__ARRIVAL_PARAM1:
				setArrivalParam1(ARRIVAL_PARAM1_EDEFAULT);
				return;
			case PerformancePackage.WORKLOAD__ARRIVAL_PARAM2:
				setArrivalParam2(ARRIVAL_PARAM2_EDEFAULT);
				return;
			case PerformancePackage.WORKLOAD__EXTERNAL_DELAY:
				setExternalDelay(EXTERNAL_DELAY_EDEFAULT);
				return;
			case PerformancePackage.WORKLOAD__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
			case PerformancePackage.WORKLOAD__COEFF_VAR_SEQ:
				setCoeffVarSeq(COEFF_VAR_SEQ_EDEFAULT);
				return;
			case PerformancePackage.WORKLOAD__POPULATION:
				setPopulation(POPULATION_EDEFAULT);
				return;
			case PerformancePackage.WORKLOAD__UNIT:
				setUnit(UNIT_EDEFAULT);
				return;
			case PerformancePackage.WORKLOAD__START_POINT:
				setStartPoint((StartPoint)null);
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
			case PerformancePackage.WORKLOAD__CLOSED:
				return closed != CLOSED_EDEFAULT;
			case PerformancePackage.WORKLOAD__ARRIVAL_PATTERN:
				return arrivalPattern != ARRIVAL_PATTERN_EDEFAULT;
			case PerformancePackage.WORKLOAD__ARRIVAL_PARAM1:
				return ARRIVAL_PARAM1_EDEFAULT == null ? arrivalParam1 != null : !ARRIVAL_PARAM1_EDEFAULT.equals(arrivalParam1);
			case PerformancePackage.WORKLOAD__ARRIVAL_PARAM2:
				return ARRIVAL_PARAM2_EDEFAULT == null ? arrivalParam2 != null : !ARRIVAL_PARAM2_EDEFAULT.equals(arrivalParam2);
			case PerformancePackage.WORKLOAD__EXTERNAL_DELAY:
				return EXTERNAL_DELAY_EDEFAULT == null ? externalDelay != null : !EXTERNAL_DELAY_EDEFAULT.equals(externalDelay);
			case PerformancePackage.WORKLOAD__VALUE:
				return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
			case PerformancePackage.WORKLOAD__COEFF_VAR_SEQ:
				return COEFF_VAR_SEQ_EDEFAULT == null ? coeffVarSeq != null : !COEFF_VAR_SEQ_EDEFAULT.equals(coeffVarSeq);
			case PerformancePackage.WORKLOAD__POPULATION:
				return POPULATION_EDEFAULT == null ? population != null : !POPULATION_EDEFAULT.equals(population);
			case PerformancePackage.WORKLOAD__UNIT:
				return unit != UNIT_EDEFAULT;
			case PerformancePackage.WORKLOAD__START_POINT:
				return getStartPoint() != null;
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
		result.append(" (closed: ");
		result.append(closed);
		result.append(", arrivalPattern: ");
		result.append(arrivalPattern);
		result.append(", arrivalParam1: ");
		result.append(arrivalParam1);
		result.append(", arrivalParam2: ");
		result.append(arrivalParam2);
		result.append(", externalDelay: ");
		result.append(externalDelay);
		result.append(", value: ");
		result.append(value);
		result.append(", coeffVarSeq: ");
		result.append(coeffVarSeq);
		result.append(", population: ");
		result.append(population);
		result.append(", unit: ");
		result.append(unit);
		result.append(')');
		return result.toString();
	}

} //WorkloadImpl
