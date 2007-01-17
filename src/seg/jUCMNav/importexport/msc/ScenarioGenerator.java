package seg.jUCMNav.importexport.msc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.core.runtime.IPath;

import seg.jUCMNav.editors.resourceManagement.UcmScenariosModelManager;
import seg.jUCMNav.model.util.modelexplore.GraphExplorer;
import seg.jUCMNav.model.util.modelexplore.queries.ReachableNodeFinder;
import seg.jUCMNav.model.util.modelexplore.queries.ReachableNodeFinder.QFindReachableNodes;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.ComponentRef;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Timer;
import ucm.map.UCMmap;
import ucm.map.WaitingPlace;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;
import ucm.scenario.ScenarioStartPoint;
import ucmscenarios.Event;
import ucmscenarios.EventType;
import ucmscenarios.Instance;
import ucmscenarios.Message;
import ucmscenarios.ModelElement;
import ucmscenarios.Parallel;
import ucmscenarios.ScenarioSpec;
import ucmscenarios.Sequence;
import ucmscenarios.UcmscenariosFactory;
import urn.URNspec;
import urncore.ComponentElement;
import urncore.Condition;
import urncore.Metadata;
import urncore.Responsibility;
import urncore.URNmodelElement;



/**
 * Generates an scenario xml from a URNspec produced by MscTraversalListener
 * 
 * TODO: all id's are from the generated URN, not the source. 
 * TODO: scenario preconditions/postconditions
 *  
 * @author jkealey
 *
 */
public class ScenarioGenerator {

	private URNspec urnspec;
	private ScenarioSpec scenariospec;
	private static int msgId=0;
	
	// factory
	private UcmscenariosFactory f = UcmscenariosFactory.eINSTANCE;
	
	private HashMap hmCompDefToComponent;
	private HashMap hmCompRefToInstance;
	
	private Instance _lastEnvironmentInstance;
	private ucmscenarios.Component _environmentComponent;
	
	public ScenarioGenerator(URNspec urnspec) {
		this.urnspec=urnspec;
		
	}


	public ScenarioSpec getScenarioDocument()
	{
		if (this.scenariospec==null){
			this.hmCompDefToComponent = new HashMap();
			this.hmCompRefToInstance = new HashMap();

            scenariospec = f.createScenarioSpec();

            scenariospec.setCreated(urnspec.getCreated());
            scenariospec.setModified(urnspec.getModified());
            scenariospec.setName(urnspec.getName());
            scenariospec.setSpecVersion(urnspec.getSpecVersion());
            scenariospec.setDescription(urnspec.getDescription());
            
            
            
            // TODO: stop using temporary field. 
            scenariospec.setFilename(urnspec.getAuthor());
            
            // urnspec has no ID
            //scenariospec.setId("");
			
	
			
			addComponentDefinitions(scenariospec);
			
			
			for (Iterator iter = urnspec.getUcmspec().getScenarioGroups().iterator(); iter.hasNext();) {
				ScenarioGroup element = (ScenarioGroup) iter.next();
				ucmscenarios.ScenarioGroup group = f.createScenarioGroup();

				setIdNameDesc(element, group);
				
				addScenarios(element, group);
				
				group.setScenarioSpec(scenariospec);

			}
			
			if (_environmentComponent.getInstances().size()==0)
				_environmentComponent.setScenarioSpec(null);
			
		}
		
		return this.scenariospec;
		
	}


	private void setIdNameDesc(URNmodelElement in, ModelElement out) {
		out.setId(in.getId());
		out.setName(in.getName());
		out.setDescription(in.getDescription());
	}
	
	private void addScenarios(ScenarioGroup in, ucmscenarios.ScenarioGroup out) {
		
		for (Iterator iter = in.getScenarios().iterator(); iter.hasNext();) {
			ScenarioDef element = (ScenarioDef) iter.next();
			
			ucmscenarios.ScenarioDef scenario = f.createScenarioDef();
			setIdNameDesc(element, scenario);
			
			boolean b = addScenario(element, scenario);

			scenario.setGroup(out);

		}

	}

	private String fix(String s) { if (s==null) return ""; else return s; }
	
	private boolean addScenario(ScenarioDef in, ucmscenarios.ScenarioDef out)
	{
		if (in.getStartPoints().size()>0) {
			// assumption: all SSP point to the same map. 
			
			ScenarioStartPoint ssp = (ScenarioStartPoint)in.getStartPoints().get(0);
			UCMmap map = (UCMmap) ssp.getStartPoint().getDiagram();
			
			
			addComponentReferences(out, map);
	
			addPath(out, in, map);
			
			if (_lastEnvironmentInstance.getSent().size()==0 && _lastEnvironmentInstance.getReceived().size()==0) {
				_lastEnvironmentInstance.setDefinition(null);
				_lastEnvironmentInstance.setScenario(null);
			}			
			
			
			return true;
		}
		else return false;
	}


