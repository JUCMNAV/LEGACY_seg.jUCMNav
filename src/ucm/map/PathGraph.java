/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Path Graph</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The PathGraph specifies all the elements (PathNodes)  that make up the paths and their interconnections. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.map.PathGraph#getPathNodes <em>Path Nodes</em>}</li>
 *   <li>{@link ucm.map.PathGraph#getNodeConnections <em>Node Connections</em>}</li>
 *   <li>{@link ucm.map.PathGraph#getMap <em>Map</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.map.MapPackage#getPathGraph()
 * @model 
 * @generated
 */
public interface PathGraph extends EObject{
    /**
     * Returns the value of the '<em><b>Path Nodes</b></em>' containment reference list.
     * The list contents are of type {@link ucm.map.PathNode}.
     * It is bidirectional and its opposite is '{@link ucm.map.PathNode#getPathGraph <em>Path Graph</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Path Nodes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Path Nodes</em>' containment reference list.
     * @see ucm.map.MapPackage#getPathGraph_PathNodes()
     * @see ucm.map.PathNode#getPathGraph
     * @model type="ucm.map.PathNode" opposite="pathGraph" containment="true"
     * @generated
     */
	EList getPathNodes();

    /**
     * Returns the value of the '<em><b>Node Connections</b></em>' containment reference list.
     * The list contents are of type {@link ucm.map.NodeConnection}.
     * It is bidirectional and its opposite is '{@link ucm.map.NodeConnection#getPathGraph <em>Path Graph</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Node Connections</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Node Connections</em>' containment reference list.
     * @see ucm.map.MapPackage#getPathGraph_NodeConnections()
     * @see ucm.map.NodeConnection#getPathGraph
     * @model type="ucm.map.NodeConnection" opposite="pathGraph" containment="true"
     * @generated
     */
	EList getNodeConnections();

    /**
     * Returns the value of the '<em><b>Map</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link ucm.map.Map#getPathGraph <em>Path Graph</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Map</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Map</em>' container reference.
     * @see #setMap(Map)
     * @see ucm.map.MapPackage#getPathGraph_Map()
     * @see ucm.map.Map#getPathGraph
     * @model opposite="pathGraph" required="true"
     * @generated
     */
	Map getMap();

    /**
     * Sets the value of the '{@link ucm.map.PathGraph#getMap <em>Map</em>}' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Map</em>' container reference.
     * @see #getMap()
     * @generated
     */
	void setMap(Map value);

} // PathGraph
