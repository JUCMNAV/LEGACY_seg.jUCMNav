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
 * A representation of the model object '<em><b>Pool</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Pools are containers for data or dynamic components. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link urncore.Pool#isOfComponents <em>Of Components</em>}</li>
 *   <li>{@link urncore.Pool#getContent <em>Content</em>}</li>
 *   <li>{@link urncore.Pool#getComponentType <em>Component Type</em>}</li>
 *   <li>{@link urncore.Pool#getDynResponsibilities <em>Dyn Responsibilities</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getPool()
 * @model
 * @generated
 */
public interface Pool extends ComponentElement{
    /**
     * Returns the value of the '<em><b>Of Components</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Of Components</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Of Components</em>' attribute.
     * @see #setOfComponents(boolean)
     * @see urncore.UrncorePackage#getPool_OfComponents()
     * @model default="false"
     * @generated
     */
    boolean isOfComponents();

    /**
     * Sets the value of the '{@link urncore.Pool#isOfComponents <em>Of Components</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Of Components</em>' attribute.
     * @see #isOfComponents()
     * @generated
     */
    void setOfComponents(boolean value);

    /**
     * Returns the value of the '<em><b>Content</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Content</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Content</em>' attribute.
     * @see #setContent(String)
     * @see urncore.UrncorePackage#getPool_Content()
     * @model
     * @generated
     */
    String getContent();

    /**
     * Sets the value of the '{@link urncore.Pool#getContent <em>Content</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Content</em>' attribute.
     * @see #getContent()
     * @generated
     */
    void setContent(String value);

    /**
     * Returns the value of the '<em><b>Component Type</b></em>' reference.
     * It is bidirectional and its opposite is '{@link urncore.ComponentType#getPools <em>Pools</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Component Type</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Component Type</em>' reference.
     * @see #setComponentType(ComponentType)
     * @see urncore.UrncorePackage#getPool_ComponentType()
     * @see urncore.ComponentType#getPools
     * @model opposite="pools"
     * @generated
     */
    ComponentType getComponentType();

    /**
     * Sets the value of the '{@link urncore.Pool#getComponentType <em>Component Type</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Component Type</em>' reference.
     * @see #getComponentType()
     * @generated
     */
    void setComponentType(ComponentType value);

    /**
     * Returns the value of the '<em><b>Dyn Responsibilities</b></em>' reference list.
     * The list contents are of type {@link urncore.DynamicResponsibility}.
     * It is bidirectional and its opposite is '{@link urncore.DynamicResponsibility#getPool <em>Pool</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Dyn Responsibilities</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Dyn Responsibilities</em>' reference list.
     * @see urncore.UrncorePackage#getPool_DynResponsibilities()
     * @see urncore.DynamicResponsibility#getPool
     * @model type="urncore.DynamicResponsibility" opposite="pool"
     * @generated
     */
    EList getDynResponsibilities();

} // Pool
