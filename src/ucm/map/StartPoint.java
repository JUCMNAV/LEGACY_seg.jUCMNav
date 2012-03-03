/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map;

import org.eclipse.emf.common.util.EList;

import ucm.performance.Workload;
import urncore.Condition;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Start Point</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A start of path should have only one target hyperedge.  It may have a source when linked to a result by a 'connect'. The  triggering-event-list gives the set of events that initiate the  sequence of actions in a path. The precondition-list must be  satisfied in order for the scenario to start
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.map.StartPoint#getWorkload <em>Workload</em>}</li>
 *   <li>{@link ucm.map.StartPoint#isLocal <em>Local</em>}</li>
 *   <li>{@link ucm.map.StartPoint#getFailureKind <em>Failure Kind</em>}</li>
 *   <li>{@link ucm.map.StartPoint#getInBindings <em>In Bindings</em>}</li>
 *   <li>{@link ucm.map.StartPoint#getPrecondition <em>Precondition</em>}</li>
 *   <li>{@link ucm.map.StartPoint#getScenarioStartPoints <em>Scenario Start Points</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.map.MapPackage#getStartPoint()
 * @model
 * @generated
 */
public interface StartPoint extends PathNode {
    /**
	 * Returns the value of the '<em><b>Workload</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link ucm.performance.Workload#getStartPoint <em>Start Point</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Workload</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Workload</em>' containment reference.
	 * @see #setWorkload(Workload)
	 * @see ucm.map.MapPackage#getStartPoint_Workload()
	 * @see ucm.performance.Workload#getStartPoint
	 * @model opposite="startPoint" containment="true"
	 * @generated
	 */
    Workload getWorkload();

    /**
	 * Sets the value of the '{@link ucm.map.StartPoint#getWorkload <em>Workload</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Workload</em>' containment reference.
	 * @see #getWorkload()
	 * @generated
	 */
    void setWorkload(Workload value);

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
	 * @see ucm.map.MapPackage#getStartPoint_Local()
	 * @model default="false"
	 * @generated
	 */
    boolean isLocal();

    /**
	 * Sets the value of the '{@link ucm.map.StartPoint#isLocal <em>Local</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Local</em>' attribute.
	 * @see #isLocal()
	 * @generated
	 */
    void setLocal(boolean value);

    /**
	 * Returns the value of the '<em><b>Failure Kind</b></em>' attribute.
	 * The default value is <code>"None"</code>.
	 * The literals are from the enumeration {@link ucm.map.FailureKind}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Failure Kind</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Failure Kind</em>' attribute.
	 * @see ucm.map.FailureKind
	 * @see #setFailureKind(FailureKind)
	 * @see ucm.map.MapPackage#getStartPoint_FailureKind()
	 * @model default="None"
	 * @generated
	 */
    FailureKind getFailureKind();

    /**
	 * Sets the value of the '{@link ucm.map.StartPoint#getFailureKind <em>Failure Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Failure Kind</em>' attribute.
	 * @see ucm.map.FailureKind
	 * @see #getFailureKind()
	 * @generated
	 */
    void setFailureKind(FailureKind value);

    /**
	 * Returns the value of the '<em><b>In Bindings</b></em>' reference list.
	 * The list contents are of type {@link ucm.map.InBinding}.
	 * It is bidirectional and its opposite is '{@link ucm.map.InBinding#getStartPoint <em>Start Point</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>In Bindings</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>In Bindings</em>' reference list.
	 * @see ucm.map.MapPackage#getStartPoint_InBindings()
	 * @see ucm.map.InBinding#getStartPoint
	 * @model type="ucm.map.InBinding" opposite="startPoint"
	 * @generated
	 */
    EList getInBindings();

    /**
	 * Returns the value of the '<em><b>Precondition</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link urncore.Condition#getStartPoint <em>Start Point</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Precondition</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Precondition</em>' containment reference.
	 * @see #setPrecondition(Condition)
	 * @see ucm.map.MapPackage#getStartPoint_Precondition()
	 * @see urncore.Condition#getStartPoint
	 * @model opposite="startPoint" containment="true"
	 * @generated
	 */
    Condition getPrecondition();

    /**
	 * Sets the value of the '{@link ucm.map.StartPoint#getPrecondition <em>Precondition</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Precondition</em>' containment reference.
	 * @see #getPrecondition()
	 * @generated
	 */
    void setPrecondition(Condition value);

    /**
	 * Returns the value of the '<em><b>Scenario Start Points</b></em>' reference list.
	 * The list contents are of type {@link ucm.scenario.ScenarioStartPoint}.
	 * It is bidirectional and its opposite is '{@link ucm.scenario.ScenarioStartPoint#getStartPoint <em>Start Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scenario Start Points</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scenario Start Points</em>' reference list.
	 * @see ucm.map.MapPackage#getStartPoint_ScenarioStartPoints()
	 * @see ucm.scenario.ScenarioStartPoint#getStartPoint
	 * @model type="ucm.scenario.ScenarioStartPoint" opposite="startPoint"
	 * @generated
	 */
	EList getScenarioStartPoints();

} // StartPoint
