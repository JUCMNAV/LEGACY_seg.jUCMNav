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
 * A representation of the literals of the enumeration '<em><b>Device Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see ucm.performance.PerformancePackage#getDeviceKind()
 * @model
 * @generated
 */
public final class DeviceKind extends AbstractEnumerator {
    /**
	 * The '<em><b>Processor</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #PROCESSOR_LITERAL
	 * @model name="Processor"
	 * @generated
	 * @ordered
	 */
    public static final int PROCESSOR = 0;

    /**
	 * The '<em><b>Disk</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #DISK_LITERAL
	 * @model name="Disk"
	 * @generated
	 * @ordered
	 */
    public static final int DISK = 1;

    /**
	 * The '<em><b>DSP</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #DSP_LITERAL
	 * @model
	 * @generated
	 * @ordered
	 */
    public static final int DSP = 2;

    /**
	 * The '<em><b>Other</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #OTHER_LITERAL
	 * @model name="Other"
	 * @generated
	 * @ordered
	 */
    public static final int OTHER = 3;

    /**
	 * The '<em><b>Processor</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Processor</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #PROCESSOR
	 * @generated
	 * @ordered
	 */
    public static final DeviceKind PROCESSOR_LITERAL = new DeviceKind(PROCESSOR, "Processor", "Processor");

    /**
	 * The '<em><b>Disk</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Disk</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #DISK
	 * @generated
	 * @ordered
	 */
    public static final DeviceKind DISK_LITERAL = new DeviceKind(DISK, "Disk", "Disk");

    /**
	 * The '<em><b>DSP</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>DSP</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #DSP
	 * @generated
	 * @ordered
	 */
    public static final DeviceKind DSP_LITERAL = new DeviceKind(DSP, "DSP", "DSP");

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
    public static final DeviceKind OTHER_LITERAL = new DeviceKind(OTHER, "Other", "Other");

    /**
	 * An array of all the '<em><b>Device Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private static final DeviceKind[] VALUES_ARRAY =
        new DeviceKind[] {
			PROCESSOR_LITERAL,
			DISK_LITERAL,
			DSP_LITERAL,
			OTHER_LITERAL,
		};

    /**
	 * A public read-only list of all the '<em><b>Device Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
	 * Returns the '<em><b>Device Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static DeviceKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DeviceKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Device Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DeviceKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DeviceKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Device Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static DeviceKind get(int value) {
		switch (value) {
			case PROCESSOR: return PROCESSOR_LITERAL;
			case DISK: return DISK_LITERAL;
			case DSP: return DSP_LITERAL;
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
	private DeviceKind(int value, String name, String literal) {
		super(value, name, literal);
	}

} //DeviceKind
