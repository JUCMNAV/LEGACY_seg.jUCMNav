/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dependency</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.Dependency#getDepender <em>Depender</em>}</li>
 *   <li>{@link grl.Dependency#getDependum <em>Dependum</em>}</li>
 *   <li>{@link grl.Dependency#getDependee <em>Dependee</em>}</li>
 *   <li>{@link grl.Dependency#getSecondRefs <em>Second Refs</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.GrlPackage#getDependency()
 * @model 
 * @generated
 */
public interface Dependency extends ElementLink {
    /**
     * Returns the value of the '<em><b>Depender</b></em>' reference.
     * It is bidirectional and its opposite is '{@link grl.IntentionalElement#getIsDepender <em>Is Depender</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Depender</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Depender</em>' reference.
     * @see #setDepender(IntentionalElement)
     * @see grl.GrlPackage#getDependency_Depender()
     * @see grl.IntentionalElement#getIsDepender
     * @model opposite="isDepender" required="true"
     * @generated
     */
    IntentionalElement getDepender();

    /**
     * Sets the value of the '{@link grl.Dependency#getDepender <em>Depender</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Depender</em>' reference.
     * @see #getDepender()
     * @generated
     */
    void setDepender(IntentionalElement value);

    /**
     * Returns the value of the '<em><b>Dependum</b></em>' reference.
     * It is bidirectional and its opposite is '{@link grl.IntentionalElement#getIsDependum <em>Is Dependum</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Dependum</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Dependum</em>' reference.
     * @see #setDependum(IntentionalElement)
     * @see grl.GrlPackage#getDependency_Dependum()
     * @see grl.IntentionalElement#getIsDependum
     * @model opposite="isDependum" required="true"
     * @generated
     */
    IntentionalElement getDependum();

    /**
     * Sets the value of the '{@link grl.Dependency#getDependum <em>Dependum</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Dependum</em>' reference.
     * @see #getDependum()
     * @generated
     */
    void setDependum(IntentionalElement value);

    /**
     * Returns the value of the '<em><b>Dependee</b></em>' reference.
     * It is bidirectional and its opposite is '{@link grl.IntentionalElement#getIsDependee <em>Is Dependee</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Dependee</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Dependee</em>' reference.
     * @see #setDependee(IntentionalElement)
     * @see grl.GrlPackage#getDependency_Dependee()
     * @see grl.IntentionalElement#getIsDependee
     * @model opposite="isDependee" required="true"
     * @generated
     */
    IntentionalElement getDependee();

    /**
     * Sets the value of the '{@link grl.Dependency#getDependee <em>Dependee</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Dependee</em>' reference.
     * @see #getDependee()
     * @generated
     */
    void setDependee(IntentionalElement value);

    /**
     * Returns the value of the '<em><b>Second Refs</b></em>' reference list.
     * The list contents are of type {@link grl.LinkRef}.
     * It is bidirectional and its opposite is '{@link grl.LinkRef#getDependency <em>Dependency</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Second Refs</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Second Refs</em>' reference list.
     * @see grl.GrlPackage#getDependency_SecondRefs()
     * @see grl.LinkRef#getDependency
     * @model type="grl.LinkRef" opposite="dependency"
     * @generated
     */
    EList getSecondRefs();

} // Dependency
