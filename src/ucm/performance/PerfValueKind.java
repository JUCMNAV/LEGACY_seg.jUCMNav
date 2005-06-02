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
 * A representation of the literals of the enumeration '<em><b>Perf Value Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see ucm.performance.PerformancePackage#getPerfValueKind()
 * @model
 * @generated
 */
public final class PerfValueKind extends AbstractEnumerator {
    /**
     * The '<em><b>Mean</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #MEAN_LITERAL
     * @model name="Mean"
     * @generated
     * @ordered
     */
	public static final int MEAN = 0;

    /**
     * The '<em><b>Variance</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #VARIANCE_LITERAL
     * @model name="Variance"
     * @generated
     * @ordered
     */
	public static final int VARIANCE = 1;

    /**
     * The '<em><b>Percentile</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #PERCENTILE_LITERAL
     * @model name="Percentile"
     * @generated
     * @ordered
     */
	public static final int PERCENTILE = 2;

    /**
     * The '<em><b>Moment</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #MOMENT_LITERAL
     * @model name="Moment"
     * @generated
     * @ordered
     */
	public static final int MOMENT = 3;

    /**
     * The '<em><b>Min</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #MIN_LITERAL
     * @model name="Min"
     * @generated
     * @ordered
     */
	public static final int MIN = 4;

    /**
     * The '<em><b>Max</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #MAX_LITERAL
     * @model name="Max"
     * @generated
     * @ordered
     */
	public static final int MAX = 5;

    /**
     * The '<em><b>Distribution</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #DISTRIBUTION_LITERAL
     * @model name="Distribution"
     * @generated
     * @ordered
     */
	public static final int DISTRIBUTION = 6;

    /**
     * The '<em><b>Unknown</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #UNKNOWN_LITERAL
     * @model name="Unknown"
     * @generated
     * @ordered
     */
	public static final int UNKNOWN = 7;

    /**
     * The '<em><b>Mean</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Mean</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #MEAN
     * @generated
     * @ordered
     */
	public static final PerfValueKind MEAN_LITERAL = new PerfValueKind(MEAN, "Mean");

    /**
     * The '<em><b>Variance</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Variance</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #VARIANCE
     * @generated
     * @ordered
     */
	public static final PerfValueKind VARIANCE_LITERAL = new PerfValueKind(VARIANCE, "Variance");

    /**
     * The '<em><b>Percentile</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Percentile</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #PERCENTILE
     * @generated
     * @ordered
     */
	public static final PerfValueKind PERCENTILE_LITERAL = new PerfValueKind(PERCENTILE, "Percentile");

    /**
     * The '<em><b>Moment</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Moment</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #MOMENT
     * @generated
     * @ordered
     */
	public static final PerfValueKind MOMENT_LITERAL = new PerfValueKind(MOMENT, "Moment");

    /**
     * The '<em><b>Min</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Min</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #MIN
     * @generated
     * @ordered
     */
	public static final PerfValueKind MIN_LITERAL = new PerfValueKind(MIN, "Min");

    /**
     * The '<em><b>Max</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Max</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #MAX
     * @generated
     * @ordered
     */
	public static final PerfValueKind MAX_LITERAL = new PerfValueKind(MAX, "Max");

    /**
     * The '<em><b>Distribution</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Distribution</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #DISTRIBUTION
     * @generated
     * @ordered
     */
	public static final PerfValueKind DISTRIBUTION_LITERAL = new PerfValueKind(DISTRIBUTION, "Distribution");

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
	public static final PerfValueKind UNKNOWN_LITERAL = new PerfValueKind(UNKNOWN, "Unknown");

    /**
     * An array of all the '<em><b>Perf Value Kind</b></em>' enumerators.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private static final PerfValueKind[] VALUES_ARRAY =
        new PerfValueKind[] {
            MEAN_LITERAL,
            VARIANCE_LITERAL,
            PERCENTILE_LITERAL,
            MOMENT_LITERAL,
            MIN_LITERAL,
            MAX_LITERAL,
            DISTRIBUTION_LITERAL,
            UNKNOWN_LITERAL,
        };

    /**
     * A public read-only list of all the '<em><b>Perf Value Kind</b></em>' enumerators.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Perf Value Kind</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static PerfValueKind get(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            PerfValueKind result = VALUES_ARRAY[i];
            if (result.toString().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Perf Value Kind</b></em>' literal with the specified value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static PerfValueKind get(int value) {
        switch (value) {
            case MEAN: return MEAN_LITERAL;
            case VARIANCE: return VARIANCE_LITERAL;
            case PERCENTILE: return PERCENTILE_LITERAL;
            case MOMENT: return MOMENT_LITERAL;
            case MIN: return MIN_LITERAL;
            case MAX: return MAX_LITERAL;
            case DISTRIBUTION: return DISTRIBUTION_LITERAL;
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
	private PerfValueKind(int value, String name) {
        super(value, name);
    }

} //PerfValueKind
