/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Component instance, which can be of a specific ComponentType.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link urncore.Component#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getComponent()
 * @model 
 * @generated
 */
public interface Component extends ComponentRegular {
    /**
     * Returns the value of the '<em><b>Type</b></em>' reference.
     * It is bidirectional and its opposite is '{@link urncore.ComponentType#getInstances <em>Instances</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' reference.
     * @see #setType(ComponentType)
     * @see urncore.UrncorePackage#getComponent_Type()
     * @see urncore.ComponentType#getInstances
     * @model opposite="instances"
     * @generated
     */
    ComponentType getType();

    /**
     * Sets the value of the '{@link urncore.Component#getType <em>Type</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' reference.
     * @see #getType()
     * @generated
     */
    void setType(ComponentType value);

} // Component
