package seg.jUCMNav.actions.staticSemantic;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.FileEditorInput;

import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.util.URNNamingHelper;
import seg.jUCMNav.rulemanagement.RuleManagementCheckingMessage;
import seg.jUCMNav.staticSemantic.StaticSemanticChecker;
import urncore.URNmodelElement;

/**
 * This is an action to the menu "Static Check".
 * 
 * @author Byrne Yan e
 */
public class VerifyStaticSemanticDelegate implements IEditorActionDelegate {
	private UCMNavMultiPageEditor editor;

	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		// DB: avoid class cast exception with e4
		if (targetEditor instanceof UCMNavMultiPageEditor) {
			editor = (UCMNavMultiPageEditor) targetEditor;
		}
		// editor = (UCMNavMultiPageEditor) targetEditor;
	}

	protected UCMNavMultiPageEditor getEditor() {
		return editor;
	}

	/**
	 * Check all selected rules by class StaticSemanticChecker and then report
	 * the result in the problem view.
	 * 
	 * @see StaticSemanticChecker
	 */
	public void run(IAction action) {
		if (getEditor() != null) {
			Vector problems = new Vector();
			StaticSemanticChecker.getInstance().check(getEditor().getModel(),
					problems);
			refreshProblemView(problems);

			String header = Messages
					.getString("VerifyStaticSemanticDelegate.StaticSemanticCheck"); //$NON-NLS-1$
			boolean hasError = false;
			for (Iterator iterator = problems.iterator(); iterator.hasNext();) {
				RuleManagementCheckingMessage m = (RuleManagementCheckingMessage) iterator
						.next();
				if (m.getSeverity() == IMarker.SEVERITY_ERROR) {
					hasError = true;
					break;
				}
			}

			String message = Messages
					.getString("VerifyStaticSemanticDelegate.NoErrors"); //$NON-NLS-1$
			if (problems.size() > 0)
				message = ((RuleManagementCheckingMessage) problems.get(0))
						.getMessage(); // first is the info message.

			message += Messages
					.getString("VerifyStaticSemanticDelegate.HasErrors"); //$NON-NLS-1$
			if (!hasError)
				MessageDialog.openInformation(getEditor().getSite().getShell(),
						header, message);
			else
				MessageDialog.openError(getEditor().getSite().getShell(),
						header, message);
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {

	}

	/**
	 * 
	 * @param problems
	 *            A list of problems which contain check results information.
	 *            The content of the vector must be type of
	 *            RuleManagementCheckingMessage.
	 * @see RuleManagementCheckingMessage
	 */
	protected void refreshProblemView(Vector problems) {
		if (getEditor() != null) {
			IFile resource = ((FileEditorInput) getEditor().getEditorInput())
					.getFile();
			try {

				IMarker[] existingMarkers = resource.findMarkers(
						IMarker.PROBLEM, true, 3);
				for (int i = 0; i < existingMarkers.length; i++) {
					IMarker marker = existingMarkers[i];
					marker.delete();
				}
			} catch (CoreException ex) {
				problems.add(new RuleManagementCheckingMessage(ex.getMessage())); //$NON-NLS-1$
			}

			if (problems.size() > 0) {

				for (int i = 0; i < problems.size(); ++i) {
					RuleManagementCheckingMessage o = (RuleManagementCheckingMessage) problems
							.get(i);
					try {
						IMarker marker = resource.createMarker(IMarker.PROBLEM);
						marker.setAttribute(IMarker.SEVERITY, o.getSeverity());
						marker.setAttribute(IMarker.MESSAGE, o.toString());
						if (o.getLocation() instanceof URNmodelElement) {
							URNmodelElement elem = (URNmodelElement) o
									.getLocation();
							marker.setAttribute(IMarker.LOCATION,
									URNNamingHelper.getName(elem));
							marker.setAttribute(
									"EObject", ((URNmodelElement) o.getLocation()).getId()); //$NON-NLS-1$
						} else if (o.getLocation() != null) {
							marker.setAttribute(IMarker.LOCATION, o
									.getLocation().toString());
						}

					} catch (CoreException ex) {
						problems.add(new RuleManagementCheckingMessage(ex
								.getMessage())); //$NON-NLS-1$
					}

				}
			}
		}
	}
}
