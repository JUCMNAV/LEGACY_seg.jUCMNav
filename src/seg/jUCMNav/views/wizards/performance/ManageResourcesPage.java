package seg.jUCMNav.views.wizards.performance;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPage;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.commands.create.CreateActiveProcessingCommand;
import seg.jUCMNav.model.commands.create.CreateExternalOperationCommand;
import seg.jUCMNav.model.commands.create.CreatePassiveResourceCommand;
import seg.jUCMNav.model.commands.delete.DeleteResourceCommand;
import ucm.performance.DeviceKind;
import ucm.performance.ExternalOperation;
import ucm.performance.GeneralResource;
import ucm.performance.PassiveResource;
import ucm.performance.ProcessingResource;
import urn.URNspec;
import urncore.Component;

/**
 * The page actually containing the metadata editor for urn model elements.
 * 
 * @author jack
 */

public class ManageResourcesPage extends WizardPage {

    private Shell shell;
    private Composite container;
    private ISelection selection;
    private EObject defaultSelected;
    private Combo availableResources;
    private Combo availableKinds;
    private List availableComponents;
    private URNspec urn;
    private GeneralResource[] resources;
    private Text resName;
    private Text description;
    private Text opTimeValue;
    private Component[] componentsInSpec;
    private Button resTypePassive;
    private Button resTypeProcessing;
    private Button resTypeExternal;
    private Canvas cv;
    private Button deleteButton;
    private Button updateResourceButton;
    private Button newButton;
    private Button addButton;
    private boolean containsErrors = false;
    private IWorkbenchPage workbenchPage;

    /**
         * The selection contains urn model elements. Loaded in
         * 
         * @param workbenchPage
         * @param urn
         * 
         * @param selection
         * @param defaultSelected
         */
    public ManageResourcesPage(IWorkbenchPage workbenchPage, URNspec urn, ISelection selection, EObject defaultSelected) {
	super("wizardPage"); //$NON-NLS-1$
	this.setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/perspectiveIcon.gif")); //$NON-NLS-1$
	this.selection = selection;
	this.defaultSelected = defaultSelected;
	this.urn = urn;
	this.workbenchPage = workbenchPage;

	setTitle("Manage Resources");
	setDescription("Select a resource and modify it or delete it.  Or create new ones.");

    }

