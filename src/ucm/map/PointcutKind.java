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
 * A representation of the literals of the enumeration '<em><b>Pointcut Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see ucm.map.MapPackage#getPointcutKind()
 * @model
 * @generated
 */
public final class PointcutKind extends AbstractEnumerator {
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
    public static final int NONE = 0;

    /**
	 * The '<em><b>Regular</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Regular</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #REGULAR_LITERAL
	 * @model name="Regular"
	 * @generated
	 * @ordered
	 */
    public static final int REGULAR = 1;

    /**
	 * The '<em><b>Replacement</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Replacement</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #REPLACEMENT_LITERAL
	 * @model name="Replacement"
	 * @generated
	 * @ordered
	 */
    public static final int REPLACEMENT = 2;

    /**
	 * The '<em><b>None</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #NONE
	 * @generated
	 * @ordered
	 */
    public static final PointcutKind NONE_LITERAL = new PointcutKind(NONE, "None", "None");

    /**
	 * The '<em><b>Regular</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #REGULAR
	 * @generated
	 * @ordered
	 */
    public static final PointcutKind REGULAR_LITERAL = new PointcutKind(REGULAR, "Regular", "Regular");

    /**
	 * The '<em><b>Replacement</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #REPLACEMENT
	 * @generated
	 * @ordered
	 */
    public static final PointcutKind REPLACEMENT_LITERAL = new PointcutKind(REPLACEMENT, "Replacement", "Replacement");

    /**
	 * An array of all the '<em><b>Pointcut Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private static final PointcutKind[] VALUES_ARRAY =
        new PointcutKind[] {
			NONE_LITERAL,
			REGULAR_LITERAL,
			REPLACEMENT_LITERAL,
		};

    /**
	 * A public read-only list of all the '<em><b>Pointcut Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
	 * Returns the '<em><b>Pointcut Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static PointcutKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PointcutKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Pointcut Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static PointcutKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PointcutKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Pointcut Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static PointcutKind get(int value) {
		switch (value) {
			case NONE: return NONE_LITERAL;
			case REGULAR: return REGULAR_LITERAL;
			case REPLACEMENT: return REPLACEMENT_LITERAL;
		}
		return null;
	}

    /**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private PointcutKind(int value, String name, String literal) {
		super(value, name, literal);
	}

} //PointcutKind
