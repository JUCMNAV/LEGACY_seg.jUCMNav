/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Qualitative Label</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see grl.GrlPackage#getQualitativeLabel()
 * @model
 * @generated
 */
public final class QualitativeLabel extends AbstractEnumerator {
	/**
	 * The '<em><b>Denied</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Denied</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DENIED_LITERAL
	 * @model name="Denied"
	 * @generated
	 * @ordered
	 */
	public static final int DENIED = 0;

	/**
	 * The '<em><b>Weakly Denied</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Weakly Denied</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #WEAKLY_DENIED_LITERAL
	 * @model name="WeaklyDenied"
	 * @generated
	 * @ordered
	 */
	public static final int WEAKLY_DENIED = 1;

	/**
	 * The '<em><b>Weakly Satisfied</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Weakly Satisfied</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #WEAKLY_SATISFIED_LITERAL
	 * @model name="WeaklySatisfied"
	 * @generated
	 * @ordered
	 */
	public static final int WEAKLY_SATISFIED = 2;

	/**
	 * The '<em><b>Satisfied</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Satisfied</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SATISFIED_LITERAL
	 * @model name="Satisfied"
	 * @generated
	 * @ordered
	 */
	public static final int SATISFIED = 3;

	/**
	 * The '<em><b>Conflict</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Conflict</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONFLICT_LITERAL
	 * @model name="Conflict"
	 * @generated
	 * @ordered
	 */
	public static final int CONFLICT = 4;

	/**
	 * The '<em><b>Unknown</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Unknown</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNKNOWN_LITERAL
	 * @model name="Unknown"
	 * @generated
	 * @ordered
	 */
	public static final int UNKNOWN = 5;

	/**
	 * The '<em><b>None</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>None</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NONE_LITERAL
	 * @model name="None"
	 * @generated
	 * @ordered
	 */
	public static final int NONE = 6;

	/**
	 * The '<em><b>Denied</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DENIED
	 * @generated
	 * @ordered
	 */
	public static final QualitativeLabel DENIED_LITERAL = new QualitativeLabel(DENIED, "Denied", "Denied");

	/**
	 * The '<em><b>Weakly Denied</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WEAKLY_DENIED
	 * @generated
	 * @ordered
	 */
	public static final QualitativeLabel WEAKLY_DENIED_LITERAL = new QualitativeLabel(WEAKLY_DENIED, "WeaklyDenied", "WeaklyDenied");

	/**
	 * The '<em><b>Weakly Satisfied</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WEAKLY_SATISFIED
	 * @generated
	 * @ordered
	 */
	public static final QualitativeLabel WEAKLY_SATISFIED_LITERAL = new QualitativeLabel(WEAKLY_SATISFIED, "WeaklySatisfied", "WeaklySatisfied");

	/**
	 * The '<em><b>Satisfied</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SATISFIED
	 * @generated
	 * @ordered
	 */
	public static final QualitativeLabel SATISFIED_LITERAL = new QualitativeLabel(SATISFIED, "Satisfied", "Satisfied");

	/**
	 * The '<em><b>Conflict</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONFLICT
	 * @generated
	 * @ordered
	 */
	public static final QualitativeLabel CONFLICT_LITERAL = new QualitativeLabel(CONFLICT, "Conflict", "Conflict");

	/**
	 * The '<em><b>Unknown</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNKNOWN
	 * @generated
	 * @ordered
	 */
	public static final QualitativeLabel UNKNOWN_LITERAL = new QualitativeLabel(UNKNOWN, "Unknown", "Unknown");

	/**
	 * The '<em><b>None</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NONE
	 * @generated
	 * @ordered
	 */
	public static final QualitativeLabel NONE_LITERAL = new QualitativeLabel(NONE, "None", "None");

	/**
	 * An array of all the '<em><b>Qualitative Label</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final QualitativeLabel[] VALUES_ARRAY =
		new QualitativeLabel[] {
			DENIED_LITERAL,
			WEAKLY_DENIED_LITERAL,
			WEAKLY_SATISFIED_LITERAL,
			SATISFIED_LITERAL,
			CONFLICT_LITERAL,
			UNKNOWN_LITERAL,
			NONE_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Qualitative Label</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Qualitative Label</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static QualitativeLabel get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			QualitativeLabel result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Qualitative Label</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static QualitativeLabel getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			QualitativeLabel result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Qualitative Label</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static QualitativeLabel get(int value) {
		switch (value) {
			case DENIED: return DENIED_LITERAL;
			case WEAKLY_DENIED: return WEAKLY_DENIED_LITERAL;
			case WEAKLY_SATISFIED: return WEAKLY_SATISFIED_LITERAL;
			case SATISFIED: return SATISFIED_LITERAL;
			case CONFLICT: return CONFLICT_LITERAL;
			case UNKNOWN: return UNKNOWN_LITERAL;
			case NONE: return NONE_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private QualitativeLabel(int value, String name, String literal) {
		super(value, name, literal);
	}

} //QualitativeLabel