    /**
         * Creates the page.
         */
    public void createControl(Composite parent) {

	container = new Composite(parent, SWT.NULL);
	shell = container.getShell();
	GridLayout containerLayout = new GridLayout();
	containerLayout.numColumns = 5;
	container.setLayout(containerLayout);

	// //////////////////////////////////////////////////////////////////////////////////////////////
	// Selection (ID) of Resource

	Label resChoice = new Label(container, SWT.NULL);
	resChoice.setText("Resource");
	availableResources = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
	GridData gd1 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
	gd1.horizontalSpan = 4;
	availableResources.setLayoutData(gd1);
	availableResources.addSelectionListener(new SelectionAdapter() {
	    public void widgetDefaultSelected(SelectionEvent e) {
	    }

	    public void widgetSelected(SelectionEvent e) {
		// there exists some resources already
		if (availableResources.getSelectionIndex() >= 0) {
		    updateForSelection();
		    deleteButton.setEnabled(true);
		    updateResourceButton.setEnabled(false);
		    dialogChanged();
		}
	    }
	});

	// //////////////////////////////////////////////////////////////////////////////////////////////
	// Name (for all resource types)

	Label resNameLabel = new Label(container, SWT.NULL);
	resNameLabel.setText("Name");

	resName = new Text(container, SWT.BORDER);
	GridData gd11 = new GridData(GridData.FILL_HORIZONTAL);
	gd11.horizontalSpan = 4;
	resName.setLayoutData(gd11);
	resName.addModifyListener(new ModifyListener() {
	    public void modifyText(ModifyEvent e) {
		dialogChanged();
	    }
	});

	// //////////////////////////////////////////////////////////////////////////////////////////////
	// Buttons (actions)

	// when selected, fill up the rest of the form accordingly
	Label blank1 = new Label(container, SWT.NULL);
	blank1.setText("");

	deleteButton = new Button(container, SWT.PUSH);
	deleteButton.setText("DELETE");
	deleteButton.addSelectionListener(new SelectionAdapter() {
	    public void widgetSelected(SelectionEvent e) {
		// should be active only when resource is selected
		delResource();
		resTypePassive.setEnabled(false);
		resTypeProcessing.setEnabled(false);
		resTypeExternal.setEnabled(false);

		availableResources.select(1);
		availableResources.setEnabled(true);
		initialize();
		if (availableResources.getItemCount() != 0) {
		    updateForSelection();    
		}
		
		
	    }
	});
	// when hit, delete this resource

	updateResourceButton = new Button(container, SWT.PUSH);
	updateResourceButton.setText("UPDATE");
	updateResourceButton.addSelectionListener(new SelectionAdapter() {
	    public void widgetSelected(SelectionEvent e) {
		// should be active only when resource is selected
		updateResource();
		resTypePassive.setEnabled(false);
		resTypeProcessing.setEnabled(false);
		resTypeExternal.setEnabled(false);

		availableResources.select(1);
		availableResources.setEnabled(true);
		initialize();
		if (availableResources.getItemCount() != 0) {
		    updateForSelection();    
		}
	    }
	});

	newButton = new Button(container, SWT.PUSH);
	newButton.setText("NEW");
	// create new editable entry; value mandatory; selection impossible; cancel enabled
	newButton.addSelectionListener(new SelectionAdapter() {
	    public void widgetSelected(SelectionEvent e) {
		// should be active only when resource is selected
		resTypePassive.setEnabled(true);
		resTypePassive.setSelection(true);
		resTypeProcessing.setEnabled(true);
		resTypeProcessing.setSelection(false);
		resTypeExternal.setEnabled(true);
		resTypeExternal.setSelection(false);

		availableResources.deselectAll();
		availableResources.setEnabled(false);

		resName.setText("new resource name");
		resName.setEnabled(true);

		deleteButton.setEnabled(false);
		updateResourceButton.setEnabled(false);
		newButton.setEnabled(false);
		addButton.setEnabled(true);

		availableKinds.deselectAll();
		availableKinds.select(1);
		availableKinds.setEnabled(false);

		opTimeValue.setEnabled(false);

		availableComponents.deselectAll();
		availableComponents.setEnabled(true);

		description.setText("new resource description");

	    }
	});

	addButton = new Button(container, SWT.PUSH);
	addButton.setText("ADD");
	addButton.addSelectionListener(new SelectionAdapter() {
	    public void widgetSelected(SelectionEvent e) {
		// should be active only when resource is selected
		// add
		addResource();
		resTypePassive.setEnabled(false);
		resTypeProcessing.setEnabled(false);
		resTypeExternal.setEnabled(false);

		availableResources.select(1);
		availableResources.setEnabled(true);
		initialize();
		updateForSelection();
	    }
	});

	// //////////////////////////////////////////////////////////////////////////////////////////////
	// Type of Resource
	// select the first entry then redraw (what if first entry is empty?)

	// type of resource
	Label type = new Label(container, SWT.NULL);
	type.setText("Type");

	cv = new Canvas(container, SWT.NULL);
	GridData gd9 = new GridData();
	gd9.horizontalSpan = 4;
	cv.setLayoutData(gd9);
	cv.setLayout(new FillLayout(SWT.VERTICAL));

	resTypePassive = new Button(cv, SWT.RADIO);
	resTypePassive.setText("Passive");
	resTypePassive.addSelectionListener(new SelectionAdapter() {
	    public void widgetSelected(SelectionEvent e) {
		if (resTypePassive.getSelection()) {
		    opTimeValue.setEnabled(false);
		    availableKinds.setEnabled(false);
		    availableComponents.setEnabled(true); // SINGLE
		    description.setEnabled(false);
		}
	    }
	});

	resTypeProcessing = new Button(cv, SWT.RADIO);
	resTypeProcessing.setText("Active Processing");
	resTypeProcessing.addSelectionListener(new SelectionAdapter() {
	    public void widgetSelected(SelectionEvent e) {
		if (resTypeProcessing.getSelection()) {
		    opTimeValue.setEnabled(true);
		    availableKinds.setEnabled(true);
		    availableComponents.setEnabled(true); // MULTI
		    description.setEnabled(false);
		}
	    }
	});

	resTypeExternal = new Button(cv, SWT.RADIO);
	resTypeExternal.setText("Active External");
	resTypeExternal.addSelectionListener(new SelectionAdapter() {
	    public void widgetSelected(SelectionEvent e) {
		if (resTypeExternal.getSelection()) {
		    opTimeValue.setEnabled(true);
		    availableKinds.setEnabled(false);
		    availableComponents.setEnabled(false);
		    description.setEnabled(true);
		}
	    }
	});

	// //////////////////////////////////////////////////////////////////////////////////////////////
	// opTime (for active resources)

	// opTime (if Active)
	Label opTimeLabel = new Label(container, SWT.NULL);
	opTimeLabel.setText("opTime");

	opTimeValue = new Text(container, SWT.BORDER);
	// text with double

	opTimeValue.setText("0.0");
	GridData gd5 = new GridData();
	gd5.horizontalSpan = 4;
	// gd5.widthHint = 50;
	opTimeValue.setLayoutData(gd5);
	opTimeValue.addModifyListener(new ModifyListener() {
	    public void modifyText(ModifyEvent e) {
		dialogChanged();
	    }
	});

	// //////////////////////////////////////////////////////////////////////////////////////////////
	// Kind (if Active Processing)

	Label kindLabel = new Label(container, SWT.NULL);
	kindLabel.setText("Kind");

	availableKinds = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
	GridData gd6 = new GridData(GridData.FILL_HORIZONTAL);
	gd6.horizontalSpan = 4;
	availableKinds.setLayoutData(gd6);
	availableKinds.addSelectionListener(new SelectionListener() {
	    public void widgetSelected(SelectionEvent e) {
		dialogChanged();
	    }

	    public void widgetDefaultSelected(SelectionEvent e) {
	    }
	});

	// //////////////////////////////////////////////////////////////////////////////////////////////
	// Components (if Active Processing)

	Label compLabel = new Label(container, SWT.NULL);
	compLabel.setText("Components");

	Canvas cv2 = new Canvas(container, SWT.BORDER);
	GridData gd10 = new GridData(GridData.FILL_HORIZONTAL);
	gd10.horizontalSpan = 4;
	gd10.heightHint = 100;
	cv2.setLayoutData(gd10);
	cv2.setLayout(new FillLayout(SWT.VERTICAL));

	availableComponents = new List(cv2, SWT.MULTI | SWT.READ_ONLY | SWT.V_SCROLL);
	availableComponents.addSelectionListener(new SelectionListener() {
	    public void widgetSelected(SelectionEvent e) {
		dialogChanged();
	    }

	    public void widgetDefaultSelected(SelectionEvent e) {
	    }
	});

	// //////////////////////////////////////////////////////////////////////////////////////////////
	// Description (if Active External)

	Label descLabel = new Label(container, SWT.NULL);
	descLabel.setText("Description");

	description = new Text(container, SWT.BORDER);
	GridData gd8 = new GridData(GridData.FILL_HORIZONTAL);
	gd8.horizontalSpan = 4;
	description.setLayoutData(gd8);
	description.addModifyListener(new ModifyListener() {
	    public void modifyText(ModifyEvent e) {
		dialogChanged();
	    }
	});

	initialize();
	if (availableResources.getItemCount() == 0) {
	    deleteButton.setEnabled(false);
	    updateResourceButton.setEnabled(false);
	    newButton.setEnabled(true);
	    addButton.setEnabled(false);
	    resTypeExternal.setEnabled(false);
	    resTypePassive.setEnabled(false);
	    resTypeProcessing.setEnabled(false);
	}

	// setTitle("Resource Type");
	// dialogChanged();
	setControl(container);

    }

