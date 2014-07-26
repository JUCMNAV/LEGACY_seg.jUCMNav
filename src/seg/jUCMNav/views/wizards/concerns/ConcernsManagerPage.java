package seg.jUCMNav.views.wizards.concerns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import urncore.Concern;
import urncore.IURNDiagram;
import urncore.URNmodelElement;

/**
 * The ConcernsManagerPage actually contains the UI code for managing concerns.
 * 
 * @author gunterm
 */
public class ConcernsManagerPage extends WizardPage {
    // this is the approach for the ConcernsManager and ConcernsManagerPage:
    // a) when initializing (see constructor), the existing concerns/diagrams are being wrapped into
    // UpdatedConcerns/UpdatedDiagrams (see these two classes). the latter are then sorted and used to
    // create widgets and set their content. in other words, the UpdatedConcerns/UpdatedDiagrams are the
    // MODEL for the ConcernsManagerPage because the actual URN model must only be changed at the end of
    // the wizard.
    // b) updates therefore do not change existing concerns/diagrams but are stored in UpdatedConcerns/
    // UpdatedDiagrams in addition to the existing concerns/diagrams. updates use methods of UpdatedConcerns/
    // UpdatedDiagrams to reflect the changes in UpdatedConcern/UpdatedDiagram classes.
    // c) after an update occurs or different items have been selected the widgets are refreshed (see
    // refreshControl()). again UpdatedConcerns/UpdatedDiagrams are used to populate the widgets and set
    // their content (i.e. if a concern/diagram has changed, then the new values are returned by
    // UpdatedConcerns/UpdatedDiagrams; if the concern/diagram has not changed, then UpdatedConcerns/
    // UpdatedDiagrams returns the original values).
    // d) when the Finish button is clicked, the UpdatedConcerns/UpdatedDiagrams are used to create commands
    // that actually change the URN model (see ConcernsManager.performFinish()). if the wizard is
    // cancelled or when all commands have been generated, all UpdatedConcerns/UpdatedDiagrams must be
    // deleted (see ConcernsManager.performFinish() and ConcernsManager.performCancel()).
    // this approach is really only possible because there is no extensive business logic involved
    // in managing concerns that impacts the wizard (i.e. the methods of UpdatedConcerns/UpdatedDiagrams that
    // change the values are simple). otherwise, the URN model would have to be updated directly and the UI
    // would have to listen to the changes from the URN model. this would require a command for each small
    // change and would result in many undoes/redoes. since the wizard pattern with a single compound command
    // that can be undone with one undo step is more desirable, the direct change and listener approach was
    // not used.
    // note that the number of concerns may change: new UpdatedConcerns can be added or existing ones can be
    // removed (addition requires the MODEL to be resorted).
    // note that the number of diagrams does not change.

    // these keep track of the selections in the UI as well as all currently shown diagrams of a concern (allConcernDiagrams)
    private UpdatedConcern selectedConcern;
    private UpdatedDiagram selectedDiagram;
    private int[] selectedConcernDiagramIndexes;
    private java.util.List allConcernDiagrams; // contains IURNDiagrams

    // these are the widgets for concerns
    private Group grpConcern;
    private Combo cbConcerns;
    private Label lblConcernNamePrompt;
    private Text txtConcernName;
    private Label lblConcernDescriptionPrompt;
    private Text txtConcernDescription;
    private Label lblConcernDiagramsPrompt;
    private List lstConcernDiagrams;
    private Button btnAddConcern;
    private Button btnDeleteConcern;
    private Button btnRemoveDiagrams;

    // these are the widgets for diagrams
    private Group grpDiagram;
    private Combo cbDiagrams;
    private Label lblAssignedConcernPrompt;
    private Label lblAssignedConcern;
    private Button btnAssignConcern;
    private Button btnRemoveConcern;

