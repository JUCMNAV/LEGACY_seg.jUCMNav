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
 *   <li>{@link urncore.ComponentType#getSubType <em>Sub Type</em>}</li>
 *   <li>{@link urncore.ComponentType#getSuperType <em>Super Type</em>}</li>
 *   <li>{@link urncore.ComponentType#getInstances <em>Instances</em>}</li>
 *   <li>{@link urncore.ComponentType#getPools <em>Pools</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getComponentType()
 * @model 
 * @generated
 */
public interface ComponentType extends ComponentRegular {
    /**
     * Returns the value of the '<em><b>Sub Type</b></em>' reference list.
     * The list contents are of type {@link urncore.ComponentType}.
     * It is bidirectional and its opposite is '{@link urncore.ComponentType#getSuperType <em>Super Type</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sub Type</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Sub Type</em>' reference list.
     * @see urncore.UrncorePackage#getComponentType_SubType()
     * @see urncore.ComponentType#getSuperType
     * @model type="urncore.ComponentType" opposite="superType"
     * @generated
     */
    EList getSubType();

    /**
     * Returns the value of the '<em><b>Super Type</b></em>' reference.
     * It is bidirectional and its opposite is '{@link urncore.ComponentType#getSubType <em>Sub Type</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Super Type</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Super Type</em>' reference.
     * @see #setSuperType(ComponentType)
     * @see urncore.UrncorePackage#getComponentType_SuperType()
     * @see urncore.ComponentType#getSubType
     * @model opposite="subType"
     * @generated
     */
    ComponentType getSuperType();

    /**
     * Sets the value of the '{@link urncore.ComponentType#getSuperType <em>Super Type</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Super Type</em>' reference.
     * @see #getSuperType()
     * @generated
     */
    void setSuperType(ComponentType value);

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
     * Returns the value of the '<em><b>Pools</b></em>' reference list.
     * The list contents are of type {@link urncore.Pool}.
     * It is bidirectional and its opposite is '{@link urncore.Pool#getComponentType <em>Component Type</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Pools</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Pools</em>' reference list.
     * @see urncore.UrncorePackage#getComponentType_Pools()
     * @see urncore.Pool#getComponentType
     * @model type="urncore.Pool" opposite="componentType"
     * @generated
     */
    EList getPools();

} // ComponentType
