package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.AspectKind;
import ucm.map.PointcutKind;
import ucm.map.Stub;

/**
 * Changes the attributes of a UCM Stub
 * 
 * @author jkealey
 */
public class ChangeStubTypeCommand extends Command implements JUCMNavCommand {
    private Stub stub;
    private boolean newIsDynamic, newSync, newBlocking;
    private boolean oldIsDynamic, oldSync, oldBlocking;
    private PointcutKind newPointcutKind, oldPointcutKind;
    private AspectKind newAspectKind, oldAspectKind;

    public ChangeStubTypeCommand(Stub stub, boolean isDynamic, PointcutKind pointcutKind, boolean isSync, boolean isBlocking, AspectKind aspectKind) {
        this.stub = stub;
        this.newIsDynamic = isDynamic;
        this.newPointcutKind = pointcutKind;
        this.newSync = isSync;
        this.newBlocking = isBlocking;
        this.newAspectKind = aspectKind;

        setLabel(Messages.getString("ChangeStubTypeCommand.ChangeStubType")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        this.oldIsDynamic = stub.isDynamic();
        this.oldPointcutKind = stub.getAopointcut();
        this.oldSync = stub.isSynchronization();
        this.oldBlocking = stub.isBlocking();
        this.oldAspectKind = stub.getAspect();
        
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
        stub.setAopointcut(newPointcutKind);
        stub.setSynchronization(newSync);
        stub.setBlocking(newBlocking);
        stub.setAspect(newAspectKind);

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
        stub.setAopointcut(oldPointcutKind);
        stub.setSynchronization(oldSync);
        stub.setBlocking(oldBlocking);
        stub.setAspect(oldAspectKind);

        testPreConditions();
    }

    public Stub getStub() {
        return stub;
    }

    public void setStub(Stub stub) {
        this.stub = stub;
    }
}