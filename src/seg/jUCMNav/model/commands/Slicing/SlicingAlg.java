package seg.jUCMNav.model.commands.Slicing;
import org.eclipse.emf.ecore.EObject;

import seg.jUCMNav.model.util.*;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.commands.delete.internal.RemovePathNodeCommand;
import grl.Actor;
import grl.ActorRef;
import grl.Belief;
import grl.Contribution;
import grl.ContributionChange;
import grl.ContributionContext;
import grl.ContributionContextGroup;
import grl.Decomposition;
import grl.Dependency;
import grl.ElementLink;
import grl.EvaluationStrategy;
import grl.GRLGraph;
import grl.GRLNode;
import grl.GRLspec;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.LinkRef;
import grl.StrategiesGroup;
import grl.kpimodel.IndicatorGroup;
import grl.kpimodel.KPIConversion;
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KPIInformationElementRef;
import grl.kpimodel.KPIModelLink;
import grl.kpimodel.KPIModelLinkRef;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

import javax.swing.JOptionPane;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UrnEditor;
import seg.jUCMNav.editparts.NodeConnectionEditPart;
import seg.jUCMNav.editparts.treeEditparts.LabelTreeEditPart;
import seg.jUCMNav.figures.PathNodeFigure;
import ucm.UCMspec;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.Anything;
import ucm.map.ComponentRef;
import ucm.map.Connect;
import ucm.map.DirectionArrow;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.FailurePoint;
import ucm.map.InBinding;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.OutBinding;
import ucm.map.PathNode;
import ucm.map.PluginBinding;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.Timer;
import ucm.map.UCMmap;
import ucm.map.WaitingPlace;
import ucm.scenario.EnumerationType;
import ucm.scenario.Initialization;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;
import urn.URNspec;
import urncore.Comment;
import urncore.Component;
import urncore.ComponentLabel;
import urncore.Concern;
import urncore.Condition;
import urncore.GRLmodelElement;
import urncore.IURNConnection;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.IURNNode;
import urncore.NodeLabel;
import urncore.Responsibility;
import urncore.UCMmodelElement;
import urncore.URNmodelElement;



















// color packages
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartListener;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
/*import org.eclipse.gmf.runtime.diagram.ui.figures.BorderedNodeFigure;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.draw2d.ui.figures.RoundedRectangleBorder;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;*/
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

/**
 *  slicing algorithm that performs backward static slicing technique given a resp ref as a criterion
 *
 * @author Taha
 *
 */
public class SlicingAlg {
	
	public static ArrayList<SliceColorList> colorList=new ArrayList<SliceColorList>();
	private IURNDiagram targetMap;
	private UCMNavMultiPageEditor editor;
	public static ArrayList<UCMmap> maps=new ArrayList<UCMmap>();
	 private EObject Criterion;
	  private AndFork stoppingFork;
	 private URNspec urn;
	  RemovePathNodeCommand removePathNode;
	  
	  
	  /**
	   * removing unrelated orfork branches usually results with orfork with empty 
	   * branch, we use this command to remove the empty orfork path path that 
	   */
	 
	  /**
	   * this commands removes all pathnodes of an unrelated orFork path/branch
	   */
	 
