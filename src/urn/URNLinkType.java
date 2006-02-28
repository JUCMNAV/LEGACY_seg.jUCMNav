/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urn;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>URN Link Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see urn.UrnPackage#getURNLinkType()
 * @model
 * @generated
 */
public final class URNLinkType extends AbstractEnumerator {
    /**
     * The '<em><b>Strategy Scenario</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Strategy Scenario</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #STRATEGY_SCENARIO_LITERAL
     * @model name="StrategyScenario"
     * @generated
     * @ordered
     */
    public static final int STRATEGY_SCENARIO = 0;

    /**
     * The '<em><b>Author Component</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Author Component</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #AUTHOR_COMPONENT_LITERAL
     * @model name="AuthorComponent"
     * @generated
     * @ordered
     */
    public static final int AUTHOR_COMPONENT = 1;

    /**
     * The '<em><b>Element Variable</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Element Variable</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #ELEMENT_VARIABLE_LITERAL
     * @model name="ElementVariable"
     * @generated
     * @ordered
     */
    public static final int ELEMENT_VARIABLE = 2;

    /**
     * The '<em><b>Element Stub</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Element Stub</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #ELEMENT_STUB_LITERAL
     * @model name="ElementStub"
     * @generated
     * @ordered
     */
    public static final int ELEMENT_STUB = 3;

    /**
     * The '<em><b>Element Responsibility</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Element Responsibility</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #ELEMENT_RESPONSIBILITY_LITERAL
     * @model name="ElementResponsibility"
     * @generated
     * @ordered
     */
    public static final int ELEMENT_RESPONSIBILITY = 4;

    /**
     * The '<em><b>Strategy Scenario</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #STRATEGY_SCENARIO
     * @generated
     * @ordered
     */
    public static final URNLinkType STRATEGY_SCENARIO_LITERAL = new URNLinkType(STRATEGY_SCENARIO, "StrategyScenario");

    /**
     * The '<em><b>Author Component</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #AUTHOR_COMPONENT
     * @generated
     * @ordered
     */
    public static final URNLinkType AUTHOR_COMPONENT_LITERAL = new URNLinkType(AUTHOR_COMPONENT, "AuthorComponent");

    /**
     * The '<em><b>Element Variable</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #ELEMENT_VARIABLE
     * @generated
     * @ordered
     */
    public static final URNLinkType ELEMENT_VARIABLE_LITERAL = new URNLinkType(ELEMENT_VARIABLE, "ElementVariable");

    /**
     * The '<em><b>Element Stub</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #ELEMENT_STUB
     * @generated
     * @ordered
     */
    public static final URNLinkType ELEMENT_STUB_LITERAL = new URNLinkType(ELEMENT_STUB, "ElementStub");

    /**
     * The '<em><b>Element Responsibility</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #ELEMENT_RESPONSIBILITY
     * @generated
     * @ordered
     */
    public static final URNLinkType ELEMENT_RESPONSIBILITY_LITERAL = new URNLinkType(ELEMENT_RESPONSIBILITY, "ElementResponsibility");

    /**
     * An array of all the '<em><b>URN Link Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final URNLinkType[] VALUES_ARRAY =
        new URNLinkType[] {
            STRATEGY_SCENARIO_LITERAL,
            AUTHOR_COMPONENT_LITERAL,
            ELEMENT_VARIABLE_LITERAL,
            ELEMENT_STUB_LITERAL,
            ELEMENT_RESPONSIBILITY_LITERAL,
        };

    /**
     * A public read-only list of all the '<em><b>URN Link Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>URN Link Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static URNLinkType get(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            URNLinkType result = VALUES_ARRAY[i];
            if (result.toString().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>URN Link Type</b></em>' literal with the specified value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static URNLinkType get(int value) {
        switch (value) {
            case STRATEGY_SCENARIO: return STRATEGY_SCENARIO_LITERAL;
            case AUTHOR_COMPONENT: return AUTHOR_COMPONENT_LITERAL;
            case ELEMENT_VARIABLE: return ELEMENT_VARIABLE_LITERAL;
            case ELEMENT_STUB: return ELEMENT_STUB_LITERAL;
            case ELEMENT_RESPONSIBILITY: return ELEMENT_RESPONSIBILITY_LITERAL;
        }
        return null;	
    }

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private URNLinkType(int value, String name) {
        super(value, name);
    }

} //URNLinkType
