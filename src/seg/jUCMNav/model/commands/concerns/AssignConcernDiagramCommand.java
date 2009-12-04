package seg.jUCMNav.model.commands.concerns;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urncore.Concern;
import urncore.IURNDiagram;

/**
 * Command to assign a concern to a diagram or to remove a concern from a diagram
 * 
 * @author gunterm
 */
public class AssignConcernDiagramCommand extends Command implements JUCMNavCommand {

    // the diagram to which to assign the concern
    private IURNDiagram diagram;
    // the concern to assign (can be null in which case the existing concern is removed)
    private Concern concern;
    // undo information
    private Concern oldConcern;

    /**
     * @param diagram
     *            to which a concern is assigned
     * @param concern
     *            to assign (can be null in which case the existing concern is removed)
     */
    public AssignConcernDiagramCommand(IURNDiagram diagram, Concern concern) {
        this.diagram = diagram;
        this.concern = concern;
        setLabel(Messages.getString("AssignConcernDiagramCommand.AssignConcernDiagramCommand")); //$NON-NLS-1$
    }

    /**
     * checks all conditions of testPreConditions that can be checked before execute()
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return testConditionNotNull();
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        // remember for undo
        oldConcern = diagram.getConcern();
        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        // assign the concern
        diagram.setConcern(concern);
        testPostConditions();
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        // re-assign old concern to the diagram
        diagram.setConcern(oldConcern);
        testPreConditions();
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert testConditionNotNull() : "post diagram not null "; //$NON-NLS-1$
        assert diagram.getConcern() == concern : "post concern was assigned"; //$NON-NLS-1$
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert testConditionNotNull() : "pre diagram not null"; //$NON-NLS-1$
        assert diagram.getConcern() == oldConcern : "pre concern is original"; //$NON-NLS-1$
    }

    /**
     * @return true if condition is met, false otherwise
     */
    private boolean testConditionNotNull() {
        return diagram != null;
    }

}