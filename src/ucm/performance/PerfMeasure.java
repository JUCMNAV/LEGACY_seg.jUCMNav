/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.performance;

import org.eclipse.emf.common.util.EList;

import ucm.UCMspec;
import ucm.map.PathNode;
import urncore.UCMmodelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Perf Measure</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.performance.PerfMeasure#getMeasure <em>Measure</em>}</li>
 *   <li>{@link ucm.performance.PerfMeasure#getUcmspec <em>Ucmspec</em>}</li>
 *   <li>{@link ucm.performance.PerfMeasure#getPerfValues <em>Perf Values</em>}</li>
 *   <li>{@link ucm.performance.PerfMeasure#getDuration <em>Duration</em>}</li>
 *   <li>{@link ucm.performance.PerfMeasure#getResource <em>Resource</em>}</li>
 *   <li>{@link ucm.performance.PerfMeasure#getTrigger <em>Trigger</em>}</li>
 *   <li>{@link ucm.performance.PerfMeasure#getEnd <em>End</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.performance.PerformancePackage#getPerfMeasure()
 * @model
 * @generated
 */
public interface PerfMeasure extends UCMmodelElement {
    /**
     * Returns the value of the '<em><b>Measure</b></em>' attribute.
     * The default value is <code>"Delay"</code>.
     * The literals are from the enumeration {@link ucm.performance.PerfAttribute}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Measure</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Measure</em>' attribute.
     * @see ucm.performance.PerfAttribute
     * @see #setMeasure(PerfAttribute)
     * @see ucm.performance.PerformancePackage#getPerfMeasure_Measure()
     * @model default="Delay"
     * @generated
     */
    PerfAttribute getMeasure();

    /**
     * Sets the value of the '{@link ucm.performance.PerfMeasure#getMeasure <em>Measure</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Measure</em>' attribute.
     * @see ucm.performance.PerfAttribute
     * @see #getMeasure()
     * @generated
     */
    void setMeasure(PerfAttribute value);

    /**
     * Returns the value of the '<em><b>Ucmspec</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link ucm.UCMspec#getPerfMeasures <em>Perf Measures</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ucmspec</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Ucmspec</em>' container reference.
     * @see #setUcmspec(UCMspec)
     * @see ucm.performance.PerformancePackage#getPerfMeasure_Ucmspec()
     * @see ucm.UCMspec#getPerfMeasures
     * @model opposite="perfMeasures" required="true"
     * @generated
     */
    UCMspec getUcmspec();

    /**
     * Sets the value of the '{@link ucm.performance.PerfMeasure#getUcmspec <em>Ucmspec</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Ucmspec</em>' container reference.
     * @see #getUcmspec()
     * @generated
     */
    void setUcmspec(UCMspec value);

    /**
     * Returns the value of the '<em><b>Perf Values</b></em>' containment reference list.
     * The list contents are of type {@link ucm.performance.PerfValue}.
     * It is bidirectional and its opposite is '{@link ucm.performance.PerfValue#getPerfMeasure <em>Perf Measure</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Perf Values</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Perf Values</em>' containment reference list.
     * @see ucm.performance.PerformancePackage#getPerfMeasure_PerfValues()
     * @see ucm.performance.PerfValue#getPerfMeasure
     * @model type="ucm.performance.PerfValue" opposite="perfMeasure" containment="true"
     * @generated
     */
    EList getPerfValues();

    /**
     * Returns the value of the '<em><b>Duration</b></em>' reference.
     * It is bidirectional and its opposite is '{@link ucm.performance.Workload#getResponseTime <em>Response Time</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Duration</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Duration</em>' reference.
     * @see #setDuration(Workload)
     * @see ucm.performance.PerformancePackage#getPerfMeasure_Duration()
     * @see ucm.performance.Workload#getResponseTime
     * @model opposite="responseTime"
     * @generated
     */
    Workload getDuration();

    /**
     * Sets the value of the '{@link ucm.performance.PerfMeasure#getDuration <em>Duration</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Duration</em>' reference.
     * @see #getDuration()
     * @generated
     */
    void setDuration(Workload value);

    /**
     * Returns the value of the '<em><b>Resource</b></em>' reference.
     * It is bidirectional and its opposite is '{@link ucm.performance.GeneralResource#getPerfMeasures <em>Perf Measures</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Resource</em>' reference.
     * @see #setResource(GeneralResource)
     * @see ucm.performance.PerformancePackage#getPerfMeasure_Resource()
     * @see ucm.performance.GeneralResource#getPerfMeasures
     * @model opposite="perfMeasures"
     * @generated
     */
	GeneralResource getResource();

    /**
     * Sets the value of the '{@link ucm.performance.PerfMeasure#getResource <em>Resource</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Resource</em>' reference.
     * @see #getResource()
     * @generated
     */
	void setResource(GeneralResource value);

    /**
     * Returns the value of the '<em><b>Trigger</b></em>' reference.
     * It is bidirectional and its opposite is '{@link ucm.map.PathNode#getPerfMTrig <em>Perf MTrig</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trigger</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Trigger</em>' reference.
     * @see #setTrigger(PathNode)
     * @see ucm.performance.PerformancePackage#getPerfMeasure_Trigger()
     * @see ucm.map.PathNode#getPerfMTrig
     * @model opposite="perfMTrig"
     * @generated
     */
	PathNode getTrigger();

    /**
     * Sets the value of the '{@link ucm.performance.PerfMeasure#getTrigger <em>Trigger</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Trigger</em>' reference.
     * @see #getTrigger()
     * @generated
     */
	void setTrigger(PathNode value);

    /**
     * Returns the value of the '<em><b>End</b></em>' reference.
     * It is bidirectional and its opposite is '{@link ucm.map.PathNode#getPerfMEnd <em>Perf MEnd</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>End</em>' reference.
     * @see #setEnd(PathNode)
     * @see ucm.performance.PerformancePackage#getPerfMeasure_End()
     * @see ucm.map.PathNode#getPerfMEnd
     * @model opposite="perfMEnd"
     * @generated
     */
	PathNode getEnd();

    /**
     * Sets the value of the '{@link ucm.performance.PerfMeasure#getEnd <em>End</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>End</em>' reference.
     * @see #getEnd()
     * @generated
     */
	void setEnd(PathNode value);

} // PerfMeasure
