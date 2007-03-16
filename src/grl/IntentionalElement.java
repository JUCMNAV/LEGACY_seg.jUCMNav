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
 *   <li>{@link grl.IntentionalElement#getDecompositionType <em>Decomposition Type</em>}</li>
 *   <li>{@link grl.IntentionalElement#getLineColor <em>Line Color</em>}</li>
 *   <li>{@link grl.IntentionalElement#getFillColor <em>Fill Color</em>}</li>
 *   <li>{@link grl.IntentionalElement#isFilled <em>Filled</em>}</li>
 *   <li>{@link grl.IntentionalElement#getGrlspec <em>Grlspec</em>}</li>
 *   <li>{@link grl.IntentionalElement#getRefs <em>Refs</em>}</li>
 *   <li>{@link grl.IntentionalElement#getLinksSrc <em>Links Src</em>}</li>
 *   <li>{@link grl.IntentionalElement#getLinksDest <em>Links Dest</em>}</li>
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
	 * Returns the value of the '<em><b>Decomposition Type</b></em>' attribute.
	 * The default value is <code>"And"</code>.
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
	 * @model default="And"
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
	 * Returns the value of the '<em><b>Line Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Line Color</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Line Color</em>' attribute.
	 * @see #setLineColor(String)
	 * @see grl.GrlPackage#getIntentionalElement_LineColor()
	 * @model
	 * @generated
	 */
    String getLineColor();

	/**
	 * Sets the value of the '{@link grl.IntentionalElement#getLineColor <em>Line Color</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Line Color</em>' attribute.
	 * @see #getLineColor()
	 * @generated
	 */
    void setLineColor(String value);

	/**
	 * Returns the value of the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Fill Color</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Fill Color</em>' attribute.
	 * @see #setFillColor(String)
	 * @see grl.GrlPackage#getIntentionalElement_FillColor()
	 * @model
	 * @generated
	 */
    String getFillColor();

	/**
	 * Sets the value of the '{@link grl.IntentionalElement#getFillColor <em>Fill Color</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fill Color</em>' attribute.
	 * @see #getFillColor()
	 * @generated
	 */
    void setFillColor(String value);

	/**
	 * Returns the value of the '<em><b>Filled</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filled</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Filled</em>' attribute.
	 * @see #setFilled(boolean)
	 * @see grl.GrlPackage#getIntentionalElement_Filled()
	 * @model default="false"
	 * @generated
	 */
    boolean isFilled();

	/**
	 * Sets the value of the '{@link grl.IntentionalElement#isFilled <em>Filled</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Filled</em>' attribute.
	 * @see #isFilled()
	 * @generated
	 */
    void setFilled(boolean value);

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
	 * @model type="grl.IntentionalElementRef" opposite="def"
	 * @generated
	 */
    EList getRefs();

	/**
	 * Returns the value of the '<em><b>Links Src</b></em>' reference list.
	 * The list contents are of type {@link grl.ElementLink}.
	 * It is bidirectional and its opposite is '{@link grl.ElementLink#getSrc <em>Src</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Links Src</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Links Src</em>' reference list.
	 * @see grl.GrlPackage#getIntentionalElement_LinksSrc()
	 * @see grl.ElementLink#getSrc
	 * @model type="grl.ElementLink" opposite="src"
	 * @generated
	 */
    EList getLinksSrc();

	/**
	 * Returns the value of the '<em><b>Links Dest</b></em>' reference list.
	 * The list contents are of type {@link grl.ElementLink}.
	 * It is bidirectional and its opposite is '{@link grl.ElementLink#getDest <em>Dest</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Links Dest</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Links Dest</em>' reference list.
	 * @see grl.GrlPackage#getIntentionalElement_LinksDest()
	 * @see grl.ElementLink#getDest
	 * @model type="grl.ElementLink" opposite="dest"
	 * @generated
	 */
    EList getLinksDest();

} // IntentionalElement