	  public ArrayList<String> baseVariables=new ArrayList<String>();
	    ArrayList<RemovePathNodeCommand> deletionList=new ArrayList<RemovePathNodeCommand>();
	    ArrayList<RespRef> deletedList=new ArrayList<RespRef>();
	    public ArrayList<String> criterionVariables=new ArrayList<String>();
	    private EditPartViewer viewer;
	    public static ArrayList<EObject> GreenedElements= new ArrayList<EObject>();
	    public static ArrayList<EObject> visitedNodes= new ArrayList<EObject>();
	    //this list will store all related concurrency branches into groups
	    public static ArrayList<ArrayList<NodeConnection>> concurrencyGroups=new ArrayList<ArrayList<NodeConnection>>();
	    public static ArrayList<RespRef> emptyRespRef=new ArrayList<RespRef>();
	    public static ArrayList<NodeConnection> UnrelatedOrForkbranches=new ArrayList<NodeConnection>();
	    public static ArrayList<NodeConnection> criterionForwardBranches=new ArrayList<NodeConnection>();
	    /**
	     * used to store which INs of stubs have not been traversed so they can be deleted 
	     * <em>(same as non traversed OrForks)</em>
	     */
	    public static ArrayList<NodeConnection> unrelatedStubINS=new ArrayList<NodeConnection>();
	    /**
	     * we need this to search Endpoints efficiently when removing empty paths
	     */
	    public static ArrayList<StartPoint> startpoints=new ArrayList<StartPoint>();
	    /**
	     * This list resulted from criterion forward check, storing the excluded forward paths of the criterion.
	     * we need this list since they are excluded branches & will be colored in black unlike {@link #concurrencyNodeConnections}  
	     */
	    public  static ArrayList<NodeConnection> criterionExcludedNC=new ArrayList<NodeConnection>();
	    public ArrayList <NodeConnection> forwardNodeConnections=new ArrayList<NodeConnection>();
	    /**
	     *  a list of all concurrency branches starting node connections
	     */
	    public static ArrayList<NodeConnection> concurrencyNodeConnections=new ArrayList<NodeConnection>();
	    private ArrayList<RespRef> relevantRespRefList=new ArrayList<RespRef>();
	    private ArrayList<RespRef> NotRelevantRespRefList=new ArrayList<RespRef>();
	    private SlicingAlg branch;
	    public static ArrayList<SlicingAlg> allPaths=new ArrayList<SlicingAlg>();
	    public static ArrayList<RespRef> allRelevantRespRefList=new ArrayList<RespRef>();
	    public static ArrayList<RespRef> allNotRelevantRespRefList=new ArrayList<RespRef>();
	    private NodeConnection startingNodeConnection;
	   public UnrelatedRespTree unrelatedTree=new UnrelatedRespTree();
	   public UnrelatedRespTree commonTree=new UnrelatedRespTree();
	    public boolean AndForkFlag=false;
	    public RemoveUnrelatedPaths removePaths;
/**
 * executes backward static slicing given a set of slicing variables
 * 
 *
 * @param startingNC the node connection from which traversal will start
 * @param baseVariables the basic dependency(criterion) variables <em>'passed to AndFork concurrent branches as their criterion variables'</em>
 */
public SlicingAlg( NodeConnection startingNodeConnection,ArrayList<String> baseVariables)
{
	criterionVariables.clear();
	this.startingNodeConnection=startingNodeConnection;
	forwardNodeConnections.clear();
	relevantRespRefList.clear();
	NotRelevantRespRefList.clear();
	if(baseVariables!=null)
	for(String var:baseVariables)
	this.baseVariables.add(var);

}
/**
 * executes backward slicing
 * @param criVar the list of criterion variables for this path 
 * @param startNC The node connection from which traversal starts
 * @param stubStack the parent stubs to which the map belongs <em> (if any or null )</em>
 * @param pathVisitedJoins the list of path visited Or/And joins <em>(used to detect loop)</em>
 * @return list of all variables related to slicing criterion
 * 
 */
public  ArrayList<String> executeAlg(ArrayList<String> criVar,NodeConnection startNC,Stack<Stub> stubStack, ArrayList<EObject> pathVisitedJoins, UnrelatedRespTree unrelatedTree,UnrelatedRespTree commonTree,boolean AndForkFlag )
{
	boolean stubEntryflag=false;
	Stack<Stub> currentStubStack=new Stack<Stub>();
	ArrayList<String> criterionVariables=new ArrayList<String>();
	for(String var:criVar)
		criterionVariables.add(var);
	 DataControlDep depAlg=new DataControlDep(criterionVariables);
	String expression,condition;
	ArrayList<EObject> currentVisitedJoins=new ArrayList<EObject>();
	NodeConnection currentNodeConnection=startNC;
	PathNode CurrentNode=(PathNode)currentNodeConnection.getSource();
	
	
	//fill currentVisitedJoins with previous/parent path visited joins
	for(EObject item:pathVisitedJoins)
		currentVisitedJoins.add(item);
	
	//fill the stubstack
	if(!stubStack.isEmpty())
	{
		for(Stub stub: stubStack)
	currentStubStack.push(stub);
	}	
	
	
	//start backward traversal and analyzing
	
	while(! (CurrentNode instanceof StartPoint) || !stubStack.isEmpty() )
	{
		//add the current node to the visited list
		
		//handling RespRef
		if(CurrentNode instanceof RespRef)
		{
			
			//this condition to avoid loop,respref should not equal the criterion indexed at 0
	if((Criterion instanceof RespRef && !((RespRef)CurrentNode).equals((RespRef)visitedNodes.get(0))) || (Criterion instanceof NodeConnection) || (Criterion instanceof EndPoint) )
			{
				if(!visitedNodes.contains((EObject)CurrentNode))
				visitedNodes.add(CurrentNode);
			 expression=((RespRef) CurrentNode).getRespDef().getExpression();
			if(expression!=null && ! expression.isEmpty())
			{
				depAlg.setExpression(expression);
				depAlg.analizeExpression();
				if(depAlg.Relevant)
				{
					//add the resp-ref to the relevantresp-ref list
					
					 if(!relevantRespRefList.contains((RespRef)CurrentNode))
					 {
						 relevantRespRefList.add((RespRef)CurrentNode);
						allRelevantRespRefList.add((RespRef)CurrentNode);
					}
				}
				//if it is not relevant, its added to the NotRelevantrespRef list
				// to be removed later from the model
				else 
				{
					unrelatedTree.unrelatedResp.add((RespRef)CurrentNode);
					if(AndForkFlag)
						commonTree.unrelatedResp.add((RespRef)CurrentNode);
					if(!NotRelevantRespRefList.contains((RespRef)CurrentNode))
					
					{
						NotRelevantRespRefList.add((RespRef)CurrentNode);
						allNotRelevantRespRefList.add((RespRef)CurrentNode);
						
					}
				}
			}
			
			//if it's a respref with an empty/null code expression
			// it's added to the emptyrespRef list
			else
				emptyRespRef.add((RespRef)CurrentNode);
		}
			//loop
			else
			{
				//updateCriterionVariables(criterionVariables);
				//return criterionVariables;
			}
			
		}
		
		
		//*** handling OrFork ***
		
		else if(CurrentNode instanceof OrFork)
		{
		if((Criterion instanceof NodeConnection && !(currentNodeConnection.equals((NodeConnection)visitedNodes.get(0)))) || (Criterion instanceof PathNode))
		{
			if(!visitedNodes.contains((EObject)CurrentNode))
			{
			//add it to the visited nodes
			visitedNodes.add(CurrentNode);
			//put the other links in the unrelatedOrforkbranches
			ArrayList<NodeConnection> temp=new ArrayList<NodeConnection>();
			for(Object link:CurrentNode.getSucc())
				temp.add((NodeConnection) link);
			//remove the current node connection
			temp.remove(currentNodeConnection);
			//add to the unrelatedOrFork list
			for(NodeConnection nc:temp)
				if(!UnrelatedOrForkbranches.contains(nc))
				UnrelatedOrForkbranches.add(nc);
			}
			//otherwise it's been visited before, so remove the current link from the list
			else
				UnrelatedOrForkbranches.remove(currentNodeConnection);
				
			//check if there is a condition
			condition=currentNodeConnection.getCondition().getExpression();
			if(condition!=null && !condition.isEmpty())
			{
				//add the condition variables to the criterionVariableList
				depAlg.addConditionVariables(condition);
			}
		}
	}
		//**** handling AndFork  ****
		
		else if(CurrentNode instanceof AndFork)
		{
			if(stoppingFork!=null && stoppingFork.equals((AndFork)CurrentNode))
			{
				updateCriterionVariables(criterionVariables);
				
				return criterionVariables;
			}
			if(!visitedNodes.contains((EObject)CurrentNode ))
			{
			
				
				visitedNodes.add((EObject)CurrentNode);
				forwardNodeConnections.clear();
				ArrayList<NodeConnection> temp= new ArrayList<NodeConnection>();
				
				for(Object o: CurrentNode.getSucc())
					temp.add((NodeConnection) o);
				
				//remove the current node connection from the list
				// we discard the current path that we are traversing in forwardCheck
				temp.remove(currentNodeConnection);
				
				ArrayList<EObject> visiteditems=new ArrayList<EObject>();
				visiteditems.add(CurrentNode);
				for(NodeConnection nc:temp)
				{
					
					forwardCheck((NodeConnection) nc, visiteditems);
					
				}
				
				if(!forwardNodeConnections.isEmpty())
				{
				//add the new concurrency NC resulted from forward check to the global list 
				for(NodeConnection nc: forwardNodeConnections)
					concurrencyNodeConnections.add(nc);
				  
				}
				//System.out.println("Andfork:"+ ((AndFork)CurrentNode).getName()+"Links="+forwardNodeConnections.size() );
			
				Concurrency_Grouping(startingNodeConnection);
				
				AndForkFlag=true;
				this.AndForkFlag=true;
				//create and execute the new slicing branches
				Iterator<NodeConnection> iterat=forwardNodeConnections.iterator();
				SlicingAlg sl;
				while(iterat.hasNext())
				{
					NodeConnection nc=iterat.next();
					sl=new SlicingAlg(nc,baseVariables);
					sl.setCriterion(Criterion);
					//the current Andfork will be the element where the slicing instance stops
					sl.setStoppingFork((AndFork) CurrentNode);
					sl.executeAlg(baseVariables, nc, currentStubStack, new ArrayList<EObject>(),sl.unrelatedTree,sl.commonTree,false);
					//update the current criterion variables with the new branch criterion variables' list
					//updateCriterionVariables(sl.getCriterionVariables());
					//depAlg.updateCriterionVariables(sl.getCriterionVariables());
					
					//add the slicing branch to the global list
					allPaths.add(sl);
				}
					
				}
				
				
			
			
			//otherwise, it's been visited before, we return
			else
			{
				updateCriterionVariables(criterionVariables);
				
				return criterionVariables;
				
				
			}
		}
		
		
		
		// handling OrJoin
		
		else if(CurrentNode instanceof OrJoin)
		{
			//this condition is used to avoid loop, written only for Orjoin, and AndJoin
			if(!currentVisitedJoins.contains((EObject)CurrentNode))
			{
				//add the OrJoin to pathVisitedJoins list, next time the loop will be detected
				//using this list
				currentVisitedJoins.add((EObject)CurrentNode);
				
			//add it to the visited nodes if it's not already visited before
			if(!visitedNodes.contains((EObject)CurrentNode))
			visitedNodes.add((EObject)CurrentNode);
		
			EList orJoinLinks=CurrentNode.getPred();
			//update the list of our criterionVariables with the latest variables included recently
			criterionVariables=depAlg.getCriterionVariables();
		
			ArrayList<String> tempList=new ArrayList<String>();
			UnrelatedRespTree child;
			UnrelatedRespTree commonchild;
			for(String var:criterionVariables)
				tempList.add(var);
			//Add the source of each orjoin link to its corresponding pathVisitedJoins
			//before passing it as a parameter in executeALg
			
			ArrayList<EObject> tempVisitedJoins=new ArrayList<EObject>();
			NodeConnection tempNC;
			for(Object link:orJoinLinks)
			{
				//****** New Code*****
				tempVisitedJoins.clear();
				tempNC=(NodeConnection) link;
				for(EObject node:currentVisitedJoins)
					tempVisitedJoins.add(node);
				//now add the source
				tempVisitedJoins.add((EObject)tempNC.getSource());
				
				//*****End of new code******
				child=new UnrelatedRespTree();
				commonchild=new UnrelatedRespTree();
				unrelatedTree.ChildrednPaths.add(child);
				commonTree.ChildrednPaths.add(commonchild);
				//starting new slicing alg for each branch
			ArrayList<String> result=	executeAlg(tempList,(NodeConnection)link, currentStubStack,tempVisitedJoins,child,commonchild,AndForkFlag);
			   updateCriterionVariables(result);
			   
			}
			
			checkInconsistency();
			return criterionVariables;
		}
			else
			{        //Loop exists
				
				//************New Code***************
				EList orJoinLinks=CurrentNode.getPred();
				ArrayList<EObject> tempVisitedJoins=new ArrayList<EObject>();
			   for(Object obj:orJoinLinks)
			   {
				   NodeConnection nc=(NodeConnection) obj;
				   if(!currentVisitedJoins.contains((EObject)nc.getSource()))
				   {
					   tempVisitedJoins.clear();
					   for(EObject node:currentVisitedJoins)
							tempVisitedJoins.add(node);
					 //now add the source
					   
					   tempVisitedJoins.add((EObject) nc.getSource());
						//update the list of our criterionVariables with the latest variables included recently
						criterionVariables=depAlg.getCriterionVariables();
					
						ArrayList<String> tempList=new ArrayList<String>();
						UnrelatedRespTree child;
						UnrelatedRespTree commonchild;
						for(String var:criterionVariables)
							tempList.add(var);				
							child=new UnrelatedRespTree();
							commonchild=new UnrelatedRespTree();
							unrelatedTree.ChildrednPaths.add(child);
							commonTree.ChildrednPaths.add(commonchild);
							//starting new slicing alg for each branch
						ArrayList<String> result=	executeAlg(tempList,(NodeConnection) nc, currentStubStack,tempVisitedJoins,child,commonchild,AndForkFlag);
						   updateCriterionVariables(result);
						   
						
						
						checkInconsistency();
						return criterionVariables;
					}
				   }
			   System.out.println("Loop break at:"+CurrentNode.getName());
				updateCriterionVariables(criterionVariables);
				return criterionVariables;
			   }
				
				
				
			}
		
		
		
		
		// handling AndJoin
		else if(CurrentNode instanceof AndJoin)
		{
			//this condition is used to avoid loop, written only for Orjoin, and AndJoin
			if(!currentVisitedJoins.contains((EObject)CurrentNode))
			{
				
				//add the OrJoin to pathVisitedJoins list, next time the loop will be detected
				//using this list
				currentVisitedJoins.add((EObject)CurrentNode);
				
		
			//add it to the visited nodes if it's not already visited before
			if(!visitedNodes.contains((EObject)CurrentNode))
			visitedNodes.add((EObject)CurrentNode);
			//add new concurrency group to the global list 
			ArrayList<NodeConnection> newGroup=new ArrayList<NodeConnection>();
			for(Object nc:CurrentNode.getPred())
			{
				newGroup.add((NodeConnection) nc);
			}
			
			concurrencyGroups.add(newGroup);
			
			//update the list of our criterionVariables with the latest variables included recently
			criterionVariables=depAlg.getCriterionVariables();
			SlicingAlg sl;
			for(Object o:CurrentNode.getPred())
			{
				NodeConnection nc=(NodeConnection) o;
				unrelatedTree.childrenConcurrencyPaths.add((NodeConnection) o);
				//used for the concurrency solution
				if(AndForkFlag)
					commonTree.childrenConcurrencyPaths.add((NodeConnection) o);
				sl=new SlicingAlg(nc,criterionVariables);
				sl.setCriterion(Criterion);
				sl.executeAlg(criterionVariables, nc, currentStubStack, new ArrayList<EObject>(),sl.unrelatedTree,sl.commonTree,false);
				//updateCriterionVariables(sl.getCriterionVariables());
				updateCriterionVariables(sl.getCriterionVariables());
				//add the instance to the global list 
				allPaths.add(sl);
			}
			/*
			//create new slicing instances 
			SlicingAlg sl;
			Iterator<NodeConnection> iterator=newGroup.iterator();
			while(iterator.hasNext())
			{
				NodeConnection nc=iterator.next();
				sl=new SlicingAlg(nc);
				sl.executeAlg(criterionVariables, nc, cuurentStub, new ArrayList<EObject>());
				updateCriterionVariables(sl.getCriterionVariables());
				//add the instance to the global list 
				allPaths.add(sl);
			}
			*/
			//updateCriterionVariables(criterionVariables);
			return criterionVariables;
			}
		         // otherwise it's a loop
				else
				{
					System.out.println("Loop break at:"+CurrentNode.getName());
					updateCriterionVariables(criterionVariables);
					return criterionVariables;
				}
		}
		
		//******* Handling Timer && WaitingPlace******
		
		//Timer is not a pathNode type, it extends a waitingPlace
		//how to recognize them depends on the number of successor links
		//Timer has 2 succ links while waitingPlace has only one
		//when SC resides within a timeout branch, it will require special handling since
		//removing the main link will cause the timer to be removed,
		//and it will break the path 
				else if (CurrentNode instanceof WaitingPlace)
				{
					
					if((Criterion instanceof NodeConnection && !(currentNodeConnection.equals((NodeConnection)visitedNodes.get(0)))) || (Criterion instanceof PathNode))	
					{
					if(!visitedNodes.contains((EObject)CurrentNode))
					{
						visitedNodes.add((EObject)CurrentNode);
						//put the other links in the unrelatedOrforkbranches
						ArrayList<NodeConnection> temp=new ArrayList<NodeConnection>();
						for(Object link:CurrentNode.getSucc())
							temp.add((NodeConnection) link);
						
						//remove the current node connection
						temp.remove(currentNodeConnection);
						//add to the unrelatedOrFork list
						
						
							//this means it's a timer not a waitingPlace
						for(NodeConnection nc:temp)
							if(!UnrelatedOrForkbranches.contains(nc))
							UnrelatedOrForkbranches.add(nc);
						
						}
				//otherwise it's been visited before, so remove the current link from the list
					//if it's a timer, remove the current link,
					//otherwise it's a waitingPlace having one succ link, 
					//and there is no need to check the list
					else 
							
							UnrelatedOrForkbranches.remove(currentNodeConnection);
							
					
					//check if there is a condition
					condition=currentNodeConnection.getCondition().getExpression();
					if(condition!=null && !condition.isEmpty())
					{
						//add the condition variables to the criterionVariableList
						depAlg.addConditionVariables(condition);
					}
				}
					
			}
		
		
			
		// handling StartPoint
		else if(CurrentNode instanceof StartPoint)
		{
			condition=((StartPoint)CurrentNode).getPrecondition().getExpression();
			if(condition!=null && !condition.isEmpty())
			{
				//add the condition variables to the criterionVariableList
				depAlg.addConditionVariables(condition);
			}
			if(!visitedNodes.contains(CurrentNode))
			{
				if(!maps.contains((UCMmap)CurrentNode.getDiagram()))
					maps.add((UCMmap)CurrentNode.getDiagram());
			visitedNodes.add((EObject)CurrentNode);
			startpoints.add((StartPoint)CurrentNode );
			}
			if(!currentStubStack.isEmpty())
			{
				Stub stub=currentStubStack.pop();
				//get the binding
				for(Object binding:stub.getBindings())
				{
					//get the IN for this binding 
					for(Object o:((PluginBinding) binding).getIn())
					{
						InBinding IN= (InBinding) o;
						if(IN.getStartPoint().equals((StartPoint)CurrentNode))
						{	
							NodeConnection stubNC=IN.getStubEntry();
							
							//******remove it from unrelated StubINS list**********
							unrelatedStubINS.remove(stubNC);
							ArrayList<String> tempList=new ArrayList<String>();
							UnrelatedRespTree child;
							UnrelatedRespTree commonchild;
							for(String var:criterionVariables)
								tempList.add(var);
							
							
								child=new UnrelatedRespTree();
								commonchild=new UnrelatedRespTree();
								unrelatedTree.ChildrednPaths.add(child);
								commonTree.ChildrednPaths.add(commonchild);
							ArrayList<String> result=	executeAlg(tempList,stubNC, currentStubStack,currentVisitedJoins,child,commonchild,AndForkFlag);
							   updateCriterionVariables(result);
							
					}
					
				}
			}
			
				//After checking all bindings,end the current recursive call to executeAlg
				return criterionVariables;
		}
			//if the stub stack is empty
			else
			{
				updateCriterionVariables(criterionVariables);
				return criterionVariables;
			}
		}
		
		// Handling EndPoint
		else if (CurrentNode instanceof EndPoint)
		{
			
			visitedNodes.add((EObject)CurrentNode);
		}
		
		
		// Handling Stub
		else if (CurrentNode instanceof Stub)
		{
			//since we might have a loop,or different paths are joined to single one , the stub might be visited
			//more than once
			if(!visitedNodes.contains(CurrentNode))
			{
			visitedNodes.add((EObject)CurrentNode);
			//put the other links in the unrelatedOrforkbranches
			ArrayList<NodeConnection> temp=new ArrayList<NodeConnection>();
			for(Object link:CurrentNode.getSucc())
				temp.add((NodeConnection) link);
			//remove the current node connection
			temp.remove(currentNodeConnection);
			//add to the unrelatedOrFork list
			for(NodeConnection nc:temp)
				if(!UnrelatedOrForkbranches.contains(nc))
				UnrelatedOrForkbranches.add(nc);
			//put unrelatedStubINS nodeConnections in the unrelated list(same as its OUTs)
			temp.clear();
			for(Object link:CurrentNode.getPred())
				temp.add((NodeConnection) link);
			//remove the current node connection
			temp.remove(currentNodeConnection);
			//add to the unrelated stub INs list
			for(NodeConnection nc:temp)
				if(!unrelatedStubINS.contains(nc))
					unrelatedStubINS.add(nc);
			}
			//otherwise, it's been visited before remove the current nc from unrelated list
			else
				UnrelatedOrForkbranches.remove(currentNodeConnection);
			//add the stub to the stack
			currentStubStack.push((Stub)CurrentNode);
			Stub stub=(Stub)CurrentNode;
			ArrayList<String> allStubsVariables=new ArrayList<String>();
			//put the current variables to the allStubsVariables
			for(String var:depAlg.getCriterionVariables())
				allStubsVariables.add(var);
			ArrayList<String> stubVariables=new ArrayList<String>();
			for(Object binding:stub.getBindings())
			{
				//get the OUT for this binding 
				for(Object o:((PluginBinding) binding).getOut())
				{
					OutBinding out=(OutBinding) o;
				   
					if(out.getStubExit().equals(currentNodeConnection))
					{
						stubVariables.clear();
						//filling the variables
						for(String var: allStubsVariables)
							stubVariables.add(var);
						EndPoint stubEntry=out.getEndPoint();
						//****** put the condition of the stubentry end point variable(end point)
						IURNDiagram map=stubEntry.getDiagram();
						if(!maps.contains((UCMmap)map))
							maps.add((UCMmap)map);
							System.out.println("Map included: "+ ((UCMmap)map).getName()+((UCMmap)map).getId());
						//get the end point and add it if it's not added before
						if(!visitedNodes.contains((EObject)stubEntry))
							visitedNodes.add((EObject)stubEntry);
						NodeConnection stubNC=(NodeConnection)stubEntry.getPred().get(0);
						
						UnrelatedRespTree child;
						UnrelatedRespTree commonchild;
						//get the Postcondition variables and add it to the criterion variables' list
						condition=((EndPoint)stubEntry).getPostcondition().getExpression();
						
						if(condition!=null && !condition.isEmpty())
						{
							
							//add the condition variables to the list related to the stub
							for(String conditionVar: depAlg.getConditionVariables(condition))
								stubVariables.add(conditionVar);
							
						}
						
						
						
						
							child=new UnrelatedRespTree();
							commonchild=new UnrelatedRespTree();
							unrelatedTree.ChildrednPaths.add(child);
							commonTree.ChildrednPaths.add(commonchild);
						ArrayList<String> result=	executeAlg(stubVariables,stubNC, currentStubStack,currentVisitedJoins,child,commonchild,false);
						   updateCriterionVariables(result);				
					}
				}
			}
			//After checking all bindings,end the current recursive call to executeAlg
			return criterionVariables;
		}
		
		
		//other elements like emptypoints, and direction arrow has no effect, just add
		//them to the visited list
		else
			visitedNodes.add((EObject)CurrentNode);
		
		             // continue traversal
		//Note: pathnodes with more than one pred-link (ex:OrJoin,AndJoin)are acually sent as new slicing alg
		// for each link, they are handeled in the aforemoentioned if-else cases
		//Hence what is left will be nodes with only one pred-link
		//that's why we get the first element at index 0 since we're sure it's the only link
		//in the getPred() list returned.
		
		//if we already reached start point and we are inside a stub,
		//that means the current node connection has already been changed to exit the stub from its stubEntry
		
		currentNodeConnection=(NodeConnection)CurrentNode.getPred().get(0);
		//get the next backward pathnode
		CurrentNode=(PathNode) currentNodeConnection.getSource();
		

	}  // ******** end of while loop *******
	
	if(CurrentNode instanceof StartPoint && !startpoints.contains((StartPoint)CurrentNode))
	{
		visitedNodes.add((EObject)CurrentNode);
		condition=((StartPoint)CurrentNode).getPrecondition().getExpression();
		if(condition!=null && !condition.isEmpty())
		{
			//add the condition variables to the criterionVariableList
			depAlg.addConditionVariables(condition);
		}
		startpoints.add((StartPoint)CurrentNode);
		if(!maps.contains((UCMmap)CurrentNode.getDiagram()))
			maps.add((UCMmap) CurrentNode.getDiagram());
	}
		checkInconsistency();
	// update the criterionVariable list
	criterionVariables=depAlg.getCriterionVariables();
	updateCriterionVariables(criterionVariables);
 return criterionVariables;
}

/**
 * executes backward slicing.Unlike {@link #executeAlg(ArrayList, NodeConnection, Stack, ArrayList, UnrelatedRespTree, UnrelatedRespTree, boolean)}, 
 * This methods executes normal backward traversal
 * without data and/or control dependency computations.This method is used when no criterion variables are selected
 * @param startNC The node connection from which traversal starts
 * @param stubStack used to enter and exit stubs
 * @param pathVisitedJoins visited nodes within a particular path<em>(used to detect loops)</em>
 */
public void  executeAlg(NodeConnection startNC,Stack<Stub> stubStack, ArrayList<EObject> pathVisitedJoins )
{
	boolean stubEntryflag=false;
	Stack<Stub> currentStubStack=new Stack<Stub>();
	ArrayList<EObject> currentVisitedJoins=new ArrayList<EObject>();
	NodeConnection currentNodeConnection=startNC;
	PathNode CurrentNode=(PathNode)currentNodeConnection.getSource();
	
	
	//fill currentVisitedJoins with previous/parent path visited joins
	for(EObject item:pathVisitedJoins)
		currentVisitedJoins.add(item);
	
	//fill the stubstack
	if(!stubStack.isEmpty())
	{
		for(Stub stub: stubStack)
	currentStubStack.push(stub);
	}	
	
	
	//start backward traversal and analyzing
	
	while(! (CurrentNode instanceof StartPoint) || !stubStack.isEmpty() )
	{
		//add the current node to the visited list
		
		//handling RespRef
		if(CurrentNode instanceof RespRef)
			
		{
			//this condition to avoid loop,respref should not equal the criterion indexed at 0
if((Criterion instanceof RespRef && !((RespRef)CurrentNode).equals((RespRef)visitedNodes.get(0))) || (Criterion instanceof NodeConnection) || (Criterion instanceof EndPoint))
			
			{
				visitedNodes.add(CurrentNode);
			}
			//loop
			else	
				return ;	
		}
		
		
		//*** handling OrFork ***
		
		else if(CurrentNode instanceof OrFork)
		{
			if((Criterion instanceof NodeConnection && !(currentNodeConnection.equals((NodeConnection)visitedNodes.get(0)))) || (Criterion instanceof PathNode))
			if(!visitedNodes.contains((EObject)CurrentNode))
			{
			//add it to the visited nodes
			visitedNodes.add(CurrentNode);
			//put the other links in the unrelatedOrforkbranches
			ArrayList<NodeConnection> temp=new ArrayList<NodeConnection>();
			for(Object link:CurrentNode.getSucc())
				temp.add((NodeConnection) link);
			//remove the current node connection
			temp.remove(currentNodeConnection);
			//add to the unrelatedOrFork list
			for(NodeConnection nc:temp)
				if(!UnrelatedOrForkbranches.contains(nc))
				UnrelatedOrForkbranches.add(nc);
			}
			//otherwise it's been visited before, so remove the current link from the list
			else
				UnrelatedOrForkbranches.remove(currentNodeConnection);
		}
		
		//**** handling AndFork  ****
		
		else if(CurrentNode instanceof AndFork)
		{
			if(!visitedNodes.contains((EObject)CurrentNode ))
			{
			
				
				visitedNodes.add((EObject)CurrentNode);
				forwardNodeConnections.clear();
				ArrayList<NodeConnection> temp= new ArrayList<NodeConnection>();
				
				for(Object o: CurrentNode.getSucc())
					temp.add((NodeConnection) o);
				
				//remove the current node connection from the list
				// we discard the current path that we are traversing in forwardCheck
				temp.remove(currentNodeConnection);
				
				ArrayList<EObject> visiteditems=new ArrayList<EObject>();
				visiteditems.add(CurrentNode);
				for(NodeConnection nc:temp)
				{
					
					forwardCheck((NodeConnection) nc, visiteditems);
					
				}
				
				if(!forwardNodeConnections.isEmpty())
				{
				//add the new concurrency NC resulted from forward check to the global list 
				for(NodeConnection nc: forwardNodeConnections)
					concurrencyNodeConnections.add(nc);
				  
				}
				//System.out.println("Andfork:"+ ((AndFork)CurrentNode).getName()+"Links="+forwardNodeConnections.size() );
			
				
				AndForkFlag=true;
				this.AndForkFlag=true;
				//create and execute the new slicing branches
				Iterator<NodeConnection> iterat=forwardNodeConnections.iterator();
				SlicingAlg sl;
				while(iterat.hasNext())
				{
					NodeConnection nc=iterat.next();
					sl=new SlicingAlg(nc,null);
					sl.setCriterion(Criterion);
					//the current Andfork will be the element where the slicing instance stops
					sl.setStoppingFork((AndFork) CurrentNode);
					sl.executeAlg(nc, currentStubStack, new ArrayList<EObject>());
					//update the current criterion variables with the new branch criterion variables' list
					//updateCriterionVariables(sl.getCriterionVariables());
					//depAlg.updateCriterionVariables(sl.getCriterionVariables());
				}
					
				}
				
				
			
			
			//otherwise, it's been visited before, we return
			else
			
				
				return ;
		}
		
		
		
		// handling OrJoin
		
		else if(CurrentNode instanceof OrJoin)
		{
			//this condition is used to avoid loop, written only for Orjoin, and AndJoin
			if(!currentVisitedJoins.contains((EObject)CurrentNode))
			{
				//add the OrJoin to pathVisitedJoins list, next time the loop will be detected
				//using this list
				currentVisitedJoins.add((EObject)CurrentNode);
				
			//add it to the visited nodes if it's not already visited before
			if(!visitedNodes.contains((EObject)CurrentNode))
			visitedNodes.add((EObject)CurrentNode);
		
			EList orJoinLinks=CurrentNode.getPred();
			//update the list of our criterionVariables with the latest variables included recently
			
		
			for(Object link:orJoinLinks)
			{
				//starting new slicing alg for each branch
			executeAlg((NodeConnection)link, currentStubStack,currentVisitedJoins);
			   
			   
			}
			
			
			return;
		}
			//otherwise it's a loop
			else
			{
				System.out.println("Loop break at:"+CurrentNode.getName());
				return ;
			}
		}
		
		
		
		// handling AndJoin
		else if(CurrentNode instanceof AndJoin)
		{
			//this condition is used to avoid loop, written only for Orjoin, and AndJoin
			if(!currentVisitedJoins.contains((EObject)CurrentNode))
			{
				
				//add the OrJoin to pathVisitedJoins list, next time the loop will be detected
				//using this list
				currentVisitedJoins.add((EObject)CurrentNode);
				
		
			//add it to the visited nodes if it's not already visited before
			if(!visitedNodes.contains((EObject)CurrentNode))
			visitedNodes.add((EObject)CurrentNode);
			//add new concurrency group to the global list 
			SlicingAlg sl;
			for(Object o:CurrentNode.getPred())
			{
				NodeConnection nc=(NodeConnection) o;
				//used for the concurrency solution
				sl=new SlicingAlg(nc,null);
				sl.setCriterion(Criterion);
				sl.executeAlg( nc, currentStubStack, new ArrayList<EObject>());
			}
			return ;
			}
		         // otherwise it's a loop
				else
				{
					System.out.println("Loop break at:"+CurrentNode.getName());
					
					return ;
				}
		}
		
		//******* Handling Timer && WaitingPlace******
				//Timer is not a pathNode type, it extends a waitingPlace
				//how to recognize them depends on the number of successor links
				//Timer has 2 succ links while waitingPlace has only one
				//when SC resides within a timeout branch, it will require special handling since
				//removing the main link will cause the timer to be removed,
				//and it will break the path 
						else if (CurrentNode instanceof WaitingPlace)
						{
							System.out.println("Timer found here");
							 //used to detect loop
							if((Criterion instanceof NodeConnection && !(currentNodeConnection.equals((NodeConnection)visitedNodes.get(0)))) || (Criterion instanceof PathNode))
							if(!visitedNodes.contains((EObject)CurrentNode))
							{
								visitedNodes.add((EObject)CurrentNode);
								//put the other links in the unrelatedOrforkbranches
								ArrayList<NodeConnection> temp=new ArrayList<NodeConnection>();
								for(Object link:CurrentNode.getSucc())
									temp.add((NodeConnection) link);
								
								//remove the current node connection
								temp.remove(currentNodeConnection);
								//add to the unrelatedOrFork list
								
								
									//this means it's a timer not a waitingPlace
								for(NodeConnection nc:temp)
									if(!UnrelatedOrForkbranches.contains(nc))
									UnrelatedOrForkbranches.add(nc);
								
								}
						//otherwise it's been visited before, so remove the current link from the list
							//if it's a timer, remove the current link,
							//otherwise it's a waitingPlace having one succ link, 
							//and there is no need to check the list
							else 
									
									UnrelatedOrForkbranches.remove(currentNodeConnection);
						}
		
		// handling StartPoint
		else if(CurrentNode instanceof StartPoint)
		{
			if(!visitedNodes.contains(CurrentNode))
			{
				if(!maps.contains((UCMmap)CurrentNode.getDiagram()))
					maps.add((UCMmap)CurrentNode.getDiagram());
			visitedNodes.add((EObject)CurrentNode);
			startpoints.add((StartPoint)CurrentNode );
			}
			if(!currentStubStack.isEmpty())
			{
				Stub stub=currentStubStack.pop();
				//get the binding
				for(Object binding:stub.getBindings())
				{
					//get the IN for this binding 
					for(Object o:((PluginBinding) binding).getIn())
					{
						InBinding IN= (InBinding) o;
						if(IN.getStartPoint().equals((StartPoint)CurrentNode))
						{	
							NodeConnection stubNC=IN.getStubEntry();
							
							//******remove it from unrelated StubINS list**********
							unrelatedStubINS.remove(stubNC);
							
								executeAlg(stubNC, currentStubStack,currentVisitedJoins);
							 
							
					}
					
				}
			}
			
				//After checking all bindings,end the current recursive call to executeAlg
				return ;
		}
			//if the stub stack is empty
			else
				return ;
		}
		
		// Handling EndPoint
		else if (CurrentNode instanceof EndPoint)
		{
			visitedNodes.add((EObject)CurrentNode);
		}
		
		
		// Handling Stub
		else if (CurrentNode instanceof Stub)
		{
			//since we might have a loop,or different paths are joined to single one , the stub might be visited
			//more than once
			if(!visitedNodes.contains(CurrentNode))
			{
			visitedNodes.add((EObject)CurrentNode);
			//put the other links in the unrelatedOrforkbranches
			ArrayList<NodeConnection> temp=new ArrayList<NodeConnection>();
			for(Object link:CurrentNode.getSucc())
				temp.add((NodeConnection) link);
			//remove the current node connection
			temp.remove(currentNodeConnection);
			//add to the unrelatedOrFork list
			for(NodeConnection nc:temp)
				if(!UnrelatedOrForkbranches.contains(nc))
				UnrelatedOrForkbranches.add(nc);
			//put unrelatedStubINS nodeConnections in the unrelated list(same as its OUTs)
			temp.clear();
			for(Object link:CurrentNode.getPred())
				temp.add((NodeConnection) link);
			//remove the current node connection
			temp.remove(currentNodeConnection);
			//add to the unrelated stub INs list
			for(NodeConnection nc:temp)
				if(!unrelatedStubINS.contains(nc))
					unrelatedStubINS.add(nc);
			}
			//otherwise, it's been visited before remove the current nc from unrelated list
			else
				UnrelatedOrForkbranches.remove(currentNodeConnection);
			//add the stub to the stack
			currentStubStack.push((Stub)CurrentNode);
			Stub stub=(Stub)CurrentNode;
			for(Object binding:stub.getBindings())
			{
				//get the IN for this binding 
				for(Object o:((PluginBinding) binding).getOut())
				{
					OutBinding out=(OutBinding) o;
				
					if(out.getStubExit().equals(currentNodeConnection))
					{
						EndPoint stubEntry=out.getEndPoint();
						IURNDiagram map=stubEntry.getDiagram();
						
						//get the end point and add it if it's not added before
						if(!visitedNodes.contains((EObject)stubEntry))
							visitedNodes.add((EObject)stubEntry);
						NodeConnection stubNC=(NodeConnection)stubEntry.getPred().get(0);
							executeAlg(stubNC, currentStubStack,currentVisitedJoins);
						   				
					}
				}
			}
			//After checking all bindings,end the current recursive call to executeAlg
			return ;
		}
		
		
		//other elements like emptypoints, and direction arrow has no effect, just add
		//them to the visited list
		else
			if(!visitedNodes.contains((EObject)CurrentNode))
			visitedNodes.add((EObject)CurrentNode);
		
		             // continue traversal
		//Note: pathnodes with more than one pred-link (ex:OrJoin,AndJoin)are acually sent as new slicing alg
		// for each link, they are handeled in the aforemoentioned if-else cases
		//Hence what is left will be nodes with only one pred-link
		//that's why we get the first element at index 0 since we're sure it's the only link
		//in the getPred() list returned.
		
		//if we already reached start point and we are inside a stub,
		//that means the current node connection has already been changed to exit the stub from its stubEntry
		
		currentNodeConnection=(NodeConnection)CurrentNode.getPred().get(0);
		//get the next backward pathnode
		CurrentNode=(PathNode) currentNodeConnection.getSource();
		

	}  // ******** end of while loop *******
	
	if(CurrentNode instanceof StartPoint && !startpoints.contains((StartPoint)CurrentNode))
	{
		visitedNodes.add((EObject)CurrentNode);
		startpoints.add((StartPoint)CurrentNode);
		if(!maps.contains((UCMmap)CurrentNode.getDiagram()))
			maps.add((UCMmap) CurrentNode.getDiagram());
	}
		
 return ;
}


/**
 * this method add the relevant and the NotRelevant variables lists for each
 * path branch to the parent lists of their parent path 
 * it also removes the contradiction that happens when branches are joined 
 * at some point(ex using OrJoin-OrFork circle, or AndJoin-AndFork circle) into one path and since each branch will have different citerion variable lists
 * resulted from its traversal; one pathnode might be Not relevant while it's 
 * relevant to the other branch.Note: this does not solve the concurrency problem
 */

