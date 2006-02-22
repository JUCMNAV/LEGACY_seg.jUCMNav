/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import urn.URNspec;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>GR Lspec</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.GRLspec#getUrnspec <em>Urnspec</em>}</li>
 *   <li>{@link grl.GRLspec#getIntElements <em>Int Elements</em>}</li>
 *   <li>{@link grl.GRLspec#getActors <em>Actors</em>}</li>
 *   <li>{@link grl.GRLspec#getLinks <em>Links</em>}</li>
 *   <li>{@link grl.GRLspec#getEvaluationGroups <em>Evaluation Groups</em>}</li>
 *   <li>{@link grl.GRLspec#getStrategies <em>Strategies</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.GrlPackage#getGRLspec()
 * @model
 * @generated
 */
public interface GRLspec extends EObject{
    /**
     * Returns the value of the '<em><b>Urnspec</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link urn.URNspec#getGrlspec <em>Grlspec</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Urnspec</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Urnspec</em>' container reference.
     * @see #setUrnspec(URNspec)
     * @see grl.GrlPackage#getGRLspec_Urnspec()
     * @see urn.URNspec#getGrlspec
     * @model opposite="grlspec" required="true"
     * @generated
     */
    URNspec getUrnspec();

    /**
     * Sets the value of the '{@link grl.GRLspec#getUrnspec <em>Urnspec</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Urnspec</em>' container reference.
     * @see #getUrnspec()
     * @generated
     */
    void setUrnspec(URNspec value);

    /**
     * Returns the value of the '<em><b>Int Elements</b></em>' containment reference list.
     * The list contents are of type {@link grl.IntentionalElement}.
     * It is bidirectional and its opposite is '{@link grl.IntentionalElement#getGrlspec <em>Grlspec</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Int Elements</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Int Elements</em>' containment reference list.
     * @see grl.GrlPackage#getGRLspec_IntElements()
     * @see grl.IntentionalElement#getGrlspec
     * @model type="grl.IntentionalElement" opposite="grlspec" containment="true"
     * @generated
     */
    EList getIntElements();

    /**
     * Returns the value of the '<em><b>Actors</b></em>' containment reference list.
     * The list contents are of type {@link grl.Actor}.
     * It is bidirectional and its opposite is '{@link grl.Actor#getGrlspec <em>Grlspec</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Actors</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Actors</em>' containment reference list.
     * @see grl.GrlPackage#getGRLspec_Actors()
     * @see grl.Actor#getGrlspec
     * @model type="grl.Actor" opposite="grlspec" containment="true"
     * @generated
     */
    EList getActors();

    /**
     * Returns the value of the '<em><b>Links</b></em>' containment reference list.
     * The list contents are of type {@link grl.ElementLink}.
     * It is bidirectional and its opposite is '{@link grl.ElementLink#getGrlspec <em>Grlspec</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Links</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Links</em>' containment reference list.
     * @see grl.GrlPackage#getGRLspec_Links()
     * @see grl.ElementLink#getGrlspec
     * @model type="grl.ElementLink" opposite="grlspec" containment="true"
     * @generated
     */
    EList getLinks();

    /**
     * Returns the value of the '<em><b>Evaluation Groups</b></em>' containment reference list.
     * The list contents are of type {@link grl.EvaluationGroup}.
     * It is bidirectional and its opposite is '{@link grl.EvaluationGroup#getGrlspec <em>Grlspec</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Evaluation Groups</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Evaluation Groups</em>' containment reference list.
     * @see grl.GrlPackage#getGRLspec_EvaluationGroups()
     * @see grl.EvaluationGroup#getGrlspec
     * @model type="grl.EvaluationGroup" opposite="grlspec" containment="true"
     * @generated
     */
    EList getEvaluationGroups();

    /**
     * Returns the value of the '<em><b>Strategies</b></em>' containment reference list.
     * The list contents are of type {@link grl.EvaluationStrategy}.
     * It is bidirectional and its opposite is '{@link grl.EvaluationStrategy#getGrlspec <em>Grlspec</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Strategies</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Strategies</em>' containment reference list.
     * @see grl.GrlPackage#getGRLspec_Strategies()
     * @see grl.EvaluationStrategy#getGrlspec
     * @model type="grl.EvaluationStrategy" opposite="grlspec" containment="true"
     * @generated
     */
    EList getStrategies();

} // GRLspec
