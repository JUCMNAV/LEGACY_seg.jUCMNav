/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map;

import org.eclipse.emf.common.util.EList;

import urncore.IURNContainerRef;
import urncore.UCMmodelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A reference to a component or pool in a particular map.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.map.ComponentRef#getRole <em>Role</em>}</li>
 *   <li>{@link ucm.map.ComponentRef#getReplicationFactor <em>Replication Factor</em>}</li>
 *   <li>{@link ucm.map.ComponentRef#isAnchored <em>Anchored</em>}</li>
 *   <li>{@link ucm.map.ComponentRef#getParentBindings <em>Parent Bindings</em>}</li>
 *   <li>{@link ucm.map.ComponentRef#getPluginBindings <em>Plugin Bindings</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.map.MapPackage#getComponentRef()
 * @model
 * @generated
 */
public interface ComponentRef extends UCMmodelElement, IURNContainerRef {
    /**
	 * Returns the value of the '<em><b>Role</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Role</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Role</em>' attribute.
	 * @see #setRole(String)
	 * @see ucm.map.MapPackage#getComponentRef_Role()
	 * @model
	 * @generated
	 */
    String getRole();

    /**
	 * Sets the value of the '{@link ucm.map.ComponentRef#getRole <em>Role</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Role</em>' attribute.
	 * @see #getRole()
	 * @generated
	 */
    void setRole(String value);

    /**
	 * Returns the value of the '<em><b>Replication Factor</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Replication Factor</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Replication Factor</em>' attribute.
	 * @see #setReplicationFactor(int)
	 * @see ucm.map.MapPackage#getComponentRef_ReplicationFactor()
	 * @model default="1"
	 * @generated
	 */
    int getReplicationFactor();

    /**
	 * Sets the value of the '{@link ucm.map.ComponentRef#getReplicationFactor <em>Replication Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Replication Factor</em>' attribute.
	 * @see #getReplicationFactor()
	 * @generated
	 */
    void setReplicationFactor(int value);

    /**
	 * Returns the value of the '<em><b>Anchored</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Anchored</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Anchored</em>' attribute.
	 * @see #setAnchored(boolean)
	 * @see ucm.map.MapPackage#getComponentRef_Anchored()
	 * @model default="false"
	 * @generated
	 */
    boolean isAnchored();

    /**
	 * Sets the value of the '{@link ucm.map.ComponentRef#isAnchored <em>Anchored</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Anchored</em>' attribute.
	 * @see #isAnchored()
	 * @generated
	 */
    void setAnchored(boolean value);

				/**
	 * Returns the value of the '<em><b>Parent Bindings</b></em>' reference list.
	 * The list contents are of type {@link ucm.map.ComponentBinding}.
	 * It is bidirectional and its opposite is '{@link ucm.map.ComponentBinding#getParentComponent <em>Parent Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Bindings</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Bindings</em>' reference list.
	 * @see ucm.map.MapPackage#getComponentRef_ParentBindings()
	 * @see ucm.map.ComponentBinding#getParentComponent
	 * @model type="ucm.map.ComponentBinding" opposite="parentComponent"
	 * @generated
	 */
	EList getParentBindings();

				/**
	 * Returns the value of the '<em><b>Plugin Bindings</b></em>' reference list.
	 * The list contents are of type {@link ucm.map.ComponentBinding}.
	 * It is bidirectional and its opposite is '{@link ucm.map.ComponentBinding#getPluginComponent <em>Plugin Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Plugin Bindings</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Plugin Bindings</em>' reference list.
	 * @see ucm.map.MapPackage#getComponentRef_PluginBindings()
	 * @see ucm.map.ComponentBinding#getPluginComponent
	 * @model type="ucm.map.ComponentBinding" opposite="pluginComponent"
	 * @generated
	 */
	EList getPluginBindings();

} // ComponentRef
