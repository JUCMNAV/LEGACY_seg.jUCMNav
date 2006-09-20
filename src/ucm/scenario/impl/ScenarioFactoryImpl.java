/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.scenario.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import ucm.scenario.*;

import ucm.scenario.EnumerationType;
import ucm.scenario.Initialization;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioFactory;
import ucm.scenario.ScenarioGroup;
import ucm.scenario.ScenarioPackage;
import ucm.scenario.Variable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ScenarioFactoryImpl extends EFactoryImpl implements ScenarioFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ScenarioFactory init() {
		try {
			ScenarioFactory theScenarioFactory = (ScenarioFactory)EPackage.Registry.INSTANCE.getEFactory("http:///ucm/scenario.ecore"); 
			if (theScenarioFactory != null) {
				return theScenarioFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ScenarioFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ScenarioFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ScenarioPackage.VARIABLE: return createVariable();
			case ScenarioPackage.SCENARIO_DEF: return createScenarioDef();
			case ScenarioPackage.SCENARIO_GROUP: return createScenarioGroup();
			case ScenarioPackage.ENUMERATION_TYPE: return createEnumerationType();
			case ScenarioPackage.INITIALIZATION: return createInitialization();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ScenarioGroup createScenarioGroup() {
		ScenarioGroupImpl scenarioGroup = new ScenarioGroupImpl();
		return scenarioGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumerationType createEnumerationType() {
		EnumerationTypeImpl enumerationType = new EnumerationTypeImpl();
		return enumerationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Initialization createInitialization() {
		InitializationImpl initialization = new InitializationImpl();
		return initialization;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Variable createVariable() {
		VariableImpl variable = new VariableImpl();
		return variable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScenarioDef createScenarioDef() {
		ScenarioDefImpl scenarioDef = new ScenarioDefImpl();
		return scenarioDef;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ScenarioPackage getScenarioPackage() {
		return (ScenarioPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
    public static ScenarioPackage getPackage() {
		return ScenarioPackage.eINSTANCE;
	}

} //ScenarioFactoryImpl
