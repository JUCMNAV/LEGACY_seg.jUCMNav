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
 * A representation of the literals of the enumeration '<em><b>Failure Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see ucm.map.MapPackage#getFailureKind()
 * @model
 * @generated
 */
public final class FailureKind extends AbstractEnumerator {
    /**
	 * The '<em><b>Failure</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Failure</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #FAILURE_LITERAL
	 * @model name="Failure"
	 * @generated
	 * @ordered
	 */
    public static final int FAILURE = 0;

    /**
	 * The '<em><b>Abort</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Abort</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #ABORT_LITERAL
	 * @model name="Abort"
	 * @generated
	 * @ordered
	 */
    public static final int ABORT = 1;

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
    public static final int NONE = 2;

    /**
	 * The '<em><b>Failure</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #FAILURE
	 * @generated
	 * @ordered
	 */
    public static final FailureKind FAILURE_LITERAL = new FailureKind(FAILURE, "Failure", "Failure");

    /**
	 * The '<em><b>Abort</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #ABORT
	 * @generated
	 * @ordered
	 */
    public static final FailureKind ABORT_LITERAL = new FailureKind(ABORT, "Abort", "Abort");

    /**
	 * The '<em><b>None</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #NONE
	 * @generated
	 * @ordered
	 */
    public static final FailureKind NONE_LITERAL = new FailureKind(NONE, "None", "None");

    /**
	 * An array of all the '<em><b>Failure Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private static final FailureKind[] VALUES_ARRAY =
        new FailureKind[] {
			FAILURE_LITERAL,
			ABORT_LITERAL,
			NONE_LITERAL,
		};

    /**
	 * A public read-only list of all the '<em><b>Failure Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
	 * Returns the '<em><b>Failure Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static FailureKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			FailureKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Failure Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static FailureKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			FailureKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Failure Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static FailureKind get(int value) {
		switch (value) {
			case FAILURE: return FAILURE_LITERAL;
			case ABORT: return ABORT_LITERAL;
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
    private FailureKind(int value, String name, String literal) {
		super(value, name, literal);
	}

} //FailureKind
