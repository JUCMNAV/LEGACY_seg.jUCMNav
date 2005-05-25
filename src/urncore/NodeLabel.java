/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore;

import ucm.map.PathNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node Label</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link urncore.NodeLabel#getPathNode <em>Path Node</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getNodeLabel()
 * @model 
 * @generated
 */
public interface NodeLabel extends Label {
	/**
	 * Returns the value of the '<em><b>Path Node</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ucm.map.PathNode#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Path Node</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Path Node</em>' container reference.
	 * @see #setPathNode(PathNode)
	 * @see urncore.UrncorePackage#getNodeLabel_PathNode()
	 * @see ucm.map.PathNode#getLabel
	 * @model opposite="label" required="true"
	 * @generated
	 */
	PathNode getPathNode();

	/**
	 * Sets the value of the '{@link urncore.NodeLabel#getPathNode <em>Path Node</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path Node</em>' container reference.
	 * @see #getPathNode()
	 * @generated
	 */
	void setPathNode(PathNode value);

} // NodeLabel
