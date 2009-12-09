package seg.jUCMNav.importexport.z151.unmarshal;

import org.eclipse.emf.common.util.EList;

import seg.jUCMNav.importexport.z151.generated.*;
import seg.jUCMNav.model.ModelCreationFactory;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;

public abstract class EObjectImplUMHandler {

	public static urn.URNspec urn = null;
	protected static Map<Class<?>, EObjectImplUMHandler> ourClass2Conv = new HashMap<Class<?>, EObjectImplUMHandler>();
	protected static Map<String, Object> id2object = new HashMap<String, Object>();
	protected static String globelId = "0"; 
	
	static {
		ourClass2Conv.put(ActiveResource.class, new ActiveResourceUMHandler());
		ourClass2Conv.put(ActorRef.class, new ActorRefUMHandler());
		ourClass2Conv.put(Actor.class, new ActorUMHandler());
		ourClass2Conv.put(AndFork.class, new AndForkUMHandler());
		ourClass2Conv.put(AndJoin.class, new AndJoinUMHandler());
		ourClass2Conv.put(CollapsedActorRef.class, new CollapsedActorRefUMHandler());
		ourClass2Conv.put(Comment.class, new CommentUMHandler());
		ourClass2Conv.put(ComponentBinding.class, new ComponentBindingUMHandler());
		ourClass2Conv.put(ComponentRef.class, new ComponentRefUMHandler());
		ourClass2Conv.put(ComponentType.class, new ComponentTypeUMHandler());
		ourClass2Conv.put(Component.class, new ComponentUMHandler());
		ourClass2Conv.put(Concern.class, new ConcernUMHandler());
		ourClass2Conv.put(Condition.class, new ConditionUMHandler());
		ourClass2Conv.put(Connect.class, new ConnectUMHandler());
		ourClass2Conv.put(Contribution.class, new ContributionUMHandler());
		ourClass2Conv.put(Decomposition.class, new DecompositionUMHandler());
		ourClass2Conv.put(Demand.class, new DemandUMHandler());
		ourClass2Conv.put(Dependency.class, new DependencyUMHandler());
		ourClass2Conv.put(DirectionArrow.class, new DirectionArrowUMHandler());
		ourClass2Conv.put(ElementLink.class, new ElementLinkUMHandler());
		ourClass2Conv.put(EmptyPoint.class, new EmptyPointUMHandler());
		ourClass2Conv.put(EndPoint.class, new EndPointUMHandler());
		ourClass2Conv.put(EnumerationType.class, new EnumerationTypeUMHandler());
		// ourClass2Conv.put( EObjectImpl.class, new EObjectImplUMHandler( ));
		ourClass2Conv.put(EvaluationStrategy.class, new EvaluationStrategyUMHandler());
		ourClass2Conv.put(Evaluation.class, new EvaluationUMHandler());
		ourClass2Conv.put(ExternalOperation.class, new ExternalOperationUMHandler());
		ourClass2Conv.put(GeneralResource.class, new GeneralResourceUMHandler());
		ourClass2Conv.put(GRLGraph.class, new GRLGraphUMHandler());
		ourClass2Conv.put(GRLLinkableElement.class, new GRLLinkableElementUMHandler());
		ourClass2Conv.put(GRLmodelElement.class, new GRLmodelElementUMHandler());
		ourClass2Conv.put(GRLNode.class, new GRLNodeUMHandler());
		ourClass2Conv.put(GRLspec.class, new GRLspecUMHandler());
		ourClass2Conv.put(InBinding.class, new InBindingUMHandler());
		ourClass2Conv.put(Initialization.class, new InitializationUMHandler());
		ourClass2Conv.put(IntentionalElementRef.class, new IntentionalElementRefUMHandler());
		ourClass2Conv.put(IntentionalElement.class, new IntentionalElementUMHandler());
		ourClass2Conv.put(Label.class, new LabelUMHandler());
		ourClass2Conv.put(LinkRefBendpoint.class, new LinkRefBendpointUMHandler());
		ourClass2Conv.put(LinkRef.class, new LinkRefUMHandler());
		ourClass2Conv.put(Metadata.class, new MetadataUMHandler());
		ourClass2Conv.put(NodeConnection.class, new NodeConnectionUMHandler());
		ourClass2Conv.put(OrFork.class, new OrForkUMHandler());
		ourClass2Conv.put(OrJoin.class, new OrJoinUMHandler());
		ourClass2Conv.put(OutBinding.class, new OutBindingUMHandler());
		ourClass2Conv.put(PassiveResource.class, new PassiveResourceUMHandler());
		ourClass2Conv.put(PathNode.class, new PathNodeUMHandler());
		ourClass2Conv.put(PluginBinding.class, new PluginBindingUMHandler());
		ourClass2Conv.put(ProcessingResource.class, new ProcessingResourceUMHandler());
		ourClass2Conv.put(Responsibility.class, new ResponsibilityUMHandler());
		ourClass2Conv.put(RespRef.class, new RespRefUMHandler());
		ourClass2Conv.put(ScenarioDef.class, new ScenarioDefUMHandler());
		ourClass2Conv.put(ScenarioGroup.class, new ScenarioGroupUMHandler());
		ourClass2Conv.put(StartPoint.class, new StartPointUMHandler());
		ourClass2Conv.put(StrategiesGroup.class, new StrategiesGroupUMHandler());
		ourClass2Conv.put(Stub.class, new StubUMHandler());
		ourClass2Conv.put(Timer.class, new TimerUMHandler());
		ourClass2Conv.put(UCMmap.class, new UCMmapUMHandler());
		ourClass2Conv.put(UCMmodelElement.class, new UCMmodelElementUMHandler());
		ourClass2Conv.put(UCMspec.class, new UCMspecUMHandler());
		ourClass2Conv.put(URNlink.class, new URNlinkUMHandler());
		ourClass2Conv.put(URNmodelElement.class, new URNmodelElementUMHandler());
		ourClass2Conv.put(URNspec.class, new URNspecUMHandler());
		ourClass2Conv.put(Variable.class, new VariableUMHandler());
		ourClass2Conv.put(WaitingPlace.class, new WaitingPlaceUMHandler());
		//ourClass2Conv.put(Workload.class, new WorkloadUMHandler());
		ourClass2Conv.put(OWPeriodic.class, new WorkloadUMHandler());
		ourClass2Conv.put(OWPoisson.class, new WorkloadUMHandler());
		ourClass2Conv.put(OWUniform.class, new WorkloadUMHandler());
		ourClass2Conv.put(OWPhaseType.class, new WorkloadUMHandler());
		ourClass2Conv.put(ClosedWorkload.class, new WorkloadUMHandler());
		// ...
	}
	
