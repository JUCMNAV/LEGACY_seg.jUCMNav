package seg.jUCMNav.views.wizards.performance;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
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
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
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
import urncore.ComponentKind;

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
    private Text multiplicity;
    private Text schedPolicy;
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
    private boolean creationMode = false;
    private boolean updating = false;

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
        this.setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/perspectiveIcon.gif")); //$NON-NLS-1$
        this.selection = selection;
        this.defaultSelected = defaultSelected;
        this.urn = urn;
        this.workbenchPage = workbenchPage;

        setTitle(Messages.getString("ManageResourcesPage.ManageResources")); //$NON-NLS-1$
        setDescription(Messages.getString("ManageResourcesPage.SelectResource")); //$NON-NLS-1$

    }

    /**
     * Creates the page.
     */
    public void createControl(Composite parent) {

        PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, "seg.jUCMNav.manage_resources"); //$NON-NLS-1$

        container = new Composite(parent, SWT.NULL);
        shell = container.getShell();
        GridLayout containerLayout = new GridLayout();
        containerLayout.numColumns = 5;
        container.setLayout(containerLayout);

        // //////////////////////////////////////////////////////////////////////////////////////////////
        // Selection (ID) of Resource

        Label resChoice = new Label(container, SWT.NULL);
        resChoice.setText(Messages.getString("ManageResourcesPage.Resource")); //$NON-NLS-1$
        availableResources = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
        GridData gd1 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        gd1.horizontalSpan = 4;
        availableResources.setLayoutData(gd1);
        availableResources.addSelectionListener(new SelectionAdapter() {
            public void widgetDefaultSelected(SelectionEvent e) {
            }

            public void widgetSelected(SelectionEvent e) {
                // there exists some resources already
                updating = true;
                if (availableResources.getSelectionIndex() >= 0) {
                    updateForSelection();
                    deleteButton.setEnabled(true);
                    updateResourceButton.setEnabled(false);
                    // dialogChanged();
                }
                updating = false;
            }
        });

        // //////////////////////////////////////////////////////////////////////////////////////////////
        // Name (for all resource types)

        Label resNameLabel = new Label(container, SWT.NULL);
        resNameLabel.setText(Messages.getString("ManageResourcesPage.Nom")); //$NON-NLS-1$

        resName = new Text(container, SWT.BORDER);
        GridData gd2 = new GridData(GridData.FILL_HORIZONTAL);
        gd2.horizontalSpan = 4;
        resName.setLayoutData(gd2);
        resName.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                dialogChanged();
            }
        });

        // //////////////////////////////////////////////////////////////////////////////////////////////
        // Buttons (actions)

        // when selected, fill up the rest of the form accordingly
        Label blank1 = new Label(container, SWT.NULL);
        blank1.setText(""); //$NON-NLS-1$

        deleteButton = new Button(container, SWT.PUSH);
        deleteButton.setText(Messages.getString("ManageResourcesPage.Delete")); //$NON-NLS-1$
        deleteButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                // should be active only when resource is selected
                updating = true;
                delResource();
                initialize();
                deleteButton.setEnabled(false);
                resTypePassive.setEnabled(false);
                resTypeProcessing.setEnabled(false);
                resTypeExternal.setEnabled(false);
                availableResources.setEnabled(true);
                if (availableResources.getItemCount() != 0) {
                    updateForSelection();
                }
                updating = false;
            }
        });
        // when hit, delete this resource

        updateResourceButton = new Button(container, SWT.PUSH);
        updateResourceButton.setText(Messages.getString("ManageResourcesPage.Update")); //$NON-NLS-1$
        updateResourceButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                // should be active only when resource is selected
                updating = true;
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
                updating = false;
            }
        });

        newButton = new Button(container, SWT.PUSH);
        newButton.setText(Messages.getString("ManageResourcesPage.New")); //$NON-NLS-1$
        // create new editable entry; value mandatory; selection impossible; cancel enabled
        newButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                // should be active only when resource is selected
                updating = true;
                creationMode = true;
                resTypePassive.setEnabled(true);
                resTypePassive.setSelection(true);
                resTypeProcessing.setEnabled(true);
                resTypeProcessing.setSelection(false);
                resTypeExternal.setEnabled(true);
                resTypeExternal.setSelection(false);

                availableResources.deselectAll();
                availableResources.setEnabled(false);

                resName.setText(Messages.getString("ManageResourcesPage.NewResName")); //$NON-NLS-1$
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

                description.setText(Messages.getString("ManageResourcesPage.NewResDescr")); //$NON-NLS-1$
                description.setEnabled(false);
                updating = false;
            }
        });

        addButton = new Button(container, SWT.PUSH);
        addButton.setText(Messages.getString("ManageResourcesPage.Add")); //$NON-NLS-1$
        addButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                // should be active only when resource is selected
                updating = true;
                // add
                addResource();
                creationMode = false;
                resTypePassive.setEnabled(false);
                resTypeProcessing.setEnabled(false);
                resTypeExternal.setEnabled(false);

                availableResources.select(1);
                availableResources.setEnabled(true);
                initialize();
                updateForSelection();
                updating = false;
            }
        });

        // //////////////////////////////////////////////////////////////////////////////////////////////
        // Type of Resource
        // select the first entry then redraw (what if first entry is empty?)

        // type of resource
        Label type = new Label(container, SWT.NULL);
        type.setText(Messages.getString("ManageResourcesPage.Type")); //$NON-NLS-1$

        cv = new Canvas(container, SWT.NULL);
        GridData gd3 = new GridData();
        gd3.horizontalSpan = 4;
        cv.setLayoutData(gd3);
        cv.setLayout(new FillLayout(SWT.VERTICAL));

        resTypePassive = new Button(cv, SWT.RADIO);
        resTypePassive.setText(Messages.getString("ManageResourcesPage.Passive")); //$NON-NLS-1$
        resTypePassive.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                if (resTypePassive.getSelection()) {
                    opTimeValue.setText(""); //$NON-NLS-1$
                    opTimeValue.setEnabled(false);
                    availableKinds.setEnabled(false);
                    availableComponents.setEnabled(true); // SINGLE
                    description.setEnabled(false);
                }
            }
        });

        resTypeProcessing = new Button(cv, SWT.RADIO);
        resTypeProcessing.setText(Messages.getString("ManageResourcesPage.ActiveProc")); //$NON-NLS-1$
        resTypeProcessing.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                if (resTypeProcessing.getSelection()) {
                    if (opTimeValue.getText().compareTo("") == 0) { //$NON-NLS-1$
                        opTimeValue.setText("0.0"); //$NON-NLS-1$
                    }
                    opTimeValue.setEnabled(true);
                    availableKinds.setEnabled(true);
                    availableComponents.setEnabled(true); // MULTI
                    description.setEnabled(false);
                }
            }
        });

        resTypeExternal = new Button(cv, SWT.RADIO);
        resTypeExternal.setText(Messages.getString("ManageResourcesPage.ActiveExtOp")); //$NON-NLS-1$
        resTypeExternal.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                if (resTypeExternal.getSelection()) {
                    if (opTimeValue.getText().compareTo("") == 0) { //$NON-NLS-1$
                        opTimeValue.setText("0.0"); //$NON-NLS-1$
                    }
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
        opTimeLabel.setText(Messages.getString("ManageResourcesPage.opTime")); //$NON-NLS-1$

        opTimeValue = new Text(container, SWT.BORDER);
        // text with double

        opTimeValue.setText("0.0"); //$NON-NLS-1$
        GridData gd4 = new GridData();
        gd4.horizontalSpan = 4;
        gd4.widthHint = 50;
        opTimeValue.setLayoutData(gd4);
        opTimeValue.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                dialogChanged();
            }
        });

        // //////////////////////////////////////////////////////////////////////////////////////////////
        // Kind (if Active Processing)

        Label kindLabel = new Label(container, SWT.NULL);
        kindLabel.setText(Messages.getString("ManageResourcesPage.Kind")); //$NON-NLS-1$

        availableKinds = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
        GridData gd5 = new GridData(GridData.FILL_HORIZONTAL);
        gd5.horizontalSpan = 4;
        availableKinds.setLayoutData(gd5);
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
        compLabel.setText(Messages.getString("ManageResourcesPage.Components")); //$NON-NLS-1$

        Canvas cv2 = new Canvas(container, SWT.BORDER);
        GridData gd6 = new GridData(GridData.FILL_HORIZONTAL);
        gd6.horizontalSpan = 4;
        gd6.heightHint = 100;
        cv2.setLayoutData(gd6);
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
        descLabel.setText(Messages.getString("ManageResourcesPage.Description")); //$NON-NLS-1$

        description = new Text(container, SWT.BORDER);
        GridData gd7 = new GridData(GridData.FILL_HORIZONTAL);
        gd7.horizontalSpan = 4;
        description.setLayoutData(gd7);
        description.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                dialogChanged();
            }
        });

        // //////////////////////////////////////////////////////////////////////////////////////////////
        // Multplicity

        Label multLabel = new Label(container, SWT.NULL);
        multLabel.setText(Messages.getString("ManageResourcesPage.Multiplicity")); //$NON-NLS-1$

        multiplicity = new Text(container, SWT.BORDER);
        multiplicity.setText(""); //$NON-NLS-1$
        GridData gd8 = new GridData();
        gd8.horizontalSpan = 4;
        gd8.widthHint = 50;
        multiplicity.setLayoutData(gd8);
        multiplicity.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                dialogChanged();
            }
        });

        // //////////////////////////////////////////////////////////////////////////////////////////////
        // Description (if Active External)

        Label schedPolLabel = new Label(container, SWT.NULL);
        schedPolLabel.setText(Messages.getString("ManageResourcesPage.SchedPol")); //$NON-NLS-1$

        schedPolicy = new Text(container, SWT.BORDER);
        schedPolicy.setText(""); //$NON-NLS-1$
        GridData gd9 = new GridData();
        gd9.horizontalSpan = 4;
        gd9.widthHint = 50;
        schedPolicy.setLayoutData(gd9);
        schedPolicy.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                dialogChanged();
            }
        });

        updating = true;
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
        dialogChanged();
        updating = false;
        setControl(container);

    }

    /**
	 * 
	 */
    private void dialogChanged() {
        if (!updating) {
            if (resTypePassive.getSelection()) {
                if (availableComponents.getSelectionCount() > 1) { // Passive resources can bound to one (1) component at most
                    setErrorMessage(Messages.getString("ManageResourcesPage.AtMostOneComp")); //$NON-NLS-1$
                    containsErrors = true;
                } else {
                    setErrorMessage(null);
                    containsErrors = false;
                }
            } else if (resTypeProcessing.getSelection()) { // opTime must be a valid double
                if (isValidDouble(opTimeValue.getText()) != null) {
                    setErrorMessage(Messages.getString("ManageResourcesPage.opTimeMustBeReal")); //$NON-NLS-1$
                    containsErrors = true;
                } else {
                    setErrorMessage(null);
                    containsErrors = false;
                }
            } else if (resTypeExternal.getSelection()) {
                if (isValidDouble(opTimeValue.getText()) != null) {
                    setErrorMessage(Messages.getString("ManageResourcesPage.opTimeMustBeReal")); //$NON-NLS-1$
                    containsErrors = true;
                } else {
                    setErrorMessage(null);
                    containsErrors = false;
                }
            }

            if ((multiplicity.getText().compareTo("") != 0) && (isValidInteger(multiplicity.getText()) != null)) { // multiplicity must be an integer //$NON-NLS-1$
                setErrorMessage(Messages.getString("ManageResourcesPage.MultiMustBeInt")); //$NON-NLS-1$
                containsErrors = true;
            } else {
                setErrorMessage(null);
                containsErrors = false;
            }

            // a resource is being created
            if (creationMode) {
                addButton.setEnabled(!containsErrors);
                // a resource is being modified
            } else if (availableResources.getItemCount() != 0) {
                if (availableResources.getSelectionIndex() != -1) {
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
            Component nextComponent = (Component) comps.next();
            ComponentKind kind = nextComponent.getKind();
            // only components of kind Agent, Object, Process and Team are convertable to CSM resources
            if ((kind == ComponentKind.AGENT_LITERAL) || (kind == ComponentKind.OBJECT_LITERAL) || (kind == ComponentKind.PROCESS_LITERAL)
                    || (kind == ComponentKind.TEAM_LITERAL)) {
                componentsInSpec[i] = nextComponent;
                availableComponents.add(componentsInSpec[i].getName());
                i++;
            }
        }
        i = 0;
        availableResources.removeAll();
        for (Iterator res = urn.getUrndef().getUrnspec().getUcmspec().getResources().iterator(); res.hasNext();) {
            GeneralResource element = (GeneralResource) res.next();
            resources[i] = element;
            i++;
            String id = "" + element.getName() + " (" + element.getId() + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            String id2;

            if (element instanceof PassiveResource) {
                if (((PassiveResource) element).getComponent() != null) {
                    id2 = Messages.getString("ManageResourcesPage.PassResource") + ((PassiveResource) element).getComponent().getName(); //$NON-NLS-1$
                } else {
                    id2 = Messages.getString("ManageResourcesPage.PassResource"); //$NON-NLS-1$
                }

                availableResources.add(id);
            } else if (element instanceof ProcessingResource) {
                if ((((ProcessingResource) element).getComponents()).size() != 0) {
                    id2 = Messages.getString("ManageResourcesPage.ProcessingResource") + (((ProcessingResource) element).getComponents()).get(0); //$NON-NLS-1$
                } else {
                    id2 = Messages.getString("ManageResourcesPage.ProcessingResource"); //$NON-NLS-1$
                }
                availableResources.add(id);
            } else if (element instanceof ExternalOperation) {
                id2 = Messages.getString("ManageResourcesPage.ExternalOperation") + ((ExternalOperation) element).getDescription(); //$NON-NLS-1$
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

            opTimeValue.setText(""); // just for aesthetics //$NON-NLS-1$
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
            String optvalString = ((ProcessingResource) element).getOpTime();
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
            String optvalString = ((ExternalOperation) element).getOpTime();
            opTimeValue.setText(optvalString);
            availableKinds.setEnabled(false);
            availableComponents.deselectAll();
            availableComponents.setEnabled(false);
            description.setEnabled(true);
            description.setText(((ExternalOperation) element).getDescription());
        }
        if (element.getMultiplicity() != null) {
            multiplicity.setText(element.getMultiplicity());
        } else {
            multiplicity.setText(""); //$NON-NLS-1$
        }
        if (element.getSchedPolicy() != null) {
            schedPolicy.setText(element.getSchedPolicy());
        } else {
            schedPolicy.setText(""); //$NON-NLS-1$
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
            String optvalString = ((ProcessingResource) element).getOpTime();
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
                System.out.println("Old Specfication?"); //$NON-NLS-1$
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
            } // we could check for newly bound components but cardinality and missing should suffice
            // External Operation
        } else if (element instanceof ExternalOperation) {
            String optvalString = ((ExternalOperation) element).getOpTime();
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
        if (element.getName().compareTo(resName.getText()) != 0) {
            changed = true;
        }
        if (element.getMultiplicity().compareTo(multiplicity.getText()) != 0) {
            changed = true;
        }
        if (element.getSchedPolicy().compareTo(schedPolicy.getText()) != 0) {
            changed = true;
        }
        return changed;
    }

    /**
     * Dealing with "double" in text widget
     */
    public String isValidDouble(Object value) {
        double doubleValue = -1;
        try {
            doubleValue = Double.parseDouble(value.toString());
            return null;
        } catch (NumberFormatException exc) {
            return Messages.getString("EObjectPropertySource.notValidNumber"); //$NON-NLS-1$
        }
    }

    /**
     * Dealing with "integer" in text widget
     */
    public String isValidInteger(Object value) {
        int intValue = 0;
        try {
            intValue = Integer.parseInt(value.toString());
            return null;
        } catch (NumberFormatException e) {
            return Messages.getString("EObjectPropertySource.notValidNumber"); //$NON-NLS-1$
        }
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

    /*
     * public String getType() { return sType; }
     */
    public void updateResource() {
        // Cut&paste: delResource() + addResource()
        // assuming all has been set for processing at this point _js_
        CommandStack cs = ((UCMNavMultiPageEditor) workbenchPage.getActiveEditor()).getDelegatingCommandStack();
        CompoundCommand command = new CompoundCommand();
        GeneralResource genRes = resources[availableResources.getSelectionIndex()];
        DeleteResourceCommand deleteCmd = new DeleteResourceCommand(workbenchPage, urn, genRes);
        command.add(deleteCmd);

        Component[] components;
        String name = resName.getText();
        String multiplicityStr = multiplicity.getText();
        String schedPolicyStr = schedPolicy.getText();
        // double testDouble;
        if (resTypePassive.getSelection()) {
            int selectedComp = availableComponents.getSelectionCount();
            if (selectedComp == 1) {
                Component component = componentsInSpec[availableComponents.getSelectionIndex()];
                CreatePassiveResourceCommand createCmd = new CreatePassiveResourceCommand(urn, name, component, multiplicityStr, schedPolicyStr);
                command.add(createCmd);
            } else {
                CreatePassiveResourceCommand createCmd = new CreatePassiveResourceCommand(urn, name, null, multiplicityStr, schedPolicyStr);
                command.add(createCmd);
            }
        } else if (resTypeProcessing.getSelection()) {
            DeviceKind deviceKind = intToKind(availableKinds.getSelectionIndex());
            String opTime = opTimeValue.getText();
            int[] indices = availableComponents.getSelectionIndices();
            components = new Component[indices.length];
            for (int i = 0; i < indices.length; i++) {
                int test4 = indices[i];
                components[i] = componentsInSpec[indices[i]];
            }
            CreateActiveProcessingCommand createCmd = new CreateActiveProcessingCommand(urn, name, components, opTime, deviceKind, multiplicityStr,
                    schedPolicyStr);
            command.add(createCmd);
        } else if (resTypeExternal.getSelection()) {
            String opTime = opTimeValue.getText();
            String descripString = description.getText();
            CreateExternalOperationCommand createCmd = new CreateExternalOperationCommand(urn, name, opTime, descripString, multiplicityStr, schedPolicyStr);
            command.add(createCmd);
        } else {
            System.out.println("No type has been chosen!"); //$NON-NLS-1$
        }

        // use a command to be undoable.
        if (command.canExecute())
            cs.execute(command);

    }

    public void delResource() {
        if (availableResources.getSelectionIndex() != -1) {
            CommandStack cs = ((UCMNavMultiPageEditor) workbenchPage.getActiveEditor()).getDelegatingCommandStack();
            CompoundCommand command = new CompoundCommand();
            GeneralResource genRes = resources[availableResources.getSelectionIndex()];
            DeleteResourceCommand deleteCmd = new DeleteResourceCommand(workbenchPage, urn, genRes);
            command.add(deleteCmd);

            // use a command to be undoable.
            if (command.canExecute())
                cs.execute(command);
        }
    }

    public void addResource() {
        // assuming all has been set for processing at this point _js_
        CommandStack cs = ((UCMNavMultiPageEditor) workbenchPage.getActiveEditor()).getDelegatingCommandStack();
        CompoundCommand command = new CompoundCommand();
        Component[] components;
        String name = resName.getText();
        String multiplicityStr = multiplicity.getText();
        String schedPolicyStr = schedPolicy.getText();
        // double testDouble;
        if (resTypePassive.getSelection()) {
            int selectedComp = availableComponents.getSelectionCount();
            if (selectedComp == 1) {
                Component component = componentsInSpec[availableComponents.getSelectionIndex()];
                CreatePassiveResourceCommand createCmd = new CreatePassiveResourceCommand(urn, name, component, multiplicityStr, schedPolicyStr);
                command.add(createCmd);
            } else {
                CreatePassiveResourceCommand createCmd = new CreatePassiveResourceCommand(urn, name, null, multiplicityStr, schedPolicyStr);
                command.add(createCmd);
            }
        } else if (resTypeProcessing.getSelection()) {
            DeviceKind deviceKind = intToKind(availableKinds.getSelectionIndex());
            String opTime = opTimeValue.getText();
            int[] indices = availableComponents.getSelectionIndices();
            components = new Component[indices.length];
            for (int i = 0; i < indices.length; i++) {
                int test4 = indices[i];
                components[i] = componentsInSpec[indices[i]];
            }
            CreateActiveProcessingCommand createCmd = new CreateActiveProcessingCommand(urn, name, components, opTime, deviceKind, multiplicityStr,
                    schedPolicyStr);
            command.add(createCmd);
        } else if (resTypeExternal.getSelection()) {
            String opTime = opTimeValue.getText();
            String descripString = description.getText();
            CreateExternalOperationCommand createCmd = new CreateExternalOperationCommand(urn, name, opTime, descripString, multiplicityStr, schedPolicyStr);
            command.add(createCmd);
        } else {
            System.out.println("No type has been chosen!"); //$NON-NLS-1$
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
