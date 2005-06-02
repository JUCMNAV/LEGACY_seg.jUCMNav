/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map;

import org.eclipse.emf.common.util.EList;

import urncore.NodeLabel;
import urncore.UCMmodelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Path Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.map.PathNode#getX <em>X</em>}</li>
 *   <li>{@link ucm.map.PathNode#getY <em>Y</em>}</li>
 *   <li>{@link ucm.map.PathNode#getPathGraph <em>Path Graph</em>}</li>
 *   <li>{@link ucm.map.PathNode#getSucc <em>Succ</em>}</li>
 *   <li>{@link ucm.map.PathNode#getPred <em>Pred</em>}</li>
 *   <li>{@link ucm.map.PathNode#getCompRef <em>Comp Ref</em>}</li>
 *   <li>{@link ucm.map.PathNode#getLabel <em>Label</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.map.MapPackage#getPathNode()
 * @model abstract="true"
 * @generated
 */
public interface PathNode extends UCMmodelElement{
    /**
     * Returns the value of the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>X</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>X</em>' attribute.
     * @see #setX(int)
     * @see ucm.map.MapPackage#getPathNode_X()
     * @model 
     * @generated
     */
	int getX();

    /**
     * Sets the value of the '{@link ucm.map.PathNode#getX <em>X</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>X</em>' attribute.
     * @see #getX()
     * @generated
     */
	void setX(int value);

    /**
     * Returns the value of the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Y</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Y</em>' attribute.
     * @see #setY(int)
     * @see ucm.map.MapPackage#getPathNode_Y()
     * @model 
     * @generated
     */
	int getY();

    /**
     * Sets the value of the '{@link ucm.map.PathNode#getY <em>Y</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Y</em>' attribute.
     * @see #getY()
     * @generated
     */
	void setY(int value);

    /**
     * Returns the value of the '<em><b>Path Graph</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link ucm.map.PathGraph#getPathNodes <em>Path Nodes</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Path Graph</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Path Graph</em>' container reference.
     * @see #setPathGraph(PathGraph)
     * @see ucm.map.MapPackage#getPathNode_PathGraph()
     * @see ucm.map.PathGraph#getPathNodes
     * @model opposite="pathNodes" required="true"
     * @generated
     */
	PathGraph getPathGraph();

    /**
     * Sets the value of the '{@link ucm.map.PathNode#getPathGraph <em>Path Graph</em>}' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Path Graph</em>' container reference.
     * @see #getPathGraph()
     * @generated
     */
	void setPathGraph(PathGraph value);

    /**
     * Returns the value of the '<em><b>Succ</b></em>' reference list.
     * The list contents are of type {@link ucm.map.NodeConnection}.
     * It is bidirectional and its opposite is '{@link ucm.map.NodeConnection#getSource <em>Source</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Succ</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Succ</em>' reference list.
     * @see ucm.map.MapPackage#getPathNode_Succ()
     * @see ucm.map.NodeConnection#getSource
     * @model type="ucm.map.NodeConnection" opposite="source"
     * @generated
     */
	EList getSucc();

    /**
     * Returns the value of the '<em><b>Pred</b></em>' reference list.
     * The list contents are of type {@link ucm.map.NodeConnection}.
     * It is bidirectional and its opposite is '{@link ucm.map.NodeConnection#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pred</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Pred</em>' reference list.
     * @see ucm.map.MapPackage#getPathNode_Pred()
     * @see ucm.map.NodeConnection#getTarget
     * @model type="ucm.map.NodeConnection" opposite="target"
     * @generated
     */
	EList getPred();

    /**
     * Returns the value of the '<em><b>Comp Ref</b></em>' reference.
     * It is bidirectional and its opposite is '{@link ucm.map.ComponentRef#getPathNodes <em>Path Nodes</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comp Ref</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Comp Ref</em>' reference.
     * @see #setCompRef(ComponentRef)
     * @see ucm.map.MapPackage#getPathNode_CompRef()
     * @see ucm.map.ComponentRef#getPathNodes
     * @model opposite="pathNodes"
     * @generated
     */
	ComponentRef getCompRef();

    /**
     * Sets the value of the '{@link ucm.map.PathNode#getCompRef <em>Comp Ref</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Comp Ref</em>' reference.
     * @see #getCompRef()
     * @generated
     */
	void setCompRef(ComponentRef value);

    /**
     * Returns the value of the '<em><b>Label</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link urncore.NodeLabel#getPathNode <em>Path Node</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Label</em>' containment reference.
     * @see #setLabel(NodeLabel)
     * @see ucm.map.MapPackage#getPathNode_Label()
     * @see urncore.NodeLabel#getPathNode
     * @model opposite="pathNode" containment="true"
     * @generated
     */
	NodeLabel getLabel();

    /**
     * Sets the value of the '{@link ucm.map.PathNode#getLabel <em>Label</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Label</em>' containment reference.
     * @see #getLabel()
     * @generated
     */
	void setLabel(NodeLabel value);

} // PathNode