	public void resetUrnSpec(){
		urn = null;
		id2object.clear();
		globelId = "0"; 
	}
	public String getObjectId(Object obj) {
		return "Z151_id_" + obj.getClass() + "_" + hashCode(obj);
	}

	public int hashCode(Object obj) {
		return obj.hashCode();
	};

	public abstract Object handle(Object obj, Object target, boolean isFullConstruction);

	protected Object process(Object obj, Object target, boolean isFullConstruction) {
		try{
		if (null != obj) {
			EObjectImplUMHandler h = ourClass2Conv.get(obj.getClass());
			if (null != h) {
				return h.handle(obj, target, isFullConstruction);
			} else {
				System.err.println(obj.getClass().getName()+" MHandler is UNDEFINED!");
			}
		}
		}catch(Exception e){
			System.err.println(obj.getClass().getName()+" Exception");
		}
		return null;
	}

	protected <V> void processList(List<V> list, EList targetList, boolean isFullConstruction) {
		if (list != null && list.size() > 0) {
			for (Object obj : list) {
				EObjectImplUMHandler h = null;
				if (obj instanceof JAXBElement) {
					h = this.getHandler(((JAXBElement) obj).getValue());
					if (null != h) {
						Object result =h.process(((JAXBElement) obj).getValue(), null, isFullConstruction);
						if (result!=null && !targetList.contains(result)) {
							targetList.add(result);
						}
					} else {
						System.err.println(((JAXBElement) obj).getValue().getClass().getName()+" UMHandler is UNDEFINED!");
					}
				} else {
					h = this.getHandler(obj);
					if (null != h) {
						Object result = h.process(obj, null, isFullConstruction);
						if (result!=null && !targetList.contains(result)) targetList.add(result);
					} else {
						System.err.println(obj.getClass().getName()+" UNMHandler is UNDEFINED!");
					}
				}

			}
		} else {
			System.out.println(this.getClass().getName()+" processList list is null or empty! " );
		}
	}

