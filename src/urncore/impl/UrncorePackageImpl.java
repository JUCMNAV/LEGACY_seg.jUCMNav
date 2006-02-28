/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore.impl;

import grl.GrlPackage;

import grl.impl.GrlPackageImpl;

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

import urncore.Component;
import urncore.ComponentElement;
import urncore.ComponentKind;
import urncore.ComponentLabel;
import urncore.ComponentRegular;
import urncore.ComponentType;
import urncore.Condition;
import urncore.DynamicRespKind;
import urncore.DynamicResponsibility;
import urncore.GRLmodelElement;
import urncore.Label;
import urncore.NodeLabel;
import urncore.Pool;
import urncore.Responsibility;
import urncore.IURNContainer;
import urncore.IURNContainerRef;
import urncore.IURNConnection;
import urncore.IURNDiagram;
import urncore.IURNNode;
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
    private EClass componentRegularEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass componentElementEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass poolEClass = null;

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
    private EClass dynamicResponsibilityEClass = null;

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
    private EEnum componentKindEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum dynamicRespKindEEnum = null;

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
     * Creates, registers, and initializes the <b>Package</b> for this
     * model, and for any others upon which it depends.  Simple
     * dependencies are satisfied by calling this method on all
     * dependent packages before doing anything else.  This method drives
     * initialization for interdependent packages directly, in parallel
     * with this package, itself.
     * <p>Of this package and its interdependencies, all packages which
     * have not yet been registered by their URI values are first created
     * and registered.  The packages are then initialized in two steps:
     * meta-model objects for all of the packages are created before any
     * are initialized, since one package's meta-model objects may refer to
     * those of another.
     * <p>Invocation of this method will not affect any packages that have
     * already been initialized.
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
        UrncorePackageImpl theUrncorePackage = (UrncorePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof UrncorePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new UrncorePackageImpl());

        isInited = true;

        // Obtain or create and register interdependencies
        UrnPackageImpl theUrnPackage = (UrnPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UrnPackage.eNS_URI) instanceof UrnPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UrnPackage.eNS_URI) : UrnPackage.eINSTANCE);
        GrlPackageImpl theGrlPackage = (GrlPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(GrlPackage.eNS_URI) instanceof GrlPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(GrlPackage.eNS_URI) : GrlPackage.eINSTANCE);
        UcmPackageImpl theUcmPackage = (UcmPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UcmPackage.eNS_URI) instanceof UcmPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UcmPackage.eNS_URI) : UcmPackage.eINSTANCE);
        PerformancePackageImpl thePerformancePackage = (PerformancePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PerformancePackage.eNS_URI) instanceof PerformancePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PerformancePackage.eNS_URI) : PerformancePackage.eINSTANCE);
        MapPackageImpl theMapPackage = (MapPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(MapPackage.eNS_URI) instanceof MapPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(MapPackage.eNS_URI) : MapPackage.eINSTANCE);
        ScenarioPackageImpl theScenarioPackage = (ScenarioPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI) instanceof ScenarioPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI) : ScenarioPackage.eINSTANCE);

        // Create package meta-data objects
        theUrncorePackage.createPackageContents();
        theUrnPackage.createPackageContents();
        theGrlPackage.createPackageContents();
        theUcmPackage.createPackageContents();
        thePerformancePackage.createPackageContents();
        theMapPackage.createPackageContents();
        theScenarioPackage.createPackageContents();

        // Initialize created meta-data
        theUrncorePackage.initializePackageContents();
        theUrnPackage.initializePackageContents();
        theGrlPackage.initializePackageContents();
        theUcmPackage.initializePackageContents();
        thePerformancePackage.initializePackageContents();
        theMapPackage.initializePackageContents();
        theScenarioPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theUrncorePackage.freeze();

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
        return (EReference)urNdefinitionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getURNdefinition_SpecDiagrams() {
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
    public EReference getResponsibility_Urndefinition() {
        return (EReference)responsibilityEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getResponsibility_Demands() {
        return (EReference)responsibilityEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getResponsibility_RespRefs() {
        return (EReference)responsibilityEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getComponentRegular() {
        return componentRegularEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getComponentRegular_Kind() {
        return (EAttribute)componentRegularEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getComponentRegular_Protected() {
        return (EAttribute)componentRegularEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getComponentRegular_Slot() {
        return (EAttribute)componentRegularEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getComponentRegular_IncludedComponent() {
        return (EReference)componentRegularEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getComponentRegular_Host() {
        return (EReference)componentRegularEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getComponentElement() {
        return componentElementEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getComponentElement_Urndefinition() {
        return (EReference)componentElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getComponentElement_IncludingComponent() {
        return (EReference)componentElementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getComponentElement_Resource() {
        return (EReference)componentElementEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getPool() {
        return poolEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getPool_OfComponents() {
        return (EAttribute)poolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getPool_Content() {
        return (EAttribute)poolEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getPool_ComponentType() {
        return (EReference)poolEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getPool_DynResponsibilities() {
        return (EReference)poolEClass.getEStructuralFeatures().get(3);
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
    public EReference getComponent_Type() {
        return (EReference)componentEClass.getEStructuralFeatures().get(0);
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
    public EReference getComponentType_SubType() {
        return (EReference)componentTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getComponentType_SuperType() {
        return (EReference)componentTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getComponentType_Instances() {
        return (EReference)componentTypeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getComponentType_Pools() {
        return (EReference)componentTypeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDynamicResponsibility() {
        return dynamicResponsibilityEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDynamicResponsibility_Kind() {
        return (EAttribute)dynamicResponsibilityEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDynamicResponsibility_ToPath() {
        return (EAttribute)dynamicResponsibilityEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDynamicResponsibility_ArrowLength() {
        return (EAttribute)dynamicResponsibilityEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDynamicResponsibility_Pool() {
        return (EReference)dynamicResponsibilityEClass.getEStructuralFeatures().get(3);
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
    public EReference getUCMmodelElement_Urnlinks() {
        return (EReference)ucMmodelElementEClass.getEStructuralFeatures().get(0);
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
    public EReference getGRLmodelElement_Urnlinks() {
        return (EReference)grLmodelElementEClass.getEStructuralFeatures().get(0);
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
    public EReference getCondition_Variables() {
        return (EReference)conditionEClass.getEStructuralFeatures().get(7);
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
    public EClass getURNmodelElement() {
        return urNmodelElementEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getURNmodelElement_Id() {
        return (EAttribute)urNmodelElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getURNmodelElement_Name() {
        return (EAttribute)urNmodelElementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getURNmodelElement_Description() {
        return (EAttribute)urNmodelElementEClass.getEStructuralFeatures().get(2);
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
    public EEnum getComponentKind() {
        return componentKindEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getDynamicRespKind() {
        return dynamicRespKindEEnum;
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
        createEReference(urNdefinitionEClass, UR_NDEFINITION__COMPONENTS);
        createEReference(urNdefinitionEClass, UR_NDEFINITION__SPEC_DIAGRAMS);

        responsibilityEClass = createEClass(RESPONSIBILITY);
        createEAttribute(responsibilityEClass, RESPONSIBILITY__EMPTY);
        createEReference(responsibilityEClass, RESPONSIBILITY__URNDEFINITION);
        createEReference(responsibilityEClass, RESPONSIBILITY__DEMANDS);
        createEReference(responsibilityEClass, RESPONSIBILITY__RESP_REFS);

        componentRegularEClass = createEClass(COMPONENT_REGULAR);
        createEAttribute(componentRegularEClass, COMPONENT_REGULAR__KIND);
        createEAttribute(componentRegularEClass, COMPONENT_REGULAR__PROTECTED);
        createEAttribute(componentRegularEClass, COMPONENT_REGULAR__SLOT);
        createEReference(componentRegularEClass, COMPONENT_REGULAR__INCLUDED_COMPONENT);
        createEReference(componentRegularEClass, COMPONENT_REGULAR__HOST);

        componentElementEClass = createEClass(COMPONENT_ELEMENT);
        createEReference(componentElementEClass, COMPONENT_ELEMENT__URNDEFINITION);
        createEReference(componentElementEClass, COMPONENT_ELEMENT__INCLUDING_COMPONENT);
        createEReference(componentElementEClass, COMPONENT_ELEMENT__RESOURCE);

        poolEClass = createEClass(POOL);
        createEAttribute(poolEClass, POOL__OF_COMPONENTS);
        createEAttribute(poolEClass, POOL__CONTENT);
        createEReference(poolEClass, POOL__COMPONENT_TYPE);
        createEReference(poolEClass, POOL__DYN_RESPONSIBILITIES);

        componentEClass = createEClass(COMPONENT);
        createEReference(componentEClass, COMPONENT__TYPE);

        componentTypeEClass = createEClass(COMPONENT_TYPE);
        createEReference(componentTypeEClass, COMPONENT_TYPE__SUB_TYPE);
        createEReference(componentTypeEClass, COMPONENT_TYPE__SUPER_TYPE);
        createEReference(componentTypeEClass, COMPONENT_TYPE__INSTANCES);
        createEReference(componentTypeEClass, COMPONENT_TYPE__POOLS);

        dynamicResponsibilityEClass = createEClass(DYNAMIC_RESPONSIBILITY);
        createEAttribute(dynamicResponsibilityEClass, DYNAMIC_RESPONSIBILITY__KIND);
        createEAttribute(dynamicResponsibilityEClass, DYNAMIC_RESPONSIBILITY__TO_PATH);
        createEAttribute(dynamicResponsibilityEClass, DYNAMIC_RESPONSIBILITY__ARROW_LENGTH);
        createEReference(dynamicResponsibilityEClass, DYNAMIC_RESPONSIBILITY__POOL);

        ucMmodelElementEClass = createEClass(UC_MMODEL_ELEMENT);
        createEReference(ucMmodelElementEClass, UC_MMODEL_ELEMENT__URNLINKS);

        grLmodelElementEClass = createEClass(GR_LMODEL_ELEMENT);
        createEReference(grLmodelElementEClass, GR_LMODEL_ELEMENT__URNLINKS);

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
        createEReference(conditionEClass, CONDITION__VARIABLES);

        iurnDiagramEClass = createEClass(IURN_DIAGRAM);
        createEReference(iurnDiagramEClass, IURN_DIAGRAM__URNDEFINITION);
        createEReference(iurnDiagramEClass, IURN_DIAGRAM__NODES);
        createEReference(iurnDiagramEClass, IURN_DIAGRAM__CONT_REFS);
        createEReference(iurnDiagramEClass, IURN_DIAGRAM__CONNECTIONS);

        urNmodelElementEClass = createEClass(UR_NMODEL_ELEMENT);
        createEAttribute(urNmodelElementEClass, UR_NMODEL_ELEMENT__ID);
        createEAttribute(urNmodelElementEClass, UR_NMODEL_ELEMENT__NAME);
        createEAttribute(urNmodelElementEClass, UR_NMODEL_ELEMENT__DESCRIPTION);

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

        // Create enums
        componentKindEEnum = createEEnum(COMPONENT_KIND);
        dynamicRespKindEEnum = createEEnum(DYNAMIC_RESP_KIND);
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
        UrnPackageImpl theUrnPackage = (UrnPackageImpl)EPackage.Registry.INSTANCE.getEPackage(UrnPackage.eNS_URI);
        PerformancePackageImpl thePerformancePackage = (PerformancePackageImpl)EPackage.Registry.INSTANCE.getEPackage(PerformancePackage.eNS_URI);
        MapPackageImpl theMapPackage = (MapPackageImpl)EPackage.Registry.INSTANCE.getEPackage(MapPackage.eNS_URI);
        ScenarioPackageImpl theScenarioPackage = (ScenarioPackageImpl)EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI);

        // Add supertypes to classes
        responsibilityEClass.getESuperTypes().add(this.getUCMmodelElement());
        componentRegularEClass.getESuperTypes().add(this.getComponentElement());
        componentElementEClass.getESuperTypes().add(this.getUCMmodelElement());
        componentElementEClass.getESuperTypes().add(this.getIURNContainer());
        poolEClass.getESuperTypes().add(this.getComponentElement());
        componentEClass.getESuperTypes().add(this.getComponentRegular());
        componentTypeEClass.getESuperTypes().add(this.getComponentRegular());
        dynamicResponsibilityEClass.getESuperTypes().add(this.getResponsibility());
        ucMmodelElementEClass.getESuperTypes().add(this.getURNmodelElement());
        grLmodelElementEClass.getESuperTypes().add(this.getURNmodelElement());
        nodeLabelEClass.getESuperTypes().add(this.getLabel());
        componentLabelEClass.getESuperTypes().add(this.getLabel());
        conditionEClass.getESuperTypes().add(this.getLabel());

        // Initialize classes and features; add operations and parameters
        initEClass(urNdefinitionEClass, URNdefinition.class, "URNdefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getURNdefinition_Urnspec(), theUrnPackage.getURNspec(), theUrnPackage.getURNspec_Urndef(), "urnspec", null, 1, 1, URNdefinition.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getURNdefinition_Responsibilities(), this.getResponsibility(), this.getResponsibility_Urndefinition(), "responsibilities", null, 0, -1, URNdefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getURNdefinition_Components(), this.getComponentElement(), this.getComponentElement_Urndefinition(), "components", null, 0, -1, URNdefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getURNdefinition_SpecDiagrams(), this.getIURNDiagram(), this.getIURNDiagram_Urndefinition(), "specDiagrams", null, 0, -1, URNdefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(responsibilityEClass, Responsibility.class, "Responsibility", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getResponsibility_Empty(), ecorePackage.getEBoolean(), "empty", "false", 0, 1, Responsibility.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getResponsibility_Urndefinition(), this.getURNdefinition(), this.getURNdefinition_Responsibilities(), "urndefinition", null, 1, 1, Responsibility.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getResponsibility_Demands(), thePerformancePackage.getDemand(), thePerformancePackage.getDemand_Responsibility(), "demands", null, 0, -1, Responsibility.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getResponsibility_RespRefs(), theMapPackage.getRespRef(), theMapPackage.getRespRef_RespDef(), "respRefs", null, 1, -1, Responsibility.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(componentRegularEClass, ComponentRegular.class, "ComponentRegular", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getComponentRegular_Kind(), this.getComponentKind(), "kind", null, 0, 1, ComponentRegular.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getComponentRegular_Protected(), ecorePackage.getEBoolean(), "protected", "false", 0, 1, ComponentRegular.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getComponentRegular_Slot(), ecorePackage.getEBoolean(), "slot", "false", 0, 1, ComponentRegular.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getComponentRegular_IncludedComponent(), this.getComponentElement(), this.getComponentElement_IncludingComponent(), "includedComponent", null, 0, -1, ComponentRegular.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getComponentRegular_Host(), thePerformancePackage.getProcessingResource(), thePerformancePackage.getProcessingResource_Components(), "host", null, 0, 1, ComponentRegular.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(componentElementEClass, ComponentElement.class, "ComponentElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getComponentElement_Urndefinition(), this.getURNdefinition(), this.getURNdefinition_Components(), "urndefinition", null, 1, 1, ComponentElement.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getComponentElement_IncludingComponent(), this.getComponentRegular(), this.getComponentRegular_IncludedComponent(), "includingComponent", null, 0, 1, ComponentElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getComponentElement_Resource(), thePerformancePackage.getPassiveResource(), thePerformancePackage.getPassiveResource_Component(), "resource", null, 0, 1, ComponentElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(poolEClass, Pool.class, "Pool", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getPool_OfComponents(), ecorePackage.getEBoolean(), "ofComponents", "false", 0, 1, Pool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getPool_Content(), ecorePackage.getEString(), "content", null, 0, 1, Pool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPool_ComponentType(), this.getComponentType(), this.getComponentType_Pools(), "componentType", null, 0, 1, Pool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPool_DynResponsibilities(), this.getDynamicResponsibility(), this.getDynamicResponsibility_Pool(), "dynResponsibilities", null, 0, -1, Pool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(componentEClass, Component.class, "Component", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getComponent_Type(), this.getComponentType(), this.getComponentType_Instances(), "type", null, 0, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(componentTypeEClass, ComponentType.class, "ComponentType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getComponentType_SubType(), this.getComponentType(), this.getComponentType_SuperType(), "subType", null, 0, -1, ComponentType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getComponentType_SuperType(), this.getComponentType(), this.getComponentType_SubType(), "superType", null, 0, 1, ComponentType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getComponentType_Instances(), this.getComponent(), this.getComponent_Type(), "instances", null, 0, -1, ComponentType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getComponentType_Pools(), this.getPool(), this.getPool_ComponentType(), "pools", null, 0, -1, ComponentType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(dynamicResponsibilityEClass, DynamicResponsibility.class, "DynamicResponsibility", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDynamicResponsibility_Kind(), this.getDynamicRespKind(), "kind", null, 0, 1, DynamicResponsibility.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDynamicResponsibility_ToPath(), ecorePackage.getEBoolean(), "toPath", null, 0, 1, DynamicResponsibility.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDynamicResponsibility_ArrowLength(), ecorePackage.getEInt(), "arrowLength", null, 0, 1, DynamicResponsibility.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDynamicResponsibility_Pool(), this.getPool(), this.getPool_DynResponsibilities(), "pool", null, 0, 1, DynamicResponsibility.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(ucMmodelElementEClass, UCMmodelElement.class, "UCMmodelElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getUCMmodelElement_Urnlinks(), theUrnPackage.getURNlink(), theUrnPackage.getURNlink_UcmModelElements(), "urnlinks", null, 0, -1, UCMmodelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(grLmodelElementEClass, GRLmodelElement.class, "GRLmodelElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getGRLmodelElement_Urnlinks(), theUrnPackage.getURNlink(), theUrnPackage.getURNlink_GrlModelElements(), "urnlinks", null, 0, -1, GRLmodelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
        initEReference(getCondition_Variables(), theScenarioPackage.getVariable(), theScenarioPackage.getVariable_Usages(), "variables", null, 0, -1, Condition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(iurnDiagramEClass, IURNDiagram.class, "IURNDiagram", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getIURNDiagram_Urndefinition(), this.getURNdefinition(), this.getURNdefinition_SpecDiagrams(), "urndefinition", null, 1, 1, IURNDiagram.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getIURNDiagram_Nodes(), this.getIURNNode(), this.getIURNNode_Diagram(), "nodes", null, 0, -1, IURNDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getIURNDiagram_ContRefs(), this.getIURNContainerRef(), this.getIURNContainerRef_Diagram(), "contRefs", null, 0, -1, IURNDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getIURNDiagram_Connections(), this.getIURNConnection(), this.getIURNConnection_Diagram(), "connections", null, 0, -1, IURNDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(urNmodelElementEClass, URNmodelElement.class, "URNmodelElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getURNmodelElement_Id(), ecorePackage.getEString(), "id", null, 0, 1, URNmodelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getURNmodelElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, URNmodelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getURNmodelElement_Description(), ecorePackage.getEString(), "description", null, 0, 1, URNmodelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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

        // Initialize enums and add enum literals
        initEEnum(componentKindEEnum, ComponentKind.class, "ComponentKind");
        addEEnumLiteral(componentKindEEnum, ComponentKind.TEAM_LITERAL);
        addEEnumLiteral(componentKindEEnum, ComponentKind.OBJECT_LITERAL);
        addEEnumLiteral(componentKindEEnum, ComponentKind.PROCESS_LITERAL);
        addEEnumLiteral(componentKindEEnum, ComponentKind.AGENT_LITERAL);
        addEEnumLiteral(componentKindEEnum, ComponentKind.ACTOR_LITERAL);
        addEEnumLiteral(componentKindEEnum, ComponentKind.OTHER_LITERAL);

        initEEnum(dynamicRespKindEEnum, DynamicRespKind.class, "DynamicRespKind");
        addEEnumLiteral(dynamicRespKindEEnum, DynamicRespKind.MOVE_LITERAL);
        addEEnumLiteral(dynamicRespKindEEnum, DynamicRespKind.MOVE_STAY_LITERAL);
        addEEnumLiteral(dynamicRespKindEEnum, DynamicRespKind.CREATE_LITERAL);
        addEEnumLiteral(dynamicRespKindEEnum, DynamicRespKind.COPY_LITERAL);
        addEEnumLiteral(dynamicRespKindEEnum, DynamicRespKind.DESTROY_LITERAL);

        // Create resource
        createResource(eNS_URI);
    }

} //UrncorePackageImpl
