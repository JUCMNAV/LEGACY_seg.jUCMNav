package seg.jUCMNav.model.commands.transformations;

import grl.Contribution;
import grl.LinkRef;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;

/**
 * Changes the correlation attribute of GRL contributions
 * 
 * @author damyot
 */
public class ChangeCorrelationCommand extends Command implements JUCMNavCommand {
    private Vector contribs = new Vector();

    public ChangeCorrelationCommand(List linkRefs) {
        setLabel(Messages.getString("ChangeCorrelationCommand.ChangeCorrelation")); //$NON-NLS-1$
        for (Iterator iter = linkRefs.iterator(); iter.hasNext();) {
            contribs.add(iter.next());
        }
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        redo();
    }

    public boolean canExecute() {
        return super.canExecute();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        for (Iterator iter = contribs.iterator(); iter.hasNext();) {
            LinkRef linkRef = (LinkRef) iter.next();
            Contribution contrib = (Contribution) linkRef.getLink();
            contrib.setCorrelation(!contrib.isCorrelation());
        }

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        for (Iterator iter = contribs.iterator(); iter.hasNext();) {
            LinkRef linkRef = (LinkRef) iter.next();
            assert linkRef != null : "post no elem to change!"; //$NON-NLS-1$
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        for (Iterator iter = contribs.iterator(); iter.hasNext();) {
            LinkRef linkRef = (LinkRef) iter.next();
            assert linkRef != null : "post no element!"; //$NON-NLS-1$
        }
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        for (Iterator iter = contribs.iterator(); iter.hasNext();) {
            LinkRef linkRef = (LinkRef) iter.next();
            Contribution contrib = (Contribution) linkRef.getLink();
            contrib.setCorrelation(!contrib.isCorrelation());
        }

        testPreConditions();
    }
}