    /**
         * 
         */
    private void dialogChanged() {
	if (resTypePassive.getSelection()) {
	    if (availableComponents.getSelectionCount() > 1) {  // Passive resources can bound to one (1) component at most
		setErrorMessage("At most one (1) component may be bound to a passive resource");
		containsErrors = true;
	    } else {
		setErrorMessage(null);
		containsErrors = false;
	    }
	} else if (resTypeProcessing.getSelection()) { // opTime must be a valid double
	    if (isValid(opTimeValue.getText()) != null) {
		setErrorMessage("opTime value must be real");
		containsErrors = true;
	    } else {
		setErrorMessage(null);
		containsErrors = false;
	    }
	} else if (resTypeExternal.getSelection()) {
	    if (isValid(opTimeValue.getText()) != null) {
		setErrorMessage("opTime value must be real");
		containsErrors = true;
	    } else {
		setErrorMessage(null);
		containsErrors = false;
	    }
	} else {
	    System.out.println("Unexpected circumstance.");
	}

	// a resource is being created
	if (resTypePassive.isEnabled() && resTypeProcessing.isEnabled() && resTypeExternal.isEnabled()) {
	    addButton.setEnabled(!containsErrors);
	    // a resource is being modified
	} else {
	    if (availableResources.getItemCount() != 0) {
		if (hasChanged(resources[availableResources.getSelectionIndex()]) && !containsErrors) {
		    deleteButton.setEnabled(false);
		    updateResourceButton.setEnabled(true);
		} else {
		    deleteButton.setEnabled(true);
		    updateResourceButton.setEnabled(false);
		}
	    }
	}
    }

