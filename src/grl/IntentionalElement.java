/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl;

import org.eclipse.emf.common.util.EList;

import urncore.GRLmodelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Intentional Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.IntentionalElement#getType <em>Type</em>}</li>
 *   <li>{@link grl.IntentionalElement#getCriticality <em>Criticality</em>}</li>
 *   <li>{@link grl.IntentionalElement#getPriority <em>Priority</em>}</li>
 *   <li>{@link grl.IntentionalElement#getDecompositionType <em>Decomposition Type</em>}</li>
 *   <li>{@link grl.IntentionalElement#getGrlspec <em>Grlspec</em>}</li>
 *   <li>{@link grl.IntentionalElement#getRefs <em>Refs</em>}</li>
 *   <li>{@link grl.IntentionalElement#getIsDepender <em>Is Depender</em>}</li>
 *   <li>{@link grl.IntentionalElement#getDecompositionSrc <em>Decomposition Src</em>}</li>
 *   <li>{@link grl.IntentionalElement#getDecompositionDest <em>Decomposition Dest</em>}</li>
 *   <li>{@link grl.IntentionalElement#getContributionSrc <em>Contribution Src</em>}</li>
 *   <li>{@link grl.IntentionalElement#getContributionDest <em>Contribution Dest</em>}</li>
 *   <li>{@link grl.IntentionalElement#getIsDependum <em>Is Dependum</em>}</li>
 *   <li>{@link grl.IntentionalElement#getIsDependee <em>Is Dependee</em>}</li>
 *   <li>{@link grl.IntentionalElement#getEvals <em>Evals</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.GrlPackage#getIntentionalElement()
 * @model 
 * @generated
 */
public interface IntentionalElement extends GRLmodelElement {
    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute.
     * The literals are from the enumeration {@link grl.IntentionalElementType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see grl.IntentionalElementType
     * @see #setType(IntentionalElementType)
     * @see grl.GrlPackage#getIntentionalElement_Type()
     * @model 
     * @generated
     */
    IntentionalElementType getType();

    /**
     * Sets the value of the '{@link grl.IntentionalElement#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see grl.IntentionalElementType
     * @see #getType()
     * @generated
     */
    void setType(IntentionalElementType value);

    /**
     * Returns the value of the '<em><b>Criticality</b></em>' attribute.
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
     * @see grl.GrlPackage#getIntentionalElement_Criticality()
     * @model 
     * @generated
     */
    Criticality getCriticality();

    /**
     * Sets the value of the '{@link grl.IntentionalElement#getCriticality <em>Criticality</em>}' attribute.
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
     * @see grl.GrlPackage#getIntentionalElement_Priority()
     * @model 
     * @generated
     */
    Priority getPriority();

    /**
     * Sets the value of the '{@link grl.IntentionalElement#getPriority <em>Priority</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Priority</em>' attribute.
     * @see grl.Priority
     * @see #getPriority()
     * @generated
     */
    void setPriority(Priority value);

    /**
     * Returns the value of the '<em><b>Decomposition Type</b></em>' attribute.
     * The default value is <code>"AND"</code>.
     * The literals are from the enumeration {@link grl.DecompositionType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Decomposition Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Decomposition Type</em>' attribute.
     * @see grl.DecompositionType
     * @see #setDecompositionType(DecompositionType)
     * @see grl.GrlPackage#getIntentionalElement_DecompositionType()
     * @model default="AND"
     * @generated
     */
    DecompositionType getDecompositionType();

    /**
     * Sets the value of the '{@link grl.IntentionalElement#getDecompositionType <em>Decomposition Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Decomposition Type</em>' attribute.
     * @see grl.DecompositionType
     * @see #getDecompositionType()
     * @generated
     */
    void setDecompositionType(DecompositionType value);

    /**
     * Returns the value of the '<em><b>Grlspec</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link grl.GRLspec#getIntElements <em>Int Elements</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Grlspec</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Grlspec</em>' container reference.
     * @see #setGrlspec(GRLspec)
     * @see grl.GrlPackage#getIntentionalElement_Grlspec()
     * @see grl.GRLspec#getIntElements
     * @model opposite="intElements" required="true"
     * @generated
     */
    GRLspec getGrlspec();

    /**
     * Sets the value of the '{@link grl.IntentionalElement#getGrlspec <em>Grlspec</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Grlspec</em>' container reference.
     * @see #getGrlspec()
     * @generated
     */
    void setGrlspec(GRLspec value);

    /**
     * Returns the value of the '<em><b>Refs</b></em>' reference list.
     * The list contents are of type {@link grl.IntentionalElementRef}.
     * It is bidirectional and its opposite is '{@link grl.IntentionalElementRef#getDef <em>Def</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Refs</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Refs</em>' reference list.
     * @see grl.GrlPackage#getIntentionalElement_Refs()
     * @see grl.IntentionalElementRef#getDef
     * @model type="grl.IntentionalElementRef" opposite="def" required="true"
     * @generated
     */
    EList getRefs();

    /**
     * Returns the value of the '<em><b>Is Depender</b></em>' reference list.
     * The list contents are of type {@link grl.Dependency}.
     * It is bidirectional and its opposite is '{@link grl.Dependency#getDepender <em>Depender</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Is Depender</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Depender</em>' reference list.
     * @see grl.GrlPackage#getIntentionalElement_IsDepender()
     * @see grl.Dependency#getDepender
     * @model type="grl.Dependency" opposite="depender"
     * @generated
     */
    EList getIsDepender();

    /**
     * Returns the value of the '<em><b>Decomposition Src</b></em>' reference list.
     * The list contents are of type {@link grl.Decomposition}.
     * It is bidirectional and its opposite is '{@link grl.Decomposition#getSrc <em>Src</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Decomposition Src</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Decomposition Src</em>' reference list.
     * @see grl.GrlPackage#getIntentionalElement_DecompositionSrc()
     * @see grl.Decomposition#getSrc
     * @model type="grl.Decomposition" opposite="src"
     * @generated
     */
    EList getDecompositionSrc();

    /**
     * Returns the value of the '<em><b>Decomposition Dest</b></em>' reference list.
     * The list contents are of type {@link grl.Decomposition}.
     * It is bidirectional and its opposite is '{@link grl.Decomposition#getDest <em>Dest</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Decomposition Dest</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Decomposition Dest</em>' reference list.
     * @see grl.GrlPackage#getIntentionalElement_DecompositionDest()
     * @see grl.Decomposition#getDest
     * @model type="grl.Decomposition" opposite="dest"
     * @generated
     */
    EList getDecompositionDest();

    /**
     * Returns the value of the '<em><b>Contribution Src</b></em>' reference list.
     * The list contents are of type {@link grl.Contribution}.
     * It is bidirectional and its opposite is '{@link grl.Contribution#getSrc <em>Src</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Contribution Src</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Contribution Src</em>' reference list.
     * @see grl.GrlPackage#getIntentionalElement_ContributionSrc()
     * @see grl.Contribution#getSrc
     * @model type="grl.Contribution" opposite="src"
     * @generated
     */
    EList getContributionSrc();

    /**
     * Returns the value of the '<em><b>Contribution Dest</b></em>' reference list.
     * The list contents are of type {@link grl.Contribution}.
     * It is bidirectional and its opposite is '{@link grl.Contribution#getDest <em>Dest</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Contribution Dest</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Contribution Dest</em>' reference list.
     * @see grl.GrlPackage#getIntentionalElement_ContributionDest()
     * @see grl.Contribution#getDest
     * @model type="grl.Contribution" opposite="dest"
     * @generated
     */
    EList getContributionDest();

    /**
     * Returns the value of the '<em><b>Is Dependum</b></em>' reference list.
     * The list contents are of type {@link grl.Dependency}.
     * It is bidirectional and its opposite is '{@link grl.Dependency#getDependum <em>Dependum</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Is Dependum</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Dependum</em>' reference list.
     * @see grl.GrlPackage#getIntentionalElement_IsDependum()
     * @see grl.Dependency#getDependum
     * @model type="grl.Dependency" opposite="dependum"
     * @generated
     */
    EList getIsDependum();

    /**
     * Returns the value of the '<em><b>Is Dependee</b></em>' reference list.
     * The list contents are of type {@link grl.Dependency}.
     * It is bidirectional and its opposite is '{@link grl.Dependency#getDependee <em>Dependee</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Is Dependee</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Is Dependee</em>' reference list.
     * @see grl.GrlPackage#getIntentionalElement_IsDependee()
     * @see grl.Dependency#getDependee
     * @model type="grl.Dependency" opposite="dependee"
     * @generated
     */
    EList getIsDependee();

    /**
     * Returns the value of the '<em><b>Evals</b></em>' reference list.
     * The list contents are of type {@link grl.Evaluation}.
     * It is bidirectional and its opposite is '{@link grl.Evaluation#getIntElement <em>Int Element</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Evals</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Evals</em>' reference list.
     * @see grl.GrlPackage#getIntentionalElement_Evals()
     * @see grl.Evaluation#getIntElement
     * @model type="grl.Evaluation" opposite="intElement"
     * @generated
     */
    EList getEvals();

} // IntentionalElement
