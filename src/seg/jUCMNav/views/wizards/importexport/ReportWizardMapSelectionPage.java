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
import seg.jUCMNav.importexport.reports.ReportExtensionPointHelper;
import seg.jUCMNav.views.preferences.ReportGeneratorPreferences;
import urncore.IURNDiagram;

/**
 * Contains controls to set the Report directory, Report file type and selected maps.
 * 
 * @author dessure
 * 
 */

public class ReportWizardMapSelectionPage extends WizardPage {

    // to be filled with alternatives from the extension points
    private Combo cboReportType;

    // after finish has been set, this array indicates the indexes in listMaps which are selected.
    // required because of threading issues in finish
    private int[] iMapSelectionIndices;

    // which extension is chosen in the drop down list (file type)
    private int iTypeSelectionIndex;

    // list of maps
    private List listMaps;

    // given a map, return its UCMNavMultiPageEditor
    private HashMap mapsToEditor;

    // if ReportPreferenceHelper.UCM, list of maps to Report. to be filtered by selection in listMaps
    // otherwise, you can infer the list of URN to Report from the maps and keeping only the first occurrence of each URNSpec
    private Vector mapsToExport;

    // the folder in which to save the files.
    private String sReportPath;

    // the filename for URN Report
    private String sFilename;

    // the text box containing sReportPath
    private Text txtReportPath;
    private Label lblMaps;

    // component for the filename
    private Label lblFilenamePrefix;
    private Text txtFilenamePrefix;

    boolean htmlSelected = false;

    /**
     * @param pageName
     * @param mapsToExport
     * @param mapsToEditor
     * 
     */
    protected ReportWizardMapSelectionPage(String pageName, Vector mapsToExport, HashMap mapsToEditor) {
        super(pageName);
        setDescription(Messages.getString("ReportWizardPage.pleaseSelectFormatAndDirectory")); //$NON-NLS-1$
        setTitle(Messages.getString("ReportWizardPage.reportWizard")); //$NON-NLS-1$

        this.mapsToEditor = mapsToEditor;
        this.mapsToExport = mapsToExport;

        filterMapsToExport();
    }

    /*
     * fetches the UCMSHOWUCMDIAGRAMS and the GRLSHOWGRLDIAGRAMS preferences, as well as the scenario and GRL 
     * evaluation preferences, in order to filter the diagrams that are part of the current URN model
     */
    private void filterMapsToExport()
    {
    	String filteredName, diagramName;
    	Vector temp = new Vector();
        int i = 0;
        for (Iterator iter = mapsToExport.iterator(); iter.hasNext();) {
            IURNDiagram diagram = (IURNDiagram) iter.next();

            diagramName = ReportWizard.getDiagramName(diagram);
            // if the diagram is a use case map and the UCMSHOWUCMDIAGRAMS preference (or one of the scenario 
            // preferences) is set to true, add this diagram to the temp vector. Otherwise if the diagram is a 
            // GRL diagram and the GRLSHOWGRLDIAGRAMS preference (or the ShowEvals preference) is set to true, 
            // add this diagram to the temp vector.
            if ((diagramName.contains("-Map")) && (ReportGeneratorPreferences.getUCMSHOWUCMDIAGRAMS() || ReportGeneratorPreferences.getUCMSHOWSCENARIOINFO() || ReportGeneratorPreferences.getUCMSHOWSCENARIOEXEC())) { //$NON-NLS-1$
                temp.add(this.mapsToExport.elementAt(i));
            }
            else if ((diagramName.contains("-GRLGraph")) && (ReportGeneratorPreferences.getGRLSHOWGRLDIAGRAMS() || ReportGeneratorPreferences.getShowGRLShowEvals())) { //$NON-NLS-1$
            	temp.add(this.mapsToExport.elementAt(i));
            }

            i++;
        }
        // remove all diagrams from the mapsToExport vector, and add all diagrams contained within
        // the temp vector to the mapsToExport vector.
        this.mapsToExport.removeAllElements();
        this.mapsToExport.addAll(temp);
    }
    /**
     * Contains controls to set the report directory, report file type and selected maps.
     */
    public void createControl(Composite parent) {
        PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, "seg.jUCMNav.import_mapselection"); //$NON-NLS-1$
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
        lblPath.setText(Messages.getString("ReportWizardPage.reportDirectory")); //$NON-NLS-1$
        data = new GridData();
        data.horizontalSpan = 4;
        lblPath.setLayoutData(data);

