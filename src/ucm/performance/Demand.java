/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.performance;

import org.eclipse.emf.ecore.EObject;

import urncore.Responsibility;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Demand</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.performance.Demand#getQuantity <em>Quantity</em>}</li>
 *   <li>{@link ucm.performance.Demand#getResponsibility <em>Responsibility</em>}</li>
 *   <li>{@link ucm.performance.Demand#getResource <em>Resource</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.performance.PerformancePackage#getDemand()
 * @model
 * @generated
 */
public interface Demand extends EObject {
    /**
	 * Returns the value of the '<em><b>Quantity</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Quantity</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Quantity</em>' attribute.
	 * @see #setQuantity(String)
	 * @see ucm.performance.PerformancePackage#getDemand_Quantity()
	 * @model
	 * @generated
	 */
    String getQuantity();

    /**
	 * Sets the value of the '{@link ucm.performance.Demand#getQuantity <em>Quantity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Quantity</em>' attribute.
	 * @see #getQuantity()
	 * @generated
	 */
	void setQuantity(String value);

    /**
	 * Returns the value of the '<em><b>Responsibility</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link urncore.Responsibility#getDemands <em>Demands</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Responsibility</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Responsibility</em>' container reference.
	 * @see #setResponsibility(Responsibility)
	 * @see ucm.performance.PerformancePackage#getDemand_Responsibility()
	 * @see urncore.Responsibility#getDemands
	 * @model opposite="demands" required="true"
	 * @generated
	 */
    Responsibility getResponsibility();

    /**
	 * Sets the value of the '{@link ucm.performance.Demand#getResponsibility <em>Responsibility</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Responsibility</em>' container reference.
	 * @see #getResponsibility()
	 * @generated
	 */
    void setResponsibility(Responsibility value);

    /**
	 * Returns the value of the '<em><b>Resource</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link ucm.performance.ExternalOperation#getDemands <em>Demands</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Resource</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource</em>' reference.
	 * @see #setResource(ExternalOperation)
	 * @see ucm.performance.PerformancePackage#getDemand_Resource()
	 * @see ucm.performance.ExternalOperation#getDemands
	 * @model opposite="demands" required="true"
	 * @generated
	 */
    ExternalOperation getResource();

    /**
	 * Sets the value of the '{@link ucm.performance.Demand#getResource <em>Resource</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource</em>' reference.
	 * @see #getResource()
	 * @generated
	 */
	void setResource(ExternalOperation value);

} // Demand
