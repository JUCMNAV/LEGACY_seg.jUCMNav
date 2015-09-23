/**
 */
package grl;

import fm.ReuseLink;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reused Strategy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link grl.ReusedStrategy#getReusingStrategies <em>Reusing Strategies</em>}</li>
 *   <li>{@link grl.ReusedStrategy#getReuseLinkInFM <em>Reuse Link In FM</em>}</li>
 * </ul>
 *
 * @see grl.GrlPackage#getReusedStrategy()
 * @model
 * @generated
 */
public interface ReusedStrategy extends EvaluationStrategy {
	/**
	 * Returns the value of the '<em><b>Reusing Strategies</b></em>' reference list.
	 * The list contents are of type {@link grl.EvaluationStrategy}.
	 * It is bidirectional and its opposite is '{@link grl.EvaluationStrategy#getReusedStrategies <em>Reused Strategies</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reusing Strategies</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reusing Strategies</em>' reference list.
	 * @see grl.GrlPackage#getReusedStrategy_ReusingStrategies()
	 * @see grl.EvaluationStrategy#getReusedStrategies
	 * @model type="grl.EvaluationStrategy" opposite="reusedStrategies" required="true"
	 * @generated
	 */
	EList getReusingStrategies();

	/**
	 * Returns the value of the '<em><b>Reuse Link In FM</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reuse Link In FM</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reuse Link In FM</em>' reference.
	 * @see #setReuseLinkInFM(ReuseLink)
	 * @see grl.GrlPackage#getReusedStrategy_ReuseLinkInFM()
	 * @model required="true"
	 * @generated
	 */
	ReuseLink getReuseLinkInFM();

	/**
	 * Sets the value of the '{@link grl.ReusedStrategy#getReuseLinkInFM <em>Reuse Link In FM</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reuse Link In FM</em>' reference.
	 * @see #getReuseLinkInFM()
	 * @generated
	 */
	void setReuseLinkInFM(ReuseLink value);

} // ReusedStrategy
