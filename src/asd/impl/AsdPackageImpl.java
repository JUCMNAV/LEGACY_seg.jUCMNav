/**
 */
package asd.impl;

import asd.ASDelement;
import asd.ASDiagram;
import asd.ASDlayout;
import asd.ASDmodelElement;
import asd.ASDspec;
import asd.ASNetwork;
import asd.AsdFactory;
import asd.AsdPackage;
import asd.Community;
import asd.DivisionOfLabour;
import asd.MediatedElement;
import asd.MediatingElement;
import asd.Mediation;
import asd.Motivation;
import asd.Outcome;
import asd.Rule;
import asd.Subject;
import asd.Tool;
import ca.mcgill.sel.core.CorePackage;
import fm.FmPackage;
import fm.impl.FmPackageImpl;
import grl.GrlPackage;
import grl.impl.GrlPackageImpl;
import grl.kpimodel.KpimodelPackage;
import grl.kpimodel.impl.KpimodelPackageImpl;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
import urncore.UrncorePackage;
import urncore.impl.UrncorePackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AsdPackageImpl extends EPackageImpl implements AsdPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass asDspecEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass asDelementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass asDiagramEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mediatingElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mediationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mediatedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass asDmodelElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass asNetworkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass asDlayoutEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass toolEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ruleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass divisionOfLabourEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass communityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass subjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass objectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass motivationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass outcomeEClass = null;

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
	 * @see asd.AsdPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private AsdPackageImpl() {
		super(eNS_URI, AsdFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link AsdPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static AsdPackage init() {
		if (isInited) return (AsdPackage)EPackage.Registry.INSTANCE.getEPackage(AsdPackage.eNS_URI);

		// Obtain or create and register package
		AsdPackageImpl theAsdPackage = (AsdPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof AsdPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new AsdPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		CorePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		FmPackageImpl theFmPackage = (FmPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(FmPackage.eNS_URI) instanceof FmPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(FmPackage.eNS_URI) : FmPackage.eINSTANCE);
		GrlPackageImpl theGrlPackage = (GrlPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(GrlPackage.eNS_URI) instanceof GrlPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(GrlPackage.eNS_URI) : GrlPackage.eINSTANCE);
		KpimodelPackageImpl theKpimodelPackage = (KpimodelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(KpimodelPackage.eNS_URI) instanceof KpimodelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(KpimodelPackage.eNS_URI) : KpimodelPackage.eINSTANCE);
		UrncorePackageImpl theUrncorePackage = (UrncorePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UrncorePackage.eNS_URI) instanceof UrncorePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UrncorePackage.eNS_URI) : UrncorePackage.eINSTANCE);
		UrnPackageImpl theUrnPackage = (UrnPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UrnPackage.eNS_URI) instanceof UrnPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UrnPackage.eNS_URI) : UrnPackage.eINSTANCE);
		UcmPackageImpl theUcmPackage = (UcmPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UcmPackage.eNS_URI) instanceof UcmPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UcmPackage.eNS_URI) : UcmPackage.eINSTANCE);
		PerformancePackageImpl thePerformancePackage = (PerformancePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PerformancePackage.eNS_URI) instanceof PerformancePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PerformancePackage.eNS_URI) : PerformancePackage.eINSTANCE);
		MapPackageImpl theMapPackage = (MapPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(MapPackage.eNS_URI) instanceof MapPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(MapPackage.eNS_URI) : MapPackage.eINSTANCE);
		ScenarioPackageImpl theScenarioPackage = (ScenarioPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI) instanceof ScenarioPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI) : ScenarioPackage.eINSTANCE);

		// Create package meta-data objects
		theAsdPackage.createPackageContents();
		theFmPackage.createPackageContents();
		theGrlPackage.createPackageContents();
		theKpimodelPackage.createPackageContents();
		theUrncorePackage.createPackageContents();
		theUrnPackage.createPackageContents();
		theUcmPackage.createPackageContents();
		thePerformancePackage.createPackageContents();
		theMapPackage.createPackageContents();
		theScenarioPackage.createPackageContents();

		// Initialize created meta-data
		theAsdPackage.initializePackageContents();
		theFmPackage.initializePackageContents();
		theGrlPackage.initializePackageContents();
		theKpimodelPackage.initializePackageContents();
		theUrncorePackage.initializePackageContents();
		theUrnPackage.initializePackageContents();
		theUcmPackage.initializePackageContents();
		thePerformancePackage.initializePackageContents();
		theMapPackage.initializePackageContents();
		theScenarioPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theAsdPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(AsdPackage.eNS_URI, theAsdPackage);
		return theAsdPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getASDspec() {
		return asDspecEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getASDspec_ASNetwork() {
		return (EReference)asDspecEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getASDspec_Motivation() {
		return (EReference)asDspecEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getASDspec_Outcome() {
		return (EReference)asDspecEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getASDspec_Dols() {
		return (EReference)asDspecEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getASDspec_Subjects() {
		return (EReference)asDspecEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getASDspec_Communities() {
		return (EReference)asDspecEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getASDspec_Objects() {
		return (EReference)asDspecEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getASDspec_Outcomes() {
		return (EReference)asDspecEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getASDspec_Motivations() {
		return (EReference)asDspecEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getASDspec_Tools() {
		return (EReference)asDspecEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getASDspec_Rules() {
		return (EReference)asDspecEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getASDelement() {
		return asDelementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getASDelement_Diagrams() {
		return (EReference)asDelementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getASDelement_ParentElements() {
		return (EReference)asDelementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getASDelement_RefinedElements() {
		return (EReference)asDelementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getASDelement_RequiredOutcomes() {
		return (EReference)asDelementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getASDiagram() {
		return asDiagramEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getASDiagram_Elements() {
		return (EReference)asDiagramEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getASDiagram_ASD() {
		return (EReference)asDiagramEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getASDiagram_RelatedASD() {
		return (EReference)asDiagramEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getASDiagram_Mediations() {
		return (EReference)asDiagramEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getASDiagram_AsdLayouts() {
		return (EReference)asDiagramEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getASDiagram_ParentDoLs() {
		return (EReference)asDiagramEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMediatingElement() {
		return mediatingElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMediatingElement_Mediations() {
		return (EReference)mediatingElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMediation() {
		return mediationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMediation_MediatedBy() {
		return (EReference)mediationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMediation_Mediates() {
		return (EReference)mediationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMediation_RelevantASD() {
		return (EReference)mediationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMediatedElement() {
		return mediatedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMediatedElement_Mediations() {
		return (EReference)mediatedElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getASDmodelElement() {
		return asDmodelElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getASNetwork() {
		return asNetworkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getASNetwork_ASDspec() {
		return (EReference)asNetworkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getASNetwork_AsdLayouts() {
		return (EReference)asNetworkEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getASDlayout() {
		return asDlayoutEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getASDlayout_X() {
		return (EAttribute)asDlayoutEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getASDlayout_Y() {
		return (EAttribute)asDlayoutEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getASDlayout_Width() {
		return (EAttribute)asDlayoutEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getASDlayout_Height() {
		return (EAttribute)asDlayoutEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getASDlayout_Collapsed() {
		return (EAttribute)asDlayoutEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getASDlayout_AsNetwork() {
		return (EReference)asDlayoutEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getASDlayout_AsDiagram() {
		return (EReference)asDlayoutEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTool() {
		return toolEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTool_AsdSpec() {
		return (EReference)toolEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRule() {
		return ruleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRule_Dols() {
		return (EReference)ruleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRule_AsdSpec() {
		return (EReference)ruleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDivisionOfLabour() {
		return divisionOfLabourEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDivisionOfLabour_Rules() {
		return (EReference)divisionOfLabourEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDivisionOfLabour_RefinedDiagrams() {
		return (EReference)divisionOfLabourEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDivisionOfLabour_PerformedBy() {
		return (EReference)divisionOfLabourEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDivisionOfLabour_AsdSpec() {
		return (EReference)divisionOfLabourEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCommunity() {
		return communityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCommunity_Performs() {
		return (EReference)communityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCommunity_AsdSpec() {
		return (EReference)communityEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCommunity_Subject() {
		return (EReference)communityEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSubject() {
		return subjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSubject_AsdSpec() {
		return (EReference)subjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSubject_MemberOf() {
		return (EReference)subjectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSubject_Objects() {
		return (EReference)subjectEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getObject() {
		return objectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getObject_AsdSpec() {
		return (EReference)objectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getObject_Outcomes() {
		return (EReference)objectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getObject_Subjects() {
		return (EReference)objectEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMotivation() {
		return motivationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMotivation_AsdSpec() {
		return (EReference)motivationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOutcome() {
		return outcomeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOutcome_EnabledElements() {
		return (EReference)outcomeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOutcome_AsdSpec() {
		return (EReference)outcomeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOutcome_Objects() {
		return (EReference)outcomeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AsdFactory getAsdFactory() {
		return (AsdFactory)getEFactoryInstance();
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
		asDspecEClass = createEClass(AS_DSPEC);
		createEReference(asDspecEClass, AS_DSPEC__AS_NETWORK);
		createEReference(asDspecEClass, AS_DSPEC__MOTIVATION);
		createEReference(asDspecEClass, AS_DSPEC__OUTCOME);
		createEReference(asDspecEClass, AS_DSPEC__DOLS);
		createEReference(asDspecEClass, AS_DSPEC__SUBJECTS);
		createEReference(asDspecEClass, AS_DSPEC__COMMUNITIES);
		createEReference(asDspecEClass, AS_DSPEC__OBJECTS);
		createEReference(asDspecEClass, AS_DSPEC__OUTCOMES);
		createEReference(asDspecEClass, AS_DSPEC__MOTIVATIONS);
		createEReference(asDspecEClass, AS_DSPEC__TOOLS);
		createEReference(asDspecEClass, AS_DSPEC__RULES);

		asDelementEClass = createEClass(AS_DELEMENT);
		createEReference(asDelementEClass, AS_DELEMENT__DIAGRAMS);
		createEReference(asDelementEClass, AS_DELEMENT__PARENT_ELEMENTS);
		createEReference(asDelementEClass, AS_DELEMENT__REFINED_ELEMENTS);
		createEReference(asDelementEClass, AS_DELEMENT__REQUIRED_OUTCOMES);

		asDiagramEClass = createEClass(AS_DIAGRAM);
		createEReference(asDiagramEClass, AS_DIAGRAM__ELEMENTS);
		createEReference(asDiagramEClass, AS_DIAGRAM__ASD);
		createEReference(asDiagramEClass, AS_DIAGRAM__RELATED_ASD);
		createEReference(asDiagramEClass, AS_DIAGRAM__MEDIATIONS);
		createEReference(asDiagramEClass, AS_DIAGRAM__ASD_LAYOUTS);
		createEReference(asDiagramEClass, AS_DIAGRAM__PARENT_DO_LS);

		mediatingElementEClass = createEClass(MEDIATING_ELEMENT);
		createEReference(mediatingElementEClass, MEDIATING_ELEMENT__MEDIATIONS);

		mediationEClass = createEClass(MEDIATION);
		createEReference(mediationEClass, MEDIATION__MEDIATED_BY);
		createEReference(mediationEClass, MEDIATION__MEDIATES);
		createEReference(mediationEClass, MEDIATION__RELEVANT_ASD);

		mediatedElementEClass = createEClass(MEDIATED_ELEMENT);
		createEReference(mediatedElementEClass, MEDIATED_ELEMENT__MEDIATIONS);

		asDmodelElementEClass = createEClass(AS_DMODEL_ELEMENT);

		asNetworkEClass = createEClass(AS_NETWORK);
		createEReference(asNetworkEClass, AS_NETWORK__AS_DSPEC);
		createEReference(asNetworkEClass, AS_NETWORK__ASD_LAYOUTS);

		asDlayoutEClass = createEClass(AS_DLAYOUT);
		createEAttribute(asDlayoutEClass, AS_DLAYOUT__X);
		createEAttribute(asDlayoutEClass, AS_DLAYOUT__Y);
		createEAttribute(asDlayoutEClass, AS_DLAYOUT__WIDTH);
		createEAttribute(asDlayoutEClass, AS_DLAYOUT__HEIGHT);
		createEAttribute(asDlayoutEClass, AS_DLAYOUT__COLLAPSED);
		createEReference(asDlayoutEClass, AS_DLAYOUT__AS_NETWORK);
		createEReference(asDlayoutEClass, AS_DLAYOUT__AS_DIAGRAM);

		toolEClass = createEClass(TOOL);
		createEReference(toolEClass, TOOL__ASD_SPEC);

		ruleEClass = createEClass(RULE);
		createEReference(ruleEClass, RULE__DOLS);
		createEReference(ruleEClass, RULE__ASD_SPEC);

		divisionOfLabourEClass = createEClass(DIVISION_OF_LABOUR);
		createEReference(divisionOfLabourEClass, DIVISION_OF_LABOUR__RULES);
		createEReference(divisionOfLabourEClass, DIVISION_OF_LABOUR__REFINED_DIAGRAMS);
		createEReference(divisionOfLabourEClass, DIVISION_OF_LABOUR__PERFORMED_BY);
		createEReference(divisionOfLabourEClass, DIVISION_OF_LABOUR__ASD_SPEC);

		communityEClass = createEClass(COMMUNITY);
		createEReference(communityEClass, COMMUNITY__PERFORMS);
		createEReference(communityEClass, COMMUNITY__ASD_SPEC);
		createEReference(communityEClass, COMMUNITY__SUBJECT);

		subjectEClass = createEClass(SUBJECT);
		createEReference(subjectEClass, SUBJECT__ASD_SPEC);
		createEReference(subjectEClass, SUBJECT__MEMBER_OF);
		createEReference(subjectEClass, SUBJECT__OBJECTS);

		objectEClass = createEClass(OBJECT);
		createEReference(objectEClass, OBJECT__ASD_SPEC);
		createEReference(objectEClass, OBJECT__OUTCOMES);
		createEReference(objectEClass, OBJECT__SUBJECTS);

		motivationEClass = createEClass(MOTIVATION);
		createEReference(motivationEClass, MOTIVATION__ASD_SPEC);

		outcomeEClass = createEClass(OUTCOME);
		createEReference(outcomeEClass, OUTCOME__ENABLED_ELEMENTS);
		createEReference(outcomeEClass, OUTCOME__ASD_SPEC);
		createEReference(outcomeEClass, OUTCOME__OBJECTS);
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
		UrncorePackage theUrncorePackage = (UrncorePackage)EPackage.Registry.INSTANCE.getEPackage(UrncorePackage.eNS_URI);

		// Add supertypes to classes
		asDelementEClass.getESuperTypes().add(this.getASDmodelElement());
		asDelementEClass.getESuperTypes().add(theUrncorePackage.getURNmodelElement());
		asDiagramEClass.getESuperTypes().add(this.getASDmodelElement());
		asDiagramEClass.getESuperTypes().add(theUrncorePackage.getURNmodelElement());
		asDiagramEClass.getESuperTypes().add(theUrncorePackage.getIURNDiagram());
		mediatingElementEClass.getESuperTypes().add(this.getASDelement());
		mediationEClass.getESuperTypes().add(this.getASDmodelElement());
		mediationEClass.getESuperTypes().add(theUrncorePackage.getURNmodelElement());
		mediatedElementEClass.getESuperTypes().add(this.getASDelement());
		asDmodelElementEClass.getESuperTypes().add(theUrncorePackage.getURNmodelElement());
		asNetworkEClass.getESuperTypes().add(theUrncorePackage.getIURNDiagram());
		toolEClass.getESuperTypes().add(this.getMediatingElement());
		ruleEClass.getESuperTypes().add(this.getMediatingElement());
		divisionOfLabourEClass.getESuperTypes().add(this.getMediatingElement());
		communityEClass.getESuperTypes().add(this.getMediatedElement());
		subjectEClass.getESuperTypes().add(this.getMediatedElement());
		objectEClass.getESuperTypes().add(this.getMediatedElement());
		motivationEClass.getESuperTypes().add(this.getASDelement());
		outcomeEClass.getESuperTypes().add(this.getASDelement());

		// Initialize classes and features; add operations and parameters
		initEClass(asDspecEClass, ASDspec.class, "ASDspec", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getASDspec_ASNetwork(), this.getASNetwork(), this.getASNetwork_ASDspec(), "ASNetwork", null, 0, -1, ASDspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getASDspec_Motivation(), this.getMotivation(), null, "Motivation", null, 0, -1, ASDspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getASDspec_Outcome(), this.getOutcome(), null, "Outcome", null, 0, -1, ASDspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getASDspec_Dols(), this.getDivisionOfLabour(), this.getDivisionOfLabour_AsdSpec(), "dols", null, 0, -1, ASDspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getASDspec_Subjects(), this.getSubject(), this.getSubject_AsdSpec(), "subjects", null, 0, -1, ASDspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getASDspec_Communities(), this.getCommunity(), this.getCommunity_AsdSpec(), "communities", null, 0, -1, ASDspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getASDspec_Objects(), this.getObject(), this.getObject_AsdSpec(), "objects", null, 0, -1, ASDspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getASDspec_Outcomes(), this.getOutcome(), this.getOutcome_AsdSpec(), "outcomes", null, 0, -1, ASDspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getASDspec_Motivations(), this.getMotivation(), this.getMotivation_AsdSpec(), "motivations", null, 0, -1, ASDspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getASDspec_Tools(), this.getTool(), this.getTool_AsdSpec(), "tools", null, 0, -1, ASDspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getASDspec_Rules(), this.getRule(), this.getRule_AsdSpec(), "rules", null, 0, -1, ASDspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(asDelementEClass, ASDelement.class, "ASDelement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getASDelement_Diagrams(), this.getASDiagram(), this.getASDiagram_Elements(), "diagrams", null, 0, -1, ASDelement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getASDelement_ParentElements(), this.getASDelement(), this.getASDelement_RefinedElements(), "parentElements", null, 0, -1, ASDelement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getASDelement_RefinedElements(), this.getASDelement(), this.getASDelement_ParentElements(), "refinedElements", null, 0, -1, ASDelement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getASDelement_RequiredOutcomes(), this.getOutcome(), this.getOutcome_EnabledElements(), "requiredOutcomes", null, 0, -1, ASDelement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(asDiagramEClass, ASDiagram.class, "ASDiagram", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getASDiagram_Elements(), this.getASDelement(), this.getASDelement_Diagrams(), "elements", null, 7, -1, ASDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getASDiagram_ASD(), this.getASDiagram(), this.getASDiagram_RelatedASD(), "ASD", null, 0, -1, ASDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getASDiagram_RelatedASD(), this.getASDiagram(), this.getASDiagram_ASD(), "relatedASD", null, 0, -1, ASDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getASDiagram_Mediations(), this.getMediation(), this.getMediation_RelevantASD(), "mediations", null, 0, -1, ASDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getASDiagram_AsdLayouts(), this.getASDlayout(), this.getASDlayout_AsDiagram(), "asdLayouts", null, 0, -1, ASDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getASDiagram_ParentDoLs(), this.getDivisionOfLabour(), this.getDivisionOfLabour_RefinedDiagrams(), "parentDoLs", null, 0, -1, ASDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(mediatingElementEClass, MediatingElement.class, "MediatingElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMediatingElement_Mediations(), this.getMediation(), this.getMediation_MediatedBy(), "mediations", null, 0, -1, MediatingElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(mediationEClass, Mediation.class, "Mediation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMediation_MediatedBy(), this.getMediatingElement(), this.getMediatingElement_Mediations(), "mediatedBy", null, 1, 1, Mediation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMediation_Mediates(), this.getMediatedElement(), this.getMediatedElement_Mediations(), "mediates", null, 2, 2, Mediation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMediation_RelevantASD(), this.getASDiagram(), this.getASDiagram_Mediations(), "relevantASD", null, 1, 1, Mediation.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(mediatedElementEClass, MediatedElement.class, "MediatedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMediatedElement_Mediations(), this.getMediation(), this.getMediation_Mediates(), "mediations", null, 0, -1, MediatedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(asDmodelElementEClass, ASDmodelElement.class, "ASDmodelElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(asNetworkEClass, ASNetwork.class, "ASNetwork", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getASNetwork_ASDspec(), this.getASDspec(), this.getASDspec_ASNetwork(), "ASDspec", null, 0, -1, ASNetwork.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getASNetwork_AsdLayouts(), this.getASDlayout(), this.getASDlayout_AsNetwork(), "asdLayouts", null, 0, -1, ASNetwork.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(asDlayoutEClass, ASDlayout.class, "ASDlayout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getASDlayout_X(), ecorePackage.getEInt(), "x", null, 0, 1, ASDlayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getASDlayout_Y(), ecorePackage.getEInt(), "y", null, 0, 1, ASDlayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getASDlayout_Width(), ecorePackage.getEInt(), "width", null, 0, 1, ASDlayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getASDlayout_Height(), ecorePackage.getEInt(), "height", null, 0, 1, ASDlayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getASDlayout_Collapsed(), ecorePackage.getEBoolean(), "collapsed", "false", 0, 1, ASDlayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getASDlayout_AsNetwork(), this.getASNetwork(), this.getASNetwork_AsdLayouts(), "asNetwork", null, 1, 1, ASDlayout.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getASDlayout_AsDiagram(), this.getASDiagram(), this.getASDiagram_AsdLayouts(), "asDiagram", null, 1, 1, ASDlayout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(toolEClass, Tool.class, "Tool", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTool_AsdSpec(), this.getASDspec(), this.getASDspec_Tools(), "asdSpec", null, 1, 1, Tool.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ruleEClass, Rule.class, "Rule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRule_Dols(), this.getDivisionOfLabour(), this.getDivisionOfLabour_Rules(), "dols", null, 1, -1, Rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRule_AsdSpec(), this.getASDspec(), this.getASDspec_Rules(), "asdSpec", null, 1, 1, Rule.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(divisionOfLabourEClass, DivisionOfLabour.class, "DivisionOfLabour", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDivisionOfLabour_Rules(), this.getRule(), this.getRule_Dols(), "rules", null, 0, -1, DivisionOfLabour.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDivisionOfLabour_RefinedDiagrams(), this.getASDiagram(), this.getASDiagram_ParentDoLs(), "refinedDiagrams", null, 0, -1, DivisionOfLabour.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDivisionOfLabour_PerformedBy(), this.getCommunity(), this.getCommunity_Performs(), "performedBy", null, 1, 1, DivisionOfLabour.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDivisionOfLabour_AsdSpec(), this.getASDspec(), this.getASDspec_Dols(), "asdSpec", null, 1, 1, DivisionOfLabour.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(communityEClass, Community.class, "Community", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCommunity_Performs(), this.getDivisionOfLabour(), this.getDivisionOfLabour_PerformedBy(), "performs", null, 0, -1, Community.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCommunity_AsdSpec(), this.getASDspec(), this.getASDspec_Communities(), "asdSpec", null, 1, 1, Community.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCommunity_Subject(), this.getSubject(), this.getSubject_MemberOf(), "subject", null, 0, 1, Community.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(subjectEClass, Subject.class, "Subject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSubject_AsdSpec(), this.getASDspec(), this.getASDspec_Subjects(), "asdSpec", null, 1, 1, Subject.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSubject_MemberOf(), this.getCommunity(), this.getCommunity_Subject(), "memberOf", null, 1, 1, Subject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSubject_Objects(), this.getObject(), this.getObject_Subjects(), "objects", null, 1, -1, Subject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(objectEClass, asd.Object.class, "Object", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getObject_AsdSpec(), this.getASDspec(), this.getASDspec_Objects(), "asdSpec", null, 1, 1, asd.Object.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getObject_Outcomes(), this.getOutcome(), this.getOutcome_Objects(), "outcomes", null, 0, -1, asd.Object.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getObject_Subjects(), this.getSubject(), this.getSubject_Objects(), "subjects", null, 1, -1, asd.Object.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(motivationEClass, Motivation.class, "Motivation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMotivation_AsdSpec(), this.getASDspec(), this.getASDspec_Motivations(), "asdSpec", null, 1, 1, Motivation.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(outcomeEClass, Outcome.class, "Outcome", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOutcome_EnabledElements(), this.getASDelement(), this.getASDelement_RequiredOutcomes(), "enabledElements", null, 0, -1, Outcome.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOutcome_AsdSpec(), this.getASDspec(), this.getASDspec_Outcomes(), "asdSpec", null, 1, 1, Outcome.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOutcome_Objects(), this.getObject(), this.getObject_Outcomes(), "objects", null, 0, -1, Outcome.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //AsdPackageImpl
