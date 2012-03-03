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
 * A representation of the literals of the enumeration '<em><b>Arrival Process</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see ucm.performance.PerformancePackage#getArrivalProcess()
 * @model
 * @generated
 */
public final class ArrivalProcess extends AbstractEnumerator {
    /**
	 * The '<em><b>Poisson PDF</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #POISSON_PDF_LITERAL
	 * @model name="PoissonPDF"
	 * @generated
	 * @ordered
	 */
    public static final int POISSON_PDF = 0;

    /**
	 * The '<em><b>Periodic</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #PERIODIC_LITERAL
	 * @model name="Periodic"
	 * @generated
	 * @ordered
	 */
    public static final int PERIODIC = 1;

    /**
	 * The '<em><b>Uniform</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #UNIFORM_LITERAL
	 * @model name="Uniform"
	 * @generated
	 * @ordered
	 */
    public static final int UNIFORM = 2;

    /**
	 * The '<em><b>Phase Type</b></em>' literal value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #PHASE_TYPE_LITERAL
	 * @model name="PhaseType"
	 * @generated
	 * @ordered
	 */
    public static final int PHASE_TYPE = 3;

    /**
	 * The '<em><b>Poisson PDF</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Poisson PDF</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #POISSON_PDF
	 * @generated
	 * @ordered
	 */
    public static final ArrivalProcess POISSON_PDF_LITERAL = new ArrivalProcess(POISSON_PDF, "PoissonPDF", "PoissonPDF");

    /**
	 * The '<em><b>Periodic</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Periodic</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #PERIODIC
	 * @generated
	 * @ordered
	 */
    public static final ArrivalProcess PERIODIC_LITERAL = new ArrivalProcess(PERIODIC, "Periodic", "Periodic");

    /**
	 * The '<em><b>Uniform</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Uniform</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #UNIFORM
	 * @generated
	 * @ordered
	 */
    public static final ArrivalProcess UNIFORM_LITERAL = new ArrivalProcess(UNIFORM, "Uniform", "Uniform");

    /**
	 * The '<em><b>Phase Type</b></em>' literal object.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Phase Type</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @see #PHASE_TYPE
	 * @generated
	 * @ordered
	 */
    public static final ArrivalProcess PHASE_TYPE_LITERAL = new ArrivalProcess(PHASE_TYPE, "PhaseType", "PhaseType");

    /**
	 * An array of all the '<em><b>Arrival Process</b></em>' enumerators.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private static final ArrivalProcess[] VALUES_ARRAY =
        new ArrivalProcess[] {
			POISSON_PDF_LITERAL,
			PERIODIC_LITERAL,
			UNIFORM_LITERAL,
			PHASE_TYPE_LITERAL,
		};

    /**
	 * A public read-only list of all the '<em><b>Arrival Process</b></em>' enumerators.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
	 * Returns the '<em><b>Arrival Process</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static ArrivalProcess get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ArrivalProcess result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Arrival Process</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ArrivalProcess getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ArrivalProcess result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

    /**
	 * Returns the '<em><b>Arrival Process</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static ArrivalProcess get(int value) {
		switch (value) {
			case POISSON_PDF: return POISSON_PDF_LITERAL;
			case PERIODIC: return PERIODIC_LITERAL;
			case UNIFORM: return UNIFORM_LITERAL;
			case PHASE_TYPE: return PHASE_TYPE_LITERAL;
		}
		return null;
	}

    /**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ArrivalProcess(int value, String name, String literal) {
		super(value, name, literal);
	}

} //ArrivalProcess
