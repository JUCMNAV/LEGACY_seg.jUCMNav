/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node Label</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link urncore.NodeLabel#getNode <em>Node</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getNodeLabel()
 * @model 
 * @generated
 */
public interface NodeLabel extends Label {
    /**
     * Returns the value of the '<em><b>Node</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link urncore.SpecificationNode#getLabel <em>Label</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Node</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Node</em>' container reference.
     * @see #setNode(SpecificationNode)
     * @see urncore.UrncorePackage#getNodeLabel_Node()
     * @see urncore.SpecificationNode#getLabel
     * @model opposite="label" required="true"
     * @generated
     */
    SpecificationNode getNode();

    /**
     * Sets the value of the '{@link urncore.NodeLabel#getNode <em>Node</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Node</em>' container reference.
     * @see #getNode()
     * @generated
     */
    void setNode(SpecificationNode value);

} // NodeLabel
