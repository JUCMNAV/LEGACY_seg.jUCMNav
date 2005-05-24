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

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CommandStackListener;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;

import seg.jUCMNav.model.commands.create.CreateMapCommand;
import seg.jUCMNav.model.commands.delete.DeleteMapCommand;
import ucm.map.Map;

/**
 * This is a delegating command stack, which delegates everything a defined the CommandStack except event listners.
 * 
 * <p>
 * Event listeners registered to a <code>DelegatingCommandStack</code> will be informed whenever the underlying <code>CommandStack</code> changes. They will
 * not be registered to the underlying <code>CommandStack</code> but they will be informed about change events of them.
 * 
 * @author Gunnar Wagenknecht
 */
public class DelegatingCommandStack extends CommandStack implements CommandStackListener {
    private static final Object[] EMPTY_OBJECT_ARRAY = new Object[] {};
    /** the current command stack */
    private CommandStack currentCommandStack;

    // some of our commands add/delete map don't belong in any of the editor stacks.
    // this stack is only available if the last execute was a DeleteMapCommand or a CreateMapCommand. it is flushed after that.
    private CommandStack URNspecStack;
    private Map lastAffectedMap;
    private boolean unsavedChanges = false;

    /**
     *  
     */
    public DelegatingCommandStack() {
        URNspecStack = new CommandStack();
        URNspecStack.addCommandStackListener(this);
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
        currentCommandStack.addCommandStackListener(this);

        // the command stack changed
        notifyListeners();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.CommandStack#canRedo()
     */
    public boolean canRedo() {

        if (URNspecStack.getRedoCommand() != null)
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
        if (URNspecStack.getUndoCommand() != null)
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
     * @see org.eclipse.gef.commands.CommandStack#dispose()
     */
    public void dispose() {
        if (null != currentCommandStack)
            currentCommandStack.dispose();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.CommandStack#execute(org.eclipse.gef.commands.Command)
     */
    public void execute(Command command) {
        if (command instanceof CompoundCommand && ((CompoundCommand) command).getCommands().size() == 1
                && ((CompoundCommand) command).getCommands().get(0) instanceof DeleteMapCommand) {
            lastAffectedMap = ((DeleteMapCommand) ((CompoundCommand) command).getCommands().get(0)).getMap();
            URNspecStack.execute(command);
        } else if (command instanceof CreateMapCommand) {
            lastAffectedMap = ((CreateMapCommand) command).getMap();
            URNspecStack.execute(command);

        } else if (null != currentCommandStack) {
            flushURNspecStack();
            currentCommandStack.execute(command);
        }
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

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.CommandStack#getRedoCommand()
     */
    public Command getRedoCommand() {
        if (URNspecStack.getRedoCommand() != null) {
            return URNspecStack.getRedoCommand();
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
        if (URNspecStack.getUndoCommand() != null) {
            return URNspecStack.getUndoCommand();
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

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.CommandStack#isDirty()
     */
    public boolean isDirty() {
        if (URNspecStack.getUndoCommand() != null || unsavedChanges) {
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
        URNspecStack.flush();
        unsavedChanges=false;

        if (null != currentCommandStack)
            currentCommandStack.markSaveLocation();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.CommandStack#redo()
     */
    public void redo() {
        if (URNspecStack.getRedoCommand() != null) {

            Command command = URNspecStack.getRedoCommand();
            if (command instanceof CompoundCommand && ((CompoundCommand) command).getCommands().size() == 1
                    && ((CompoundCommand) command).getCommands().get(0) instanceof DeleteMapCommand) {
                lastAffectedMap = ((DeleteMapCommand) ((CompoundCommand) command).getCommands().get(0)).getMap();
            } else if (command instanceof CreateMapCommand) {
                lastAffectedMap = ((CreateMapCommand) command).getMap();
            }

            URNspecStack.redo();
        } else {
            if (null != currentCommandStack)
                currentCommandStack.redo();
        }
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
     * @see org.eclipse.gef.commands.CommandStack#undo()
     */
    public void undo() {
        if (URNspecStack.getUndoCommand() != null) {
            Command command = URNspecStack.getUndoCommand();
            if (command instanceof CompoundCommand && ((CompoundCommand) command).getCommands().size() == 1
                    && ((CompoundCommand) command).getCommands().get(0) instanceof DeleteMapCommand) {
                lastAffectedMap = ((DeleteMapCommand) ((CompoundCommand) command).getCommands().get(0)).getMap();
            } else if (command instanceof CreateMapCommand) {
                lastAffectedMap = ((CreateMapCommand) command).getMap();
            }

            URNspecStack.undo();
        } else {

            if (null != currentCommandStack)
                currentCommandStack.undo();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "DelegatingCommandStack(" + currentCommandStack + ")";
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.CommandStackListener#commandStackChanged(java.util.EventObject)
     */
    public void commandStackChanged(EventObject event) {
        notifyListeners();
    }

    public Map getLastAffectedMap() {
        return lastAffectedMap;
    }

    public CommandStack getURNspecStack() {
        return URNspecStack;
    }

    public void flushURNspecStack() {

        if (URNspecStack.getCommands().length > 0) {
            if (URNspecStack.getUndoCommand() != null)
                unsavedChanges = true;
            URNspecStack.flush();
        }
        lastAffectedMap = null;

    }
}