package seg.jUCMNav.model.commands.Slicing;
import org.eclipse.emf.ecore.EObject;

import seg.jUCMNav.model.util.*;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.commands.delete.DeleteComponentRefCommand;
import seg.jUCMNav.model.commands.delete.DeleteMapCommand;
import seg.jUCMNav.model.commands.delete.internal.RemovePathNodeCommand;
import seg.jUCMNav.model.commands.transformations.CutAnyPathCommand;
import seg.jUCMNav.model.commands.transformations.MergeStartEndCommand;
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
import java.util.Stack;
import java.util.Vector;

import javax.swing.JOptionPane;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
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
import urncore.URNdefinition;
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
 * This class removes the untraversed Orfork and Timer branches, and all pathnodes coming after the slicing criterion  
 * @author Taha
 *
 */
public class RemoveUnrelatedPaths {
	private ArrayList<ComponentRef> compRefList=new ArrayList<ComponentRef>();
     private ArrayList<EObject> visitedNodes;
	 private RespRef criterion=null;
	 private EndPoint EndP_criterion=null;
	 private NodeConnection NodeConn_criterion=null;
	 private EndPoint criterionMergeEndPoint;
	 private StartPoint criterionMergeStartPoint;
	 private NodeConnection criterionReservedNC;
	 private EndPoint criterionReservedEndPoint;
	 private ArrayList<NodeConnection> criterionNCList;
	public ArrayList<NodeConnection> UnrelatedOrForkbranches;
	public ArrayList<EndPoint> unrelatedEndpoints;
	 private Stack<Command> cmdStack;
	 private EditPartViewer viewer;
	  ArrayList<UCMmap> maps;
	 /**
	  * used to remove unrelated orFork and Timer paths as well as unrelated pathnodes coming after the slicing criterion
	  * @param criterion slicing criterion
	  */
	public RemoveUnrelatedPaths(RespRef criterion) {
		this.criterion=criterion;
		this.visitedNodes=SlicingAlg.visitedNodes;
		criterionNCList=new ArrayList<NodeConnection>();
		UnrelatedOrForkbranches=new ArrayList<NodeConnection>();
	cmdStack=new Stack<Command>();	
	unrelatedEndpoints=new ArrayList<EndPoint>();
	maps=new ArrayList<UCMmap>();
	}
	
