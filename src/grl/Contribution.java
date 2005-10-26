/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Contribution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.Contribution#getContibution <em>Contibution</em>}</li>
 *   <li>{@link grl.Contribution#isCorrelation <em>Correlation</em>}</li>
 *   <li>{@link grl.Contribution#getSrc <em>Src</em>}</li>
 *   <li>{@link grl.Contribution#getDest <em>Dest</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.GrlPackage#getContribution()
 * @model 
 * @generated
 */
public interface Contribution extends ElementLink {
    /**
     * Returns the value of the '<em><b>Contibution</b></em>' attribute.
     * The literals are from the enumeration {@link grl.ContributionType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Contibution</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Contibution</em>' attribute.
     * @see grl.ContributionType
     * @see #setContibution(ContributionType)
     * @see grl.GrlPackage#getContribution_Contibution()
     * @model 
     * @generated
     */
    ContributionType getContibution();

    /**
     * Sets the value of the '{@link grl.Contribution#getContibution <em>Contibution</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Contibution</em>' attribute.
     * @see grl.ContributionType
     * @see #getContibution()
     * @generated
     */
    void setContibution(ContributionType value);

    /**
     * Returns the value of the '<em><b>Correlation</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Correlation</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Correlation</em>' attribute.
     * @see #setCorrelation(boolean)
     * @see grl.GrlPackage#getContribution_Correlation()
     * @model default="false"
     * @generated
     */
    boolean isCorrelation();

    /**
     * Sets the value of the '{@link grl.Contribution#isCorrelation <em>Correlation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Correlation</em>' attribute.
     * @see #isCorrelation()
     * @generated
     */
    void setCorrelation(boolean value);

    /**
     * Returns the value of the '<em><b>Src</b></em>' reference.
     * It is bidirectional and its opposite is '{@link grl.IntentionalElement#getContributionSrc <em>Contribution Src</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Src</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Src</em>' reference.
     * @see #setSrc(IntentionalElement)
     * @see grl.GrlPackage#getContribution_Src()
     * @see grl.IntentionalElement#getContributionSrc
     * @model opposite="contributionSrc" required="true"
     * @generated
     */
    IntentionalElement getSrc();

    /**
     * Sets the value of the '{@link grl.Contribution#getSrc <em>Src</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Src</em>' reference.
     * @see #getSrc()
     * @generated
     */
    void setSrc(IntentionalElement value);

    /**
     * Returns the value of the '<em><b>Dest</b></em>' reference.
     * It is bidirectional and its opposite is '{@link grl.IntentionalElement#getContributionDest <em>Contribution Dest</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Dest</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Dest</em>' reference.
     * @see #setDest(IntentionalElement)
     * @see grl.GrlPackage#getContribution_Dest()
     * @see grl.IntentionalElement#getContributionDest
     * @model opposite="contributionDest" required="true"
     * @generated
     */
    IntentionalElement getDest();

    /**
     * Sets the value of the '{@link grl.Contribution#getDest <em>Dest</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Dest</em>' reference.
     * @see #getDest()
     * @generated
     */
    void setDest(IntentionalElement value);

} // Contribution
