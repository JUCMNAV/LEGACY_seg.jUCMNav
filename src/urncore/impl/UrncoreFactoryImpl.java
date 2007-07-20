/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import urncore.*;

import urncore.Component;
import urncore.ComponentKind;
import urncore.ComponentLabel;
import urncore.ComponentType;
import urncore.Concern;
import urncore.Condition;
import urncore.DynamicRespKind;
import urncore.DynamicResponsibility;
import urncore.Metadata;
import urncore.NodeLabel;
import urncore.Pool;
import urncore.Responsibility;
import urncore.URNdefinition;
import urncore.UrncoreFactory;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class UrncoreFactoryImpl extends EFactoryImpl implements UrncoreFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static UrncoreFactory init() {
        try {
            UrncoreFactory theUrncoreFactory = (UrncoreFactory)EPackage.Registry.INSTANCE.getEFactory("http:///urncore.ecore"); 
            if (theUrncoreFactory != null) {
                return theUrncoreFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new UrncoreFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UrncoreFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case UrncorePackage.UR_NDEFINITION: return createURNdefinition();
            case UrncorePackage.RESPONSIBILITY: return createResponsibility();
            case UrncorePackage.POOL: return createPool();
            case UrncorePackage.COMPONENT: return createComponent();
            case UrncorePackage.COMPONENT_TYPE: return createComponentType();
            case UrncorePackage.DYNAMIC_RESPONSIBILITY: return createDynamicResponsibility();
            case UrncorePackage.NODE_LABEL: return createNodeLabel();
            case UrncorePackage.COMPONENT_LABEL: return createComponentLabel();
            case UrncorePackage.CONDITION: return createCondition();
            case UrncorePackage.METADATA: return createMetadata();
            case UrncorePackage.CONCERN: return createConcern();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
            case UrncorePackage.COMPONENT_KIND:
                return createComponentKindFromString(eDataType, initialValue);
            case UrncorePackage.DYNAMIC_RESP_KIND:
                return createDynamicRespKindFromString(eDataType, initialValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
            case UrncorePackage.COMPONENT_KIND:
                return convertComponentKindToString(eDataType, instanceValue);
            case UrncorePackage.DYNAMIC_RESP_KIND:
                return convertDynamicRespKindToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public URNdefinition createURNdefinition() {
        URNdefinitionImpl urNdefinition = new URNdefinitionImpl();
        return urNdefinition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Responsibility createResponsibility() {
        ResponsibilityImpl responsibility = new ResponsibilityImpl();
        return responsibility;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Pool createPool() {
        PoolImpl pool = new PoolImpl();
        return pool;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Component createComponent() {
        ComponentImpl component = new ComponentImpl();
        return component;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ComponentType createComponentType() {
        ComponentTypeImpl componentType = new ComponentTypeImpl();
        return componentType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DynamicResponsibility createDynamicResponsibility() {
        DynamicResponsibilityImpl dynamicResponsibility = new DynamicResponsibilityImpl();
        return dynamicResponsibility;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NodeLabel createNodeLabel() {
        NodeLabelImpl nodeLabel = new NodeLabelImpl();
        return nodeLabel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ComponentLabel createComponentLabel() {
        ComponentLabelImpl componentLabel = new ComponentLabelImpl();
        return componentLabel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Condition createCondition() {
        ConditionImpl condition = new ConditionImpl();
        return condition;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Metadata createMetadata() {
        MetadataImpl metadata = new MetadataImpl();
        return metadata;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Concern createConcern() {
        ConcernImpl concern = new ConcernImpl();
        return concern;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ComponentKind createComponentKindFromString(EDataType eDataType, String initialValue) {
        ComponentKind result = ComponentKind.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String convertComponentKindToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public DynamicRespKind createDynamicRespKindFromString(EDataType eDataType, String initialValue) {
        DynamicRespKind result = DynamicRespKind.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String convertDynamicRespKindToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UrncorePackage getUrncorePackage() {
        return (UrncorePackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    public static UrncorePackage getPackage() {
        return UrncorePackage.eINSTANCE;
    }

} //UrncoreFactoryImpl
