package seg.jUCMNav.importexport.msc;

import java.io.FileWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.core.runtime.IPath;
import org.jdom.DocType;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

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
import urn.URNspec;
import urncore.ComponentElement;
import urncore.Condition;
import urncore.Responsibility;



/**
 * Generates an MSC xml from a URNspec produced by MscTraversalListener
 * 
 * TODO: all id's are from the generated URN, not the source. 
 * TODO: scenario preconditions/postconditions
 *  
 * @author jkealey
 *
 */
public class MscGenerator {

	private URNspec urnspec;
	Document scenarioxml;
	private boolean envInstanceRequired=false;
	private boolean envDefinitionRequired=false;
	private static int msgId=0;
	
	public MscGenerator(URNspec urnspec) {
		this.urnspec=urnspec;
	}


	public Document getScenarioDocument()
	{
		if (this.scenarioxml==null){

            DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
            String sDate = df.format(new Date());

			scenarioxml = new Document();
			scenarioxml.setDocType(new DocType("UCMscenarios").setSystemID("UCMscenario2.dtd"));
			
			Element scenarios = new Element("scenarios");
			scenarios.setAttribute("date", sDate );
			scenarios.setAttribute("ucm-file", fix(urnspec.getDescription()));
			scenarios.setAttribute("design-name", fix(urnspec.getName()));
			scenarios.setAttribute("ucm-design-version", fix(urnspec.getSpecVersion()));
			
			scenarioxml.addContent(scenarios);
			
			envInstanceRequired=false;
			envDefinitionRequired=false;
			
			
			addComponentDefinitions(scenarios);
			
			
			for (Iterator iter = urnspec.getUcmspec().getScenarioGroups().iterator(); iter.hasNext();) {
				ScenarioGroup element = (ScenarioGroup) iter.next();
				Element group = new Element("group").setAttribute("name", fix(element.getName())).setAttribute("group-id", element.getId());
				
				addScenarios(element, group);
				
				scenarios.addContent(group);

			}
			
			if (!envDefinitionRequired)
				((Element)((Element)scenarios.getChildren().get(0)).getChildren().get(0)).detach();
			
				
			
			
			
		}
		
		return this.scenarioxml;
		
	}


	private void addScenarios(ScenarioGroup scenariogroup, Element group) {
		
		for (Iterator iter = scenariogroup.getScenarios().iterator(); iter.hasNext();) {
			ScenarioDef element = (ScenarioDef) iter.next();
			Element scenario = new Element("scenario").setAttribute("name", fix(element.getName())).setAttribute("scenario-definition-id", element.getId()).setAttribute("description", fix(element.getDescription()));
			
			boolean b = addScenario(element, scenario);

			group.addContent(scenario);

		}

	}

	private String fix(String s) { if (s==null) return ""; else return s; }
	
	private boolean addScenario(ScenarioDef scenariodef, Element scenario)
	{
		if (scenariodef.getStartPoints().size()>0) {
			// assumption: all SSP point to the same map. 
			
			ScenarioStartPoint ssp = (ScenarioStartPoint)scenariodef.getStartPoints().get(0);
			UCMmap map = (UCMmap) ssp.getStartPoint().getDiagram();
			
			
			addComponentReferences(scenario, map);
	
			addPath(scenario, scenariodef, map);
			
			if (!envInstanceRequired)
				((Element)((Element)scenario.getChildren().get(0)).getChildren().get(0)).detach();
			
			
			envInstanceRequired=false;
				
			
			return true;
		}
		else return false;
	}


