package seg.jUCMNav.importexport.z151.marshal;

import org.eclipse.emf.common.util.EList;
import seg.jUCMNav.importexport.z151.generated.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBElement;

public abstract class MHandler {
	protected static seg.jUCMNav.importexport.z151.generated.ObjectFactory of = new seg.jUCMNav.importexport.z151.generated.ObjectFactory();
    protected static seg.jUCMNav.importexport.z151.generated.URNspec urnZ = null;
    protected static Map<Class<?>,MHandler> ourClass2Conv =
        new HashMap<Class<?>,MHandler>();
    protected static Map<String,Object> id2object = new HashMap<String,Object>();
    	
    static {
//    	ourClass2Conv.put( ucm.performance.impl.ActiveResourceImpl.class, new ActiveResourceMHandler());
//    	ourClass2Conv.put( grl.Actor.class, new ActorMHandler());
//    	ourClass2Conv.put( grl.ActorRef.class, new ActorRefMHandler());
//    	ourClass2Conv.put( ucm.map.AndFork.class, new AndForkMHandler());
//    	ourClass2Conv.put( ucm.map.AndJoin.class, new AndJoinMHandler());
//    	ourClass2Conv.put( grl.CollapsedActorRef.class, new CollapsedActorRefMHandler());
//    	ourClass2Conv.put( urncore.Comment.class, new CommentMHandler());
//    	ourClass2Conv.put( ucm.map.ComponentBinding.class, new ComponentBindingMHandler());
//    	ourClass2Conv.put( urncore.Component.class, new ComponentMHandler());
//    	ourClass2Conv.put( ucm.map.ComponentRef.class, new ComponentRefMHandler());
//    	ourClass2Conv.put( urncore.ComponentType.class, new ComponentTypeMHandler());
//    	ourClass2Conv.put( urncore.Concern.class, new ConcernMHandler());
//    	ourClass2Conv.put( urncore.Condition.class, new ConditionMHandler());
//    	ourClass2Conv.put( ucm.map.Connect.class, new ConnectMHandler());
//    	ourClass2Conv.put( grl.Contribution.class, new ContributionMHandler());
//    	ourClass2Conv.put( grl.Decomposition.class, new DecompositionMHandler());
//    	ourClass2Conv.put( ucm.performance.Demand.class, new DemandMHandler());
//    	ourClass2Conv.put( grl.Dependency.class, new DependencyMHandler());
//    	ourClass2Conv.put( ucm.map.DirectionArrow.class, new DirectionArrowMHandler());
//    	ourClass2Conv.put( grl.ElementLink.class, new ElementLinkMHandler());
//    	ourClass2Conv.put( ucm.map.EmptyPoint.class, new EmptyPointMHandler());
//    	ourClass2Conv.put( ucm.map.EndPoint.class, new EndPointMHandler());
//    	ourClass2Conv.put( ucm.scenario.EnumerationType.class, new EnumerationTypeMHandler());
//    	ourClass2Conv.put( grl.Evaluation.class, new EvaluationMHandler());
//    	ourClass2Conv.put( grl.EvaluationStrategy.class, new EvaluationStrategyMHandler());
//    	ourClass2Conv.put( ucm.performance.ExternalOperation.class, new ExternalOperationMHandler());
//    	ourClass2Conv.put( ucm.performance.GeneralResource.class, new GeneralResourceMHandler());
//    	ourClass2Conv.put( grl.GRLGraph.class, new GRLGraphMHandler());
//    	ourClass2Conv.put( grl.GRLLinkableElement.class, new GRLLinkableElementMHandler());
//    	//ourClass2Conv.put( grl.GRLmodelElement.class, new GRLmodelElementMHandler());**
//    	//ourClass2Conv.put( grl.GRLNode.class, new GRLNodeMHandler());**
//    	ourClass2Conv.put( grl.GRLspec.class, new GRLspecMHandler());
//    	ourClass2Conv.put( ucm.map.InBinding.class, new InBindingMHandler());
//    	ourClass2Conv.put( ucm.scenario.Initialization.class, new InitializationMHandler());
//    	ourClass2Conv.put( grl.IntentionalElement.class, new IntentionalElementMHandler());
//    	ourClass2Conv.put( grl.IntentionalElementRef.class, new IntentionalElementRefMHandler());
//    	//ourClass2Conv.put( IURNConnection.class, new IURNConnectionMHandler());**
//    	ourClass2Conv.put( grl.LinkRefBendpoint.class, new LinkRefBendpointMHandler());
//    	ourClass2Conv.put( grl.LinkRef.class, new LinkRefMHandler());
//    	ourClass2Conv.put( urncore.Metadata.class, new MetadataMHandler());
//    	ourClass2Conv.put( ucm.map.NodeConnection.class, new NodeConnectionMHandler());
//    	ourClass2Conv.put( ucm.map.OrFork.class, new OrForkMHandler());
//    	ourClass2Conv.put( ucm.map.OrJoin.class, new OrJoinMHandler());
//    	ourClass2Conv.put( ucm.map.OutBinding.class, new OutBindingMHandler());
//    	ourClass2Conv.put( ucm.performance.PassiveResource.class, new PassiveResourceMHandler());
//    	ourClass2Conv.put( ucm.map.PathNode.class, new PathNodeMHandler());
//    	ourClass2Conv.put( ucm.map.PluginBinding.class, new PluginBindingMHandler());
//    	ourClass2Conv.put( ucm.performance.ProcessingResource.class, new ProcessingResourceMHandler());
//    	ourClass2Conv.put( urncore.Responsibility.class, new ResponsibilityMHandler());
//    	ourClass2Conv.put( ucm.map.RespRef.class, new RespRefMHandler());
//    	ourClass2Conv.put( ucm.scenario.ScenarioDef.class, new ScenarioDefMHandler());
//    	ourClass2Conv.put( ucm.scenario.ScenarioGroup.class, new ScenarioGroupMHandler());
//    	ourClass2Conv.put( ucm.map.StartPoint.class, new StartPointMHandler());
//    	ourClass2Conv.put( grl.StrategiesGroup.class, new StrategiesGroupMHandler());
//    	ourClass2Conv.put( ucm.map.Stub.class, new StubMHandler());
//    	ourClass2Conv.put( ucm.map.Timer.class, new TimerMHandler());
//    	ourClass2Conv.put( ucm.map.UCMmap.class, new UCMmapMHandler());
//    	//ourClass2Conv.put( urncore.UCMmodelElement.class, new UCMmodelElementMHandler()); **
//    	ourClass2Conv.put( ucm.UCMspec.class, new UCMspecMHandler());
//    	ourClass2Conv.put( urn.URNlink.class, new URNlinkMHandler());
//    	//ourClass2Conv.put( urncore.URNmodelElement.class, new URNmodelElementMHandler());**
//    	ourClass2Conv.put( urn.URNspec.class, new URNspecMHandler());
//    	ourClass2Conv.put( ucm.scenario.Variable.class, new VariableMHandler());
//    	ourClass2Conv.put( ucm.map.WaitingPlace.class, new WaitingPlaceMHandler());
//    	ourClass2Conv.put( ucm.performance.Workload.class, new WorkloadMHandler());

    	ourClass2Conv.put( grl.impl.BeliefImpl.class, new BeliefMHandler());
    	ourClass2Conv.put( grl.impl.BeliefLinkImpl.class, new BeliefLinkMHandler());
    	
    	ourClass2Conv.put( ucm.performance.impl.ActiveResourceImpl.class, new ActiveResourceMHandler());
    	ourClass2Conv.put( grl.impl.ActorImpl.class, new ActorMHandler());
    	ourClass2Conv.put( grl.impl.ActorRefImpl.class, new ActorRefMHandler());
    	ourClass2Conv.put( ucm.map.impl.AndForkImpl.class, new AndForkMHandler());
    	ourClass2Conv.put( ucm.map.impl.AndJoinImpl.class, new AndJoinMHandler());
    	ourClass2Conv.put( grl.impl.CollapsedActorRefImpl.class, new CollapsedActorRefMHandler());
    	ourClass2Conv.put( urncore.impl.CommentImpl.class, new CommentMHandler());
    	ourClass2Conv.put( ucm.map.impl.ComponentBindingImpl.class, new ComponentBindingMHandler());
    	ourClass2Conv.put( urncore.impl.ComponentImpl.class, new ComponentMHandler());
    	ourClass2Conv.put( ucm.map.impl.ComponentRefImpl.class, new ComponentRefMHandler());
    	ourClass2Conv.put( urncore.impl.ComponentTypeImpl.class, new ComponentTypeMHandler());
    	ourClass2Conv.put( urncore.impl.ConcernImpl.class, new ConcernMHandler());
    	ourClass2Conv.put( urncore.impl.ConditionImpl.class, new ConditionMHandler());
    	ourClass2Conv.put( ucm.map.impl.ConnectImpl.class, new ConnectMHandler());
    	ourClass2Conv.put( grl.impl.ContributionImpl.class, new ContributionMHandler());
    	ourClass2Conv.put( grl.impl.DecompositionImpl.class, new DecompositionMHandler());
    	ourClass2Conv.put( ucm.performance.impl.DemandImpl.class, new DemandMHandler());
    	ourClass2Conv.put( grl.impl.DependencyImpl.class, new DependencyMHandler());
    	ourClass2Conv.put( ucm.map.impl.DirectionArrowImpl.class, new DirectionArrowMHandler());
    	ourClass2Conv.put( grl.impl.ElementLinkImpl.class, new ElementLinkMHandler());
    	ourClass2Conv.put( ucm.map.impl.EmptyPointImpl.class, new EmptyPointMHandler());
    	
    	ourClass2Conv.put( ucm.scenario.impl.ScenarioEndPointImpl.class, new ScenarioEndPointMHandler());
    	ourClass2Conv.put( ucm.map.impl.EndPointImpl.class, new EndPointMHandler());
    	ourClass2Conv.put( ucm.scenario.impl.EnumerationTypeImpl.class, new EnumerationTypeMHandler());
    	ourClass2Conv.put( grl.impl.EvaluationImpl.class, new EvaluationMHandler());
    	ourClass2Conv.put( grl.impl.EvaluationStrategyImpl.class, new EvaluationStrategyMHandler());
    	ourClass2Conv.put( ucm.performance.impl.ExternalOperationImpl.class, new ExternalOperationMHandler());
    	ourClass2Conv.put( ucm.performance.impl.GeneralResourceImpl.class, new GeneralResourceMHandler());
    	ourClass2Conv.put( grl.impl.GRLGraphImpl.class, new GRLGraphMHandler());
    	ourClass2Conv.put( grl.impl.GRLLinkableElementImpl.class, new GRLLinkableElementMHandler());
    	//ourClass2Conv.put( grl.impl.GRLmodelElementImpl.class, new GRLmodelElementMHandler());
    	//ourClass2Conv.put( grl.impl.GRLNodeImpl.class, new GRLNodeMHandler());
    	ourClass2Conv.put( grl.impl.GRLspecImpl.class, new GRLspecMHandler());
    	ourClass2Conv.put( ucm.map.impl.InBindingImpl.class, new InBindingMHandler());
    	ourClass2Conv.put( ucm.scenario.impl.InitializationImpl.class, new InitializationMHandler());
    	ourClass2Conv.put( grl.impl.IntentionalElementImpl.class, new IntentionalElementMHandler());
    	ourClass2Conv.put( grl.impl.IntentionalElementRefImpl.class, new IntentionalElementRefMHandler());
    	//ourClass2Conv.put( IURNConnectionImpl.class, new IURNConnectionMHandler());
    	ourClass2Conv.put( grl.impl.LinkRefBendpointImpl.class, new LinkRefBendpointMHandler());
    	ourClass2Conv.put( grl.impl.LinkRefImpl.class, new LinkRefMHandler());
    	ourClass2Conv.put( urncore.impl.MetadataImpl.class, new MetadataMHandler());
    	ourClass2Conv.put( ucm.map.impl.NodeConnectionImpl.class, new NodeConnectionMHandler());
    	ourClass2Conv.put( ucm.map.impl.OrForkImpl.class, new OrForkMHandler());
    	ourClass2Conv.put( ucm.map.impl.OrJoinImpl.class, new OrJoinMHandler());
    	ourClass2Conv.put( ucm.map.impl.OutBindingImpl.class, new OutBindingMHandler());
    	ourClass2Conv.put( ucm.performance.impl.PassiveResourceImpl.class, new PassiveResourceMHandler());
    	ourClass2Conv.put( ucm.map.impl.PathNodeImpl.class, new PathNodeMHandler());
    	ourClass2Conv.put( ucm.map.impl.PluginBindingImpl.class, new PluginBindingMHandler());
    	ourClass2Conv.put( ucm.performance.impl.ProcessingResourceImpl.class, new ProcessingResourceMHandler());
    	ourClass2Conv.put( urncore.impl.ResponsibilityImpl.class, new ResponsibilityMHandler());
    	ourClass2Conv.put( ucm.map.impl.RespRefImpl.class, new RespRefMHandler());
    	ourClass2Conv.put( ucm.scenario.impl.ScenarioDefImpl.class, new ScenarioDefMHandler());
    	ourClass2Conv.put( ucm.scenario.impl.ScenarioGroupImpl.class, new ScenarioGroupMHandler());
    	
    	ourClass2Conv.put( ucm.scenario.impl.ScenarioStartPointImpl.class, new ScenarioStartPointMHandler());
    	ourClass2Conv.put( ucm.map.impl.StartPointImpl.class, new StartPointMHandler());
    	ourClass2Conv.put( grl.impl.StrategiesGroupImpl.class, new StrategiesGroupMHandler());
    	ourClass2Conv.put( ucm.map.impl.StubImpl.class, new StubMHandler());
    	ourClass2Conv.put( ucm.map.impl.TimerImpl.class, new TimerMHandler());
    	ourClass2Conv.put( ucm.map.impl.UCMmapImpl.class, new UCMmapMHandler());
    	//ourClass2Conv.put( urncore.impl.UCMmodelElementImpl.class, new UCMmodelElementMHandler());
    	ourClass2Conv.put( ucm.impl.UCMspecImpl.class, new UCMspecMHandler());
    	ourClass2Conv.put( urn.impl.URNlinkImpl.class, new URNlinkMHandler());
    	//ourClass2Conv.put( urncore.impl.URNmodelElementImpl.class, new URNmodelElementMHandler());
    	ourClass2Conv.put( urn.impl.URNspecImpl.class, new URNspecMHandler());
    	ourClass2Conv.put( ucm.scenario.impl.VariableImpl.class, new VariableMHandler());
    	ourClass2Conv.put( ucm.map.impl.WaitingPlaceImpl.class, new WaitingPlaceMHandler());
    	ourClass2Conv.put( ucm.performance.impl.WorkloadImpl.class, new WorkloadMHandler());
    	
    	
//    	
//    	
//        ourClass2Conv.put( grl.Actor.class,   new ActorMHandler() );
//        ourClass2Conv.put( grl.impl.ActorImpl.class,   new ActorMHandler() );
//        ourClass2Conv.put( grl.ActorRef.class,   new ActorRefMHandler() );
//        ourClass2Conv.put( grl.impl.ActorRefImpl.class,   new ActorRefMHandler() );
//        ourClass2Conv.put( grl.CollapsedActorRef.class,   new CollapsedActorRefMHandler() );
//        ourClass2Conv.put( grl.impl.CollapsedActorRefImpl.class,   new ActorRefMHandler() );
//        ourClass2Conv.put( urncore.Concern.class,   new ConcernMHandler() );
//        ourClass2Conv.put( urncore.impl.ConcernImpl.class,   new ConcernMHandler() );
//        ourClass2Conv.put( urncore.Condition.class,   new ConditionMHandler() );
//        ourClass2Conv.put( urncore.impl.ConditionImpl.class,   new ConditionMHandler() );       
//        ourClass2Conv.put( grl.Contribution.class,   new ContributionMHandler() );
//        ourClass2Conv.put( grl.impl.ContributionImpl.class,   new ContributionMHandler() );     
//        ourClass2Conv.put( grl.Decomposition.class,   new DecompositionMHandler() );
//        ourClass2Conv.put( grl.impl.DecompositionImpl.class,   new DecompositionMHandler() ); 
//        ourClass2Conv.put( grl.Dependency.class,   new DependencyMHandler() );
//        ourClass2Conv.put( grl.impl.DependencyImpl.class,   new DependencyMHandler() ); 
//        ourClass2Conv.put( grl.ElementLink.class,   new ElementLinkMHandler() );
//        ourClass2Conv.put( grl.impl.ElementLinkImpl.class,   new ElementLinkMHandler() );
//        ourClass2Conv.put( grl.Evaluation.class,   new EvaluationMHandler() );
//        ourClass2Conv.put( grl.impl.EvaluationImpl.class,   new EvaluationMHandler() );
//        ourClass2Conv.put( grl.EvaluationStrategy.class,   new EvaluationStrategyMHandler() );
//        ourClass2Conv.put( grl.impl.EvaluationStrategyImpl.class,   new EvaluationStrategyMHandler() );
//        ourClass2Conv.put( grl.GRLGraph.class,   new GRLGraphMHandler() );
//        ourClass2Conv.put( grl.impl.GRLGraphImpl.class,   new GRLGraphMHandler() );
//        ourClass2Conv.put( grl.GRLLinkableElement.class,   new GRLLinkableElementMHandler() );
//        ourClass2Conv.put( grl.impl.GRLLinkableElementImpl.class,   new GRLLinkableElementMHandler() );
////        ourClass2Conv.put( urncore.GRLmodelElement.class,   new GRLmodelElementMHandler() );
////        ourClass2Conv.put( urncore.impl.GRLmodelElementImpl.class,   new GRLmodelElementMHandler() );
//        ourClass2Conv.put( grl.GRLspec.class,   new GRLspecMHandler() );
//        ourClass2Conv.put( grl.impl.GRLspecImpl.class,   new GRLspecMHandler() );
//        ourClass2Conv.put( grl.IntentionalElement.class,   new IntentionalElementMHandler() );
//        ourClass2Conv.put( grl.impl.IntentionalElementImpl.class,   new IntentionalElementMHandler() );
//        ourClass2Conv.put( grl.IntentionalElementRef.class,   new IntentionalElementRefMHandler() );
//        ourClass2Conv.put( grl.impl.IntentionalElementRefImpl.class,   new IntentionalElementRefMHandler() );
//        ourClass2Conv.put( grl.LinkRefBendpoint.class,   new LinkRefBendpointMHandler() );
//        ourClass2Conv.put( grl.impl.LinkRefBendpointImpl.class,   new LinkRefBendpointMHandler() );
//        ourClass2Conv.put( grl.LinkRef.class,   new LinkRefMHandler() );
//        ourClass2Conv.put( grl.impl.LinkRefImpl.class,   new LinkRefMHandler() );
//        ourClass2Conv.put( urncore.Metadata.class,   new MetadataMHandler() );
//        ourClass2Conv.put( urncore.impl.MetadataImpl.class,   new StrategiesGroupMHandler() );
//        ourClass2Conv.put( grl.StrategiesGroup.class,   new StrategiesGroupMHandler() );
//        ourClass2Conv.put( grl.impl.StrategiesGroupImpl.class,   new StrategiesGroupMHandler() );
//        ourClass2Conv.put( urn.URNlink.class,   new URNlinkMHandler() );
//        ourClass2Conv.put( urn.impl.URNlinkImpl.class,   new URNlinkMHandler() );
//        ourClass2Conv.put( urn.URNspec.class, new URNspecMHandler() );
//        ourClass2Conv.put( urn.impl.URNspecImpl.class, new URNspecMHandler() );
        //...
    }
    
