package seg.jUCMNav.model.commands.Slicing;

import org.eclipse.emf.ecore.EObject;

import seg.jUCMNav.actions.StaticSlicingAction;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.commands.delete.internal.RemovePathNodeCommand;
import seg.jUCMNav.model.util.URNElementFinder;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

import javax.swing.JOptionPane;

import org.eclipse.core.internal.utils.FileUtil;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UrnEditor;
import seg.jUCMNav.editparts.NodeConnectionEditPart;
import seg.jUCMNav.editparts.treeEditparts.LabelTreeEditPart;
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
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.jface.wizard.WizardDialog;


import org.eclipse.ocl.utilities.CallingASTNode;
/*import org.eclipse.gmf.runtime.diagram.ui.figures.BorderedNodeFigure;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.draw2d.ui.figures.RoundedRectangleBorder;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;*/
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.Page;

import urncore.IURNDiagram;

/**
 *   This Command performs Backward Static Prevslicing given a respRef in the model
 * 
 * @author Talhaj
 * 
 */

public class StaticSlicingCommand extends Command implements JUCMNavCommand {
	 private EObject Criterion;
	    public static SlicingAlg Prevslicing=null;
	    public SlicingAlg slicing;
	    public String OutPutFile;
	    public URNspec urn;
	    public UCMNavMultiPageEditor  editor;
	  public static StaticSlicingCommand prevCommand=null;
	 public EditPartViewer viewer;
	 public Boolean removeType;
	 public String fileName;
	  private ArrayList<String> criterionVariables=new ArrayList<String>();
	  /**
	   * Creates a new backward slicing command
	   * @param Criterion a resp-ref as a slicing criterion
	   * @param criterionVariables criterion variables related to the slicing criterion
	   */
	  public StaticSlicingCommand(EObject Criterion, ArrayList<String> criterionVariables,Boolean removeType,String fileName)
	  {  
	        this.Criterion = Criterion;
	        this.criterionVariables=criterionVariables;
	       this.removeType=removeType;
	       this.fileName=fileName;
	       
	        setLabel("Backward static slicing"); //$NON-NLS-1$
	       
           
	    }
	
