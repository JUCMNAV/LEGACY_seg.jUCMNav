/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.scenario.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import ucm.scenario.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ScenarioFactoryImpl extends EFactoryImpl implements ScenarioFactory {
    /**
     * Creates and instance of the factory.
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
            case ScenarioPackage.SCENARIO_GROUP: return createScenarioGroup();
            case ScenarioPackage.SCENARIO_DEF: return createScenarioDef();
            case ScenarioPackage.VARIABLE: return createVariable();
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
    public ScenarioDef createScenarioDef() {
        ScenarioDefImpl scenarioDef = new ScenarioDefImpl();
        return scenarioDef;
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
