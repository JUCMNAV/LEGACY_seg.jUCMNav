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
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.Messages;
import seg.jUCMNav.importexport.UCMExportExtensionPointHelper;
import seg.jUCMNav.importexport.URNExportExtensionPointHelper;
import urncore.IURNDiagram;

/**
 * Contains controls to set the export directory, export file type and selected maps.
 * 
 * @author jkealey
 * 
 */
public class ExportWizardMapSelectionPage extends WizardPage {

    // to be filled with alternatives from the extension points
    private Combo cboImageType;

    // after finish has been set, this array indicates the indexes in listMaps which are selected.
    // required because of threading issues in finish
    private int[] iMapSelectionIndices;

    // which extension is chosen in the dropdown list (file type)
    private int iTypeSelectionIndex;

    // list of maps (if ExportPreferenceHelper.UCM) or list of URNspecs (if ExportPreferenceHelper.URN)
    private List listMaps;

    // given a map, return its UCMNavMultiPageEditor
    private HashMap mapsToEditor;

    // if ExportPreferenceHelper.UCM, list of maps to export. to be filtered by selection in listMaps
    // otherwise, you can infer the list of URN to export from the maps and keeping only the first occurence of each URNSpec
    private Vector mapsToExport;

    // the folder in which to save the files.
    private String sExportPath;

    // the filename for URN export
    private String sFilename;

    // the textbox containing sExportPath
    private Text txtExportPath;
    private Label lblMaps;

    // component for the filename
    private Label lblFilenamePrefix;
    private Text txtFilenamePrefix;
    
    private Composite composite;
    
    private SelectionListener exportTypeSelectionListner;
    

    /**
     * @param pageName
     * @param mapsToExport
     * @param mapsToEditor
     * 
     */
    protected ExportWizardMapSelectionPage(String pageName, Vector mapsToExport, HashMap mapsToEditor,SelectionListener exportTypeSelectionListner) {
        super(pageName);
        setDescription(Messages.getString("ExportImageWizardPage.pleaseSelectFormatAndDirectory")); //$NON-NLS-1$
        setTitle(Messages.getString("ExportImageWizardPage.exportImageWizard")); //$NON-NLS-1$

        this.mapsToEditor = mapsToEditor;
        this.mapsToExport = mapsToExport;
        this.exportTypeSelectionListner = exportTypeSelectionListner;
    }

    /**
     * Contains controls to set the export directory, export file type and selected maps.
     */
    public void createControl(Composite parent) {
        PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, "seg.jUCMNav.export_mapselection"); //$NON-NLS-1$
        SelectionListener selList = new SelectionListener() {
            public void widgetDefaultSelected(SelectionEvent e) {
                verifyPage();
            }

            public void widgetSelected(SelectionEvent e) {
                verifyPage();
            }
        };

        // create the composite to hold the widgets
        composite = new Composite(parent, SWT.NONE);

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

        Label lblType = new Label(composite, SWT.NONE);
        lblType.setText(Messages.getString("ExportImageWizardPage.fileType")); //$NON-NLS-1$
        data = new GridData();
        data.horizontalSpan = 1;
        data.horizontalAlignment = GridData.FILL;
        lblType.setLayoutData(data);

        cboImageType = new Combo(composite, SWT.READ_ONLY);
        
        cboImageType.addSelectionListener(this.exportTypeSelectionListner); 

        data = new GridData();
        data.horizontalSpan = 3;
        data.horizontalAlignment = GridData.FILL;
        cboImageType.setLayoutData(data);

        lblFilenamePrefix = new Label(composite, SWT.NONE);
        setLblFilenamePrefixTextToDefault();
        data = new GridData();
        data.horizontalSpan = 1;
        data.horizontalAlignment = GridData.FILL;
        lblFilenamePrefix.setLayoutData(data);

        txtFilenamePrefix = new Text(composite, SWT.BORDER | SWT.SINGLE | SWT.LEFT);
        txtFilenamePrefix.setText(""); //$NON-NLS-1$
        // txtFilenamePrefix.setText(ExportPreferenceHelper.getPath());

