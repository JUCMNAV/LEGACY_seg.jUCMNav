/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucmscenarios;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Event Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see ucmscenarios.UcmscenariosPackage#getEventType()
 * @model
 * @generated
 */
public final class EventType extends AbstractEnumerator {
	/**
	 * The '<em><b>Start Point</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Start Point</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #START_POINT_LITERAL
	 * @model name="StartPoint"
	 * @generated
	 * @ordered
	 */
	public static final int START_POINT = 0;

	/**
	 * The '<em><b>Responsibility</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Responsibility</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RESPONSIBILITY_LITERAL
	 * @model name="Responsibility"
	 * @generated
	 * @ordered
	 */
	public static final int RESPONSIBILITY = 1;

	/**
	 * The '<em><b>End Point</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>End Point</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #END_POINT_LITERAL
	 * @model name="EndPoint"
	 * @generated
	 * @ordered
	 */
	public static final int END_POINT = 2;

	/**
	 * The '<em><b>WP Enter</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>WP Enter</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #WP_ENTER_LITERAL
	 * @model name="WP_Enter"
	 * @generated
	 * @ordered
	 */
	public static final int WP_ENTER = 3;

	/**
	 * The '<em><b>WP Leave</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>WP Leave</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #WP_LEAVE_LITERAL
	 * @model name="WP_Leave"
	 * @generated
	 * @ordered
	 */
	public static final int WP_LEAVE = 4;

	/**
	 * The '<em><b>Connect Start</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Connect Start</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONNECT_START_LITERAL
	 * @model name="Connect_Start"
	 * @generated
	 * @ordered
	 */
	public static final int CONNECT_START = 5;

	/**
	 * The '<em><b>Connect End</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Connect End</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONNECT_END_LITERAL
	 * @model name="Connect_End"
	 * @generated
	 * @ordered
	 */
	public static final int CONNECT_END = 6;

	/**
	 * The '<em><b>Trigger End</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Trigger End</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TRIGGER_END_LITERAL
	 * @model name="Trigger_End"
	 * @generated
	 * @ordered
	 */
	public static final int TRIGGER_END = 7;

	/**
	 * The '<em><b>Timer Set</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Timer Set</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TIMER_SET_LITERAL
	 * @model name="Timer_Set"
	 * @generated
	 * @ordered
	 */
	public static final int TIMER_SET = 8;

	/**
	 * The '<em><b>Timer Reset</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Timer Reset</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TIMER_RESET_LITERAL
	 * @model name="Timer_Reset"
	 * @generated
	 * @ordered
	 */
	public static final int TIMER_RESET = 9;

	/**
	 * The '<em><b>Timeout</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Timeout</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TIMEOUT_LITERAL
	 * @model name="Timeout"
	 * @generated
	 * @ordered
	 */
	public static final int TIMEOUT = 10;

	/**
	 * The '<em><b>Start Point</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #START_POINT
	 * @generated
	 * @ordered
	 */
	public static final EventType START_POINT_LITERAL = new EventType(START_POINT, "StartPoint", "StartPoint");

	/**
	 * The '<em><b>Responsibility</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RESPONSIBILITY
	 * @generated
	 * @ordered
	 */
	public static final EventType RESPONSIBILITY_LITERAL = new EventType(RESPONSIBILITY, "Responsibility", "Responsibility");

	/**
	 * The '<em><b>End Point</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #END_POINT
	 * @generated
	 * @ordered
	 */
	public static final EventType END_POINT_LITERAL = new EventType(END_POINT, "EndPoint", "EndPoint");

	/**
	 * The '<em><b>WP Enter</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WP_ENTER
	 * @generated
	 * @ordered
	 */
	public static final EventType WP_ENTER_LITERAL = new EventType(WP_ENTER, "WP_Enter", "WP_Enter");

	/**
	 * The '<em><b>WP Leave</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WP_LEAVE
	 * @generated
	 * @ordered
	 */
	public static final EventType WP_LEAVE_LITERAL = new EventType(WP_LEAVE, "WP_Leave", "WP_Leave");

	/**
	 * The '<em><b>Connect Start</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONNECT_START
	 * @generated
	 * @ordered
	 */
	public static final EventType CONNECT_START_LITERAL = new EventType(CONNECT_START, "Connect_Start", "Connect_Start");

	/**
	 * The '<em><b>Connect End</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONNECT_END
	 * @generated
	 * @ordered
	 */
	public static final EventType CONNECT_END_LITERAL = new EventType(CONNECT_END, "Connect_End", "Connect_End");

	/**
	 * The '<em><b>Trigger End</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TRIGGER_END
	 * @generated
	 * @ordered
	 */
	public static final EventType TRIGGER_END_LITERAL = new EventType(TRIGGER_END, "Trigger_End", "Trigger_End");

	/**
	 * The '<em><b>Timer Set</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TIMER_SET
	 * @generated
	 * @ordered
	 */
	public static final EventType TIMER_SET_LITERAL = new EventType(TIMER_SET, "Timer_Set", "Timer_Set");

	/**
	 * The '<em><b>Timer Reset</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TIMER_RESET
	 * @generated
	 * @ordered
	 */
	public static final EventType TIMER_RESET_LITERAL = new EventType(TIMER_RESET, "Timer_Reset", "Timer_Reset");

	/**
	 * The '<em><b>Timeout</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TIMEOUT
	 * @generated
	 * @ordered
	 */
	public static final EventType TIMEOUT_LITERAL = new EventType(TIMEOUT, "Timeout", "Timeout");

	/**
	 * An array of all the '<em><b>Event Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final EventType[] VALUES_ARRAY =
		new EventType[] {
			START_POINT_LITERAL,
			RESPONSIBILITY_LITERAL,
			END_POINT_LITERAL,
			WP_ENTER_LITERAL,
			WP_LEAVE_LITERAL,
			CONNECT_START_LITERAL,
			CONNECT_END_LITERAL,
			TRIGGER_END_LITERAL,
			TIMER_SET_LITERAL,
			TIMER_RESET_LITERAL,
			TIMEOUT_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Event Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Event Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EventType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			EventType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Event Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EventType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			EventType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Event Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EventType get(int value) {
		switch (value) {
			case START_POINT: return START_POINT_LITERAL;
			case RESPONSIBILITY: return RESPONSIBILITY_LITERAL;
			case END_POINT: return END_POINT_LITERAL;
			case WP_ENTER: return WP_ENTER_LITERAL;
			case WP_LEAVE: return WP_LEAVE_LITERAL;
			case CONNECT_START: return CONNECT_START_LITERAL;
			case CONNECT_END: return CONNECT_END_LITERAL;
			case TRIGGER_END: return TRIGGER_END_LITERAL;
			case TIMER_SET: return TIMER_SET_LITERAL;
			case TIMER_RESET: return TIMER_RESET_LITERAL;
			case TIMEOUT: return TIMEOUT_LITERAL;
		}
		return null;
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EventType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //EventType