 public void checkInconsistency()
 {
	
	//solving the contradiction by removing any respref, that is contained in the
	//relevant list, from the NotRelevant List so that it is not deleted
	for(RespRef item:relevantRespRefList)
		NotRelevantRespRefList.removeAll(Collections.singleton(item));
	for(RespRef item:allRelevantRespRefList)
		allNotRelevantRespRefList.removeAll(Collections.singleton(item));
	
 }
/**
 * After executing the slicing algorithm, this method is called to remove the unrelated
 * resp-refs, Unrelated/non-traversed OrFork & Timer paths, and all unrelated PathNodes/Paths that come after the slicing criterion  
 * @param with remove empty resp-ref
 */
 public void removeUnrelatedElements(Boolean removeEmptyResp)
 {
	
	
	 if(!allNotRelevantRespRefList.isEmpty())
		 {
			 removePathNode=new RemovePathNodeCommand(allNotRelevantRespRefList.get(0),null);
		 allNotRelevantRespRefList.remove(0);
	 if(!allNotRelevantRespRefList.isEmpty())
	 {
	 for(RespRef respref:allNotRelevantRespRefList)
	 
		 removePathNode.add(new RemovePathNodeCommand(respref,null));
	 }
	 removePathNode.execute();
		 }
	 //remove empty respref
	 if(removeEmptyResp)
	 {
	 if(!emptyRespRef.isEmpty())
	 {
		 removePathNode=new RemovePathNodeCommand(emptyRespRef.get(0),null);
		 emptyRespRef.remove(0);
		 if(!emptyRespRef.isEmpty())
		 {
		 for(RespRef respref:emptyRespRef)
		 
			 removePathNode.add(new RemovePathNodeCommand(respref,null));
		 }
		 removePathNode.execute();
	 }
	 }
	 
	 // Here, we remove unrelated/untraversed paths as well as unrelated path nodes coming after the slicing criterion
	if(Criterion instanceof RespRef)
	{
	 removePaths=new RemoveUnrelatedPaths((RespRef) getCriterion());
	//set the viewer
	 removePaths.setViewer(viewer);
	 
	//first , remove what comes after the slicing criterion and elements between criterion and AndJoin 
		 //(if criterion resides within a concurrency path)
		removePaths.removeElements( criterionExcludedNC);
		 
	 //then,removing unrelated OrFork/Timer/Stubs branches
		//first saving all forks and stubs to be used later
		ArrayList<PathNode> orforks_Stubs_Timers=new ArrayList<PathNode>();
			for(NodeConnection nc: UnrelatedOrForkbranches)
			{
				if(!orforks_Stubs_Timers.contains(nc.getSource()))
					orforks_Stubs_Timers.add( (PathNode)nc.getSource());
			}
		 removePaths.removeOrForkBranches(startpoints,UnrelatedOrForkbranches);
		 //clean maps
		 
        //remove empty paths
		 removePaths.removeEmptyPaths(orforks_Stubs_Timers);
		
		 //finally save the changes
		 IWorkbench workbench= PlatformUI.getWorkbench();
         IWorkbenchPage page=	 workbench.getActiveWorkbenchWindow().getActivePage();
         page.getActiveEditor().doSave(new NullProgressMonitor());
		 //********* handling stub INS ***************
		 //Here , we handle un-traversed stub INs branches (same way as non-traversed Orfork & stub-OUTS branches)
		 orforks_Stubs_Timers.clear();
		//first saving all stubs to be used later
		 for(NodeConnection nc: unrelatedStubINS)
			{
				if(!orforks_Stubs_Timers.contains(nc.getTarget()))
					orforks_Stubs_Timers.add( (PathNode)nc.getTarget());
			}
		
		 removePaths.removeUnrelatedStubINS(startpoints,unrelatedStubINS );
		//remove empty paths
		 removePaths.removeStubINsEmptyPaths(orforks_Stubs_Timers);
		 //*****************End of Stub INS handling******************************
		  
		 //remove unrelated maps
		 removePaths.removeMaps(urn,maps);
	     removePaths.removeComponentRefs();
		 //finally save the changes
	 workbench= PlatformUI.getWorkbench();
         page=	 workbench.getActiveWorkbenchWindow().getActivePage();
         page.getActiveEditor().doSave(new NullProgressMonitor());
	 
       //refresh display
 		editor.recreatePages();
 		editor.setActivePage(targetMap);
	}
	
	else if(Criterion instanceof EndPoint)
	{
	 removePaths=new RemoveUnrelatedPaths((EndPoint)Criterion);	
	//set the viewer
		 removePaths.setViewer(viewer);
		 //remove unrelated OrFork/Timer/Stubs branches
			//first saving all forks and stubs to be used later
			ArrayList<PathNode> orforks_Stubs_Timers=new ArrayList<PathNode>();
				for(NodeConnection nc: UnrelatedOrForkbranches)
				{
					if(!orforks_Stubs_Timers.contains(nc.getSource()))
						orforks_Stubs_Timers.add( (PathNode)nc.getSource());
				}
			 removePaths.removeOrForkBranches(startpoints,UnrelatedOrForkbranches);
			 //clean maps
			 
	        //remove empty paths
			 removePaths.removeEmptyPaths(orforks_Stubs_Timers);
			
			 //finally save the changes
			 IWorkbench workbench= PlatformUI.getWorkbench();
	         IWorkbenchPage page=	 workbench.getActiveWorkbenchWindow().getActivePage();
	         page.getActiveEditor().doSave(new NullProgressMonitor());
			 //********* handling stub INS ***************
			 //Here , we handle un-traversed stub INs branches (same way as non-traversed Orfork & stub-OUTS branches)
			 orforks_Stubs_Timers.clear();
			//first saving all stubs to be used later
			 for(NodeConnection nc: unrelatedStubINS)
				{
					if(!orforks_Stubs_Timers.contains(nc.getTarget()))
						orforks_Stubs_Timers.add( (PathNode)nc.getTarget());
				}
			
			 removePaths.removeUnrelatedStubINS(startpoints,unrelatedStubINS );
			//remove empty paths
			 removePaths.removeStubINsEmptyPaths(orforks_Stubs_Timers);
			 //*****************End of Stub INS handling******************************
			  
			 //remove unrelated maps
			 removePaths.removeMaps(urn,maps);
			 removePaths.removeComponentRefs();
			 //finally save the changes
		 workbench= PlatformUI.getWorkbench();
	         page=	 workbench.getActiveWorkbenchWindow().getActivePage();
	         page.getActiveEditor().doSave(new NullProgressMonitor());
		 
	       //refresh display
	 		editor.recreatePages();
	 		editor.setActivePage(targetMap);

	}
	
	else if(Criterion instanceof NodeConnection)
	{
		removePaths=new RemoveUnrelatedPaths((NodeConnection)Criterion);
		//set the viewer
		 removePaths.setViewer(viewer);
		ArrayList<PathNode> nodes=new ArrayList<PathNode>();
		//removes what comes after SC node connection
		removePaths. removeRest((NodeConnection)Criterion, nodes, new ArrayList<EObject>());
		
		//then,removing unrelated OrFork/Timer/Stubs branches
				//first saving all forks and stubs to be used later
				ArrayList<PathNode> orforks_Stubs_Timers=new ArrayList<PathNode>();
					for(NodeConnection nc: UnrelatedOrForkbranches)
					{
						if(!orforks_Stubs_Timers.contains(nc.getSource()))
							orforks_Stubs_Timers.add( (PathNode)nc.getSource());
					}
				 removePaths.removeOrForkBranches(startpoints,UnrelatedOrForkbranches);
				 //remove empty paths
				 removePaths.removeEmptyPaths(orforks_Stubs_Timers);
				
				 //finally save the changes
				 IWorkbench workbench= PlatformUI.getWorkbench();
		         IWorkbenchPage page=	 workbench.getActiveWorkbenchWindow().getActivePage();
		         page.getActiveEditor().doSave(new NullProgressMonitor());
		         //********* handling stub INS ***************
				 //Here , we handle un-traversed stub INs branches (same way as non-traversed Orfork & stub-OUTS branches)
				 orforks_Stubs_Timers.clear();
				//first saving all stubs to be used later
				 for(NodeConnection nc: unrelatedStubINS)
					{
						if(!orforks_Stubs_Timers.contains(nc.getTarget()))
							orforks_Stubs_Timers.add( (PathNode)nc.getTarget());
					}
				
				 removePaths.removeUnrelatedStubINS(startpoints,unrelatedStubINS );
				//remove empty paths
				 removePaths.removeStubINsEmptyPaths(orforks_Stubs_Timers);
				 //*****************End of Stub INS handling******************************
				//remove unrelated maps
				 removePaths.removeMaps(urn,maps);
				 //remove unbinded/unrelated componentRef
				 removePaths.removeComponentRefs();
				 //finally save the changes
			 workbench= PlatformUI.getWorkbench();
		         page=	 workbench.getActiveWorkbenchWindow().getActivePage();
		         page.getActiveEditor().doSave(new NullProgressMonitor());
			 
		       //refresh display
		 		editor.recreatePages();
		 		if(targetMap!=null)
		 			System.out.println("Tmap Not Null: ");
		 		else
		 			System.out.println("Tmap is NULL");
		 		editor.setActivePage(targetMap);
	
	}
 }
 

public void undoSlicing()
{
	
   if(removePathNode!=null)
    	try{
    		
    		removePathNode.undo();	
    		
    		
    	}
   
	   
    catch (RuntimeException e)
    {
    	System.out.println(e);
    }
   //undo removal of unrelated paths and pathnodes coming after the criterion
   if(removePaths!=null)
   {
	   removePaths.undoRemoval();
   }
   
    
  //  Uncoloring();
}


/**
 * mark slice elements with green starting from the slicing criterion
 * 
 */
public void coloring()
{    
	
	/*  original code
	 * modelEditPart=(EditPart)getViewer().getEditPartRegistry().get((EObject) respref );
		if(modelEditPart!=null)
	    {
			//original
			( (GraphicalEditPart) modelEditPart).getFigure().setForegroundColor(ColorConstants.red);
			
		}
	 */
	SliceColorList editPartList=new SliceColorList(editor);
	if(Criterion instanceof NodeConnection)
	{
		editPartList.setNodeConn_criterion((NodeConnection) Criterion);
		visitedNodes.remove(0);
	}
    /* if(Criterion instanceof NodeConnection )
     {
    	 editPartList.setNodeConn_criterion((NodeConnection) Criterion);
    	 visitedNodes.remove(0);
     }*/
	EditPart modelEditPart=null;
	
	//remove the removed nodes from the list
	//for(RespRef removed:NotRelevantRespRefList)
		//visitedNodes.remove(removed);
	
	for(EObject element:visitedNodes)
	{
		
		//********************************IMPORTANT**************
		/*
		  IURNDiagram map=((PathNode)element).getDiagram();
		for(int i=0; i<editor.getPageCount();i++)
		{
			//how to get the editpart of the model element
			 pageEditor=(UrnEditor) editor.getEditor(i);
			//compare the diagrams to get the right editor
			  if(pageEditor.getModel().equals(((PathNode) element).getDiagram()))
			  {
				  modelEditPart=(EditPart) pageEditor.getGraphicalViewer().getEditPartRegistry().get(element);
				  break;
			  }
		}
		*/
		
			//add it to the green list
		     
		     
			    PathNode node= (PathNode)element;
			    editPartList.green.add(node);    
		     
		    
		     
			 /*   
			    EList connections=node.getPred();
    	if(connections!=null)
    	for(Object nodeConn:connections)
    
    		editPartList.blackLinks.add((NodeConnection) nodeConn);
    		*/
	    
	   
	
	}
	//coloring Unrelated respref
	if(!allNotRelevantRespRefList.isEmpty())
	for(RespRef respref:allNotRelevantRespRefList)
	{
		//remove it from green
		editPartList.green.removeAll(Collections.singleton(respref));
		//add it to red
		editPartList.red.add(respref);
	}
	//coloring the empty respref
	if(!emptyRespRef.isEmpty())
	for(RespRef respref:emptyRespRef)
	{
		//remove it from green
		editPartList.green.removeAll(Collections.singleton(respref));
		//add it to gray
		editPartList.gray.add(respref);
	}
	
	//coloring criterion concurrency branch(s) to black
	if(!criterionExcludedNC.isEmpty())
	for(NodeConnection nc:criterionExcludedNC)
	{
		      
				editPartList.blackLinks.add(nc);
	}
	if(!unrelatedStubINS .isEmpty())
		for(NodeConnection nc:unrelatedStubINS)
		{
			      
					editPartList.blackLinks.add(nc);
		}
	
	// coloring unrelated stubINs to black
	
	colorList.add(editPartList);
	ColorUtils.sliceON=true;
	ColorUtils.doColor();
}
/**
 * remark the slice elements to black
 */

public void Uncoloring()
{  
		for(SliceColorList list:colorList)
		{
			if(list.editor.equals(editor))
			{
				
				ColorUtils.unColor(list);
				colorList.remove(list);
				if(colorList.isEmpty())
					ColorUtils.sliceON=false;
				
				break;
			}
		}
}
/**
 * returns the list of relevant resp-ref 
 */
public ArrayList<RespRef> getRelevantRespRefList() {
	
	return relevantRespRefList;
}
public void setRelevantRespRefList(ArrayList<RespRef> relevantRespRefList) {
	this.relevantRespRefList = relevantRespRefList;
}

/**
 * updates the main <em>(the class list of all branches of a path)</em> list of criterion variables by adding new variables that are not
 * included in the main list
 * @param criterionVariables a list of variables resulted from certain path
 */
public void updateCriterionVariables(ArrayList<String> criterionVariables)
{
	for(String var:criterionVariables)
		if(!this.criterionVariables.contains(var))
			this.criterionVariables.add(var);
}

public ArrayList<String> getCriterionVariables()
{
	
	return criterionVariables;
}
public ArrayList<RespRef> getNotRelevantRespRefList() {
	return NotRelevantRespRefList;
}
public void setNotRelevantRespRefList(ArrayList<RespRef> notRelevantRespRefList) {
	NotRelevantRespRefList = notRelevantRespRefList;
}
public URNspec getUrn() {
	return urn;
}
public void setUrn(URNspec urn) {
	this.urn = urn;
}
public NodeConnection getStartingNodeConnection() {
	return startingNodeConnection;
}
public void setStartingNodeConnection(NodeConnection startingNodeConnection) {
	this.startingNodeConnection = startingNodeConnection;
}

/**
 * This method is called once before running Slicing. It moves forward and checks whether the criterion resides within concurrency 
 * branch. if the criterion resides within a concurrency branch , all other branches are stored in a list in order to run slicing for these  
 * branches as well
 * @param startNC the starting node connection from which forward traversal starts
 * @param pathVisitedForks the already visited nodes in the path 
 */
 public void forwardCheck_criterion(NodeConnection startNC,ArrayList<EObject> pathVisitedForks)
 {
		
		ArrayList<EObject> currentVisitedForks=new ArrayList<EObject>();
		NodeConnection currentNodeConnection=startNC;
		PathNode CurrentNode=(PathNode)currentNodeConnection.getTarget();
		
		
		//fill currentVisitedJoins with previous/parent path visited joins
		for(EObject item:pathVisitedForks)
			currentVisitedForks.add(item);
		//start backward traversal and analyzing
		
		while(! (CurrentNode instanceof EndPoint))
		{
			//handling RespRef,
			////this condition to avoid loop,respref should not equal the criterion indexed at 0
			if( Criterion instanceof RespRef && CurrentNode instanceof RespRef && ((RespRef) CurrentNode ).equals((RespRef)visitedNodes.get(0) ))
			
				return;
			
				//handling OrFork
			else if(CurrentNode instanceof OrFork)
			{
				//this condition is used to avoid loop, written only for Orjoin, and AndJoin
				if(!currentVisitedForks.contains((EObject)CurrentNode))
				{
					//add the OrJoin to pathVisitedJoins list, next time the loop will be detected
					//using this list
					currentVisitedForks.add((EObject)CurrentNode);
				
				
				EList orJoinLinks=CurrentNode.getSucc();
				// call the method recursively for each successor link of orfork
				for(Object link:orJoinLinks)	
				 forwardCheck_criterion((NodeConnection) link,currentVisitedForks );
				return;
				
				
			}
				// otherwise it's a loop
				else
					return;
			}
			
			// handling AndFork, in case of criterion forward check, we will stop and return
			else if(CurrentNode instanceof AndFork)
			{
					return;
			}
			
			
			
			// handling OrJoin
			else if (CurrentNode instanceof OrJoin)
			{
				if (!currentVisitedForks.contains(CurrentNode))
					currentVisitedForks.add(CurrentNode);
				//otherwise it's already been visited
				else
					return;
			}
			
			
			// handling AndJoin
			else if(CurrentNode instanceof AndJoin)
			{		
			
				
				if(!visitedNodes.contains((EObject)CurrentNode))
				{
					//we add it to visited nodes since we need it in coloring later
					visitedNodes.add((EObject)CurrentNode);
					criterionExcludedNC.add(currentNodeConnection);
					
				
					for(Object link:CurrentNode.getPred())
						// add all the branches
						forwardNodeConnections.add((NodeConnection) link);
					//exclude the node connection coming from criterion path
					forwardNodeConnections.remove(currentNodeConnection);
				return;
				}
				
				else 
				{
					if(!criterionExcludedNC.contains(currentNodeConnection))
					criterionExcludedNC.add(currentNodeConnection);
					forwardNodeConnections.remove(currentNodeConnection);
					return;
					
				}
				
				}
			         
			else if (CurrentNode instanceof Stub)
			{
				if(!currentVisitedForks.contains(CurrentNode))
				{
				currentVisitedForks.add(CurrentNode) ;
				EList temp=CurrentNode .getSucc();
				for(Object link:temp)
					forwardCheck_criterion((NodeConnection) link, currentVisitedForks);
			    return;	
				}
				else
					return;
			}
		
			// Handling a timer
			
						else if (CurrentNode instanceof Timer)
						{
							if(!currentVisitedForks.contains(CurrentNode))
							{
							currentVisitedForks.add(CurrentNode) ;
							EList temp=CurrentNode .getSucc();
							for(Object link:temp)
								forwardCheck_criterion((NodeConnection) link, currentVisitedForks);
						    return;	
							}
							else
								return;
						}
					
			
			             // continue traversal
			//Note: pathnodes with more than one pred-link (ex:OrJoin,AndJoin)are acually sent as new slicing alg
			// for each link, they are handeled in the aforemoentioned if-else cases
			//Hence what is left will be nodes with only one pred-link
			//that's why we get the first element at index 0 since we're sure it's the only link
			//in the getPred() list returned.
			
			currentNodeConnection=(NodeConnection)CurrentNode.getSucc().get(0);
			//get the next backward pathnode
			CurrentNode=(PathNode) currentNodeConnection.getTarget();
			

		}  // ******** end of while loop ******
		
	 return ;
 }

