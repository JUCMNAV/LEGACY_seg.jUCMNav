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
 *   <li>{@link grl.Contribution#getContribution <em>Contribution</em>}</li>
 *   <li>{@link grl.Contribution#getQuantitativeContribution <em>Quantitative Contribution</em>}</li>
 *   <li>{@link grl.Contribution#isCorrelation <em>Correlation</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.GrlPackage#getContribution()
 * @model
 * @generated
 */
public interface Contribution extends ElementLink {
    /**
	 * Returns the value of the '<em><b>Contribution</b></em>' attribute.
	 * The default value is <code>"Help"</code>.
	 * The literals are from the enumeration {@link grl.ContributionType}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Contribution</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Contribution</em>' attribute.
	 * @see grl.ContributionType
	 * @see #setContribution(ContributionType)
	 * @see grl.GrlPackage#getContribution_Contribution()
	 * @model default="Help"
	 * @generated
	 */
    ContributionType getContribution();

    /**
	 * Sets the value of the '{@link grl.Contribution#getContribution <em>Contribution</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Contribution</em>' attribute.
	 * @see grl.ContributionType
	 * @see #getContribution()
	 * @generated
	 */
    void setContribution(ContributionType value);

    /**
	 * Returns the value of the '<em><b>Quantitative Contribution</b></em>' attribute.
	 * The default value is <code>"25"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Quantitative Contribution</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Quantitative Contribution</em>' attribute.
	 * @see #setQuantitativeContribution(int)
	 * @see grl.GrlPackage#getContribution_QuantitativeContribution()
	 * @model default="25"
	 * @generated
	 */
	int getQuantitativeContribution();

				/**
	 * Sets the value of the '{@link grl.Contribution#getQuantitativeContribution <em>Quantitative Contribution</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Quantitative Contribution</em>' attribute.
	 * @see #getQuantitativeContribution()
	 * @generated
	 */
	void setQuantitativeContribution(int value);

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

} // Contribution
