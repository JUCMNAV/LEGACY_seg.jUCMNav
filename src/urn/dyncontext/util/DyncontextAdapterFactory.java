/**
 */
package urn.dyncontext.util;

import ca.mcgill.sel.core.CORENamedElement;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import urn.dyncontext.*;

import urncore.URNmodelElement;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see urn.dyncontext.DyncontextPackage
 * @generated
 */
public class DyncontextAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static DyncontextPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DyncontextAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = DyncontextPackage.eINSTANCE;
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
	protected DyncontextSwitch modelSwitch =
		new DyncontextSwitch() {
			public Object caseQuadraticChange(QuadraticChange object) {
				return createQuadraticChangeAdapter();
			}
			public Object caseTimepointGroup(TimepointGroup object) {
				return createTimepointGroupAdapter();
			}
			public Object caseTimepoint(Timepoint object) {
				return createTimepointAdapter();
			}
			public Object casePropertyChange(PropertyChange object) {
				return createPropertyChangeAdapter();
			}
			public Object caseNumericChange(NumericChange object) {
				return createNumericChangeAdapter();
			}
			public Object caseFormulaChange(FormulaChange object) {
				return createFormulaChangeAdapter();
			}
			public Object caseLinearChange(LinearChange object) {
				return createLinearChangeAdapter();
			}
			public Object caseEnumChange(EnumChange object) {
				return createEnumChangeAdapter();
			}
			public Object caseDynamicContext(DynamicContext object) {
				return createDynamicContextAdapter();
			}
			public Object caseDynamicContextGroup(DynamicContextGroup object) {
				return createDynamicContextGroupAdapter();
			}
			public Object caseDeactivationChange(DeactivationChange object) {
				return createDeactivationChangeAdapter();
			}
			public Object caseConstantChange(ConstantChange object) {
				return createConstantChangeAdapter();
			}
			public Object caseChange(Change object) {
				return createChangeAdapter();
			}
			public Object caseCORENamedElement(CORENamedElement object) {
				return createCORENamedElementAdapter();
			}
			public Object caseURNmodelElement(URNmodelElement object) {
				return createURNmodelElementAdapter();
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
	 * Creates a new adapter for an object of class '{@link urn.dyncontext.QuadraticChange <em>Quadratic Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urn.dyncontext.QuadraticChange
	 * @generated
	 */
	public Adapter createQuadraticChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link urn.dyncontext.TimepointGroup <em>Timepoint Group</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urn.dyncontext.TimepointGroup
	 * @generated
	 */
	public Adapter createTimepointGroupAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link urn.dyncontext.Timepoint <em>Timepoint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urn.dyncontext.Timepoint
	 * @generated
	 */
	public Adapter createTimepointAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link urn.dyncontext.PropertyChange <em>Property Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urn.dyncontext.PropertyChange
	 * @generated
	 */
	public Adapter createPropertyChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link urn.dyncontext.NumericChange <em>Numeric Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urn.dyncontext.NumericChange
	 * @generated
	 */
	public Adapter createNumericChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link urn.dyncontext.FormulaChange <em>Formula Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urn.dyncontext.FormulaChange
	 * @generated
	 */
	public Adapter createFormulaChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link urn.dyncontext.LinearChange <em>Linear Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urn.dyncontext.LinearChange
	 * @generated
	 */
	public Adapter createLinearChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link urn.dyncontext.EnumChange <em>Enum Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urn.dyncontext.EnumChange
	 * @generated
	 */
	public Adapter createEnumChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link urn.dyncontext.DynamicContext <em>Dynamic Context</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urn.dyncontext.DynamicContext
	 * @generated
	 */
	public Adapter createDynamicContextAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link urn.dyncontext.DynamicContextGroup <em>Dynamic Context Group</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urn.dyncontext.DynamicContextGroup
	 * @generated
	 */
	public Adapter createDynamicContextGroupAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link urn.dyncontext.DeactivationChange <em>Deactivation Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urn.dyncontext.DeactivationChange
	 * @generated
	 */
	public Adapter createDeactivationChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link urn.dyncontext.ConstantChange <em>Constant Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urn.dyncontext.ConstantChange
	 * @generated
	 */
	public Adapter createConstantChangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link urn.dyncontext.Change <em>Change</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urn.dyncontext.Change
	 * @generated
	 */
	public Adapter createChangeAdapter() {
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

} //DyncontextAdapterFactory
