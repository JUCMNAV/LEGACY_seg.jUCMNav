/**
 */
package core;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CORE Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link core.COREBinding#getCoreMappings <em>Core Mappings</em>}</li>
 * </ul>
 * </p>
 *
 * @see core.CorePackage#getCOREBinding()
 * @model abstract="true"
 * @generated
 */
public interface COREBinding extends CORECompositionSpecification {
	/**
	 * Returns the value of the '<em><b>Core Mappings</b></em>' reference list.
	 * The list contents are of type {@link core.COREMapping}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Core Mappings</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Core Mappings</em>' reference list.
	 * @see core.CorePackage#getCOREBinding_CoreMappings()
	 * @model type="core.COREMapping"
	 * @generated
	 */
	EList getCoreMappings();

} // COREBinding
