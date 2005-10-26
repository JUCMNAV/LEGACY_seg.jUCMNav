/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Specification Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link urncore.SpecificationConnection#getSource <em>Source</em>}</li>
 *   <li>{@link urncore.SpecificationConnection#getTarget <em>Target</em>}</li>
 *   <li>{@link urncore.SpecificationConnection#getSpecDiagram <em>Spec Diagram</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getSpecificationConnection()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface SpecificationConnection extends EObject {
    /**
     * Returns the value of the '<em><b>Source</b></em>' reference.
     * It is bidirectional and its opposite is '{@link urncore.SpecificationNode#getSucc <em>Succ</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source</em>' reference.
     * @see #setSource(SpecificationNode)
     * @see urncore.UrncorePackage#getSpecificationConnection_Source()
     * @see urncore.SpecificationNode#getSucc
     * @model opposite="succ" required="true"
     * @generated
     */
    SpecificationNode getSource();

    /**
     * Sets the value of the '{@link urncore.SpecificationConnection#getSource <em>Source</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source</em>' reference.
     * @see #getSource()
     * @generated
     */
    void setSource(SpecificationNode value);

    /**
     * Returns the value of the '<em><b>Target</b></em>' reference.
     * It is bidirectional and its opposite is '{@link urncore.SpecificationNode#getPred <em>Pred</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target</em>' reference.
     * @see #setTarget(SpecificationNode)
     * @see urncore.UrncorePackage#getSpecificationConnection_Target()
     * @see urncore.SpecificationNode#getPred
     * @model opposite="pred" required="true"
     * @generated
     */
    SpecificationNode getTarget();

    /**
     * Sets the value of the '{@link urncore.SpecificationConnection#getTarget <em>Target</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target</em>' reference.
     * @see #getTarget()
     * @generated
     */
    void setTarget(SpecificationNode value);

    /**
     * Returns the value of the '<em><b>Spec Diagram</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link urncore.SpecificationDiagram#getConnections <em>Connections</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Spec Diagram</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Spec Diagram</em>' container reference.
     * @see #setSpecDiagram(SpecificationDiagram)
     * @see urncore.UrncorePackage#getSpecificationConnection_SpecDiagram()
     * @see urncore.SpecificationDiagram#getConnections
     * @model opposite="connections" required="true"
     * @generated
     */
    SpecificationDiagram getSpecDiagram();

    /**
     * Sets the value of the '{@link urncore.SpecificationConnection#getSpecDiagram <em>Spec Diagram</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Spec Diagram</em>' container reference.
     * @see #getSpecDiagram()
     * @generated
     */
    void setSpecDiagram(SpecificationDiagram value);

} // SpecificationConnection
