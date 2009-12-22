package seg.jUCMNav.importexport.z151.unmarshal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;

import org.eclipse.emf.common.util.EList;

import seg.jUCMNav.importexport.z151.generated.ActiveResource;
import seg.jUCMNav.importexport.z151.generated.Actor;
import seg.jUCMNav.importexport.z151.generated.ActorRef;
import seg.jUCMNav.importexport.z151.generated.AndFork;
import seg.jUCMNav.importexport.z151.generated.AndJoin;
import seg.jUCMNav.importexport.z151.generated.ClosedWorkload;
import seg.jUCMNav.importexport.z151.generated.CollapsedActorRef;
import seg.jUCMNav.importexport.z151.generated.Comment;
import seg.jUCMNav.importexport.z151.generated.Component;
import seg.jUCMNav.importexport.z151.generated.ComponentBinding;
import seg.jUCMNav.importexport.z151.generated.ComponentRef;
import seg.jUCMNav.importexport.z151.generated.ComponentType;
import seg.jUCMNav.importexport.z151.generated.Concern;
import seg.jUCMNav.importexport.z151.generated.Condition;
import seg.jUCMNav.importexport.z151.generated.Connect;
import seg.jUCMNav.importexport.z151.generated.Contribution;
import seg.jUCMNav.importexport.z151.generated.ContributionType;
import seg.jUCMNav.importexport.z151.generated.Decomposition;
import seg.jUCMNav.importexport.z151.generated.DecompositionType;
import seg.jUCMNav.importexport.z151.generated.Demand;
import seg.jUCMNav.importexport.z151.generated.Dependency;
import seg.jUCMNav.importexport.z151.generated.DeviceKind;
import seg.jUCMNav.importexport.z151.generated.DirectionArrow;
import seg.jUCMNav.importexport.z151.generated.ElementLink;
import seg.jUCMNav.importexport.z151.generated.EmptyPoint;
import seg.jUCMNav.importexport.z151.generated.EndPoint;
import seg.jUCMNav.importexport.z151.generated.EnumerationType;
import seg.jUCMNav.importexport.z151.generated.Evaluation;
import seg.jUCMNav.importexport.z151.generated.EvaluationStrategy;
import seg.jUCMNav.importexport.z151.generated.ExternalOperation;
import seg.jUCMNav.importexport.z151.generated.GRLGraph;
import seg.jUCMNav.importexport.z151.generated.GRLLinkableElement;
import seg.jUCMNav.importexport.z151.generated.GRLNode;
import seg.jUCMNav.importexport.z151.generated.GRLmodelElement;
import seg.jUCMNav.importexport.z151.generated.GRLspec;
import seg.jUCMNav.importexport.z151.generated.GeneralResource;
import seg.jUCMNav.importexport.z151.generated.InBinding;
import seg.jUCMNav.importexport.z151.generated.Initialization;
import seg.jUCMNav.importexport.z151.generated.IntentionalElement;
import seg.jUCMNav.importexport.z151.generated.IntentionalElementRef;
import seg.jUCMNav.importexport.z151.generated.IntentionalElementType;
import seg.jUCMNav.importexport.z151.generated.Label;
import seg.jUCMNav.importexport.z151.generated.LinkRef;
import seg.jUCMNav.importexport.z151.generated.LinkRefBendpoint;
import seg.jUCMNav.importexport.z151.generated.Metadata;
import seg.jUCMNav.importexport.z151.generated.NodeConnection;
import seg.jUCMNav.importexport.z151.generated.OWPeriodic;
import seg.jUCMNav.importexport.z151.generated.OWPhaseType;
import seg.jUCMNav.importexport.z151.generated.OWPoisson;
import seg.jUCMNav.importexport.z151.generated.OWUniform;
import seg.jUCMNav.importexport.z151.generated.OrFork;
import seg.jUCMNav.importexport.z151.generated.OrJoin;
import seg.jUCMNav.importexport.z151.generated.OutBinding;
import seg.jUCMNav.importexport.z151.generated.PassiveResource;
import seg.jUCMNav.importexport.z151.generated.PathNode;
import seg.jUCMNav.importexport.z151.generated.PluginBinding;
import seg.jUCMNav.importexport.z151.generated.ProcessingResource;
import seg.jUCMNav.importexport.z151.generated.RespRef;
import seg.jUCMNav.importexport.z151.generated.Responsibility;
import seg.jUCMNav.importexport.z151.generated.ScenarioDef;
import seg.jUCMNav.importexport.z151.generated.ScenarioGroup;
import seg.jUCMNav.importexport.z151.generated.StartPoint;
import seg.jUCMNav.importexport.z151.generated.StrategiesGroup;
import seg.jUCMNav.importexport.z151.generated.Stub;
import seg.jUCMNav.importexport.z151.generated.TimeUnit;
import seg.jUCMNav.importexport.z151.generated.Timer;
import seg.jUCMNav.importexport.z151.generated.UCMmap;
import seg.jUCMNav.importexport.z151.generated.UCMmodelElement;
import seg.jUCMNav.importexport.z151.generated.UCMspec;
import seg.jUCMNav.importexport.z151.generated.URNlink;
import seg.jUCMNav.importexport.z151.generated.URNmodelElement;
import seg.jUCMNav.importexport.z151.generated.URNspec;
import seg.jUCMNav.importexport.z151.generated.Variable;
import seg.jUCMNav.importexport.z151.generated.WaitingPlace;
import seg.jUCMNav.model.ModelCreationFactory;

