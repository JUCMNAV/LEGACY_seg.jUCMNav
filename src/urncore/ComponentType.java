/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * User-defined component type. Type hiearchies (trees) can also be created.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link urncore.ComponentType#getInstances <em>Instances</em>}</li>
 *   <li>{@link urncore.ComponentType#getUrndefinition <em>Urndefinition</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getComponentType()
 * @model
 * @generated
 */
public interface ComponentType extends UCMmodelElement {
    /**
	 * Returns the value of the '<em><b>Instances</b></em>' reference list.
	 * The list contents are of type {@link urncore.Component}.
	 * It is bidirectional and its opposite is '{@link urncore.Component#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Instances</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Instances</em>' reference list.
	 * @see urncore.UrncorePackage#getComponentType_Instances()
	 * @see urncore.Component#getType
	 * @model type="urncore.Component" opposite="type"
	 * @generated
	 */
    EList getInstances();

    /**
	 * Returns the value of the '<em><b>Urndefinition</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link urncore.URNdefinition#getComponentTypes <em>Component Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Urndefinition</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Urndefinition</em>' container reference.
	 * @see #setUrndefinition(URNdefinition)
	 * @see urncore.UrncorePackage#getComponentType_Urndefinition()
	 * @see urncore.URNdefinition#getComponentTypes
	 * @model opposite="componentTypes" required="true"
	 * @generated
	 */
	URNdefinition getUrndefinition();

				/**
	 * Sets the value of the '{@link urncore.ComponentType#getUrndefinition <em>Urndefinition</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Urndefinition</em>' container reference.
	 * @see #getUrndefinition()
	 * @generated
	 */
	void setUrndefinition(URNdefinition value);

} // ComponentType
