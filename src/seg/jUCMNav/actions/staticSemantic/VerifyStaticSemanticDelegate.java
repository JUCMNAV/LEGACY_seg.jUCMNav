package seg.jUCMNav.actions.staticSemantic;

import java.util.Vector;

import seg.jUCMNav.staticSemantic.*;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.FileEditorInput;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.util.URNNamingHelper;
import urncore.URNmodelElement;

public class VerifyStaticSemanticDelegate implements IEditorActionDelegate {
    private UCMNavMultiPageEditor editor;

    public void setActiveEditor(IAction action, IEditorPart targetEditor) {
        editor = (UCMNavMultiPageEditor) targetEditor;

	}

    public void run(IAction action) {
    	if (editor!=null) {
    		Vector problems = new Vector();
    		StaticSemanticChecker.getInstance().check(editor.getModel(),problems);
    		refreshProblemView(problems);
    	}
    }

    public void selectionChanged(IAction action, ISelection selection) {

    }

    private void refreshProblemView(Vector problems)
    {
        if (editor != null) {
            IFile resource = ((FileEditorInput) editor.getEditorInput()).getFile();
            try {

                IMarker[] existingMarkers = resource.findMarkers(IMarker.PROBLEM, true, 3);
                for (int i = 0; i < existingMarkers.length; i++) {
                    IMarker marker = existingMarkers[i];
                    marker.delete();
                }
            } catch (CoreException ex) {
            	problems.add(new StaticCheckingMsg(ex.getMessage()));  //$NON-NLS-1$
            }
        
            if (problems.size() > 0) {

                for (int i=0;i< problems.size();++i) {
                    StaticCheckingMsg o =  (StaticCheckingMsg)problems.get(i); 
                    try {
                        IMarker marker = resource.createMarker(IMarker.PROBLEM);
                        marker.setAttribute(IMarker.SEVERITY, o.getSeverity());
                        marker.setAttribute(IMarker.MESSAGE, o.toString());
                        if (o.getLocation() instanceof URNmodelElement) {
                            URNmodelElement elem = (URNmodelElement) o.getLocation();
                            marker.setAttribute(IMarker.LOCATION, URNNamingHelper.getName(elem));
                            marker.setAttribute("EObject", ((URNmodelElement) o.getLocation()).getId()); //$NON-NLS-1$
                        } else if (o.getLocation() != null) {
                            marker.setAttribute(IMarker.LOCATION, o.getLocation().toString());
                        }

                    } catch (CoreException ex) {
                    	problems.add(new StaticCheckingMsg(ex.getMessage()));  //$NON-NLS-1$
                    }

                }
            } 
        }
    }
}