	    /**
	     * @see org.eclipse.gef.commands.Command#canExecute()
	     */
	    public boolean canExecute() {
	    	
	    	
	    	return true;
	        
	    }



/**
 * @see org.eclipse.gef.commands.Command#execute()
 */
public void execute() {
  testPreConditions();
	redo();
	
	
   
}

/**
 * 
 * @see org.eclipse.gef.commands.Command#redo()
 */
public void redo() {
	
	IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	   editor=(UCMNavMultiPageEditor) page.getActiveEditor();
	   //saving the current editor before copying the file
      editor.doSave(new NullProgressMonitor());
	   urn=	editor.getModel();
	   //check if slicing with variables or with no variables
	  
		   //if removal approach is chosen
		   if(removeType)
		   {
		sliceWithRemove();
		// run slicing Algorithm
	     runSlicingAlg();

		   }
		   //otherwise coloring is chosen,therefore no need for output file 
	   else
		   runSlicingAlg();
		   
	   
	   
	  }
/**
 * executes slicing with removal approach, this requires creating a new ucm file,opening it, and then 
 * executing the slicing algorithm by calling {@link #runSlicingAlg(IFile, IURNDiagram)}  
 */
public void sliceWithRemove()
{
	IURNDiagram targetMap=null;
	String ID=null;
	String MapID=null;
	IURNDiagram mapNC=null;
	String idSource=null,idTarget=null;
	if(Criterion instanceof PathNode)
	{
	  ID=((PathNode)Criterion).getId();
	  UCMmap map= (UCMmap) (((PathNode) Criterion).getDiagram());
	   MapID=map.getId();
	}
    //otherwise criterion is a node connection
	else
	{
		mapNC=((NodeConnection) Criterion).getDiagram();
		idSource=((PathNode)((NodeConnection) Criterion).getSource()).getId();
		idTarget=((PathNode)((NodeConnection) Criterion).getTarget()).getId();
		MapID=((UCMmap)mapNC).getId();
	}
	File output=copyfiles(fileName);
	
	
	
	IPath location= Path.fromOSString(output.getAbsolutePath()); 
	IFile iFile=ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(location);
	//refresh workspace before opening the new ucm file
	try {
		iFile.refreshLocal(IResource.DEPTH_ZERO, null);
	} catch (CoreException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	      //Open the editor
	IWorkbenchPage   page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	  UCMNavMultiPageEditor prevEditor=(UCMNavMultiPageEditor) page.getActiveEditor();
	 
    // IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(output.getName());
     try {
   	  IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry().findEditor("seg.jUCMNav.MainEditor");
		  editor = (UCMNavMultiPageEditor) page.openEditor(new FileEditorInput(iFile), desc.getId());
	
		 urn=	editor.getModel();
	
	//set active page to the diagram to which the criterion belong
	for(Object diagram:urn.getUrndef().getSpecDiagrams())
	{
		     if(diagram instanceof UCMmap &&  ((UCMmap) diagram).getId().equals(MapID))
		     {
		    	 targetMap=(IURNDiagram)diagram;
		    	 editor.setActivePage((IURNDiagram)diagram);
		    	 viewer = ((UrnEditor) editor.getActiveEditor()).getGraphicalViewer();
		    	 if(Criterion instanceof RespRef)
		    	Criterion= (RespRef) URNElementFinder.find(urn,ID );
		    	 else if(Criterion instanceof EndPoint)
		    		 Criterion= (EndPoint) URNElementFinder.find(urn,ID );
		    	 //otherwise, the criterion is a node connection
		    	 else
		    	 {
		    		 mapNC= URNElementFinder.findMap(urn, MapID);
		    		 Criterion=(EObject) URNElementFinder.findConnection(mapNC, idSource, idTarget);
		    	 
		    	 }
		    	editor.doSave(new NullProgressMonitor());
		     }
	}
	} catch (PartInitException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
     
}
public void runSlicingAlg()
{
	NodeConnection startingNC=null;
	NodeConnection forwardStartingNC=null;
	if(Criterion instanceof PathNode)
	 startingNC=(NodeConnection) ((PathNode)Criterion).getPred().get(0);
	//otherwise it's a node connection
	else
	{
		//get pred link after orfork,timer,or waitingplace
		startingNC= (NodeConnection) ((NodeConnection) Criterion).getSource().getPred().get(0);
	}
		// used for concurrency forward check
	 if(Criterion instanceof RespRef)
		 forwardStartingNC=(NodeConnection) ((PathNode)Criterion).getSucc().get(0);
	
		slicing=new SlicingAlg(startingNC,criterionVariables);
		//setting required attributes 
		slicing.setCriterion(Criterion);
		slicing.setViewer(viewer);
		slicing.setUrn(urn);
		slicing.setEditor(editor);
		if(Criterion instanceof PathNode)
		slicing.setTargetMap(((PathNode)Criterion).getDiagram());
		else
			slicing.setTargetMap(((NodeConnection)Criterion).getDiagram());
		SlicingAlg.concurrencyGroups.clear();
		SlicingAlg.unrelatedStubINS.clear();
		SlicingAlg.maps.clear();
		SlicingAlg.startpoints.clear();
		SlicingAlg.UnrelatedOrForkbranches.clear();
		SlicingAlg.allNotRelevantRespRefList.clear();
		SlicingAlg.allRelevantRespRefList.clear();
		slicing.visitedNodes.clear();
		slicing.visitedNodes.add(Criterion);
		 
		if(Criterion instanceof PathNode)
		{
			slicing.setUrn(((PathNode)Criterion).getDiagram().getUrndefinition().getUrnspec());
			slicing.maps.add((UCMmap)((PathNode)Criterion).getDiagram());
		}
			else
			{
			slicing.setUrn(((NodeConnection)Criterion).getDiagram().getUrndefinition().getUrnspec());
			slicing.maps.add((UCMmap)((NodeConnection)Criterion).getDiagram());
			//when criterion is a node connection, add unrelated branches of the source
			ArrayList<NodeConnection> temp=new ArrayList<NodeConnection>();
			PathNode source=(PathNode) ((NodeConnection)Criterion).getSource();
			for(Object link:source.getSucc())
				temp.add((NodeConnection) link);
			//remove the current node connection
			temp.remove((NodeConnection)Criterion);
			//add to the unrelatedOrFork list
			for(NodeConnection nc:temp)
				if(!slicing.UnrelatedOrForkbranches.contains(nc))
				slicing.UnrelatedOrForkbranches.add(nc);
			//add source to visited nodes list
			slicing.visitedNodes.add((EObject)source);
			}
			
		 slicing.emptyRespRef.clear();
		 SlicingAlg.criterionExcludedNC.clear();
		 SlicingAlg.concurrencyNodeConnections.clear();
		SlicingAlg.criterionForwardBranches.clear();
		SlicingAlg.allPaths.clear();
		 ArrayList<EObject> pathVisitedJoins=new ArrayList<EObject>();
	     //adding the resp-ref criterion in the visitedNodes list
		 	 
		 if(Criterion instanceof RespRef)
		 {	
			 slicing.forwardNodeConnections.clear();
		 slicing.forwardCheck_criterion(forwardStartingNC, pathVisitedJoins);
			System.out.println("criterion concurrent branches="+ slicing.forwardNodeConnections.size());
			if(!slicing.forwardNodeConnections.isEmpty())
			{
				//if no variables are there, no need to create concurrency groups
				if(criterionVariables!=null)
				slicing.Concurreny_CreateGroup(slicing.getStartingNodeConnection());
				Iterator<NodeConnection> it=slicing.forwardNodeConnections.iterator();
				while(it.hasNext())
				{
					NodeConnection nc=it.next();
					//we add the criterion forward branches to run them after running the basic instance
					SlicingAlg.criterionForwardBranches.add(nc);
					SlicingAlg.concurrencyNodeConnections.add(nc);
				}
			}
			
           }
			//if no criterion variables are there, execute normal slicing algorithm
			if(criterionVariables!=null)
			{
		slicing.executeAlg(criterionVariables ,startingNC,new Stack<Stub>(),pathVisitedJoins,slicing.unrelatedTree,slicing.commonTree,false);
		SlicingAlg.allPaths.add(slicing);
			}
			//otherwise, execute the other form of traversal in which no dependencies are computed
			else
				slicing.executeAlg(startingNC,new Stack<Stub>(),pathVisitedJoins);
		if(Criterion instanceof RespRef)
		{
			//now we create and execute the criterion's forward concurrent slicing instances
			if(!SlicingAlg.criterionForwardBranches.isEmpty())
			{
				SlicingAlg sl;
			Iterator<NodeConnection> iter=SlicingAlg.criterionForwardBranches.iterator();
			while(iter.hasNext())
			{
				NodeConnection nc=iter.next();
				sl=new SlicingAlg(nc,criterionVariables);
				sl.setCriterion(Criterion);
				if(criterionVariables!=null)
				{
				sl.executeAlg(criterionVariables, nc, new Stack<Stub>(), new ArrayList<EObject>(),sl.unrelatedTree,sl.commonTree,false);
				SlicingAlg.allPaths.add(sl);
				}
				//otherwise, execute the other form of traversal in which no dependencies are computed
				else
					sl.executeAlg(nc, new Stack<Stub>(), new ArrayList<EObject>());
			}
			
			}
		}
		
		/*
		//System.out.println("Number of groups:"+SlicingAlg.concurrencyGroups.size());
		int i=1;
		for(ArrayList<NodeConnection> group:SlicingAlg.concurrencyGroups)
		{
			System.out.println("Size of Group NO "+i+"="+group.size());
			i++;
		}
		*/
		
		//handle concurrency
		//first remove inconsistency
		if(criterionVariables!=null)
		for(RespRef resp:SlicingAlg.allRelevantRespRefList)
			SlicingAlg.allNotRelevantRespRefList.removeAll(Collections.singleton(resp));	
		
		System.out.println("NO of Instances="+SlicingAlg.allPaths.size());
		if(criterionVariables!=null)
		{
		ConcurrencyHandler con=new ConcurrencyHandler();
		con.handleGroups(SlicingAlg.concurrencyGroups);
		}
		
		System.out.println("Number of unrelated respRef= "+ slicing.allNotRelevantRespRefList.size());
		System.out.println("Related RespRef= "+slicing.allRelevantRespRefList.size());
		//Now check whether remove approach  or coloring is selected 
		if(removeType)
			if(criterionVariables!=null)
		slicing.removeUnrelatedElements(true);
			else
				slicing.removeUnrelatedElements(false);
		//otherwise coloring is selected
		else
		{    
			System.out.println("coloring chosen");
			slicing.coloring();
			//if criterion variables are there coloring is normally executed
			//otherwise colorAll is invoked
			//slicing.coloring();
		}
		//slicing.removeOrForkBranches();
		//slicing.coloring();
		
	    Prevslicing=slicing;
	    //JOptionPane.showMessageDialog(null,"Criterion Variables"+slicing.getCriterionVariables());
	    
	      testPostConditions();
	     

}
/**
 * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
 */
public void testPreConditions() {

	/*
	if(Prevslicing!=null)
	{
		Prevslicing.undoSlicing();
		Prevslicing=null;
		
		
	}
	*/
}

/**
 * @see org.eclipse.gef.commands.Command#undo()
 */
public void undo() {
	
	if(!removeType)
		slicing.Uncoloring();
  
}
/**
 * copy the current ucm file
 * @param name name of the file
 * @return the new ucm file
 */
public File copyfiles(String name)
{
	IWorkbench workbench= PlatformUI.getWorkbench();
	IWorkbenchPage page=	 workbench.getActiveWorkbenchWindow().getActivePage();
	 UCMNavMultiPageEditor editor = (UCMNavMultiPageEditor) page.getActiveEditor();
	
	 
	
	 IEditorInput input=editor.getEditorInput();
	 IFile file = ((IFileEditorInput)input).getFile();
	 String sourceFile=file.getLocation().toOSString();
	//String sourceFile=file.getFullPath().toOSString();
			 
	 String targetFile=sourceFile.substring(0, sourceFile.lastIndexOf("\\")+1);
	 targetFile+=name+".jucm";
	  System.out.println("LOcation="+sourceFile+"\nnew file="+targetFile);
	File source=new File(sourceFile);
	File target=new File(targetFile);
	
	if(!target.exists())
	{
		try {
			Files.copy(source.toPath(), target.toPath());
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}
	}
	
	else
	{
		int i=1;
		while (target.exists())
		{
			
			targetFile=sourceFile.substring(0, sourceFile.lastIndexOf("\\")+1);
			targetFile+=name+"("+i+").jucm";
			i++;
			target=new File(targetFile);
		}
		target=new File(targetFile);
		try {
			Files.copy(source.toPath(), target.toPath());
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}
	}
		return target;

}
public void testPostConditions() {
	   // assert urn != null && urn.getUrndef() != null && respref != null : "post not null"; //$NON-NLS-1$
	    //assert urn.getUrndef().getResponsibilities().contains(respref) : "post resp not in model"; //$NON-NLS-1$
		
	}


}
