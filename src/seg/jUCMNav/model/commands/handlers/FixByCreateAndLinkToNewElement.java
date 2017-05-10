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
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.commands.ConsistencyCheck.CreateAndLinkToElementCommand;
import seg.jUCMNav.model.commands.create.CreateMapCommand;
import seg.jUCMNav.model.util.URNElementFinder;
import urn.URNspec;
import urncore.IURNDiagram;
import urncore.URNmodelElement;

public class FixByCreateAndLinkToNewElement extends AbstractHandler implements IHandler {
	private URNspec urnspec;
	private URNmodelElement urnelem;
	private CommandStack cmdStack;
	public static String metadataName="Traces";
	public static String metadataValue="No";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		urnspec = ((UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor()).getModel();
		cmdStack = ((UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().
				getActiveEditor()).getDelegatingCommandStack();
		System.out.println("FixByCreateAndLinkToNewElement");

		// get the selected items in problem view
		IWorkbenchWindow window =  PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IStructuredSelection selection = (IStructuredSelection)window.getSelectionService().getSelection("org.eclipse.ui.views.ProblemView");

		// Go through the selected items, find which Element it points to, then then create corresponding element and link to it.
		Set<URNmodelElement> elemSet = new HashSet<URNmodelElement>();
		for (Object markerEntry : selection.toArray()) {
			if (markerEntry instanceof IAdaptable) {
				IAdaptable adaptable = (IAdaptable)markerEntry;
				IMarker marker = (IMarker)adaptable.getAdapter(IMarker.class);
				String elementID="";
				try {
					elementID = (String)marker.getAttribute("EObject");
					urnelem = (URNmodelElement) URNElementFinder.find(urnspec, elementID);
					if (urnelem == null) {
						continue;
					}
					elemSet.add(urnelem);
				} catch (CoreException e) {
					System.out.println(e);
				}
			}
		}

		CreateAndLinkToElementCommand createCmd = new CreateAndLinkToElementCommand(urnspec, elemSet);
		UCMNavMultiPageEditor editor = (UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		int oldActivePage = editor.getActivePage();
		for (CreateMapCommand cmd: createCmd.getCreateMapCommands()) {
			cmdStack.execute(cmd);
		}
		editor.setActivePage((IURNDiagram)urnspec.getUrndef().getSpecDiagrams().get(oldActivePage));
		cmdStack.execute(createCmd);

		Set<URNmodelElement> linkedElements = createCmd.getLinkedElements();
		// remove the problems that have been fixed from problem view;
		// problems are fixed if the element in in linkedElements.
		for (Object markerEntry : selection.toArray()) {
			if (markerEntry instanceof IAdaptable) {
				IAdaptable adaptable = (IAdaptable)markerEntry;
				IMarker marker = (IMarker)adaptable.getAdapter(IMarker.class);
				String elementID="";
				try {
					elementID = (String)marker.getAttribute("EObject");
					urnelem = (URNmodelElement) URNElementFinder.find(urnspec, elementID);
					if (urnelem == null) {
						continue;
					}
					if (linkedElements.contains(urnelem)) {
						marker.delete();
					}
				} catch (CoreException e) {
					System.out.println(e);
				}
			}
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
