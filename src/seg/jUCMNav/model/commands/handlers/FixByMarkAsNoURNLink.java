package seg.jUCMNav.model.commands.handlers;

import java.util.HashSet;
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

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.commands.metadata.ChangeTracesCommand;
import seg.jUCMNav.model.util.URNElementFinder;
import urn.URNspec;
import urncore.URNmodelElement;

public class FixByMarkAsNoURNLink extends AbstractHandler implements IHandler {
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

		// Go through the selected items, find which Element it points to, then mark as "Traces=No".
		CompoundCommand cpdCmd = new CompoundCommand();
		Set<URNmodelElement> markedAlready = new HashSet<URNmodelElement>();
		for (Object markerEntry : selection.toArray()) {
			if (markerEntry instanceof IAdaptable) {
				IAdaptable adaptable = (IAdaptable)markerEntry;
				IMarker marker = (IMarker)adaptable.getAdapter(IMarker.class);
				try {
					String elementID = (String)marker.getAttribute("EObject");
					urnelem = (URNmodelElement) URNElementFinder.find(urnspec, elementID);
					if (markedAlready.contains(urnelem) || null == urnelem) {
						marker.delete();
						continue;
					}

					cpdCmd.add(new ChangeTracesCommand(urnspec, urnelem, "No"));

					marker.delete();
					markedAlready.add(urnelem);
				} catch (CoreException e) {
					System.out.println(e);
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