	private void addPath(ucmscenarios.ScenarioDef out, ScenarioDef in, UCMmap map) {
		Sequence seq = f.createSequence();
		
		if (in.getStartPoints().size()>1)
		{
			// multiple paths started in parallel
			Parallel par = f.createParallel();
			par.setSequence(seq);

			for (int i=0;i<in.getStartPoints().size();i++) {
				ScenarioStartPoint ssp = (ScenarioStartPoint) in.getStartPoints().get(i);
				StartPoint sp = (StartPoint) ssp.getStartPoint();
				
				Sequence seq2 = f.createSequence();
				
				addPath(seq2, sp);
				seq2.setParent(par);
			}			
			
		} else  {
			ScenarioStartPoint ssp = (ScenarioStartPoint)in.getStartPoints().get(0);
			
			addPath(seq, ssp.getStartPoint());
		}
		
		seq.setParentScenario(out);
	}

	private ComponentRef addPath(Sequence seq, PathNode start) {
		QFindReachableNodes qReachableNodes = new ReachableNodeFinder().new QFindReachableNodes(start, new HashSet(), QFindReachableNodes.DIRECTION_FORWARD);
		ReachableNodeFinder.RReachableNodes rReachableNodes = (ReachableNodeFinder.RReachableNodes) GraphExplorer.getInstance().run(qReachableNodes);
		Vector vReachable = rReachableNodes.getNodes();
		ComponentRef initialCompRef = (ComponentRef)start.getContRef();
		ComponentRef oldCompRef=null;
		ComponentRef compRef=null;
		int index=-1;
		for (int i=0;i<vReachable.size();i++) {
			PathNode pn = (PathNode) vReachable.get(i);
			


			oldCompRef=compRef;
			index = seq.getChildren().size();
			// TODO: synthesis messages. 
			if (pn instanceof RespRef)
			{
				compRef = addDo(seq, (RespRef) pn);
			}
			else if (pn instanceof StartPoint || pn instanceof EndPoint || pn instanceof Timer )
			{
				compRef = addDoSimple(seq, pn );
			}
			else if (pn instanceof WaitingPlace)
			{
				compRef = addCondition(seq, (WaitingPlace) pn);
			} else if (pn instanceof AndFork) {
				compRef = addAllBranches(seq, (AndFork)pn);
				if (i>0 && oldCompRef!=compRef) {
					addMessage(seq, index, oldCompRef, compRef);
				}
			//	break;
			} else if (pn instanceof AndJoin) {
				// stop here.
				compRef = (ComponentRef)pn.getContRef();
				if (i>0 && oldCompRef!=compRef) {
					addMessage(seq, index, oldCompRef, compRef);
				}
				//break;
			}
				
			if (i>0 && oldCompRef!=compRef) {
				addMessage(seq, index, oldCompRef, compRef);
			}
			
			
		}

		return initialCompRef;
	}
	
	private ComponentRef addDo(Sequence seq, RespRef respref) {
		Event action = f.createEvent();

		setIdNameDesc(getDef(respref), action);
		// override id
		action.setId(respref.getId());
		action.setType(EventType.RESPONSIBILITY_LITERAL);
		
		ComponentRef compRef = (ComponentRef)respref.getContRef();

		action.setInstance(getInstance(compRef));

		action.setSequence(seq);
		return compRef;
	}
	private ComponentRef addDoSimple(Sequence seq, PathNode pn) {
		Event action = f.createEvent();

		setIdNameDesc(pn, action);
		ComponentRef compRef = (ComponentRef)pn.getContRef();
		action.setInstance(getInstance(compRef));
		action.setSequence(seq);
		
		if (pn instanceof StartPoint)
			action.setType(EventType.START_POINT_LITERAL);
		else if (pn instanceof EndPoint) 
			action.setType(EventType.END_POINT_LITERAL);
		else if (pn instanceof Timer) {
			action.setType(EventType.TIMER_SET_LITERAL);
			action.setName(getMetaData(pn, "name"));
			action = f.createEvent();
			action.setType(EventType.TIMEOUT_LITERAL);
			setIdNameDesc(pn, action);
			compRef = (ComponentRef)pn.getContRef();
			action.setInstance(getInstance(compRef));
			action.setSequence(seq);
			
		}
		

		return compRef;
	}
	private String getMetaData(PathNode pn, String name) {
		for (Iterator iter = pn.getMetadata().iterator(); iter.hasNext();) {
			Metadata md = (Metadata) iter.next();
			if (name.equalsIgnoreCase(md.getName()))
				return md.getValue();
		}
		return null;
	}
	private ComponentRef addCondition(Sequence seq, WaitingPlace wp)
	{
		Condition cond = ((NodeConnection)wp.getSucc().get(0)).getCondition();
		assert cond!=null;
		
		ucmscenarios.Condition condition = f.createCondition();
		setIdNameDesc(wp, condition);
		
		// TODO: we currently don't have labels on our generated NCs 
		//condition.setLabel(cond.getLabel());
		condition.setLabel(wp.getName());
		
		condition.setExpression(cond.getExpression());
		
		
		ComponentRef compRef = (ComponentRef)wp.getContRef();
		condition.setInstance(getInstance(compRef));
		
		
		condition.setSequence(seq);
		
		return compRef;
			
	}


