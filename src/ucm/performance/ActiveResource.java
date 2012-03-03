/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.performance;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Active Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.performance.ActiveResource#getOpTime <em>Op Time</em>}</li>
 *   <li>{@link ucm.performance.ActiveResource#getUnit <em>Unit</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.performance.PerformancePackage#getActiveResource()
 * @model abstract="true"
 * @generated
 */
public interface ActiveResource extends GeneralResource {
    /**
	 * Returns the value of the '<em><b>Op Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Op Time</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Op Time</em>' attribute.
	 * @see #setOpTime(String)
	 * @see ucm.performance.PerformancePackage#getActiveResource_OpTime()
	 * @model
	 * @generated
	 */
    String getOpTime();

    /**
	 * Sets the value of the '{@link ucm.performance.ActiveResource#getOpTime <em>Op Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Op Time</em>' attribute.
	 * @see #getOpTime()
	 * @generated
	 */
	void setOpTime(String value);

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
	 * @see ucm.performance.PerformancePackage#getActiveResource_Unit()
	 * @model default="ms"
	 * @generated
	 */
    TimeUnit getUnit();

    /**
	 * Sets the value of the '{@link ucm.performance.ActiveResource#getUnit <em>Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unit</em>' attribute.
	 * @see ucm.performance.TimeUnit
	 * @see #getUnit()
	 * @generated
	 */
    void setUnit(TimeUnit value);

} // ActiveResource