    /**
         * Tests if the current workbench selection is a suitable container to use.
         */
    private void initialize() {
	if (urn != null) {
	    resources = new GeneralResource[urn.getUrndef().getUrnspec().getUcmspec().getResources().size()];
	}

	// yeurk...
	availableKinds.removeAll();
	availableKinds.add((DeviceKind.DISK_LITERAL).toString());
	availableKinds.add((DeviceKind.DSP_LITERAL).toString());
	availableKinds.add((DeviceKind.PROCESSOR_LITERAL).toString());
	availableKinds.add((DeviceKind.OTHER_LITERAL).toString());

	availableComponents.removeAll();
	int i = 0;
	componentsInSpec = new Component[urn.getUrndef().getComponents().size()];
	for (Iterator comps = urn.getUrndef().getComponents().iterator(); comps.hasNext();) {
	    componentsInSpec[i] = (Component) comps.next();
	    availableComponents.add(componentsInSpec[i].getName());
	    i++;
	}
	i = 0;
	availableResources.removeAll();
	for (Iterator res = urn.getUrndef().getUrnspec().getUcmspec().getResources().iterator(); res.hasNext();) {
	    GeneralResource element = (GeneralResource) res.next();
	    resources[i] = element;
	    i++;
	    String id = "" + element.getName() + " (" + element.getId() + ")";
	    String id2;

	    if (element instanceof PassiveResource) {
		if (((PassiveResource) element).getComponent() != null) {
		    id2 = " (Passive Resource) " + ((PassiveResource) element).getComponent().getName();
		} else {
		    id2 = " (Passive Resource) ";
		}

		availableResources.add(id);
	    } else if (element instanceof ProcessingResource) {
		if ((((ProcessingResource) element).getComponents()).size() != 0) {
		    id2 = " (Processing Resource) " + (((ProcessingResource) element).getComponents()).get(0);
		} else {
		    id2 = " (Processing Resource) ";
		}
		availableResources.add(id);
	    } else if (element instanceof ExternalOperation) {
		id2 = " (External Operation) " + ((ExternalOperation) element).getDescription();
		availableResources.add(id);
	    }
	}
	if (availableResources.getItemCount() != 0) {
	    availableResources.select(0);
	    updateForSelection();
	    // deselect ALL in availableComponents
	}

    }