 /**
  * this method is used to move forward when an AndFork is encountered.
  * it retrieves the concurrent starting node connections so that new instances 
  * of the slicing algorithm are instantiated from the collected node connections
  * @param startNC a single node connection that is retrieved from <em>AndFork.getsucc()</em>
  * @param pathVisitedForks path visited nodes <em>'(used to detect loop while path traversal)'</em>
  */
 public void forwardCheck(NodeConnection startNC,ArrayList<EObject> pathVisitedForks)
 {
		
		ArrayList<EObject> currentVisitedForks=new ArrayList<EObject>();
		NodeConnection currentNodeConnection=startNC;
		PathNode CurrentNode=(PathNode)currentNodeConnection.getTarget();
		
		
		//fill currentVisitedJoins with previous/parent path visited joins
		
				for(EObject item:pathVisitedForks)
					currentVisitedForks.add(item);
		//start backward traversal and analyzing
		
		while(! (CurrentNode instanceof EndPoint))
		{
			//handling RespRef,
			////this condition to avoid loop,respref should not equal the criterion indexed at 0
			if(Criterion instanceof RespRef && CurrentNode instanceof RespRef && ((RespRef) CurrentNode ).equals((RespRef)visitedNodes.get(0) ))
			
				return;
			
				//handling OrFork
			else if(CurrentNode instanceof OrFork)
			{
				//this condition is used to avoid loop, written only for Orjoin, and AndJoin
				if(!currentVisitedForks.contains((EObject)CurrentNode))
				{
					//add the OrJoin to pathVisitedJoins list, next time the loop will be detected
					//using this list
					currentVisitedForks.add((EObject)CurrentNode);
				
				
				EList orJoinLinks=CurrentNode.getSucc();
				// call the method recursively for each successor link of orfork
				for(Object link:orJoinLinks)	
				 forwardCheck((NodeConnection) link,currentVisitedForks );
				return;
				
				
			}
				// otherwise it's a loop
				else
					return;
			}
			
			// handling AndFork, in case of criterion forward check, we will stop and return
			else if(CurrentNode instanceof AndFork)
			{
				//this condition is used to avoid loop, written only for Orjoin, and AndJoin
				if(!currentVisitedForks.contains((EObject)CurrentNode))
				{
					//add the OrJoin to pathVisitedJoins list, next time the loop will be detected
					//using this list
					currentVisitedForks.add((EObject)CurrentNode);
				
				
				EList temp=CurrentNode.getSucc();
				// call the method recursively for each successor link of AndFork
				for(Object link:temp)	
				 forwardCheck((NodeConnection) link,currentVisitedForks );
				return;
				
				
			}
				// otherwise it's a loop
				else
					return;	
			
			}
			
			
			
			// handling OrJoin
			else if (CurrentNode instanceof OrJoin)
			{
				if (!currentVisitedForks.contains(CurrentNode))
					currentVisitedForks.add(CurrentNode);
				//otherwise it's already been visited
				else
					return;
			}
			
			
			// handling AndJoin
			else if(CurrentNode instanceof AndJoin)
			{		
			
				
				if(!visitedNodes.contains((EObject)CurrentNode))
				{	
					visitedNodes.add((EObject)CurrentNode);
					ArrayList<NodeConnection> temp= new ArrayList<NodeConnection>();
					for(Object o:CurrentNode.getPred())
						temp.add((NodeConnection) o);
					for(Object link:temp)
					{
						forwardNodeConnections.add((NodeConnection) link);
					
					}
					//remove the links to which the criterion belong 
					//collected when calling forwardCheck_criterion
					for(NodeConnection nc:criterionExcludedNC)
						forwardNodeConnections.removeAll(Collections.singleton(nc));
				return;
				}
				
				//otherwise the AndJoin has been already visited and links has been 
				//already collected/added to the list
				else 
				{
					
					return;
					
				}
			
				}
			         
			
			// handling waiting place
			//no action required
			
			// Handling Stub
			
			else if (CurrentNode instanceof Stub)
			{
				if(!currentVisitedForks.contains(CurrentNode))
				{
				currentVisitedForks.add(CurrentNode) ;
				EList temp=CurrentNode .getSucc();
				for(Object link:temp)
					forwardCheck((NodeConnection) link, currentVisitedForks);
			    return;	
				}
				else
					return;
			}
		
			// Handling a timer
			
						else if (CurrentNode instanceof Timer)
						{
							if(!currentVisitedForks.contains(CurrentNode))
							{
							currentVisitedForks.add(CurrentNode) ;
							EList temp=CurrentNode .getSucc();
							for(Object link:temp)
								forwardCheck((NodeConnection) link, currentVisitedForks);
						    return;	
							}
							else
								return;
						}
					
			
			             // continue traversal
			//Note: pathnodes with more than one pred-link (ex:OrJoin,AndJoin)are acually sent as new slicing alg
			// for each link, they are handeled in the aforemoentioned if-else cases
			//Hence what is left will be nodes with only one pred-link
			//that's why we get the first element at index 0 since we're sure it's the only link
			//in the getPred() list returned.
			
			currentNodeConnection=(NodeConnection)CurrentNode.getSucc().get(0);
			//get the next backward pathnode
			CurrentNode=(PathNode) currentNodeConnection.getTarget();
			

		}  // ******** end of while loop ******
		
		//when the current node is an EndPoint
		if(! visitedNodes.contains((EObject)CurrentNode) && !forwardNodeConnections.contains(currentNodeConnection) && !concurrencyNodeConnections.contains(currentNodeConnection))
			{
			
			visitedNodes.add((EObject)CurrentNode);
			forwardNodeConnections.add(currentNodeConnection);
			}
	 return ;
 }//method end

 
 
public AndFork getStoppingFork() {
	return stoppingFork;
}
public void setStoppingFork(AndFork stoppingFork) {
	this.stoppingFork = stoppingFork;
}
/**
 * 
 * @return
 */
public static Color getColor(EObject node)
{
	
	Color color=null;
	
	for(SliceColorList element: colorList )
	{
		if(node instanceof PathNode)
		{
		if(element.green.contains(node))
			{
			color=ColorConstants.green;
			break;
			}
		else if(element.red.contains(node))
		{
			color=ColorConstants.red;
			break;
		}
		else if(element.gray.contains(node))
		{
			color=ColorConstants.gray;
			break;
		}
		}
		//if it is a nodeConnection
		else if(node instanceof NodeConnection)
		{
			if(element.blackLinks.contains(node));
			{
				color=ColorConstants.green;
			break;
			}
		}
	}
	if(color==null)
		System.out.println("NULL");
	else
		System.out.println("NOT NULL");
	return color;
}
/**
 * find the right group of related concurrency starting node connections.If the current instance does not belong to a group,
 * then new group is created
 * @param currentInstanceNC starting node connection of the current slicing instance
 */
public void Concurrency_Grouping(NodeConnection currentInstanceNC)
{
	
	ArrayList<NodeConnection> TargetGroup=new ArrayList<NodeConnection>() ;
	boolean groupFound=false;
	//search whether current instance resides within a group

	Iterator<ArrayList<NodeConnection>> itter=concurrencyGroups.iterator();
	
	while(itter.hasNext())
	{
		ArrayList<NodeConnection> group=itter.next();
		
		if(groupFound)
			break;
		else
		{
			Iterator<NodeConnection> iterator=group.iterator();
			while(iterator.hasNext())
			{
				NodeConnection startNodeConnection=iterator.next();
				if(currentInstanceNC.equals(startNodeConnection))
				{
					groupFound=true;
					TargetGroup=group;
					break;
				}
				
			}
		}
	}
	
	
	
	if(groupFound)
	{	
		//add the node connections to the right group,TargetGroup
		Iterator<NodeConnection> iterat=forwardNodeConnections.iterator();
		while(iterat.hasNext())
		{
			
			NodeConnection nc=iterat.next();
			TargetGroup.add(nc);
			
		}
}
	
	//if current instance does not belong to a group, new group will be
	//created & the global concurrency group will be appended by this new group
	else
		Concurreny_CreateGroup(currentInstanceNC);
	
}

/**
 * creates a new concurrency group. The group consists of starting node connections of slicingAlg instances 
 * to be created 
 * @param currentInstanceNC starting node connection of the current slicing instance
 */
public void Concurreny_CreateGroup(NodeConnection currentInstanceNC)
{
	ArrayList<NodeConnection> newGroup=new ArrayList<NodeConnection>();
	
	Iterator<NodeConnection> iter= forwardNodeConnections.iterator();
	while(iter.hasNext())
	{
		NodeConnection nc=iter.next();
		newGroup.add(nc);
		
	}

	if(currentInstanceNC!=null)
		newGroup.add(currentInstanceNC);

	concurrencyGroups.add(newGroup);
	
	
}
public EObject getCriterion() {
	return Criterion;
}
public void setCriterion(EObject criterion) {
	Criterion = criterion;
}
public EditPartViewer getViewer() {
	return viewer;
}
public void setViewer(EditPartViewer viewer) {
	this.viewer = viewer;
}
public IURNDiagram getTargetMap() {
	return targetMap;
}
public void setTargetMap(IURNDiagram targetMap) {
	this.targetMap = targetMap;
}
public UCMNavMultiPageEditor getEditor() {
	return editor;
}
public void setEditor(UCMNavMultiPageEditor editor) {
	this.editor = editor;
}
}


