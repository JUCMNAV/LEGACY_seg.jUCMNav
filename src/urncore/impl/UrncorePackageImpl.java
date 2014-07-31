/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore.impl;

import asd.AsdPackage;
import asd.impl.AsdPackageImpl;
import ca.mcgill.sel.core.CorePackage;
import fm.FmPackage;
import fm.impl.FmPackageImpl;
import grl.GrlPackage;
import grl.impl.GrlPackageImpl;
import grl.kpimodel.KpimodelPackage;
import grl.kpimodel.impl.KpimodelPackageImpl;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import ucm.UcmPackage;
import ucm.impl.UcmPackageImpl;
import ucm.map.MapPackage;
import ucm.map.impl.MapPackageImpl;
import ucm.performance.PerformancePackage;
import ucm.performance.impl.PerformancePackageImpl;
import ucm.scenario.ScenarioPackage;
import ucm.scenario.impl.ScenarioPackageImpl;
import urn.UrnPackage;
import urn.impl.UrnPackageImpl;
import urncore.Comment;
import urncore.Component;
import urncore.ComponentKind;
import urncore.ComponentLabel;
import urncore.ComponentType;
import urncore.Concern;
import urncore.Condition;
import urncore.ConnectionLabel;
import urncore.GRLmodelElement;
import urncore.IURNConnection;
import urncore.IURNContainer;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.IURNNode;
import urncore.Label;
import urncore.Metadata;
import urncore.NodeLabel;
import urncore.Responsibility;
import urncore.UCMmodelElement;
import urncore.URNdefinition;
import urncore.URNmodelElement;
import urncore.UrncoreFactory;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class UrncorePackageImpl extends EPackageImpl implements UrncorePackage {
    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass urNdefinitionEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass responsibilityEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass componentEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass componentTypeEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass ucMmodelElementEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass grLmodelElementEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass nodeLabelEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass labelEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass componentLabelEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass conditionEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass iurnDiagramEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass urNmodelElementEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass iurnNodeEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass iurnContainerRefEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass iurnContainerEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass iurnConnectionEClass = null;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass metadataEClass = null;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass concernEClass = null;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass connectionLabelEClass = null;

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass commentEClass = null;

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum componentKindEEnum = null;

    /**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see urncore.UrncorePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
    private UrncorePackageImpl() {
		super(eNS_URI, UrncoreFactory.eINSTANCE);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private static boolean isInited = false;

    /**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link UrncorePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
    public static UrncorePackage init() {
		if (isInited) return (UrncorePackage)EPackage.Registry.INSTANCE.getEPackage(UrncorePackage.eNS_URI);

		// Obtain or create and register package
		UrncorePackageImpl theUrncorePackage = (UrncorePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof UrncorePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new UrncorePackageImpl());

		isInited = true;

		// Initialize simple dependencies
		CorePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		FmPackageImpl theFmPackage = (FmPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(FmPackage.eNS_URI) instanceof FmPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(FmPackage.eNS_URI) : FmPackage.eINSTANCE);
		GrlPackageImpl theGrlPackage = (GrlPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(GrlPackage.eNS_URI) instanceof GrlPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(GrlPackage.eNS_URI) : GrlPackage.eINSTANCE);
		KpimodelPackageImpl theKpimodelPackage = (KpimodelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(KpimodelPackage.eNS_URI) instanceof KpimodelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(KpimodelPackage.eNS_URI) : KpimodelPackage.eINSTANCE);
		UrnPackageImpl theUrnPackage = (UrnPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UrnPackage.eNS_URI) instanceof UrnPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UrnPackage.eNS_URI) : UrnPackage.eINSTANCE);
		UcmPackageImpl theUcmPackage = (UcmPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UcmPackage.eNS_URI) instanceof UcmPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UcmPackage.eNS_URI) : UcmPackage.eINSTANCE);
		PerformancePackageImpl thePerformancePackage = (PerformancePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PerformancePackage.eNS_URI) instanceof PerformancePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PerformancePackage.eNS_URI) : PerformancePackage.eINSTANCE);
		MapPackageImpl theMapPackage = (MapPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(MapPackage.eNS_URI) instanceof MapPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(MapPackage.eNS_URI) : MapPackage.eINSTANCE);
		ScenarioPackageImpl theScenarioPackage = (ScenarioPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI) instanceof ScenarioPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI) : ScenarioPackage.eINSTANCE);
		AsdPackageImpl theAsdPackage = (AsdPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(AsdPackage.eNS_URI) instanceof AsdPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(AsdPackage.eNS_URI) : AsdPackage.eINSTANCE);

		// Create package meta-data objects
		theUrncorePackage.createPackageContents();
		theFmPackage.createPackageContents();
		theGrlPackage.createPackageContents();
		theKpimodelPackage.createPackageContents();
		theUrnPackage.createPackageContents();
		theUcmPackage.createPackageContents();
		thePerformancePackage.createPackageContents();
		theMapPackage.createPackageContents();
		theScenarioPackage.createPackageContents();
		theAsdPackage.createPackageContents();

		// Initialize created meta-data
		theUrncorePackage.initializePackageContents();
		theFmPackage.initializePackageContents();
		theGrlPackage.initializePackageContents();
		theKpimodelPackage.initializePackageContents();
		theUrnPackage.initializePackageContents();
		theUcmPackage.initializePackageContents();
		thePerformancePackage.initializePackageContents();
		theMapPackage.initializePackageContents();
		theScenarioPackage.initializePackageContents();
		theAsdPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theUrncorePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(UrncorePackage.eNS_URI, theUrncorePackage);
		return theUrncorePackage;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getURNdefinition() {
		return urNdefinitionEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getURNdefinition_Urnspec() {
		return (EReference)urNdefinitionEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getURNdefinition_Responsibilities() {
		return (EReference)urNdefinitionEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getURNdefinition_Components() {
		return (EReference)urNdefinitionEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getURNdefinition_ComponentTypes() {
		return (EReference)urNdefinitionEClass.getEStructuralFeatures().get(5);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getURNdefinition_SpecDiagrams() {
		return (EReference)urNdefinitionEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getURNdefinition_Concerns() {
		return (EReference)urNdefinitionEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getResponsibility() {
		return responsibilityEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getResponsibility_Empty() {
		return (EAttribute)responsibilityEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResponsibility_Expression() {
		return (EAttribute)responsibilityEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getResponsibility_Context() {
		return (EAttribute)responsibilityEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getResponsibility_Urndefinition() {
		return (EReference)responsibilityEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getResponsibility_Demands() {
		return (EReference)responsibilityEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getResponsibility_RespRefs() {
		return (EReference)responsibilityEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getResponsibility_ParentBindings() {
		return (EReference)responsibilityEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getComponent() {
		return componentEClass;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_Kind() {
		return (EAttribute)componentEClass.getEStructuralFeatures().get(0);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_Protected() {
		return (EAttribute)componentEClass.getEStructuralFeatures().get(1);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_Slot() {
		return (EAttribute)componentEClass.getEStructuralFeatures().get(2);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponent_Context() {
		return (EAttribute)componentEClass.getEStructuralFeatures().get(3);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getComponent_Type() {
		return (EReference)componentEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponent_Urndefinition() {
		return (EReference)componentEClass.getEStructuralFeatures().get(5);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponent_IncludedComponent() {
		return (EReference)componentEClass.getEStructuralFeatures().get(6);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponent_IncludingComponent() {
		return (EReference)componentEClass.getEStructuralFeatures().get(7);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponent_Resource() {
		return (EReference)componentEClass.getEStructuralFeatures().get(8);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponent_Host() {
		return (EReference)componentEClass.getEStructuralFeatures().get(9);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getComponentType() {
		return componentTypeEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getComponentType_Instances() {
		return (EReference)componentTypeEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponentType_Urndefinition() {
		return (EReference)componentTypeEClass.getEStructuralFeatures().get(1);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getUCMmodelElement() {
		return ucMmodelElementEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getGRLmodelElement() {
		return grLmodelElementEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getNodeLabel() {
		return nodeLabelEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getNodeLabel_Node() {
		return (EReference)nodeLabelEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getLabel() {
		return labelEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLabel_DeltaX() {
		return (EAttribute)labelEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLabel_DeltaY() {
		return (EAttribute)labelEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getComponentLabel() {
		return componentLabelEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getComponentLabel_ContRef() {
		return (EReference)componentLabelEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getCondition() {
		return conditionEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCondition_Label() {
		return (EAttribute)conditionEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCondition_Expression() {
		return (EAttribute)conditionEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCondition_Description() {
		return (EAttribute)conditionEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCondition_StartPoint() {
		return (EReference)conditionEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCondition_EndPoint() {
		return (EReference)conditionEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCondition_PluginBinding() {
		return (EReference)conditionEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCondition_NodeConnection() {
		return (EReference)conditionEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCondition_Concern() {
		return (EReference)conditionEClass.getEStructuralFeatures().get(7);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCondition_ScenarioDefPre() {
		return (EReference)conditionEClass.getEStructuralFeatures().get(8);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCondition_ScenarioDefPost() {
		return (EReference)conditionEClass.getEStructuralFeatures().get(9);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getIURNDiagram() {
		return iurnDiagramEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getIURNDiagram_Urndefinition() {
		return (EReference)iurnDiagramEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getIURNDiagram_Nodes() {
		return (EReference)iurnDiagramEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getIURNDiagram_ContRefs() {
		return (EReference)iurnDiagramEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getIURNDiagram_Connections() {
		return (EReference)iurnDiagramEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIURNDiagram_Concern() {
		return (EReference)iurnDiagramEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIURNDiagram_Comments() {
		return (EReference)iurnDiagramEClass.getEStructuralFeatures().get(5);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getURNmodelElement() {
		return urNmodelElementEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getURNmodelElement_FromLinks() {
		return (EReference)urNmodelElementEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getURNmodelElement_ToLinks() {
		return (EReference)urNmodelElementEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getURNmodelElement_Id() {
		return (EAttribute)urNmodelElementEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getURNmodelElement_Description() {
		return (EAttribute)urNmodelElementEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getURNmodelElement_Metadata() {
		return (EReference)urNmodelElementEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getURNmodelElement_Inconcern() {
		return (EReference)urNmodelElementEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getIURNNode() {
		return iurnNodeEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getIURNNode_X() {
		return (EAttribute)iurnNodeEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getIURNNode_Y() {
		return (EAttribute)iurnNodeEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getIURNNode_Diagram() {
		return (EReference)iurnNodeEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getIURNNode_ContRef() {
		return (EReference)iurnNodeEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getIURNNode_Succ() {
		return (EReference)iurnNodeEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getIURNNode_Pred() {
		return (EReference)iurnNodeEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getIURNNode_Label() {
		return (EReference)iurnNodeEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getIURNContainerRef() {
		return iurnContainerRefEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getIURNContainerRef_X() {
		return (EAttribute)iurnContainerRefEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getIURNContainerRef_Y() {
		return (EAttribute)iurnContainerRefEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getIURNContainerRef_Width() {
		return (EAttribute)iurnContainerRefEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getIURNContainerRef_Height() {
		return (EAttribute)iurnContainerRefEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getIURNContainerRef_Fixed() {
		return (EAttribute)iurnContainerRefEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getIURNContainerRef_Diagram() {
		return (EReference)iurnContainerRefEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getIURNContainerRef_ContDef() {
		return (EReference)iurnContainerRefEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getIURNContainerRef_Nodes() {
		return (EReference)iurnContainerRefEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getIURNContainerRef_Label() {
		return (EReference)iurnContainerRefEClass.getEStructuralFeatures().get(8);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getIURNContainerRef_Parent() {
		return (EReference)iurnContainerRefEClass.getEStructuralFeatures().get(9);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getIURNContainerRef_Children() {
		return (EReference)iurnContainerRefEClass.getEStructuralFeatures().get(10);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getIURNContainer() {
		return iurnContainerEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getIURNContainer_LineColor() {
		return (EAttribute)iurnContainerEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getIURNContainer_FillColor() {
		return (EAttribute)iurnContainerEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getIURNContainer_Filled() {
		return (EAttribute)iurnContainerEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getIURNContainer_ContRefs() {
		return (EReference)iurnContainerEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getIURNConnection() {
		return iurnConnectionEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getIURNConnection_Source() {
		return (EReference)iurnConnectionEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getIURNConnection_Target() {
		return (EReference)iurnConnectionEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getIURNConnection_Diagram() {
		return (EReference)iurnConnectionEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIURNConnection_Label() {
		return (EReference)iurnConnectionEClass.getEStructuralFeatures().get(3);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMetadata() {
		return metadataEClass;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetadata_Name() {
		return (EAttribute)metadataEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMetadata_Value() {
		return (EAttribute)metadataEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConcern() {
		return concernEClass;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConcern_Urndefinition() {
		return (EReference)concernEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConcern_SpecDiagrams() {
		return (EReference)concernEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getConcern_Elements() {
		return (EReference)concernEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConcern_CoreConcern() {
		return (EReference)concernEClass.getEStructuralFeatures().get(3);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConcern_Condition() {
		return (EReference)concernEClass.getEStructuralFeatures().get(4);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConnectionLabel() {
		return connectionLabelEClass;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConnectionLabel_Connection() {
		return (EReference)connectionLabelEClass.getEStructuralFeatures().get(0);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComment() {
		return commentEClass;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComment_Description() {
		return (EAttribute)commentEClass.getEStructuralFeatures().get(0);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComment_X() {
		return (EAttribute)commentEClass.getEStructuralFeatures().get(1);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComment_Y() {
		return (EAttribute)commentEClass.getEStructuralFeatures().get(2);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComment_Width() {
		return (EAttribute)commentEClass.getEStructuralFeatures().get(3);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComment_Height() {
		return (EAttribute)commentEClass.getEStructuralFeatures().get(4);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComment_FillColor() {
		return (EAttribute)commentEClass.getEStructuralFeatures().get(5);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComment_Diagram() {
		return (EReference)commentEClass.getEStructuralFeatures().get(6);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getComponentKind() {
		return componentKindEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public UrncoreFactory getUrncoreFactory() {
		return (UrncoreFactory)getEFactoryInstance();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private boolean isCreated = false;

    /**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		urNdefinitionEClass = createEClass(UR_NDEFINITION);
		createEReference(urNdefinitionEClass, UR_NDEFINITION__URNSPEC);
		createEReference(urNdefinitionEClass, UR_NDEFINITION__RESPONSIBILITIES);
		createEReference(urNdefinitionEClass, UR_NDEFINITION__SPEC_DIAGRAMS);
		createEReference(urNdefinitionEClass, UR_NDEFINITION__CONCERNS);
		createEReference(urNdefinitionEClass, UR_NDEFINITION__COMPONENTS);
		createEReference(urNdefinitionEClass, UR_NDEFINITION__COMPONENT_TYPES);

		responsibilityEClass = createEClass(RESPONSIBILITY);
		createEAttribute(responsibilityEClass, RESPONSIBILITY__EMPTY);
		createEAttribute(responsibilityEClass, RESPONSIBILITY__EXPRESSION);
		createEAttribute(responsibilityEClass, RESPONSIBILITY__CONTEXT);
		createEReference(responsibilityEClass, RESPONSIBILITY__URNDEFINITION);
		createEReference(responsibilityEClass, RESPONSIBILITY__DEMANDS);
		createEReference(responsibilityEClass, RESPONSIBILITY__RESP_REFS);
		createEReference(responsibilityEClass, RESPONSIBILITY__PARENT_BINDINGS);

		componentEClass = createEClass(COMPONENT);
		createEAttribute(componentEClass, COMPONENT__KIND);
		createEAttribute(componentEClass, COMPONENT__PROTECTED);
		createEAttribute(componentEClass, COMPONENT__SLOT);
		createEAttribute(componentEClass, COMPONENT__CONTEXT);
		createEReference(componentEClass, COMPONENT__TYPE);
		createEReference(componentEClass, COMPONENT__URNDEFINITION);
		createEReference(componentEClass, COMPONENT__INCLUDED_COMPONENT);
		createEReference(componentEClass, COMPONENT__INCLUDING_COMPONENT);
		createEReference(componentEClass, COMPONENT__RESOURCE);
		createEReference(componentEClass, COMPONENT__HOST);

		componentTypeEClass = createEClass(COMPONENT_TYPE);
		createEReference(componentTypeEClass, COMPONENT_TYPE__INSTANCES);
		createEReference(componentTypeEClass, COMPONENT_TYPE__URNDEFINITION);

		ucMmodelElementEClass = createEClass(UC_MMODEL_ELEMENT);

		grLmodelElementEClass = createEClass(GR_LMODEL_ELEMENT);

		nodeLabelEClass = createEClass(NODE_LABEL);
		createEReference(nodeLabelEClass, NODE_LABEL__NODE);

		labelEClass = createEClass(LABEL);
		createEAttribute(labelEClass, LABEL__DELTA_X);
		createEAttribute(labelEClass, LABEL__DELTA_Y);

		componentLabelEClass = createEClass(COMPONENT_LABEL);
		createEReference(componentLabelEClass, COMPONENT_LABEL__CONT_REF);

		conditionEClass = createEClass(CONDITION);
		createEAttribute(conditionEClass, CONDITION__LABEL);
		createEAttribute(conditionEClass, CONDITION__EXPRESSION);
		createEAttribute(conditionEClass, CONDITION__DESCRIPTION);
		createEReference(conditionEClass, CONDITION__START_POINT);
		createEReference(conditionEClass, CONDITION__END_POINT);
		createEReference(conditionEClass, CONDITION__PLUGIN_BINDING);
		createEReference(conditionEClass, CONDITION__NODE_CONNECTION);
		createEReference(conditionEClass, CONDITION__CONCERN);
		createEReference(conditionEClass, CONDITION__SCENARIO_DEF_PRE);
		createEReference(conditionEClass, CONDITION__SCENARIO_DEF_POST);

		iurnDiagramEClass = createEClass(IURN_DIAGRAM);
		createEReference(iurnDiagramEClass, IURN_DIAGRAM__URNDEFINITION);
		createEReference(iurnDiagramEClass, IURN_DIAGRAM__NODES);
		createEReference(iurnDiagramEClass, IURN_DIAGRAM__CONT_REFS);
		createEReference(iurnDiagramEClass, IURN_DIAGRAM__CONNECTIONS);
		createEReference(iurnDiagramEClass, IURN_DIAGRAM__CONCERN);
		createEReference(iurnDiagramEClass, IURN_DIAGRAM__COMMENTS);

		urNmodelElementEClass = createEClass(UR_NMODEL_ELEMENT);
		createEReference(urNmodelElementEClass, UR_NMODEL_ELEMENT__FROM_LINKS);
		createEReference(urNmodelElementEClass, UR_NMODEL_ELEMENT__TO_LINKS);
		createEAttribute(urNmodelElementEClass, UR_NMODEL_ELEMENT__ID);
		createEAttribute(urNmodelElementEClass, UR_NMODEL_ELEMENT__DESCRIPTION);
		createEReference(urNmodelElementEClass, UR_NMODEL_ELEMENT__METADATA);
		createEReference(urNmodelElementEClass, UR_NMODEL_ELEMENT__INCONCERN);

		iurnNodeEClass = createEClass(IURN_NODE);
		createEAttribute(iurnNodeEClass, IURN_NODE__X);
		createEAttribute(iurnNodeEClass, IURN_NODE__Y);
		createEReference(iurnNodeEClass, IURN_NODE__DIAGRAM);
		createEReference(iurnNodeEClass, IURN_NODE__CONT_REF);
		createEReference(iurnNodeEClass, IURN_NODE__SUCC);
		createEReference(iurnNodeEClass, IURN_NODE__PRED);
		createEReference(iurnNodeEClass, IURN_NODE__LABEL);

		iurnContainerRefEClass = createEClass(IURN_CONTAINER_REF);
		createEAttribute(iurnContainerRefEClass, IURN_CONTAINER_REF__X);
		createEAttribute(iurnContainerRefEClass, IURN_CONTAINER_REF__Y);
		createEAttribute(iurnContainerRefEClass, IURN_CONTAINER_REF__WIDTH);
		createEAttribute(iurnContainerRefEClass, IURN_CONTAINER_REF__HEIGHT);
		createEAttribute(iurnContainerRefEClass, IURN_CONTAINER_REF__FIXED);
		createEReference(iurnContainerRefEClass, IURN_CONTAINER_REF__DIAGRAM);
		createEReference(iurnContainerRefEClass, IURN_CONTAINER_REF__CONT_DEF);
		createEReference(iurnContainerRefEClass, IURN_CONTAINER_REF__NODES);
		createEReference(iurnContainerRefEClass, IURN_CONTAINER_REF__LABEL);
		createEReference(iurnContainerRefEClass, IURN_CONTAINER_REF__PARENT);
		createEReference(iurnContainerRefEClass, IURN_CONTAINER_REF__CHILDREN);

		iurnContainerEClass = createEClass(IURN_CONTAINER);
		createEAttribute(iurnContainerEClass, IURN_CONTAINER__LINE_COLOR);
		createEAttribute(iurnContainerEClass, IURN_CONTAINER__FILL_COLOR);
		createEAttribute(iurnContainerEClass, IURN_CONTAINER__FILLED);
		createEReference(iurnContainerEClass, IURN_CONTAINER__CONT_REFS);

		iurnConnectionEClass = createEClass(IURN_CONNECTION);
		createEReference(iurnConnectionEClass, IURN_CONNECTION__SOURCE);
		createEReference(iurnConnectionEClass, IURN_CONNECTION__TARGET);
		createEReference(iurnConnectionEClass, IURN_CONNECTION__DIAGRAM);
		createEReference(iurnConnectionEClass, IURN_CONNECTION__LABEL);

		metadataEClass = createEClass(METADATA);
		createEAttribute(metadataEClass, METADATA__NAME);
		createEAttribute(metadataEClass, METADATA__VALUE);

		concernEClass = createEClass(CONCERN);
		createEReference(concernEClass, CONCERN__URNDEFINITION);
		createEReference(concernEClass, CONCERN__SPEC_DIAGRAMS);
		createEReference(concernEClass, CONCERN__ELEMENTS);
		createEReference(concernEClass, CONCERN__CORE_CONCERN);
		createEReference(concernEClass, CONCERN__CONDITION);

		connectionLabelEClass = createEClass(CONNECTION_LABEL);
		createEReference(connectionLabelEClass, CONNECTION_LABEL__CONNECTION);

		commentEClass = createEClass(COMMENT);
		createEAttribute(commentEClass, COMMENT__DESCRIPTION);
		createEAttribute(commentEClass, COMMENT__X);
		createEAttribute(commentEClass, COMMENT__Y);
		createEAttribute(commentEClass, COMMENT__WIDTH);
		createEAttribute(commentEClass, COMMENT__HEIGHT);
		createEAttribute(commentEClass, COMMENT__FILL_COLOR);
		createEReference(commentEClass, COMMENT__DIAGRAM);

		// Create enums
		componentKindEEnum = createEEnum(COMPONENT_KIND);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private boolean isInitialized = false;

    /**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		UrnPackage theUrnPackage = (UrnPackage)EPackage.Registry.INSTANCE.getEPackage(UrnPackage.eNS_URI);
		PerformancePackage thePerformancePackage = (PerformancePackage)EPackage.Registry.INSTANCE.getEPackage(PerformancePackage.eNS_URI);
		MapPackage theMapPackage = (MapPackage)EPackage.Registry.INSTANCE.getEPackage(MapPackage.eNS_URI);
		ScenarioPackage theScenarioPackage = (ScenarioPackage)EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI);
		CorePackage theCorePackage = (CorePackage)EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);

		// Add supertypes to classes
		responsibilityEClass.getESuperTypes().add(this.getUCMmodelElement());
		componentEClass.getESuperTypes().add(this.getUCMmodelElement());
		componentEClass.getESuperTypes().add(this.getIURNContainer());
		componentTypeEClass.getESuperTypes().add(this.getUCMmodelElement());
		ucMmodelElementEClass.getESuperTypes().add(this.getURNmodelElement());
		grLmodelElementEClass.getESuperTypes().add(this.getURNmodelElement());
		nodeLabelEClass.getESuperTypes().add(this.getLabel());
		componentLabelEClass.getESuperTypes().add(this.getLabel());
		conditionEClass.getESuperTypes().add(this.getLabel());
		urNmodelElementEClass.getESuperTypes().add(theCorePackage.getCORENamedElement());
		concernEClass.getESuperTypes().add(this.getURNmodelElement());
		connectionLabelEClass.getESuperTypes().add(this.getLabel());

		// Initialize classes and features; add operations and parameters
		initEClass(urNdefinitionEClass, URNdefinition.class, "URNdefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getURNdefinition_Urnspec(), theUrnPackage.getURNspec(), theUrnPackage.getURNspec_Urndef(), "urnspec", null, 1, 1, URNdefinition.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getURNdefinition_Responsibilities(), this.getResponsibility(), this.getResponsibility_Urndefinition(), "responsibilities", null, 0, -1, URNdefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getURNdefinition_SpecDiagrams(), this.getIURNDiagram(), this.getIURNDiagram_Urndefinition(), "specDiagrams", null, 0, -1, URNdefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getURNdefinition_Concerns(), this.getConcern(), this.getConcern_Urndefinition(), "concerns", null, 0, -1, URNdefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getURNdefinition_Components(), this.getComponent(), this.getComponent_Urndefinition(), "components", null, 0, -1, URNdefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getURNdefinition_ComponentTypes(), this.getComponentType(), this.getComponentType_Urndefinition(), "componentTypes", null, 0, -1, URNdefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(responsibilityEClass, Responsibility.class, "Responsibility", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getResponsibility_Empty(), ecorePackage.getEBoolean(), "empty", "false", 0, 1, Responsibility.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResponsibility_Expression(), ecorePackage.getEString(), "expression", null, 0, 1, Responsibility.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResponsibility_Context(), ecorePackage.getEBoolean(), "context", "false", 0, 1, Responsibility.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResponsibility_Urndefinition(), this.getURNdefinition(), this.getURNdefinition_Responsibilities(), "urndefinition", null, 1, 1, Responsibility.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResponsibility_Demands(), thePerformancePackage.getDemand(), thePerformancePackage.getDemand_Responsibility(), "demands", null, 0, -1, Responsibility.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResponsibility_RespRefs(), theMapPackage.getRespRef(), theMapPackage.getRespRef_RespDef(), "respRefs", null, 1, -1, Responsibility.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResponsibility_ParentBindings(), theMapPackage.getResponsibilityBinding(), theMapPackage.getResponsibilityBinding_ParentResp(), "parentBindings", null, 0, -1, Responsibility.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(componentEClass, Component.class, "Component", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getComponent_Kind(), this.getComponentKind(), "kind", null, 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_Protected(), ecorePackage.getEBoolean(), "protected", "false", 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_Slot(), ecorePackage.getEBoolean(), "slot", "false", 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponent_Context(), ecorePackage.getEBoolean(), "context", "false", 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponent_Type(), this.getComponentType(), this.getComponentType_Instances(), "type", null, 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponent_Urndefinition(), this.getURNdefinition(), this.getURNdefinition_Components(), "urndefinition", null, 1, 1, Component.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponent_IncludedComponent(), this.getComponent(), this.getComponent_IncludingComponent(), "includedComponent", null, 0, -1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponent_IncludingComponent(), this.getComponent(), this.getComponent_IncludedComponent(), "includingComponent", null, 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponent_Resource(), thePerformancePackage.getPassiveResource(), thePerformancePackage.getPassiveResource_Component(), "resource", null, 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponent_Host(), thePerformancePackage.getProcessingResource(), thePerformancePackage.getProcessingResource_Components(), "host", null, 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(componentTypeEClass, ComponentType.class, "ComponentType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComponentType_Instances(), this.getComponent(), this.getComponent_Type(), "instances", null, 0, -1, ComponentType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponentType_Urndefinition(), this.getURNdefinition(), this.getURNdefinition_ComponentTypes(), "urndefinition", null, 1, 1, ComponentType.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ucMmodelElementEClass, UCMmodelElement.class, "UCMmodelElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(grLmodelElementEClass, GRLmodelElement.class, "GRLmodelElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(nodeLabelEClass, NodeLabel.class, "NodeLabel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNodeLabel_Node(), this.getIURNNode(), this.getIURNNode_Label(), "node", null, 1, 1, NodeLabel.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(labelEClass, Label.class, "Label", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLabel_DeltaX(), ecorePackage.getEInt(), "deltaX", null, 0, 1, Label.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabel_DeltaY(), ecorePackage.getEInt(), "deltaY", null, 0, 1, Label.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(componentLabelEClass, ComponentLabel.class, "ComponentLabel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComponentLabel_ContRef(), this.getIURNContainerRef(), this.getIURNContainerRef_Label(), "contRef", null, 0, 1, ComponentLabel.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(conditionEClass, Condition.class, "Condition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCondition_Label(), ecorePackage.getEString(), "label", null, 0, 1, Condition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCondition_Expression(), ecorePackage.getEString(), "expression", null, 0, 1, Condition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCondition_Description(), ecorePackage.getEString(), "description", null, 0, 1, Condition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCondition_StartPoint(), theMapPackage.getStartPoint(), theMapPackage.getStartPoint_Precondition(), "startPoint", null, 0, 1, Condition.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCondition_EndPoint(), theMapPackage.getEndPoint(), theMapPackage.getEndPoint_Postcondition(), "endPoint", null, 0, 1, Condition.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCondition_PluginBinding(), theMapPackage.getPluginBinding(), theMapPackage.getPluginBinding_Precondition(), "pluginBinding", null, 0, 1, Condition.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCondition_NodeConnection(), theMapPackage.getNodeConnection(), theMapPackage.getNodeConnection_Condition(), "nodeConnection", null, 0, 1, Condition.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCondition_Concern(), this.getConcern(), this.getConcern_Condition(), "concern", null, 0, 1, Condition.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCondition_ScenarioDefPre(), theScenarioPackage.getScenarioDef(), theScenarioPackage.getScenarioDef_Preconditions(), "scenarioDefPre", null, 0, 1, Condition.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCondition_ScenarioDefPost(), theScenarioPackage.getScenarioDef(), theScenarioPackage.getScenarioDef_Postconditions(), "scenarioDefPost", null, 0, 1, Condition.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iurnDiagramEClass, IURNDiagram.class, "IURNDiagram", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIURNDiagram_Urndefinition(), this.getURNdefinition(), this.getURNdefinition_SpecDiagrams(), "urndefinition", null, 1, 1, IURNDiagram.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIURNDiagram_Nodes(), this.getIURNNode(), this.getIURNNode_Diagram(), "nodes", null, 0, -1, IURNDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIURNDiagram_ContRefs(), this.getIURNContainerRef(), this.getIURNContainerRef_Diagram(), "contRefs", null, 0, -1, IURNDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIURNDiagram_Connections(), this.getIURNConnection(), this.getIURNConnection_Diagram(), "connections", null, 0, -1, IURNDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIURNDiagram_Concern(), this.getConcern(), this.getConcern_SpecDiagrams(), "concern", null, 0, 1, IURNDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIURNDiagram_Comments(), this.getComment(), this.getComment_Diagram(), "comments", null, 0, -1, IURNDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(urNmodelElementEClass, URNmodelElement.class, "URNmodelElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getURNmodelElement_FromLinks(), theUrnPackage.getURNlink(), theUrnPackage.getURNlink_FromElem(), "fromLinks", null, 0, -1, URNmodelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getURNmodelElement_ToLinks(), theUrnPackage.getURNlink(), theUrnPackage.getURNlink_ToElem(), "toLinks", null, 0, -1, URNmodelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getURNmodelElement_Id(), ecorePackage.getEString(), "id", null, 0, 1, URNmodelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getURNmodelElement_Description(), ecorePackage.getEString(), "description", null, 0, 1, URNmodelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getURNmodelElement_Metadata(), this.getMetadata(), null, "metadata", null, 0, -1, URNmodelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getURNmodelElement_Inconcern(), this.getConcern(), this.getConcern_Elements(), "inconcern", null, 0, 1, URNmodelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iurnNodeEClass, IURNNode.class, "IURNNode", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIURNNode_X(), ecorePackage.getEInt(), "x", null, 0, 1, IURNNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIURNNode_Y(), ecorePackage.getEInt(), "y", null, 0, 1, IURNNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIURNNode_Diagram(), this.getIURNDiagram(), this.getIURNDiagram_Nodes(), "diagram", null, 1, 1, IURNNode.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIURNNode_ContRef(), this.getIURNContainerRef(), this.getIURNContainerRef_Nodes(), "contRef", null, 0, 1, IURNNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIURNNode_Succ(), this.getIURNConnection(), this.getIURNConnection_Source(), "succ", null, 0, -1, IURNNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIURNNode_Pred(), this.getIURNConnection(), this.getIURNConnection_Target(), "pred", null, 0, -1, IURNNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIURNNode_Label(), this.getNodeLabel(), this.getNodeLabel_Node(), "label", null, 0, 1, IURNNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iurnContainerRefEClass, IURNContainerRef.class, "IURNContainerRef", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIURNContainerRef_X(), ecorePackage.getEInt(), "x", null, 0, 1, IURNContainerRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIURNContainerRef_Y(), ecorePackage.getEInt(), "y", null, 0, 1, IURNContainerRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIURNContainerRef_Width(), ecorePackage.getEInt(), "width", null, 0, 1, IURNContainerRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIURNContainerRef_Height(), ecorePackage.getEInt(), "height", null, 0, 1, IURNContainerRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIURNContainerRef_Fixed(), ecorePackage.getEBoolean(), "fixed", "false", 0, 1, IURNContainerRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIURNContainerRef_Diagram(), this.getIURNDiagram(), this.getIURNDiagram_ContRefs(), "diagram", null, 1, 1, IURNContainerRef.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIURNContainerRef_ContDef(), this.getIURNContainer(), this.getIURNContainer_ContRefs(), "contDef", null, 1, 1, IURNContainerRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIURNContainerRef_Nodes(), this.getIURNNode(), this.getIURNNode_ContRef(), "nodes", null, 0, -1, IURNContainerRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIURNContainerRef_Label(), this.getComponentLabel(), this.getComponentLabel_ContRef(), "label", null, 1, 1, IURNContainerRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIURNContainerRef_Parent(), this.getIURNContainerRef(), this.getIURNContainerRef_Children(), "parent", null, 0, 1, IURNContainerRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIURNContainerRef_Children(), this.getIURNContainerRef(), this.getIURNContainerRef_Parent(), "children", null, 0, -1, IURNContainerRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iurnContainerEClass, IURNContainer.class, "IURNContainer", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIURNContainer_LineColor(), ecorePackage.getEString(), "lineColor", null, 0, 1, IURNContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIURNContainer_FillColor(), ecorePackage.getEString(), "fillColor", null, 0, 1, IURNContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIURNContainer_Filled(), ecorePackage.getEBoolean(), "filled", "false", 0, 1, IURNContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIURNContainer_ContRefs(), this.getIURNContainerRef(), this.getIURNContainerRef_ContDef(), "contRefs", null, 0, -1, IURNContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iurnConnectionEClass, IURNConnection.class, "IURNConnection", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIURNConnection_Source(), this.getIURNNode(), this.getIURNNode_Succ(), "source", null, 1, 1, IURNConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIURNConnection_Target(), this.getIURNNode(), this.getIURNNode_Pred(), "target", null, 1, 1, IURNConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIURNConnection_Diagram(), this.getIURNDiagram(), this.getIURNDiagram_Connections(), "diagram", null, 1, 1, IURNConnection.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIURNConnection_Label(), this.getConnectionLabel(), this.getConnectionLabel_Connection(), "label", null, 0, 1, IURNConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(metadataEClass, Metadata.class, "Metadata", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMetadata_Name(), ecorePackage.getEString(), "name", null, 0, 1, Metadata.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMetadata_Value(), ecorePackage.getEString(), "value", null, 0, 1, Metadata.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(concernEClass, Concern.class, "Concern", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConcern_Urndefinition(), this.getURNdefinition(), this.getURNdefinition_Concerns(), "urndefinition", null, 1, 1, Concern.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConcern_SpecDiagrams(), this.getIURNDiagram(), this.getIURNDiagram_Concern(), "specDiagrams", null, 0, -1, Concern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConcern_Elements(), this.getURNmodelElement(), this.getURNmodelElement_Inconcern(), "elements", null, 0, -1, Concern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConcern_CoreConcern(), theCorePackage.getCOREConcern(), null, "coreConcern", null, 0, 1, Concern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConcern_Condition(), this.getCondition(), this.getCondition_Concern(), "condition", null, 0, 1, Concern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(connectionLabelEClass, ConnectionLabel.class, "ConnectionLabel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConnectionLabel_Connection(), this.getIURNConnection(), this.getIURNConnection_Label(), "connection", null, 1, 1, ConnectionLabel.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(commentEClass, Comment.class, "Comment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getComment_Description(), ecorePackage.getEString(), "description", null, 0, 1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComment_X(), ecorePackage.getEInt(), "x", null, 0, 1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComment_Y(), ecorePackage.getEInt(), "y", null, 0, 1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComment_Width(), ecorePackage.getEInt(), "width", null, 0, 1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComment_Height(), ecorePackage.getEInt(), "height", null, 0, 1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComment_FillColor(), ecorePackage.getEString(), "fillColor", null, 0, 1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComment_Diagram(), this.getIURNDiagram(), this.getIURNDiagram_Comments(), "diagram", null, 0, 1, Comment.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(componentKindEEnum, ComponentKind.class, "ComponentKind");
		addEEnumLiteral(componentKindEEnum, ComponentKind.TEAM_LITERAL);
		addEEnumLiteral(componentKindEEnum, ComponentKind.OBJECT_LITERAL);
		addEEnumLiteral(componentKindEEnum, ComponentKind.PROCESS_LITERAL);
		addEEnumLiteral(componentKindEEnum, ComponentKind.AGENT_LITERAL);
		addEEnumLiteral(componentKindEEnum, ComponentKind.ACTOR_LITERAL);
		addEEnumLiteral(componentKindEEnum, ComponentKind.OTHER_LITERAL);

		// Create resource
		createResource(eNS_URI);
	}

} //UrncorePackageImpl