    /**
         * update all displayed fields based on the currently selected resource
         * 
         */
    private void updateForSelection() {

	GeneralResource element = resources[availableResources.getSelectionIndex()];

	// (normally?) an existing resource has been selected
	deleteButton.setEnabled(true);
	updateResourceButton.setEnabled(false);
	newButton.setEnabled(true);
	addButton.setEnabled(false);

	// Passive Resource:
	if (element instanceof PassiveResource) {
	    resName.setEnabled(true);
	    resName.setText(((PassiveResource) element).getName());

	    resTypePassive.setSelection(true);
	    resTypeProcessing.setSelection(false);
	    resTypeExternal.setSelection(false);

	    opTimeValue.setText("0.0"); // just for aesthetics
	    opTimeValue.setEnabled(false);

	    availableKinds.setEnabled(false);

	    availableComponents.deselectAll();
	    availableComponents.setEnabled(true);
	    availableComponents.showSelection();
	    Component component = (Component) ((PassiveResource) element).getComponent();
	    for (int j = 0; j < componentsInSpec.length; j++) {
		if (component == componentsInSpec[j]) {
		    availableComponents.select(j);
		    availableComponents.showSelection();
		}
	    }
	    availableKinds.setEnabled(false);
	    description.setEnabled(false);
	} else if (element instanceof ProcessingResource) {
	    resName.setEnabled(true);
	    resName.setText(((ProcessingResource) element).getName());

	    resTypePassive.setSelection(false);
	    resTypeProcessing.setSelection(true);
	    resTypeExternal.setSelection(false);
	    opTimeValue.setEnabled(true);
	    double optval = ((ProcessingResource) element).getOpTime();
	    String optvalString = "" + optval + "";
	    opTimeValue.setText(optvalString);
	    availableKinds.setEnabled(true);
	    for (int i = 0; i < availableKinds.getItemCount(); i++) {
		String availableKindString = availableKinds.getItem(i).toString();
		String processingResKindString = ((ProcessingResource) element).getKind().toString();
		if (availableKindString.equals(processingResKindString)) {
		    availableKinds.select(i);
		}
	    }
	    availableComponents.deselectAll();
	    availableComponents.setEnabled(true);
	    availableComponents.showSelection();
	    for (Iterator boundComponents = ((ProcessingResource) element).getComponents().iterator(); boundComponents.hasNext();) {
		Component boundComponent = (Component) boundComponents.next();
		for (int j = 0; j < componentsInSpec.length; j++) {
		    if (boundComponent == componentsInSpec[j]) {
			availableComponents.select(j);
			availableComponents.showSelection();
		    }
		}
	    }
	    description.setEnabled(false);
	    // for Kind
	} else if (element instanceof ExternalOperation) {
	    resName.setEnabled(true);
	    resName.setText(((ExternalOperation) element).getName());

	    resTypePassive.setSelection(false);
	    resTypeProcessing.setSelection(false);
	    resTypeExternal.setSelection(true);
	    opTimeValue.setEnabled(true);
	    double optval = ((ExternalOperation) element).getOpTime();
	    String optvalString = "" + optval + "";
	    opTimeValue.setText(optvalString);
	    availableKinds.setEnabled(false);
	    availableComponents.deselectAll();
	    availableComponents.setEnabled(false);
	    description.setEnabled(true);
	    description.setText(((ExternalOperation) element).getDescription());
	}
	resTypePassive.setEnabled(false);
	resTypeProcessing.setEnabled(false);
	resTypeExternal.setEnabled(false);
    }

