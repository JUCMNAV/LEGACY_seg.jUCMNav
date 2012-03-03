/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Wait Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see ucm.map.MapPackage#getWaitKind()
 * @model
 * @generated
 */
public final class WaitKind extends AbstractEnumerator {
	/**
	 * The '<em><b>Transient</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Transient</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TRANSIENT_LITERAL
	 * @model name="Transient"
	 * @generated
	 * @ordered
	 */
	public static final int TRANSIENT = 0;

	/**
	 * The '<em><b>Persistent</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Persistent</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PERSISTENT_LITERAL
	 * @model name="Persistent"
	 * @generated
	 * @ordered
	 */
	public static final int PERSISTENT = 1;

	/**
	 * The '<em><b>Transient</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TRANSIENT
	 * @generated
	 * @ordered
	 */
	public static final WaitKind TRANSIENT_LITERAL = new WaitKind(TRANSIENT, "Transient", "Transient");

	/**
	 * The '<em><b>Persistent</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PERSISTENT
	 * @generated
	 * @ordered
	 */
	public static final WaitKind PERSISTENT_LITERAL = new WaitKind(PERSISTENT, "Persistent", "Persistent");

	/**
	 * An array of all the '<em><b>Wait Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final WaitKind[] VALUES_ARRAY =
		new WaitKind[] {
			TRANSIENT_LITERAL,
			PERSISTENT_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Wait Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Wait Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static WaitKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			WaitKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Wait Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static WaitKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			WaitKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Wait Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static WaitKind get(int value) {
		switch (value) {
			case TRANSIENT: return TRANSIENT_LITERAL;
			case PERSISTENT: return PERSISTENT_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private WaitKind(int value, String name, String literal) {
		super(value, name, literal);
	}

} //WaitKind
