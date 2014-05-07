/**
 */
package org.etsi.mts.tdl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Component Instance Role</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.etsi.mts.tdl.TdlPackage#getComponentInstanceRole()
 * @model
 * @generated
 */
public enum ComponentInstanceRole implements Enumerator {
	/**
	 * The '<em><b>SUT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SUT_VALUE
	 * @generated
	 * @ordered
	 */
	SUT(0, "SUT", "SUT"),

	/**
	 * The '<em><b>Tester</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TESTER_VALUE
	 * @generated
	 * @ordered
	 */
	TESTER(1, "Tester", "Tester");

	/**
	 * The '<em><b>SUT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SUT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SUT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SUT_VALUE = 0;

	/**
	 * The '<em><b>Tester</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Tester</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TESTER
	 * @model name="Tester"
	 * @generated
	 * @ordered
	 */
	public static final int TESTER_VALUE = 1;

	/**
	 * An array of all the '<em><b>Component Instance Role</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ComponentInstanceRole[] VALUES_ARRAY =
		new ComponentInstanceRole[] {
			SUT,
			TESTER,
		};

	/**
	 * A public read-only list of all the '<em><b>Component Instance Role</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ComponentInstanceRole> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Component Instance Role</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ComponentInstanceRole get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ComponentInstanceRole result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Component Instance Role</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ComponentInstanceRole getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ComponentInstanceRole result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Component Instance Role</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ComponentInstanceRole get(int value) {
		switch (value) {
			case SUT_VALUE: return SUT;
			case TESTER_VALUE: return TESTER;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ComponentInstanceRole(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //ComponentInstanceRole
