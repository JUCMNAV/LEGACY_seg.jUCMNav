package seg.jUCMNav.views.wizards.performance;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWizard;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.commands.create.CreateActiveProcessingCommand;
import seg.jUCMNav.model.commands.create.CreateExternalOperationCommand;
import seg.jUCMNav.model.commands.create.CreatePassiveResourceCommand;
import ucm.map.ComponentRef;
import ucm.map.RespRef;
import ucm.map.impl.RespRefImpl;
import ucm.performance.DeviceKind;
import urn.URNspec;
import urncore.Component;
import urncore.ComponentElement;
import urncore.Responsibility;

/**
 * Wizard for editing resources.
 * 
 * Can be called from component (ref) or - for now - responsibility (ref).
 * 
 * @author jack
 */
public class CreateResource extends Wizard {

	// first page1QueryResType: chose between active & passive
	private CreateResourceQueryType page1QueryResType;

	// get opTime and choice of Processing or External
	private CreateResourceActive page2QueryActiveType;

	//
	private CreateResourceActiveProcessing page3ActiveProcessing;

	private CreateResourceActiveExternal page4ActiveExternal;

	// passive: finished if called from component; ask for component (if called
	// from responsibility)
	private CreateResourcePassive page5Passive;

	private ISelection selection;

	private EObject defaultSelected;

	// for when selection is componentRef (or similar)
	private Component component = null;

	// for when selection is responsibility
	private Responsibility responsibility = null;

	// because...
	URNspec urn;

	/*
	 * resources should be "manageable" from responsibilities (Demand) component
	 * refs (binding to) (futur) resource folder in outline
	 */

	/**
	 * The workbench page1QueryResType in which we are working
	 */
	protected IWorkbenchPage workbenchPage;

	/**
	 * Creates the editor
	 */
	public CreateResource() {
		super();
		setNeedsProgressMonitor(true);
		this.setWindowTitle("Create a Resource"); //$NON-NLS-1$
	}

	/**
	 * Adding the page1QueryResType to the wizard.
	 */
	public void addPages() {
		page1QueryResType = new CreateResourceQueryType(selection, defaultSelected);
		page2QueryActiveType = new CreateResourceActive(selection, defaultSelected);
		page3ActiveProcessing = new CreateResourceActiveProcessing(selection, defaultSelected);
		page4ActiveExternal = new CreateResourceActiveExternal(selection, defaultSelected);
		page5Passive = new CreateResourcePassive(selection, defaultSelected);
		addPage(page1QueryResType);
		addPage(page2QueryActiveType);
		addPage(page3ActiveProcessing);
		addPage(page4ActiveExternal);
		addPage(page5Passive);
	}

	public IWizardPage getNextPage(IWizardPage page) {
		if (page == page1QueryResType) {
			if (page1QueryResType.getType() != null && page1QueryResType.getType().equals(CreateResourceQueryType.sPassive)) {
				if (component != null) {
					return super.getNextPage(page);
				} else {
					return page5Passive; // _js_ this should set "component"
											// ...
				}
			} else
				return page2QueryActiveType;
		} else if (page == page2QueryActiveType) {
			if (page2QueryActiveType.getType().equals(CreateResourceQueryType.sActiveProcessing)) {
				return page3ActiveProcessing;
			} else if (page2QueryActiveType.getType().equals(CreateResourceQueryType.sActiveExternal)) {
				return page4ActiveExternal;
			} else {
				return super.getNextPage(page);
			}
		} else
			return super.getNextPage(page);
	}

