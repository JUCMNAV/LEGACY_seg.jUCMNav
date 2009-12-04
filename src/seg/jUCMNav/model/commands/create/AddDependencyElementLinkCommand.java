/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import grl.Dependency;
import grl.IntentionalElement;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import urn.URNspec;

/**
 * Command to create a Dependency between two intentional elements. The direction of the dependency is dependee->depender (dest->src) (inverse of standard
 * Element Link)
 * 
 * @author Jean-François Roy
 * 
 */
public class AddDependencyElementLinkCommand extends Command implements JUCMNavCommand {

    private IntentionalElement depender, dependee;
    private URNspec urnspec;
    private Dependency link;

    /**
     * 
     */
    public AddDependencyElementLinkCommand(URNspec urn, IntentionalElement dependee, Dependency link) {
        this.urnspec = urn;
        this.link = link;
        this.dependee = dependee;

        setLabel(Messages.getString("AddDependencyElementLinkCommand.addDependency")); //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        // disallow source -> source connections
        if (dependee.equals(depender)) {
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
        depender.getLinksSrc().add(link);
        dependee.getLinksDest().add(link);

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
        this.depender = target;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert link != null : "pre link"; //$NON-NLS-1$
        assert urnspec != null : "pre urn spec"; //$NON-NLS-1$
        assert dependee != null : "pre dependee"; //$NON-NLS-1$
        assert depender != null : "pre depender"; //$NON-NLS-1$

        assert !urnspec.getGrlspec().getLinks().contains(link) : "pre link in spec"; //$NON-NLS-1$
        assert !depender.getLinksSrc().contains(link) : "pre link in source"; //$NON-NLS-1$
        assert !dependee.getLinksDest().contains(link) : "pre link in destination"; //$NON-NLS-1$

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert link != null : "post link"; //$NON-NLS-1$
        assert urnspec != null : "post urn spec"; //$NON-NLS-1$
        assert dependee != null : "post dependee"; //$NON-NLS-1$
        assert depender != null : "post depender"; //$NON-NLS-1$

        assert urnspec.getGrlspec().getLinks().contains(link) : "post link in spec"; //$NON-NLS-1$
        assert depender.getLinksSrc().contains(link) : "post link in source"; //$NON-NLS-1$
        assert dependee.getLinksDest().contains(link) : "post link in destination"; //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        // remove the source
        depender.getLinksSrc().remove(link);
        dependee.getLinksDest().remove(link);

        urnspec.getGrlspec().getLinks().remove(link);

        EvaluationStrategyManager.getInstance().calculateEvaluation();

        testPreConditions();
    }
}
