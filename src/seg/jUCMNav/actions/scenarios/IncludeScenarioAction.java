package seg.jUCMNav.actions.scenarios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.editparts.strategyTreeEditparts.ScenarioLabelTreeEditPart;
import seg.jUCMNav.scenarios.ScenarioUtils;
import seg.jUCMNav.views.wizards.scenarios.IncludeScenario;
import ucm.scenario.ScenarioDef;

/**
 * Opens the include scenario wizard
 * 
 * @author jkealey
 */
public class IncludeScenarioAction extends URNSelectionAction {

	public static final String INCLUDESCENARIO = "seg.jUCMNav.IncludeScenario"; //$NON-NLS-1$

	protected ScenarioDef scenario;

	/**
	 * @param part
	 */
	public IncludeScenarioAction(IWorkbenchPart part) {
		super(part);
		setId(INCLUDESCENARIO);
		setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/ucm16.gif")); //$NON-NLS-1$
	}

	/**
	 * True if we've selected something with code. 	 */
	protected boolean calculateEnabled() {
		initScenario();
		return scenario!=null && ScenarioUtils.getPossibleIncludedScenarios(scenario).size()>0;
	}

	protected void initScenario() {
		List list = getSelectedObjects();
		ArrayList list2 = new ArrayList();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Object object = (Object) iter.next();
		
			if (object instanceof ScenarioLabelTreeEditPart)
				list2.add(((ScenarioLabelTreeEditPart)object).getParent());
			else
				list2.add(object);
		}
		SelectionHelper sel = new SelectionHelper(list2);
		switch (sel.getSelectionType()) {
		case SelectionHelper.SCENARIO:
		case SelectionHelper.INITIALIZATION: 
			scenario = sel.getScenario();
			break;
		default: 
			scenario = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		IncludeScenario wizard = new IncludeScenario();

		StructuredSelection selection = new StructuredSelection(scenario);
		wizard.init(PlatformUI.getWorkbench(), selection);
		WizardDialog dialog = new WizardDialog(shell, wizard);
		dialog.open();

	}

}