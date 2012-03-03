/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.map.ComponentBinding#getBinding <em>Binding</em>}</li>
 *   <li>{@link ucm.map.ComponentBinding#getParentComponent <em>Parent Component</em>}</li>
 *   <li>{@link ucm.map.ComponentBinding#getPluginComponent <em>Plugin Component</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.map.MapPackage#getComponentBinding()
 * @model
 * @generated
 */
public interface ComponentBinding extends EObject {
	/**
	 * Returns the value of the '<em><b>Binding</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ucm.map.PluginBinding#getComponents <em>Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Binding</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Binding</em>' container reference.
	 * @see #setBinding(PluginBinding)
	 * @see ucm.map.MapPackage#getComponentBinding_Binding()
	 * @see ucm.map.PluginBinding#getComponents
	 * @model opposite="components" required="true"
	 * @generated
	 */
	PluginBinding getBinding();

	/**
	 * Sets the value of the '{@link ucm.map.ComponentBinding#getBinding <em>Binding</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding</em>' container reference.
	 * @see #getBinding()
	 * @generated
	 */
	void setBinding(PluginBinding value);

	/**
	 * Returns the value of the '<em><b>Parent Component</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link ucm.map.ComponentRef#getParentBindings <em>Parent Bindings</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Component</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Component</em>' reference.
	 * @see #setParentComponent(ComponentRef)
	 * @see ucm.map.MapPackage#getComponentBinding_ParentComponent()
	 * @see ucm.map.ComponentRef#getParentBindings
	 * @model opposite="parentBindings" required="true"
	 * @generated
	 */
	ComponentRef getParentComponent();

	/**
	 * Sets the value of the '{@link ucm.map.ComponentBinding#getParentComponent <em>Parent Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent Component</em>' reference.
	 * @see #getParentComponent()
	 * @generated
	 */
	void setParentComponent(ComponentRef value);

	/**
	 * Returns the value of the '<em><b>Plugin Component</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link ucm.map.ComponentRef#getPluginBindings <em>Plugin Bindings</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Plugin Component</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Plugin Component</em>' reference.
	 * @see #setPluginComponent(ComponentRef)
	 * @see ucm.map.MapPackage#getComponentBinding_PluginComponent()
	 * @see ucm.map.ComponentRef#getPluginBindings
	 * @model opposite="pluginBindings" required="true"
	 * @generated
	 */
	ComponentRef getPluginComponent();

	/**
	 * Sets the value of the '{@link ucm.map.ComponentBinding#getPluginComponent <em>Plugin Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Plugin Component</em>' reference.
	 * @see #getPluginComponent()
	 * @generated
	 */
	void setPluginComponent(ComponentRef value);

} // ComponentBinding