public abstract class EObjectImplUMHandler {

	public static urn.URNspec urn = null;
	protected static Map<Class<?>, EObjectImplUMHandler> ourClass2Conv = new HashMap<Class<?>, EObjectImplUMHandler>();
	protected static Map<String, Object> id2object = new HashMap<String, Object>();
	protected static String globelId = "0"; //$NON-NLS-1$

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
		// ourClass2Conv.put(Workload.class, new WorkloadUMHandler());
		ourClass2Conv.put(OWPeriodic.class, new WorkloadUMHandler());
		ourClass2Conv.put(OWPoisson.class, new WorkloadUMHandler());
		ourClass2Conv.put(OWUniform.class, new WorkloadUMHandler());
		ourClass2Conv.put(OWPhaseType.class, new WorkloadUMHandler());
		ourClass2Conv.put(ClosedWorkload.class, new WorkloadUMHandler());
		// ...
	}

	public void resetUrnSpec() {
		urn = null;
		id2object.clear();
		globelId = "0"; //$NON-NLS-1$
	}

	public String getObjectId(Object obj) {
		return "Z151_id_" + obj.getClass() + "_" + hashCode(obj); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public int hashCode(Object obj) {
		return obj.hashCode();
	};

	public abstract Object handle(Object obj, Object target, boolean isFullConstruction);

	protected Object process(Object obj, Object target, boolean isFullConstruction) {
		try {
			if (null != obj) {
				EObjectImplUMHandler h = ourClass2Conv.get(obj.getClass());
				if (null != h) {
					return h.handle(obj, target, isFullConstruction);
				} else {
					System.err.println(obj.getClass().getName() + " MHandler is UNDEFINED!"); //$NON-NLS-1$
				}
			}
		} catch (Exception e) {
			System.err.println(obj.getClass().getName() + " Exception"); //$NON-NLS-1$
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
						Object result = h.process(((JAXBElement) obj).getValue(), null, isFullConstruction);
						if (result != null && !targetList.contains(result)) {
							targetList.add(result);
						}
					} else {
						System.err.println(((JAXBElement) obj).getValue().getClass().getName() + " UMHandler is UNDEFINED!"); //$NON-NLS-1$
					}
				} else {
					h = this.getHandler(obj);
					if (null != h) {
						Object result = h.process(obj, null, isFullConstruction);
						if (result != null && !targetList.contains(result))
							targetList.add(result);
					} else {
						System.err.println(obj.getClass().getName() + " UNMHandler is UNDEFINED!"); //$NON-NLS-1$
					}
				}

			}
		} else {
			// System.out.println(this.getClass().getName() + " processList list is null or empty! ");
		}
	}

	protected grl.DecompositionType getDecompositionType(DecompositionType typeJ) {
		switch (typeJ) {
		case AND:
			return grl.DecompositionType.AND_LITERAL;
		case IOR:
			return grl.DecompositionType.OR_LITERAL;
			// //DecompositionType: jUCMNav has OR, but Z151 is IOR

		case XOR:
			return grl.DecompositionType.XOR_LITERAL;
		default:
			return grl.DecompositionType.AND_LITERAL;
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
	}

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

    //    <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
    //    <!--  TimeUnit  -->
    //    <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
    //            <xsd:enumeration value="year" />
    //            <xsd:enumeration value="day" />
    //            <xsd:enumeration value="h" />
    //            <xsd:enumeration value="s" />
    //            <xsd:enumeration value="ms" />
    //            <xsd:enumeration value="us" />
    //            <xsd:enumeration value="ns" />

    protected ucm.performance.TimeUnit getTimeUnit(TimeUnit typeJ) {
        switch (typeJ) {
        case MS: // Default situation sorted first...
            return ucm.performance.TimeUnit.MS_LITERAL;
        case YEAR:
            return ucm.performance.TimeUnit.YEAR_LITERAL;
        case DAY:
            return ucm.performance.TimeUnit.DAY_LITERAL;
        case H:
            return ucm.performance.TimeUnit.H_LITERAL;
        case S:
            return ucm.performance.TimeUnit.S_LITERAL;
        case US:
            return ucm.performance.TimeUnit.US_LITERAL;
        case NS:
            return ucm.performance.TimeUnit.NS_LITERAL;
        default:
            return ucm.performance.TimeUnit.MS_LITERAL;
        }
    }

	protected EObjectImplUMHandler getHandler(Object obj) {
		return ourClass2Conv.get(obj.getClass());
	}

	protected Object getObjectFromId(String id, Class type) {
		Object obj = EObjectImplUMHandler.id2object.get(id);
		if (obj == null) {
			obj = ModelCreationFactory.getNewObject(urn, type);
			EObjectImplUMHandler.id2object.put(id, obj);
		}
		return obj;
	}

	protected Object getObject(String objId, Object target, Class type) {
		Object elem = null;
		elem=id2object.get(objId);
		if (null == elem) {
			if (null == target) {
				elem = ModelCreationFactory.getNewObject(urn, type);
				try {
					int value = Integer.parseInt(objId);
					if (Integer.valueOf(globelId) < Integer.valueOf(objId))
						globelId = Integer.toString(value);
				} catch (NumberFormatException e) {
				}
			} else
				elem = target;
			id2object.put(objId, elem);
		}
		return elem;
	}
}
