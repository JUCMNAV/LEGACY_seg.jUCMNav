/**
 */
package asd.impl;

import asd.ASDiagram;
import asd.ASDlayout;
import asd.ASDspec;
import asd.ASNetwork;
import asd.AsdFactory;
import asd.AsdPackage;
import asd.Community;
import asd.DivisionOfLabour;
import asd.Mediation;
import asd.Motivation;
import asd.Outcome;
import asd.Rule;
import asd.Subject;
import asd.Tool;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AsdFactoryImpl extends EFactoryImpl implements AsdFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AsdFactory init() {
		try {
			AsdFactory theAsdFactory = (AsdFactory)EPackage.Registry.INSTANCE.getEFactory(AsdPackage.eNS_URI);
			if (theAsdFactory != null) {
				return theAsdFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new AsdFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AsdFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case AsdPackage.AS_DSPEC: return createASDspec();
			case AsdPackage.AS_DIAGRAM: return createASDiagram();
			case AsdPackage.MEDIATION: return createMediation();
			case AsdPackage.AS_NETWORK: return createASNetwork();
			case AsdPackage.AS_DLAYOUT: return createASDlayout();
			case AsdPackage.TOOL: return createTool();
			case AsdPackage.RULE: return createRule();
			case AsdPackage.DIVISION_OF_LABOUR: return createDivisionOfLabour();
			case AsdPackage.COMMUNITY: return createCommunity();
			case AsdPackage.SUBJECT: return createSubject();
			case AsdPackage.OBJECT: return createObject();
			case AsdPackage.MOTIVATION: return createMotivation();
			case AsdPackage.OUTCOME: return createOutcome();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ASDspec createASDspec() {
		ASDspecImpl asDspec = new ASDspecImpl();
		return asDspec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ASDiagram createASDiagram() {
		ASDiagramImpl asDiagram = new ASDiagramImpl();
		return asDiagram;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Mediation createMediation() {
		MediationImpl mediation = new MediationImpl();
		return mediation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ASNetwork createASNetwork() {
		ASNetworkImpl asNetwork = new ASNetworkImpl();
		return asNetwork;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ASDlayout createASDlayout() {
		ASDlayoutImpl asDlayout = new ASDlayoutImpl();
		return asDlayout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Tool createTool() {
		ToolImpl tool = new ToolImpl();
		return tool;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Rule createRule() {
		RuleImpl rule = new RuleImpl();
		return rule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DivisionOfLabour createDivisionOfLabour() {
		DivisionOfLabourImpl divisionOfLabour = new DivisionOfLabourImpl();
		return divisionOfLabour;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Community createCommunity() {
		CommunityImpl community = new CommunityImpl();
		return community;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Subject createSubject() {
		SubjectImpl subject = new SubjectImpl();
		return subject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public asd.Object createObject() {
		ObjectImpl object = new ObjectImpl();
		return object;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Motivation createMotivation() {
		MotivationImpl motivation = new MotivationImpl();
		return motivation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Outcome createOutcome() {
		OutcomeImpl outcome = new OutcomeImpl();
		return outcome;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AsdPackage getAsdPackage() {
		return (AsdPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static AsdPackage getPackage() {
		return AsdPackage.eINSTANCE;
	}

} //AsdFactoryImpl
