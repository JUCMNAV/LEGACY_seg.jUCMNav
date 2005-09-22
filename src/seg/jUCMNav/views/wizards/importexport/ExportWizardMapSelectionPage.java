package seg.jUCMNav.views.wizards.importexport;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

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
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;

import seg.jUCMNav.Messages;
import seg.jUCMNav.importexport.UCMExportExtensionPointHelper;
import seg.jUCMNav.importexport.URNExportExtensionPointHelper;
import ucm.map.Map;

/**
 * Contains controls to set the export directory, export file type and selected maps.
 * 
 * @author jkealey
 * 
 */
public class ExportWizardMapSelectionPage extends WizardPage {

    // to be filled with alternatives from the extension points
    private Combo cboImageType;

    // after finish has been set, this array indicates the indexes in lstMaps which are selected.
    // required because of threading issues in finish
    private int[] iMapSelectionIndices;

    // which extension is chosen in the dropdown list (file type)
    private int iTypeSelectionIndex;

    // list of maps (if ExportPreferenceHelper.UCM) or list of URNspecs (if ExportPreferenceHelper.URN)
    private List lstMaps;

    // given a map, return its UCMNavMultiPageEditor
    private HashMap mapsToEditor;

    // if ExportPreferenceHelper.UCM, list of maps to export. to be filtered by selection in lstMaps
    // otherwise, you can infer the list of URN to export from the maps and keeping only the first occurence of each URNSpec
    private Vector mapsToExport;

    // the folder in which to save the files.
    private String sExportPath;

    // the textbox containing sExportPath
    private Text txtExportPath;

    /**
     * @param pageName
     * @param mapsToExport
     * @param mapsToEditor
     * 
     */
    protected ExportWizardMapSelectionPage(String pageName, Vector mapsToExport, HashMap mapsToEditor) {
        super(pageName);
        setDescription(Messages.getString("ExportImageWizardPage.pleaseSelectFormatAndDirectory")); //$NON-NLS-1$
        setTitle(Messages.getString("ExportImageWizardPage.exportImageWizard")); //$NON-NLS-1$

        this.mapsToEditor = mapsToEditor;
        this.mapsToExport = mapsToExport;

    }

