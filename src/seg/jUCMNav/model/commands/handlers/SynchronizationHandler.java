package seg.jUCMNav.model.commands.handlers;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;

import ca.mcgill.sel.ram.util.Constants;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.resourceManagement.MultiPageFileManager;
import seg.jUCMNav.editors.resourceManagement.UrnModelManager;
import seg.jUCMNav.model.commands.concerns.SynchronizeJUCMNavWithCoreCommand;
import seg.jUCMNav.model.util.MetadataHelper;
import urn.URNspec;



public class SynchronizationHandler extends AbstractHandler implements IHandler{

	private URNspec urn ;
	private IFile jucmFile;
	private boolean coreExistent;
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {	
		
	
		IWorkbenchWindow workbench = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		// get the selected file name and looking to find the exact same name of jucm file
        // how to get the most recent opened page which is JUCMNav editor 
		IEditorPart editor = workbench.getActivePage().getActiveEditor();
		
		// if IFileEditor is TextEditor how would we get the Editor which is jucm Editor 
		MultiPageFileManager fileManager = null;
		if( editor instanceof UCMNavMultiPageEditor){
              fileManager = ((UCMNavMultiPageEditor) editor).getFileManager();
		}else{
			IEditorReference[] edref = workbench.getActivePage().getEditorReferences();
			for(int i = edref.length-1; i>=0; i-- ){
				edref[i].getName().equals( jucmFile.getName());
				editor = (UCMNavMultiPageEditor)edref[i].getEditor(false);
			}
		}
        intializeSynchronize(workbench, fileManager);
        //((UCMNavMultiPageEditor) editor).setModel(urn);// setModel to the URNsepc newly loaded from file
        ((UCMNavMultiPageEditor) editor).setModel(urn);
        ((UCMNavMultiPageEditor) editor).getPageCount();
        String value = MetadataHelper.getMetaData( urn, "CoURN" );
        
        if( !(value!=null && value.equals("true") && urn.getUrndef().getConcerns().size()>0) ){
        	
        	showErrorDialog1(workbench);
        	return null;
        }
        if( !coreExistent ){
        	showErrorDialog2(workbench);
        	return null;
        }
       
        SynchronizeJUCMNavWithCoreCommand command = new SynchronizeJUCMNavWithCoreCommand(urn, jucmFile, coreExistent );
        command.execute();
        
        
        fileManager.setSaveType(fileManager.INIT);
        fileManager.doSave(new NullProgressMonitor());
        
        IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        ((UCMNavMultiPageEditor) editor).recreatePages();
     
        
		try{
			// if the Editor is not referenced to current File, file hasn't been opened with JUCM
			IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry().findEditor("seg.jUCMNav.MainEditor"); //$NON-NLS-1$
			
			activePage.openEditor( new FileEditorInput(jucmFile), desc.getId());
			//IDE.openEditor(activePage, jucmFile);
		}catch(Exception e){
			//display error messages
			MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
                    Messages.getString("UCMNavMultiPageEditor.Error"), "File cannot be opened."); //$NON-NLS-1$ 
		}
		return null;
	}
	
	
	
	public void  intializeSynchronize(IWorkbenchWindow workbench, MultiPageFileManager fileManager){
		if(workbench!=null){

			URNspec urn = null;

			ISelectionService selectionService = workbench.getSelectionService(); 
			ISelection selection = selectionService.getSelection(); 

			IFile file = null;
			if (selection instanceof IStructuredSelection) { 
				IStructuredSelection ssel = (IStructuredSelection) selection;
				System.out.println(selection.toString());
				Object obj = ssel.getFirstElement();
				try{
					file = (IFile) Platform.getAdapterManager().getAdapter(obj, IFile.class);
					if(file == null){
						if(obj instanceof IAdaptable){
							file = (IFile) ((IAdaptable)obj).getAdapter(IFile.class);
						}

					}
				} catch(Exception e){
					e.printStackTrace();
				}
				jucmFile = file;
				String filePath = file.getRawLocation().makeAbsolute().removeFileExtension().toOSString();
				String coreFileName =  file.getProject().toString().substring(2) + File.separator + file.getName().substring(0, file.getName().lastIndexOf(".")) + ".core";

				IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
				try {
					this.urn = fileManager.create(jucmFile);

				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				// we can find .core file to .jucm file then sychronize
				
				IResource resource = root.findMember(new Path(coreFileName));
				if( resource!=null && resource.exists() && resource instanceof IFile ){
					this.coreExistent = true;
				}else{
					// .core file doesn't exist, display error dialog
					coreExistent = false;
				}

			}	  
		}
		        
	}
	
	public void showErrorDialog2(IWorkbenchWindow workbench){
		
		MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
                Messages.getString("UCMNavMultiPageEditor.Error"), "The synchronized core file doesn't exist"); //$NON-NLS-1$ 
	}
	
	public void showErrorDialog1(IWorkbenchWindow workbench){
		
		MessageDialog.openError(workbench.getShell(), Messages.getString("UCMNavMultiPageEditor.Error")
    			, "It can't synchronize since the selected file is not concern-oriented");//$NON-NLS-1$ 
  
	}
	@Override
	public void setEnabled(Object evaluationContext) {	
	}
	
	
	@Override
	public boolean isEnabled() {
		return true;
	}
	

}