    public String getObjectId(Object obj){
    	return "Z151_id_"+obj.getClass().getName()+"_"+obj.hashCode();
    }
    
    public int  hashCode(Object obj){
    	return obj.hashCode();
    };
    
    public void resetUrnSpec(){
		urnZ = null;
		id2object.clear();
	}
    public abstract Object handle(Object obj, Object target, boolean isFullConstruction);
    
    protected Object process(Object obj, Object target, boolean isFullConstruction){
        if(null != obj){
            MHandler h = ourClass2Conv.get( obj.getClass() );
            if( null != h ){
                return h.handle( obj, target, isFullConstruction);
            }else{
            	System.err.println(obj.getClass().getName()+" MHandler is UNDEFINED!");
            }
        }
        return null;
    }
    
    protected JAXBElement<Object> process(Object obj, Object target, String methodName, boolean isFullConstruction){
        if(null != obj){
            MHandler h = ourClass2Conv.get( obj.getClass());
            if( null != h ){
            	try {
            		Object tmp = h.handle( obj, target, isFullConstruction);
					Method method = of.getClass().getMethod(methodName, Object.class);
					JAXBElement<Object> jaxbElement =  (JAXBElement<Object>) method.invoke(of, tmp);
					return jaxbElement;
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					System.err.println(obj.getClass().getName()+" IllegalAccessException");
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					System.err.println(obj.getClass().getName()+" InvocationTargetException");
					e.printStackTrace();
				}catch (NullPointerException e){
					System.err.println(obj.getClass().getName()+" NullPointerException");
					e.printStackTrace();
				}catch (Exception e){
					System.err.println(obj.getClass().getName()+" Exception");
					e.printStackTrace();
				}
                //return h.handle( obj, target, isFullConstruction);
            }else{
            	System.err.println(obj.getClass().getName()+" MHandler is UNDEFINED!");
            }
        }
        return null;
    }
    protected <V> void processList( EList list, List<V> targetList, boolean isFullConstruction){
    	if (list!=null && list.size()> 0){ 
    		for( Object obj: list){
    			MHandler h = this.getHandler(obj);
                if( null != h ){
                	V result = (V) h.process(obj, null, isFullConstruction);
                	if (!targetList.contains(result)) targetList.add(result);
                }else{
                	System.err.println(obj.getClass().getName()+" MHandler is UNDEFINED!");
                }
    		}
    	}else{
    		//System.err.println("processList list is null or empty! "+list.getClass());
    	}
    }
   
