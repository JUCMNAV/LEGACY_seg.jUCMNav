/**
 * Eclipse Development using GEF and EMF: NetworkEditor example
 * 
 * (c) Copyright IBM Corp.
 *
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     Gunnar Wagenknecht - initial contribution
 * 
 */
package seg.jUCMNav.editors;

import java.util.EventObject;
import java.util.Iterator;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CommandStackListener;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import seg.jUCMNav.model.commands.IGlobalStackCommand;
import seg.jUCMNav.model.commands.cutcopypaste.PasteCommand;
import seg.jUCMNav.views.outline.UrnOutlinePage;
import urncore.IURNDiagram;

/**
 * This is a delegating command stack, which delegates everything a defined the CommandStack except event listners.
 * 
 * <p>
 * Event listeners registered to a <code>DelegatingCommandStack</code> will be informed whenever the underlying <code>CommandStack</code> changes. They will not
 * be registered to the underlying <code>CommandStack</code> but they will be informed about change events of them.
 * 
 * All ugly stkUrnSpec related code added by jkealey. This code is to allow DeleteMapCommands/CreateMapCommands to be undone because they can't be executed in
 * one of the UcmEditor's command stacks.
 * 
 * @author Gunnar Wagenknecht, jkealey
 */
public class DelegatingCommandStack extends CommandStack implements CommandStackListener {
    private static final Object[] EMPTY_OBJECT_ARRAY = new Object[] {};
    /** the current command stack */
    private CommandStack currentCommandStack;
    private IURNDiagram lastAffectedDiagram;

    // some of our commands add/delete map don't belong in any of the editor stacks.
    // this stack is only available if the last execute was a DeleteMapCommand or a CreateMapCommand. it is flushed after that.
    private CommandStack stkUrnSpec;
    private boolean unsavedChanges = false;

