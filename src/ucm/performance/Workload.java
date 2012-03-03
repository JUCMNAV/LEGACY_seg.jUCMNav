/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.performance;

import ucm.map.StartPoint;
import urncore.UCMmodelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Workload</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.performance.Workload#isClosed <em>Closed</em>}</li>
 *   <li>{@link ucm.performance.Workload#getArrivalPattern <em>Arrival Pattern</em>}</li>
 *   <li>{@link ucm.performance.Workload#getArrivalParam1 <em>Arrival Param1</em>}</li>
 *   <li>{@link ucm.performance.Workload#getArrivalParam2 <em>Arrival Param2</em>}</li>
 *   <li>{@link ucm.performance.Workload#getExternalDelay <em>External Delay</em>}</li>
 *   <li>{@link ucm.performance.Workload#getValue <em>Value</em>}</li>
 *   <li>{@link ucm.performance.Workload#getCoeffVarSeq <em>Coeff Var Seq</em>}</li>
 *   <li>{@link ucm.performance.Workload#getPopulation <em>Population</em>}</li>
 *   <li>{@link ucm.performance.Workload#getUnit <em>Unit</em>}</li>
 *   <li>{@link ucm.performance.Workload#getStartPoint <em>Start Point</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.performance.PerformancePackage#getWorkload()
 * @model
 * @generated
 */
public interface Workload extends UCMmodelElement {
    /**
	 * Returns the value of the '<em><b>Closed</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Closed</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Closed</em>' attribute.
	 * @see #setClosed(boolean)
	 * @see ucm.performance.PerformancePackage#getWorkload_Closed()
	 * @model default="false"
	 * @generated
	 */
    boolean isClosed();

    /**
	 * Sets the value of the '{@link ucm.performance.Workload#isClosed <em>Closed</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Closed</em>' attribute.
	 * @see #isClosed()
	 * @generated
	 */
    void setClosed(boolean value);

    /**
	 * Returns the value of the '<em><b>Arrival Pattern</b></em>' attribute.
	 * The literals are from the enumeration {@link ucm.performance.ArrivalProcess}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Arrival Pattern</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Arrival Pattern</em>' attribute.
	 * @see ucm.performance.ArrivalProcess
	 * @see #setArrivalPattern(ArrivalProcess)
	 * @see ucm.performance.PerformancePackage#getWorkload_ArrivalPattern()
	 * @model
	 * @generated
	 */
    ArrivalProcess getArrivalPattern();

    /**
	 * Sets the value of the '{@link ucm.performance.Workload#getArrivalPattern <em>Arrival Pattern</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Arrival Pattern</em>' attribute.
	 * @see ucm.performance.ArrivalProcess
	 * @see #getArrivalPattern()
	 * @generated
	 */
    void setArrivalPattern(ArrivalProcess value);

    /**
	 * Returns the value of the '<em><b>Arrival Param1</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Arrival Param1</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Arrival Param1</em>' attribute.
	 * @see #setArrivalParam1(String)
	 * @see ucm.performance.PerformancePackage#getWorkload_ArrivalParam1()
	 * @model
	 * @generated
	 */
    String getArrivalParam1();

    /**
	 * Sets the value of the '{@link ucm.performance.Workload#getArrivalParam1 <em>Arrival Param1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Arrival Param1</em>' attribute.
	 * @see #getArrivalParam1()
	 * @generated
	 */
	void setArrivalParam1(String value);

    /**
	 * Returns the value of the '<em><b>Arrival Param2</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Arrival Param2</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Arrival Param2</em>' attribute.
	 * @see #setArrivalParam2(String)
	 * @see ucm.performance.PerformancePackage#getWorkload_ArrivalParam2()
	 * @model
	 * @generated
	 */
    String getArrivalParam2();

    /**
	 * Sets the value of the '{@link ucm.performance.Workload#getArrivalParam2 <em>Arrival Param2</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Arrival Param2</em>' attribute.
	 * @see #getArrivalParam2()
	 * @generated
	 */
	void setArrivalParam2(String value);

    /**
	 * Returns the value of the '<em><b>External Delay</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>External Delay</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>External Delay</em>' attribute.
	 * @see #setExternalDelay(String)
	 * @see ucm.performance.PerformancePackage#getWorkload_ExternalDelay()
	 * @model
	 * @generated
	 */
    String getExternalDelay();

    /**
	 * Sets the value of the '{@link ucm.performance.Workload#getExternalDelay <em>External Delay</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>External Delay</em>' attribute.
	 * @see #getExternalDelay()
	 * @generated
	 */
	void setExternalDelay(String value);

    /**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see ucm.performance.PerformancePackage#getWorkload_Value()
	 * @model
	 * @generated
	 */
    String getValue();

    /**
	 * Sets the value of the '{@link ucm.performance.Workload#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

    /**
	 * Returns the value of the '<em><b>Coeff Var Seq</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Coeff Var Seq</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Coeff Var Seq</em>' attribute.
	 * @see #setCoeffVarSeq(String)
	 * @see ucm.performance.PerformancePackage#getWorkload_CoeffVarSeq()
	 * @model
	 * @generated
	 */
    String getCoeffVarSeq();

    /**
	 * Sets the value of the '{@link ucm.performance.Workload#getCoeffVarSeq <em>Coeff Var Seq</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Coeff Var Seq</em>' attribute.
	 * @see #getCoeffVarSeq()
	 * @generated
	 */
	void setCoeffVarSeq(String value);

    /**
	 * Returns the value of the '<em><b>Population</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Population</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Population</em>' attribute.
	 * @see #setPopulation(String)
	 * @see ucm.performance.PerformancePackage#getWorkload_Population()
	 * @model
	 * @generated
	 */
    String getPopulation();

    /**
	 * Sets the value of the '{@link ucm.performance.Workload#getPopulation <em>Population</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Population</em>' attribute.
	 * @see #getPopulation()
	 * @generated
	 */
	void setPopulation(String value);

    /**
	 * Returns the value of the '<em><b>Unit</b></em>' attribute.
	 * The default value is <code>"ms"</code>.
	 * The literals are from the enumeration {@link ucm.performance.TimeUnit}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Unit</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Unit</em>' attribute.
	 * @see ucm.performance.TimeUnit
	 * @see #setUnit(TimeUnit)
	 * @see ucm.performance.PerformancePackage#getWorkload_Unit()
	 * @model default="ms"
	 * @generated
	 */
    TimeUnit getUnit();

    /**
	 * Sets the value of the '{@link ucm.performance.Workload#getUnit <em>Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unit</em>' attribute.
	 * @see ucm.performance.TimeUnit
	 * @see #getUnit()
	 * @generated
	 */
    void setUnit(TimeUnit value);

    /**
	 * Returns the value of the '<em><b>Start Point</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ucm.map.StartPoint#getWorkload <em>Workload</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Start Point</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Start Point</em>' container reference.
	 * @see #setStartPoint(StartPoint)
	 * @see ucm.performance.PerformancePackage#getWorkload_StartPoint()
	 * @see ucm.map.StartPoint#getWorkload
	 * @model opposite="workload" required="true"
	 * @generated
	 */
    StartPoint getStartPoint();

    /**
	 * Sets the value of the '{@link ucm.performance.Workload#getStartPoint <em>Start Point</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Point</em>' container reference.
	 * @see #getStartPoint()
	 * @generated
	 */
    void setStartPoint(StartPoint value);

} // Workload
