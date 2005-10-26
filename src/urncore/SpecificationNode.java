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
 *   <li>{@link urncore.SpecificationNode#getX <em>X</em>}</li>
 *   <li>{@link urncore.SpecificationNode#getY <em>Y</em>}</li>
 *   <li>{@link urncore.SpecificationNode#getSpecDiagram <em>Spec Diagram</em>}</li>
 *   <li>{@link urncore.SpecificationNode#getCompRef <em>Comp Ref</em>}</li>
 *   <li>{@link urncore.SpecificationNode#getSucc <em>Succ</em>}</li>
 *   <li>{@link urncore.SpecificationNode#getPred <em>Pred</em>}</li>
 *   <li>{@link urncore.SpecificationNode#getLabel <em>Label</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getSpecificationNode()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface SpecificationNode extends EObject {
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
     * @see urncore.UrncorePackage#getSpecificationNode_X()
     * @model 
     * @generated
     */
    int getX();

    /**
     * Sets the value of the '{@link urncore.SpecificationNode#getX <em>X</em>}' attribute.
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
     * @see urncore.UrncorePackage#getSpecificationNode_Y()
     * @model 
     * @generated
     */
    int getY();

    /**
     * Sets the value of the '{@link urncore.SpecificationNode#getY <em>Y</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Y</em>' attribute.
     * @see #getY()
     * @generated
     */
    void setY(int value);

    /**
     * Returns the value of the '<em><b>Spec Diagram</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link urncore.SpecificationDiagram#getNodes <em>Nodes</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Spec Diagram</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Spec Diagram</em>' container reference.
     * @see #setSpecDiagram(SpecificationDiagram)
     * @see urncore.UrncorePackage#getSpecificationNode_SpecDiagram()
     * @see urncore.SpecificationDiagram#getNodes
     * @model opposite="nodes" required="true"
     * @generated
     */
    SpecificationDiagram getSpecDiagram();

    /**
     * Sets the value of the '{@link urncore.SpecificationNode#getSpecDiagram <em>Spec Diagram</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Spec Diagram</em>' container reference.
     * @see #getSpecDiagram()
     * @generated
     */
    void setSpecDiagram(SpecificationDiagram value);

    /**
     * Returns the value of the '<em><b>Comp Ref</b></em>' reference.
     * It is bidirectional and its opposite is '{@link urncore.SpecificationComponentRef#getNodes <em>Nodes</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Comp Ref</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Comp Ref</em>' reference.
     * @see #setCompRef(SpecificationComponentRef)
     * @see urncore.UrncorePackage#getSpecificationNode_CompRef()
     * @see urncore.SpecificationComponentRef#getNodes
     * @model opposite="nodes"
     * @generated
     */
    SpecificationComponentRef getCompRef();

    /**
     * Sets the value of the '{@link urncore.SpecificationNode#getCompRef <em>Comp Ref</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Comp Ref</em>' reference.
     * @see #getCompRef()
     * @generated
     */
    void setCompRef(SpecificationComponentRef value);

    /**
     * Returns the value of the '<em><b>Succ</b></em>' reference list.
     * The list contents are of type {@link urncore.SpecificationConnection}.
     * It is bidirectional and its opposite is '{@link urncore.SpecificationConnection#getSource <em>Source</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Succ</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Succ</em>' reference list.
     * @see urncore.UrncorePackage#getSpecificationNode_Succ()
     * @see urncore.SpecificationConnection#getSource
     * @model type="urncore.SpecificationConnection" opposite="source"
     * @generated
     */
    EList getSucc();

    /**
     * Returns the value of the '<em><b>Pred</b></em>' reference list.
     * The list contents are of type {@link urncore.SpecificationConnection}.
     * It is bidirectional and its opposite is '{@link urncore.SpecificationConnection#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Pred</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Pred</em>' reference list.
     * @see urncore.UrncorePackage#getSpecificationNode_Pred()
     * @see urncore.SpecificationConnection#getTarget
     * @model type="urncore.SpecificationConnection" opposite="target"
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
     * @see urncore.UrncorePackage#getSpecificationNode_Label()
     * @see urncore.NodeLabel#getNode
     * @model opposite="node" containment="true"
     * @generated
     */
    NodeLabel getLabel();

    /**
     * Sets the value of the '{@link urncore.SpecificationNode#getLabel <em>Label</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Label</em>' containment reference.
     * @see #getLabel()
     * @generated
     */
    void setLabel(NodeLabel value);

} // SpecificationNode
