package seg.jUCMNav.views.property;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.views.property.tabbed.GEFTabbedPropertySheetPage;

public class StackHelper {

    public static CommandStack getStack(GEFTabbedPropertySheetPage property) {
        if (property == null || property.getEditor() == null)
            return null;
        else
            return property.getEditor().getCommandStack();
    }

    public static CommandStack getDelegatingStack() {
        if (PlatformUI.getWorkbench() == null || PlatformUI.getWorkbench().getActiveWorkbenchWindow() == null
                || PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() == null
                || PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() == null)
            return null;

        return getDelegatingStack(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor());
    }

    public static CommandStack getDelegatingStack(GEFTabbedPropertySheetPage property) {
        if (property == null || property.getEditor() == null || property.getEditor().getParent() == null)
            return null;
        else
            return property.getEditor().getParent().getDelegatingCommandStack();
    }

    public static CommandStack getDelegatingStack(IWorkbenchPart part) {
        UCMNavMultiPageEditor multiPage = getMultiPage(part);
        if (multiPage == null)
            return null;
        else
            return multiPage.getDelegatingCommandStack();
    }

    public static UCMNavMultiPageEditor getMultiPage(IWorkbenchPart part) {
        if (part instanceof UCMNavMultiPageEditor)
            return (UCMNavMultiPageEditor) part;

        if (part == null || part.getSite() == null || part.getSite().getWorkbenchWindow() == null
                || part.getSite().getWorkbenchWindow().getActivePage() == null)
            return null;

        IEditorPart editor = part.getSite().getWorkbenchWindow().getActivePage().getActiveEditor();
        if (editor instanceof UCMNavMultiPageEditor)
            return (UCMNavMultiPageEditor) editor;
        else
            return null;
    }

}
