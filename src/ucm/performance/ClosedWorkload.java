/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.performance;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Closed Workload</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.performance.ClosedWorkload#getPopulation <em>Population</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.performance.PerformancePackage#getClosedWorkload()
 * @model 
 * @generated
 */
public interface ClosedWorkload extends Workload {
	/**
	 * Returns the value of the '<em><b>Population</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Population</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Population</em>' attribute.
	 * @see #setPopulation(int)
	 * @see ucm.performance.PerformancePackage#getClosedWorkload_Population()
	 * @model 
	 * @generated
	 */
	int getPopulation();

	/**
	 * Sets the value of the '{@link ucm.performance.ClosedWorkload#getPopulation <em>Population</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Population</em>' attribute.
	 * @see #getPopulation()
	 * @generated
	 */
	void setPopulation(int value);

} // ClosedWorkload
