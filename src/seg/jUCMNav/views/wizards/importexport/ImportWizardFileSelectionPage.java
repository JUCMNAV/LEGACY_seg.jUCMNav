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

    /**
     * @param pageName
     */
    protected ImportWizardFileSelectionPage(String pageName) {
        super(pageName);
        setDescription("Please select the file to import.");
        setTitle("Import File");
    }

    public void createControl(Composite parent) {
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
        lblPath.setText("File to import: ");
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
        b.setText("...");
        b.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
                dialog.setFileName(ImportPreferenceHelper.getPath());
                dialog.setText("Select file to import");
                if (cboFileTypes.getItemCount() > 0) {
                    String importer = URNImportExtensionPointHelper.getExporterFromLabelIndex(cboFileTypes.getSelectionIndex());
                    String extension = URNImportExtensionPointHelper.getFilenameExtension(importer);
                    dialog.setFilterExtensions(new String[] { "*." + extension });
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
        label.setText("Into folder: ");
        data = new GridData();
        data.horizontalSpan = 4;
        label.setLayoutData(data);

        containerText = new Text(composite, SWT.BORDER | SWT.SINGLE);
        containerText.setText(ImportPreferenceHelper.getProject());
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
        button.setText("...");
        button.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                handleBrowse();
            }
        });

        chkAutolayout = new Button(composite, SWT.CHECK);
        chkAutolayout.setText("Perform autolayout after import?");
        chkAutolayout.setSelection(ImportPreferenceHelper.getAutoLayout());
        data = new GridData();
        data.horizontalSpan = 2;
        chkAutolayout.setLayoutData(data);

        b = new Button(getShell(), SWT.PUSH);
        b.setParent(composite);
        b.setText("Autolayout preferences");
        b.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                IPreferencePage page = new AutoLayoutPreferencePage();
                page.setTitle("Autolayout preferences");
                PreferenceManager mgr = new PreferenceManager();
                IPreferenceNode node = new PreferenceNode("1", page);
                
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
        ContainerSelectionDialog dialog = new ContainerSelectionDialog(getShell(), ResourcesPlugin.getWorkspace().getRoot(), false, "Select Project");
        if (dialog.open() == ContainerSelectionDialog.OK) {
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
            updateStatus("Invalid filename specified.");
            return;
        }

        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IResource resource = root.findMember(new Path(container));
        if (resource == null || !resource.exists() || !(resource instanceof IContainer) || resource instanceof IWorkspaceRoot) {
            updateStatus("No folder selected or folder doesn't exist.");
            return;
        }

        if ((resource instanceof IProject) && !((IProject) resource).isOpen()) {
            updateStatus("This project is closed.");
            return;
        }

        if (cboFileTypes.getItemCount() == 0) {
            updateStatus("No import modules available.");
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
        if (resource != null) {
            overwrite = MessageDialog.openQuestion(getShell(), "Target file already exists",
                    "The import destination already exists. Do you wish to overwrite it?");
        }
    }

}