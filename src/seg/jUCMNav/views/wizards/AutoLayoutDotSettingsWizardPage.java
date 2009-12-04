package seg.jUCMNav.views.wizards;

import java.io.File;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
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

import seg.jUCMNav.Messages;
import seg.jUCMNav.views.preferences.AutoLayoutPreferences;

/**
 * Settings page for the autolayout wizard.
 * 
 * @author jkealey
 * 
 */
public class AutoLayoutDotSettingsWizardPage extends WizardPage {
    private Combo cboOrientation;

    private Text txtDotPath, txtWidth, txtHeight;

    private Button chkEmptyPoints;

    /**
     * @param pageName
     */
    protected AutoLayoutDotSettingsWizardPage(String pageName) {
        super(pageName);
        setDescription(Messages.getString("AutoLayoutDotSettingsWizardPage.pleaseEnterPreferences")); //$NON-NLS-1$
        setTitle(Messages.getString("AutoLayoutDotSettingsWizardPage.autoLayoutWizard")); //$NON-NLS-1$

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets .Composite)
     */
    public void createControl(Composite parent) {

        PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, "seg.jUCMNav.autolayout"); //$NON-NLS-1$

        // create the composite to hold the widgets
        Composite composite = new Composite(parent, SWT.NONE);

        // create the desired layout for this wizard page
        GridLayout gl = new GridLayout(4, false);
        composite.setLayout(gl);
        GridData data;

        Label lblPath = new Label(composite, SWT.NONE);
        lblPath.setText(Messages.getString("AutoLayoutDotSettingsWizardPage.dotPath")); //$NON-NLS-1$
        data = new GridData();
        data.horizontalSpan = 4;
        lblPath.setLayoutData(data);

        txtDotPath = new Text(composite, SWT.BORDER | SWT.SINGLE | SWT.LEFT);
        setDotPath(AutoLayoutPreferences.getDotPath());

        data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        data.horizontalSpan = 3;
        // data.grabExcessVerticalSpace = true;
        txtDotPath.setLayoutData(data);
        txtDotPath.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                setDotPath(txtDotPath.getText());
            }
        });

        Button b = new Button(getShell(), SWT.PUSH);
        b.setParent(composite);
        b.setText("..."); //$NON-NLS-1$
        b.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
                dialog.setFileName(AutoLayoutPreferences.getDotPath());
                dialog.setText(Messages.getString("AutoLayoutDotSettingsWizardPage.selectGraphvizDot")); //$NON-NLS-1$
                String path = dialog.open();

                if (path != null)
                    setDotPath(path);

            }
        });

        Label lblWidth = new Label(composite, SWT.NONE);
        lblWidth.setText(Messages.getString("AutoLayoutDotSettingsWizardPage.width")); //$NON-NLS-1$

        Label lblHeight = new Label(composite, SWT.NONE);
        lblHeight.setText(Messages.getString("AutoLayoutDotSettingsWizardPage.height")); //$NON-NLS-1$

        Label lblOrientation = new Label(composite, SWT.NONE);
        lblOrientation.setText(Messages.getString("AutoLayoutDotSettingsWizardPage.orientation")); //$NON-NLS-1$
        data = new GridData();
        data.horizontalSpan = 2;
        lblOrientation.setLayoutData(data);

        txtWidth = new Text(composite, SWT.BORDER | SWT.SINGLE | SWT.LEFT);
        data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        txtWidth.setLayoutData(data);

        setWidth(AutoLayoutPreferences.getWidth());
        txtWidth.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                setWidth(txtWidth.getText());

            }
        });

        txtHeight = new Text(composite, SWT.BORDER | SWT.SINGLE | SWT.LEFT);

        data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        txtHeight.setLayoutData(data);

        setHeight(AutoLayoutPreferences.getHeight());
        txtHeight.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                setHeight(txtHeight.getText());

            }
        });

        cboOrientation = new Combo(composite, SWT.READ_ONLY);
        cboOrientation.setItems(new String[] {
                Messages.getString("AutoLayoutDotSettingsWizardPage.topdown"), Messages.getString("AutoLayoutDotSettingsWizardPage.leftright") }); //$NON-NLS-1$ //$NON-NLS-2$

        if (AutoLayoutPreferences.getOrientation().equalsIgnoreCase("TB")) //$NON-NLS-1$
            cboOrientation.select(0); // TB
        else
            cboOrientation.select(1); // LR

        data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        cboOrientation.setLayoutData(data);

        cboOrientation.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                setOrientation(cboOrientation.getSelectionIndex());
            }
        });

        chkEmptyPoints = new Button(composite, SWT.CHECK);
        chkEmptyPoints.setText(Messages.getString("AutoLayoutDotSettingsWizardPage.manipulateEmptyPoints")); //$NON-NLS-1$
        chkEmptyPoints.setSelection(AutoLayoutPreferences.getEmptyPoints());
        data = new GridData();
        data.horizontalSpan = 3;
        chkEmptyPoints.setLayoutData(data);
        chkEmptyPoints.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                setEmptyPoints(chkEmptyPoints.getSelection());
            }
        });

        setControl(composite);

    }

    public void setDotPath(String path) {
        txtDotPath.setText(path);
        AutoLayoutPreferences.setDotPath(path);

        updateStatus(doesDotPathExist() ? null : Messages.getString("AutoLayoutDotSettingsWizardPage.GraphvizNotFound")); //$NON-NLS-1$
    }

    public void setHeight(String height) {
        AutoLayoutPreferences.setHeight(height);
        txtHeight.setText(AutoLayoutPreferences.getHeight());
    }

    public void setOrientation(int i) {
        cboOrientation.select(i);
        if (i == 0)
            AutoLayoutPreferences.setOrientation("TB"); //$NON-NLS-1$
        else
            AutoLayoutPreferences.setOrientation("LR"); //$NON-NLS-1$

    }

    public void setWidth(String width) {
        AutoLayoutPreferences.setWidth(width);
        txtWidth.setText(AutoLayoutPreferences.getWidth());

    }

    public void setEmptyPoints(boolean b) {
        AutoLayoutPreferences.setEmptyPoints(b);
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

    private boolean doesDotPathExist() {
        try {
            File f = new File(AutoLayoutPreferences.getDotPath());
            return f.exists();
        } catch (Exception ex) {
            return false;
        }
    }

}