package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.Stub;

/**
 * Changes an element's color
 * 
 * @author jkealey
 */
public class ChangeStubTypeCommand extends Command implements JUCMNavCommand {
    private Stub stub;
    private boolean newIsDynamic, newIsPointcut, newSync, newBlocking;
    private boolean oldIsDynamic, oldIsPointcut, oldSync, oldBlocking;

    public ChangeStubTypeCommand(Stub stub, boolean isDynamic, boolean isPointcut, boolean isSync, boolean isBlocking) {
        this.stub = stub;
        this.newIsDynamic = isDynamic;
        this.newIsPointcut = isPointcut;
        this.newSync = isSync;
        this.newBlocking = isBlocking;

        setLabel(Messages.getString("ChangeStubTypeCommand.ChangeStubType")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        this.oldIsDynamic = stub.isDynamic();
        this.oldIsPointcut = stub.isPointcut();
        this.oldSync = stub.isSynchronization();
        this.oldBlocking = stub.isBlocking();
        
        redo();
    }

    public boolean canExecute() {
        // don't make it a static stub if has multiple bindings.
        if (!newIsDynamic && stub.getBindings().size() > 1)
            return false;
        return super.canExecute();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        stub.setDynamic(newIsDynamic);
        stub.setPointcut(newIsPointcut);
        stub.setSynchronization(newSync);
        stub.setBlocking(newBlocking);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert stub != null : "post no elem to change!"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert stub != null : "pre no elem to change"; //$NON-NLS-1$

    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        stub.setDynamic(oldIsDynamic);
        stub.setPointcut(oldIsPointcut);
        stub.setSynchronization(oldSync);
        stub.setBlocking(oldBlocking);

        testPreConditions();
    }

    public Stub getStub() {
        return stub;
    }

    public void setStub(Stub stub) {
        this.stub = stub;
    }
}