	private String getLabel(Condition cond) {
		if (cond.getLabel()!=null && cond.getLabel().length()>0)
			return "[" + cond.getLabel() + "]";
		else
			return "[" + cond.getExpression() + "]";
	}
	
	private ComponentRef addAllBranches(Sequence seq, AndFork fork) {
		
		Parallel par = f.createParallel();
		par.setSequence(seq);
		par.setName(fork.getName());
		ComponentRef compRef = (ComponentRef)fork.getContRef();
		
		for (int i=0;i<fork.getSucc().size();i++) {
			NodeConnection nc = (NodeConnection) fork.getSucc().get(i);
			Sequence subseq = f.createSequence();
			
			ComponentRef branchCompRef = addPath(subseq, (PathNode) nc.getTarget());
			
			if (branchCompRef!=compRef)
				addMessage(subseq, 0, compRef, branchCompRef);

			subseq.setParent(par);
		}
		return compRef;
		
	}
	
	private void addMessage(Sequence element, ComponentRef from, ComponentRef to) {
		addMessage(element, -1, from, to);
	}
	private void addMessage(Sequence element, int index, ComponentRef from, ComponentRef to)
	{
		Message msg = f.createMessage();
		msg.setId("M" + ++msgId);
		msg.setName("msg");
		msg.setDescription("synthetic message");
		
		msg.setSource(getInstance(from));
		msg.setTarget(getInstance(to));
		
		if (index<0)
			element.getChildren().add(msg);
		else
			element.getChildren().add(index, msg);
	}
	private void addComponentReferences(ucmscenarios.ScenarioDef scenario, UCMmap map) {
		Instance instance = f.createInstance();
		instance.setId("I0");
		instance.setName("Environment");
		instance.setDescription("The external environment.");
		instance.setScenario(scenario);
		instance.setDefinition(_environmentComponent);
		_lastEnvironmentInstance = instance;
		for (Iterator iter = map.getContRefs().iterator(); iter.hasNext();) {
			ComponentRef element = (ComponentRef) iter.next();
			instance = f.createInstance();
			setIdNameDesc(element, instance);
			// refs have no useful names. 
			instance.setName(((ComponentElement)element.getContDef()).getName());
			instance.setScenario(scenario);
			hmCompRefToInstance.put(element, instance);
		}
	}


	private ComponentElement getDef(ComponentRef element) {
		return ((ComponentElement)element.getContDef());
	}
	private Responsibility getDef(RespRef element) {
		return ((Responsibility)element.getRespDef());
	}

	private void addComponentDefinitions(ScenarioSpec scenarios) {

		ucmscenarios.Component comp = f.createComponent();
		comp.setId("C0");
		comp.setName("Environment");
		comp.setDescription("The external environment.");
		comp.setScenarioSpec(scenarios);
		
		_environmentComponent = comp;
		
		for (Iterator iter = urnspec.getUrndef().getComponents().iterator(); iter.hasNext();) {
			ComponentElement element = (ComponentElement) iter.next();
			
			comp = f.createComponent();
			setIdNameDesc(element, comp);
			comp.setScenarioSpec(scenarios);
			hmCompDefToComponent.put(element, comp);
		}
	}
	
	
    
	public void save(IPath path)
	{
			UcmScenariosModelManager mgr = new UcmScenariosModelManager();
			mgr.createScenarioSpec(path, getScenarioDocument());
	}
	
	private Instance getInstance(ComponentRef comp) {
		
		if (hmCompRefToInstance.containsKey(comp))
			return (Instance) hmCompRefToInstance.get(comp);
		else
			return _lastEnvironmentInstance;
		}
	
	
	private ucmscenarios.Component getComponent(ComponentElement comp) {
		
		if (hmCompDefToComponent.containsKey(comp))
			return (ucmscenarios.Component) hmCompDefToComponent.get(comp);
		else
			return _environmentComponent;
		
	}
}
