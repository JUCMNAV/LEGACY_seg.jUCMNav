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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

import javax.swing.JOptionPane;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;

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
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.PathNode;
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

/**
 * Handles the case of concurrent paths in UCM by considering all possible execution scenarios and recompute dependencies
 * accordingly
 * @author Taha
 */
public class ConcurrencyHandler {

	public ArrayList<NodeConnection> currentGroup=new ArrayList<NodeConnection>();
	public ArrayList<SlicingAlg> allPaths=SlicingAlg.allPaths;
	Boolean updateFlag=false;
	Boolean replacedFlag=false;
	private ArrayList<String> criterionVariables=new ArrayList<String>();
	public ConcurrencyHandler() {

	}

	/**
	 * this method will handle each concurrency groups to recompute the dependencies
	 */
	public void handleGroups(ArrayList<ArrayList<NodeConnection>> groups )
	{
		Iterator<ArrayList<NodeConnection>> iter=groups.iterator();
		//for each group
		int no=1;
		while (iter.hasNext())
		{
			//System.out.println("Group No:"+no+"\n***8888888");
			ArrayList<NodeConnection> singleGroup=iter.next();
			currentGroup=singleGroup;
			
			Set<Integer> indices = new HashSet<Integer>();
			//fill all indices of the group elements 
			for(int i=0;i<currentGroup.size();i++)
			{
				indices.add(i);
			}
			//get all possible sequences
			ComputeCombinations(indices, new Stack<Integer>(),indices.size());
		
		}
	}
	
	/**
	 * recomputes dependencies of a particular slicing instance using its unrelatedRespTree list. The recomputation is 
	 * performed only on the unrelated resp objects collected during traversal and stored in the tree list
	 * @param critVar List of criterion variables against which dependencies will computed
	 * @param slicingInsance the target slicing instance
	 * @param unrelatedTree tree structure list of unrelated Resp-ref
	 * @param position the index of the slicing instance in the {@link #allPaths} list
	 * @param WithUpdate whether the criterion list of the instance to be updated 
	 */
	public void recompute(ArrayList<String> critVar ,SlicingAlg slicingInsance,UnrelatedRespTree unrelatedTree, int position, Boolean WithUpdate)
	{
		ArrayList<String> criterionVariables=new ArrayList<String>();
		
		DataControlDep dep;
		//fill the variables
		for(String var:critVar)
			criterionVariables.add(var);
		dep=new DataControlDep(criterionVariables);
		if(!unrelatedTree.unrelatedResp.isEmpty())
		{
	       
	       Iterator<RespRef> it=unrelatedTree.unrelatedResp.iterator();
	       while(it.hasNext())
	       {
	    	   RespRef resp=it.next();
	    	 //if it's not in the global unrelated resp list, remove it
	    	  // if(!SlicingAlg.allNotRelevantRespRefList.contains(resp))
	    	   //continue;
	    	   //else
	    	   {
	    		   dep.setExpression(resp.getRespDef().getExpression());
	    		   dep.analizeExpression();
	    		   if(dep.Relevant)
	    		   {
	    			  // unrelatedTree.unrelatedResp.remove(resp);
	    			   SlicingAlg.allNotRelevantRespRefList.removeAll(Collections.singleton(resp));
	    			   SlicingAlg.allRelevantRespRefList.add(resp);
	    			   //NotRelatedRespRef.remove(resp);
	    			   //AllRelatedRespRef.add(resp);
	    		   }
	    	   }
	    	updateCriterionVariables(dep.getCriterionVariables());   
	       }
	       /*
	       for(RespRef resp:unrelatedTree.unrelatedResp)
	       {
	    	   //if it's not in the global unrelated resp list, remove it
	    	   if(!NotRelatedRespRef.contains(resp))
	    	   {
	    		   unrelatedTree.unrelatedResp.remove(resp);
	    	   }
	    	   else
	    	   {
	    		   dep.setExpression(resp.getRespDef().getExpression());
	    		   dep.analizeExpression();
	    		   if(dep.Relevant)
	    		   {
	    			   unrelatedTree.unrelatedResp.remove(resp);
	    			   SlicingAlg.allNotRelevantRespRefList.removeAll(Collections.singleton(resp));
	    			   SlicingAlg.allRelevantRespRefList.add(resp);
	    			   //NotRelatedRespRef.remove(resp);
	    			   //AllRelatedRespRef.add(resp);
	    		   }
	    	   }
	       }
	       */
		}
		
		//continue recomputation if there are children paths within the tree
		if(!unrelatedTree.ChildrednPaths.isEmpty())
		{
			Iterator<UnrelatedRespTree> iter=unrelatedTree.ChildrednPaths.iterator();
			while(iter.hasNext())
			{
				UnrelatedRespTree child=iter.next();
				recompute(dep.getCriterionVariables(), slicingInsance, child, position,WithUpdate);
			}
			/*
			for(UnrelatedRespTree child:unrelatedTree.ChildrednPaths)
			{
				recompute(criterionVariables, slicingInsance, child, position,WithUpdate);
			}
			*/
		}
		else if(!unrelatedTree.childrenConcurrencyPaths.isEmpty())
		{
			updateCriterionVariables(dep.getCriterionVariables());
			if(WithUpdate)
				slicingInsance.updateCriterionVariables(dep.getCriterionVariables());
			//replace the updated instance
			allPaths.set(position, slicingInsance);
			SlicingAlg concurrentchild;
			Iterator<NodeConnection> iterat=unrelatedTree.childrenConcurrencyPaths.iterator();
			while(iterat.hasNext())
			{
				NodeConnection child=iterat.next();
				concurrentchild=getSlicingInstance(child);
				recompute(dep.getCriterionVariables(), concurrentchild,concurrentchild.unrelatedTree ,allPaths.indexOf(concurrentchild) , true);
				
			}
			/*
			for(NodeConnection child:unrelatedTree.childrenConcurrencyPaths)
			{
				concurrentchild=getSlicingInstance(child);
				recompute(criterionVariables, concurrentchild,concurrentchild.unrelatedTree ,allPaths.indexOf(concurrentchild) , true);
			}
			*/
		}
		
		else
		{
			updateCriterionVariables(dep.getCriterionVariables());
		}
		
		
		
	}


                                     
/**
 * computes all possible combinations of set of indexes, we use this method to get all possible 
 * sequences of concurrent elements in a given group 	
 * @param items  
 * @param permutation
 * @param size
 */
	public  void  ComputeCombinations(Set<Integer> items, Stack<Integer> permutation, int size) {

	    /* permutation stack has become equal to size that we require */
	    if(permutation.size() == size) {
	        /* print the permutation */
	       
	    	/* Test*
	    	 Integer[] a=permutation.toArray(new Integer[0]);
	    	System.out.println("********************\nPossible scenarios");
	    	for(int i=0;i<a.length;i++)
	    	{
	    		System.out.print(a[i]);
	    	}*/
	    	
	    	computeSequence(permutation.toArray(new Integer[0]));
	    }

	    /* items available for permutation */
	    Integer[] availableItems = items.toArray(new Integer[0]);
	    for(Integer i : availableItems) {
	        /* add current item */
	        permutation.push(i);

	        /* remove item from available item set */
	        items.remove(i);

	        /* pass it on for next permutation */
	        ComputeCombinations(items, permutation, size);

	        /* pop and put the removed item back */
	        items.add(permutation.pop());
	    }
	}

