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
 * A representation of the literals of the enumeration '<em><b>Aspect Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see ucm.map.MapPackage#getAspectKind()
 * @model
 * @generated
 */
public final class AspectKind extends AbstractEnumerator {
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
	 * The '<em><b>Entrance</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Entrance</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #ENTRANCE_LITERAL
	 * @model name="Entrance"
	 * @generated
	 * @ordered
	 */
    public static final int ENTRANCE = 2;

    /**
	 * The '<em><b>Exit</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Exit</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #EXIT_LITERAL
	 * @model name="Exit"
	 * @generated
	 * @ordered
	 */
    public static final int EXIT = 3;

    /**
	 * The '<em><b>Conditional</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Conditional</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #CONDITIONAL_LITERAL
	 * @model name="Conditional"
	 * @generated
	 * @ordered
	 */
    public static final int CONDITIONAL = 4;

    /**
	 * The '<em><b>None</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #NONE
	 * @generated
	 * @ordered
	 */
    public static final AspectKind NONE_LITERAL = new AspectKind(NONE, "None", "None");

    /**
	 * The '<em><b>Regular</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #REGULAR
	 * @generated
	 * @ordered
	 */
    public static final AspectKind REGULAR_LITERAL = new AspectKind(REGULAR, "Regular", "Regular");

    /**
	 * The '<em><b>Entrance</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #ENTRANCE
	 * @generated
	 * @ordered
	 */
    public static final AspectKind ENTRANCE_LITERAL = new AspectKind(ENTRANCE, "Entrance", "Entrance");

    /**
	 * The '<em><b>Exit</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #EXIT
	 * @generated
	 * @ordered
	 */
    public static final AspectKind EXIT_LITERAL = new AspectKind(EXIT, "Exit", "Exit");

    /**
	 * The '<em><b>Conditional</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #CONDITIONAL
	 * @generated
	 * @ordered
	 */
    public static final AspectKind CONDITIONAL_LITERAL = new AspectKind(CONDITIONAL, "Conditional", "Conditional");

    /**
	 * An array of all the '<em><b>Aspect Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private static final AspectKind[] VALUES_ARRAY =
        new AspectKind[] {
			NONE_LITERAL,
			REGULAR_LITERAL,
			ENTRANCE_LITERAL,
			EXIT_LITERAL,
			CONDITIONAL_LITERAL,
		};

    /**
	 * A public read-only list of all the '<em><b>Aspect Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
	 * Returns the '<em><b>Aspect Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static AspectKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AspectKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Aspect Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static AspectKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AspectKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Aspect Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static AspectKind get(int value) {
		switch (value) {
			case NONE: return NONE_LITERAL;
			case REGULAR: return REGULAR_LITERAL;
			case ENTRANCE: return ENTRANCE_LITERAL;
			case EXIT: return EXIT_LITERAL;
			case CONDITIONAL: return CONDITIONAL_LITERAL;
		}
		return null;
	}

    /**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private AspectKind(int value, String name, String literal) {
		super(value, name, literal);
	}

} //AspectKind
