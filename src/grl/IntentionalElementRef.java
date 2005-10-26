/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl;

import urncore.GRLmodelElement;
import urncore.SpecificationNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Intentional Element Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.IntentionalElementRef#getDef <em>Def</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.GrlPackage#getIntentionalElementRef()
 * @model 
 * @generated
 */
public interface IntentionalElementRef extends GRLmodelElement, SpecificationNode {
    /**
     * Returns the value of the '<em><b>Def</b></em>' reference.
     * It is bidirectional and its opposite is '{@link grl.IntentionalElement#getRefs <em>Refs</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Def</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Def</em>' reference.
     * @see #setDef(IntentionalElement)
     * @see grl.GrlPackage#getIntentionalElementRef_Def()
     * @see grl.IntentionalElement#getRefs
     * @model opposite="refs" required="true"
     * @generated
     */
    IntentionalElement getDef();

    /**
     * Sets the value of the '{@link grl.IntentionalElementRef#getDef <em>Def</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Def</em>' reference.
     * @see #getDef()
     * @generated
     */
    void setDef(IntentionalElement value);

} // IntentionalElementRef
