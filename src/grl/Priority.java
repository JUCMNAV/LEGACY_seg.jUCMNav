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
 * A representation of the literals of the enumeration '<em><b>Priority</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see grl.GrlPackage#getPriority()
 * @model
 * @generated
 */
public final class Priority extends AbstractEnumerator {
    /**
     * The '<em><b>High</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>High</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #HIGH_LITERAL
     * @model name="High"
     * @generated
     * @ordered
     */
    public static final int HIGH = 0;

    /**
     * The '<em><b>Medium</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Medium</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #MEDIUM_LITERAL
     * @model name="Medium"
     * @generated
     * @ordered
     */
    public static final int MEDIUM = 1;

    /**
     * The '<em><b>Low</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Low</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #LOW_LITERAL
     * @model name="Low"
     * @generated
     * @ordered
     */
    public static final int LOW = 2;

    /**
     * The '<em><b>High</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #HIGH
     * @generated
     * @ordered
     */
    public static final Priority HIGH_LITERAL = new Priority(HIGH, "High");

    /**
     * The '<em><b>Medium</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #MEDIUM
     * @generated
     * @ordered
     */
    public static final Priority MEDIUM_LITERAL = new Priority(MEDIUM, "Medium");

    /**
     * The '<em><b>Low</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #LOW
     * @generated
     * @ordered
     */
    public static final Priority LOW_LITERAL = new Priority(LOW, "Low");

    /**
     * An array of all the '<em><b>Priority</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final Priority[] VALUES_ARRAY =
        new Priority[] {
            HIGH_LITERAL,
            MEDIUM_LITERAL,
            LOW_LITERAL,
        };

    /**
     * A public read-only list of all the '<em><b>Priority</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Priority</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static Priority get(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            Priority result = VALUES_ARRAY[i];
            if (result.toString().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Priority</b></em>' literal with the specified value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static Priority get(int value) {
        switch (value) {
            case HIGH: return HIGH_LITERAL;
            case MEDIUM: return MEDIUM_LITERAL;
            case LOW: return LOW_LITERAL;
        }
        return null;	
    }

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private Priority(int value, String name) {
        super(value, name);
    }

} //Priority
