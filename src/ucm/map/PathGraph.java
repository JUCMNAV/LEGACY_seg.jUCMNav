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
 * </ul>
 * </p>
 *
 * @see ucm.map.MapPackage#getPathGraph()
 * @model 
 * @generated
 */
public interface PathGraph extends EObject {
    /**
     * Returns the value of the '<em><b>Path Nodes</b></em>' containment reference list.
     * The list contents are of type {@link ucm.map.PathNode}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Path Nodes</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Path Nodes</em>' containment reference list.
     * @see ucm.map.MapPackage#getPathGraph_PathNodes()
     * @model type="ucm.map.PathNode" containment="true"
     * @generated
     */
    EList getPathNodes();

    /**
     * Returns the value of the '<em><b>Node Connections</b></em>' containment reference list.
     * The list contents are of type {@link ucm.map.NodeConnection}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Node Connections</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Node Connections</em>' containment reference list.
     * @see ucm.map.MapPackage#getPathGraph_NodeConnections()
     * @model type="ucm.map.NodeConnection" containment="true"
     * @generated
     */
    EList getNodeConnections();

} // PathGraph
