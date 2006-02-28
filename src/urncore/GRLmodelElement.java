/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore;


import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>GR Lmodel Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link urncore.GRLmodelElement#getUrnlinks <em>Urnlinks</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getGRLmodelElement()
 * @model abstract="true"
 * @generated
 */
public interface GRLmodelElement extends URNmodelElement{
    /**
     * Returns the value of the '<em><b>Urnlinks</b></em>' reference list.
     * The list contents are of type {@link urn.URNlink}.
     * It is bidirectional and its opposite is '{@link urn.URNlink#getGrlModelElements <em>Grl Model Elements</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Urnlinks</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Urnlinks</em>' reference list.
     * @see urncore.UrncorePackage#getGRLmodelElement_Urnlinks()
     * @see urn.URNlink#getGrlModelElements
     * @model type="urn.URNlink" opposite="grlModelElements"
     * @generated
     */
    EList getUrnlinks();

} // GRLmodelElement
