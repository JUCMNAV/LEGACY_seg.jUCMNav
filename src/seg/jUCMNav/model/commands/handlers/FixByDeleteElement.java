package seg.jUCMNav.model.commands.handlers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import grl.Actor;
import grl.IntentionalElement;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.commands.create.CreateMapCommand;
import seg.jUCMNav.model.commands.delete.DeleteActorCommand;
import seg.jUCMNav.model.commands.delete.DeleteComponentCommand;
import seg.jUCMNav.model.commands.delete.DeleteIntentionalElementCommand;
import seg.jUCMNav.model.commands.delete.DeleteMapCommand;
import seg.jUCMNav.model.commands.delete.DeleteResponsibilityCommand;
import seg.jUCMNav.model.util.URNElementFinder;
import ucm.map.UCMmap;
import urn.URNspec;
import urncore.Component;
import urncore.Responsibility;
import urncore.URNmodelElement;

public class FixByDeleteElement extends AbstractHandler implements IHandler {
	private URNspec urnspec;
	private URNmodelElement urnelem;
	public static String metadataName="Traces";
	public static String metadataValue="No";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		urnspec = ((UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor()).getModel();
		CommandStack cmdStack = ((UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().
				getActiveEditor()).getDelegatingCommandStack();

		// get the selected items in problem view
		IWorkbenchWindow window =  PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IStructuredSelection selection = (IStructuredSelection)window.getSelectionService().getSelection("org.eclipse.ui.views.ProblemView");

		// Go through the selected items, find which Element it points to, then delete it.	
		CompoundCommand cpdCmd = new CompoundCommand();
		List<DeleteMapCommand> deleteMapCmdList = new ArrayList<DeleteMapCommand>();
		Set<URNmodelElement> deletedAlready = new HashSet<URNmodelElement>();
		for (Object markerEntry : selection.toArray()) {
			if (markerEntry instanceof IAdaptable) {
				IAdaptable adaptable = (IAdaptable)markerEntry;
				IMarker marker = (IMarker)adaptable.getAdapter(IMarker.class);
				String elementID="";
				try {
					elementID = (String)marker.getAttribute("EObject");
					urnelem = (URNmodelElement) URNElementFinder.find(urnspec, elementID);
					if (deletedAlready.contains(urnelem)) {
						marker.delete();
						continue;
					}

					if (urnelem instanceof IntentionalElement) {
						cpdCmd.add(new DeleteIntentionalElementCommand((IntentionalElement)urnelem));
					} else if (urnelem instanceof Actor ) {
						cpdCmd.add(new DeleteActorCommand((Actor)urnelem));
					} else if (urnelem instanceof Component ) {
						cpdCmd.add(new DeleteComponentCommand((Component)urnelem));
					} else if (urnelem instanceof Responsibility ) {
						cpdCmd.add(new DeleteResponsibilityCommand((Responsibility)urnelem));
					} else if (urnelem instanceof UCMmap ) {
						deleteMapCmdList.add(new DeleteMapCommand((UCMmap)urnelem));
					}
					marker.delete();
					deletedAlready.add(urnelem);
				} catch (CoreException e) {
					System.out.println(e);
				}
			}
		}
		for (DeleteMapCommand cmd: deleteMapCmdList) {
			cmdStack.execute(cmd);
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
