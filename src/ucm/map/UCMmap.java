/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map;

import org.eclipse.emf.common.util.EList;

import urncore.IURNDiagram;
import urncore.UCMmodelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>UC Mmap</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.map.UCMmap#getParentStub <em>Parent Stub</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.map.MapPackage#getUCMmap()
 * @model
 * @generated
 */
public interface UCMmap extends UCMmodelElement, IURNDiagram {
	/**
	 * Returns the value of the '<em><b>Parent Stub</b></em>' reference list.
	 * The list contents are of type {@link ucm.map.PluginBinding}.
	 * It is bidirectional and its opposite is '{@link ucm.map.PluginBinding#getPlugin <em>Plugin</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parent Stub</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Stub</em>' reference list.
	 * @see ucm.map.MapPackage#getUCMmap_ParentStub()
	 * @see ucm.map.PluginBinding#getPlugin
	 * @model type="ucm.map.PluginBinding" opposite="plugin"
	 * @generated
	 */
    EList getParentStub();

} // UCMmap
