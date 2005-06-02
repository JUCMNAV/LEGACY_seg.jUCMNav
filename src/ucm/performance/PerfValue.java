/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.performance;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Perf Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.performance.PerfValue#getValue <em>Value</em>}</li>
 *   <li>{@link ucm.performance.PerfValue#getKind <em>Kind</em>}</li>
 *   <li>{@link ucm.performance.PerfValue#getSource <em>Source</em>}</li>
 *   <li>{@link ucm.performance.PerfValue#getPercentile <em>Percentile</em>}</li>
 *   <li>{@link ucm.performance.PerfValue#getKthMoment <em>Kth Moment</em>}</li>
 *   <li>{@link ucm.performance.PerfValue#getPerfMeasure <em>Perf Measure</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.performance.PerformancePackage#getPerfValue()
 * @model 
 * @generated
 */
public interface PerfValue extends EObject{
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
     * @see ucm.performance.PerformancePackage#getPerfValue_Value()
     * @model 
     * @generated
     */
	String getValue();

    /**
     * Sets the value of the '{@link ucm.performance.PerfValue#getValue <em>Value</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value</em>' attribute.
     * @see #getValue()
     * @generated
     */
	void setValue(String value);

    /**
     * Returns the value of the '<em><b>Kind</b></em>' attribute.
     * The default value is <code>"Unknown"</code>.
     * The literals are from the enumeration {@link ucm.performance.PerfValueKind}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Kind</em>' attribute.
     * @see ucm.performance.PerfValueKind
     * @see #setKind(PerfValueKind)
     * @see ucm.performance.PerformancePackage#getPerfValue_Kind()
     * @model default="Unknown"
     * @generated
     */
	PerfValueKind getKind();

    /**
     * Sets the value of the '{@link ucm.performance.PerfValue#getKind <em>Kind</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Kind</em>' attribute.
     * @see ucm.performance.PerfValueKind
     * @see #getKind()
     * @generated
     */
	void setKind(PerfValueKind value);

    /**
     * Returns the value of the '<em><b>Source</b></em>' attribute.
     * The default value is <code>"Unknown"</code>.
     * The literals are from the enumeration {@link ucm.performance.PerfValueSource}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Source</em>' attribute.
     * @see ucm.performance.PerfValueSource
     * @see #setSource(PerfValueSource)
     * @see ucm.performance.PerformancePackage#getPerfValue_Source()
     * @model default="Unknown"
     * @generated
     */
	PerfValueSource getSource();

    /**
     * Sets the value of the '{@link ucm.performance.PerfValue#getSource <em>Source</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source</em>' attribute.
     * @see ucm.performance.PerfValueSource
     * @see #getSource()
     * @generated
     */
	void setSource(PerfValueSource value);

    /**
     * Returns the value of the '<em><b>Percentile</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Percentile</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Percentile</em>' attribute.
     * @see #setPercentile(String)
     * @see ucm.performance.PerformancePackage#getPerfValue_Percentile()
     * @model 
     * @generated
     */
	String getPercentile();

    /**
     * Sets the value of the '{@link ucm.performance.PerfValue#getPercentile <em>Percentile</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Percentile</em>' attribute.
     * @see #getPercentile()
     * @generated
     */
	void setPercentile(String value);

    /**
     * Returns the value of the '<em><b>Kth Moment</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kth Moment</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Kth Moment</em>' attribute.
     * @see #setKthMoment(String)
     * @see ucm.performance.PerformancePackage#getPerfValue_KthMoment()
     * @model 
     * @generated
     */
	String getKthMoment();

    /**
     * Sets the value of the '{@link ucm.performance.PerfValue#getKthMoment <em>Kth Moment</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Kth Moment</em>' attribute.
     * @see #getKthMoment()
     * @generated
     */
	void setKthMoment(String value);

    /**
     * Returns the value of the '<em><b>Perf Measure</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link ucm.performance.PerfMeasure#getPerfValues <em>Perf Values</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Perf Measure</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Perf Measure</em>' container reference.
     * @see #setPerfMeasure(PerfMeasure)
     * @see ucm.performance.PerformancePackage#getPerfValue_PerfMeasure()
     * @see ucm.performance.PerfMeasure#getPerfValues
     * @model opposite="perfValues" required="true"
     * @generated
     */
	PerfMeasure getPerfMeasure();

    /**
     * Sets the value of the '{@link ucm.performance.PerfValue#getPerfMeasure <em>Perf Measure</em>}' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Perf Measure</em>' container reference.
     * @see #getPerfMeasure()
     * @generated
     */
	void setPerfMeasure(PerfMeasure value);

} // PerfValue