	private void addPath(Element scenario, ScenarioDef scenariodef, UCMmap map) {
		Element seq = new Element("seq");
		seq.setAttribute("para-label", "");
		
		if (scenariodef.getStartPoints().size()>1)
		{
			// multiple paths started in parallel
			Element par = new Element("par");
			seq.addContent(par);
			for (int i=0;i<scenariodef.getStartPoints().size();i++) {
				ScenarioStartPoint ssp = (ScenarioStartPoint) scenariodef.getStartPoints().get(i);
				StartPoint sp = (StartPoint) ssp.getStartPoint();
				
				Element seq2 = new Element("seq").setAttribute("para-label", "p1.s" + i);
				addPath(seq2, sp);
				par.addContent(seq2);
			}			
			
		} else  {
			ScenarioStartPoint ssp = (ScenarioStartPoint)scenariodef.getStartPoints().get(0);
			
			addPath(seq, ssp.getStartPoint());
		}
		
		scenario.addContent(seq);
	}

	private ComponentRef addPath(Element seq, PathNode start) {
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
			index = seq.getContentSize();
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
				break;
			} else if (pn instanceof AndJoin) {
				// stop here.
				compRef = (ComponentRef)pn.getContRef();
				if (i>0 && oldCompRef!=compRef) {
					addMessage(seq, index, oldCompRef, compRef);
				}
				break;
			}
				
