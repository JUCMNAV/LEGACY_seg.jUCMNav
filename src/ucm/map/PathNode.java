/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map;

import org.eclipse.emf.common.util.EList;

import urncore.IURNNode;
import urncore.UCMmodelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Path Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.map.PathNode#getPerfMTrig <em>Perf MTrig</em>}</li>
 *   <li>{@link ucm.map.PathNode#getPerfMEnd <em>Perf MEnd</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.map.MapPackage#getPathNode()
 * @model abstract="true"
 * @generated
 */
public interface PathNode extends UCMmodelElement, IURNNode {
    /**
     * Returns the value of the '<em><b>Perf MTrig</b></em>' reference list.
     * The list contents are of type {@link ucm.performance.PerfMeasure}.
     * It is bidirectional and its opposite is '{@link ucm.performance.PerfMeasure#getTrigger <em>Trigger</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Perf MTrig</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Perf MTrig</em>' reference list.
     * @see ucm.map.MapPackage#getPathNode_PerfMTrig()
     * @see ucm.performance.PerfMeasure#getTrigger
     * @model type="ucm.performance.PerfMeasure" opposite="trigger"
     * @generated
     */
	EList getPerfMTrig();

    /**
     * Returns the value of the '<em><b>Perf MEnd</b></em>' reference list.
     * The list contents are of type {@link ucm.performance.PerfMeasure}.
     * It is bidirectional and its opposite is '{@link ucm.performance.PerfMeasure#getEnd <em>End</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Perf MEnd</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Perf MEnd</em>' reference list.
     * @see ucm.map.MapPackage#getPathNode_PerfMEnd()
     * @see ucm.performance.PerfMeasure#getEnd
     * @model type="ucm.performance.PerfMeasure" opposite="end"
     * @generated
     */
	EList getPerfMEnd();

} // PathNode
