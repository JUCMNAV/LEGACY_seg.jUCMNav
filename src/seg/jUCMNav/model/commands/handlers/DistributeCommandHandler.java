package seg.jUCMNav.model.commands.handlers;

import grl.ActorRef;
import grl.IntentionalElementRef;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.commands.ExecutionEvent;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editparts.ActorRefEditPart;
import seg.jUCMNav.editparts.ComponentRefEditPart;
import seg.jUCMNav.editparts.IntentionalElementEditPart;
import seg.jUCMNav.editparts.PathNodeEditPart;
import seg.jUCMNav.editparts.RespRefEditPart;
import seg.jUCMNav.editparts.StubEditPart;
import seg.jUCMNav.model.commands.changeConstraints.AlignCommand;
import seg.jUCMNav.model.commands.changeConstraints.DistributeCommand;
import seg.jUCMNav.sourceProviders.AlignStateSourceProvider;
import seg.jUCMNav.views.wizards.DistributeCustomDialog;
import ucm.map.ComponentRef;
import ucm.map.PathNode;
import urn.URNspec;
import urncore.URNmodelElement;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.HandlerEvent;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.services.ISourceProviderService;

public class DistributeCommandHandler extends AbstractHandler implements IHandler {

    
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		final CommandStack cmdStack = ((UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().
				getActiveEditor()).getDelegatingCommandStack();
		
		// getting the selection type
		ISourceProviderService service = (ISourceProviderService) 
        		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getService(ISourceProviderService.class);
        AlignStateSourceProvider alignStateSourceProvider = (AlignStateSourceProvider) 
        		service.getSourceProvider(AlignStateSourceProvider.SELECTION_TYPE);
       
       final String selectionType = alignStateSourceProvider.getCurrentState().get("seg.jUCMNav.AlignSelectionType");
       
       final String distributeType = event.getParameter("seg.jUCMNav.DistributeType");
       
       final List sel = alignStateSourceProvider.getFilterdSelection();

      
   	
       if( distributeType.compareTo("seg.jUCMNav.DistributeCustom") == 0 ){
   		  
    	   DistributeCustomDialog dialog = new DistributeCustomDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
    	   HashMap<String, String> values = dialog.getValues();
    	  
    	   if( values != null){
    		   cmdStack.execute(new DistributeCommand(sel, Integer.valueOf(selectionType), values.get("DistributeType"), true));
    	   }
    	  
   		}else{
       
   			cmdStack.execute(new DistributeCommand(sel, Integer.valueOf(selectionType), distributeType, false));
   		}
		return null;
	}
	
	@Override
	public void setEnabled(Object evaluationContext) {	
	}
	
	
	@Override
	public boolean isEnabled() {
		
		return true;
	}
}
