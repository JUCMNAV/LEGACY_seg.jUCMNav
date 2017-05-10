package seg.jUCMNav.model.commands.handlers;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import grl.ActorRef;
import grl.IntentionalElementRef;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.ConsistencyCheck.CreateAndLinkToElementCommand;
import seg.jUCMNav.model.commands.create.AddUrnLinkCommand;
import seg.jUCMNav.model.commands.create.CreateMapCommand;
import ucm.map.ComponentRef;
import ucm.map.RespRef;
import urn.URNlink;
import urn.URNspec;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.IURNNode;
import urncore.URNmodelElement;

public class CreateAndLinkToNewElement extends AbstractHandler implements IHandler {
    private URNspec urnspec;
    private URNmodelElement urnelem;
    private CommandStack cmdStack;
	public static String metadataName="Traces";
	public static String metadataValue="No";
	
	public static URNmodelElement findURNmodelElement(URNmodelElement refElement) {
        URNmodelElement element = refElement;
        
        // Other types are not transformed, only transform these elements. If definition were chosen, they will be directly modified.
        // This means that all selected elements will be modified.
        if (refElement instanceof IntentionalElementRef) {
            element = ((IntentionalElementRef)refElement).getDef();
        } else if (refElement instanceof ActorRef) {
        	element = (URNmodelElement) ((ActorRef)refElement).getContDef();
        } else if (refElement instanceof RespRef) {
        	element = (URNmodelElement) ((RespRef)refElement).getRespDef();
        } else if (refElement instanceof ComponentRef) {
        	element = (URNmodelElement) ((ComponentRef)refElement).getContDef();
        }

        return element;
    }
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		urnspec = ((UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor()).getModel();
		cmdStack = ((UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().
				getActiveEditor()).getDelegatingCommandStack();
		
		// get the selected items in problem view
		IWorkbenchWindow window =  PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IStructuredSelection selection = (IStructuredSelection)window.getSelectionService().getSelection();
		
		// Go through the selected items, find which Element it points to, then mark as no.
		Set<URNmodelElement> elemSet = new HashSet<URNmodelElement>();
		for (Object element : selection.toArray()) {
			if (element instanceof AbstractEditPart) {
				if (((AbstractEditPart) element).getModel() instanceof URNmodelElement) {
					urnelem = (URNmodelElement)((AbstractEditPart) element).getModel();
					urnelem = findURNmodelElement(urnelem);
					if (urnelem == null) {
						continue;
					}
					elemSet.add(urnelem);
				}
			}
		}
		CreateAndLinkToElementCommand createAndLinkCmd = new CreateAndLinkToElementCommand(urnspec, elemSet);
		UCMNavMultiPageEditor editor = (UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		int oldActivePage = editor.getActivePage();
		for (CreateMapCommand cmd: createAndLinkCmd.getCreateMapCommands()) {
			cmdStack.execute(cmd);
		}
		editor.setActivePage((IURNDiagram)urnspec.getUrndef().getSpecDiagrams().get(oldActivePage));
		cmdStack.execute(createAndLinkCmd);

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
