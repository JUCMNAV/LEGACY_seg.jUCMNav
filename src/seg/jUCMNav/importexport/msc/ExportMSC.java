package seg.jUCMNav.importexport.msc;


import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

import org.eclipse.core.internal.resources.Workspace;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.extensionpoints.IURNExport;
import seg.jUCMNav.extensionpoints.IURNExportPrePostHooks;
import seg.jUCMNav.scenarios.ScenarioUtils;
import seg.jUCMNav.views.preferences.ScenarioExportPreferences;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;
import urn.URNspec;

public class ExportMSC implements IURNExport, IURNExportPrePostHooks {

	protected String oldFilename;
	protected String newFilename;
	
	public void export(URNspec urn, HashMap mapDiagrams, FileOutputStream fos) throws InvocationTargetException {
		// TODO Auto-generated method stub

	}

	public void export(URNspec urn, HashMap mapDiagrams, String filename) throws InvocationTargetException {

		// filename always ends with jucmscenarios
		if (!ScenarioExportPreferences.getExportType().equalsIgnoreCase("0")) //$NON-NLS-1$
			filename = filename.substring(0, filename.length()-"jucmscenarios".length()) + "jucm"; //$NON-NLS-1$ //$NON-NLS-2$
		
		Collection c = mapDiagrams.values();
		
		if (c.size()==0)
			return;
		
		this.newFilename = filename;
		            
		Vector v = new Vector();
		// TODO: find original filename
		v.add(new MscTraversalListener(this.oldFilename, this.newFilename, ScenarioExportPreferences.getExportType()));

		if (ScenarioExportPreferences.getExportAll().equalsIgnoreCase("all")) //$NON-NLS-1$
			ScenarioUtils.setActiveScenario(urn.getUcmspec(), v);
		else {
			EObject eo = ScenarioUtils.getActiveScenario(urn);
			
			if (eo instanceof ScenarioDef)
				ScenarioUtils.setActiveScenario((ScenarioDef)eo, v);
			else if (eo instanceof ScenarioGroup)
				ScenarioUtils.setActiveScenario((ScenarioGroup)eo, v);
			else 
				ScenarioUtils.setActiveScenario(urn.getUcmspec(), v);
		}
			
		
		

	}

	public void postHook(IWorkbenchPage page) {
		try {
			

			URI newFile = (new File(this.newFilename)).toURI().normalize();
			URI workspaceFile = ResourcesPlugin.getWorkspace().getRoot().getLocationURI().normalize();
						
			if (newFile.toString().startsWith(workspaceFile.toString()))
			{
				String path = newFile.toString().substring(workspaceFile.toString().length());
                path = path.replaceAll("%20", " ");

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
		this.oldFilename = ((FileEditorInput)editor.getEditorInput()).getFile().getName();
		
	}

}
