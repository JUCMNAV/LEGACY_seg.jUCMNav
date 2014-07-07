package seg.jUCMNav.views.wizards.importexport;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UrnEditor;
import seg.jUCMNav.editparts.URNRootEditPart;
import seg.jUCMNav.extensionpoints.IURNExportPrePostHooks;
import seg.jUCMNav.extensionpoints.IURNReport;
import seg.jUCMNav.importexport.reports.ReportExtensionPointHelper;
import urncore.IURNDiagram;

/**
 * Assumption: the default editor for .jucm files is UCMNavMultiPageEditor. Otherwise, a bunch of stuff won't work properly.
 * 
 * Will look into its IStructuredSelection and find all applicable maps.
 * 
 * 
 * @author dessure
 * 
 */

public class ReportWizard extends ExportWizard {

    protected static final String PAGE0 = Messages.getString("ReportWizard.reportWizard"); //$NON-NLS-1$
    public static boolean userChoice = true;
    public static boolean fileExists = false;

    /**
     * Add the map selection page
     */
    public void addPages() {
        addPage(new ReportWizardMapSelectionPage(PAGE0, mapsToExport, mapsToEditor));
    }

    /**
     * Saves all images and closes opened editors.
     */
    protected boolean doFinish(final IProgressMonitor monitor) throws Exception {
        boolean b = ((ReportWizardMapSelectionPage) getPage(PAGE0)).finish();
        postHooks = new Vector();

        if (b) {
            // Provide any diagram to the export. Will be used to find the right editor.
			ExportDiagramtoPDF((IURNDiagram) mapsToExport.get(0));
            monitor.worked(1);
        }
        return b;
    }

    /**
     * Exports a diagram to a PDF file. Uses mapsToExport to find the editor and the preference store to build the file name.
     * 
     * @param diagram
     */
    private void ExportDiagramtoPDF(IURNDiagram diagram) throws Exception {
    	
        try {
            
            // given the export type, get the exporter id
            int imgtype = ReportPreferenceHelper.getPreferenceStore().getInt(ReportPreferenceHelper.PREF_REPORTYPE);
            String id = ReportExtensionPointHelper.getExporterFromLabelIndex(imgtype);

            // generate the path.
            Path genericPath = new Path(ReportPreferenceHelper.getPreferenceStore().getString(ReportPreferenceHelper.PREF_PATH));
            genericPath = (Path) genericPath.append("/" + ReportPreferenceHelper.getFilenamePrefix()); //$NON-NLS-1$
            genericPath = (Path) genericPath.addFileExtension(ReportExtensionPointHelper.getFilenameExtension(id));

            // get the simple editor
             UCMNavMultiPageEditor editor = (UCMNavMultiPageEditor) mapsToEditor.get(diagram);

            // get exporter
             IURNReport exporter = ReportExtensionPointHelper.getExporter(id);

             HashMap mapDiagrams = new HashMap();

            for (int i = 0; i < mapsToExport.size(); i++) {
                IURNDiagram diag = (IURNDiagram) mapsToExport.get(i);
                UrnEditor editor2 = ((UrnEditor) mapsToSpecificEditor.get(diag));
                LayeredPane pane = ((URNRootEditPart) (editor2.getGraphicalViewer().getRootEditPart())).getScaledLayers();
                mapDiagrams.put(diag, pane);
            }
                     
           File f;
           
            if (ReportExtensionPointHelper.getFilenameExtension(id).equals("html")){ //$NON-NLS-1$
            	String htmlIndex = ReportPreferenceHelper.getPreferenceStore().getString(ReportPreferenceHelper.PREF_PATH) + "/index.html"; //$NON-NLS-1$
            	System.out.println(htmlIndex);
            	f = new File(htmlIndex);
            }else{
            	
            	f = new File(genericPath.toString());
            }                      
            
            //if file already exists, launch warning 
            if(f.exists()){
            	fileExists = true;
            	Display.getDefault().syncExec(new Runnable() {public void run() {
            	userChoice = MessageDialog.openQuestion(getShell(), Messages.getString("ReportWizard.OverwriteFile"), Messages.getString("ReportWizard.FileAlreadyExists")); //$NON-NLS-1$ //$NON-NLS-2$
            	}
            	});
            	
            }else{
            	fileExists = false;
            }
            
            if(!(fileExists) || userChoice){
				exporter.export(editor.getModel(), mapDiagrams, genericPath.toOSString());
            }

        } catch (Exception e) {
            IStatus error = new Status(IStatus.ERROR, JUCMNavPlugin.PLUGIN_ID, 1, e.toString(), e);
            System.out.println( getStackTrace(e) );
            ErrorDialog.openError(getShell(), Messages.getString("ExportWizard.ErrorOccurred"), e.getMessage(), error); //$NON-NLS-1$
            return;
            
        }

       
    }

    
    public static String getStackTrace(Throwable throwable) {
    	  Writer writer = new StringWriter();
    	  PrintWriter printWriter = new PrintWriter(writer);
    	  throwable.printStackTrace(printWriter);
    	  return writer.toString();
    }
    
    /**
     * This method is called when 'Finish' button is pressed in the wizard. We will create an operation and run it using wizard as execution context.
     */
    public boolean performFinish() {
        IRunnableWithProgress op = new IRunnableWithProgress() {
            public void run(IProgressMonitor monitor) throws InvocationTargetException {
            	           	
                try {
                    monitor.beginTask(Messages.getString("ReportWizard.Exporting"), IProgressMonitor.UNKNOWN); //$NON-NLS-1$   
                    
                    doFinish(monitor);
                } catch (Exception ex) {
                    if (ex instanceof InvocationTargetException)
                        throw (InvocationTargetException) ex;
                    else
                        throw new InvocationTargetException(ex);
                } finally {
                    monitor.done();
                }
            	
            }

        }; 
     
        try {
            ((ReportWizardMapSelectionPage) getPage(PAGE0)).preFinish();
            getContainer().run(true, false, op);
            for (Iterator iter = postHooks.iterator(); iter.hasNext();) {
                IURNExportPrePostHooks hook = (IURNExportPrePostHooks) iter.next();
                hook.postHook(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage());

            }
            super.closedOpenedEditors();

        } catch (InterruptedException e) {
            return false;
        } catch (InvocationTargetException e) {
            Throwable realException = e.getTargetException();
            MessageDialog.openError(getShell(), Messages.getString("ExportImageWizard.error"), realException.getMessage()); //$NON-NLS-1$
            return false;
        }
        
        
        
        if (fileExists && !userChoice){
        	return false; //user cancelled overwriting existing files      	
        }else{
        	return true;
        }
    }


}