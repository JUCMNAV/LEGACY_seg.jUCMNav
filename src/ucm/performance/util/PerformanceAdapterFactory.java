/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.performance.util;

import ca.mcgill.sel.core.CORENamedElement;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import ucm.performance.*;
import urncore.UCMmodelElement;
import urncore.URNmodelElement;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see ucm.performance.PerformancePackage
 * @generated
 */
public class PerformanceAdapterFactory extends AdapterFactoryImpl {
    /**
	 * The cached model package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected static PerformancePackage modelPackage;

    /**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PerformanceAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = PerformancePackage.eINSTANCE;
		}
	}

    /**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
    public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

    /**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected PerformanceSwitch modelSwitch =
        new PerformanceSwitch() {
			public Object caseWorkload(Workload object) {
				return createWorkloadAdapter();
			}
			public Object caseGeneralResource(GeneralResource object) {
				return createGeneralResourceAdapter();
			}
			public Object caseActiveResource(ActiveResource object) {
				return createActiveResourceAdapter();
			}
			public Object casePassiveResource(PassiveResource object) {
				return createPassiveResourceAdapter();
			}
			public Object caseExternalOperation(ExternalOperation object) {
				return createExternalOperationAdapter();
			}
			public Object caseProcessingResource(ProcessingResource object) {
				return createProcessingResourceAdapter();
			}
			public Object caseDemand(Demand object) {
				return createDemandAdapter();
			}
			public Object caseCORENamedElement(CORENamedElement object) {
				return createCORENamedElementAdapter();
			}
			public Object caseURNmodelElement(URNmodelElement object) {
				return createURNmodelElementAdapter();
			}
			public Object caseUCMmodelElement(UCMmodelElement object) {
				return createUCMmodelElementAdapter();
			}
			public Object defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

    /**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
    public Adapter createAdapter(Notifier target) {
		return (Adapter)modelSwitch.doSwitch((EObject)target);
	}


    /**
	 * Creates a new adapter for an object of class '{@link ucm.performance.Workload <em>Workload</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see ucm.performance.Workload
	 * @generated
	 */
    public Adapter createWorkloadAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link ucm.performance.GeneralResource <em>General Resource</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see ucm.performance.GeneralResource
	 * @generated
	 */
    public Adapter createGeneralResourceAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link ucm.performance.ActiveResource <em>Active Resource</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see ucm.performance.ActiveResource
	 * @generated
	 */
    public Adapter createActiveResourceAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link ucm.performance.PassiveResource <em>Passive Resource</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see ucm.performance.PassiveResource
	 * @generated
	 */
    public Adapter createPassiveResourceAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link ucm.performance.ExternalOperation <em>External Operation</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see ucm.performance.ExternalOperation
	 * @generated
	 */
    public Adapter createExternalOperationAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link ucm.performance.ProcessingResource <em>Processing Resource</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see ucm.performance.ProcessingResource
	 * @generated
	 */
    public Adapter createProcessingResourceAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link ucm.performance.Demand <em>Demand</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see ucm.performance.Demand
	 * @generated
	 */
    public Adapter createDemandAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link ca.mcgill.sel.core.CORENamedElement <em>CORE Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see ca.mcgill.sel.core.CORENamedElement
	 * @generated
	 */
	public Adapter createCORENamedElementAdapter() {
		return null;
	}

				/**
	 * Creates a new adapter for an object of class '{@link urncore.URNmodelElement <em>UR Nmodel Element</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urncore.URNmodelElement
	 * @generated
	 */
    public Adapter createURNmodelElementAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link urncore.UCMmodelElement <em>UC Mmodel Element</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urncore.UCMmodelElement
	 * @generated
	 */
    public Adapter createUCMmodelElementAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
    public Adapter createEObjectAdapter() {
		return null;
	}

} //PerformanceAdapterFactory
