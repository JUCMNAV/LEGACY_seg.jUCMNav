package seg.jUCMNav.importexport.msc;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import seg.jUCMNav.editors.resourceManagement.UcmScenariosModelManager;
import seg.jUCMNav.model.util.modelexplore.GraphExplorer;
import seg.jUCMNav.model.util.modelexplore.queries.ReachableNodeFinder;
import seg.jUCMNav.model.util.modelexplore.queries.ResponsibilityFinder;
import seg.jUCMNav.model.util.modelexplore.queries.ReachableNodeFinder.QFindReachableNodes;
import seg.jUCMNav.model.util.modelexplore.queries.ResponsibilityFinder.QFindResponsibilities;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.ComponentRef;
import ucm.map.DirectionArrow;
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
 * TODO: all id's are from the generated URN, not the source. TODO: scenario preconditions/postconditions
 * 
 * @author jkealey
 * 
 */
public class ScenarioGenerator {

	private static int msgId = 0;
	private ucmscenarios.Component _environmentComponent;
	private Instance _lastEnvironmentInstance;

	// TODO: parallel is only an approximation in the current implementation; works well except for parallel scenariostartpoints that merge into each other
	private boolean ARE_SCENARIO_STARTPOINTS_PARALLEL = false;

	// factory
	private UcmscenariosFactory f = UcmscenariosFactory.eINSTANCE;
	private HashMap hmCompDefToComponent;

	private HashMap hmCompRefToInstance;
	private HashMap processedPathNodes;

	private HashMap queuedMessages;

	private ScenarioSpec scenariospec;
	
	private URNspec urnspec;

	
	public ScenarioGenerator(URNspec urnspec) {
		this.urnspec = urnspec;

	}