	protected grl.DecompositionType getDecompositionType(DecompositionType typeJ) {
		switch (typeJ) {
		case AND:
			return grl.DecompositionType.AND_LITERAL;
		case IOR: return grl.DecompositionType.OR_LITERAL;
			// //DecompositionType: jUCMNav has OR, but Z151 is IOR
		
		case XOR:
			return grl.DecompositionType.XOR_LITERAL;
		default:
			return null; //grl.DecompositionType.AND_LITERAL;
		}
	}

	// <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
	// <!-- IntentionalElementType -->
	// <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
	// <xsd:simpleType name="IntentionalElementType">
	// <xsd:restriction base="xsd:string">
	// <xsd:enumeration value="Softgoal"/>
	// <xsd:enumeration value="Goal"/>
	// <xsd:enumeration value="Task"/>
	// <xsd:enumeration value="Resource"/>
	// <xsd:enumeration value="Belief"/>
	// </xsd:restriction>
	// </xsd:simpleType>
	protected grl.IntentionalElementType getIntentionalElementType(IntentionalElementType typeJ) {
		switch (typeJ) {
		case SOFTGOAL:
			return grl.IntentionalElementType.SOFTGOAL_LITERAL;
		case GOAL:
			return grl.IntentionalElementType.GOAL_LITERAL;
		case TASK:
			return grl.IntentionalElementType.TASK_LITERAL;
		case RESOURCE:
			return grl.IntentionalElementType.RESSOURCE_LITERAL;
		case BELIEF:
			return null;// IntentionalElementType: jNCMNav has Indicator, but
						// Z151 has Belief
		default:
			return null;
		}
	}

	// protected seg.jUCMNav.importexport.z151.generated.ImportanceType
	// getImportanceType(grl.ImportanceType typeJ){
	// switch (typeJ.getValue()){
	// case 0: return
	// seg.jUCMNav.importexport.z151.generated.ImportanceType.HIGH;
	// case 1: return
	// seg.jUCMNav.importexport.z151.generated.ImportanceType.MEDIUM;
	// case 2: return
	// seg.jUCMNav.importexport.z151.generated.ImportanceType.LOW;
	// case 3: return
	// seg.jUCMNav.importexport.z151.generated.ImportanceType.NONE;
	// default: return
	// seg.jUCMNav.importexport.z151.generated.ImportanceType.NONE;// null;
	// }
	//		
	// }

	// <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
	// <!-- ComponentKind -->
	// <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
	// <xsd:simpleType name="ComponentKind">
	// <xsd:restriction base="xsd:string">
	// <xsd:enumeration value="Team"/>
	// <xsd:enumeration value="Object"/>
	// <xsd:enumeration value="Process"/>
	// <xsd:enumeration value="Agent"/>
	// <xsd:enumeration value="Actor"/>
	// </xsd:restriction>
	// </xsd:simpleType>
	/* jUCMNav has ComponentKind.OTHER but Z151 does not have */

	protected urncore.ComponentKind getComponentKind(seg.jUCMNav.importexport.z151.generated.ComponentKind typeJ) {
		switch (typeJ) {
		case TEAM:
			return urncore.ComponentKind.TEAM_LITERAL;
		case OBJECT:
			return urncore.ComponentKind.OBJECT_LITERAL;
		case PROCESS:
			return urncore.ComponentKind.PROCESS_LITERAL;
		case AGENT:
			return urncore.ComponentKind.AGENT_LITERAL;
		case ACTOR:
			return urncore.ComponentKind.ACTOR_LITERAL;
		}
		return null;
	}

	// <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
	// <!-- QualitativeLabel -->
	// <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
	// <xsd:simpleType name="QualitativeLabel">
	// <xsd:restriction base="xsd:string">
	// <xsd:enumeration value="Denied"/>
	// <xsd:enumeration value="WeaklyDenied"/>
	// <xsd:enumeration value="WeaklySatisfied"/>
	// <xsd:enumeration value="Satisfied"/>
	// <xsd:enumeration value="Conflict"/>
	// <xsd:enumeration value="Unknown"/>
	// <xsd:enumeration value="None"/>
	// </xsd:restriction>
	// </xsd:simpleType>

