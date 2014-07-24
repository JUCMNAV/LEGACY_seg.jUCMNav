package seg.jUCMNav.model.commands.handlers;

import grl.ActorRef;
import grl.IntentionalElementRef;

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
import seg.jUCMNav.sourceProviders.AlignStateSourceProvider;
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

public class AlignCommandHandler extends AbstractHandler implements IHandler {

    
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		CommandStack cmdStack = ((UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().
				getActiveEditor()).getDelegatingCommandStack();
		
		// getting the selection type
		ISourceProviderService service = (ISourceProviderService) 
        		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getService(ISourceProviderService.class);
        AlignStateSourceProvider alignStateSourceProvider = (AlignStateSourceProvider) 
        		service.getSourceProvider(AlignStateSourceProvider.SELECTION_TYPE);
       
       String selectionType = alignStateSourceProvider.getCurrentState().get("seg.jUCMNav.AlignSelectionType");
       
       String alignType = event.getParameter("seg.jUCMNav.AlignType");
       
       List sel = alignStateSourceProvider.getFilterdSelection();
       
       cmdStack.execute(new AlignCommand(sel, Integer.valueOf(selectionType), alignType ));

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