	private ComponentRef addAllBranches(Sequence seq, AndFork fork) {

		Parallel par = f.createParallel();
		processedPathNodes.put(fork, par);

		par.setSequence(seq);
		par.setName(fork.getName());
		ComponentRef compRef = (ComponentRef) fork.getContRef();

		Vector common = null;

		for (int i = 0; i < fork.getSucc().size(); i++) {
			HashSet ncs = new HashSet();
			ncs.addAll(fork.getSucc());
			ncs.remove(fork.getSucc().get(i));
			QFindReachableNodes qReachableNodes = new ReachableNodeFinder.QFindReachableNodes(fork, ncs, QFindReachableNodes.DIRECTION_FORWARD);
			ReachableNodeFinder.RReachableNodes rReachableNodes = (ReachableNodeFinder.RReachableNodes) GraphExplorer.run(qReachableNodes);
			Vector vReachable = rReachableNodes.getNodes();

			if (common == null)
				common = vReachable;
			else
				common.retainAll(vReachable);
		}

		for (int i = 0; i < fork.getSucc().size(); i++) {
			NodeConnection nc = (NodeConnection) fork.getSucc().get(i);
			Sequence subseq = f.createSequence();

			ComponentRef branchCompRef = addPath(subseq, (PathNode) nc.getTarget(), common.size() == 0 ? null : (PathNode) common.lastElement());

			// if (branchCompRef!=compRef)
			// addMessage(subseq, 0, compRef, branchCompRef);

			subseq.setParent(par);
		}
		return compRef;

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
			instance.setName(((ComponentElement) element.getContDef()).getName());
			instance.setScenario(scenario);
			hmCompRefToInstance.put(element, instance);
		}
	}

	private ComponentRef addCondition(Sequence seq, WaitingPlace wp) {
		ComponentRef compRef = (ComponentRef) wp.getContRef();

		Condition cond = ((NodeConnection) wp.getSucc().get(0)).getCondition();
		assert cond != null;

		
		ucmscenarios.Condition condition = f.createCondition();
		processedPathNodes.put(wp, condition);
		setIdNameDesc(wp, condition);

		// TODO: we currently don't have labels on our generated NCs
		// condition.setLabel(cond.getLabel());
		condition.setLabel(wp.getName());
		condition.setExpression(cond.getExpression());

		condition.setInstance(getInstance(compRef));
		condition.setSequence(seq);

		
		
		
		return compRef;

	}

	private ComponentRef addDo(Sequence seq, RespRef respref) {
		Event action = f.createEvent();
		processedPathNodes.put(respref, seq);
		setIdNameDesc(getDef(respref), action);
		// override id
		action.setId(respref.getId());
		action.setType(EventType.RESPONSIBILITY_LITERAL);

		ComponentRef compRef = (ComponentRef) respref.getContRef();

		action.setInstance(getInstance(compRef));

		action.setSequence(seq);

		return compRef;
	}

	private ComponentRef addDoSimple(Sequence seq, PathNode pn) {
		Event action = f.createEvent();
		processedPathNodes.put(pn, action);

		setIdNameDesc(pn, action);
		ComponentRef compRef = (ComponentRef) pn.getContRef();
		action.setInstance(getInstance(compRef));
		

		if (pn instanceof StartPoint)
			action.setType(EventType.START_POINT_LITERAL);
		else if (pn instanceof EndPoint)
			action.setType(EventType.END_POINT_LITERAL);
		else if (pn instanceof Timer) {
			action.setType(EventType.TIMEOUT_LITERAL);
		} else if (pn instanceof DirectionArrow) {
			EventType type = EventType.get(getMetaData(pn, "type"));
			action.setType(type);
			
			if (type == EventType.WP_ENTER_LITERAL)
				action.setName(getMetaData(pn, "name") + " ENTER");
			else if (type == EventType.WP_LEAVE_LITERAL)
				action.setName(getMetaData(pn, "name") + " LEAVE");
			else if (type == EventType.TIMER_SET_LITERAL)
				action.setName(getMetaData(pn, "name") + " SET");
			else if (type == EventType.TIMER_RESET_LITERAL)
				action.setName(getMetaData(pn, "name") + " RESET");
				
		}
		action.setSequence(seq);
		return compRef;
	}

	private Message addMessage(Sequence element, ComponentRef from, ComponentRef to, PathNode src, PathNode target) {
		return addMessage(element, -1, from, to, src, target);
	}

	private Message addMessage(Sequence element, int index, ComponentRef from, ComponentRef to, PathNode src, PathNode target) {
		Message msg = f.createMessage();

		//msg.setId("M" + ++msgId);
		msg.setId(src.getId() + "_" + target.getId());
		
		
		if (target instanceof Timer) {
			msg.setName("Set " + getMetaData(target, "name"));
			msg.setDescription(target.getDescription());
		}
		if (target instanceof EndPoint || target instanceof WaitingPlace) {
			msg.setName(target.getName());
			msg.setDescription(target.getDescription());
		}
		else if (target instanceof RespRef) {
			msg.setName(getDef((RespRef)target).getName());
			msg.setDescription(getDef((RespRef)target).getDescription());
		}
		else { 
			msg.setName("msg");
			msg.setDescription("synthetic message");
		}

		msg.setSource(getInstance(from));
		msg.setTarget(getInstance(to));

		if (element!=null) {
			if (index < 0)
				element.getChildren().add(msg);
			else
				element.getChildren().add(index, msg);
		}
		
		return msg;
	}

	private ComponentRef addPath(Sequence seq, PathNode start, PathNode stopAtNode) {
		QFindReachableNodes qReachableNodes = new ReachableNodeFinder.QFindReachableNodes(start, new HashSet(), QFindReachableNodes.DIRECTION_FORWARD);
		ReachableNodeFinder.RReachableNodes rReachableNodes = (ReachableNodeFinder.RReachableNodes) GraphExplorer.run(qReachableNodes);
		Vector vReachable = rReachableNodes.getNodes();
		ComponentRef initialCompRef = (ComponentRef) start.getContRef();
		ComponentRef compRef = null;
		int index = -1;
		for (int i = 0; i < vReachable.size(); i++) {
			PathNode pn = (PathNode) vReachable.get(i);

			// stop when subbranch is done
			if (pn == stopAtNode)
				break;

			// skip subbranch elements covered by other calls.
			if (processedPathNodes.containsKey(pn)) {
				continue;
			}
			
			
			if (queuedMessages.containsKey(pn))
			{
				ArrayList msgs = (ArrayList) queuedMessages.get(pn);
				for (Iterator iter = msgs.iterator(); iter.hasNext();) {
					Message msg = (Message) iter.next();
					msg.setSequence(seq);
				}
			}

			if (pn instanceof RespRef) {
				compRef = addDo(seq, (RespRef) pn);
			} else if (pn instanceof DirectionArrow) {
				EventType type = EventType.get(getMetaData(pn, "type"));
				// these types are ignored. 
				if (type == EventType.CONNECT_END_LITERAL || type == EventType.CONNECT_START_LITERAL || type == EventType.TRIGGER_END_LITERAL)
					continue;
				else
					compRef = addDoSimple(seq, pn);
			
			} else if (pn instanceof StartPoint || pn instanceof EndPoint|| pn instanceof Timer) {
				compRef = addDoSimple(seq, pn);
			} else if (pn instanceof WaitingPlace) {
				compRef = addCondition(seq, (WaitingPlace) pn);
			} else if (pn instanceof AndFork) {
				compRef = addAllBranches(seq, (AndFork) pn);
			} else if (pn instanceof AndJoin) {
				compRef = (ComponentRef) pn.getContRef();

				processedPathNodes.put(pn, new Object[] { seq, new Integer(seq.getChildren().size()) });
			} else {
				System.out.println("unexpected pathnode");
				continue;
			}

			// skip flow points
			if (pn instanceof AndFork || pn instanceof AndJoin)
				continue;

			QFindResponsibilities qReachableResponsibilities = new ResponsibilityFinder.QFindResponsibilities(pn, new HashSet(),
					QFindResponsibilities.DIRECTION_FORWARD, false);
			ResponsibilityFinder.RNextResponsibilities rReachableResponsibilities = (ResponsibilityFinder.RNextResponsibilities) GraphExplorer
					.run(qReachableResponsibilities);
			Vector vResponsibilities = rReachableResponsibilities.getNodes();

			int count=0;
			for (int j = 0; j < vResponsibilities.size(); j++) {
				PathNode next = (PathNode) vResponsibilities.get(j);
				ComponentRef nextCompRef = (ComponentRef) next.getContRef();
				if (nextCompRef != compRef) {
					count++;
				}
			}
			
			if (count>=2)
			{
				for (int j = 0; j < vResponsibilities.size(); j++) {
					PathNode next = (PathNode) vResponsibilities.get(j);
					ComponentRef nextCompRef = (ComponentRef) next.getContRef();
					if (nextCompRef != compRef) {
						Message msg = addMessage(null, compRef, nextCompRef, pn, next);
						if (!queuedMessages.containsKey(next))
							queuedMessages.put(next, new ArrayList());
						
						((ArrayList)queuedMessages.get(next)).add(msg);
					}
				}
			} else  if (count>0) {
//				Parallel par = null; 
//				if (count>=2) {
//					par = f.createParallel();
//					par.setName("par");
//					par.setSequence(seq);
//				}
			
			
				for (int j = 0; j < vResponsibilities.size(); j++) {
					PathNode next = (PathNode) vResponsibilities.get(j);
					ComponentRef nextCompRef = (ComponentRef) next.getContRef();
					if (nextCompRef != compRef) {
						Sequence seq2 = seq;
						
//						if (par!=null) {
//							seq2 = f.createSequence();
//							seq2.setParent(par);
//							seq2.setName("msg");
//						}
						addMessage(seq2, compRef, nextCompRef, pn, next);
						
					}
				}
			}

		}

		return initialCompRef;
	}

	private void addPath(ucmscenarios.ScenarioDef out, ScenarioDef in, UCMmap map) {
		Sequence seq = f.createSequence();

		// TODO: semantic variation point.
		if (ARE_SCENARIO_STARTPOINTS_PARALLEL) {
			if (in.getStartPoints().size() > 1) {
				// multiple paths started in parallel
				Parallel par = f.createParallel();
				par.setSequence(seq);

				// inverse order by convention
				for (int i = in.getStartPoints().size() - 1; i >= 0; i--) {
					ScenarioStartPoint ssp = (ScenarioStartPoint) in.getStartPoints().get(i);
					StartPoint sp = ssp.getStartPoint();

					Sequence seq2 = f.createSequence();
					// TODO: we assume they are in parallel but we may run into merged paths from different scenario start points
					// TODO: look at code below for sequencing, but move the nodes after the join point on seq, instead of their original sequence inside
					// parallel
					addPath(seq2, sp, null);
					seq2.setParent(par);
				}

			} else {
				ScenarioStartPoint ssp = (ScenarioStartPoint) in.getStartPoints().get(0);

				addPath(seq, ssp.getStartPoint(), null);
			}
		} else {
			Vector[] reachable = new Vector[in.getStartPoints().size()];

			for (int i = 0; i < in.getStartPoints().size(); i++) {
				ScenarioStartPoint ssp = (ScenarioStartPoint) in.getStartPoints().get(i);
				QFindReachableNodes qReachableNodes = new ReachableNodeFinder.QFindReachableNodes(ssp.getStartPoint(), new HashSet(),
						QFindReachableNodes.DIRECTION_FORWARD);
				ReachableNodeFinder.RReachableNodes rReachableNodes = (ReachableNodeFinder.RReachableNodes) GraphExplorer.run(qReachableNodes);
				reachable[i] = rReachableNodes.getNodes();
			}

			// inverse order by convention
			for (int i = in.getStartPoints().size() - 1; i >= 0; i--) {
				ScenarioStartPoint ssp = (ScenarioStartPoint) in.getStartPoints().get(i);
				StartPoint sp = ssp.getStartPoint();

				Sequence seq2 = f.createSequence();

				addPath(seq2, sp, null);

				// look to see if was merged in other path.
				for (int j = i + 1; j < in.getStartPoints().size(); j++) {
					Vector curr = (Vector) reachable[i].clone();
					curr.retainAll(reachable[j]);

					if (curr.size() > 0) {
						PathNode join = (PathNode) curr.firstElement();
						assert join instanceof AndJoin;
						assert processedPathNodes.containsKey(join);

						Object[] location = (Object[]) processedPathNodes.get(join);
						Sequence location_seq = (Sequence) location[0];
						Integer location_pos = (Integer) location[1];

						location_seq.getChildren().add(location_pos.intValue()==0?0:location_pos.intValue()-1, seq2);

						// update for next
						location[1] = new Integer(location_pos.intValue() + 1);
						break;
					}

				}

				// nope, not merged!
				if (seq2.getSequence() == null)
					seq2.setSequence(seq);

			}
		}
		seq.setParentScenario(out);
	}

	private boolean addScenario(ScenarioDef in, ucmscenarios.ScenarioDef out) {
		processedPathNodes = new HashMap();
		queuedMessages = new HashMap();

		if (in.getStartPoints().size() > 0) {
			// assumption: all SSP point to the same map.

			ScenarioStartPoint ssp = (ScenarioStartPoint) in.getStartPoints().get(0);
			UCMmap map = (UCMmap) ssp.getStartPoint().getDiagram();

			addComponentReferences(out, map);

			addPath(out, in, map);

			if (_lastEnvironmentInstance.getSent().size() == 0 && _lastEnvironmentInstance.getReceived().size() == 0) {
				_lastEnvironmentInstance.setDefinition(null);
				_lastEnvironmentInstance.setScenario(null);
			}

			return true;
		} else
			return false;
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

	private String fix(String s) {
		if (s == null)
			return "";
		else
			return s;
	}

	private ucmscenarios.Component getComponent(ComponentElement comp) {

		if (hmCompDefToComponent.containsKey(comp))
			return (ucmscenarios.Component) hmCompDefToComponent.get(comp);
		else
			return _environmentComponent;

	}

	private ComponentElement getDef(ComponentRef element) {
		return ((ComponentElement) element.getContDef());
	}

	private Responsibility getDef(RespRef element) {
		return (element.getRespDef());
	}

	private Instance getInstance(ComponentRef comp) {

		if (hmCompRefToInstance.containsKey(comp))
			return (Instance) hmCompRefToInstance.get(comp);
		else
			return _lastEnvironmentInstance;
	}

	private String getLabel(Condition cond) {
		if (cond.getLabel() != null && cond.getLabel().length() > 0)
			return "[" + cond.getLabel() + "]";
		else
			return "[" + cond.getExpression() + "]";
	}

	private String getMetaData(PathNode pn, String name) {
		for (Iterator iter = pn.getMetadata().iterator(); iter.hasNext();) {
			Metadata md = (Metadata) iter.next();
			if (name.equalsIgnoreCase(md.getName()))
				return md.getValue();
		}
		return "";
	}

	public ScenarioSpec getScenarioDocument() {
		if (this.scenariospec == null) {
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
			// scenariospec.setId("");

			addComponentDefinitions(scenariospec);

			for (Iterator iter = urnspec.getUcmspec().getScenarioGroups().iterator(); iter.hasNext();) {
				ScenarioGroup element = (ScenarioGroup) iter.next();
				ucmscenarios.ScenarioGroup group = f.createScenarioGroup();

				setIdNameDesc(element, group);

				addScenarios(element, group);

				group.setScenarioSpec(scenariospec);

			}

			if (_environmentComponent.getInstances().size() == 0)
				_environmentComponent.setScenarioSpec(null);

		}

		return this.scenariospec;

	}

	public void save(File path) {
		UcmScenariosModelManager mgr = new UcmScenariosModelManager();
		mgr.createScenarioSpec(path, getScenarioDocument());
	}

	private void setIdNameDesc(URNmodelElement in, ModelElement out) {
		out.setId(in.getId());
		out.setName(in.getName());
		out.setDescription(in.getDescription());
	}
}
