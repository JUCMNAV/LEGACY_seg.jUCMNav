package seg.jUCMNav.editors.resourceManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.part.FileEditorInput;
import org.xml.sax.SAXParseException;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.ModelCreationFactory;
import urn.URNspec;
import urncore.Concern;
import ca.mcgill.sel.core.COREConcern;
import ca.mcgill.sel.core.CoreFactory;

/**
 * Originally included in the UCMNavMultiPageEditor, this code was factored out.
 * 
 * UCMNavMultiPageEditor delegates file management operations to this class. Saving / creating is handled here.
 * 
 * @author jkealey
 * 
 */
public class MultiPageFileManager {
    /** the managed editor */
    private UCMNavMultiPageEditor editor;

    /** the model manager handles serialization */
    private UrnModelManager modelManager = new UrnModelManager();
    
    /** handles serialization of COREConcern */
    private COREmodelManager coreModelManager = null;   
    private boolean coreFileToSave;

    /**
     * 
     * @param editor
     *            jUCMNav
     */
    public MultiPageFileManager(UCMNavMultiPageEditor editor) {
        this.editor = editor;
        coreFileToSave = false;
    }

    /**
     * Returns the URNspec object from the specified file.
     * 
     * @param file
     *            the file to create
     * @return the urn object from the specified file
     */
    public URNspec create(IFile file) throws CoreException {
        URNspec urn = null;

        if (file.exists()) {
            urn = create(file.getFullPath());
        }
        
        // load .core file
        loadCORE(file, urn);
        
        return urn;
    }

    /**
     * Returns the URNspec object from the specified file.
     * 
     * @param path
     *            the file to create
     * @return the urn object from the specified file
     */
    public URNspec create(IPath path) throws CoreException {
        URNspec urn = null;
        modelManager = new UrnModelManager();
        
        // if (path.exists()) {
        try {
            modelManager.load(path);
        } catch (Exception e) {// SAXParseException
            if (!(e instanceof WrappedException) || !(((WrappedException) e).exception() instanceof SAXParseException)
                    || ((SAXParseException) ((WrappedException) e).exception()).getLineNumber() >= 0) {
                // don't pop error if file is empty (not created by wizard).
                ErrorDialog
                        .openError(
                                getEditor().getSite().getShell(),
                                Messages.getString("MultiPageFileManager.errorLoadingUCM"), //$NON-NLS-1$
                                Messages.getString("MultiPageFileManager.errorOpeningFileCreatingNew") + e.getMessage(), new Status(IStatus.ERROR, "seg.jUCMNav", IStatus.ERROR, "", e)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

            }
            modelManager.createURNspec(path);
        }

        urn = modelManager.getModel();
   
        if (null == urn) {
            throw new CoreException(new Status(IStatus.ERROR, JUCMNavPlugin.PLUGIN_ID, 0, Messages.getString("MultiPageFileManager.errorLoadingUCM"), null)); //$NON-NLS-1$
        }

        // }

        return urn;
    }

    /**
     * Perform a save on the file we are editing.
     * 
     * @see org.eclipse.ui.ISaveablePart#doSave(org.eclipse.core.runtime.IProgressMonitor)
     */
	@SuppressWarnings("unchecked")
	public void doSave(IProgressMonitor monitor) {
        try {
            IFile file = ((IFileEditorInput) getEditor().getEditorInput()).getFile();
            if (file.exists()
                    || MessageDialog.openConfirm(getEditor().getSite().getShell(),
                            Messages.getString("MultiPageFileManager.createFile"), Messages.getString("MultiPageFileManager.theFile") + file.getName() //$NON-NLS-1$ //$NON-NLS-2$
                                    + Messages.getString("MultiPageFileManager.doesntExistClickOk"))) { //$NON-NLS-1$
                String sDate;
                DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.US);
                sDate = df.format(new Date());
                editor.getModel().setModified(sDate);
                setVersions();            
                
                // create .core file
                boolean coreFileAlreadyCreated = false;             
                coreFileAlreadyCreated = createCORE(file, coreFileAlreadyCreated);

                
                // MetadataHelper.cleanRunTimeMetadata(getEditor().getModel()); *** does not work here. Refresh/adds metadata while deleting --> infinite loop!
                save(file, monitor);
                
                // save .core file
                saveCORE(coreFileAlreadyCreated);
                
                getEditor().getMultiPageCommandStackListener().markSaveLocations();
 
            }
        } catch (CoreException e) {
            ErrorDialog.openError(getEditor().getSite().getShell(),
                    Messages.getString("MultiPageFileManager.errorDuringSave"), Messages.getString("MultiPageFileManager.ucmCouldNotBeSaved"), e.getStatus()); //$NON-NLS-1$ //$NON-NLS-2$
            assert false : "error occured while saving; "; //$NON-NLS-1$
        }
    }

