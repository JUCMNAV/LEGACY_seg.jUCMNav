package seg.jUCMNav.editors;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CommandStackListener;
import org.eclipse.ui.PartInitException;

import seg.jUCMNav.editors.actionContributors.ModeComboContributionItem;
import seg.jUCMNav.editparts.URNRootEditPart;
import seg.jUCMNav.model.commands.transformations.ChangeUCMDiagramOrderCommand;
import ucm.map.UCMmap;
import urncore.IURNDiagram;

/**
 * This class listens for command stack changes of the pages contained in this editor. It decides if the editor is dirty or not and refreshes the pages if maps
 * are added/removed.
 * 
 * @author Gunnar Wagenknecht, jkealey
 */
public class MultiPageCommandStackListener implements CommandStackListener {

    private final UCMNavMultiPageEditor editor;

    /**
     * @param editor
     *            jUCMNav
     */
    MultiPageCommandStackListener(UCMNavMultiPageEditor editor) {
        this.editor = editor;
    }

    /** the observed command stacks */
    private List commandStacks = new ArrayList(2);

    /**
     * Adds a <code>CommandStack</code> to observe.
     * 
     * @param commandStack
     */
    public void addCommandStack(CommandStack commandStack) {
        commandStacks.add(commandStack);
        commandStack.addCommandStackListener(this);
    }

    /**
     * What should be done when the stack changes.
     * 
     * @param event
     *            the command stack changed event.
     * 
     * @see org.eclipse.gef.commands.CommandStackListener#commandStackChanged(java.util.EventObject)
     */
    public void commandStackChanged(EventObject event) {
        if (((CommandStack) event.getSource()).isDirty()) {
            // at least one command stack is dirty,
            // so the multi page editor is dirty too
            this.editor.setDirty(true);
        } else {
            // probably a save, we have to check all command stacks
            boolean oneIsDirty = false;
            for (Iterator stacks = commandStacks.iterator(); stacks.hasNext();) {
                CommandStack stack = (CommandStack) stacks.next();
                if (stack.isDirty()) {
                    oneIsDirty = true;
                    break;
                }
            }
            this.editor.setDirty(oneIsDirty);
        }

        // update the contextual menus / toolbars
        this.editor.getActionRegistryManager().updateStackActions();

        // check to see if there are any new/deleted pages; will have to update tabs.
        commandStackVerifyPages(event);
    }

    /**
     * Updates the editor when a new page is added/removed. Keeps the open editors in synch with the omdel.
     * 
     * @param event
     *            the command stack changed event.
     */
    private void commandStackVerifyPages(EventObject event) {
        if (this.editor.getPageCount() != this.editor.getModel().getUrndef().getSpecDiagrams().size() && event.getSource() instanceof DelegatingCommandStack) {
            IURNDiagram diagramChanged = ((DelegatingCommandStack) event.getSource()).getLastAffectedDiagram();

            // was added
            if (this.editor.getModel().getUrndef().getSpecDiagrams().contains(diagramChanged)) {
                if (this.editor.getPageCount() <  this.editor.getModel().getUrndef().getSpecDiagrams().size())
                    createEditor(diagramChanged);

            } else // was deleted
            {
                if (this.editor.getPageCount() >  this.editor.getModel().getUrndef().getSpecDiagrams().size() )
                    removeEditor(diagramChanged);
            }
        } else {
            // if the command stack changed directly instead of calling DelegatingCommandStack, we need to inform it.
            // we do this so that we cannot undo a create/delete map after another action has been performed.
            if (!(event.getSource() instanceof DelegatingCommandStack))
                this.editor.getDelegatingCommandStack().flushURNspecStack();
            else
            {
                if (this.editor.getDelegatingCommandStack().getRedoCommand() instanceof ChangeUCMDiagramOrderCommand ||  this.editor.getDelegatingCommandStack().getUndoCommand() instanceof ChangeUCMDiagramOrderCommand)
                {
                    IURNDiagram diag =this.editor.getDelegatingCommandStack().getLastAffectedDiagram();
                    if (diag.getUrndefinition()!=null) 
                    {
                        int modelIndex = diag.getUrndefinition().getSpecDiagrams().indexOf(diag);
                        if (((UrnEditor)editor.getEditor(modelIndex)).getModel()!=diag) {
                            removeEditor(diag);
                            // might get done by notifications
                            if (this.editor.getPageCount() != diag.getUrndefinition().getSpecDiagrams().size())
                                commandStackVerifyPages(event);
                        }
                    }
                }
            }

        }
    }

    private void removeEditor(IURNDiagram diagramChanged) {
        int i;
        for (i = 0; i < this.editor.getPageCount(); i++) {
            if (((UrnEditor) this.editor.getEditor(i)).getModel().equals(diagramChanged)) {
                // remove command stacks
                this.editor.getMultiPageCommandStackListener().removeCommandStack(((UrnEditor) this.editor.getEditor(i)).getCommandStack());
                this.editor.removePage(i);

                break;
            }
        }

        if (diagramChanged != null)
            diagramChanged.eAdapters().remove(this.editor);

        this.editor.getMultiPageTabManager().currentPageChanged();
    }

    private void createEditor(IURNDiagram diagramChanged) {
        UrnEditor u = null;
        if (diagramChanged instanceof UCMmap) {
            u = new UcmEditor(this.editor);
        } else { // if(diagramChanged instanceof GRLGraph){
            u = new GrlEditor(this.editor);
        }
        u.setModel(diagramChanged);

        try {
            this.editor.addPage(this.editor.getModel().getUrndef().getSpecDiagrams().indexOf(diagramChanged), u, this.editor.getEditorInput());
        } catch (PartInitException e) {
            e.printStackTrace();
        }

        // add command stacks
        this.editor.getMultiPageCommandStackListener().addCommandStack(u.getCommandStack());

        diagramChanged.eAdapters().add(this.editor);

        // set the mode to that already in use
        if (!ModeComboContributionItem.isLocal() && this.editor.getPageCount() >= 1) {
            int mode = ((URNRootEditPart) ((UrnEditor) this.editor.getEditor(0)).getGraphicalViewer().getRootEditPart()).getMode();
            ((URNRootEditPart) u.getGraphicalViewer().getRootEditPart()).setMode(mode);
        }

        this.editor.getMultiPageTabManager().refreshPageNames();
        this.editor.setActivePage(this.editor.getModel().getUrndef().getSpecDiagrams().indexOf(diagramChanged));
        u.getGraphicalViewer().select((EditPart) u.getGraphicalViewer().getEditPartRegistry().get(diagramChanged));
    }

    /**
     * Disposed the listener
     */
    public void dispose() {
        for (Iterator stacks = commandStacks.iterator(); stacks.hasNext();) {
            ((CommandStack) stacks.next()).removeCommandStackListener(this);
        }
        commandStacks.clear();
    }

    /**
     * Marks every observed command stack beeing saved. This method should be called whenever the editor/model was saved.
     */
    public void markSaveLocations() {
        for (Iterator stacks = commandStacks.iterator(); stacks.hasNext();) {
            CommandStack stack = (CommandStack) stacks.next();
            stack.markSaveLocation();
        }

        // bug 447
        if (editor != null && editor.getDelegatingCommandStack() != null)
            editor.getDelegatingCommandStack().markSaveLocation();
    }

    /**
     * Removes a <code>CommandStack</code> that was observed.
     * 
     * @param commandStack
     */
    public void removeCommandStack(CommandStack commandStack) {
        commandStacks.remove(commandStack);
        commandStack.removeCommandStackListener(this);
    }
}