    protected void processList( EList list, List<JAXBElement<Object>> targetList, String methodName, boolean isFullConstruction){
    	if (list!=null && list.size()> 0){ 
    		for( Object obj: list){
    			MHandler h = this.getHandler(obj);
                if( null != h ){
                	Object tmp = h.process(obj, null,isFullConstruction);
                	try {
						Method method = of.getClass().getMethod(methodName, Object.class);
						JAXBElement<Object> jaxbElement =  (JAXBElement<Object>) method.invoke(of, tmp);
	        			if (!targetList.contains(jaxbElement)) targetList.add(jaxbElement);
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

                }else{
                	System.err.println(obj.getClass().getName()+" MHandler is UNDEFINED!");
                }    			
    		}
    	}else{
    		//System.out.println("processList list is null or empty! "+list.getClass());
    	}
    }
    
    protected DecompositionType getDecompositionType(grl.DecompositionType typeJ){
		switch (typeJ.getValue()){
			case 0: return seg.jUCMNav.importexport.z151.generated.DecompositionType.AND;
			//case 1: return seg.jUCMNav.importexport.z151.generated.DecompositionType.IOR;  //DecompositionType: jUCMNav has OR, but Z151 is IOR
			case 2: return seg.jUCMNav.importexport.z151.generated.DecompositionType.XOR;
			default: return null; //return seg.jUCMNav.importexport.z151.generated.DecompositionType.AND; 
		}
    }
    protected seg.jUCMNav.importexport.z151.generated.IntentionalElementType getIntentionalElementType(grl.IntentionalElementType typeJ){
		switch (typeJ.getValue()){
			case 0: return seg.jUCMNav.importexport.z151.generated.IntentionalElementType.SOFTGOAL;
			case 1: return seg.jUCMNav.importexport.z151.generated.IntentionalElementType.GOAL;
			case 2: return seg.jUCMNav.importexport.z151.generated.IntentionalElementType.TASK;
			case 3: return seg.jUCMNav.importexport.z151.generated.IntentionalElementType.RESOURCE;
			case 4: return null;//IntentionalElementType: jNCMNav has Indicator, but Z151 has Belief
			default: return null;
		}		
	}
	
	protected seg.jUCMNav.importexport.z151.generated.ImportanceType getImportanceType(grl.ImportanceType typeJ){
		switch (typeJ.getValue()){
			case 0: return seg.jUCMNav.importexport.z151.generated.ImportanceType.HIGH;
			case 1: return seg.jUCMNav.importexport.z151.generated.ImportanceType.MEDIUM;
			case 2: return seg.jUCMNav.importexport.z151.generated.ImportanceType.LOW;
			case 3: return seg.jUCMNav.importexport.z151.generated.ImportanceType.NONE;
			default: return seg.jUCMNav.importexport.z151.generated.ImportanceType.NONE;// null;
		}
		
	}
       
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  QualitativeLabel  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:simpleType name="QualitativeLabel">
//    <xsd:restriction base="xsd:string">
//      <xsd:enumeration value="Denied"/>
//      <xsd:enumeration value="WeaklyDenied"/>
//      <xsd:enumeration value="WeaklySatisfied"/>
//      <xsd:enumeration value="Satisfied"/>
//      <xsd:enumeration value="Conflict"/>
//      <xsd:enumeration value="Unknown"/>
//      <xsd:enumeration value="None"/>
//    </xsd:restriction>
//  </xsd:simpleType>
	
	protected QualitativeLabel getQualitativeEvaluation(grl.QualitativeLabel typeJ){
		switch (typeJ.getValue()){
			case 0: return seg.jUCMNav.importexport.z151.generated.QualitativeLabel.DENIED;
			case 1: return seg.jUCMNav.importexport.z151.generated.QualitativeLabel.WEAKLY_DENIED;  
			case 2: return seg.jUCMNav.importexport.z151.generated.QualitativeLabel.WEAKLY_SATISFIED;
			case 3: return seg.jUCMNav.importexport.z151.generated.QualitativeLabel.SATISFIED; 
			case 4: return seg.jUCMNav.importexport.z151.generated.QualitativeLabel.CONFLICT;  
			case 5: return seg.jUCMNav.importexport.z151.generated.QualitativeLabel.UNKNOWN;
			case 6: return seg.jUCMNav.importexport.z151.generated.QualitativeLabel.NONE; 
			default: return seg.jUCMNav.importexport.z151.generated.QualitativeLabel.NONE;
		}
	}
	
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  ComponentKind  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:simpleType name="ComponentKind">
//    <xsd:restriction base="xsd:string">
//      <xsd:enumeration value="Team"/>
//      <xsd:enumeration value="Object"/>
//      <xsd:enumeration value="Process"/>
//      <xsd:enumeration value="Agent"/>
//      <xsd:enumeration value="Actor"/>
//    </xsd:restriction>
//  </xsd:simpleType>
	/*jUCMNav has ComponentKind.OTHER but Z151 does not have*/
	
	
//	public static ComponentKind get(int value) {
//		switch (value) {
//			case TEAM: return TEAM_LITERAL;
//			case OBJECT: return OBJECT_LITERAL;
//			case PROCESS: return PROCESS_LITERAL;
//			case AGENT: return AGENT_LITERAL;
//			case ACTOR: return ACTOR_LITERAL;
//			case OTHER: return OTHER_LITERAL;
//		}
//		return null;
//	}
	protected ComponentKind getComponentKind(urncore.ComponentKind typeJ){
		switch (typeJ.getValue()){
		case 0: return seg.jUCMNav.importexport.z151.generated.ComponentKind.TEAM;
		case 1: return seg.jUCMNav.importexport.z151.generated.ComponentKind.OBJECT;
		case 2: return seg.jUCMNav.importexport.z151.generated.ComponentKind.PROCESS;
		case 3: return seg.jUCMNav.importexport.z151.generated.ComponentKind.AGENT;
		case 4: return seg.jUCMNav.importexport.z151.generated.ComponentKind.ACTOR; 
		}
		return null;
    }
	
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  ContributionType  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:simpleType name="ContributionType">
//    <xsd:restriction base="xsd:string">
//      <xsd:enumeration value="Make"/>
//      <xsd:enumeration value="Help"/>
//      <xsd:enumeration value="SomePositive"/>
//      <xsd:enumeration value="Unknown"/>
//      <xsd:enumeration value="SomeNegative"/>
//      <xsd:enumeration value="Hurt"/>
//      <xsd:enumeration value="Break"/>
//    </xsd:restriction>
//  </xsd:simpleType>
	protected ContributionType getContributionType(grl.ContributionType typeJ){
		switch (typeJ.getValue()){
		case 0: return seg.jUCMNav.importexport.z151.generated.ContributionType.MAKE;
		case 1: return seg.jUCMNav.importexport.z151.generated.ContributionType.HELP;
		case 2: return seg.jUCMNav.importexport.z151.generated.ContributionType.SOME_POSITIVE;
		case 3: return seg.jUCMNav.importexport.z151.generated.ContributionType.UNKNOWN;
		case 4: return seg.jUCMNav.importexport.z151.generated.ContributionType.SOME_NEGATIVE;
		case 5: return seg.jUCMNav.importexport.z151.generated.ContributionType.HURT;
		case 6: return seg.jUCMNav.importexport.z151.generated.ContributionType.BREAK;
		default: return seg.jUCMNav.importexport.z151.generated.ContributionType.UNKNOWN;
		}
    }
	
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  DatatypeKind  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:simpleType name="DatatypeKind">
//    <xsd:restriction base="xsd:string">
//      <xsd:enumeration value="Boolean"/>
//      <xsd:enumeration value="Integer"/>
//      <xsd:enumeration value="Enumeration"/>
//    </xsd:restriction>
	//Use DatatypeKind.fromValue(String)
	
	
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  DeviceKind  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:simpleType name="DeviceKind">
//    <xsd:restriction base="xsd:string">
//      <xsd:enumeration value="Processor"/>
//      <xsd:enumeration value="Disk"/>
//      <xsd:enumeration value="DSP"/>
//    </xsd:restriction>
//  </xsd:simpleType>
	
	protected DeviceKind getDeviceKind(ucm.performance.DeviceKind typeJ){
		switch (typeJ.getValue()){
		case 0: return seg.jUCMNav.importexport.z151.generated.DeviceKind.PROCESSOR;
		case 1: return seg.jUCMNav.importexport.z151.generated.DeviceKind.DISK;
		case 2: return seg.jUCMNav.importexport.z151.generated.DeviceKind.DSP;
		default: return seg.jUCMNav.importexport.z151.generated.DeviceKind.PROCESSOR;
		}
    }
	
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <!--  WaitKind  -->
//  <!-- ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
//  <xsd:simpleType name="WaitKind">
//    <xsd:restriction base="xsd:string">
//      <xsd:enumeration value="Transient"/>
//      <xsd:enumeration value="Persistent"/>
//    </xsd:restriction>
//  </xsd:simpleType>
	
	protected WaitKind getWaitKind(ucm.map.WaitKind typeJ){
		switch (typeJ.getValue()){
		case 0: return seg.jUCMNav.importexport.z151.generated.WaitKind.TRANSIENT;
		case 1: return seg.jUCMNav.importexport.z151.generated.WaitKind.PERSISTENT;
		default: return null;
		}
    }
    
    protected MHandler getHandler(Object obj){
    	return ourClass2Conv.get(obj.getClass());
    }
}
