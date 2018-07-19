package seg.jUCMNav.editpolicies.element;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.ui.PlatformUI;

import asd.Tool;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editparts.ASDiagramEditPart;
import seg.jUCMNav.editparts.ToolEditPart;
import seg.jUCMNav.model.commands.delete.DeleteToolCommand;
 import org.eclipse.ui.IWorkbenchPart;
/**
 * ComponentEditPolicy for UCM labels. Returns delete commands. See ConditionComponentEditPolicy for conditions.
 * 
 * @author Jordan
 */
public class ToolComponentEditPolicy extends ComponentEditPolicy {

    /**
     * Returns a DeleteLabelCommand
     * 
     * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#getDeleteCommand(org.eclipse.gef.requests.GroupRequest)
     */
	
	
	
    protected Command getDeleteCommand(GroupRequest request) {
   ( (ToolEditPart)	getHost()).getASDiagramEditPart().refreshVisuals();
     Tool tool= (Tool) getHost().getModel();
        java.util.Map registry;
         UCMNavMultiPageEditor editor;
        if (getHost().getViewer() instanceof TreeViewer) {
            // we need an editpart registry with NodeConnectionEditParts
             editor = (UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
            registry = editor.getCurrentPage().getGraphicalViewer().getEditPartRegistry();
        } else
            registry = getHost().getViewer().getEditPartRegistry();
        
        
       DeleteToolCommand deleteCommand = new DeleteToolCommand(((ToolEditPart)getHost()).getDiagram().getUrndefinition().getUrnspec(),tool,((ToolEditPart)getHost()).getDiagram());
       editor = (UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
       CommandStack stack = getHost().getViewer().getEditDomain().getCommandStack();
       ( (UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor()).setDirty(true);
     
      // getHost().getViewer().getEditDomain().getCommandStack().
   

        return deleteCommand;
       

    }
    
   

}