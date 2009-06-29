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
 * A representation of the literals of the enumeration '<em><b>Decomposition Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see grl.GrlPackage#getDecompositionType()
 * @model
 * @generated
 */
public final class DecompositionType extends AbstractEnumerator {
    /**
	 * The '<em><b>And</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>And</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #AND_LITERAL
	 * @model name="And"
	 * @generated
	 * @ordered
	 */
    public static final int AND = 0;

    /**
	 * The '<em><b>Or</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Or</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #OR_LITERAL
	 * @model name="Or"
	 * @generated
	 * @ordered
	 */
    public static final int OR = 1;

    /**
	 * The '<em><b>Xor</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Xor</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #XOR_LITERAL
	 * @model name="Xor"
	 * @generated
	 * @ordered
	 */
	public static final int XOR = 2;

				/**
	 * The '<em><b>And</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #AND
	 * @generated
	 * @ordered
	 */
    public static final DecompositionType AND_LITERAL = new DecompositionType(AND, "And", "And");

    /**
	 * The '<em><b>Or</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #OR
	 * @generated
	 * @ordered
	 */
    public static final DecompositionType OR_LITERAL = new DecompositionType(OR, "Or", "Or");

    /**
	 * The '<em><b>Xor</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #XOR
	 * @generated
	 * @ordered
	 */
	public static final DecompositionType XOR_LITERAL = new DecompositionType(XOR, "Xor", "Xor");

				/**
	 * An array of all the '<em><b>Decomposition Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private static final DecompositionType[] VALUES_ARRAY =
        new DecompositionType[] {
			AND_LITERAL,
			OR_LITERAL,
			XOR_LITERAL,
		};

    /**
	 * A public read-only list of all the '<em><b>Decomposition Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
	 * Returns the '<em><b>Decomposition Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static DecompositionType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DecompositionType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Decomposition Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DecompositionType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DecompositionType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Decomposition Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static DecompositionType get(int value) {
		switch (value) {
			case AND: return AND_LITERAL;
			case OR: return OR_LITERAL;
			case XOR: return XOR_LITERAL;
		}
		return null;
	}

    /**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private DecompositionType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //DecompositionType