    // listeners - defined here so that they can be easily added/removed/added again...
    private SelectionListener sListenerCbConcerns = new SelectionListener() {
        public void widgetSelected(SelectionEvent e) {
            // single click.
            try {
                UpdatedConcern uConcern = (UpdatedConcern) UpdatedConcern.getUpdatedConcerns().get(cbConcerns.getSelectionIndex());
                if (uConcern != selectedConcern) {
                    selectedConcern = uConcern;
                    selectedConcernDiagramIndexes = null;
                    refreshControl();
                }
            } catch (IndexOutOfBoundsException ex) {
                // ignore
            }
        }

        public void widgetDefaultSelected(SelectionEvent e) {
            // double click.
        }
    };
    private SelectionAdapter sListenerBtnAddConcern = new SelectionAdapter() {
        public void widgetSelected(SelectionEvent e) {
            selectedConcern = new UpdatedConcern();
            selectedConcernDiagramIndexes = null;
            sortListByDisplayText(UpdatedConcern.getUpdatedConcerns());
            refreshControl();
        }
    };
    private SelectionAdapter sListenerBtnDeleteConcern = new SelectionAdapter() {
        public void widgetSelected(SelectionEvent e) {
            if (selectedConcern != null && selectedConcern.delete()) {
                selectedConcern = null;
                selectedConcernDiagramIndexes = null;
                refreshControl();
            }
        }
    };
    private ModifyListener mListenerTxt = new ModifyListener() {
        public void modifyText(ModifyEvent e) {
            selectedConcern.setName(txtConcernName.getText());
            selectedConcern.setDescription(txtConcernDescription.getText());
            // no need to refresh since this change does not impact the rest of the page
            // (the name of the concern should be updated in cbConcerns but considering performance, this is not
            // necessary for each single changed character because the FocusListener fListenerTxtConcernName
            // will update everything if the name was changed)
        }
    };
    private String name;
    private FocusListener fListenerTxtConcernName = new FocusListener() {
        public void focusGained(FocusEvent e) {
            name = txtConcernName.getText();
        }

        public void focusLost(FocusEvent e) {
            if (!name.equals(txtConcernName.getText())) {
                // a change to the name may change the order of the concerns
                sortListByDisplayText(UpdatedConcern.getUpdatedConcerns());
                refreshControl();
            }
        }
    };
    private SelectionListener sListenerLstConcernDiagrams = new SelectionListener() {
        public void widgetSelected(SelectionEvent e) {
            // single click.
            selectedConcernDiagramIndexes = lstConcernDiagrams.getSelectionIndices();
            refreshControl();
        }

        public void widgetDefaultSelected(SelectionEvent e) {
            // double click.
        }
    };
    private SelectionAdapter sListenerBtnRemoveDiagrams = new SelectionAdapter() {
        public void widgetSelected(SelectionEvent e) {
            if (selectedConcernDiagramIndexes != null && selectedConcern != null && UpdatedDiagram.removeUpdatedConcern(getSelectedConcernDiagrams())) {
                selectedConcernDiagramIndexes = null;
                refreshControl();
            }
        }
    };
    private SelectionListener sListenerCbDiagrams = new SelectionListener() {
        public void widgetSelected(SelectionEvent e) {
            // single click.
            try {
                UpdatedDiagram diagram = (UpdatedDiagram) UpdatedDiagram.getUpdatedDiagrams().get(cbDiagrams.getSelectionIndex());
                if (diagram != selectedDiagram) {
                    selectedDiagram = diagram;
                    refreshControl();
                }
            } catch (IndexOutOfBoundsException ex) {
                // ignore
            }
        }

        public void widgetDefaultSelected(SelectionEvent e) {
            // double click.
        }
    };
    private SelectionAdapter sListenerBtnAssignConcern = new SelectionAdapter() {
        public void widgetSelected(SelectionEvent e) {
            if (selectedDiagram != null && selectedDiagram.assignUpdatedConcern(selectedConcern)) {
                selectedConcernDiagramIndexes = null;
                refreshControl();
            }
        }
    };
    private SelectionAdapter sListenerBtnRemoveConcern = new SelectionAdapter() {
        public void widgetSelected(SelectionEvent e) {
            if (selectedDiagram != null && selectedDiagram.removeUpdatedConcern(true)) {
                selectedConcernDiagramIndexes = null;
                refreshControl();
            }
        }
    };

