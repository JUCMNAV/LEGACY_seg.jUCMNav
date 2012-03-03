/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Intentional Element Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.IntentionalElementRef#getCriticality <em>Criticality</em>}</li>
 *   <li>{@link grl.IntentionalElementRef#getPriority <em>Priority</em>}</li>
 *   <li>{@link grl.IntentionalElementRef#getDef <em>Def</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.GrlPackage#getIntentionalElementRef()
 * @model
 * @generated
 */
public interface IntentionalElementRef extends GRLNode {
    /**
	 * Returns the value of the '<em><b>Criticality</b></em>' attribute.
	 * The default value is <code>"None"</code>.
	 * The literals are from the enumeration {@link grl.Criticality}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Criticality</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Criticality</em>' attribute.
	 * @see grl.Criticality
	 * @see #setCriticality(Criticality)
	 * @see grl.GrlPackage#getIntentionalElementRef_Criticality()
	 * @model default="None"
	 * @generated
	 */
    Criticality getCriticality();

    /**
	 * Sets the value of the '{@link grl.IntentionalElementRef#getCriticality <em>Criticality</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Criticality</em>' attribute.
	 * @see grl.Criticality
	 * @see #getCriticality()
	 * @generated
	 */
    void setCriticality(Criticality value);

    /**
	 * Returns the value of the '<em><b>Priority</b></em>' attribute.
	 * The default value is <code>"None"</code>.
	 * The literals are from the enumeration {@link grl.Priority}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Priority</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Priority</em>' attribute.
	 * @see grl.Priority
	 * @see #setPriority(Priority)
	 * @see grl.GrlPackage#getIntentionalElementRef_Priority()
	 * @model default="None"
	 * @generated
	 */
    Priority getPriority();

    /**
	 * Sets the value of the '{@link grl.IntentionalElementRef#getPriority <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Priority</em>' attribute.
	 * @see grl.Priority
	 * @see #getPriority()
	 * @generated
	 */
    void setPriority(Priority value);

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
