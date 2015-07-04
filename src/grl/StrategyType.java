/**
 */
package grl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Strategy Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see grl.GrlPackage#getStrategyType()
 * @model
 * @generated
 */
public final class StrategyType extends AbstractEnumerator {
	/**
	 * The '<em><b>Default</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Default</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #DEFAULT_LITERAL
	 * @model name="Default"
	 * @generated
	 * @ordered
	 */
	public static final int DEFAULT = 0;

	/**
	 * The '<em><b>Public</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Public</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PUBLIC_LITERAL
	 * @model name="Public"
	 * @generated
	 * @ordered
	 */
	public static final int PUBLIC = 1;

	/**
	 * The '<em><b>Selected</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Selected</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SELECTED_LITERAL
	 * @model name="Selected"
	 * @generated
	 * @ordered
	 */
	public static final int SELECTED = 2;

	/**
	 * The '<em><b>Private</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Private</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #PRIVATE_LITERAL
	 * @model name="Private"
	 * @generated
	 * @ordered
	 */
	public static final int PRIVATE = 3;

	/**
	 * The '<em><b>Default</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DEFAULT
	 * @generated
	 * @ordered
	 */
	public static final StrategyType DEFAULT_LITERAL = new StrategyType(DEFAULT, "Default", "Default");

	/**
	 * The '<em><b>Public</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PUBLIC
	 * @generated
	 * @ordered
	 */
	public static final StrategyType PUBLIC_LITERAL = new StrategyType(PUBLIC, "Public", "Public");

	/**
	 * The '<em><b>Selected</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SELECTED
	 * @generated
	 * @ordered
	 */
	public static final StrategyType SELECTED_LITERAL = new StrategyType(SELECTED, "Selected", "Selected");

	/**
	 * The '<em><b>Private</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PRIVATE
	 * @generated
	 * @ordered
	 */
	public static final StrategyType PRIVATE_LITERAL = new StrategyType(PRIVATE, "Private", "Private");

	/**
	 * An array of all the '<em><b>Strategy Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final StrategyType[] VALUES_ARRAY =
		new StrategyType[] {
			DEFAULT_LITERAL,
			PUBLIC_LITERAL,
			SELECTED_LITERAL,
			PRIVATE_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Strategy Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Strategy Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static StrategyType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			StrategyType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Strategy Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static StrategyType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			StrategyType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Strategy Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static StrategyType get(int value) {
		switch (value) {
			case DEFAULT: return DEFAULT_LITERAL;
			case PUBLIC: return PUBLIC_LITERAL;
			case SELECTED: return SELECTED_LITERAL;
			case PRIVATE: return PRIVATE_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private StrategyType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //StrategyType