	/*
	 * 
	 */
	public boolean canFinish() {
		boolean couldFinish;
		couldFinish = false;
		String test1, test2;
		DeviceKind test3;
		String test4;

		if (component != null) {
			test1 = page1QueryResType.getType();
			// if called from component
			if (page1QueryResType.getType() == CreateResourceQueryType.sPassive) {
				couldFinish = true;
				// } else {
				// return super.canFinish();
			} else if (page1QueryResType.getType() == CreateResourceQueryType.sActive) {
				test2 = page2QueryActiveType.getType();
				// check if opTime is valid
				if (page2QueryActiveType.getType() == CreateResourceQueryType.sActiveProcessing) {
					test3 = page3ActiveProcessing.getDeviceKind();
					// check pageX.getKind()
					if (page3ActiveProcessing.getDeviceKind() != null) {
						couldFinish = true;
					}
				} else if (page2QueryActiveType.getType() == CreateResourceQueryType.sActiveExternal) {
					test4 = page4ActiveExternal.getDescription().toString();
					if (page4ActiveExternal.getDescription().toString().length() != 0) {
						couldFinish = true;
					}
				}
			}
		} else if (responsibility != null) {
			// if called from responsibility (Demand?)
		}
		return couldFinish;
	}

	/**
	 * This method is called when 'Finish' button is pressed in the wizard. We
	 * will create an operation and run it using wizard as execution context.
	 */
	public boolean performFinish() {
		// assuming all has been set for processing at this point _js_
		CommandStack cs = ((UCMNavMultiPageEditor) workbenchPage.getActiveEditor()).getDelegatingCommandStack();
		CompoundCommand command = new CompoundCommand();
		DeviceKind testDeviceKind;
		URNspec testUrn;
		Component testComponent;
		double testDouble;
		if (component != null) {
			testUrn = urn;
			testComponent = component;
			if (page1QueryResType.getType() == CreateResourceQueryType.sPassive) {
				CreatePassiveResourceCommand createCmd = new CreatePassiveResourceCommand(urn, component);
				command.add(createCmd);
			} else if (page2QueryActiveType.getType() == CreateResourceQueryType.sActiveProcessing) {
				DeviceKind deviceKind = page3ActiveProcessing.getDeviceKind();
				double opTime = page2QueryActiveType.getOpTime();
				CreateActiveProcessingCommand createCmd = new CreateActiveProcessingCommand(urn, component, opTime, deviceKind);
				command.add(createCmd);
			} else if (page2QueryActiveType.getType() == CreateResourceQueryType.sActiveExternal) {
				String description = page4ActiveExternal.getDescription();
				double opTime = page2QueryActiveType.getOpTime();
				CreateExternalOperationCommand createCmd = new CreateExternalOperationCommand(urn, opTime, description);
				command.add(createCmd);
			} else {
				// abort ??
			}
		} else if (responsibility != null) {
			// not there yet _js_
		}

		// use a command to be undoable.
		if (command.canExecute())
			cs.execute(command);

		return true;
	}

	/**
	 * Throws an error using the message.
	 * 
	 * @param message
	 *            the error message.
	 * @throws CoreException
	 *             the generated exception.
	 */
	private void throwCoreException(String message) throws CoreException {
		IStatus status = new Status(IStatus.ERROR, "seg.jUCMNav", IStatus.OK, message, null); //$NON-NLS-1$
		throw new CoreException(status);
	}

	/**
	 * We will accept the selection in the workbench to see if we can initialize
	 * from it.
	 * 
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	public void init(IWorkbench workbench, EObject defaultSelected) {
		this.defaultSelected = defaultSelected;
		this.workbenchPage = workbench.getActiveWorkbenchWindow().getActivePage();

		component = null;
		responsibility = null;
		if (defaultSelected instanceof ComponentRef) {
			component = (Component) ((ComponentRef) defaultSelected).getContDef();
		} else if (defaultSelected instanceof ComponentElement) {
			component = (Component) ((ComponentElement) defaultSelected);
		} else if (defaultSelected instanceof Component) {
			component = (Component) defaultSelected;
		} else if (defaultSelected instanceof Responsibility) {
			responsibility = (Responsibility) defaultSelected;
		} else if (defaultSelected instanceof RespRefImpl) {
			responsibility = ((RespRefImpl) defaultSelected).getRespDef();
		} else if (defaultSelected instanceof RespRef) {
			responsibility = ((RespRef) defaultSelected).getRespDef();
		}

		if (component != null) {
			urn = component.getUrndefinition().getUrnspec();
		} else if (responsibility != null) {
			urn = responsibility.getUrndefinition().getUrnspec();
		} else {
			try {
				throwCoreException("Abort:  unexpected case");
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
