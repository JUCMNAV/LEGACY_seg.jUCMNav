/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import grl.ElementLink;
import grl.IntentionalElement;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import urn.URNspec;

/**
 * Command that create a contribution between 2 intentional element
 * 
 * @author Jean-François Roy
 * 
 */
public class AddStandardElementLinkCommand extends Command implements JUCMNavCommand {

    private IntentionalElement src, dest;
    private URNspec urnspec;
    private ElementLink link;

    /**
     * 
     */
    public AddStandardElementLinkCommand(URNspec urn, IntentionalElement source, ElementLink link) {

        this.urnspec = urn;
        this.link = link;
        this.src = source;

        setLabel(Messages.getString("AddStandardElementLinkCommand.addElementLink")); //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        // disallow source -> source connections
        if (src.equals(dest)) {
            return false;
        }
        return true;
    }

    /**
     * 
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

        // Set the source and destination
        src.getLinksSrc().add(link);
        dest.getLinksDest().add(link);

        urnspec.getGrlspec().getLinks().add(link);

        EvaluationStrategyManager.getInstance().calculateEvaluation();

        testPostConditions();
    }

    /**
     * Set the target endpoint for the connection.
     * 
     * @param target
     *            that target endpoint (a non-null IntentionalElement instance)
     */
    public void setTarget(IntentionalElement target) {
        this.dest = target;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert link != null : "pre link"; //$NON-NLS-1$
        assert urnspec != null : "pre urn spec"; //$NON-NLS-1$
        assert src != null : "pre src"; //$NON-NLS-1$
        assert dest != null : "pre dest"; //$NON-NLS-1$

        assert !urnspec.getGrlspec().getLinks().contains(link) : "pre link in spec"; //$NON-NLS-1$
        assert !src.getLinksSrc().contains(link) : "pre link in source"; //$NON-NLS-1$
        assert !dest.getLinksDest().contains(link) : "pre link in destination"; //$NON-NLS-1$

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert link != null : "post link"; //$NON-NLS-1$
        assert urnspec != null : "post urn spec"; //$NON-NLS-1$
        assert src != null : "post src"; //$NON-NLS-1$
        assert dest != null : "post dest"; //$NON-NLS-1$

        assert urnspec.getGrlspec().getLinks().contains(link) : "post link in spec"; //$NON-NLS-1$
        assert src.getLinksSrc().contains(link) : "post link in source"; //$NON-NLS-1$
        assert dest.getLinksDest().contains(link) : "post link in destination"; //$NON-NLS-1$

    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        // remove the source
        src.getLinksSrc().remove(link);
        dest.getLinksDest().remove(link);

        urnspec.getGrlspec().getLinks().remove(link);

        EvaluationStrategyManager.getInstance().calculateEvaluation();

        testPreConditions();
    }
}
