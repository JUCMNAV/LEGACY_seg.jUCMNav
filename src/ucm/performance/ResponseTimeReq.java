/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.performance;

import ucm.UCMspec;
import urncore.UCMmodelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Response Time Req</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.performance.ResponseTimeReq#getResponseTime <em>Response Time</em>}</li>
 *   <li>{@link ucm.performance.ResponseTimeReq#getPercentage <em>Percentage</em>}</li>
 *   <li>{@link ucm.performance.ResponseTimeReq#getUcmspec <em>Ucmspec</em>}</li>
 *   <li>{@link ucm.performance.ResponseTimeReq#getTs1 <em>Ts1</em>}</li>
 *   <li>{@link ucm.performance.ResponseTimeReq#getTs2 <em>Ts2</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.performance.PerformancePackage#getResponseTimeReq()
 * @model
 * @generated
 */
public interface ResponseTimeReq extends UCMmodelElement {
	/**
	 * Returns the value of the '<em><b>Response Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Response Time</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Response Time</em>' attribute.
	 * @see #setResponseTime(String)
	 * @see ucm.performance.PerformancePackage#getResponseTimeReq_ResponseTime()
	 * @model
	 * @generated
	 */
    String getResponseTime();

	/**
	 * Sets the value of the '{@link ucm.performance.ResponseTimeReq#getResponseTime <em>Response Time</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Response Time</em>' attribute.
	 * @see #getResponseTime()
	 * @generated
	 */
    void setResponseTime(String value);

	/**
	 * Returns the value of the '<em><b>Percentage</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Percentage</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Percentage</em>' attribute.
	 * @see #setPercentage(String)
	 * @see ucm.performance.PerformancePackage#getResponseTimeReq_Percentage()
	 * @model
	 * @generated
	 */
    String getPercentage();

	/**
	 * Sets the value of the '{@link ucm.performance.ResponseTimeReq#getPercentage <em>Percentage</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Percentage</em>' attribute.
	 * @see #getPercentage()
	 * @generated
	 */
    void setPercentage(String value);

	/**
	 * Returns the value of the '<em><b>Ucmspec</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ucm.UCMspec#getResptimereq <em>Resptimereq</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ucmspec</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Ucmspec</em>' container reference.
	 * @see #setUcmspec(UCMspec)
	 * @see ucm.performance.PerformancePackage#getResponseTimeReq_Ucmspec()
	 * @see ucm.UCMspec#getResptimereq
	 * @model opposite="resptimereq" required="true"
	 * @generated
	 */
    UCMspec getUcmspec();

	/**
	 * Sets the value of the '{@link ucm.performance.ResponseTimeReq#getUcmspec <em>Ucmspec</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ucmspec</em>' container reference.
	 * @see #getUcmspec()
	 * @generated
	 */
    void setUcmspec(UCMspec value);

	/**
	 * Returns the value of the '<em><b>Ts1</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link ucm.performance.Timestamp#getTargets <em>Targets</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ts1</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Ts1</em>' reference.
	 * @see #setTs1(Timestamp)
	 * @see ucm.performance.PerformancePackage#getResponseTimeReq_Ts1()
	 * @see ucm.performance.Timestamp#getTargets
	 * @model opposite="targets" required="true"
	 * @generated
	 */
    Timestamp getTs1();

	/**
	 * Sets the value of the '{@link ucm.performance.ResponseTimeReq#getTs1 <em>Ts1</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ts1</em>' reference.
	 * @see #getTs1()
	 * @generated
	 */
    void setTs1(Timestamp value);

	/**
	 * Returns the value of the '<em><b>Ts2</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link ucm.performance.Timestamp#getSources <em>Sources</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ts2</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Ts2</em>' reference.
	 * @see #setTs2(Timestamp)
	 * @see ucm.performance.PerformancePackage#getResponseTimeReq_Ts2()
	 * @see ucm.performance.Timestamp#getSources
	 * @model opposite="sources" required="true"
	 * @generated
	 */
    Timestamp getTs2();

	/**
	 * Sets the value of the '{@link ucm.performance.ResponseTimeReq#getTs2 <em>Ts2</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ts2</em>' reference.
	 * @see #getTs2()
	 * @generated
	 */
    void setTs2(Timestamp value);

} // ResponseTimeReq
