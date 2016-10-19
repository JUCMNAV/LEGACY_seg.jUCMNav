package seg.jUCMNav.model.commands.delete.internal;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;
import urn.dyncontext.DynamicContext;
import urn.dyncontext.DynamicContextGroup;
import urn.dyncontext.Timepoint;
import urn.dyncontext.TimepointGroup;

/**
 * Removes a Timepoint from its group.
 * 
 * @author aprajita
 * 
 */
public class RemoveTimepointCommand extends Command implements JUCMNavCommand {
	
	private Timepoint tp;
    private TimepointGroup group;
    private URNspec urn;

    /**
     * Constructor
     */
    public RemoveTimepointCommand(Timepoint tp) {
        this.tp = tp;
        setLabel("RemoveTimepointCommand"); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        group = (TimepointGroup) tp.getGroup();
        urn = group.getUrnspec();
        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        group.getTimepoints().remove(tp);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert urn != null && tp != null && group != null : "pre something null"; //$NON-NLS-1$
        assert group.getTimepoints().contains(tp) : "pre timepoint in group"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert urn!= null && tp != null && group != null : "post something null"; //$NON-NLS-1$
        assert !group.getTimepoints().contains(tp) : "post timepoint in group"; //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        group.getTimepoints().add(tp);

        testPreConditions();
    }

}
