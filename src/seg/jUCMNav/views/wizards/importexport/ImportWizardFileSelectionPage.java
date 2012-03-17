package seg.jUCMNav.views.wizards.importexport;

import java.io.File;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.preference.IPreferencePage;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.preference.PreferenceNode;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;

import seg.jUCMNav.Messages;
import seg.jUCMNav.importexport.URNImportExtensionPointHelper;
import seg.jUCMNav.views.preferences.AutoLayoutPreferencePage;

/**
 * Wizard page to select a file to import.
 * 
 * @author jkealey
 * 
 */
public class ImportWizardFileSelectionPage extends WizardPage {

    private Text txtFilePath, containerText;
    private String sFileName, sContainer;

    // to be filled with alternatives from the extension points
    private Combo cboFileTypes;

    // perform autolayout on creation?
    private Button chkAutolayout;

    // which extension is chosen in the dropdown list (file type)
    private int iTypeSelectionIndex;
    public boolean overwrite = false;

    private boolean bAutolayout;

    private ISelection selection;

    /**
     * @param pageName
     */
    protected ImportWizardFileSelectionPage(String pageName, ISelection selection) {
        super(pageName);
        this.selection = selection;
        setDescription(Messages.getString("ImportWizardFileSelectionPage.SelectFileImport")); //$NON-NLS-1$
        setTitle(Messages.getString("ImportWizardFileSelectionPage.ImportFile")); //$NON-NLS-1$
    }

    public void createControl(Composite parent) {
        PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, "seg.jUCMNav.import_fileselection"); //$NON-NLS-1$

        // create the composite to hold the widgets
        Composite composite = new Composite(parent, SWT.NONE);

        // create the desired layout for this wizard page
        GridLayout gl = new GridLayout(4, false);
        composite.setLayout(gl);
        GridData data;

        Label lblType = new Label(composite, SWT.NONE);
        lblType.setText(Messages.getString("ExportImageWizardPage.fileType")); //$NON-NLS-1$
        data = new GridData();
        data.horizontalSpan = 1;
        data.horizontalAlignment = GridData.FILL;
        lblType.setLayoutData(data);

        cboFileTypes = new Combo(composite, SWT.READ_ONLY);

        data = new GridData();
        data.horizontalSpan = 3;
        data.horizontalAlignment = GridData.FILL;
        cboFileTypes.setLayoutData(data);

        Label lblPath = new Label(composite, SWT.NONE);
        lblPath.setText(Messages.getString("ImportWizardFileSelectionPage.FileToImport")); //$NON-NLS-1$
        data = new GridData();
        data.horizontalSpan = 4;
        lblPath.setLayoutData(data);

        txtFilePath = new Text(composite, SWT.BORDER | SWT.SINGLE | SWT.LEFT);
        txtFilePath.setText(ImportPreferenceHelper.getPath());
        setFileName(txtFilePath.getText());

