/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Dynamic Resp Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see urncore.UrncorePackage#getDynamicRespKind()
 * @model
 * @generated
 */
public final class DynamicRespKind extends AbstractEnumerator {
	/**
	 * The '<em><b>Move</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MOVE_LITERAL
	 * @model name="Move"
	 * @generated
	 * @ordered
	 */
	public static final int MOVE = 0;

	/**
	 * The '<em><b>Move Stay</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MOVE_STAY_LITERAL
	 * @model name="MoveStay"
	 * @generated
	 * @ordered
	 */
	public static final int MOVE_STAY = 1;

	/**
	 * The '<em><b>Create</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CREATE_LITERAL
	 * @model name="Create"
	 * @generated
	 * @ordered
	 */
	public static final int CREATE = 2;

	/**
	 * The '<em><b>Copy</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #COPY_LITERAL
	 * @model name="Copy"
	 * @generated
	 * @ordered
	 */
	public static final int COPY = 3;

	/**
	 * The '<em><b>Destroy</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DESTROY_LITERAL
	 * @model name="Destroy"
	 * @generated
	 * @ordered
	 */
	public static final int DESTROY = 4;

	/**
	 * The '<em><b>Move</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Move</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MOVE
	 * @generated
	 * @ordered
	 */
	public static final DynamicRespKind MOVE_LITERAL = new DynamicRespKind(MOVE, "Move");

	/**
	 * The '<em><b>Move Stay</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Move Stay</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MOVE_STAY
	 * @generated
	 * @ordered
	 */
	public static final DynamicRespKind MOVE_STAY_LITERAL = new DynamicRespKind(MOVE_STAY, "MoveStay");

	/**
	 * The '<em><b>Create</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Create</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CREATE
	 * @generated
	 * @ordered
	 */
	public static final DynamicRespKind CREATE_LITERAL = new DynamicRespKind(CREATE, "Create");

	/**
	 * The '<em><b>Copy</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Copy</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #COPY
	 * @generated
	 * @ordered
	 */
	public static final DynamicRespKind COPY_LITERAL = new DynamicRespKind(COPY, "Copy");

	/**
	 * The '<em><b>Destroy</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Destroy</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DESTROY
	 * @generated
	 * @ordered
	 */
	public static final DynamicRespKind DESTROY_LITERAL = new DynamicRespKind(DESTROY, "Destroy");

	/**
	 * An array of all the '<em><b>Dynamic Resp Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final DynamicRespKind[] VALUES_ARRAY =
		new DynamicRespKind[] {
			MOVE_LITERAL,
			MOVE_STAY_LITERAL,
			CREATE_LITERAL,
			COPY_LITERAL,
			DESTROY_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Dynamic Resp Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Dynamic Resp Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DynamicRespKind get(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DynamicRespKind result = VALUES_ARRAY[i];
			if (result.toString().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Dynamic Resp Kind</b></em>' literal with the specified value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DynamicRespKind get(int value) {
		switch (value) {
			case MOVE: return MOVE_LITERAL;
			case MOVE_STAY: return MOVE_STAY_LITERAL;
			case CREATE: return CREATE_LITERAL;
			case COPY: return COPY_LITERAL;
			case DESTROY: return DESTROY_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private DynamicRespKind(int value, String name) {
		super(value, name);
	}

} //DynamicRespKind
