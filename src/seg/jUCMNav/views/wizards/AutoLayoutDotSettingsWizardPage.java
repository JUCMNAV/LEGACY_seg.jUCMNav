package seg.jUCMNav.views.wizards;

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

import seg.jUCMNav.Messages;

/**
 * Created on 9-May-2005
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
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent) {
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
        setDotPath(getDotPath());

        data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        data.horizontalSpan = 3;
        //        data.grabExcessVerticalSpace = true;
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
                dialog.setFileName(getDotPath());
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

        setWidth(getWidth());
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

        setHeight(getHeight());
        txtHeight.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                setHeight(txtHeight.getText());

            }
        });

        cboOrientation = new Combo(composite, SWT.READ_ONLY);
        cboOrientation.setItems(new String[] { Messages.getString("AutoLayoutDotSettingsWizardPage.topdown"), Messages.getString("AutoLayoutDotSettingsWizardPage.leftright") }); //$NON-NLS-1$ //$NON-NLS-2$
        cboOrientation.select(getOrientation());
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
        chkEmptyPoints.setSelection(getEmptyPoints());
        data = new GridData();
        data.horizontalSpan = 3;
        chkEmptyPoints.setLayoutData( data );
        chkEmptyPoints.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
            }

            public void focusLost(FocusEvent e) {
                setEmptyPoints(chkEmptyPoints.getSelection());
            }
        });

        setControl(composite);

    }

    public String getDotPath() {
        return AutoLayoutWizard.getPreferenceStore().getString(AutoLayoutWizard.PREF_DOTPATH);
    }

    public String getHeight() {
        return AutoLayoutWizard.getPreferenceStore().getString(AutoLayoutWizard.PREF_HEIGHT);
    }

    public int getOrientation() {
        return AutoLayoutWizard.getPreferenceStore().getInt(AutoLayoutWizard.PREF_ORIENTATION);
    }

    public String getWidth() {
        return AutoLayoutWizard.getPreferenceStore().getString(AutoLayoutWizard.PREF_WIDTH);
    }

    public boolean getEmptyPoints() {
        return AutoLayoutWizard.getPreferenceStore().getBoolean(AutoLayoutWizard.PREF_EMPTYPOINTS);
    }

    public void setDotPath(String path) {
        txtDotPath.setText(path);
        AutoLayoutWizard.getPreferenceStore().setValue(AutoLayoutWizard.PREF_DOTPATH, path);
    }

    public void setHeight(String height) {
        String s;
        // want to make sure it is convertible.
        try {
            double d = Double.parseDouble(height);
            s = Double.toString(d);
        } catch (Exception e) {
            s = "0"; //$NON-NLS-1$
        }
        txtHeight.setText(s);

        AutoLayoutWizard.getPreferenceStore().setValue(AutoLayoutWizard.PREF_HEIGHT, s);
    }

    public void setOrientation(int i) {
        cboOrientation.select(i);
        AutoLayoutWizard.getPreferenceStore().setValue(AutoLayoutWizard.PREF_ORIENTATION, i);
    }

    public void setWidth(String width) {
        String s;
        // want to make sure it is convertible.
        try {
            double d = Double.parseDouble(width);
            s = Double.toString(d);
        } catch (Exception e) {
            s = "0"; //$NON-NLS-1$
        }
        txtWidth.setText(s);

        AutoLayoutWizard.getPreferenceStore().setValue(AutoLayoutWizard.PREF_WIDTH, s);
    }

    public void setEmptyPoints(boolean b) {
        AutoLayoutWizard.getPreferenceStore().setValue(AutoLayoutWizard.PREF_EMPTYPOINTS, b);
    }

}