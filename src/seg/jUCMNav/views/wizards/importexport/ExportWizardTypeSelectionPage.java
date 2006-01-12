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
        // create the composite to hold the widgets
        Composite composite = new Composite(parent, SWT.NONE);

        // create the desired layout for this wizard page
        GridLayout gl = new GridLayout(1, false);
        composite.setLayout(gl);
        GridData data;

        Label lblPath = new Label(composite, SWT.NONE);
        lblPath.setText("Please select an option: "); //$NON-NLS-1$

        radios[0] = new Button(composite, SWT.RADIO);
        radios[0].setSelection(ExportPreferenceHelper.getExportType() == ExportPreferenceHelper.UCM);
        radios[0].setText("Export Use Case Maps (UCM): ");

        radios[0].setBounds(10, 5, 75, 30);
        radios[0].addSelectionListener(new SelectionListener() {
            public void widgetDefaultSelected(SelectionEvent e) {
                ExportPreferenceHelper.setExportType(ExportPreferenceHelper.UCM);
                setPageComplete(iUCMCount != 0);
                ((ExportWizard) getWizard()).refreshPages();
            }

            public void widgetSelected(SelectionEvent e) {
                ExportPreferenceHelper.setExportType(ExportPreferenceHelper.UCM);
                setPageComplete(iUCMCount != 0);
                ((ExportWizard) getWizard()).refreshPages();
            }
        });

        radios[1] = new Button(composite, SWT.RADIO);
        radios[1].setSelection(ExportPreferenceHelper.getExportType() == ExportPreferenceHelper.URN);
        radios[1].setText("Export User Requirements Notation (URN): ");
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

        iUCMCount = UCMExportExtensionPointHelper.getExportLabels().length;
        iURNCount = URNExportExtensionPointHelper.getExportLabels().length;

        String ucm = getUcmExportStrings();
        String urn = getUrnExportStrings();

        lblInformation.setText("You can export UCMs to the following formats: \n  " + ucm + "\nYou can export URN to the following formats: \n  " + urn + "\n");


        // page can only be complete if we have a type to export to at the next step.
        if ((ExportPreferenceHelper.getExportType() == ExportPreferenceHelper.UCM && iUCMCount == 0)
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
        String ucm = "";
        if (iUCMCount > 0) {
            for (int i = 0; i < iUCMCount; i++) {
                ucm = ucm + UCMExportExtensionPointHelper.getExportLabels()[i] + "\n  ";
            }
            ucm = ucm.substring(0, ucm.length() - 2);
        } else
            ucm = "  (None currently available)";
        return ucm;
    }

    /**
     * Builds the list of possible formats when exporting the URNspec
     * 
     * @return a label to show the available formats
     */
    private String getUrnExportStrings() {
        String urn = "";
        if (iURNCount > 0) {
            for (int i = 0; i < iURNCount; i++) {
                urn = urn + URNExportExtensionPointHelper.getExportLabels()[i] + "\n  ";
            }
            urn = urn.substring(0, urn.length() - 2);
        } else
            urn = "  (None currently available)";
        return urn;
    }
}