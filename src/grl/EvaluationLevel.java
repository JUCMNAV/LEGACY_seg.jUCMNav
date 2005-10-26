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
 * A representation of the literals of the enumeration '<em><b>Evaluation Level</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see grl.GrlPackage#getEvaluationLevel()
 * @model
 * @generated
 */
public final class EvaluationLevel extends AbstractEnumerator {
    /**
     * The '<em><b>Satisficed</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #SATISFICED_LITERAL
     * @model name="Satisficed"
     * @generated
     * @ordered
     */
    public static final int SATISFICED = 0;

    /**
     * The '<em><b>Weakly Satisficed</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #WEAKLY_SATISFICED_LITERAL
     * @model name="WeaklySatisficed"
     * @generated
     * @ordered
     */
    public static final int WEAKLY_SATISFICED = 1;

    /**
     * The '<em><b>Undecided</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #UNDECIDED_LITERAL
     * @model name="Undecided"
     * @generated
     * @ordered
     */
    public static final int UNDECIDED = 2;

    /**
     * The '<em><b>Weakly Denied</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #WEAKLY_DENIED_LITERAL
     * @model name="WeaklyDenied"
     * @generated
     * @ordered
     */
    public static final int WEAKLY_DENIED = 3;

    /**
     * The '<em><b>Denied</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DENIED_LITERAL
     * @model name="Denied"
     * @generated
     * @ordered
     */
    public static final int DENIED = 4;

    /**
     * The '<em><b>Conflict</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #CONFLICT_LITERAL
     * @model name="Conflict"
     * @generated
     * @ordered
     */
    public static final int CONFLICT = 5;

    /**
     * The '<em><b>Satisficed</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Satisficed</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #SATISFICED
     * @generated
     * @ordered
     */
    public static final EvaluationLevel SATISFICED_LITERAL = new EvaluationLevel(SATISFICED, "Satisficed");

    /**
     * The '<em><b>Weakly Satisficed</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Weakly Satisficed</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #WEAKLY_SATISFICED
     * @generated
     * @ordered
     */
    public static final EvaluationLevel WEAKLY_SATISFICED_LITERAL = new EvaluationLevel(WEAKLY_SATISFICED, "WeaklySatisficed");

    /**
     * The '<em><b>Undecided</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Undecided</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #UNDECIDED
     * @generated
     * @ordered
     */
    public static final EvaluationLevel UNDECIDED_LITERAL = new EvaluationLevel(UNDECIDED, "Undecided");

    /**
     * The '<em><b>Weakly Denied</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Weakly Denied</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #WEAKLY_DENIED
     * @generated
     * @ordered
     */
    public static final EvaluationLevel WEAKLY_DENIED_LITERAL = new EvaluationLevel(WEAKLY_DENIED, "WeaklyDenied");

    /**
     * The '<em><b>Denied</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Denied</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #DENIED
     * @generated
     * @ordered
     */
    public static final EvaluationLevel DENIED_LITERAL = new EvaluationLevel(DENIED, "Denied");

    /**
     * The '<em><b>Conflict</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Conflict</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #CONFLICT
     * @generated
     * @ordered
     */
    public static final EvaluationLevel CONFLICT_LITERAL = new EvaluationLevel(CONFLICT, "Conflict");

    /**
     * An array of all the '<em><b>Evaluation Level</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final EvaluationLevel[] VALUES_ARRAY =
        new EvaluationLevel[] {
            SATISFICED_LITERAL,
            WEAKLY_SATISFICED_LITERAL,
            UNDECIDED_LITERAL,
            WEAKLY_DENIED_LITERAL,
            DENIED_LITERAL,
            CONFLICT_LITERAL,
        };

    /**
     * A public read-only list of all the '<em><b>Evaluation Level</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Evaluation Level</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static EvaluationLevel get(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            EvaluationLevel result = VALUES_ARRAY[i];
            if (result.toString().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Evaluation Level</b></em>' literal with the specified value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static EvaluationLevel get(int value) {
        switch (value) {
            case SATISFICED: return SATISFICED_LITERAL;
            case WEAKLY_SATISFICED: return WEAKLY_SATISFICED_LITERAL;
            case UNDECIDED: return UNDECIDED_LITERAL;
            case WEAKLY_DENIED: return WEAKLY_DENIED_LITERAL;
            case DENIED: return DENIED_LITERAL;
            case CONFLICT: return CONFLICT_LITERAL;
        }
        return null;	
    }

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EvaluationLevel(int value, String name) {
        super(value, name);
    }

} //EvaluationLevel