    /**
     * Creates a stack that delegates to another stack. This stack can be registered once and have its behaviour change dynamically.
     */
    public DelegatingCommandStack() {
        stkUrnSpec = new CommandStack();
        stkUrnSpec.addCommandStackListener(this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.CommandStack#canRedo()
     */
    public boolean canRedo() {

        if (stkUrnSpec.getRedoCommand() != null)
            return true;
        else {
            if (null == currentCommandStack)
                return false;
            else
                return currentCommandStack.canRedo();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.CommandStack#canUndo()
     */
    public boolean canUndo() {
        if (stkUrnSpec.getUndoCommand() != null)
            return true;
        else {

            if (null == currentCommandStack)
                return false;

            return currentCommandStack.canUndo();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.CommandStackListener#commandStackChanged(java.util.EventObject)
     */
    public void commandStackChanged(EventObject event) {
        notifyListeners();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.CommandStack#dispose()
     */
    public void dispose() {
        if (null != currentCommandStack)
            currentCommandStack.dispose();
    }

    /**
     * 
     * If the command adds or removes a new diagram, it executes the command in a special stack that will refresh the UI properly.
     * 
     */
    public void execute(Command command) {
        if (command instanceof PasteCommand) {
            PasteCommand pasteCommand = (PasteCommand) command;
            pasteCommand.build(); // typically built later during execution.
        }

        boolean b = checkSimpleCommand(command);
        if (b)
            return;

        if (null != currentCommandStack) {
            flushURNspecStack();
            currentCommandStack.execute(command);
        }
    }

    private boolean checkSimpleCommand(Command command) {

        if (command instanceof IGlobalStackCommand) {
            lastAffectedDiagram = ((IGlobalStackCommand) command).getAffectedDiagram();
            stkUrnSpec.execute(command);
            return true;
        }

        if (command instanceof CompoundCommand) {
            for (Iterator iter = ((CompoundCommand) command).getCommands().iterator(); iter.hasNext();) {
                Command internal = (Command) iter.next();

                // recurse
                boolean b = checkSimpleCommand(internal);
                if (b)
                    return true;
            }
        }

        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.CommandStack#flush()
     */
    public void flush() {
        if (null != currentCommandStack)
            currentCommandStack.flush();
    }

    /**
     * Clears the stack that is external to any of the individual editors.
     * 
     */
    public void flushURNspecStack() {

        if (stkUrnSpec.getCommands().length > 0) {
            if (stkUrnSpec.getUndoCommand() != null)
                unsavedChanges = true;
            stkUrnSpec.flush();
        }
        lastAffectedDiagram = null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.CommandStack#getCommands()
     */
    public Object[] getCommands() {
        if (null == currentCommandStack)
            return EMPTY_OBJECT_ARRAY;

        return currentCommandStack.getCommands();
    }

    /**
     * Returns the current <code>CommandStack</code>.
     * 
     * @return the current <code>CommandStack</code>
     */
    public CommandStack getCurrentCommandStack() {
        return currentCommandStack;
    }

    /**
     * 
     * @return the map for which the command stack was last changed.
     */
    public IURNDiagram getLastAffectedDiagram() {
        return lastAffectedDiagram;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.CommandStack#getRedoCommand()
     */
    public Command getRedoCommand() {
        if (stkUrnSpec.getRedoCommand() != null) {
            return stkUrnSpec.getRedoCommand();
        } else {
            if (null == currentCommandStack)
                return UnexecutableCommand.INSTANCE;

            return currentCommandStack.getRedoCommand();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.CommandStack#getUndoCommand()
     */
    public Command getUndoCommand() {
        if (stkUrnSpec.getUndoCommand() != null) {
            return stkUrnSpec.getUndoCommand();
        } else {

            if (null == currentCommandStack)
                return UnexecutableCommand.INSTANCE;

            return currentCommandStack.getUndoCommand();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.CommandStack#getUndoLimit()
     */
    public int getUndoLimit() {
        if (null == currentCommandStack)
            return -1;

        return currentCommandStack.getUndoLimit();
    }

    /**
     * @return A stack that is external to any of the individual editors.
     */
    public CommandStack getURNspecStack() {
        return stkUrnSpec;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.CommandStack#isDirty()
     */
    public boolean isDirty() {
        if (stkUrnSpec.getUndoCommand() != null || unsavedChanges) {
            return true;
        } else {

            if (null == currentCommandStack)
                return false;

            return currentCommandStack.isDirty();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.CommandStack#markSaveLocation()
     */
    public void markSaveLocation() {
        stkUrnSpec.flush();
        unsavedChanges = false;

        if (null != currentCommandStack)
            currentCommandStack.markSaveLocation();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.CommandStack#redo()
     */
    public void redo() {
        try {
            tryUnhookOutlineSelectionSynchronizer();

            if (stkUrnSpec.getRedoCommand() != null) {

                Command command = stkUrnSpec.getRedoCommand();

                if (command instanceof IGlobalStackCommand) {
                    lastAffectedDiagram = ((IGlobalStackCommand) command).getAffectedDiagram();
                }

                stkUrnSpec.redo();
            } else {
                if (null != currentCommandStack)
                    currentCommandStack.redo();
            }
        } finally {
            tryHookOutlineSelectionSynchronizer();
        }
    }

    /**
     * Sets the current <code>CommandStack</code>.
     * 
     * @param stack
     *            the <code>CommandStack</code> to set
     */
    public void setCurrentCommandStack(CommandStack stack) {
        if (currentCommandStack == stack)
            return;

        // remove from old command stack
        if (null != currentCommandStack)
            currentCommandStack.removeCommandStackListener(this);

        // set new command stack
        currentCommandStack = stack;

        // watch new command stack
        if (currentCommandStack != null)
            currentCommandStack.addCommandStackListener(this);

        // the command stack changed
        notifyListeners();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.CommandStack#setUndoLimit(int)
     */
    public void setUndoLimit(int undoLimit) {
        if (null != currentCommandStack)
            currentCommandStack.setUndoLimit(undoLimit);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "DelegatingCommandStack(" + currentCommandStack + ")"; //$NON-NLS-1$ //$NON-NLS-2$
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.CommandStack#undo()
     */
    public void undo() {
        try {
            tryUnhookOutlineSelectionSynchronizer();

            if (stkUrnSpec.getUndoCommand() != null) {
                Command command = stkUrnSpec.getUndoCommand();
                if (command instanceof IGlobalStackCommand) {
                    lastAffectedDiagram = ((IGlobalStackCommand) command).getAffectedDiagram();
                }

                stkUrnSpec.undo();
            } else {

                if (null != currentCommandStack)
                    currentCommandStack.undo();
            }
        } finally {
            tryHookOutlineSelectionSynchronizer();
        }
    }

    protected void tryUnhookOutlineSelectionSynchronizer() {
        /* Did not produce significant performance boost. 
         * UrnOutlinePage urnOutlinePage = getOutlinePage();
        if (urnOutlinePage != null)
            urnOutlinePage.unhookOutlineViewer(); // temporarily unhook selection synchronizer.
            */
    }

    protected void tryHookOutlineSelectionSynchronizer() {
        /* Did not produce significant performance boost. 
         * UrnOutlinePage urnOutlinePage = getOutlinePage();
        if (urnOutlinePage != null)
            urnOutlinePage.unhookOutlineViewer(); // rehook selection synchronizer.
            */
    }

    protected UrnOutlinePage getOutlinePage() {
        UrnOutlinePage urnOutlinePage = null;
        if (PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null
                && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() instanceof UCMNavMultiPageEditor) {
            UCMNavMultiPageEditor editor = (UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
            IContentOutlinePage outline = (IContentOutlinePage) editor.getAdapter(IContentOutlinePage.class);
            if (outline instanceof UrnOutlinePage) {
                urnOutlinePage = (UrnOutlinePage) outline;
            }
        }
        return urnOutlinePage;
    }
}