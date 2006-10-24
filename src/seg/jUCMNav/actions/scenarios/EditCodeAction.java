package seg.jUCMNav.actions.scenarios;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.views.wizards.scenarios.CodeEditor;

/**
 * Opens the code editor
 * 
 * @author jkealey
 */
public class EditCodeAction extends URNSelectionAction {

	public static final String EDITCODEACTION = "seg.jUCMNav.EditCodeAction"; //$NON-NLS-1$

	private EObject obj;

	/**
	 * @param part
	 */
	public EditCodeAction(IWorkbenchPart part) {
		super(part);
		setId(EDITCODEACTION);
		setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Binding16.gif")); //$NON-NLS-1$
	}

	/**
	 * True if we've selected something with code. 	 */
	protected boolean calculateEnabled() {
		SelectionHelper sel = new SelectionHelper(getSelectedObjects());
		switch (sel.getSelectionType()) {
		case SelectionHelper.STARTPOINT: {
			obj = sel.getStartpoint().getPrecondition();
			return true;
		} 
		case SelectionHelper.ENDPOINT: {
			obj = sel.getEndpoint().getPostcondition();
			return true;
		}
		case SelectionHelper.CONDITION: 
			obj = sel.getCondition();
			return true;
		case SelectionHelper.NODECONNECTION:
			if (sel.getNodeconnection().getCondition() != null) {
				obj = sel.getNodeconnection().getCondition();
				return true;
			}
			break;
		case SelectionHelper.RESPONSIBILITY: 
			obj = sel.getRespDef();
			return true;
		case SelectionHelper.RESPONSIBILITYREF:
			obj = sel.getRespRef().getRespDef();
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		CodeEditor wizard = new CodeEditor();

		StructuredSelection selection = new StructuredSelection(obj);
		wizard.init(PlatformUI.getWorkbench(), selection);
		WizardDialog dialog = new WizardDialog(shell, wizard);
		dialog.open();

	}

}