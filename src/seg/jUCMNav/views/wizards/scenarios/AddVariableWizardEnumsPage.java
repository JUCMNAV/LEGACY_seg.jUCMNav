package seg.jUCMNav.views.wizards.scenarios;

import java.util.Vector;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.commands.create.CreateEnumerationTypeCommand;
import seg.jUCMNav.model.commands.delete.DeleteEnumerationTypeCommand;
import seg.jUCMNav.model.commands.transformations.ChangeEnumerationTypeCommand;
import seg.jUCMNav.model.util.URNNamingHelper;
import seg.jUCMNav.scenarios.ScenarioUtils;
import ucm.scenario.EnumerationType;
import urn.URNspec;

/**
 * The page to select an enumeration, create new ones, modify their values.
 * 
 */
public class AddVariableWizardEnumsPage extends WizardPage {
    private URNspec urn;

    private Combo possibilities;
    private Text enumerationName;
    private Text enumerationValues;

    private int executedCommandCount = 0;
    private int currentlySelectedIndex = 0;

    private Button btnNewEnumeration;
    private Button btnDeleteEnumeration;

    private ModifyListener modifyListener = new ModifyListener() {
        public void modifyText(ModifyEvent e) {
            dialogChanged();
        }
    };

    private SelectionListener changeSelection = new SelectionListener() {

        public void widgetSelected(SelectionEvent e) {
            // single click.

            if (possibilities.getSelectionIndex() >= 0 && possibilities.getSelectionIndex() != currentlySelectedIndex) {
                currentlySelectedIndex = possibilities.getSelectionIndex();
                fillFields((EnumerationType) urn.getUcmspec().getEnumerationTypes().get(possibilities.getSelectionIndex()));
                dialogChanged();
                refreshPossibilityLabels();
            }
        }

        public void widgetDefaultSelected(SelectionEvent e) {
            // double click.

        }

    };

    /**
     * Initializes the wizard page with the URNspec.
     * 
     * @param urn
     */
    public AddVariableWizardEnumsPage(URNspec urn) {
        super("wizardPage"); //$NON-NLS-1$

        this.setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/perspectiveIcon.gif")); //$NON-NLS-1$

        if (getWizard() instanceof AddVariableWizard)
            setTitle(Messages.getString("AddVariableWizardEnumsPage.NewVariableWizard")); //$NON-NLS-1$
        else
            setTitle(Messages.getString("AddVariableWizardEnumsPage.ChooseAnEnumeration")); //$NON-NLS-1$
        setDescription(Messages.getString("AddVariableWizardEnumsPage.ChooseAnEnumeration")); //$NON-NLS-1$
        this.urn = urn;

    }

    /**
     * Creates the page.
     */
    public void createControl(Composite parent) {
        PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, "seg.jUCMNav.scenario_addvariableenums"); //$NON-NLS-1$
        Composite container = new Composite(parent, SWT.NULL);

        GridLayout layout = new GridLayout();
        container.setLayout(layout);
        layout.numColumns = 2;
        layout.verticalSpacing = 5;

        Label label = new Label(container, SWT.NULL);
        label.setText(Messages.getString("AddVariableWizardEnumsPage.PleaseSelectAnEnumeration")); //$NON-NLS-1$

        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 2;
        label.setLayoutData(gd);

        possibilities = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 2;
        possibilities.setLayoutData(gd);
        currentlySelectedIndex = -1;

        possibilities.addSelectionListener(changeSelection);

        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 2;
        label.setLayoutData(gd);

