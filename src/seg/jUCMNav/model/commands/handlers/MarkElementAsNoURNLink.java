package seg.jUCMNav.model.commands.handlers;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import grl.ActorRef;
import grl.IntentionalElementRef;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.commands.metadata.ChangeTracesCommand;
import ucm.map.ComponentRef;
import ucm.map.RespRef;
import urn.URNspec;
import urncore.URNmodelElement;

public class MarkElementAsNoURNLink extends AbstractHandler implements IHandler {
	private URNspec urnspec;
	private URNmodelElement urnelem;
	public static String metadataName="Traces";
	public static String metadataValue="No";

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
		CommandStack cmdStack = ((UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().
				getActiveEditor()).getDelegatingCommandStack();

		// get the selected items from editor
		IWorkbenchWindow window =  PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IStructuredSelection selection = (IStructuredSelection)window.getSelectionService().getSelection();

		String markType = event.getParameter("seg.jUCMNav.commandParameter.quickfix.markType");
		// when we choose to check a element, we simply remove the "Traces" metadata.
		if (markType.equals("Yes")) {
			markType = "";
		}

		// Go through the selected items, find which Element it points to, then mark as No/Yes.  
		CompoundCommand cpdCmd = new CompoundCommand();
		Set<URNmodelElement> markedAlready = new HashSet<URNmodelElement>();
		for (Object element : selection.toArray()) {
			if (element instanceof AbstractEditPart) {
				if (((AbstractEditPart) element).getModel() instanceof URNmodelElement) {
					urnelem = (URNmodelElement)((AbstractEditPart) element).getModel();
					urnelem = findURNmodelElement(urnelem);
					if (null != urnelem) {
						if (markedAlready.contains(urnelem)) {
							continue;
						}
						cpdCmd.add(new ChangeTracesCommand(urnspec, urnelem, markType));
						markedAlready.add(urnelem);
					}
				}
			}
		}
		cmdStack.execute(cpdCmd);
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
