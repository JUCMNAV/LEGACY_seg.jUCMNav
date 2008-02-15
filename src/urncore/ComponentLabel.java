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
 *   <li>{@link urncore.ComponentLabel#getContRef <em>Cont Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getComponentLabel()
 * @model
 * @generated
 */
public interface ComponentLabel extends Label {
    /**
	 * Returns the value of the '<em><b>Cont Ref</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link urncore.IURNContainerRef#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Cont Ref</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Cont Ref</em>' container reference.
	 * @see #setContRef(IURNContainerRef)
	 * @see urncore.UrncorePackage#getComponentLabel_ContRef()
	 * @see urncore.IURNContainerRef#getLabel
	 * @model opposite="label"
	 * @generated
	 */
    IURNContainerRef getContRef();

    /**
	 * Sets the value of the '{@link urncore.ComponentLabel#getContRef <em>Cont Ref</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cont Ref</em>' container reference.
	 * @see #getContRef()
	 * @generated
	 */
    void setContRef(IURNContainerRef value);

} // ComponentLabel
