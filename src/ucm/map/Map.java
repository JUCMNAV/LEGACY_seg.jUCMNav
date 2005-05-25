/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map;

import org.eclipse.emf.common.util.EList;

import ucm.UCMspec;

import urncore.UCMmodelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Map</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.map.Map#getPathGraph <em>Path Graph</em>}</li>
 *   <li>{@link ucm.map.Map#getCompRefs <em>Comp Refs</em>}</li>
 *   <li>{@link ucm.map.Map#getUcmspec <em>Ucmspec</em>}</li>
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
	 * Returns the value of the '<em><b>Path Graph</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link ucm.map.PathGraph#getMap <em>Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Path Graph</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Path Graph</em>' containment reference.
	 * @see #setPathGraph(PathGraph)
	 * @see ucm.map.MapPackage#getMap_PathGraph()
	 * @see ucm.map.PathGraph#getMap
	 * @model opposite="map" containment="true"
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
	 * It is bidirectional and its opposite is '{@link ucm.map.ComponentRef#getMap <em>Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comp Refs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Comp Refs</em>' containment reference list.
	 * @see ucm.map.MapPackage#getMap_CompRefs()
	 * @see ucm.map.ComponentRef#getMap
	 * @model type="ucm.map.ComponentRef" opposite="map" containment="true"
	 * @generated
	 */
	EList getCompRefs();

	/**
	 * Returns the value of the '<em><b>Ucmspec</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ucm.UCMspec#getMaps <em>Maps</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ucmspec</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ucmspec</em>' container reference.
	 * @see #setUcmspec(UCMspec)
	 * @see ucm.map.MapPackage#getMap_Ucmspec()
	 * @see ucm.UCMspec#getMaps
	 * @model opposite="maps" required="true"
	 * @generated
	 */
	UCMspec getUcmspec();

	/**
	 * Sets the value of the '{@link ucm.map.Map#getUcmspec <em>Ucmspec</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ucmspec</em>' container reference.
	 * @see #getUcmspec()
	 * @generated
	 */
	void setUcmspec(UCMspec value);

	/**
	 * Returns the value of the '<em><b>Parent Stub</b></em>' reference list.
	 * The list contents are of type {@link ucm.map.PluginBinding}.
	 * It is bidirectional and its opposite is '{@link ucm.map.PluginBinding#getPlugin <em>Plugin</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Stub</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Stub</em>' reference list.
	 * @see ucm.map.MapPackage#getMap_ParentStub()
	 * @see ucm.map.PluginBinding#getPlugin
	 * @model type="ucm.map.PluginBinding" opposite="plugin"
	 * @generated
	 */
	EList getParentStub();

} // Map
