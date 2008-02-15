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
 * A representation of the literals of the enumeration '<em><b>Contribution Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see grl.GrlPackage#getContributionType()
 * @model
 * @generated
 */
public final class ContributionType extends AbstractEnumerator {
    /**
	 * The '<em><b>Make</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Make</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #MAKE_LITERAL
	 * @model name="Make"
	 * @generated
	 * @ordered
	 */
    public static final int MAKE = 0;

    /**
	 * The '<em><b>Help</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Help</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #HELP_LITERAL
	 * @model name="Help"
	 * @generated
	 * @ordered
	 */
    public static final int HELP = 1;

    /**
	 * The '<em><b>Some Positive</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Some Positive</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #SOME_POSITIVE_LITERAL
	 * @model name="SomePositive"
	 * @generated
	 * @ordered
	 */
    public static final int SOME_POSITIVE = 2;

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
    public static final int UNKNOWN = 3;

    /**
	 * The '<em><b>Some Negative</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Some Negative</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #SOME_NEGATIVE_LITERAL
	 * @model name="SomeNegative"
	 * @generated
	 * @ordered
	 */
    public static final int SOME_NEGATIVE = 4;

    /**
	 * The '<em><b>Hurt</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Hurt</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #HURT_LITERAL
	 * @model name="Hurt"
	 * @generated
	 * @ordered
	 */
    public static final int HURT = 5;

    /**
	 * The '<em><b>Break</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Break</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #BREAK_LITERAL
	 * @model name="Break"
	 * @generated
	 * @ordered
	 */
    public static final int BREAK = 6;

    /**
	 * The '<em><b>Make</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #MAKE
	 * @generated
	 * @ordered
	 */
    public static final ContributionType MAKE_LITERAL = new ContributionType(MAKE, "Make", "Make");

    /**
	 * The '<em><b>Help</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #HELP
	 * @generated
	 * @ordered
	 */
    public static final ContributionType HELP_LITERAL = new ContributionType(HELP, "Help", "Help");

    /**
	 * The '<em><b>Some Positive</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #SOME_POSITIVE
	 * @generated
	 * @ordered
	 */
    public static final ContributionType SOME_POSITIVE_LITERAL = new ContributionType(SOME_POSITIVE, "SomePositive", "SomePositive");

    /**
	 * The '<em><b>Unknown</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #UNKNOWN
	 * @generated
	 * @ordered
	 */
    public static final ContributionType UNKNOWN_LITERAL = new ContributionType(UNKNOWN, "Unknown", "Unknown");

    /**
	 * The '<em><b>Some Negative</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #SOME_NEGATIVE
	 * @generated
	 * @ordered
	 */
    public static final ContributionType SOME_NEGATIVE_LITERAL = new ContributionType(SOME_NEGATIVE, "SomeNegative", "SomeNegative");

    /**
	 * The '<em><b>Hurt</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #HURT
	 * @generated
	 * @ordered
	 */
    public static final ContributionType HURT_LITERAL = new ContributionType(HURT, "Hurt", "Hurt");

    /**
	 * The '<em><b>Break</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #BREAK
	 * @generated
	 * @ordered
	 */
    public static final ContributionType BREAK_LITERAL = new ContributionType(BREAK, "Break", "Break");

    /**
	 * An array of all the '<em><b>Contribution Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private static final ContributionType[] VALUES_ARRAY =
        new ContributionType[] {
			MAKE_LITERAL,
			HELP_LITERAL,
			SOME_POSITIVE_LITERAL,
			UNKNOWN_LITERAL,
			SOME_NEGATIVE_LITERAL,
			HURT_LITERAL,
			BREAK_LITERAL,
		};

    /**
	 * A public read-only list of all the '<em><b>Contribution Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
	 * Returns the '<em><b>Contribution Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static ContributionType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ContributionType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Contribution Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ContributionType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ContributionType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Contribution Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static ContributionType get(int value) {
		switch (value) {
			case MAKE: return MAKE_LITERAL;
			case HELP: return HELP_LITERAL;
			case SOME_POSITIVE: return SOME_POSITIVE_LITERAL;
			case UNKNOWN: return UNKNOWN_LITERAL;
			case SOME_NEGATIVE: return SOME_NEGATIVE_LITERAL;
			case HURT: return HURT_LITERAL;
			case BREAK: return BREAK_LITERAL;
		}
		return null;
	}

    /**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ContributionType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //ContributionType
