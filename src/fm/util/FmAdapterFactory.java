/**
 */
package fm.util;

import ca.mcgill.sel.core.COREFeature;
import ca.mcgill.sel.core.COREFeatureModel;
import ca.mcgill.sel.core.COREModel;
import ca.mcgill.sel.core.COREModelElement;
import ca.mcgill.sel.core.CORENamedElement;

import fm.*;

import grl.Contribution;
import grl.ElementLink;
import grl.GRLGraph;
import grl.GRLLinkableElement;
import grl.IntentionalElement;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import urncore.GRLmodelElement;
import urncore.IURNDiagram;
import urncore.URNmodelElement;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see fm.FmPackage
 * @generated
 */
public class FmAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static FmPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FmAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = FmPackage.eINSTANCE;
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
	protected FmSwitch modelSwitch =
		new FmSwitch() {
			public Object caseFeatureDiagram(FeatureDiagram object) {
				return createFeatureDiagramAdapter();
			}
			public Object caseFeature(Feature object) {
				return createFeatureAdapter();
			}
			public Object caseMandatoryFMLink(MandatoryFMLink object) {
				return createMandatoryFMLinkAdapter();
			}
			public Object caseOptionalFMLink(OptionalFMLink object) {
				return createOptionalFMLinkAdapter();
			}
			public Object caseFeatureModel(FeatureModel object) {
				return createFeatureModelAdapter();
			}
			public Object caseCORENamedElement(CORENamedElement object) {
				return createCORENamedElementAdapter();
			}
			public Object caseURNmodelElement(URNmodelElement object) {
				return createURNmodelElementAdapter();
			}
			public Object caseGRLmodelElement(GRLmodelElement object) {
				return createGRLmodelElementAdapter();
			}
			public Object caseIURNDiagram(IURNDiagram object) {
				return createIURNDiagramAdapter();
			}
			public Object caseGRLGraph(GRLGraph object) {
				return createGRLGraphAdapter();
			}
			public Object caseGRLLinkableElement(GRLLinkableElement object) {
				return createGRLLinkableElementAdapter();
			}
			public Object caseIntentionalElement(IntentionalElement object) {
				return createIntentionalElementAdapter();
			}
			public Object caseCOREModelElement(COREModelElement object) {
				return createCOREModelElementAdapter();
			}
			public Object caseCOREFeature(COREFeature object) {
				return createCOREFeatureAdapter();
			}
			public Object caseElementLink(ElementLink object) {
				return createElementLinkAdapter();
			}
			public Object caseContribution(Contribution object) {
				return createContributionAdapter();
			}
			public Object caseCOREModel(COREModel object) {
				return createCOREModelAdapter();
			}
			public Object caseCOREFeatureModel(COREFeatureModel object) {
				return createCOREFeatureModelAdapter();
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
	 * Creates a new adapter for an object of class '{@link fm.FeatureDiagram <em>Feature Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fm.FeatureDiagram
	 * @generated
	 */
	public Adapter createFeatureDiagramAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fm.Feature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fm.Feature
	 * @generated
	 */
	public Adapter createFeatureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fm.MandatoryFMLink <em>Mandatory FM Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fm.MandatoryFMLink
	 * @generated
	 */
	public Adapter createMandatoryFMLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fm.OptionalFMLink <em>Optional FM Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fm.OptionalFMLink
	 * @generated
	 */
	public Adapter createOptionalFMLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link fm.FeatureModel <em>Feature Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see fm.FeatureModel
	 * @generated
	 */
	public Adapter createFeatureModelAdapter() {
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
	 * Creates a new adapter for an object of class '{@link urncore.GRLmodelElement <em>GR Lmodel Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urncore.GRLmodelElement
	 * @generated
	 */
	public Adapter createGRLmodelElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link urncore.IURNDiagram <em>IURN Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urncore.IURNDiagram
	 * @generated
	 */
	public Adapter createIURNDiagramAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link grl.GRLGraph <em>GRL Graph</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see grl.GRLGraph
	 * @generated
	 */
	public Adapter createGRLGraphAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link grl.GRLLinkableElement <em>GRL Linkable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see grl.GRLLinkableElement
	 * @generated
	 */
	public Adapter createGRLLinkableElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link grl.IntentionalElement <em>Intentional Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see grl.IntentionalElement
	 * @generated
	 */
	public Adapter createIntentionalElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link ca.mcgill.sel.core.COREModelElement <em>CORE Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see ca.mcgill.sel.core.COREModelElement
	 * @generated
	 */
	public Adapter createCOREModelElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link ca.mcgill.sel.core.COREFeature <em>CORE Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see ca.mcgill.sel.core.COREFeature
	 * @generated
	 */
	public Adapter createCOREFeatureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link grl.ElementLink <em>Element Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see grl.ElementLink
	 * @generated
	 */
	public Adapter createElementLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link grl.Contribution <em>Contribution</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see grl.Contribution
	 * @generated
	 */
	public Adapter createContributionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link ca.mcgill.sel.core.COREModel <em>CORE Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see ca.mcgill.sel.core.COREModel
	 * @generated
	 */
	public Adapter createCOREModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link ca.mcgill.sel.core.COREFeatureModel <em>CORE Feature Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see ca.mcgill.sel.core.COREFeatureModel
	 * @generated
	 */
	public Adapter createCOREFeatureModelAdapter() {
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

} //FmAdapterFactory
