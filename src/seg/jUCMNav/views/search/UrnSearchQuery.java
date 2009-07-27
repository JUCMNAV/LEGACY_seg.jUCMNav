package seg.jUCMNav.views.search;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.search.ui.ISearchResult;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editors.resourceManagement.UrnModelManager;
import seg.jUCMNav.model.util.URNElementFinder;
import urn.URNspec;
import urncore.URNmodelElement;

/**
 * Search the model for a certain string and produce a result. 
 * @author jkealey
 *
 */
public class UrnSearchQuery implements ISearchQuery
{
	protected String name;
	protected UrnSearchResult result;

	protected IResource source;

	/**
	 * Searches in all workspace for name. 
	 * 
	 * @param name
	 */
	public UrnSearchQuery(String name)
	{
		this.name = name;
		
	}

	public UrnSearchQuery(String name, IResource source)
	{
		this.name = name;
		this.source = source;
		
	}

	public boolean canRerun()
	{
		return true;
	}

	public boolean canRunInBackground()
	{
		return true;
	}


	public String getLabel()
	{
		return getName();
	}

	public String getName()
	{
		return name;
	}

	public ISearchResult getSearchResult()
	{
		if (result == null)
			result = new UrnSearchResult(this, "URN Search Results for '" + getLabel() + "'");
		return result;
	}

	public IResource getSource()
	{
		return source;
	}

	public boolean isFileNameSearch()
	{
		return false;
	}

	public IStatus run(IProgressMonitor monitor) throws OperationCanceledException
	{
		monitor.beginTask("", 1000);

		UrnSearchResult res = (UrnSearchResult) getSearchResult();
		res.removeAll();

		// if you don't specify a source, do all the workspace. 
		if (getSource() == null)
		{
			setSource(ResourcesPlugin.getWorkspace().getRoot());
		}

		if (getSource() instanceof IWorkspaceRoot)
			searchInContainer(monitor, (IWorkspaceRoot)getSource());
		else  if (getSource() instanceof IFile)
			searchInFile(monitor, (IFile) getSource());
		
		monitor.done();

		return new Status(IStatus.OK, JUCMNavPlugin.PLUGIN_ID, 0, "", null);
	}

	public void searchInContainer(IProgressMonitor monitor, IContainer project)
	{
		// recurse into files.
		try
		{
			IResource[] members = project.members();
			for (int i = 0; i < members.length; i++)
			{
				IResource member = members[i];
				if (member instanceof IFile)
				{
					searchInFile(monitor, (IFile)member);
				} 
				else if (member instanceof IContainer)
				{
					searchInContainer(monitor, (IContainer)member);
					monitor.worked(1);
				}
			}
		}
		catch (CoreException ex)
		{
		}
	}

	public void searchInFile(IProgressMonitor monitor, IFile file)
	{
		if (file.getFileExtension()!=null && file.getFileExtension().equalsIgnoreCase("jucm")) {
			
			// load file. 
			UrnModelManager manager = new UrnModelManager();
			try
			{
				manager.load(file.getFullPath());
			}
			catch (IOException e)
			{
				return;
			}
			URNspec urn = manager.getModel();

			// get all matches in model. 
			Collection c = URNElementFinder.findAllByNamePattern(urn, getName());
			
			// add them to result. 
			for (Iterator iterator = c.iterator(); iterator.hasNext();)
			{
				Object o = (Object) iterator.next();
				int i=1;
				if (o!=null && o instanceof URNmodelElement)
				{
					URNmodelElement modelElement = (URNmodelElement) o;
					((UrnSearchResult) getSearchResult()).addEntry(file,  getName(), modelElement);
				}
			}
		}
		
		monitor.worked(1);
	}

	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setSource(IResource source)
	{
		this.source = source;
	}
	

}
