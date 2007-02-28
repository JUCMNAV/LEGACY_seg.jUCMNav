package seg.jUCMNav.views.wizards.performance;

import java.util.Iterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.commands.create.CreateDemandCommand;
import ucm.map.RespRef;
import ucm.performance.Demand;
import ucm.performance.ExternalOperation;
import ucm.performance.GeneralResource;
import ucm.performance.PassiveResource;
import ucm.performance.ProcessingResource;
import urn.URNspec;
import urncore.Component;
import urncore.Responsibility;

/**
 * The page that actually manages responsibilities' demands (resources) 
 * 
 * @author jack
 */
public class ManageDemandPage extends WizardPage {

	private Shell shell;
	private Composite container;
	private Label typeLabel;
	private EObject defaultSelected;
	private URNspec spec;
	private Component[] components;
	private Combo allcomponents;
	private Composite selectInOut;
	private Responsibility responsibility;
	private List inList;
	private Composite buttons;
	private Button left;
	private Button right;
	private List availList;
	private GeneralResource[] availResources;
	private GeneralResource[] boundResources;
	private Demand[] demands;
	private double quantity;
	private IWorkbenchPage workbenchPage;
	private GeneralResource resToManage;


	/**
	 *
	 * @param workbenchPage 
	 * @param defaultSelected
	 * 		responsibility for which demand will be managed
	 */
	public ManageDemandPage(IWorkbenchPage workbenchPage, EObject defaultSelected) {
		super("wizardPage"); //$NON-NLS-1$
		this.setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/perspectiveIcon.gif")); //$NON-NLS-1$
		this.workbenchPage = workbenchPage;
		this.defaultSelected = defaultSelected;

		setTitle("Manage Demand");
		setDescription("man dem.");

	}