    /**
     * Perform a save as action on the file we are editing.
     * 
     * @see org.eclipse.ui.ISaveablePart#doSaveAs()
     */
    public void doSaveAs() {
        // get the new path
        SaveAsDialog dialog = new SaveAsDialog(getEditor().getSite().getShell());
        dialog.setOriginalFile(((IFileEditorInput) getEditor().getEditorInput()).getFile());
        dialog.open();
        IPath path = dialog.getResult();

        // if the user presses cancel or refuses to overwrite an existing file, null will be returned.
        if (path == null)
            return;
        else if (((IFileEditorInput) getEditor().getEditorInput()).getFile().getFullPath().equals(path)) {
            doSave(new NullProgressMonitor());
            return;
        }

        try {
            // because our modelmanager is tightly coupled with the resource.
            modelManager = new UrnModelManager();
            
            IWorkspace workspace = ResourcesPlugin.getWorkspace();
            IFile file = workspace.getRoot().getFile(path);

            // we're overwriting the file so get rid of it
            if (file.exists()) {
                file.delete(true, false, new NullProgressMonitor());
            }

            // creates a non existing resource and assigns it our model
            modelManager.createURNspec(path, getEditor().getModel());
            
            // set its data.
            String sDate;
            DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.US);
            sDate = df.format(new Date());
            editor.getModel().setCreated(sDate);
            editor.getModel().setModified(sDate);

            setVersions();
            // MetadataHelper.cleanRunTimeMetadata(getEditor().getModel()); *** does not work here. Refresh/adds metadata while deleting --> infinite loop!

            boolean coreFileAlreadyCreated = false;             
            coreFileAlreadyCreated = createCORE(file, coreFileAlreadyCreated);
            
            // save the new file
            modelManager.save(path);
            
            // save .core file
            saveCORE(coreFileAlreadyCreated);
            
            // we used to reinit everything without closing the editor but this caused bugs that appeared out of nowhere and made the whole codebase weaker.
            // therefore, we're closing the editor and reopening it.
            getEditor().closeEditor(false);
            IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
            IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(file.getName());
            editor = (UCMNavMultiPageEditor) page.openEditor(new FileEditorInput(file), desc.getId());

        } catch (Exception e) {
            ErrorDialog
                    .openError(
                            getEditor().getSite().getShell(),

                            Messages.getString("MultiPageFileManager.errorDuringSave"), Messages.getString("MultiPageFileManager.ucmCouldNotBeSaved"), new Status(IStatus.ERROR, //$NON-NLS-1$ //$NON-NLS-2$
                                    "seg.jUCMNav", IStatus.ERROR, "", e)); //$NON-NLS-1$ //$NON-NLS-2$
        }

    }

    /**
     * 
     * @return the editor that is worked on.
     */
    private UCMNavMultiPageEditor getEditor() {
        return editor;
    }

    /**
     * Sets the current URN/UCM versions to saved files.
     */
    private void setVersions() {
        String ver = editor.getModel().getSpecVersion();
        try {
            ver = Integer.toString((Integer.parseInt(ver) + 1));
        } catch (Exception ex) {
            ver = "1"; //$NON-NLS-1$
        }
        editor.getModel().setSpecVersion(ver);
        editor.getModel().setUrnVersion(ModelCreationFactory.URNSPEC_VERSION);
    }

    /**
     * Saves the ucm under the specified file.
     * 
     * @param file
     *            is the file where we want to save this UCM.
     * @param progressMonitor
     *            is the object used to show the progress of the save.
     * @throws CoreException
     */
    private void save(IFile file, IProgressMonitor progressMonitor) throws CoreException {

        if (null == progressMonitor)
            progressMonitor = new NullProgressMonitor();

        progressMonitor.beginTask(Messages.getString("MultiPageFileManager.saving") + file, 2); //$NON-NLS-1$
        
        if (null == modelManager) {
            IStatus status = new Status(IStatus.ERROR, JUCMNavPlugin.PLUGIN_ID, 0, Messages.getString("MultiPageFileManager.noModelManagerFound"), null); //$NON-NLS-1$
            throw new CoreException(status);
        }
       

        // save URNspec to file
        try {
            modelManager.save(file.getFullPath());
            progressMonitor.worked(1);
            file.refreshLocal(IResource.DEPTH_ZERO, new SubProgressMonitor(progressMonitor, 1));
        } catch (FileNotFoundException e) {
            IStatus status = new Status(IStatus.ERROR, JUCMNavPlugin.PLUGIN_ID, 0, Messages.getString("MultiPageFileManager.errorWritingFile"), e); //$NON-NLS-1$
            throw new CoreException(status);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            IStatus status = new Status(IStatus.ERROR, JUCMNavPlugin.PLUGIN_ID, 0, Messages.getString("MultiPageFileManager.errorWritingFile"), e); //$NON-NLS-1$
            throw new CoreException(status);
        } finally {
            progressMonitor.done();
        }

    }

    public void resetFile(IFile file, URNspec spec) {
        modelManager.createURNspec(file.getFullPath().toFile(), spec);
    }
    
	/**
	 * Saves .core file.
	 * 
	 * @param coreFileAlreadyCreated
	 * 		true if a .core file already exists, false otherwise
	 */
	public void saveCORE(boolean coreFileAlreadyCreated) {
		if(coreFileToSave){
		    try{
		    		coreModelManager.updateCoreConcern(editor.getModel());
		    		if( !coreFileAlreadyCreated )
		    			coreModelManager.createCoreConcern(coreModelManager.getCoreFile(), coreModelManager.getCOREConcern());
		    		coreModelManager.save(coreModelManager.getCoreFile());

				
		    }catch(Exception e){
		    	e.printStackTrace();
		    }
		}
	}
	
	/**
	 * Creates a new .core file if no .core file 
	 * is associated with the current .jucm file, otherwise
	 * simply stores the .core file in memory.
	 * 
	 * @param file
	 * 		.jucm file
	 * @param coreFileAlreadyCreated
	 * 		true if a .core file exists for <b>file<\b>, false otherwise
	 * 
	 * @return	coreFileAlreadyCreated
	 */
	public boolean createCORE(IFile file, boolean coreFileAlreadyCreated) {
		// Does the urnspec have at least one Concern?
		if ( editor.getModel().getUrndef().getConcerns() != null && editor.getModel().getUrndef().getConcerns().size() != 0){	// YES								// YES
				
				Concern concern = (Concern)editor.getModel().getUrndef().getConcerns().get(0);
				COREConcern coreConcern = null;
				
				// Does this concern has a COREConcern assigned?
				if( concern.getCoreConcern() != null )
					coreConcern = concern.getCoreConcern();
				else																			
					coreConcern = CoreFactory.eINSTANCE.createCOREConcern();
				
				IPath coreConcernPath = file.getLocation().removeLastSegments(1);
				coreConcernPath = coreConcernPath.append("/" + 
						file.getName().substring(0, file.getName().lastIndexOf(".")) + ".core");
				
				// If there's no .core file present assigned to this concern
				// create a new coreModelManager and assign it to this concern
				if( coreModelManager == null){
					coreModelManager = new COREmodelManager(concern);
					coreModelManager.setCoreFile(coreConcernPath.toFile());
					coreModelManager.setCOREConcern(coreConcern);
		    		coreModelManager.getCOREConcern().setName(concern.getName());
		    		coreModelManager.createCoreConcern(coreModelManager.getCoreFile());
				}else{ 
		    		coreFileAlreadyCreated = true;
		    		coreModelManager.setConcern(concern);
					File currentCoreFile = coreModelManager.getCoreFile();
					if ( currentCoreFile.getName().substring(0, currentCoreFile.getName().lastIndexOf(".")).compareTo(file.getName()) != 0 ){
						currentCoreFile.renameTo(coreConcernPath.toFile());
					}
				}
				coreFileToSave = true;
			}
		return coreFileAlreadyCreated;
	}
	
	/**
	 * Loads .core file when .jucm is loaded if .core
	 * file exists, does nothing otherwise.
	 * 
	 * @param file
	 * 		the .jucm file
	 * @param urn
	 * 		current URNspec
	 */
	public void loadCORE(IFile file, URNspec urn) {
		if( urn.getUrndef().getConcerns() != null && urn.getUrndef().getConcerns().size() > 0){
	        	
			// find the .core file(s)
		    File projectDir = file.getLocation().removeLastSegments(1).toFile();
		    File[] projectFileList = projectDir.listFiles();
		    for( File fileToCheck : projectFileList){
	        	String filename = fileToCheck.getName();
	        	String fileExtension = filename.substring(filename.lastIndexOf(".") + 1);
	        	if ( fileExtension.compareTo("core") == 0){
		        		
		       		coreModelManager = new COREmodelManager();
		        		
		       		try{
		       	    	coreModelManager.load(fileToCheck);
		       	    }catch( IOException e){
		       	    	
		       	    }
		       	    // Is this the .core file associated with our .jucm file?
		       	    if ( fileToCheck.getName().substring(0, fileToCheck.getName().lastIndexOf(".")).compareTo(
		       	    		file.getName().substring(0, fileToCheck.getName().lastIndexOf("."))) == 0){ //YES
		        	   
		        	  	Concern concern = (Concern)urn.getUrndef().getConcerns().get(0);
		        	  	COREConcern coreConcern = (COREConcern)coreModelManager.resource.getContents().get(0);
		        	  	concern.setCoreConcern(coreConcern);
		        	  	if ( concern.getCoreConcern() != null){
		         			coreModelManager.setConcern(concern);
		       	  			coreModelManager.setCOREConcern(coreConcern);
		       				coreModelManager.setCoreFile(fileToCheck);
		   	  				// Does the name of this Concern matches this COREConcern's name?
		        	 		if ( concern.getCoreConcern().getName().compareTo(coreConcern.getName()) == 0 ){ // YES
		       	  				if( concern.getName().compareTo(concern.getCoreConcern().getName()) != 0 ){
		       	  					concern.setName(concern.getCoreConcern().getName());
		       	  					}
			       	  			}
			       	  		}
		        	    }
		        	}
		        }
        }
	}
}