    /**
     * Contains controls to set the export directory, export file type and selected maps.
     */
    public void createControl(Composite parent) {
        SelectionListener selList = new SelectionListener() {
            public void widgetDefaultSelected(SelectionEvent e) {
                verifyPage();
            }

            public void widgetSelected(SelectionEvent e) {
                verifyPage();
            }
        };

        // create the composite to hold the widgets
        Composite composite = new Composite(parent, SWT.NONE);

        // create the desired layout for this wizard page
        GridLayout gl = new GridLayout(4, false);
        composite.setLayout(gl);
        GridData data;

        Label lblPath = new Label(composite, SWT.NONE);
        lblPath.setText(Messages.getString("ExportImageWizardPage.directoryToExport")); //$NON-NLS-1$
        data = new GridData();
        data.horizontalSpan = 4;
        lblPath.setLayoutData(data);

        txtExportPath = new Text(composite, SWT.BORDER | SWT.SINGLE | SWT.LEFT);
        txtExportPath.setText(ExportPreferenceHelper.getPath());

        data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        data.horizontalSpan = 3;
        // data.grabExcessVerticalSpace = true;
        txtExportPath.setLayoutData(data);
        txtExportPath.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                verifyPage();
            }
        });

        Button b = new Button(getShell(), SWT.PUSH);
        b.setParent(composite);
        b.setText("..."); //$NON-NLS-1$
        b.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                DirectoryDialog dialog = new DirectoryDialog(getShell(), SWT.OPEN);
                dialog.setFilterPath(txtExportPath.getText());

                dialog.setText(Messages.getString("ExportImageWizardPage.pleaseSelectDirectory")); //$NON-NLS-1$
                String path = dialog.open();

                if (path != null) {
                    txtExportPath.setText(path);
                }

            }
        });

        Label lblOrientation = new Label(composite, SWT.NONE);
        lblOrientation.setText(Messages.getString("ExportImageWizardPage.fileType")); //$NON-NLS-1$
        data = new GridData();
        data.horizontalSpan = 1;
        data.horizontalAlignment = GridData.FILL;
        lblOrientation.setLayoutData(data);

        cboImageType = new Combo(composite, SWT.READ_ONLY);

        data = new GridData();
        data.horizontalSpan = 3;
        data.horizontalAlignment = GridData.FILL;
        cboImageType.setLayoutData(data);

        Label lblMaps = new Label(composite, SWT.NONE);
        lblMaps.setText(Messages.getString("ExportImageWizardPage.mapsToBeExported")); //$NON-NLS-1$
        data = new GridData();
        data.horizontalSpan = 4;
        data.horizontalAlignment = GridData.FILL;
        lblMaps.setLayoutData(data);

        lstMaps = new List(composite, SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        lstMaps.addSelectionListener(selList);
        cboImageType.addSelectionListener(selList);

        data = new GridData();
        data.heightHint = 200;
        data.horizontalSpan = 4;
        data.horizontalAlignment = GridData.FILL;
        lstMaps.setLayoutData(data);

        setControl(composite);

        fillSelectionList();
        fillTypeDropDown();
        verifyPage();
    }

    /**
     * Refresh lstMaps to list all Maps (if ExportPreferenceHelper.UCM) or all URNspecs otherwise
     * 
     */
    void fillSelectionList() {

        lstMaps.setItems(new String[] {});
        Vector v = new Vector();

        for (Iterator iter = mapsToExport.iterator(); iter.hasNext();) {
            Map map = (Map) iter.next();

            if (ExportPreferenceHelper.getExportType() == ExportPreferenceHelper.UCM)
                lstMaps.add(((ExportWizard) getWizard()).getMapName(map));
            else if (!v.contains(map.getUcmspec().getUrnspec())) {
                lstMaps.add(((ExportWizard) getWizard()).getFilePrefix(map));
                v.add(map.getUcmspec().getUrnspec());
            }
        }
        lstMaps.selectAll();
    }

    /**
     * Refresh cboImageType to present the choices offered by the extensions.
     * 
     */
    void fillTypeDropDown() {
        if (ExportPreferenceHelper.getExportType() == ExportPreferenceHelper.UCM)
            cboImageType.setItems(UCMExportExtensionPointHelper.getExportLabels());
        else
            cboImageType.setItems(URNExportExtensionPointHelper.getExportLabels());

        if (cboImageType.getItemCount() > ExportPreferenceHelper.getImageType()) {
            cboImageType.select(ExportPreferenceHelper.getImageType());
            setPageComplete(true);
        } else if (cboImageType.getItemCount() != 0) {
            cboImageType.select(0);
        }
    }

    /**
     * Updates passed Vector and preference store with the selection properties
     * 
     * @return success
     */
    public boolean finish() {
        File dir = new File(sExportPath);
        if (!(dir.exists() && dir.isDirectory())) {
            setErrorMessage(Messages.getString("ExportImageWizardPage.invalidPath")); //$NON-NLS-1$
            return false;
        }

        ExportPreferenceHelper.setPath(sExportPath);
        ExportPreferenceHelper.setImageType(iTypeSelectionIndex);
        updateMapsToExport();

        return true;
    }

    /**
     * Saves the current state of the page so that another thread can run finish() and not worry about thread access exceptions.
     * 
     */
    public void preFinish() {
        sExportPath = txtExportPath.getText();
        iTypeSelectionIndex = cboImageType.getSelectionIndex();
        iMapSelectionIndices = lstMaps.getSelectionIndices();
    }

    /**
     * Rebuilds mapToExport according to the current selection.
     * 
     */
    private void updateMapsToExport() {
        Vector toKeep = new Vector();

        if (ExportPreferenceHelper.getExportType() == ExportPreferenceHelper.URN) {
            // since our indexes don't directly map, our job is a bit complicated
            // a selected index references to the first occurence of a URNspec
            // in the list of maps to export.
            Vector v = new Vector();
            int j = 0,k=0;
            for (int i = 0; i < mapsToExport.size(); i++) {
                Map m = (Map) mapsToExport.get(i);
                if (!v.contains(m.getUcmspec().getUrnspec())) {
                    v.add(m.getUcmspec().getUrnspec());
                    if (iMapSelectionIndices[j] == k) {
                        toKeep.add(m);
                        j++;
                    }
                    k++;
                }
            }
        } else {
            // direct mapping
            for (int i = 0; i < iMapSelectionIndices.length; i++) {
                int index = iMapSelectionIndices[i];
                toKeep.add(mapsToExport.get(index));
            }
        }

        mapsToExport.removeAllElements();
        mapsToExport.addAll(toKeep);

    }

    /**
     * Verify if we can proceed with finish.
     * 
     */
    private void verifyPage() {
        File dir = new File(txtExportPath.getText());
        if (!(dir.exists() && dir.isDirectory())) {
            setErrorMessage(Messages.getString("ExportImageWizardPage.invalidPath")); //$NON-NLS-1$
        } else {
            setErrorMessage(null);
        }

        if (mapsToExport.size() == 0) {
            setErrorMessage(Messages.getString("ExportImageWizardPage.noMapsSelected")); //$NON-NLS-1$
        }

        setPageComplete(getErrorMessage() == null && lstMaps.getSelectionCount() > 0 && cboImageType.getSelectionIndex() >= 0);
    }

}