	/**
	 * Creates the page.
	 */
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NULL);
		shell = container.getShell();

		FillLayout layout1 = new FillLayout(SWT.VERTICAL);
		container.setLayout(layout1);
		typeLabel = new Label(container, SWT.NULL);
		typeLabel.setText("Define a resource to be associated to a component");

		// 3 cols: in + arrows + avail
		selectInOut = new Composite(container, SWT.NULL);
		RowLayout layout2 = new RowLayout();
		layout2.pack = false;
		selectInOut.setLayout(layout2);

		// inlist:
		Composite cin = new Composite(selectInOut, SWT.NULL);
		FillLayout layout3 = new FillLayout(SWT.VERTICAL);
		cin.setLayout(layout3);
		Label lin = new Label(cin, SWT.NULL);
		lin.setText("assigned availResources");
		lin.setAlignment(SWT.LEAD);
		inList = new List(cin, SWT.READ_ONLY);
		inList.setSize(150, 120);
		inList.addSelectionListener(inListListener);

		// buttons:
		buttons = new Composite(selectInOut, SWT.NULL);
		FillLayout layout4 = new FillLayout(SWT.VERTICAL);
		layout4.spacing = 5;
		buttons.setLayout(layout4);
		buttons.pack(true);
		left = new Button(buttons, SWT.Activate);
		left.setText("< add <");
		left.setEnabled(false);
		left.addSelectionListener(leftBtnListener);

		right = new Button(buttons, SWT.Activate);
		right.setText("> remove >");
		right.setEnabled(false);
		right.addSelectionListener(rightBtnListener);

		// avail:
		Composite cavail = new Composite(selectInOut, SWT.NULL);
		FillLayout layout5 = new FillLayout(SWT.VERTICAL);
		cavail.setLayout(layout5);
		Label lavail = new Label(cavail, SWT.NULL);
		lavail.setText("available availResources");
		lavail.setAlignment(SWT.LEAD);
		availList = new List(cavail, SWT.READ_ONLY);
		availList.setSize(150, 120);
		availList.addSelectionListener(availListListener);

		// dialogChanged();
		// setControl(container);

		initialize();
		setTitle("Resource Type");
		setControl(container);

	}

	private SelectionListener inListListener = new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			inListChanged();
		}
	};

	private void inListChanged() {
		if (inList.getSelectionIndex() != -1) {
			resToManage = boundResources[inList.getSelectionIndex()];
			right.setEnabled(true);
			availList.deselectAll();
			left.setEnabled(false);
		} else {
			right.setEnabled(false);
		}
	}

	private SelectionListener rightBtnListener = new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			removeRes();
		}
	};

	private void removeRes() {
//		int idx = inList.getSelectionIndex();
//		GeneralResource resToRemove = boundResources[inList.getSelectionIndex()];
//		responsibility.getDemands().remove(resToRemove);
		responsibility.getDemands().remove(resToManage);
		int itemToRemove = -1;
		int i = 0;
		for (Iterator demand = responsibility.getDemands().iterator(); demand.hasNext();) {
			Demand dem = (Demand) demand.next();
			if (dem.getResource().equals(resToManage)) {
				itemToRemove = i;
			}				
			i++;
		}
		if (itemToRemove != -1) {
			responsibility.getDemands().remove(itemToRemove);	
			updateLists();
		}
	}

	private SelectionListener availListListener = new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			availListChanged();
		}
	};

	private void availListChanged() {
		if (availList.getSelectionIndex() != -1) {
			resToManage = availResources[availList.getSelectionIndex()];
			left.setEnabled(true);
			inList.deselectAll();
			right.setEnabled(false);
		} else {
			left.setEnabled(false);
		}
	}

	private SelectionListener leftBtnListener = new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			createResCmd();
			updateLists();
		}
	};

	public void createResCmd() {
		CommandStack cs = ((UCMNavMultiPageEditor) workbenchPage.getActiveEditor()).getDelegatingCommandStack();
		CompoundCommand command = new CompoundCommand();

		CreateDemandCommand createCmd = new CreateDemandCommand(spec, resToManage, quantity, responsibility);
		command.add(createCmd);

		// use a command to be undoable.
		if (command.canExecute())
			cs.execute(command);
		}

	/**
	 * Ensures that the selection is legal
	 */
	private void dialogChanged() {

		// if (invalid choices made and/or values entered)
		// updateStatus("Please Select The Type of Resource");
		// else
		// updateStatus(null);

	}

	/**
	 * Tests if the current workbench selection is a suitable container to use.
	 */
	private void initialize() {
		if (defaultSelected != null) {
			if (defaultSelected instanceof RespRef) {
				responsibility = ((RespRef) defaultSelected).getRespDef();
				spec = responsibility.getUrndefinition().getUrnspec();
			}
		}
		updateLists();
	}
	private void updateLists() {
		GeneralResource res;
		if (spec != null) {
			availResources = new GeneralResource[spec.getUrndef().getUrnspec().getUcmspec().getResources().size()];
		}
		if (responsibility != null) {
			boundResources = new GeneralResource[responsibility.getDemands().size()];
		}
		int indexRes = 0;
		int indexDem = 0;
		inList.removeAll();
		availList.removeAll();
		boolean found;
		for (Iterator resource = spec.getUrndef().getUrnspec().getUcmspec().getResources().iterator(); resource.hasNext();) {
			res = (GeneralResource) resource.next();
			// if availResources[i] not already in demand! _js_
			found = false;
			for (Iterator checkDem = responsibility.getDemands().iterator(); checkDem.hasNext();) {
				Demand adem = (Demand) checkDem.next();
				GeneralResource agen = adem.getResource();
				found = found | agen.equals(res);
			}
			if (!found) {
				if (res instanceof PassiveResource) {
					availList.add(((PassiveResource) res).getComponent().getName() + " (Passive Resource)");
				} else if (res instanceof ProcessingResource) {
					for (Iterator comp = ((ProcessingResource) res).getComponents().iterator(); comp.hasNext();) {
						availList.add(((Component) comp.next()).getName() + " (Processing Resource)");
					}
				} else if (res instanceof ExternalOperation) {
					availList.add(((ExternalOperation) res).getDescription() + " (External Operation)");
				}
				availResources[indexRes] = res;
				indexRes++;
			} else {
				if (res instanceof PassiveResource) {
					inList.add(((PassiveResource) res).getComponent().getName() + " (Passive Resource)");
				} else if (res instanceof ProcessingResource) {
					for (Iterator comp = ((ProcessingResource) res).getComponents().iterator(); comp.hasNext();) {
						inList.add(((Component) comp.next()).getName() + " (Processing Resource)");
					}
				} else if (res instanceof ExternalOperation) {
					inList.add(((ExternalOperation) res).getDescription() + " (External Operation)");
				}
				boundResources[indexDem] = res;
				indexDem++;
			}
		}
		inList.deselectAll();
		inListChanged();
		availList.deselectAll();
		availListChanged();
	}

	/**
	 * Updates the status of the window
	 * 
	 * @param message
	 *            the error message or null if no error message.
	 */
	private void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}

	private GeneralResource getResource() {
		return resToManage;
	}

	private double getQuantity() {
		return quantity;
	}

	private Responsibility getResponsibility() {
		return responsibility;
	}
}