	/**
	 * compute dependencies given a particular sequence of concurrent elements
	 * @param sequence
	 */
public void computeSequence(Integer[] sequence) {

	SlicingAlg parent=getParent(sequence);
	//we get the first instance in the sequence because it's list will be used against the rest
	SlicingAlg instance=getSlicingInstance(currentGroup.get(sequence[0]));
	//fill the list
	criterionVariables.clear();
	for(String var:instance.getCriterionVariables())
	{
		criterionVariables.add(var);
	}
	int position=0;
	//System.out.println("Sequence="+sequence);
	//System.out.println("Variables="+criterionVariables+"\n*********");
	for(int index=1;index<sequence.length;index++)
	{
		SlicingAlg slicingInsance=getSlicingInstance(currentGroup.get(sequence[index]));
		 position=allPaths.indexOf(slicingInsance);
		// System.out.println("item:"+sequence[index]);
		 recompute(criterionVariables, slicingInsance,slicingInsance.unrelatedTree , position, false);
		// handle what comes after andFork, it's shared among all instances
		 if(slicingInsance.getStoppingFork()!=null && parent!=null)
		 {
			 position=allPaths.indexOf(parent);
				recompute(criterionVariables, parent, parent.commonTree, position, false); 
		 }
			 
	}
	
	// handle what comes after andFork, it's shared among all instances
	/*
	SlicingAlg sl=getParent(sequence);
	if(sl!=null)
	{
		position=allPaths.indexOf(sl);
		recompute(criterionVariables, sl, sl.commonTree, position, false);
	}
	
	*/
	
}

public SlicingAlg getParent(Integer[] sequence)
{
	SlicingAlg parent=null;
	boolean found=false;
	for(int index=0;index<sequence.length;index++)
	{
		parent=getSlicingInstance(currentGroup.get(sequence[index]));
		if(parent.AndForkFlag)
		{	
		found=true;
		break;
		}
	}
	
	if(found)
	return parent;
	else
		return null;
}

/**
 * searches the {@link #allPaths} list and get the slicing instance with the given startingNode connection
 * @param startingNodeConnection starting node connection of the slice instance to be retrieved
 * @return
 */
public SlicingAlg getSlicingInstance(NodeConnection startingNodeConnection)
{
	SlicingAlg instance=null;
	for(SlicingAlg sl:allPaths)
	{
		if(sl.getStartingNodeConnection().equals(startingNodeConnection))
			instance=sl;
	}
	return instance;
}

/**
 * get the index of slicing instance given its starting node connection
 * @param startingNodeConnection starting node connection of the slicing instance
 * @return index of the slicing instance in the {@link #allPaths} list
 */
public int getInstanceIndex(NodeConnection startingNodeConnection)
{
	int index=0;
	for(SlicingAlg sl:allPaths)
	{
		if(sl.getStartingNodeConnection().equals(startingNodeConnection))
			index=allPaths.indexOf(sl);
	}
	
	return index;
}

public void updateCriterionVariables(ArrayList<String> criterVar)
{
	for(String var:criterVar)
		if(!criterionVariables.contains(var))
			criterionVariables.add(var);
}
}



