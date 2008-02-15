/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Specification Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link urncore.IURNNode#getX <em>X</em>}</li>
 *   <li>{@link urncore.IURNNode#getY <em>Y</em>}</li>
 *   <li>{@link urncore.IURNNode#getDiagram <em>Diagram</em>}</li>
 *   <li>{@link urncore.IURNNode#getContRef <em>Cont Ref</em>}</li>
 *   <li>{@link urncore.IURNNode#getSucc <em>Succ</em>}</li>
 *   <li>{@link urncore.IURNNode#getPred <em>Pred</em>}</li>
 *   <li>{@link urncore.IURNNode#getLabel <em>Label</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getIURNNode()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IURNNode extends EObject {
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
	 * @see urncore.UrncorePackage#getIURNNode_X()
	 * @model
	 * @generated
	 */
    int getX();

    /**
	 * Sets the value of the '{@link urncore.IURNNode#getX <em>X</em>}' attribute.
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
	 * @see urncore.UrncorePackage#getIURNNode_Y()
	 * @model
	 * @generated
	 */
    int getY();

    /**
	 * Sets the value of the '{@link urncore.IURNNode#getY <em>Y</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Y</em>' attribute.
	 * @see #getY()
	 * @generated
	 */
    void setY(int value);

    /**
	 * Returns the value of the '<em><b>Diagram</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link urncore.IURNDiagram#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Spec Diagram</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Diagram</em>' container reference.
	 * @see #setDiagram(IURNDiagram)
	 * @see urncore.UrncorePackage#getIURNNode_Diagram()
	 * @see urncore.IURNDiagram#getNodes
	 * @model opposite="nodes" required="true"
	 * @generated
	 */
    IURNDiagram getDiagram();

    /**
	 * Sets the value of the '{@link urncore.IURNNode#getDiagram <em>Diagram</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Diagram</em>' container reference.
	 * @see #getDiagram()
	 * @generated
	 */
    void setDiagram(IURNDiagram value);

    /**
	 * Returns the value of the '<em><b>Cont Ref</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link urncore.IURNContainerRef#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Cont Ref</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Cont Ref</em>' reference.
	 * @see #setContRef(IURNContainerRef)
	 * @see urncore.UrncorePackage#getIURNNode_ContRef()
	 * @see urncore.IURNContainerRef#getNodes
	 * @model opposite="nodes"
	 * @generated
	 */
    IURNContainerRef getContRef();

    /**
	 * Sets the value of the '{@link urncore.IURNNode#getContRef <em>Cont Ref</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cont Ref</em>' reference.
	 * @see #getContRef()
	 * @generated
	 */
    void setContRef(IURNContainerRef value);

    /**
	 * Returns the value of the '<em><b>Succ</b></em>' reference list.
	 * The list contents are of type {@link urncore.IURNConnection}.
	 * It is bidirectional and its opposite is '{@link urncore.IURNConnection#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Succ</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Succ</em>' reference list.
	 * @see urncore.UrncorePackage#getIURNNode_Succ()
	 * @see urncore.IURNConnection#getSource
	 * @model type="urncore.IURNConnection" opposite="source"
	 * @generated
	 */
    EList getSucc();

    /**
	 * Returns the value of the '<em><b>Pred</b></em>' reference list.
	 * The list contents are of type {@link urncore.IURNConnection}.
	 * It is bidirectional and its opposite is '{@link urncore.IURNConnection#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Pred</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Pred</em>' reference list.
	 * @see urncore.UrncorePackage#getIURNNode_Pred()
	 * @see urncore.IURNConnection#getTarget
	 * @model type="urncore.IURNConnection" opposite="target"
	 * @generated
	 */
    EList getPred();

    /**
	 * Returns the value of the '<em><b>Label</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link urncore.NodeLabel#getNode <em>Node</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Label</em>' containment reference.
	 * @see #setLabel(NodeLabel)
	 * @see urncore.UrncorePackage#getIURNNode_Label()
	 * @see urncore.NodeLabel#getNode
	 * @model opposite="node" containment="true"
	 * @generated
	 */
    NodeLabel getLabel();

    /**
	 * Sets the value of the '{@link urncore.IURNNode#getLabel <em>Label</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' containment reference.
	 * @see #getLabel()
	 * @generated
	 */
    void setLabel(NodeLabel value);

} // SpecificationNode