        btnNewEnumeration = new Button(container, SWT.NULL);
        btnNewEnumeration.setText(Messages.getString("AddVariableWizardEnumsPage.NewEnumeration")); //$NON-NLS-1$
        btnNewEnumeration.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                execute(new CreateEnumerationTypeCommand(urn));
                refreshPossibilityLabels();
                dialogChanged();

            }
        });

        btnDeleteEnumeration = new Button(container, SWT.NULL);
        btnDeleteEnumeration.setText(Messages.getString("AddVariableWizardEnumsPage.DeleteSelectedEnumeration")); //$NON-NLS-1$
        btnDeleteEnumeration.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                if (MessageDialog.openConfirm(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), Messages
                        .getString("AddVariableWizardEnumsPage.DeleteEnumerationType"), //$NON-NLS-1$
                        Messages.getString("AddVariableWizardEnumsPage.AreYouSureYouWishToDeleteThisEnumerationType"))) { //$NON-NLS-1$
                    execute(new DeleteEnumerationTypeCommand((EnumerationType) urn.getUcmspec().getEnumerationTypes().get(possibilities.getSelectionIndex())));
                    refreshPossibilityLabels();
                    dialogChanged();
                }
            }
        });

        label = new Label(container, SWT.NULL);
        label.setText(Messages.getString("AddVariableWizardEnumsPage.ModifySelectedEnumeration")); //$NON-NLS-1$
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 2;
        label.setLayoutData(gd);

        label = new Label(container, SWT.NULL);
        label.setText(Messages.getString("AddVariableWizardEnumsPage.Enumerationname")); //$NON-NLS-1$

        enumerationName = new Text(container, SWT.BORDER);
        enumerationName.addModifyListener(modifyListener);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        enumerationName.setLayoutData(gd);

        label = new Label(container, SWT.NULL);
        label.setText(Messages.getString("AddVariableWizardEnumsPage.EnumerationValuesOnePerLine")); //$NON-NLS-1$
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 2;
        label.setLayoutData(gd);

        enumerationValues = new Text(container, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
        gd = new GridData(GridData.FILL_BOTH);
        gd.horizontalSpan = 2;
        enumerationValues.setLayoutData(gd);

        enumerationValues.addModifyListener(modifyListener);

        refreshPossibilityLabels();
        dialogChanged();
        setControl(container);

    }

    protected void fillFields(EnumerationType myEnum) {

        enumerationName.removeModifyListener(modifyListener);
        enumerationValues.removeModifyListener(modifyListener);
        if (myEnum == null) {
            enumerationName.setText(""); //$NON-NLS-1$
        } else {
            enumerationName.setText(myEnum.getName());
        }

        enumerationName.setEditable(myEnum != null);
        enumerationValues.setEditable(myEnum != null);
        btnDeleteEnumeration.setEnabled(myEnum != null);

        if (myEnum == null || myEnum.getValues() == null || myEnum.getValues().length() == 0) {
            enumerationValues.setText(""); //$NON-NLS-1$
        } else {
            enumerationValues.setText(myEnum.getValues().replace(",", "\n")); //$NON-NLS-1$ //$NON-NLS-2$
        }
        enumerationName.addModifyListener(modifyListener);
        enumerationValues.addModifyListener(modifyListener);

    }

    private void refreshPossibilityLabels() {
        currentlySelectedIndex = possibilities.getSelectionIndex();
        // newly added
        if (urn.getUcmspec().getEnumerationTypes().size() > possibilities.getItemCount() && possibilities.getSelectionIndex() >= 0)
            currentlySelectedIndex = urn.getUcmspec().getEnumerationTypes().size() - 1;

        possibilities.removeAll();
        for (int i = 0; i < urn.getUcmspec().getEnumerationTypes().size(); i++) {
            EnumerationType element = (EnumerationType) urn.getUcmspec().getEnumerationTypes().get(i);
            possibilities.add(URNNamingHelper.getName(element));
        }
        if (currentlySelectedIndex < 0)
            currentlySelectedIndex = 0;

        if (currentlySelectedIndex >= possibilities.getItemCount())
            currentlySelectedIndex = possibilities.getItemCount() - 1;

        if (currentlySelectedIndex < possibilities.getItemCount()) {
            if (currentlySelectedIndex != -1) {
                possibilities.select(currentlySelectedIndex);
                fillFields((EnumerationType) urn.getUcmspec().getEnumerationTypes().get(currentlySelectedIndex));
            } else
                fillFields(null);
        }

    }

    /**
     * Ensures that the selection is legal
     */
    private void dialogChanged() {
        if (possibilities.getSelectionIndex() < 0) {
            if (getWizard() instanceof AddVariableWizard)
                updateStatus(Messages.getString("AddVariableWizardEnumsPage.PleaseSelectThisVariablesEnumerationType")); //$NON-NLS-1$
            else
                updateStatus(null);
            return;
        }
        String[] values = enumerationValues.getText().split("\n"); //$NON-NLS-1$
        Vector v = new Vector();
        for (int i = 0; i < values.length; i++) {
            if (values[i].trim().length() > 0)
                v.add(URNNamingHelper.cleanVariableName(values[i].trim()));//.toUpperCase());
        }

        boolean err = v.size() == 0;
        if (err)
            updateStatus(Messages.getString("AddVariableWizardEnumsPage.PleaseEnterAtLeastOneValue")); //$NON-NLS-1$
        else {
            
            Vector v2 = new Vector();
            for (Object s : v) {
                v2.add(s.toString().toUpperCase());
            }
            // verify uniqueness
            for (int i = 0; i < v.size() / 2; i++) {
                if (v2.lastIndexOf(v.get(i).toString().toUpperCase()) != i)
                    err = true;
            }

            if (err) {
                updateStatus(Messages.getString("AddVariableWizardEnumsPage.PleaseEnterUniqueValues")); //$NON-NLS-1$
            } else {
                // everything okay.
                execute(new ChangeEnumerationTypeCommand((EnumerationType) urn.getUcmspec().getEnumerationTypes().get(currentlySelectedIndex), URNNamingHelper
                        .cleanVariableName(enumerationName.getText()), v));

                String[] strings = new String[v.size()];
                for (int i = 0; i < v.size(); i++)
                    strings[i] = v.get(i).toString();
                if (getWizard() instanceof AddVariableWizard) {
                    AddVariableWizardInitsPage page = ((AddVariableWizardInitsPage) ((AddVariableWizard) getWizard()).getPages()[2]);
                    page.enum_values = strings;
                    page.setupInitializations();
                }
                updateStatus(null);
            }

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

    private void execute(Command cmd) {
        // TODO: Build a compound command instead so that undo in main editor is still possible.
        UCMNavMultiPageEditor editor = (UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        CommandStack stack = editor.getDelegatingCommandStack();
        if (cmd instanceof ChangeEnumerationTypeCommand && stack.getUndoCommand() instanceof ChangeEnumerationTypeCommand
                && ((ChangeEnumerationTypeCommand) cmd).getElem() == ((ChangeEnumerationTypeCommand) stack.getUndoCommand()).getElem()) {
            // replace it, don't increment count
            stack.undo();
            stack.execute(cmd);
        } else {
            stack.execute(cmd);
            executedCommandCount++;
        }

        // bug 654
        ScenarioUtils.releaseEnvironment(this.urn);

    }

    private void undo() {
        UCMNavMultiPageEditor editor = (UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        CommandStack stack = editor.getDelegatingCommandStack();
        stack.undo();
    }

    public void undoAll() {
        UCMNavMultiPageEditor editor = (UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        CommandStack stack = editor.getDelegatingCommandStack();

        for (int i = 0; i < executedCommandCount; i++)
            stack.undo();
    }

    public EnumerationType getSelectedEnumerationType() {
        if (currentlySelectedIndex >= 0)
            return (EnumerationType) urn.getUcmspec().getEnumerationTypes().get(currentlySelectedIndex);
        else
            return null;
    }

}
