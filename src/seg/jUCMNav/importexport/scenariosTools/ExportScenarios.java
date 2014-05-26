package seg.jUCMNav.importexport.scenariosTools;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.core.internal.resources.Workspace;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.extensionpoints.IURNExport;
import seg.jUCMNav.extensionpoints.IURNExportPrePostHooks;
import ucm.scenario.ScenarioGroup;
import urn.URNspec;

public class ExportScenarios {

	protected String oldFilename;
	protected String newFilename;

	public ExportScenarios() {
		super();
	}

    public void export(URNspec urn, HashMap mapDiagrams, FileOutputStream fos) throws InvocationTargetException {
        // TODO Auto-generated method stub

    }
	
	public boolean scenarioDefExists(URNspec urn) {
	    // Is there at least one scenario definition?
	    for (Iterator groups = urn.getUcmspec().getScenarioGroups().iterator(); groups.hasNext();) {
	        if (((ScenarioGroup) groups.next()).getScenarios().size() > 0) {
	            return true;
	        }
	    }
	    return false;
	}

	public void postHook(IWorkbenchPage page) {
	    try {
	        URI newFile = (new File(this.newFilename)).toURI().normalize();
	        URI workspaceFile = ResourcesPlugin.getWorkspace().getRoot().getLocationURI().normalize();
	
	        if (newFile.toString().toLowerCase().startsWith(workspaceFile.toString().toLowerCase())) {
	            String path = newFile.toString().substring(workspaceFile.toString().length());
	            path = path.replaceAll("%20", " "); //$NON-NLS-1$ //$NON-NLS-2$
	
	            IFile file = (IFile) ((Workspace) ResourcesPlugin.getWorkspace()).newResource(new Path(path), IResource.FILE);
	            file.getParent().refreshLocal(1, null);
	            IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(file.getName());
	
	            if (desc == null)
	                desc = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor("xml.xml"); //$NON-NLS-1$
	            page.openEditor(new FileEditorInput(file), desc.getId());
	        }
	
	    } catch (Exception ex) {
	        // hide any errors.
	    }
	
	}

	public void preHook(UCMNavMultiPageEditor editor) {
	    this.oldFilename = ((FileEditorInput) editor.getEditorInput()).getFile().getName();
	}

}