package seg.jUCMNav.actions;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.util.BundleUtility;
import org.eclipse.ui.part.FileEditorInput;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.RootEditPart;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.Slicing.*;
import seg.jUCMNav.model.commands.create.CreatePathCommand;
import seg.jUCMNav.views.preferences.DisplayPreferences;
import seg.jUCMNav.views.preferences.GeneralPreferencePage;
import seg.jUCMNav.views.wizards.NewUcmFileWizard;
import seg.jUCMNav.views.wizards.importexport.jUCMNavLoader;
import ucm.map.*;
import urn.URNspec;
import urncore.Responsibility;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UrnEditor;





























//GMF
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

public class StaticSlicingAction extends URNSelectionAction {
	public static final String StaticSlicing = "seg.jUCMNav.StaticSlicing"; //$NON-NLS-1$
	public static EditPartViewer Viewr;
	public static boolean dialogFinished;
	RespRef R;URNspec urn;
	EObject criterion;
	 /**
     * @param part
     */
    public StaticSlicingAction(IWorkbenchPart part) {
        super(part);
             
        setId(StaticSlicing);
        dialogFinished=false;
        Bundle bundle = Platform.getBundle(JUCMNavPlugin.PLUGIN_ID);
        URL fullPathString = BundleUtility.find(bundle, "icons/SlicingIcon.gif");
        setImageDescriptor( ImageDescriptor.createFromURL(fullPathString)); //$NON-NLS-1$
    }
    
    /**
     * True if we select one responsibility .
     */
    protected boolean calculateEnabled() {
    	EditPart t;
    	
       SelectionHelper sel = new SelectionHelper(getSelectedObjects());
  if ( getSelectedObjects().size()==1 && (sel.getSelectionType()==SelectionHelper.RESPONSIBILITYREF || sel.getSelectionType()==SelectionHelper.NODECONNECTION || sel.getSelectionType()==SelectionHelper.ENDPOINT) ) 
  {
        
        	
        //	RespRef temp=(RespRef) getSelectedObjects().get(0);
        //	RespRef ref= (RespRef) ((EObject) getSelectedObjects().get(0));
        	
                 
        		t=  ((EditPart) getSelectedObjects().get(0));
        		 //( (GraphicalEditPart) t).getFigure().setForegroundColor(ColorConstants.green);
                Viewr= t.getViewer();
               EObject obj= (EObject) t.getModel();
              // EditPart modelEditPart=(EditPart)Viewr.getEditPartRegistry().get(obj);
               //( (GraphicalEditPart) modelEditPart).getFigure().setForegroundColor(ColorConstants.green);
        		
        		if(obj instanceof RespRef)
        		{
            	R= (RespRef) obj ;
                criterion=R;
        	
        	return true;
        		}
        		
        		else if(obj instanceof EndPoint)
        		{
        			criterion=obj;
        			return true;
        		}
        		
        		else if(obj instanceof NodeConnection )
        		{
        			PathNode pred=(PathNode) ((NodeConnection) obj).getSource();
        			if(pred instanceof OrFork || pred instanceof Timer || pred instanceof WaitingPlace)
        			{
        				criterion=obj;
        				return true;
        			}
        		}
     	
        }
        
         return false;
    }
    /**
     * @return a {@link StaticSlicingCommand}
     */
    protected Command getCommand() {
    	//***********
		
         //*********************
        
    	Boolean isEmptyExpression=false;
    	if(calculateEnabled())
        
    	{   
    		ArrayList<String> expressionVariables=null;
    		//check if it's not empty
    		if(criterion instanceof RespRef)
    		{
    			if(R.getRespDef().getExpression()!=null && !R.getRespDef().getExpression().isEmpty())
        		{
        			expressionVariables=new ArrayList<String>();	
    	    	DataControlDep obj=new DataControlDep(null);
    	    	obj.setExpression(R.getRespDef().getExpression());
    	    	expressionVariables=obj.getAllVariables();
        		}
        		//otherwise it's empty
        		else
        			isEmptyExpression=true;
    	    	
    		}
    		
    		//if criterion is endpoint
    		else if(criterion instanceof EndPoint)
    		{
    			EndPoint endpoint= (EndPoint) criterion;
    			expressionVariables=new ArrayList<String>();	
    	    	DataControlDep obj=new DataControlDep(null);
    	    	String condition=endpoint.getPostcondition().getExpression();
    	    	expressionVariables=obj.getConditionVariables(condition);
    		}
    		
    		//otherwise it's node connection criterion
    		else
    		{
    			NodeConnection nodeConn= (NodeConnection) criterion;
    			String condition=nodeConn.getCondition().getExpression();
    			expressionVariables=new ArrayList<String>();	
    	    	DataControlDep obj=new DataControlDep(null);
    	    	expressionVariables=obj.getConditionVariables(condition);
    		}
    		String name=null;
    		if(criterion instanceof RespRef)
    			name=R.getRespDef().getName();
    		else if(criterion instanceof EndPoint)
    			name= ((EndPoint) criterion).getName();
    		else
    		{
    			name=((PathNode) ((NodeConnection)criterion).getSource()).getName()+" Condition";
    		}
    		WizardDialog dialog = new WizardDialog(null, new SelectSlicingCriterionWizard(expressionVariables,name));
	    	//dialog.setMinimumPageSize(800, 200);
	    	dialog.open();
	    	SelectSlicingCriterionWizard wiz=(SelectSlicingCriterionWizard)dialog.getCurrentPage().getWizard();
	    	
	    	//removal can not be executed if no code exists, only coloring can
	    	if(dialogFinished)
	    	{
	    		//System.out.println("Finish clicked");
    		StaticSlicingCommand slicing= new  StaticSlicingCommand(criterion,wiz.getSelectedVariables(),wiz.Removetype,wiz.fileName);
    		StaticSlicingCommand.prevCommand=slicing;
    		wiz.dispose();
    		//CreatePathCommand path=new CreatePathCommand((UCMmap) ((PathNode)criterion).getDiagram(), 300,200);
    		//path.execute();
    		
    		return  slicing;
	    	
	    	}
	    	else
	    	{
	    		//System.out.println("Cancel Pressed");
	    		return null;
	    		
	    	}
	    	}
	    	
	    		
	    
	    	
    	
    		
    	else
    		return null; 
        }    	
        
    
        	   
    
    

}
