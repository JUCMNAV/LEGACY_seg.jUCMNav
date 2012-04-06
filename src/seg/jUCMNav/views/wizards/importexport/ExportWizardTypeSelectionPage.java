package seg.jUCMNav.views.wizards.importexport;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.Messages;
import seg.jUCMNav.importexport.UCMExportExtensionPointHelper;
import seg.jUCMNav.importexport.URNExportExtensionPointHelper;

/**
 * Wizard page to define if we want to export the UCMs individually (images, dot file format) or if we want to export the URN (dxl)
 * 
 * @author jkealey
 * 
 */
public class ExportWizardTypeSelectionPage extends WizardPage {

    private int iUCMCount = 0, iURNCount = 0;
    private Button[] radios = new Button[2];

    /**
     * @param pageName
     */
    protected ExportWizardTypeSelectionPage(String pageName) {
        super(pageName);
        setDescription(Messages.getString("ExportImageWizardPage.pleaseSelectFormatAndDirectory")); //$NON-NLS-1$
        setTitle(Messages.getString("ExportImageWizardPage.exportImageWizard")); //$NON-NLS-1$
    }

    /**
     * Contains radio button to decide on the export type and a label explaning the alternatives.
     */
    public void createControl(Composite parent) {
        PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, "seg.jUCMNav.export_typeselection"); //$NON-NLS-1$
        // create the composite to hold the widgets
        Composite composite = new Composite(parent, SWT.NONE);

        // create the desired layout for this wizard page
        GridLayout gl = new GridLayout(1, false);
        composite.setLayout(gl);
        GridData data;

        Label lblPath = new Label(composite, SWT.NONE);
        lblPath.setText("Please select an option: "); //$NON-NLS-1$

        radios[0] = new Button(composite, SWT.RADIO);
        radios[0].setSelection(ExportPreferenceHelper.getExportType() == ExportPreferenceHelper.URN_DIAGRAM);
        radios[0].setText(Messages.getString("ExportWizardTypeSelectionPage.ExportUCM")); //$NON-NLS-1$

        radios[0].setBounds(10, 5, 75, 30);
        radios[0].addSelectionListener(new SelectionListener() {
            public void widgetDefaultSelected(SelectionEvent e) {
                ExportPreferenceHelper.setExportType(ExportPreferenceHelper.URN_DIAGRAM);
                setPageComplete(iUCMCount != 0);
                ((ExportWizard) getWizard()).refreshPages();
            }

            public void widgetSelected(SelectionEvent e) {
                ExportPreferenceHelper.setExportType(ExportPreferenceHelper.URN_DIAGRAM);
                setPageComplete(iUCMCount != 0);
                ((ExportWizard) getWizard()).refreshPages();
            }
        });

        Label lblUCMInformation = new Label(composite, SWT.NONE);
        iUCMCount = UCMExportExtensionPointHelper.getExportLabels().length;
        String ucm = getUcmExportStrings();
        lblUCMInformation.setText(Messages.getString("ExportWizardTypeSelectionPage.ExportUCMTo") + ucm + "\n"); //$NON-NLS-1$ //$NON-NLS-2$

        radios[1] = new Button(composite, SWT.RADIO);
        radios[1].setSelection(ExportPreferenceHelper.getExportType() == ExportPreferenceHelper.URN);
        radios[1].setText(Messages.getString("ExportWizardTypeSelectionPage.ExportURN")); //$NON-NLS-1$
        radios[1].setBounds(10, 30, 75, 30);
        radios[1].addSelectionListener(new SelectionListener() {
            public void widgetDefaultSelected(SelectionEvent e) {
                ExportPreferenceHelper.setExportType(ExportPreferenceHelper.URN);
                setPageComplete(iURNCount != 0);
                ((ExportWizard) getWizard()).refreshPages();
            }

            public void widgetSelected(SelectionEvent e) {
                ExportPreferenceHelper.setExportType(ExportPreferenceHelper.URN);
                setPageComplete(iURNCount != 0);
                ((ExportWizard) getWizard()).refreshPages();
            }
        });

        Label lblInformation = new Label(composite, SWT.NONE);
        iURNCount = URNExportExtensionPointHelper.getExportLabels().length;
        String urn = getUrnExportStrings();
        lblInformation.setText(Messages.getString("ExportWizardTypeSelectionPage.ExportURNTo") + urn + "\n"); //$NON-NLS-1$ //$NON-NLS-2$ 

        // page can only be complete if we have a type to export to at the next step.
        if ((ExportPreferenceHelper.getExportType() == ExportPreferenceHelper.URN_DIAGRAM && iUCMCount == 0)
                || (ExportPreferenceHelper.getExportType() == ExportPreferenceHelper.URN && iURNCount == 0))
            setPageComplete(false);
        else
            setPageComplete(true);

        setControl(composite);
    }

    /**
     * Builds the list of possible formats when exporting the Map
     * 
     * @return a label to show the available formats
     */
    private String getUcmExportStrings() {
        String ucm = ""; //$NON-NLS-1$
        if (iUCMCount > 0) {
            for (int i = 0; i < iUCMCount; i++) {
                ucm = ucm + UCMExportExtensionPointHelper.getExportLabels()[i] + "\n  "; //$NON-NLS-1$
            }
            ucm = ucm.substring(0, ucm.length() - 2);
        } else
            ucm = Messages.getString("ExportWizardTypeSelectionPage.NoneAvailable"); //$NON-NLS-1$
        return ucm;
    }

    /**
     * Builds the list of possible formats when exporting the URNspec
     * 
     * @return a label to show the available formats
     */
    private String getUrnExportStrings() {
        String urn = ""; //$NON-NLS-1$
        if (iURNCount > 0) {
            for (int i = 0; i < iURNCount; i++) {
                urn = urn + URNExportExtensionPointHelper.getExportLabels()[i] + "\n  "; //$NON-NLS-1$
            }
            urn = urn.substring(0, urn.length() - 2);
        } else
            urn = Messages.getString("ExportWizardTypeSelectionPage.NoneAvailable"); //$NON-NLS-1$
        return urn;
    }
}