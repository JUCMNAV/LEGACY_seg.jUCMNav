/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore;

import org.eclipse.emf.common.util.EList;

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
 *   <li>{@link urncore.ComponentElement#getLineColor <em>Line Color</em>}</li>
 *   <li>{@link urncore.ComponentElement#getFillColor <em>Fill Color</em>}</li>
 *   <li>{@link urncore.ComponentElement#getIncludingComponent <em>Including Component</em>}</li>
 *   <li>{@link urncore.ComponentElement#getResource <em>Resource</em>}</li>
 *   <li>{@link urncore.ComponentElement#getCompRefs <em>Comp Refs</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getComponentElement()
 * @model abstract="true"
 * @generated
 */
public interface ComponentElement extends UCMmodelElement {
	/**
	 * Returns the value of the '<em><b>Line Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Line Color</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Line Color</em>' attribute.
	 * @see #setLineColor(String)
	 * @see urncore.UrncorePackage#getComponentElement_LineColor()
	 * @model 
	 * @generated
	 */
	String getLineColor();

	/**
	 * Sets the value of the '{@link urncore.ComponentElement#getLineColor <em>Line Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Line Color</em>' attribute.
	 * @see #getLineColor()
	 * @generated
	 */
	void setLineColor(String value);

	/**
	 * Returns the value of the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fill Color</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fill Color</em>' attribute.
	 * @see #setFillColor(String)
	 * @see urncore.UrncorePackage#getComponentElement_FillColor()
	 * @model 
	 * @generated
	 */
	String getFillColor();

	/**
	 * Sets the value of the '{@link urncore.ComponentElement#getFillColor <em>Fill Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fill Color</em>' attribute.
	 * @see #getFillColor()
	 * @generated
	 */
	void setFillColor(String value);

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

	/**
	 * Returns the value of the '<em><b>Comp Refs</b></em>' reference list.
	 * The list contents are of type {@link ucm.map.ComponentRef}.
	 * It is bidirectional and its opposite is '{@link ucm.map.ComponentRef#getCompDef <em>Comp Def</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comp Refs</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Comp Refs</em>' reference list.
	 * @see urncore.UrncorePackage#getComponentElement_CompRefs()
	 * @see ucm.map.ComponentRef#getCompDef
	 * @model type="ucm.map.ComponentRef" opposite="compDef" required="true"
	 * @generated
	 */
	EList getCompRefs();

} // ComponentElement