        data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        data.horizontalSpan = 3;
        // data.grabExcessVerticalSpace = true;
        txtFilePath.setLayoutData(data);
        txtFilePath.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                setFileName(txtFilePath.getText());
                dialogChanged();
            }
        });

        Button b = new Button(getShell(), SWT.PUSH);
        b.setParent(composite);
        b.setText("..."); //$NON-NLS-1$
        b.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
                dialog.setFileName(ImportPreferenceHelper.getPath());
                dialog.setText(Messages.getString("ImportWizardFileSelectionPage.SelectFileImport")); //$NON-NLS-1$
                if (cboFileTypes.getItemCount() > 0) {
                    String importer = URNImportExtensionPointHelper.getExporterFromLabelIndex(cboFileTypes.getSelectionIndex());
                    String extension = URNImportExtensionPointHelper.getFilenameExtension(importer);
                    dialog.setFilterExtensions(new String[] { "*." + extension }); //$NON-NLS-1$
                }
                String path = dialog.open();

                if (path != null) {
                    setFileName(path);
                    txtFilePath.setText(path);
                    dialogChanged();
                }

            }
        });

        Label label = new Label(composite, SWT.NULL);
        label.setText(Messages.getString("ImportWizardFileSelectionPage.IntoFolder")); //$NON-NLS-1$
        data = new GridData();
        data.horizontalSpan = 4;
        label.setLayoutData(data);

        containerText = new Text(composite, SWT.BORDER | SWT.SINGLE);
        containerText.setText(ImportPreferenceHelper.getProject());
        if (selection != null && selection.isEmpty() == false && selection instanceof IStructuredSelection) {
            IStructuredSelection ssel = (IStructuredSelection) selection;
            if (ssel.size() == 1) {
                Object obj = ssel.getFirstElement();
                if (obj instanceof IResource) {
                    IContainer container;
                    if (obj instanceof IContainer)
                        container = (IContainer) obj;
                    else
                        container = ((IResource) obj).getParent();
                    containerText.setText(container.getFullPath().toString());
                }
            }
        }

        setContainerName(containerText.getText());
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        containerText.setLayoutData(gd);
        containerText.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                setContainerName(containerText.getText());
                dialogChanged();
            }
        });

        data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        data.horizontalSpan = 3;
        containerText.setLayoutData(data);

        Button button = new Button(composite, SWT.PUSH);
        button.setText("..."); //$NON-NLS-1$
        button.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                handleBrowse();
            }
        });

        chkAutolayout = new Button(composite, SWT.CHECK);
        chkAutolayout.setText(Messages.getString("ImportWizardFileSelectionPage.PerformAutolayoutAfter")); //$NON-NLS-1$
        chkAutolayout.setSelection(ImportPreferenceHelper.getAutoLayout());
        data = new GridData();
        data.horizontalSpan = 2;
        chkAutolayout.setLayoutData(data);

        b = new Button(getShell(), SWT.PUSH);
        b.setParent(composite);
        b.setText(Messages.getString("ImportWizardFileSelectionPage.AutolayoutPreferences")); //$NON-NLS-1$
        b.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                IPreferencePage page = new AutoLayoutPreferencePage();
                page.setTitle(Messages.getString("ImportWizardFileSelectionPage.AutolayoutPreferences")); //$NON-NLS-1$
                PreferenceManager mgr = new PreferenceManager();
                IPreferenceNode node = new PreferenceNode("1", page); //$NON-NLS-1$

                mgr.addToRoot(node);
                PreferenceDialog dialog = new PreferenceDialog(getShell(), mgr);
                dialog.create();
                dialog.setMessage(page.getTitle());
                dialog.open();
            }
        });
        data = new GridData();
        data.horizontalSpan = 2;
        b.setLayoutData(data);

        fillTypeDropDown();

        dialogChanged();
        setControl(composite);

    }

    protected void setFileName(String path) {
        sFileName = path;
    }

    protected String getFileName() {
        return sFileName;
    }

    /**
     * Uses the standard container selection dialog to choose the new value for the container field.
     */

    private void handleBrowse() {
        ContainerSelectionDialog dialog = new ContainerSelectionDialog(getShell(), ResourcesPlugin.getWorkspace().getRoot(), false, Messages
                .getString("ImportWizardFileSelectionPage.SelectProject")); //$NON-NLS-1$
        if (dialog.open() == Window.OK) {
            Object[] result = dialog.getResult();
            if (result.length == 1) {
                containerText.setText(((Path) result[0]).toOSString());
                setContainerName(containerText.getText());
            }
        }
    }

    /**
     * Ensures that both text fields are set.
     */

    private void dialogChanged() {
        String container = getContainerName();
        String fileName = getFileName();

        File f = new File(fileName);
        if (fileName == null || fileName.length() == 0 || !f.isFile()) {
            updateStatus(Messages.getString("ImportWizardFileSelectionPage.InvalidFilename")); //$NON-NLS-1$
            return;
        }

        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IResource resource = root.findMember(new Path(container));
        if (resource == null || !resource.exists() || !(resource instanceof IContainer) || resource instanceof IWorkspaceRoot) {
            updateStatus(Messages.getString("ImportWizardFileSelectionPage.NoFolderSelected")); //$NON-NLS-1$
            return;
        }

        if ((resource instanceof IProject) && !((IProject) resource).isOpen()) {
            updateStatus(Messages.getString("ImportWizardFileSelectionPage.ClosedProject")); //$NON-NLS-1$
            return;
        }

        if (cboFileTypes.getItemCount() == 0) {
            updateStatus(Messages.getString("ImportWizardFileSelectionPage.NoImportModulesAvailable")); //$NON-NLS-1$
            return;
        }
        updateStatus(null);
    }

    private void updateStatus(String message) {
        setErrorMessage(message);
        setPageComplete(message == null);
    }

    /**
     * Refresh cboImageType to present the choices offered by the extensions.
     * 
     */
    private void fillTypeDropDown() {
        cboFileTypes.setItems(URNImportExtensionPointHelper.getImportLabels());
        if (cboFileTypes.getItemCount() > ImportPreferenceHelper.getType()) {
            cboFileTypes.select(ImportPreferenceHelper.getType());
            // setPageComplete(true);
        } else if (cboFileTypes.getItemCount() != 0) {
            cboFileTypes.select(0);
        }
    }

    public String getContainerName() {
        return sContainer;
    }

    public void setContainerName(String s) {
        sContainer = s;
    }

    /**
     * Saves settings to preferences.
     * 
     * @return success
     */
    public boolean finish() {

    	ImportPreferenceHelper.setPath(sFileName);
        ImportPreferenceHelper.setProject(sContainer);
        ImportPreferenceHelper.setType(iTypeSelectionIndex);
        ImportPreferenceHelper.setAutoLayout(bAutolayout);

        return true;
    }

    /**
     * Saves the current state of the page so that another thread can run finish() and not worry about thread access exceptions.
     * 
     */
    public void preFinish() {
        sFileName = txtFilePath.getText();
        iTypeSelectionIndex = cboFileTypes.getSelectionIndex();
        sContainer = containerText.getText();
        bAutolayout = chkAutolayout.getSelection();

        jUCMNavLoader loader = new jUCMNavLoader(((ImportWizard) getWizard()).getPage(), getShell());

        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IResource resource = root.findMember(new Path(loader.getTargetFilename(sFileName, sContainer, true)));
        if (resource != null && (ImportPreferenceHelper.getImportType() == ImportPreferenceHelper.IMPORT_NEWFILE)) {
            overwrite = MessageDialog.openQuestion(getShell(), Messages.getString("ImportWizardFileSelectionPage.TargetFileExists"), //$NON-NLS-1$
                    Messages.getString("ImportWizardFileSelectionPage.ImportDestinationExists")); //$NON-NLS-1$
        }
    }

}