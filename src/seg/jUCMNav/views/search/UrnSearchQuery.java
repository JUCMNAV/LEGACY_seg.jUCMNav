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
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.resourceManagement.UrnModelManager;
import seg.jUCMNav.model.util.URNElementFinder;
import urn.URNspec;
import urncore.URNmodelElement;

/**
 * Search the model for a certain string and produce a result.
 * 
 * @author jkealey
 * 
 */
public class UrnSearchQuery implements ISearchQuery {
    protected String name;
    protected UrnSearchResult result;
    protected int scope;
    
    protected IResource source;
    
    public final static int SCOPE_ALL_WORKSPACE =0;
    public final static int SCOPE_OPEN_ONLY =1;
    public final static int SCOPE_ACTIVE_ONLY =2;

    /**
     * Searches in all workspace for name.
     * 
     * @param name
     */
    public UrnSearchQuery(String name, int scope) {
        this.name = name;
        this.scope = scope;

    }

    public UrnSearchQuery(String name, int scope, IResource source) {
        this.name = name;
        this.source = source;
        this.scope = scope;

    }

    public boolean canRerun() {
        return true;
    }

    public boolean canRunInBackground() {
        return true;
    }

    public String getLabel() {
        return getName();
    }

    public String getName() {
        return name;
    }

    public ISearchResult getSearchResult() {
        if (result == null)
            result = new UrnSearchResult(this, Messages.getString("UrnSearchQuery_URNSearchResults") + getLabel() + "'"); //$NON-NLS-1$ //$NON-NLS-2$
        return result;
    }

    public IResource getSource() {
        return source;
    }

    public boolean isFileNameSearch() {
        return false;
    }

    public IStatus run(IProgressMonitor monitor) throws OperationCanceledException {
        monitor.beginTask("", 1000); //$NON-NLS-1$

        UrnSearchResult res = (UrnSearchResult) getSearchResult();
        res.removeAll();

        // if you don't specify a source, do all the workspace.
        if (getSource() == null) {
            setSource(ResourcesPlugin.getWorkspace().getRoot());
        }

        if (getSource() instanceof IWorkspaceRoot)
            searchInContainer(monitor, (IWorkspaceRoot) getSource());
        else if (getSource() instanceof IFile)
            searchInFile(monitor, (IFile) getSource());

        monitor.done();

        return new Status(IStatus.OK, JUCMNavPlugin.PLUGIN_ID, 0, "", null); //$NON-NLS-1$
    }

    public void searchInContainer(IProgressMonitor monitor, IContainer project) {
        // recurse into files.
        try {
            IResource[] members = project.members();
            for (int i = 0; i < members.length; i++) {
                IResource member = members[i];
                if (member instanceof IFile) {
                    searchInFile(monitor, (IFile) member);
                } else if (member instanceof IContainer) {
                    searchInContainer(monitor, (IContainer) member);
                    monitor.worked(1);
                }
            }
        } catch (CoreException ex) {
        }
    }

    public void searchInFile(IProgressMonitor monitor, IFile file) {
        if (this.scope == SCOPE_OPEN_ONLY) 
        {
            boolean found = false;
            if (PlatformUI.getWorkbench() != null && PlatformUI.getWorkbench().getWorkbenchWindows() != null) {
                IWorkbenchWindow[] windows = PlatformUI.getWorkbench().getWorkbenchWindows();
                for (int i = 0; i < windows.length; i++) {
                    IWorkbenchWindow window = windows[i];
                    if (window.getActivePage() != null) {

                        IWorkbenchPage page = window.getActivePage();
                        if (page != null) {
                            IEditorReference[] edref = page.getEditorReferences();
                            for (int j = 0; j < edref.length; j++) {
                                IEditorReference editor = edref[j];
                                try {
                                    if (editor.getEditorInput() instanceof IFileEditorInput) {
                                        IFileEditorInput input = (IFileEditorInput) editor.getEditorInput();
                                        if (input.getFile().getFullPath().toString().equalsIgnoreCase(file.getFullPath().toString()))
                                            found = true;
                                    }
                                } catch (PartInitException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (!found)
                                return;
                        }
                    }
                }
            }
        }
        else if (this.scope == SCOPE_ACTIVE_ONLY)
        {
            boolean found=false;
            if (PlatformUI.getWorkbench() != null && PlatformUI.getWorkbench().getWorkbenchWindows() != null) {
                IWorkbenchWindow[] windows = PlatformUI.getWorkbench().getWorkbenchWindows();
                for (int i = 0; i < windows.length; i++) {
                    IWorkbenchWindow window = windows[i];
                    if (window.getActivePage() != null) {
                        IWorkbenchPage page = window.getActivePage();
                        if (page.getActiveEditor()!=null && page.getActiveEditor().getEditorInput() instanceof IFileEditorInput)
                        {
                            IFileEditorInput input = (IFileEditorInput) page.getActiveEditor().getEditorInput();
                            if (input.getFile().getFullPath().toString().equalsIgnoreCase(file.getFullPath().toString()))
                                found = true;
                        }
                    }
                }
                if (!found) return;
            }
        }
        if (file.getFileExtension() != null && file.getFileExtension().equalsIgnoreCase(Messages.getString("UrnSearchQuery_4"))) { //$NON-NLS-1$

            // load file.
            UrnModelManager manager = new UrnModelManager();
            try {
                manager.load(file.getFullPath());
            } catch (IOException e) {
                return;
            }
            URNspec urn = manager.getModel();

            // get all matches in model.
            Collection c = URNElementFinder.findAllByNamePattern(urn, getName());

            // add them to result.
            for (Iterator iterator = c.iterator(); iterator.hasNext();) {
                Object o = (Object) iterator.next();
                int i = 1;
                if (o != null && o instanceof URNmodelElement) {
                    URNmodelElement modelElement = (URNmodelElement) o;
                    ((UrnSearchResult) getSearchResult()).addEntry(file, getName(), modelElement);
                }
            }
        }

        monitor.worked(1);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSource(IResource source) {
        this.source = source;
    }

}
