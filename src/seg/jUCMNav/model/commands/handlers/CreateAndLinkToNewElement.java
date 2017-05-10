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
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import grl.ActorRef;
import grl.IntentionalElementRef;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.commands.ConsistencyCheck.CreateAndLinkToElementCommand;
import seg.jUCMNav.model.commands.create.CreateMapCommand;
import ucm.map.ComponentRef;
import ucm.map.RespRef;
import urn.URNspec;
import urncore.IURNDiagram;
import urncore.URNmodelElement;

public class CreateAndLinkToNewElement extends AbstractHandler implements IHandler {
	private URNspec urnspec;
	private URNmodelElement urnelem;
	private CommandStack cmdStack;
	public static String metadataName="Traces";
	public static String metadataValue="No";

	/**
	 * @param refElement 
	 * 			selected element
	 * @return 
	 * 			1, if it's a reference, return definition of the selected element;
	 *			2, if it's a definition, return itself;
	 */
	public static URNmodelElement findURNmodelElement(URNmodelElement refElement) {
		URNmodelElement element = refElement;

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

		// get the selected items in editor
		IWorkbenchWindow window =  PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IStructuredSelection selection = (IStructuredSelection)window.getSelectionService().getSelection();

		// Go through the selected items, find which Element it points to, then create corresponding element and link to it.
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
		// CreateMapCommand will change focus of tab to newly added map. So, store here, restore after execution.
		UCMNavMultiPageEditor editor = (UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		int oldActivePage = editor.getActivePage();
		
		/* CreateMapCommand are executed independently, see link for more info.
		 * http://jucmnav.softwareengineering.ca/foswiki/ProjetSEG/DevDocCommandStack
		 * 
		 */
		for (CreateMapCommand cmd: createAndLinkCmd.getCreateMapCommands()) {
			cmdStack.execute(cmd);
		}
		// restore active tab.
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
