/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stub</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.map.Stub#isDynamic <em>Dynamic</em>}</li>
 *   <li>{@link ucm.map.Stub#isShared <em>Shared</em>}</li>
 *   <li>{@link ucm.map.Stub#getBindings <em>Bindings</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.map.MapPackage#getStub()
 * @model
 * @generated
 */
public interface Stub extends PathNode {
	/**
	 * Returns the value of the '<em><b>Dynamic</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Dynamic</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Dynamic</em>' attribute.
	 * @see #setDynamic(boolean)
	 * @see ucm.map.MapPackage#getStub_Dynamic()
	 * @model default="false"
	 * @generated
	 */
    boolean isDynamic();

	/**
	 * Sets the value of the '{@link ucm.map.Stub#isDynamic <em>Dynamic</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dynamic</em>' attribute.
	 * @see #isDynamic()
	 * @generated
	 */
    void setDynamic(boolean value);

	/**
	 * Returns the value of the '<em><b>Shared</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Shared</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Shared</em>' attribute.
	 * @see #setShared(boolean)
	 * @see ucm.map.MapPackage#getStub_Shared()
	 * @model default="false"
	 * @generated
	 */
    boolean isShared();

	/**
	 * Sets the value of the '{@link ucm.map.Stub#isShared <em>Shared</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Shared</em>' attribute.
	 * @see #isShared()
	 * @generated
	 */
    void setShared(boolean value);

	/**
	 * Returns the value of the '<em><b>Bindings</b></em>' containment reference list.
	 * The list contents are of type {@link ucm.map.PluginBinding}.
	 * It is bidirectional and its opposite is '{@link ucm.map.PluginBinding#getStub <em>Stub</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Bindings</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Bindings</em>' containment reference list.
	 * @see ucm.map.MapPackage#getStub_Bindings()
	 * @see ucm.map.PluginBinding#getStub
	 * @model type="ucm.map.PluginBinding" opposite="stub" containment="true"
	 * @generated
	 */
    EList getBindings();

} // Stub