    private boolean hasChanged(GeneralResource element) {
	boolean changed = false;
	// IF NO ERROR PENDING!!!
	// Passive Resource
	if (element instanceof PassiveResource) {
	    if (((((PassiveResource) element).getComponent() != null) && (availableComponents.getSelectionCount() == 0))
		    || ((((PassiveResource) element).getComponent() == null) && (availableComponents.getSelectionCount() != 0))) {
		changed = true;
	    } else if ((((PassiveResource) element).getComponent() != null)
		    && (componentsInSpec[availableComponents.getSelectionIndex()] != ((PassiveResource) element).getComponent())) {
		changed = true;
	    }
	    // Processing Resource
	} else if (element instanceof ProcessingResource) {
	    double optval = ((ProcessingResource) element).getOpTime();
	    String optvalString = "" + optval;
	    String optvalUser = opTimeValue.getText().toString();
	    if (!optvalString.equals(optvalUser)) {
		changed = true;
	    }
	    if (availableKinds.getSelectionIndex() != -1) {
		String currentlySelectedKind = availableKinds.getItem(availableKinds.getSelectionIndex()).toString();
		String resourceDefinedKind = ((ProcessingResource) element).getKind().toString();
		if (currentlySelectedKind != resourceDefinedKind) {
		    changed = true;
		}
	    } else {
		System.out.println("Old Specfication?");
	    }
	    // a different cardinality is sufficient to conclude to a change
	    if (((ProcessingResource) element).getComponents().size() != availableComponents.getSelectionCount()) {
		changed = true;
	    } else {
		// checking for no longer bound components
		// for each component bound in the original resource definition
		for (Iterator boundComponents = ((ProcessingResource) element).getComponents().iterator(); boundComponents.hasNext();) {
		    Component boundComponent = (Component) boundComponents.next();
		    boolean missing = false;
		    // for each available component in the system
		    for (int j = 0; j < componentsInSpec.length; j++) {
			// if the component from the original resource definition...
			if (boundComponent == componentsInSpec[j]) {
			    // ... is no longer selected
			    if (!availableComponents.isSelected(j)) {
				missing = true;
			    }
			}
		    }
		    changed = changed || missing;
		}
	    } // we could check for newly bound components  but cardinality and missing should suffice
	// External Operation
	} else if (element instanceof ExternalOperation) {
	    double optval = ((ExternalOperation) element).getOpTime();
	    String optvalString = "" + optval;
	    String optvalUser = opTimeValue.getText().toString();
	    if (!optvalString.equals(optvalUser)) {
		changed = true;
	    }
	    String originalDescription = (String) ((ExternalOperation) element).getDescription();
	    String newDescription = (String) description.getText();
	    if (originalDescription != null) {
		if (originalDescription.compareTo(newDescription) != 0) {
		    changed = true;
		}
	    }
	}
	if ( element.getName().compareTo(resName.getText()) != 0) {
	    changed = true;
	}
	return changed;
    }

    /**
         * Dealing with "double" in text widget
         */
    public String isValid(Object value) {
	double doubleValue = -1;
	try {
	    doubleValue = Double.parseDouble(value.toString());
	    return null;
	} catch (NumberFormatException exc) {
	    return "Messages.getString(\"EObjectPropertySource.notValidNumber\"); //$NON-NLS-1$"; // Ouch. _js_
	}
    }

    /**
         * Updates the status of the window
         * 
         * @param message
         *                the error message or null if no error message.
         */
    private void updateStatus(String message) {
	setErrorMessage(message);
	setPageComplete(message == null);
    }
/*
    public String getType() {
	return sType;
    }
*/
    public void updateResource() {
	// Cut&paste:  delResource() + addResource()
//	 assuming all has been set for processing at this point _js_
	CommandStack cs = ((UCMNavMultiPageEditor) workbenchPage.getActiveEditor()).getDelegatingCommandStack();
	CompoundCommand command = new CompoundCommand();
	GeneralResource genRes = resources[availableResources.getSelectionIndex()];
	DeleteResourceCommand deleteCmd = new DeleteResourceCommand(workbenchPage, urn, genRes);
	command.add(deleteCmd);

	Component[] components;
	String name = resName.getText();
	// double testDouble;
	if (resTypePassive.getSelection()) {
	    int selectedComp = availableComponents.getSelectionCount();
	    if (selectedComp == 1) {
		Component component = componentsInSpec[availableComponents.getSelectionIndex()];
		CreatePassiveResourceCommand createCmd = new CreatePassiveResourceCommand(urn, name, component);
		command.add(createCmd);
	    } else {
		CreatePassiveResourceCommand createCmd = new CreatePassiveResourceCommand(urn, name, null);
		command.add(createCmd);
	    }
	} else if (resTypeProcessing.getSelection()) {
	    DeviceKind deviceKind = intToKind(availableKinds.getSelectionIndex());
	    double opTime = Double.parseDouble(opTimeValue.getText());
	    int[] indices = availableComponents.getSelectionIndices();
	    components = new Component[indices.length];
	    for (int i = 0; i < indices.length; i++) {
		int test4 = indices[i];
		components[i] = componentsInSpec[indices[i]];
	    }
	    CreateActiveProcessingCommand createCmd = new CreateActiveProcessingCommand(urn, name, components, opTime, deviceKind);
	    command.add(createCmd);
	} else if (resTypeExternal.getSelection()) {
	    double opTime = Double.parseDouble(opTimeValue.getText());
	    String descripString = description.getText();
	    CreateExternalOperationCommand createCmd = new CreateExternalOperationCommand(urn, name, opTime, descripString);
	    command.add(createCmd);
	} else {
	    System.out.println("No type has been chosen!");
	}
	// use a command to be undoable.
	if (command.canExecute())
	    cs.execute(command);
    }
    
