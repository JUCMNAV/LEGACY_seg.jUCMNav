/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.performance;

import urncore.ComponentElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Passive Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.performance.PassiveResource#getComponent <em>Component</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.performance.PerformancePackage#getPassiveResource()
 * @model
 * @generated
 */
public interface PassiveResource extends GeneralResource{
    /**
     * Returns the value of the '<em><b>Component</b></em>' reference.
     * It is bidirectional and its opposite is '{@link urncore.ComponentElement#getResource <em>Resource</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Component</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Component</em>' reference.
     * @see #setComponent(ComponentElement)
     * @see ucm.performance.PerformancePackage#getPassiveResource_Component()
     * @see urncore.ComponentElement#getResource
     * @model opposite="resource"
     * @generated
     */
    ComponentElement getComponent();

    /**
     * Sets the value of the '{@link ucm.performance.PassiveResource#getComponent <em>Component</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Component</em>' reference.
     * @see #getComponent()
     * @generated
     */
    void setComponent(ComponentElement value);

} // PassiveResource
