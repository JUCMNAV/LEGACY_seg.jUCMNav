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
 * A representation of the model object '<em><b>Strategies Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.StrategiesGroup#getStrategies <em>Strategies</em>}</li>
 *   <li>{@link grl.StrategiesGroup#getGrlspec <em>Grlspec</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.GrlPackage#getStrategiesGroup()
 * @model
 * @generated
 */
public interface StrategiesGroup extends GRLmodelElement {
    /**
	 * Returns the value of the '<em><b>Strategies</b></em>' reference list.
	 * The list contents are of type {@link grl.EvaluationStrategy}.
	 * It is bidirectional and its opposite is '{@link grl.EvaluationStrategy#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Strategies</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Strategies</em>' reference list.
	 * @see grl.GrlPackage#getStrategiesGroup_Strategies()
	 * @see grl.EvaluationStrategy#getGroup
	 * @model type="grl.EvaluationStrategy" opposite="group"
	 * @generated
	 */
    EList getStrategies();

    /**
	 * Returns the value of the '<em><b>Grlspec</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link grl.GRLspec#getGroups <em>Groups</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Grlspec</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Grlspec</em>' container reference.
	 * @see #setGrlspec(GRLspec)
	 * @see grl.GrlPackage#getStrategiesGroup_Grlspec()
	 * @see grl.GRLspec#getGroups
	 * @model opposite="groups" required="true"
	 * @generated
	 */
    GRLspec getGrlspec();

    /**
	 * Sets the value of the '{@link grl.StrategiesGroup#getGrlspec <em>Grlspec</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grlspec</em>' container reference.
	 * @see #getGrlspec()
	 * @generated
	 */
    void setGrlspec(GRLspec value);
    
    void sortStrategies();

} // StrategiesGroup