	/**
	 ** used to remove unrelated orFork and Timer paths as well as unrelated pathnodes coming after the slicing criterion
	  * @param EndP_criterion: EndPoint slicing criterion 
	   
	 */
	public RemoveUnrelatedPaths(EndPoint EndP_criterion) 
	{
		this.EndP_criterion=EndP_criterion;
		this.visitedNodes=SlicingAlg.visitedNodes;
		criterionNCList=new ArrayList<NodeConnection>();
		UnrelatedOrForkbranches=new ArrayList<NodeConnection>();
	cmdStack=new Stack<Command>();	
	unrelatedEndpoints=new ArrayList<EndPoint>();
	maps=new ArrayList<UCMmap>();
	}
	/**
	 ** used to remove unrelated orFork and Timer paths as well as unrelated pathnodes coming after the slicing criterion
	  * @param NodeConn_criterion: NodeConnection as slicing criterion 
	   
	 */
	public RemoveUnrelatedPaths(NodeConnection NodeConn_criterion) 
	{
		this.NodeConn_criterion=NodeConn_criterion;
		this.visitedNodes=SlicingAlg.visitedNodes;
		criterionNCList=new ArrayList<NodeConnection>();
		UnrelatedOrForkbranches=new ArrayList<NodeConnection>();
	cmdStack=new Stack<Command>();	
	unrelatedEndpoints=new ArrayList<EndPoint>();
	maps=new ArrayList<UCMmap>();
	}
	 
	
	/**
	 * removes unrelated <em>untraversed</em> Orfork & timer paths
	 * @param UnrelatedOrForkbranches unrelated/non traversed branches
	 * @param startpoints needed to clean the maps from empty paths resulted from deletion
	 */
	 public void removeOrForkBranches(ArrayList<StartPoint> startpoints, ArrayList<NodeConnection> UnrelatedOrForkbranches)
	 { 
		 PathNode Target,Source;
		  RemovePathNodeCommand removeOrForkForwardPthNodes;
		  RemovePathNodeCommand removeOrforkEndPoints;
		  if(criterion!=null)
		  maps.add((UCMmap) criterion.getDiagram());
		  else if(EndP_criterion!=null)
			  maps.add((UCMmap) EndP_criterion.getDiagram());
		  else if(NodeConn_criterion!=null)
			  maps.add((UCMmap) NodeConn_criterion.getDiagram());
		 UCMmap tempUCM;
		 ArrayList<PathNode> nodes=new ArrayList<PathNode>();
		 if(! UnrelatedOrForkbranches.isEmpty())
		 {	
			 /*
			 //new code cut first, then delete
			 for(NodeConnection nodeconn: UnrelatedOrForkbranches)
			 {
		       
				 Target=(PathNode) nodeconn.getTarget();
		        Source=(PathNode) nodeconn.getSource();
		        
		        
		        //cut the path
		        cutPath(nodeconn);
		        //we store the end points of empty orfork branches to be deleted later
		        
		        
		        for(Object o:Source.getSucc())
		        {
		        	PathNode node=(PathNode) ((NodeConnection)o).getTarget();
		        	if(node instanceof EndPoint)
		        		if(!unrelatedEndpoints.contains(node))
		        		unrelatedEndpoints.add((EndPoint) node);
		        }
		        
		        
		        
		        
		        if(!this.UnrelatedOrForkbranches.contains((NodeConnection) Target.getPred().get(0)))
		        this.UnrelatedOrForkbranches.add((NodeConnection) Target.getPred().get(0));
		        
			 }
			 */
			 
			//after cutting, we delete each branch's nodes
		 for(NodeConnection nc:UnrelatedOrForkbranches)
		 {  
			 if(criterionReservedEndPoint==null ||!nc.getTarget().equals(criterionReservedEndPoint))
			 {
				 tempUCM=(UCMmap) nc.getDiagram();
			 if(!maps.contains(tempUCM))
				 maps.add(tempUCM);
			
				 nodes.clear();
				// nodes.add((PathNode) nc.getSource());
			 Forwardtraversal(nc, nodes,new ArrayList<EObject>());
		 
		 if(!nodes.isEmpty())
		 {
			 removeOrForkForwardPthNodes=new RemovePathNodeCommand(nodes.get(0),null);
			 nodes.remove(0);
			 if(!nodes.isEmpty())
			 {
			 for(PathNode node:nodes)
			 
				 removeOrForkForwardPthNodes.add(new RemovePathNodeCommand(node,null));
			 }
			 removeOrForkForwardPthNodes.execute();
			 //add it to the stack
			 cmdStack.push(removeOrForkForwardPthNodes);
		 }
		 
		 }
		 
			 /*
		 //since executing the previous command will result in new startPoints created, we will delete them
		 if(! UnrelatedOrForkbranches.isEmpty())
		 {
		 ArrayList<NodeConnection> temp=new ArrayList<NodeConnection>();
			 for(NodeConnection nc:UnrelatedOrForkbranches)
				 temp.add(nc);
			 PathNode firstPathNode=(PathNode) temp.get(0).getTarget();
			 removeOrforkEndPoints=new RemovePathNodeCommand(firstPathNode,null);
			 temp.remove(0);
			 for(NodeConnection nc:temp)
			 {
				 firstPathNode=(PathNode) nc.getTarget();
				 removeOrforkEndPoints.add(new RemovePathNodeCommand(firstPathNode,null));
			 }
			 removeOrforkEndPoints.execute();
			 cmdStack.push(removeOrforkEndPoints);
		 }
		 */
		 
		 /*
		 //remove empty branches (using endpoints stored) [put a condition here to check the CriterionreservedNC]
		 if(!unrelatedEndpoints.isEmpty())
		 {
			 RemovePathNodeCommand removeEndpoints;
			 removeEndpoints=new RemovePathNodeCommand(unrelatedEndpoints.get(0),null);
			 unrelatedEndpoints.remove(0);
			 if(!unrelatedEndpoints.isEmpty())
			 {
			 for(PathNode node:unrelatedEndpoints)
			 
				 removeEndpoints.add(new RemovePathNodeCommand(node,null));
			 }
			 removeEndpoints.execute();
			 //add it to the stack
			 cmdStack.push(removeEndpoints);
		 } */
		 }
		 } 
			 
			 
		 cleanMaps(startpoints, maps);
	 }
	 /**
	  * 
	  */
	 public void removeUnrelatedStubINS(ArrayList<StartPoint> startpoints, ArrayList<NodeConnection> UnrelatedStubINBranches)
	 {
		 PathNode Target,Source;
		  RemovePathNodeCommand removeOrForkForwardPthNodes;
		  RemovePathNodeCommand removeOrforkEndPoints;
		
		  
		  if(criterion!=null)
			  maps.add((UCMmap) criterion.getDiagram());
			  else if(EndP_criterion!=null)
				  maps.add((UCMmap) EndP_criterion.getDiagram());
			  else if(NodeConn_criterion!=null)
				  maps.add((UCMmap) NodeConn_criterion.getDiagram());
		 UCMmap tempUCM;
		 ArrayList<PathNode> nodes=new ArrayList<PathNode>();
		 if(! UnrelatedStubINBranches.isEmpty())
		 {	
			 /*
			 //new code cut first, then delete
			 for(NodeConnection nodeconn: UnrelatedOrForkbranches)
			 {
		       
				 Target=(PathNode) nodeconn.getTarget();
		        Source=(PathNode) nodeconn.getSource();
		        
		        
		        //cut the path
		        cutPath(nodeconn);
		        //we store the end points of empty orfork branches to be deleted later
		        
		        
		        for(Object o:Source.getSucc())
		        {
		        	PathNode node=(PathNode) ((NodeConnection)o).getTarget();
		        	if(node instanceof EndPoint)
		        		if(!unrelatedEndpoints.contains(node))
		        		unrelatedEndpoints.add((EndPoint) node);
		        }
		        
		        
		        
		        
		        if(!this.UnrelatedOrForkbranches.contains((NodeConnection) Target.getPred().get(0)))
		        this.UnrelatedOrForkbranches.add((NodeConnection) Target.getPred().get(0));
		        
			 }
			 */
			 
			//after cutting, we delete each branch's nodes
		 for(NodeConnection nc:UnrelatedStubINBranches)
		 {  
			 if(criterionReservedEndPoint==null ||(nc.getSource()!=null && !nc.getSource().equals(criterionReservedEndPoint)))
			 {
				 tempUCM=(UCMmap) nc.getDiagram();
			 if(!maps.contains(tempUCM))
				 maps.add(tempUCM);
			
				 nodes.clear();
				// nodes.add((PathNode) nc.getSource());
			 backwardTraversal(nc, nodes,new ArrayList<EObject>());
		 
		 if(!nodes.isEmpty())
		 {
			 removeOrForkForwardPthNodes=new RemovePathNodeCommand(nodes.get(0),null);
			 nodes.remove(0);
			 if(!nodes.isEmpty())
			 {
			 for(PathNode node:nodes)
			 
				 removeOrForkForwardPthNodes.add(new RemovePathNodeCommand(node,null));
			 }
			 removeOrForkForwardPthNodes.execute();
			 //add it to the stack
			 cmdStack.push(removeOrForkForwardPthNodes);
		 }
		 
		 }
		 
			 /*
		 //since executing the previous command will result in new startPoints created, we will delete them
		 if(! UnrelatedOrForkbranches.isEmpty())
		 {
		 ArrayList<NodeConnection> temp=new ArrayList<NodeConnection>();
			 for(NodeConnection nc:UnrelatedOrForkbranches)
				 temp.add(nc);
			 PathNode firstPathNode=(PathNode) temp.get(0).getTarget();
			 removeOrforkEndPoints=new RemovePathNodeCommand(firstPathNode,null);
			 temp.remove(0);
			 for(NodeConnection nc:temp)
			 {
				 firstPathNode=(PathNode) nc.getTarget();
				 removeOrforkEndPoints.add(new RemovePathNodeCommand(firstPathNode,null));
			 }
			 removeOrforkEndPoints.execute();
			 cmdStack.push(removeOrforkEndPoints);
		 }
		 */
		 
		 /*
		 //remove empty branches (using endpoints stored) [put a condition here to check the CriterionreservedNC]
		 if(!unrelatedEndpoints.isEmpty())
		 {
			 RemovePathNodeCommand removeEndpoints;
			 removeEndpoints=new RemovePathNodeCommand(unrelatedEndpoints.get(0),null);
			 unrelatedEndpoints.remove(0);
			 if(!unrelatedEndpoints.isEmpty())
			 {
			 for(PathNode node:unrelatedEndpoints)
			 
				 removeEndpoints.add(new RemovePathNodeCommand(node,null));
			 }
			 removeEndpoints.execute();
			 //add it to the stack
			 cmdStack.push(removeEndpoints);
		 } */
		 }
		 } 
			 
			 
		 cleanMaps(startpoints, maps);
	 }
	 /**
	  * Moves forward and collects the pathnodes to be deleted
	  * @param startingNC the node connection from which forward traversal starts
	  * @param nodesForDeletion The list to which elements to be deleted are added
	  */
	 public void Forwardtraversal(NodeConnection startingNC, ArrayList<PathNode> nodesForDeletion,ArrayList<EObject> pathVisitedForks)
	 {
			
			ArrayList<EObject> currentVisitedForks=pathVisitedForks;
			NodeConnection currentNodeConnection=startingNC;
			PathNode CurrentNode=(PathNode)currentNodeConnection.getTarget();
	        
			//start backward traversal and analyzing
			
			while(! (CurrentNode instanceof EndPoint))
			{
				//this to avoid any exceptional cases,if we stop once a visited path-node is reached 
				if(visitedNodes.contains(CurrentNode))
					return;
				//handling RespRef,
				////this condition to avoid loop,respref should not equal the criterion indexed at 0
	if(criterion!=null && CurrentNode instanceof RespRef && ((RespRef) CurrentNode ).equals(criterion))
					return;
				
					//handling OrFork
				else if(CurrentNode instanceof OrFork)
				{
	if(NodeConn_criterion!=null && (currentNodeConnection.equals( NodeConn_criterion)))
						return;
					//this condition is used to avoid loop, written only for Orjoin, and AndJoin
		if(!visitedNodes.contains((EObject)CurrentNode) && !currentVisitedForks.contains((EObject)CurrentNode))
					{
						//add the OrJoin to pathVisitedJoins list, next time the loop will be detected
						//using this list
						currentVisitedForks.add((EObject)CurrentNode);
						//add the node to the list to be removed later
						if(!nodesForDeletion.contains(CurrentNode))
					    nodesForDeletion.add(CurrentNode);
					
					EList orJoinLinks=CurrentNode.getSucc();
					// call the method recursively for each successor link of orfork
					for(Object link:orJoinLinks)	
					 Forwardtraversal((NodeConnection) link,nodesForDeletion,currentVisitedForks );
					return;
					
					
				}
					// otherwise it's a loop
					else
						return;
				}
				
				// handling AndFork
				else if(CurrentNode instanceof AndFork)
				{
					//this condition is used to avoid loop, written only for Orjoin, and AndJoin
					if(!currentVisitedForks.contains((EObject)CurrentNode))
					{
						//add the OrJoin to pathVisitedJoins list, next time the loop will be detected
						//using this list
						currentVisitedForks.add((EObject)CurrentNode);
						if(!nodesForDeletion.contains(CurrentNode))
					 nodesForDeletion.add(CurrentNode);
					
					EList temp=CurrentNode.getSucc();
					// call the method recursively for each successor link of AndFork
					for(Object link:temp)	
						Forwardtraversal((NodeConnection) link,nodesForDeletion,currentVisitedForks );
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
						{
						currentVisitedForks.add(CurrentNode);
						if(!nodesForDeletion.contains(CurrentNode))
						nodesForDeletion.add(CurrentNode);
						ArrayList<NodeConnection> temp=new ArrayList<NodeConnection>();
						for(Object link:CurrentNode.getPred())
							temp.add((NodeConnection) link);
						//remove the current node connection
						temp.remove(currentNodeConnection);
						for(NodeConnection nc:temp)
							backwardTraversal(nc, nodesForDeletion, currentVisitedForks);
						}
					//otherwise it's already been visited
					else
						return;
				}
				
				
				// handling AndJoin
				else if(CurrentNode instanceof AndJoin)
				{		
				
					
					if(!currentVisitedForks.contains((EObject)CurrentNode))
					{	
						currentVisitedForks.add((EObject)CurrentNode);
						if(!nodesForDeletion.contains(CurrentNode))
						nodesForDeletion.add(CurrentNode);
						ArrayList<NodeConnection> temp= new ArrayList<NodeConnection>();
						for(Object o:CurrentNode.getPred())
							temp.add((NodeConnection) o);
						//remove the current node connection
						    temp.remove(currentNodeConnection);
						for(NodeConnection link:temp)
						{
							backwardTraversal(link,nodesForDeletion,currentVisitedForks);
						
						}
						
					}
					
					//otherwise the AndJoin has been already visited and links has been 
					//already collected/added to the list
					else 
					{
						
						return;
						
					}
				
					}
				         
				
				// handling waiting place
				else if(CurrentNode instanceof WaitingPlace)
				{
			if(  NodeConn_criterion!=null && (currentNodeConnection.equals(NodeConn_criterion)))
						return;
				if(!visitedNodes.contains((EObject)CurrentNode) && !currentVisitedForks.contains((EObject)CurrentNode))
					{
						//add the OrJoin to pathVisitedJoins list, next time the loop will be detected
						//using this list
						currentVisitedForks.add((EObject)CurrentNode);
						//add the node to the list to be removed later
						if(!nodesForDeletion.contains(CurrentNode))
					    nodesForDeletion.add(CurrentNode);
					
					EList orJoinLinks=CurrentNode.getSucc();
					// call the method recursively for each successor link of orfork
					for(Object link:orJoinLinks)	
					 Forwardtraversal((NodeConnection) link,nodesForDeletion,currentVisitedForks );
					return;	
					}
					// otherwise it's a loop
					else
						return;
				}
				
				
				// Handling Stub
				
				else if (CurrentNode instanceof Stub)
				{
					if(!currentVisitedForks.contains(CurrentNode))
					{
					currentVisitedForks.add(CurrentNode) ;
					if(!nodesForDeletion.contains(CurrentNode))
					nodesForDeletion.add(CurrentNode);
					ArrayList<NodeConnection> temp2=new ArrayList<NodeConnection>();
					for(Object link:CurrentNode.getPred())
						temp2.add((NodeConnection) link);
					//remove the current node connection
					temp2.remove(currentNodeConnection);
					for(NodeConnection nc:temp2)
						backwardTraversal(nc, nodesForDeletion, currentVisitedForks);
					EList temp=CurrentNode .getSucc();
					for(Object link:temp)
						Forwardtraversal((NodeConnection) link, nodesForDeletion, currentVisitedForks);
				    return;	
					}
					//otherwise it's been visited before
					else
						return;
				}
			
				// Handling a timer
				
							else if (CurrentNode instanceof Timer)
							{
								
			if(NodeConn_criterion!=null && (currentNodeConnection.equals(NodeConn_criterion)))
						return;
								if(!currentVisitedForks.contains(CurrentNode))
								{
								currentVisitedForks.add(CurrentNode) ;
								if(!nodesForDeletion.contains(CurrentNode))
								nodesForDeletion.add(CurrentNode);
								EList temp=CurrentNode .getSucc();
								for(Object link:temp)
									Forwardtraversal((NodeConnection) link, nodesForDeletion, currentVisitedForks);
							    return;	
								}
								//otherwise it's been visited before
								else
									return;
							}
				
				//add the other pathnodes
							else
							{
								if(!nodesForDeletion.contains(CurrentNode))
								nodesForDeletion.add(CurrentNode);
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
			if(!visitedNodes.contains(CurrentNode) && ! nodesForDeletion.contains(CurrentNode))
				{
				
				nodesForDeletion.add(CurrentNode);
				}
		 return ;
	 }//method end

		 /**
		  * moves backward & collects nodes for deletion
		  * @param startingNC node connection from which backward traversal starts
		  * @param nodesForDeletion the list containing the elements to be deleted
		  * @param pathVisitedForks used to detect loops during traversal
		  */
	 public void backwardTraversal(NodeConnection startingNC, ArrayList<PathNode> nodesForDeletion, ArrayList<EObject> pathVisitedForks)
	 {
			ArrayList<EObject> currentVisitedForks=pathVisitedForks;
			NodeConnection currentNodeConnection=startingNC;
			PathNode CurrentNode=(PathNode)currentNodeConnection.getSource();
	     
			//start backward traversal and analyzing
			
			while(!(CurrentNode instanceof StartPoint))
			{
				//this to avoid any exceptional cases,if we stop once a visited path-node is reached 
				if(CurrentNode==null ||visitedNodes.contains(CurrentNode))
					return;
				//handling RespRef,
				////this condition to avoid loop,respref should not equal the criterion indexed at 0
		if(criterion!=null && CurrentNode instanceof RespRef && ((RespRef) CurrentNode ).equals(criterion ))
					return;
				
					//handling OrFork
				else if(CurrentNode instanceof OrFork)
				{
		if(NodeConn_criterion!=null && (currentNodeConnection.equals( NodeConn_criterion)))
						return;
	
					//this condition is used to avoid loop, written only for Orjoin, and AndJoin
					if(!visitedNodes.contains((EObject)CurrentNode) && !currentVisitedForks.contains((EObject)CurrentNode))
					{
						//add the OrJoin to pathVisitedJoins list, next time the loop will be detected
						//using this list
						currentVisitedForks.add((EObject)CurrentNode);
						//add the node to the list to be removed later
						if(!nodesForDeletion.contains(CurrentNode))
					    nodesForDeletion.add(CurrentNode);
						ArrayList<NodeConnection> temp=new ArrayList<NodeConnection>();
						for(Object link:CurrentNode.getSucc())
							temp.add((NodeConnection) link);
						//remove the current node connection
						temp.remove(currentNodeConnection);
						for(NodeConnection nc:temp)
							Forwardtraversal(nc, nodesForDeletion, currentVisitedForks);
						
				     }
					// otherwise it's a loop
					else
						return;
				}
				
				// handling AndFork
				else if(CurrentNode instanceof AndFork)
				{
					//this condition is used to avoid loop, written only for Orjoin, and AndJoin
					if(!currentVisitedForks.contains((EObject)CurrentNode))
					{
						//add the OrJoin to pathVisitedJoins list, next time the loop will be detected
						//using this list
						currentVisitedForks.add((EObject)CurrentNode);
						if(!nodesForDeletion.contains(CurrentNode))
					 nodesForDeletion.add(CurrentNode);
					
					ArrayList<NodeConnection> temp=new ArrayList<NodeConnection>();
					for(Object link:CurrentNode.getSucc())
						temp.add((NodeConnection) link);
					//remove the current node connection
					temp.remove(currentNodeConnection);
					// call the method recursively for each successor link of AndFork
					for(NodeConnection link:temp)	
						Forwardtraversal((NodeConnection) link,nodesForDeletion,currentVisitedForks );
					
					
				}
					// otherwise it's a loop
					else
						return;	
				
				}
				
				
				
				// handling OrJoin
				else if (CurrentNode instanceof OrJoin)
				{
					if (!currentVisitedForks.contains(CurrentNode))
						{
						currentVisitedForks.add(CurrentNode);
						if(!nodesForDeletion.contains(CurrentNode))
						nodesForDeletion.add(CurrentNode);
						
						ArrayList<NodeConnection> temp=new ArrayList<NodeConnection>();
						for(Object link:CurrentNode.getPred())
							temp.add((NodeConnection) link);
						for(NodeConnection nc:temp)
							backwardTraversal(nc, nodesForDeletion, currentVisitedForks);
						return;
						}
					//otherwise it's already been visited
					else
						return;
				}
				
				
				// handling AndJoin
				else if(CurrentNode instanceof AndJoin)
				{		
				
					
					if(!currentVisitedForks.contains((EObject)CurrentNode))
					{	
						currentVisitedForks.add((EObject)CurrentNode);
						if(!nodesForDeletion.contains(CurrentNode))
						nodesForDeletion.add(CurrentNode);
						ArrayList<NodeConnection> temp=new ArrayList<NodeConnection>();
						for(Object link:CurrentNode.getPred())
							temp.add((NodeConnection) link);
						for(NodeConnection nc:temp)
							backwardTraversal(nc, nodesForDeletion, currentVisitedForks);
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
		// handling waiting place
				else if(CurrentNode instanceof WaitingPlace)
				{
			if(  NodeConn_criterion!=null && (currentNodeConnection.equals(NodeConn_criterion)))
						return;
				if(!visitedNodes.contains((EObject)CurrentNode) && !currentVisitedForks.contains((EObject)CurrentNode))
					{
						//add the OrJoin to pathVisitedJoins list, next time the loop will be detected
						//using this list
						currentVisitedForks.add((EObject)CurrentNode);
						//add the node to the list to be removed later
						if(!nodesForDeletion.contains(CurrentNode))
					    nodesForDeletion.add(CurrentNode);
						ArrayList<NodeConnection> temp=new ArrayList<NodeConnection>();
						for(Object link:CurrentNode.getSucc())
							temp.add((NodeConnection) link);
						//remove the current node connection
						temp.remove(currentNodeConnection);
						for(NodeConnection nc:temp)
							Forwardtraversal(nc, nodesForDeletion, currentVisitedForks);
					return;	
					}
					// otherwise it's a loop
					else
						return;
				}
				
				// Handling Stub
				
				else if (CurrentNode instanceof Stub)
				{
					if(!currentVisitedForks.contains(CurrentNode))
					{
					currentVisitedForks.add(CurrentNode) ;
					if(!nodesForDeletion.contains(CurrentNode))
					nodesForDeletion.add(CurrentNode);
					ArrayList<NodeConnection> temp=new ArrayList<NodeConnection>();
					for(Object link:  CurrentNode.getSucc())
						temp.add((NodeConnection) link);
					//remove the current node connection
					temp.remove(currentNodeConnection);
					for(NodeConnection link:temp)
						Forwardtraversal((NodeConnection) link, nodesForDeletion, currentVisitedForks);
					
					for(Object link:CurrentNode.getPred())
						backwardTraversal((NodeConnection) link, nodesForDeletion, currentVisitedForks);
					
				    return;	
					}
					//otherwise it's been visited before
					else
						return;
				}
			
				// Handling a timer
				
							else if (CurrentNode instanceof Timer)
							{
		if(NodeConn_criterion!=null &&(currentNodeConnection.equals( NodeConn_criterion)))
						return;
				
								if(!currentVisitedForks.contains(CurrentNode))
								{
								currentVisitedForks.add(CurrentNode) ;
								if(!nodesForDeletion.contains(CurrentNode))
								nodesForDeletion.add(CurrentNode);
								ArrayList<NodeConnection> temp=new ArrayList<NodeConnection>();
								for(Object link:CurrentNode.getSucc())
									temp.add((NodeConnection) link);
								//remove the current node node connection
								temp.remove(currentNodeConnection);
								for(Object link:temp)
									Forwardtraversal((NodeConnection) link, nodesForDeletion, currentVisitedForks);
							    
								}
								//otherwise it's been visited before
								else
									return;
							}
				
				//add the other pathnodes
							else
							{
								if(!nodesForDeletion.contains(CurrentNode))
								nodesForDeletion.add(CurrentNode);
							}
								
				
				             // continue traversal
				//Note: pathnodes with more than one pred-link (ex:OrJoin,AndJoin)are acually sent as new slicing alg
				// for each link, they are handeled in the aforemoentioned if-else cases
				//Hence what is left will be nodes with only one pred-link
				//that's why we get the first element at index 0 since we're sure it's the only link
				//in the getPred() list returned.
				
				currentNodeConnection=(NodeConnection)CurrentNode.getPred().get(0);
				//get the next backward pathnode
				CurrentNode=(PathNode) currentNodeConnection.getSource();
				

			}  // ******** end of while loop ******
			
			//when the current node is an StartPoint
			if(! nodesForDeletion.contains(CurrentNode))
				{
				
				nodesForDeletion.add(CurrentNode);
				}
		 return ; 
	 }

	 /**
	  * Removes pathNodes residing between SC and Andjoin (when SC resides within 
	  * a concurrent path). Then {@link #removeRest(NodeConnection, ArrayList, ArrayList)} is called to 
	  * remove what comes after SC. 
	  * @param criterionExcludedNC the concurrent link(s) that lead to SC
	  */
	 public void removeElements( ArrayList<NodeConnection> criterionExcludedNC)
	 {
		 
		 RemovePathNodeCommand removeMiddlePathNodes;
		 
		 //check if the criterion resides within a concurrency path by checking the list
		 if(criterionExcludedNC!=null && !criterionExcludedNC.isEmpty())
		 {
			 AndJoin andJoin=(AndJoin) criterionExcludedNC.get(0).getTarget();
			 //first , split
			NodeConnection criterionNC =(NodeConnection)criterion.getSucc().get(0);
			PathNode temp=(PathNode) criterionNC.getTarget();
			cutPath(criterionNC);
			NodeConnection NCtemp=null;
			for(NodeConnection nc:criterionExcludedNC)
			cutPath(nc);
			for(Object link:andJoin.getPred())
			{
				PathNode source=(PathNode) ((NodeConnection)link).getSource();
				if(source instanceof StartPoint && !SlicingAlg.startpoints.contains((StartPoint)source))
			     {
					NCtemp=(NodeConnection) link;
			     break;
			     }
			}
			
			if(NCtemp!=null)
				System.out.println("NCtemp!=null");
			else 
				System.out.println("IT IS NULL");
			//NodeConnection NCtemp=criterionExcludedNC.get(0);
			criterionMergeStartPoint=(StartPoint) NCtemp.getSource();
			criterionMergeEndPoint=(EndPoint) criterionNC.getTarget();
			
			//now merge
			MergeStartEndCommand merge=new MergeStartEndCommand((UCMmap) criterion.getDiagram(),criterionMergeStartPoint , criterionMergeEndPoint,criterionMergeStartPoint.getX(),criterionMergeStartPoint.getY());
			merge.execute();
			cmdStack.push(merge);
			
			//now traverse and delete the middle elements
			 NodeConnection nodeConnection= (NodeConnection) temp.getPred().get(0);
			 ArrayList<PathNode> nodes=new ArrayList<PathNode>();
			 //add the start point to the list
			 nodes.add((PathNode) nodeConnection.getSource());
			 Forwardtraversal(nodeConnection, nodes, new ArrayList<EObject>());
			 if(!nodes.isEmpty())
			 {
				 removeMiddlePathNodes=new RemovePathNodeCommand(nodes.get(0),null);
				 nodes.remove(0);
				 if(!nodes.isEmpty())
				 {
				 for(PathNode node:nodes)
				 
					 removeMiddlePathNodes.add(new RemovePathNodeCommand(node,null));
				 }
				 removeMiddlePathNodes.execute();
				 //add it to the stack
				 cmdStack.push(removeMiddlePathNodes);
			 } 
				
			//finally , remove what comes after the concurrency Andjoin
			PathNode tempPathNode=(PathNode) NCtemp.getTarget();
			ArrayList<EObject> pathVisitedForks=new ArrayList<EObject>();
			pathVisitedForks.add((EObject)tempPathNode);
			removeRest((NodeConnection) tempPathNode.getSucc().get(0), nodes,pathVisitedForks);
		 }
		 
		 //when the criterion does not reside within concurrency
		 else
		 {
			 ArrayList<PathNode> nodes=new ArrayList<PathNode>();
			 removeRest((NodeConnection)criterion.getSucc().get(0), nodes, new ArrayList<EObject>());
		 }
		 
	 }
	 
	 /**
	  * removes all path nodes coming after the criterion
	  * @param startNodeConnection starting node Connection from which traversal/collecting of nodes begins
	  * @param nodes list of nodes to be deleted
	  * @param pathVisitedForks used to detect loops during traversal
	  */
	 public void removeRest(NodeConnection startNodeConnection, ArrayList<PathNode> nodes,ArrayList<EObject>pathVisitedForks)
	 {
		 
		 getCriterionNC(startNodeConnection, pathVisitedForks);
		 PathNode tempPathNode;
		 System.out.println("Criterion branches list="+criterionNCList.size());
		 NodeConnection tempNodeConnection;
		 RemovePathNodeCommand removeNodes;
		 if(criterionNCList.size()==1)
		 {
			 
			 tempPathNode=(PathNode) criterionNCList.get(0).getTarget();
			 
			 //if it's an endPoint , no need for traversal , cutpath, etc
			 if(! (tempPathNode instanceof EndPoint))
			 {
				 
				 criterionReservedNC=criterionNCList.get(0);
				 nodes.clear();
				 PathNode source=(PathNode) criterionReservedNC.getSource();
				 cutPath(criterionNCList.get(0));
				 
				 //after cutting the path, preserve one branch to solve loop disconnectivity problem
				 
				 for(Object o:source.getSucc())
				 {
					 PathNode node=(PathNode) ((NodeConnection) o).getTarget();
					 if(node instanceof EndPoint)
					 {
						 criterionReservedNC=(NodeConnection) o;
						 criterionNCList.remove((NodeConnection)o);
						 criterionReservedEndPoint=(EndPoint) node;
						 break;
					 }
				 }
				 NodeConnection nodeConnection=(NodeConnection) tempPathNode.getPred().get(0);
				 nodes.add((PathNode) nodeConnection.getSource());
				 
				 Forwardtraversal(nodeConnection, nodes, new ArrayList<EObject>());
				 
				 if(!nodes.isEmpty())
				 {
					 removeNodes=new RemovePathNodeCommand(nodes.get(0),null);
					 nodes.remove(0);
					 if(!nodes.isEmpty())
					 {
					 for(PathNode node:nodes)
					 
						 removeNodes.add(new RemovePathNodeCommand(node,null));
					 }
					 removeNodes.execute();
					 //add it to the stack
					 cmdStack.push(removeNodes);
				 } 
			 }
		 }
		 
		 //when there are more than one node connection 
		 else if(criterionNCList.size()>1)
		 {
			 criterionReservedNC=criterionNCList.get(0);
			 tempPathNode=(PathNode) criterionReservedNC.getTarget();
			 //remove the reserved node connection from the list
			 criterionNCList.remove(criterionReservedNC);
			 PathNode source=(PathNode) criterionReservedNC.getSource();
			 cutPath(criterionReservedNC);
                   //*******
			 
			 for(Object o:source.getSucc())
			 {
				 PathNode node=(PathNode) ((NodeConnection) o).getTarget();
				 if(node instanceof EndPoint)
				 {
					 criterionReservedNC=(NodeConnection) o;
					 criterionNCList.remove((NodeConnection)o);
					 criterionReservedEndPoint=(EndPoint) node;
				 break;
				 }
			 }
			//******
			 
			 nodes.clear();
			 tempNodeConnection=(NodeConnection) tempPathNode.getPred().get(0);
			 nodes.add((PathNode) tempNodeConnection.getSource());
			 Forwardtraversal(tempNodeConnection, nodes, new ArrayList<EObject>());
			 criterionNCList.remove(tempNodeConnection);
			 if(!nodes.isEmpty())
			 {
				 removeNodes=new RemovePathNodeCommand(nodes.get(0),null);
				 nodes.remove(0);
				 if(!nodes.isEmpty())
				 {
				 for(PathNode node:nodes)
				 
					 removeNodes.add(new RemovePathNodeCommand(node,null));
				 }
				 removeNodes.execute();
				 //add it to the stack
				 cmdStack.push(removeNodes);
			 }
			 //do the same for the remaining links
			 for(NodeConnection nc:criterionNCList)
			 {
				 nodes.clear();
				 Forwardtraversal(nc, nodes, new ArrayList<EObject>());
				 if(!nodes.isEmpty())
				 {
					 removeNodes=new RemovePathNodeCommand(nodes.get(0),null);
					 nodes.remove(0);
					 if(!nodes.isEmpty())
					 {
					 for(PathNode node:nodes)
					 
						 removeNodes.add(new RemovePathNodeCommand(node,null));
					 }
					 removeNodes.execute();
					 //add it to the stack
					 cmdStack.push(removeNodes);
				 } 
			 }
		 }
	 }
/**
 * removes empty paths (branches that have only endPoints) resulted after deleting all unrelated elements
 * @param nodes a list of traversed Orforks,Timers, or Stubs
 */
	 public void removeEmptyPaths(ArrayList<PathNode> nodes)
	 {
		 ArrayList<NodeConnection> branches=new ArrayList<NodeConnection>();
		 PathNode target;
		 NodeConnection nc;
		 RemovePathNodeCommand remove;
		 Iterator<PathNode> iterat=nodes.iterator();
		 while(iterat.hasNext())
		 {
			 PathNode element=iterat.next();
			 branches.clear();
			for(Object link:element.getSucc())
				branches.add((NodeConnection) link);
			while(!branches.isEmpty())
			{
				nc=branches.remove(0);
				target=(PathNode) nc.getTarget();
				 if(SlicingAlg.UnrelatedOrForkbranches.contains(nc) && target instanceof EndPoint && !target.equals(criterionReservedEndPoint))
				 {
					 remove=new RemovePathNodeCommand(target,null);
					 remove.execute();
					 cmdStack.add(remove);
				 }
			 
			}
				
				
			 }
		 
		 
				
		 
	 }
	/**removes empty paths (branches that have only startPoints) resulted after deleting all unrelated Stub INS.
	 * <em>works the same as {@link #removeEmptyPaths(ArrayList)}, but with the difference of moving backward & removing startPoint,not endPoints </em>
     * @param nodes a list of traversed Orforks,Timers, or Stubs</em>
	 *  
	 */
	 public void removeStubINsEmptyPaths(ArrayList<PathNode> nodes)
		{
		 ArrayList<NodeConnection> branches=new ArrayList<NodeConnection>();
		 PathNode source;
		 NodeConnection nc;
		 RemovePathNodeCommand remove;
		 Iterator<PathNode> iterat=nodes.iterator();
		 while(iterat.hasNext())
		 {
			 PathNode element=iterat.next();
			 branches.clear();
			 if(element!=null)
				 for(Object link:element.getPred())
				if(link!=null)
				branches.add((NodeConnection) link);
			while(!branches.isEmpty())
			{
				nc=branches.remove(0);
				source=(PathNode) nc.getSource();
				 if(source instanceof StartPoint)
				 {
					 remove=new RemovePathNodeCommand(source,null);
					 remove.execute();
					 cmdStack.add(remove);
				 }
			 
			}
				
				
			 }
		 
		 
	
		}
	 /**
	  * removes unrelated maps
	  * @param urn
	  */
	 
	 public void removeMaps(URNspec urn,ArrayList<UCMmap>maps)
	 
	 {
		 DeleteMapCommand removeMap;
		 ArrayList<Object> mapList=new ArrayList<Object>();
		 for(Object o:urn.getUrndef().getSpecDiagrams())
			 mapList.add(o);
		 Iterator<Object> iter=mapList.iterator();
		 while(iter.hasNext())
		 {
			 Object obj=iter.next();
			 if(obj instanceof UCMmap )
			 {
				 UCMmap map=(UCMmap) obj;
			 if(!maps.contains(map))
			 {
				 System.out.println("map removed:"+((UCMmap) map).getName());
				 removeMap=new DeleteMapCommand(map);
			      removeMap.execute();
			 }
		 }
		 }
		
	 }
		
	 /**
	  * cleans the maps from empty paths resulted from deletion
	  * @param startPoints all startpoints traversed,other paths with different start points will be deleted
	  */
	 private void cleanMaps(ArrayList<StartPoint> startPoints, ArrayList<UCMmap> maps)
	 {
		 RemovePathNodeCommand removePath;
		 /*
		//saving the file to apply the recent changes
		 IWorkbench workbench= PlatformUI.getWorkbench();
	         IWorkbenchPage page=	 workbench.getActiveWorkbenchWindow().getActivePage();
	         page.getActiveEditor().doSave(new NullProgressMonitor());
	         */
		 //display the names
		/* System.out.println("number of start points="+startPoints.size()+"\n************");
		 for(StartPoint sp:startPoints)
		 {
			 System.out.println("ID="+sp.getId()+", Name="+sp.getName());
		 }*/
			 Iterator<UCMmap> iterat=maps.iterator();
			 while(iterat.hasNext())
			 {
				 UCMmap map=iterat.next();
				 ArrayList<IURNNode> nodes=new ArrayList<IURNNode>();
				 if(map!=null)
				 {
				 for(Object o:map.getNodes())
					 nodes.add((IURNNode) o);
			 Iterator<IURNNode> iter=nodes.iterator();
			 while(iter.hasNext())
			 {
				 IURNNode node=iter.next();
				 
				 if( ( !(node instanceof EndPoint) && node instanceof PathNode && !visitedNodes.contains((EObject)node)) ||(node instanceof PathNode && node instanceof StartPoint && !startPoints.contains((StartPoint)node)))
					{
					 //System.out.println(((StartPoint)node).getName()+ "will be deleted");
					
					 removePath=new RemovePathNodeCommand( (PathNode) node,null);
						removePath.execute();
						cmdStack.push(removePath);
						
					}
				  
			 }
			 // get component refs
			ArrayList< IURNContainerRef> contRef=new ArrayList<IURNContainerRef>();
			 for(Object o:map.getContRefs())
				 contRef.add((IURNContainerRef) o);
		 Iterator<IURNContainerRef> iterator=contRef.iterator();
		 while(iterator.hasNext())
		 {
			 IURNContainerRef node=iterator.next();
			if(node instanceof ComponentRef)
			{
				if(!compRefList.contains((ComponentRef) node))
				compRefList.add((ComponentRef) node);
			
				}
			
			  
		 }
			 // end of compref
			
		 }
			 
			 }
			 /*
			  * ********IMPORTANT**********
               * YOU MUST UNCOMMENT THIS CODE WHEN EXECUTING THE SLICE TO NEW UCM FILE			 
			//saving the file to apply the recent changes
			  workbench= PlatformUI.getWorkbench();
		          page=	 workbench.getActiveWorkbenchWindow().getActivePage();
		         page.getActiveEditor().doSave(new NullProgressMonitor());
		         */
	 }
	 
	 /**
	  * removes unrelated/unbinded componentRefs from maps
	  */
	 public void removeComponentRefs()
	 {
		 System.out.println("number of components found: "+ compRefList.size());
		 
		 for (ComponentRef comp: compRefList)
		 {
			 System.out.println("Component: "+comp.getId());
			 boolean emptyComponent=true;
		 if(!comp.getNodes().isEmpty())
		 {
			 System.out.println("Has: "+ comp.getNodes().size()+" nodes");
			 for(Object element:comp.getNodes())
		 {
			 if(! (element instanceof DirectionArrow) && ! (element instanceof EmptyPoint)  )
			 {
				 //this means it's binded to a path node
				 emptyComponent=false;
				 break;
			 }
		 }
		 }
		 if(emptyComponent)
		 {
			 //remove the component
			 DeleteComponentRefCommand removeComp=new DeleteComponentRefCommand(comp);
			 removeComp.execute();
		 }
		 }
	 }
	 /**
	  * used to cut a path
	  * @param nc the node connection from which the path to be cut
	  */
	 private void cutPath(NodeConnection nc)
	 {
		 Point nodeconnectionmiddle;
		 if(nc!=null)
		 {
		// EditPartViewer viewer=seg.jUCMNav.actions.StaticSlicingAction.Viewr;
		 
		 EditPart NCEditPart=(EditPart)viewer.getEditPartRegistry().get((EObject) nc);
		 if(NCEditPart!=null)
		 {
		  nodeconnectionmiddle = ((NodeConnectionEditPart) NCEditPart).getMiddlePoint();
		 
		 CutAnyPathCommand cut=new CutAnyPathCommand((UCMmap) nc.getDiagram() ,(NodeConnection) nc,nodeconnectionmiddle.x, nodeconnectionmiddle.y);
		 cut.execute();
		 
		 //finally save the changes
		 IWorkbench workbench= PlatformUI.getWorkbench();
         IWorkbenchPage page=	 workbench.getActiveWorkbenchWindow().getActivePage();
         page.getActiveEditor().doSave(new NullProgressMonitor());
		 cmdStack.push(cut);
		 }
		 }
	 }
	 /**
	  * moving forward to get the starting links of non traversed paths that comes after the slicing criterion, we need this method
	  * since criterion resides within a loop in some scenarios. 
	  */
	 private void getCriterionNC(NodeConnection startNC,ArrayList<EObject> pathVisitedForks)
	 {
			
			ArrayList<EObject> currentVisitedForks=new ArrayList<EObject>();
			NodeConnection currentNodeConnection=startNC;
			PathNode CurrentNode=(PathNode)currentNodeConnection.getTarget();
			
			
			//fill currentVisitedJoins with previous/parent path visited joins
			
					for(EObject item:pathVisitedForks)
						currentVisitedForks.add(item);
			//start forward traversal and analyzing
			
			while(! (CurrentNode instanceof EndPoint))
			{
				//handling RespRef,
				////this condition to avoid loop,respref should not equal the criterion indexed at 0
				if(criterion!=null && CurrentNode instanceof RespRef && ((RespRef) CurrentNode ).equals((RespRef)visitedNodes.get(0) ))
				
					return;
				else if ( CurrentNode instanceof RespRef && !visitedNodes.contains((EObject)CurrentNode))
				{
					criterionNCList.add(currentNodeConnection);
					return;
				}
				
					//handling OrFork
				else if(CurrentNode instanceof OrFork)
				{
					//this condition is used to avoid loop, written only for Orjoin, and AndJoin
	if(!currentVisitedForks.contains((EObject)CurrentNode) && visitedNodes.contains((EObject)CurrentNode))
					{
						//add the OrJoin to pathVisitedJoins list, next time the loop will be detected
						//using this list
						currentVisitedForks.add((EObject)CurrentNode);
					
					
					EList orJoinLinks=CurrentNode.getSucc();
					// call the method recursively for each successor link of orfork
					for(Object link:orJoinLinks)	
						getCriterionNC((NodeConnection) link,currentVisitedForks );
					return;
					
					
				}
					// otherwise it's either a loop where we can stop, or not visited which means we can cut from here
					else if(!visitedNodes.contains((EObject)CurrentNode))
					{
						criterionNCList.add(currentNodeConnection);
						return;
					}
					//it's a loop,we just return
					else 
						return;
						
				}
				
				// handling AndFork, in case of criterion forward check, we will stop and return
				else if(CurrentNode instanceof AndFork)
				{
					
					//this condition is used to avoid loop, written only for Orjoin, and AndJoin
					if(!currentVisitedForks.contains((EObject)CurrentNode) && visitedNodes.contains((EObject)CurrentNode))
					{
						//add the OrJoin to pathVisitedJoins list, next time the loop will be detected
						//using this list
						currentVisitedForks.add((EObject)CurrentNode);
					
					
					EList temp=CurrentNode.getSucc();
					// call the method recursively for each successor link of AndFork
					for(Object link:temp)	
						getCriterionNC((NodeConnection) link,currentVisitedForks );
					return;
					
					
				}
					//else it might be not visited, which means we can cut from here
					else if(!visitedNodes.contains((EObject)CurrentNode))
					{
						criterionNCList.add(currentNodeConnection);
						return;
					}
					// otherwise it's a loop
					else
						return;	
				
				}
				
				
				
				// handling OrJoin
				else if (CurrentNode instanceof OrJoin)
				{
					if (!currentVisitedForks.contains(CurrentNode) && visitedNodes.contains((EObject)CurrentNode))
						currentVisitedForks.add(CurrentNode);
					
					else if(!visitedNodes.contains((EObject)CurrentNode))
					{
						criterionNCList.add(currentNodeConnection);
						return;
					}
					//otherwise, it's a loop
					else
						return;
				}
				
				
				// handling AndJoin, no action needed since it's checked separately before as concurrency situation 
				/*
				else if(CurrentNode instanceof AndJoin)
				{		
				
					
					
					}
				  */       
				
				// handling waiting place
				//no action required
				
				// Handling Stub
				
				else if (CurrentNode instanceof Stub)
				{
					if(!currentVisitedForks.contains(CurrentNode) && visitedNodes.contains((EObject)CurrentNode) )
					{
					currentVisitedForks.add(CurrentNode) ;
					EList temp=CurrentNode .getSucc();
					for(Object link:temp)
						getCriterionNC((NodeConnection) link, currentVisitedForks);
				    return;	
					}
					
					//not in visited list which means we can cut from here
					else if (!visitedNodes.contains((EObject)CurrentNode))
					{
						criterionNCList.add(currentNodeConnection);
						return;
					}
					//otherwise it's a loop
					else
						return;
				}
			
				// Handling a timer
				
							else if (CurrentNode instanceof Timer || CurrentNode instanceof WaitingPlace)
							{
								if(!currentVisitedForks.contains(CurrentNode) && visitedNodes.contains((EObject)CurrentNode))
								{
								currentVisitedForks.add(CurrentNode) ;
								EList temp=CurrentNode .getSucc();
								for(Object link:temp)
									getCriterionNC((NodeConnection) link, currentVisitedForks);
							    return;	
								}
								//not in visited, cut from here
								else if(!visitedNodes.contains((EObject)CurrentNode))
								{
									criterionNCList.add(currentNodeConnection);
									return;
								}
								else
									return;
							}
				
				//check rest of pathnodes such as emptypoint , waiting place,etc
							else if(!visitedNodes.contains((EObject)CurrentNode))
							{
								criterionNCList.add(currentNodeConnection);
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
			if(CurrentNode instanceof EndPoint && !visitedNodes.contains((EObject)CurrentNode))
				 criterionNCList.add(currentNodeConnection);
		 return ;
	 }//method end


	 
	 /**
	  * undo the removal of unrelated elements
	  */
	 public void undoRemoval()
	 {
		while(!cmdStack.isEmpty())
		{
			Command cmd=cmdStack.pop();
			cmd.undo();
		}
		
		//saving the file to apply the recent changes
		 IWorkbench workbench= PlatformUI.getWorkbench();
	         IWorkbenchPage page=	 workbench.getActiveWorkbenchWindow().getActivePage();
	         page.getActiveEditor().doSave(new NullProgressMonitor());
	        //to close the editor
	         //page.closeEditor(page.getActiveEditor(), false);
	         URNspec urn=criterion.getRespDef().getUrndefinition().getUrnspec();
	         urn.getUcmspec();
	 }
/**
 * removes unrelated scenario startPoints.If the scenario becomes empty after removing the unrelated startPoints,
 * the whole scenario will be removed too.
 */
	 public void removeUnrelatedScenarioStartPoints()
	 {
		 EList scenarioGr=criterion.getDiagram().getUrndefinition().getUrnspec().getUcmspec().getScenarioGroups();
		 ArrayList<ScenarioGroup> scGr=new ArrayList<ScenarioGroup>();
		 for(Object gr:scenarioGr)
			 scGr.add((ScenarioGroup) gr);
		 Iterator<UCMmap> iterat=maps.iterator();
		 while(iterat.hasNext())
		 {
			 UCMmap map=iterat.next();
			 ArrayList<IURNNode> nodes=new ArrayList<IURNNode>();
			 if(map!=null)
			 {
			 for(Object o:map.getNodes())
				 nodes.add((IURNNode) o);
		 Iterator<IURNNode> iter=nodes.iterator();
		 while(iter.hasNext())
		 {
			 IURNNode node=iter.next();
			 
			 if(node instanceof PathNode && node instanceof StartPoint && !SlicingAlg.startpoints.contains((StartPoint)node))
				{
				 for(ScenarioGroup Sgroup:scGr)
				 {
					 for(Object o:Sgroup.getScenarios())
					 {
						 ScenarioDef def=(ScenarioDef) o;
						 
					 }
				 }
				 //System.out.println(((StartPoint)node).getName()+ "will be deleted");
				}
		 }
			 }
		 }
		
	 }
	public EditPartViewer getViewer() {
		return viewer;
	}

	public void setViewer(EditPartViewer viewer) {
		this.viewer = viewer;
	}

}
