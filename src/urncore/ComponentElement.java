/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore;

import ucm.performance.PassiveResource;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Abstract class for ComponentType, Component (instances), and Pools.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link urncore.ComponentElement#getUrndefinition <em>Urndefinition</em>}</li>
 *   <li>{@link urncore.ComponentElement#getIncludingComponent <em>Including Component</em>}</li>
 *   <li>{@link urncore.ComponentElement#getResource <em>Resource</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getComponentElement()
 * @model abstract="true"
 * @generated
 */
public interface ComponentElement extends UCMmodelElement, IURNContainer {
	/**
	 * Returns the value of the '<em><b>Urndefinition</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link urncore.URNdefinition#getComponents <em>Components</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Urndefinition</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Urndefinition</em>' container reference.
	 * @see #setUrndefinition(URNdefinition)
	 * @see urncore.UrncorePackage#getComponentElement_Urndefinition()
	 * @see urncore.URNdefinition#getComponents
	 * @model opposite="components" required="true"
	 * @generated
	 */
    URNdefinition getUrndefinition();

	/**
	 * Sets the value of the '{@link urncore.ComponentElement#getUrndefinition <em>Urndefinition</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Urndefinition</em>' container reference.
	 * @see #getUrndefinition()
	 * @generated
	 */
    void setUrndefinition(URNdefinition value);

	/**
	 * Returns the value of the '<em><b>Including Component</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link urncore.ComponentRegular#getIncludedComponent <em>Included Component</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Including Component</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Including Component</em>' reference.
	 * @see #setIncludingComponent(ComponentRegular)
	 * @see urncore.UrncorePackage#getComponentElement_IncludingComponent()
	 * @see urncore.ComponentRegular#getIncludedComponent
	 * @model opposite="includedComponent"
	 * @generated
	 */
    ComponentRegular getIncludingComponent();

	/**
	 * Sets the value of the '{@link urncore.ComponentElement#getIncludingComponent <em>Including Component</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Including Component</em>' reference.
	 * @see #getIncludingComponent()
	 * @generated
	 */
    void setIncludingComponent(ComponentRegular value);

	/**
	 * Returns the value of the '<em><b>Resource</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link ucm.performance.PassiveResource#getComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Resource</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource</em>' reference.
	 * @see #setResource(PassiveResource)
	 * @see urncore.UrncorePackage#getComponentElement_Resource()
	 * @see ucm.performance.PassiveResource#getComponent
	 * @model opposite="component"
	 * @generated
	 */
    PassiveResource getResource();

	/**
	 * Sets the value of the '{@link urncore.ComponentElement#getResource <em>Resource</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource</em>' reference.
	 * @see #getResource()
	 * @generated
	 */
    void setResource(PassiveResource value);

} // ComponentElement