        txtReportPath = new Text(composite, SWT.BORDER | SWT.SINGLE | SWT.LEFT);
        txtReportPath.setText(ReportPreferenceHelper.getPath());

        data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        data.horizontalSpan = 3;
        // data.grabExcessVerticalSpace = true;
        txtReportPath.setLayoutData(data);
        txtReportPath.addModifyListener(new ModifyListener() {
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
                dialog.setFilterPath(txtReportPath.getText());

                dialog.setText(Messages.getString("ReportWizardPage.reportDirectory")); //$NON-NLS-1$
                String path = dialog.open();

                if (path != null) {
                    txtReportPath.setText(path);
                }

            }
        });

        Label lblType = new Label(composite, SWT.NONE);
        lblType.setText(Messages.getString("ReportWizardPage.reportType")); //$NON-NLS-1$
        data = new GridData();
        data.horizontalSpan = 1;
        data.horizontalAlignment = GridData.FILL;
        lblType.setLayoutData(data);

        cboReportType = new Combo(composite, SWT.READ_ONLY);

        data = new GridData();
        data.horizontalSpan = 3;
        data.horizontalAlignment = GridData.FILL;
        cboReportType.setLayoutData(data);

        lblFilenamePrefix = new Label(composite, SWT.NONE);
        lblFilenamePrefix.setText(Messages.getString("ReportWizardMapSelectionPage.reportPrefix")); //$NON-NLS-1$
        data = new GridData();
        data.horizontalSpan = 1;
        data.horizontalAlignment = GridData.FILL;
        lblFilenamePrefix.setLayoutData(data);

        txtFilenamePrefix = new Text(composite, SWT.BORDER | SWT.SINGLE | SWT.LEFT);
        txtFilenamePrefix.setText(ReportPreferenceHelper.getFilenamePrefix()); //$NON-NLS-1$
        // txtFilenamePrefix.setText(ReportPreferenceHelper.getPath());

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
        lblMaps.setText(Messages.getString("ReportWizardPage.mapsToBeReported")); //$NON-NLS-1$
        data = new GridData();
        data.horizontalSpan = 4;
        data.horizontalAlignment = GridData.FILL;
        lblMaps.setLayoutData(data);

        listMaps = new List(composite, SWT.MULTI | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        listMaps.addSelectionListener(selList);
        cboReportType.addSelectionListener(selList);

        data = new GridData();
        data.heightHint = 200;
        data.horizontalSpan = 4;
        data.horizontalAlignment = GridData.FILL;
        listMaps.setLayoutData(data);

        setControl(composite);

        refresh();
        verifyPage();
    }

    /**
     * Refresh listMaps to list all Maps (if ReportPreferenceHelper.UCM) or all URNspecs otherwise
     * 
     */
    private void fillSelectionList() {

        listMaps.setItems(new String[] {});
        Vector v = new Vector();
        Vector selected = ((ReportWizard) getWizard()).getSelectedDiagrams();
        Vector vIndices = new Vector();
        String filteredName, diagramName;

        int i = 0;
        for (Iterator iter = mapsToExport.iterator(); iter.hasNext();) {
            IURNDiagram diagram = (IURNDiagram) iter.next();

            // peek to see if this is one of the maps that should be selected by default
            if (selected.indexOf(diagram) >= 0) {
                vIndices.add(new Integer(i));
            }

            diagramName = ReportWizard.getDiagramName(diagram);
            if (diagramName.contains("-Map")){ //$NON-NLS-1$
                filteredName = diagramName.substring(0, diagramName.indexOf("-Map") + 1) //$NON-NLS-1$
                        + diagramName.substring(diagramName.lastIndexOf('-') + 1, diagramName.length());
            }
            else { 
                filteredName = diagramName.substring(0, diagramName.indexOf("-GRLGraph") + 1) //$NON-NLS-1$
                        + diagramName.substring(diagramName.lastIndexOf('-') + 1, diagramName.length());
            }
            listMaps.add(filteredName);

            i++;
        }

        // build the int array from the vector of Integers
        int[] indices = new int[vIndices.size()];
        for (i = 0; i < vIndices.size(); i++)
            indices[i] = ((Integer) vIndices.get(i)).intValue();

        if (vIndices.size() > 0) {
            listMaps.select(indices);
        } else
            listMaps.selectAll();
    }

    /**
     * Refresh cboReportType to present the choices offered by the extensions.
     * 
     */
    private void fillTypeDropDown() {
        cboReportType.setItems(ReportExtensionPointHelper.getExportLabels());
        cboReportType.select(0); // Select PDF by default
    }

    /**
     * Refresh the page
     * 
     */
    void refresh() {

        lblMaps.setVisible(true);
        listMaps.setVisible(true);
        fillSelectionList();
        lblFilenamePrefix.setVisible(true);
        txtFilenamePrefix.setVisible(true);
        if (mapsToExport.size() > 0)
            txtFilenamePrefix.setText(ExportWizard.getFilePrefix((IURNDiagram) mapsToExport.get(0)));
        fillTypeDropDown();

    }

    /**
     * Updates passed Vector and preference store with the selection properties
     * 
     * @return success
     */
    public boolean finish() {
        File dir = new File(sReportPath);
        if (!(dir.exists() && dir.isDirectory())) {
            setErrorMessage(Messages.getString("ReportWizardPage.invalidPath")); //$NON-NLS-1$
            return false;
        }

        ReportPreferenceHelper.setPath(sReportPath);
        ReportPreferenceHelper.setReportType(iTypeSelectionIndex);
        ReportPreferenceHelper.setFilenamePrefix(sFilename);

        // if HTML report type is selected, bypass map selection filtering by exporting all maps, or all GRL/UCM diagrams
        // (depending on the report generation preferences).
        if (!htmlSelected) {
            updatemapsToExport();
        }

        return true;
    }

    /**
     * Saves the current state of the page so that another thread can run finish() and not worry about thread access exceptions.
     * 
     */
    public void preFinish() {
        sReportPath = txtReportPath.getText();
        sFilename = txtFilenamePrefix.getText();
        iTypeSelectionIndex = cboReportType.getSelectionIndex();
        htmlSelected = cboReportType.getText().contains("HTML") ? true : false; //$NON-NLS-1$
        iMapSelectionIndices = listMaps.getSelectionIndices();
    }

    /**
     * Rebuilds mapToReport according to the current selection.
     * 
     */
    private void updatemapsToExport() {

        Vector toKeep = new Vector();

        // direct mapping
        for (int i = 0; i < iMapSelectionIndices.length; i++) {
            int index = iMapSelectionIndices[i];
            toKeep.add(mapsToExport.get(index));
        }

        mapsToExport.removeAllElements();
        mapsToExport.addAll(toKeep);

    }

    /**
     * Verify if we can proceed with finish.
     * 
     */
    private void verifyPage() {
        File dir = new File(txtReportPath.getText());
        if (!(dir.exists() && dir.isDirectory())) {
            setErrorMessage(Messages.getString("ReportWizardPage.invalidPath")); //$NON-NLS-1$
        } else {
            setErrorMessage(null);
        }

        // disable the Report prefix feature and select all available maps if the report type
        // selected is HTML.
        if (cboReportType.getText().contains("HTML")) {	//$NON-NLS-1$
        	if (txtFilenamePrefix.getText() == "") {
        		txtFilenamePrefix.setText(ReportPreferenceHelper.getFilenamePrefix());
        	}
        	lblFilenamePrefix.setEnabled(false);
        	txtFilenamePrefix.setEnabled(false);
        	listMaps.selectAll();  	
        } else {
        	lblFilenamePrefix.setEnabled(true);
        	txtFilenamePrefix.setEnabled(true);
        	if (mapsToExport.size() == 0) {
        		setErrorMessage(Messages.getString("ReportWizardPage.noMapsSelected")); //$NON-NLS-1$
        	}
        }
        
        if (txtFilenamePrefix.getText() == "") { //$NON-NLS-1$
            setErrorMessage(Messages.getString("ReportWizardMapSelectionPage.invalidFilename")); //$NON-NLS-1$
        }
        
        setPageComplete(getErrorMessage() == null && (listMaps.getSelectionCount() > 0) && cboReportType.getSelectionIndex() >= 0);
    }

}