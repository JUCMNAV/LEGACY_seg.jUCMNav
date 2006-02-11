/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import grl.EvaluationGroup;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;

/**
 * This command add an Evaluation Group to the model (for GRL scenario)
 * 
 * @author Jean-François Roy
 *
 */
public class CreateEvaluationGroupCommand extends Command implements JUCMNavCommand {

    private URNspec urn;
    private EvaluationGroup group;
    
    /**
     * 
     */
    public CreateEvaluationGroupCommand(URNspec urn) {
        this.urn = urn;
        
        group = (EvaluationGroup) ModelCreationFactory.getNewObject(urn, EvaluationGroup.class);
        setLabel("CreateEvaluationGroupCommand");
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return urn != null;
    }
    
    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        urn.getGrlspec().getEvaluationGroups().add(group);
        testPostConditions();
    }
    
    /* (non-Javadoc)
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPostConditions() {
        assert urn != null && urn.getGrlspec() != null && group != null : "post not null"; //$NON-NLS-1$
        assert urn.getGrlspec().getEvaluationGroups().contains(group) : "post group not in model"; //$NON-NLS-1$
    }

    /* (non-Javadoc)
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPreConditions() {
        assert urn != null && urn.getGrlspec() != null && group != null : "pre not null"; //$NON-NLS-1$
        assert !urn.getGrlspec().getEvaluationGroups().contains(group) : "pre groups not in model"; //$NON-NLS-1$
    }


    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        urn.getGrlspec().getEvaluationGroups().remove(group);
        testPreConditions();
    }
}