			if (i>0 && oldCompRef!=compRef) {
				addMessage(seq, index, oldCompRef, compRef);
			}
			
			
		}

		return initialCompRef;
	}
	
	private ComponentRef addDo(Element seq, RespRef respref) {
		Element action = new Element("do");

		action.setAttribute("hyperedge-id", respref.getId());
		action.setAttribute("name", getDef(respref).getName());
		action.setAttribute("type", "Resp");
		
		ComponentRef compRef = (ComponentRef)respref.getContRef();
		if (compRef==null) {
			action.setAttribute("component-name", "Environment" );
			action.setAttribute("component-id", "I0" );
			action.setAttribute("component-role", "Env" );
			envInstanceRequired=true;
			envDefinitionRequired=true;
		}
		else {
			action.setAttribute("component-name", fix(getDef(compRef).getName()) );
			action.setAttribute("component-id", "I" + compRef.getId() );
			action.setAttribute("component-role", "" );
		}
		seq.addContent(action);
		return compRef;
	}
	private ComponentRef addDoSimple(Element seq, PathNode pn) {
		Element action = new Element("do");

		action.setAttribute("hyperedge-id", pn.getId());
		action.setAttribute("name", fix(pn.getName()));
		if (pn instanceof StartPoint)
			action.setAttribute("type", "Start");
		else if (pn instanceof EndPoint) 
			action.setAttribute("type", "End_Point");
		else if (pn instanceof Timer)
			action.setAttribute("type", "Timeout");
		
		ComponentRef compRef = (ComponentRef)pn.getContRef();
		if (pn.getContRef()==null) {
			action.setAttribute("component-name", "Environment" );
			action.setAttribute("component-id", "I0" );
			action.setAttribute("component-role", "Env" );
			envInstanceRequired=true;
			envDefinitionRequired=true;
		}
		else {
			action.setAttribute("component-name", fix(getDef(compRef).getName()));
			action.setAttribute("component-id", "I" + compRef.getId()  );
			action.setAttribute("component-role", "" );
		}
		seq.addContent(action);
		return compRef;
	}
	private ComponentRef addCondition(Element seq, WaitingPlace wp)
	{
		Condition cond = ((NodeConnection)wp.getSucc().get(0)).getCondition();
		assert cond!=null;
		
		Element condition = new Element("condition");
		condition.setAttribute("hyperedge-id", wp.getId());
		condition.setAttribute("label", wp.getName());
		condition.setAttribute("expression", cond.getExpression());
		
		ComponentRef compRef = (ComponentRef)wp.getContRef();
		if (compRef==null) {
			condition.setAttribute("instance", "I0");
			envInstanceRequired=true;
			envDefinitionRequired=true;
		}
		else
			condition.setAttribute("instance", "I" + compRef.getId());
		
		seq.addContent(condition);
		
		return compRef;
			
	}


	private String getLabel(Condition cond) {
		if (cond.getLabel()!=null && cond.getLabel().length()>0)
			return "[" + cond.getLabel() + "]";
		else
			return "[" + cond.getExpression() + "]";
	}
	
	private ComponentRef addAllBranches(Element seq, AndFork fork) {
		
		Element par = new Element("par");
		seq.addContent(par);
		ComponentRef compRef = (ComponentRef)fork.getContRef();
		
		for (int i=0;i<fork.getSucc().size();i++) {
			NodeConnection nc = (NodeConnection) fork.getSucc().get(i);
			Element subseq = new Element("seq");
			String label = seq.getAttributeValue("para-label") + ".p1.s" + i ;
			if (label.charAt(0)=='.')
				label = label.substring(1);
			
			subseq.setAttribute("para-label", label);
			
			ComponentRef branchCompRef = addPath(subseq, (PathNode) nc.getTarget());
			
			if (branchCompRef!=compRef)
				addMessage(subseq, 0, compRef, branchCompRef);
			
			par.addContent(subseq);
		}
		return compRef;
		
	}
	
	private void addMessage(Element element, ComponentRef from, ComponentRef to) {
		addMessage(element, -1, from, to);
	}
	private void addMessage(Element element, int index, ComponentRef from, ComponentRef to)
	{
		Element msg = new Element("message");
		msg.setAttribute("id", "M" + ++msgId);
		msg.setAttribute("name", "msg");
		msg.setAttribute("source-id", "I" + (from==null?"0":from.getId()));
		msg.setAttribute("destination-id", "I" + (to==null?"0":to.getId()));
		msg.setAttribute("is-task", "true");
		msg.setAttribute("is-timer", "false");
		msg.setAttribute("timer-property", "");
		msg.setAttribute("last-ref", "");
		msg.setAttribute("desc", "");
		msg.setAttribute("para-label", element.getAttributeValue("para-label"));
		msg.setAttribute("is-connector", "");
		msg.setAttribute("connector-type", "");

		if (index<0)
			element.addContent(msg);
		else
			element.addContent(index, msg);
	}
	private void addComponentReferences(Element scenario, UCMmap map) {
		Element instances = new Element("instances");
		instances.addContent(new Element("instance").setAttribute("id", "I0").setAttribute("name",  "Environment").setAttribute("component", "Environment").setAttribute("role", "Env").setAttribute("desc", "The external environment."));
		for (Iterator iter = map.getContRefs().iterator(); iter.hasNext();) {
			ComponentRef element = (ComponentRef) iter.next();
			// TODO: do something with role, it changes the name as well (name_role) 
			instances.addContent(new Element("instance").setAttribute("id", "I" + element.getId()).setAttribute("name",  fix(getDef(element).getName())).setAttribute("component", fix(getDef(element).getName())).setAttribute("role", "").setAttribute("desc", fix(getDef(element).getDescription())));
		}
		scenario.addContent(instances);
	}


	private ComponentElement getDef(ComponentRef element) {
		return ((ComponentElement)element.getContDef());
	}
	private Responsibility getDef(RespRef element) {
		return ((Responsibility)element.getRespDef());
	}

	private void addComponentDefinitions(Element scenarios) {
		Element components = new Element("components");
		components.addContent(new Element("component").setAttribute("name", "Environment").setAttribute("id", "C0"));
		for (Iterator iter = urnspec.getUrndef().getComponents().iterator(); iter.hasNext();) {
			ComponentElement element = (ComponentElement) iter.next();
			
			components.addContent(new Element("component").setAttribute("name", element.getName()).setAttribute("id", "C" + element.getId()));
		}
		scenarios.addContent(components);
	}
	
	
	public void save(IPath path)
	{
		try {
			
		    XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
			FileWriter writer = new FileWriter(path.toOSString());
			outputter.output(getScenarioDocument(), writer);
			writer.close();
		} catch (java.io.IOException e) {
		    e.printStackTrace();
		}
	}
}