	// protected grl.QualitativeLabel getQualitativeEvaluation(QualitativeLabel
	// typeJ){
	// switch (typeJ.getValue()){
	// case 0: return
	// seg.jUCMNav.importexport.z151.generated.QualitativeLabel.DENIED;
	// case 1: return
	// seg.jUCMNav.importexport.z151.generated.QualitativeLabel.WEAKLY_DENIED;
	// case 2: return
	// seg.jUCMNav.importexport.z151.generated.QualitativeLabel.WEAKLY_SATISFIED;
	// case 3: return
	// seg.jUCMNav.importexport.z151.generated.QualitativeLabel.SATISFIED;
	// case 4: return
	// seg.jUCMNav.importexport.z151.generated.QualitativeLabel.CONFLICT;
	// case 5: return
	// seg.jUCMNav.importexport.z151.generated.QualitativeLabel.UNKNOWN;
	// case 6: return
	// seg.jUCMNav.importexport.z151.generated.QualitativeLabel.NONE;
	// default: return
	// seg.jUCMNav.importexport.z151.generated.QualitativeLabel.NONE;
	// }
	// }

	// <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
	// <!-- ContributionType -->
	// <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
	// <xsd:simpleType name="ContributionType">
	// <xsd:restriction base="xsd:string">
	// <xsd:enumeration value="Make"/>
	// <xsd:enumeration value="Help"/>
	// <xsd:enumeration value="SomePositive"/>
	// <xsd:enumeration value="Unknown"/>
	// <xsd:enumeration value="SomeNegative"/>
	// <xsd:enumeration value="Hurt"/>
	// <xsd:enumeration value="Break"/>
	// </xsd:restriction>
	// </xsd:simpleType>
	protected grl.ContributionType getContributionType(ContributionType typeJ) {
		return grl.ContributionType.get(typeJ.ordinal());
		// switch (typeJ.ordinal()){
		// case 0: return grl.ContributionType.MAKE_LITERAL;
		// case 1: return grl.ContributionType.HELP_LITERAL;
		// case 2: return grl.ContributionType.SOME_POSITIVE_LITERAL;
		// case 3: return grl.ContributionType.UNKNOWN_LITERAL;
		// case 4: return grl.ContributionType.SOME_NEGATIVE_LITERAL;
		// case 5: return grl.ContributionType.HURT_LITERAL;
		// case 6: return grl.ContributionType.BREAK_LITERAL;
		// default: return grl.ContributionType.UNKNOWN_LITERAL;
		// }
	}

	// <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
	// <!-- DatatypeKind -->
	// <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
	// <xsd:simpleType name="DatatypeKind">
	// <xsd:restriction base="xsd:string">
	// <xsd:enumeration value="Boolean"/>
	// <xsd:enumeration value="Integer"/>
	// <xsd:enumeration value="Enumeration"/>
	// </xsd:restriction>
	// Use DatatypeKind.fromValue(String)

	// <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
	// <!-- DeviceKind -->
	// <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
	// <xsd:simpleType name="DeviceKind">
	// <xsd:restriction base="xsd:string">
	// <xsd:enumeration value="Processor"/>
	// <xsd:enumeration value="Disk"/>
	// <xsd:enumeration value="DSP"/>
	// </xsd:restriction>
	// </xsd:simpleType>

	protected ucm.performance.DeviceKind getDeviceKind(DeviceKind typeJ) {
		switch (typeJ) {
		case PROCESSOR:
			return ucm.performance.DeviceKind.PROCESSOR_LITERAL;
		case DISK:
			return ucm.performance.DeviceKind.DISK_LITERAL;
		case DSP:
			return ucm.performance.DeviceKind.DSP_LITERAL;
		default:
			return ucm.performance.DeviceKind.OTHER_LITERAL;
		}
	}

	// <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
	// <!-- WaitKind -->
	// <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
	// <xsd:simpleType name="WaitKind">
	// <xsd:restriction base="xsd:string">
	// <xsd:enumeration value="Transient"/>
	// <xsd:enumeration value="Persistent"/>
	// </xsd:restriction>
	// </xsd:simpleType>

	// protected WaitKind getWaitKind(ucm.map.WaitKind typeJ){
	// switch (typeJ.getValue()){
	// case 0: return
	// seg.jUCMNav.importexport.z151.generated.WaitKind.TRANSIENT;
	// case 1: return
	// seg.jUCMNav.importexport.z151.generated.WaitKind.PERSISTENT;
	// default: return null;
	// }
	// }

	protected EObjectImplUMHandler getHandler(Object obj) {
		return ourClass2Conv.get(obj.getClass());
	}
	
	protected Object getObjectFromId(String id, Class type){
		Object obj = this.id2object.get(id);
		if (obj == null){
			obj = ModelCreationFactory.getNewObject(urn, type);
			this.id2object.put(id, obj);
		}
		return obj;
	}
}
