package seg.jUCMNav.views.wizards;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.views.wizards.importexport.jUCMNavLoader;

/**
 * The settings page for the new wizard; name your file and select where you want to create it.
 * 
 * @author etremblay
 *  
 */
public class NewUcmFileWizardPage extends WizardPage {
    private Text containerText;
    private Text fileText;
    private ISelection selection;
    public boolean overwrite=false;
    /**
     * Constructor for SampleNewWizardPage.
     * 
     * @param selection
     */
    public NewUcmFileWizardPage(ISelection selection) {
        super("wizardPage"); //$NON-NLS-1$
        setTitle(Messages.getString("NewUcmFileWizardPage.jUCMFile")); //$NON-NLS-1$
        setDescription(Messages.getString("NewUcmFileWizardPage.createsNewJUCM")); //$NON-NLS-1$
        this.setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/perspectiveIcon.gif")); //$NON-NLS-1$
        this.selection = selection;
    }

    /**
     */
    public void createControl(Composite parent) {
        Composite container = new Composite(parent, SWT.NULL);
        GridLayout layout = new GridLayout();
        container.setLayout(layout);
        layout.numColumns = 3;
        layout.verticalSpacing = 9;
        Label label = new Label(container, SWT.NULL);
        label.setText(Messages.getString("NewUcmFileWizardPage.Container")); //$NON-NLS-1$

        containerText = new Text(container, SWT.BORDER | SWT.SINGLE);
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        containerText.setLayoutData(gd);
        containerText.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                dialogChanged();
            }
        });

        Button button = new Button(container, SWT.PUSH);
        button.setText(Messages.getString("NewUcmFileWizardPage.Browse")); //$NON-NLS-1$
        button.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                handleBrowse();
            }
        });
        label = new Label(container, SWT.NULL);
        label.setText(Messages.getString("NewUcmFileWizardPage.fileName")); //$NON-NLS-1$

        fileText = new Text(container, SWT.BORDER | SWT.SINGLE);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        fileText.setLayoutData(gd);
        fileText.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                dialogChanged();
            }
        });
        initialize();
        dialogChanged();
        setControl(container);
    }

    /**
     * Tests if the current workbench selection is a suitable container to use.
     */

    private void initialize() {
        if (selection != null && selection.isEmpty() == false && selection instanceof IStructuredSelection) {
            IStructuredSelection ssel = (IStructuredSelection) selection;
            if (ssel.size() > 1)
                return;
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
        fileText.setText(Messages.getString("NewUcmFileWizardPage.newFile.jucm")); //$NON-NLS-1$
    }

    /**
     * Uses the standard container selection dialog to choose the new value for the container field.
     */

    private void handleBrowse() {
        ContainerSelectionDialog dialog = new ContainerSelectionDialog(getShell(), ResourcesPlugin.getWorkspace().getRoot(), false, Messages
                .getString("NewUcmFileWizardPage.selectNewFileContainer")); //$NON-NLS-1$
        if (dialog.open() == ContainerSelectionDialog.OK) {
            Object[] result = dialog.getResult();
            if (result.length == 1) {
                containerText.setText(((Path) result[0]).toOSString());
            }
        }
    }

    /**
     * Ensures that both text fields are set.
     */

    private void dialogChanged() {
        String container = getContainerName();
        String fileName = getFileName();

        if (container.length() == 0) {
            updateStatus(Messages.getString("NewUcmFileWizardPage.fileContainerMissing")); //$NON-NLS-1$
            return;
        }
        if (fileName.length() == 0) {
            updateStatus(Messages.getString("NewUcmFileWizardPage.fileNameMissing")); //$NON-NLS-1$
            return;
        }
        int dotLoc = fileName.lastIndexOf('.');
        if (dotLoc != -1) {
            String ext = fileName.substring(dotLoc + 1);
            if (ext.equalsIgnoreCase("jucm") == false) { //$NON-NLS-1$
                updateStatus(Messages.getString("NewUcmFileWizardPage.fileExtensionJUCM")); //$NON-NLS-1$
                return;
            }
        } else {
            updateStatus(Messages.getString("NewUcmFileWizardPage.fileExtensionJUCM")); //$NON-NLS-1$
            return;
        }

        updateStatus(null);
    }

    private void updateStatus(String message) {
        setErrorMessage(message);
        setPageComplete(message == null);
    }

    public String getContainerName() {
        return containerText.getText();
    }

    public String getFileName() {
        return fileText.getText();
    }
    
    public void preFinish() {
        String sFileName = fileText.getText();
        String sContainer = containerText.getText();
        jUCMNavLoader loader = new jUCMNavLoader(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), getShell());
    	IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IResource resource = root.findMember(new Path(loader.getTargetFilename(sFileName, sContainer, true)));
        if (resource != null) {
            overwrite = MessageDialog.openQuestion(getShell(), "File is already in use",
                   "Filename already in use. Overwrite?");
            
        }

    }
}