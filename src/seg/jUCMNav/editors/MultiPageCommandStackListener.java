package seg.jUCMNav.editors;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CommandStackListener;
import org.eclipse.ui.PartInitException;

import ucm.map.Map;


/**
 * This class listens for command stack changes of the pages contained in this editor. It decides if the editor is dirty or not and refreshes the pages if
 * maps are added/removed.
 * 
 * @author Gunnar Wagenknecht, jkealey
 */
public class MultiPageCommandStackListener implements CommandStackListener {

    private final UCMNavMultiPageEditor editor;

    /**
     * @param editor
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
     * @param event
     */
    private void commandStackVerifyPages(EventObject event) {
        if (this.editor.getPageCount() != this.editor.getModel().getUcmspec().getMaps().size() && event.getSource() instanceof DelegatingCommandStack) {
            Map mapChanged = ((DelegatingCommandStack) event.getSource()).getLastAffectedMap();

            // was added
            if (this.editor.getModel().getUcmspec().getMaps().contains(mapChanged)) {
                UcmEditor u = new UcmEditor(this.editor);
                u.setModel(mapChanged);

                try {
                    this.editor.addPage(u, this.editor.getEditorInput());
                } catch (PartInitException e) {
                    e.printStackTrace();
                }

                // add command stacks
                this.editor.getMultiPageCommandStackListener().addCommandStack(u.getCommandStack());

                this.editor.getMultiPageTabManager().refreshPageNames();
                this.editor.setActivePage(this.editor.getModel().getUcmspec().getMaps().indexOf(mapChanged));

            } else // was deleted
            {
                int i;
                for (i = 0; i < this.editor.getPageCount(); i++) {
                    if (((UcmEditor) this.editor.getEditor(i)).getModel().equals(mapChanged)) {
                        break;
                    }
                }

                // remove command stacks
                this.editor.getMultiPageCommandStackListener().removeCommandStack(((UcmEditor) this.editor.getEditor(i)).getCommandStack());

                this.editor.removePage(i);

                this.editor.getMultiPageTabManager().currentPageChanged();
            }
        } else {
            // if the command stack changed directly instead of calling DelegatingCommandStack, we need to inform it.
            // we do this so that we cannot undo a create/delete map after another action has been performed.
            if (!(event.getSource() instanceof DelegatingCommandStack))
                this.editor.getDelegatingCommandStack().flushURNspecStack();

        }
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