        data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        data.horizontalSpan = 3;
        txtFilenamePrefix.setLayoutData(data);
        txtFilenamePrefix.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                verifyPage();
            }
        });

        lblMaps = new Label(composite, SWT.NONE);
        lblMaps.setText(Messages.getString("ExportImageWizardPage.mapsToBeExported")); //$NON-NLS-1$
        data = new GridData();
        data.horizontalSpan = 4;
        data.horizontalAlignment = GridData.FILL;
        lblMaps.setLayoutData(data);

        listMaps = new List(composite, SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        listMaps.addSelectionListener(selList);
        cboImageType.addSelectionListener(selList);

        data = new GridData();
        data.heightHint = 200;
        data.horizontalSpan = 4;
        data.horizontalAlignment = GridData.FILL;
        listMaps.setLayoutData(data);

        setControl(composite);
        
        refresh();
        verifyPage();
        
        this.exportTypeSelectionListner.widgetSelected(null);
    }

    /**
     * Refresh listMaps to list all Maps (if ExportPreferenceHelper.UCM) or all URNspecs otherwise
     * 
     */
    private void fillSelectionList() {

        listMaps.setItems(new String[] {});
        Vector v = new Vector();
        Vector selected = ((ExportWizard) getWizard()).getSelectedDiagrams();
        Vector vIndices = new Vector();
        int i = 0;
        String filteredName, diagramName;

        for (Iterator iter = mapsToExport.iterator(); iter.hasNext();) {
            IURNDiagram diagram = (IURNDiagram) iter.next();

            if (ExportPreferenceHelper.getExportType() == ExportPreferenceHelper.URN_DIAGRAM) {
                // peek to see if this is one of the maps that should be selected by default
                if (selected.indexOf(diagram) >= 0) {
                    vIndices.add(new Integer(i));
                }

                diagramName = ReportWizard.getDiagramName(diagram);
                if (diagramName.contains("-Map")) //$NON-NLS-1$
                {
                    String[] splitName = diagramName.split("-Map[0-9]*-");//$NON-NLS-1$
                    if (splitName.length > 1)
                    {
                        filteredName = splitName[0] + "-" + splitName[1];  //$NON-NLS-1$
                    }
                    else
                    {
                        // Should not happen, but to be on the same side...
                        filteredName = diagramName;
                    }
                }
                else
                {
                    String[] splitName = diagramName.split("-GRLGraph[0-9]*-");//$NON-NLS-1$
                    if (splitName.length > 1)
                    {
                        filteredName = splitName[0] + "-" + splitName[1];  //$NON-NLS-1$
                    }
                    else
                    {
                        // Should not happen, but to be on the same side...
                        filteredName = diagramName;
                    }
                }                
                listMaps.add(filteredName);
            } else if (!v.contains(diagram.getUrndefinition().getUrnspec())) {
                listMaps.add(ExportWizard.getFilePrefix(diagram));
                v.add(diagram.getUrndefinition().getUrnspec());
            }
            i++;
        }

        // build the int array from the vector of Integers
        int[] indices = new int[vIndices.size()];
        for (i = 0; i < vIndices.size(); i++)
            indices[i] = ((Integer) vIndices.get(i)).intValue();

        if (ExportPreferenceHelper.getExportType() == ExportPreferenceHelper.URN_DIAGRAM && vIndices.size() > 0) {
            listMaps.select(indices);
        } else
            listMaps.selectAll();
    }

    /**
     * Refresh cboImageType to present the choices offered by the extensions.
     * 
     */
    private void fillTypeDropDown() {
        if (ExportPreferenceHelper.getExportType() == ExportPreferenceHelper.URN_DIAGRAM)
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
     * Refresh the page based on the selection from ExportWizard
     * 
     */
    void refresh() {
        if (ExportPreferenceHelper.getExportType() == ExportPreferenceHelper.URN_DIAGRAM) {
            lblMaps.setVisible(true);
            listMaps.setVisible(true);
            fillSelectionList();
            lblFilenamePrefix.setVisible(false);
            txtFilenamePrefix.setVisible(false);
        } else {
            lblMaps.setVisible(false);
            listMaps.setVisible(false);
            lblFilenamePrefix.setVisible(true);
            txtFilenamePrefix.setVisible(true);
            // Vector selected = ((ExportWizard) getWizard()).getSelectedDiagrams();
            // Used any of the selected diagrams because we just need the file prefix
            if (mapsToExport.size() > 0) {
                txtFilenamePrefix.setText(ExportWizard.getFilePrefix((IURNDiagram) mapsToExport.get(0)));
            }
        }
        fillTypeDropDown();
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
        if (ExportPreferenceHelper.getExportType() == ExportPreferenceHelper.URN_DIAGRAM) {
            updateMapsToExport();
        } else {
            ExportPreferenceHelper.setFilenamePrefix(sFilename);
        }

        return true;
    }

    /**
     * Saves the current state of the page so that another thread can run finish() and not worry about thread access exceptions.
     * 
     */
    public void preFinish() {
        sExportPath = txtExportPath.getText();
        sFilename = txtFilenamePrefix.getText();
        iTypeSelectionIndex = cboImageType.getSelectionIndex();
        iMapSelectionIndices = listMaps.getSelectionIndices();
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
            int j = 0, k = 0;
            for (int i = 0; i < mapsToExport.size(); i++) {
                IURNDiagram m = (IURNDiagram) mapsToExport.get(i);
                if (!v.contains(m.getUrndefinition().getUrnspec())) {
                    v.add(m.getUrndefinition().getUrnspec());
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

        if (mapsToExport.size() == 0 && ExportPreferenceHelper.getExportType() == ExportPreferenceHelper.URN_DIAGRAM) {
            setErrorMessage(Messages.getString("ExportImageWizardPage.noMapsSelected")); //$NON-NLS-1$
        } else if (ExportPreferenceHelper.getExportType() == ExportPreferenceHelper.URN && txtFilenamePrefix.getText() == "") { //txtFilenamePrefix is used only in export URN //$NON-NLS-1$
            setErrorMessage(Messages.getString("ExportWizardMapSelectionPage.invalidFilename")); //$NON-NLS-1$
        }

        setPageComplete(getErrorMessage() == null && (listMaps.getSelectionCount() > 0 || ExportPreferenceHelper.getExportType() == ExportPreferenceHelper.URN)
                && cboImageType.getSelectionIndex() >= 0);
    }

    
//*********************************************************
//CustomizedLabel
//*********************************************************/
    public void updateLblFilenamePrefixText(String text){
    	lblFilenamePrefix.setText(text);
    	composite.layout();
    }
    
    public void updateLblFilenamePrefixTextToDefault(){
    	setLblFilenamePrefixTextToDefault();
    	composite.layout();
    }
    
    private void setLblFilenamePrefixTextToDefault(){
    	lblFilenamePrefix.setText(Messages.getString("ExportWizardMapSelectionPage.filenamePrefix")); //$NON-NLS-1$	
    }
    
    public int getTypeSelectionIndex(){
    	return cboImageType.getSelectionIndex();
    }
}