/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map;

import org.eclipse.emf.common.util.EList;

import urncore.Condition;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>End Point</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An end-point (or end bar, or result) has usually one  source hyperedge (and possibly a target hyperedge to a connect  for linking to starts or waiting places). The  resulting-event-list gives the set of events that occur once the  sequence of actions in a path are completed. The  postcondition-list must be satisfied once the sequence is  completed. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.map.EndPoint#isLocal <em>Local</em>}</li>
 *   <li>{@link ucm.map.EndPoint#getOutBindings <em>Out Bindings</em>}</li>
 *   <li>{@link ucm.map.EndPoint#getPostcondition <em>Postcondition</em>}</li>
 *   <li>{@link ucm.map.EndPoint#getScenarioEndPoints <em>Scenario End Points</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.map.MapPackage#getEndPoint()
 * @model
 * @generated
 */
public interface EndPoint extends PathNode {
    /**
	 * Returns the value of the '<em><b>Local</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Local</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Local</em>' attribute.
	 * @see #setLocal(boolean)
	 * @see ucm.map.MapPackage#getEndPoint_Local()
	 * @model default="false"
	 * @generated
	 */
    boolean isLocal();

    /**
	 * Sets the value of the '{@link ucm.map.EndPoint#isLocal <em>Local</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Local</em>' attribute.
	 * @see #isLocal()
	 * @generated
	 */
    void setLocal(boolean value);

    /**
	 * Returns the value of the '<em><b>Out Bindings</b></em>' reference list.
	 * The list contents are of type {@link ucm.map.OutBinding}.
	 * It is bidirectional and its opposite is '{@link ucm.map.OutBinding#getEndPoint <em>End Point</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Out Bindings</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Out Bindings</em>' reference list.
	 * @see ucm.map.MapPackage#getEndPoint_OutBindings()
	 * @see ucm.map.OutBinding#getEndPoint
	 * @model type="ucm.map.OutBinding" opposite="endPoint"
	 * @generated
	 */
    EList getOutBindings();

    /**
	 * Returns the value of the '<em><b>Postcondition</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link urncore.Condition#getEndPoint <em>End Point</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Postcondition</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Postcondition</em>' containment reference.
	 * @see #setPostcondition(Condition)
	 * @see ucm.map.MapPackage#getEndPoint_Postcondition()
	 * @see urncore.Condition#getEndPoint
	 * @model opposite="endPoint" containment="true"
	 * @generated
	 */
    Condition getPostcondition();

    /**
	 * Sets the value of the '{@link ucm.map.EndPoint#getPostcondition <em>Postcondition</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Postcondition</em>' containment reference.
	 * @see #getPostcondition()
	 * @generated
	 */
    void setPostcondition(Condition value);

    /**
	 * Returns the value of the '<em><b>Scenario End Points</b></em>' reference list.
	 * The list contents are of type {@link ucm.scenario.ScenarioEndPoint}.
	 * It is bidirectional and its opposite is '{@link ucm.scenario.ScenarioEndPoint#getEndPoint <em>End Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scenario End Points</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scenario End Points</em>' reference list.
	 * @see ucm.map.MapPackage#getEndPoint_ScenarioEndPoints()
	 * @see ucm.scenario.ScenarioEndPoint#getEndPoint
	 * @model type="ucm.scenario.ScenarioEndPoint" opposite="endPoint"
	 * @generated
	 */
	EList getScenarioEndPoints();

} // EndPoint
