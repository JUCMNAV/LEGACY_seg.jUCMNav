package seg.jUCMNav.views.wizards;

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
import ucm.map.Map;

/**
 * Contains controls to set the export directory, export file type and selected maps.
 * 
 * @author jkealey
 *  
 */
public class ExportImageWizardPage extends WizardPage {

    private Combo cboImageType;

    private int[] iMapSelectionIndices;

    private int iTypeSelectionIndex;

    // widget list
    private List lstMaps;
    private HashMap mapsToEditor;
    private Vector mapsToExport;

    private String sExportPath;
    private Text txtExportPath;

    /**
     * @param pageName
     */
    protected ExportImageWizardPage(String pageName, Vector mapsToExport, HashMap mapsToEditor) {
        super(pageName);
        setDescription(Messages.getString("ExportImageWizardPage.pleaseSelectFormatAndDirectory")); //$NON-NLS-1$
        setTitle(Messages.getString("ExportImageWizardPage.exportImageWizard")); //$NON-NLS-1$

        this.mapsToEditor = mapsToEditor;
        this.mapsToExport = mapsToExport;

        if (mapsToExport.size() == 0) {
            setErrorMessage(Messages.getString("ExportImageWizardPage.noMapsSelected")); //$NON-NLS-1$
            setPageComplete(false);
        }

    }

    /**
     * Contains controls to set the export directory, export file type and selected maps.
     */
    public void createControl(Composite parent) {
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
        txtExportPath.setText(getPath());

        data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        data.horizontalSpan = 3;
        //        data.grabExcessVerticalSpace = true;
        txtExportPath.setLayoutData(data);
        txtExportPath.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                File dir = new File(txtExportPath.getText());
                if (!(dir.exists() && dir.isDirectory())) {
                    setErrorMessage(Messages.getString("ExportImageWizardPage.invalidPath")); //$NON-NLS-1$
                    setPageComplete(false);
                } else {
                    setErrorMessage(null);
                    setPageComplete(true);
                }
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

        //cboImageType.setItems(new String[] { "BMP", "GIF", "JPEG", "PNG", "TIFF" });

        // others not yet implemented.
        // 
        // Example :
        // https://bugs.eclipse.org/bugs/show_bug.cgi?id=24697
        //

        cboImageType.setItems(new String[] { "BMP", "JPEG" }); //$NON-NLS-1$ //$NON-NLS-2$
        cboImageType.select(getImageType());

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
        for (Iterator iter = mapsToExport.iterator(); iter.hasNext();) {
            Map map = (Map) iter.next();
            lstMaps.add(((ExportImageWizard) getWizard()).getMapName(map));
        }
        lstMaps.selectAll();
        lstMaps.addSelectionListener(new SelectionListener() {
            public void widgetDefaultSelected(SelectionEvent e) {
                setPageComplete(lstMaps.getSelectionCount() > 0);
            }

            public void widgetSelected(SelectionEvent e) {
                setPageComplete(lstMaps.getSelectionCount() > 0);
            }
        });

        data = new GridData();
        data.heightHint = 200;
        data.horizontalSpan = 4;
        data.horizontalAlignment = GridData.FILL;
        lstMaps.setLayoutData(data);

        setControl(composite);
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

        setPath(sExportPath);
        setImageType(iTypeSelectionIndex);
        updateMapsToExport();

        return true;
    }

    public int getImageType() {
        return ExportImageWizard.getPreferenceStore().getInt(ExportImageWizard.PREF_IMAGETYPE);
    }

    public String getPath() {
        return ExportImageWizard.getPreferenceStore().getString(ExportImageWizard.PREF_PATH);
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

    public void setImageType(int type) {
        ExportImageWizard.getPreferenceStore().setValue(ExportImageWizard.PREF_IMAGETYPE, type);
    }

    public void setPath(String path) {
        ExportImageWizard.getPreferenceStore().setValue(ExportImageWizard.PREF_PATH, path);
    }

    /**
     * Rebuilds mapToExport according to the current selection.
     *  
     */
    private void updateMapsToExport() {
        Vector toKeep = new Vector();
        for (int i = 0; i < iMapSelectionIndices.length; i++) {
            int index = iMapSelectionIndices[i];
            toKeep.add(mapsToExport.get(i));
        }

        mapsToExport.removeAllElements();
        mapsToExport.addAll(toKeep);

    }

}