    public void delResource() {
//	 assuming all has been set for processing at this point _js_
	CommandStack cs = ((UCMNavMultiPageEditor) workbenchPage.getActiveEditor()).getDelegatingCommandStack();
	CompoundCommand command = new CompoundCommand();
	GeneralResource genRes = resources[availableResources.getSelectionIndex()];
	DeleteResourceCommand deleteCmd = new DeleteResourceCommand(workbenchPage, urn, genRes);
	command.add(deleteCmd);

	// use a command to be undoable.
	if (command.canExecute())
	    cs.execute(command);
    }
    
    public void addResource() {
	// assuming all has been set for processing at this point _js_
	CommandStack cs = ((UCMNavMultiPageEditor) workbenchPage.getActiveEditor()).getDelegatingCommandStack();
	CompoundCommand command = new CompoundCommand();
	Component[] components;
	String name = resName.getText();
	// double testDouble;
	if (resTypePassive.getSelection()) {
	    int selectedComp = availableComponents.getSelectionCount();
	    if (selectedComp == 1) {
		Component component = componentsInSpec[availableComponents.getSelectionIndex()];
		CreatePassiveResourceCommand createCmd = new CreatePassiveResourceCommand(urn, name, component);
		command.add(createCmd);
	    } else {
		CreatePassiveResourceCommand createCmd = new CreatePassiveResourceCommand(urn, name, null);
		command.add(createCmd);
	    }
	} else if (resTypeProcessing.getSelection()) {
	    DeviceKind deviceKind = intToKind(availableKinds.getSelectionIndex());
	    double opTime = Double.parseDouble(opTimeValue.getText());
	    int[] indices = availableComponents.getSelectionIndices();
	    components = new Component[indices.length];
	    for (int i = 0; i < indices.length; i++) {
		int test4 = indices[i];
		components[i] = componentsInSpec[indices[i]];
	    }
	    CreateActiveProcessingCommand createCmd = new CreateActiveProcessingCommand(urn, name, components, opTime, deviceKind);
	    command.add(createCmd);
	} else if (resTypeExternal.getSelection()) {
	    double opTime = Double.parseDouble(opTimeValue.getText());
	    String descripString = description.getText();
	    CreateExternalOperationCommand createCmd = new CreateExternalOperationCommand(urn, name, opTime, descripString);
	    command.add(createCmd);
	} else {
	    System.out.println("No type has been chosen!");
	}

	// use a command to be undoable.
	if (command.canExecute())
	    cs.execute(command);

    }

    public DeviceKind intToKind(int index) {
	DeviceKind kind = null;
	if (availableKinds.getItem(index).toString() == (DeviceKind.DISK_LITERAL).toString()) {
	    kind = DeviceKind.get(DeviceKind.DISK);
	} else if (availableKinds.getItem(index).toString() == (DeviceKind.DSP_LITERAL).toString()) {
	    kind = DeviceKind.get(DeviceKind.DSP);
	} else if (availableKinds.getItem(index).toString() == (DeviceKind.OTHER_LITERAL).toString()) {
	    kind = DeviceKind.get(DeviceKind.OTHER);
	} else if (availableKinds.getItem(index).toString() == (DeviceKind.PROCESSOR_LITERAL).toString()) {
	    kind = DeviceKind.get(DeviceKind.PROCESSOR);
	}
	return kind;
    }
}
