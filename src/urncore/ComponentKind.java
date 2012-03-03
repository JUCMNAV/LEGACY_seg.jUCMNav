/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Component Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Correspond to the component types defined originally for Use Case Maps by Buhr et al.
 * <!-- end-model-doc -->
 * @see urncore.UrncorePackage#getComponentKind()
 * @model
 * @generated
 */
public final class ComponentKind extends AbstractEnumerator {
    /**
	 * The '<em><b>Team</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #TEAM_LITERAL
	 * @model name="Team"
	 * @generated
	 * @ordered
	 */
    public static final int TEAM = 0;

    /**
	 * The '<em><b>Object</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #OBJECT_LITERAL
	 * @model name="Object"
	 * @generated
	 * @ordered
	 */
    public static final int OBJECT = 1;

    /**
	 * The '<em><b>Process</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #PROCESS_LITERAL
	 * @model name="Process"
	 * @generated
	 * @ordered
	 */
    public static final int PROCESS = 2;

    /**
	 * The '<em><b>Agent</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #AGENT_LITERAL
	 * @model name="Agent"
	 * @generated
	 * @ordered
	 */
    public static final int AGENT = 3;

    /**
	 * The '<em><b>Actor</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #ACTOR_LITERAL
	 * @model name="Actor"
	 * @generated
	 * @ordered
	 */
    public static final int ACTOR = 4;

    /**
	 * The '<em><b>Other</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #OTHER_LITERAL
	 * @model name="Other"
	 * @generated
	 * @ordered
	 */
    public static final int OTHER = 5;

    /**
	 * The '<em><b>Team</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Team</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #TEAM
	 * @generated
	 * @ordered
	 */
    public static final ComponentKind TEAM_LITERAL = new ComponentKind(TEAM, "Team", "Team");

    /**
	 * The '<em><b>Object</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Object</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #OBJECT
	 * @generated
	 * @ordered
	 */
    public static final ComponentKind OBJECT_LITERAL = new ComponentKind(OBJECT, "Object", "Object");

    /**
	 * The '<em><b>Process</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Process</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #PROCESS
	 * @generated
	 * @ordered
	 */
    public static final ComponentKind PROCESS_LITERAL = new ComponentKind(PROCESS, "Process", "Process");

    /**
	 * The '<em><b>Agent</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Agent</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #AGENT
	 * @generated
	 * @ordered
	 */
    public static final ComponentKind AGENT_LITERAL = new ComponentKind(AGENT, "Agent", "Agent");

    /**
	 * The '<em><b>Actor</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Actor</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #ACTOR
	 * @generated
	 * @ordered
	 */
    public static final ComponentKind ACTOR_LITERAL = new ComponentKind(ACTOR, "Actor", "Actor");

    /**
	 * The '<em><b>Other</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Other</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #OTHER
	 * @generated
	 * @ordered
	 */
    public static final ComponentKind OTHER_LITERAL = new ComponentKind(OTHER, "Other", "Other");

    /**
	 * An array of all the '<em><b>Component Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private static final ComponentKind[] VALUES_ARRAY =
        new ComponentKind[] {
			TEAM_LITERAL,
			OBJECT_LITERAL,
			PROCESS_LITERAL,
			AGENT_LITERAL,
			ACTOR_LITERAL,
			OTHER_LITERAL,
		};

    /**
	 * A public read-only list of all the '<em><b>Component Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
	 * Returns the '<em><b>Component Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static ComponentKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ComponentKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Component Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ComponentKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ComponentKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Component Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static ComponentKind get(int value) {
		switch (value) {
			case TEAM: return TEAM_LITERAL;
			case OBJECT: return OBJECT_LITERAL;
			case PROCESS: return PROCESS_LITERAL;
			case AGENT: return AGENT_LITERAL;
			case ACTOR: return ACTOR_LITERAL;
			case OTHER: return OTHER_LITERAL;
		}
		return null;
	}

    /**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ComponentKind(int value, String name, String literal) {
		super(value, name, literal);
	}

} //ComponentKind
