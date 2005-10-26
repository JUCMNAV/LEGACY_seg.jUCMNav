/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Label</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link urncore.ComponentLabel#getCompRef <em>Comp Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getComponentLabel()
 * @model 
 * @generated
 */
public interface ComponentLabel extends Label {
    /**
     * Returns the value of the '<em><b>Comp Ref</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link urncore.SpecificationComponentRef#getLabel <em>Label</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Comp Ref</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Comp Ref</em>' container reference.
     * @see #setCompRef(SpecificationComponentRef)
     * @see urncore.UrncorePackage#getComponentLabel_CompRef()
     * @see urncore.SpecificationComponentRef#getLabel
     * @model opposite="label"
     * @generated
     */
    SpecificationComponentRef getCompRef();

    /**
     * Sets the value of the '{@link urncore.ComponentLabel#getCompRef <em>Comp Ref</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Comp Ref</em>' container reference.
     * @see #getCompRef()
     * @generated
     */
    void setCompRef(SpecificationComponentRef value);

} // ComponentLabel