    /**
     * @return the list of currently selected IURNDiagrams of the concern given the selection indexes
     */
    private java.util.List getSelectedConcernDiagrams() {
        java.util.List list = new ArrayList();
        for (int i = 0; i < selectedConcernDiagramIndexes.length; i++) {
            list.add(allConcernDiagrams.get(selectedConcernDiagramIndexes[i]));
        }
        return list;
    }

    /**
     * Initializes the MODEL for the page based on the parameters passed into the constructor.
     * 
     * @param selectionConcerns
     *            the list of concerns in the URN model
     * @param selectionDiagrams
     *            the list of IURNDiagrams in the URN model
     * @param defaultSelected
     *            the URNmodelElement currently selected (any element other than Concerns or IURNDiagrams are ignored)
     */
    public ConcernsManagerPage(java.util.List selectionConcerns, java.util.List selectionDiagrams, URNmodelElement defaultSelected) {
        super("wizardPage"); //$NON-NLS-1$
        this.setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/perspectiveIcon.gif")); //$NON-NLS-1$
        // create the model to be used by this page by wrapping the concerns in
        // UpdatedConcern objects; sorting ensures that the items are also sorted in the widgets;
        // also sets the selected concern if defaultSelected can be matched to a concern
        this.selectedConcern = null;
        for (Iterator iter = selectionConcerns.iterator(); iter.hasNext();) {
            Object element = (Object) iter.next();
            if (element instanceof Concern) {
                UpdatedConcern uConcern = new UpdatedConcern((Concern) element);
                if ((Concern) element == defaultSelected)
                    this.selectedConcern = uConcern;
            }
        }
        sortListByDisplayText(UpdatedConcern.getUpdatedConcerns());
        // create the model to be used by this page by wrapping the diagrams in
        // UpdatedDiagram objects; sorting ensures that the items are also sorted in the widgets;
        // also sets the selected diagram if defaultSelected can be matched to a diagram
        this.selectedDiagram = null;
        for (Iterator iter = selectionDiagrams.iterator(); iter.hasNext();) {
            Object element = (Object) iter.next();
            if (element instanceof IURNDiagram) {
                UpdatedDiagram uDiagram = new UpdatedDiagram((IURNDiagram) element);
                if ((IURNDiagram) element == defaultSelected)
                    this.selectedDiagram = uDiagram;
            }
        }
        sortListByDisplayText(UpdatedDiagram.getUpdatedDiagrams());
    }

