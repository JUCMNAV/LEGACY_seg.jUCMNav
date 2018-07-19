package seg.jUCMNav.editpolicies.element;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.ui.PlatformUI;

import asd.Community;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editparts.CommunityEditPart;
import seg.jUCMNav.model.commands.delete.DeleteCommunityCommand;
 import org.eclipse.ui.IWorkbenchPart;
/**
 * ComponentEditPolicy for UCM labels. Returns delete commands. See ConditionComponentEditPolicy for conditions.
 * 
 * @author Jordan
 */
public class CommunityComponentEditPolicy extends ComponentEditPolicy {

    /**
     * Returns a DeleteLabelCommand
     * 
     * @see org.eclipse.gef.editpolicies.ComponentEditPolicy#getDeleteCommand(org.eclipse.gef.requests.GroupRequest)
     */
    protected Command getDeleteCommand(GroupRequest request) {
    	( (CommunityEditPart)	getHost()).getASDiagramEditPart().refreshVisuals();
        Community community= (Community) getHost().getModel();
        java.util.Map registry;
         UCMNavMultiPageEditor editor;
        if (getHost().getViewer() instanceof TreeViewer) {
            // we need an editpart registry with NodeConnectionEditParts
             editor = (UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
            registry = editor.getCurrentPage().getGraphicalViewer().getEditPartRegistry();
        } else
            registry = getHost().getViewer().getEditPartRegistry();
        
        
       DeleteCommunityCommand deleteCommand = new DeleteCommunityCommand(((CommunityEditPart)getHost()).getDiagram().getUrndefinition().getUrnspec(),community,((CommunityEditPart)getHost()).getDiagram());
       editor = (UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
       CommandStack stack = getHost().getViewer().getEditDomain().getCommandStack();
       ( (UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor()).setDirty(true);
     
      // getHost().getViewer().getEditDomain().getCommandStack().
   

        return deleteCommand;
       

    }
    
   

}