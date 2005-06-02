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
 * A representation of the literals of the enumeration '<em><b>Perf Value Source</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see ucm.performance.PerformancePackage#getPerfValueSource()
 * @model
 * @generated
 */
public final class PerfValueSource extends AbstractEnumerator {
    /**
     * The '<em><b>Required</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #REQUIRED_LITERAL
     * @model name="Required"
     * @generated
     * @ordered
     */
	public static final int REQUIRED = 0;

    /**
     * The '<em><b>Assumed</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #ASSUMED_LITERAL
     * @model name="Assumed"
     * @generated
     * @ordered
     */
	public static final int ASSUMED = 1;

    /**
     * The '<em><b>Predicted</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #PREDICTED_LITERAL
     * @model name="Predicted"
     * @generated
     * @ordered
     */
	public static final int PREDICTED = 2;

    /**
     * The '<em><b>Measured</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #MEASURED_LITERAL
     * @model name="Measured"
     * @generated
     * @ordered
     */
	public static final int MEASURED = 3;

    /**
     * The '<em><b>Unknown</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #UNKNOWN_LITERAL
     * @model name="Unknown"
     * @generated
     * @ordered
     */
	public static final int UNKNOWN = 4;

    /**
     * The '<em><b>Required</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Required</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #REQUIRED
     * @generated
     * @ordered
     */
	public static final PerfValueSource REQUIRED_LITERAL = new PerfValueSource(REQUIRED, "Required");

    /**
     * The '<em><b>Assumed</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Assumed</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #ASSUMED
     * @generated
     * @ordered
     */
	public static final PerfValueSource ASSUMED_LITERAL = new PerfValueSource(ASSUMED, "Assumed");

    /**
     * The '<em><b>Predicted</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Predicted</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #PREDICTED
     * @generated
     * @ordered
     */
	public static final PerfValueSource PREDICTED_LITERAL = new PerfValueSource(PREDICTED, "Predicted");

    /**
     * The '<em><b>Measured</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Measured</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #MEASURED
     * @generated
     * @ordered
     */
	public static final PerfValueSource MEASURED_LITERAL = new PerfValueSource(MEASURED, "Measured");

    /**
     * The '<em><b>Unknown</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Unknown</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #UNKNOWN
     * @generated
     * @ordered
     */
	public static final PerfValueSource UNKNOWN_LITERAL = new PerfValueSource(UNKNOWN, "Unknown");

    /**
     * An array of all the '<em><b>Perf Value Source</b></em>' enumerators.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private static final PerfValueSource[] VALUES_ARRAY =
        new PerfValueSource[] {
            REQUIRED_LITERAL,
            ASSUMED_LITERAL,
            PREDICTED_LITERAL,
            MEASURED_LITERAL,
            UNKNOWN_LITERAL,
        };

    /**
     * A public read-only list of all the '<em><b>Perf Value Source</b></em>' enumerators.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Perf Value Source</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static PerfValueSource get(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            PerfValueSource result = VALUES_ARRAY[i];
            if (result.toString().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Perf Value Source</b></em>' literal with the specified value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static PerfValueSource get(int value) {
        switch (value) {
            case REQUIRED: return REQUIRED_LITERAL;
            case ASSUMED: return ASSUMED_LITERAL;
            case PREDICTED: return PREDICTED_LITERAL;
            case MEASURED: return MEASURED_LITERAL;
            case UNKNOWN: return UNKNOWN_LITERAL;
        }
        return null;	
    }

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private PerfValueSource(int value, String name) {
        super(value, name);
    }

} //PerfValueSource