    /**
     * sorts the items in the given list based on the items' textual representation in the UI
     * 
     * @param list
     *            to be sorted
     */
    private void sortListByDisplayText(java.util.List list) {
        Collections.sort(list, new DisplayTextComparator());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent) {
        PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, "seg.jUCMNav.concerns"); //$NON-NLS-1$
        Composite container = createWidgets(parent);
        refreshControl(); // also adds listeners
        setControl(container);
    }

    /**
     * Creates all widgets but does not set any dynamic content
     * 
     * @param parent
     *            of the container
     * @return the Composite that acts as a container for all widgets defined for this page
     */
    private Composite createWidgets(Composite parent) {
        Composite container = new Composite(parent, SWT.NULL);
        GridLayout layout = new GridLayout();
        container.setLayout(layout);
        ((WizardDialog) this.getWizard().getContainer()).setMinimumPageSize(100, 450);
        layout.numColumns = 2;
        layout.verticalSpacing = 5;

        grpConcern = new Group(container, SWT.NONE);
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 2;
        grpConcern.setLayoutData(gd);
        grpConcern.setText(Messages.getString("ConcernsManagerPage.Concern")); //$NON-NLS-1$
        layout = new GridLayout();
        layout.numColumns = 2;
        grpConcern.setLayout(layout);

        cbConcerns = new Combo(grpConcern, SWT.DROP_DOWN | SWT.READ_ONLY);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 2;
        cbConcerns.setLayoutData(gd);

        btnAddConcern = new Button(grpConcern, SWT.PUSH);
        btnAddConcern.setText(Messages.getString("ConcernsManagerPage.AddConcern")); //$NON-NLS-1$

        btnDeleteConcern = new Button(grpConcern, SWT.PUSH);
        btnDeleteConcern.setText(Messages.getString("ConcernsManagerPage.DeleteConcern")); //$NON-NLS-1$

        lblConcernNamePrompt = new Label(grpConcern, SWT.RIGHT);
        lblConcernNamePrompt.setText(Messages.getString("ConcernsManagerPage.NamePrompt")); //$NON-NLS-1$

        txtConcernName = new Text(grpConcern, SWT.BORDER);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        txtConcernName.setLayoutData(gd);

        lblConcernDescriptionPrompt = new Label(grpConcern, SWT.RIGHT);
        lblConcernDescriptionPrompt.setText(Messages.getString("ConcernsManagerPage.DescriptionPrompt")); //$NON-NLS-1$

        txtConcernDescription = new Text(grpConcern, SWT.BORDER);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        txtConcernDescription.setLayoutData(gd);

        lblConcernDiagramsPrompt = new Label(grpConcern, SWT.LEFT | SWT.TOP);
        lblConcernDiagramsPrompt.setText(Messages.getString("ConcernsManagerPage.URNDiagramsPrompt")); //$NON-NLS-1$

        lstConcernDiagrams = new List(grpConcern, SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.heightHint = 100;
        lstConcernDiagrams.setLayoutData(gd);

        btnRemoveDiagrams = new Button(grpConcern, SWT.PUSH);
        gd = new GridData(GridData.HORIZONTAL_ALIGN_END);
        gd.horizontalSpan = 2;
        btnRemoveDiagrams.setLayoutData(gd);
        btnRemoveDiagrams.setText(Messages.getString("ConcernsManagerPage.RemoveSelectedDiagrams")); //$NON-NLS-1$

        grpDiagram = new Group(container, SWT.NONE);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 2;
        grpDiagram.setLayoutData(gd);
        grpDiagram.setText(Messages.getString("ConcernsManagerPage.URNDiagram")); //$NON-NLS-1$
        layout = new GridLayout();
        layout.numColumns = 2;
        grpDiagram.setLayout(layout);

        cbDiagrams = new Combo(grpDiagram, SWT.DROP_DOWN | SWT.READ_ONLY);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 2;
        cbDiagrams.setLayoutData(gd);

        lblAssignedConcernPrompt = new Label(grpDiagram, SWT.RIGHT);
        lblAssignedConcernPrompt.setText(Messages.getString("ConcernsManagerPage.AssignedConcernPrompt")); //$NON-NLS-1$

        lblAssignedConcern = new Label(grpDiagram, SWT.BORDER | SWT.LEFT);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        lblAssignedConcern.setLayoutData(gd);

        btnAssignConcern = new Button(grpDiagram, SWT.PUSH);
        btnAssignConcern.setText(Messages.getString("ConcernsManagerPage.AssignConcern")); //$NON-NLS-1$

        btnRemoveConcern = new Button(grpDiagram, SWT.PUSH);
        btnRemoveConcern.setText(Messages.getString("ConcernsManagerPage.RemoveConcern")); //$NON-NLS-1$
        return container;
    }

    /**
     * adds all listeners to the widgets
     */
    private void addListeners() {
        cbConcerns.addSelectionListener(sListenerCbConcerns);
        btnAddConcern.addSelectionListener(sListenerBtnAddConcern);
        btnDeleteConcern.addSelectionListener(sListenerBtnDeleteConcern);
        txtConcernName.addModifyListener(mListenerTxt);
        txtConcernName.addFocusListener(fListenerTxtConcernName);
        txtConcernDescription.addModifyListener(mListenerTxt);
        lstConcernDiagrams.addSelectionListener(sListenerLstConcernDiagrams);
        btnRemoveDiagrams.addSelectionListener(sListenerBtnRemoveDiagrams);
        cbDiagrams.addSelectionListener(sListenerCbDiagrams);
        btnAssignConcern.addSelectionListener(sListenerBtnAssignConcern);
        btnRemoveConcern.addSelectionListener(sListenerBtnRemoveConcern);
    }

    /**
     * removes all listeners from the widgets
     */
    private void removeListeners() {
        cbConcerns.removeSelectionListener(sListenerCbConcerns);
        btnAddConcern.removeSelectionListener(sListenerBtnAddConcern);
        btnDeleteConcern.removeSelectionListener(sListenerBtnDeleteConcern);
        txtConcernName.removeModifyListener(mListenerTxt);
        txtConcernName.removeFocusListener(fListenerTxtConcernName);
        txtConcernDescription.removeModifyListener(mListenerTxt);
        lstConcernDiagrams.removeSelectionListener(sListenerLstConcernDiagrams);
        btnRemoveDiagrams.removeSelectionListener(sListenerBtnRemoveDiagrams);
        cbDiagrams.removeSelectionListener(sListenerCbDiagrams);
        btnAssignConcern.removeSelectionListener(sListenerBtnAssignConcern);
        btnRemoveConcern.removeSelectionListener(sListenerBtnRemoveConcern);
    }

    /**
     * refreshes the dynamic content of the widgets (removes listeners before doing so and adds them back when finished)
     */
    private void refreshControl() {
        removeListeners();

        // populate the combo box with the concerns
        cbConcerns.removeAll();
        for (Iterator iter = UpdatedConcern.getUpdatedConcerns().iterator(); iter.hasNext();)
            cbConcerns.add(DisplayTextHelper.getDisplayName(iter.next()));
        // select the default
        int index = UpdatedConcern.getUpdatedConcerns().indexOf(selectedConcern);
        cbConcerns.select(index);
        if (cbConcerns.getSelectionIndex() != -1) {
            btnDeleteConcern.setEnabled(true);
            txtConcernName.setText(selectedConcern.getName());
            txtConcernName.setEnabled(true);
            txtConcernDescription.setText(selectedConcern.getDescription());
            txtConcernDescription.setEnabled(true);
            // create sorted list of the diagrams of this concern
            allConcernDiagrams = selectedConcern.getSpecDiagrams();
            sortListByDisplayText(allConcernDiagrams);
            lstConcernDiagrams.removeAll();
            for (Iterator iter = allConcernDiagrams.iterator(); iter.hasNext();)
                lstConcernDiagrams.add(DisplayTextHelper.getDisplayName(iter.next()));
            if (selectedConcernDiagramIndexes != null)
                lstConcernDiagrams.select(selectedConcernDiagramIndexes);
            btnRemoveDiagrams.setEnabled(lstConcernDiagrams.getSelectionCount() > 0);
        } else {
            selectedConcern = null;
            selectedConcernDiagramIndexes = null;
            btnDeleteConcern.setEnabled(false);
            txtConcernName.setText(""); //$NON-NLS-1$
            txtConcernName.setEnabled(false);
            txtConcernDescription.setText(""); //$NON-NLS-1$
            txtConcernDescription.setEnabled(false);
            lstConcernDiagrams.removeAll();
            btnRemoveDiagrams.setEnabled(false);
        }

        // populate the combo box with the diagrams
        cbDiagrams.removeAll();
        for (Iterator iter = UpdatedDiagram.getUpdatedDiagrams().iterator(); iter.hasNext();)
            cbDiagrams.add(DisplayTextHelper.getDisplayName(iter.next()));
        // select the default
        index = UpdatedDiagram.getUpdatedDiagrams().indexOf(selectedDiagram);
        cbDiagrams.select(index);
        if (cbDiagrams.getSelectionIndex() != -1) {
            UpdatedConcern concern = selectedDiagram.getUpdatedConcern();
            if (concern != null)
                lblAssignedConcern.setText(DisplayTextHelper.getDisplayName(concern));
            else
                lblAssignedConcern.setText(""); //$NON-NLS-1$
            btnAssignConcern.setEnabled(cbConcerns.getSelectionIndex() != -1);
            btnRemoveConcern.setEnabled(!lblAssignedConcern.getText().equals("")); //$NON-NLS-1$
        } else {
            selectedDiagram = null;
            lblAssignedConcern.setText(""); //$NON-NLS-1$
            btnAssignConcern.setEnabled(false);
            btnRemoveConcern.setEnabled(false);
        }

        addListeners();
    }

}