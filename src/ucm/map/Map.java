/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map;

import org.eclipse.emf.common.util.EList;

import urncore.UCMmodelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Map</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.map.Map#getTitle <em>Title</em>}</li>
 *   <li>{@link ucm.map.Map#getPathGraph <em>Path Graph</em>}</li>
 *   <li>{@link ucm.map.Map#getCompRefs <em>Comp Refs</em>}</li>
 *   <li>{@link ucm.map.Map#getParentStub <em>Parent Stub</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.map.MapPackage#getMap()
 * @model 
 * @generated
 */
public interface Map extends UCMmodelElement {
	/**
	 * Returns the value of the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Title</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Title</em>' attribute.
	 * @see #setTitle(String)
	 * @see ucm.map.MapPackage#getMap_Title()
	 * @model 
	 * @generated
	 */
	String getTitle();

	/**
	 * Sets the value of the '{@link ucm.map.Map#getTitle <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Title</em>' attribute.
	 * @see #getTitle()
	 * @generated
	 */
	void setTitle(String value);

	/**
	 * Returns the value of the '<em><b>Path Graph</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Path Graph</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Path Graph</em>' containment reference.
	 * @see #setPathGraph(PathGraph)
	 * @see ucm.map.MapPackage#getMap_PathGraph()
	 * @model containment="true"
	 * @generated
	 */
	PathGraph getPathGraph();

	/**
	 * Sets the value of the '{@link ucm.map.Map#getPathGraph <em>Path Graph</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path Graph</em>' containment reference.
	 * @see #getPathGraph()
	 * @generated
	 */
	void setPathGraph(PathGraph value);

	/**
	 * Returns the value of the '<em><b>Comp Refs</b></em>' containment reference list.
	 * The list contents are of type {@link ucm.map.ComponentRef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comp Refs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Comp Refs</em>' containment reference list.
	 * @see ucm.map.MapPackage#getMap_CompRefs()
	 * @model type="ucm.map.ComponentRef" containment="true"
	 * @generated
	 */
	EList getCompRefs();

	/**
	 * Returns the value of the '<em><b>Parent Stub</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link ucm.map.PluginBinding#getPlugin <em>Plugin</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Stub</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Stub</em>' reference.
	 * @see #setParentStub(PluginBinding)
	 * @see ucm.map.MapPackage#getMap_ParentStub()
	 * @see ucm.map.PluginBinding#getPlugin
	 * @model opposite="plugin"
	 * @generated
	 */
	PluginBinding getParentStub();

	/**
	 * Sets the value of the '{@link ucm.map.Map#getParentStub <em>Parent Stub</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent Stub</em>' reference.
	 * @see #getParentStub()
	 * @generated
	 */
	void setParentStub(PluginBinding value);

} // Map
