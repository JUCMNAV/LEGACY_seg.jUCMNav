/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.performance;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Time Unit</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see ucm.performance.PerformancePackage#getTimeUnit()
 * @model
 * @generated
 */
public final class TimeUnit extends AbstractEnumerator {
    /**
	 * The '<em><b>Year</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Year</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #YEAR_LITERAL
	 * @model name="year"
	 * @generated
	 * @ordered
	 */
    public static final int YEAR = 0;

    /**
	 * The '<em><b>Day</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Day</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #DAY_LITERAL
	 * @model name="day"
	 * @generated
	 * @ordered
	 */
    public static final int DAY = 1;

    /**
	 * The '<em><b>H</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>H</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #H_LITERAL
	 * @model name="h"
	 * @generated
	 * @ordered
	 */
    public static final int H = 2;

    /**
	 * The '<em><b>S</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>S</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #S_LITERAL
	 * @model name="s"
	 * @generated
	 * @ordered
	 */
    public static final int S = 3;

    /**
	 * The '<em><b>Ms</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Ms</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #MS_LITERAL
	 * @model name="ms"
	 * @generated
	 * @ordered
	 */
    public static final int MS = 4;

    /**
	 * The '<em><b>Us</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Us</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #US_LITERAL
	 * @model name="us"
	 * @generated
	 * @ordered
	 */
    public static final int US = 5;

    /**
	 * The '<em><b>Ns</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Ns</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #NS_LITERAL
	 * @model name="ns"
	 * @generated
	 * @ordered
	 */
    public static final int NS = 6;

    /**
	 * The '<em><b>Year</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #YEAR
	 * @generated
	 * @ordered
	 */
    public static final TimeUnit YEAR_LITERAL = new TimeUnit(YEAR, "year", "year");

    /**
	 * The '<em><b>Day</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #DAY
	 * @generated
	 * @ordered
	 */
    public static final TimeUnit DAY_LITERAL = new TimeUnit(DAY, "day", "day");

    /**
	 * The '<em><b>H</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #H
	 * @generated
	 * @ordered
	 */
    public static final TimeUnit H_LITERAL = new TimeUnit(H, "h", "h");

    /**
	 * The '<em><b>S</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #S
	 * @generated
	 * @ordered
	 */
    public static final TimeUnit S_LITERAL = new TimeUnit(S, "s", "s");

    /**
	 * The '<em><b>Ms</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #MS
	 * @generated
	 * @ordered
	 */
    public static final TimeUnit MS_LITERAL = new TimeUnit(MS, "ms", "ms");

    /**
	 * The '<em><b>Us</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #US
	 * @generated
	 * @ordered
	 */
    public static final TimeUnit US_LITERAL = new TimeUnit(US, "us", "us");

    /**
	 * The '<em><b>Ns</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #NS
	 * @generated
	 * @ordered
	 */
    public static final TimeUnit NS_LITERAL = new TimeUnit(NS, "ns", "ns");

    /**
	 * An array of all the '<em><b>Time Unit</b></em>' enumerators.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private static final TimeUnit[] VALUES_ARRAY =
        new TimeUnit[] {
			YEAR_LITERAL,
			DAY_LITERAL,
			H_LITERAL,
			S_LITERAL,
			MS_LITERAL,
			US_LITERAL,
			NS_LITERAL,
		};

    /**
	 * A public read-only list of all the '<em><b>Time Unit</b></em>' enumerators.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
	 * Returns the '<em><b>Time Unit</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static TimeUnit get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TimeUnit result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Time Unit</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static TimeUnit getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TimeUnit result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Time Unit</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static TimeUnit get(int value) {
		switch (value) {
			case YEAR: return YEAR_LITERAL;
			case DAY: return DAY_LITERAL;
			case H: return H_LITERAL;
			case S: return S_LITERAL;
			case MS: return MS_LITERAL;
			case US: return US_LITERAL;
			case NS: return NS_LITERAL;
		}
		return null;
	}

    /**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private TimeUnit(int value, String name, String literal) {
		super(value, name, literal);
	}

} //TimeUnit
