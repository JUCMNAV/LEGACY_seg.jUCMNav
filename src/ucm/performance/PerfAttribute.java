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
 * A representation of the literals of the enumeration '<em><b>Perf Attribute</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see ucm.performance.PerformancePackage#getPerfAttribute()
 * @model
 * @generated
 */
public final class PerfAttribute extends AbstractEnumerator {
    /**
     * The '<em><b>Delay</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DELAY_LITERAL
     * @model name="Delay"
     * @generated
     * @ordered
     */
    public static final int DELAY = 0;

    /**
     * The '<em><b>Throughput</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #THROUGHPUT_LITERAL
     * @model name="Throughput"
     * @generated
     * @ordered
     */
    public static final int THROUGHPUT = 1;

    /**
     * The '<em><b>Utilization</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #UTILIZATION_LITERAL
     * @model name="Utilization"
     * @generated
     * @ordered
     */
    public static final int UTILIZATION = 2;

    /**
     * The '<em><b>Interval</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #INTERVAL_LITERAL
     * @model name="Interval"
     * @generated
     * @ordered
     */
    public static final int INTERVAL = 3;

    /**
     * The '<em><b>Wait</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #WAIT_LITERAL
     * @model name="Wait"
     * @generated
     * @ordered
     */
    public static final int WAIT = 4;

    /**
     * The '<em><b>Delay</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Delay</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #DELAY
     * @generated
     * @ordered
     */
    public static final PerfAttribute DELAY_LITERAL = new PerfAttribute(DELAY, "Delay");

    /**
     * The '<em><b>Throughput</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Throughput</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #THROUGHPUT
     * @generated
     * @ordered
     */
    public static final PerfAttribute THROUGHPUT_LITERAL = new PerfAttribute(THROUGHPUT, "Throughput");

    /**
     * The '<em><b>Utilization</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Utilization</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #UTILIZATION
     * @generated
     * @ordered
     */
    public static final PerfAttribute UTILIZATION_LITERAL = new PerfAttribute(UTILIZATION, "Utilization");

    /**
     * The '<em><b>Interval</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Interval</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #INTERVAL
     * @generated
     * @ordered
     */
    public static final PerfAttribute INTERVAL_LITERAL = new PerfAttribute(INTERVAL, "Interval");

    /**
     * The '<em><b>Wait</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Wait</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #WAIT
     * @generated
     * @ordered
     */
    public static final PerfAttribute WAIT_LITERAL = new PerfAttribute(WAIT, "Wait");

    /**
     * An array of all the '<em><b>Perf Attribute</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final PerfAttribute[] VALUES_ARRAY =
        new PerfAttribute[] {
            DELAY_LITERAL,
            THROUGHPUT_LITERAL,
            UTILIZATION_LITERAL,
            INTERVAL_LITERAL,
            WAIT_LITERAL,
        };

    /**
     * A public read-only list of all the '<em><b>Perf Attribute</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Perf Attribute</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static PerfAttribute get(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            PerfAttribute result = VALUES_ARRAY[i];
            if (result.toString().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Perf Attribute</b></em>' literal with the specified value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static PerfAttribute get(int value) {
        switch (value) {
            case DELAY: return DELAY_LITERAL;
            case THROUGHPUT: return THROUGHPUT_LITERAL;
            case UTILIZATION: return UTILIZATION_LITERAL;
            case INTERVAL: return INTERVAL_LITERAL;
            case WAIT: return WAIT_LITERAL;
        }
        return null;	
    }

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private PerfAttribute(int value, String name) {
        super(value, name);
    }

} //PerfAttribute
